package codeconverter.templates.basicget;
import java.util.HashMap;
import java.util.StringTokenizer;

import codeconverter.java.JavaName;
import codeconverter.java.JavaType;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;


public class BasicGetJavaTemplate implements Template{

	public static final int ID=2;

	private HashMap<String, String> param=new HashMap<String, String>();



	public BasicGetJavaTemplate(){
		init();
	}

	private  void init(){
		param.put("$TYPE$", "$TYPE$");
		param.put("$NAME$", "$NAME$");
	}


	private BasicGetJavaTemplate(HashMap<String, String> param) {
		super();
		this.param=param;
	}

	public int getId(){
		return ID;
	}


	//Tu che un giorno modificherai questo codice: lo so e' la cosa piu' brutta mai scritta nella mia vita.

	public boolean matchCode(String code){

		code=code.trim();
		init();
		//System.out.println("Processing... "+code);
		if(code.startsWith("@Override")){
			code=code.substring(9,code.length()).trim();
		}
		boolean bool=false;
		String modc=code.split(" ")[0];
		if(!(modc.equals("public"))){
			//System.out.println("ex1+ "+modc);
			return false;
		}

		String code1=code.substring(modc.length(), code.length()).trim();

		String modt=code1.split(" ")[0];
		if(!GeneralPurposeTemplateUtilities.isNameSupported(new JavaType(), modt)){
			//System.out.println("uns1");
			return false;
		}
		if(modt.contains("boolean")){
			bool=true;
		}

		String code2=code1.substring(modt.length(),code1.length()).trim();
		int off=3;
		if(!code2.startsWith("get")){
			if(bool==false){
				//System.out.println("ex2");
				return false;
			} else{
				if(!code2.startsWith("is")){
					//System.out.println("ex2a");
					return false;
				}
				off=2;
			}

		}
		String code3=code2.substring(off,code2.length());

		StringTokenizer tok=new StringTokenizer(code3,"(");

		String nam=tok.nextToken();
		String name=nam.trim();
		String init=name.substring(0,1);
		name=init.toLowerCase()+name.substring(1,name.length());

		if(!GeneralPurposeTemplateUtilities.isNameSupported(new JavaName(), name)){
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
		if(!code6.startsWith("{")){
			//System.out.println("ex6");
			return false;
		}

		String code7=code6.substring(1,code6.length()).trim();

		if(!code7.startsWith("return")){
			//System.out.println("ex7");
			return false;
		}

		String code8=code7.substring(6,code7.length()).trim();
		if(code8.startsWith("this.")){
			code8=code8.split("this.")[1];
		}

		if(!code8.startsWith(name)){
			String x="is"+name.substring(0,1).toUpperCase()+name.substring(1,name.length());
			if(!code8.startsWith(x) || bool==false){
				//System.out.println("ex8");
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

		param.put("$TYPE$", modt);
		param.put("$NAME$", name);

		return true;
	}


	public String constructCode(){

		String key="get";
		String n=param.get("$NAME$");
		if(param.get("$TYPE$").contains("boolean")){
			key="is";
			if(n.startsWith("is")){
				n=n.substring(2,n.length());
			}
		}

		String s="public "+param.get("$TYPE$")+" "+key+n.substring(0, 1).toUpperCase()+n.substring(1,n.length())+"( ) {\n";
		s+="\treturn this."+param.get("$NAME$")+";\n";
		s+="}";

		return s;
	}

	public Template clone(){
		HashMap<String, String> par=new HashMap<String, String>();
		par.putAll(param);
		return new BasicGetJavaTemplate( par);
	}

	@Override
	public HashMap<String, String> getProperties() {
		return param;
	}

	@Override
	public void setProperty(String prop, String value) {
		if(param.containsKey(prop)){
			if(prop.equals("$TYPE$") && value.equalsIgnoreCase("string")){
				value="String";
			}
			if(prop.equals("$TYPE$") && value.equalsIgnoreCase("bool")){
				value="boolean";
			}
			param.put(prop, value);
		}
	}







}
