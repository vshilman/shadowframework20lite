package codeconverter.templates.basicconstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.js.JsName;
import codeconverter.templates.Template;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class BasicContructorJsTemplate implements Template{

	public static final int ID=4;

	private String classn;
	private List<String> list=new ArrayList<String>();
	private List<String> assigned=new ArrayList<String>();



	/*function SFFloat(floatValue){
		this.floatValue=floatValue;
	}*/

	public BasicContructorJsTemplate() {

	}


	public int getId(){
		return ID;
	}


	private BasicContructorJsTemplate(String classn, List<String> list,
			List<String> assigned) {
		super();
		this.classn = classn;
		this.list = list;
		this.assigned = assigned;
	}


	@Override
	public boolean matchCode(String code) {
		code=code.trim();
		list.clear();
		assigned.clear();

		//System.out.println("Processing... "+code);
		if(!code.startsWith("function")){
			System.out.println("ex1");
			return false;
		}

		String code1=code.substring(8,code.length()).trim();

		StringTokenizer tok=new StringTokenizer(code1,"(");
		String mcl=tok.nextToken();
		String mclass=mcl.trim();
		if(!GeneralPurposeTemplateUtilities.isNameSupported(new JsName(), mclass)){
			System.out.println("uns1");
			return false;
		}
		String code2=code1.substring(mcl.length(),code1.length());
		if(!code2.startsWith("(")){
			System.out.println("ex2");
			return false;
		}

		String code3=code2.substring(1,code2.length()).trim();
		StringTokenizer tok2=new StringTokenizer(" "+code3,")");
		String param=tok2.nextToken();
		StringTokenizer tokp=new StringTokenizer(param,",");

		while(tokp.hasMoreTokens()){
			String x=tokp.nextToken().trim();

			if(x.equals("")){
				continue;
			}

			if(!GeneralPurposeTemplateUtilities.isNameSupported(new JsName(), x)){
				list.clear();
				System.out.println("ex3");
				return false;
			}
			list.add(x);
		}

		String code4=code3.substring(param.length(),code3.length()).trim();
		if(!code4.startsWith("{")){
			list.clear();
			System.out.println("ex4");
			return false;
		}
		String code5=code4.substring(1,code4.length());
		StringTokenizer tokf=new StringTokenizer(code5,";");

		int count=0;
		while (tokf.hasMoreTokens()){
			String x=tokf.nextToken().trim();

			if(x.equals("}")){
				break;
			}
			if(!x.startsWith("this.")){
				list.clear();
				assigned.clear();
				System.out.println("ex5");
				return false;
			}
			String x1=x.substring(5,x.length()).trim();
			StringTokenizer tokx=new StringTokenizer(x1,"=");
			String n=tokx.nextToken().trim();
			if(!list.contains(n)){
				list.clear();
				assigned.clear();
				System.out.println("ex6");
				return false;
			}
			String n2=tokx.nextToken().trim();
			if(!n2.equals(n)){
				list.clear();
				assigned.clear();
				System.out.println("ex7");
				return false;
			}
			assigned.add(n);
		}

		this.classn=mclass;


		return true;
	}

	@Override
	public String constructCode() {

		String s="function "+classn+"( ";
		if(list.size()>0){
			s+=list.get(0);
			for (int i = 1; i < list.size(); i++) {
				s+=","+list.get(i);
			}
		}
		s+=") {\n";

		for (int i = 0; i <assigned.size(); i++) {
			s+="\tthis."+assigned.get(i)+" = "+assigned.get(i)+";\n";
		}

		s+="}";

		return s;
	}

	public Template clone(){
		String a=classn;
		List<String> b=new ArrayList<String>();
		b.addAll(list);
		List<String> c=new ArrayList<String>();
		b.addAll(assigned);
		return new BasicContructorJsTemplate(a,b,c);
	}


	@Override
	public HashMap<String, String> getProperties() {
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("$CLASS$", classn);

		String s="";
		for (int i = 0; i < list.size(); i++) {
			s+=list.get(i)+"$"+" &";
		}
		map.put("$ARGS$", s);

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
		if(prop.equals("$ARGS$")){
			StringTokenizer tok=new StringTokenizer(value,"&");
			list.clear();
			while (tok.hasMoreTokens()){
				String x=tok.nextToken();
				StringTokenizer tokx=new StringTokenizer(x,"$");
				String name=tokx.nextToken();
				String type=tokx.nextToken();
				list.add(name);
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
