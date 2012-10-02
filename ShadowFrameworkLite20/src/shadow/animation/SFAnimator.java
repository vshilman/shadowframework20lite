package shadow.animation;

import java.util.ArrayList;

/**
 *
 * @author Alessandro Martinelli
 */
public class SFAnimator {

	private static ArrayList<SFAnimation> animations=new ArrayList<SFAnimation>();

	private SFAnimator(){
		
	}
	
	public static void addAnimation(SFAnimation animation){
		animations.add(animation);
	}
	
	public static void animate(long time){
		for (int i = 0; i < animations.size(); i++) {
			animations.get(i).animate(time);
		}
	}
}
