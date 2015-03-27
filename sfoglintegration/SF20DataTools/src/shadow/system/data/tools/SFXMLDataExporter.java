package shadow.system.data.tools;

import java.io.StringWriter;

import shadow.system.SFException;

public class SFXMLDataExporter implements SFIDataExporter{

	private StringWriter writer;	
	private int tabs=0;
	
	public SFXMLDataExporter() {
		super();
		this.writer = new StringWriter();
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	}

	private String tabs(){
		char[] ts=new char[tabs];
		for (int i = 0; i < tabs; i++) {
			ts[i]='\t';
		}
		return new String(ts);
	}

	@Override
	public void reportError(String name, String moduleName) {
		throw new SFException("SFJavaDataExporter Error on "+name+" with type "+moduleName);
	}
	
	@Override
	public void insertElement(String name, String info) {
		writer.write(tabs()+"<"+name+">"+info+"</"+name+">\n");
		//System.out.println(tabs()+name+" = "+info);
	}
	@Override
	public void startLibraryReferenceList(String name) {
		writer.write(tabs()+"<references id=\""+name+"\">\n");
		tabs++;
	}
	@Override
	public void endLibraryReferenceList() {
		tabs--;
		writer.write(tabs()+"</references>\n");
	}
	@Override
	public void startAssetEvaluation(String type, String name) {
		writer.write(tabs()+"<"+type+" name=\""+name+"\">\n");
		//System.out.println(tabs()+name+"(asset:"+type+") { ");
		tabs++;
	}
	@Override
	public void endAssetEvaluation(String type) {
		tabs--;
		writer.write(tabs()+"</"+type+">\n");
		//System.out.println(tabs()+"} ");
	}
	
	@Override
	public void endListEvaluation(String name) {
		tabs--;
		writer.write(tabs()+"</"+name+">\n");
	}
	
	@Override
	public void startListEvaluation(String name) {
		writer.write(tabs()+"<"+name+">\n");
		tabs++;
	}
	
	public String getText(){
		return writer.toString();
	}
}
