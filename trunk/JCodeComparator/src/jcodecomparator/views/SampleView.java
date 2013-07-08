package jcodecomparator.views;




import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;
import org.eclipse.swt.SWT;


public class SampleView extends ViewPart {

	private String[] supportedLanguages={"Java","JavaScript"};

	private Combo combo0;
	private Combo combo1;

	public SampleView() {
	}

	public SampleView(String[] supported){
		this.supportedLanguages=new String[supported.length];
		for (int i = 0; i < supported.length; i++) {
			this.supportedLanguages[i]=supported[i];
		}
	}


	@Override
	public void createPartControl(Composite arg0) {
		GridLayout gl=new GridLayout(2, true);
		gl.marginWidth=0;
		gl.marginHeight=0;
		gl.marginLeft=0;
		gl.marginRight=0;
		gl.marginTop=0;
		gl.marginBottom=0;
		gl.verticalSpacing=0;
		gl.horizontalSpacing=0;

		combo0=new Combo(arg0, SWT.WRAP);
		combo0.setItems(supportedLanguages);
		combo0.setText("Select Language...");

		combo1=new Combo(arg0, SWT.NONE);
		combo1.setItems(supportedLanguages);
		combo1.setText("Select Language...");
	}



	public Combo getCombo0() {
		return combo0;
	}

	public Combo getCombo1() {
		return combo1;
	}

	@Override
	public void setFocus() {
	}


}