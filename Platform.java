
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
public class Platform extends JComponent 
{
	private double x, y;
	private int w, h;
	private Color color; 
	public Platform(int x, int y, int w, int h, Color c)
	{
		this.setSize(new Dimension(w,h));
		this.setLocation((int)x,(int)y);
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		color = c;
	}
	public Rectangle getRect()
	{
		Rectangle rect = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		return rect;
		
	}
	
	public void setPlatformColor(Color c){ color = c; }
	public Color getPlatformColor(){ return this.color; }
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
	    g2.setColor(color);
		g2.fillRect(0, 0, w, h);
	}
	
	
	
}