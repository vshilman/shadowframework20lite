package codeconverter.javaCppComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.comparator.CodePieceComparator;

public class NumberComparatorJC extends CodePieceComparator {

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> cppPieces) {

		String formattedJavaNumber = getFormattedNumber(lang1Father.toString());
		String formattedCppNumber = getFormattedNumber(lang2Father.toString());

		if (!formattedJavaNumber.equals(formattedCppNumber)) {
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
