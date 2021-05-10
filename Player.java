
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
public class Player extends JComponent implements Updatable
{
	private double dx, dy, x, y;
	private int w = 20, h = 20; 
	private Color color;
	private boolean falling = true;
	public Player(double x, double y, Color c)
	{
		
		this.setSize(new Dimension(w,h));
		this.x = x;
		this.y = y;
		this.setLocation((int)x, (int)y);
		dy = 0;
		dx = 0;
		
		color = c;
		
	}
	public Rectangle getHitbox()
	{
		return new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		
	}

	
	
	public void setDx(double dx) { this.dx = dx;}
	public double getDx() {return dx;}
	public void setDy(double dy) { this.dy = dy;}
	public double getDy() { return dy;}

	public void setX(double i) { x = i;}
	public void setY(double i) { y = i;}
	
	public Point getPos()
	{
		return new Point((int)x,(int)y);
	}
	public void setPos(double x,double y)
	{
		this.setX(x);
		this.setY(y);
	}
	public void setFalling(boolean b) { falling = b;}
	public boolean isFalling() { return falling;}
	
	
	public void update()
	{

		x += dx;
		y += dy;
		/*
		if (isFalling())
		{
			y += dy;
			//gravity acc.
			dy += 0.01;
		}
		*/
		this.setLocation((int)(x), (int)(y)); 
		
		this.setPos(x, y);
		repaint();
		
	}
	//naming this "setColor" causes errors 
	public void setObjectColor(Color c)
    {
		this.color = c; 
    }
	
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 30, 30);
		
	}
		
}
