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

public class ConnectIn extends JFrame implements ActionListener
{
	public static ArrayList<Updatable> characters;
	public static JFrame frame; 
	private static int numPlayers = 0;

	public ConnectIn()
	{
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
				
				
				Player player = new Player(Math.random()*200,Math.random()*200,Color.RED);
				
				Handler clientThread = new Handler(client,numPlayers, player);
				
				characters.add(player);
				frame.add(player);
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
					player.setDy(-SPEED);
					break;
				case "A":
					
					player.setDx(-SPEED);
					break;
				case "S":
					player.setDy(SPEED);
					break;
				case "D":
					player.setDx(SPEED);
					break;
				
				case "w":
					player.setDy(0);
					break;
				case "s":
					player.setDy(0);
					break;
				case "a":
					player.setDx(0);
					break;
				case "d":
					player.setDx(0);
					break;
				}
				out.print("||");
					
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