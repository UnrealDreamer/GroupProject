package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class WordLevelEditorFrame implements FocusListener, ActionListener {
	
	private static final JFrame frame = new JFrame("Editor");
	private static ArrayList<JButton> JButtonList = new ArrayList<JButton>();
	JPanel panel, wordPane;
	JTextField word;
	ImageIcon microphone;
	JScrollPane scrollPane;
	JList list;
	DefaultListModel listModel;
	JLabel time,title,record,select;
	JComboBox<String> levelMenu;
	
	public WordLevelEditorFrame() {
		
		panel = new JPanel();
		wordPane = new JPanel();
		wordPane.setPreferredSize(new Dimension(800,900));
		title = new JLabel("Word & Level Editor");
		select = new JLabel("Select a Level");
		word = new JTextField("Enter a new word to add");
		record = new JLabel("Record");
		
		JButtonList.add(new JButton()); //Button 1 is X button at top right
		
		JButtonList.add(new JButton());
		microphone = new ImageIcon("res/microphone.png");
		JButtonList.get(1).setSize(new Dimension(80,80));
		Image img = microphone.getImage().getScaledInstance(JButtonList.get(1).getWidth(),JButtonList.get(1).getWidth(), java.awt.Image.SCALE_SMOOTH);;
		JButtonList.get(1).setIcon(new ImageIcon(img));
		
		JButtonList.add(new JButton("Replay"));				//Button 3 is replay button
		
		JButtonList.add(new JButton("Save Word"));			//Button 4 is save the new word button
		JButtonList.get(3).setBackground(new Color(106, 185, 216));
		
		JButtonList.add(new JButton("Delete Word"));		//Button 5 is delete a word button
		JButtonList.get(4).setBackground(Color.red);
		
		JButtonList.add(new JButton("<html>Create<br />Level</html>"));	//Button 6 is create a new level button
		JButtonList.get(5).setBackground(new Color(106, 185, 216));
		
		JButtonList.add(new JButton("<html>Delete<br />Level</html>")); //Button 7 is delete the current level button
		JButtonList.get(6).setBackground(Color.red);
		
		listModel = new DefaultListModel();
	
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectedIndex(0);
		
		scrollPane = new JScrollPane(list);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(500,500));
	
		ArrayList<Integer> levelNum = new ArrayList<Integer>();
		levelMenu = new JComboBox<String>(new String[]{"Select a Level "});
		levelMenu.setPreferredSize(new Dimension(500,25));//TODO change later.
		
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf")));
			Font rockSalt = Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf"));
			
			title.setFont(rockSalt.deriveFont(35f));
			select.setFont(new Font("Arial", Font.PLAIN,20));
			word.setFont(rockSalt.deriveFont(25f));
			record.setFont(rockSalt.deriveFont(40f));
			JButtonList.get(2).setFont(rockSalt.deriveFont(20f));
			JButtonList.get(3).setFont(rockSalt.deriveFont(25f));
			JButtonList.get(4).setFont(rockSalt.deriveFont(25f));
			JButtonList.get(5).setFont(rockSalt.deriveFont(25f));
			JButtonList.get(6).setFont(rockSalt.deriveFont(25f));
			
		} catch (FontFormatException|IOException e) {}
		
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		panel.add(title,c);
	
		c.gridx = 0;
		c.gridy = 1;
		panel.add(word,c);
		
		c.gridx = 0;
		c.gridy = 2;
		panel.add(record,c);
		
		c.gridx = 1;
		c.gridy = 2;
		panel.add(JButtonList.get(1),c);
		
		c.gridx =1;
		c.gridy =3;
		panel.add(JButtonList.get(2),c);
		
		c.gridx = 0;
		c.gridy = 4;
		panel.add(JButtonList.get(3),c);
		
		c.gridx = 1;
		c.gridy = 4;
		panel.add(JButtonList.get(4),c);
		
		c.gridx = 0;
		c.gridy = 5;
		panel.add(JButtonList.get(5),c);
		
		c.gridx = 2;
		c.gridy = 5;
		panel.add(JButtonList.get(6),c);
		
		wordPane.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		
//		c2.gridy = 0;
//		wordPane.add(select,c2);
		
		c2.gridy = 1;
		wordPane.add(levelMenu,c2);
		
		c2.gridy = 2;
		wordPane.add(scrollPane,c2);
	
		frame.add(panel,BorderLayout.WEST);
		frame.add(wordPane, BorderLayout.EAST);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(screenSize.width +" "+screenSize.height);
		frame.setSize(screenSize.width, screenSize.height - 40);
		wordPane.setBackground(new Color(224,102,102));
		panel.setBackground(new Color(224,102,102));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		WordLevelEditorFrame window = new WordLevelEditorFrame();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {}

	@Override
	public void focusGained(FocusEvent arg0) {}

	@Override
	public void focusLost(FocusEvent arg0) {}

}
