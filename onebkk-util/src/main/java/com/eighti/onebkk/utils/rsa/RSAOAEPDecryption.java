package com.eighti.onebkk.utils.rsa;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSAOAEPDecryption {

	// Convert PEM string to PrivateKey
	public static PrivateKey getPrivateKeyFromBase64(String base64Key) throws Exception {
		byte[] decoded = Base64.getDecoder().decode(base64Key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(keySpec);
	}

	// Decrypt the data using RSA-OAEP with SHA-1 padding
	public static String decrypt(String ciphertext, PrivateKey privateKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
		return new String(decryptedBytes);
	}

	public static void main(String[] args) {
		try {
			// Your provided private key
			String pemPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgCW5xu/xiRbgiMfv8ZMUsII4Ib+SOixyoihzMY3kTrMFGbDAmI80iG166FmfmlW1F9b1gBZbdf2FSPb+YkYiEV5/MAMUyUTysSKMQBUVbratsW4VJ24QliBVPcC2oFY2LZKNcUYjIx8fumaVQRztNcE7isiCLFnv7yPvss/X25Z9hUXWPKn5lYo2SD1h6OirK1q9H7My5vYs6mzjBLgwn/jyUzW58y26EsN4J7YHUHBR6A/sXsebqk8b0DmGo+abnUJZwl4uMO/2y1fKDK7DsUogEYgC5kXk89mAGQPqEXQ4DibCumKtl/0mLltKQHJf/X2HbaL61UwfSMP0IC8dNAgMBAAECggEADFZ9zcXk4LyoQ/afiydHri26VXsu3zBpXrL7ImS+1l34rpGzqpUTAQCkLmuAyh2WBZE67taveVcTLTB2f3Ak2rMYrjUW/tiVeWDGNXt6i+VZNGrf3UBPsUcLAYtP8cfrmCYDlB/zECuaxdHRlV1QfJlec4qxCJgMqe4mQerj2QtepSwp3gjzjf3cDZ8D3IdbR9xjbPWfH+ghMJI5PewDILBgEUwTZMETC5IfwdShfVBzKaAP3+E4ZnQAAe5FUQabur0x/vg8T8NaCXogIDQs2PBK9Y2ZFQ/af28jNhqD5EvV/QRHrvoOAxjzYm+V1OWcQcHMtvoGN15vLMU+R0+NYQKBgQDO4yGqF1aCr91i2J2oq6AR6veBwEniFIbAsAMZ328tjjCz5UVpxYdy6ZxEWUVgYOPAgq9CDcQRreaS644g0X4PutiIn1Jtrhev1oE4uiVVtcTFf2Ds0EZtaDWD+ktDePyDXkUK6v+a0DLardzcC8tUNXWXBY2Dr9l0sZsGchzC0QKBgQDGByBqQGGSBL5x3Pv152sPxMF/SlNjkCYKX3RvO979kvZo9XIlQNspMxnnZEfLayq5jTiGrYF9mRMfuAawMedStCLk/vxxzKC0RPO98tyAf7yOkTzVdHS+MXGOia5S9ZqKwR84FIcnerfp19M7lAcHRcSt8My0U3H/ZPdlMO+DvQKBgDwzP1J9JAE3J1Zh7c5s0lEkbAznZRhSHLvo+54FtBYvb+CI8F/MyJMZgw0oZNWcm+kindi8pLb4QUWzIVJPYz00g1mN8rBTnjTeWA+nqsF7+Nv3Kr7+A+BGGHxk0o25oNChF7oLk4D64DFdsgydqfbA74yYYwtUmhQoshF4FwJhAoGAT0HO8XevFXgcmQnvNi5XJUqEf2PKGFE7SG+H9HUr/KVzRfLmqo5YqRawyXHTYlEUGouxEgYji5dWlh1bX+25PdqnFKwRcaKKt1vZii7Hng6B8zWh+XTQvMahQoFMahuurNY6noFozp5JOaiN/Ix0e66q10tHQu0a9TL96Vw6oKkCgYEAxW5N7UI7DWwf9xwtoATWLY9fmcsopY8Ki1F2kcLHA6ZH5fvDAef13jJkyok9RZtNDecbCusR72XSGCfhV16uPJ6pQqHqM06eAxQmMUf1y9fP7cQgcUScCs2jlKZTKdLL47mFw8cP41ATBnTjFRsABIUv4J32tr3s4doMmy2xQMg=";

			// Load the private key
			PrivateKey privateKey = getPrivateKeyFromBase64(pemPrivateKey);

			// Sample encrypted message (Base64-encoded ciphertext)
			String encryptedMessage = "lGrNqU/Qs5a5Mru651e6QlosV29IBW8BtucXfdWq3XE39jgVc6p3cNSQNulOPQH9g8h4B78+LOk4Y/MlZTxXPbYsEXh+RLWMgOGNHis808x6o467NFfc1bAMsEBNt6e2/OE5f0SRWvwqNRZbU48FWKtr54I+4JqftTVRD3sjKm56JaXo1ICwpynX+0grRUqTkTzRcuEqJVkKpJntot6z4vp+9fJ7soblOVIUWZnashZhJUklks+MYRzlJhg3HsbMgStqyMEXSftQmJLH7za5166UbWGMeVQaC9MS8Q2lCi2pjqCBXltF58e/d2ohOyRgME8bSS++cF0ssYaogJJfdQ==";

			// Decrypt the message
			String decryptedMessage = decrypt(encryptedMessage, privateKey);
			System.out.println("Decrypted Message: " + decryptedMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}