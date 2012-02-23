
package shadow.pipeline.loader.parser;

import java.util.HashMap;
import java.util.StringTokenizer;

import shadow.pipeline.loader.SFLineParser;
import shadow.pipeline.loader.SFParsableElement;
import shadow.pipeline.loader.SFParsableGrid;
import shadow.pipeline.loader.SFParsableProgramComponent;
import shadow.pipeline.loader.SFParsableStructure;
import shadow.system.SFException;

public class SFBeginParser implements SFLineParser{

	private static HashMap<String, Class> typeMap=new HashMap<String, Class>();
	
	static{
		typeMap.put("Tessellator", SFParsableProgramComponent.class);
		typeMap.put("Primitive", SFParsableProgramComponent.class);
		typeMap.put("Transforms", SFParsableProgramComponent.class);
		typeMap.put("Material", SFParsableProgramComponent.class);
		typeMap.put("LightStep", SFParsableProgramComponent.class);
		typeMap.put("Grid", SFParsableGrid.class);
		typeMap.put("Structure", SFParsableStructure.class);
	}
	
	private static SFParsableElement getElement(String type) {
		
		try {
			return (SFParsableElement)(typeMap.get(type).newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public SFParsableElement parseLine(SFParsableElement component,StringTokenizer tokenizer,
			int codeLine) throws SFException {
		
		if(component!=null){
			throw new SFException("("+codeLine+") Begin called, but last Components is " +
					"not finished. use end command please :"+tokenizer.nextToken());
		}

		SFParsableElement temp=null;
		String type,name;
		if(tokenizer.hasMoreElements()){
			type=tokenizer.nextToken();
			if(tokenizer.hasMoreElements()){
				name=tokenizer.nextToken();
				
				try {
					temp=getElement(type);
				} catch (Exception e) {
					throw new SFException("("+codeLine+"):Begin Called with an undefined type ("+type+") only primitive," +
						"tesselator,transforms,material and lightstep are allowed");
				} 
				temp.setName(name);
				
				if(tokenizer.hasMoreTokens()){
					throw new SFException("("+codeLine+"): Begin Command has too much parameters");
				}
			}else{
				throw new SFException("("+codeLine+"): Begin Command not well formed, a correct type and a name are required");
			}
		}else{
			throw new SFException("("+codeLine+"): Begin Command not well formed, a correct type and a name are required");
		}
		return temp;
	}
}
