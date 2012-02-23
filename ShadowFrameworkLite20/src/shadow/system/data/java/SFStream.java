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
package shadow.system.data.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import shadow.system.data.SFDataset;


/**
 * GLStream is a general use class to write on or read from a stream
 * differenti kind of data. It even keep the methods to write/read
 * GLDatasets headers on the streams
 * 
 * @author Alessandro Martinelli
 */
public class SFStream {

	public InputStreamReader loadFileInputStreamReader(String filename) throws IOException{
		return new InputStreamReader(new FileInputStream(new File(filename)));
	}
	
	public void loadDataset(SFDataset dataset,String filename){
		try {
			FileInputStream stream=new FileInputStream(new File(filename));
			SFInputStreamJava inStream=new SFInputStreamJava(stream,new SFIOExceptionKeeper() {
				@Override
				public void launch(IOException exception) {
					exception.printStackTrace();
				}
			});
			//The return of this operation is not used here
				stream.skip(2);
				//GLStream.readShort(stream);
			dataset.getSFDataObject().readFromStream(inStream);
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeDataset(SFDataset dataset,String filename){
		try {
			FileOutputStream stream1=new FileOutputStream(new File(filename));
			SFOutputStreamJava outStream=new SFOutputStreamJava(stream1, new SFIOExceptionKeeper() {
				@Override
				public void launch(IOException exception) {
					exception.printStackTrace();
				}
			});
			dataset.getSFDataObject().writeOnStream(outStream);
			stream1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
