package sfogl2;

import javax.media.opengl.GL2ES2;

/**
 * A sequence of SFOGLState
 * @author Alessandro Martinelli
 */
public class SFOGLCompositeState implements SFOGLState{

	private SFOGLState[] states;

	public SFOGLCompositeState(SFOGLState[] states) {
		super();
		this.states = states;
	};
	
	@Override
	public void applyState(GL2ES2 gl) {
		for (int i = 0; i < states.length; i++) {
			states[i].applyState(gl);
		}
	}
}
