package shadow.pipeline.builder;

import java.util.List;

import shadow.pipeline.SFPipelineElement;
import shadow.system.SFException;

public interface SFIPipelineBuilder {

	public void generateElement(String type, String name);

	public SFPipelineElement getComponent();

	public void setComponent(SFPipelineElement component);

	public void setUseRule(String use) throws SFException;
	
	public void buildDefineRule( String pWrote,short type, String function) ;

	//public void addGridVertex(String token);

	public void buildWriteRule(String wrote, String function)
			throws SFException;

	public void buildRewriteRule(String wrote, String function) throws SFException;
	
	public void buildParamRule(short parameter, String use);

	//public void buildPath(List<String> paths);

	public void buildParamRule(String moduleString, List<String> pars);

	//public void buildGridInternals(List<String> internals);

	public void buildGrid(List<String> pars, String model,String type, int n);
	
	public void buildComponent(String componentName) ;

	public void buildDomain(String domain) ;

	public void buildBlock(String block,String primitiveComponent) ;

	public void closeElement();

	//public void buildEdge(List<String> edges);
	

}