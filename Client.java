import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Client extends JFrame
{
	
	static boolean WTrue = true;
	static boolean ATrue = true;
	static boolean DTrue = true;
	static boolean STrue = true;
	static boolean HTrue = true;
	
	public Client(PrintWriter pr)
	{
		
		
		final PrintWriter p = pr;
		setTitle("Instructions");
		setResizable(false);
		setLayout(null);
		
		setSize(200, 200);
		setLocation(100,100);
		
		JLabel j = new JLabel("Name: ");
		JTextField t = new JTextField();
		j.setBounds(20, 25, 100, 25);
		j.setFocusable(false);
		
		add(j);
		
		t.setBounds(20, 60, 100, 50);
		add(t);
		
		/*
		JCheckBox check = new JCheckBox("Done: ");
		check.setBounds(250, 450, 50,50);
		
		add(check);
		
		if(check.is)
		*/
		p.println(t.getText());
		
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
			//Lucas Home:    192.168.1.173
			//Vishnu School: 10.30.36.85 
			Socket client = new Socket("192.168.1.173",9999);
						
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			
			Scanner keyboard = new Scanner(System.in);
			
			new Client(out);
			
			String input;
			
			
			while(!(input = keyboard.nextLine()).equals("quit"))
			{
				out.println(input);
				String response;
				
				response = in.readLine();
				
				System.out.println("Message Recieved: " + response);
				System.out.println("outgoing:::");
			}
			client.close();
			
		}
		catch(Exception e)
		{
			System.out.println("There was an issue in the connection");
		}

	}
	
	
	
	

}
