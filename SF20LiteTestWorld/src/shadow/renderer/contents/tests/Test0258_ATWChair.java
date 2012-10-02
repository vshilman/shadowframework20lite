package shadow.renderer.contents.tests;

public class Test0258_ATWChair extends SFAbstractTest{

	private static final String FILENAME = "test0258";
	
	private Test0250_0230Commons general=new Test0250_0230Commons(this);
	
	public static void main(String[] args) {
		execute(new Test0258_ATWChair());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		general.loadViewer("PlainChair", "TexturedChair", "BumpMappedChair");				
	}
	
	@Override
	public void buildTestData() {

		String xmlFileName="test0258InputATWChair";

		compileAndLoadXmlFile(xmlFileName);

		general.loadTextures();
		
		store(library);
	}
	
}
