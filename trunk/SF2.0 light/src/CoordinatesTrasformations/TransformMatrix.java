package CoordinatesTrasformations;

import CoordinatesTrasformations.OperationMatrix;

public class TransformMatrix {
	
	private static float[] getTransform (float[] matrix){
		float[] transform = {matrix[0],matrix[1],matrix[2],
							matrix[3],matrix[4],matrix[5],
							matrix[6],matrix[7],matrix[8],
							0,0,0};
		
		return transform;
	}
	
	public static float[] identity(){
		
		float[]  p=OperationMatrix.identity();
		float[] transform = getTransform(p);
		return transform; 
	}
	
	public static float[] scale(float Sx, float Sy, float Sz){
		
		float[] s=OperationMatrix.scale(Sx,Sy,Sz);
		float[] scale=getTransform(s);
		return scale; 
	}
	public static float[] getRotationX (float alfa){
		
		float[] rX=OperationMatrix.getRotationX(alfa);
		float[] rotationX=getTransform(rX);
		return rotationX; 
		
	}
	
	public static float[] getRotationY (float alfa){
		
		float[] rY=OperationMatrix.getRotationY(alfa);
		float[] rotationY=getTransform(rY);
		return rotationY; 
		
	}

	public static float[] getRotationZ (float alfa){
	
	float[] rZ=OperationMatrix.getRotationZ(alfa);
	float[] rotationZ=getTransform(rZ);
	return rotationZ; 
	
	}
	
	public static float[] getPOVLight (float xL,float yL, float zL){
		
		float[] pov=OperationMatrix.POVLight(xL,yL,zL);
		float[] POVL=getTransform(pov);
		return POVL;
	}
	
}