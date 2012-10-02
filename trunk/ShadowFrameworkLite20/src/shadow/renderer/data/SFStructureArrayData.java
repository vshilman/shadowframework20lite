package shadow.renderer.data;

import shadow.math.SFValuenf;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.system.data.SFDataset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFBinaryVertexArrayList;
import shadow.system.data.objects.SFGenericFixedFloat;
import shadow.system.data.objects.SFString;

public class SFStructureArrayData<T extends SFGenericFixedFloat> extends SFDataAsset<SFStructureArray> implements SFDataset{

	private SFString structure=new SFString();
	private SFStructureArray array;
	private SFBinaryVertexArrayList<T> values;
	
	public SFStructureArrayData(T t){
		values=new SFBinaryVertexArrayList<T>(t);
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		//parameters.addObject("vertexSize", this.vertexSize);
		parameters.addObject("structure", structure);
		parameters.addObject("values", values);
		setData(parameters);
	}
	
	public void setStructure(String structureName){
		this.structure.setString(structureName);
	}
	
	public SFStructureArray getArray() {
		if(array==null){
			String structureName=structure.getString();
			SFPipelineStructure materialStructureInstance=((SFPipelineStructure)(SFPipeline.getModule(structureName)));
			array=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance); 
			int n=values.getSize();
			SFStructureData data=new SFStructureData(materialStructureInstance);
			for (int i = 0; i < n; i++) {
				SFValuenf[] value=data.getValues();
				for (int j = 0; j < value.length; j++) {
					values.getValue(i, j, value[j]);
				}
				array.generateElement();
				array.setElement(i, data);
			}
		}
		return array;
	}

	public void setArray(SFStructureArray array) {
		this.array = array;
		values.getDataObject().clear();
		SFStructureData data=new SFStructureData(array.getPipelineStructure());
		for (int i = 0; i < array.getElementsCount(); i++) {
			array.getElement(i, data);
			SFValuenf[] value=data.getValues();
			values.addValue(value);
		}
	}
	
	@Override
	protected SFStructureArray buildResource() {
		return getArray();
	}

	
	public void invalidate() {
	}

}
