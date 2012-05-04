package shadow.geometry.geometries.data;

import shadow.geometry.SFGeometry;
import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.SFSurfaceGeometryTexCoordFunctionuv;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.pipeline.SFPrimitive;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.renderer.data.SFPrimitiveData;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFFloatArray;
import shadow.system.data.objects.SFIntArray;
import shadow.system.data.objects.SFShort;

public class SFQuadsSurfaceGeometryData extends SFDataAsset<SFGeometry>{

	private class SFQuadSurfaceGeometryData extends SFCompositeDataArray{
		
		private SFIntArray positions;
		private SFFloatArray u_splits;
		private SFFloatArray v_splits;
		private SFShort NuNv;
		private SFLibraryReference<SFDataAsset<SFSurfaceFunction>> surfaceFunction;
		private SFLibraryReference<SFDataAsset<SFSurfaceGeometryTexCoordFunctionuv>> texCoordFunction;
		private SFPrimitiveData primitive;
		
		@Override
		public void generateData() {
			positions=new SFIntArray(0);
			u_splits=new SFFloatArray(0);
			v_splits=new SFFloatArray(0);
			NuNv=new SFShort((short)0);
			surfaceFunction=new SFLibraryReference<SFDataAsset<SFSurfaceFunction>>();
			texCoordFunction=new SFLibraryReference<SFDataAsset<SFSurfaceGeometryTexCoordFunctionuv>>();
			primitive=new SFPrimitiveData();
			addDataObject(positions);
			addDataObject(u_splits);
			addDataObject(v_splits);
			addDataObject(NuNv);
			addDataObject(surfaceFunction);
			addDataObject(texCoordFunction);
			addDataObject(primitive);
			//addDataObject(getPrimitiveData());//why should I?
		}
		
		@Override
		public SFQuadSurfaceGeometryData clone() {
			
			return new SFQuadSurfaceGeometryData();
		}
	}
	
	private SFQuadsSurfaceGeometry surfaceGeometry;
	private SFQuadSurfaceGeometryData data=new SFQuadSurfaceGeometryData();
	
	public SFQuadsSurfaceGeometryData() {
		setData(data);
	}
	
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFQuadsSurfaceGeometryData();
	}
	
	public class FunctionListener implements SFDataCenterListener<SFDataAsset<SFSurfaceFunction>>{
		@Override
		public void onDatasetAvailable(String name,SFDataAsset<SFSurfaceFunction> dataset) {
			surfaceGeometry.setSurfaceFunction(dataset.getResource());
		}
	}
	
	public class TexCoordFunctionuvListener implements SFDataCenterListener<SFDataAsset<SFSurfaceGeometryTexCoordFunctionuv>>{
		@Override
		public void onDatasetAvailable(String name,SFDataAsset<SFSurfaceGeometryTexCoordFunctionuv> dataset) {
			surfaceGeometry.setFunctionuv(dataset.getResource());
		}
	}

	@Override
	protected SFQuadsSurfaceGeometry buildResource() {
		
		surfaceGeometry=new SFQuadsSurfaceGeometry();
		data.surfaceFunction.retrieveDataset(new FunctionListener());
		data.texCoordFunction.retrieveDataset(new TexCoordFunctionuvListener());
		surfaceGeometry.setNu(data.NuNv.getByte(1));
		surfaceGeometry.setNv(data.NuNv.getByte(1));
		surfaceGeometry.setPrimitive(data.primitive.getPrimitive());
		if(data.u_splits.getFloatValues().length!=0){
			surfaceGeometry.setU_splits(data.u_splits.getFloatValues());
		}
		if(data.v_splits.getFloatValues().length!=0){
			surfaceGeometry.setU_splits(data.v_splits.getFloatValues());
		}
		return surfaceGeometry;
	}


	public SFIntArray getPositions() {
		return data.positions;
	}

	public SFFloatArray getU_splits() {
		return data.u_splits;
	}

	public SFFloatArray getV_splits() {
		return data.v_splits;
	}

	public void setNu(int nu){
		data.NuNv.setByte(1, nu);
	}
	
	public void setNv(int nv){
		data.NuNv.setByte(0, nv);
	}
	
	public int getNu() {
		return data.NuNv.getByte(1);
	}

	public int getNv() {
		return data.NuNv.getByte(0);
	}
	
	public void setNuNv(int nu,int nv){
		setNu(nu);
		setNv(nv);
	}

	public SFLibraryReference<SFDataAsset<SFSurfaceFunction>> getSurfaceFunction() {
		return data.surfaceFunction;
	}

	public SFLibraryReference<SFDataAsset<SFSurfaceGeometryTexCoordFunctionuv>> getTexCoordFunction() {
		return data.texCoordFunction;
	}
	
	public SFPrimitiveData getPrimitive() {
		return data.primitive;
	}
	
	public void setup(String function, int Nu, int Nv, 
			SFDataAsset<SFSurfaceGeometryTexCoordFunctionuv> textCoordData, SFPrimitive primitive) {
		setNuNv(Nu,Nv);
		getSurfaceFunction().setReference(function);
		getTexCoordFunction().setDataset(textCoordData);
		getPrimitive().setPrimitive(primitive);
	}

	public void setup(SFDataAsset<SFSurfaceFunction> function, int Nu, int Nv, 
			SFDataAsset<SFSurfaceGeometryTexCoordFunctionuv> textCoordData, SFPrimitive primitive) {
		setNuNv(Nu,Nv);
		getSurfaceFunction().setDataset(function);
		getTexCoordFunction().setDataset(textCoordData);
		getPrimitive().setPrimitive(primitive);
	}
}
