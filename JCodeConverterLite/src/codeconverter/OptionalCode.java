package codeconverter;

import java.util.ArrayList;
import java.util.List;

public class OptionalCode  implements ICodePiece {

	private ICodePiece element;
	private ICodePiece piece;

	public OptionalCode(ICodePiece element) {
		this.element=element;
	}


	@Override
	public int elementMatch(String data, int matchPosition) {
		int position=matchPosition;
		int nextIndex=0;
		
		nextIndex=element.elementMatch(data,position);
		piece=(ICodePiece)(element.cloneCodePiece());

		if(nextIndex==-1){
			return nextIndex;
		}

		return matchPosition;
	}

	public ICodePiece getPiece() {
		return piece;
	}

	@Override
	public ICodePiece cloneCodePiece() {
		OptionalCode code=new OptionalCode(element);
		code.piece=this.piece;
		return code;
	}
}
