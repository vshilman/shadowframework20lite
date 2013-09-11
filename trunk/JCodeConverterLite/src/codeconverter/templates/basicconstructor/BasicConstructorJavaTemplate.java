package codeconverter.templates.basicconstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.JavaName;
import codeconverter.java.JavaType;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicConstructorJavaTemplate implements Template{

	public static final int ID=4;

	private String modifier="$MOD$";
	private String classn="$CLASS$";
	private LinkedHashMap<String, String> mapArgs=new LinkedHashMap<String, String>();
	private List<String> supernames=new ArrayList<String>();
	private List<String> assigned=new ArrayList<String>();

	public BasicConstructorJavaTemplate() {

	}
	public int getId(){
		return ID;
	}

	private BasicConstructorJavaTemplate(String modifier, String classn,
			LinkedHashMap<String, String> mapArgs, List<String> supernames,
			List<String> assigned) {
		super();
		this.modifier = modifier;
		this.classn = classn;
		this.mapArgs = mapArgs;
		this.supernames = supernames;
		this.assigned = assigned;
	}


	@Override
	public boolean matchCode(String code) {

		code=code.trim();
		mapArgs.clear();
		supernames.clear();
		assigned.clear();
		//System.out.println("Processing... "+code);
		String modc=code.split(" ")[0];
		if(!(modc.equals("public") || modc.equals("private") || modc.equals("protected"))){
			System.out.println("ex1");
			return false;
		}


		String code1=code.substring(modc.length(),code.length()).trim();
		if(!code1.contains("(")){
			System.out.println("ex2");
			return false;
		}
		StringTokenizer tok=new StringTokenizer(code1,"(");
		String mcl=tok.nextToken();
		String mclass=mcl.trim();
		if(!GeneralPurposeTemplateUtilities.isNameSupported(new JavaName(), mclass)){
			System.out.println("uns1");
			return false;
		}
		String code2=code1.substring(mcl.length()+1,code1.length()).trim();
		StringTokenizer tok2=new StringTokenizer(" "+code2,")");
		String arguments=tok2.nextToken();
		StringTokenizer toka=new StringTokenizer(arguments,",");

		while (toka.hasMoreTokens()){
			String x=toka.nextToken().trim();
			//System.out.println("-->"+x);
			StringTokenizer tokx=new StringTokenizer(x," ");
			if(tokx.countTokens()<2 && !x.trim().equals("")){
				System.out.println("exit");
				mapArgs.clear();
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
			if(!GeneralPurposeTemplateUtilities.isNameSupported(new JavaType(), tp)){
				System.out.println("uns2");
				return false;
			}
			String n=tokx.nextToken();
			if(!GeneralPurposeTemplateUtilities.isNameSupported(new JavaName(), n)){
				mapArgs.clear();
				System.out.println("ex3");
				return false;
			}
			mapArgs.put(n, tp);
		}

		String code3=code2.substring(arguments.length(),code2.length()).trim();
		if(!code3.startsWith("{")){
			mapArgs.clear();
			System.out.println("ex4");
			return false;
		}

		String code4=code3.substring(1,code3.length()).trim();
		if(code4.startsWith("super(")){
			code4=code4.substring(6,code4.length()).trim();
			StringTokenizer tokk=new StringTokenizer(" "+code4,")");
			String args=tokk.nextToken();
			StringTokenizer tokkk=new StringTokenizer(args,",");
			while (tokkk.hasMoreTokens()){
				String n1=tokkk.nextToken().trim();
				if(n1.equals("")){
					continue;
				}
				if(!(GeneralPurposeTemplateUtilities.isNameSupported(new JavaName(), n1))){
					System.out.println("uns3");
					return false;
				}
				supernames.add(n1);
			}
			code4=code4.substring(args.length()+2,code4.length()).trim();
		}

		StringTokenizer tokf=new StringTokenizer(code4,";");

		while (tokf.hasMoreTokens()){
			String x=tokf.nextToken().trim();
			//System.out.println("----"+x);

			if(x.equals("}")){
				break;
			}

			if(!x.startsWith("this.")){
				mapArgs.clear();
				supernames.clear();
				assigned.clear();
				System.out.println("ex5");
				return false;
			}
			String x1=x.substring(5,x.length()).trim();
			StringTokenizer tokx=new StringTokenizer(x1,"=");
			String n=tokx.nextToken().trim();
			if(!mapArgs.containsKey(n)){
				mapArgs.clear();
				supernames.clear();
				assigned.clear();
				System.out.println("ex6");
				return false;
			}
			String n2=tokx.nextToken().trim();
			if(!n2.equals(n)){
				mapArgs.clear();
				supernames.clear();
				assigned.clear();
				System.out.println("ex7");
				return false;
			}
			assigned.add(n);
		}

		this.modifier=modc;
		this.classn=mclass;

		return true;
	}

	@Override
	public String constructCode() {
		String s=(modifier.equals("$MOD$")? "public": modifier)+" "+classn+"(";
		Set<String> set=mapArgs.keySet();
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String str=iterator.next();
			s+=mapArgs.get(str)+" "+str+",";
		}
		if(s.endsWith(",")){
			s=s.substring(0,s.length()-1);
		}
		s+=") {\n\tsuper(";

		if(supernames.size()>0){
			s+=supernames.get(0);
			for (int i = 1; i < supernames.size(); i++) {
				s+=", "+supernames.get(i);
			}
		}
		s+=");\n";

		for (int i = 0; i < assigned.size(); i++) {
			String str=assigned.get(i);
			s+="\tthis."+str+" = "+str+";\n";
		}
		s+="}";
		return s;
	}

	public Template clone(){

		String a=modifier;
		String a1=classn;
		LinkedHashMap<String, String> b=new LinkedHashMap<String, String>();
		b.putAll(mapArgs);
		List<String> d=new ArrayList<String>();
		d.addAll(supernames);
		List<String> e=new ArrayList<String>();
		e.addAll(assigned);
		return new BasicConstructorJavaTemplate(a,a1,b,d,e);
	}

	@Override
	public HashMap<String, String> getProperties() {
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("$MOD$", modifier);
		map.put("$CLASS$", classn);

		String s="";
		Set<String> set=mapArgs.keySet();
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String  name= iterator.next();
			s+=name+"$"+mapArgs.get(name)+"&";
		}
		map.put("$ARGS$", s);

		s="";
		for (int i = 0; i < supernames.size(); i++) {
			s+=supernames.get(i)+"&";
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
		if(prop.equals("$MOD$")){
			this.modifier=value;
		}
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
					type="String";
				}
				if(type.equalsIgnoreCase("bool")){
					type="boolean";
				}
				mapArgs.put(name,type);
			}
		}
		if(prop.equals("$SUPER$")){
			StringTokenizer tok=new StringTokenizer(value,"&");
			supernames.clear();
			while(tok.hasMoreTokens()){
				supernames.add(tok.nextToken());
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
