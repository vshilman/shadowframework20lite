package shadow.renderer.contents.tests;

public class Test0251_ATWStrangeGlass extends SFAbstractTest{

	private static final String FILENAME = "test0251";
	
	private Test0250_0230Commons general=new Test0250_0230Commons(this);
	
	public static void main(String[] args) {
		execute(new Test0251_ATWStrangeGlass());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		general.loadViewer("PlainStrangeGlass", "TexturedStrangeGlass", "BumpMappedStrangeGlass");				
	}
	
	@Override
	public void buildTestData() {

		String xmlFileName="test0251InputATWStrangeGlass";

		compileAndLoadXmlFile(xmlFileName);

		general.loadTextures();
		
		store(library);
	}
	
}
