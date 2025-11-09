package com.smartjob.usersbackend.core.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private final Long id;

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be in valid format")
    private String email;

    private String passwordRandomSalt;
    private String token;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime lastLogin;
    private Boolean isActive;

    //Children
    private List<Telephone> telephonesList;

    public static class TelephoneBuilder {
        public TelephoneBuilder telephoneBuilder(List<Telephone.TelephoneBuilder> telephoneBuilder) {
            List<Telephone> telephonesList = telephoneBuilder.stream()
                    .map(Telephone.TelephoneBuilder::build)
                    .toList();
            return this;
        }
    }

}

