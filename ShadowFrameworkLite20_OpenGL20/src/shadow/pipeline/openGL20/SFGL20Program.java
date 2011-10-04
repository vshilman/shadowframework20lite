/*
	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
    Copyright (C) 2010  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

    This file is part of The Shadow Framework.

    The Shadow Framework is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The Shadow Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
*/
package shadow.pipeline.openGL20;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.media.opengl.GL2;

import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFFunction;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.loader.parser.SFPipelineStructureInstance;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

/* GTL: Geometry-Texturing-Lighting
 */
public class SFGL20Program extends SFGLSLSet implements SFProgram{
	
	private ArrayList<SFProgramComponent> vertexShader=new ArrayList<SFProgramComponent>();
	private ArrayList<SFProgramComponent> fragmentShader=new ArrayList<SFProgramComponent>();
	private Collection<SFParameteri> vset=new LinkedList<SFParameteri>();
	private Collection<SFParameteri> fset=new LinkedList<SFParameteri>();
	private SFPrimitive primitive;
	private ArrayList<SFProgramComponent> materials=new ArrayList<SFProgramComponent>();
	private ArrayList<SFProgramComponent> lights=new ArrayList<SFProgramComponent>();
	private boolean registeredUniforms=false;
	
	private HashMap<SFProgramComponent, String> outputName=new HashMap<SFProgramComponent, String>();
	private HashMap<SFProgramComponent, SFPipelineRegister> registers=new HashMap<SFProgramComponent, SFPipelineRegister>();
	
	private int primiviteUniforms[][];
	private int materialUniforms[][];
	private int lightUniforms[][];
	private int mainUniforms[];
	
	public void clearFragmentShader(){
		fragmentShader.clear();
	}
	public void clearVertexShader(){
		vertexShader.clear();
	}
	
	public void addToFragmentShader(SFProgramComponent component){
		fragmentShader.add(component);
	}
	public void addToVertexShader(SFProgramComponent component){
		vertexShader.add(component);
	}

	public String loadFragmentShaderText() {
		fset.clear();
		return getShaderText(fragmentShader,fset);
	}
	
	
	public String loadVertexShaderText() {
		vset.clear();
		return getShaderText(vertexShader,vset);
	}

	public void write(){
		System.err.println("Vertex Program");
		for (int i = 0; i < vertexShader.size(); i++) {
			System.err.println("\t\tvertexShader.get(i) " + vertexShader.get(i));
		}
		System.err.println("Fragment Program");
		for (int i = 0; i < fragmentShader.size(); i++) {
			System.err.println("\t\tfragmentShader(i) " + fragmentShader.get(i));
		}
	}	
	
	public String getShaderText(ArrayList<SFProgramComponent> list,Collection<SFParameteri> shaderParametersSet) {
		return SFShaderGL20Implementor.getShaderText(this,list);
	}
	
	public int getUniformCode(GL2 gl,SFParameteri uniform){
		return gl.glGetUniformLocationARB(this.getProgram(),uniform.getName());
	}

	@Override
	public void setLightStep(SFProgramComponent lightStep) {
		addToFragmentShader(lightStep);
		lights.add(lightStep);
	}
	
