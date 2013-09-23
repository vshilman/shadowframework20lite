package testPackage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import tests.tmp.GeneralTests;

import codeconverter.DifferentiationResult;
import codeconverter.factories.test.TestDataStructureTemplateFactory;
import codeconverter.factories.test.TestLanguagesObjectsFactory;
import codeconverter.templates.ConversionByTemplateDelegate;

public class ConversionTest {
    public static void main(String[] args) throws IOException {
        ConversionByTemplateDelegate conv=new ConversionByTemplateDelegate(new TestLanguagesObjectsFactory(), new TestDataStructureTemplateFactory());




        String exOut="h";
        String ifile="SFParameter.java";
        String c=conv.convertCode(ifile,getStream(ifile),exOut);



        PrintWriter w=new PrintWriter(new File("logMio."+exOut));
        w.write(c);
        w.close();


        DifferentiationResult res=GeneralTests.compareFiles("logMio."+exOut, ifile, getStream("logMio."+exOut), getStream(ifile),new StringWriter(), true);

   /*
        Set<CodeModule> s1=res.getDifferentLeft();
        Set<CodeModule> s2=res.getDifferentRight();

        PrintWriter w2=new PrintWriter(new File("logDiff.txt"));
        w2.write("Differences on the left:\n");
        for (Iterator<CodeModule> iterator = s1.iterator(); iterator.hasNext();) {
			CodeModule codeModule =iterator.next();
			w2.write(codeModule.print()+"\n");
		}
        w2.write("Differences on the right:\n");
        for (Iterator<CodeModule> iterator = s2.iterator(); iterator.hasNext();) {
			CodeModule codeModule =iterator.next();
			w2.write(codeModule.print()+"\n");
		}
        w2.close();*/

    }


    private static InputStream getStream(String file) throws IOException{
        BufferedReader r=new BufferedReader(new FileReader(new File(file)));

        String s=r.readLine();
        String finalist="";
        while(s!=null){
            finalist+=s+"\n";
            s=r.readLine();
        }
        return new ByteArrayInputStream(finalist.getBytes());
    }
}
