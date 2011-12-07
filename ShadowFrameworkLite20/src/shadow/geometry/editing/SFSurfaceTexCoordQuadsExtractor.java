package shadow.geometry.editing;

import shadow.math.SFVertex2f;
import shadow.system.SFArray;


public interface SFSurfaceTexCoordQuadsExtractor {

	public int getN1();
	public int getPrimitivesNumber();
	
	/**
	 * 
	 * @param array the array of Vertex3f which is updated
	 * @param data	the array of Vertex3f which is the input and represent some specific position of the model 
	 * @param offset the offset where the vertices of the client geometry are positioned in the array
	 * @param N  The sundivision level of the client geometry 
	 * @param I  The first index of the client geometry tessel
	 * @param J  The second index of the client geometry tessel
	 * @return an array of indices which describes the number of primitives which have been generated
	 */
	/*  	- Genera un certo numero di GLIndices da aggiungere all'elenco di indici.
	 *      - aggirna un certo numero di valori nell'array
	 *  array.setVertex(offset + index,data[index]) //if array[index]==data[index]
	 *  array.setVertex(offset + index,x,y,z)		//if array[index]comes from elaboration
	 *  
	 *  GLindices indices;
	 *  indices[k]=offset+index;
	 *  
	 *  index :		0<i<=n1 0<j<=n1  : for all the indices used 
	 *  			index =  (J*N1 + j) + (I*N1+i)*(N*N1); 
	 */
	public void extractIndices(short[][] output,SFArray<SFVertex2f> array,
			SFVertex2f[] data,int offset,int Nu,int Nv,int I,int J);
}
