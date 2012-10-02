package shadow.pipeline.data;

import java.util.List;

import shadow.pipeline.SFPipelineElement;
import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.expression.SFBasicExpressionGenerator;
import shadow.pipeline.expression.SFExpressionGeneratorKeeper;
import shadow.system.SFException;
import shadow.system.data.SFCharsetObjectList;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFDataList;

public class SFDataPipelineBuilder extends SFCompositeDataArray implements SFIPipelineBuilder,SFCharsetObjectList{

	private SFDataList<SFPipelineInstructionObjects> allInformations;

	private SFPipelineBuilder pipelineBuilder = new SFPipelineBuilder();

	public SFDataPipelineBuilder() {
		super();
		SFExpressionGeneratorKeeper.getKeeper().setGenerator(new SFBasicExpressionGenerator());
	}

//	@Override
//	public void addGridVertex(String token) {
//		pipelineBuilder.addGridVertex(token);
//		allInformations.add(new SFPipelineInstructionObjects("Vertex", token));
//	}

	@Override
	public void buildDefineRule(String pWrote, short type, String function) {
		pipelineBuilder.buildDefineRule(pWrote, type, function);
		SFPipelineInstructionObjects instruction = new SFPipelineInstructionObjects("Define",
				pWrote, type + "",function);
		allInformations.add(instruction);
	}
	
	@Override
	public void buildComponent(String componentName) {
		pipelineBuilder.buildComponent(componentName);
		SFPipelineInstructionObjects instruction = new SFPipelineInstructionObjects("Component",componentName);
		allInformations.add(instruction);
	}

//	@Override
//	public void buildEdge(List<String> edges) {
//		pipelineBuilder.buildEdge(edges);
//		allInformations.add(new SFPipelineInstructionObjects("Edge", edges));
//	}

	@Override
	public void buildGrid(List<String> pars, String model,String type, int n) {
		pipelineBuilder.buildGrid(pars, model,type,n);
		allInformations.add(new SFPipelineInstructionObjects("Grid", pars, model ,type,n+""));
	}
	

//	@Override
//	public void buildGridInternals(List<String> internals) {
//		pipelineBuilder.buildGridInternals(internals);
//		allInformations.add(new SFPipelineInstructionObjects("Internal", internals));
//	}

	@Override
	public void buildParamRule(short parameter, String use) {
		pipelineBuilder.buildParamRule(parameter, use);
		allInformations.add(new SFPipelineInstructionObjects("Param", parameter + "", use));
	}

	@Override
	public void buildParamRule(String moduleString, List<String> pars) {
		pipelineBuilder.buildParamRule(moduleString, pars);
		allInformations.add(new SFPipelineInstructionObjects("Param2", pars, moduleString));
	}

//	@Override
//	public void buildPath(List<String> paths) {
//		pipelineBuilder.buildPath(paths);
//		allInformations.add(new SFPipelineInstructionObjects("Path", paths));
//	}

	@Override
	public void buildWriteRule(String wrote, String function) throws SFException {
		pipelineBuilder.buildWriteRule(wrote, function);
		SFPipelineInstructionObjects instruction = new SFPipelineInstructionObjects("Write", wrote,function);
		allInformations.add(instruction);
	}
	
	@Override
	public void buildRewriteRule(String wrote, String function) throws SFException {
		pipelineBuilder.buildRewriteRule(wrote, function);
		SFPipelineInstructionObjects instruction = new SFPipelineInstructionObjects("Rewrite", wrote,function);
	
		allInformations.add(instruction);
	}

	@Override
	public void closeElement() {
		pipelineBuilder.closeElement();
		allInformations.add(new SFPipelineInstructionObjects("Close"));
	}

	@Override
	public SFCompositeDataArray clone() {
		return new SFDataPipelineBuilder();
	}

	@Override
	public void generateData() {
		allInformations = new SFDataList<SFPipelineInstructionObjects>(
				new SFPipelineInstructionObjects());
		addDataObject(allInformations);
	}

	@Override
	public void buildBlock(String block, String primitiveComponent) {
		allInformations.add(new SFPipelineInstructionObjects("Block", block, primitiveComponent));
	}
	
