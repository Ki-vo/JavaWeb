package com.project.utils;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


/**
 * @author: wljlsmz
 * @date: 2022/11/15 10:28
 * @description: ip工具类
 */
@Slf4j
public class IpUtils {

    /**
     * 本地环回地址
     */
    private static final String LOCAL_IP = "127.0.0.1";

    /**
     * 未知
     */
    private static final String UNKNOWN = "unknown";

    public static String getIpAddr(HttpServletRequest request) {

        if (request == null) {
            return UNKNOWN;
        }

        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? LOCAL_IP : ip;
    }
}