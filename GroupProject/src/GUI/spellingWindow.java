package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import AudioParser.Microphone;
import BackEnd.BackEnd;
import BackEnd.Game;
import BackEnd.Word;
public class spellingWindow implements FocusListener
{
	private JFrame frame; 
	private JPanel panel; 
	private JLabel title;
	private JLabel levelNum;
	private JLabel name;
	private JButton audioButton; 
	private JButton giveUp; 
	private JButton exit; 
	private JTextField wordEnter;
	private ImageIcon volume;
	private ImageIcon red;
	private Word currentWord;
	private int audioDelay  = 0;
	private BackEnd back;
	private listener listener;
	private boolean playing = false;
	private boolean canGiveUp = false;
	////////////////////////START BUTTON LISTENER////////////////////////

	private class listener implements ActionListener //underlining method
	{
		
		public void actionPerformed(ActionEvent arg0)
		{
			if(!playing) {
				if(arg0.getActionCommand().equals("wordEnter")) {
					ArrayList<Boolean> wrong = back.checkSpelling(currentWord, wordEnter.getText());
					int correctLetters = 0;
					boolean spelledWrong = false;
					for(boolean b:wrong) {
						if(b==true) {
							underline(wrong,wordEnter.getText());
							canGiveUp = true;
							spelledWrong = true;
							break;
						} else {
							correctLetters++;
						}
					}
					
					if(correctLetters<currentWord.getSpelling().length() && !spelledWrong)
						spelledWrong = true;
					
					if(correctLetters == currentWord.getSpelling().length() && wordEnter.getText().length()==currentWord.getSpelling().length()) {
						wordRightPopUp right = new wordRightPopUp(currentWord.getSpelling());
						canGiveUp = false;
					}
					if (wordEnter.getText().length() < currentWord.getSpelling().length() && spelledWrong) {
						String newString = wordEnter.getText();
						for(int i = 0; i < currentWord.getSpelling().length()-wordEnter.getText().length();i++) {
							newString += " ";
						}
						wordEnter.setText(newString);
						underline(wrong,wordEnter.getText());
						actionPerformed(arg0);
					}
				} else if (arg0.getActionCommand().equals("giveUp") && canGiveUp) {
					giveUpPopUp giveUpPop = new giveUpPopUp();
				} else if (arg0.getActionCommand().equals("exit")) {
					quitPopUp quit = new quitPopUp();
				}
			}
			if(canGiveUp) {
				giveUp.setBackground(Color.green);
			}

		}
		private void underline(ArrayList<Boolean> correct, String input)
		{

			try 
			{
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf")));
				Font rockSalt = Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf"));
				Highlighter h = wordEnter.getHighlighter();

				Highlighter.HighlightPainter yellowPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

				h.removeAllHighlights();

				try {
					for (int i = 0; i < correct.size(); i++){
						if (correct.get(i) == true){
							h.addHighlight(i,i+1,yellowPainter);
						}
					}		
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
			catch (FontFormatException | IOException e) 
			{
				e.printStackTrace();
			}
		}			
	}


	public spellingWindow(BackEnd back)
	{
		this.back = back;

		frame=new JFrame("");
		panel=new JPanel();

		title=new JLabel(" Spelling  ");
		name=new JLabel(back.getUser().getName()); //Have to enter method that returns name
		levelNum=new JLabel("Level # : " + back.getUser().getLastLevel());//Have to enter method that returns level number

		audioButton = new JButton();
		volume = new ImageIcon("res/volume.png");
		audioButton.setSize(new Dimension(125,125));
		Image img = volume.getImage().getScaledInstance(audioButton.getWidth(),audioButton.getWidth(), java.awt.Image.SCALE_SMOOTH);;
		audioButton.setIcon(new ImageIcon(img));
		audioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (audioDelay==0) {
					final double len = Microphone.lengthReturn(currentWord.getSpelling());
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
					Thread t2 = new Thread(new Runnable() {
				        public void run() {
				        	playing = true;
				        	Microphone.fileReceive(currentWord.getSpelling());
				        	playing = false;
				        }
					});  
					t2.start();
				}
			}
		});

		this.listener = new listener();

		wordEnter=new RoundJTextField("Type the word... ");
		wordEnter.addFocusListener(this);
		wordEnter.addActionListener(listener);
		wordEnter.setActionCommand("wordEnter");

		giveUp=new JButton("Give Up");
		giveUp.setBackground(new Color(255,235,215));
		giveUp.addActionListener(listener);
		giveUp.setActionCommand("giveUp");

