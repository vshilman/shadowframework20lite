import java.util.ArrayList;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.renderer.SFNode;
import shadow.renderer.contents.tests.common.CommonData;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.contents.tests.common.CommonTextures;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.java.SFObjectsLibraryDecoder;
import shadow.renderer.data.java.SFXMLDataInterpreter;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.renderer.viewer.SFFrameController;
import shadow.renderer.viewer.SFTextureViewer;
import shadow.renderer.viewer.SFViewer;
import shadow.system.SFArray;
import shadow.system.SFException;
import shadow.system.SFInitiable;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.SFObjectsLibrary.RecordData;

public abstract class SFAbstractTestAO {

	protected static String root = "testsData";
	protected static String rootInput = "testsDataInput";
	protected static boolean storeData = true;
	protected static boolean loadLibrariesAsXml = false;
	protected SFObjectsLibrary library;
	
	
	
	//AO
	
	
		public ArrayList<Triangle> convertMeshGeometryInTriangles(SFMeshGeometry meshGeometry) {
			
			ArrayList <Triangle> triangleMesh = new ArrayList<Triangle>();
			ArrayList <SFVertex3f> values = new ArrayList<SFVertex3f>();
			SFArray <SFValuenf> sfArray;
			
			SFPrimitiveArray array = meshGeometry.getArray(); 
			SFPrimitiveIndices primitiveIndices = array.generateSample();
			
			for (int index=0; index<array.getElementsCount(); index++){ 
				
				array.getElement(index, primitiveIndices);
				
				SFPrimitive primitive = meshGeometry.getPrimitive();
				int [] indicesSizes = primitive.getIndicesSizes();
				int [] indices = primitiveIndices.getPrimitiveIndices(); 
			
				for (int i=0; i<indices.length; i++){
					
					if (i < indicesSizes[0]){
						
						sfArray = array.getPrimitiveData(0);
						SFValuenf value = sfArray.generateSample();
						sfArray.getElement(indices[i], value);
						float [] v = value.get();
						SFVertex3f vertex = new SFVertex3f(v[0], v[1], v[2]);
						values.add(i, vertex);
						
					} else {
						
						sfArray = array.getPrimitiveData(1);
						SFValuenf value = sfArray.generateSample();
						sfArray.getElement(indices[i], value);
						float [] n = value.get();
						SFVertex3f normal = new SFVertex3f(n[0], n[1], n[2]);
						values.add(i, normal);
						
					}
					
				}
				
				if (primitive.getName().equals("Triangle2PN")){
					//0,3,5 + 6,9,11
					Triangle triangle1 = new Triangle(values.get(0), values.get(3), values.get(5), values.get(6), values.get(9), values.get(11));
					triangleMesh.add(triangle1);
					//1,4,3 + 7,10,9
					Triangle triangle2 = new Triangle(values.get(1), values.get(4), values.get(3), values.get(7), values.get(10), values.get(9));
					triangleMesh.add(triangle2);
					//2,5,4 + 8,11,10
					Triangle triangle3 = new Triangle(values.get(2), values.get(5), values.get(4), values.get(8), values.get(11), values.get(10));
					triangleMesh.add(triangle3);
					//3,5,4 + 9,11,10
					Triangle triangle4 = new Triangle(values.get(3), values.get(5), values.get(4), values.get(9), values.get(11), values.get(10));
					triangleMesh.add(triangle4);

				}
				
				if (primitive.getName().equals("Triangle3PN")){
					//2 7 6
					Triangle triangle1 = new Triangle(values.get(2), values.get(7), values.get(6), values.get(12), values.get(17), values.get(16));
					triangleMesh.add(triangle1);
					//6 9 5
					Triangle triangle2 = new Triangle(values.get(6), values.get(9), values.get(5), values.get(16), values.get(19), values.get(15));
					triangleMesh.add(triangle2);
					//5 4 1
					Triangle triangle3 = new Triangle(values.get(5), values.get(4), values.get(1), values.get(15), values.get(14), values.get(11));
					triangleMesh.add(triangle3);
					//5 4 9
					Triangle triangle4 = new Triangle(values.get(5), values.get(4), values.get(9), values.get(15), values.get(14), values.get(19));
					triangleMesh.add(triangle4);
					//6 9 7
					Triangle triangle5 = new Triangle(values.get(6), values.get(9), values.get(7), values.get(16), values.get(19), values.get(17));
					triangleMesh.add(triangle5);
					//7 8 9
					Triangle triangle6 = new Triangle(values.get(7), values.get(8), values.get(9), values.get(17), values.get(18), values.get(19));
					triangleMesh.add(triangle6);
					//9 4 3
					Triangle triangle7 = new Triangle(values.get(9), values.get(4), values.get(3), values.get(19), values.get(14), values.get(13));
					triangleMesh.add(triangle7);
					//9 3 8
					Triangle triangle8 = new Triangle(values.get(9), values.get(3), values.get(8), values.get(19), values.get(13), values.get(18));
					triangleMesh.add(triangle8);
					//8 0 3
					Triangle triangle9 = new Triangle(values.get(8), values.get(0), values.get(3), values.get(18), values.get(10), values.get(13));
					triangleMesh.add(triangle9);
					
				}
				
			}

			return triangleMesh;

		}
		
		
		public float calculateAOValue(SFVertex3f vertex, SFVertex3f normal, ArrayList <Triangle> triangleArray){
			
			
			float totalRays = 100; 
			float numOccluded = 0;
			float aoValue = 0;
			boolean intersection;
			
			
	    	for (int r=0; r<totalRays; r++){
	    		   		
	    		  double a = Math.random()*(Math.PI);
	    		  double b = Math.random()*(2*Math.PI);
	    		  
	    		  SFVertex3f randomDirection = new SFVertex3f((float)(Math.cos(b)*Math.sin(a)), (float)(Math.sin(b)*Math.sin(a)), (float)(Math.cos(a)));
	    		  
	    		  randomDirection.subtract3f(vertex);
	    		  
	    		  SFVertex3f rayDirection = randomDirection;
	    				  
	    		  if (normal.dot3f(rayDirection) < 0){
	    		   			
	    		   	r--;
	    		   			
	    		  } else {
	    			  
	    			  rayDirection.normalize3f();
	    			  intersection = intersectionTest(vertex, rayDirection, triangleArray);
	        		   		
	        		  if (intersection == true){
	        			  
	        		   		numOccluded ++;
	        		   }
	    		   			
	    		 }

	    	}
			
	        aoValue = (totalRays - numOccluded)/totalRays;
	           
	        return aoValue;
			
	}
		
		
		
		
	public boolean intersectionTest(SFVertex3f p, SFVertex3f d, ArrayList<Triangle> triangleArray) {


		boolean intersection = false;
		boolean isFinish = false;
	    
		while (intersection == false && isFinish == false){
		
			for (int k=0; k<triangleArray.size(); k++){
				
				if(intersection == false){
					
					intersection = true;
					
					SFVertex3f v0 = triangleArray.get(k).getVertex1();
					SFVertex3f v1 = triangleArray.get(k).getVertex2();
					SFVertex3f v2 = triangleArray.get(k).getVertex3();
				
					//e1 = v1-v0
					//e2 = v2-v0
					SFVertex3f clonev1 = v1.cloneV();
					SFVertex3f clonev2 = v2.cloneV();
					clonev1.subtract3f(v0);
					clonev2.subtract3f(v0);
					SFVertex3f e1 = clonev1;
					SFVertex3f e2 = clonev2;
					
					SFVertex3f h = d.cross(e2);

					float a = e1.dot3f(h);
					
					if (a>-0.00001 && a<0.00001)
						
						intersection = false;
						
					if(intersection == true){

						float f = 1.0f/a;
						
						//s = p- v0
						SFVertex3f clonep = p.cloneV();
						clonep.subtract3f(v0);
						SFVertex3f s = clonep;
						
						float u = f*(s.dot3f(h));
							
						if(u<0.0 || u >1.0)
							
							intersection = false;
							
						if(intersection == true){
								
							SFVertex3f q = s.cross(e1);
							float v = f*(d.dot3f(q));
								
							if(v<0.0 || u+v>1.0)
								
								intersection = false;
								
							if(intersection == true){
									
								float t = f*(e2.dot3f(q));
								
								if(t<0.00001)
									
									intersection = false;
								
								if(intersection == true){
									
									SFVertex3f intersectionPoint = new SFVertex3f(p.getX()+t*d.getX(), p.getY()+t*d.getY(), p.getZ()+t*d.getZ());
									
									if(intersectionPoint == v0 || intersectionPoint == v1 || intersectionPoint == v2 )
										
										intersection = false;
								}
							}
						}
					}
				}
				
				if (k == triangleArray.size()-1)
					isFinish = true;
			}	 
		}
			
		return intersection;

	}

		
		
		
	public ArrayList<Triangle> tessellation (ArrayList<Triangle> triangleArray){
		
		
		ArrayList <Triangle> tessellationArray = new ArrayList <Triangle>(); 
		int n=2;
		float step=1.0f/n;
		
		
		for (int k=0; k<triangleArray.size(); k++){
			
			for (int i=0; i<n; i++){
				
				float v1 = i*step;
				float v2 = v1+step;
				
				int total=2*(n-i-1)+1;
				
				SFVertex3f [] vertices = new SFVertex3f [total+2];
				SFVertex3f [] normals = new SFVertex3f [total+2];
				
				int index = 0;
				
				// disegno della striscia 
				for (int j=0; j<(n-i); j++){
					
					float u = j*step;
					float w = 1-v1-u;
					
					SFVertex3f newVertex1 = new SFVertex3f (getPositionX(u,v1,w,triangleArray.get(k)), getPositionY(u,v1,w,triangleArray.get(k)), getPositionZ(u,v1,w,triangleArray.get(k)));
					SFVertex3f newNormal1 = new SFVertex3f (getNormalX(u,v1,w,triangleArray.get(k)), getNormalY(u,v1,w,triangleArray.get(k)), getNormalZ(u,v1,w,triangleArray.get(k)));
					newNormal1.normalize3f();
					vertices[index]= newVertex1;
					normals[index]= newNormal1;
					index ++;
					
					w = 1-v2-u;
					
					SFVertex3f newVertex2 = new SFVertex3f (getPositionX(u,v2,w,triangleArray.get(k)), getPositionY(u,v2,w,triangleArray.get(k)), getPositionZ(u,v2,w,triangleArray.get(k)));	
					SFVertex3f newNormal2 = new SFVertex3f (getNormalX(u,v2,w,triangleArray.get(k)), getNormalY(u,v2,w,triangleArray.get(k)), getNormalZ(u,v2,w,triangleArray.get(k))); 
					newNormal2.normalize3f();
					vertices[index]= newVertex2;
					normals[index]= newNormal2;
					index ++;
					
				}
				
				SFVertex3f newVertex3 = new SFVertex3f (getPositionX(1-v1,v1,0,triangleArray.get(k)), getPositionY(1-v1,v1,0,triangleArray.get(k)), getPositionZ(1-v1,v1,0,triangleArray.get(k)));	
				SFVertex3f newNormal3 = new SFVertex3f (getNormalX(1-v1,v1,0,triangleArray.get(k)), getNormalY(1-v1,v1,0,triangleArray.get(k)), getNormalZ(1-v1,v1,0,triangleArray.get(k))); 
				newNormal3.normalize3f();
				vertices[index]= newVertex3;
				normals[index]= newNormal3;
				
				// creo i triangoli
				
				for (int z=0; z<total; z++){
					Triangle t = new Triangle(vertices[z], vertices[z+1], vertices[z+2], normals[z], normals[z+1], normals[z+2]);
					tessellationArray.add(t);
				}
				
			}
			
		}
		
		return tessellationArray;
		
	}
		
		
		public float getPositionX (float u, float v, float w, Triangle t){
			float p = t.getVertex1().getX()*u + t.getVertex2().getX()*v + t.getVertex3().getX()*w;
			return p;
		}
		
