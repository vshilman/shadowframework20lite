package codeconverter.templates.basicconstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.cpp.CppName;
import codeconverter.cpp.CppType;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicConstructorCppTemplate implements Template{

	public static final int ID=4;

	private String classn="$CLASS$";
	private String classin="$CLASSIN$";
	private LinkedHashMap<String, String> mapArgs=new LinkedHashMap<String, String>();
	private boolean isDerivate=false;
	private List<String> superlist=new ArrayList<String>();
	private List<String> assigned=new ArrayList<String>();
	private HashMap<String, String> defInstance=new HashMap<String, String>();


	public BasicConstructorCppTemplate() {

	}
	public int getId(){
		return ID;
	}

	private BasicConstructorCppTemplate(String classn, String classin,
			LinkedHashMap<String, String> mapArgs, boolean isDerivate,
			List<String> superlist, List<String> assigned) {
		super();
		this.classn = classn;
		this.classin = classin;
		this.mapArgs = mapArgs;
		this.isDerivate = isDerivate;
		this.superlist = superlist;
		this.assigned = assigned;
	}


	/*SFParameter::SFParameter(string name, short type) {
	    this->name = name;
	    this->type = type;
	}*/

	@Override
	public boolean matchCode(String code) {

		isDerivate=false;

		code=code.trim();
		mapArgs.clear();
		superlist.clear();
		assigned.clear();

		//System.out.println("Processing... "+code);
		if(!code.contains("::")){
			//System.out.println("ex1");
			return false;
		}

		StringTokenizer tok=new StringTokenizer(code,":");
		String mclass=tok.nextToken().trim();

		if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), mclass)){
			//System.out.println("uns1");
			return false;
		}

		String code1=code.substring(mclass.length()+2,code.length()).trim();

		StringTokenizer tok2=new StringTokenizer(code1,"(");
		String mcl=tok2.nextToken();
		String mclass2=mcl.trim();


		if(!mclass.equals(mclass2)){
			//System.out.println("ex2");
			return false;
		}

		String code2=code1.substring(mcl.length()+1,code1.length()).trim();

		StringTokenizer tok3=new StringTokenizer(" "+code2,")");
		String arguments=tok3.nextToken();
		StringTokenizer toka=new StringTokenizer(arguments,",");

		while (toka.hasMoreTokens()){
			String x=toka.nextToken().trim();
			StringTokenizer tokx=new StringTokenizer(x," ");
			if(tokx.countTokens()<2 && !x.trim().equals("")){
				//System.out.println("exit");
				mapArgs.clear();
				//System.out.println("ex3");
				return false;
			}
			if(x.trim().equals("")){
				continue;
			}

			int num=tokx.countTokens();
			String tp=tokx.nextToken();
			for (int i = 1; i < num-1; i++) {
				tp+=" "+tokx.nextToken();
			}

			if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppType(), tp)){
				//System.out.println("uns2");
				return false;
			}

			String n=tokx.nextToken();
			if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), n)){
				mapArgs.clear();
				//System.out.println("ex4");
				return false;
			}
			mapArgs.put(n, tp);
		}

		String code3=code2.substring(arguments.length(),code2.length()).trim();
		String classd="";

	if(code3.startsWith(":")){


		String code4=code3.substring(1,code3.length()).trim();


		if(code4.substring(0,1).matches("[A-Z]")){
			//it has a superclass
			isDerivate=true;
			StringTokenizer toks=new StringTokenizer(code4,"(");
			String cld=toks.nextToken();
			classd=cld.trim();
			if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), classd)){
				//System.out.println("uns3");
				return false;
			}
			code4=code4.substring(cld.length()+1,code4.length()).trim();
			StringTokenizer tokk=new StringTokenizer(" "+code4,")");
			String args=tokk.nextToken();
			StringTokenizer tokkk=new StringTokenizer(args,",");
			while (tokkk.hasMoreTokens()){
				String n1=tokkk.nextToken().trim();
				superlist.add(n1);
			}
			code4=code4.substring(args.length()+1,code4.length()).trim();
		}
		code3=code4;
	 }

	if(!code3.startsWith("{")){
		superlist.clear();
		mapArgs.clear();
		//System.out.println("extboh");
		return false;
	}
	String code5=code3.substring(1,code3.length());

	StringTokenizer tokf=new StringTokenizer(code5,";");

	while (tokf.hasMoreTokens()){
		String x=tokf.nextToken().trim();
		if(x.equals("}")){
			break;
		}
		int space=0;
		if(x.startsWith("this.")){
			space=5;
		} else {
			if(x.startsWith("this->")){
				space=6;
			} else{
				mapArgs.clear();
				superlist.clear();
				assigned.clear();
				//System.out.println("ex5");
				return false;
			}
		}
		String x1=x.substring(space,x.length()).trim();
		StringTokenizer tokx=new StringTokenizer(x1,"=");
		String n=tokx.nextToken().trim();
		if(!mapArgs.containsKey(n)){
			mapArgs.clear();
			superlist.clear();
			assigned.clear();
			//System.out.println("ex6");
			return false;
		}
		String n2=tokx.nextToken().trim();
		if(!n2.equals(n)){
			mapArgs.clear();
			superlist.clear();
			assigned.clear();
			//System.out.println("ex7");
			return false;
		}
		assigned.add(n);
	}

	this.classn=mclass;
	this.classin=classd;

	return true;
	}

	@Override
	public String constructCode() {
		String s=classn+"::"+classn+"( ";

		Set<String> set=mapArgs.keySet();


		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String name =  iterator.next();
			s+=mapArgs.get(name)+" "+name+",";
		}
		if(s.endsWith(",")){
			s=s.substring(0,s.length()-1);
		}
		s+=")";

		if(isDerivate || superlist.size()>0){
			s+=":"+ classin+"(";
			if(superlist.size()>0){
				s+=superlist.get(0);
				for (int i = 1; i < superlist.size(); i++) {
					s+=","+superlist.get(i);
				}
			}
			s+=") ";
		}

		s+="{\n";
		for (int i = 0; i < assigned.size(); i++) {
			s+="\tthis->"+assigned.get(i)+" = "+assigned.get(i)+";\n";
		}
		Set<String> set2=defInstance.keySet();
		for (Iterator<String> iterator = set2.iterator(); iterator.hasNext();) {
			String string =iterator.next();
			s+="\tthis->"+string+"="+defInstance.get(string)+";\n";
		}

		s+="}";

		return s;
	}


	public Template clone(){
		String a=classn;
		String a1=classin;
		LinkedHashMap<String, String> b=new LinkedHashMap<String, String>();
		b.putAll(mapArgs);
		boolean c=isDerivate;
		List<String> d=new ArrayList<String>();
		d.addAll(superlist);
		List<String> e=new ArrayList<String>();
		e.addAll(assigned);

		return new BasicConstructorCppTemplate(a,a1,b,c,d,e);
	}


	@Override
	public HashMap<String, String> getProperties() {

		HashMap<String, String> map=new HashMap<String, String>();
		map.put("$CLASS$", classn);
		map.put("$FATHER$", classin);
		map.put("$DER$", isDerivate+"");

		String s="";
		Set<String> set=mapArgs.keySet();
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String  name= iterator.next();
			s+=name+"$"+mapArgs.get(name)+"&";
		}
		map.put("$ARGS$", s);

		s="";
		for (int i = 0; i < superlist.size(); i++) {
			s+=superlist.get(i)+"&";
		}
		map.put("$SUPER$", s);

		s="";
		for (int i = 0; i < assigned.size(); i++) {
			s+=assigned.get(i)+"&";
		}
		map.put("$ASS$", s);


		return map;
	}

	@Override
	public void setProperty(String prop, String value) {
		if(prop.equals("$CLASS$")){
			this.classn=value;
		}
		if(prop.equals("$FATHER$")){
			this.classin=value;
		}
		if(prop.equals("$DER$")){
			this.isDerivate=Boolean.parseBoolean(value);
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
				if(type.equalsIgnoreCase("boolean")){
					type="bool";
				}
				mapArgs.put(name,type);
			}
		}
		if(prop.equals("$SUPER$")){
			StringTokenizer tok=new StringTokenizer(value,"&");
			superlist.clear();
			while(tok.hasMoreTokens()){
				superlist.add(tok.nextToken());
			}
		}
		if(prop.equals("$ASS$")){
			StringTokenizer tok=new StringTokenizer(value,"&");
			assigned.clear();
			while(tok.hasMoreTokens()){
				assigned.add(tok.nextToken());
			}
		}
		if(prop.equals("$DEF$")){
			StringTokenizer tok=new StringTokenizer(value,"&");
			defInstance.clear();
			while (tok.hasMoreTokens()){
				String x=tok.nextToken();
				StringTokenizer tokx=new StringTokenizer(x,"$");
				String name=tokx.nextToken();
				String ass=tokx.nextToken();
				if(!assigned.contains(name)){
					defInstance.put(name,ass);
				}
			}
		}


	}





}
