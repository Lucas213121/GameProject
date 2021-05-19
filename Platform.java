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
	private int damage = 0;
	public Platform(int x, int y, int w, int h, Color c, int dam)
	{
		this.setSize(new Dimension(w,h));
		this.setLocation((int)x,(int)y);
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		color = c;
		damage = dam;
	}
	public Rectangle getHitbox()
	{
		Rectangle rect = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		return rect;
	}
	public int getDamage(){return damage;}
	public void setDamage(int d){damage = d;}
	
	public void setPlatformColor(Color c){ color = c; }
	public Color getPlatformColor(){ return this.color; }
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
	    g2.setColor(color);
		g2.fillRect(0, 0, w, h);
	}
	
	
	
}