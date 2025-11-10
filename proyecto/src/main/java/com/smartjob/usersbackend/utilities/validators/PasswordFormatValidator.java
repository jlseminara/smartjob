package com.smartjob.usersbackend.utilities.validators;

public interface PasswordFormatValidator {
    boolean isPasswordValidFormat(String password);
    String getFormatExplanation();
}
