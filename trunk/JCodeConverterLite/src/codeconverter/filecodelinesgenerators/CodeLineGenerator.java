package codeconverter.filecodelinesgenerators;


import java.io.InputStream;
import java.util.List;


public interface CodeLineGenerator {

	public List<FileCodeLine> generateFileCodeLines(InputStream stream);

	public CodeLineGenerator clone();

}