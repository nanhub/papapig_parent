package util;
/**
 * 
 * 
 * 图片验证码生成工具类
 */


/**
 * session.setAttribute("rand",sRand); 
 * 后台通过session获取 匹配验证
 * 
 * web.xml 配置
 * <!-- 图片验证码 -->
  <servlet>
    <servlet-name>AuthImageServlet</servlet-name>
    <servlet-class>com.bw.utils.AuthImageServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>AuthImageServlet</servlet-name>
    <url-pattern>/AuthImageServlet</url-pattern>
  </servlet-mapping>
 * 
 * 调用方法
 * function changeCode(){
		var imgNode = document.getElementById("vimg");
		//重新加载验证码，达到刷新的目的
		imgNode.src = "AuthImageServlet?t=" + Math.random();
		// 防止浏览器缓存的问题
	}
 * 			jsp页面 显示
 * 			<label>输入验证码</label>
			<input type="text" name="randomCode"/>
			<img id="vimg"  title="点击更换" onclick="changeCode();" src="servlet/AuthImageServlet">
 * 
 * 
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller

public class AuthImageController {
 
    private static final String CONTENT_TYPE = "text/html; charset=gb2312";   
    //设置字母的大小,大小   
    private Font mFont = new Font("Times New Roman", Font.PLAIN, 17);   
//    public void init() throws ServletException
//    {
//        super.init();
//    }
    Color getRandColor(int fc,int bc)   
    {   
        Random random = new Random();   
        if(fc>255) fc=255;   
        if(bc>255) bc=255;   
        int r=fc+random.nextInt(bc-fc);   
        int g=fc+random.nextInt(bc-fc);   
        int b=fc+random.nextInt(bc-fc);   
        return new Color(r,g,b);   
    }
    @RequestMapping("AuthImageServlet")
    @ResponseBody
  public String createImage(HttpServletRequest request, HttpServletResponse response)
//    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {   
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");   
        response.setDateHeader("Expires", 0);   
        //表明生成的响应是图片   
        response.setContentType("image/jpeg");   
           
        int width=80, height=18;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   
           
        Graphics g = image.getGraphics();   
        Random random = new Random();   
        g.setColor(getRandColor(200,250));   
        g.fillRect(1, 1, width-1, height-1);   
        g.setColor(new Color(102,102,102));   
        g.drawRect(0, 0, width-1, height-1);   
        g.setFont(mFont);   
  
        g.setColor(getRandColor(160,200));   
  
        //画随机线   
        for (int i=0;i<155;i++)   
        {   
            int x = random.nextInt(width - 1);   
            int y = random.nextInt(height - 1);   
            int xl = random.nextInt(6) + 1;   
            int yl = random.nextInt(12) + 1;   
            g.drawLine(x,y,x + xl,y + yl);   
        }   
  
        //从另一方向画随机线   
        for (int i = 0;i < 70;i++)   
        {   
            int x = random.nextInt(width - 1);   
            int y = random.nextInt(height - 1);   
            int xl = random.nextInt(12) + 1;   
            int yl = random.nextInt(6) + 1;   
            g.drawLine(x,y,x - xl,y - yl);   
        }   
  
        //生成随机数,并将随机数字转换为字母   
        String sRand="";   
        for (int i=0;i<4;i++)
        {   
            int itmp = random.nextInt(26) + 65;   
            char ctmp = (char)itmp;   
            sRand += String.valueOf(ctmp);   
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));   
            g.drawString(String.valueOf(ctmp),15*i+10,16);   
        }   
  
        HttpSession session = request.getSession(true);
        session.setAttribute("rand",sRand);   
        g.dispose();
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    } 
}