		public float getPositionY (float u, float v, float w, Triangle t){
			float p= t.getVertex1().getY()*u + t.getVertex2().getY()*v + t.getVertex3().getY()*w;
			return p;
		}
		
		public float getPositionZ (float u, float v, float w, Triangle t){
			float p = t.getVertex1().getZ()*u + t.getVertex2().getZ()*v + t.getVertex3().getZ()*w;
			return p;
		}
		
		public float getNormalX (float u, float v, float w, Triangle t){
			float n = t.getNormal1().getX()*u + t.getNormal2().getX()*v + t.getNormal3().getX()*w;
			return n;
		}
		
		public float getNormalY (float u, float v, float w, Triangle t){
			float n = t.getNormal1().getY()*u + t.getNormal2().getY()*v + t.getNormal3().getY()*w;
			return n;
		}
		
		public float getNormalZ (float u, float v, float w, Triangle t){
			float n = t.getNormal1().getZ()*u + t.getNormal2().getZ()*v + t.getNormal3().getZ()*w;
			return n;
		}
		
		
		
		public void storeXML(SFDataAsset<?> asset) {

			SFDataUtility.saveXMLFile(root, getFilename(), asset);

		}
		
		
// end AO		



	

	public void execute() {

		setupAmbient();

		if (storeData) {

			buildTestData();
		}

		viewTestData();

	}
	
	
	public static void execute(SFAbstractTestAO test){
		test.execute();
	}

