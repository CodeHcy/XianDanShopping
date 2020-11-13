package com.xawl.jswn.xiandan.controller;

import com.xawl.jswn.xiandan.domain.Msg;
import com.xawl.jswn.xiandan.domain.BUser;
import com.xawl.jswn.xiandan.domain.GetUserInfo;
import com.xawl.jswn.xiandan.service.UserLogin;
import com.xawl.jswn.xiandan.utiles.getYZM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class UserController {

    @Autowired()
    UserLogin userLogin;
//    获取验证码
    @CrossOrigin
    @RequestMapping("/api/user/getVerify.do")
    @ResponseBody
    public String user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getYZM getYZM = new getYZM();
        HashMap<Object,Object> map = getYZM.YZM(request,response);
        String YZM  = (String) map.get("randomCode");
        String image = (String) map.get("image");
        System.out.println(YZM);
        return image;
    }
// 登录验证
    @RequestMapping("/api/user/login.do")
    @ResponseBody
    public Msg userLogin(HttpServletRequest request,HttpServletResponse response, BUser BUser, String YZM){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        String randomCode = jedis.get(request.getRemoteAddr());
        if(jedis.ttl(request.getRemoteAddr()) == -2){
            return Msg.fail("验证码失效");
        }
        if(!YZM.equals(randomCode)){
            return Msg.fail("验证码错误");
        }

        BUser selectUser = userLogin.selectUser(BUser.getBname());

        if(selectUser == null){
            return Msg.fail("用户名错误");
        }
        System.out.println("数据库" +selectUser.getBpsd());
        if(!BUser.getBpsd().equals(selectUser.getBpsd())){
            return Msg.fail("密码错误");
        }
        HttpSession session = request.getSession();
        session.setAttribute("UserInfo",selectUser);
        Cookie cookie = new Cookie("username",selectUser.getBname());
        cookie.setPath("/");
        cookie.setDomain(".aotori.com");
        cookie.setMaxAge(7*24*60*60);
        response.addCookie(cookie);
        return Msg.success();
    }
//    获取用户信息
    @RequestMapping("/api/user/getUserInfo.do")
    public GetUserInfo getUserInfo(HttpServletRequest request,HttpServletResponse response){
        GetUserInfo userInfo = new GetUserInfo();
        if (request.getSession().getAttribute("UserInfo")==null){
            userInfo.setCode(400);
            userInfo.setMsg("登录已过期请再次登录");
        }
        userInfo.setCode(200);
        userInfo.setMsg("");
        userInfo.setUserInfo((BUser) request.getSession().getAttribute("UserInfo"));
        return userInfo;
    }
}
