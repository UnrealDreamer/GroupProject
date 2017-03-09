package GUI;

import javax.swing.*;

import BackEnd.BackEnd;
import BackEnd.Game;
import BackEnd.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

public class WelcomeScreen implements FocusListener
{
	
	private JFrame frame;
	private JPanel pane;
	private JLabel title;
	private JTextField name,age;
	private JButton start;
	private int first = 1;
	private Game game;
	private String userName;
	
////////////////////////START BUTTON LISTENER////////////////////////
	
	class listener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(arg0.getActionCommand().equals("start")) {
				userName = name.getText();
				int userAge = 0;
				try {
					userAge = Integer.parseInt(age.getText());
					if(userAge<1){
						throw new Exception();
					}
					if(userName.equals("Enter your name...")) {
						JOptionPane.showMessageDialog(frame,
							    "There is no input for a name.",
							    "Improper Input",
							    JOptionPane.WARNING_MESSAGE);
						//if username is not entered
					} else {
						System.out.println(userName + " " + userAge);
						//CREATE NEW USER AND ADVANCE TO GAMEPLAY
						User newUser = new User(userName, userAge);
						frame.setVisible(false);
						game.moveToSpelling(newUser);
					}
					
				} catch (Exception e) {
					//if age is not entered correctly
					JOptionPane.showMessageDialog(frame,
						    "Age is not an positive whole number.",
						    "Improper Input",
						    JOptionPane.WARNING_MESSAGE);
					if(userName.equals("Enter your name...")) {
						System.out.println("FAIL NAME");
						JOptionPane.showMessageDialog(frame,
							    "There is no input for a name.",
							    "Improper Input",
							    JOptionPane.WARNING_MESSAGE);
						//if username is not entered
					}
					//e.printStackTrace();
				}
				
			}
			
		}
		public void setName(String name)
		{
			userName=name;
		}
		public String getName()
		{
			return userName;
		}
		
	}
	
////////////////////////WELCOMESCREEN CLASS////////////////////////
	
	public WelcomeScreen(Game g) {
		game = g;
		frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		
		try {

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf")));
			Font rockSalt = Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf"));
			
			title = new JLabel("Welcome to Spelling Game");
			title.setFont(rockSalt.deriveFont(75f));
			
			name = new RoundJTextField("Enter your name...",20);
			name.setFont(rockSalt.deriveFont(40f));
			name.setBackground(new Color(234,152,154));
			name.addFocusListener(this);
			
			age = new RoundJTextField("Enter your age...",20);
			age.setFont(rockSalt.deriveFont(40f));
			age.setBackground(new Color(234,152,154));
			age.addFocusListener(this);
		
			start = new JButton("START");
			start.setFont(rockSalt.deriveFont(50f));
			
			start.setActionCommand("start");
			start.setBorder(new RoundedBorder(70));
			start.setBackground(new Color(106,168,79));
			start.addActionListener(new listener());
			
			
		} catch (FontFormatException|IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//places title
		con.anchor = GridBagConstraints.PAGE_START;
		con.gridx = 0;
		con.gridy = 0;
		con.weighty = 0.1;
		pane.add(title,con);
		//places name
		con.gridx =0;
		con.gridy = 1;
		con.weighty =0.05; 
		con.ipady = 45;  
		pane.add(name,con);
		//places age
		con.gridx =0;
		con.gridy =2;
		con.weighty = 0.1;
		con.ipady = 45;  
		pane.add(age,con);
		//places start
		con.gridx =0;
		con.gridy =3;
		start.setPreferredSize(new Dimension(350,50));
		pane.add(start,con);
		
		pane.setBackground(new Color(224,102,102));
		pane.setPreferredSize(new Dimension(700,700));
		
		frame.addFocusListener(this);
		frame.setContentPane(pane);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height - 40);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.setVisible(true);
	}
	
	//when fields is selected displays input or blank
	@Override
	public void focusGained(FocusEvent f) {	
		if (first > 1){
			if(f.getSource() == age && age.getText().equals("Enter your age...")) {
				age.setText("");
			}else if(f.getSource() == name && name.getText().equals("Enter your name...")) {
				name.setText("");
			}
		} else {
			first++;
		}
		
	}
	
	//when fields arent selected or typed into displays a prompt
	@Override
	public void focusLost(FocusEvent arg0) {
		if(age.getText().equals("")) {
			age.setText("Enter your age...");
		}else if(name.getText().equals("")) {
			name.setText("Enter your name...");
		}
	}
	
	
}