package com.wm.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {

    private static final SecureRandom secureRandom = new SecureRandom(); // 线程安全
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding(); // 无填充的 URL 安全 Base64

    /**
     * 生成指定字节长度的随机令牌，并返回 Base64 编码的字符串
     * @param byteLength 随机字节长度（推荐 32 字节以上）
     * @return Base64 编码的令牌字符串
     */
    public static String generateToken(int byteLength) {
        byte[] randomBytes = new byte[byteLength];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}