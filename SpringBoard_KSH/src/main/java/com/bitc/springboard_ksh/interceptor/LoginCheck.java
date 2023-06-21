package com.bitc.springboard_ksh.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheck implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        HttpSession session = req.getSession();

        String userId = (String) session.getAttribute("userId");
        if (userId == null || userId.equals("")) {
            resp.sendRedirect("/login/");
            return false;
        } else {
            session.setMaxInactiveInterval(60);
            return true;
        }
    }
}
