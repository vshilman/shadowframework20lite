#ifndef shadow_renderer_data_utils_H_
#define shadow_renderer_data_utils_H_

#include "java/io/InputStream.h"

#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataCenter.h"
#include "shadow/system/data.SFDataset.h"
#include "shadow/system/data.SFIDataCenter.h"
#include "shadow/system/data.SFObjectsLibrary.h"
#include "shadow/system/data.java.SFInputStreamJava.h"

class SFStandardObjectsLibrary implements SFIDataCenter{

	SFObjectsLibrary library;

	String libraryname;



	SFStandardObjectsLibrary(String root,String library){
		this->library=(SFObjectsLibrary)SFDataUtility.loadDataset(root);
		this->libraryname=root+"/"+library;
	}

	SFStandardObjectsLibrary(String library){
		this->library=new SFObjectsLibrary();
		loadLibrary(library);
	}

	void loadLibrary(String library) {
		this->library=new SFObjectsLibrary();
		SFDataUtility.loadDataset( library,this->library);
		this->libraryname=library;
	}


	void loadCompiledLibrary(String library) {
		this->library=new SFObjectsLibrary();
		SFDataUtility.loadCompiledDataset( library,this->library);
		this->libraryname=library;
	}

	String getLibraryname() {
		return libraryname;
	}

	
	SFStandardObjectsLibrary clone() {
		SFStandardObjectsLibrary library=new SFStandardObjectsLibrary();
		library.libraryname=this->libraryname;
		library.library=this->library;
		return library;
	}

	void setLibraryname(String libraryname) {
		this->libraryname = libraryname;
	}

	void compile(){
		SFDataUtility.compileDataset(library,libraryname);
	}

	void save(){
		SFDataUtility.saveDataset(library,libraryname);
	}

	SFStandardObjectsLibrary(InputStream stream){
		try {
			SFDataset dataset=SFDataCenter.getDataCenter().readDataset(
					new SFInputStreamJava(stream, new DefaultExceptionKeeper()));
			this->library=(SFObjectsLibrary)(dataset);
		}
			e.printStackTrace();
		}
	}

	SFStandardObjectsLibrary(){
		this->library=new SFObjectsLibrary();
	}

	SFObjectsLibrary.SFLibraryRecord getRecord(String name){
		return this->library.getRecord(name);
	}

	boolremoveRecord(String name){
		return this->library.removeRecord(name);
	}


	
	SFDataAsset<?> makeDatasetAvailable(String name) {
		SFDataset dataset=library.retrieveDataset(name);
		return (SFDataAsset<?>)dataset;
	}

	SFObjectsLibrary getLibrary() {
		return library;
	}

	void addLibrary(SFStandardObjectsLibrary library) {
		this->library.addLibrary(library.getLibrary());
	}

}
;
}
#endif
