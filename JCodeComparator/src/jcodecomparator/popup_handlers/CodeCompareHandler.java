package jcodecomparator.popup_handlers;


import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import jcodecomparator.compare.BlankCompareItem;
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
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.launch.Framework;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;


public class CodeCompareHandler extends AbstractHandler{

    private Shell shell;
    private ISelection currentSelection;
    private boolean left=true;
    private IEditorPart part;
    public static final ICompareItem DEF_ITEM=new BlankCompareItem();


    private String languageRight=SampleView.DEF;
    private String languageLeft=SampleView.DEF;

    public CodeCompareHandler() {
        super();
        MessageConsole myconsole = findConsole("console");
        final MessageConsoleStream out = myconsole.newMessageStream();

        BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();

        EventHandler handler=new EventHandler() {

			@Override
			public void handleEvent(Event event) {
				if(event.getProperty("COMBO_0")!=null){
					languageLeft=(String) event.getProperty("COMBO_0");
					//out.println(languageLeft);

				}
				if(event.getProperty("COMBO_1")!=null){
					languageRight=(String) event.getProperty("COMBO_1");
					//out.println(languageRight);
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
		//event=new Event("viewcommunication/asyncEvent", propert);
		//eventAdmin.postEvent(event);
    }



    @Override
    public Object execute(final ExecutionEvent arg0) throws ExecutionException {

    	 MessageConsole myconsole = findConsole("console");
         final MessageConsoleStream out = myconsole.newMessageStream();

         out.println("premuto");
        shell = HandlerUtil.getActiveWorkbenchWindow(arg0).getShell();
        currentSelection = HandlerUtil.getCurrentSelection(arg0);
        out.println("preso");
        TestCompareItemConstructorFactory tcicf=new TestCompareItemConstructorFactory(new TestCompareItemGenerator(new ConcreteImageByTypeKeeper()));
        ICompareItem ci=tcicf.generateCompareItemConstructor(currentSelection.getClass().getCanonicalName()).getCompareItem(currentSelection);
        out.println("generato");
        AdmittedTypesKeeper atk=new ConcreteAdmittedTypesKeeper();
        if(ci==null || atk.getAmmittedTypes().contains(ci.getType())==false){
            CannotCompareHandler cch=new CannotCompareHandler();
            cch.execute(arg0);
        } else {
        	out.println("elsato");
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

                out.println("accetablacostruito");
                //out.println(languageRight+" "+languageLeft+" "+ci.getType());

                boolean notblock=true;
                boolean blockRight=false;


                if(languageRight.equals(ci.getType())){
                	  out.print("1");
                	  lr.acceptRight(new CompareEditorInput(ci));
                	  notblock=false;
                	  blockRight=true;
                } else
	                if(languageLeft.equals(ci.getType())){
	                	out.print("3");
	                	out.print(ci.getType());
	                  lr.acceptLeft(new CompareEditorInput(ci));
	                  notblock=false;
	                }
                 else
                	 if(languageLeft.equals(SampleView.DEF)){
                		 	out.print("5");
                			lr.acceptLeft(new CompareEditorInput(ci));

                	        BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
                			ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
                			EventAdmin eventAdmin =ctx.getService(ref);
                	        Map<String,Object> propert=new HashMap<>();
                			propert.put("Set_Left", ci.getType());
                	        Event event=new Event("viewcommunication/init",propert);
                			eventAdmin.sendEvent(event);
                		//	event=new Event("viewcommunication/asyncEvent", propert);
        				//	eventAdmin.postEvent(event);

                			languageLeft=ci.getType();

                			notblock=false;
                	 }
                	 else
                		 if(languageRight.equals(SampleView.DEF)){
                			 out.print(9+"");
                			 lr.acceptRight(new CompareEditorInput(ci));
                 	        BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
                 			ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
                 			EventAdmin eventAdmin =ctx.getService(ref);
                 	        Map<String,Object> propert=new HashMap<>();
                 			propert.put("Set_Right", ci.getType());
                 	        Event event=new Event("viewcommunication/init",propert);
                 			eventAdmin.sendEvent(event);
                 			//event=new Event("viewcommunication/asyncEvent", propert);
        					//eventAdmin.postEvent(event);
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
            	//	event=new Event("viewcommunication/asyncEvent", propert);
				//	eventAdmin.postEvent(event);
                }

                 }    catch (PartInitException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return null;
    }


	 private MessageConsole findConsole(String name) {
	        ConsolePlugin plugin = ConsolePlugin.getDefault();
	        IConsoleManager conMan = plugin.getConsoleManager();
	        IConsole[] existing = conMan.getConsoles();
	        for (int i = 0; i < existing.length; i++) {
	            if (name.equals(existing[i].getName())) {
	                return (MessageConsole) existing[i];
	            }
	        }
	        //no console found, so create a new one
	        MessageConsole myConsole = new MessageConsole(name, null);
	        conMan.addConsoles(new IConsole[]{myConsole});
	        return myConsole;
	    }

}
