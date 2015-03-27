package shadow.integration.examples;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import sfogl.integration.ShadingParameter;
import sfogl.integration.ShadingParameter.ParameterType;
import sfogl.integration.ShadingProgram;
import sfogl2.SFOGLShader;
import shadow.system.SFContext;

public class ShadersFactory {

	private static final String shadersPath="shaders/";

	public static String loadText(String filename){
		String text="";
		
		String line=null;
		try {
			BufferedReader reader=new BufferedReader(new FileReader(filename));
			line = reader.readLine();
			while(line!=null){
				
				text+=line+"\n";
				
				line=reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
	
	private static ShadingParameter pColor=new ShadingParameter("color", ParameterType.GLOBAL_FLOAT4);
	private static ShadingParameter pTextureMaterial=new ShadingParameter("textureMaterial", ParameterType.GLOBAL_TEXTURE);

	public static void loadPipelineShaders(SFContext context){

		loadShaderModel(context,"shader01", shadersPath+"goldFragmentShader.fsh", shadersPath+"goldFragmentShader.fsh", pColor);
		loadShaderModel(context,"shader02", shadersPath+"stdVertexShader.fsh", shadersPath+"textureFragmentShader.fsh", pColor, pTextureMaterial);
		loadShaderModel(context,"shader03", shadersPath+"stdVertexShader.fsh", shadersPath+"goldFragmentShader2.fsh", pColor);
		
	}
	
	public static void loadShaderModel(SFContext context,String name,String vertexShaderFile,String fragmentShaderFile,ShadingParameter... parameters){
		String fragmentShader=loadText(fragmentShaderFile);
		
		String vertexShader=loadText(vertexShaderFile);
		
		SFOGLShader shader=new SFOGLShader(vertexShader, fragmentShader);
		
		ShadingProgram program = new ShadingProgram(shader);
		
		//TODO : to be finished
		//program.set
		//Pipeline.getPipeline().put(name, shader);
	}
}
