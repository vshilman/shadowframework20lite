package jcodecomparator.views;


import java.util.HashMap;
import java.util.Map;

import jcodecomparator.Activator;
import jcodecomparator.compare.CompareDelegate;
import jcodecomparator.core.ColumnLayout;
import jcodecomparator.core.CompareEditorInput;
import jcodecomparator.core.IAccettableLeftRight;
import jcodecomparator.editors.CompareEditorViewer;
import jcodecomparator.test.TestLineStyleFactory;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

public class MainView extends ViewPart implements IAccettableLeftRight{

	private CompareEditorViewer viewer;

	private String titleLeft="";
	private String titleRight="";

	private Label l1;
	private Label l2;


	@Override
	public void createPartControl(Composite arg0) {
		int style=SWT.MULTI;

		MessageConsole myConsole = findConsole("Console");
        final MessageConsoleStream out=myConsole.newMessageStream();

		ColumnLayout l=new ColumnLayout();
		arg0.setLayout(l);

		Composite comp=new Composite(arg0, SWT.BORDER);
		comp.setLayout(new FillLayout());

		l1=new Label(comp,SWT.BOLD);
		l1.setText(titleLeft);
		l1.setAlignment(SWT.LEFT);
		l1.setBackground(new Color(null, new RGB(255, 255, 255)));

		FontData fontData = l1.getFont().getFontData()[0];
		Font font = new Font(null, new FontData(fontData.getName(), fontData
		    .getHeight()+2, SWT.BOLD));
		l1.setFont(font);

		l2=new Label(comp,SWT.BOLD);
		l2.setText(titleRight);
		l2.setAlignment(SWT.LEFT);
		l2.setBackground(new Color(null, new RGB(255, 255, 255)));

		FontData fontData2 = l2.getFont().getFontData()[0];
		Font font2 = new Font(null, new FontData(fontData2.getName(), fontData2
		    .getHeight()+2, SWT.BOLD));
		l2.setFont(font2);

		comp.layout();


		final Composite comp2=new Composite(arg0, SWT.BORDER);

		comp2.setBackground(new Color(null, new RGB(240,240,240)));

		comp2.setLayout(new FillLayout());

		final Composite comp2a=new Composite(comp2, SWT.NONE);

		comp2a.setLayout(null);

		comp2a.setBackground(new Color(null, new RGB(240,240,240)));

		comp2a.addPaintListener(new PaintListener(){

            @Override
            public void paintControl(PaintEvent e) {
                int x = comp2a.getBounds().x;
                int y = comp2a.getBounds().y;
                e.gc.setForeground(new Color(null, new RGB(193,197,192)));
                e.gc.drawRectangle(x, y, comp2.getBounds().width/2-3,comp2.getBounds().height-3);
                //out.println(comp2.getBounds().width/2+" "+comp2.getBounds().height);
            }


        });


		Button buta=new Button(comp2a, SWT.PUSH);
		buta.setSize(new Point(40, 30));
		buta.setLocation(0, -2);
		buta.setImage(AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/ics.png").createImage());

		buta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);

				viewer.removeResource(true);
				titleLeft="";
				l1.setText(titleLeft);
				BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
     			ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
     			EventAdmin eventAdmin =ctx.getService(ref);
     	        Map<String,Object> propert=new HashMap<>();
     			propert.put("Unblock", "left");
     	        Event event=new Event("viewcommunication/init",propert);
     			eventAdmin.sendEvent(event);


			}
		});

		comp2a.layout();

		final Composite comp2b=new Composite(comp2, SWT.NONE);

		comp2b.setBackground(new Color(null, new RGB(240,240,240)));

		comp2b.setLayout(null);

		comp2b.addPaintListener(new PaintListener(){

            @Override
            public void paintControl(PaintEvent e) {
                int x = 0;
                int y = 0;
                e.gc.setForeground(new Color(null, new RGB(193,197,192)));
                e.gc.drawRectangle(x, y, comp2.getBounds().width/2-1,comp2.getBounds().height-3);
                //out.println(comp2.getBounds().width/2+" "+comp2.getBounds().height);
            }


        });



		Button butb=new Button(comp2b, SWT.PUSH);
		butb.setSize(new Point(40, 30));
		butb.setLocation(0, -2);
		butb.setImage(AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/ics.png").createImage());

		butb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);

				viewer.removeResource(false);
				titleRight="";
				l1.setText(titleRight);
				BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
     			ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
     			EventAdmin eventAdmin =ctx.getService(ref);
     	        Map<String,Object> propert=new HashMap<>();
     			propert.put("Unblock", "right");
     	        Event event=new Event("viewcommunication/init",propert);
     			eventAdmin.sendEvent(event);


			}
		});

		comp2b.layout();



		comp2.layout();

		//can2.setBackground(new Color(null, new RGB(193,197,192)));

		viewer=new CompareEditorViewer(arg0, style, new CompareConfiguration(),new CompareDelegate(),new TestLineStyleFactory());


	}

	@Override
	public void setFocus() {

	}



	@Override
	public void acceptRight(CompareEditorInput cei) {
		viewer.setRightInput(cei);
		titleRight=cei.getDelegate().getName();
		l2.setText(titleRight);
	}


	@Override
	public void acceptLeft(CompareEditorInput cei) {
		viewer.setLeftInput(cei);
		titleLeft=cei.getDelegate().getName();
		l1.setText(titleLeft);
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
