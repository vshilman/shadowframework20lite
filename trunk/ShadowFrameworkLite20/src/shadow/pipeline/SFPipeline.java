package shadow.pipeline;

import java.util.HashMap;


/**
 * The Shadow Framework Pipeline is a Virtual Rendering Pipeline which the
 * ShadowFramework will implement through some OpenGL version.
 * 
 * The ShadowFramework Pipeline allows a combination of five ShaderComponents,
 * one for each programmable Virtual Shadow Framework Rendering steps:
 * Tessellation, Primitive, Transform, Material and Lighting.
 * 
 * @author Alessandro Martinelli
 */
public class SFPipeline {

	// Pipeline components.
	private HashMap<String, SFProgramComponent> components=new HashMap<String, SFProgramComponent>();
	private HashMap<String, SFPipelineGrid> grids=new HashMap<String, SFPipelineGrid>();
	private HashMap<String, SFPipelineStructure> structures=new HashMap<String, SFPipelineStructure>();

	private SFProgramBuilder sfProgramBuilder;
	private SFPipelineGraphics sfPipelineGraphics;
	private SFPipelineMemory sfPipelineMemory;
	private SFTexturePipeline sfTexturePipeline;

	private static SFPipeline pipeline=new SFPipeline();
	
	private SFPipeline() {
		super();
	}

	public static SFPipeline getPipeline() {
		return pipeline;
	}

	public static void setSfTexturePipeline(SFTexturePipeline sfTexturePipeline) {
		pipeline.sfTexturePipeline=sfTexturePipeline;
	}

	public static SFProgramBuilder getSfProgramBuilder() {
		return pipeline.sfProgramBuilder;
	}

	public static void setSfProgramBuilder(SFProgramBuilder sfProgramBuilder) {
		pipeline.sfProgramBuilder=sfProgramBuilder;
	}
	
	public static SFTexturePipeline getSfTexturePipeline() {
		return pipeline.sfTexturePipeline;
	}
	
	public static SFPipelineMemory getSfPipelineMemory() {
		return pipeline.sfPipelineMemory;
	}

	public static void setSfPipelineMemory(SFPipelineMemory sfPipelineMemory) {
		pipeline.sfPipelineMemory = sfPipelineMemory;
	}

	public static SFPipelineGraphics getSfPipelineGraphics() {
		return pipeline.sfPipelineGraphics;
	}

	public static void setSfPipelineGraphics(
			SFPipelineGraphics sfPipelineGraphics) {
		pipeline.sfPipelineGraphics=sfPipelineGraphics;
	}

	// Pipeline components.
	public static void loadShaderComponent(String code,
			SFProgramComponent component) {
		pipeline.components.put(code,component);
	}

	public static SFPipelineElement getModule(String structureCode) {
		SFPipelineStructure structure=pipeline.structures.get(structureCode);
		if (structure != null) {
			return structure;
		}
		SFProgramComponent component=pipeline.components.get(structureCode);
		if (component != null) {
			return component;
		}
		return pipeline.grids.get(structureCode);
	}
	

	public static SFPipelineStructure getStructure(String structureCode) {
		return pipeline.structures.get(structureCode);
	}

	// Pipeline components.
	public static void loadStructure(String code, SFPipelineStructure component) {
		pipeline.structures.put(code,component);
	}

	// Pipeline components.
	public static void loadGrid(String code, SFPipelineGrid component) {
		pipeline.grids.put(code,component);
	}

	/**
	 * Generate a unique Static SFShader given its parameters, or create it if
	 * it does not exists.
	 * 
	 * @param tessellator
	 * @param primitive
	 * @param transform
	 * @param material
	 * @param light
	 * @return
	 */
	public static SFProgram getStaticProgram(SFPrimitive primitive, String material[], String light) {

		SFProgram program=pipeline.sfProgramBuilder.generateNewProgram();

		program.setPrimitive(primitive);
		
		for (int i=0; i < material.length; i++) {
			program.setMaterial(i,pipeline.components.get(material[i]));
		}
		program.setLightStep(pipeline.components.get(light));

		pipeline.sfProgramBuilder.prepareProgram(program);
		// Now program is returned, and it is ready to be used

		return program;
	}

