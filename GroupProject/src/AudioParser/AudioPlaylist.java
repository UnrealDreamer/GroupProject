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
	 * Captures the sound and record into a WAV file
	 */
	void recordStart() {
		try {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
	        String audioFilePath = s+"/res/" + fileName + ".wav";
			wavFile = new File(audioFilePath);
			AudioFormat format = getAudioFormat();
			info = new DataLine.Info(TargetDataLine.class, format);

			// checks if system supports the data line
			if (!AudioSystem.isLineSupported(info)) {
				System.out.println("Line not supported");
				System.exit(0);
			}
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
			line.start();	// start capturing

			System.out.println("Start capturing...");

			ais = new AudioInputStream(line);

			System.out.println("Start recording...");

			// start recording
			AudioSystem.write(ais, fileType, wavFile);

		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Closes the target data line to finish capturing and recording
	 */
	public void recordFinish() {
		line.stop();
		line.close();
		System.out.println("Finished");
	}
	
	public void recordEntry(String name) {
		fileName = name;
		Thread stopper = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(RECORD_TIME);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				recordFinish();
			}
		});
		stopper.start();
		recordStart();
	}

	/**
	 * Entry to run the program
	 */
	public static void main(String[] args) {
		AudioPlaylist recorder = new AudioPlaylist();
		recorder.recordEntry("geography");
	}
}