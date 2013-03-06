package CoordinatesTrasformations;

import CoordinatesTrasformations.Angle2Rad;

public class OperationMatrix {

	public static float[] identity(){
		
		float[]  identity={1,0,0, 
							0,1,0,	
							0,0,1};
							
		
		return identity; 
	}
	
	public static float[] scale(float Sx, float Sy, float Sz){
		
		float[]  scale={Sx,0,0, 
							0,Sy,0,	
							0,0,Sz,};
							
		
		return scale; 
	}
	
	public static float[] getRotationX (float alfa){
				
		float[]  rotX={1,0,0, 
				0,Angle2Rad.cos(alfa),-Angle2Rad.sin(alfa),	
				0,Angle2Rad.sin(alfa),Angle2Rad.cos(alfa)};
							
		return rotX; 
	}
	
	public static float[] getRotationY (float alfa){
		
		float[]  rotY={Angle2Rad.cos(alfa),0,Angle2Rad.sin(alfa),  
				0,1,0,
				-Angle2Rad.sin(alfa),0,Angle2Rad.cos(alfa)};
							
		return rotY; 
	}
	
	public static float[] getRotationZ (float alfa){
		
		float[]  rotZ={Angle2Rad.cos(alfa),Angle2Rad.sin(alfa),0,
							-Angle2Rad.sin(alfa),Angle2Rad.cos(alfa),0,	
							0,0,1};
							
		return rotZ;
	}
	
	public static float[] add (float[] a, float[] b){
		float[] add = {
				a[0]+b[0],a[1]+b[1],a[2]+b[2],
				a[3]+b[3],a[4]+b[4],a[5]+b[5],
				a[6]+b[6],a[7]+b[7],a[8]+b[8]};
		return add;
		
	}
	
	public static float[] multCoeff (float[] a, float b){
		float[] mC = {
				a[0]*b,a[1]*b,a[2]*b,
				a[3]*b,a[4]*b,a[5]*b,
				a[6]*b,a[7]*b,a[8]*b};
		return mC;
		
	}
	public static float[] mult (float[] a, float[] b){
		float[] m = {1,0,0,0,1,0,0,0,1};
		m[0]=a[0]*b[0] + a[1]*b[3] + a[2]*b[6];
		m[1]=a[0]*b[1] + a[1]*b[4] + a[2]*b[7];
		m[2]=a[0]*b[2] + a[1]*b[5] + a[2]*b[8];
		
		m[3]=a[3]*b[0] + a[4]*b[3] + a[5]*b[6];
		m[4]=a[3]*b[1] + a[4]*b[4] + a[5]*b[7];
		m[5]=a[3]*b[2] + a[4]*b[5] + a[5]*b[8];
		
		m[6]=a[6]*b[0] + a[7]*b[3] + a[8]*b[6];
		m[7]=a[6]*b[1] + a[7]*b[4] + a[8]*b[7];
		m[8]=a[6]*b[2] + a[7]*b[5] + a[8]*b[8];
				
		return m;
		
	}
	
		/*
		 * calcolo matrice di proiezione per vedere mondo da punto di vista della luce
		 * 1. calcolo di 3 matrici differenti
		 * 		a.rescale: per posizionarmi alla stassa distanza che ha la luce
		 * 		b.firstRotation: ruoto intorno a Y per posizionarmi in asse con luce
		 * 		c.secondRotation: ruoto intorno al nuovo asseX, che è il vettore D=(cosA,sinA,0)
		 * 							per questa operazione ho bisogno di una matrice ausiliaria chiamata C
		 * 
		 * 2. calcolo della projection della luce come prodotto delle tre matrici precedenti nel seguente ordine
		 * 	c*b*a, dato che a è la prima che deve essere fatta
		 */
		
	public static float[] POVLight(float xL,float yL, float zL){
		float scale = (float)(Math.sqrt(xL*xL+yL*yL+zL*zL));
		float[] rescale ={scale,0,0,
						0,scale,0,	
						0,0,scale};
		
		double alpha= Math.atan(zL/xL);
		float cosA=(float)(Math.cos(alpha));
		float sinA=(float)(Math.sin(alpha));
		float[] firstRotation ={cosA,0,sinA,
								0,1,0,	
								-sinA,0,cosA};
		
		double beta= Math.atan(yL/xL);
		float cosB=(float)(Math.cos(beta));
		float sinB=(float)(Math.sin(beta));
		float[] C={0,0,sinA,
					0,0,-sinA,
					-sinA,cosA,0};
		float[] I={1,0,0,
					0,1,0,
					0,0,1};
		
		float[]C2=mult(C,C);
		float[]tmp1=multCoeff(C2,(1-cosB));
		float[]tmp2=multCoeff(C,sinB);
		float[]tmp3=add(identity(),tmp2);
		float[]secondRotation=add(tmp3,tmp1);		
		
		float[]tmp=mult(firstRotation,rescale);
		float[] POVL=mult(secondRotation,tmp);
		return POVL;
	}
}