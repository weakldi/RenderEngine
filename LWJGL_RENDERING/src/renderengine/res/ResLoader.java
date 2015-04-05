package renderengine.res;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResLoader {
	public static String loadFile(String file){
		String text = "";
		try {
			
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = in.readLine())!=null){
				text+=line+"\n";
				
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return text;
		
	}
}
