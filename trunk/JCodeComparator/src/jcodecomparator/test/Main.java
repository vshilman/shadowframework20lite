package jcodecomparator.test;

import jcodecomparator.dialogs.MyTitleAreaDialog;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Main {

	public static void main(String[] args) {
		final Shell shell = new Shell();
		shell.setLayout(new FillLayout());
		 TitleAreaDialog dialog = new MyTitleAreaDialog(shell,"\n\n\nOps!","\n\nCodeCompareAction cannot be done on this type of object.","/icons/gear_sad.png");
	    dialog.setTitleAreaColor(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND).getRGB());
	    dialog.open();
	}
}
