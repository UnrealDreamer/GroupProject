package GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
		frame=new JFrame("Spelling Screen");
		panel=new JPanel();

		title=new JLabel(" Spelling  ");

		name=new JLabel("<Name>"); //Have to enter method that returns name
		levelNum=new JLabel("<Level # : >");//Have to enter method that returns level number
		volume = new ImageIcon("src/Pictures/Volume2.png");
		red = new ImageIcon("src/Pictures/red.png");
		wordEnter=new JTextField();
		wordEnter.addFocusListener(this);
		wordEnter.setText(" Type the word... ");
		giveUp=new JButton("Give Up");

		giveUp.setBackground(new Color(255,235,215));
		audioButton=new JButton(volume);
		exit=new JButton(red);
		exit.setBackground(null);
		exit.setBorder(null);
		audioButton.setBackground(new Color(164,228,180));

		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		panel.setLayout(new GridBagLayout());	
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0.01;

		c.gridx=0;
		panel.add(giveUp,c);
		panel.add(audioButton,c);
		c.weightx = 0.01;
		c.gridx=1;
		c.gridy = GridBagConstraints.RELATIVE;
		panel.add(title,c);
		panel.add(wordEnter,c);
		panel.add(name,c);
		panel.add(levelNum,c);		
		c.gridx=2;
		c.gridy = GridBagConstraints.RELATIVE;
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


		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		name.requestFocusInWindow();
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