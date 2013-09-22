package jcodecomparator.dialogs;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Personal dialog
 *
 * @author Nicola Pellicano'
 *
 */

public class MyTitleAreaDialog extends TitleAreaDialog{


	private Image image;
	private String title;
	private String message;


	public MyTitleAreaDialog(Shell parentShell, String title, String message, String imagePath) {
		super(parentShell);
		this.title=title;
		this.message=message;
		image=AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", imagePath).createImage();
	}

	/**
	 * Creation
	 */

	public void create(){
		super.create();
		 setTitle(title);
	     setMessage(message);
	}



	protected Control createDialogArea(Composite parent) {

		Composite c=(Composite) super.createDialogArea(parent);
	       if (image != null)
	            setTitleImage(image);

	       Label label2 = getTitleImageLabel();
	       FormData data = (FormData) label2.getLayoutData();
          data.left =  new FormAttachment(0,350);
          data.bottom=new FormAttachment(0,220);
          c.setVisible(false);
	       return c;
	}

}

