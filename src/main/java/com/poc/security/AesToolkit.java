package com.poc.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AES cyphering algorithm implementation
 * @author Andrey Shchagin on 11/23/15.
 */
public class AesToolkit extends ASecurityToolkit{

    /**
     * java.util.logging is used here on purpose - as this tool supposed to be run from the console to escape importing
     * external classes
     */
    private static final Logger LOG = Logger.getLogger(AesToolkit.class.getName());
    private Cipher cipher;


    /**
     * Constructs AES crypto tool with given pass phrase
     */
    public AesToolkit(){
        // Create key and cipher
        String key = System.getenv(CoreConfigConstants.ENV_PROPERTY_AES_PASS_PHRASE) != null
                ? System.getProperty(CoreConfigConstants.ENV_PROPERTY_AES_PASS_PHRASE)
                : CoreConfigConstants.SECURITY_DEFAULT_AES_PASS_PHRASE;
        try {
            setKey(new SecretKeySpec(key.getBytes(), Algorithm.AES.name()));
            setCipher(Cipher.getInstance(Algorithm.AES.name()));

        }catch (Exception err){
            LOG.log(Level.SEVERE, "Error during construction AES toolkit", err);
        }
    }

    @Override
    protected String _encrypt(String phrase) {
        try {
            getCipher().init(Cipher.ENCRYPT_MODE, getKey());
            byte[] encrypted = getCipher().doFinal(phrase.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b: encrypted) {
                sb.append((char)b);
            }
            return sb.toString();
        }catch(Exception err){
            LOG.log(Level.SEVERE, "Error during encryption", err);
        }
        return null;
    }

    @Override
    protected byte[] _encryptToByteStream(String phrase) {
        try {
            getCipher().init(Cipher.ENCRYPT_MODE, getKey());
            return getCipher().doFinal(phrase.getBytes());
        }catch(Exception err){
            LOG.log(Level.SEVERE, "Error during encryption", err);
        }
        return null;
    }

    @Override
    protected String _decrypt(String phrase) {
        String decrypted = null;
        //read encrypted string
        byte[] bb = new byte[phrase.length()];
        for (int i=0; i<phrase.length(); i++)
            bb[i] = (byte) phrase.charAt(i);
        try {
            getCipher().init(Cipher.DECRYPT_MODE, getKey());
            decrypted = new String(getCipher().doFinal(bb));
        }catch (Exception err){
            LOG.log(Level.SEVERE, "Error during decryption", err);
        }
        return decrypted;
    }

    @Override
    protected String _decryptFromByteStream(byte[] phrase) {
        String decrypted = null;
        //read encrypted string
        try {
            getCipher().init(Cipher.DECRYPT_MODE, getKey());
            decrypted = new String(getCipher().doFinal(phrase));
        }catch (Exception err){
            LOG.log(Level.SEVERE, "Error during decryption", err);
        }
        return decrypted;
    }


    private Cipher getCipher() {
        return cipher;
    }

    private void setCipher(Cipher cipher) {
        this.cipher = cipher;
    }
}
