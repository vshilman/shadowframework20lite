/*
	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
    Copyright (C) 2010  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

    This file is part of The Shadow Framework.

    The Shadow Framework is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The Shadow Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
*/
package shadow.geometry.editing;

import shadow.math.SFValuenf;
import shadow.pipeline.SFArrayElementException;
import shadow.system.SFArray;

public class SFConcreteTriangleExtractor implements SFSurfaceQuadsExtractor {


	@Override
	public int getN1() {
		return 2;
	}
	
	public int getPrimitivesNumber(){
		return 2;
	}
	
	
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
	@Override
	public void extractIndices(int[][] output,SFArray<SFValuenf> array,
			SFValuenf[] data,int offset,int Nu,int Nv,int I,int J) throws SFArrayElementException{
		
		int index00=(J*2)+(I*2)*((2*Nu-1));
		int index01=index00+1;
		int index02=index00+2;
		int index10=index00+Nu*2-1;
		int index11=index10+1;
		int index12=index10+2;
		int index20=index10+Nu*2-1;
		int index21=index20+1;
		int index22=index20+2;
		
		array.setElement(offset+index00, data[index00]);
		array.setElement(offset+index20, data[index20]);
		array.setElement(offset+index02, data[index02]);
		array.setElement(offset+index22, data[index22]);
		
		SFValuenf tmpV=data[index01].cloneValue();
		tmpV.mult(2);
		tmpV.addMult(-0.5f, data[index00]);
		tmpV.addMult(-0.5f, data[index02]);
		array.setElement(offset+index01, tmpV);
		
		tmpV.set(data[index12]);
		tmpV.mult(2);
		tmpV.addMult(-0.5f, data[index02]);
		tmpV.addMult(-0.5f, data[index22]);
		array.setElement(offset+index12, tmpV);
		
		tmpV.set(data[index21]);
		tmpV.mult(2);
		tmpV.addMult(-0.5f, data[index20]);
		tmpV.addMult(-0.5f, data[index22]);
		array.setElement(offset+index21, tmpV);
		
		tmpV.set(data[index10]);
		tmpV.mult(2);
		tmpV.addMult(-0.5f, data[index00]);
		tmpV.addMult(-0.5f, data[index20]);
		array.setElement(offset+index10, tmpV);
		
		tmpV.set(data[index11]);
		tmpV.mult(2);
		tmpV.addMult(-0.5f, data[index02]);
		tmpV.addMult(-0.5f, data[index20]);
		array.setElement(offset+index11, tmpV);
		
		//setup GLIndices
		int [] tmp_S=output[0];
		tmp_S[0]=(offset+index00);
		tmp_S[1]=(offset+index20);
		tmp_S[2]=(offset+index02);
		tmp_S[3]=(offset+index10);
		tmp_S[4]=(offset+index11);
		tmp_S[5]=(offset+index01);
		tmp_S=output[1];
		tmp_S[0]=(offset+index22);
		tmp_S[1]=(offset+index20);
		tmp_S[2]=(offset+index02);
		tmp_S[3]=(offset+index21);
		tmp_S[4]=(offset+index11);
		tmp_S[5]=(offset+index12);
	}
	
	

}
