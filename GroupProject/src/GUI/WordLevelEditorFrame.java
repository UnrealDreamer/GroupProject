package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.*;

import AudioParser.AudioPlaylist;
import AudioParser.Microphone;
import BackEnd.BackEnd;

public class WordLevelEditorFrame implements FocusListener, ActionListener {
	
	private static final JFrame frame = new JFrame("Editor");
	private static ArrayList<JButton> JButtonList = new ArrayList<JButton>();
	private static int num = 1;
	private static int seconds = 0;
	private static BackEnd backend;
	JPanel panel, wordPane;
	JLabel levelLabel;
	JTextField wordAdd;
	ImageIcon microphone;
	JScrollPane scrollPane;
	JList list;
	private Color backColor;
	DefaultListModel<String> listModel;
	JLabel time,title,record,select, timer;
	JComboBox<String> levelMenu;
	int recordNum = 0;
	String wordToReplay = "";
	AudioPlaylist au = new AudioPlaylist();
	Thread t = new Thread(new Runnable()
	{
		private volatile boolean running = true;
		public void terminate()
		{
			running = false;
		}
		public void run() {
			try{
				while(running){
					if(recordNum==1)
					{
						seconds++;
						timer.setText("Time: " + seconds + " seconds");
					}else;	
					Thread.sleep(1000);
				}
				}
			catch(InterruptedException e)
			{ 
				//e.printStackTrace();
				terminate();
			}
		}
	});
	Thread t1 = new Thread(new Runnable() {
        private volatile boolean running = true;
    	public void terminate()
		{
			running = false;
		}
		public void run() {
			try{
				while(running)
        		{
        			if(recordNum == 1)
        				au.recordStart(wordAdd.getText());  
        			au.recordFinish();
        		}
			}catch(Exception ex){terminate();}
		}
	});  
	
