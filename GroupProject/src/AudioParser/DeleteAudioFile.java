package AudioParser;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteAudioFile
{
	
	public boolean delete(String name) {
		try{
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			File file = new File(s + "/res/" + name + ".wav");
			
			if(file.delete()){
				return true;
			}
    	}catch(Exception e){}
		return false;
	}
	
	public static void main(String[] args) {
		DeleteAudioFile st= new DeleteAudioFile();
		st.delete("geography");
	}
}