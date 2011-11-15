package codeconverter;

public enum PatternType {

	CLASS_DECLARATION("Class Declaration"),
	CONSTRUCTOR_DECLARATION("Constructor Declaration"),
	METHOD_DECLARATION("Method Declaration"),
	ATTRIBUTE_DECLARATION("Attribute Declaration"),
	VISIBILITY("visibility assignement"),
	NEWINSTANCE("new"),
	IF("if"),
	ELSE_IF("else if"),
	ELSE("else"),
	FOR("for"),
	WHILE("while"),
	ASSIGNMENT("assignment"),
	CALL("call"),
	BLOCK_CLOSE("close block"),
	RETURN("return"),
	UNIDENTIFIED_CODE("unidentified"),
	LIBRARY_DECLARATION("folder"),
	LINE_OF_CODE("codeline");
	
	private String name;

	private PatternType(String name) {
	}

	public String getName() {
		return name;
	}

}