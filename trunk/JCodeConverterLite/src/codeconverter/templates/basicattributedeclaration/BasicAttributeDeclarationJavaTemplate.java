package codeconverter.templates.basicattributedeclaration;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import codeconverter.java.JavaName;
import codeconverter.java.JavaType;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicAttributeDeclarationJavaTemplate extends BasicAttributeDeclarationTemplate{


	//private int edges=0;

	public BasicAttributeDeclarationJavaTemplate(){
			setDefValues();
			this.piecen=new JavaName();
			this.piecet=new JavaType();
	}


	private BasicAttributeDeclarationJavaTemplate(HashMap<String, String> param) {
		super();
		this.param=param;
		this.piecen=new JavaName();
		this.piecet=new JavaType();
		setDefValues();
	}

	protected void setDefValues() {
		String[] intv={"0"};
		defValues.put("int", intv);
		defValues.put("Integer", intv);
		defValues.put("short", intv);
		defValues.put("byte", intv);
		String[] floatv={"0","0f","0.0f","0F","0.0F"};
		defValues.put("float", floatv);
		defValues.put("Float", floatv);
		String[] longv={"0","0l","0L"};
		defValues.put("long", longv);
		defValues.put("Long", longv);
		String[] doublev={"0","0d","0.0d","0D","0.0D"};
		defValues.put("double", doublev);
		defValues.put("Double", doublev);
		String[] charv={"\'u0000\'","0"};
		defValues.put("char", charv);
		defValues.put("Char", charv);
		String[] boolv={"false"};
		defValues.put("boolean", boolv);
		defValues.put("Boolean", boolv);
	}


	@Override
	public boolean matchCode(String code) {

		code=code.trim();
		init();
		//System.out.println("Processing... "+code);
		boolean assigned=false;
		if(!code.endsWith(";")){
			System.out.println("ex0");
			return false;
		}

		String modc=code.split(" ")[0];
		if(!(modc.equals("public") || modc.equals("private") || modc.equals("protected"))){
			System.out.println("ext1");
			return false;
		}
		code=code.substring(modc.length(),code.length()).trim();

		String modt=code.split(" ")[0];
		if(!GeneralPurposeTemplateUtilities.isNameSupported(piecet, modt)){
			System.out.println("uns1");
			return false;
		}

		String code2=code.substring(modt.length(),code.length()).trim();

		String name="";
		if(code2.contains("=")){
			StringTokenizer tok=new StringTokenizer(code2,"=");
			String n=tok.nextToken();
			name=n.trim();
			if(!GeneralPurposeTemplateUtilities.isNameSupported(piecen, name)){
				System.out.println("uns2");
				return false;
			}
			String code3=code2.substring(n.length()+1,code2.length()).trim();
			StringTokenizer tok2=new StringTokenizer(code3,";");
			if(tok2.countTokens()!=1){
				System.out.println("ext2");
				return false;
			}
			String assignment=tok2.nextToken().trim();
			String defAss="null";
			if(defValues.containsKey(modt)){
				String[] arr=defValues.get(modt);
				if(!GeneralPurposeTemplateUtilities.isValueInArray(arr, assignment)){
					System.out.println("extass");
					return false;
				}
			} else {
				if(!assignment.equals(defAss)){
					System.out.println("extass2");
					return false;
				}
			}
			assigned=true;

		} else {
			StringTokenizer tokk=new StringTokenizer(code2,";");
			String n=tokk.nextToken();
			name=n.trim();
			if(!GeneralPurposeTemplateUtilities.isNameSupported(piecen, name)){
				System.out.println("uns2");
				return false;
			}
			String code3=code2.substring(n.length(),code2.length()).trim();
			if(!code3.equals(";")){
				System.out.println("ex6+"+code3);
				return false;
			}
		}

		param.put("$ASS$", assigned+"");
		//param.put("$ASS$", true+""); //a java attribute is always assigned by default
		param.put("$MOD$", modc);
		param.put("$TYPE$", modt);
		param.put("$NAME$", name);

		return true;
	}



	@Override
	public String constructCode() {
		String ass="null";
		if(defValues.containsKey(param.get("$TYPE$"))){
			ass=defValues.get(param.get("$TYPE$"))[0];
		}
		String s=(param.get("$MOD$").equals("$MOD$")?"private":param.get("$MOD$"))+" "+param.get("$TYPE$")+" "+param.get("$NAME$")+(Boolean.parseBoolean(param.get("$ASS$"))?"="+ass : "")+";";
		return s;
	}

	public void setProperty(String prop, String value){
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


	public Template clone(){
		HashMap<String, String> par=new HashMap<String, String>();
		par.putAll(param);
		return new BasicAttributeDeclarationJavaTemplate(par);
	}




}
