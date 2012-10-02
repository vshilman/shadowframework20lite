package shadow.pipeline.openGL20;

import java.util.ArrayList;
import java.util.List;

import shadow.image.SFPipelineTexture;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipelineGraphics.Module;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFProgramModule;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.java.SFGL20ValuenfArray;
import shadow.pipeline.java.SFProgramDataModel;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

public class SFGL20UniformData implements SFProgramDataModel {
	
	private SFGLSLSet program;
	
	private int[][] transformStructureUniforms;
	private int[][] materialStructureUniforms;
	private int[][] lightStructureUniforms;
	private int[] transformTextures;
	private int[] materialTextures;
	private int[] lightTextures;
	
	private List<Integer[]> gridUniforms=new ArrayList<Integer[]>();
	
	public int[] mainUniforms;
	
	private int[] getUniforms(String prefix,SFPipelineStructureInstance instance,ArrayList<Integer> textures){
		int[] data=new int[instance.size()];
		List<SFParameteri> parameters=instance.getParameters();
		for (int i = 0; i < data.length; i++) {
			SFParameteri param=parameters.get(i);
			String name=prefix+param.getName();
			data[i]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),name);
			if(param.getType()==SFParameteri.GLOBAL_TEXTURE){
				textures.add(data[i]);
			}
				
		}
		return data;
	}
	
	private Integer[] getUniforms(String prefix,SFPipelineGrid instance){
		Integer[] data=new Integer[instance.size()];
		SFParameteri[] parameters=instance.getParameters();
		for (int i = 0; i < data.length; i++) {
			SFParameteri param=parameters[i];
			String name=prefix+param.getName();
			data[i]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),name);
		}
		return data;
	}

	void evaluateUniforms(SFGL20AbstractProgram program) {
		this.program=program;
		
		gridUniforms.clear();
		
		SFPrimitive primitive=program.getPrimitive();
		if(primitive!=null){
			for (int i = 0; i < primitive.getBlocks().length; i++) {
				SFProgramComponent component=primitive.getComponents()[i];
				SFPipelineRegister register=primitive.getBlocks()[i].getRegister();
				List<SFPipelineGrid> grids=component.getGrid();
				for (SFPipelineGrid sfPipelineGridInstance : grids) {
					
					gridUniforms.add(getUniforms(register.getName(), sfPipelineGridInstance));
				}
			}
		}
		
		ArrayList<Integer> textures=new ArrayList<Integer>();
		transformStructureUniforms=evaluateStructureUniforms(program.getTransforms(),textures);
		this.transformTextures=listToInts(textures);
		textures.clear();
		materialStructureUniforms=evaluateStructureUniforms(program.getMaterials(),textures);
		this.materialTextures=listToInts(textures);
		textures.clear();
		lightStructureUniforms=evaluateStructureUniforms(program.getLight(),textures);
		this.lightTextures=listToInts(textures);
		
		mainUniforms=new int[3];
		mainUniforms[0]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),"projection");
		mainUniforms[1]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),"modelview");
		mainUniforms[2]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),"vectorsModelview");
		
	}
	
	private static int[] listToInts(ArrayList<Integer> list){
		int[] values=new int[list.size()];
		for (int i = 0; i < values.length; i++) {
			values[i]=list.get(i);
		}
		return values;
	}

	public int[][] evaluateStructureUniforms(SFProgramModule module,ArrayList<Integer> textures) {
		if(module!=null){
			List<SFPipelineStructureInstance> allStructures=new ArrayList<SFPipelineStructureInstance>();
			for (int i = 0; i < module.getComponents().length; i++) {
				SFProgramComponent component=module.getComponents()[i];
				allStructures.addAll(component.getStructures());
			}
			
			int[][] uniforms=new int[allStructures.size()][];

			for (int i = 0; i < uniforms.length; i++) {
				SFPipelineStructureInstance sfPipelineStructureInstance=allStructures.get(i);
				uniforms[i]=getUniforms("", sfPipelineStructureInstance,textures);
			}
			return uniforms;
		}
		
		return new int[0][];
	}
	

	void evaluateUniforms(SFGL20ImageProgram program) {
		this.program=program;
		//Primitive Uniforms
		
		gridUniforms.clear();
		ArrayList<Integer> textures=new ArrayList<Integer>();
		materialStructureUniforms=evaluateStructureUniforms(program.getMaterials(),textures);
		this.materialTextures=listToInts(textures);
		textures.clear();
		lightStructureUniforms=evaluateStructureUniforms(program.getLight(),textures);
		this.lightTextures=listToInts(textures);
	}

	
	@Override
	public void setData(Module module, int index, SFPipelineTexture texture) {
		int level=index;
		int[] textures=transformTextures;
		if(module==Module.LIGHT){
			level+=transformTextures.length+materialTextures.length;
			textures=lightTextures;
		}else if(module==Module.MATERIAL){
			level+=transformTextures.length;
			textures=materialTextures;
		}
		
		texture.apply(level);
		//with its related uniform..
		SFGL2.getGL().glUniform1i(textures[index], level);
	}
	

