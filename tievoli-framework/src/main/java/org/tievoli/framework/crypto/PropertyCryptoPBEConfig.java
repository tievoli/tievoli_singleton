package org.tievoli.framework.crypto;

import org.jasypt.encryption.pbe.PBEStringEncryptor;

/**
 * 加解密的配置类.
 * 
 * @author Ryan Jiang
 * 
 */
public class PropertyCryptoPBEConfig implements PBEStringEncryptor {
	private final PropertyCrypto crypto;

	public PropertyCryptoPBEConfig() {
		this.crypto = new PropertyCrypto();
	}

	public final String decrypt(String text) {
		return this.crypto.decrypt(text);
	}

	public final String encrypt(String text) {
		return this.crypto.encrypt(text);
	}

	public final void setPassword(String password) {
	}

}
