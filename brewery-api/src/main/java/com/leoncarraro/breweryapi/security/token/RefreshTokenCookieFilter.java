package com.leoncarraro.breweryapi.security.token;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookieFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getRequestURI().equals("/oauth/token") &&
                req.getParameter("grant_type").equals("refresh_token") &&
                req.getCookies() != null) {

            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals("refreshToken")) {
                    String refreshToken = cookie.getValue();
                    req = new CustomServletRequestWrapper(req, refreshToken);
                }
            }
        }

        filterChain.doFilter(req, servletResponse);
    }

    static class CustomServletRequestWrapper extends HttpServletRequestWrapper {

        private final String refreshToken;

        public CustomServletRequestWrapper(HttpServletRequest request, String refreshToken) {
            super(request);
            this.refreshToken = refreshToken;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            ParameterMap<String, String[]> map = new ParameterMap<>(super.getRequest().getParameterMap());
            map.put("refresh_token", new String[] { refreshToken });
            map.setLocked(true);

            return map;
        }

    }

}
