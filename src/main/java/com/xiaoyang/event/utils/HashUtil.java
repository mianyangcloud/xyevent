package com.xiaoyang.event.utils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    // md5加密
    public static String md5(String inputText) {
        return encrypt(inputText, "md5");
    }

    // sha加密
    public static String sha(String inputText) {
        return encrypt(inputText, "sha-1");
    }

    public static String md5File(File file) throws IOException {

        MessageDigest digest = null;
        try (InputStream data = new FileInputStream(file)) {
            digest = MessageDigest.getInstance("md5");
            final byte[] buffer = new byte[1024];
            int read = data.read(buffer, 0, 1024);

            while (read > -1) {
                digest.update(buffer, 0, read);
                read = data.read(buffer, 0, 1024);
            }
        } catch (NoSuchAlgorithmException e) {
            //do nothing
        }

        return hex(digest.digest());
    }

    /**
     * md5或者sha-1加密
     *
     * @param inputText     要加密的内容
     * @param algorithmName 加密算法名称：md5或者sha-1，不区分大小写
     * @return
     */
    private static String encrypt(String inputText, String algorithmName) {
        if (inputText == null || "".equals(inputText.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }
        String encryptText = null;
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputText.getBytes("UTF8"));
            byte s[] = m.digest();
            // m.digest(inputText.getBytes("UTF8"));
            return hex(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    // 返回十六进制字符串
    public static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
                    3));
        }
        return sb.toString();
    }
}