package com.rjxx.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * Created by wangyahui on 2018/3/12 0012
 */
public class AESUtil {
    static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";

    static Cipher cipher = null;
    static KeyGenerator kgen = null;
    static{
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            kgen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String str,String password ) throws Exception {
        kgen.init(128, new SecureRandom(password.getBytes()));
        SecretKey secretKey = kgen.generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(getIV()));//使用加密模式初始化 密钥
        byte[] encrypt = cipher.doFinal(str.getBytes()); //按单部分操作加密或解密数据，或者结束一个多部分操作。
        return parseByte2HexStr(encrypt);
    }

    public static String decrypt(String str,String password) throws Exception {
        kgen.init(128, new SecureRandom(password.getBytes()));
        SecretKey secretKey = kgen.generateKey();
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(getIV()));//使用解密模式初始化 密钥
        byte[] decrypt = cipher.doFinal(parseHexStr2Byte(str));
        return new String(decrypt);
    }

    //加盐
    public static byte[] getIV() {
        String iv = "%$#@&*()@$#(!&$^"; //IV length: must be 16 bytes long
        return iv.getBytes();
    }

    /**将16进制转换成二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1){
            return null;
        }
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String message = "on=1234567&ot=20180309155203&pr=20&sn=sh01&sp=1";
        String password = "fa19f6c4d0e4144e8115ed71b0e4c349";
        String mw = encrypt(message, password);
        System.out.println(mw);
        System.out.println(decrypt(mw,password));
    }
}
