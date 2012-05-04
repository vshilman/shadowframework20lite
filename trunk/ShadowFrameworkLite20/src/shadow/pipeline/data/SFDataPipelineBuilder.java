package shadow.pipeline.data;

import java.util.List;

import shadow.pipeline.SFPipelineElement;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.builder.SFBuilderElement;
import shadow.pipeline.builder.SFBuilderGrid;
import shadow.pipeline.builder.SFExpressionBuilder;
import shadow.pipeline.builder.SFIPipelineBuilder;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionGeneratorKeeper;
import shadow.pipeline.expression.data.SFExpressionParser;
import shadow.system.SFException;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFDataList;

public class SFDataPipelineBuilder extends SFCompositeDataArray implements SFIPipelineBuilder {

	private SFDataList<SFPipelineInstructionObjects> allInformations;

	private SFBuilderElement element;

	private SFPipelineBuilder pipelineBuilder = new SFPipelineBuilder();

	public SFDataPipelineBuilder() {
		super();
		SFExpressionGeneratorKeeper.getKeeper().setGenerator(new SFDataExpressionGenerator());
	}

	@Override
	public void addGridVertex(String token) {
		pipelineBuilder.addGridVertex(token);
		allInformations.add(new SFPipelineInstructionObjects("Vertex", token));
	}

	@Override
	public void buildDefineRule(String pWrote, short type, String function) {
		pipelineBuilder.buildDefineRule(pWrote, type, function);

		SFProgramComponent cmp = (SFProgramComponent) getComponent();
		SFExpressionBuilder builder = new SFExpressionBuilder();
		SFExpressionParser.getParser().parseString(function, cmp.getParameterSet(), builder);
		SFExpressionElement element = builder.getBuiltExpression();
		SFDataExpressionOperator operator = new SFDataExpressionOperator();
		operator.addElement(element);
		SFPipelineInstructionObjects instruction = new SFPipelineInstructionObjects("Define",
				pWrote, type + "");
		instruction.addObject(operator);
		allInformations.add(instruction);

	}

	@Override
	public void buildEdge(List<String> edges) {
		pipelineBuilder.buildEdge(edges);
		allInformations.add(new SFPipelineInstructionObjects("Edge", edges));
	}

	@Override
	public void buildGrid(List<String> pars, String moduleString,String type) {
		pipelineBuilder.buildGrid(pars, moduleString,type);
		allInformations.add(new SFPipelineInstructionObjects("Grid", pars, moduleString,type));
	}

	@Override
	public void buildGridInternals(List<String> internals) {
		pipelineBuilder.buildGridInternals(internals);
		allInformations.add(new SFPipelineInstructionObjects("Internal", internals));
	}

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

	@Override
	public void buildPath(List<String> paths) {
		pipelineBuilder.buildPath(paths);
		allInformations.add(new SFPipelineInstructionObjects("Path", paths));
	}

	@Override
	public void buildWriteRule(String wrote, String function) throws SFException {
		pipelineBuilder.buildWriteRule(wrote, function);
		SFProgramComponent cmp = (SFProgramComponent) getComponent();

		SFExpressionBuilder builder = new SFExpressionBuilder();
		SFExpressionParser.getParser().parseString(function, cmp.getParameterSet(), builder);
		SFExpressionElement element = builder.getBuiltExpression();
		SFDataExpressionOperator operator = new SFDataExpressionOperator();
		operator.addElement(element);
		SFPipelineInstructionObjects instruction = new SFPipelineInstructionObjects("Write", wrote);
		instruction.addObject(operator);
		allInformations.add(instruction);
	}
	
	@Override
	public void buildRewriteRule(String wrote, String function) throws SFException {
		pipelineBuilder.buildRewriteRule(wrote, function);
		SFBuilderGrid grid=(SFBuilderGrid)getComponent();
		
		SFExpressionBuilder builder = new SFExpressionBuilder();
		SFExpressionParser.getParser().parseString(function, grid.getAllParameters(), builder);
		SFExpressionElement element = builder.getBuiltExpression();
		SFDataExpressionOperator operator = new SFDataExpressionOperator();
		operator.addElement(element);
		SFPipelineInstructionObjects instruction = new SFPipelineInstructionObjects("Rewrite", wrote);
		instruction.addObject(operator);
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

				// Not the best code in the world...
				if (command.equalsIgnoreCase("Begin")) {
					alternativeBuilder.generateElement(allInformations.get(i).getData(0),
							allInformations.get(i).getData(1));
				}
				if (command.equalsIgnoreCase("Vertex")) {
					alternativeBuilder.addGridVertex(allInformations.get(i).getData(0));
				}
				if (command.equalsIgnoreCase("Edge")) {
					alternativeBuilder.buildEdge(parameters);
				}
				if (command.equalsIgnoreCase("Define")) {
					SFDataExpressionOperator operator = allInformations.get(i).getObject();
					SFExpressionElement element = operator.retrieveEffectiveElement();
					alternativeBuilder.buildDefineRule(allInformations.get(i).getData(0),
							new Short(allInformations.get(i).getData(1)), element);
				}
				if (command.equalsIgnoreCase("Grid")) {
					alternativeBuilder.buildGrid(parameters, allInformations.get(i).getData(0),allInformations.get(i).getData(1));
				}
				if (command.equalsIgnoreCase("Internal")) {
					alternativeBuilder.buildGridInternals(parameters);
				}
				if (command.equalsIgnoreCase("Param")) {
					alternativeBuilder.buildParamRule(new Short(allInformations.get(i).getData(0)),
							allInformations.get(i).getData(1));
				}
				if (command.equalsIgnoreCase("Param2")) {
					alternativeBuilder
							.buildParamRule(allInformations.get(i).getData(0), parameters);
				}
				if (command.equalsIgnoreCase("Path")) {
					alternativeBuilder.buildPath(parameters);
				}
				if (command.equalsIgnoreCase("Write")) {
					SFDataExpressionOperator operator = allInformations.get(i).getObject();
					SFExpressionElement element = operator.retrieveEffectiveElement();
					alternativeBuilder.buildWriteRule(allInformations.get(i).getData(0), element);
				}
				if (command.equalsIgnoreCase("Rewrite")) {
					SFDataExpressionOperator operator = allInformations.get(i).getObject();
					SFExpressionElement element = operator.retrieveEffectiveElement();
					alternativeBuilder.buildRewriteRule(allInformations.get(i).getData(0), element);
				}
				if (command.equalsIgnoreCase("Close")) {
					alternativeBuilder.closeElement();
				}
				if (command.equalsIgnoreCase("Use")) {
					alternativeBuilder.setUseRule(allInformations.get(i).getData(0));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SFException e) {
			e.printStackTrace();
		}

	}

}
