package Test;
import AudioParser.AudioPlaylist;

public class AudioTester {
	public static void main(String[] args)
	{
		AudioPlaylist player = new AudioPlaylist();
		player.addWav("res/sound.wav");
	}
}
