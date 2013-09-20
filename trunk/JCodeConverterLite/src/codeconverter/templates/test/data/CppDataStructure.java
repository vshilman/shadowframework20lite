package codeconverter.templates.test.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import codeconverter.templates.EmptyTemplate;
import codeconverter.templates.Structure;
import codeconverter.templates.Template;
import codeconverter.templates.basicattributedeclaration.BasicAttributeDeclarationCppTemplate;
import codeconverter.templates.basicattributedeclaration.BasicAttributeDeclarationJavaTemplate;
import codeconverter.templates.basicclassdeclaration.BasicClassDeclarationJavaTemplate;
import codeconverter.templates.basicconstructor.BasicConstructorCppTemplate;
import codeconverter.templates.basicconstructor.BasicConstructorIntializationListCppTemplate;
import codeconverter.templates.basicconstructor.BasicConstructorJavaTemplate;
import codeconverter.templates.basicget.BasicGetCppTemplate;
import codeconverter.templates.basicget.BasicGetJavaTemplate;
import codeconverter.templates.basicset.BasicSetCppTemplate;
import codeconverter.templates.basicset.BasicSetJavaTemplate;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class CppDataStructure implements Structure{

	private static List<Template> list=new ArrayList<Template>();

	static{
		list.add(new EmptyTemplate());
		list.add(new BasicAttributeDeclarationCppTemplate());
		list.add(new BasicGetCppTemplate());
		list.add(new BasicSetCppTemplate());
		list.add(new BasicConstructorCppTemplate());
		list.add(new BasicConstructorIntializationListCppTemplate());
	}

	@Override
	public List<Template> getTemplates() {
		return list;
	}

	@Override
	public String buildCode(String className,List<Template> convlist) {

		String fin="#include ";

		int[] good={2,3,4};
		String name=className;
		for (int i = 0; i < good.length; i++) {
			if(GeneralPurposeTemplateUtilities.findTemplateByID(convlist, good[i])!=null){
				name=GeneralPurposeTemplateUtilities.findTemplateByID(convlist, good[i]).getProperties().get("$CLASS$");
				break;
			}
		}
		fin+=name+".h\n#include <iostream>\n\n";

		HashMap<String, String> toInitialize=new HashMap<String, String>();

		while(GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 1)!=null){
			HashMap<String, String> prop=GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 1).getProperties();
			if(Boolean.parseBoolean(prop.get("$ASS$")) && !prop.get("$DEF$").equals("null")){
				toInitialize.put(prop.get("$NAME$"), prop.get("$DEF$"));
			}
			GeneralPurposeTemplateUtilities.deleteByID(convlist, 1, false);
		}

		Set<String> set=toInitialize.keySet();
		String c="";
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String n =  iterator.next();
			c+=n+"$"+toInitialize.get(n)+"&";
		}

		for (int i = 0; i < convlist.size(); i++) {

			if(convlist.get(i).getId()==4){
				convlist.get(i).setProperty("$DEF$", c);
			}
			fin+=convlist.get(i).constructCode()+"\n\n";
		}

		return fin;
	}



}
