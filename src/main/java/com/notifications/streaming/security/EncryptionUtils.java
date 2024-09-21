package com.notifications.streaming.security;

import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class EncryptionUtils {

    private static final String SECRET_KEY = "qD6u84Th8C7q9JQ4iYHK9wHk3k73+VOTCGL6He2p9G8=";
    private static final int IV_SIZE = 16;
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 256; // AES-256

    private static SecretKey generateKey(String password) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(KEY_SIZE);
        return keyGen.generateKey();
    }

    public String encrypt(String data) throws Exception {

        SecretKey secretKey = decodeKey(SECRET_KEY);
        byte[] iv = generateIv();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        // Combine IV and encrypted data for decryption
        byte[] combined = new byte[IV_SIZE + encryptedBytes.length];
        System.arraycopy(iv, 0, combined, 0, IV_SIZE);
        System.arraycopy(encryptedBytes, 0, combined, IV_SIZE, encryptedBytes.length);
        return Base64.getEncoder().encodeToString(combined);

    }

    public String decrypt(String encryptedData) throws Exception {
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);

        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(decodedData, 0, iv, 0, IV_SIZE);

        byte[] encryptedBytes = new byte[decodedData.length - IV_SIZE];
        System.arraycopy(decodedData, IV_SIZE, encryptedBytes, 0, encryptedBytes.length);

        SecretKey secretKey = decodeKey(SECRET_KEY);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);

    }

    private SecretKey decodeKey(String base64Key) {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
    }

    private static byte[] generateIv() {
        byte[] iv = new byte[IV_SIZE];
        new SecureRandom().nextBytes(iv);
        return iv;
    }


}
