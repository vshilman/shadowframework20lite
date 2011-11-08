package shadow.pipeline.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.parameters.SFParameter;
import shadow.pipeline.parameters.SFParameteri;

public class SFParsableGrid extends SFPipelineGrid implements SFParsableElement{

	private static HashMap<String,Short> verticesLoading=new HashMap<String,Short>();
	private static ArrayList<ArrayList<Short>> edgesLoading=new ArrayList<ArrayList<Short>>();
	private static ArrayList<Short> cornersLoading=new ArrayList<Short>();
	private static ArrayList<ArrayList<Short>> pathsLoading=new ArrayList<ArrayList<Short>>();
	
	private short loadName(String vertex){
		if(verticesLoading.get(vertex)==null)
			verticesLoading.put(vertex,(short)verticesLoading.size());
		return verticesLoading.get(vertex);
	}
	
	public void loadVertex(String vertex){
		cornersLoading.add(loadName(vertex));
	}
	
	public void loadEdge(ArrayList<String> vertex){
		edgesLoading.add(getPath(vertex));	
	}

	public void loadPath(ArrayList<String> vertex){
		pathsLoading.add(getPath(vertex));
	}
	
	public void loadInternal(ArrayList<String> vertex){
		for (Iterator<String> iterator = vertex.iterator(); iterator.hasNext();) {
			String vName = (String) iterator.next();
			loadName(vName);
		}
	}
	
	private ArrayList<Short> getPath(ArrayList<String> vertex) {
		ArrayList<Short> path=new ArrayList<Short>();
		for (Iterator<String> iterator = vertex.iterator(); iterator.hasNext();) {
			String vertexName = (String) iterator.next();
			path.add(loadName(vertexName));
		}
		return path;
	}
	
	@Override
	public void finalize() {
		
		names=new String[verticesLoading.size()];
		Set<String> ns=verticesLoading.keySet();
		int j=0;
		for (Iterator<String> iterator = ns.iterator(); iterator.hasNext(); j++) {
			names[j]= (String) iterator.next();
		}

		//corners idx
		corners=new short[cornersLoading.size()];
		for(int i=0;i<cornersLoading.size();i++){
			corners[i]=cornersLoading.get(i);
		}

		//edges idx
		edges=new short[edgesLoading.size()][];
		for (int i = 0; i < edges.length; i++) {
			edges[i]=new short[edgesLoading.get(i).size()];
			for(int k=0;k<edges[i].length;k++){
				edges[i][k]=edgesLoading.get(i).get(k);
			}
		}
		
		//paths idx
		paths=new short[pathsLoading.size()][];
		for (int i = 0; i < paths.length; i++) {
			paths[i]=new short[pathsLoading.get(i).size()];
			for(int k=0;k<paths[i].length;k++){
				paths[i][k]=pathsLoading.get(i).get(k);
			}
		}
		
		for(int i=0;i<names.length;i++){
			addParameter(new SFParameter(names[i],SFParameteri.GLOBAL_GENERIC));
		}
		
		verticesLoading.clear();
		edgesLoading.clear();
		cornersLoading.clear();
		pathsLoading.clear();
		
		SFPipeline.loadGrid(getName(), this);
		
	}
	
	private static ArrayList<String> allCommands=new ArrayList<String>();
	static{
		allCommands.add("begin");
		allCommands.add("vertex");
		allCommands.add("internal");
		allCommands.add("edge");
		allCommands.add("path");
		allCommands.add("end");
	}
	
	@Override
	public List<String> getAllCommands() {
		return allCommands;
	}
}
