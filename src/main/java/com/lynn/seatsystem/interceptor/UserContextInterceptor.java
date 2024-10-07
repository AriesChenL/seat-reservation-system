package com.lynn.seatsystem.interceptor;

import com.lynn.seatsystem.util.IpUtils;
import com.lynn.seatsystem.util.JwtUtils;
import com.lynn.seatsystem.util.UserContext;
import com.lynn.seatsystem.domain.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午7:00
 */
@Slf4j
@Component
public class UserContextInterceptor implements HandlerInterceptor {
    private static final String AUTH_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTH_HEADER);
        String clientIp = IpUtils.getClientIpAddress(request);
        String requestPath = request.getRequestURI();
        String requestMethod = request.getMethod();

        log.info("Request from IP: {}, Path: {}, Method: {}", clientIp, requestPath, requestMethod);

        if (token != null && !token.isEmpty()) {
            try {
                Long userId = JwtUtils.extractUserId(token);

                // 验证token
                if (JwtUtils.validateToken(token, userId) && !JwtUtils.isTokenExpired(token)) {
                    // 创建 User 对象并设置到 UserContext 中
                    User user = new User();
                    user.setUserId(userId);
                    user.setLastLoginIp(clientIp);
                    UserContext.setUser(user);

                    log.info("User {} authenticated from IP {}, Path: {}, Method: {}", userId, clientIp, requestPath, requestMethod);
                    return true;
                }
            } catch (Exception e) {
                log.warn("Invalid token from IP: {}, Path: {}, Method: {}", clientIp, requestPath, requestMethod, e);
            }
        }
        return true;
    }
}
