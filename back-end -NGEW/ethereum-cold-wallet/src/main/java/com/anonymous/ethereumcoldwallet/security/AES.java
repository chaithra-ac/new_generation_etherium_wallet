package com.anonymous.ethereumcoldwallet.security;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/***
 * @author Chaithtra AC
 * @since November 05, 2020
 */

public final class AES {

    private AES() {
    }

    /***
     * This method perform the AES Encryption
     *
     * @param privateKey real private key
     * @param aesSecretKey  (Three AES will be combine from caller method side AES1+AES2+AES3)
     * @return the Base64 encoded string after AES perform.
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encryptPrivateKey(String privateKey, String aesSecretKey) throws
            NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException,
            BadPaddingException,
            IllegalBlockSizeException,
            InvalidKeyException,
            InvalidAlgorithmParameterException {

        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKey secret = getSecretKey(aesSecretKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret, ivParameterSpec);
        return Base64.getEncoder().encodeToString(cipher.doFinal(privateKey.getBytes(StandardCharsets.UTF_8)));

    }

    private static SecretKey getSecretKey(String aesSecretKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(aesSecretKey.toCharArray(), "finesse".getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }

    public static String decryptPrivateKey(String encryptPrivateKey, String aesSecretKey) throws
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            BadPaddingException,
            IllegalBlockSizeException,
            InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidKeyException {

        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKey secret = getSecretKey(aesSecretKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret, ivParameterSpec);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptPrivateKey.getBytes())), StandardCharsets.UTF_8);
    }

}
