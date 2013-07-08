package jcodecomparator.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public  class DefaultLineStyler implements LineBackgroundStylerListener{

		  private DefaultScanner scanner;

		  int[] tokenColors;

		  Color[] colors;

		 private  Vector blockComments = new Vector();

		  private  Vector<StyleRange> styles = new Vector();

		  private HashMap<Point, Color> toConsider=new HashMap<>();


		  public DefaultLineStyler(DefaultScanner scanner) {
		    initializeColors();
		    this.scanner=scanner;
		  }

		  /* (non-Javadoc)
		 * @see jcodecomparator.core.LineBackgroundStylerListener#cleanToConsider()
		 */
		@Override
		public void cleanToConsider(){
			  toConsider.clear();
		  }

		 private void internalSetBackground (Point pos,Color color){

			 MessageConsole myConsole = findConsole("Console");
			 MessageConsoleStream out=myConsole.newMessageStream();

			 out.println("E il momento di "+pos.x+" "+pos.y);

			 for (int j = pos.x; j < pos.y; j++) {
				//out.println("Comincio ad indagare per "+j);
				 List<Integer> l=new ArrayList<>();
				// out.println(styles.size()+"");
				 for (int i = 0; i < styles.size(); i++) {
						 StyleRange r=(StyleRange) ((StyleRange)styles.get(i)).clone();
						// out.println("------>Vado bene? "+r.start+" "+(r.start+r.length));
						 boolean cont=false;
						 if(r.start <= j && (r.start+r.length) > j){
						//	 out.print("SI!");
							 styles.remove(i);
							 cont=true;
						 }
						 if(!cont){
							 if(r.start<=j){
								 l.add(-1);
							 } else {
								 l.add(+1);
							 }
						 }
					}
				 	//out.println(l.size()+"");
					  int x=0;
					  while(x<l.size() && l.get(x)==-1){
						  x++;
					  }
					  StyleRange newRange=new StyleRange(j, 1, null, color,SWT.NORMAL);
					  styles.add(x,newRange);

			 }
		 }


		/* (non-Javadoc)
		 * @see jcodecomparator.core.LineBackgroundStylerListener#setBackground(org.eclipse.swt.graphics.Point, org.eclipse.swt.graphics.Color)
		 */
		@Override
		public void setBackground (Point pos,Color color){
				toConsider.put(pos, color);
		  }



		  public Color getColor(int type) {
			    if (type < 0 || type >= tokenColors.length) {
			      return null;
			    }
			    return colors[tokenColors[type]];
			  }


		  public boolean inBlockComment(int start, int end) {
			    for (int i = 0; i < blockComments.size(); i++) {
			      int[] offsets = (int[]) blockComments.elementAt(i);

			      if ((offsets[0] >= start) && (offsets[0] <= end))
			        return true;

			      if ((offsets[1] >= start) && (offsets[1] <= end))
			        return true;

			      if ((offsets[0] <= start) && (offsets[1] >= end))
			        return true;
			    }
			    return false;
			  }



			public  void initializeColors() {
			    Display display = Display.getDefault();
			    colors = new Color[] { new Color(display, new RGB(0, 0, 0)),
			        new Color(display, new RGB(59, 127, 115)),
			        new Color(display, new RGB(54, 21, 246)),
			        new Color(display, new RGB(131, 5, 85))
			    };

			    tokenColors = new int[Keywords.MAXIMUM_TOKEN.id];
			    tokenColors[Keywords.WORD.id] = 0;
			    tokenColors[Keywords.WHITE.id] = 0;
			    tokenColors[Keywords.KEY.id] = 3;
			    tokenColors[Keywords.COMMENT.id] = 1;
			    tokenColors[Keywords.STRING.id] = 2;
			    tokenColors[Keywords.OTHER.id] = 0;
			    tokenColors[Keywords.NUMBER.id] = 0;

			  }

			  void disposeColors() {
			    for (int i = 0; i < colors.length; i++) {
			      colors[i].dispose();
			    }
			  }


private boolean containsRange(int start, int lenght){

		for (int i = 0; i < styles.size(); i++) {
			if(styles.get(i).start==start && styles.get(i).length==lenght){
				return true;
			}
		}

	return false;
}




 public void lineGetStyle(LineStyleEvent event) {


	 	styles.clear();
	    int token;
	    StyleRange lastStyle;

	    if (inBlockComment(event.lineOffset, event.lineOffset
	        + event.lineText.length())) {

	    	if(!containsRange(event.lineOffset,  event.lineText
	          .length()))	{
			      styles.addElement(new StyleRange(event.lineOffset, event.lineText
			          .length(), getColor(Keywords.COMMENT.id), null));

			      event.styles = new StyleRange[styles.size()];
			      styles.copyInto(event.styles);
	    	}
	      return;
	    }

	    Color defaultFgColor = ((Control) event.widget).getForeground();
	    scanner.setRange(event.lineText);
	    token = scanner.nextToken();
	    while (token != Keywords.EOF.id) {
	      if (token == Keywords.OTHER.id) {
	    	  //Do nothing
	      } else if (token != Keywords.WHITE.id) {
	        Color color = getColor(token);

	        if ((!color.equals(defaultFgColor)) || (token == Keywords.KEY.id)) {

	        	StyleRange style = new StyleRange(scanner.getStartOffset()
	              + event.lineOffset, scanner.getLength(), color,
	              null);
	          if (token == Keywords.KEY.id) {
	            style.fontStyle = SWT.BOLD;
	          }
	          if (styles.isEmpty()) {
	        	  if(!containsRange(style.start, style.length)){
	        		  styles.addElement(style);
	        	  }
	          } else {
	            lastStyle = (StyleRange) styles.lastElement();
	            if (lastStyle.similarTo(style)
	                && (lastStyle.start + lastStyle.length == style.start)) {
	              lastStyle.length += style.length;
	            } else {
	              if(!containsRange(style.start, style.length)){
	            	  styles.addElement(style);
	              }
	            }
	          }
	        }
	      } else if ((!styles.isEmpty())
	              && ((lastStyle = (StyleRange) styles.lastElement()).fontStyle == SWT.BOLD)) {
	            int start = scanner.getStartOffset() + event.lineOffset;
	            lastStyle = (StyleRange) styles.lastElement();
	            if (lastStyle.start + lastStyle.length == start) {
	              lastStyle.length += scanner.getLength();
	            }
	          }
	          token = scanner.nextToken();
	        }

	 //   MessageConsole myConsole = findConsole("Console");
	//	 MessageConsoleStream out=myConsole.newMessageStream();

	    	Set<Point> keys=toConsider.keySet();
	    //	out.println("-> "+keys.size());
	    	for (Iterator<Point> iterator = keys.iterator(); iterator.hasNext();) {
				Point point = iterator.next();
				internalSetBackground(point, toConsider.get(point));
			}
	        event.styles = new StyleRange[styles.size()];
	        styles.copyInto(event.styles);
	      }





				     public void parseBlockComments(String text) {
				        blockComments = new Vector();
				        StringReader buffer = new StringReader(text);
				        int ch;
				        boolean blkComment = false;
				        int cnt = 0;
				        int[] offsets = new int[2];
				        boolean done = false;

				        try {
				          while (!done) {
				            switch (ch = buffer.read()) {
				            case -1:
				            		if (blkComment) {
				            			offsets[1] = cnt;
				            			blockComments.addElement(offsets);
				            		}
				            		done = true;
				            		break;


				            case '/':
					            		ch = buffer.read();
					              if ((ch == '*') && (!blkComment)) {
					            	  offsets = new int[2];
					            	  offsets[0] = cnt;
					            	  blkComment = true;
					            	  cnt++;
					              } else {
					                cnt++;
					              }
					              cnt++;
					              break;

				            case '*':
				                if (blkComment) {
				                  ch = buffer.read();
				                  cnt++;
				                  if (ch == '/') {
				                    blkComment = false;
				                    offsets[1] = cnt;
				                    blockComments.addElement(offsets);
				                  }
				                }
				                cnt++;
				                break;

				            default:
				                cnt++;
				                break;


				            }
				           }
				          } catch (IOException e) {
				            // ignore errors
				          }
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
