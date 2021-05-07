import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.Paper;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;



public class BattleGameTesting extends JFrame implements ActionListener
{
	public ArrayList<Updatable> characters;
	public ArrayList<Platform> platforms;
	public ArrayList<Person> players;
	public ArrayList<Weapon> weapons;
	private JFrame frame;
	private double multiplySpeed = 4;
	private Person player;
	//private Person player2;
	public BattleGameTesting()
	{
		frame = this;
		characters = new ArrayList<Updatable>();
		platforms = new ArrayList<Platform>();
		//weapons = new ArrayList<Weapon>();
		players = new ArrayList<Person>();
		
		setBounds(100, 100, 800, 500);
		setResizable(true);
		
		setTitle("Pong / Brick Breaker");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		/*
		Ranged w = new Ranged("Sniper", (int)(Math.random()*this.getWidth()), (int)(Math.random())*this.getHeight()+200, 0.4, 4, 3*multiplySpeed, 100, 3, 2, 1, Color.RED);
		add(w);
		characters.add(w);
		weapons.add(w);
		w = new Ranged("Pistol", (int)(Math.random()*this.getWidth()), (int)(Math.random()*this.getHeight()+200), 0.1, 0.5, 2*multiplySpeed, 100, 5, 5, 1, Color.CYAN);
		add(w);
		characters.add(w);
		weapons.add(w);
		w = new Ranged("Uzi", (int)(Math.random()*this.getWidth()), (int)(Math.random()*this.getHeight()+200), 0.01, 2, 1.5*multiplySpeed, 100, 100, 20, 3, Color.GREEN);
		add(w);
		characters.add(w);
		weapons.add(w);
		w = new Ranged("Shotgun", (int)(Math.random()*this.getWidth()), (int)(Math.random()*this.getHeight()+200), 0.3, 2, 2*multiplySpeed, 100, 15, 50, 8, Color.MAGENTA);
		add(w);
		characters.add(w);
		weapons.add(w);
		*/
		
		player = new Person(200,250,Color.BLUE);
		add(player);
		characters.add(player);
		players.add(player);
		/*
		player2 = new Person(300,250,Color.RED);
		add(player2);
		characters.add(player2);
		players.add(player2);
		*/
		
		Platform ground = new Platform(100,350,600,50,Color.BLUE);
		add(ground);
		//characters.add(ground);
		platforms.add(ground);
		
		final double speedP = 1.2 * multiplySpeed;
		this.setVisible(true);
		
		this.addKeyListener(new KeyListener() 
		{
			
			public void keyPressed(KeyEvent e) 
			{
				
				if(e.getKeyCode() == e.VK_W)
				{
					
					if (!player.isFalling())
					{
						player.setDy(-speedP);
						player.setFalling(true);
					}
				}
				else if(e.getKeyCode() == e.VK_S)
				{
					if (player.isFalling())
					{
						player.setDy(speedP);
						player.setFalling(true);
					}
					
					
				
					
					
					
				}
				else if(e.getKeyCode() == e.VK_A)
				{
					player.setDx(-speedP);
					
					
					
					
				}
				else if(e.getKeyCode() == e.VK_D)
				{
					player.setDx(speedP);
				}
				/*
				else if(e.getKeyCode() == e.VK_SPACE)
				{
					if(player.getWeapon() instanceof Ranged)
					{
						if ((player.getWeapon().use()) && (!((Ranged)player.getWeapon()).isReloading()))
						{
							int k = (int)(Math.random()*360);
							Point m = MouseInfo.getPointerInfo().getLocation();
							Point f = frame.getLocation();
							
							int relX = f.x-(m.x-8);
							int relY = f.y-(m.y-30);
							
							double dx = - relX - (player.getX()+player.getWidth()/2);
							double dy = relY + (player.getY()+player.getHeight()/2);
							
							double a = Math.atan(-dy/dx);
							if (dx < 0)
								a += Math.PI;
							Ranged weapon = (Ranged)player.getWeapon();
							for(int i = 0; i < weapon.getBulletsPerShot(); i++)
							{
								Projectile p = new Projectile(player.getX()+player.getWidth()/2-5, player.getY()+player.getHeight()/2-5, weapon.getBulletVelocity(), a/Math.PI*180, weapon.getBulletSpread());
								add(p);
								characters.add(p);
							}
							
							
						}
						
					}
					
				}
				else if(e.getKeyCode() == e.VK_R)
				{
					if(player.getWeapon() instanceof Ranged)
					{
						if (!((Ranged)player.getWeapon()).isReloading())
						{
							if (((Ranged)player.getWeapon()).startReload())
							{
								System.out.println("reloading");
								
							}
							else
							{
								System.out.println("failed reloading");
							}
						}
						
					}
					
				}
				else if(e.getKeyCode() == e.VK_F)
				{
					for(Weapon w : weapons)
					{
						if (w.isInPickupRange(player))
						{
							player.pickup(w);
							print("yay");
						}
						else
						{
							print("aww");
						}		
					}
				}
				*/
				/*
				if(e.getKeyCode() == e.VK_UP)
				{
					
					if (!player2.isFalling())
					{
						player2.setDy(-speedP);
						player2.setFalling(true);
					}
				}
				else if(e.getKeyCode() == e.VK_DOWN)
				{
					if (player2.isFalling())
					{
						player2.setDy(speedP);
						player2.setFalling(true);
					}
					
				
					
					
				}
				else if(e.getKeyCode() == e.VK_LEFT)
				{
					player2.setDx(-speedP);
					
					
				}
				else if(e.getKeyCode() == e.VK_RIGHT)
				{
					player2.setDx(speedP);
				}
				/*
				else if(e.getKeyCode() == e.VK_P)
				{
					if(player2.getWeapon() instanceof Ranged)
					{
						if ((player2.getWeapon().use()) && (!((Ranged)player2.getWeapon()).isReloading()))
						{
							int k = (int)(Math.random()*360);
							Point m = MouseInfo.getPointerInfo().getLocation();
							Point f = frame.getLocation();
							
							int relX = f.x-(m.x-8);
							int relY = f.y-(m.y-30);
							
							double dx = - relX - (player2.getX()+player2.getWidth()/2);
							double dy = relY + (player2.getY()+player2.getHeight()/2);
							
							double a = Math.atan(-dy/dx);
							if (dx < 0)
								a += Math.PI;
							Ranged weapon = (Ranged)player2.getWeapon();
							for(int i = 0; i < weapon.getBulletsPerShot(); i++)
							{
								Projectile p = new Projectile(player2.getX()+player2.getWidth()/2-5, player2.getY()+player2.getHeight()/2-5, weapon.getBulletVelocity(), a/Math.PI*180, weapon.getBulletSpread());
								add(p);
								characters.add(p);
							}
							
							
						}
						
					}
					
				}
				
				else if(e.getKeyCode() == e.VK_O)
				{
					if(player2.getWeapon() instanceof Ranged)
					{
						if (!((Ranged)player2.getWeapon()).isReloading())
						{
							if (((Ranged)player2.getWeapon()).startReload())
							{
								System.out.println("reloading");
								
							}
							else
							{
								System.out.println("failed reloading");
							}
						}
						
					}
					
				}
				else if(e.getKeyCode() == e.VK_I)
				{
					for(Weapon w : weapons)
					{
						if (w.isInPickupRange(player2))
						{
							player2.pickup(w);
							print("yay");
						}
						else
						{
							print("aww");
						}
					}
					
				}
				*/
				
			}
			public void keyReleased(KeyEvent e) 
			{
				
				/*
				if(e.getKeyCode() == e.VK_W)
				{
					player.setDy(0);
					
					
				}
				else if(e.getKeyCode() == e.VK_S)
				{
					player.setDy(0);
				}	
				*/	
					
				
				if(e.getKeyCode() == e.VK_A)
				{
					player.setDx(0);
					
					
					
					
				}
				else if(e.getKeyCode() == e.VK_D)
				{
					player.setDx(0);
					
				}
				
				/*
				
				if(e.getKeyCode() == e.VK_LEFT) 
				{
					player2.setDx(0);
					
					
					
					
				}
				else if(e.getKeyCode() == e.VK_RIGHT)
				{
					player2.setDx(0);
					
				}
				*/
				
				
			}
			public void keyTyped(KeyEvent e) {}
		});
		Timer timer = new Timer(2,this);
		timer.start();
	}
	public static void main(String[] args) 
	{
		new BattleGameTesting();
	}
	public void actionPerformed(ActionEvent e)
	{
		for(Updatable character: characters)
		{		
			character.update();
		}
		boolean onGround = false;
		
		for(Platform p : platforms)
		{
			if(p.getRect().intersects(player.getRect()))
			{
				int playerCY = player.getY()+player.getHeight()/2;
				int pCY = p.getY()+p.getHeight()/2;
				int playerCX = player.getX()+player.getWidth()/2;
				int pCX = p.getX()+p.getWidth()/2;
				
				if (playerCY < pCY)
				{
					//top left quarter
					if (playerCX < pCX) 
					{
						if ((p.getY() - player.getY()-player.getHeight()) > p.getX()-player.getX()-player.getWidth()) 
						{
							player.setFalling(false);
							player.setDy(0);
							player.setY(p.getY()-player.getHeight());
						}
						else
						{
							
							player.setDx(0);
							player.setX(p.getX()-player.getWidth());
						}
					}
					//top right quarter
					else
					{
						if((p.getY() - player.getY()-player.getHeight()) > player.getX() - p.getX() - p.getWidth()) 
						{
							
							player.setFalling(false);
							player.setDy(0);
							player.setY(p.getY()-player.getHeight());
						}
						else
						{
							
							player.setDx(0);
							player.setX(p.getX()+p.getWidth());
						}
					}
				}
				else
				{
				 	//bottom left quarter
					if (playerCX < pCX) 
					{
						if ((player.getY()-p.getY()-p.getHeight()) > p.getX()-player.getX()-player.getWidth()) 
						{
							
							player.setDy(0);
							player.setY(p.getY()+p.getHeight());
						}
						else
						{
							
							player.setDx(0);
							player.setX(p.getX()-player.getWidth());
						}
					}
					//bottom right quarter
					else
					{
						if((player.getY()-p.getY()-p.getHeight()) > player.getX() - p.getX() - p.getWidth()) 
						{
							player.setDy(0);
							player.setY(p.getY()+p.getHeight());
						}
						else
						{
							
							player.setDx(0);
							player.setX(p.getX()+p.getWidth());
						}
					}
				}

			}
			//testing if ground is beneath player
			if(!player.isFalling())	
			{
				// tests if bottom left corner, bottom center point, or bottom right corner is on any platform
				if(p.getRect().contains(player.getX(),player.getY()+player.getHeight()) || p.getRect().contains(player.getX()+player.getWidth()/2,player.getY()+player.getHeight()) || p.getRect().contains(player.getX()+player.getWidth(),player.getY()+player.getHeight()))
				{
					onGround = true;
				}
				
			}
		}	
		if(!onGround)
		{
			player.setFalling(true);	
			
		}
		repaint();	
	}
	
	
	
	public void print(String s) 
	{
		System.out.println(s);
	}
	
	
	
}
