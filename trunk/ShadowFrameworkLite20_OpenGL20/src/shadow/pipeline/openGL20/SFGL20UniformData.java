package shadow.pipeline.openGL20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipelineGridInstance;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.java.SFGL20ValuenfArray;
import shadow.pipeline.java.SFProgramDataModel;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

public class SFGL20UniformData implements SFProgramDataModel {
	
	private SFGLSLSet program;
	
	private HashMap<SFPipelineStructure, Integer[]> structureUniforms=new HashMap<SFPipelineStructure, Integer[]>();
	//private HashMap<String, Integer[]> gridUniforms=new HashMap<String, Integer[]>();
	private List<Integer[]> gridUniforms=new ArrayList<Integer[]>();
	
	public int[] mainUniforms;
	
	private Integer[] getUniforms(String prefix,SFPipelineStructureInstance instance){
		Integer[] data=new Integer[instance.size()];
		List<SFParameteri> parameters=instance.getParameters();
		for (int i = 0; i < data.length; i++) {
			SFParameteri param=parameters.get(i);
			String name=prefix+param.getName();
			data[i]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),name);
		}
		return data;
	}
	
	private Integer[] getUniforms(String prefix,SFPipelineGridInstance instance){
		Integer[] data=new Integer[instance.size()];
		List<SFParameteri> parameters=instance.getParameters();
		for (int i = 0; i < data.length; i++) {
			SFParameteri param=parameters.get(i);
			String name=prefix+param.getName();
			data[i]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),name);
		}
		return data;
	}

	void evaluateUniforms(SFGL20AbstractProgram program) {
		this.program=program;
		
		//Primitive Uniforms
		
		gridUniforms.clear();
		structureUniforms.clear();
		
		SFPrimitive primitive=program.getPrimitive();
		if(primitive!=null){
			for (int i = 0; i < primitive.getBlocks().length; i++) {
				SFProgramComponent component=primitive.getComponents()[i];
				SFPipelineRegister register=primitive.getBlocks()[i].getRegister();
				List<SFPipelineStructureInstance> structures=component.getStructures();
				for (SFPipelineStructureInstance sfPipelineStructureInstance : structures) {
					structureUniforms.put(sfPipelineStructureInstance.getStructure(), getUniforms(register.getName(), sfPipelineStructureInstance));
				}
				List<SFPipelineGridInstance> grids=component.getGrid();
				for (SFPipelineGridInstance sfPipelineGridInstance : grids) {
					gridUniforms.add(getUniforms(register.getName(), sfPipelineGridInstance));
				}
			}
		}
		
		List<SFProgramComponent> materials=program.getMaterials();
		for (SFProgramComponent component : materials) {
			List<SFPipelineStructureInstance> structures=component.getStructures();
			for (SFPipelineStructureInstance sfPipelineStructureInstance : structures) {
				structureUniforms.put(sfPipelineStructureInstance.getStructure(), getUniforms("", sfPipelineStructureInstance));
			}
		}
		
		List<SFPipelineStructureInstance> structures=program.getLight().getStructures();
		for (SFPipelineStructureInstance sfPipelineStructureInstance : structures) {
			structureUniforms.put(sfPipelineStructureInstance.getStructure(), getUniforms("", sfPipelineStructureInstance));
		}
		
		mainUniforms=new int[3];
		mainUniforms[0]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),"projection");
		mainUniforms[1]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),"modelview");
		mainUniforms[2]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),"vectorsModelview");
		
		evaluateTextureUniforms(program);
		
		
	}
	

	void evaluateUniforms(SFGL20ImageProgram program) {
		this.program=program;
		//Primitive Uniforms
		
		gridUniforms.clear();
		structureUniforms.clear();
		
		List<SFProgramComponent> materials=program.getMaterials();
		for (SFProgramComponent component : materials) {
			List<SFPipelineStructureInstance> structures=component.getStructures();
			for (SFPipelineStructureInstance sfPipelineStructureInstance : structures) {
				structureUniforms.put(sfPipelineStructureInstance.getStructure(), getUniforms("", sfPipelineStructureInstance));
			}
		}
		
		List<SFPipelineStructureInstance> structures=program.getLight().getStructures();
		for (SFPipelineStructureInstance sfPipelineStructureInstance : structures) {
			structureUniforms.put(sfPipelineStructureInstance.getStructure(), getUniforms("", sfPipelineStructureInstance));
		}

		evaluateTextureUniforms(program);
	}

	private void evaluateTextureUniforms(SFGLSLSet program) {
		int index=0;
		while(index<8){
			
			int textureLevel=SFGL2.getGL().glGetUniformLocation(program.getProgram(),"texture"+index);
			if(textureLevel>=0)
				SFGL2.getGL().glUniform1i(textureLevel, index);
			
			index++;
		}
	}
	
	/* (non-Javadoc)
	 * @see shadow.pipeline.openGL20.SFProgramDataModel#setData(shadow.pipeline.SFPipelineStructure, shadow.pipeline.SFStructureData)
	 */
	@Override
	public void setData(SFPipelineStructure structure, SFStructureData data){
		
		Integer[] uniforms=structureUniforms.get(structure);
		
		for (int i=0; i < uniforms.length; i++) {
			SFValuenf v=data.getValue(i);
			if(v.getSize()==3){
				SFVertex3f v3=(SFVertex3f)(v);
				SFGL2.getGL().glUniform3f(uniforms[i],v3.getX(),v3.getY(),v3.getZ());
			}
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
		
		for (int i = 0; i < datas.length; i++) {
			
			int position=positions[i];
			int size=sizes[i];
			SFGL20ValuenfArray data=datas[i];
			
			
			Integer[] uniform=gridUniforms.get(uniformIndex);
			short type=primitive.getGridInstances()[i].getType();
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

		float vModelview[]={//you know this is not going to work properly, but let's say it's right most of the times..
				transform[0],	transform[3],	transform[6],0,
				transform[1],	transform[4],	transform[7],0,
				transform[2],	transform[5],	transform[8],0,
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

	/* (non-Javadoc)
	 * @see shadow.pipeline.openGL20.SFProgramDataModel#setTransformData(float, float, float)
	 */
	@Override
	public void setTransformData( float x, float y, float z, float rx,float ry,float rz){
		
		float c1=(float)(Math.cos(rz));
		float s1=(float)(Math.sin(rz));
		float c2=(float)(Math.cos(rx));
		float s2=(float)(Math.sin(rx));
		float c3=(float)(Math.cos(ry));
		float s3=(float)(Math.sin(ry));
		
		float modelview[]={
				c1*c3+s1*s2*s3,		c1*s2*s3-c3*s1,	c2*s3,0,
				c2*s1,				c1*c2,			-s2,0,
				c3*s1*s2-c1*s3,		s1*s3+c1*c3*s2,	c2*c3,0,
				x,y,z,1
		};
		
		float vModelview[]={
				c1*c3+s1*s2*s3,		c1*s2*s3-c3*s1,	c2*s3,0,
				c2*s1,				c1*c2,			-s2,0,
				c3*s1*s2-c1*s3,		s1*s3+c1*c3*s2,	c2*c3,0,
				0,0,0,1
		};
		
		//All transforms are identity no more..
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[1],1,false,modelview,0);
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[2],1,false,vModelview,0);
	}

	public void setupProjetion(float projection[]){
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[0],1,false,projection,0);
	}
}