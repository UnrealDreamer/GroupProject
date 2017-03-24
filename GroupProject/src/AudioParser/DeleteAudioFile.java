package AudioParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteAudioFile
{
	
	public boolean delete(String name) {
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			File file = new File(s + "/res/" + name + ".wav");
			
			//System.out.println(file.getAbsolutePath());	
			try{
				FileInputStream fis = new FileInputStream(file);
				System.out.close();
				if(file.delete()){
					System.out.print("good");
				}
				else {
					System.out.print("nope");
				}
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
		return false;
	}
	
	public static void main(String[] args) {
		DeleteAudioFile st= new DeleteAudioFile();
		st.delete("aaaaaaq");
	}
}