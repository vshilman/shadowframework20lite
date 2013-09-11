package codeconverter.templates.basicattributedeclaration;

import java.util.HashMap;

import codeconverter.cpp.CppName;
import codeconverter.cpp.CppType;
import codeconverter.templates.Template;

//Cpp doesn't have attribute declaration but he needs them for default initializations

public class BasicAttributeDeclarationCppTemplate extends BasicAttributeDeclarationTemplate{




	public BasicAttributeDeclarationCppTemplate() {
		this.piecen=new CppName();
		this.piecet=new CppType();
		setDefValues();

	}


	private BasicAttributeDeclarationCppTemplate(HashMap<String, String> param) {
		super();
		this.param=param;
		this.piecen=new CppName();
		this.piecet=new CppType();
		setDefValues();
		String s="null";
		if(defValues.containsKey(param.get("$TYPE$"))){
			s=defValues.get(param.get("$TYPE$"))[0];
		}
		param.put("$DEF$", s);
	}

	@Override
	public boolean matchCode(String code) {
		return false;
	}

	@Override
	public String constructCode() {
		return "";
	}

	public void setProperty(String prop, String value){
		if(param.containsKey(prop)){
			if(prop.equals("$TYPE$") && value.equalsIgnoreCase("string")){
				value="string";
			}
			if(prop.equals("$TYPE$") && value.equalsIgnoreCase("boolean")){
				value="bool";
			}

			param.put(prop, value);
		}

		String s="null";
		if(defValues.containsKey(param.get("$TYPE$"))){
			s=defValues.get(param.get("$TYPE$"))[0];
		}
		param.put("$DEF$", s);
	}

	@Override
	protected void setDefValues() {
		String[] intv={"0"};
		defValues.put("int", intv);
		defValues.put("short", intv);
		defValues.put("byte", intv);
		String[] floatv={"0","0f","0.0f","0F","0.0F"};
		defValues.put("float", floatv);
		String[] longv={"0","0l","0L"};
		defValues.put("long", longv);
		String[] doublev={"0","0d","0.0d","0D","0.0D"};
		defValues.put("double", doublev);
		String[] charv={"\'u0000\'","0"};
		defValues.put("char", charv);
		String[] boolv={"false"};
		defValues.put("bool", boolv);
	}

	public Template clone(){
		HashMap<String, String> a=new HashMap<String, String>();
		a.putAll(param);
		return new BasicAttributeDeclarationCppTemplate(a);
	}





}
