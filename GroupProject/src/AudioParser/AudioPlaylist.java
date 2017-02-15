package AudioParser;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.*;
public class AudioPlaylist {
	private ArrayList<Clip> playlist;
	public AudioPlaylist()
	{
		playlist = new ArrayList<Clip>();
	}
	public void addWav(String path)
	{
		Clip c = null;
		try
		{
			FileInputStream reader = new FileInputStream(path);
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		playlist.add(c);
	}
	public Clip getClip(int ind)
	{	return playlist.get(ind++);	}
}
