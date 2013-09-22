package jcodecomparator.views;



import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.part.*;
import org.eclipse.swt.SWT;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

/**
 * View for language selection
 * @author Nicola Pellicano'
 *
 */


public class SampleView extends ViewPart {

    private String[] supportedLanguages={"java","js","cpp","h"};
    public static final String DEF="Select Language...";

    private Combo combo0;
    private Combo combo1;

    @SuppressWarnings("deprecation")
	public SampleView() {
        setTitle("Select_language");
        EventHandler handler=new EventHandler() {

            @Override
            public void handleEvent(Event arg0) {

                if(arg0.getProperty("Request")!=null){
                    BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
                    ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
                    EventAdmin eventAdmin =ctx.getService(ref);
                    Map<String,Object> properties=new HashMap<>();
                    if(!combo0.getText().equals(DEF)){
                        properties.put("COMBO_0", combo0.getText());
                    }
                    if(!combo1.getText().equals(DEF)){
                    properties.put("COMBO_1", combo1.getText());
                    }
                    Event event=new Event("viewcommunication/syncEvent",properties);
                    eventAdmin.sendEvent(event);

                }

                if(arg0.getProperty("Block")!=null){
                    String message=(String) arg0.getProperty("Block");
                    if(message.equals("right")){
                        combo1.setEnabled(false);
                    }
                    if(message.equals("left")){
                        combo0.setEnabled(false);
                    }
                }

                if(arg0.getProperty("Unblock")!=null){
                    String message=(String) arg0.getProperty("Unblock");
                    if(message.equals("right")){
                        combo1.setEnabled(true);
                    }
                    if(message.equals("left")){
                        combo0.setEnabled(true);
                    }
                }

                if(arg0.getProperty("Set_Left")!=null){
                    String message=(String) arg0.getProperty("Set_Left");
                    combo0.setText(message);


                    String [] currentLanguages=new String[supportedLanguages.length-1];
                    int x=0;
                    for (int i = 0; i < supportedLanguages.length; i++) {
                        if(!supportedLanguages[i].equals(combo0.getText())){
                            currentLanguages[x]=supportedLanguages[i];
                            x++;
                        }
                    }

                    String c=combo1.getText();
                    combo1.setItems(currentLanguages);
                    combo1.setText(c);
                    BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
                    ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
                    EventAdmin eventAdmin =ctx.getService(ref);
                    Map<String,Object> properties=new HashMap<>();
                    properties.put("COMBO_0", combo0.getText());

                    Event event=new Event("viewcommunication/syncEvent",properties);
                    eventAdmin.sendEvent(event);


                }

                if(arg0.getProperty("Set_Right")!=null){
                    String message=(String) arg0.getProperty("Set_Right");
                    combo1.setText(message);


                    String [] currentLanguages=new String[supportedLanguages.length-1];
                    int x=0;
                    for (int i = 0; i < supportedLanguages.length; i++) {
                        if(!supportedLanguages[i].equals(combo1.getText())){
                            currentLanguages[x]=supportedLanguages[i];
                            x++;
                        }
                    }

                    String c=combo0.getText();
                    combo0.setItems(currentLanguages);
                    combo0.setText(c);
                    BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
                    ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
                    EventAdmin eventAdmin =ctx.getService(ref);
                    Map<String,Object> properties=new HashMap<>();
                    properties.put("COMBO_1", combo1.getText());

                    Event event=new Event("viewcommunication/syncEvent",properties);
                    eventAdmin.sendEvent(event);

                }


            }
        };
        BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
        Dictionary<String, String> properties=new Hashtable<>();
        properties.put(EventConstants.EVENT_TOPIC, "viewcommunication/init");
        ctx.registerService(EventHandler.class, handler, properties);

    }


    @Override
    public void createPartControl(Composite arg0) {

        combo0=new Combo(arg0, SWT.WRAP);
        combo0.setItems(supportedLanguages);
        combo0.setText(DEF);

        combo0.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {

                String [] currentLanguages=new String[supportedLanguages.length-1];
                int x=0;
                for (int i = 0; i < supportedLanguages.length; i++) {
                    if(!supportedLanguages[i].equals(combo0.getText())){
                        currentLanguages[x]=supportedLanguages[i];
                        x++;
                    }
                }

                String c=combo1.getText();
                combo1.setItems(currentLanguages);
                combo1.setText(c);

                BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
                ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
                EventAdmin eventAdmin =ctx.getService(ref);
                Map<String,Object> properties=new HashMap<>();
                properties.put("COMBO_0", combo0.getText());

                Event event=new Event("viewcommunication/syncEvent",properties);
                eventAdmin.sendEvent(event);

                //event=new Event("viewcommunication/asyncEvent", properties);
                //eventAdmin.postEvent(event);

            };
        });


        combo1=new Combo(arg0, SWT.NONE);
        combo1.setItems(supportedLanguages);
        combo1.setText(DEF);

        combo1.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {

                String [] currentLanguages=new String[supportedLanguages.length-1];
                int x=0;
                for (int i = 0; i < supportedLanguages.length; i++) {
                    if(!supportedLanguages[i].equals(combo1.getText())){
                        currentLanguages[x]=supportedLanguages[i];
                        x++;
                    }
                }

                String c=combo0.getText();
                combo0.setItems(currentLanguages);
                combo0.setText(c);


                BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
                ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
                EventAdmin eventAdmin =ctx.getService(ref);
                Map<String,Object> properties=new HashMap<>();
                properties.put("COMBO_1", combo1.getText());

                Event event=new Event("viewcommunication/syncEvent",properties);
                eventAdmin.sendEvent(event);

                //event=new Event("viewcommunication/asyncEvent", properties);
                //eventAdmin.postEvent(event);

            };
        });

    }

    /**
     *
     * @return left combo
     */



    public Combo getCombo0() {
        return combo0;
    }

    /**
     *
     * @return right combo
     */

    public Combo getCombo1() {
        return combo1;
    }

    @Override
    public void setFocus() {
    }

    @Override
    public void dispose() {
    	super.dispose();
    }



}
