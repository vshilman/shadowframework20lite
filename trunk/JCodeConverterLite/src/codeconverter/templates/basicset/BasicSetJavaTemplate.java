package codeconverter.templates.basicset;

import java.util.HashMap;
import java.util.StringTokenizer;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.JavaName;
import codeconverter.java.JavaType;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicSetJavaTemplate implements Template{

	public static final int ID=3;

	private HashMap<String, String> param=new HashMap<String, String>();


	public BasicSetJavaTemplate() {
		init();
	}

	private void init(){
		param.put("$NAME$", "$NAME$");
		param.put("$TYPE$", "$TYPE$");
	}

	private BasicSetJavaTemplate( HashMap<String, String> param) {
		super();
		this.param=param;
	}

	public int getId(){
		return ID;
	}

	/*public void setFoo (String foo) {
		this.foo=foo;
		}
	 */

	@Override
	public boolean matchCode(String code) {

		code=code.trim();
		init();
		//System.out.println("Processing... "+code);
		if(code.startsWith("@Override")){
			code=code.substring(9,code.length()).trim();
		}
		boolean bool=false;
		String modc=code.split(" ")[0];
		if(!(modc.equals("public"))){
			//System.out.println("ex1");
			return false;
		}

		String code1=code.substring(modc.length(),code.length()).trim();
		if(!code1.startsWith("void")){
			//System.out.println("ex2");
			return false;
		}

		String code2=code1.substring(4,code1.length()).trim();
		if(!code2.startsWith("set")){
			//System.out.println("ex3");
			return false;
		}

		String code3=code2.substring(3,code2.length()).trim();
		StringTokenizer tok=new StringTokenizer(code3,"(");


		String nam=tok.nextToken();
		String name=nam.trim();
		String init=name.substring(0,1);
		name=init.toLowerCase()+name.substring(1,name.length());

		if(!GeneralPurposeTemplateUtilities.isNameSupported(new JavaName(), name)){
			//System.out.println("ex4");
			return false;
		}

		String code4=code3.substring(nam.length(),code3.length()).trim();
		if(!code4.startsWith("(")){
			//System.out.println("ex5");
			return false;
		}

		String code5=code4.substring(1,code4.length()).trim();
		StringTokenizer tokk=new StringTokenizer(code5,")");

		String args=tokk.nextToken();
		StringTokenizer toka=new StringTokenizer(args, " ");


		int num=toka.countTokens();
		if(num<2){
			//System.out.println("exx");
			return false;
		}
		String mtype=toka.nextToken();
		for (int i = 1; i < num-1; i++) {
			mtype+=" "+toka.nextToken();
		}

		if(mtype.contains("boolean")){
			bool=true;
		}

		if(!GeneralPurposeTemplateUtilities.isNameSupported(new JavaType(), mtype)){
			//System.out.println("uns2");
			return false;
		}
		String name2=toka.nextToken();

		if(!name2.equals(name)){
			String x="is"+name.substring(0,1).toUpperCase()+name.substring(1,name.length());
			if(!x.equals(name2) || bool==false){
				//System.out.println("ext6");
				return false;
			}
			name=name2;
		}

		String code7=code5.substring(args.length(),code5.length()).trim();
		if(!code7.startsWith(")")){
			//System.out.println("ex7");
			return false;
		}

		String code8=code7.substring(1,code7.length()).trim();
		if(!code8.startsWith("{")){
			//System.out.println("ex8");
			return false;
		}

		String code9=code8.substring(1,code8.length()).trim();

		if(code9.startsWith("this.")){
			code9=code9.split("this.")[1];
		}
		if(!code9.startsWith(name)){
			//System.out.println("ex9");
			return false;
		}

		String code10=code9.substring(name.length(),code9.length()).trim();
		if(!code10.startsWith("=")){
			//System.out.println("ex10");
			return false;
		}

		String code11=code10.substring(1,code10.length()).trim();
		if(!code11.startsWith(name)){
			//System.out.println("ex11");
			return false;
		}

		String code12=code11.substring(name.length(),code11.length()).trim();
		if(!code12.startsWith(";")){
			//System.out.println("ex12");
			return false;
		}

		String code13=code12.substring(1,code12.length()).trim();
		if(!code13.equals("}")){
			//System.out.println("ex13");
			return false;
		}

		param.put("$NAME$", name);
		param.put("$TYPE$", mtype);

		return true;
	}

	@Override
	public String constructCode() {

		String n=param.get("$NAME$");
		if(param.get("$TYPE$").contains("boolean")){
			if(n.startsWith("is")){
				n=n.substring(2,n.length());
			}
		}

		String s="public void set"+n.substring(0, 1).toUpperCase()+n.substring(1,n.length())+" ("+param.get("$TYPE$")+" "+param.get("$NAME$")+" ) {\n" +
				"\tthis."+param.get("$NAME$")+"="+param.get("$NAME$")+";\n}";
		return s;
	}

	public Template clone(){
		HashMap<String, String> par=new HashMap<String, String>();
		par.putAll(param);
		return new BasicSetJavaTemplate( par);
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
