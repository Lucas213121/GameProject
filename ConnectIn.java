import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;


/*
public void setTryGrab(boolean b) {tryGrab = b;}
public boolean isTryGrab() {return tryGrab;}
public void setHeldBy(Player p) {heldBy = p;}
public Player getHeldBy() {return heldBy;}
public void makeInvolentaryFriend(Player p) {holding = p;}
public Player getInvolentaryFriend() {return holding;}





 */
public class ConnectIn extends JFrame implements ActionListener
{
	public static ArrayList<Updatable> characters;
	public static ArrayList<Player> players;
	public static ArrayList<Projectile> projectiles;
	public static JFrame frame; 
	private static int numPlayers = 0;
	public ArrayList<Platform> platforms;
	
	public ConnectIn()
	{
	
		characters = new ArrayList<Updatable>();
		platforms = new ArrayList<Platform>();
		players = new ArrayList<Player>();
		projectiles = new ArrayList<Projectile>();
		setBounds(100, 100, 600, 600);
		setResizable(false);
		setTitle("Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		Platform platform;
		
		//how to create new platforms for now
		ArrayList<ArrayList<Platform>> maps = new ArrayList<ArrayList<Platform>>();
		
		
		ArrayList<Platform> map = new ArrayList<Platform>();
		
		platform = new Platform(0,500,250,100,Color.BLUE,0);
		map.add(platform);
		
		platform = new Platform(350,500,250,100,Color.BLUE,0);
		map.add(platform);
		
		platform = new Platform(250,300,100,300,Color.RED,25);
		map.add(platform);
		
		platform = new Platform(250,275,100,25,Color.BLUE,0);
		map.add(platform);
		
		platform = new Platform(50,375,100,25,Color.BLUE,0);
		map.add(platform);
		
		platform = new Platform(450,375,100,25,Color.BLUE,0);
		map.add(platform);
		
		maps.add(map);
		
		map = new ArrayList<Platform>();
		
		platform = new Platform(20,50,50,200,Color.BLUE,0);
		map.add(platform);
		
		platform = new Platform(530,50,50,200,Color.BLUE,0);
		map.add(platform);
		
		platform = new Platform(20,150,150,50,Color.BLUE,0);
		map.add(platform);
		
		platform = new Platform(430,150,150,50,Color.BLUE,0);
		map.add(platform);
		
		platform = new Platform(20,500,200,50,Color.BLUE,0);
		map.add(platform);
		
		platform = new Platform(380,500,200,50,Color.BLUE,0);
		map.add(platform);
		
		platform = new Platform(200,300,200,50,Color.BLUE,0);
		map.add(platform);
		
		platform = new Platform(100,400,50,100,Color.BLUE,0);
		map.add(platform);
		
		maps.add(map);
		System.out.print(maps.size());
		for(Platform p : maps.get(1))
		{
			platforms.add(platform);
			add(p);
		}
		
		frame = this;

		characters = new ArrayList<Updatable>();
		setBounds(100, 100, 600, 600);
		setResizable(false);
		setTitle("Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		Timer timer = new Timer(2,this);
		timer.start();
	}
	public static void main(String[] args) throws IOException 
	{
		new ConnectIn();
		int port = 9999;
		ServerSocket server = new ServerSocket(port);
		System.out.println("clients: " + numPlayers);
		
		
		while(true)//for(;;)
		{
			try
			{
				
				System.out.println("Server listening...");
				Socket client = server.accept();
				System.out.println("server accepted client");
				
				Color color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
				Player player = new Player(Math.random()*200,Math.random()*200,color, frame);
				
				Handler clientThread = new Handler(client,numPlayers, player, players);
				
				characters.add(player);
				players.add(player);
				frame.add(player);
				
				/*heal
				player = new Player(Math.random()*200,Math.random()*200,Color.RED, frame);
				characters.add(player);
				players.add(player);
				frame.add(player);
				*/
				
				frame.repaint();
				new Thread(clientThread).start();
				add_client();
				
				
				
			}
			catch(Exception e)
			{
				System.out.println("there was an issue, 1");
			}
		}
	}
	public static void add_client()
	{
		System.out.println("clients: "+ (++numPlayers));
	}

	public static void remove_client()
	{
		System.out.println("clients: "+ (--numPlayers));
	}

	@Override
	
	
	public void actionPerformed(ActionEvent e) 
	{
		for(Updatable character: characters)
		{		
			character.update();
		}
		for(Player player : players) 
		{
			boolean onGround = false;
			player.setWallSliding(false);
			for(Platform p : platforms)
			{
				if(p.getHitbox().intersects(player.getHitbox()))
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
								
								player.setX(p.getX()-player.getWidth());
								player.setWallSliding(true);
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
								
								player.setX(p.getX()+p.getWidth());
								player.setWallSliding(true);
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
								
								player.setX(p.getX()-player.getWidth());
								player.setWallSliding(true);
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
								player.setX(p.getX()+p.getWidth());
								player.setWallSliding(true);
							}
						}
					}
					if(p.getDamage() > 0)
					{
						player.changeHealth(-p.getDamage());
						player.setDy(-2);
						
						player.setFalling(true);
						player.setDx(2);
					}
	
				}
				//testing if ground is beneath player
				if(!player.isFalling())	
				{
					// tests if bottom left corner, bottom center point, or bottom right corner is on any platform
					if(p.getHitbox().contains(player.getX(),player.getY()+player.getHeight()) || p.getHitbox().contains(player.getX()+player.getWidth()/2,player.getY()+player.getHeight()) || p.getHitbox().contains(player.getX()+player.getWidth(),player.getY()+player.getHeight()))
					{
						onGround = true;
					}
					
				}
			}	
			if(!onGround)
			{
				player.setFalling(true);
			}
			else
			{
				if(player.isSlamming())
				{
					for(Player pl : players)
					{
						int diffX = pl.getX()-player.getX();
						int diffY = pl.getY()-player.getY();	
						if(!player.equals(pl) && (Math.hypot(diffX, diffY) < 80))
						{
							if (diffX > 0)
							{
								pl.setDx(pl.getDx() + (5 - Math.pow(diffX*1.0/40, 2)));
							}
							if (diffX < 0)
							{
								pl.setDx(pl.getDx() - (5 - Math.pow(diffX*1.0/40, 2)));
							}
							if (diffY < 0)
							{
								pl.setDy(-2);
							}
							if (diffY > 0)
							{
								pl.setDy(2);	
							}
						}
					}
					player.setDy(0);
				}
				player.setSlamming(false);
				
				player.resetJumps();
			}
			
