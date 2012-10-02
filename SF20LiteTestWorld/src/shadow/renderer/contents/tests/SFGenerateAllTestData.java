package shadow.renderer.contents.tests;

import java.io.File;

public class SFGenerateAllTestData {

	
	public static void main(String[] args) {
		
		String folder = "src/shadow/renderer/contents/tests/";
		String packageName = "shadow.renderer.contents.tests.";
		
		File[] files=(new File(folder)).listFiles();
		
			for (int i = 0; i < files.length; i++) {
				
				String filename=files[i].getName();
				if(filename.endsWith(".java")){
					filename=filename.substring(0, filename.length()-5);

					if(!filename.equalsIgnoreCase("SFAbstractTest")){
						try {
							Class<?> classFile=Class.forName(packageName+filename);
							
							Object object = classFile.newInstance();
							
							if(object instanceof SFAbstractTest){
								SFAbstractTest test = (SFAbstractTest)object;
								
								test.setupAmbient();
								test.buildTestData();
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
	}
}
