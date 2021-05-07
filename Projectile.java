import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
public class Projectile extends JComponent implements Updatable
{
	double dx, dy, posX, posY;
	private double rotation, velocity, spread;
	private Color color;
	private Ellipse2D.Double circle = new Ellipse2D.Double(0,0,10,10);
	public Projectile(int x, int y, double d, double rot, double spr)
	{
		this.setSize(new Dimension(10,10));
		this.setLocation(x,y);
		posX = x;
		posY = y;
		spread = spr;
		rotation = rot + Math.random()*spread - spread/2;
		velocity = d;
		
		dx = Math.cos(rotation/180.0*Math.PI)*velocity;
		dy = Math.sin(rotation/180.0*Math.PI)*velocity;
		
	}
	public Rectangle getRect()
	{
		Rectangle rect = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		return rect;
		
	}
	public void setDx(double dx)
	{
		this.dx = dx;
	}
	public void setDy(double dy)
	{
		this.dy = dy;
	}
	public double getDx()
	{
		return dx;
	}
	public double getDy()
	{
		return dy;
	}
	public void update() 
	{
		posX += dx;
		posY += dy;
		dy += 0.09;
		this.setLocation((int)posX,(int)posY);
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fill(circle);
		g2.draw(circle);
	}
}