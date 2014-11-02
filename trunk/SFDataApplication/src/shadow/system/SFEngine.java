package shadow.system;

public class SFEngine {

	public static void draw(SFDrawable drawable){
		SFInitiator.solveInitiables();
		SFUpdater.refresh();
		drawable.draw();
	}
}
