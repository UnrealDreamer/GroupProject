package GUI;

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import AudioParser.Microphone;
import BackEnd.Word;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
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
	Word currentWord;
	int audioDelay  = 0;
	////////////////////////START BUTTON LISTENER////////////////////////

	private class listener implements ActionListener //underlining method
	{
		public void actionPerformed(ActionEvent arg0)
		{
			underline(wordEnter.getText());
			
		}
		public void underline(String output)
		{
			ArrayList<Boolean> a=new ArrayList();
			a.add(true);//add method that returns boolean array
			a.add(false);
			a.add(true);
			a.trimToSize();
			try 
			{
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf")));
				Font rockSalt = Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf"));
				for(int i=0;i<a.size();i++)
				{
					if((boolean)a.get(i)==false)
					{
						wordEnter.setSelectionColor(Color.YELLOW);
						wordEnter.setSelectedTextColor(Color.BLACK);
						wordEnter.setSelectionStart(i);
						wordEnter.setSelectionEnd(i+1);
						
						
						
						/*Map attributes1 = rockSalt.getAttributes();
						Map attributes2 = rockSalt.getAttributes();
						attributes1.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
						attributes1.put(TextAttribute.SIZE, 35f);
						attributes2.put(TextAttribute.SIZE, 35f);
						Font f1 = rockSalt.deriveFont(attributes1);
						Font f2 = rockSalt.deriveFont(attributes2);
						wordEnter.set
						*/
						
						
						/*String output2=wordEnter.getText();
						String wrongString=output2.substring(i,i+1);
						wrongString=wrongString.toUpperCase();
						System.out.println(wrongString); 
						wordEnter.setText(output2.substring(0,i)+wrongString+output2.substring(i+1));	*/
					}
				}
			}
			catch (FontFormatException | IOException e) 
			{
				e.printStackTrace();
			}
		}			
	}


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
		audioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (audioDelay==0) {
					final double len = Microphone.lengthReturn("abound");
					Thread t4 = new Thread(new Runnable(){
											private long startTime = System.currentTimeMillis();
											public void run(){
												while(System.currentTimeMillis() - startTime < ((int)(len*1000))){}
													audioDelay =0;
												}
											}
					);
					t4.start();
					audioDelay++;
					
					
					
					Microphone.fileReceive("abound");
				}
			  }
		});
		
		wordEnter=new RoundJTextField("Type the word... ");
		wordEnter.addFocusListener(this);
		wordEnter.addActionListener(new listener());
		
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
		Microphone.fileReceive("abound");
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