package codeconverter.elements;


/**
 * 
 * @author Alessandro Martinelli
 */
public class Method {
	
	private MethodDeclaration declaration=new MethodDeclaration();
	private CodeBlock block=new CodeBlock();
	
	public MethodDeclaration getDeclaration() {
		return declaration;
	}
	public void setDeclaration(MethodDeclaration declaration) {
		this.declaration = declaration;
	}
	public CodeBlock getBlock() {
		return block;
	}
	public void setBlock(CodeBlock block) {
		this.block = block;
	}
	
}