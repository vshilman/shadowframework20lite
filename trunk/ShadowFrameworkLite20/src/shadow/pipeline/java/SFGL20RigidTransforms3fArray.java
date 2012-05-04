package shadow.pipeline.java;

import java.util.ArrayList;
import java.util.LinkedList;

import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFRigidTransform3fArray;
import shadow.system.SFArrayElementException;

public class SFGL20RigidTransforms3fArray implements SFRigidTransform3fArray {

	private class SFGL20RigidTransform {
		public SFTransform3f transform = new SFTransform3f();
		public SFTransform3f effectiveTransform = new SFTransform3f();
		public SFGL20RigidTransform father = null;
		public LinkedList<SFGL20RigidTransform> sons = new LinkedList<SFGL20RigidTransforms3fArray.SFGL20RigidTransform>();

		public void update() {
			if (father == null) {
				effectiveTransform.set(transform);
				sonsUpdate();
			} else {
				sonUpdate();
			}
		}

		protected void sonUpdate() {
			effectiveTransform.set(father.effectiveTransform);
			effectiveTransform.mult(this.transform);
//			System.out.println("Transform "+transform);
//			System.out.println("Effective Transform "+effectiveTransform);
			sonsUpdate();
		}

		private void sonsUpdate() {
			for (SFGL20RigidTransform son : sons) {
				son.sonUpdate();
			}
		}

		public void attach(SFGL20RigidTransform father) {
			if (this.father != null) {		
				this.father.sons.remove(this);
			}
			this.father = father;
			this.father.sons.add(this);
			update();
		}
	}

	private ArrayList<SFGL20RigidTransform> transforms = new ArrayList<SFGL20RigidTransforms3fArray.SFGL20RigidTransform>();
	private static float[] matrix = new float[16];
	static {
		matrix[12] = 0;
		matrix[13] = 0;
		matrix[14] = 0;
		matrix[15] = 1;
	}

	@Override
	public synchronized void apply(int index) {
		SFPipeline.getSfPipelineGraphics().setupTransform(transforms.get(index).effectiveTransform.get());
	}

	@Override
	public void attach(SFRigidTransform3fArray sonArray, int sonIndex,
			int fatherIndex) {
		
		
		SFGL20RigidTransforms3fArray trueSon = (SFGL20RigidTransforms3fArray) sonArray;
		trueSon.transforms.get(sonIndex).attach(this.transforms.get(fatherIndex));
//		System.out.println("Updating... "+sonIndex);
		trueSon.transforms.get(sonIndex).update();
	}

	@Override
	public void eraseElements(int index, int elementsCount) {
		for (int i = 0; i < elementsCount; i++) {
			transforms.remove(index);
		}
	}
	
	@Override
	public SFTransform3f generateSample() {
		return new SFTransform3f();
	}

	@Override
	public int generateElement() {
		
		transforms.add(new SFGL20RigidTransform());
		return transforms.size() - 1;
	}

	@Override
	public int generateElements(int count) {
		int size = transforms.size();
		for (int i = 0; i < count; i++) {
			transforms.add(new SFGL20RigidTransform());
		}
		return size;
	}

	@Override
	public void getElement(int index, SFTransform3f element)
			throws SFArrayElementException {
		element.set(transforms.get(index).transform);
	}

	@Override
	public int getElementsCount() {
		return transforms.size();
	}

	@Override
	public void setElement(int index, SFTransform3f element)
			throws SFArrayElementException {
		transforms.get(index).transform.set(element);
		transforms.get(index).update();
	}

	@Override
	public void getElementOrientation(int index, SFMatrix3f matrix) {
		transforms.get(index).transform.getMatrix(matrix);
	}
	
	@Override
	public void getElementPosition(int index, SFVertex3f vertex) {
		transforms.get(index).transform.getPosition(vertex);
	}
	
	@Override
	public void setElementOrientation(int index, SFMatrix3f matrix) {
		transforms.get(index).transform.setMatrix(matrix);
		transforms.get(index).update();
//		System.out.println("Setting Element Orientation "+index);
//
//		System.out.println("Transform "+this.transforms.get(index).transform);
//		System.out.println("Effective Transform "+this.transforms.get(index).effectiveTransform);
	}
	
	@Override
	public void setElementPosition(int index, SFVertex3f vertex) {
		transforms.get(index).transform.setPosition(vertex);
		transforms.get(index).update();
//		System.out.println("Setting Element Position "+index);
//
//		System.out.println("Transform "+this.transforms.get(index).transform);
//		System.out.println("Effective Transform "+this.transforms.get(index).effectiveTransform);
	}
}
