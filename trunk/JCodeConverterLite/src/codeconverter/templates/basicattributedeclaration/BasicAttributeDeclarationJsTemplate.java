package codeconverter.templates.basicattributedeclaration;

import java.util.HashMap;
import java.util.StringTokenizer;

import codeconverter.js.JsName;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicAttributeDeclarationJsTemplate implements Template{


	public static final int ID=1;

	private HashMap<String, String> param=new HashMap<String, String>();




	public BasicAttributeDeclarationJsTemplate() {
		init();
	}


	public HashMap<String, String> getProperties(){
		return param;
	}

	private void init(){
		param.put("$NAME$","$NAME$");
		param.put("$ASS$","false");
		param.put("$TYPE","var");
		param.put("$MOD$", "private");
	}


	private BasicAttributeDeclarationJsTemplate(HashMap<String, String> param) {
		super();
		this.param = param;
	}


	@Override
	public boolean matchCode(String code) {

		code=code.trim();
		init();
		//System.out.println("Processing... "+code);
		boolean assigned=false;
		if(!code.endsWith(";")){
			//System.out.println("ex0");
			return false;
		}

		String modc=code.split(" ")[0];
		if(!modc.equals("var")){
			//System.out.println("ex1");
			return false;
		}
		String code2=code.substring(modc.length(),code.length()).trim();

		String name="";
		if(code2.contains("=")){
			StringTokenizer tok=new StringTokenizer(code2,"=");
			String n=tok.nextToken();
			name=n.trim();
			if(!GeneralPurposeTemplateUtilities.isNameSupported(new JsName(), name)){
				//System.out.println("uns2");
				return false;
			}
			String code3=code2.substring(n.length()+1,code2.length()).trim();
			StringTokenizer tok2=new StringTokenizer(code3,";");
			if(tok2.countTokens()!=1){
				//System.out.println("ext2");
				return false;
			}
			String assignment=tok2.nextToken().trim();
			if(!assignment.equals("0")){
				//System.out.println("ext3");
				return false;
			}
			assigned=true;

		} else {
			StringTokenizer tokk=new StringTokenizer(code2,";");
			String n=tokk.nextToken();
			name=n.trim();
			if(!GeneralPurposeTemplateUtilities.isNameSupported(new JsName(), name)){
				//System.out.println("uns2");
				return false;
			}
			String code3=code2.substring(n.length(),code2.length()).trim();
			if(!code3.equals(";")){
				//System.out.println("ex6+"+code3);
				return false;
			}
		}

		param.put("$ASS$", assigned+"");
		param.put("$NAME$", name);

		return true;
	}



	public Template clone(){

		HashMap<String, String> a=new HashMap<String, String>();
		a.putAll(param);
		return new BasicAttributeDeclarationJsTemplate(a);
	}

	public int getId(){
		return ID;
	}


	@Override
	public String constructCode() {
		String s="var "+param.get("$NAME$");
		if(Boolean.parseBoolean(param.get("$ASS$"))){
			s+="=0";
		}
		s+=";";
		return s;
	}


	@Override
	public void setProperty(String prop, String value) {
		if(param.containsKey(prop)){
			param.put(prop, value);
		}

	}

}