	public WordLevelEditorFrame(BackEnd backend) {
		this.backend = backend;
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800,900));
		wordPane = new JPanel();
		wordPane.setPreferredSize(new Dimension(800,900));
		title = new JLabel("Word & Level Editor");
		select = new JLabel("Select a Level");
		wordAdd = new JTextField("Enter a new word to add");
		wordAdd.setPreferredSize(new Dimension(600,80));
		wordAdd.addFocusListener(this);
		record = new JLabel("Record");
		
		JButtonList.add(new JButton()); //Button 1 is X button at top right

		JButtonList.add(new JButton());
		microphone = new ImageIcon("res/microphone.png");
		JButtonList.get(1).setSize(new Dimension(80,80));
		Image img = microphone.getImage().getScaledInstance(JButtonList.get(1).getWidth(),JButtonList.get(1).getWidth(), java.awt.Image.SCALE_SMOOTH);
		JButtonList.get(1).setIcon(new ImageIcon(img));
		backColor = JButtonList.get(1).getBackground();
		JButtonList.get(1).addActionListener(this);
		
		JButtonList.add(new JButton("Replay"));
		JButtonList.get(2).addActionListener(this);//Button 3 is replay button
		
		JButtonList.add(new JButton("Save Word"));			//Button 4 is save the new word button
		JButtonList.get(3).setBackground(new Color(106, 185, 216));
		JButtonList.get(3).addActionListener(this);
		JButtonList.get(3).setPreferredSize(new Dimension(250, 95));
		
		JButtonList.add(new JButton("Delete Word"));		//Button 5 is delete a word button
		JButtonList.get(4).setBackground(Color.red);
		JButtonList.get(4).addActionListener(this);
		JButtonList.get(4).setPreferredSize(new Dimension(250, 95));
		
		JButtonList.add(new JButton("<html>Create<br />Level</html>"));	//Button 6 is create a new level button
		JButtonList.get(5).addActionListener(this);
		JButtonList.get(5).setBackground(new Color(106, 185, 216));
		JButtonList.get(5).setPreferredSize(new Dimension(175, 115));
		
		JButtonList.add(new JButton("<html>Delete<br />Level</html>")); //Button 7 is delete the current level button
		JButtonList.get(6).setBackground(Color.red);
		JButtonList.add(new JButton());		
		JButtonList.get(6).addActionListener(this);
		JButtonList.get(6).setPreferredSize(new Dimension(175, 115));
		
		ImageIcon upTri = new ImageIcon("res/upTriangle.png");
		JButtonList.get(7).setSize(new Dimension(20, 20));
		Image up = upTri.getImage().getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
		JButtonList.get(7).setIcon(new ImageIcon(up));
		JButtonList.get(7).addActionListener(this);
		
		JButtonList.add(new JButton());		
		ImageIcon downTri = new ImageIcon("res/downTriangle.png");
		JButtonList.get(8).setSize(new Dimension(20, 20));
		Image down = downTri.getImage().getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
		JButtonList.get(8).setIcon(new ImageIcon(down));
		JButtonList.get(8).addActionListener(this);
		
		levelLabel = new JLabel("Level #: " + num, SwingConstants.CENTER);
		levelLabel.setBackground(Color.WHITE);
		levelLabel.setLayout(new FlowLayout());
		levelLabel.add(JButtonList.get(7));
		levelLabel.add(JButtonList.get(8));	
		levelLabel.setOpaque(true);
		levelLabel.setPreferredSize(new Dimension(175, 115));
		
		timer = new JLabel("Time: " + seconds + " seconds");
		timer.setLayout(new FlowLayout());
		
		listModel = new DefaultListModel<String>();
	
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectedIndex(0);
		
		scrollPane = new JScrollPane(list);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(600,700));
	
		ArrayList<Integer> levelNum = new ArrayList<Integer>();
		levelMenu = new JComboBox<String>(new String[]{"Select a Level "});
		for(int i=1; i<backend.getWordList().size()+1;i++){
			levelMenu.addItem(Integer.toString(i));
		}
		levelMenu.setPreferredSize(new Dimension(600,25));//TODO change later.
		levelMenu.addActionListener(this);
		
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf")));
			Font rockSalt = Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf"));
			
			title.setFont(rockSalt.deriveFont(50f));
			select.setFont(new Font("Arial", Font.PLAIN,20));
			wordAdd.setFont(rockSalt.deriveFont(25f));
			record.setFont(rockSalt.deriveFont(35f));
			levelLabel.setFont(rockSalt.deriveFont(14f));
			JButtonList.get(2).setFont(rockSalt.deriveFont(20f));
			JButtonList.get(3).setFont(rockSalt.deriveFont(25f));
			JButtonList.get(4).setFont(rockSalt.deriveFont(25f));
			JButtonList.get(5).setFont(rockSalt.deriveFont(20f));
			JButtonList.get(6).setFont(rockSalt.deriveFont(20f));		
			levelLabel.setFont(rockSalt.deriveFont(20f));
			timer.setFont(new Font("Consolas", Font.PLAIN, 20));
		} catch (FontFormatException|IOException e) {}
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weighty =0.01;
		panel.add(title,c);
		
		c.gridx = 0;
		c.gridy = 1;
		panel.add(wordAdd,c);
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 2;
		panel.add(record,c2);
		
		c2.gridx = 2;
		c2.gridy = 2;
		panel.add(JButtonList.get(1),c2);
		
		c2.gridx = 0;
		c2.gridy = 3;
		panel.add(timer, c2);
		
		c2.gridx =2;
		c2.gridy =3;
		panel.add(JButtonList.get(2),c2);
		
		c2.gridx = 0;
		c2.gridy = 4;
		c2.weighty = 0.01;
		panel.add(JButtonList.get(3),c2);
		
		c2.gridx = 2;
		c2.gridy = 4;
		panel.add(JButtonList.get(4),c2);
		
		c2.gridx = 0;
		c2.gridy = 5;
		c.gridwidth = GridBagConstraints.CENTER;
		panel.add(JButtonList.get(5),c2);
		
		c2.gridx = 1;
		c2.gridy = 5;
		panel.add(levelLabel,c2);
		
		c2.gridx = 2;
		c2.gridy = 5;
		panel.add(JButtonList.get(6),c2);
		
		wordPane.setLayout(new GridBagLayout());
		GridBagConstraints wordcon = new GridBagConstraints();
		
		wordcon.gridy = 1;
		wordPane.add(levelMenu,wordcon);
		
		wordcon.gridy = 2;
		wordPane.add(scrollPane,wordcon);
	
		frame.add(panel,BorderLayout.WEST);
		frame.add(wordPane, BorderLayout.EAST);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height - 40);
		wordPane.setBackground(new Color(224,102,102));
		panel.setBackground(new Color(224,102,102));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		WordLevelEditorFrame window = new WordLevelEditorFrame(new BackEnd());
		/*for(int c = 0; c < backend.getWordList().size();c++) 
		{
			System.out.println(backend.getWordList().get(c));
		}*/
	}

	private boolean exclude(String s, String[] excludedCharacters)
	{
		boolean stillTrue = true;
		for(int i = 0; i < excludedCharacters.length && stillTrue; i++)
		{
			if(s.contains(excludedCharacters[i]))
				stillTrue = false;
		}
		return stillTrue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{
			if(e.getSource().equals(JButtonList.get(2))){
				if(!wordToReplay.equals("") && wordToReplay.equals(list.getSelectedValue().toString()))
					Microphone.fileReceive(wordToReplay);
				else 
				{
					System.out.println(list.getSelectedValue().toString());
					wordToReplay = list.getSelectedValue().toString();
					Microphone.fileReceive(wordToReplay);
				}
			}//TODO error check this to fix
		}catch(Exception ex){ /*ex.printStackTrace(); */}
		if(e.getSource().equals(JButtonList.get(1))){
			if (recordNum == 0 ) {
				JButtonList.get(1).setBackground(Color.green);
				/*if (wordAdd.getText().equals("") || wordAdd.getText().equals("Enter a new word to add")) {}
				else {*/
				recordNum = 1;
				seconds = 0;
				wordToReplay = wordAdd.getText();
				try{
					t1.start();
					t.start();
					}catch(java.lang.IllegalThreadStateException ex){ /*ex.printStackTrace();*/}
//				}
			}
			else if (recordNum==1) {
				System.out.println(recordNum);
				JButtonList.get(1).setBackground(backColor);
				au.recordFinish();
				recordNum=0;
			}
		}
		if(e.getActionCommand().equals(JButtonList.get(3).getText())){
			if(!(wordAdd.getText().equalsIgnoreCase("Enter a new word to add")))
			{
				File file = new File("res/"+wordAdd.getText() +".wav");
				if(levelMenu.getSelectedIndex() != 0 && wordAdd.getText().indexOf(" ") == -1 && file.exists() 
						&& exclude(wordAdd.getText(), new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
																	,"`","~","!","@", "#", "$", "%", "^",
																	"&", "*", "(", ")", "-","_","+","=","{", "[", "}", "]", 
																	":", ";", "'", "\"", "|", "\\", "<" , "," ,">",".", "?", "/"}))
				{
					addWordPopUp popup = new addWordPopUp(this, wordAdd.getText());
				}
				else if(levelMenu.getSelectedIndex() == 0)
					new SelectLevelErrorPopUp();
				else if(!file.exists())
					new noAudioErrorPopUp();
				else new wordCreationErrorPopUp();
			}
		}
		
		if(e.getActionCommand().equals(JButtonList.get(4).getText())){
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			File f = new File(s+"/res/"+listModel.get(list.getSelectedIndex())+".wav");
			
			deleteWordPopUp popup = new deleteWordPopUp(this, listModel.get(list.getSelectedIndex()));
		}
		
		if(e.getActionCommand().equals(JButtonList.get(5).getText())){
			levelMenu.removeAllItems();
			levelMenu.addItem("Select a Level");
			System.out.println(backend.getWordList().size());
			if(num > backend.getWordList().size()){
				backend.addLevel();
			}else{
				backend.addLevel(num);
			}
			
			for(int i=1; i<backend.getWordList().size()+1;i++)		
				levelMenu.addItem(Integer.toString(i));
			backend.exit();
		}
		
		if(e.getActionCommand().equals(JButtonList.get(6).getText())){
			
			backend.removeLevel(levelMenu.getSelectedIndex());
			levelMenu.removeAllItems();
			levelMenu.addItem("Select a Level");
		
			for(int i=1; i<backend.getWordList().size()+1;i++)		
				levelMenu.addItem(Integer.toString(i));
			backend.exit();
		}
		if(e.getSource().equals(JButtonList.get(7))){
			if(num<backend.getWordList().size()+1)
			{
				num++;
				backend.exit();
			}
			levelLabel.setText("Level #: " + num);
		
		}else if(e.getSource().equals(JButtonList.get(8))){
			if(num>1)
			{
				num--;
				backend.exit();
			}
				levelLabel.setText("Level #: " + num);
		}
		
		if(e.getSource()==levelMenu){
			
			listModel.clear();
			for(int i=1; i<=levelMenu.getItemCount();i++){
				if(levelMenu.getSelectedIndex() == i){
			
					for(int c = 0; c < backend.getWordList().get(i-1).size();c++) 
					{
						listModel.addElement(backend.getWordList().get(levelMenu.getSelectedIndex()-1).get(c).toString());
					}
				}

			}
		}
	}
	public BackEnd getBackEnd()
	{
		return backend;
	}
	public void focusGained(FocusEvent f) {
	
		if(f.getSource() == wordAdd && wordAdd.getText().equals("Enter a new word to add")) 
			wordAdd.setText("");
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if(wordAdd.getText().equals("")) 
			wordAdd.setText("Enter a new word to add");
	}
}