		exit = new JButton();
		red = new ImageIcon("res/red.png");
		exit.setSize(new Dimension(70,70));
		Image img2 = red.getImage().getScaledInstance(exit.getWidth(),exit.getWidth(), java.awt.Image.SCALE_SMOOTH);;
		exit.setIcon(new ImageIcon(img2));
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.addActionListener(listener);
		exit.setActionCommand("exit");

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

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(panel);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				new quitPopUp();
			}
		});

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
		frame.setResizable(false);
		frame.setVisible(true);
		//first word
		if(back.getUser().getCorrectlySpelt().size()==0) {
			currentWord = back.nextWord(null, false);
		} else {
			currentWord = back.nextWord(back.getUser().getCorrectlySpelt().get(back.getUser().getCorrectlySpelt().size()-1), false);
		}
		Thread t1 = new Thread(new Runnable() {
	        public void run() {
	        	playing = true;
	        	Microphone.fileReceive(currentWord.getSpelling());
	        	playing = false;
	        }
		});  
		t1.start();
	}
	public void focusGained(FocusEvent e) {
		wordEnter.setText("");
	}
	public void focusLost(FocusEvent e) {
		if(wordEnter.getText().trim().equals(""))
			wordEnter.setText("Type a word...");
	}
	
	private void nextWord(boolean spelledRight) {
		if(back.nextWord(currentWord, spelledRight).getLevel()>currentWord.getLevel())
			levelNum=new JLabel("Level # : " + back.getUser().getLastLevel());
		currentWord = back.nextWord(currentWord, spelledRight);
		Thread t1 = new Thread(new Runnable() {
	        public void run() {
	        	playing = true;
	        	Microphone.fileReceive(currentWord.getSpelling());
	        	playing = false;
	        }
		});  
		t1.start();
	}

	private class giveUpPopUp implements ActionListener {

		private JFrame frame;
		private JPanel panel;
		private JLabel question;
		private JButton confirm;
		private JButton reject;
		public giveUpPopUp()
		{
			playing = true;
			
			frame=new JFrame("Give Up");
			panel=new JPanel();
			question=new JLabel("   Are you sure you want to give up?");
			confirm=new JButton("Yes, I am sure.");
			reject=new JButton("No, I want to continue.");
			confirm.addActionListener(this);
			reject.addActionListener(this);
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
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			panel.setLayout(new GridBagLayout());	
			GridBagConstraints c = new GridBagConstraints();
			panel.add(question,c);
			c.gridy=1;
			c.gridx=0;
			panel.add(confirm,c);
			c.gridy=2;
			panel.add(reject,c);
			frame.pack();
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			question.requestFocusInWindow();
			frame.setVisible(true);
			
		}
		public void actionPerformed(ActionEvent event) 
		{
			String eventName=event.getActionCommand();
			if(eventName.equals("Yes, I am sure.")) {
				frame.dispose();
				canGiveUp = false;
				System.out.println("yeet");
				giveUp.setBackground(new Color(255,235,215));
				nextWord(false);
				playing = false;
			}else {
				frame.dispose();
				playing = false;
			}
		}
	}
	private class quitPopUp implements ActionListener{

		private JFrame quitFrame;
		private JPanel panel;
		private JLabel question;
		private JButton confirm;
		private JButton reject;
		private boolean quit = false;
		
		
		
		public quitPopUp()
		{
			playing = true;
			
			quitFrame=new JFrame("Quit");
			panel=new JPanel();
			question=new JLabel("   Are you sure you want to quit game?");
			confirm=new JButton("Yes, I am sure.");
			reject=new JButton("No, I want to continue.");
			confirm.addActionListener(this);
			reject.addActionListener(this);
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
			
			quitFrame.setContentPane(panel);
			quitFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			panel.setLayout(new GridBagLayout());	
			GridBagConstraints c = new GridBagConstraints();
			panel.add(question,c);
			c.gridy=1;
			c.gridx=0;
			panel.add(confirm,c);
			c.gridy=2;
			panel.add(reject,c);
			quitFrame.pack();
			quitFrame.setResizable(false);
			quitFrame.setLocationRelativeTo(null);
			question.requestFocusInWindow();
			quitFrame.setVisible(true);
			
		}
		public void actionPerformed(ActionEvent event) 
		{
			String eventName=event.getActionCommand();
			if(eventName.equals("Yes, I am sure.")) {
				//back.exit();
				quitFrame.dispose();
				frame.dispose();
				new Game();
			} else {
				quitFrame.dispose();
			}
			
		}
	}
	private class wordRightPopUp implements ActionListener {

		private JFrame frame;
		private JPanel panel;
		private JLabel question;
		private JButton confirm;
		public wordRightPopUp(String word)
		{
			playing = true;

			frame=new JFrame("Congratulations!");
			panel=new JPanel();
			question=new JLabel("   Good job! You got the word \""+word+"\" right");
			confirm=new JButton("Move to the next word!");
			confirm.addActionListener(this);
			panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

			try 
			{
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf")));
				Font rockSalt = Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf"));
				question.setFont(rockSalt.deriveFont(25f));
				confirm.setFont(rockSalt.deriveFont(15f));
			}
			catch (FontFormatException | IOException e) 
			{
				e.printStackTrace();
			}
			question.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			confirm.setAlignmentX(JButton.CENTER_ALIGNMENT);
			panel.setBackground(new Color(224,102,102));
			confirm.setBackground(new Color(255,235,215));
			frame.setContentPane(panel);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			panel.setLayout(new GridBagLayout());	
			GridBagConstraints c = new GridBagConstraints();
			panel.add(question,c);
			c.gridy=1;
			c.gridx=0;
			panel.add(confirm,c);

			frame.pack();
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			question.requestFocusInWindow();
			frame.setVisible(true);
		}
		public void actionPerformed(ActionEvent event) 
		{
			String eventName=event.getActionCommand();
			if(eventName.equals("Move to the next word!")){
				frame.dispose();
				playing = false;
				nextWord(true);
			}
		}
	}

}