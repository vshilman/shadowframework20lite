package codeconverter.templates;

import java.io.InputStream;
import java.text.spi.DecimalFormatSymbolsProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import tests.tmp.GeneralTests;

import codeconverter.Block;
import codeconverter.BlockDataInterpreter;
import codeconverter.CodeLine;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.DeclaredBlock;
import codeconverter.factories.DataStructureTemplateFactory;
import codeconverter.factories.LanguagesObjectsFactory;

public class ConversionByTemplateDelegate {


	private LanguagesObjectsFactory lof;
	private DataStructureTemplateFactory dtf;

	private String father="";
	private String isDerivate="";
	private String name="";



	public ConversionByTemplateDelegate(LanguagesObjectsFactory lof,
			DataStructureTemplateFactory dtf) {
		super();
		this.lof = lof;
		this.dtf = dtf;
	}





	public String convertCode(String nameIn, InputStream streamIn,String langOut){

		father="";
		isDerivate="";
		String ext=getFileExtension(nameIn);
		name=nameIn.substring(0,nameIn.length()-(ext.length()+1));


		BlockDataInterpreter interpreter=lof.getBlockDataInterpreter(ext);
		Structure struct=dtf.getDataStructure(ext);
		if(interpreter==null || struct==null){
			System.out.println("Aho sto a usci'");
			return null;
		}

		Block rootBlock=GeneralTests.getBlocks(streamIn, ext);
		HashMap<CodeModule,CodePattern> patMap=GeneralTests.generateInterpretation(interpreter, rootBlock);

		Set<CodeModule> mod=patMap.keySet();
		Set<CodeModule> modOut=new LinkedHashSet<CodeModule>();

		List<Template> list=struct.getTemplates();
		List<Template> outList=dtf.getDataStructure(langOut).getTemplates();

		List<CodeModule> converted=new ArrayList<CodeModule>();
		List<Template> convlist=new ArrayList<Template>();

		for (Iterator<CodeModule> iterator = mod.iterator(); iterator.hasNext();) {
			CodeModule cd =  iterator.next();
			String code=cd.getExtendedCode();
			if(cd instanceof DeclaredBlock){
				modOut.add(((DeclaredBlock) cd).getBlockDeclaration());
			}

			research(list, outList, cd, code, converted, convlist);
		}
		for (Iterator<CodeModule> iterator = modOut.iterator(); iterator.hasNext();) {
			CodeModule cd = iterator.next();
			String code=cd.getCode();
			//System.out.println("........."+code+"......");
			research(list, outList, cd, code, converted, convlist);
		}
		generalInformationsSetting(convlist);
		orderByLine(convlist, converted);
		fillHoles(convlist, converted, mod);

	//	for (int i = 0; i < convlist.size(); i++) {
	//		System.out.println(convlist.get(i).constructCode()+"\n{"+converted.get(i).getFirstLine()+","+converted.get(i).getLastLine()+"}");
	//	}

		return dtf.getDataStructure(langOut).buildCode(name,convlist);
	}


	private void fillHoles(List<Template> convlist, List<CodeModule> converted,Set<CodeModule> set){

		for (Iterator<CodeModule> iterator = set.iterator(); iterator.hasNext();) {
			CodeModule cd =  iterator.next();
			for (int i = 0; i < converted.size(); i++) {
				if(cd.getFirstLine()>converted.get(i).getLastLine()){
					if(i!=(converted.size()-1)){
						if(cd.getLastLine()<converted.get(i+1).getFirstLine()){
							converted.add(i+1,cd);
							convlist.add(i+1,new CannotConvertTemplate("/*\n"+cd.getExtendedCode()+"\n*/"));
							break;
						}
					} else {
						converted.add(cd);
						convlist.add(new CannotConvertTemplate("/*"+cd.getExtendedCode()+"*/"));
						break;
					}
				}
				if(cd.getLastLine()<converted.get(0).getFirstLine()){
					converted.add(0,cd);
					convlist.add(0,new CannotConvertTemplate("/*"+cd.getExtendedCode()+"*/"));
				}
			}
		}

	}


	private void orderByLine(List<Template> convlist, List<CodeModule> converted){

		for (int i = 0; i < converted.size(); i++) {
			for (int j = i; j < converted.size(); j++) {
				if(converted.get(i).getFirstLine()>converted.get(j).getLastLine()){
					CodeModule buf=converted.get(i);
					CodeModule buff=converted.get(j);
					converted.remove(i);
					converted.add(i, buff);
					converted.remove(j);
					converted.add(j, buf);

					Template buf2=convlist.get(i);
					Template buff2=convlist.get(j);
					convlist.remove(i);
					convlist.add(i, buff2);
					convlist.remove(j);
					convlist.add(j, buf2);
				}
			}
		}


	}

	private void research(List<Template> list,List<Template> outList,CodeModule cd,String code,List<CodeModule> converted,List<Template> convlist){
		int index=-1;
		Template templ=null;
		for (int i = 0; i < list.size(); i++) {
			templ=list.get(i).clone();
			if(templ.matchCode(code)){
				index=i;
				break;
			}
		}
		System.out.println("*"+index+"*"+"-"+"["+cd.getFirstLine()+","+cd.getLastLine()+"]");
		if(index!=-1) {
			while(index>outList.size()){
				index--;
			}
			Template templOut=outList.get(index).clone();
			//System.out.println(templOut.getProperties().get("$NAME$")!=null?templOut.getProperties().get("$NAME$"):"non c'e");
			HashMap<String, String> map=templ.getProperties();
			if(map.containsKey("$DER$")){
				isDerivate=map.get("$DER$");
				if(Boolean.parseBoolean(isDerivate) && map.containsKey("$FATHER$")){
					father=map.get("$FATHER$");
				}
			}
			Set<String> set=map.keySet();
			for (Iterator<String> iterator2 = set.iterator(); iterator2.hasNext();) {
				String prop = iterator2.next();
				templOut.setProperty(prop, map.get(prop));
			}
			converted.add(cd);
			//convertions.add(templOut.constructCode());
			//convmap.put(templOut, index);
			convlist.add(templOut);
		}
	}

	//General informations setting (if someone needs them) [not very abstract-> all the template class must declare this attributes with the name below]
	private void generalInformationsSetting(List<Template> convlist){
		for (int i = 0; i < convlist.size(); i++) {
			Template templOut=convlist.get(i);
			templOut.setProperty("$CLASS$", name);
			if(!isDerivate.equals("")){
				templOut.setProperty("$DER$", isDerivate);
			}
			if(!father.equals("")){
				templOut.setProperty("$FATHER$", father);
			}
		}
	}



	private static String getFileExtension(String name){

		String ext="";
		if(name.contains(".")){
			ext=name.substring(name.lastIndexOf(".")+1);
		}
		return  ext;
	}


}
