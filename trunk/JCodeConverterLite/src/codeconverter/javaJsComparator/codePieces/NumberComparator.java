package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.comparator.CodePieceComparator;

public class NumberComparator extends CodePieceComparator {

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {

		String formattedJavaNumber = getFormattedNumber(lang1Father.toString());
		String formattedJsNumber = getFormattedNumber(lang2Father.toString());

		if (!formattedJavaNumber.equals(formattedJsNumber)) {
			return false;
		}

		return true;
	}

	private String getFormattedNumber(String number) {
		String formattedNumber = "";
		for (int i = 0; i < number.length(); i++) {
			if (!(number.charAt(i) <= 'Z' && number.charAt(i) >= 'A' || number.charAt(i) <= 'z'
					&& number.charAt(i) >= 'a')) {
				formattedNumber += number.charAt(i);
			}
		}
		return formattedNumber;
	}

}
