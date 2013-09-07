package jcodecomparator.editors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import jcodecomparator.compare.BlankCompareItem;
import jcodecomparator.core.CompareEditorInput;
import jcodecomparator.core.ComparisonExecutingDelegate;
import jcodecomparator.core.LineBackgroundStylerListener;
import jcodecomparator.core.LineStylerFactory;
import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.compare.contentmergeviewer.ContentMergeViewer;


import org.eclipse.compare.internal.ICompareContextIds;
import org.eclipse.compare.internal.Utilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import codeconverter.CodeModule;
import codeconverter.DifferentiationResult;



public class CompareEditorViewer extends ContentMergeViewer{


    public CompareEditorInput getRightInput() {
		return rightInput;
	}

	public CompareEditorInput getLeftInput() {
		return leftInput;
	}


	public static final String BUNDLE_NAME= "org.eclipse.compare.internal.ImageMergeViewerResources"; //$NON-NLS-1$

    private CompareEditorInput rightInput;
    private CompareEditorInput leftInput;

    //private Label fLeft;
    //private Label fRight;

    private StyledText fLeft;
    private StyledText fRight;

    private ComparisonExecutingDelegate ced;
    private LineStylerFactory lsf;

    private DifferentiationResult res;

    private LineBackgroundStylerListener dLeft;
    private LineBackgroundStylerListener dRight;

    private int count=0;
    private  MessageConsoleStream out;

    //private BufferedCanvas bcLeft;
    //private BufferedCanvas bcRight;



