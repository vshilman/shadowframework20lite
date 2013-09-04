package codeconverter.templates.basicattributedeclaration;

import java.util.HashMap;
import java.util.StringTokenizer;

import codeconverter.ICodePiece;
import codeconverter.java.JavaName;
import codeconverter.java.JavaType;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public abstract class BasicAttributeDeclarationTemplate implements Template{

	public static final int ID=1;

	protected HashMap<String, String> param=new HashMap<String, String>();

	protected HashMap<String, String[]> defValues = new HashMap<String, String[]>();
	protected ICodePiece piecen;
	protected ICodePiece piecet;



	public BasicAttributeDeclarationTemplate() {
		init();
	}


	public HashMap<String, String> getProperties(){
		return param;
	}

	protected void init(){
		param.put("$NAME$","$NAME$");
		param.put("$ASS$","false");
		param.put("$TYPE$","$TYPE$");
		param.put("$MOD$", "$MOD$");
	}





	public Template clone(){
		return null;
	}

	public int getId(){
		return ID;
	}

	protected abstract void setDefValues();

}