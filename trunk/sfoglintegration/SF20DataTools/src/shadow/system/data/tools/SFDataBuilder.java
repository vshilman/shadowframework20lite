package shadow.system.data.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

import shadow.system.SFException;
import shadow.system.data.SFCharsetObject;
import shadow.system.data.SFCharsetObjectList;
import shadow.system.data.SFDataAsset;
import shadow.system.data.SFDataAssetList;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFLibraryReference;
import shadow.system.data.SFLibraryReferenceList;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.SFParameterObject;
import shadow.system.data.SFTemplateDataAsset;
import shadow.system.data.objects.SFBinaryObject;
import shadow.system.data.objects.SFDataAssetObject;

/* Actually .... this is the worst class ever!!!
 * even worst than {@link SFAssetDecoder}
 */
public class SFDataBuilder {

	private static final String templateCodeStart = "TEMPLATE";
	private static final String templateCodeEnd = "END";
	private static final String templateCodeCall = "CALL";
	private static final String templateCodeReplace = "REPLACE";
	private static final String templateCodeWith = "WITH";
	private static final String templateCodeImport = "import";
	private static final String codeOverride = "Override";
	private static final String commentCode="//";
	
	private LinkedList<Object> buildingElements=new LinkedList<Object>();
	
	private SFObjectsLibrary library;
	private String filename;
	
	private HashMap<String, String> replacements=new HashMap<String, String>();
	private HashMap<String, ArrayList<String>> codeTemplate = new HashMap<String, ArrayList<String>>();
	private  String onTemplate = null;
	
	private ArrayList<String> alreadyImportedFiles=new ArrayList<String>();
	
	public void loadBuilderData(String filename,SFObjectsLibrary library){
		
		this.library=library;
		this.filename=filename;
		loadBuilderData(filename);
		
	}



