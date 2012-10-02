package shadow.image.bitmaps.data;

import java.util.ArrayList;

import shadow.geometry.SFValuesList;
import shadow.image.bitmaps.SFBasicVoronoiDistances;
import shadow.image.bitmaps.SFBasicVoronoiModels;
import shadow.image.bitmaps.SFBitmapFunction;
import shadow.image.bitmaps.SFVoronoiBitmapFunction;
import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFEnumObject;

public class SFVoronoiBitmapFunctionData extends SFDataAsset<SFBitmapFunction>{

	protected SFLibraryReference<SFValuesList<SFValuenf>> vertices = 
			new SFLibraryReference<SFValuesList<SFValuenf>>();

	private static String[] modelsNames=SFBasicVoronoiModels.names();
	
	private static SFBasicVoronoiModels[] models=SFBasicVoronoiModels.values();
	
	private SFEnumObject<SFBasicVoronoiModels> model=new SFEnumObject<SFBasicVoronoiModels>(models,modelsNames);
	
	private static String[] distancesNames=SFBasicVoronoiDistances.names();
	
	private static SFBasicVoronoiDistances[] distances=SFBasicVoronoiDistances.values();
	
	private SFEnumObject<SFBasicVoronoiDistances> distance=new SFEnumObject<SFBasicVoronoiDistances>(distances,distancesNames);
	
	public SFVoronoiBitmapFunctionData() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		parameters.addObject("model", model);
		parameters.addObject("distance", distance);
		setData(parameters);
	}
	
	@Override
	protected SFBitmapFunction buildResource() {
		SFVoronoiBitmapFunction function=new SFVoronoiBitmapFunction();
		function.setDistance(distance.getElement());
		function.setModel(model.getElement());
		SFValuesList<SFValuenf> vertices=this.vertices.getDataset().getResource();
		ArrayList<SFVertex2f> centers=new ArrayList<SFVertex2f>();
		for (int i = 0; i < vertices.getSize(); i++) {
			SFVertex2f vertex=new SFVertex2f(0,0);
			vertices.getValue(i, vertex);
			centers.add(vertex);
		}
		function.setCenters(centers);
		return function;
	}
	
}
