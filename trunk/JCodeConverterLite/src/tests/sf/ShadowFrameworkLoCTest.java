package tests.sf;

import java.io.File;
import java.util.List;

import codeconverter.utility.FileStringUtility;

public class ShadowFrameworkLoCTest {

	public static void main(String[] args) {

		File f=new File("../ShadowFramework2.0/src");
		checkDirectory(f);
		
		//4654
		System.err.println("TotalLoC "+totalLoC);
	}

	public static void checkDirectory(File f) {
		File[] files=f.listFiles();
		for (int i=0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				checkDirectory(files[i]);
			} else if(files[i].getName().endsWith(".java")){
				checkFile(files[i]);
			}
		}
	}

	private static int totalLoC=0;
	private static boolean stamp=true;
	
	public static void checkFile(File f){
		
		List<String> list=FileStringUtility.loadTextFile(f.getAbsolutePath());

		int index=0;
		int loc=0;
		while(index<list.size()){
			
			if(list.size()>index && list.get(index).trim().length()>0 && !list.get(index).trim().startsWith("//")){
				if(list.get(index).trim().startsWith("/*")){
					while(list.size()>index && !list.get(index).trim().startsWith("*/")){
						index++;
					}
				}else{
					if(stamp)
						System.err.println("LoC:"+list.get(index));
					loc++;	
				}
			}
			index++;
			
		}
		
		totalLoC+=loc;
	}
}
