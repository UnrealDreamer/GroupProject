package AudioParser;

import java.util.HashMap;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.*;

/**
 * A sample program is to demonstrate how to record sound in Java
 * author: www.codejava.net
 */
public class AudioPlaylist {
	//HashMap<String, Audio> mapimport javax.sound.sampled.*;


	static final long RECORD_TIME = 4000;
	String fileName;

	File wavFile;

	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

	TargetDataLine line;
	
	AudioInputStream ais;
	
	DataLine.Info info;

	/**
	 * Defines an audio format
	 */
	AudioFormat getAudioFormat() {
		float sampleRate = 16000;
		int sampleSizeInBits = 8;
		int channels = 2;
		boolean signed = true;
		boolean bigEndian = true;
		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
											 channels, signed, bigEndian);
		return format;
	}

	/**
	 * This is the mehod that should be accessed from outside
	 */
	

	
	
	/**
	 * Captures the sound and record into a WAV file
	 */
	public boolean recordStart(String name) {
		fileName = name;
		try {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
	        String audioFilePath = s+"/res/" + fileName + ".wav";
			wavFile = new File(audioFilePath);
			AudioFormat format = getAudioFormat();
			info = new DataLine.Info(TargetDataLine.class, format);

			// checks if system supports the data line
			if (!AudioSystem.isLineSupported(info)) {
				return false;
			}
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
			line.start();
			ais = new AudioInputStream(line);
			AudioSystem.write(ais, fileType, wavFile);
			
			return true;
		} catch (LineUnavailableException ex) {} 
		catch (IOException ioe) {}
		
		return false;
	}

	/**
	 * Closes the target data line to finish capturing and recording
	 */
	public boolean recordFinish() {
		line.stop();
		line.close();
		return true;
	}
}