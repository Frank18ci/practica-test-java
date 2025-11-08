package com.carpio.practicatest1.session2;

import java.util.regex.Pattern;

public class PasswordValidator {
    private final static int MIN_PW_LENGTH = 7;
    private final static int MAX_PW_LENGTH = 10;
    public static boolean isValid(String password) {
        return (password.length() >= MIN_PW_LENGTH && password.length() <= MAX_PW_LENGTH)&&
                Pattern.matches(".*\\d.*", password)&& !password.toLowerCase().equals(password);
    }
}



