package jcodecomparator.views;


import java.io.InputStream;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import jcodecomparator.compare.CompareDelegate;
import jcodecomparator.compare.SelectedTextCompareItem;
import jcodecomparator.core.ColumnLayout;
import jcodecomparator.core.CompareEditorInput;
import jcodecomparator.core.IAccettableLeftRight;
import jcodecomparator.editors.CompareEditorViewer;
import jcodecomparator.test.TestImageByTypeKeeper;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import codeconverter.factories.test.TestDataStructureTemplateFactory;
import codeconverter.factories.test.TestLanguagesObjectsFactory;
import codeconverter.templates.ConversionByTemplateDelegate;

/**
 *That's the view that shows all the central elements of the plugin
 *
 * @author Nicola Pellicano'
 *
 */

public class MainView extends ViewPart implements IAccettableLeftRight{

	private CompareEditorViewer viewer;

	private String titleLeft="";
	private String titleRight="";

	private String extSelectLeft="";
	private String extSelectRigth="";

	private Label l1;
	private Label l2;

	public MainView() {
		 BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
	        EventHandler handler=new EventHandler() {

				@Override
				public void handleEvent(Event event) {
					if(event.getProperty("COMBO_0")!=null){
						extSelectLeft=(String) event.getProperty("COMBO_0");
					}
					if(event.getProperty("COMBO_1")!=null){
						extSelectRigth=(String) event.getProperty("COMBO_1");
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
	public void createPartControl(Composite arg0) {
		int style=SWT.MULTI;

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
     			event=new Event("viewcommunication/asyncEvent", propert);
     			eventAdmin.postEvent(event);

			}
		});

		Button but1a=new Button(comp2a, SWT.PUSH);
		but1a.setSize(new Point(40, 30));
		but1a.setLocation(43, -2);
		but1a.setImage(AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/refresh.png").createImage());

		but1a.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				refresh();
			}
		});



		Button buta2=new Button(comp2a,SWT.PUSH);
		buta2.setSize(new Point(40,30));
		buta2.setLocation(83, -2);
		buta2.setImage(AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/templtrasp.png").createImage());
		buta2.setToolTipText("Generate conversion using Data Structure Templates");
		buta2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				if(!titleLeft.equals("")){
							InputStream str=viewer.getLeftInput().getDelegate().getContents();
							ConversionByTemplateDelegate conv=new ConversionByTemplateDelegate(new TestLanguagesObjectsFactory(), new TestDataStructureTemplateFactory());
							if(!extSelectRigth.equals("") && !extSelectRigth.equals(SampleView.DEF)){
								String c=conv.convertCode(titleLeft, str, extSelectRigth);
								StringTokenizer tok=new StringTokenizer(titleLeft, ".");
								String name=tok.nextToken();
								if(c!=null){
										SelectedTextCompareItem efc=new SelectedTextCompareItem(new TestImageByTypeKeeper());
										efc.setInformations(c, name+"."+extSelectRigth);
										viewer.setRightInput(new CompareEditorInput(efc));
										titleRight=name+"."+extSelectRigth;
										l2.setText(titleRight);
								}
							}
						}
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
				l2.setText(titleRight);
				BundleContext ctx=  FrameworkUtil.getBundle(SampleView.class).getBundleContext();
     			ServiceReference<EventAdmin> ref=ctx.getServiceReference(EventAdmin.class);
     			EventAdmin eventAdmin =ctx.getService(ref);
     	        Map<String,Object> propert=new HashMap<>();
     			propert.put("Unblock", "right");
     	        Event event=new Event("viewcommunication/init",propert);
     			eventAdmin.sendEvent(event);
     			event=new Event("viewcommunication/asyncEvent", propert);
     			eventAdmin.postEvent(event);

			}
		});

		Button but1b=new Button(comp2b, SWT.PUSH);
		but1b.setSize(new Point(40, 30));
		but1b.setLocation(43, -2);
		but1b.setImage(AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/refresh.png").createImage());

		but1b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				refresh();
			}
		});



		Button butb2=new Button(comp2b,SWT.PUSH);
		butb2.setSize(new Point(40,30));
		butb2.setLocation(83, -2);
		butb2.setImage(AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/templtrasp.png").createImage());
		butb2.setToolTipText("Generate conversion using Data Structure Templates");
		butb2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				if(!titleRight.equals("")){
						InputStream str=viewer.getRightInput().getDelegate().getContents();
						ConversionByTemplateDelegate conv=new ConversionByTemplateDelegate(new TestLanguagesObjectsFactory(), new TestDataStructureTemplateFactory());
						if(!extSelectLeft.equals("") && !extSelectLeft.equals(SampleView.DEF)){
							String c=conv.convertCode(titleRight, str, extSelectLeft);
							StringTokenizer tok=new StringTokenizer(titleRight, ".");
							String name=tok.nextToken();
							if(c!=null){
									SelectedTextCompareItem efc=new SelectedTextCompareItem(new TestImageByTypeKeeper());
									efc.setInformations(c, name+"."+extSelectLeft);
									viewer.setLeftInput(new CompareEditorInput(efc));
									titleLeft=name+"."+extSelectLeft;
									l1.setText(titleLeft);
							}
						}
					}
				}
		});

		comp2b.layout();



		comp2.layout();


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



	private void refresh(){
		String strleft=viewer.getfLeft().getText();
		String strRight=viewer.getfRight().getText();
		SelectedTextCompareItem efcLeft=new SelectedTextCompareItem(new TestImageByTypeKeeper());
		efcLeft.setInformations(strleft,titleLeft);
		SelectedTextCompareItem efcRight=new SelectedTextCompareItem(new TestImageByTypeKeeper());
		efcRight.setInformations(strRight,titleRight);
		viewer.isComparisonEnabled(false);
		viewer.setLeftInput(new CompareEditorInput(efcLeft));
		viewer.isComparisonEnabled(true);
		viewer.setRightInput(new CompareEditorInput(efcRight));

	}

	@Override
	public void dispose() {
		super.dispose();
	}


}
