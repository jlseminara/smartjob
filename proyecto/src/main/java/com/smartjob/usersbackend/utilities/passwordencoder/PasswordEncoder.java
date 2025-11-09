package com.smartjob.usersbackend.utilities.passwordencoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.security.SecureRandom;
import java.util.Base64;


public class PasswordEncoder {

    private static final int MIN_SALT_SIZE = 20;
    private static final int MAX_SALT_SIZE = 50;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String generateRandomSalt(int size) {

        if(size<MIN_SALT_SIZE || size>MAX_SALT_SIZE)
            size = MIN_SALT_SIZE;

        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, size);
    }

    public static String bcryptPasswordEncryption(String password, String salt) {
        return encoder.encode(password+salt);
    }

}
