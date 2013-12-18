package test;

import java.util.ArrayList;

import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.math.SFValue1f;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.renderer.SFNode;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.java.SFObjectsLibraryDecoder;
import shadow.renderer.data.java.SFXMLDataInterpreter;
import shadow.renderer.data.utils.SFDataUtility;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.system.SFArray;
import shadow.system.SFException;
import shadow.system.SFInitiable;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.SFObjectsLibrary.RecordData;
import util.Triangle;
import viewer.SFFrameController;
import viewer.SFTextureViewer;
import viewer.SFViewer;
import common.CommonData;
import common.CommonPipeline;
import common.CommonTextures;

public abstract class SFAbstractTestAO {

	protected static String root = "testsData";
	protected static String rootInput = "testsDataInput";
	protected static boolean storeData = true;
	protected static boolean loadLibrariesAsXml = false;
	protected SFObjectsLibrary library;
	
	
	//AO
	
	public ArrayList<Triangle> convertSFMeshGeometryInTriangles(SFMeshGeometry meshGeometry) {
			
		ArrayList <Triangle> triangleMesh = new ArrayList<Triangle>();
		ArrayList <SFVertex3f> values = new ArrayList<SFVertex3f>();
		SFArray <SFValuenf> sfArray;
			
		SFPrimitiveArray array = meshGeometry.getArray(); 
		SFPrimitive primitive = meshGeometry.getPrimitive();
		SFPrimitiveIndices primitiveIndices = array.generateSample();
			
		for (int index=0; index<array.getElementsCount(); index++){ 
				
			array.getElement(index, primitiveIndices);
	
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
					//0 3 5 
				Triangle triangle1 = new Triangle(values.get(0), values.get(3), values.get(5), values.get(6), values.get(9), values.get(11));
				triangleMesh.add(triangle1);
					//1 4 3  
				Triangle triangle2 = new Triangle(values.get(1), values.get(4), values.get(3), values.get(7), values.get(10), values.get(9));
				triangleMesh.add(triangle2);
					//2 5 4 
				Triangle triangle3 = new Triangle(values.get(2), values.get(5), values.get(4), values.get(8), values.get(11), values.get(10));
				triangleMesh.add(triangle3);
					//3 5 4 
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
	
	
	
	
	public void calculateAOValues(ArrayList<Triangle> triangleArray, float totalRays){
		
		ArrayList<Triangle> triangleCurrent = new ArrayList<Triangle>(); 
				
				for (int k=0; k<triangleArray.size(); k++){
					
					triangleCurrent = (ArrayList<Triangle>)triangleArray.clone();
					triangleCurrent.remove(k);
					triangleArray.get(k).setAO1(calculateAO(triangleArray.get(k).getVertex1(), triangleArray.get(k).getNormal1(), triangleCurrent, totalRays));
					triangleArray.get(k).setAO2(calculateAO(triangleArray.get(k).getVertex2(), triangleArray.get(k).getNormal2(), triangleCurrent, totalRays));
					triangleArray.get(k).setAO3(calculateAO(triangleArray.get(k).getVertex3(), triangleArray.get(k).getNormal3(), triangleCurrent, totalRays));
								
				}
				
	}
	
	
		
		
	public float calculateAO(SFVertex3f vertex, SFVertex3f normal, ArrayList <Triangle> triangleArray, float totalRays){
				
			float numOccluded = 0;
			float aoValue = 0;
			boolean intersection;

	    	for (int r=0; r<totalRays; r++){
	    		   		
	    		  double a = Math.random()*(Math.PI);
	    		  double b = Math.random()*(2*Math.PI);
	    		  
	    		  SFVertex3f rayDirection = new SFVertex3f((float)(Math.cos(b)*Math.sin(a)), (float)(Math.sin(b)*Math.sin(a)), (float)(Math.cos(a)));
	    		  
	    		  if (normal.dot3f(rayDirection) < 0.01f){
	    		 
	    		   	r--;
	    		   			
	    		  } else {
	    			 
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
		
			for(int k=0; k<triangleArray.size(); k++){
				
				if(intersection == false){
					
					intersection = true;
					
					// vertici del triangolo da testare
					SFVertex3f v0 = triangleArray.get(k).getVertex1();
					SFVertex3f v1 = triangleArray.get(k).getVertex2();
					SFVertex3f v2 = triangleArray.get(k).getVertex3();
					
					// normali del triangolo da testare
					//SFVertex3f n0 = triangleArray.get(k).getNormal1();
					//SFVertex3f n1 = triangleArray.get(k).getNormal2();
					//SFVertex3f n2 = triangleArray.get(k).getNormal3();
					
					//if(n0.dot3f(d)>0.05f && n1.dot3f(d)>0.05f && n2.dot3f(d)>0.05f)
						//intersection = false;
					
					if(intersection==true){
						
						//e1 = v1-v0
						SFVertex3f clonev1 = v1.cloneV();
						clonev1.subtract3f(v0);
						SFVertex3f e1 = clonev1;
						
						//e2 = v2-v0
						SFVertex3f clonev2 = v2.cloneV();
						clonev2.subtract3f(v0);
						SFVertex3f e2 = clonev2;
						
						SFVertex3f h = d.cross(e2);
							
						float a = e1.dot3f(h);
						
						//verifico se il raggio è sullo stesso piano del triangolo; se a=0 sono //
						if (Math.abs(a) < 0.0001f) 
							intersection = false;
							
						if(intersection == true){

							float f = 1.0f/a;
						
							//s = p- v0
							SFVertex3f clonep = p.cloneV();
							clonep.subtract3f(v0);
							SFVertex3f s = clonep;
							
							float u = f*(s.dot3f(h));
							
							if(u < 0.0f || u > 1.0f)
								intersection = false;
								
							if(intersection == true){
									
								SFVertex3f q = s.cross(e1);
								
								float v = f*(d.dot3f(q));
									
								if(v < 0.0f || (u+v) > 1.0f)
									intersection = false;
									
								if(intersection == true){
										
									float t = f*(e2.dot3f(q));
										
										// t è la distanza tra l'origine del raggio e il punto di intersezione
										//if(t <= 0.05f)
										if(t<= 0.01f)
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
	
	
	



		
		
		
	public ArrayList<Triangle> tessellation (ArrayList<Triangle> triangleArray, int n){
		
		ArrayList <Triangle> tessellationArray = new ArrayList <Triangle>(); 

		float step=1.0f/n;
		
		for (int k=0; k<triangleArray.size(); k++){
			
			// divisione in strisce
			for (int i=0; i<n; i++){
				
				float v1 = i*step;
				float v2 = v1+step;
				
				int total=2*(n-i-1)+1; // numero di triangoli totali per striscia
				
				ArrayList <SFVertex3f> vertices = new ArrayList <SFVertex3f>();
				ArrayList <SFVertex3f> normals = new ArrayList <SFVertex3f>();
				
				SFVertex3f newVertex;
				SFVertex3f newNormal;
				
				// disegno della striscia
				for (int j=0; j<(n-i); j++){
					
					float u = j*step;
					float w = 1-v1-u;
					
					newVertex = new SFVertex3f (getPositionX(u,v1,w,triangleArray.get(k)), getPositionY(u,v1,w,triangleArray.get(k)), getPositionZ(u,v1,w,triangleArray.get(k)));
					newNormal = new SFVertex3f (getNormalX(u,v1,w,triangleArray.get(k)), getNormalY(u,v1,w,triangleArray.get(k)), getNormalZ(u,v1,w,triangleArray.get(k)));
					newNormal.normalize3f();
					vertices.add(newVertex);
					normals.add(newNormal);
					
					w = 1-v2-u;
					
					newVertex = new SFVertex3f (getPositionX(u,v2,w,triangleArray.get(k)), getPositionY(u,v2,w,triangleArray.get(k)), getPositionZ(u,v2,w,triangleArray.get(k)));	
					newNormal = new SFVertex3f (getNormalX(u,v2,w,triangleArray.get(k)), getNormalY(u,v2,w,triangleArray.get(k)), getNormalZ(u,v2,w,triangleArray.get(k))); 
					newNormal.normalize3f();
					vertices.add(newVertex);
					normals.add(newNormal);
					
				}
				
				newVertex = new SFVertex3f (getPositionX((1-v1),v1,0,triangleArray.get(k)), getPositionY((1-v1),v1,0,triangleArray.get(k)), getPositionZ((1-v1),v1,0,triangleArray.get(k)));	
				newNormal = new SFVertex3f (getNormalX((1-v1),v1,0,triangleArray.get(k)), getNormalY((1-v1),v1,0,triangleArray.get(k)), getNormalZ((1-v1),v1,0,triangleArray.get(k))); 
				newNormal.normalize3f();
				vertices.add(newVertex);
				normals.add(newNormal);
				
				// creazione triangoli di una striscia
				for (int z=0; z<total; z++){
				
                    Triangle t = new Triangle(vertices.get(z), vertices.get(z+1), vertices.get(z+2), normals.get(z), normals.get(z+1), normals.get(z+2));
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


public SFMeshGeometry createNeWSFMeshGeometryAO(final ArrayList<Triangle> triangleArray, SFPrimitive primitive){
	
	SFMeshGeometry geometry=new SFMeshGeometry(primitive){
		@Override
		public void compile() {
				
			super.compile();
			
			if(primitive.getName().equals("TrianglePND1")){
				
				for (int i=0; i<triangleArray.size(); i++){
					
					int positions = getArray().getPrimitiveData(0).generateElements(3);
					int normals = getArray().getPrimitiveData(1).generateElements(3);
					int datas = getArray().getPrimitiveData(2).generateElements(3);
					
					SFVertex3f[] positionsArray = triangleArray.get(i).getVertices();
					SFVertex3f[] normalsArray = triangleArray.get(i).getNormals();
					float[] occlusionArray = triangleArray.get(i).getAOValues();
					
					for (int j=0; j<3; j++){
						
						getArray().getPrimitiveData(0).setElement(positions+j, positionsArray[j]);
						getArray().getPrimitiveData(1).setElement(normals+j, normalsArray[j]);	
						getArray().getPrimitiveData(2).setElement(datas+j, new SFValue1f(occlusionArray[j]));
						
					}
					
					int[] indices = { positions,positions+1,positions+2   ,  normals,normals+1,normals+2   ,    datas,datas+1,datas+2 };
					int primitiveIndex = getArray().generateElement();
					SFPrimitiveIndices prIndices=new SFPrimitiveIndices();
					prIndices.setPrimitiveIndices(indices);
					getArray().setElement(primitiveIndex, prIndices);
				}
				
			}else if(primitive.getName().equals("Triangle2PND1")){
				
				for (int i=0; i<triangleArray.size(); i+=4){
					
					int positions = getArray().getPrimitiveData(0).generateElements(6);
					int normals = getArray().getPrimitiveData(1).generateElements(6);
					int datas = getArray().getPrimitiveData(2).generateElements(6);
						
					SFVertex3f[] positionsArray = new SFVertex3f[6];
					SFVertex3f[] normalsArray = new SFVertex3f[6];
					float[] occlusionArray = new float[6];
					//0
					SFVertex3f p0 = triangleArray.get(i).getVertex1();
					SFVertex3f n0 = triangleArray.get(i).getNormal1();
					float ao0 = triangleArray.get(i).getAO1();
					positionsArray[0] = p0;
					normalsArray[0] = n0;
					occlusionArray[0] = ao0;
					//1
					SFVertex3f p1 = triangleArray.get(i+1).getVertex1();
					SFVertex3f n1 = triangleArray.get(i+1).getNormal1();
					float ao1 = triangleArray.get(i+1).getAO1();
					positionsArray[1] = p1;
					normalsArray[1] = n1;
					occlusionArray[1] = ao1;
					//2
					SFVertex3f p2 = triangleArray.get(i+2).getVertex1();
					SFVertex3f n2 = triangleArray.get(i+2).getNormal1();
					float ao2 = triangleArray.get(i+2).getAO1();
					positionsArray[2] = p2;
					normalsArray[2] = n2;
					occlusionArray[2] = ao2;
					//3
					SFVertex3f p3 = triangleArray.get(i+3).getVertex1();
					SFVertex3f n3 = triangleArray.get(i+3).getNormal1();
					float ao3 = triangleArray.get(i+3).getAO1();
					positionsArray[3] = p3;
					normalsArray[3] = n3;
					occlusionArray[3] = ao3;
					//4
					SFVertex3f p4 = triangleArray.get(i+1).getVertex2();
					SFVertex3f n4 = triangleArray.get(i+1).getNormal2();
					float ao4 = triangleArray.get(i+1).getAO2();
					positionsArray[4] = p4;
					normalsArray[4] = n4;
					occlusionArray[4] = ao4;
					//5
					SFVertex3f p5 = triangleArray.get(i+2).getVertex2();
					SFVertex3f n5 = triangleArray.get(i+2).getNormal2();
					float ao5 = triangleArray.get(i+2).getAO2();
					positionsArray[5] = p5;
					normalsArray[5] = n5;
					occlusionArray[5] = ao5;
					
					for (int j=0; j<6; j++){
						
						getArray().getPrimitiveData(0).setElement(positions+j, positionsArray[j]);
						getArray().getPrimitiveData(1).setElement(normals+j, normalsArray[j]);	
						getArray().getPrimitiveData(2).setElement(datas+j, new SFValue1f(occlusionArray[j]));
						
					}
						
					int primitiveIndex = getArray().generateElement();
					int[] indices = { positions,positions+1,positions+2,positions+3,positions+4,positions+5   ,  normals,normals+1,normals+2,normals+3,normals+4,normals+5   ,    datas,datas+1,datas+2,datas+3,datas+4,datas+5 };
					SFPrimitiveIndices prIndices=new SFPrimitiveIndices();
					prIndices.setPrimitiveIndices(indices);
					getArray().setElement(primitiveIndex, prIndices);
				}
			}else if(primitive.getName().equals("Triangle3PND1")){
				
				for (int i=0; i<triangleArray.size(); i+=9){
					
					int positions = getArray().getPrimitiveData(0).generateElements(10);
					int normals = getArray().getPrimitiveData(1).generateElements(10);
					int datas = getArray().getPrimitiveData(2).generateElements(10);
						
					SFVertex3f[] positionsArray = new SFVertex3f[10];
					SFVertex3f[] normalsArray = new SFVertex3f[10];
					float[] occlusionArray = new float[10];
					//0
					SFVertex3f p0 = triangleArray.get(i+8).getVertex2();
					SFVertex3f n0 = triangleArray.get(i+8).getNormal2();
					float ao0 = triangleArray.get(i+8).getAO2();
					positionsArray[0] = p0;
					normalsArray[0] = n0;
					occlusionArray[0] = ao0;
					//1
					SFVertex3f p1 = triangleArray.get(i+2).getVertex3();
					SFVertex3f n1 = triangleArray.get(i+2).getNormal3();
					float ao1 = triangleArray.get(i+2).getAO3();
					positionsArray[1] = p1;
					normalsArray[1] = n1;
					occlusionArray[1] = ao1;
					//2
					SFVertex3f p2 = triangleArray.get(i).getVertex1();
					SFVertex3f n2 = triangleArray.get(i).getNormal1();
					float ao2 = triangleArray.get(i).getAO1();
					positionsArray[2] = p2;
					normalsArray[2] = n2;
					occlusionArray[2] = ao2;
					//3
					SFVertex3f p3 = triangleArray.get(i+8).getVertex3();
					SFVertex3f n3 = triangleArray.get(i+8).getNormal3();
					float ao3 = triangleArray.get(i+8).getAO3();
					positionsArray[3] = p3;
					normalsArray[3] = n3;
					occlusionArray[3] = ao3;
					//4
					SFVertex3f p4 = triangleArray.get(i+3).getVertex2();
					SFVertex3f n4 = triangleArray.get(i+3).getNormal2();
					float ao4 = triangleArray.get(i+3).getAO2();
					positionsArray[4] = p4;
					normalsArray[4] = n4;
					occlusionArray[4] = ao4;
					//5
					SFVertex3f p5 = triangleArray.get(i+2).getVertex1();
					SFVertex3f n5 = triangleArray.get(i+2).getNormal1();
					float ao5 = triangleArray.get(i+2).getAO1();
					positionsArray[5] = p5;
					normalsArray[5] = n5;
					occlusionArray[5] = ao5;
					//6
					SFVertex3f p6 = triangleArray.get(i).getVertex3();
					SFVertex3f n6 = triangleArray.get(i).getNormal3();
					float ao6 = triangleArray.get(i).getAO3();
					positionsArray[6] = p6;
					normalsArray[6] = n6;
					occlusionArray[6] = ao6;
					//7
					SFVertex3f p7 = triangleArray.get(i).getVertex2();
					SFVertex3f n7 = triangleArray.get(i).getNormal2();
					float ao7 = triangleArray.get(i).getAO2();
					positionsArray[7] = p7;
					normalsArray[7] = n7;
					occlusionArray[7] = ao7;
					//8
					SFVertex3f p8 = triangleArray.get(i+8).getVertex1();
					SFVertex3f n8 = triangleArray.get(i+8).getNormal1();
					float ao8 = triangleArray.get(i+8).getAO1();
					positionsArray[8] = p8;
					normalsArray[8] = n8;
					occlusionArray[8] = ao8;
					//9
					SFVertex3f p9 = triangleArray.get(i+6).getVertex1();
					SFVertex3f n9 = triangleArray.get(i+6).getNormal1();
					float ao9 = triangleArray.get(i+6).getAO1();
					positionsArray[9] = p9;
					normalsArray[9] = n9;
					occlusionArray[9] = ao9;
					
					for (int j=0; j<10; j++){
						
						getArray().getPrimitiveData(0).setElement(positions+j, positionsArray[j]);
						getArray().getPrimitiveData(1).setElement(normals+j, normalsArray[j]);	
						getArray().getPrimitiveData(2).setElement(datas+j, new SFValue1f(occlusionArray[j]));
						
					}
						
					int primitiveIndex = getArray().generateElement();
					int[] indices = { positions,positions+1,positions+2,positions+3,positions+4,positions+5,positions+6,positions+7,positions+8,positions+9
							,  normals,normals+1,normals+2,normals+3,normals+4,normals+5,normals+6,normals+7,normals+8,normals+9  
							,    datas,datas+1,datas+2,datas+3,datas+4,datas+5,datas+6,datas+7,datas+8,datas+9 };
					SFPrimitiveIndices prIndices=new SFPrimitiveIndices();
					prIndices.setPrimitiveIndices(indices);
					getArray().setElement(primitiveIndex, prIndices);
				}
			}
		}
	};
	
	return geometry;
	
}

	

public ArrayList<Triangle> sample(SFCurvedTubeFunction function, float step_u, float step_v){
	
	ArrayList<Triangle> triangleMesh = new ArrayList<Triangle>();
	
	step_u = 0.05f;
	step_v = 0.05f;
	
	for(float v=0; v<1.0f; v += step_v){
		
		for(float u=0; u<1.0f; u += step_u){
			
			SFVertex3f p1 = function.getPosition(u, v);  
			SFVertex3f p2 = function.getPosition(u, v+step_v); 
			SFVertex3f p3 = function.getPosition(u+step_u, v ); 
			
			SFVertex3f n1 = function.getNormal(u, v); 
			n1.normalize3f();
			SFVertex3f n2 = function.getNormal(u, v+step_v);
			n2.normalize3f();
			SFVertex3f n3 = function.getNormal(u+step_u, v);
			n3.normalize3f();
			
			Triangle t2 = new Triangle (p1,p2,p3,n1,n2,n3);
			t2.setAO1(getKao(function, u,v,n1));
			t2.setAO2(getKao(function, u,v+step_v,n2));
			t2.setAO3(getKao(function, u+step_u,v,n3));
			triangleMesh.add(t2);
			
			p1 = function.getPosition(u, v+step_v);  
			p2 = function.getPosition(u+step_u, v); 
			p3 = function.getPosition(u+step_u, v+step_v); 
			
	     	n1 = function.getNormal(u, v+step_v);  
	     	n1.normalize3f();
			n2 = function.getNormal(u+step_u, v); 
			n2.normalize3f();
			n3 = function.getNormal(u+step_u, v+step_v);
			n3.normalize3f();
		 
			t2 = new Triangle (p1,p2,p3,n1,n2,n3);
			t2.setAO1(getKao(function, u,v+step_v,n1));
			t2.setAO2(getKao(function, u+step_u,v,n2));
			t2.setAO3(getKao(function, u+step_u,v+step_v,n3));
			triangleMesh.add(t2);
			
		}
		
	} 
	
	
	return triangleMesh;
	
	
	
}



private static final float eps=0.01f;
private static final double g = 0.05f;


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

public float getKao(SFCurvedTubeFunction function, float u, float v, SFVertex3f normal){
	
	SFVertex3f Du2 = getDu2(function,u,v);
	SFVertex3f Dv2 = getDv2(function,u,v);
	SFVertex3f Duv = getDuv(function,u,v);
	
	float ku2 = normal.dot3f(Du2); 
	float kv2 = normal.dot3f(Dv2);
	float kuv = normal.dot3f(Duv);
	
	float k = ku2+kv2+2*kuv;
	
	if (k<0.001f){
		
		k=1;
		
	}else{
		
		k = (float)Math.exp(-g*(double)k);
		
	}
	
	return k;

}


//end AO		

	

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
