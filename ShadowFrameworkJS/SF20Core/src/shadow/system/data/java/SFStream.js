
function SFStream(){
}

SFStream.prototype = {

	loadFileInputStreamReader:function(filename){
		return ,new ,InputStreamReader(new FileInputStream(new File(filename)));
	},

	loadDataset:function(dataset, filename){
		try{
		 FileInputStream  stream = new  FileInputStream(new ,File(filename));
	//SFInputStreamJava inStream=new SFInputStreamJava(stream,new SFIOExceptionKeeper();//Warning: Not well Identified 
	//@Override				public void launch(IOException exception);//Warning: Not well Identified 
		exception.printStackTrace();
	//}
	//}
	);//Warning: Not well Identified 
	//The return of this operation is not used here				stream.skip(2);//Warning: Not well Identified 
	//GLStream.readShort(stream);//Warning: Not well Identified 
		dataset.readFromStream(inStream);
		stream.close();
	}
		catch (FileNotFoundException e){
		e.printStackTrace();
	}
		catch (IOException e){
		e.printStackTrace();
	}
	},

	writeDataset:function(dataset, filename){
		try{
		 FileOutputStream  stream1 = new  FileOutputStream(new ,File(filename));
	//SFOutputStreamJava outStream=new SFOutputStreamJava(stream1, new SFIOExceptionKeeper();//Warning: Not well Identified 
	//@Override				public void launch(IOException exception);//Warning: Not well Identified 
		exception.printStackTrace();
	//}
	//}
	);//Warning: Not well Identified 
		outStream.writeHeader(dataset);
		dataset.writeOnStream(outStream);
		stream1.close();
	}
		catch (FileNotFoundException e){
		e.printStackTrace();
	}
		catch (IOException e){
		e.printStackTrace();
	}
	}

};