package shadow.pipeline.openGL20;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipelineGridInstance;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

public class SFGL20UniformData implements SFProgramDataModel {
	
	private SFGLSLSet program;
	
	private HashMap<SFPipelineStructure, Integer[]> structureUniforms=new HashMap<SFPipelineStructure, Integer[]>();
	private HashMap<SFPipelineRegister, Integer[]> gridUniforms=new HashMap<SFPipelineRegister, Integer[]>();
	
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

	void evaluateUniforms(SFGL20Program program) {
		
		this.program=program;
		List<Entry<SFPipelineRegister, SFProgramComponent>> map=program.primitive.getPrimitiveMap();

		//Primitive Uniforms
		
		gridUniforms.clear();
		structureUniforms.clear();
		
		for (Entry<SFPipelineRegister, SFProgramComponent> entry : map) {
			SFProgramComponent component=entry.getValue();
			//TODO: this is the first problem
			SFPipelineRegister register=entry.getKey();//program.getOutputRegister(component);
			List<SFPipelineStructureInstance> structures=component.getStructures();
			for (SFPipelineStructureInstance sfPipelineStructureInstance : structures) {
				structureUniforms.put(sfPipelineStructureInstance.getStructure(), getUniforms(register.getName(), sfPipelineStructureInstance));
			}
			SFPipelineGridInstance grids=component.getGrid();
			gridUniforms.put(register, getUniforms(register.getName(), grids));
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
	
	public Integer[][] getAllPrimitiveUniforms(SFPipelineRegister[] registers){
		Integer[][] uniforms=new Integer[registers.length][];
		for (int i = 0; i < uniforms.length; i++) {
			uniforms[i]=gridUniforms.get(registers[i]);
		}
		return uniforms;
	}
	
	/* (non-Javadoc)
	 * @see shadow.pipeline.openGL20.SFProgramDataModel#setPrimitiveData(shadow.pipeline.SFPrimitiveIndices, shadow.pipeline.openGL20.SFGL20ListData, shadow.pipeline.openGL20.SFGL20ListData)
	 */
	@Override
	public void setIndexedData(SFPrimitiveIndices indices,
			SFGL20ListData<SFValuenf>[] datas,Integer[][] uniforms,SFPipelineRegister[] registers){
		
		for (int i = 0; i < datas.length; i++) {
			Integer[] uniform=uniforms[i];
			SFGL20ListData<SFValuenf> data=datas[i];
			int[] idx=indices.getPrimitiveIndices()[i];

			switch (registers[i].getType()) {
				case SFPipelineRegister.GLOBAL_FLOAT3:
						for (int j=0; j < idx.length; j++) {
							SFVertex3f pv=(SFVertex3f)(data.getValue(idx[j]));
							SFGL2.getGL().glUniform3f(uniform[j],pv.getX(),pv.getY(),pv.getZ());
						}
					break;
				case SFPipelineRegister.GLOBAL_FLOAT2:
					for (int j=0; j < idx.length; j++) {
						SFVertex2f pv=(SFVertex2f)(data.getValue(idx[j]));
						SFGL2.getGL().glUniform2f(uniform[j],pv.getX(),pv.getY());
					}
				break;
				default:
					break;
			}
			
		}

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
		
		float projection[]={
				1,0,0,0,
				0,1,0,0,
				0,0,1,0,
				0,0,0,1
		};

		float vModelview[]={
				c1*c3+s1*s2*s3,		c1*s2*s3-c3*s1,	c2*s3,0,
				c2*s1,				c1*c2,			-s2,0,
				c3*s1*s2-c1*s3,		s1*s3+c1*c3*s2,	c2*c3,0,
				0,0,0,1
		};
		
		//All transforms are identity no more..
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[0],1,false,projection,0);
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[1],1,false,modelview,0);
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[2],1,false,vModelview,0);
	}

	public void setupProjetion(float projection[]){
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[0],1,false,projection,0);
	}
}