    public CompareEditorViewer(Composite parent, int styles, CompareConfiguration mp, ComparisonExecutingDelegate ced, LineStylerFactory lsf) {
        super(styles, ResourceBundle.getBundle(BUNDLE_NAME), mp);
        this.ced=ced;
        this.lsf=lsf;
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, ICompareContextIds.IMAGE_COMPARE_VIEW);
        buildControl(parent);
        String title= Utilities.getString(getResourceBundle(), "title"); //$NON-NLS-1$
        getControl().setData(CompareUI.COMPARE_VIEWER_TITLE, title);
        MessageConsole myConsole = findConsole("PositionFinding");
        out=myConsole.newMessageStream();

    }

    protected void updateContent(Object ancestor, Object left, Object right) {

        MessageConsole myConsole = findConsole("Console");
        MessageConsoleStream out=myConsole.newMessageStream();

        if(left!=null){
            leftInput= (CompareEditorInput) left;
            setLineStyler(true);
            setInput(fLeft, left);
            dLeft.cleanToConsider();
            if(dRight!=null){
                dRight.cleanToConsider();
            }
        }
        if(right!=null){
            rightInput= (CompareEditorInput) right;
            setLineStyler(false);
            setInput(fRight, right);
            dRight.cleanToConsider();
            if(dLeft!=null){
                dLeft.cleanToConsider();
            }
        }


        fRight.redrawRange(0,fRight.getText().length(),true);
        fLeft.redrawRange(0, fLeft.getText().length(), true);



        if(fLeft.getText()!=null && fRight.getText()!=null && fLeft.getText().length()>0 && fRight.getText().length()>0){
            res=ced.executeComparison(leftInput.getDelegate(), rightInput.getDelegate());
            showDifferences();
        }

    }

    public void setRightInput(CompareEditorInput right){
        updateContent(null, null, right);

    }

    public void setLeftInput(CompareEditorInput left){
        updateContent(null, left,null);
    }

    /*
     * We can't modify the contents of either side we just return null.
     */
    protected byte[] getContents(boolean left) {
        return null;
    }



    public void createControls(Composite composite) {

        Combo combo=new Combo(composite, SWT.NONE);

        fLeft=new StyledText(composite,SWT.BORDER| SWT.V_SCROLL | SWT.H_SCROLL);
        fRight=new StyledText(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);

       // createToolBar();
    }



    private static void setInput( StyledText canvas , Object input) {
        if (canvas != null) {

            InputStream stream= null;
            CompareEditorInput in=(CompareEditorInput) input;
                if (in != null) {
                        stream= in.getDelegate().getContents();
            }

            org.eclipse.swt.widgets.Display display= canvas.getDisplay();
            String text=null;
            if (stream != null) {
                    text=extractText(stream);
                    canvas.setText(text);
            }

            if (text != null) {
                canvas.setBackground(display.getSystemColor(SWT.COLOR_LIST_BACKGROUND));
            } else {
                canvas.setBackground(null);
            }

            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ex) {
                    // silently ignored
                }
            }
        }
    }

    protected void handleResizeAncestor(int x, int y, int width, int height) {
        //No ANCESTOR
    }

    protected void handleResizeLeftRight(int x, int y, int width1, int centerWidth, int width2, int height) {
        fLeft.setBounds(x, y, width1, height);
        fRight.setBounds(x+width1+centerWidth, y, width2, height);

    }

    protected void copy(boolean leftToRight) {
    /*	if (leftToRight) {
            fRightImage= fLeftImage;
            setInput(fRight, fRightImage);
            setRightDirty(true);
        } else {
            fLeftImage= fRightImage;
            setInput(fLeft, fLeftImage);
            setLeftDirty(true);
        }*/
        //TO IMPLEMENT
    }


    private static String extractText (InputStream is) {
        BufferedReader r=new BufferedReader(new InputStreamReader(is));
        try {
            String s=r.readLine();
            String text="";
            while (s!=null){
                text+=s+"\n";
                s=r.readLine();
            }
            return text;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    }
        return "No text";

    }

    public void setLineStyler(boolean left){
            if(left){
                LineBackgroundStylerListener ls1=lsf.getLineStyler(leftInput.getDelegate().getType());
                dLeft=ls1;
                dLeft.cleanToConsider();
                if(ls1!=null){
                    fLeft.addLineStyleListener(dLeft);

                }
            } else {
                LineBackgroundStylerListener ls2=lsf.getLineStyler(rightInput.getDelegate().getType());
                dRight=ls2;
                dRight.cleanToConsider();
                if(ls2!=null){
                    fRight.addLineStyleListener(dRight);

                }
            }
    }


    private void showDifferences(){
        MessageConsole myConsole = findConsole("Console");
         MessageConsoleStream out=myConsole.newMessageStream();
        String infos="Non interpretate sinistra:\n";
        Display display = Display.getDefault();

        for (int i = 0; i < res.getUninterpretatesLeft().size(); i++) {
            String s=res.getUninterpretatesLeft().get(i).getExtendedCode();
            Point pos=getPos(s, true,res.getUninterpretatesLeft().get(i).getFirstLine(),res.getUninterpretatesLeft().get(i).getLastLine());
            //setBackGround(pos, new Color(display, new RGB(128, 128, 128)), true);
            dLeft.setBackground(pos, new Color(display, new RGB(229, 220, 209)));
            out.println("posUs:  "+pos);
            infos+=s+"\n";
        }

        infos+="Non interpretate destra:\n";

        for (int i = 0; i < res.getUninterpretatesRight().size(); i++) {
            String s=res.getUninterpretatesRight().get(i).getExtendedCode();
            Point pos=getPos(s, false,res.getUninterpretatesRight().get(i).getFirstLine(),res.getUninterpretatesRight().get(i).getLastLine());
            //setBackGround(pos, new Color(display, new RGB(128, 128, 128)), false);
            dRight.setBackground(pos, new Color(display, new RGB(229, 220, 209)));
           out.println("posUd:  "+pos);
            infos+=s+"\n";
        }


        infos+="Differenti sinistra:\n";


        if(res.getDifferentLeft()!=null){
            for (Iterator<CodeModule> iterator = res.getDifferentLeft().iterator(); iterator.hasNext();) {
                CodeModule cm=iterator.next();
                String s=cm.getExtendedCode();
                Point pos=getPos(s, true,cm.getFirstLine(),cm.getLastLine());
                //setBackGround(pos, new Color(display, new RGB(128, 128, 255)), true);
                dLeft.setBackground(pos, new Color(display, new RGB(180, 214, 252)));
              out.println("posS:  "+pos+"   "+"["+cm.getFirstLine()+","+cm.getLastLine()+"]"+s);
                infos+=s+"\n";
            }
        }
        infos+="Differenti destra:\n";

        if(res.getDifferentRight()!=null){
            for (Iterator<CodeModule> iterator = res.getDifferentRight().iterator(); iterator.hasNext();) {
                CodeModule cm=iterator.next();
                String s=cm.getExtendedCode();
                Point pos=getPos(s, false,cm.getFirstLine(),cm.getLastLine());
                dRight.setBackground(pos, new Color(display, new RGB(180, 214, 252)));
                out.println("posD:  "+pos+"   "+"["+cm.getFirstLine()+","+cm.getLastLine()+"]"+s);
                infos+=s+"\n";

            }
        }

        fLeft.redrawRange(0, fLeft.getText().length(), true);
        fRight.redrawRange(0,fRight.getText().length(),true);


    }


    public Point getPos(String s, boolean left,int firstLine,int lastLine){


         out.println(s+" "+firstLine+" "+lastLine);

        String text="";
        if(left){
            text=fLeft.getText();
        } else {
            text=fRight.getText();
        }

        int f=getFirstIndexAtLine(text, firstLine);
        int l=getLastIndexAtLine(text, lastLine);

        out.println("Devo ricercare da "+f+" a "+l);

        if(text.indexOf(s,f)>=0 && text.indexOf(s,f)<=l){
        	out.println("Esco subito");
            return new Point(text.indexOf(s,f),text.indexOf(s,f)+s.length());
        }

        if(replaceSpaces(text).indexOf(replaceSpaces(s))>=0){
        	s=replaceN(s);
        	text=replaceN(text);
        	out.println("------>"+text+"<-----");
        	out.println("<------"+s+"------->");
            char[] sc=s.toCharArray();
            int pos=0;
            List<Character> fin=new ArrayList<>();
            int oldpos=pos;
            for (int i = 0; i < sc.length; i++) {
                fin.add(sc[i]);
                String actual=convertToString(fin);
                out.println(actual+"//");
                int x=0;
                while((text.indexOf(actual,f)<0 || text.indexOf(actual,f)>l) && x<10){
                    fin.add(fin.size()-1, ' ');
                    actual=convertToString(fin);
                    x++;
                }
                pos=text.indexOf(actual,f);

                if(pos==-1){
                	//That's possible that text has less spaces than s
                	if(sc[i]==' '){
                		for (int j = 0; j <11; j++) {
							fin.remove(fin.size()-1);
						}
                		pos=oldpos;
                	} else {
                		   break;
                	}
                }
                oldpos=pos;

            }
            if(pos==-1){
            	return new Point(-1,0);
            }
            return new Point(pos, pos+fin.size());

        } else {
            return new Point(-1, 0);
        }


    }

    private String replaceSpaces(String s){

    	char[] sc=s.toCharArray();
    	List<Character> list=new ArrayList<>();
    	for (int i = 0; i < sc.length; i++) {
			if(sc[i]!=' ' && sc[i]!='\n' && sc[i]!='\t'){
				list.add(sc[i]);
			}
		}
    	return convertToString(list);
    }

    private String replaceN(String s){
    	char[] sc=s.toCharArray();
    	List<Character> list=new ArrayList<>();
    	for (int i = 0; i < sc.length; i++) {
			if(sc[i]=='\n' || sc[i]=='\t'){
				sc[i]=' ';
			}
			list.add(sc[i]);
		}
    	return convertToString(list);
    }


    private int getFirstIndexAtLine(String text,int line){

        int x=0;
        int position=0;
        int buf=position;
        while(x<line){
            position=text.indexOf("\n",buf);
            buf=position+1;
            x++;
        }
        return position+1;
    }


    private int getLastIndexAtLine(String text, int line){

        int x=0;
        int position=0;
        int buf=position;
        while(x<(line+1)){
            position=text.indexOf("\n",buf);
            buf=position+1;
            x++;
        }

        return position-1;
    }

    private String convertToString(List<Character> fin){

        String s="";
        for (int i = 0; i < fin.size(); i++) {
            s+=fin.get(i);
        }
        return s;
    }


    public void removeResource(boolean left){
            CompareEditorInput cei=new CompareEditorInput(new BlankCompareItem());
            if(left){
                updateContent(null, cei, null);
            } else {
                updateContent(null,null,cei);
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
