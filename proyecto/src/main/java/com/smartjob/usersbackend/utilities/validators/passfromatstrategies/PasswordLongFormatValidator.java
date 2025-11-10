package com.smartjob.usersbackend.utilities.validators.passfromatstrategies;

import com.smartjob.usersbackend.utilities.validators.PasswordFormatValidator;
import java.util.regex.Pattern;


public class PasswordLongFormatValidator implements PasswordFormatValidator {
    private final String passRegex = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
    private final Pattern passPattern = Pattern.compile(passRegex);
    private final String formatExplanation = "Mínimo 8 caracteres, al menos una mayúscula, al menos un número";

    @Override
    public boolean isPasswordValidFormat(String password) {
        return passPattern.matcher(password).matches();
    }

    @Override
    public String getFormatExplanation() {
        return formatExplanation;
    }
}
