package lines;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class graphicsPanel extends JPanel {
	
	/**
	 * Create the panel.
	 */
	int mode = -1;
	int count = 0;
	int x1, y1,x2,y2;
	frame frame = null;
	boolean first = true;
	int frameBuff[][];
	
	Triangle t = new Triangle();
	boolean complete = false;
	
	//we will create three array lists one for each line type
	ArrayList<ParametricLine> parametricList = new ArrayList<ParametricLine>();
	ArrayList<BresLine> bresenhamList = new ArrayList<BresLine>();
	ArrayList<APILine> apiList = new ArrayList<APILine>();
	public graphicsPanel(frame f) {
		this.frame = f;
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("CLICK");
					int x = e.getX();
					int y = e.getY();
				if (e.getButton() == MouseEvent.BUTTON1) {
						if (count == 0){
							x1 = x;
							y1 = y;
							count++;
						}
						else if (count == 1){
							x2 = x;
							y2 = y;
							count++;
						}
						else{
							makeTriangle(x1,y1,x2,y2,x,y);
							count = 0;
						}
					}
					repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
		});
	}
	
	public void hub(int x1, int y1, int x2, int y2){
		switch (mode){
		case -1: 
			break;
		
		//Parametric
		case 0:
			ParametricLine pline = new ParametricLine (x1,y1,x2,y2,frame.getSteps());
			parametricList.add(pline);
			break;
			
		//Brasenham's
		case 1:
			BresLine bLine = new BresLine (x1,y1,x2,y2);
			bresenhamList.add(bLine);
			break;
			
		//API
		case 2:
			APILine aline = new APILine(x1,y1,x2,y2);
			apiList.add(aline);
			break;
		}
	}
	
	public void makeTriangle (int x1In, int y1In, int x2In, int y2In, int x3In, int y3In){
		t = new Triangle(x1In, y1In, x2In, y2In, x3In, y3In, frameBuff);
		complete = true;
	}
	
	//paint goes through each array list rendering all the lines
	public void paint (Graphics g){
		super.paint(g);
		
		if(first){
			frameBuff = new int[this.getHeight()][this.getWidth()];
			first = false;
		}
		
		Graphics2D g2d = (Graphics2D)g;
		if (complete){
			t.render(g2d);}
	}

	//clears each array list
	public void erase() {
		frameBuff = null;
		first = true;
		complete = false;
		repaint();
	}

}