//	private void evaluateTextureUniforms(SFGLSLSet program) {
//		int index=0;
//		while(index<8){
//			
//			int textureLevel=SFGL2.getGL().glGetUniformLocation(program.getProgram(),"texture"+index);
//			if(textureLevel>=0)
//				SFGL2.getGL().glUniform1i(textureLevel, index);
//			
//			index++;
//		}
//	}
	
	@Override
	public void setData(Module module, int index, SFStructureData data) {
		switch (module) {
			case LIGHT:
					setData(lightStructureUniforms[index], data);
				break;
			case MATERIAL:
				setData(materialStructureUniforms[index], data);
				break;
			case TRANSFORM:
				setData(transformStructureUniforms[index], data);
				break;
		}
	}
	
	
	public void setData(int[] uniforms,SFStructureData data){
		
		if(uniforms!=null){
			for (int i=0; i < uniforms.length; i++) {
				SFValuenf v=data.getValue(i);
				if(v.getSize()==3){
					SFGL2.getGL().glUniform3f(uniforms[i],v.get()[0],v.get()[1],v.get()[2]);
				}else if(v.getSize()==1){
					SFGL2.getGL().glUniform1f(uniforms[i],v.get()[0]);
				}
			}
		}
	}
	
	@Override
	public void sendVertex(SFValuenf value) {
		int uniformIndex=0;
		Integer[] uniform=gridUniforms.get(uniformIndex);
		switch (value.getSize()) {
			case 1:
				SFGL2.getGL().glUniform1f(uniform[0],value.get()[0]);
			break;
			case 2:
				SFGL2.getGL().glUniform2f(uniform[0],value.get()[0],value.get()[1]);
				break;
			case 3:
				SFGL2.getGL().glUniform3f(uniform[0],value.get()[0],value.get()[1],value.get()[2]);
				break;
			default:
				break;
		}
	}
	
	/* (non-Javadoc)
	 * @see shadow.pipeline.openGL20.SFProgramDataModel#setPrimitiveData(shadow.pipeline.SFPrimitiveIndices, shadow.pipeline.openGL20.SFGL20ListData, shadow.pipeline.openGL20.SFGL20ListData)
	 */
	@Override
	public void setIndexedData(SFPrimitiveIndices indices,
			SFGL20ValuenfArray[] datas,SFPrimitive primitive) {
		
		int uniformIndex=0;

		int[] idx=indices.getPrimitiveIndices();
		int[] positions=primitive.getIndicesPositions();
		int[] sizes=primitive.getIndicesSizes();
		
		for (int i = 0; i < datas.length && i<gridUniforms.size(); i++) {
			
			int position=positions[i];
			int size=sizes[i];
			SFGL20ValuenfArray data=datas[i];
			
			
			Integer[] uniform=gridUniforms.get(uniformIndex);
			short type=primitive.getType(i);
			switch (type) {
				case SFPipelineRegister.GLOBAL_FLOAT:
					for (int j=0; j < size; j++) {
						SFValuenf pv=(data.getValue(idx[j+position]));
						SFGL2.getGL().glUniform1f(uniform[j],pv.get()[0]);
					}
				break;
				case SFPipelineRegister.GLOBAL_FLOAT3:
						for (int j=0; j < size; j++) {
							SFValuenf pv=(data.getValue(idx[j+position]));
							SFGL2.getGL().glUniform3f(uniform[j],pv.get()[0],pv.get()[1],pv.get()[2]);
						}
					break;
				case SFPipelineRegister.GLOBAL_FLOAT2:
					for (int j=0; j < size; j++) {
						SFValuenf pv=(data.getValue(idx[j+position]));
						SFGL2.getGL().glUniform2f(uniform[j],pv.get()[0],pv.get()[1]);
					}
				break;
				default:
					break;
			}
			uniformIndex++;
		}

	}
	
	@Override
	public void setTransformData(float[] transform) {
		
		SFVertex3f v1=new SFVertex3f(transform[0],transform[1],transform[2]);
		SFVertex3f v2=new SFVertex3f(transform[3],transform[4],transform[5]);
		SFVertex3f v3=new SFVertex3f(transform[6],transform[7],transform[8]);
		//TODO : work on this normalize!
//		v1.normalize3f();
//		v2.normalize3f();
//		v3.normalize3f();

		float vModelview[]={//you know this is not going to work properly, but let's say it's right most of the times..
				v1.getX(),	v2.getX(),	v3.getX(),0,
				v1.getY(),	v2.getY(),	v3.getY(),0,
				v1.getZ(),	v2.getZ(),	v3.getZ(),0,
				0,0,0,1
		};
		float modelview[]={//you know this is not going to work properly, but let's say it's right most of the times..
				transform[0],	transform[3],	transform[6],0,
				transform[1],	transform[4],	transform[7],0,
				transform[2],	transform[5],	transform[8],0,
				transform[9],	transform[10],	transform[11],1
		};

		//All transforms are identity no more..
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[1],1,false,modelview,0);
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[2],1,false,vModelview,0);
	}

	public void setupProjetion(float projection[]){
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[0],1,false,projection,0);
	}

	
}