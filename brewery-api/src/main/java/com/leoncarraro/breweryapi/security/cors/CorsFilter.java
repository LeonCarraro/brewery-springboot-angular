package com.leoncarraro.breweryapi.security.cors;

import com.leoncarraro.breweryapi.config.property.ApplicationProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    private final ApplicationProperty applicationProperty;

    public CorsFilter(ApplicationProperty applicationProperty) {
        this.applicationProperty = applicationProperty;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        res.setHeader("Access-Control-Allow-Origin", applicationProperty.getApplicationSecurity().getAllowedOrigin());
        res.setHeader("Access-Control-Allow-Credentials", "true");

        if (req.getMethod().equals("OPTIONS") &&
                req.getHeader("Origin").equals(applicationProperty.getApplicationSecurity().getAllowedOrigin())) {
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
            res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            res.setHeader("Access-Control-Max-Age", "3600");
            res.setStatus(HttpServletResponse.SC_OK);
        }

        filterChain.doFilter(req, res);
    }

}
