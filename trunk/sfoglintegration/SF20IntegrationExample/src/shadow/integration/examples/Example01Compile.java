package shadow.integration.examples;


public class Example01Compile {
	
	private static final String SFB_FILE = "data/example01.sfb";
	private static final String SF_FILE = "data/example01.sf";

	public static void main(String[] args) {

		//loadPipeline();
		
		ExamplesUtils.compileAndExport(SFB_FILE,SF_FILE);
		
		ExamplesUtils.reloadContent(SF_FILE);
		
		//SFContext context=new SFContext(SFDataCenter.getDataCenter().getDictionary());
		
		//SFDrawable drawable=(SFDrawable)SFDataCenter.makeDatasetAvailable("samples.RedMushroomDrawable").getResource(context);
		
		//context.setDrawable(drawable);
		
		//SFDrawableFrame frame=new SFDrawableFrame("Tests Launcher", 800, 800, drawable);
		//frame.setVisible(true);
	}
}