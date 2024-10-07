package com.lynn.seatsystem.util;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午7:21
 */
public class IpUtils {

    private static final String[] IP_HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADERS) {
            String ip = request.getHeader(header);
            if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                // 如果是多个代理，那么第一个IP为客户端真实IP
                return ip.contains(",") ? ip.split(",")[0] : ip;
            }
        }
        return request.getRemoteAddr();
    }
}
