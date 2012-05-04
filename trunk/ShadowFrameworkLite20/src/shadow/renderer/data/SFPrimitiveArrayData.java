package shadow.renderer.data;

import shadow.math.SFValuenf;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.SFStructureData;
import shadow.renderer.data.SFStructureArrayData.SFStructureArrayDataObject;
import shadow.system.SFArrayElementException;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

/**
 * 
 * @author Alessandro Martinelli
 */
public class SFPrimitiveArrayData extends SFDataAsset<SFPrimitiveArray> implements SFDataset{

	private SFPrimitiveArray array;

	public SFPrimitiveArrayData() {
		setData(new SFStructureArrayDataObject());
	}
	
	public SFPrimitiveArray getArray() {
		return array;
	}

	public void setArray(SFPrimitiveArray array) {
		this.array = array;
	}
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFStructureArrayData();
	}
	
	@Override
	protected SFPrimitiveArray buildResource() {
		return array;
	}

	
	public void invalidate() {
	}


	public class SFStructureArrayDataObject implements SFDataObject{
		

		@Override
		public SFStructureArrayDataObject clone() {
			return new SFStructureArrayDataObject();
		}
		
		@Override
		public void readFromStream(SFInputStream stream) {
//			try {
//				String structureName=stream.readString();
//				SFPipelineStructure materialStructureInstance=((SFPipelineStructure)(SFPipeline.getModule(structureName)));
//				SFPrimitiveArray.this.array=SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance); 
//				int n=stream.readInt();
//				SFStructureData data=new SFStructureData(array.getPipelineStructure());
//				for (int i = 0; i < n; i++) {
//					SFValuenf[] value=data.getValues();
//					for (int j = 0; j < value.length; j++) {
//						value[j].set(stream.readFloats(value[j].getSize()));
//					}
//					array.generateElement();
//					array.setElement(i, data);
//				}
//			} catch (SFArrayElementException e) {
//				e.printStackTrace();
//			}
		}
		
		@Override
		public void writeOnStream(SFOutputStream stream) {
//			try {
//				String structureName=array.getPipelineStructure().getName();
//				stream.writeString(structureName);
//				int n=array.getElementsCount();
//				stream.writeInt(n);
//				SFStructureData data=new SFStructureData(array.getPipelineStructure());
//				for (int i = 0; i < n; i++) {
//					array.getElement(i, data);
//					SFValuenf[] value=data.getValues();
//					for (int j = 0; j < value.length; j++) {
//						stream.writeFloats(value[j].get());
//					}
//				}
//			} catch (SFArrayElementException e) {
//				e.printStackTrace();
//			}
		}
	}
}
