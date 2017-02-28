package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

public class Frame implements FocusListener
{
	
	
	////////////////////////MOVED TO WELCOMESCREEN//////////////////////////
	
	
//	
//	JFrame frame;
//	JPanel pane;
//	JLabel title;
//	JTextField name,age;
//	JButton start;
//	int first = 1;
//	
//	private class listener implements ActionListener 
//	{
//		
//		@Override
//		public void actionPerformed(ActionEvent arg0) 
//		{
//			
//			if(arg0.getActionCommand().equals("start"))
//			{
//				String userName = name.getText();
//				int userAge = 0;
//				try
//				{
//					userAge = Integer.parseInt(age.getText());
//					
//					if(userName.equals("Enter a Name...")) 
//					{
//						//if username is not entered
//					} else 
//					{
//						System.out.println(userName + " " + userAge);
//						//CREATE NEW USER AND ADVANCE TO GAMEPLAY
//					}
//					
//					
//				} catch (Exception e)
//				{
//					//if age is not entered correctly
//					System.out.println("FAIL");
//					//e.printStackTrace();
//				}
//				
//				
//				
//				
//				
//				
//				
//			}
//			
//		}
//		
//	}
//	
//	public Frame() 
//	{
//		
//		frame = new JFrame("");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		pane = new JPanel();
//		pane.setLayout(new GridBagLayout());
//		GridBagConstraints con = new GridBagConstraints();
//		
//		try 
//		{
//
//			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf")));
//			Font rockSalt = Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf"));
//			
//			title = new JLabel("Welcome to Spelling Game");
//			title.setFont(rockSalt.deriveFont(75f));
//			
//			name = new JTextField("Enter your name...",20);
//			name.setFont(rockSalt.deriveFont(40f));
//			name.addFocusListener(this);
//			
//			age = new JTextField("Enter your age...",20);
//			age.setFont(rockSalt.deriveFont(40f));
//			age.addFocusListener(this);
//		
//			start = new JButton("START");
//			start.setFont(rockSalt.deriveFont(60f));
//			start.setBackground(new Color(106,168,79));
//			start.setActionCommand("start");
//			start.addActionListener(new listener());
//			
//		} catch (FontFormatException|IOException e) 
//		{
//			
//				// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		//places title
//		con.anchor = GridBagConstraints.PAGE_START;
//		con.gridx = 0;
//		con.gridy = 0;
//		con.weighty = 0.1;
//		pane.add(title,con);
//		//places name
//		con.gridx =0;
//		con.gridy = 1;
//		con.weighty =0.05; 
//		con.ipady = 45;  
//		pane.add(name,con);
//		//places age
//		con.gridx =0;
//		con.gridy =2;
//		con.weighty = 0.1;
//		con.ipady = 45;  
//		pane.add(age,con);
//		//places start
//		con.gridx =0;
//		con.gridy =3;
//		start.setPreferredSize(new Dimension(350,50));
//		pane.add(start,con);
//		
//		pane.setBackground(new Color(224,102,102));
//		pane.setPreferredSize(new Dimension(700,700));
//		
//		frame.addFocusListener(this);
//		frame.setContentPane(pane);
//		
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		frame.setSize(screenSize.width, screenSize.height - 40);
//		frame.setResizable(false);
//		frame.setFocusable(true);
//		frame.setVisible(true);
//	}
//
//	@Override
//	public void focusGained(FocusEvent f) 
//	{	
//		if (first > 1){
//			if(f.getSource() == age && age.getText().equals("Enter your age..."))
//			{
//				age.setText("");
//			}else if(f.getSource() == name && name.getText().equals("Enter your name..."))
//			{
//				name.setText("");
//			}
//		} else 
//		{
//			first++;
//		}
//		
//	}
//	
//	@Override
//	public void focusLost(FocusEvent arg0) 
//	{
//		if(age.getText().equals(""))
//		{
//			age.setText("Enter your age...");
//		}else if(name.getText().equals(""))
//		{
//			name.setText("Enter your name...");
//		}
//	}
	
	public static void main(String args[])
	{
		Frame screen = new Frame();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}