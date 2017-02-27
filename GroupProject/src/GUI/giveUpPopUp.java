package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
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
			question.setFont(rockSalt.deriveFont(40f));
			confirm.setFont(rockSalt.deriveFont(20f));
			reject.setFont(rockSalt.deriveFont(20f));
		}
		catch (FontFormatException | IOException e) 
		{
			e.printStackTrace();
		}
		panel.setBackground(new Color(224,102,102));
		frame.setContentPane(panel);
		panel.add(question);
		panel.add(confirm);
		panel.add(reject);
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
