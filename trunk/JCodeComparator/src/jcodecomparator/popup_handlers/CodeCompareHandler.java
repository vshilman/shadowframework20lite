package jcodecomparator.popup_handlers;

import jcodecomparator.compare.SelectedTextCompareItem;
import jcodecomparator.core.AdmittedTypesKeeper;
import jcodecomparator.core.CompareEditorInput;
import jcodecomparator.core.IAccettableLeftRight;
import jcodecomparator.core.ICompareItem;
import jcodecomparator.test.ConcreteAdmittedTypesKeeper;
import jcodecomparator.test.ConcreteImageByTypeKeeper;
import jcodecomparator.test.TestCompareItemConstructorFactory;
import jcodecomparator.test.TestCompareItemGenerator;
import jcodecomparator.views.SampleView;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;


public class CodeCompareHandler extends AbstractHandler{

    private Shell shell;
    private ISelection currentSelection;
    private boolean left=true;
    private IEditorPart part;
    private  ICompareItem special;

    public CodeCompareHandler() {
        super();
        special=new SelectedTextCompareItem(new ConcreteImageByTypeKeeper());
       ((SelectedTextCompareItem) special).setInformations("Default input, from Nicola Pellicano","blank");
    }
    @Override
    public Object execute(final ExecutionEvent arg0) throws ExecutionException {

        shell = HandlerUtil.getActiveWorkbenchWindow(arg0).getShell();
        currentSelection = HandlerUtil.getCurrentSelection(arg0);
        TestCompareItemConstructorFactory tcicf=new TestCompareItemConstructorFactory(new TestCompareItemGenerator(new ConcreteImageByTypeKeeper()));
        ICompareItem ci=tcicf.generateCompareItemConstructor(currentSelection.getClass().getCanonicalName()).getCompareItem(currentSelection);
        AdmittedTypesKeeper atk=new ConcreteAdmittedTypesKeeper();
        if(ci==null || atk.getAmmittedTypes().contains(ci.getType())==false){
            CannotCompareHandler cch=new CannotCompareHandler();
            cch.execute(arg0);
        } else {
            IWorkbenchWindow w=HandlerUtil.getActiveWorkbenchWindow(arg0);
            IWorkbenchPage p=w.getActivePage();
            try {
                if(part==null || p.findEditor(part.getEditorInput())==null ){
                    part=p.openEditor(new CompareEditorInput(special), "jcodecomparator.editors.CodeCompareEditor");
                }

               IPerspectiveRegistry reg=PlatformUI.getWorkbench().getPerspectiveRegistry();
               IPerspectiveDescriptor pd=reg.findPerspectiveWithId("jcodecomparator.perspectives.JCodeComparatorPerspective");

        		if(pd!=null){
        			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPerspective(pd);
        		} else {
        			MessageDialog.openError(null, "Error", "Error trying to open the perspective");
        		}

                IAccettableLeftRight lr=(IAccettableLeftRight) part;
               /* if(left){
                    lr.acceptLeft(new CompareEditorInput(ci));
                    left=false;
                } else {
                    lr.acceptRight(new CompareEditorInput(ci));
                    left=true;
                }*/


            } catch (PartInitException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return null;
    }
   // fLeftContributor.setDocument(fLeft, cc.isLeftEditable() && cp.isLeftEditable(input));
}
