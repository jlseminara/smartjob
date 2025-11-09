package com.smartjob.usersbackend.adapter.usecases;

import com.smartjob.usersbackend.core.model.User;
import com.smartjob.usersbackend.core.port.out.persistence.UsersRepository;
import com.smartjob.usersbackend.exceptions.DataCreationException;
import com.smartjob.usersbackend.exceptions.DataFormatErrorException;
import com.smartjob.usersbackend.utilities.validators.PasswordFormatValidator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import java.util.ArrayList;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = AddUserUsecaseImpl.class)
public class AddUserUsecaseImplTests {

    @MockitoBean
    private UsersRepository usersRepository;

    @MockitoBean
    private PasswordFormatValidator passwordFormatValidator;

    @Autowired
    private AddUserUsecaseImpl addUserUsecase;

    @Test
    @SneakyThrows
    @DisplayName("Method addUser() - Invalid email throws DataFormatError exception")
    void addUserInvalidEmailThrowsDataFormatError() throws Exception {
        User mockUser = User.builder().name("asd").email("juan.org").password("asd")
                .telephonesList(new ArrayList<>()).build();

        RuntimeException expectedException = assertThrows(RuntimeException.class, () ->
                addUserUsecase.addUser(mockUser));

        assertThat(expectedException).isNotNull();
        assertThat(expectedException).isInstanceOf(DataFormatErrorException.class);
    }

    @Test
    @SneakyThrows
    @DisplayName("Method addUser() - Invalid password format throws DataFormatError exception")
    void addUserInvalidPasswordFormatThrowsDataFormatError() throws Exception {
        User mockUser = User.builder().name("asd").email("juan@rodriguez.org").password("asd")
                .telephonesList(new ArrayList<>()).build();

        RuntimeException expectedException = assertThrows(RuntimeException.class, () ->
                addUserUsecase.addUser(mockUser));

        assertThat(expectedException).isNotNull();
        assertThat(expectedException).isInstanceOf(DataFormatErrorException.class);
    }

    @Test
    @SneakyThrows
    @DisplayName("Method addUser() - Data access error throws DataCreationException exception")
    void addUserDataAccessErrorThrowsDataFormatErrorDataCreationException() throws Exception {
        User mockUser = User.builder().name("asd").email("juan@rodriguez.org").password("asdf")
                .telephonesList(new ArrayList<>()).build();

        when(passwordFormatValidator.isPasswordValidFormat(anyString())).thenReturn(true);
        when(usersRepository.addUser(any(User.class))).thenThrow(new InvalidDataAccessResourceUsageException(""));

        RuntimeException expectedException = assertThrows(RuntimeException.class, () ->
                addUserUsecase.addUser(mockUser));

        assertThat(expectedException).isNotNull();
        assertThat(expectedException).isInstanceOf(DataCreationException.class);
    }

    @Test
    @SneakyThrows
    @DisplayName("Method addUser() - Data access error throws DataIntegrityViolation exception")
    void addUserDataIntegrityViolationThrowsDataFormatErrorDataCreationException() throws Exception {
        User mockUser = User.builder().name("asd").email("juan@rodriguez.org").password("asdf")
                .telephonesList(new ArrayList<>()).build();

        when(passwordFormatValidator.isPasswordValidFormat(anyString())).thenReturn(true);
        when(usersRepository.addUser(any(User.class))).thenThrow(new DataIntegrityViolationException(""));

        RuntimeException expectedException = assertThrows(RuntimeException.class, () ->
                addUserUsecase.addUser(mockUser));

        assertThat(expectedException).isNotNull();
        assertThat(expectedException).isInstanceOf(DataCreationException.class);
    }

    @Test
    @SneakyThrows
    @DisplayName("Method addUser() - Returns User object OK")
    void addUserReturnsUserObjectOk() throws Exception {
        User mockUser = User.builder().name("asd").email("juan@rodriguez.org").password("asdf")
                .telephonesList(new ArrayList<>()).build();

        when(passwordFormatValidator.isPasswordValidFormat(anyString())).thenReturn(true);
        when(usersRepository.addUser(any(User.class))).thenReturn(Optional.of(mockUser));

        var result = addUserUsecase.addUser(mockUser);

        assertThat(result).isNotNull();
    }
}
