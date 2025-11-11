package com.smartjob.usersbackend.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartjob.usersbackend.adapter.in.web.models.ErrorResponse;
import com.smartjob.usersbackend.core.model.User;
import com.smartjob.usersbackend.core.usecases.AddUserUsecase;
import com.smartjob.usersbackend.exceptions.DataCreationException;
import com.smartjob.usersbackend.exceptions.DataFormatErrorException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(UsersController.class)
public class UsersControllerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AddUserUsecase addUserUsecase;

    String requestBodyOK = """
                {
                    "name": "Juan Rodriguez",
                    "email": "juan@rodriguez.org",
                    "password": "hunter2",
                    "phones": [
                                {
                                    "number": "1234567",
                                    "cityCode": "1",
                                    "countryCode": "57"
                                },
                                {
                                    "number": "8465334",
                                    "cityCode": "9",
                                    "countryCode": "54"
                                }
                            ]
                }
                """;

    String requestBodyWrongMail = """
                {
                    "name": "Juan Rodriguez",
                    "email": "juan.org",
                    "password": "hunter2",
                    "phones": [
                                {
                                    "number": "1234567",
                                    "cityCode": "1",
                                    "countryCode": "57"
                                },
                                {
                                    "number": "8465334",
                                    "cityCode": "9",
                                    "countryCode": "54"
                                }
                            ]
                }
                """;

    @Test
    @SneakyThrows
    @DisplayName("Add user without body returns bad request exception")
    void noBodyReturnsUnsupportedMediaTypeException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SneakyThrows
    @DisplayName("Add user with empty body returns UnsupportedMediaType Exception")
    void emptyBodyReturnsBadRequestException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ }"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SneakyThrows
    @DisplayName("Add user with invalid email returns BadRequest Exception")
    void invalidEmailReturnsBadRequestException() throws Exception {
        String errorMessage = "ASD123";

        when(addUserUsecase.addUser(any(User.class))).thenThrow(new DataFormatErrorException(errorMessage));

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyWrongMail))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ErrorResponse response = objectMapper.readValue(responseBody, ErrorResponse.class);

        assertThat(response.getMensaje()).isEqualTo(errorMessage);
    }

    @Test
    @SneakyThrows
    @DisplayName("Add user returns internal server error when DataCreationException is thrown in use case")
    void useCaseReturnsDataException() throws Exception {
        String errorMessage = "ASD123";

        when(addUserUsecase.addUser(any(User.class))).thenThrow(new DataCreationException(errorMessage));

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyOK))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError()).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ErrorResponse response = objectMapper.readValue(responseBody, ErrorResponse.class);

        assertThat(response.getMensaje()).isEqualTo(errorMessage);
    }

    @Test
    @SneakyThrows
    @DisplayName("Add user returns internal server error when DataFormatError is thrown in use case")
    void useCaseReturnsDataFormatError() throws Exception {
        String errorMessage = "ASD123";

        when(addUserUsecase.addUser(any(User.class))).thenThrow(new DataFormatErrorException(errorMessage));

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyOK))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ErrorResponse response = objectMapper.readValue(responseBody, ErrorResponse.class);

        assertThat(response.getMensaje()).isEqualTo(errorMessage);
    }

    @Test
    @SneakyThrows
    @DisplayName("Add user with correct body returns OK")
    void correctBodyReturnsOK() throws Exception {
        User mockUser = User.builder().email("juan@rodriguez.org")
                .name("").password("asd").telephonesList(new ArrayList<>()).build();

        when(addUserUsecase.addUser(any(User.class))).thenReturn(mockUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyOK))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
