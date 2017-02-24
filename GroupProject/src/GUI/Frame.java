package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

public class Frame implements FocusListener
{
	
	JFrame frame;
	JPanel pane;
	JLabel title;
	JTextField name,age;
	JButton start;
	int first = 1;
	
	public Frame() 
	{
		
		frame = new JFrame("");
		pane = new JPanel();
		
		try {

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf")));
			Font rockSalt = Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf"));
			
			title = new JLabel("Welcome to Spelling Game");
			title.setFont(rockSalt.deriveFont(75f));
			
			name = new JTextField("Enter your name...",20);
			name.setFont(rockSalt.deriveFont(40f));
			name.addFocusListener(this);
			
			age = new JTextField("Enter your age...",20);
			age.setFont(rockSalt.deriveFont(40f));
			age.addFocusListener(this);
		
			start = new JButton("START");
			start.setFont(rockSalt.deriveFont(60f));
			start.setBackground(new Color(106,168,79));
		} catch (FontFormatException|IOException e) {
			
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pane.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		
		con.anchor = GridBagConstraints.PAGE_START;
		con.gridx = 0;
		con.gridy = 0;
		con.weighty = 0.1;
		pane.add(title,con);
		
		con.gridx =0;
		con.gridy = 1;
		con.weighty =0.05; 
		con.ipady = 45;  
		pane.add(name,con);
		
		con.gridx =0;
		con.gridy =2;
		con.weighty = 0.1;
		con.ipady = 45;  
		pane.add(age,con);
		
		con.gridx =0;
		con.gridy =3;
		start.setPreferredSize(new Dimension(350,50));
		pane.add(start,con);
		
		pane.setBackground(new Color(224,102,102));
		pane.setPreferredSize(new Dimension(700,700));
		
		frame.addFocusListener(this);
		frame.addFocusListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(pane);
		frame.pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height - 40);
		
		frame.setVisible(true);
		frame.setFocusable(false);
		frame.setFocusable(true);
	}
	
	public static void main(String args[])
	{
		
		Frame screen = new Frame();
	}

	@Override
	public void focusGained(FocusEvent f) 
	{	
		if (first > 1){
			if(f.getSource() == age && age.getText().equals("Enter your age...")){
				age.setText("");
			}else if(f.getSource() == name && name.getText().equals("Enter your name...")){
				name.setText("");
			}
		} else {
			first++;
		}
		
	}
	@Override
	public void focusLost(FocusEvent arg0) 
	{
		if(age.getText().equals("")){
			age.setText("Enter your age...");
		}else if(name.getText().equals("")){
			name.setText("Enter your name...");
		}
	}
	
}