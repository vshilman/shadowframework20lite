package testPackage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import codeconverter.factories.test.TestLanguagesObjectsFactory;
import codeconverter.templates.ConversionByTemplateDelegate;
import codeconverter.templates.test.data.TestDataStructureTemplateFactory;

public class ConversionTest {
	public static void main(String[] args) throws IOException {
		ConversionByTemplateDelegate conv=new ConversionByTemplateDelegate(new TestLanguagesObjectsFactory(), new TestDataStructureTemplateFactory());


		BufferedReader r=new BufferedReader(new FileReader(new File("SFParameter.java")));

		String s=r.readLine();
		String finalist="";
		while(s!=null){
			finalist+=s+"\n";
			s=r.readLine();
		}


		String c=conv.convertCode("SFParameter.java", new ByteArrayInputStream(finalist.getBytes()),"h");



		PrintWriter w=new PrintWriter(new File("logMio.txt"));
		w.write(c);
		w.close();

	}
}
