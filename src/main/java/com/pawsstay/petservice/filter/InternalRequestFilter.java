package com.pawsstay.petservice.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 內部請求驗證 Filter
 * 確保所有請求都必須經過 API Gateway（帶有 X-User-Email Header）
 * 防止外部直接繞過 Gateway 呼叫 pet-service
 */
@Component
public class InternalRequestFilter extends OncePerRequestFilter {

    private static final String USER_EMAIL_HEADER = "X-User-Email";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String userEmail = request.getHeader(USER_EMAIL_HEADER);

        // 如果沒有攜帶 X-User-Email Header，代表請求不是來自 Gateway，直接拒絕
        if (userEmail == null || userEmail.isBlank()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(
                "{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"Missing internal gateway header\"}"
            );
            return;
        }

        filterChain.doFilter(request, response);
    }
}
