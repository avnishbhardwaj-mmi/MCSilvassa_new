package com.silvassa.util;
import java.net.URLEncoder;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

public class ASE
{
    public ASE() 
    {
    }

    private static String encryptionKeyBase64 = "DWIzFkO22qfVMgx2fIsxOXnwz10pRuZfFJBvf4RS3eY=";
    private static String ivBase64 = "AcynMwikMkW4c7+mHtwtfw==";
            
    public static String encrypt(String plainText) throws Exception
    {
        byte[] plainTextArray = plainText.getBytes(StandardCharsets.UTF_8);
        byte[] keyArray = DatatypeConverter.parseBase64Binary(encryptionKeyBase64);
        byte[] iv = DatatypeConverter.parseBase64Binary(ivBase64);

        SecretKeySpec secretKey = new SecretKeySpec(keyArray, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");   
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
        return new String(DatatypeConverter.printBase64Binary(cipher.doFinal(plainTextArray)));
    }

    public static String decrypt(String messageBase64) throws Exception {

        byte[] messageArray = DatatypeConverter.parseBase64Binary(messageBase64);
        byte[] keyArray = DatatypeConverter.parseBase64Binary(encryptionKeyBase64);
        byte[] iv = DatatypeConverter.parseBase64Binary(ivBase64);

        SecretKey secretKey = new SecretKeySpec(keyArray, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        return new String(cipher.doFinal(messageArray));
    }
    
    public static String parseToURLParam(String data){
        String str = null;
        try{
            str = URLEncoder.encode(data, "UTF-8" );
        } catch(Exception ex){
        }
        return str;
    }

    public static void main(String[] args) {

        try
        {   
            String plainText = "Hello world!";
      
            String cipherText = encrypt(plainText);
            String decryptedCipherText = decrypt(cipherText);

            System.out.println("Plaintext: " + plainText);
            System.out.println("Ciphertext: " + cipherText);
            System.out.println("Decrypted text: " + decryptedCipherText);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}