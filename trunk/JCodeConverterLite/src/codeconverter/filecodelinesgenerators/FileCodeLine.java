package codeconverter.filecodelinesgenerators;

public class FileCodeLine {

	private String code;//A line of Code
	private int line;//its position within a file

	public FileCodeLine(String code, int line) {
		super();
		this.code = code;
		this.line = line;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}




}
