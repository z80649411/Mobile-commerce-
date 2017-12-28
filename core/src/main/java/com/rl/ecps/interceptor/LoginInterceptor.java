package com.rl.ecps.interceptor;

import com.rl.ecps.model.TsPtlUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ozc on 2017/10/10.
 */
public class LoginInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //得到session
        HttpSession session = request.getSession();
        TsPtlUser user = (TsPtlUser) session.getAttribute("user");

        /**
         * 如果没有登陆，就跳转到登陆页面，登陆了就放行。
         */
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/user/toLogin.do");
            return false;
        } else {
            return true;
        }

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
