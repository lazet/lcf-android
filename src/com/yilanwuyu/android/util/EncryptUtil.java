package com.yilanwuyu.android.util;

import java.security.MessageDigest;


public class EncryptUtil {
	
	
    /**
	 * Encode a string using specified algorithm and return the
	 * resulting encrypted string. If exception, the plain credentials string
	 * is returned
	 * 
	 * @return encypted string based on the algorithm.
	 */
    public static String encode(String id, String algorithm) {
        byte[] unencodedPassword = id.getBytes();
        
        MessageDigest md = null;
        
        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            return id;
        }
        
        md.reset();
        
        // call the update method one or more times
        // (useful when you don't know the size of your data, eg. stream)
        md.update(unencodedPassword);
        
        // now calculate the hash
        byte[] encodedPassword = md.digest();
        
        StringBuffer buf = new StringBuffer();
        
        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            
            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }
        
        return buf.toString();
    }
    public static String encodeWithSha256(String text) {
    	return encode(text,SHA256);
    }
    public static String encodeWithMD5(String text) {
    	return encode(text,MD5);
    }
    public static String genSign(String text){
    	return encode(encode(text,SHA256),MD5);
    }
    public final static String MD5 = "MD5";
    public final static String SHA1 = "SHA1";
    public final static String SHA256 = "SHA-256";
    public final static String SHA512 = "SHA-512";
}