	public <S extends SFDataset> S loadDataset() {
		return loadDataset(getFilename());
	}

	
	@SuppressWarnings("unchecked")
	public <S extends SFDataset> S loadDataset(String testName) throws SFException{
		S s;
		try {
			s = (S) SFDataUtility.loadDataset(root, testName + ".sf");
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFException("Couldn't load an asset from file " + testName + ".sf");
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	public <T extends SFInitiable> T getAlreadyAvailableDatasetResource(String assetName) throws SFException{
		T t;
		try {
			t=(T)((SFDataAsset<?>)SFDataCenter.getDataCenter().getAlreadyAvailableDataset(assetName)).getResource();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFException("The asset " + assetName + " is not an 'already available' asset");
		}
		return t;
	}

	public void setupAmbient() {
	
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		library = objectsLibrary.getLibrary();
		CommonPipeline.prepare();
	}

	
	public void store(SFObjectsLibrary library) {

		String filename = getFilename();

		SFDataUtility.saveDataset(root, filename + ".sf", library);

		SFDataUtility.saveXMLFile(root, filename, library);
	}

	
	public void store(SFDataAsset<?> asset) {

		String filename = getFilename();

		SFDataUtility.saveDataset(root, filename + ".sf", asset);

		SFDataUtility.saveXMLFile(root, filename, asset);
	}
	
	
	public void store(SFDataObject dataObject) {

		String filename = getFilename();

		SFDataUtility.saveDataObject(root, filename + ".sf", dataObject);
	}
	
	
	public void store(SFDataObject dataObject,String elementName) {

		String filename = getFilename();

		SFDataUtility.saveDataObject(root, filename + ".sf", dataObject);

		SFDataUtility.saveXMLFile(root, filename, elementName, dataObject);
	}
		

	
	public abstract void buildTestData();

	public abstract void viewTestData();

	public abstract String getFilename();

	public void loadLibraryAsDataCenter() {
		
		if(!loadLibrariesAsXml){
			SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root, getFilename()
					+ ".sf"));	
		}else{
			SFViewerObjectsLibrary library=new SFViewerObjectsLibrary();
			
			SFObjectsLibraryDecoder decoder=new SFObjectsLibraryDecoder(library.getLibrary());
			
			SFXMLDataInterpreter interpreter=new SFXMLDataInterpreter(decoder);
			interpreter.generateInterpretation(root+"/"+getFilename()+".xml");
			
			SFDataCenter.setDataCenterImplementation(library);
			
		}
	}

