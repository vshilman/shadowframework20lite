package jcodecomparator.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;

public class VersionsView extends ViewPart{

	
	
	
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
		
		
		Table t=new Table(arg0, SWT.BORDER);
		t.setLinesVisible(true);
	
		
		Table t2=new Table(arg0, SWT.BORDER);
		t2.setLinesVisible(true);
	
		//TODO Insert versions
	
	}

	@Override
	public void setFocus() {
	}
	
}
