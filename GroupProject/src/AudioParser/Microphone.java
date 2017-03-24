package AudioParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
 
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
 
/**
 * This is an example program that demonstrates how to play back an audio file
 * using the SourceDataLine in Java Sound API.
 * @author www.codejava.net
 *
 */
public class Microphone {
 
    // size of the byte buffer used to read/write the audio stream
    private static final int BUFFER_SIZE = 4096;
    static SourceDataLine audioLine;
    static AudioInputStream audioStream;
     static int bytesRead = -1; 
     static int control = 0;
    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);
        try {
            audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
            audioLine = (SourceDataLine) AudioSystem.getLine(info);
 
            audioLine.open(format);
 
            audioLine.start();
            
            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            bytesRead = audioStream.read(bytesBuffer);
            bytesRead = 0; 
            while (bytesRead != -1) {
            	if (control ==0) {
            	
            	audioLine.write(bytesBuffer, 0, bytesRead);
            	bytesRead = audioStream.read(bytesBuffer);
            	}
            	
            }
            try{ 
            	audioLine.drain();
                audioLine.close();
                audioStream.close();
            }
            catch(IOException e) {e.printStackTrace();;}
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }      
        bytesRead =-1;
        control = 0;
    }
    
    public static void endAudio() {
    	control = 1;
    	bytesRead = -1;
    }
    
    public static double lengthReturn(String word) {
    	Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
        File file = new File(s+"/res/" + word +".wav");
    	AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(file);
			AudioFormat format = audioInputStream.getFormat();
	        long audioFileLength = file.length();
	        int frameSize = format.getFrameSize();
	        float frameRate = format.getFrameRate();
	        return (double) (audioFileLength / (frameSize * frameRate));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
    }
    
    public static void fileReceive(String name){
    	Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
        String audioFilePath = s+"/res/" + name +".wav";
        Microphone player = new Microphone();
        player.play(audioFilePath);
    }
     
    public static void main(String[] args) {
		Microphone tse = new Microphone();
    	tse.fileReceive("aaaaaaa");
    }
 
}