package codeconverter.templates.basicget;

import java.util.HashMap;
import java.util.StringTokenizer;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.cpp.CppName;
import codeconverter.cpp.CppType;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicGetCppHeaderTemplate implements Template{

	public static final int ID=2;

	private HashMap<String, String> param=new HashMap<String, String>();

	public BasicGetCppHeaderTemplate(){
		init();
	}

	private void init(){
		param.put("$TYPE$", "$TYPE$");
		param.put("$NAME$", "$NAME$");
	}

	private BasicGetCppHeaderTemplate(HashMap<String, String> param) {
		super();
		this.param=param;
	}

	public int getId(){
		return ID;
	}

	/*string getName();*/


	@Override
	public boolean matchCode(String code) {

		code=code.trim();
		init();
		//System.out.println("Processing... "+code);
		boolean bool=false;

		String mtype=code.split(" ")[0];
		if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppType(), mtype)){
			//System.out.println("uns1");
			return false;
		}
		String code1=code.substring(mtype.length(),code.length()).trim();

		if(mtype.contains("bool")){
			bool=true;
		}

		int off=3;
		if(!code1.startsWith("get")){
			if(bool==false){
				//System.out.println("ext1");
				return false;
			} else{
				if(!code1.startsWith("is")){
					//System.out.println("ext1a");
					return false;
				}
				off=2;
			}

		}

		String code3=code1.substring(off,code1.length()).trim();

		StringTokenizer tok2=new StringTokenizer(code3,"(");

		String nam=tok2.nextToken();
		String name=nam.trim();
		String init=name.substring(0,1);
		name=init.toLowerCase()+name.substring(1,name.length());

		if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), name)){
			//System.out.println("ex3");
			return false;
		}
		String code4=code3.substring(nam.length(),code3.length()).trim();

		if(!code4.startsWith("(")){
			//System.out.println("ex4");
			return false;
		}
		String code5=code4.substring(1,code4.length()).trim();

		if(!code5.startsWith(")")){
			//System.out.println("ex5");
			return false;
		}

		String code6=code5.substring(1,code5.length()).trim();
		if(!code6.equals(";")){
			return false;
		}

		param.put("$TYPE$", mtype);
		param.put("$NAME$", name);


		return true;
	}

	@Override
	public String constructCode() {

		String key="get";
		String n=param.get("$NAME$");
		if(param.get("$TYPE$").contains("bool")){
			key="is";
			if(n.startsWith("is")){
				n=n.substring(2,n.length());
			}
		}

		String s=param.get("$TYPE$")+" "+key+n.substring(0, 1).toUpperCase()+n.substring(1,n.length())+" ( );";
		return s;
	}

	public Template clone(){
		HashMap<String, String> par=new HashMap<String, String>();
		par.putAll(param);
		return new BasicGetCppHeaderTemplate(par);
	}

	@Override
	public HashMap<String, String> getProperties() {
		return param;
	}

	@Override
	public void setProperty(String prop, String value) {
		if(param.containsKey(prop)){
			if(prop.equals("$TYPE$") && value.equalsIgnoreCase("string")){
				value="string";
			}
			if(prop.equals("$TYPE$") && value.equalsIgnoreCase("boolean")){
				value="bool";
			}
			param.put(prop, value);
		}
	}


}
