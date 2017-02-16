package com.poc.security;

/**
 * @author Andrey Shchagin on 16.02.17.
 *         Syner Trade AEG
 */
public class CoreConfigConstants {
    public final static String ENV_PROPERTY_AES_PASS_PHRASE = "encryption.aes.key";
    public final static String ENV_PROPERTY_AUTH_CREDENTIALS_PATH = "encryption.auth.credentials.path";
    public final static String ENV_PROPERTY_AUTH_REQUIRED = "encryption.auth.required";
    //Length must be 16/24/32 bytes per AES standard
    public final static String SECURITY_DEFAULT_AES_PASS_PHRASE = "8IRI3wAFt1TxZaqp";
}
