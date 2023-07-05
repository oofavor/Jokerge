package ru.ofavor.auth;

import java.security.MessageDigest;
import java.util.regex.Pattern;

public class AuthUtils {

    public static boolean validateEmail(String email) {
        String emailRegexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!Pattern.compile(emailRegexp).matcher(email).matches())
            return false;
        return true;
    }

    public static boolean validatePassword(String password) {
        if (password.length() < 6) return false;
        return true;
    }

    public static String encodePassword(String password) {
        String algorithm = "SHA";

        byte[] plainText = password.getBytes();

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.reset();
            digest.update(plainText);
            byte[] encodedPassword = digest.digest();

            StringBuilder builder = new StringBuilder();
            for (byte b : encodedPassword) {
                if ((b & 0xff) < 0x10) {
                    builder.append("0");
                }
                builder.append(Long.toString(b & 0xff, 16));
            }
            password = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return password;
    }

}
