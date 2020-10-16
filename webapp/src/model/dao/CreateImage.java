package model.dao;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;




public class CreateImage {
	private static final int WIDTH = 100;//设置宽度
    private static final int HEIGHT = 30;//设置高度
    private static final int LENGHT = 4;//设置验证码长度
    private static final int LINECOUNT = 20;//设置干扰线个数
    
    private static final String str = "23456789" + "abcdefghigklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    private static Random random = new Random();
    
    public String createCode(){
   	 
   	 String code="";
   	 for(int i=0;i<LENGHT;i++) {
   		 char c=str.charAt(random.nextInt(str.length()));
   		 
   		 code=code+c;
   	 }
   	 return code;
    }
    public Color getColor() {
   	 return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
  	  
    } 
    
    
    //获取字体样式
    public Font getFont() {
   	 return new Font("Fixedsys",Font.CENTER_BASELINE,22);
    }
    
    
    //绘制字符
    public void drawChar(Graphics g,String code) {
    	g.setFont(getFont());
    	for(int i=0 ; i<LENGHT ; i++) {
    		char c=code.charAt(i);
    		g.setColor(getColor());
    		g.drawString(c+"", 20*i+10, 20);
    	}
    }
    
    
    //绘制随机线
    public void drawLine(Graphics g) {
   	    int x = random.nextInt(WIDTH);
   	    int y = random.nextInt(HEIGHT);
  		int x1 = random.nextInt(13);
  		int y1 = random.nextInt(15);
  		g.setColor(getColor());
  		g.drawLine(x, y, x+x1, y+y1);
    }
		//在一连串字符，数组中随机取一些符号，组成指定的code

	public BufferedImage CreateIm(String code) {
		BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g=image.getGraphics();
		//设置背景颜色并设置矩形
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//验证码的绘制
		drawChar(g,code);
		for(int i=0;i<LINECOUNT;i++) {
			drawLine(g);
		}
		g.dispose();
		return image;
	}

}
