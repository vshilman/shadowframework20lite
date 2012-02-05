
function SFParsableGrid(){
}

SFParsableGrid.prototype = {

	loadName:function(vertex){
	if(verticesLoading.get(vertex)==null)			verticesLoading.put(vertex,(short)verticesLoading.size());//Warning: Not well Identified 
		return ,verticesLoading.get(vertex);
	},

	loadVertex:function(vertex){
		cornersLoading.add(loadName(vertex));
	},

	loadEdge:function(vertex){
		edgesLoading.add(getPath(vertex));
	},

	loadPath:function(vertex){
		pathsLoading.add(getPath(vertex));
	},

	loadInternal:function(vertex){
	//for (Iterator<String> iterator = vertex.iterator();iterator.hasNext(););//Warning: Not well Identified 
		 String  vName   = (String) iterator.next();
		loadName(vName);
	//}
	},

	getPath:function(vertex){
		 ArrayList<Short>  path = new  ArrayList<Short>();
	//for (Iterator<String> iterator = vertex.iterator();iterator.hasNext(););//Warning: Not well Identified 
		 String  vertexName   = (String) iterator.next();
		path.add(loadName(vertexName));
	//}
		return ,path;
	},

	finalize:function(){
	names=new String[verticesLoading.size()];//Warning: Not well Identified 
		 Set<String>  ns = verticesLoading.keySet();
		 int  j = 0;
	//for (Iterator<String> iterator = ns.iterator();iterator.hasNext();j++);//Warning: Not well Identified 
		names[j]  = (String) iterator.next();
	//}
	//corners idx		corners=new short[cornersLoading.size()];//Warning: Not well Identified 
		for ( int  i=0 ; i < cornersLoading.size() ; i++ ){
		corners[i]  = cornersLoading.get(i);
	}
	//edges idx		edges=new short[edgesLoading.size()][];//Warning: Not well Identified 
		for ( int  i  =  0 ; i   < edges.length ; i++ ){
	edges[i]=new short[edgesLoading.get(i).size()];//Warning: Not well Identified 
		for ( int  k=0 ; k < edges[i].length ; k++ ){
		edges[i][k]  = edgesLoading.get(i).get(k);
	}
	}
	//paths idx		paths=new short[pathsLoading.size()][];//Warning: Not well Identified 
		for ( int  i  =  0 ; i   < paths.length ; i++ ){
	paths[i]=new short[pathsLoading.get(i).size()];//Warning: Not well Identified 
		for ( int  k=0 ; k < paths[i].length ; k++ ){
		paths[i][k]  = pathsLoading.get(i).get(k);
	}
	}
		for ( int  i=0 ; i < names.length ; i++ ){
		addParameter(new SFParameter(names[i],SFParameteri.GLOBAL_GENERIC));
	}
		verticesLoading.clear();
		edgesLoading.clear();
		cornersLoading.clear();
		pathsLoading.clear();
		SFPipeline.loadGrid(getName(), this);
	},

	getAllCommands:function(){
		return ,allCommands;
	}

};