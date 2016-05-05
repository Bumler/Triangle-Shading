package lines;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Triangle {
	
	int x1,x2,x3,y1,y2,y3;
	BresLine line1, line2, line3;
	int[][] frameBuff;
	ArrayList<BresLine> shadeLines = new ArrayList<BresLine>();
	
	int left,right;
	
	public Triangle(){
		
	}
	
	public Triangle (int x1In, int y1In, int x2In, int y2In, int x3In, int y3In, int[][] frameBuffIn){
		x1 = x1In;
		x2 = x2In;
		x3 = x3In;
		y1 = y1In;
		y2 = y2In;
		y3 = y3In;
		
		frameBuff = frameBuffIn;
		
		line1 = new BresLine(x1,y1,x2,y2,0,128);
		line2 = new BresLine(x2,y2,x3,y3,128,255);
		line3 = new BresLine(x3,y3,x1,y1,255,0);
	}
	
//	public void shade(){
//		for (int i = 0; i < frameBuff.length; i++){
//			for (int j = 0; j < frameBuff.length; j++){
//				if(frameBuff[i][j]!= 0){
//					for (int n = frameBuff.length; n > 0; n--){
//						if(frameBuff[i][n]!= 0){
//							shadeLines.add(new BresLine(i,j,i,n,frameBuff[i][j],frameBuff[i][n]));
//						}
//					}
//				}
//			}
//		}
//	}
	
	public void render(Graphics2D g2d){
		line1.render(frameBuff, g2d);
		line2.render(frameBuff, g2d);
		line3.render(frameBuff, g2d);
//		shade();
//		for (BresLine l:shadeLines){
//			l.render(frameBuff, g2d);
//		}
	}
}
