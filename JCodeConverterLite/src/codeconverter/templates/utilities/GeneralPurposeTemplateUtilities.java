package codeconverter.templates.utilities;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Value;
import codeconverter.java.JavaType;
import codeconverter.templates.Template;

public class GeneralPurposeTemplateUtilities {

	public static boolean isNameSupported(ICodePiece comp,String s){

		ICodePieceMatch match=comp.elementMatch(s, 0);
		if((match.getMatchPosition()-s.length())==0){
			return true;
		}
		return false;
	}


	public static boolean isValueInArray(String[] array, String s){

		for (int i = 0; i < array.length; i++) {
			if(s.equals(array[i])){
				return true;
			}
		}
		return false;
	}

	public static Template findTemplateByID(List<Template> convlist,int id){

		for (int i = 0; i < convlist.size(); i++) {
			if(convlist.get(i).getId()==id){
				return convlist.get(i).clone();
			}
		}
		return null;
	}

	public static void deleteByID(List<Template> convlist,int id,boolean all){

		for (int i = 0; i < convlist.size(); i++) {
			if(convlist.get(i).getId()==id){
				convlist.remove(i);
				if(!all){
					return;
				}
			}
		}
	}





}
