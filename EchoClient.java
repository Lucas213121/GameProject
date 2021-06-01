import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EchoClient extends JFrame
{
	
	static boolean WTrue = true;
	static boolean ATrue = true;
	static boolean DTrue = true;
	static boolean STrue = true;
	static boolean HTrue = true;
	static boolean MTrue = true;
	public ArrayList<Platform> platforms = new ArrayList<Platform>();
	private boolean isStageClear;
	private Platform platform;
	
	public JFrame frame = this;
	public EchoClient(BufferedReader i, PrintWriter pr)
	{
		
		final BufferedReader in = i;
		final PrintWriter p = pr;
		setTitle("Instructions");
		setResizable(false);
		setLayout(null);
		
		setSize(600, 600);
		setLocation(100,100);
		
		
		final JLabel j = new JLabel("Name: ");
		final JTextField t = new JTextField();
		j.setBounds(150, 200, 100,100);
		
		add(j);
		
		t.setBounds(250, 200, 100, 100);
		add(t);
		
		final JButton send = new JButton("Send It");
		send.setBounds(400, 200, 100, 100);
		
		send.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				setStage(p, j, t, send);
				repaint();
				
				if(isStageClear == true)
				{
					for( Platform plat : createStage(platforms))
					{
						frame.add(plat);
					}
					repaint();
				}
			}
			
		});
		
		add(send);
		
		
		
		
		this.addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent e)
			{
				
				if(e.getKeyCode() == e.VK_W)
				{
					while(WTrue != false)
					{
						p.println("W");
						WTrue = false;
					}
				}
					
				if(e.getKeyCode() == e.VK_A)
				{
					while(ATrue != false)
					{
						p.println("A");
						ATrue = false;
					}
				}
					
				if(e.getKeyCode() == e.VK_D)
				{
					while(DTrue != false)
					{
						p.println("D");
						DTrue = false;
					}
				}
				
				if(e.getKeyCode() == e.VK_S)
				{
					while(STrue != false)
					{
						p.println("S");
						STrue = false;
					}
				}
				
				if(e.getKeyCode() == e.VK_H)
				{
					while(HTrue != false)
					{
						p.println("H");
						HTrue = false;
					}
				}
				
				if(e.getKeyCode() == e.VK_M)
				{
					while(MTrue != false)
					{
						p.println("M");
						MTrue = false;
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(e.getKeyCode() == e.VK_W)
				{
					while(WTrue != true)
					{
						p.println("w");
						WTrue = true;
					}
				}
				if(e.getKeyCode() == e.VK_A)
				{
					while(ATrue != true)
					{
						p.println("a");
						ATrue = true;
					}
				}
				if(e.getKeyCode() == e.VK_D)
				{
					while(DTrue != true)
					{
						p.println("d");
						DTrue = true;
					}
				}
				if(e.getKeyCode() == e.VK_S)
				{
					while(STrue != true)
					{
						p.println("s");
						STrue = true;
					}
				}
				if(e.getKeyCode() == e.VK_H)
				{
					while(HTrue != true)
					{
						p.println("h");
						HTrue = true;
					}
				}
				
				if(e.getKeyCode() == e.VK_M)
				{
					while(MTrue != true)
					{
						p.println("m");
						MTrue = true;
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
				
			
		});	
		
		
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	
	}
	
	public static void main(String[] args) 
	{
		
		try
		{
			Socket client = new Socket("10.30.39.42",9999);
						
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			
			Scanner keyboard = new Scanner(System.in);
			
			new EchoClient(in, out);
			
			String input;
			
			
			while(!(input = keyboard.nextLine()).equals("quit"))
			{
				out.println(input);
				String response;
				
				
			}
			client.close();
			
		}
		catch(Exception e)
		{
			System.out.println("There was an issue in the connection");
		}

	}
	private void setStage(PrintWriter p, JLabel j, JTextField t, JButton send)
	{
		p.println(t.getText());
		j.setFocusable(false);
		remove(j);
		t.setFocusable(false);
		remove(t);
		send.setFocusable(false);
		remove(send);
		isStageClear = true;
	}
	
	private ArrayList<Platform> createStage(ArrayList<Platform> platforms)
	{
		platform = new Platform(0,500,250,100,Color.BLUE,0);
		platforms.add(platform);
		
		platform = new Platform(350,500,250,100,Color.BLUE,0);
		platforms.add(platform);
		
		platform = new Platform(250,300,100,300,Color.RED,25);
		platforms.add(platform);
		
		
		platform = new Platform(250,275,100,25,Color.BLUE,0);
		platforms.add(platform);
		
		platform = new Platform(50,375,100,25,Color.BLUE,0);
		platforms.add(platform);
		
		platform = new Platform(450,375,100,25,Color.BLUE,0);
		platforms.add(platform);
		
		platform = new Platform(500,50,50,300,Color.BLUE,0);
		platforms.add(platform);
		
		return platforms;
	}
	
	
	
	

}
