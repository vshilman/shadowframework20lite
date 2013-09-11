package codeconverter.templates.basicget;

import java.util.HashMap;
import java.util.StringTokenizer;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.cpp.CppName;
import codeconverter.cpp.CppType;
import codeconverter.java.JavaName;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicGetCppTemplate implements Template{

	public static final int ID=2;

	private HashMap<String, String> param=new HashMap<String, String>();

	public BasicGetCppTemplate() {
		init();
	}

	private void init(){
		param.put("$TYPE$", "$TYPE$");
		param.put("$CLASS$","$CLASS$");
		param.put("$NAME$", "$NAME$");
	}

	private BasicGetCppTemplate(HashMap<String, String> param) {
		super();
		this.param=param;
	}

	public int getId(){
		return ID;
	}

	/*
	string SFParameter::getName() {
	    return name;
	}
*/

	@Override
	public boolean matchCode(String code) {
		code=code.trim();
		init();
		boolean bool=false;
		//System.out.println("Processing... "+code);
		String mtype=code.split(" ")[0];
		if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppType(), mtype)){
			System.out.println("uns1");
			return false;
		}
		String code1=code.substring(mtype.length(),code.length()).trim();

		if(mtype.contains("bool")){
			bool=true;
		}


		if(!code1.contains("::")){
			System.out.println("ext1");
			return false;
		}

		StringTokenizer tok=new StringTokenizer(code1,":");
		String mclass=tok.nextToken();
		if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), mclass)){
			System.out.println("uns2");
			return false;
		}

		String code2=code1.substring(mclass.length()+2,code1.length()).trim();

		int off=3;
		if(!code2.startsWith("get")){
			if(bool==false){
				System.out.println("ext2");
				return false;
			} else{
				if(!code2.startsWith("is")){
					System.out.println("ext2a");
					return false;
				}
				off=2;
			}
		}

		String code3=code2.substring(off,code2.length()).trim();

		StringTokenizer tok2=new StringTokenizer(code3,"(");


		String nam=tok2.nextToken();
		String name=nam.trim();
		String init=name.substring(0,1);
		name=init.toLowerCase()+name.substring(1,name.length());

		if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), name)){
			System.out.println("ex3");
			return false;
		}
		String code4=code3.substring(nam.length(),code3.length()).trim();

		if(!code4.startsWith("(")){
			System.out.println("ex4");
			return false;
		}
		String code5=code4.substring(1,code4.length()).trim();

		if(!code5.startsWith(")")){
			System.out.println("ex5");
			return false;
		}
		String code6=code5.substring(1,code5.length()).trim();
		if(!code6.startsWith("{")){
			System.out.println("ex6");
			return false;
		}

		String code7=code6.substring(1,code6.length()).trim();

		if(!code7.startsWith("return")){
			System.out.println("ex7");
			return false;
		}

		String code8=code7.substring(6,code7.length()).trim();
		if(code8.startsWith("this.")){
			code8=code8.split("this.")[1];
		}
		if(code8.startsWith("this->")){
			code8=code8.split("this->")[1];
		}

		if(!code8.startsWith(name)){
			String x="is"+name.substring(0,1).toUpperCase()+name.substring(1,name.length());
			if(!code8.startsWith(x) || bool==false){
				System.out.println("ex8");
				return false;
			}
			name=x;
		}

		String code9=code8.substring(name.length(),code8.length()).trim();
		if(!code9.startsWith(";")){
			return false;
		}

		String code10=code9.substring(1,code9.length()).trim();
		if(!code10.equals("}")){
			return false;
		}

		param.put("$CLASS$", mclass.trim());
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

		String s=param.get("$TYPE$")+" "+param.get("$CLASS$")+"::"+key+n.substring(0, 1).toUpperCase()+n.substring(1,n.length())+"( ) {\n"+
					"\treturn this->"+param.get("$NAME$")+";\n}";
		return s;
	}

	public Template clone(){
		HashMap<String, String> par=new HashMap<String, String>();
		par.putAll(param);
		return new BasicGetCppTemplate(par);
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
