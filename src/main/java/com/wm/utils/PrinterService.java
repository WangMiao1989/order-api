package com.wm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wm.exception.SystemException;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;


@Slf4j
@Service
public class PrinterService {
	
	private final String APP_ID = "sp69cb8e8de03d3";
	private final String SN = "1928978726";
	private final String APP_SECRET = "1de5cf31ea9389cb42e581574a29c7a0";
	private final String PRINTER_PRINT = "https://open.spyun.net/v1/printer/print";
	private final String PRINTER_INFO = "https://open.spyun.net/v1/printer/info";
	
	private final RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;

    public PrinterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 打印订单
     * @param content 打印内容（最大5000字节）
     */
    public void printOrder(String content) {
        // 准备请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("appid", APP_ID);
        params.put("timestamp", Instant.now().getEpochSecond()); // 10位unix时间戳
        params.put("content", content);
        params.put("sn", SN);

        // 发送请求
        String responseBody = doPost(PRINTER_PRINT, params);
        
        try {
            JsonNode root =objectMapper.readTree(responseBody);
            int errorCode = root.path("errorCode").asInt();
            if (errorCode != 0) {
                String errMsg = root.path("errormsg").asText("未知错误");
                throw new SystemException(errMsg);
            }
            // 成功时不做处理，方法结束
        } catch (Exception e) {
            // 如果解析失败或网络异常，也包装为 SystemException
            throw new SystemException("打印接口调用失败: " + e.getMessage());
        }
    }
    
    public String showPrinterInfo() {
    	// 准备请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("appid", APP_ID);
        params.put("timestamp", Instant.now().getEpochSecond()); // 10位unix时间戳
        params.put("sn", SN);
         
        // 发送请求
        return  doGet(PRINTER_INFO, params);
    }
    
    private String doPost(String url, Map<String, Object> params) {
    	// 生成签名
        String sign = generateSign(params);
        params.put("sign", sign);
        
    	// 构建表单请求体
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        params.forEach((k, v) -> formData.add(k, v.toString()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

       return response.getBody();
    }
    
    /**
     * GET 请求，支持查询参数和请求头
     * @param url 基础URL（不含查询参数）
     * @param params 查询参数（可为null）
     * @return 响应体字符串
     */
    private String doGet(String url, Map<String, Object> params) {
    	// 生成签名
        String sign = generateSign(params);
        params.put("sign", sign);
        
    	// 构建表单请求体
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        params.forEach((k, v) -> formData.add(k, v.toString()));
        
        // 构建带参数的URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (params != null) {
            builder.queryParams(formData);
        }
        String finalUrl = builder.build().toUriString();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        
        ResponseEntity<String> response = restTemplate.exchange(finalUrl, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    /**
     * 签名生成算法
     * @param params 请求参数（不包含 sign）
     * @param key 密钥
     * @return 大写MD5签名
     */
    private String generateSign(Map<String, Object> params) {
        // 1. 过滤空值参数（注意：空字符串不参与，但 "0" 参与）
        // 2. 按参数名ASCII码升序排序
        List<String> keys = new ArrayList<>(params.keySet());
        keys.remove("sign"); // 确保 sign 不参与（但 params 中本来就不包含 sign）
        Collections.sort(keys);

        // 3. 拼接 key=value 字符串
        StringBuilder sb = new StringBuilder();
        for (String k : keys) {
            Object value = params.get(k);
            if (value == null) continue;
            String strValue = value.toString();
            // 空字符串不参与签名（但 "0" 是字符，不为空，故不判断）
            if (strValue.isEmpty()) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(k).append("=").append(strValue);
        }

        // 4. 拼接 key
        sb.append("&appsecret=").append(APP_SECRET);
        String stringSignTemp = sb.toString();

        // 5. MD5 加密并转为大写
        return md5(stringSignTemp).toUpperCase();
    }

    private String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}