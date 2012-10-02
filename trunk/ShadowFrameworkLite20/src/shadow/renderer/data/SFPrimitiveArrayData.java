package shadow.renderer.data;

import shadow.geometry.SFValuesList;
import shadow.geometry.vertices.SFValueListData;
import shadow.geometry.vertices.SFVertexListData16;
import shadow.math.SFValuenf;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.system.SFArray;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFShortArray;
import shadow.system.data.objects.SFString;

/**
 * 
 * @author Alessandro Martinelli
 */
public class SFPrimitiveArrayData extends SFDataAsset<SFPrimitiveArray> implements SFDataset{

	private SFString primitive=new SFString();
	private SFPrimitiveArray array;
	private SFLibraryReferenceList<SFValuesList<SFValuenf>> primitiveData=
		new SFLibraryReferenceList<SFValuesList<SFValuenf>>(new SFLibraryReference<SFValuesList<SFValuenf>>());
	private SFDataObjectsList<SFShortArray> primitives=new SFDataObjectsList<SFShortArray>(new SFShortArray(new short[0]));

	//sample is used only on setArray.
	private SFValueListData<?> sample=new SFVertexListData16();
	
	public SFPrimitiveArrayData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("primitive", primitive);
		parameters.addObject("primitiveData", primitiveData);
		parameters.addObject("primitives", primitives);
		setData(parameters);
	}
	
	public void setPrimitive(String primitive){
		this.primitive.setString(primitive);
	}

	public void setArray(SFPrimitiveArray array) {
		this.array = array;
		
		for (int gridIndex = 0; gridIndex < array.getPrimitive().getGridsCount(); gridIndex++) {
			SFArray<SFValuenf> data=array.getPrimitiveData(gridIndex);
			
			SFValueListData<?> dataList=(SFValueListData<?>)sample.generateNewDatasetInstance();
			
			SFValuenf value=data.generateSample();
			for (int i = 0; i < data.getElementsCount(); i++) {
				data.getElement(i, value);
				dataList.addVertices(value.get());
			}
			
			SFLibraryReference<SFValuesList<SFValuenf>> reference=new SFLibraryReference<SFValuesList<SFValuenf>>();
			reference.setDataset(dataList);
			this.primitiveData.add(reference);
		}
		
		SFPrimitiveIndices indices=array.generateSample();
		for (int i = 0; i < array.getElementsCount(); i++) {
			array.getElement(i, indices);
			short[] primitiveIndices=new short[indices.getPrimitiveIndices().length];
			for (int j = 0; j < primitiveIndices.length; j++) {
				primitiveIndices[j]=(short)(indices.getPrimitiveIndices()[j]);
			}
			primitives.add(new SFShortArray(primitiveIndices));
		}
	}
	
	@Override
	protected SFPrimitiveArray buildResource() {

		SFPrimitive primitive=SFPipeline.getPrimitive(this.primitive.getString());
		
		array = SFPipeline.getSfPipelineMemory().generatePrimitiveArray(primitive);

		for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
			
			final SFArray<SFValuenf> values = array.getPrimitiveData(gridIndex);
			
			primitiveData.get(gridIndex).retrieveDataset(new SFDataCenterListener<SFDataAsset<SFValuesList<SFValuenf>>>() {
				@Override
				public void onDatasetAvailable(String name,
						SFDataAsset<SFValuesList<SFValuenf>> dataset) {
					SFValuesList<SFValuenf> list=dataset.getResource();
					values.generateElements(list.getSize());
					SFValuenf sample = values.generateSample();
					for (int i = 0; i < list.getSize(); i++) {
						list.getValue(i, sample);
						values.setElement(i, sample);
					}
				}
			});
		}
		
		SFPrimitiveIndices indices = array.generateSample();
		array.generateElements(primitives.size());
		for (int i = 0; i < primitives.size(); i++) {
			short[] primitiveIndices=primitives.get(i).getShortValues();
			for (int j = 0; j < primitiveIndices.length; j++) {
				indices.getPrimitiveIndices()[j]=primitiveIndices[j];
			}
			array.setElement(i, indices);
		}
		
		return array;
	}

	
	public void invalidate() {
	}

}
