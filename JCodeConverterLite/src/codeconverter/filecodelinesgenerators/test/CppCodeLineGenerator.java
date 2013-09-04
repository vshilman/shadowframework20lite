package codeconverter.filecodelinesgenerators.test;

import java.io.InputStream;
import java.util.List;

import codeconverter.filecodelinesgenerators.CodeLineGenerator;
import codeconverter.filecodelinesgenerators.FileCodeLine;



public class CppCodeLineGenerator extends DefaultCodeLineGenerator{


	public CppCodeLineGenerator(){

	}



	public  List<FileCodeLine> generateFileCodeLines(InputStream stream) {
		List<FileCodeLine> list= super.generateFileCodeLines(stream);

		for (int i = 0; i < list.size(); i++) {
			String s=list.get(i).getCode();
			if(s.trim().startsWith("#")){
				list.get(i).setCode(s+";");  //adding a ; for block recognition
			}
		}

		return list;
	}


	public CodeLineGenerator clone(){
		return new CppCodeLineGenerator();
	}




}
