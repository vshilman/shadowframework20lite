package test;


import java.awt.event.KeyEvent;

import shadow.geometry.geometries.SFMeshGeometry;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.openGL20.tutorials.geometriesExample.Cone;
import shadow.pipeline.openGL20.tutorials.geometriesExample.LateralTube;
import shadow.pipeline.openGL20.tutorials.geometriesExample.Mushroom;
import shadow.pipeline.openGL20.tutorials.geometriesExample.StrangeCylinder;
import shadow.pipeline.openGL20.tutorials.geometriesExample.StrangeGlass;
import shadow.pipeline.openGL20.tutorials.utils.SFBasicTutorial;
import shadow.pipeline.openGL20.tutorials.utils.SFTutorialsUtilities;
import shadow.renderer.SFStructureReference;

public class Tut02MoreGeometries extends SFBasicTutorial{

	private static SFMeshGeometry[] geometries;
	private int geometriesIndex=0;
	
	public Tut02MoreGeometries(SFProgram program,
			SFStructureArray lightData, SFStructureReference lightReference,
			SFStructureArray materialData,SFStructureReference materialReference,
			SFPrimitiveArray primitiveData, int elementIndex, int elementSize) {
		super(program, lightData, lightReference, materialData, materialReference,
				primitiveData, elementIndex, elementSize);
	}

	public static void main(String[] args) {

		SFGL20Pipeline.setup();
		
		SFPrimitive primitive=new SFPrimitive();
		String[] materials={"BasicMat"};
		SFProgram program=SFTutorialsUtilities.generateProgram("data/primitive", materials, 
				"BasicLSPN", primitive, "Triangle2", "Triangle2", "BasicTess");

		geometries=generateGeometries(primitive);
		
		SFStructureArray materialArray=SFTutorialsUtilities.generateMaterialData(program, 0, 0);
		SFVertex3f[] materialData={new SFVertex3f(1,1,0),new SFVertex3f(0.1f,0.1f,0.1f)};
		SFStructureReference materialReference=SFTutorialsUtilities.generateStructureDataReference(program, materialArray, materialData);

		SFStructureArray lightArray=SFTutorialsUtilities.generateLightData(program, 0);
		SFVertex3f[] lightData={new SFVertex3f(2, 1, 1),new SFVertex3f(1, 1, -1)};
		SFStructureReference lightReference=SFTutorialsUtilities.generateStructureDataReference(program, lightArray, lightData);
		
		Tut02MoreGeometries tut01CurvedTubeFunction=new Tut02MoreGeometries(program, 
				lightArray, lightReference, 
				materialArray, materialReference,
				geometries[0].getArray(), geometries[0].getFirstElement(),geometries[0].getElementsCount());
		
		tut01CurvedTubeFunction.prepareFrame("Curved Tube Function", 600, 600);
	}


	public static SFMeshGeometry[] generateGeometries(SFPrimitive primitive){
		SFMeshGeometry[] geometries= {
				(new StrangeCylinder()).generateGeometry(primitive),
				(new Cone()).generateGeometry(primitive),
				(new LateralTube()).generateGeometry(primitive),
				(new StrangeGlass()).generateGeometry(primitive),
				(new Mushroom()).generateGeometry(primitive)
		};
		return geometries;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		if(e.getKeyCode()==KeyEvent.VK_PLUS){
			geometriesIndex++;
			if(geometriesIndex>=geometries.length){
				geometriesIndex=0;
			}
			setGeometry(geometries[geometriesIndex]);
		}
		if(e.getKeyCode()==KeyEvent.VK_MINUS){
			geometriesIndex--;
			if(geometriesIndex<0){
				geometriesIndex=geometries.length-1;
			}
			setGeometry(geometries[geometriesIndex]);
		}
	}
}
