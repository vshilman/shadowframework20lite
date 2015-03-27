package shadow.integration.data;

import sfogl.integration.ArrayObject;
import shadow.system.SFContext;
import shadow.system.data.SFDataAsset;
import shadow.system.data.SFDataObjectsList;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFBinaryVertexList;
import shadow.system.data.objects.SFShortArray;

public class ArrayObjectData extends SFDataAsset<ArrayObject>{
	
	public ArrayObjectData() {
		setName("Arrays");
		addObject("vertices", new SFBinaryVertexList<SFFixedFloat161>(new SFFixedFloat161()));
		addObject("normals", new SFBinaryVertexList<SFFixedFloat161>(new SFFixedFloat161()));
		addObject("txCoords", new SFBinaryVertexList<SFFixedFloat161>(new SFFixedFloat161()));
		addObject("indices", new SFDataObjectsList<SFShortArray>(new SFShortArray(new short[0])));
	}
	
	
	//There is no txCoords. You know THAT. 
	@Override
	public ArrayObject createResource(SFContext context,SFNamedParametersObject sfDataObject) {

		SFBinaryVertexList<SFFixedFloat161> vertices=sfDataObject.getDataObject(0);
		SFBinaryVertexList<SFFixedFloat161> normals=sfDataObject.getDataObject(1);
		SFBinaryVertexList<SFFixedFloat161> txCoords=sfDataObject.getDataObject(2);
		SFDataObjectsList<SFShortArray> indices=sfDataObject.getDataObject(3);
		
		//ArrayList<Short> indicesSet=new ArrayList<Short>();
		int size=0;
		for (int i = 0; i < indices.size(); i++) {
			short[] ids=indices.get(i).getShortValues();
			size+=ids.length;
		}
		short[] indicesSet_=new short[size];
		int counter=0;
		for (int i = 0; i < indices.size(); i++) {
			short[] ids=indices.get(i).getShortValues();
			for (int j = 0; j < ids.length; j++) {
				indicesSet_[counter]=ids[j];
				counter++;
			}
		}
		ArrayObject object=new ArrayObject(vertices.getValues(),normals.getValues(),txCoords.getValues(),indicesSet_);
		
		return object;
	}
}
