package com.xawl.jswn.xiandan.handler;

import com.xawl.jswn.xiandan.domain.BUser;
import com.xawl.jswn.xiandan.domain.Msg;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author hcy
 * @create 2020-11-12-12:46
 * @flag Keep on running
 */
public class AutoLoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        HttpSession session=request.getSession();
        BUser user = (BUser) session.getAttribute("UserInfo");
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(user.getBname())) {
                    Msg.success();
                    response.setContentType("{'code':200,'msg':''}");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
