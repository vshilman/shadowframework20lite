package shadow.geometry.vertices;

import java.util.ArrayList;
import java.util.StringTokenizer;

import shadow.geometry.SFValuesIterator;
import shadow.geometry.SFValuesList;
import shadow.math.SFValuenf;
import shadow.renderer.data.SFDataAsset;
import shadow.system.SFException;
import shadow.system.data.SFCharsetObjectList;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.SFOutputStream;
import shadow.system.data.objects.SFPrimitiveType;

public class SFParametricValuesList  extends SFDataAsset<SFValuesList<SFValuenf>>{

	private SFParametricData vertices;
	
	public SFParametricValuesList(){
		vertices=new SFParametricData();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		setData(parameters);
	}
	
	
	//TODO: is really all this necessary?
	private class SFParametricVListIterator implements SFValuesIterator<SFValuenf>{
		private int index=0;
		
		@Override
		public void getNext(SFValuenf write) {
			vertices.getValue(index, write);
			index++;
		}
		
		@Override
		public boolean hasNext() {
			return index<vertices.getSize();
		}
	}
	
	//TODO: should be move to its own file
	private class SFParametricVList implements SFValuesList<SFValuenf>{
		@Override
		public void init() {
			//Nothing special
		}
		@Override
		public void destroy() {
			//Nothing special
		}
		@Override
		public int getSize() {
			return vertices.getSize();
		}
		@Override
		public void setValue(int index, SFValuenf read) {
			//TODO : no, setValue is not required on Viewers!!
		}
		@Override
		public int addValue(SFValuenf read) {
			//vertices.addValue(read);
			throw new SFException("SFParametricValuesList can be edited only in the xml way atm");
		}
		@Override
		public void getValue(int index, SFValuenf write) {
			vertices.getValue(index, write);
		}
		
		@Override
		public SFValuesIterator<SFValuenf> getIterator() {
			return new SFParametricVListIterator();
		}
	}

	@Override
	protected SFValuesList<SFValuenf> buildResource() {
		return new SFParametricVList();
	}
	

	private class SFParametricData extends SFPrimitiveType implements
					SFCharsetObjectList {
		
		private int valueSize=0;
		private ArrayList<String> stringsList=new ArrayList<String>();
		private ArrayList<Short> dataList=new ArrayList<Short>();
		private String[] stringsVector=new String[0];
		private short[] dataVector=new short[0];
		
		public void finalize(){
			if(stringsList.size()>0){
				stringsVector=stringsList.toArray(new String[0]);
				stringsList.clear();
				dataVector=new short[dataList.size()];
				for (int i = 0; i < dataVector.length; i++) {
					dataVector[i]=dataList.get(i);
				}
				dataList.clear();
			}
		}
		
		private void getValue(int index,SFValuenf value){
			for (int i = 0; i < valueSize; i++) {
				String vString=getValue(index*valueSize+i);
				try {
					float fValue=new Float(vString);
					value.get()[i]=fValue;
				} catch (NumberFormatException e) {
					value.get()[i]=SFVerticesParameters.getParameters().getValue(vString);
				}
			}
		}
		
		private void addValue(String value){
			int indexof=stringsList.indexOf(value);
			if(indexof==-1){
				indexof=stringsList.size();
				stringsList.add(value);
			}
			dataList.add((short)indexof);
		}
		
		private String getValue(int index){
			return stringsVector[dataVector[index]];
		}
		
		@Override
		public void addCharSetObjects(String value) {
			
			StringTokenizer tokenizer=new StringTokenizer(value,"(,)",false);
			
			int count = 0;
			while (tokenizer.hasMoreTokens()) {
				String token=tokenizer.nextToken();
				addValue(token);
				count++;
			}
			valueSize=count;
		}
		
		@Override
		public String getCharSetObjectString(int index) {
			finalize();
			String data="(";
			for (int i = 0; i < valueSize; i++) {
				if(i!=0){
					data=data+","+getValue(index*valueSize+i);
				}else{
					data=data+getValue(index*valueSize);
				}
			}
			return data+")";
		}
		
		@Override
		public int getSize() {
			return dataVector.length/valueSize;
		}
		
		@Override
		public void readFromStream(SFInputStream stream) {
			this.valueSize=stream.readByte();
			this.stringsVector=new String[stream.readShort()];
			for (int i = 0; i < stringsVector.length; i++) {
				stringsVector[i]=stream.readString();
			}
			short dataVectorLength=stream.readShort();
			this.dataVector=stream.readShorts(dataVectorLength);
		}
		
		@Override
		public void writeOnStream(SFOutputStream stream) {
			finalize();
			stream.writeByte((short)valueSize);
			stream.writeShort((short)stringsVector.length);
			for (int i = 0; i < stringsVector.length; i++) {
				stream.writeString(stringsVector[i]);
			}
			stream.writeShort((short)dataVector.length);
			stream.writeShorts(dataVector);
		}
		
		@Override
		public SFPrimitiveType clone() {
			return new SFParametricData();
		}
		
	}
	
}
