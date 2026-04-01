package com.wm.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class TableSessionIdGenerator {
	// 时间格式：年月日时分秒毫秒
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");

    // 随机数位数
    private static final int RANDOM_NUM_DIGITS = 4;

    /**
     * 生成唯一订单号
     * 格式：yyyyMMddHHmmssSSS + 4位随机数
     */
    public static String generateTableSessionId() {
        // 获取当前时间字符串（毫秒级）
        String timestamp = LocalDateTime.now().format(FORMATTER);
        // 生成指定位数的随机数
        String randomNum = generateRandomNumber(RANDOM_NUM_DIGITS);
        return timestamp + randomNum;
    }

    /**
     * 生成指定长度的随机数字字符串（0-9）
     */
    private static String generateRandomNumber(int length) {
        StringBuilder sb = new StringBuilder(length);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // 0-9
        }
        return sb.toString();
    }
}
