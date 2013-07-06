package codeconverter;

/**
 * An attribute which can be assigned to a CodePattern
 *
 * @author Alessandro Martinelli
 */
public enum PatternType {

	CLASS("Class"),
	CONSTRUCTUR("Method"),
	METHOD("Method"),
	CLASS_DECLARATION("Class Declaration"),
	CONSTRUCTOR_DECLARATION("Constructor Declaration"),
	METHOD_DECLARATION("Method Declaration"),
	ATTRIBUTE_DECLARATION("Attribute Declaration"),
	ATTRIBUTE_ASSIGNMENT("Attribute assignment"),
	VARIABLE_DECLARATION("Variable Declaration"),
	VARIABLE_ASSIGNMENT("Variable assignment"),
	ARRAY_DECLARTION("Array Declaration"),
	ARRAY_CONTENT_DECLARTION("Array Content Declaration"),
	VISIBILITY("visibility assignement"),
	ISOLATED_KEYWORDS("Isolated Keywords"),
	NEWINSTANCE("new"),
	IF("if"),
	ELSE_IF("else if"),
	ELSE("else"),
	FOR("for"),
	WHILE("while"),
	ASSIGNMENT("assignment"),
	CALL("call"),
	OPENGL_CALL("Open GL call"),
	BLOCK_CLOSE("close block"),
	RETURN("return"),
	SUPER("super"),
	UNIDENTIFIED_CODE("unidentified"),
	LIBRARY_DECLARATION("folder"),
	LINE_OF_CODE("codeline"),
	DEALLOCATION("delete"),
	FRIENDSHIP("friend"),
	NAMESPACE_DECLARATION("namespace"),
	NAMESPACE_USAGE("using_namespace");

	private String name;

	private PatternType(String name) {
	}

	public String getName() {
		return name;
	}

}
