package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import AudioParser.DeleteAudioFile;

public class wordCreationErrorPopUp implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JLabel errorStatement;
	private JButton confirm;
	private String word;
	
	public wordCreationErrorPopUp()
	{
		frame=new JFrame("Word Creation Error");
		panel=new JPanel();
		errorStatement=new JLabel("   Can not enter more than one word. Try Again.");
		confirm=new JButton("Ok");
		confirm.addActionListener(this);
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		try 
		{
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf")));
			Font rockSalt = Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf"));
			errorStatement.setFont(rockSalt.deriveFont(25f));
			confirm.setFont(rockSalt.deriveFont(15f));
		}
		catch (FontFormatException | IOException e) 
		{
			e.printStackTrace();
		}
		errorStatement.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		confirm.setAlignmentX(JButton.CENTER_ALIGNMENT);
		panel.setBackground(new Color(224,102,102));
		confirm.setBackground(new Color(255,235,215));
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setLayout(new GridBagLayout());	
		GridBagConstraints c = new GridBagConstraints();
		panel.add(errorStatement,c);
		c.gridy=1;
		c.gridx=0;
		panel.add(confirm,c);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		errorStatement.requestFocusInWindow();
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		wordCreationErrorPopUp x=new wordCreationErrorPopUp();
	}
	public void actionPerformed(ActionEvent event) 
	{
		String eventName=event.getActionCommand();
		if(eventName.equals("Ok")){
			System.out.println("");
		}
		
		frame.dispose();
	}
	
}
