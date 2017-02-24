package AudioParser;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteAudioFile
{
	
	public void delete(String name) {
		try{
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			File file = new File(s + "res/" + name + ".wav");
			
			if(file.delete()){
				System.out.println(file.getName() + " is deleted!");
			}
			else{
				System.out.println("Delete operation is failed.");
			}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
    public static void main(String[] args){
    	
    	DeleteAudioFile stuff = new DeleteAudioFile();
    	stuff.delete("geography");
    }
}