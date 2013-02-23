package shadow.renderer.data;

import shadow.geometry.vertices.SFVerticesParameters;
import shadow.renderer.data.SFDataParametersSet.SFDataParameter;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;
import shadow.system.data.objects.SFPrimitiveType;

public class SFDataParametersSet extends SFDataObjectsList<SFDataParameter>{

	private static final long serialVersionUID=0;
	
	public static class SFDataParameter extends SFPrimitiveType implements SFWritableDataObject{
	
		private String name;
		private float value;
		
		@Override
		public SFPrimitiveType clone() {
			return new SFDataParameter();
		}
		
		@Override
		public void readFromStream(SFInputStream stream) {
			name=stream.readString();
			value=stream.readFloat();
		}
		
		@Override
		public void writeOnStream(SFOutputStream stream) {
			stream.writeString(name);
			stream.writeFloat(value);
		}
		
	
		public void setStringValue(String data){
			int indexof=data.indexOf("=");
			name=data.substring(0,indexof);
			this.value=new Float(data.substring(indexof+1));
		}
		
		public String toStringValue(){
			return name+"="+value;
		}
	}

	public SFDataParametersSet() {
		super(new SFDataParameter());
	}
	
	public void apply(){
		SFVerticesParameters.getParameters().clear();
		for (int i = 0; i < size(); i++) {
			SFDataParameter parameter=get(i);
			SFVerticesParameters.getParameters().setParameter(parameter.name, parameter.value);
		}
	}
}
