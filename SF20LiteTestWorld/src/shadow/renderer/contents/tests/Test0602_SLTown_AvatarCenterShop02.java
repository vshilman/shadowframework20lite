package shadow.renderer.contents.tests;




/**
 * 
 * @author Alessandro Martinelli
 */
public class Test0602_SLTown_AvatarCenterShop02 extends SFAbstractTest{
	

	private static final String FILENAME="test0602";
	
	public static void main(String[] args) {
		execute(new Test0602_SLTown_AvatarCenterShop02());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		viewNode("SLAvatarCenterShop02");
		//viewNode("Dummy01");
		
	}
	
	@Override
	public void buildTestData() {
		
		String xmlFileName="test0602Input_ClonedReferences";
		//String xmlFileName="test0601Input";
		
		compileAndLoadXmlFile(xmlFileName);
		
		store(library);
	}
}
