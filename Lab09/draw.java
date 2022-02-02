import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.*;


public class draw extends JFrame{
	
		public draw()
		{
			setTitle("OAT NO1");
			setSize(600,600);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		public void paint(Graphics g)
		{
			g.setColor(Color.red);
			g.drawOval(175, 200 , 250 , 250);
			g.fillOval(175, 200 , 250 ,250);
			
			
			g.setColor(Color.black);
			g.drawOval(225, 250 , 50 , 50);
			g.fillOval(225, 250 , 50 ,50);
			
			g.setColor(Color.black);
			g.drawOval(325, 250 , 50 , 50);
			g.fillOval(325, 250 , 50 ,50);
			
			
			g.setColor(Color.BLUE);
			g.drawRect(295, 300, 20, 50);
			g.fillRect(295, 300, 20, 50);
			
			g.setColor(Color.green);
			g.drawRect(255, 370, 100, 50);
			g.fillRect(255,370, 100, 50);
			
		}
		public static void main(String[] args)
		{
			draw Rec = new draw();
			Rec.paint(null);
			
		}
}
