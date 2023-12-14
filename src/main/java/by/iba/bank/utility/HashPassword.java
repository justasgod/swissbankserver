package by.iba.bank.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public static byte[] getHash(String passStr) {
        MessageDigest digest = null;
        byte[] hash = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            hash = digest.digest(passStr.getBytes("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }
}
