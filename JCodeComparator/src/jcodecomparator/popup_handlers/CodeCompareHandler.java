package jcodecomparator.popup_handlers;


import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import jcodecomparator.compare.BlankCompareItem;
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
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

/**
 * The main handler of selections
 *
 * @author nicolapelicano
 *
 */


public class CodeCompareHandler extends AbstractHandler{

    private Shell shell;
    private ISelection currentSelection;
    private IEditorPart part;
    public static final ICompareItem DEF_ITEM=new BlankCompareItem();


    private String languageRight=SampleView.DEF;
    private String languageLeft=SampleView.DEF;

    public CodeCompareHandler() {
        super();
        BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
        EventHandler handler=new EventHandler() {

			@Override
			public void handleEvent(Event event) {
				if(event.getProperty("COMBO_0")!=null){
					languageLeft=(String) event.getProperty("COMBO_0");

				}
				if(event.getProperty("COMBO_1")!=null){
					languageRight=(String) event.getProperty("COMBO_1");


				}

			}
		};
		Dictionary<String, String> properties=new Hashtable<>();
		properties.put(EventConstants.EVENT_TOPIC, "viewcommunication/*");
		ctx.registerService(EventHandler.class, handler, properties);

		ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
		EventAdmin eventAdmin =ctx.getService(ref);
        Map<String,Object> propert=new HashMap<>();
		propert.put("Request", "");
        Event event=new Event("viewcommunication/init",propert);
		eventAdmin.sendEvent(event);
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
                    part=p.openEditor(new CompareEditorInput(DEF_ITEM), "jcodecomparator.editors.CodeCompareEditor");
                }

               IPerspectiveRegistry reg=PlatformUI.getWorkbench().getPerspectiveRegistry();
               IPerspectiveDescriptor pd=reg.findPerspectiveWithId("jcodecomparator.perspectives.JCodeComparatorPerspective");

        		if(pd!=null){
        			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPerspective(pd);
        		} else {
        			MessageDialog.openError(shell, "Error", "Error trying to open the perspective");
        		}

                IAccettableLeftRight lr=(IAccettableLeftRight) part;

                boolean notblock=true;
                boolean blockRight=false;


                if(languageRight.equals(ci.getType())){
                	  lr.acceptRight(new CompareEditorInput(ci));
                	  notblock=false;
                	  blockRight=true;
                } else
	                if(languageLeft.equals(ci.getType())){
	                  lr.acceptLeft(new CompareEditorInput(ci));
	                  notblock=false;
	                }
                 else
                	 if(languageLeft.equals(SampleView.DEF)){
                			lr.acceptLeft(new CompareEditorInput(ci));
                	        BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
                			ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
                			EventAdmin eventAdmin =ctx.getService(ref);
                	        Map<String,Object> propert=new HashMap<>();
                			propert.put("Set_Left", ci.getType());
                	        Event event=new Event("viewcommunication/init",propert);
                			eventAdmin.sendEvent(event);
                			languageLeft=ci.getType();
                			notblock=false;
                	 }
                	 else
                		 if(languageRight.equals(SampleView.DEF)){
                			lr.acceptRight(new CompareEditorInput(ci));
                 	        BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
                 			ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
                 			EventAdmin eventAdmin =ctx.getService(ref);
                 	        Map<String,Object> propert=new HashMap<>();
                 			propert.put("Set_Right", ci.getType());
                 	        Event event=new Event("viewcommunication/init",propert);
                 			eventAdmin.sendEvent(event);
                 			languageRight=ci.getType();
                 			notblock=false;
                 			blockRight=true;

                 	 } else
                 		 MessageDialog.openInformation(shell, "Problem occured", "The file type that you want to compare isn't selected in the Selection Language View. ");



                if(!notblock){
                	BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
                	ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
            		EventAdmin eventAdmin =ctx.getService(ref);
                    Map<String,Object> propert=new HashMap<>();
            		propert.put("Block",blockRight? "right":"left" );
                    Event event=new Event("viewcommunication/init",propert);
            		eventAdmin.sendEvent(event);
                }

                 }    catch (PartInitException e) {
                	 MessageDialog.openInformation(shell, "Problem occured", "Initialization error");
                	 e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public void dispose() {
    	super.dispose();
    }



}
