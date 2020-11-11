package com.xawl.jswn.xiandan.controller;

import com.xawl.jswn.xiandan.domain.Msg;
import com.xawl.jswn.xiandan.domain.loginUser;
import com.xawl.jswn.xiandan.service.UserLogin;
import com.xawl.jswn.xiandan.utiles.getYZM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class UserController {

    @Autowired()
    UserLogin userLogin;

    @CrossOrigin
    @RequestMapping("/getVerify")
    @ResponseBody
    public String user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getYZM getYZM = new getYZM();
        HashMap<Object,Object> map = getYZM.YZM(request,response);
        String YZM  = (String) map.get("randomCode");
        String image = (String) map.get("image");
        System.out.println(YZM);
        return image;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Msg userLogin(HttpServletRequest request, loginUser loginUser, String YZM){
        Jedis jedis = new Jedis("127.0.0.1",6379);

        System.out.println(loginUser.getBname());
        System.out.println(loginUser.getBpsd());

        String randomCode = jedis.get(request.getRemoteAddr());
        System.out.println(randomCode);
        if(jedis.ttl(request.getRemoteAddr()) == -2){
            return Msg.fail("验证码失效");
        }
        if(!YZM.equals(randomCode)){
            return Msg.fail("验证码错误");
        }

        com.xawl.jswn.xiandan.domain.loginUser selectUser = userLogin.selectUser(loginUser.getBname());

        if(selectUser == null){
            return Msg.fail("用户名错误");
        }
        System.out.println("数据库" +selectUser.getBpsd());
        if(loginUser.getBpsd().equals(selectUser.getBpsd())){
            HttpSession session = request.getSession();
            session.setAttribute("userInfo",selectUser);
            return Msg.success();
        }else{
            return Msg.fail("密码错误");
        }
    }
}
