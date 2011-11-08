package shadow.pipeline.openGL20;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.loader.parser.SFPipelineGridInstance;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

public class SFGL20UniformData implements SFProgramDataModel {
	
	private SFGL20Program program;
	
	private HashMap<SFPipelineStructureInstance, Integer[]> structureUniforms=new HashMap<SFPipelineStructureInstance, Integer[]>();
	private HashMap<SFPipelineRegister, Integer[]> gridUniforms=new HashMap<SFPipelineRegister, Integer[]>();
	
//	public int[][] primiviteUniforms;
//	public int[][] materialUniforms;
//	public int[][] lightUniforms;
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
				structureUniforms.put(sfPipelineStructureInstance, getUniforms(register.getName(), sfPipelineStructureInstance));
			}
			SFPipelineGridInstance grids=component.getGrid();
			gridUniforms.put(register, getUniforms(register.getName(), grids));
		}
		
		List<SFProgramComponent> materials=program.getMaterials();
		for (SFProgramComponent component : materials) {
			List<SFPipelineStructureInstance> structures=component.getStructures();
			for (SFPipelineStructureInstance sfPipelineStructureInstance : structures) {
				structureUniforms.put(sfPipelineStructureInstance, getUniforms("", sfPipelineStructureInstance));
			}
		}
		
		List<SFPipelineStructureInstance> structures=program.getLight().getStructures();
		for (SFPipelineStructureInstance sfPipelineStructureInstance : structures) {
			structureUniforms.put(sfPipelineStructureInstance, getUniforms("", sfPipelineStructureInstance));
		}
		
		mainUniforms=new int[3];
		mainUniforms[0]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),"projection");
		mainUniforms[1]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),"modelview");
		mainUniforms[2]=SFGL2.getGL().glGetUniformLocation(program.getProgram(),"vectorsModelview");
	}

	/* (non-Javadoc)
	 * @see shadow.pipeline.openGL20.SFProgramDataModel#setData(shadow.pipeline.SFPipelineStructure, shadow.pipeline.SFStructureData)
	 */
	@Override
	public void setData(SFPipelineStructureInstance structure, SFStructureData data){
		
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
	public void setTransformData( float x, float y, float z){
		
		float matrix[]={
				1,0,0,0,
				0,1,0,0,
				0,0,1,0,
				0,0,0,1
		};
		
		//All transforms are identity now
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[0],1,false,matrix,0);
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[1],1,false,matrix,0);
		SFGL2.getGL().glUniformMatrix4fv(mainUniforms[2],1,false,matrix,0);
	}

	
}