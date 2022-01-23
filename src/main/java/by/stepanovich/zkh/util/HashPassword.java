package by.stepanovich.zkh.util;

import by.stepanovich.zkh.util.exeption.ZkhUtilException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public String hashPassword(String password) throws ZkhUtilException {
        StringBuilder hashBuilder = new StringBuilder();
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            if (password != null) {
                byte[] hash = messageDigest.digest(password.getBytes());
                for (byte b : hash) {
                    hashBuilder.append(b);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new ZkhUtilException("Exception while hashing password", e);
        }
        return hashBuilder.toString();
    }
}
