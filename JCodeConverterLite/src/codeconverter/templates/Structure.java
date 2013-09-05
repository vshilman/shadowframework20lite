package codeconverter.templates;

import java.util.List;

public interface Structure {

	public List<Template> getTemplates();
	public String buildCode(String className,List<Template> convlist);
}
