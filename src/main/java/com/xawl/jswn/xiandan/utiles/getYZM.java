package com.xawl.jswn.xiandan.utiles;

import redis.clients.jedis.Jedis;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class getYZM {

    public static HashMap<Object,Object> YZM(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, java.io.IOException {
        // 验证码图片的宽度。
        int width = 68;
        // 验证码图片的高度。
        int height = 22;
        BufferedImage buffImg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 创建一个随机数生成器类。
        Random random = new Random();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Times New Roman", Font.PLAIN, 18);
        // 设置字体。
        g.setFont(font);

        // 画边框。
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        //随机产生12条干扰线，使图象中的认证码不易被其它程序探测到。 g.setColor(Color.GRAY);
        for (int i=0;i<10;i++) { int x = random.nextInt(width); int y =
                random.nextInt(height); int xl = random.nextInt(10); int yl =
                random.nextInt(10); g.drawLine(x,y,x+xl,y+yl); }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        // 随机产生4位数字的验证码。
        int i=0;
        while(i<4) {
            // 得到随机产生的验证码数字。
            String strRand;
				/*if(i==1){
					strRand = String.valueOf((char)(random.nextInt(26)+65));
				}else{
					strRand = String.valueOf(random.nextInt(10));
				}*/
            String ch=String.valueOf(random.nextInt(10));
            if(i==0){
                if("0".equals(ch)){
                    continue;
                }
            }
            if(i>0){
                if(randomCode.indexOf(ch)!=-1){
                    continue;
                }
            }
            strRand = ch;
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(110);
            green = random.nextInt(50);
            blue = random.nextInt(50);

            // 用随机产生的颜色将验证码绘制到图像中。
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, 13 * i + 6, 16);

            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
            i++;
        }

         //将四位数字的验证码保存到Session中。
//        HttpSession session = request.getSession();
//        session.setAttribute();
//        session.removeAttribute("randomCode");
//        session.setAttribute("randomCode", randomCode.toString());


        Jedis jedis = new Jedis("127.0.0.1",6379);

        jedis.setex(request.getRemoteAddr(),60,randomCode.toString());

         //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HashMap<Object,Object> map =  new HashMap<>();
        map.put("randomCode", randomCode.toString());
        map.put("image",BufferedImageToBase64(buffImg));

        return map;

    }
    private static String BufferedImageToBase64(BufferedImage bufferedImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        try {
            ImageIO.write(bufferedImage, "png", baos);//写入流中
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();//转换成字节
        BASE64Encoder encoder = new BASE64Encoder();
        String png_base64 = encoder.encodeBuffer(bytes).trim();//转换成base64串
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
        System.out.println("值为：" + "data:image/jpg;base64," + png_base64);
        return "data:image/jpg;base64," + png_base64;
    }
}
