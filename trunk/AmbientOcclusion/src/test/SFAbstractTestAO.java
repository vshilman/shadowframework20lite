package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.math.SFValue1f;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.java.SFObjectsLibraryDecoder;
import shadow.renderer.data.java.SFXMLDataInterpreter;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.system.SFArray;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFObjectsLibrary;
import util.CheckContainer;
import util.Triangle;
import common.CommonData;
import common.CommonPipeline;

public abstract class SFAbstractTestAO{

	protected static String root = "testsData";
	protected static String rootInput = "testsDataInput";
	protected static boolean storeData = true;
	protected static boolean loadLibrariesAsXml = false;
	protected SFObjectsLibrary library;
	private static final float eps = 0.025f;
	private static final double g = 0.035f;
	
	
	
	public void execute(){
		setupAmbient();
		if (storeData){
			buildTestData();
		}
		viewTestData();
	}
	
	
	public static void execute(SFAbstractTestAO test){
		test.execute();
	}
	
	
	public void setupAmbient(){
		SFViewerObjectsLibrary objectsLibrary = CommonData.generateDataSettings();
		library = objectsLibrary.getLibrary();
	    CommonPipeline.prepare();
	}
	
	
	public abstract void buildTestData();

	public abstract void viewTestData();

	public abstract String getFilename();

