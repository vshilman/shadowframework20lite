#ifndef shadow_renderer_data_java_H_
#define shadow_renderer_data_java_H_

#include "java/io/StringWriter.h"

#include "shadow/system/SFException.h"

class SFXMLDataExporter implements SFIDataExporter{

	StringWriter writer;	
	int tabs=0;

	SFXMLDataExporter() {
		super();
		this->writer = new StringWriter();
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	}

	String tabs(){
		char[] ts=new char[tabs];
		for (int i = 0; i < tabs; i++) {
			ts[i]='\t';
		}
		return new String(ts);
	}

	
	void reportError(String name, String moduleName) {
		throw new SFException("SFJavaDataExporter Error on "+name+" with type "+moduleName);
	}

	
	void insertElement(String name, String info) {
		writer.write(tabs()+"<"+name+">"+info+"</"+name+">\n");
		//System.out.println(tabs()+name+" = "+info);
	}
	
	void startLibraryReferenceList(String name) {
		writer.write(tabs()+"<references id=\""+name+"\">\n");
		tabs++;
	}
	
	void endLibraryReferenceList() {
		tabs--;
		writer.write(tabs()+"</references>\n");
	}
	
	void startAssetEvaluation(String type, String name) {
		writer.write(tabs()+"<"+type+" name=\""+name+"\">\n");
		//System.out.println(tabs()+name+"(asset:"+type+") { ");
		tabs++;
	}
	
	void endAssetEvaluation(String type) {
		tabs--;
		writer.write(tabs()+"</"+type+">\n");
		}
	}

	
	void endListEvaluation(String name) {
		tabs--;
		writer.write(tabs()+"</"+name+">\n");
	}

	
	void startListEvaluation(String name) {
		writer.write(tabs()+"<"+name+">\n");
		tabs++;
	}

	String getText(){
		return writer.toString();
	}
}
;
}
#endif
