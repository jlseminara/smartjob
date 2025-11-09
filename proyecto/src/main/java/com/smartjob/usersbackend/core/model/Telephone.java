package com.smartjob.usersbackend.core.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Telephone {
    @NotBlank(message = "Telephone number must not be blank")
    private final String number;

    @NotBlank(message = "Citycode must not be blank")
    private final String cityCode;

    @NotBlank(message = "Countrycode must not be blank")
    private final String countryCode;
}
