package lines;

import java.awt.Graphics2D;

public class Triangle {
	
	int x1,x2,x3,y1,y2,y3;
	BresLine line1, line2, line3;
	int[][] frameBuff;
	
	public Triangle(){
		
	}
	
	public Triangle (int x1In, int y1In, int x2In, int y2In, int x3In, int y3In, int[][] frameBuffIn){
		x1 = x1In;
		x2 = x2In;
		x3 = x3In;
		y1 = y2In;
		y2 = y2In;
		y3 = y3In;
		
		frameBuff = frameBuffIn;
		
		line1 = new BresLine(x1,y1,x2,y2);
		line2 = new BresLine(x2,y2,x3,y3);
		line3 = new BresLine(x3,y3,x1,y1);
	}
	
	public void render(Graphics2D g2d){
		line1.render(frameBuff, g2d);
		line2.render(frameBuff, g2d);
		line3.render(frameBuff, g2d);
	}
}
