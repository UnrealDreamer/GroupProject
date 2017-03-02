package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class spellingWindow implements FocusListener
{
	JFrame frame; 
	JPanel panel; 
	JLabel title;
	JLabel levelNum;
	JLabel name;
	JButton audioButton; 
	JButton giveUp; 
	JButton exit; 
	JTextField wordEnter;
	ImageIcon volume;
	ImageIcon red;
	
	public spellingWindow()
	{
		frame=new JFrame("");
		panel=new JPanel();

		title=new JLabel(" Spelling  ");
		name=new JLabel("<Name>"); //Have to enter method that returns name
		levelNum=new JLabel("<Level # : >");//Have to enter method that returns level number
		
		audioButton = new JButton();
		volume = new ImageIcon("res/volume.png");
		audioButton.setSize(new Dimension(125,125));
		Image img = volume.getImage().getScaledInstance(audioButton.getWidth(),audioButton.getWidth(), java.awt.Image.SCALE_SMOOTH);;
		audioButton.setIcon(new ImageIcon(img));
		
		wordEnter=new JTextField("Type the word... ");
		wordEnter.addFocusListener(this);
		
		giveUp=new JButton("Give Up");
		giveUp.setBackground(new Color(255,235,215));
		
		exit = new JButton();
		red = new ImageIcon("res/red.png");
		exit.setSize(new Dimension(70,70));
		Image img2 = red.getImage().getScaledInstance(exit.getWidth(),exit.getWidth(), java.awt.Image.SCALE_SMOOTH);;
		exit.setIcon(new ImageIcon(img2));
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);

		panel.setLayout(new GridBagLayout());	
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx=0;
		c.gridy=0;
		panel.add(giveUp,c);
		
		c.gridx = 2;
		c.gridy = 1;
		panel.add(title,c);
		
		c.fill = GridBagConstraints.RELATIVE;
		c.gridx =2;
		c.gridy = 2;
		panel.add(wordEnter,c);
		
		c.gridx =1;
		c.gridy =2;
		panel.add(audioButton,c);
		
		c.gridx = 2;
		c.gridy = 3;
		panel.add(name,c);
		
		c.gridx =2;
		c.gridy=4;
		panel.add(levelNum,c);
		
		c.gridx =4;
		c.gridy=0;
		panel.add(exit,c);
		panel.setBackground(new Color(250,128,114));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		
		try 
		{
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf")));
			Font rockSalt = Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf"));
			title.setFont(rockSalt.deriveFont(95f));
			wordEnter.setFont(rockSalt.deriveFont(35f));
			name.setFont(rockSalt.deriveFont(20f));
			levelNum.setFont(rockSalt.deriveFont(20f));
			giveUp.setFont(rockSalt.deriveFont(30f));
		}
		catch (FontFormatException | IOException e) 
		{
			e.printStackTrace();
		}
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height - 40);
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		spellingWindow x=new spellingWindow();
	}
	public void focusGained(FocusEvent e) {
		wordEnter.setText("");
	}
	public void focusLost(FocusEvent e) {
		if(wordEnter.getText().trim().equals(""))
			wordEnter.setText("Type a word...");
	}
}