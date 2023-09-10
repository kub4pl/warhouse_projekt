package com.example.warehouse.validators;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PasswordValidator {
    public static boolean isValidPassword(String password) {

        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }


}
