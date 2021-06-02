
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
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
	private JLabel nameTag, healthTag;
	private JFrame frame;
	private boolean tryGrab = false;
	private Player heldBy, holding;
	private int maxJumps = 2;
	private int jumps = maxJumps; 
	private String facing = "d";
	private boolean wallSliding = false;
	private boolean slamming = false;
	private boolean tryShoot= false;
	private int maxHealth = 100;
	private int health = maxHealth;
	private boolean reloading, firing;
	private int reloadEnd = 0,  fireEnd = 0;
	private int maxBullets = 5;
	private int bullets = maxBullets;
	private int lives = 3;
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
		
		healthTag = new JLabel("100", SwingConstants.CENTER);
		
		healthTag.setFont(new Font("Helvetica", Font.BOLD, 13));
		healthTag.setSize(100,20);
		
		//nameTag.setLocation(100, 100);
		color = c;
		this.frame = frame;
		frame.add(nameTag);
		frame.add(healthTag);
		
	}
	public Rectangle getHitbox()
	{
		return new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		
	}
	public void changeHealth(int c)
	{
		health += c;
		if( health < 0)
			health = 0;
		color = new Color((int)(255*(1.0*health/maxHealth)), 0, 0);
		healthTag.setText(health + "");
	}
	public void resetHealth()
	{
		health = maxHealth;
		color = new Color((int)(255*(1.0*health/maxHealth)), 0, 0);
		healthTag.setText(health + "");
		lives -= 1;
	}
	public int getHealth(){return health;}
	
	public int getLives(){return lives;}
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

	
	public boolean startReload() 
	{
		if (bullets != maxBullets)
		{
			reloadEnd = (int) ((System.currentTimeMillis() / 1000l) + 1);
			reloading = true;
			setObjectColor(Color.RED);
			return(true);
		}
		return(false);
	}

	public boolean isReloading()
	{
		return reloading;
	}
	public boolean isfiring()
	{
		return firing;
	}
	
	public void setX(double i) { x = i;}
	public void setY(double i) { y = i;}
	
	
	public Projectile shoot()
	{
		if(bullets > 0)
		{
			bullets -= 1;
			if(facing.equals("a"))
			{
				return(new Projectile((int)x - 5, (int)y+h/2, -7));
			}
			else
			{
				return(new Projectile((int)x + w + 5, (int)y+h/2, 7));
			}
		}
		return null;
				
			
	}
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
	
	public String getFacing(){ return facing;}
	public void setFacing(String s){ facing = s;}
	
	public boolean isWallSliding() {return wallSliding;}
	public void setWallSliding(boolean b) {wallSliding = b;}
	
	public boolean isSlamming() {return slamming;}
	public void setSlamming(boolean b) {slamming = b;}
	
	public void update()
	{
		if (reloading)
		{
			
			if (System.currentTimeMillis() / 1000l > reloadEnd)
			{
				reloading = false;
				bullets = maxBullets;
				setObjectColor(Color.BLUE);
			}
		}
		if (firing)
		{
			
			if (System.currentTimeMillis() / 1000l > fireEnd)
			{
				firing = false;
				setObjectColor(Color.GREEN);
			}
		}
		
		if(health <= 0)
		{
			if(lives != 0)
			{
				x = Math.random()*(600-w);
				y = -30;
				dx = 0;
				dy = 0;
				resetHealth();	
			}
			else
			{
				y = 1000000;
			}
		}
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
				if(!wallSliding)
				{
					dy += 0.2;
					if(slamming)
					{
						dy += 0.1;
					}
				}
				else
				{
					if(dy > 1);
					{
						dy = 1;
					}
					
				}
				
				
			}
		}
		
		
		if(x < 0)
		{
			x = 0;
		}
		
		if(x > 593 - w)
		{
			x = 593-w;
		}

		if(y > 600)
		{
			changeHealth(100);
		}
		this.setLocation((int)(x), (int)(y)); 
		
		this.setPos(x, y);
		nameTag.setLocation((int)x + w/2 - nameTag.getWidth()/2, (int)y - 30);
		healthTag.setLocation((int)x + w/2 - healthTag.getWidth()/2, (int)y - 15);
		repaint();
		
	}
	//naming this "setColor" causes errors 
	public void setObjectColor(Color c)
    {
		this.color = c; 
    }
	
	
	public void paintComponent(Graphics g)
	{
		g.setColor(color);
		g.fillRect(0, 0, 30, 30);
		
	}
	public void setShoot(boolean b) {tryShoot = b;}
	public boolean isTryShoot()
	{return tryShoot;}
}