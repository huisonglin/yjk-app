package com.yjk.app.util;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import sun.misc.BASE64Decoder;

public class RSAUtil {
	
    public RSAUtil() {
    }
    public static PublicKey pbkey;
    public static PrivateKey prkey;
    static {
        try {
			 pbkey = getPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyvYZOVo+i+rKdl+7/H8S65PF6D9tiZFl/H7D78JJcqUaIAS0kI0NtF1/zYtb1yPIEDS3FU6HwePSM/qPIKZu87L5PSuAQ5g3wtacXzYPRlZLogOuaX6xFN/Ny3vUm0YXWlHI4cNxHHj7hn/cwYXz37qUo6dWTJxNnDmLXZVc53nwmK0Wf/AHosUEqMlQFTfToq1EGYT6XCRPYcl0r2963vvqPN9J/agV2tBGooO4zJpqatUdeMLNqyYl3ylTpzxLgPKiKBWOozL3h7UHnVZKf/dTYdzdBr8dwsCPyo8LmqcBuH+N1BLlNGCllDg+LJCnGlNFOOac/GzXMJdfqrgLgQIDAQAB");
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
			 prkey = getPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDK9hk5Wj6L6sp2X7v8fxLrk8XoP22JkWX8fsPvwklypRogBLSQjQ20XX/Ni1vXI8gQNLcVTofB49Iz+o8gpm7zsvk9K4BDmDfC1pxfNg9GVkuiA65pfrEU383Le9SbRhdaUcjhw3EcePuGf9zBhfPfupSjp1ZMnE2cOYtdlVznefCYrRZ/8AeixQSoyVAVN9OirUQZhPpcJE9hyXSvb3re++o830n9qBXa0Eaig7jMmmpq1R14ws2rJiXfKVOnPEuA8qIoFY6jMveHtQedVkp/91Nh3N0Gvx3CwI/KjwuapwG4f43UEuU0YKWUOD4skKcaU0U45pz8bNcwl1+quAuBAgMBAAECggEADi/XoYeqWZNm5ZFZjZPAWdExg0Nzv6r3KwgdYBP/mFDsG5LYoEYL0DC87zwvhkFNCbz1kzqzCJ2JIvL/sDOSC9zVyS3SQGdSdn8T3R/lP3BmxwNk3KDrdd8S/odAXD4xAbI3zXgg3vGh+0vSvYBEhL4BB9N5l89RKXtoP3liThe7FQO6LcPcL/caYaKkrBKDcHP8ZrZ5HwetkwMeq8isCasrxjLB8tvGqFf2GL3ZS3VC9+Cz/qDai72zGj2ao4qUFcYZ0x7FQjqmPk9C1My8793ka//oTlIjtoinElKcDZR60flsRFFhRo0rEEuNFJJNgfrjLB7ZnuzWDj4soMjf1QKBgQDq86oSmVEjYyO/Hlr3PlB/L/KwdtlP/ds/Ldf5hXFdguLyG7n8z9vNuxbobbnYehJLtA1/737AxiIeUTekCR5f+sJCCj7QoeCkg8a2gr622y5K5DgsmZrp5qBAPOlpLD/8iiDiE8Jef3NOuyun63rKB25lnvlPqzBr3w6ZXm0GJwKBgQDdJMVdkMg/Z0po/DUbDHwscBQcAbwMajMflom5XgAi7dQnDPE111zELW/NJoqdW39vLX5cInMYGAXwMt8HH05kNjXDPZpeDdgxizIKBG4873Ras2JlFVMH16KYmADQhi3GEFjYeNwlBvBERNAoC864V8aTi0jMBEJ0pYN5EexSFwKBgDfpfJOogLKXKS6WHrev0KAz3OE5p0Vt9/r1AeyTYQML3s4W4w/St1AiMbVmR0/I3Qm+82d6qwNdI0lrsn4K3XuhxRDKN2CW5UrBQUy1mIUTa9TOsr+KXl9DTud6l2E78o46oNCsOxkZ42rdB+qSE0p4skDksBfReVhyYJsG6ZMJAoGBAMD3zC0IIGSLGjggE6sFqvkP18xUiyX+KZwfvQ/+gXn5M83bkkOcKHua9lr6jawf4kQ+W2vEN9BHbi8aBNlvLPVvBFzYzXHS/NrGjqSFFzie7lYTAAIaqHhIZr5mrRdIbnrNWumSFO9Obav0q/q1yu260jF/Mces09x7bQzhYeiDAoGAGSkxSIq1WSKyUPagwH/uiKuFRDNvR9Qca7GOI9ReNmVjSjoAokBHIkB3kdzqB4cG5c0ZUFeoc6uobpRo2EdCU1RYI8TqZt2ku6c8Ut6dPZuOxePkbn7dIapArVqeYHaT7v0DDsR8M68Q2ZkHW3qga+mCJjbm1hAQtI2Kvja1f2c=");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void generateKey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.genKeyPair();
            pbkey = kp.getPublic();
            prkey = kp.getPrivate();
        } catch (Exception e) {
        }
    }

    //加密，需要公钥
    public byte[] encrypt(byte[] ptext) throws Exception {
        // 获取公钥及参数e,n
        RSAPublicKey pbk = (RSAPublicKey)pbkey;
        BigInteger e = pbk.getPublicExponent();
        BigInteger n = pbk.getModulus();
        // 获取明文m
        BigInteger m = new BigInteger(ptext);
        // 计算密文c
        BigInteger c = m.modPow(e, n);
        return c.toByteArray();
    }

    //使用私钥进行解密
    public byte[] decrypt(byte[] ctext) throws Exception {
        // 读取密文
        BigInteger c = new BigInteger(ctext);
        // 读取私钥
        RSAPrivateKey prk = (RSAPrivateKey)prkey;
        BigInteger d = prk.getPrivateExponent();

        // 获取私钥参数及解密
        BigInteger n = prk.getModulus();
        BigInteger m = c.modPow(d, n);

        // 显示解密结果
        byte[] mt = m.toByteArray();
        return mt;
    }
    
    /**
     * String转公钥PublicKey
     * @param key
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
 
    /**
     * String转私钥PrivateKey
     * @param key
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    
    public static void main(String args[]) 
    {
        try {
        	RSAUtil rsa=new RSAUtil();
            //rsa.generateKey();
            byte[] data=rsa.encrypt("i am to large".getBytes("UTF-8"));
            byte[] data1=rsa.decrypt(data);
            
            String str=new String(data1, "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


}
