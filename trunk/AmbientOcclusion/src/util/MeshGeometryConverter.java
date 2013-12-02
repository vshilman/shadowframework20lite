package util;

import common.CommonPipeline;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.geometry.geometries.data.SFMeshGeometryData;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.SFObjectsLibrary.RecordData;


public class MeshGeometryConverter {

	protected static String root = "testsData";
	
	@SuppressWarnings("unchecked")
	public static void convert(String inputFileName,String element,String outputfileName,String newElement){
		
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		CommonPipeline.prepare();
		
		SFObjectsLibrary library=(SFObjectsLibrary)SFDataUtility.loadDataset(root, inputFileName);
		
		SFDataAsset<SFMeshGeometry> dataset=(SFDataAsset<SFMeshGeometry>)library.retrieveDataset(element);
		
		SFObjectsLibrary outputLibrary=new SFObjectsLibrary();
		
		SFViewerObjectsLibrary oldLibrary=new SFViewerObjectsLibrary(root, inputFileName);
		SFDataCenter.setDataCenterImplementation(oldLibrary);
		for (RecordData recordData : oldLibrary.getLibrary()) {
		
			outputLibrary.put(recordData.getName(), oldLibrary.getLibrary().retrieveDataset(recordData.getName()));
		}
		
		SFMeshGeometryData data=new SFMeshGeometryData();
		SFMeshGeometry geometry=dataset.getResource();
		geometry.init();
		data.setupGeometry(geometry.getArray(), geometry.getPrimitive().getName(), geometry.getFirstElement(),geometry.getLastElement());
		outputLibrary.put(newElement, data);
		
		SFDataUtility.saveDataset(root, outputfileName, outputLibrary);
		
	}
	
	
	public static void main(String args[]){
		
		convert("test0002.sf", "Mushroom", "test0002b.sf", "MushroomMesh");
		convert("test0250.sf", "PlainMushroomModel", "test0250b.sf", "MushroomMesh2");
		convert("test0251.sf", "PlainStrangeGlassModel", "test0251b.sf", "StrangeGlassMesh");
		convert("test0252.sf", "PlainLateralTubeModel", "test0252b.sf", "LateralTubeMesh");
		
	}
}
