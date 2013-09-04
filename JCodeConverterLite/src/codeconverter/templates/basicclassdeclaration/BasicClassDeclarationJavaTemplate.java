package codeconverter.templates.basicclassdeclaration;

import java.util.HashMap;

import codeconverter.java.JavaName;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicClassDeclarationJavaTemplate implements Template{

	public static final int ID=0;

	private HashMap<String, String> param=new HashMap<String, String>();

	public BasicClassDeclarationJavaTemplate() {
		init();
	}
	public int getId(){
		return ID;
	}

	private void init(){
		param.put("$CLASS$", "$CLASS$");
		param.put("$FATHER$", "$FATHER$");
		param.put("$DER$", "false");
	}

	private BasicClassDeclarationJavaTemplate(HashMap<String,String > param) {
		super();
		this.param=param;
	}

	@Override
	public boolean matchCode(String code) {

		code=code.trim();
		init();
		//System.out.println("Processing... "+code);
		boolean isDerivate=false;
		String modc=code.split(" ")[0];
		if(!(modc.equals("public"))){
			System.out.println("ex1"+modc);
			return false;
		}

		String code1=code.substring(modc.length(),code.length()).trim();
		if(!code1.startsWith("class")){
			System.out.println("ex2");
			return false;
		}
		String code2=code1.substring(5,code1.length()).trim();
		String name=code2.split(" ")[0];
		if(!GeneralPurposeTemplateUtilities.isNameSupported(new JavaName(), name)){
			System.out.println("uns1");
			return false;
		}
		String code3=code2.substring(name.length(),code2.length()).trim();
		String classd="";
		if(code3.startsWith("extends")){
			isDerivate=true;
			code3=code3.substring(7,code3.length()).trim();
			if(!GeneralPurposeTemplateUtilities.isNameSupported(new JavaName(), code3)){
				System.out.println("uns2");
				return false;
			}
			classd=code3;
		} else {
			if(!code3.equals("")){
				System.out.println("exfischia");
				return false;
			}
		}

		param.put("$CLASS$",name);
		if(isDerivate){
			param.put("$FATHER$", classd);
		}
		param.put("$DER$", isDerivate+"");

		return true;
	}

	@Override
	public String constructCode() {
		String s="public class "+param.get("$CLASS$");
		if(Boolean.parseBoolean(param.get("$DER$"))){
			s+=" extends "+param.get("$FATHER$");
		}
		return s;
	}

	public Template clone(){
		HashMap<String, String> par=new HashMap<String, String>();
		par.putAll(param);
		return new BasicClassDeclarationJavaTemplate(par);
	}

	@Override
	public HashMap<String, String> getProperties() {
		return param;
	}

	@Override
	public void setProperty(String prop, String value) {
		if(param.containsKey(prop)){
			param.put(prop, value);
		}
	}





}
