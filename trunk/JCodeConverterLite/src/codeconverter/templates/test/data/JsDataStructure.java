package codeconverter.templates.test.data;

import java.util.ArrayList;
import java.util.List;

import codeconverter.templates.EmptyTemplate;
import codeconverter.templates.Structure;
import codeconverter.templates.Template;
import codeconverter.templates.basicattributedeclaration.BasicAttributeDeclarationJavaTemplate;
import codeconverter.templates.basicattributedeclaration.BasicAttributeDeclarationJsTemplate;
import codeconverter.templates.basicclassdeclaration.BasicClassDeclarationJavaTemplate;
import codeconverter.templates.basicconstructor.BasicConstructorJavaTemplate;
import codeconverter.templates.basicconstructor.BasicContructorJsTemplate;
import codeconverter.templates.basicget.BasicGetJavaTemplate;
import codeconverter.templates.basicget.BasicGetJsTemplate;
import codeconverter.templates.basicset.BasicSetJavaTemplate;
import codeconverter.templates.basicset.BasicSetJsTemplate;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class JsDataStructure implements Structure {

	private static List<Template> list=new ArrayList<Template>();

	static{
		list.add(new EmptyTemplate());
		list.add(new BasicAttributeDeclarationJsTemplate());
		list.add(new BasicGetJsTemplate());
		list.add(new BasicSetJsTemplate());
		list.add(new BasicContructorJsTemplate());
	}

	@Override
	public List<Template> getTemplates() {
		return list;
	}

	@Override
	public String buildCode(String className,List<Template> convlist) {

		String fin="\n\n";
		while(GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 1)!=null){
			fin+=GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 1).constructCode()+"\n\n";
			GeneralPurposeTemplateUtilities.deleteByID(convlist, 1, false);
		}
		if(GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 4)==null){
			BasicContructorJsTemplate t=new BasicContructorJsTemplate();
			t.setProperty("$CLASS$", className);
			fin+=t.constructCode()+"\n\n";
		}

		for (int i = 0; i < convlist.size(); i++) {
			fin+=convlist.get(i).constructCode()+"\n\n";
		}

		return fin;
	}



}
