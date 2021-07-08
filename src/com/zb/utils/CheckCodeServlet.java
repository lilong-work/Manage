package com.zb.utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/check")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width=200;
        int height=120;
        //1.创建对象，在内存中图片(验证码图片对象)
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //2.美化图片
        //2.1填充背景色
        Graphics graphics = image.getGraphics();//画笔对象
        graphics.setColor(Color.PINK);
        graphics.fillRect(0,0,width,height);
        //2.2画边框
        graphics.setColor(Color.BLUE);
        //因为边框会有一个宽度
        graphics.drawRect(0,0,width-1,height-1);

        String str = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuopasdfghjklzxcvbnm0123456789";
        Random random = new Random();
        String check1=str.charAt(random.nextInt(str.length()))+"";
        String check2=str.charAt(random.nextInt(str.length()))+"";
        String check3=str.charAt(random.nextInt(str.length()))+"";
        String check4=str.charAt(random.nextInt(str.length()))+"";
        String check = check1+check2+check3+check4;
        check=check.toUpperCase();
        HttpSession session = request.getSession();
        session.setAttribute("check",check);
        //2.3写验证码
        graphics.setFont(new Font("隶书", Font.BOLD, 28));
        graphics.drawString(check1,30,70);
        graphics.drawString(check2,70,70);
        graphics.drawString(check3,115,70);
        graphics.drawString(check4,155,70);
        //2.4画干扰线
        graphics.setColor(Color.GREEN);

        //随机生成坐标点
        for(int i=0;i<10;i++){
            int x = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x,x2,y1,y2);
        }
        //3.将图片传输到页面展示
        ImageIO.write(image,"jpg",response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
