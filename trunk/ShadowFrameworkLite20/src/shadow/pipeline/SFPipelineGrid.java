package shadow.pipeline;

import java.util.List;

import shadow.pipeline.parameters.SFParameteri;
import shadow.system.SFException;

public class SFPipelineGrid {
	
	private int n;
	private SFGridModel model;
	protected SFFunction[] edgeFunctions;
	protected SFFunction[] internalsFunctions;
	
	private SFParameteri[] params;

	public SFPipelineGrid(int n,SFGridModel model,
			List<SFParameteri> params) {
		super();
		this.model=model;
		this.n = n;
		if(params.size()!=getGridSize()){
			throw new SFException("SFPipelineGrid malformed, "+getGridSize()+" parameters was attended, only "+params.size()+" was given");
		}
		this.params=new SFParameteri[params.size()];
		for (int i = 0; i < params.size(); i++) {
			this.params[i]=params.get(i);
		}
		
		prepareFunctionsArrays();
	}
	
	private void prepareFunctionsArrays(){
		if(n==1){
			edgeFunctions=new SFFunction[0];
			internalsFunctions=new SFFunction[0];
			
			return;
		}
		int edgeFunctionsSize=model.getEdges()*(n-2);
		int cornerSize=model.getCorners();
		int internalsFunctionsSize=getGridSize()-cornerSize-edgeFunctionsSize;
		
		edgeFunctions=new SFFunction[edgeFunctionsSize];
		internalsFunctions=new SFFunction[internalsFunctionsSize];
	}

	public int getN(){
		return n;
	}

	public SFParameteri[] getParameters() {
		return params;
	}
	
	public int getGridSize() {
		return this.model.getGridSize(this.n);
	}
	
	public int size(){
		return params.length;
	}

	public SFGridModel getModel() {
		return model;
	}
	
	public boolean isTriangular(){
		return model==SFGridModel.Triangle;
	}
	
	
	public void addFunction(SFFunction function,SFParameteri wrote){
		int index=0;
		for (int i = model.getEdges(); i < params.length; i++) {
			if(params[i].getName().equalsIgnoreCase(wrote.getName())){
				index=i;
				i=params.length;
			}
		}

		function.compileFunction(params);

		if(index<model.getCorners()+edgeFunctions.length) {
			index-=model.getCorners();
			edgeFunctions[index]=(function);
		}else{
			index-=(model.getCorners()+edgeFunctions.length);
			internalsFunctions[index]=(function);
		}
	}
	
	public SFFunction[] getEdgeFunctions(){
		return edgeFunctions;
	}

	public SFFunction[] getInternalsFunctions(){
		return internalsFunctions;
	}
	
	public short getParameterType(int parameterIndex){
		return params[parameterIndex].getType();
	}
}