	public void copyAssets(String fromLibraryFileName,SFObjectsLibrary toLibrary,String... assetsName){

		SFViewerObjectsLibrary oldLibrary=new SFViewerObjectsLibrary(root, fromLibraryFileName+".sf");
		
		for (int i = 0; i < assetsName.length; i++) {
			String assetName=assetsName[i];
			toLibrary.put(assetName, oldLibrary.getLibrary().retrieveDataset(assetName));
		}
		
	}
	
	public void copyAssets(String fromLibraryFileName,SFObjectsLibrary toLibrary){

		SFViewerObjectsLibrary oldLibrary=new SFViewerObjectsLibrary(root, fromLibraryFileName+".sf");
		
		for (RecordData recordData : oldLibrary.getLibrary()) {
			System.err.println(recordData.getName());
			toLibrary.put(recordData.getName(), oldLibrary.getLibrary().retrieveDataset(recordData.getName()));
		}
		
	}
	

	public void viewNode(String nodeName){
		SFDataCenter.getDataCenter().makeDatasetAvailable(nodeName, new SFDataCenterListener<SFDataAsset<SFNode>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFNode> dataset) {
				SFViewer.generateFrame(dataset.getResource(),SFViewer.getLightStepController(),SFViewer.getRotationController(),SFViewer.getWireframeController(),
						SFViewer.getZoomController());
			}
		});
	}
	

	public void viewNode(String nodeName,final SFFrameController... controllers){
		SFDataCenter.getDataCenter().makeDatasetAvailable(nodeName, new SFDataCenterListener<SFDataAsset<SFNode>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFNode> dataset) {
				SFViewer.generateFrame(dataset.getResource(),controllers);
			}
		});
	}
	
	public String[] getAllNamesInLibraryOfOneType(String... type){
		
		ArrayList<String> names=new ArrayList<String>();
		for (RecordData record : library) {
			for (int i = 0; i < type.length; i++) {
				if(record.getDataset().getClass().getSimpleName().equalsIgnoreCase(type[i])){
					names.add(record.getName());
				}
			}
		}
		return names.toArray(new String[names.size()]);
	}
	

	public void viewTextureSet(String textureSetName,final int startingTextureIndex){
		
		SFDataCenter.getDataCenter().makeDatasetAvailable(textureSetName, new SFDataCenterListener<SFDataAsset<SFRenderedTexturesSet>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFRenderedTexturesSet> dataset) {
				SFRenderedTexturesSet set=dataset.getResource();
				SFTexture texture=new SFTexture(set, startingTextureIndex);
				SFTextureViewer.generateFrame(texture,CommonTextures.generateTextureSelectionController(texture, set.getTextureSize()));
			}
		});	
	}

	public void compileAndLoadXmlFile(String xmlFileName) {
		
		SFObjectsLibraryDecoder decoder=new SFObjectsLibraryDecoder(library);
		
		SFXMLDataInterpreter interpreter=new SFXMLDataInterpreter(decoder);
		interpreter.generateInterpretation(rootInput+"/"+xmlFileName+".xml");
	}
	
	
}	
