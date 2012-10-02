package shadow.renderer.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import shadow.animation.SFAnimator;

public class SFAnimationTimer implements ActionListener{

	private static final int TIMER_DELAY=30;
	
	private long time=0;
	
	
	public static void startTimer(){
		SFAnimationTimer animatorTimer = new SFAnimationTimer();
		Timer timer=new Timer(TIMER_DELAY, animatorTimer);
		animatorTimer.time = System.currentTimeMillis();
		timer.start();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SFAnimator.animate(System.currentTimeMillis()-this.time);
	}
}
