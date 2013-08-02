package jcodecomparator.core;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class ColumnLayout extends Layout{

	  // fixed margin and spacing
    public static final int MARGIN = 4;
    public static final int SPACING = 2;
    // cache
    Point[] sizes;
    int maxWidth, totalHeight;

    protected Point computeSize(Composite composite, int wHint, int hHint,
            boolean flushCache) {
        Control children[] = composite.getChildren();
        if (flushCache || sizes == null || sizes.length != children.length) {
            initialize(children);
        }
        int width = wHint, height = hHint;
        if (wHint == SWT.DEFAULT)
            width = maxWidth;
        if (hHint == SWT.DEFAULT)
            height = totalHeight;
        return new Point(width + 2 * MARGIN, height + 2 * MARGIN);
    }

    protected void layout(Composite composite, boolean flushCache) {

    	MessageConsole myConsole = findConsole("Console");
        MessageConsoleStream out=myConsole.newMessageStream();

        Control children[] = composite.getChildren();
        if (flushCache || sizes == null || sizes.length != children.length) {
            initialize(children);
        }
        Rectangle rect = composite.getClientArea();
        int x = MARGIN, y = MARGIN;
        int width = Math.max(rect.width - 2 * MARGIN, maxWidth);
        for (int i = 0; i < children.length; i++) {
            int height = 25;
            //The last occupate the rest of the view
            if(i==children.length-1){
            	height=rect.height-y;
            }

           // out.println("For children "+i+" : "+x+" "+y+" "+width+" "+height);
            children[i].setBounds(x, y, width, height);
            y += height + SPACING;
        }
    }

    void initialize(Control children[]) {
        maxWidth = 0;
        totalHeight = 0;
        sizes = new Point[children.length];
        for (int i = 0; i < children.length; i++) {
            sizes[i] = children[i].computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
            maxWidth = Math.max(maxWidth, sizes[i].x);
            totalHeight += sizes[i].y;
        }
        totalHeight += (children.length - 1) * SPACING;
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
