package com.smartjob.usersbackend.utilities.validators;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;


@Component
public class PasswordFormatValidator {

    @Value("${custom.max_page_size:200}")
    private int password_format;

    // Mínimo 8 caracteres, al menos una mayúscula, al menos un número
    private static final String PASS_REGEX_1 =  "^(?=.*[A-Z])(?=.*\\d).{8,}$";
    private static final Pattern PASS_PATTERN_1 = Pattern.compile(PASS_REGEX_1);
    private static final String FORMAT_EXPLANATION_1 = "Mínimo 8 caracteres, al menos una mayúscula, al menos un número";

    // Mínimo 6 caracteres, al menos un símbolo (no letra ni número)
    private static final String PASS_REGEX_2 = "^(?=.*[^A-Za-z0-9]).{6,}$";
    private static final Pattern PASS_PATTERN_2 = Pattern.compile(PASS_REGEX_2);
    private static final String FORMAT_EXPLANATION_2 = "Mínimo 6 caracteres, al menos un símbolo (no letra ni número)";


    /*
     Los switsc son siempre una mala opcion y algo mas profesional como un patron stragegy es optimo
     Pero no se me dio especificaciones acerca de la "configuracion" y esto es lo mas rapido
     */
    public boolean isPasswordValidFormat(String password) {
        Pattern validator = switch (password_format) {
            case 1 -> PASS_PATTERN_1;
            case 2 -> PASS_PATTERN_2;
            default -> PASS_PATTERN_1;
        };

        return validator.matcher(password).matches();
    }

    public String getFormatExplanation() {
        String strFormat = switch (password_format) {
            case 1 -> FORMAT_EXPLANATION_1;
            case 2 -> FORMAT_EXPLANATION_2;
            default -> FORMAT_EXPLANATION_1;
        };
        return strFormat;
    }
}
