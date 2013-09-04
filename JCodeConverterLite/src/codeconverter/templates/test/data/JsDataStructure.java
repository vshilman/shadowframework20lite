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
	public String buildCode(List<Template> convlist) {

		String fin="\n\n";
		for (int i = 0; i < convlist.size(); i++) {
			fin+=convlist.get(i).constructCode()+"\n\n";
		}

		return fin;
	}



}
