package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class WordLevelEditorFrame {
	
	private static final JFrame frame = new JFrame("Editor");
	private static ArrayList<JButton> JButtonList = new ArrayList<JButton>();
	JPanel panel;
	JTextField word;
	JLabel microphone;
	JScrollBar scroll;
	JLabel time;
	JLabel title;
	JLabel record;
	JTextField levelNum;
	JComboBox<String> levelMenu;
	
	public WordLevelEditorFrame() {
		panel = new JPanel();
		
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf")));
			Font rockSalt = Font.createFont(Font.TRUETYPE_FONT, new File("res/RockSalt.ttf"));
			
			title = new JLabel("Word & Level Editor");
			title.setFont(rockSalt.deriveFont(40f));
			
			word = new JTextField("Enter a new word to add");
			word.setFont(rockSalt.deriveFont(30f));
			
			levelNum = new JTextField("Level #");
			levelNum.setFont(rockSalt.deriveFont(30f));
			
			record = new JLabel("Record");
			record.setFont(rockSalt.deriveFont(40f));
			
			JButtonList.add(new JButton()); 					//Button 1 is X button at top right
			JButtonList.add(new JButton()); 					//Button 2 is recording button
			
			JButtonList.add(new JButton("Replay"));				//Button 3 is replay button
			JButtonList.get(2).setFont(rockSalt.deriveFont(30f));
			JButtonList.get(2).addActionListener((ActionListener) this);
			
			JButtonList.add(new JButton("Save Word"));			//Button 4 is save the new word button
			JButtonList.get(3).setFont(rockSalt.deriveFont(30f));
			JButtonList.get(3).setBackground(new Color(106, 185, 216));
			
			JButtonList.add(new JButton("Delete Word"));		//Button 5 is delete a word button
			JButtonList.get(4).setFont(rockSalt.deriveFont(30f));
			JButtonList.get(4).setBackground(Color.red);
			
			JButtonList.add(new JButton("Create New Level"));	//Button 6 is create a new level button
			JButtonList.get(5).setFont(rockSalt.deriveFont(30f));
			JButtonList.get(5).setBackground(new Color(106, 185, 216));
			
			JButtonList.add(new JButton("Delete Current Level")); //Button 7 is delete the current level button
			JButtonList.get(6).setFont(rockSalt.deriveFont(30f));
			JButtonList.get(6).setBackground(Color.red);
			
			scroll = new JScrollBar();
			
			String[] leveList = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5",
					"Level 6", "Level 7", "Level 8", "Level 9", "Level 10"};
			
			
		} catch (FontFormatException|IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		WordLevelEditorFrame window = new WordLevelEditorFrame();
	}

}
