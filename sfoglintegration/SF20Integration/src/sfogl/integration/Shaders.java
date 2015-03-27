package sfogl.integration;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import sfogl2.SFOGLShader;

public class Shaders {


    public static ShadingProgram loadShaderModel(String vertexShader,String fragmentShaderFile,ShadingParameter... parameters){

        SFOGLShader shader=new SFOGLShader(vertexShader, fragmentShaderFile);
        ShadingProgram program=new ShadingProgram(shader);
        program.loadData(parameters);
        program.init();
        return program;
    }

    public static String loadText(String filename){
        String text="";

        String line=null;
        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
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


}
