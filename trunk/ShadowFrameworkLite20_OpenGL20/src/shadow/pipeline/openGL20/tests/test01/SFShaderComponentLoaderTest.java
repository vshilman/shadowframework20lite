package shadow.pipeline.openGL20.tests.test01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

/**
 * @author Alessandro Martinelli
 */
public class SFShaderComponentLoaderTest {

	public static void main(String[] args) {
		
		try {
			SFGL20Pipeline.setup();
			SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"));
			
			System.out.println("Load succesfull");
			
			String tessellator="BasicTess";
			//String primitiveP="Triangle2";
			//String primitiveN="Triangle";
			//String transformP="BasicP";
			//String transformN="BasicN";
			String material="BasicMat";
			String light="BasicLSPN";
			
			//SFPipelineRegister registers[]={SFPipelineRegister.getFromName("P"),SFPipelineRegister.getFromName("N")};
			//String[] primitivesP={primitiveP};
			//String[] primitivesN={primitiveN};
			//String[][] primitives={primitivesP,primitivesN};
			//String[] transform={transformP,transformN};
			String[] materials={material};
			
			
			SFPrimitive primitive=new SFPrimitive();
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("P"), (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
			primitive.addPrimitiveElement(SFPipelineRegister.getFromName("N"), (SFProgramComponent)(SFPipeline.getModule("Triangle")));
			primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));

			System.out.println("Primitive built");
			
			//registers,
			//primitives, transform
			SFProgram program=SFPipeline.getStaticProgram( primitive, materials, light);

			System.out.println("Program Built");
			
			System.out.println(program);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			ArrayList<String> errors=e.getList();
			for (int i = 0; i < errors.size(); i++) {
				System.err.println(errors.get(i));
			}
		} catch(SFException moduleException){
			moduleException.printStackTrace();
		}
	}
}