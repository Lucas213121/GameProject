import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
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
	static boolean MTrue = true;
	
	public Client(PrintWriter pr)
	{
		
		
		final PrintWriter p = pr;
		setTitle("Instructions");
		setResizable(false);
		setLayout(null);
		
		setSize(700, 500);
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
				p.println(t.getText());
				j.setFocusable(false);
				t.setFocusable(false);
				send.setFocusable(false);
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
				if(e.getKeyCode() == e.VK_SPACE)
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
				if(e.getKeyCode() == e.VK_SPACE)
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