			for(int i = 0; i < projectiles.size(); i++)
			{
				Projectile p = projectiles.get(i);
				if(player.getHitbox().contains(p.getLocation()))
				{
					player.changeHealth(-p.getDamage());
					characters.remove(p);
					remove(p);
					projectiles.remove(p);
				}
			}
			if(player.isTryShoot())
			{
				Object o = player.shoot();
				
				if(o != null)
				{
					Projectile p = (Projectile) o; 
					frame.add(p);
					characters.add(p);
					projectiles.add(p);
				}
			}
			player.setShoot(false);
			if(player.isTryGrab())
			{
				player.setTryGrab(false);
				
				if(player.getHeldBy() == null)
				{
					for(Player p : players)
					{
						if(p.getY() < player.getY() + player.getHeight() && !p.equals(player) && Math.hypot(player.getX()-p.getX(),player.getY() - p.getY()) < 30)
						{
							player.makeInvolentaryFriend(p);
							p.setHeldBy(player);
						}
					}
				}
				else	
				{
					if(Math.random() < .1)
					{
						player.setDx(0);
						player.setDy(-4);
						player.getHeldBy().makeInvolentaryFriend(null);
						player.setHeldBy(null);
					}
				}
			}
		}
		repaint();
	}
}

class Handler implements Runnable
{
	private Socket client;
	private String name;
	private final int ID;
	private Player player;
	private ArrayList<Player> players;
	public Handler(Socket s, int id, Player p, ArrayList<Player> players)
	{
		client = s;
		this.ID = id;
		this. player = p;
		this.players = players;
	}
	
	@Override
	public void run() 
	{
		try
		{
					
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			
			String message;
			
			name = in.readLine();
			if(name.toUpperCase().contains("VISH"))
			{
				name = "dumb";
			}
			player.setName(name);
			final int SPEED = 3;	
			
			while((message = in.readLine()) != null)
			{
				if(message.equals("New Player"))
				{
				//	 out.print(ID);
				}
				System.out.print(message);
				switch(message)
				{	
				case "W":
					
					//if(!player.isFalling())
					
					if(player.numJumpsLeft() > 0 && !player.isSlamming())
					{
						
						if(player.isWallSliding())
						{
							if(player.getFacing().equals("a"))
							{
								player.setDx(1*SPEED);
								
							}
							else
							{
								player.setDx(-1*SPEED);
								
							}
							player.setDy(-1*SPEED);
						}
						else
						{
							player.setDy(-2*SPEED);
						}
						//jump speed
						
						player.setWallSliding(false);
						//player.setDx(0);
						player.jump();
						player.setFalling(true);
					}
					break;
				case "A":
					player.setDx(-SPEED);
					player.setFacing("a");
					break;
				case "S":
					if(player.numJumpsLeft() > 0 && !player.isSlamming())
					{
						player.setDy(6);
						player.setSlamming(true);
					}
					break;
				case "D":
					player.setDx(SPEED);
					player.setFacing("d");
					
					break;
				case "M":
					player.setShoot(true);
					System.out.print("Pew");
					break;
				
				case "w":
					//player.setDy(0);
					break;
				case "s":
					break;
				case "a":
					player.setDx(0);
					break;
				case "d":
					player.setDx(0);
					break;
				case "H":
					player.setTryGrab(true);

					break;
				case "h":
					
					player.setTryGrab(false);
					if(player.getInvolentaryFriend() != null)
					{
						Player f = player.getInvolentaryFriend();
						f.setHeldBy(null);
						int r = (int) (4);
						if(player.getFacing().equals("d"))
						{
							f.setDx(r);
							f.setDy(-r);
						}
						
						else //if(player.getFacing().equals("a"))
						{
							f.setDx(-r);
							f.setDy(-r);
						}
						
						player.makeInvolentaryFriend(null);
						
					}
					break;
				case "m":
					break;
				}
				/*
				String output = "";
				for(Player p : players)
				{
					output += "p1x";
					output += p.getX();
					output += "y";
					output += p.getY();
				}
				
				out.print(output);
				*/
			}
			System.out.print("why are you like this");
			client.close();
			ConnectIn.remove_client();
		}
		catch(Exception e)
		{
			System.out.println("there was an issue, 2");
		}
		
	}
	
}