package codeconverter.javaCppComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.comparator.CodePieceComparator;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlConstantComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
import codeconverter.javaJsComparator.codePieces.TernaryOperatorComparator;

public class ArrayContentComparatorJC extends CodePieceComparator{

	private ExpressionComparatorJC expressionComparator;

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces,
			List<ICodePiece> cppPieces) {

		initializeComparators();

		List<ICodePiece> javaPiecesComp = javaPieces.get(1).getPieces();
		List<ICodePiece> cppPiecesComp = cppPieces.get(1).getPieces();

		for (int j = 0; j < javaPiecesComp.size(); j++) {
			if (javaPiecesComp.get(j).getPieceType() == PieceType.EXPRESSION) {
				if (!expressionComparator.compare(javaPiecesComp.get(j), cppPiecesComp.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	private void initializeComparators() {
		if (expressionComparator == null) {
			expressionComparator = new ExpressionComparatorJC();
			TernaryOperatorComparatorJC ternaryOperatorComparator = new TernaryOperatorComparatorJC();
			MethodComparatorJC methodComparator = new MethodComparatorJC();
			BooleanExpressionComparatorJC booleanExpressionComparator = new BooleanExpressionComparatorJC();
			NewStatementComparatorJC newStatementComparator = new NewStatementComparatorJC();
			NameComparatorJC nameComparator = new NameComparatorJC();

			nameComparator.setComparators(expressionComparator);
			ternaryOperatorComparator.setComparators(expressionComparator, booleanExpressionComparator);
			methodComparator.setComparators(nameComparator, expressionComparator, newStatementComparator);
			booleanExpressionComparator.setComparators(nameComparator, methodComparator);
			newStatementComparator.setComparators(nameComparator, expressionComparator);
			expressionComparator.setComparators(nameComparator, ternaryOperatorComparator, methodComparator);
		}
	}


}