	@Override
	public void setMaterial(int index, SFProgramComponent material) {
		addToFragmentShader(material);
		materials.add(material);
	}
	
	
	@Override
	public void setPrimitive(SFPrimitive primitive) {
		// TODO Auto-generated method stub
		
		this.primitive=primitive;

		addToVertexShader(primitive.getTessellator());
		
		Map<SFPipelineRegister, SFProgramComponent> parameters=primitive.getPrimitiveMap();
		Iterator<Entry<SFPipelineRegister, SFProgramComponent>> iterator=parameters.entrySet().iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			Entry<SFPipelineRegister, SFProgramComponent> entry=iterator.next();
			SFProgramComponent pr=entry.getValue();
			SFPipelineRegister outputRegister=entry.getKey();
			//outputName == registerName, no need to make it more complex 
			outputName.put(pr, outputRegister.getName());
			registers.put(pr,outputRegister);
			addToVertexShader(pr);
		}
	}
	
	/*@Override
	public void setPrimitive(SFPipelineRegister outputRegister, int i,
			SFProgramComponent primitive) {
		outputName.put(primitive, outputRegister.getName()+i);
		registers.put(primitive,outputRegister);
		addToVertexShader(primitive);
	}*/
	
	/*@Override
	public void setTransforms(SFPipelineRegister outPipelineRegister,SFProgramComponent transform) {
		outputName.put(transform, outPipelineRegister.getName());
		registers.put(transform,outPipelineRegister);
		addToVertexShader(transform);
	}*/
	
	@Override
	public String toString() {
		this.checkComponent();
		String vShader=loadVertexShaderText();
		String fShader=loadFragmentShaderText();
		String toString="---------\nvShader\n---------:\n"+vShader+"---------\nfShader\n---------:\n"+fShader;
		return toString;
	}
	
	public ArrayList<SFProgramComponent> getVertexShader() {
		return vertexShader;
	}
	public ArrayList<SFProgramComponent> getFragmentShader() {
		return fragmentShader;
	}
	

	public SFPipelineRegister getOutputRegister(SFProgramComponent component) {
		SFPipelineRegister register=registers.get(component);
		return register;
	}
	
	public String getOutputName(SFProgramComponent component) {
		String suffix=outputName.get(component);
		if(suffix==null)
			suffix="";
		return suffix;
	}
	
	public SFPipelineRegister getRegister(SFProgramComponent component) {
		SFPipelineRegister register=registers.get(component);
		if(register==null){
			try {		
				register=SFPipelineRegister.getFromName("<>");
			} catch (SFException e) {
				//register=null; //
			}	
		}
		
		return register;
	}
	
	private static SFProgramComponent defaultPositionTransform=new SFProgramComponent(){{
			try {
				LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
				set.add(SFPipelineRegister.getFromName("modelview"));
				set.add(SFPipelineRegister.getFromName("P"));
				SFPipelineRegister register=SFPipelineRegister.getFromName("position");
				addRegister(register);
				addCodeString(new SFFunction(register,"modelview*projection*P",set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};
	
	private static SFProgramComponent defaultNormalTransform=new SFProgramComponent(){{
			try {
				LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
				set.add(SFPipelineRegister.getFromName("vectorsModelview"));
				set.add(SFPipelineRegister.getFromName("N"));
				SFPipelineRegister register=SFPipelineRegister.getFromName("normal");
				addRegister(register);
				addCodeString(new SFFunction(register,"vectorsModelview*N",set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};
	
	private static SFProgramComponent defaultGLPosition=new SFProgramComponent(){{
			try {
				LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
				set.add(SFPipelineRegister.getFromName("position"));
				set.add(SFPipelineRegister.getFromName("projection"));
				SFPipelineRegister glPosition=new SFPipelineRegister(SFParameteri.GLOBAL_FLOAT4,"gl_Position",SFPipelineRegister.WRITE_ON_TRANSFORM);
				addCodeString(new SFFunction(glPosition,"projection*position",set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};
	
	private static SFProgramComponent defaultFragmentColor=new SFProgramComponent(){{
	try {
		LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
		set.add(SFPipelineRegister.getFromName("fColor"));
		SFPipelineRegister glPosition=new SFPipelineRegister(SFParameteri.GLOBAL_FLOAT4,
				"gl_FragColor",SFPipelineRegister.WRITE_ON_LIGHTING);
		addCodeString(new SFFunction(glPosition,"fColor",set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};
	
	private void checkComponent(){
		if(!vertexShader.contains(defaultPositionTransform)){
			vertexShader.add(defaultPositionTransform);
			try {
				registers.put(defaultPositionTransform,SFPipelineRegister.getFromName("position"));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}	
		if(!vertexShader.contains(defaultNormalTransform)){
			vertexShader.add(defaultNormalTransform);
			try {
				registers.put(defaultPositionTransform,SFPipelineRegister.getFromName("normal"));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
		if(!vertexShader.contains(defaultGLPosition))
			vertexShader.add(defaultGLPosition);
		if(!fragmentShader.contains(defaultFragmentColor))
			fragmentShader.add(defaultFragmentColor);
	}
	
	@Override
	public void load() {
		
		//TODO prepare DATA trace. When mathGraphics module loads data
		//You should be able to attach it
	
		this.checkComponent();
		
		this.init();
		this.apply();
		
		if(!registeredUniforms){
		
			Map<SFPipelineRegister, SFProgramComponent> map=primitive.getPrimitiveMap();
			
			//Primitive Uniforms
			primiviteUniforms=new int[map.size()][];
			
			Collection<SFProgramComponent> collection=map.values();
			int index=0;
			
			for (Iterator<SFProgramComponent> iterator = collection.iterator(); 
					iterator.hasNext();index++) {
				SFProgramComponent component=iterator.next();
				SFPipelineRegister register=getOutputRegister(component);
				
				System.out.println("component.getName() "+component.getName());
				//NOTE:this is a bit long.Its based on the idea that primitive ProgramComponent are 
				//using always only 1 grid; that's the reason of getGrids().iterator().next().
				LinkedList<SFParameteri> parameters=component.getGrids().iterator().next().getParameters();
				
				primiviteUniforms[index]=new int[parameters.size()];
				for (int i=0; i < parameters.size(); i++) {
					SFParameteri param=parameters.get(i);
					String name=register.getName()+param.getName();
					primiviteUniforms[index][i]=SFGL2.getGL().glGetUniformLocation(this.getProgram(),name);
				}
			}
		
			mainUniforms=new int[3];
			mainUniforms[0]=SFGL2.getGL().glGetUniformLocation(this.getProgram(),"projection");
			mainUniforms[1]=SFGL2.getGL().glGetUniformLocation(this.getProgram(),"modelview");
			mainUniforms[2]=SFGL2.getGL().glGetUniformLocation(this.getProgram(),"vectorsModelview");
			
			materialUniforms=new int[materials.size()][];
			for (int i=0; i < materialUniforms.length; i++) {
				SFPipelineStructureInstance structure=materials.get(i).getStructures().iterator().next();
				Collection<SFParameteri> parameters=structure.getParameters();
				int idx=0;
				materialUniforms[i]=new int[parameters.size()];
				for (Iterator<SFParameteri> iterator=parameters.iterator(); iterator
						.hasNext();idx++) {
					SFParameteri sfParameteri= iterator.next();
					materialUniforms[i][idx]=SFGL2.getGL().glGetUniformLocation(this.getProgram(),sfParameteri.getName());
				}
			}
			
			lightUniforms=new int[lights.size()][];
			for (int i=0; i < lightUniforms.length; i++) {
				SFPipelineStructureInstance structure=lights.get(i).getStructures().iterator().next();
				Collection<SFParameteri> parameters=structure.getParameters();
				int idx=0;
				lightUniforms[i]=new int[parameters.size()];
				for (Iterator<SFParameteri> iterator=parameters.iterator(); iterator
						.hasNext();idx++) {
					SFParameteri sfParameteri= iterator.next();
					lightUniforms[i][idx]=SFGL2.getGL().glGetUniformLocation(this.getProgram(),sfParameteri.getName());
				}
			}
			
			registeredUniforms=true;
		}	
	}

	public void setData(SFPipelineStructure structure,SFStructureData data){
		int uniforms[]=null;
		if(structure==materials.get(0).getStructures().iterator().next().getStructure()){
			//TODO : not working with more materials
			uniforms=materialUniforms[0];
		}else{
			//TODO : not working with more materials
			uniforms=lightUniforms[0];
		}
		for (int i=0; i < uniforms.length; i++) {
			SFValuenf v=data.getValue(i);
			if(v.getSize()==3){
				SFVertex3f v3=(SFVertex3f)(v);
				SFGL2.getGL().glUniform3f(uniforms[0],v3.getX(),v3.getY(),v3.getZ());
			}
		}
	}
	
	public void setPrimitiveData(SFPrimitiveIndices indices,SFGL20ListData<SFValuenf> p,SFGL20ListData<SFValuenf> n){
		
		//Note we are assuming primitive is P-N, not all primitives are P-N
		int[] pIndices=indices.getPrimitiveIndices()[1];
		int[] nIndices=indices.getPrimitiveIndices()[0];
		
		for (int i=0; i < pIndices.length; i++) {
			SFVertex3f pv=(SFVertex3f)(p.getValue(pIndices[i]));
			SFGL2.getGL().glUniform3f(primiviteUniforms[1][i],pv.getX(),pv.getY(),pv.getZ());
		}
		
		for (int i=0; i < nIndices.length; i++) {
			SFVertex3f nv=(SFVertex3f)(n.getValue(nIndices[i]));
			SFGL2.getGL().glUniform3f(primiviteUniforms[0][i],nv.getX(),nv.getY(),nv.getZ());
		}
		
	}

	//TODO: set Transform data is going to accept as parameters
	//a Camera and ModedelviewData 
	public void setTransformData(float x,float y,float z){
		
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

