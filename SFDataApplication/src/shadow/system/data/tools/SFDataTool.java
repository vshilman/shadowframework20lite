package shadow.system.data.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import shadow.system.data.SFDataAsset;
import shadow.system.data.SFDataObject;
import shadow.system.data.java.SFIOExceptionKeeper;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;

public class SFDataTool {

	public void writeDataObject(SFDataObject inputObject,SFDataObject outputObject) {
		SFIOExceptionKeeper keeper = new SFIOExceptionKeeper() {
			@Override
			public void launch(IOException exception) {
			}
		};
		ByteArrayOutputStream outPut=new ByteArrayOutputStream();
		SFOutputStreamJava output=new SFOutputStreamJava(outPut,keeper);
		inputObject.writeOnStream(output);
		
		ByteArrayInputStream inPut=new ByteArrayInputStream(outPut.toByteArray());
		SFInputStreamJava input=new SFInputStreamJava(inPut, keeper);
		((SFDataObject)outputObject).readFromStream(input);
	}
	
	public SFDataAsset<?> cloneDataAsset(SFDataAsset<?> dataAsset){
		SFDataAsset<?> clone=(SFDataAsset<?>)dataAsset.copyDataset();
		writeDataObject(dataAsset,clone);
		return clone;
	}
}
