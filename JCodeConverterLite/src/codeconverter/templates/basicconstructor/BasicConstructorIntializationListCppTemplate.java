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

//Multiple inheritance is not accepted
//Based on the simple rule that classes start with capital letters

public class BasicConstructorIntializationListCppTemplate implements Template{

	public static final int ID=4;

	private String classn="$CLASS$";
	private String classin="$CLASSIN$";
	private LinkedHashMap<String, String> mapArgs=new LinkedHashMap<String, String>();
	private boolean isDerivate=false;
	private List<String> superlist=new ArrayList<String>();
	private List<String> assigned=new ArrayList<String>();




	/*	SFValue1f::SFValue1f(int n,float data[],int x, float y):SFValuenf(n,data) , x(x) , y(y) {
	}*/


	public BasicConstructorIntializationListCppTemplate() {
		// TODO Auto-generated constructor stub
	}
	public int getId(){
		return ID;
	}



	private BasicConstructorIntializationListCppTemplate(String classn,
			String classin, LinkedHashMap<String, String> mapArgs,
			boolean isDerivate, List<String> superlist, List<String> assigned) {
		super();
		this.classn = classn;
		this.classin = classin;
		this.mapArgs = mapArgs;
		this.isDerivate = isDerivate;
		this.superlist = superlist;
		this.assigned = assigned;
	}



	@Override
	public boolean matchCode(String code) {

		isDerivate=false;
		code=code.trim();
		mapArgs.clear();
		superlist.clear();
		assigned.clear();
		//System.out.println("Processing... "+code);
		if(!code.contains("::")){
			System.out.println("ex1");
			return false;
		}

		StringTokenizer tok=new StringTokenizer(code,":");
		String mclass=tok.nextToken();
		if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), mclass)){
			System.out.println("uns1");
			return false;
		}

		String code1=code.substring(mclass.length()+2,code.length()).trim();

		StringTokenizer tok2=new StringTokenizer(code1,"(");
		String mcl=tok2.nextToken();
		String mclass2=mcl.trim();

		if(!mclass.equals(mclass2)){
			System.out.println("ex2");
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
				System.out.println("exit");
				mapArgs.clear();
				System.out.println("ex3");
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
				System.out.println("uns3");
				return false;
			}
			String n=tokx.nextToken();
			if(!GeneralPurposeTemplateUtilities.isNameSupported(new CppName(), n)){
				mapArgs.clear();
				System.out.println("ex3");
				System.out.println("ex4");
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

		if(code4.startsWith(",")){
			code4=code4.substring(1,code4.length()).trim();
		}


		if(!code4.contains("{")){
			mapArgs.clear();
			superlist.clear();
			System.out.println("ex5");
			return false;
		}

		StringTokenizer tokz=new StringTokenizer(" "+code4,"{");

		String initlistass=tokz.nextToken();
		StringTokenizer toki=new StringTokenizer(initlistass,",");

		while (toki.hasMoreTokens()){
			String x=toki.nextToken().trim();

			if(x.equals("")){
				continue;
			}
			if(!x.contains("(") || !x.contains(")")){
				mapArgs.clear();
				superlist.clear();
				assigned.clear();
				System.out.println("ex6");
				return false;
			}
			StringTokenizer t1=new StringTokenizer(x,"(");
			String nn1=t1.nextToken();
			String n1=nn1.trim();
			if(!mapArgs.containsKey(n1)){
				mapArgs.clear();
				superlist.clear();
				assigned.clear();
				System.out.println("ex7");
				return false;
			}
			String x1=x.substring(nn1.length()+1,x.length()).trim();
			StringTokenizer t2=new StringTokenizer(" "+x1,")");
			String nn2=t2.nextToken();
			String n2=nn2.trim();
			if(!n1.equals(n2)){
				mapArgs.clear();
				superlist.clear();
				assigned.clear();
				System.out.println("ex8");
				return false;
			}
			String x2=x1.substring(nn2.length(),x1.length()).trim();
			if(!x2.equals("")){
				mapArgs.clear();
				superlist.clear();
				assigned.clear();
				System.out.println("ex9");
				return false;
			}
			assigned.add(n1);
		}

		String code5=code4.substring(initlistass.length()+1,code4.length()).trim();
		if(!code5.equals("}")){
			mapArgs.clear();
			superlist.clear();
			assigned.clear();
			System.out.println("ex10");
			return false;
		}
	} else {
		if(!code3.startsWith("{")){
			mapArgs.clear();
			System.out.println("ex11");
			return false;
		}
		code3=code3.substring(1,code3.length()).trim();
		if(!code3.equals("}")){
			mapArgs.clear();
			System.out.println("ex12");
			return false;
		}
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

		if(isDerivate || assigned.size()>0 || superlist.size()>0){
			s+=":";
		}


		if(isDerivate || superlist.size()>0){
			s+=classin+"(";
			if(superlist.size()>0){
				s+=superlist.get(0);
				for (int i = 1; i < superlist.size(); i++) {
					s+=","+superlist.get(i);
				}
			}
			s+="),";
		}

		for (int i = 0; i < assigned.size(); i++) {
			String name=assigned.get(i);
			s+=name+"("+name+") ,";
		}

		if(s.endsWith(",")){
			s=s.substring(0,s.length()-1);
		}
		s+=" {\n\n}";
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

		return new BasicConstructorIntializationListCppTemplate(a,a1,b,c,d,e);
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

	}



}
