
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
abstract class Weapon extends JComponent
{
	private int posX, posY;
	private String name;
	private Person player;
	
	//public Weapon(String n, Person p, int x, int y)
	public Weapon(String n, Person p, int x, int y)
	{
		name = n;
		posX = x;
		posY = y;
		this.setSize(new Dimension(30,30));
		this.setLocation(posX, posY);
		
	}
	public Weapon(String n, int x, int y)
	{
		name = n;
		posX = x;
		posY = y;
		this.setSize(new Dimension(30,30));
		this.setLocation(posX, posY);
		
	}
	
	abstract boolean use();
	public void toPerson(Person p)
	{
		player = p;
		this.setVisible(false);
	}
	public boolean isInPickupRange(Person p)
	{
		if(Math.hypot(this.getX()-p.getX(), this.getY()-p.getY()) < 60)
		{
			return(true);
		}
		return(false);
	}
	public int getX()
	{
		return posX;
	}
	public int getY()
	{
		return posY;
	}
	public String getName()
	{
		return(name);
	}
	public Person getPlayer()
	{
		return player;
	}
	
}
