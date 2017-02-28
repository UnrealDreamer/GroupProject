package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class giveUpPopUp {

	JFrame frame;
	JPanel panel;
	JLabel question;
	JButton confirm;
	JButton reject;
	public giveUpPopUp()
	{
		frame=new JFrame("Give Up");
		panel=new JPanel();
		question=new JLabel(" Are you sure you want to give up?  ");
		confirm=new JButton("Yes, I am sure.");
		reject=new JButton("No, I want to continue");
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		try 
		{
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf")));
			Font rockSalt = Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf"));
			question.setFont(rockSalt.deriveFont(25f));
			confirm.setFont(rockSalt.deriveFont(15f));
			reject.setFont(rockSalt.deriveFont(15f));
		}
		catch (FontFormatException | IOException e) 
		{
			e.printStackTrace();
		}
		question.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		confirm.setAlignmentX(JButton.CENTER_ALIGNMENT);
		reject.setAlignmentX(JButton.CENTER_ALIGNMENT);
		panel.setBackground(new Color(224,102,102));
		confirm.setBackground(new Color(255,235,215));
		reject.setBackground(new Color(255,235,215));
		frame.setContentPane(panel);
		frame.setSize(300,300);
		
		panel.setLayout(new GridBagLayout());	
		GridBagConstraints c = new GridBagConstraints();
	//	c.weighty = 0.01;
		c.gridy=0;
		panel.add(question,c);
		
		
		c.gridy=1;
		c.gridx = GridBagConstraints.RELATIVE;
		panel.add(confirm,c);
		panel.add(reject,c);
		
		
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		question.requestFocusInWindow();
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		giveUpPopUp x=new giveUpPopUp();
	}
}
