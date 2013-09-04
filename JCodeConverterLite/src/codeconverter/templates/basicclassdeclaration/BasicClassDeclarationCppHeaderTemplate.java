package codeconverter.templates.basicclassdeclaration;

import java.util.HashMap;
import java.util.StringTokenizer;

import codeconverter.cpp.CppName;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicClassDeclarationCppHeaderTemplate implements Template{

	public static final int ID=0;

	//class SFParameter:public SFParameteri

	private HashMap<String, String> param=new HashMap<String, String>();


	public BasicClassDeclarationCppHeaderTemplate() {
		init();
	}

	private BasicClassDeclarationCppHeaderTemplate(HashMap<String, String> param) {
		super();
		this.param=param;
	}

	public int getId(){
		return ID;
	}

	private void init(){
		param.put("$CLASS$", "$CLASS$");
		param.put("$FATHER$", "$FATHER$");
		param.put("$DER$", "false");
	}



	@Override
	public boolean matchCode(String code) {
		code=code.trim();
		init();
		//System.out.println("Processing... "+code);
		boolean isDerivate=false;
		if(!code.startsWith("class")){
			System.out.println("ext1");
			return false;
		}
		String code1=code.substring(5,code.length()).trim();
		String name="";
		String classd="";
		if(code1.contains(":")){
			StringTokenizer tok=new StringTokenizer(code1,":");
			String n=tok.nextToken();
			name=n.trim();
			if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), name)){
				System.out.println("uns1");
				return false;
			}
			code1=code1.substring(n.length()+1,code1.length()).trim();
			if(!code1.startsWith("public")){
				System.out.println("exx");
				return false;
			}
			code1=code1.substring(6,code1.length()).trim();

			if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), code1)){
				System.out.println("uns2"+code1);
				return false;
			}
			classd=code1;
			isDerivate=true;
		} else {
			if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), code1)){
				System.out.println("uns3");
				return false;
			}
			name=code1;
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

		String s="class "+param.get("$CLASS$");
		if(Boolean.parseBoolean(param.get("$DER$"))){
			s+=" : public "+param.get("$FATHER$");
		}

		return s;
	}

	public Template clone(){
		HashMap<String, String> par=new HashMap<String, String>();
		par.putAll(param);
		return new BasicClassDeclarationCppHeaderTemplate(par);
	}

	@Override
	public HashMap<String, String> getProperties() {
		return param;
	}

	@Override
	public void setProperty(String prop, String value) {
		if(param.containsKey(prop)){
			param.put(prop,value);
		}
	}





}
