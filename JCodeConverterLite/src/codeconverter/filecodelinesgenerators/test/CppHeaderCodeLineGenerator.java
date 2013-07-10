package codeconverter.filecodelinesgenerators.test;

import java.io.InputStream;
import java.util.List;

import codeconverter.filecodelinesgenerators.CodeLineGenerator;
import codeconverter.filecodelinesgenerators.FileCodeLine;

public class CppHeaderCodeLineGenerator extends CppCodeLineGenerator{

	private String[] toIgnore=new String[]{"public:","protected:","private:"};

	protected CppHeaderCodeLineGenerator(){

	}


	@Override
	public List<FileCodeLine> generateFileCodeLines(InputStream stream) {
		List<FileCodeLine> list= super.generateFileCodeLines(stream);

		for (int i = 0; i < list.size(); i++) {
			String s=list.get(i).getCode();
				for (int j = 0; j < toIgnore.length; j++) {
					if(s.contains(toIgnore[j])){
						s=s.replace(toIgnore[j], "");
						list.get(i).setCode(s);
					}
				}
		}

		return list;

	}

	public CodeLineGenerator clone(){
		return new CppHeaderCodeLineGenerator();
	}

}
