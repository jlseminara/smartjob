package com.smartjob.usersbackend.utilities.validators.passfromatstrategies;

import com.smartjob.usersbackend.utilities.validators.PasswordFormatValidator;
import java.util.regex.Pattern;


public class PasswordShortFormatValidator implements PasswordFormatValidator {
    private final String passRegex = "^(?=.*[^A-Za-z0-9]).{6,}$";
    private final Pattern passPattern = Pattern.compile(passRegex);
    private final String formatExplanation = "Mínimo 6 caracteres, al menos un símbolo (no letra ni número)";

    @Override
    public boolean isPasswordValidFormat(String password) {
        return passPattern.matcher(password).matches();
    }

    @Override
    public String getFormatExplanation() {
        return formatExplanation;
    }
}
