package org.tievoli.framework.crypto;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * 标准加解密的类.
 * 
 * @author Ryan Jiang
 * 
 */
public class PropertyCrypto {
	private final String getKey() {
		return "!@#$%^&*()_+|anrndghk!#%780";
	}

	private final StandardPBEStringEncryptor getEncryptor() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(getKey());
		encryptor.setAlgorithm("PBEWithMD5AndDES");
		return encryptor;
	}

	public final String encrypt(String text) {
		return getEncryptor().encrypt(text);
	}

	public final String decrypt(String text) {
		return getEncryptor().decrypt(text);
	}
}