	@Override
	public void buildDomain(String domain) {
		allInformations.add(new SFPipelineInstructionObjects("Domain", domain));
	}
	
	@Override
	public void generateElement(String type, String name) {
		pipelineBuilder.generateElement(type, name);
		allInformations.add(new SFPipelineInstructionObjects("Begin", type, name));
	}

	@Override
	public SFPipelineElement getComponent() {
		return pipelineBuilder.getComponent();
	}

	@Override
	public void setComponent(SFPipelineElement component) {
		pipelineBuilder.setComponent(component);
	}

	@Override
	public void setUseRule(String use) throws SFException {
		pipelineBuilder.setUseRule(use);
		allInformations.add(new SFPipelineInstructionObjects("Use", use));
	}

	public void apply(SFPipelineBuilder alternativeBuilder) {

		try {
			for (int i = 0; i < allInformations.size(); i++) {

				List<String> parameters = allInformations.get(i).getParameters();
				String command = allInformations.get(i).commandName();

				if (command.equalsIgnoreCase("Begin")) {
					alternativeBuilder.generateElement(allInformations.get(i).getData(0),
							allInformations.get(i).getData(1));
				}
//				if (command.equalsIgnoreCase("Vertex")) {
//					alternativeBuilder.addGridVertex(allInformations.get(i).getData(0));
//				}
//				if (command.equalsIgnoreCase("Edge")) {
//					alternativeBuilder.buildEdge(parameters);
//				}
				if (command.equalsIgnoreCase("Define")) {
					alternativeBuilder.buildDefineRule(allInformations.get(i).getData(0),
							new Short(allInformations.get(i).getData(1)), allInformations.get(i).getData(2));
				}
				if (command.equalsIgnoreCase("Grid")) {
					alternativeBuilder.buildGrid(parameters, allInformations.get(i).getData(0),
							 allInformations.get(i).getData(1),
							new Integer(allInformations.get(i).getData(2)));
				}
//				if (command.equalsIgnoreCase("Internal")) {
//					alternativeBuilder.buildGridInternals(parameters);
//				}
				if (command.equalsIgnoreCase("Param")) {
					alternativeBuilder.buildParamRule(new Short(allInformations.get(i).getData(0)),
							allInformations.get(i).getData(1));
				}
				if (command.equalsIgnoreCase("Param2")) {
					alternativeBuilder.buildParamRule(allInformations.get(i).getData(0), parameters);
				}
//				if (command.equalsIgnoreCase("Path")) {
//					alternativeBuilder.buildPath(parameters);
//				}
				if (command.equalsIgnoreCase("Write")) {
					alternativeBuilder.buildWriteRule(allInformations.get(i).getData(0), allInformations.get(i).getData(1));
				}
				if (command.equalsIgnoreCase("Rewrite")) {
					alternativeBuilder.buildRewriteRule(allInformations.get(i).getData(0), allInformations.get(i).getData(1));
				}
				if (command.equalsIgnoreCase("Close")) {
					alternativeBuilder.closeElement();
				}
				if (command.equalsIgnoreCase("Use")) {
					alternativeBuilder.setUseRule(allInformations.get(i).getData(0));
				}
				if (command.equalsIgnoreCase("Component")) {
					alternativeBuilder.buildComponent(allInformations.get(i).getData(0));
				}
				if (command.equalsIgnoreCase("Domain")) {
					alternativeBuilder.buildDomain(allInformations.get(i).getData(0));
				}
				if (command.equalsIgnoreCase("Block")) {
					alternativeBuilder.buildBlock(allInformations.get(i).getData(0),allInformations.get(i).getData(1));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SFException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void addCharSetObjects(String value) {
		SFPipelineInstructionObjects instructionObject=new SFPipelineInstructionObjects();
		instructionObject.setStringValue(value);
		allInformations.add(instructionObject);
	}
	
	@Override
	public String getCharSetObjectString(int index) {
		return allInformations.get(index).toStringValue();
	}
	
	@Override
	public int getSize() {
		return allInformations.size();
	}
}
