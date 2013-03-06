package CoordinatesTrasformations;

import CoordinatesTrasformations.OperationMatrix;

public class ProjectionMatrix {
	
	private static float[] getProjection (float[] matrix){
		float[] projection = {matrix[0],matrix[1],matrix[2],0,
							matrix[3],matrix[4],matrix[5],0,
							matrix[6],matrix[7],matrix[8],0,
							0,0,0,1};
		
		return projection;
	}
	
	public static float[] identity(){
		
		float[]  p=OperationMatrix.identity();
		float[] projection = getProjection(p);
		return projection; 
	}
	
	public static float[] scale(float Sx, float Sy, float Sz){
		
		float[] s=OperationMatrix.scale(Sx,Sy,Sz);
		float[] scale=getProjection(s);
		return scale; 
	}
	public static float[] getRotationX (float alfa){
		
		float[] rX=OperationMatrix.getRotationX(alfa);
		float[] rotationX=getProjection(rX);
		return rotationX; 
		
	}
	
	public static float[] getRotationY (float alfa){
		
		float[] rY=OperationMatrix.getRotationY(alfa);
		float[] rotationY=getProjection(rY);
		return rotationY; 
		
	}

	public static float[] getRotationZ (float alfa){
	
	float[] rZ=OperationMatrix.getRotationZ(alfa);
	float[] rotationZ=getProjection(rZ);
	return rotationZ; 
	
	}
	
	public static float[] getPOVLight (float xL,float yL, float zL){
		
		float[] pov=OperationMatrix.POVLight(xL,yL,zL);
		float[] POVL=getProjection(pov);
		return POVL;
	}
}