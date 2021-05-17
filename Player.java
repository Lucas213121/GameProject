
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.time.Instant;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class Player extends JComponent implements Updatable
{
	private double dx, dy, x, y;
	private int w = 20, h = 20; 
	private Color color;
	private boolean falling = true;
	private JLabel nameTag;
	private JFrame frame;
	private boolean tryGrab = false;
	private Player heldBy, holding;
	private int maxJumps = 2;
	private int jumps = maxJumps;
	private long time;
	
	public Player(double x, double y, Color c, JFrame frame)
	{
		
		this.setSize(new Dimension(w,h));
		this.x = x;
		this.y = y;
		this.setLocation((int)x, (int)y);
		dy = 0;
		dx = 0;
		
		nameTag = new JLabel("Loading...", SwingConstants.CENTER);
		
		nameTag.setFont(new Font("Helvetica", Font.BOLD, 13));
		nameTag.setSize(100,20);
		
		//nameTag.setLocation(100, 100);
		color = c;
		this.frame = frame;
		frame.add(nameTag);
		
	}
	public Rectangle getHitbox()
	{
		return new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		
	}
	
	
	
	public void setName(String s) { nameTag.setText(s);}
	public String getName() {return nameTag.getText();}
	public void setDx(double dx) { this.dx = dx;}
	public double getDx() {return dx;}
	public void setDy(double dy) { this.dy = dy;}
	public double getDy() { return dy;}
	
	public void setTryGrab(boolean b) {tryGrab = b;}
	public boolean isTryGrab() {return tryGrab;}
	
	public void setHeldBy(Player p) {heldBy = p;}
	public Player getHeldBy() {return heldBy;}
	public void makeInvolentaryFriend(Player p) {holding = p;}
	public Player getInvolentaryFriend() {return holding;}

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
	public void resetJumps() { jumps = maxJumps;}
	public void jump() { jumps--;}
	public int numJumpsLeft() { return jumps;}
	
	
	public void update()
	{

		time = Instant.now().toEpochMilli();
		if(heldBy != null)
		{
			x = heldBy.getX();
			y = heldBy.getY()-30;
			dx = 0;
			dy = 0;
		}
		
		else
		{
			x += dx;
		
		//y += dy;
			
			if (isFalling())
			{
				y += dy;
				//gravity acc.
				dy += 0.2;
			}
		}
		
		this.setLocation((int)(x), (int)(y)); 
		
		this.setPos(x, y);
		nameTag.setLocation((int)x + w/2 - nameTag.getWidth()/2, (int)y - 10);
		
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
