package codeconverter.templates.test.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import codeconverter.templates.EmptyTemplate;
import codeconverter.templates.Structure;
import codeconverter.templates.Template;
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
		list.add(new EmptyTemplate());
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
		fin+=name+".h\n\n";

		for (int i = 0; i < convlist.size(); i++) {
			fin+=convlist.get(i).constructCode()+"\n\n";
		}

		return fin;
	}



}
