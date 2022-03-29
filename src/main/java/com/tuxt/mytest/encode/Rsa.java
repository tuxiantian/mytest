package com.tuxt.mytest.encode;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Rsa {
    public static class Keys {
        private String privateKey;
        private String publicKey;

        public Keys(String privateKey, String publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }
    }

    public static class Generator {
        public static Keys generate() {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            try {
                KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
                generator.initialize(2048, new SecureRandom());
                KeyPair pair = generator.generateKeyPair();
                PublicKey publicKey = pair.getPublic();
                PrivateKey privateKey = pair.getPrivate();
                return new Keys(new String(Base64.encode(privateKey.getEncoded())), new String(Base64.encode(publicKey.getEncoded())));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class Encoder {
        private PrivateKey mPrivateKey;
        private Cipher cipher;

        public Encoder(String privateKey) {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            PKCS8EncodedKeySpec privatePKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey.getBytes()));
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
                mPrivateKey = keyFactory.generatePrivate(privatePKCS8);
                cipher = Cipher.getInstance("RSA", "BC");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String encode(String source) {
            try {
                cipher.init(Cipher.ENCRYPT_MODE, mPrivateKey);
                byte[] cipherText = cipher.doFinal(source.getBytes("utf-8"));
                return new String(Base64.encode(cipherText));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class Decoder {
        private PublicKey mPublicKey;
        private Cipher cipher;

        public Decoder(String publicKey) {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            X509EncodedKeySpec publicX509 = new X509EncodedKeySpec(Base64.decode(publicKey.getBytes()));
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
                mPublicKey = keyFactory.generatePublic(publicX509);
                cipher = Cipher.getInstance("RSA", "BC");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String decode(String source) {
            try {
                cipher.init(Cipher.DECRYPT_MODE, mPublicKey);
                byte[] output = cipher.doFinal(Base64.decode(source.getBytes()));
                return new String(output, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    
    public static void main(String[] args) {
    	/*Rsa r=new Rsa();
    	Keys key=new Generator().generate();
    	String pri=key.getPrivateKey();
    	String pub=key.getPublicKey();
    	System.out.println(pri);
    	System.out.println("-----");
    	System.out.println(pub);*/
    	String pri="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCTPLqgD/iyxJ7IUp2CXl0IJjBabHm9pYPzkDnyHlLQ/j2HB3l7p4C9gPdnJO6Dyvya7xhsoR9r7bHJQ4SBYpOPthSAJt3XRYtMcZ32aKbrlVq5i1RTQplesM6NQ3aRHqCoJ8K54fJwHIxfvMTFwWlThkd5dqZlaKUdx6wgyeRvFgkY3Q8KD9lQ8q8yls2Ch4HG2t9+AhPsfc+CoWRXiC4bq70RCMcwiaGe4Z9NDmTZpRm8+60k2kZ7XKJQEIx14LS+9geZNn5LLmhXyf9ib86hBmCaTQToI1LS79oLjr/0dpZYg32Esisd++SK7MBUUoSccSSRiCedLH3yNOZAQRXRAgMBAAECggEAJcBO64Zc0l1Ghv9jwk72lIXSJQUmK8F59SO4L8OloU9msC3y8IkQocYk6Q3Xl/QeU41VWljviQk/aebFXTVMt27Mj9UnUTBUGIwTH+BSWBoJ0oQOYXC0iUpVE3RGPMw5DhRvwaBUvHkdZtisLTY7jr5hqhuJa3vqxhlxoVCSoQaF8mWxeRAiDK5JI5WT4t/EG89VNAmP3jz6WQRumt9f0RfyTd1XRLtiayT0QT7LQizQ66eVuB3jd0kEsWNGuKlhPUgNx2pU/r2p52Aw/pJ8CMPzo7m90K9SzGDo1XMSGgjBxYP6FOyqM3RiCj+CtTsLO7Y5AD7/WiuJKWV14CyfdwKBgQDTLsA+xonBCejFQZLHRI/A/sugoSL8v3xkpuLYE831WlOpK+TixPd70TwAcqfB8a5QyqpN2Xw83/SX0fdWsGTwbp7ZsBmvdNKgi9neTWcvPod6+3o8q0vY3ElAzK6FEHjjOAK08tXGksIlAIlpR/CCxs4h0r8pu1bBZfidDkDmMwKBgQCye+qONXfNgeDlhIg7xUwZ+SHTCDAaicQE9a1x4XKAG8ZH9KeeddgyDzJ4CgRVPSv56XtFK8qT1LKUAxOzcfq0qkSPtDr9XRnyumDYxfEg9DXW3svu90jY629I9bBUprfjfmJtKWgSfJs8jmyuAXC5gqz4m10sNYG9yGlcpfQn6wKBgQCIdN398jXSyT9iLlPUdKXa1AjpmxVLccPEGd3cNzfPAPWsC1PTf+hWVkXKGEvz0uGLDNlsgkp331Jhw2dp2YLvpNr52INQT1FGMfeuYB97dJEAU3yRtTHGR9kBiQB/tLwYPfnpyDuAGxZd6HHh2H09U46gZlfldErDnss9WJdw5wKBgQCU44YA511vE58WF37FzYn0SL0Mm9H08WORd5geXaD0eTboo5CIQWn+KGtDc6e1q3fZ3Ak25JbyxwXJLt/qW3R17WuQLRrgbB/POea8LjM9+DpJakXYrHQacBu/UySrGQBhR/a3dUkZ0tR0qaLDbPQp7HZ6VLBexqvaMyqhXNbVzQKBgQC/EyXUyMSkGQUI6rdTeP4Y8gI8RhhkHDu5yCzt+aD8Nhu791SBtRYCeAQ9Z4JCloLMEaherjOQDy2ucYsoPmzQZHI9OO0SmDVDFPreSS31TWkiXzqLWHwrBFdQEdriX2QYJ9bkNUhTITqAszjvWC9PxFaO3WsrhKn+u85V0k+G6A==";
    	String pub="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkzy6oA/4ssSeyFKdgl5dCCYwWmx5vaWD85A58h5S0P49hwd5e6eAvYD3ZyTug8r8mu8YbKEfa+2xyUOEgWKTj7YUgCbd10WLTHGd9mim65VauYtUU0KZXrDOjUN2kR6gqCfCueHycByMX7zExcFpU4ZHeXamZWilHcesIMnkbxYJGN0PCg/ZUPKvMpbNgoeBxtrffgIT7H3PgqFkV4guG6u9EQjHMImhnuGfTQ5k2aUZvPutJNpGe1yiUBCMdeC0vvYHmTZ+Sy5oV8n/Ym/OoQZgmk0E6CNS0u/aC46/9HaWWIN9hLIrHfvkiuzAVFKEnHEkkYgnnSx98jTmQEEV0QIDAQAB";

    	
    	
    	String source= "您好";
    	System.out.println("公钥长度："+pub.length());
    	 System.out.println("私钥长度："+pri.length());
    	String ss=new Encoder(pri).encode(source);
    	System.out.println("加密串长度："+ss.length());
		String ssss=new Decoder(pub).decode(ss);
	   System.out.println(ssss);
    	
	}
}
