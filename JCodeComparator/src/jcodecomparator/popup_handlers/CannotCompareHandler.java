package jcodecomparator.popup_handlers;

import jcodecomparator.dialogs.MyTitleAreaDialog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * The handler for a failed comparison
 *
 * @author Nicola Pellicano'
 *
 */

public class CannotCompareHandler extends AbstractHandler{


	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {

		 Shell shell = HandlerUtil.getActiveWorkbenchWindow(arg0).getShell();
         TitleAreaDialog tad = new MyTitleAreaDialog(shell,"\n\n\nOps!","\n\nCodeCompareAction cannot be done on this type of object.","/icons/gear_sad.png");
         tad.setTitleAreaColor(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND).getRGB());
         @SuppressWarnings("unused")
		int selected = tad.open();
         return null;

	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
