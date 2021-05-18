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
	public static JFrame frame; 
	private static int numPlayers = 0;
	public ArrayList<Platform> platforms;
	
	public ConnectIn()
	{
	
		characters = new ArrayList<Updatable>();
		platforms = new ArrayList<Platform>();
		players = new ArrayList<Player>();
			
		setBounds(100, 100, 600, 600);
		setResizable(false);
		setTitle("Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		Platform platform;
		
		//how to create new platforms for now
		
		platform = new Platform(0,500,600,40,Color.RED);
		platforms.add(platform);
		add(platform);
		
		platform = new Platform(200,450,100,40,Color.RED);
		platforms.add(platform);
		add(platform);
		
		platform = new Platform(300,400,100,40,Color.RED);
		platforms.add(platform);
		add(platform);
		
		platform = new Platform(400,350,100,40,Color.RED);
		platforms.add(platform);
		add(platform);
		
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
				
				
				Player player = new Player(Math.random()*200,Math.random()*200,Color.RED, frame);
				
				Handler clientThread = new Handler(client,numPlayers, player);
				
				characters.add(player);
				players.add(player);
				frame.add(player);
				
				/*
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
				System.out.println("there was an issue");
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
							}
						}
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
				player.addFriction();
				player.resetJumps();
			}
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
	public Handler(Socket s, int id, Player p)
	{
		client = s;
		this.ID = id;
		this. player = p;
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
			player.setName(name);
			final int SPEED = 2;	
			
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
					if(player.numJumpsLeft() > 0)
					{
						//jump speed
						player.jump();
						player.setDy(-3*SPEED);
						player.setFalling(true);
					}
					break;
				case "A":
					player.setDx(-SPEED);
					player.setFacing("a");
					break;
				case "S":
					player.setDy(0);
					break;
				case "D":
					player.setDx(SPEED);
					player.setFacing("d");
					
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
							f.setEDx(r);
							f.setDy(-r);
						}
						
						else //if(player.getFacing().equals("a"))
						{
							f.setEDx(-r);
							f.setDy(-r);
						}
						
						player.makeInvolentaryFriend(null);
						
					}
					break;
				}
					
			}
			System.out.print("why are you like this");
			client.close();
			ConnectIn.remove_client();
		}
		catch(Exception e)
		{
			System.out.println("there was an issue");
		}
		
	}
	
}




/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectIn 
{

	public static void main(String[] args) throws IOException 
	{
		int port = 101;
		ServerSocket server = new ServerSocket(port);
		int numPlayers = 0;
		while(true)//for(;;)
		{
			try
			{
				System.out.println("Server listening...");
				Socket client = server.accept();
				System.out.println("server accepted client");
				
				
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter out = new PrintWriter(client.getOutputStream(),true);
				
				String message;
				
				System.out.print("Friend!");	
				while((message = in.readLine()) != null)
				{
					System.out.println("Message recieved: " + message);
					
					for(int i = 0; i < message.length();i++)
					{
						if(message.equals("New Player"))
						{
							 out.print(numPlayers++);
						}
						switch(message.substring(i, i+1))
						{	
						case "w":
							break;
						case "a":
							break;
						case "s":
							break;
						case "d":
							break;
						}
					}
					
				}
				client.close();
			}
			catch(Exception e)
			{
				System.out.println("there was an issue");
			}
		}

	}

}
*/