	private void loadBuilderData(String filename) {
		try {
			BufferedReader reader=new BufferedReader(new FileReader(new File(filename)));
			String line=reader.readLine();
			
			while(line!=null){

				String[] splits=line.split("[\\\\{}]");
				//String[] splits=line.split("\\\\");
				
				for (int i = 0; i < splits.length; i++) {
					
					String[] compiledLines=compileLine(splits[i]);
					
					for (int j = 0; j < compiledLines.length; j++) {
						manageLine(compiledLines[j]);
						//System.err.println(compiledLines[j]);
					}
						
				}
				
				line=reader.readLine();
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();//TODO : not this way...
		} catch (IOException e) {
			e.printStackTrace();//TODO : not this way...
		}
	}
	
	
	
	private String[] compileLine(String line){
	
		String tempLine=line;
		line=line.trim();
		String message="";
		if(line.startsWith(templateCodeImport)){
			int indexOfComma=line.indexOf(';');
			line=line.substring(templateCodeImport.length()+1,indexOfComma).trim();
			
			int indexOfSlash=filename.lastIndexOf('/');
			
			String importFileName=filename.substring(0,indexOfSlash)+"/"+line+".sfb";
			//System.err.println(importFileName);
			//Import only files not already imported!
			if(!alreadyImportedFiles.contains(importFileName)){
				loadBuilderData(importFileName);
				alreadyImportedFiles.add(importFileName);
			}
				
			
		}else if(line.startsWith(templateCodeStart)){
			line= line.substring(templateCodeStart.length()+1).trim();
			codeTemplate.put(line,new ArrayList<String>());
			onTemplate=line;
		}else if(line.startsWith(templateCodeEnd)){
			onTemplate=null;
		}else if(onTemplate!=null){
			codeTemplate.get(onTemplate).add(tempLine);
		}else if(line.startsWith(templateCodeReplace)){
			line= line.substring(templateCodeReplace.length()+1).trim();
			String name = line.substring(0,line.indexOf(templateCodeWith)).trim();
			String value = line.substring(line.indexOf(templateCodeWith)+templateCodeWith.length()).trim();
			replacements.put(name, value);
		}else if(line.startsWith(templateCodeCall)){
			line= line.substring(templateCodeCall.length()+1).trim();
			ArrayList<String> template=codeTemplate.get(line);
			String[] code=new String[template.size()];
				for (int i = 0; i < code.length; i++) {
					String lineCode=template.get(i);
					for (String key:replacements.keySet()) {
						if(lineCode.contains(key)){
							lineCode=lineCode.replace(key, replacements.get(key));
						}
					}
					code[i]=lineCode;
				}
			return code;
		}else{
			message=tempLine;
		}
		
		String[] compiled={message};
		return compiled;
	}


	private void addElementData(String line){
		Object element=buildingElements.getLast();
		if(element instanceof SFCharsetObjectList){
			SFCharsetObjectList list=(SFCharsetObjectList)element;
			StringTokenizer tok=new StringTokenizer(line," ");
			ArrayList<String> elements=new ArrayList<String>();
			elements.add(tok.nextToken());
			String[] values=elements.toArray(new String[elements.size()]);
			list.setStringValues(values);
		}
		
	}
	
	/*
	 * Put an element in the library
	 */
	private void storeElement(String name,SFDataAsset<?> dataset){
		library.put(name, dataset);
	}
	
	@SuppressWarnings("all")
	private void manageLine(String line) {
		//System.err.println("Building LINE "+line);
		line=line.trim();
		if(line.startsWith(commentCode) || line.length()==0){
			return;
		}
		
		if(line.trim().startsWith(codeOverride)){
			String name=line.trim().substring(codeOverride.length()).trim();
			
			SFDataAsset<?> dataset=library.retrieveDataset(name);
			
			buildingElements.add(dataset);
			
			return;
		}
		
		int indexOfAs=line.indexOf(" as ");
		int size=2;
		if(indexOfAs<0){
			indexOfAs=line.indexOf("=");
			size=1;
			if(line.lastIndexOf("=")!=indexOfAs){
				size=2;
			}
			int indexOfPlus=line.indexOf("+=");
			if(indexOfPlus>=0){
				indexOfAs=indexOfPlus;
				size=2;
			}
		}else{
			indexOfAs++;
		}
		//int indexOfLike=line.indexOf(" like ");
		
		if(indexOfAs>0){//data creation
			
			String elementName=line.substring(0,indexOfAs).trim();
			String elementType=line.substring(indexOfAs+size).trim();
			
			//System.err.println("\t\t "+buildingElements);

			//System.err.println("line :{"+line+"} "+buildingElements);
			if(buildingElements.size()==0){
				
				SFDataAsset<?> dataset=createDataset(elementType,elementName);
				
				buildingElements.add(dataset);
				
				storeElement(elementName,dataset);
				
				if(((SFDataAsset<?>)dataset).getResourceGenerator() instanceof SFTemplateDataAsset){
					SFNamedParametersObject parameters=((SFDataAsset<?>)dataset).getDataObject(1);
					SFNamedParametersObject.setParametersObject(parameters);
					//so parameters are stored in the correct Data Object
				}

			}else{
				
				SFDataObject dataObject=null;
				
				while(dataObject==null && buildingElements.size()>0){
				
					if(buildingElements.getLast() instanceof SFDataAsset<?>){
						SFDataAsset<?> dataAsset=(SFDataAsset<?>)buildingElements.getLast();
						try {
							if(line.contains("+=")){
								dataObject=dataAsset.getObject(elementName);	
							}else{
								if(elementType.startsWith("$")){
									//dataObject=dataAsset.getSFDataObject().getNewValueObject(elementName);
									int indexOf=elementType.indexOf(':');
									String parameterName=elementType.substring(1, indexOf);
									String defaultValue=elementType.substring(indexOf+1);
									SFNamedParametersObject staticObject=SFNamedParametersObject.getParametersObject();
									int sParametersSize=staticObject.getIndex(parameterName);
									if(sParametersSize==-1){//
										sParametersSize=staticObject.getSize();
										dataObject=dataAsset.getNewParameterReference(parameterName,elementName,sParametersSize);
										staticObject.addObject(parameterName, dataObject);	
									}else{
										dataObject=dataAsset.getNewParameterReference(parameterName,elementName,sParametersSize);
									}
									//System.err.println("elementType+"+elementType);
									elementType=defaultValue;
								}else{
									dataObject=dataAsset.getNewValueObject(elementName);	
								}		
							}
						} catch (Exception e) {
							//e.printStackTrace();
							dataObject=null;
						}
						
					}else if(buildingElements.getLast() instanceof SFNamedParametersObject){
						try {
							dataObject=((SFNamedParametersObject)buildingElements.getLast()).getNewValueObject(elementName);
						} catch (Exception e) {
							//e.printStackTrace();
							dataObject=null;
						}
					}
					
					if(dataObject!=null){
						
						if(dataObject instanceof SFLibraryReference<?>){
							
							if(line.contains("==")){
								((SFLibraryReference<?>)dataObject).setReference(elementType);
							}else{
								SFDataAsset<?> dataset=(SFDataAsset<?>)createDataset(elementType,null);
								((SFLibraryReference<?>)dataObject).setDataset((SFDataAsset)dataset);
								((SFLibraryReference<?>)dataObject).setReference(SFLibraryReference.NULL_REFERENCE);
								buildingElements.add(dataset);	
							}
						
						}else if(dataObject instanceof SFNamedParametersObject){
							buildingElements.add(dataObject);
						}else if(dataObject instanceof SFParameterObject){
							SFNamedParametersObject staticObject=SFNamedParametersObject.getParametersObject();
							SFDataObject dataObject2 = staticObject.getNewValueObject(elementName);
							((SFCharsetObject)dataObject2).setStringValue(elementType);
						}else if(dataObject instanceof SFDataAssetObject<?>){
							SFDataAsset<?> dataset=(SFDataAsset<?>)createDataset(elementType,null);
							((SFDataAssetObject)dataObject).setDataAsset((SFDataAsset)dataset);
							buildingElements.add(dataset);
						}else if(dataObject instanceof SFLibraryReferenceList<?>){
							if(line.contains("==")){
								String[] splits=elementType.split(",");
								for (int i = 0; i < splits.length; i++) {
									((SFLibraryReferenceList<?>)dataObject).add(splits[i].trim());
								}
							}else if(line.contains("+=")){
								SFDataAsset<?> dataset=(SFDataAsset<?>)createDataset(elementType,null);
								((SFLibraryReferenceList<?>)dataObject).add((SFDataAsset)dataset);
								
								buildingElements.add(dataset);
							}else{
								SFDataAsset<?> dataset=(SFDataAsset<?>)createDataset(elementType,null);
								((SFLibraryReferenceList<?>)dataObject).add((SFDataAsset)dataset);
								buildingElements.add(dataset);
							}
							
						}else if(dataObject instanceof SFDataAssetList){
							SFDataAsset<?> dataset=(SFDataAsset<?>)createDataset(elementType,null);
							((SFDataAssetList<?>)dataObject).add((SFDataAsset)dataset);
							
							buildingElements.add(dataset);
						}else if(dataObject instanceof SFCharsetObjectList){
							buildingElements.add(dataObject);
							addElementData(elementType);
						}else if(dataObject instanceof SFCharsetObject){
							((SFCharsetObject)dataObject).setStringValue(elementType);
						}else if(dataObject instanceof SFBinaryObject<?>){
							((SFBinaryObject<?>)dataObject).getBinaryValue().setStringValue(elementType);
						}else{
							throw new SFException("\t\t cannot read "+elementName+" "+elementType+" "+dataObject);
						}
						
					}else{
						//System.err.println("Removing element");
						buildingElements.removeLast();	
					}
					
				}
				if(buildingElements.size()==0){
					manageLine(line);
				}
			}
			
		}else{//data adding
			addElementData(line);
		}
	}


	private SFDataAsset<?> createDataset(String elementType,String name) {
		return SFDataCenter.getDataCenter().getDictionary().createDataset(elementType,name);
	}
}
