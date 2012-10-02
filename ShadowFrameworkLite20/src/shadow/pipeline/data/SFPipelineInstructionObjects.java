package shadow.pipeline.data;

import java.util.ArrayList;
import java.util.List;

import shadow.system.data.SFWritableDataObject;
import shadow.system.data.java.SFStringTokenizerInputStream;
import shadow.system.data.java.SFStringWriterStream;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFDataList;
import shadow.system.data.objects.SFString;

public class SFPipelineInstructionObjects extends SFCompositeDataArray implements SFWritableDataObject{

	private SFString command;
	private SFDataList<SFString> parametersList;
	private SFDataList<SFString> dataList;
	
	public SFPipelineInstructionObjects(){
		
	}
	
	public SFPipelineInstructionObjects(String command, String... dataList) {
		super();
		
		this.command.setString(command);
		for (String string : dataList) {
			this.dataList.add(new SFString(string));
		}
	}
	
	public SFPipelineInstructionObjects(String command,
			List<String> parametersList, String... dataList) {
		super();
		this.command.setString(command);
		for (String string : parametersList) {
			this.parametersList.add(new SFString(string));
		}
		for (String string : dataList) {
			this.dataList.add(new SFString(string));
		}
	}

	@Override
	public void generateData() {
		command=new SFString("");
		parametersList=new SFDataList<SFString>(new SFString(""));
		dataList=new SFDataList<SFString>(new SFString(""));
		addDataObject(command);
		addDataObject(parametersList);
		addDataObject(dataList);
	}
	
	@Override
	public SFCompositeDataArray clone() {
		return new SFPipelineInstructionObjects();
	}
	
	public String commandName(){
		return command.getString();
	}
	
	public List<String> getParameters(){
		List<String> parameters=new ArrayList<String>();
		for (SFString string : parametersList) {
			parameters.add(string.getString());
		}
		return parameters;
	}
	
	public String getData(int index){
		return dataList.get(index).getString();
	}
	
	@Override
	public void setStringValue(String value) {
		SFStringTokenizerInputStream stream=new SFStringTokenizerInputStream(value);
		readFromStream(stream);
	}
	
	@Override
	public String toStringValue() {
		SFStringWriterStream stream=new SFStringWriterStream();
		writeOnStream(stream);
		return stream.getString();
	}
}
