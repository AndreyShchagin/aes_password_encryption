package com.poc.security;

import java.security.Key;

/**
 * Abstraction to be extended with concrete cyphering algorithm implementations
 * @author Andrey Shchagin on 11/23/15.
 */
public abstract class ASecurityToolkit {
    private Key aKey;

    public final String encrypt(final String phrase){
        return _encrypt(phrase);
    }

    public final byte[] encryptToByteStream(final String phrase){
        return _encryptToByteStream(phrase);
    }

    protected abstract String _encrypt(String phrase);

    protected abstract byte[] _encryptToByteStream(String phrase);

    public String decrypt(final String phrase){
        return _decrypt(phrase);

    }

    public String decryptFromByteStream(final byte[] phrase){
        return _decryptFromByteStream(phrase);

    }

    protected abstract String _decrypt(String phrase);

    protected abstract String _decryptFromByteStream(byte[] phrase);

    public Key getKey() {
        return aKey;
    }

    public void setKey(Key aKey) {
        this.aKey = aKey;
    }

    public enum Algorithm {
        AES
    };
}
