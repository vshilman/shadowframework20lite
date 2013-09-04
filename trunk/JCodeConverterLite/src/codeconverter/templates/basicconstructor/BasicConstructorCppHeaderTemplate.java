package codeconverter.templates.basicconstructor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.StringTokenizer;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.cpp.CppName;
import codeconverter.cpp.CppType;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicConstructorCppHeaderTemplate implements Template{

	public static final int ID=4;

	private String classn="$CLASS$";
	private LinkedHashMap<String, String> mapArgs=new LinkedHashMap<String, String>();

	public BasicConstructorCppHeaderTemplate() {

	}
	public int getId(){
		return ID;
	}


	private BasicConstructorCppHeaderTemplate(String classn,
			LinkedHashMap<String, String> mapArgs) {
		super();
		this.classn = classn;
		this.mapArgs = mapArgs;
	}


	//SFParameter(string name, short type);

	@Override
	public boolean matchCode(String code) {

		code=code.trim();
		mapArgs.clear();
		//System.out.println("Processing... "+code);
		if(!(code.contains("(") && code.contains(")"))){
			System.out.println("ex1");
			return false;
		}

		StringTokenizer tok=new StringTokenizer(code,"(");
		String mclas=tok.nextToken();
		String mclass=mclas.trim();

		if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), mclass)){
			System.out.println("uns1");
			return false;
		}

		String code1=code.substring(mclas.length()+1,code.length());
		StringTokenizer tok2=new StringTokenizer(" "+code1,")");
		String args=tok2.nextToken();

		StringTokenizer toka=new StringTokenizer(args,",");

		while(toka.hasMoreTokens()){
			String x=toka.nextToken().trim();
			StringTokenizer tokx=new StringTokenizer(x," ");

			if(x.trim().equals("")){
				continue;
			}

			if(tokx.countTokens()<2){
				System.out.println("exit");
				mapArgs.clear();
				System.out.println("ex3");
				return false;
			}

			int num=tokx.countTokens();
			String tp=tokx.nextToken();
			for (int i = 1; i < num-1; i++) {
				tp+=" "+tokx.nextToken();
			}

			if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppType(), tp)){
				System.out.println("uns2");
				return false;
			}

			String n=tokx.nextToken();
			if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), n)){
				mapArgs.clear();
				System.out.println("ex4");
				return false;
			}
			mapArgs.put(n, tp);
		}

		String code2=code1.substring(args.length(),code1.length()).trim();
		if(!code2.equals(";")){
			System.out.println("exf");
			return false;
		}
		this.classn=mclass;

		return true;
	}

	@Override
	public String constructCode() {
		String s=classn+"(";
		Set<String> set=mapArgs.keySet();

		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String name = iterator.next();
			s+=mapArgs.get(name)+" "+name+",";
		}
		if(s.endsWith(",")){
			s=s.substring(0,s.length()-1);
		}
		s+=");";
		return s;
	}



	public Template clone(){
		String a=classn;
		LinkedHashMap<String, String> b=new LinkedHashMap<String, String>();
		b.putAll(mapArgs);
		return new BasicConstructorCppHeaderTemplate(a,b);
	}


	public HashMap<String, String> getProperties() {

		HashMap<String, String> map=new HashMap<String, String>();
		map.put("$CLASS$", classn);

		String s="";
		Set<String> set=mapArgs.keySet();
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String  name= iterator.next();
			s+=name+"$"+mapArgs.get(name)+"&";
		}
		map.put("$ARGS$", s);

		return map;
	}


	@Override
	public void setProperty(String prop, String value) {
		if(prop.equals("$CLASS$")){
			this.classn=value;
		}
		if(prop.equals("$ARGS$")){
			StringTokenizer tok=new StringTokenizer(value,"&");
			mapArgs.clear();
			while (tok.hasMoreTokens()){
				String x=tok.nextToken();
				StringTokenizer tokx=new StringTokenizer(x,"$");
				String name=tokx.nextToken();
				String type=tokx.nextToken();
				if(type.equalsIgnoreCase("string")){
					type="string";
				}
				mapArgs.put(name,type);
			}
		}
	}



}