	/**
	 * Generate a unique Static SFShader given its parameters, or create it if
	 * it does not exists.
	 * 
	 * @param tessellator
	 * @param primitive
	 * @param transform
	 * @param material
	 * @param light
	 * @return
	 */
	public static SFProgram getStaticImageProgram(String material[], String light) {

		SFProgram program=pipeline.sfProgramBuilder.generateImageProgram();

		for (int i=0; i < material.length; i++) {
			program.setMaterial(i,pipeline.components.get(material[i]));
		}
		program.setLightStep(pipeline.components.get(light));

		pipeline.sfProgramBuilder.prepareProgram(program);

		return program;
	}
	

	/*
	@Override
	public void setModelPosition(float modelX,float modelY,float modelZ){
		sfPipelineGraphics.setModelPosition(modelX,modelY,modelZ);
	}
	
	@Override
	public void loadStructure(String strucuteCode, SFArray<SFValuenf> structure,int index){
		sfPipelineGraphics.loadStructure(strucuteCode,structure,index);
	}
	
	@Override
	public void loadBuffer1f(SFParameteri parameter, SFArray<SFValue1f> points,int indexP){
		sfPipelineGraphics.loadBuffer1f(parameter,points,indexP);
	}
	
	@Override
	public void loadBuffer2f(SFParameteri parameter, SFArray<SFVertex2f> points){
		sfPipelineGraphics.loadBuffer2f(parameter,points);
	}
	
	@Override
	public void loadBuffer3f(SFParameteri parameter, SFArray<SFVertex3f> points){
		sfPipelineGraphics.loadBuffer3f(parameter,points);
	}
	
	@Override
	public void loadBuffer4f(SFParameteri parameter, SFArray<SFVertex4f> points){
		sfPipelineGraphics.loadBuffer4f(parameter,points);
	}
	
	@Override
	public void renderElements(int elements){
		sfPipelineGraphics.renderElements(elements);
	}
	
	@Override
	public SFArray<SFVertex2f> generateVertices2f(){
		return sfPipelineGraphics.generateVertices2f();
	}
	
	@Override
	public SFArray<SFMatrix2f> generateTransforms2f(){
		return sfPipelineGraphics.generateTransforms2f();
	}

	@Override
	public SFArray<SFVertex3f> generateVertices3f(){
		return sfPipelineGraphics.generateVertices3f();
	}

	@Override
	public SFArray<SFMatrix3f> generateTransforms3f(){
		return sfPipelineGraphics.generateTransforms3f();
	}
	
	@Override
	public SFArray<SFVertex4f> generateVertices4f(){
		return sfPipelineGraphics.generateVertices4f();
	}

	@Override
	public SFArray<SFMatrix4f> generateTransforms4f(){
		return sfPipelineGraphics.generateTransforms4f();
	}
	
	@Override
	public SFStructureArray generateStructureData(SFPipelineStructure structure){
		return sfPipelineGraphics.generateStructureData(structure);
	}
		
	@Override
	public SFPrimitiveArray generatePrimitiveArray(){
		return sfPipelineGraphics.generatePrimitiveArray();
	}

	@Override
	public void drawPrimitives(SFPrimitiveArray primitives,List<SFPipelineStructure> structures,
			int first,int count){
		sfPipelineGraphics.drawPrimitives(primitives,structures,first,count);
	}
	
	@Override
	public void loadMaterial(SFStructureArray array,SFPipelineStructure structure,int indexOfData){
		sfPipelineGraphics.loadMaterial(array,structure,indexOfData);
	}*/
	
}