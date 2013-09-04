package codeconverter.templates.test.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import codeconverter.CodeModule;
import codeconverter.templates.Structure;
import codeconverter.templates.Template;
import codeconverter.templates.basicattributedeclaration.BasicAttributeDeclarationJavaTemplate;
import codeconverter.templates.basicclassdeclaration.BasicClassDeclarationJavaTemplate;
import codeconverter.templates.basicconstructor.BasicConstructorJavaTemplate;
import codeconverter.templates.basicget.BasicGetJavaTemplate;
import codeconverter.templates.basicset.BasicSetJavaTemplate;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class JavaDataStructure implements Structure{

	private static List<Template> list=new ArrayList<Template>();

	static{
		list.add(new BasicClassDeclarationJavaTemplate());
		list.add(new BasicAttributeDeclarationJavaTemplate());
		list.add(new BasicGetJavaTemplate());
		list.add(new BasicSetJavaTemplate());
		list.add(new BasicConstructorJavaTemplate());
	}

	@Override
	public List<Template> getTemplates() {
		return list;
	}

	public String buildCode(List<Template> convlist){

		String fin="//Package and imports are not generated automatically\n\n\n";
		if(GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 0)!=null){
			fin+=GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 0).constructCode();
			GeneralPurposeTemplateUtilities.deleteByID(convlist, 0,true);
		} else {
			if(GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 4)!=null){
				String name=GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 4).getProperties().get("$CLASS$");
				fin+="public class "+name;
			} else {
				fin+="public class $CLASS_NAME$";
			}
		}
		fin+="{\n\n";

		for (int i = 0; i < convlist.size(); i++) {
			fin+=convlist.get(i).constructCode()+"\n\n";
		}

		fin+="}";

		return fin;
	}





}
