package shadow.pipeline.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shadow.pipeline.SFFunction;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineElement;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPipelineGridInstance;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.data.SFExpressionParser;
import shadow.pipeline.parameters.SFParameter;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

public class SFPipelineBuilder implements SFIPipelineBuilder {

	private static HashMap<String, Class> typeMap=new HashMap<String, Class>();
	
	static{
		typeMap.put("Tessellator", SFParsableProgramComponent.class);
		typeMap.put("Primitive", SFParsableProgramComponent.class);
		typeMap.put("Transforms", SFParsableProgramComponent.class);
		typeMap.put("Material", SFParsableProgramComponent.class);
		typeMap.put("LightStep", SFParsableProgramComponent.class);
		typeMap.put("Grid", SFBuilderGrid.class);
		typeMap.put("Structure", SFBuilderStructure.class);
	}
	
	public static SFBuilderElement getElement(String type) {
		
		try {
			return (SFBuilderElement)(typeMap.get(type).newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private SFPipelineElement component;

	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#generateElement(java.lang.String, java.lang.String)
	 */
	@Override
	public void generateElement(String type, String name) {
		SFBuilderElement temp=SFPipelineBuilder.getElement(type);
		temp.setName(name);
		this.component=(SFPipelineElement)temp;
	}
	
	public void buildDefineRule( String pWrote,short type, SFExpressionElement function) {
		SFParameter param=new SFParameter(pWrote,type);
		SFProgramComponent cmp=(SFProgramComponent)getComponent();
		cmp.addParameter(param);
		SFFunction functionCode = new SFFunction(param,function,cmp.getParameterSet());
		cmp.addCodeFunction(functionCode);
	}

	public void buildDefineRule( String pWrote,short type, String function) {
		SFParameter param=new SFParameter(pWrote,type);
		SFProgramComponent cmp=(SFProgramComponent)getComponent();
		cmp.addParameter(param);
		
		SFExpressionBuilder builder=new SFExpressionBuilder();
		SFExpressionParser.getParser().parseString(function, cmp.getParameterSet(),builder);
		SFFunction functionCode = new SFFunction(param,builder.getBuiltExpression(),cmp.getParameterSet());
		cmp.addCodeFunction(functionCode);
	}
	
	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#getComponent()
	 */
	@Override
	public SFPipelineElement getComponent() {
		return component;
	}
	
	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#setComponent(shadow.pipeline.SFPipelineElement)
	 */
	@Override
	public void setComponent(SFPipelineElement component) {
		this.component = component;
	}

	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#setUseRule(java.lang.String)
	 */
	@Override
	public void setUseRule(String use)
			throws SFException {
		SFPipelineRegister global=SFPipelineRegister.getFromName(use);
		SFProgramComponent cmp=(SFProgramComponent)getComponent();
		cmp.addRegister(global);
	}

	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#addGridVertex(java.lang.String)
	 */
	@Override
	public void addGridVertex(String token) {
		((SFBuilderGrid)(getComponent())).loadVertex(token);
	}

	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#buildWriteRule(java.lang.String, java.lang.String)
	 */
	@Override
	public void buildWriteRule(String wrote, String function) throws SFException {
		SFPipelineRegister global=SFPipelineRegister.getFromName(wrote);
		SFProgramComponent cmp=(SFProgramComponent)getComponent();
		((SFProgramComponent)getComponent()).addRegister(global);
		
		SFExpressionBuilder builder=new SFExpressionBuilder();
		SFExpressionParser.getParser().parseString(function, cmp.getParameterSet(),builder);
		SFFunction functionCode = new SFFunction(global,builder.getBuiltExpression(),cmp.getParameterSet());
		cmp.addCodeFunction(functionCode);
	}
	
	
	public void buildWriteRule(String wrote, SFExpressionElement function) throws SFException {
		SFPipelineRegister global=SFPipelineRegister.getFromName(wrote);
		SFProgramComponent cmp=(SFProgramComponent)getComponent();
		((SFProgramComponent)getComponent()).addRegister(global);
		
		SFFunction functionCode = new SFFunction(global,function,cmp.getParameterSet());
		cmp.addCodeFunction(functionCode);
	}


	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#buildWriteRule(java.lang.String, java.lang.String)
	 */
	@Override
	public void buildRewriteRule(String wrote, String function) throws SFException {
		SFBuilderGrid grid=(SFBuilderGrid)getComponent();
		SFParameteri parameter=new SFParameter(wrote,SFParameteri.GLOBAL_GENERIC);
		
		List<String> parameters=SFBuilderGrid.getVerticesLoading();
		List<SFParameteri> params=new ArrayList<SFParameteri>();
		for (String sfParameteri : parameters) {
			params.add(new SFParameter(sfParameteri));
		}
		
		SFExpressionBuilder builder=new SFExpressionBuilder();
		SFExpressionParser.getParser().parseString(function, params,builder);
		SFFunction functionCode = new SFFunction(parameter,builder.getBuiltExpression(),params);
		grid.addFunction(functionCode);
	}

	public void buildRewriteRule(String wrote, SFExpressionElement function) throws SFException {
		SFBuilderGrid grid=(SFBuilderGrid)getComponent();
		SFParameteri parameter=new SFParameter(wrote,SFParameteri.GLOBAL_GENERIC);
		
		List<String> parameters=SFBuilderGrid.getVerticesLoading();
		List<SFParameteri> params=new ArrayList<SFParameteri>();
		for (String sfParameteri : parameters) {
			params.add(new SFParameter(sfParameteri));
		}
		
		SFFunction functionCode = new SFFunction(parameter,function,params);
		grid.addFunction(functionCode);
	}

	
	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#buildParamRule(short, java.lang.String)
	 */
	@Override
	public void buildParamRule(short parameter, String use) {
		SFParameter param=new SFParameter(use, parameter);
		SFPipelineStructure cmp=(SFPipelineStructure)getComponent();
		cmp.addParameter(param);
	}
	
	

	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#buildPath(java.util.ArrayList)
	 */
	@Override
	public void buildPath(List<String> paths) {
		SFBuilderGrid grid=(SFBuilderGrid)getComponent();
		grid.loadPath(paths);
	}

	//TODO: this is not ok...
	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#buildParamRule(java.lang.String, java.util.List)
	 */
	@Override
	public void buildParamRule(String moduleString, List<String> pars) {
		Vector<SFParameteri> params_ = new Vector<SFParameteri>();//[pars.length];

		SFPipelineElement module = SFPipeline
				.getModule(moduleString);
		SFProgramComponent cmp = (SFProgramComponent) getComponent();
		
		SFPipelineStructure sfPs = (SFPipelineStructure) module;
		List<SFParameteri> parameters = sfPs.getAllParameters();
	
		Iterator<String> parsIterator=pars.iterator();
		for (Iterator<SFParameteri> iterator = parameters.iterator(); iterator.hasNext();) {
			SFParameteri sfParameteri = (SFParameteri) iterator.next();
	
			params_.add(new SFParameter(parsIterator.next(),sfParameteri.getType()));
		
		}
		
		SFPipelineStructureInstance instance = new SFPipelineStructureInstance(
				(SFPipelineStructure) module, params_);
		cmp.addStructureInstance(instance);
	}

	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#buildGridInternals(java.util.ArrayList)
	 */
	@Override
	public void buildGridInternals(List<String> internals) {
		((SFBuilderGrid)getComponent()).loadInternal(internals);
	}
	
	private HashMap<String, Short> types=new HashMap<String, Short>();
	{
		types.put("<>", SFParameter.GLOBAL_GENERIC);
		types.put("float", SFParameter.GLOBAL_FLOAT);
		types.put("float2", SFParameter.GLOBAL_FLOAT2);
		types.put("float3", SFParameter.GLOBAL_FLOAT3);
		types.put("float4", SFParameter.GLOBAL_FLOAT4);
	}

	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#buildGrid(java.util.List, java.lang.String)
	 */
	@Override
	public void buildGrid(List<String> pars, String moduleString, String typeString) {
		Vector<SFParameteri> params_ = new Vector<SFParameteri>();
	
		SFPipelineElement module = SFPipeline
				.getModule(moduleString);
		SFProgramComponent cmp = (SFProgramComponent) getComponent();

		short type=types.get(typeString);

		for (Iterator<String> parsIterator = pars.iterator(); parsIterator.hasNext();) {
			params_.add(new SFParameter(parsIterator.next(),type));
		}

		SFPipelineGridInstance instance = new SFPipelineGridInstance(
				(SFPipelineGrid) module, params_);
		
		cmp.addGridInstance(instance);
		
	}

	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#closeElement()
	 */
	@Override
	public void closeElement() {
		if(getComponent()!=null)
			((SFBuilderElement)getComponent()).finalize();
		
		setComponent(null);
	}

	/* (non-Javadoc)
	 * @see shadow.pipeline.builder.SFIPipelineBuilder#buildEdge(java.util.ArrayList)
	 */
	@Override
	public void buildEdge(List<String> edges) {
		SFBuilderGrid grid=(SFBuilderGrid)getComponent();	
		grid.loadEdge(edges);
	}

	
	
	
}
