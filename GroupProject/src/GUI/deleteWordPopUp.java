package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

public class deleteWordPopUp implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JLabel question;
	private JButton confirm;
	private JButton reject;
	private String word;
	private WordLevelEditorFrame editor;
	private DeleteAudioFile kepOut = new DeleteAudioFile();
	public int choice = 0;
	
	public deleteWordPopUp(WordLevelEditorFrame editor, String save)
	{
		word = save;
		frame=new JFrame("Delete Word");
		panel=new JPanel();
		this.editor = editor;
		question=new JLabel("   Are you sure you want to delete \""+word+"\"?");
		confirm=new JButton("Yes, I am sure.");
		reject=new JButton("No, I like the word.");
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
//		frame.setSize(new Dimension(700, 200));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		question.requestFocusInWindow();
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		deleteWordPopUp x=new deleteWordPopUp(new WordLevelEditorFrame(), "aaaaaaa");
	}
	public void actionPerformed(ActionEvent event) 
	{
		String eventName=event.getActionCommand();
		if(eventName.equals("Yes, I am sure.")){
			editor.listModel.remove(editor.listModel.indexOf(word));
			kepOut.delete(word);
		}
		else;
		frame.dispose();
	}
}
