package renderengine.core;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import org.lwjgl.opengl.GLContext;

public class NativeLoader {
	public static void loadNatives(){
		String os = System.getProperty("os.name");
		String arch = System.getProperty("os.arch");
		System.out.println(os + " " + arch);
		if(os.startsWith("Windows")){
			if(arch.equalsIgnoreCase("amd64")){
				
			}else{
				
			}
		}else if(os.startsWith("Linux")) {
			
		}
	}
	
	private class LibFileFilter extends FileFilter{
		public LibFileFilter(String libArch) {
			
		}
		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}


