#ifndef shadow_renderer_data_utils_H_
#define shadow_renderer_data_utils_H_

#include "java/io/BufferedWriter.h"
#include "java/io/File.h"
#include "java/io/FileInputStream.h"
#include "java/io/FileNotFoundException.h"
#include "java/io/FileOutputStream.h"
#include "java/io/FileWriter.h"
#include "java/io/IOException.h"

#include "shadow/renderer/data.java.SFJavaDataExporter.h"
#include "shadow/renderer/data.java.SFXMLDataExporter.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataCenter.h"
#include "shadow/system/data.SFDataObject.h"
#include "shadow/system/data.SFDataset.h"
#include "shadow/system/data.SFInputStream.h"
#include "shadow/system/data.SFObjectsLibrary.h"
#include "shadow/system/data.java.SFCompilerInputStream.h"
#include "shadow/system/data.java.SFCompilerOutputStream.h"
#include "shadow/system/data.java.SFInputStreamJava.h"
#include "shadow/system/data.java.SFOutputStreamJava.h"

class SFDataUtility {

	static void saveDataset(String root,String name,SFDataset dataset){

		String library=root+"/"+name;

		saveDataset(dataset, library);
	}


	static void saveDataset(SFDataset dataset, String library) {
		//write it
		try {

			FileOutputStream output=new FileOutputStream(library);

			SFOutputStreamJava outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());

			//outputStream.writeString(dataset.getCode());
			SFDataCenter.getDataCenter().writeDataset(outputStream, dataset);
			output.close();

		}
			e.printStackTrace();
		}
			e.printStackTrace();
		}
	}

	static void compileDataset(SFDataset dataset, String library) {
		//write it
		try {

			FileOutputStream output=new FileOutputStream(library);

			SFCompilerOutputStream outputStream=new SFCompilerOutputStream(new SFOutputStreamJava(output, new DefaultExceptionKeeper()));

			//outputStream.writeString(dataset.getCode());
			SFDataCenter.getDataCenter().writeDataset(outputStream, dataset);
			output.close();

		}
			e.printStackTrace();
		}
			e.printStackTrace();
		}
	}

	static SFDataset loadDataset(String root,String name){

		return loadDataset(root+"/"+name);

	}


	static SFDataset loadDataset(String name){

		//read it
		try {

			FileInputStream input=new FileInputStream(name);
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			SFDataset dataset=SFDataCenter.getDataCenter().readDataset(inputStream);
			input.close();
			return dataset;

		}
			e.printStackTrace();
		}
			e.printStackTrace();
		}

		return null;
	}

	static void loadDataset(String name,SFDataset dataset){

		//read it
		try {

			FileInputStream input=new FileInputStream(name);
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());

//				//inputStream.readInt();//skip type loading
				inputStream.readString();//skip type loading
				dataset.getSFDataObject().readFromStream(inputStream);

			input.close();

		}
			e.printStackTrace();
		}
			e.printStackTrace();
		}

	}

	static void loadCompiledDataset(String name,SFDataset dataset){

		//read it
		try {

			FileInputStream input=new FileInputStream(name);
			SFCompilerInputStream inputStream=new SFCompilerInputStream(new SFInputStreamJava(input, new DefaultExceptionKeeper()));

			inputStream.readInt();//skip type loading
		//	inputStream.readString();//skip type loading
			dataset.getSFDataObject().readFromStream(inputStream);

			input.close();

		}
			e.printStackTrace();
		}
			e.printStackTrace();
		}

	}


	static void saveDataObject(String root,String name,SFDataObject data){
		//write it
		try {
			FileOutputStream output=new FileOutputStream(root+"/"+name);
			SFOutputStreamJava outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());

			data.writeOnStream(outputStream);
			output.close();

		}
			e.printStackTrace();
		}
			e.printStackTrace();
		}
	}


	static void loadDataset(String root,String name,SFDataObject data){

		//read it
		try {
			FileInputStream input=new FileInputStream(root+"/"+name);
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());

			//String assetName=inputStream.readString();
			data.readFromStream(inputStream);

			input.close();

		}
			e.printStackTrace();
		}
			e.printStackTrace();
		}

	}

	static void saveXMLFile(String root, String filename, String name,SFDataObject dataObject){

		try {
			SFXMLDataExporter exporter=new SFXMLDataExporter();
			(new SFJavaDataExporter(exporter)).evaluateDataObject(name,dataObject);
			BufferedWriter writer=new BufferedWriter(new FileWriter(new File(
					root+"/"+filename+".xml")));
			writer.write(exporter.getText());
			writer.close();
		}
			e.printStackTrace();
		}

	}

	static void saveXMLFile(String root, String filename, SFDataAsset<?> asset){

		try {
			SFXMLDataExporter exporter=new SFXMLDataExporter();
			(new SFJavaDataExporter(exporter)).writeAsset(asset);
			BufferedWriter writer=new BufferedWriter(new FileWriter(new File(
					root+"/"+filename+".xml")));
			writer.write(exporter.getText());
			writer.close();
		}
			e.printStackTrace();
		}

	}


	static void saveXMLFile(String root, String filename, SFObjectsLibrary library){

		try {
			SFXMLDataExporter exporter=new SFXMLDataExporter();
			(new SFJavaDataExporter(exporter)).writeLibrary(library);
			BufferedWriter writer=new BufferedWriter(new FileWriter(new File(
					root+"/"+filename+".xml")));
			writer.write(exporter.getText());
			writer.close();
		}
			e.printStackTrace();
		}

	}
}
;
}
#endif