	public void loadLibraryAsDataCenter() {
		if(!loadLibrariesAsXml){
			SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root, getFilename()+ ".sf"));	
		}else{
			SFViewerObjectsLibrary library=new SFViewerObjectsLibrary();
			SFObjectsLibraryDecoder decoder=new SFObjectsLibraryDecoder(library.getLibrary());
			SFXMLDataInterpreter interpreter=new SFXMLDataInterpreter(decoder);
			interpreter.generateInterpretation(root+"/"+getFilename()+".xml");
			SFDataCenter.setDataCenterImplementation(library);
		}
	}
	
	
	
	
	
	
	//Ray Tracer
	
	public ArrayList<Triangle> convertSFMeshGeometryInTriangles(SFMeshGeometry meshGeometry) {
			
		ArrayList <Triangle> triangleMesh = new ArrayList<Triangle>();
		ArrayList <SFVertex3f> values = new ArrayList<SFVertex3f>();
		SFArray <SFValuenf> sfArray;
		SFPrimitiveArray array = meshGeometry.getArray(); 
		SFPrimitive primitive = meshGeometry.getPrimitive();
		SFPrimitiveIndices primitiveIndices = array.generateSample();
			
		for(int index=0; index<array.getElementsCount(); index++){ 
				
			array.getElement(index, primitiveIndices);
			int [] indicesSizes = primitive.getIndicesSizes();
			int [] indices = primitiveIndices.getPrimitiveIndices(); 
	
			for(int i=0; i<indices.length; i++){
					
				if(i < indicesSizes[0]){
					sfArray = array.getPrimitiveData(0);
					SFValuenf value = sfArray.generateSample();
					sfArray.getElement(indices[i], value);
					float [] v = value.get();
					SFVertex3f vertex = new SFVertex3f(v[0], v[1], v[2]);
					values.add(i, vertex);
				}else{
					sfArray = array.getPrimitiveData(1);
					SFValuenf value = sfArray.generateSample();
					sfArray.getElement(indices[i], value);
					float [] n = value.get();
					SFVertex3f normal = new SFVertex3f(n[0], n[1], n[2]);
					values.add(i, normal);
				}
			}
				
			if(primitive.getName().equals("Triangle2PN")){
				//ADF(0 3 5) 
				Triangle triangle1 = new Triangle(values.get(0), values.get(3), values.get(5), values.get(6), values.get(9), values.get(11));
				triangleMesh.add(triangle1);
				//BDE(1 3 4)  
				Triangle triangle2 = new Triangle(values.get(1), values.get(3), values.get(4), values.get(7), values.get(9), values.get(10));
				triangleMesh.add(triangle2);
				//CEF(2 4 5) 
				Triangle triangle3 = new Triangle(values.get(2), values.get(4), values.get(5), values.get(8), values.get(10), values.get(11));
				triangleMesh.add(triangle3);
				//DEF(3 4 5) 
				Triangle triangle4 = new Triangle(values.get(3), values.get(4), values.get(5), values.get(9), values.get(10), values.get(11));
				triangleMesh.add(triangle4);
			}
				
			if(primitive.getName().equals("Triangle3PN")){
				//C-CA-CB(2 7 6)
				Triangle triangle1 = new Triangle(values.get(2), values.get(7), values.get(6), values.get(12), values.get(17), values.get(16));
				triangleMesh.add(triangle1);
				//CB-ABC-BC(6 9 5)
				Triangle triangle2 = new Triangle(values.get(6), values.get(9), values.get(5), values.get(16), values.get(19), values.get(15));
				triangleMesh.add(triangle2);
				//BC-BA-B(5 4 1)
				Triangle triangle3 = new Triangle(values.get(5), values.get(4), values.get(1), values.get(15), values.get(14), values.get(11));
				triangleMesh.add(triangle3);
				//BC-BA-ABC(5 4 9)
				Triangle triangle4 = new Triangle(values.get(5), values.get(4), values.get(9), values.get(15), values.get(14), values.get(19));
				triangleMesh.add(triangle4);
				//CB-ACB-CA(6 9 7)
				Triangle triangle5 = new Triangle(values.get(6), values.get(9), values.get(7), values.get(16), values.get(19), values.get(17));
				triangleMesh.add(triangle5);
				//CA-AC-ABC(7 8 9)
				Triangle triangle6 = new Triangle(values.get(7), values.get(8), values.get(9), values.get(17), values.get(18), values.get(19));
				triangleMesh.add(triangle6);
				//ABC-BA-AB(9 4 3)
				Triangle triangle7 = new Triangle(values.get(9), values.get(4), values.get(3), values.get(19), values.get(14), values.get(13));
				triangleMesh.add(triangle7);
				//ABC-AB-AC(9 3 8)
				Triangle triangle8 = new Triangle(values.get(9), values.get(3), values.get(8), values.get(19), values.get(13), values.get(18));
				triangleMesh.add(triangle8);
				//AC-A-AB(8 0 3)
				Triangle triangle9 = new Triangle(values.get(8), values.get(0), values.get(3), values.get(18), values.get(10), values.get(13));
				triangleMesh.add(triangle9);		
			}
		}
		return triangleMesh;
	}
	
	
	
	
	public void calculateAOValues(ArrayList<Triangle> triangleArray, float totalRays){
		
		ArrayList<Triangle> triangleCurrent = new ArrayList<Triangle>(); 
		CheckContainer checkContainer = new CheckContainer();
		
		for(int k=0; k<triangleArray.size(); k++){
			
			triangleCurrent = (ArrayList<Triangle>)triangleArray.clone();
			triangleCurrent.remove(k);
			
			// controllo il primo vertice del triangolo
			if(!checkContainer.isCalculate(triangleArray.get(k).getVertex1())){
				triangleArray.get(k).setAO1(calculateAO(triangleArray.get(k).getVertex1(), triangleArray.get(k).getNormal1(), triangleCurrent, totalRays));
				checkContainer.storeCheck(triangleArray.get(k).getVertex1(), triangleArray.get(k).getAO1());
			}else{
				triangleArray.get(k).setAO1(checkContainer.retrieveAO(triangleArray.get(k).getVertex1()));
			}
			
			// controllo il secondo vertice del triangolo
			if(!checkContainer.isCalculate(triangleArray.get(k).getVertex2())){
				triangleArray.get(k).setAO2(calculateAO(triangleArray.get(k).getVertex2(), triangleArray.get(k).getNormal2(), triangleCurrent, totalRays));
				checkContainer.storeCheck(triangleArray.get(k).getVertex2(), triangleArray.get(k).getAO2());
			}else{
				triangleArray.get(k).setAO2(checkContainer.retrieveAO(triangleArray.get(k).getVertex2()));
			}
			
			// controllo terzo vertice del triangolo
			if(!checkContainer.isCalculate(triangleArray.get(k).getVertex3())){	
				triangleArray.get(k).setAO3(calculateAO(triangleArray.get(k).getVertex3(), triangleArray.get(k).getNormal3(), triangleCurrent, totalRays));
				checkContainer.storeCheck(triangleArray.get(k).getVertex3(), triangleArray.get(k).getAO3());
			}else{
				triangleArray.get(k).setAO3(checkContainer.retrieveAO(triangleArray.get(k).getVertex3()));
			}
		
		}
		
	}
	
	
	
		
	public float calculateAO(SFVertex3f vertex, SFVertex3f normal, ArrayList <Triangle> triangleArray, float totalRays){
				
		float numOcc = 0;
		float aoValue = 0;
		boolean intersection;

	    for(int r=0; r<totalRays; r++){
	    		   		
	    	SFVertex3f rayDirection = generateRayDirection();
	    		  
	    	if(normal.dot3f(rayDirection) < 0.01f){
	    		r--;
	    	}else{
	    		intersection = intersectionTest(vertex, rayDirection, triangleArray);
	        		   		
	        	if(intersection==true)
	        		numOcc ++;
	        	
	    	}
	    }
		aoValue = (totalRays-numOcc)/totalRays; 
	    return aoValue;
	}
	
	
	
	public SFVertex3f generateRayDirection(){
		
		double a = Math.random()*(Math.PI);
		double b = Math.random()*(2*Math.PI);
		SFVertex3f rayDirection = new SFVertex3f((float)(Math.cos(b)*Math.sin(a)), (float)(Math.sin(b)*Math.sin(a)), (float)(Math.cos(a)));
		return rayDirection;
		
	}
	
		
		
			
	public boolean intersectionTest(SFVertex3f p, SFVertex3f d, ArrayList<Triangle> triangleArray){

		boolean intersection = false;
		int k = 0;

		while(intersection == false && k < triangleArray.size()){

			SFVertex3f v0 = triangleArray.get(k).getVertex1();
			SFVertex3f v1 = triangleArray.get(k).getVertex2();
			SFVertex3f v2 = triangleArray.get(k).getVertex3();
			
			SFVertex3f clonev1 = v1.cloneV();
			clonev1.subtract3f(v0);
			SFVertex3f e1 = clonev1;
			
			SFVertex3f clonev2 = v2.cloneV();
			clonev2.subtract3f(v0);
			SFVertex3f e2 = clonev2;
			
			SFVertex3f h = d.cross(e2);
			float a = e1.dot3f(h);
			
			boolean sameplane = false;
			
			if(Math.abs(a) < 0.0001f)
				sameplane = true;

			if(sameplane == false){
				
				float f = 1.0f/a;
				
				SFVertex3f clonep = p.cloneV();
				clonep.subtract3f(v0);
				SFVertex3f s = clonep;
				
				float u = f * (s.dot3f(h));

				if (!(u < 0.0f || u > 1.0f))
					intersection = true;

				if (intersection == true){
					
					SFVertex3f q = s.cross(e1);
					float v = f*(d.dot3f(q));

					if ((v < 0.0f || (u + v) > 1.0f))
						intersection = false;
					
					if (intersection == true){

						float t = f * (e2.dot3f(q));
					
						if (t < 0.05f)
							intersection = false;

					}
				}
			}
			
			k++;
			
		}
		
		return intersection;
		
	}
	
	
	
	
	public void writeData(String name, ArrayList<Triangle> triangleMesh){
		 
		 Iterator<Triangle> iter = triangleMesh.iterator();
	     FileWriter file;
		
	     try{
			String sep = " ";
			file = new FileWriter(name);
			
			while(iter.hasNext()){	
			    Triangle tri =	iter.next();
				file.write(Float.toString(tri.getVertex1().getX()));
				file.write(sep);
				file.write(Float.toString(tri.getVertex1().getY()));
				file.write(sep);
				file.write(Float.toString(tri.getVertex1().getZ()));
				file.write(sep);
				file.write(Float.toString(tri.getVertex2().getX()));
				file.write(sep);
				file.write(Float.toString(tri.getVertex2().getY()));
				file.write(sep);
				file.write(Float.toString(tri.getVertex2().getZ()));
				file.write(sep);
				file.write(Float.toString(tri.getVertex3().getX()));
				file.write(sep);
				file.write(Float.toString(tri.getVertex3().getY()));
				file.write(sep);
				file.write(Float.toString(tri.getVertex3().getZ()));
				file.write(sep);
				file.write(Float.toString(tri.getNormal1().getX()));
				file.write(sep);
				file.write(Float.toString(tri.getNormal1().getY()));
				file.write(sep);
				file.write(Float.toString(tri.getNormal1().getZ()));
				file.write(sep);
				file.write(Float.toString(tri.getNormal2().getX()));
				file.write(sep);
				file.write(Float.toString(tri.getNormal2().getY()));
				file.write(sep);
				file.write(Float.toString(tri.getNormal2().getZ()));
				file.write(sep);
				file.write(Float.toString(tri.getNormal3().getX()));
				file.write(sep);
				file.write(Float.toString(tri.getNormal3().getY()));
				file.write(sep);
				file.write(Float.toString(tri.getNormal3().getZ()));
				file.write(sep);
				file.write(Float.toString(tri.getAO1()));
				file.write(sep);
				file.write(Float.toString(tri.getAO2()));
				file.write(sep);
				file.write(Float.toString(tri.getAO3()));
				file.write("\n");
			  }
			  	
             file.close();
		
		  }catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
	
	
	public ArrayList<Triangle> readData(int index) throws NumberFormatException, IOException{
		
		ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
		String[] files = {"mushroom.dat", "tube.dat", "glass.dat"};
		BufferedReader file;
		file = new BufferedReader(new FileReader(files[index]));
		String line;
		
		while((line=file.readLine()) != null){
			
			StringTokenizer token = new StringTokenizer(line, " ");
			float[] values = new float[21];
			int i = 0;
			
			while(token.hasMoreTokens()){		
				values[i] = Float.parseFloat((String) token.nextElement()) ;
				i++;
			}

			Triangle triangle = new Triangle(new SFVertex3f(values[0], values[1], values[2]), new SFVertex3f(values[3], values[4], values[5]),
											new SFVertex3f(values[6], values[7], values[8]), new SFVertex3f(values[9], values[10], values[11]),
											new SFVertex3f(values[12], values[13], values[14]), new SFVertex3f(values[15], values[16], values[17]));
			triangle.setAO1(values[18]);
			triangle.setAO2(values[19]);
			triangle.setAO3(values[20]);
			triangleMesh.add(triangle);
			
		}
		
		file.close();
		return triangleMesh;
	}
	
	


	
	public SFMeshGeometry createNeWSFMeshRayTracer(final ArrayList<Triangle> triangleArray, SFPrimitive primitive){
	
		SFMeshGeometry geometry=new SFMeshGeometry(primitive){
			@Override
			public void compile(){
				
				super.compile();
			
				if(primitive.getName().equals("TrianglePND1")){
				
					for(int i=0; i<triangleArray.size(); i++){
						
						int positions = getArray().getPrimitiveData(0).generateElements(3);
						int normals = getArray().getPrimitiveData(1).generateElements(3);
						int datas = getArray().getPrimitiveData(2).generateElements(3);
						SFVertex3f[] positionsArray = triangleArray.get(i).getVertices();
						SFVertex3f[] normalsArray = triangleArray.get(i).getNormals();
						float[] occlusionArray = triangleArray.get(i).getAOValues();
					
						for(int j=0; j<3; j++){
							getArray().getPrimitiveData(0).setElement(positions+j, positionsArray[j]);
							getArray().getPrimitiveData(1).setElement(normals+j, normalsArray[j]);	
							getArray().getPrimitiveData(2).setElement(datas+j, new SFValue1f(occlusionArray[j]));
						}
					
						int[] indices = {positions,positions+1,positions+2	,  normals,normals+1,normals+2   ,    datas,datas+1,datas+2};
						int primitiveIndex = getArray().generateElement();
						SFPrimitiveIndices prIndices = new SFPrimitiveIndices();
						prIndices.setPrimitiveIndices(indices);
						getArray().setElement(primitiveIndex, prIndices);
						
					}
				
				}else if(primitive.getName().equals("Triangle2PND1")){
				
					for(int i=0; i<triangleArray.size(); i+=4){
					
						int positions = getArray().getPrimitiveData(0).generateElements(6);
						int normals = getArray().getPrimitiveData(1).generateElements(6);
						int datas = getArray().getPrimitiveData(2).generateElements(6);
						SFVertex3f[] positionsArray = new SFVertex3f[6];
						SFVertex3f[] normalsArray = new SFVertex3f[6];
						float[] occlusionArray = new float[6];
						//A
						SFVertex3f p = triangleArray.get(i).getVertex1();
						SFVertex3f n = triangleArray.get(i).getNormal1();
						float ao = triangleArray.get(i).getAO1();
						positionsArray[0] = p;
						normalsArray[0] = n;
						occlusionArray[0] = ao;
						//B
						p = triangleArray.get(i+1).getVertex1();
						n = triangleArray.get(i+1).getNormal1();
						ao = triangleArray.get(i+1).getAO1();
						positionsArray[1] = p;
						normalsArray[1] = n;
						occlusionArray[1] = ao;
						//C
						p = triangleArray.get(i+2).getVertex1();
						n = triangleArray.get(i+2).getNormal1();
						ao = triangleArray.get(i+2).getAO1();
						positionsArray[2] = p;
						normalsArray[2] = n;
						occlusionArray[2] = ao;
						//D
						p = triangleArray.get(i+3).getVertex1();
						n = triangleArray.get(i+3).getNormal1();
						ao = triangleArray.get(i+3).getAO1();
						positionsArray[3] = p;
						normalsArray[3] = n;
						occlusionArray[3] = ao;
						//E
						p = triangleArray.get(i+3).getVertex2();
						n = triangleArray.get(i+3).getNormal2();
						ao = triangleArray.get(i+3).getAO2();
						positionsArray[4] = p;
						normalsArray[4] = n;
						occlusionArray[4] = ao;
						//F
						p = triangleArray.get(i+3).getVertex3();
						n = triangleArray.get(i+3).getNormal3();
						ao = triangleArray.get(i+3).getAO3();
						positionsArray[5] = p;
						normalsArray[5] = n;
						occlusionArray[5] = ao;
					
						for(int j=0; j<6; j++){
							getArray().getPrimitiveData(0).setElement(positions+j, positionsArray[j]);
							getArray().getPrimitiveData(1).setElement(normals+j, normalsArray[j]);	
							getArray().getPrimitiveData(2).setElement(datas+j, new SFValue1f(occlusionArray[j]));
						}
						
						int primitiveIndex = getArray().generateElement();
						int[] indices = {positions,positions+1,positions+2,positions+3,positions+4,positions+5   ,  
								normals,normals+1,normals+2,normals+3,normals+4,normals+5   ,    
								datas,datas+1,datas+2,datas+3,datas+4,datas+5 };
						SFPrimitiveIndices prIndices=new SFPrimitiveIndices();
						prIndices.setPrimitiveIndices(indices);
						getArray().setElement(primitiveIndex, prIndices);
						
					}
					
				}else if(primitive.getName().equals("Triangle3PND1")){
					
					for(int i=0; i<triangleArray.size(); i+=9){
					
						int positions = getArray().getPrimitiveData(0).generateElements(10);
						int normals = getArray().getPrimitiveData(1).generateElements(10);
						int datas = getArray().getPrimitiveData(2).generateElements(10);	
						SFVertex3f[] positionsArray = new SFVertex3f[10];
						SFVertex3f[] normalsArray = new SFVertex3f[10];
						float[] occlusionArray = new float[10];
						//A
						SFVertex3f p = triangleArray.get(i+8).getVertex2();
						SFVertex3f n = triangleArray.get(i+8).getNormal2();
						float ao = triangleArray.get(i+8).getAO2();
						positionsArray[0] = p;
						normalsArray[0] = n;
						occlusionArray[0] = ao;
						//B
						p = triangleArray.get(i+2).getVertex3();
						n = triangleArray.get(i+2).getNormal3();
						ao = triangleArray.get(i+2).getAO3();
						positionsArray[1] = p;
						normalsArray[1] = n;
						occlusionArray[1] = ao;
						//C
						p = triangleArray.get(i).getVertex1();
						n = triangleArray.get(i).getNormal1();
						ao = triangleArray.get(i).getAO1();
						positionsArray[2] = p;
						normalsArray[2] = n;
						occlusionArray[2] = ao;
						//AB
						p = triangleArray.get(i+8).getVertex3();
						n = triangleArray.get(i+8).getNormal3();
						ao = triangleArray.get(i+8).getAO3();
						positionsArray[3] = p;
						normalsArray[3] = n;
						occlusionArray[3] = ao;
						//BA
						p = triangleArray.get(i+3).getVertex2();
						n = triangleArray.get(i+3).getNormal2();
						ao = triangleArray.get(i+3).getAO2();
						positionsArray[4] = p;
						normalsArray[4] = n;
						occlusionArray[4] = ao;
						//BC
						p = triangleArray.get(i+2).getVertex1();
						n = triangleArray.get(i+2).getNormal1();
						ao = triangleArray.get(i+2).getAO1();
						positionsArray[5] = p;
						normalsArray[5] = n;
						occlusionArray[5] = ao;
						//CB
						p = triangleArray.get(i).getVertex3();
						n = triangleArray.get(i).getNormal3();
						ao = triangleArray.get(i).getAO3();
						positionsArray[6] = p;
						normalsArray[6] = n;
						occlusionArray[6] = ao;
						//CA
						p = triangleArray.get(i).getVertex2();
						n = triangleArray.get(i).getNormal2();
						ao = triangleArray.get(i).getAO2();
						positionsArray[7] = p;
						normalsArray[7] = n;
						occlusionArray[7] = ao;
						//AC
						p = triangleArray.get(i+8).getVertex1();
						n = triangleArray.get(i+8).getNormal1();
						ao = triangleArray.get(i+8).getAO1();
						positionsArray[8] = p;
						normalsArray[8] = n;
						occlusionArray[8] = ao;
						//ABC
						p = triangleArray.get(i+1).getVertex2();
						n = triangleArray.get(i+1).getNormal2();
						ao = triangleArray.get(i+1).getAO2();
						positionsArray[9] = p;
						normalsArray[9] = n;
						occlusionArray[9] = ao;
					
						for(int j=0; j<10; j++){
							getArray().getPrimitiveData(0).setElement(positions+j, positionsArray[j]);
							getArray().getPrimitiveData(1).setElement(normals+j, normalsArray[j]);	
							getArray().getPrimitiveData(2).setElement(datas+j, new SFValue1f(occlusionArray[j]));
						}
					
						int primitiveIndex = getArray().generateElement();
						int[] indices = {positions,positions+1,positions+2,positions+3,positions+4,positions+5,positions+6,positions+7,positions+8,positions+9
							,  normals,normals+1,normals+2,normals+3,normals+4,normals+5,normals+6,normals+7,normals+8,normals+9  
							,    datas,datas+1,datas+2,datas+3,datas+4,datas+5,datas+6,datas+7,datas+8,datas+9};
						SFPrimitiveIndices prIndices=new SFPrimitiveIndices();
						prIndices.setPrimitiveIndices(indices);
						getArray().setElement(primitiveIndex, prIndices);
						
					}
				}
			}
		};
		return geometry;
	}
	
	
	
	
	// D2
	
	public ArrayList<Triangle> sample(SFCurvedTubeFunction function, float step_u, float step_v, int primitive){
		
		ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
		float v = 0;
		float u = 0;
		float step = 0;
		int lato = 0;
		
		if(primitive==0){
			
			step = 2*step_u;
			lato = 2;
			
		}else if(primitive==1){
			
			step = 3*step_u;
			lato = 3;
			
		}
		
		for(int j=0; j<7.0f; j+=1){
				
			for(int i=0; i<7.0f; i+=1){
				
				u = i*step;
				v = j*step;
				 
				for(int z=0; z<lato; z+=1){
				
					for(float k=0; k<lato; k+=1){
						
						SFVertex3f p1 = function.getPosition(u, v);  
						SFVertex3f n1 = function.getNormal(u, v); 
						n1.normalize3f();
						SFVertex3f p2 = function.getPosition(u, v+step_v); 
						SFVertex3f n2 = function.getNormal(u, v+step_v);
						n2.normalize3f();
						SFVertex3f p3 = function.getPosition(u+step_u, v ); 
						SFVertex3f n3 = function.getNormal(u+step_u, v);
						n3.normalize3f();
						Triangle t1 = new Triangle(p1,p2,p3,n1,n2,n3);
						t1.setAO1(getKao(function, u, v, n1));
						t1.setAO2(getKao(function, u, v+step_v, n2));
						t1.setAO3(getKao(function, u+step_u, v, n3));
						triangleMesh.add(t1);
						SFVertex3f p4 = function.getPosition(u+step_u, v+step_v);
						SFVertex3f n4 = function.getNormal(u+step_u, v+step_v); 
						n4.normalize3f();
						Triangle t2 = new Triangle(p2,p3,p4,n2,n3,n4);
						t2.setAO1(t1.getAO2());
						t2.setAO2(t1.getAO3());
						t2.setAO3(getKao(function, u+step_u, v+step_v, n4));
						triangleMesh.add(t2);
						u = u + step_u;
					}
					
					u = i*step;
					v = v+step_v;
				
				}	
				//System.out.println(triangleMesh.size());
			  }
		  
		}
		
		return triangleMesh;
	}
		
	
	
	
	

	
	

	


	
	public float getKao(SFCurvedTubeFunction function, float u, float v, SFVertex3f normal){
		
		SFVertex3f Du2 = getDu2(function,u,v);
		SFVertex3f Dv2 = getDv2(function,u,v);
		SFVertex3f Duv = getDuv(function,u,v);
		float ku2 = normal.dot3f(Du2); 
		float kv2 = normal.dot3f(Dv2);
		float kuv = normal.dot3f(Duv);
		float k = ku2+kv2+2*kuv;
		
		if(k<0.001f){
			
			k=1;
			
		}else{
			
			k = (float)Math.exp(-g*(double)k);
			
		}
		return k;
	}



	
	public SFVertex3f getDu2(SFCurvedTubeFunction function, float u,float v){
		SFVertex3f p1 = function.getPosition(u+eps,v);
		SFVertex3f p2 = function.getPosition(u,v);
		SFVertex3f p3 = function.getPosition(u-eps,v);
		p2.mult(2.0f);
		p1.subtract3f(p2);
		p1.add3f(p3);
		p1.mult(1.0f/(eps*eps));
		return p1;
	}


	
	public SFVertex3f getDv2(SFCurvedTubeFunction function, float u,float v){
		SFVertex3f p1 = function.getPosition(u,v+eps);
		SFVertex3f p2 = function.getPosition(u,v);
		SFVertex3f p3 = function.getPosition(u,v-eps);
		p2.mult(2.0f);
		p1.subtract3f(p2);
		p1.add3f(p3);
		p1.mult(1.0f/(eps*eps));
		return p1;
	}


	
	
	public SFVertex3f getDuv(SFCurvedTubeFunction function, float u,float v){
		SFVertex3f p1 = function.getPosition(u+eps,v+eps);
		SFVertex3f p2 = function.getPosition(u+eps,v-eps);
		SFVertex3f p3 = function.getPosition(u-eps,v+eps);
		SFVertex3f p4 = function.getPosition(u-eps,v-eps);
		p1.subtract3f(p2);
		p1.subtract3f(p3);
		p1.add3f(p4);
		p1.mult(1.0f/(4*(eps*eps)));
		return p1;
	}




	

	
	
	

	
	
	public SFMeshGeometry createNeWSFMeshD2(final ArrayList<Triangle> triangleArray, SFPrimitive primitive){
	
		SFMeshGeometry geometry=new SFMeshGeometry(primitive){
			@Override
			public void compile(){
				
				super.compile();
				
				if(primitive.getName().equals("Triangle3PND1")){
					
					for(int i=0; i<triangleArray.size(); i+=18){
						
						//primo triangolo
						int positions = getArray().getPrimitiveData(0).generateElements(10);
						int normals = getArray().getPrimitiveData(1).generateElements(10);
						int datas = getArray().getPrimitiveData(2).generateElements(10);	
						SFVertex3f[] positionsArray = new SFVertex3f[10];
						SFVertex3f[] normalsArray = new SFVertex3f[10];
						float[] occlusionArray = new float[10];
						//A
						SFVertex3f p = triangleArray.get(i).getVertex1();
						SFVertex3f n = triangleArray.get(i).getNormal1();
						float ao = triangleArray.get(i).getAO1();
						positionsArray[0] = p;
						normalsArray[0] = n;
						occlusionArray[0] = ao;
						//B
						p = triangleArray.get(i+4).getVertex3();
						n = triangleArray.get(i+4).getNormal3();
						ao = triangleArray.get(i+4).getAO3();
						positionsArray[1] = p;
						normalsArray[1] = n;
						occlusionArray[1] = ao;
						//C
						p = triangleArray.get(i+12).getVertex2();
						n = triangleArray.get(i+12).getNormal2();
						ao = triangleArray.get(i+12).getAO2();
						positionsArray[2] = p;
						normalsArray[2] = n;
						occlusionArray[2] = ao;
						//AB
						p = triangleArray.get(i).getVertex3();
						n = triangleArray.get(i).getNormal3();
						ao = triangleArray.get(i).getAO3();
						positionsArray[3] = p;
						normalsArray[3] = n;
						occlusionArray[3] = ao;
						//BA
						p = triangleArray.get(i+2).getVertex3();
						n = triangleArray.get(i+2).getNormal3();
						ao = triangleArray.get(i+2).getAO3();
						positionsArray[4] = p;
						normalsArray[4] = n;
						occlusionArray[4] = ao;
						//BC
						p = triangleArray.get(i+4).getVertex2();
						n = triangleArray.get(i+4).getNormal2();
						ao = triangleArray.get(i+4).getAO2();
						positionsArray[5] = p;
						normalsArray[5] = n;
						occlusionArray[5] = ao;
						//CB
						p = triangleArray.get(i+8).getVertex2();
						n = triangleArray.get(i+8).getNormal2();
						ao = triangleArray.get(i+8).getAO2();
						positionsArray[6] = p;
						normalsArray[6] = n;
						occlusionArray[6] = ao;
						//CA
						p = triangleArray.get(i+6).getVertex2();
						n = triangleArray.get(i+6).getNormal2();
						ao = triangleArray.get(i+6).getAO2();
						positionsArray[7] = p;
						normalsArray[7] = n;
						occlusionArray[7] = ao;
						//AC
						p = triangleArray.get(i).getVertex2();
						n = triangleArray.get(i).getNormal2();
						ao = triangleArray.get(i).getAO2();
						positionsArray[8] = p;
						normalsArray[8] = n;
						occlusionArray[8] = ao;
						//ABC
						p = triangleArray.get(i+2).getVertex2();
						n = triangleArray.get(i+2).getNormal2();
						ao = triangleArray.get(i+2).getAO2();
						positionsArray[9] = p;
						normalsArray[9] = n;
						occlusionArray[9] = ao;
					
						for(int j=0; j<10; j++){
							getArray().getPrimitiveData(0).setElement(positions+j, positionsArray[j]);
							getArray().getPrimitiveData(1).setElement(normals+j, normalsArray[j]);	
							getArray().getPrimitiveData(2).setElement(datas+j, new SFValue1f(occlusionArray[j]));
						}
					
						int primitiveIndex = getArray().generateElement();
						int[] indices = {positions,positions+1,positions+2,positions+3,positions+4,positions+5,positions+6,positions+7,positions+8,positions+9
							,  normals,normals+1,normals+2,normals+3,normals+4,normals+5,normals+6,normals+7,normals+8,normals+9  
							,    datas,datas+1,datas+2,datas+3,datas+4,datas+5,datas+6,datas+7,datas+8,datas+9};
						SFPrimitiveIndices prIndices=new SFPrimitiveIndices();
						prIndices.setPrimitiveIndices(indices);
						getArray().setElement(primitiveIndex, prIndices);
						
						//secondo triangolo
						positions = getArray().getPrimitiveData(0).generateElements(10);
						normals = getArray().getPrimitiveData(1).generateElements(10);
						datas = getArray().getPrimitiveData(2).generateElements(10);	
						positionsArray = new SFVertex3f[10];
						normalsArray = new SFVertex3f[10];
						occlusionArray = new float[10];
						
						//A
						p = triangleArray.get(i+17).getVertex3();
						n = triangleArray.get(i+17).getNormal3();
						ao = triangleArray.get(i+17).getAO3();
						positionsArray[0] = p;
						normalsArray[0] = n;
						occlusionArray[0] = ao;
						//B
						p = triangleArray.get(i+4).getVertex3();
						n = triangleArray.get(i+4).getNormal3();
						ao = triangleArray.get(i+4).getAO3();
						positionsArray[1] = p;
						normalsArray[1] = n;
						occlusionArray[1] = ao;
						//C
						p = triangleArray.get(i+12).getVertex2();
						n = triangleArray.get(i+12).getNormal2();
						ao = triangleArray.get(i+12).getAO2();
						positionsArray[2] = p;
						normalsArray[2] = n;
						occlusionArray[2] = ao;
						//AB
						p = triangleArray.get(i+16).getVertex3();
						n = triangleArray.get(i+16).getNormal3();
						ao = triangleArray.get(i+16).getAO3();
						positionsArray[3] = p;
						normalsArray[3] = n;
						occlusionArray[3] = ao;
						//BA
						p = triangleArray.get(i+10).getVertex3();
						n = triangleArray.get(i+10).getNormal3();
						ao = triangleArray.get(i+10).getAO3();
						positionsArray[4] = p;
						normalsArray[4] = n;
						occlusionArray[4] = ao;
						//BC
						p = triangleArray.get(i+4).getVertex2();
						n = triangleArray.get(i+4).getNormal2();
						ao = triangleArray.get(i+4).getAO2();
						positionsArray[5] = p;
						normalsArray[5] = n;
						occlusionArray[5] = ao;
						//CB
						p = triangleArray.get(i+8).getVertex2();
						n = triangleArray.get(i+8).getNormal2();
						ao = triangleArray.get(i+8).getAO2();
						positionsArray[6] = p;
						normalsArray[6] = n;
						occlusionArray[6] = ao;
						//CA
						p = triangleArray.get(i+14).getVertex2();
						n = triangleArray.get(i+14).getNormal2();
						ao = triangleArray.get(i+14).getAO2();
						positionsArray[7] = p;
						normalsArray[7] = n;
						occlusionArray[7] = ao;
						//AC
						p = triangleArray.get(i+16).getVertex2();
						n = triangleArray.get(i+16).getNormal2();
						ao = triangleArray.get(i+16).getAO2();
						positionsArray[8] = p;
						normalsArray[8] = n;
						occlusionArray[8] = ao;
						//ABC
						p = triangleArray.get(i+10).getVertex2();
						n = triangleArray.get(i+10).getNormal2();
						ao = triangleArray.get(i+10).getAO2();
						positionsArray[9] = p;
						normalsArray[9] = n;
						occlusionArray[9] = ao;
						
						
						for(int j=0; j<10; j++){
							getArray().getPrimitiveData(0).setElement(positions+j, positionsArray[j]);
							getArray().getPrimitiveData(1).setElement(normals+j, normalsArray[j]);	
							getArray().getPrimitiveData(2).setElement(datas+j, new SFValue1f(occlusionArray[j]));
						}
					
						primitiveIndex = getArray().generateElement();
						int[] indices2 = {positions,positions+1,positions+2,positions+3,positions+4,positions+5,positions+6,positions+7,positions+8,positions+9
							,  normals,normals+1,normals+2,normals+3,normals+4,normals+5,normals+6,normals+7,normals+8,normals+9  
							,    datas,datas+1,datas+2,datas+3,datas+4,datas+5,datas+6,datas+7,datas+8,datas+9};
						prIndices=new SFPrimitiveIndices();
						prIndices.setPrimitiveIndices(indices2);
						getArray().setElement(primitiveIndex, prIndices);						
			
					}
					
				}else if(primitive.getName().equals("Triangle2PND1")){
				
					for(int i=0; i<triangleArray.size(); i+=8){
			
						int positions = getArray().getPrimitiveData(0).generateElements(6);
						int normals = getArray().getPrimitiveData(1).generateElements(6);
						int datas = getArray().getPrimitiveData(2).generateElements(6);
						SFVertex3f[] positionsArray = new SFVertex3f[6];
						SFVertex3f[] normalsArray = new SFVertex3f[6];
						float[] occlusionArray = new float[6];
						//A
						SFVertex3f p = triangleArray.get(i).getVertex1();
						SFVertex3f n = triangleArray.get(i).getNormal1();
						float ao = triangleArray.get(i).getAO1();
						positionsArray[0] = p;
						normalsArray[0] = n;
						occlusionArray[0] = ao;
						//B
						p = triangleArray.get(i+2).getVertex3();
						n = triangleArray.get(i+2).getNormal3();
						ao = triangleArray.get(i+2).getAO3();
						positionsArray[1] = p;
						normalsArray[1] = n;
						occlusionArray[1] = ao;
						//C
						p = triangleArray.get(i+4).getVertex2();
						n = triangleArray.get(i+4).getNormal2();
						ao = triangleArray.get(i+4).getAO2();
						positionsArray[2] = p;
						normalsArray[2] = n;
						occlusionArray[2] = ao;
						//D
						p = triangleArray.get(i).getVertex3();
						n = triangleArray.get(i).getNormal3();
						ao = triangleArray.get(i).getAO3();
						positionsArray[3] = p;
						normalsArray[3] = n;
						occlusionArray[3] = ao;
						//E
						p = triangleArray.get(i+2).getVertex2();
						n = triangleArray.get(i+2).getNormal2();
						ao = triangleArray.get(i+2).getAO2();
						positionsArray[4] = p;
						normalsArray[4] = n;
						occlusionArray[4] = ao;
						//F
						p = triangleArray.get(i).getVertex2();
						n = triangleArray.get(i).getNormal2();
						ao = triangleArray.get(i).getAO2();
						positionsArray[5] = p;
						normalsArray[5] = n;
						occlusionArray[5] = ao;
				
						for(int j=0; j<6; j++){
							getArray().getPrimitiveData(0).setElement(positions+j, positionsArray[j]);
							getArray().getPrimitiveData(1).setElement(normals+j, normalsArray[j]);	
							getArray().getPrimitiveData(2).setElement(datas+j, new SFValue1f(occlusionArray[j]));
						}
					
						int primitiveIndex = getArray().generateElement();
						int[] indices = {positions,positions+1,positions+2,positions+3,positions+4,positions+5   ,  
							normals,normals+1,normals+2,normals+3,normals+4,normals+5   ,    
							datas,datas+1,datas+2,datas+3,datas+4,datas+5 };
						SFPrimitiveIndices prIndices=new SFPrimitiveIndices();
						prIndices.setPrimitiveIndices(indices);
						getArray().setElement(primitiveIndex, prIndices);
					
						// secondo triangolo
						positions = getArray().getPrimitiveData(0).generateElements(6);
						normals = getArray().getPrimitiveData(1).generateElements(6);
						datas = getArray().getPrimitiveData(2).generateElements(6);
						positionsArray = new SFVertex3f[6];
						normalsArray = new SFVertex3f[6];
						occlusionArray = new float[6];
						//A
						p = triangleArray.get(i+7).getVertex3();
						n = triangleArray.get(i+7).getNormal3();
						ao = triangleArray.get(i+7).getAO3();
						positionsArray[0] = p;
						normalsArray[0] = n;
						occlusionArray[0] = ao;
						//B
						p = triangleArray.get(i+2).getVertex3();
						n = triangleArray.get(i+2).getNormal3();
						ao = triangleArray.get(i+2).getAO3();
						positionsArray[1] = p;
						normalsArray[1] = n;
						occlusionArray[1] = ao;
						//C
						p = triangleArray.get(i+4).getVertex2();
						n = triangleArray.get(i+4).getNormal2();
						ao = triangleArray.get(i+4).getAO2();
						positionsArray[2] = p;
						normalsArray[2] = n;
						occlusionArray[2] = ao;
						//D
						p = triangleArray.get(i+6).getVertex3();
						n = triangleArray.get(i+6).getNormal3();
						ao = triangleArray.get(i+6).getAO3();
						positionsArray[3] = p;
						normalsArray[3] = n;
						occlusionArray[3] = ao;
						//E
						p = triangleArray.get(i+6).getVertex1();
						n = triangleArray.get(i+6).getNormal1();
						ao = triangleArray.get(i+6).getAO1();
						positionsArray[4] = p;
						normalsArray[4] = n;
						occlusionArray[4] = ao;
						//F
						p = triangleArray.get(i+6).getVertex2();
						n = triangleArray.get(i+6).getNormal2();
						ao = triangleArray.get(i+6).getAO2();
						positionsArray[5] = p;
						normalsArray[5] = n;
						occlusionArray[5] = ao;
				
						for(int j=0; j<6; j++){
							getArray().getPrimitiveData(0).setElement(positions+j, positionsArray[j]);
							getArray().getPrimitiveData(1).setElement(normals+j, normalsArray[j]);	
							getArray().getPrimitiveData(2).setElement(datas+j, new SFValue1f(occlusionArray[j]));
						}
					
						primitiveIndex = getArray().generateElement();
						int[] indices2 = {positions,positions+1,positions+2,positions+3,positions+4,positions+5   ,  
							normals,normals+1,normals+2,normals+3,normals+4,normals+5   ,    
							datas,datas+1,datas+2,datas+3,datas+4,datas+5 };
						prIndices=new SFPrimitiveIndices();
						prIndices.setPrimitiveIndices(indices2);
						getArray().setElement(primitiveIndex, prIndices);
					
					}
				
				}	
			}
		};
		
		return geometry;
		
	}


	
	

	
	public void storeXML(SFDataAsset<?> asset) {
		SFDataUtility.saveXMLFile(root, getFilename(), asset);
	}
		


	public void printAOValues(ArrayList<Triangle> triangleArray){
		for (int k=0; k<triangleArray.size(); k++){
			System.out.println("triangolo: " + k);
			System.out.println(triangleArray.get(k).getAO1() + " " + triangleArray.get(k).getAO2() + " " + triangleArray.get(k).getAO3());
		}
	}



	public void printTriangleInfo(ArrayList<Triangle> triangleArray){
		for (int k=0; k<triangleArray.size(); k++){
			System.out.println("triangolo: " + k);
			System.out.println("vertici: " + triangleArray.get(k).getVertex1() + "  " + triangleArray.get(k).getVertex2() + "  " + triangleArray.get(k).getVertex3());
			System.out.println("normali: " + triangleArray.get(k).getNormal1() + "  " + triangleArray.get(k).getNormal2() + "  " + triangleArray.get(k).getNormal3());
		}
	}


	
}	
