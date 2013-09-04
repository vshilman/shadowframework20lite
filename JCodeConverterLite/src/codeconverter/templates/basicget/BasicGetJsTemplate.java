package codeconverter.templates.basicget;

import java.util.HashMap;
import java.util.StringTokenizer;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.JavaName;
import codeconverter.js.JsName;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicGetJsTemplate implements Template{

	public static final int ID=2;

	private HashMap<String, String> param=new HashMap<String, String>();

	public BasicGetJsTemplate() {
		init();
	}

	private void init(){
		param.put("$CLASS$", "$CLASS$");
		param.put("$NAME$", "$NAME$");
	}



	private BasicGetJsTemplate(HashMap<String, String> param) {
		super();
		this.param=param;
	}

	public int getId(){
		return ID;
	}

	/*SFGridModel.prototype["getEdges"] = function(){
	return this.edges;
}*/

	@Override
	public boolean matchCode(String code) {
		code=code.trim();
		init();
		//System.out.println("Processing... "+code);
		if(!code.contains(".")){
			System.out.println("ext1");
			return false;
		}
		StringTokenizer tok=new StringTokenizer(code,".");
		String mclass=tok.nextToken();
		if(!GeneralPurposeTemplateUtilities.isNameSupported(new JsName(), mclass)){
			System.out.println("uns1");
			return false;
		}
		String code2=code.substring(mclass.length()+1,code.length()).trim();

		String s="prototype[\"";
		if(!code2.startsWith(s)){
			System.out.println("ext2");
			return false;
		}

		String code3=code2.substring(s.length(),code2.length()).trim();
		int off=3;
		if(!code3.startsWith("get")){
			if(!code3.startsWith("is")){
				System.out.println("ext2a");
				return false;
			}
			off=2;
		}
		code3=code3.substring(off,code3.length());

		StringTokenizer tok2=new StringTokenizer(code3,"\"");
		String name=tok2.nextToken().trim();
		String init=name.substring(0,1);
		name=init.toLowerCase()+name.substring(1,name.length());
		if(!GeneralPurposeTemplateUtilities.isNameSupported(new JsName(), name)){
			System.out.println("ext3");
			return false;
		}

		String code4=code3.substring(name.length()+1,code3.length()).trim();


		if(!code4.startsWith("]")){
			System.out.println("ext4");
			return false;
		}
		String code5=code4.substring(1,code4.length()).trim();
		if(!code5.startsWith("=")){
			System.out.println("ext5");
			return false;
		}

		String code6=code5.substring(1,code5.length()).trim();

		if(!code6.startsWith("function")){
			System.out.println("ext6");
			return false;
		}
		String code7=code6.substring(8,code6.length()).trim();
		if(!code7.startsWith("(")){
			System.out.println("ext7");
			return false;
		}
		String code8=code7.substring(1,code7.length()).trim();
		if(!code8.startsWith(")")){
			System.out.println("ext8");
			return false;
		}
		String code9=code8.substring(1,code8.length()).trim();
		if(!code9.startsWith("{")){
			System.out.println("ext9");
			return false;
		}

		String code10=code9.substring(1,code9.length()).trim();
		if(!code10.startsWith("return")){
			System.out.println("ext10");
			return false;
		}

		String code11=code10.substring(6,code10.length()).trim();
		if(code11.startsWith("this.")){
			code11=code11.split("this.")[1];
		}
		if(!code11.startsWith(name)){
			String x="is"+name.substring(0,1).toUpperCase()+name.substring(1,name.length());
			if(!code11.startsWith(x)){
				System.out.println("ext11");
				return false;
			}
			name=x;
		}

		String code12=code11.substring(name.length(),code11.length()).trim();
		if(!code12.startsWith(";")){
			System.out.println("ext12");
			return false;
		}

		String code13=code12.substring(1,code12.length()).trim();
		if(!code13.startsWith("}")){
			System.out.println("ext13");
			return false;
		}
		String code14=code13.substring(1,code13.length()).trim();
		if(!code14.equals("")){
			System.out.println("ext14");
			return false;
		}

		param.put("$CLASS$", mclass);
		param.put("$NAME$", name);

		return true;
	}

	@Override
	public String constructCode() {
		String n=param.get("$NAME$");
		String key="get";
		if(n.startsWith("is") && n.substring(2,3).matches("[A-Z]")){
			n=n.substring(2,n.length());
			key="is";
		}

		String s=param.get("$CLASS$")+".prototype[\""+key+n.substring(0, 1).toUpperCase()+n.substring(1,n.length())+"\"]=function(){\n" +
					"\treturn this."+param.get("$NAME$")+";\n};";
		return s;
	}

	public Template clone(){
		HashMap<String, String> par=new HashMap<String, String>();
		par.putAll(param);
		return new BasicGetJsTemplate(par);
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
