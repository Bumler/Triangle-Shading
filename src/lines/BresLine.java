package lines;

import java.awt.Color;
import java.awt.Graphics2D;

//this is just a basic holder the api's line drawing algorithm 
//it holds the variables and draws the line yellow
public class BresLine {
	int x1;
	int y1;
	int x2;
	int y2;
	int deltaX;
	int deltaY;
	int Pk;
	
	int color1 = 255;
	int color2 = 255;
	
	int color = 255;
	
	boolean yAlg = false;
	boolean xAlg = false;
	
	boolean bigYfirst = false;
	boolean bigXfirst = false;
	
	public BresLine (int x1, int y1, int x2, int y2){
		//makes sure that x1 is less than x2
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;

			//we need to organize our points by comparing the delta X and Y
			deltaX = Math.abs(x2 - x1);
			deltaY = Math.abs(y2 - y1);
			if (deltaX < deltaY){
				//once we compare our delta values we need to order our points
				yAlg = true;
				if (this.y1 > this.y2){
					this.x1 = x2;
					this.y1 = y2;
					this.x2 = x1;
					this.y2 = y1;
					
					int tempColor = color2;
					color2 = color1;
					color1 = tempColor;
				}
			}
			else if (deltaY < deltaX){
				xAlg = true;
				if (this.x1 > x2){
					this.x1 = x2;
					this.y1 = y2;
					this.x2 = x1;
					this.y2 = y1;
					
					int tempColor = color2;
					color2 = color1;
					color1 = tempColor;
				}
			}
			
			//the big Y first allowed us to plot points on the graphs 1st and 3rd quadrant
			//the big X first allows to plot points between 45 and 90 deg on the 3rd and 4th quadrant
			//assuming your looking at a standard graph
			//this may not be the most optimal fix and could be inefficient
			if (this.y1-this.y2 > 0){
				bigYfirst = true;
			}
			if (this.x1-this.x2 > 0){
				bigXfirst = true;
			}
			
			// this is our initial parameter
			// 2deltay - deltaX
			if (xAlg) {Pk = (2*deltaY) - deltaX;}
			else {Pk = (2*deltaX) - deltaY;}
			
			//deltaX and Y are best stored like this
			deltaX *= 2;
			deltaY *= 2;
	}
	
	public BresLine (int x1, int y1, int x2, int y2, int c1, int c2){
		this (x1,y1,x2,y2);
		color1 = c1;
		color2 = c2;
		color = color1;
	}
	
	//https://www.youtube.com/watch?v=TRbwu17oAYY
	//helped explaining the algorithm
	public void render (Graphics2D g2d){
		g2d.setColor(Color.RED);
		//plot our starting point
		g2d.drawOval(x1, y1, 1, 1);
			
			int x = x1;
			int y = y1;
			//when the y is larger in front only the x version of the algorithm is possible
			//after that we have a few more if statements to cover every other possible algorithm
			if (bigYfirst){
				if (xAlg){
					while (x < x2) {
						if (Pk < 0) {
							x++;
							g2d.drawOval(x, y, 1, 1);
							Pk = Pk + deltaY;
						} else {
							x++;
							y--;
							g2d.drawOval(x, y, 1, 1);
							Pk = Pk + deltaY - deltaX;
						}
					}	
					}
				}
			else{
				if (xAlg){
				while (x < x2) {
					if (Pk < 0) {
						x++;
						g2d.drawOval(x, y, 1, 1);
						Pk = Pk + deltaY;
					} else {
						x++;
						y++;
						g2d.drawOval(x, y, 1, 1);
						Pk = Pk + deltaY - deltaX;
					}
				}	
				}
				else if (bigXfirst){
					while (y < y2) {
						if (Pk < 0) {
							y++;
							g2d.drawOval(x, y, 1, 1);
							Pk = Pk + deltaX;
						} else {
							x--;
							y++;
							g2d.drawOval(x, y, 1, 1);
							Pk = Pk + deltaX - deltaY;
						}
					}	
				}
				else
					while (y < y2) {
						if (Pk < 0) {
							y++;
							g2d.drawOval(x, y, 1, 1);
							Pk = Pk + deltaX;
						} else {
							x++;
							y++;
							g2d.drawOval(x, y, 1, 1);
							Pk = Pk + deltaX - deltaY;
						}
					}	
			}
	}

	//https://www.youtube.com/watch?v=TRbwu17oAYY
	//helped explaining the algorithm
	public void render (int[][] frameBuff, Graphics2D g2d){
		//g2d.setColor(Color.RED);
		//plot our starting point
		//g2d.drawOval(x1, y1, 1, 1);
		frameBuff[y1][x1] = 1;
			
			int x = x1;
			int y = y1;
			//when the y is larger in front only the x version of the algorithm is possible
			//after that we have a few more if statements to cover every other possible algorithm
			if (bigYfirst){
				if (xAlg){
					while (x < x2) {
						if (Pk < 0) {
							x++;
							//g2d.drawOval(x, y, 1, 1);
							frameBuff[y][x] = 1;
							Pk = Pk + deltaY;
						} else {
							x++;
							y--;
							//g2d.drawOval(x, y, 1, 1);
							frameBuff[y][x] = 1;
							Pk = Pk + deltaY - deltaX;
						}
					}	
					}
				}
			else{
				if (xAlg){
				while (x < x2) {
					if (Pk < 0) {
						x++;
						//g2d.drawOval(x, y, 1, 1);
						frameBuff[y][x] = 1;
						Pk = Pk + deltaY;
					} else {
						x++;
						y++;
						//g2d.drawOval(x, y, 1, 1);
						frameBuff[y][x] = 1;
						Pk = Pk + deltaY - deltaX;
					}
				}	
				}
				else if (bigXfirst){
					while (y < y2) {
						if (Pk < 0) {
							y++;
							//g2d.drawOval(x, y, 1, 1);
							frameBuff[y][x] = 1;
							Pk = Pk + deltaX;
						} else {
							x--;
							y++;
							//g2d.drawOval(x, y, 1, 1);
							frameBuff[y][x] = 1;
							Pk = Pk + deltaX - deltaY;
						}
					}	
				}
				else
					while (y < y2) {
						if (Pk < 0) {
							y++;
							//g2d.drawOval(x, y, 1, 1);
							frameBuff[y][x] = 1;
							Pk = Pk + deltaX;
						} else {
							x++;
							y++;
							//g2d.drawOval(x, y, 1, 1);
							frameBuff[y][x] = 1;
							Pk = Pk + deltaX - deltaY;
						}
					}	
			}
			
			for(int i = 0; i < frameBuff.length; i++){
				for (int j = 0; j < frameBuff[i].length; j++){
					if (frameBuff[i][j] == 1){

						if(xAlg){
							System.out.println("x");
							float a = (float)(j-x1)/(x2-x1);
							color = (int) (((1-a) * color1) + (a * color2));
							if (color > 255){
								color = 255;}
							if (color <  1){
								color = 1;}
						}
						else{
							System.out.println("y");
							float a = (float)(i-y1)/(y2-y1);
							color = (int) (((1-a) * color1) + (a * color2));
							if (color > 255){
								color = 255;}
							if (color <  1){
								color = 1;}
						}
						System.out.println(color);
						frameBuff[i][j] = color;
						g2d.setColor(new Color(color,color,color));
						g2d.fillRect(j, i, 1, 1);
					}
//					else {
//						g2d.setColor(Color.PINK);
//						g2d.fillRect(j, i, 1, 1);
//					}
				}
			}
	}
}
