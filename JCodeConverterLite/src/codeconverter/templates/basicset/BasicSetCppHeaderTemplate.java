package codeconverter.templates.basicset;

import java.util.HashMap;
import java.util.StringTokenizer;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.cpp.CppName;
import codeconverter.cpp.CppType;
import codeconverter.java.JavaType;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicSetCppHeaderTemplate implements Template{

	public static final int ID=3;

	private HashMap<String, String> param=new HashMap<String, String>();


	public BasicSetCppHeaderTemplate() {
		init();
	}

	private void init(){
		param.put("$TYPE$","$TYPE$");
		param.put("$NAME$", "$NAME$");
	}

	public int getId(){
		return ID;
	}


	private BasicSetCppHeaderTemplate(HashMap<String, String> param) {
		super();
		this.param=param;
	}

	//void setType(short type);

	@Override
	public boolean matchCode(String code) {
		code=code.trim();
		init();
		//System.out.println("Processing... "+code);
		boolean bool=false;

		if(!code.startsWith("void")){
			return false;
		}

		String code1=code.substring(4,code.length()).trim();

		if(!code1.startsWith("set")){
			return false;
		}

		String code3=code1.substring(3,code1.length()).trim();
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

		StringTokenizer tokk=new StringTokenizer(code5,")");

		String args=tokk.nextToken();
		StringTokenizer toka=new StringTokenizer(args, " ");


		int num=toka.countTokens();
		if(num<2){
			System.out.println("exx");
			return false;
		}
		String mtype=toka.nextToken();
		for (int i = 1; i < num-1; i++) {
			mtype+=" "+toka.nextToken();
		}

		if(mtype.contains("bool")){
			bool=true;
		}

		if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppType(), mtype)){
			System.out.println("uns2");
			return false;
		}
		String name2=toka.nextToken();

		if(!name2.equals(name)){
			String x="is"+name.substring(0,1).toUpperCase()+name.substring(1,name.length());
			if(!x.equals(name2) || bool==false){
				System.out.println("ext6");
				return false;
			}
			name=name2;
		}

		String code7=code5.substring(args.length(),code5.length()).trim();
		if(!code7.startsWith(")")){
			System.out.println("ex7");
			return false;
		}
		String code8=code7.substring(1,code7.length()).trim();
		if(!code8.equals(";")){
			return false;
		}

		param.put("$NAME$", name);
		param.put("$TYPE$", mtype);


		return true;
	}

	@Override
	public String constructCode() {

		String n=param.get("$NAME$");
		if(param.get("$TYPE$").contains("bool")){
			if(n.startsWith("is")){
				n=n.substring(2,n.length());
			}
		}


		String s="void set"+n.substring(0, 1).toUpperCase()+n.substring(1,n.length())+"( "+param.get("$TYPE$")+" "+param.get("$NAME$")+" );";
		return s;
	}

	public Template clone(){
		HashMap<String, String> par=new HashMap<String, String>();
		par.putAll(par);
		return new BasicSetCppHeaderTemplate(param);
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
