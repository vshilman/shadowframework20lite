package shadow.system.data.tests.js;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import shadow.system.data.java.SFIOExceptionKeeper;
import shadow.system.data.js.SFOutputStreamJs;

public class Test001_SavingJSData01 {

	public static void main(String[] args) {
		
		try {
			FileWriter writer=new FileWriter(new File("examplesData/js/Test001.sf"));
			writer.write("");
			
			SFOutputStreamJs outputStream=new SFOutputStreamJs(writer, new SFIOExceptionKeeper() {
				@Override
				public void launch(IOException exception) {
					System.err.println(exception);
				}
			});
			
			outputStream.writeInt(10);
			outputStream.writeFloat(2.45f);
			outputStream.writeString("ciao");
			outputStream.writeFloat(1.34f);
			
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
