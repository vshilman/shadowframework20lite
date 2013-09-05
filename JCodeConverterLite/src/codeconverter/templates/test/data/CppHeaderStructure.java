package codeconverter.templates.test.data;

import java.util.ArrayList;
import java.util.List;

import codeconverter.templates.Structure;
import codeconverter.templates.Template;
import codeconverter.templates.basicattributedeclaration.BasicAtrributeDeclarationCppHeaderTemplate;
import codeconverter.templates.basicclassdeclaration.BasicClassDeclarationCppHeaderTemplate;
import codeconverter.templates.basicconstructor.BasicConstructorCppHeaderTemplate;
import codeconverter.templates.basicget.BasicGetCppHeaderTemplate;
import codeconverter.templates.basicset.BasicSetCppHeaderTemplate;
import codeconverter.templates.utilities.GeneralPurposeTemplateUtilities;

public class CppHeaderStructure implements Structure{

	private static List<Template> list=new ArrayList<Template>();

	static{
		list.add(new BasicClassDeclarationCppHeaderTemplate());
		list.add(new BasicAtrributeDeclarationCppHeaderTemplate());
		list.add(new BasicGetCppHeaderTemplate());
		list.add(new BasicSetCppHeaderTemplate());
		list.add(new BasicConstructorCppHeaderTemplate());
	}

	@Override
	public List<Template> getTemplates() {
		return list;
	}

	@Override
	public String buildCode(String className,List<Template> convlist) {

		String fin="#include <string>\n"; //too much common

		boolean hasClassDec=false;
		Template t=null;
		if(GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 0)!=null){
			hasClassDec=true;
			t=GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 0);
			boolean isDer=Boolean.parseBoolean(t.getProperties().get("$DER$"));
			if(isDer){
				String father=t.getProperties().get("$FATHER$");
				fin+="#include "+father+".h\n";
			}
		}
		fin+="\nusing namespace std;\n\n";
		if(hasClassDec){
			fin+=t.constructCode();
			GeneralPurposeTemplateUtilities.deleteByID(convlist, 0,true);
		} else {
			if(GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 4)!=null){
				String name=GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 4).getProperties().get("$CLASS$");
				fin+="class "+name;
			} else {
				fin+="class "+className;
			}
		}
		fin+="{\n\n";
		while(GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 1)!=null){
			Template tl=GeneralPurposeTemplateUtilities.findTemplateByID(convlist, 1);
			fin+=tl.constructCode()+"\n\n";
			GeneralPurposeTemplateUtilities.deleteByID(convlist, 1, false);
		}
		fin+="public:\n\n";

		for (int i = 0; i < convlist.size(); i++) {
			fin+=convlist.get(i).constructCode()+"\n\n";
		}

		fin+="};";

		return fin;
	}

}
