package jcodecomparator.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;

public class VersionsView extends ViewPart{




	@Override
	public void createPartControl(Composite arg0) {


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
