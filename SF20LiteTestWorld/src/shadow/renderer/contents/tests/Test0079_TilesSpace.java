package shadow.renderer.contents.tests;

import shadow.renderer.data.SFTilesSpaceData;

public class Test0079_TilesSpace extends SFAbstractTest{

	private static final String FILENAME = "test0079";
	
	public static void main(String[] args) {
		execute(new Test0079_TilesSpace());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		
		loadLibraryAsDataCenter();
		
		viewNode("PebblesTilesSpace");
	}
	
	@Override
	public void buildTestData() {

		copyAssets("test0014", library);

		SFTilesSpaceData tilesSpace=new SFTilesSpaceData();
		tilesSpace.setSpace(5, 5);
		tilesSpace.addNode("PebblesGround");
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tilesSpace.addElement(i+1,j+1,-0.1f, "PebblesModel");
			}
		}
		library.put("PebblesTilesSpace", tilesSpace);
		
		store(library);
	}

}
