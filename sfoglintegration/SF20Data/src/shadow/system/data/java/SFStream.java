
package shadow.system.data.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import shadow.system.data.SFDataAsset;


/**
 * GLStream is a general use class to write on or read from a stream
 * differenti kind of data. It even keep the methods to write/read
 * GLDatasets headers on the streams
 * 
 * @author Alessandro Martinelli
 */
public class SFStream {

	public InputStreamReader loadFileInputStreamReader(String filename) throws IOException{
		return new InputStreamReader(new FileInputStream(new File(filename)));
	}
	
	public void loadDataset(SFDataAsset<?> dataset,String filename){
		try {
			FileInputStream stream=new FileInputStream(new File(filename));
			SFInputStreamJava inStream=new SFInputStreamJava(stream,new SFIOExceptionKeeper() {
				@Override
				public void launch(IOException exception) {
					exception.printStackTrace();
				}
			});
			//The return of this operation is not used here
				stream.skip(2);
				//GLStream.readShort(stream);
			dataset.readFromStream(inStream);
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeDataset(SFDataAsset<?> dataset,String filename){
		try {
			FileOutputStream stream1=new FileOutputStream(new File(filename));
			SFOutputStreamJava outStream=new SFOutputStreamJava(stream1, new SFIOExceptionKeeper() {
				@Override
				public void launch(IOException exception) {
					exception.printStackTrace();
				}
			});
			dataset.writeOnStream(outStream);
			stream1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
