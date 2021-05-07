import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.time.Clock;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JComponent;
public class Ranged extends Weapon implements Updatable 
{
	
	private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	private double reloadEnd, fireEnd, bulletVelocity;
	private boolean reloading, firing = false; 
	
	//Temporary colors, before we add actual models
	private Color color;
	
	private int bulletDistance, bulletsInMag, maxInMag, bulletsPerShot;
	private double reloadSpeed, bulletSpread, fireRate;
	public Ranged(String n, Person p, int x, int y, double fir, double rel, double vel, int dis, int mag, double spr, int bps, Color c)
	{
		super(n,p,x,y);
		this.setSize(new Dimension(30,30));
		this.setLocation(x,y);
		fireRate = fir;
		reloadSpeed = rel;
		bulletVelocity = vel;
		bulletDistance = dis;
		bulletsInMag = mag;
		maxInMag = mag;
		bulletSpread = spr;
		bulletsPerShot = bps;
		color = c;
		
	}
	public Ranged(String n, int x, int y, double fir, double rel, double vel, int dis, int mag, double spr, int bps, Color c)
	{
		super(n,x,y);
		this.setSize(new Dimension(30,30));
		this.setLocation(x,y);
		
		fireRate = fir;
		reloadSpeed = rel;
		bulletVelocity = vel/1.5;
		bulletDistance = dis;
		bulletsInMag = mag;
		maxInMag = mag;
		bulletSpread = spr;
		bulletsPerShot = bps;
		color = c;
	}
	public double getBulletVelocity()
	{
		return(bulletVelocity);
	}
	public boolean use()
	{
		if (bulletsInMag != 0)
		{
			firing = true;
			bulletsInMag --;
			return true;
		}
		return false;
	}
	public boolean reload()
	{
		if (bulletsInMag != maxInMag)
		{
			reloading = true;
			return true;
			
		}
		return false;
	}
	
	public void update() 
	{
		if (reloading)
		{
			
			if (System.currentTimeMillis() / 1000l > reloadEnd)
			{
				reloading = false;
				bulletsInMag = maxInMag;
				getPlayer().setObjectColor(Color.BLUE);
			}
		}
		if (firing)
		{
			
			if (System.currentTimeMillis() / 1000l > fireEnd)
			{
				firing = false;
				getPlayer().setObjectColor(Color.BLUE);
			}
		}

	}
	public int getBulletsInMag() 
	{
		return bulletsInMag;
	}
	public int getBulletsPerShot() 
	{
		return bulletsPerShot;
	}
	public boolean startReload() 
	{
		if (bulletsInMag != maxInMag)
		{
			reloadEnd = (System.currentTimeMillis() / 1000l) + reloadSpeed;
			reloading = true;
			getPlayer().setObjectColor(Color.RED);
			return(true);
		}
		return(false);
	}
	public boolean startFire() 
	{
		if (bulletsInMag != maxInMag)
		{
			reloadEnd = (System.currentTimeMillis() / 1000l) + fireRate;
			reloading = true;
			getPlayer().setObjectColor(Color.GREEN);
			return true;
		}
		return false;
	}

	public boolean isReloading()
	{
		return reloading;
	}
	public boolean isfiring()
	{
		return firing;
	}
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
	    g.setColor(color);
	    
		g.fillRect(0, 0, 10, 10);
	}
	public double getBulletSpread() 
	{	
		return bulletSpread;
	}
	
	
}