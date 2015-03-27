package shadow.system.data.tools;

import java.io.File;
import java.util.HashMap;

import shadow.system.data.SFLibrary;
import shadow.system.data.SFObjectsLibrary;


/**
 * A Tool used to decide where libraries will reside
 * 
 * @author Alessandro Martinelli
 */
public class SFLibraryTool {
	
	public static final String loadFileName="loadfile";
	
	private String root;
	
	private HashMap<SFObjectsLibrary, String> libraryNames=new HashMap<SFObjectsLibrary, String>(); 
	
	public SFLibraryTool(String root) {
		super();
		this.setRoot(root);
	}

	/**
	 * Remove all Libraries from root position
	 */
	public void clearLibraries(){

		File f=new File(root);
		
		File[] libs=f.listFiles();
		
		for (int i = 0; i < libs.length; i++) {
			String name=libs[i].getAbsolutePath();
			if(name.endsWith(".sf")){
				libs[i].delete();
			}
		}
	}

	
	private interface LibraryManager{
		public void loadLibrary(SFLibrary library,String libraryFilename);
	}

//	public SFCompositeDataCenter<SFObjectsLibrary> loadLibraries(){
//		return loadLibraries(getRoot());
//	}
//	
//	private SFCompositeDataCenter<SFObjectsLibrary> loadLibraries(String root){
//		return loadLibraries(root, new LibraryManager() {
//			@Override
//			public void loadLibrary(SFObjectsLibrary library,
//					String libraryFilename) {
//				SFLibraryTool.this.loadLibrary(library, libraryFilename);
//			}
//		});
//	}
//	
//	public String getLibraryName(SFObjectsLibrary library){
//		return libraryNames.get(library);
//	}
//	
//	public void setLibraryName(SFObjectsLibrary library,String libraryName){
//		libraryNames.put(library,libraryName);
//	}
//
//	public SFCompositeDataCenter<SFObjectsLibrary> loadCompiledCommonLibraries(){
//		return loadCompiledLibraries(getRoot());
//	}
//	
//	public void loadLibrary(SFObjectsLibrary library,
//			String libraryFilename) {
//		//library.loadLibrary(libraryFilename);
//		SFDataUtility.loadDataset( libraryFilename,library.getLibrary());
//		libraryNames.put(library,libraryFilename);
//		//this.libraryname=library;
//	}
//	
//	private SFCompositeDataCenter<SFObjectsLibrary> loadCompiledLibraries(String root){
//		return loadLibraries(root, new LibraryManager() {
//			@Override
//			public void loadLibrary(SFObjectsLibraryDataCenter library,
//					String libraryFilename) {
//				SFLibraryTool.this.loadCompiledLibrary(library, libraryFilename);
//			}
//		});
//	}
//	
//	public void loadCompiledLibrary(SFObjectsLibrary library,
//			String libraryFilename) {
//		SFDataUtility.loadCompiledDataset( libraryFilename,library.getLibrary());
//		libraryNames.put(library,libraryFilename);
//	}
//	
//	
//	public void saveLoadFile(SFCompositeDataCenter<SFObjectsLibrary> libraries) {
//		String loadFile=getRoot()+"/"+loadFileName;
//		try {
//			FileWriter writer=new FileWriter(loadFile);
//			
//			Collection<String> libraryNames=libraries.getDataCenters().keySet();
//			
//			for (String string : libraryNames) {
//				writer.write(string+".sf\n");
//			}
//			writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	private static SFCompositeDataCenter<SFObjectsLibrary> loadLibraries(String root,LibraryManager manager){
//		
//		SFCompositeDataCenter<SFObjectsLibrary> libraries=new SFCompositeDataCenter<SFObjectsLibrary>();
//		
//		SFDataCenter.setDataCenterImplementation(libraries);
//		
//		//I do not need a 'template.sf'. I do need a library declaration file.
//		
//		try {
//			String loadFile=root+"/"+loadFileName;
//			BufferedReader reader=new BufferedReader(new FileReader(loadFile));
//			String line=reader.readLine();
//			while(line!=null){
//				if(line.endsWith(".sf")){
//					int index2=line.lastIndexOf('.');
//					String libName=line.substring(0,index2);
//					if(!line.startsWith("//") && !(line.trim().length()==0)){
//						String filename=root+"/"+line;
//						if((new File(filename)).exists()){
//							System.err.println("libName "+libName);
//							SFObjectsLibraryDataCenter library=new SFObjectsLibraryDataCenter();
//							libraries.add(libName,library);
//							manager.loadLibrary(library,filename);
//						}
//					}
//				}else if(line.endsWith(".sfp")){
//					
//					//OK Ok : TODO!!
////					SFDataPipelineBuilder builder2=new SFDataPipelineBuilder();
////					SFDataUtility.loadDataset(root, line, builder2);
////					builder2.apply(new SFPipelineBuilder());
//				}
//				line=reader.readLine();
//				
//			}
//			reader.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return libraries;
//	}
	
	/**
	 * Create a copy of this LibraryTool having a new Root
	 * @param newRoot
	 * @return
	 */
	public SFLibraryTool getCopyFromNewRoot(String newRoot){
		SFLibraryTool tool=new SFLibraryTool(newRoot);
		HashMap<SFObjectsLibrary, String> libraryNames=tool.libraryNames;
		
		for (SFObjectsLibrary library : this.libraryNames.keySet()) {
			String name=this.libraryNames.get(library);
			name=name.replace(root, newRoot);
			libraryNames.put(library, name);
		}
		
		return tool;
	}
	
//	/**
//	 * Save a set of Libraries
//	 */
//	public void save(SFCompositeDataCenter<SFObjectsLibrary> vLibrary){
//		Map<String,SFObjectsLibrary> libraries=vLibrary.getDataCenters();
//	
//		for (SFObjectsLibrary lib : libraries.values()) {
//			
//			SFDataUtility.saveDataset(lib.getLibrary(),this.getLibraryName(lib));
//		}	
//	}
//
//	/**
//	 * Save a compiled version of a set of Libraries
//	 */
//	public void saveCompiled(SFCompositeDataCenter<SFObjectsLibrary> vLibrary){
//		Map<String,SFObjectsLibrary> libraries=vLibrary.getDataCenters();
//	
//		for (SFObjectsLibrary lib : libraries.values()) {
//			
//			SFDataUtility.compileDataset(lib.getLibrary(),this.getLibraryName(lib));
//		}	
//	}
	

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}
}
