package codeconverter.javaJsComparator.special;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlConstantComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
import codeconverter.javaJsComparator.codePieces.TernaryOperatorComparator;

public class ArrayContentComparator {

	private ExpressionComparator expressionComparator;

	public boolean compare(CodePattern javaCodePattern, ICodePiece jsCodePiece) {
		initializeComparators();

		if (jsCodePiece == null) {
			return false;
		}

		List<ICodePiece> javaPieces = javaCodePattern.getPieces().get(0).getPieces();
		List<ICodePiece> jsPieces = jsCodePiece.getPieces().get(1).getPieces();

		for (int j = 0; j < javaPieces.size(); j++) {
			if (javaPieces.get(j).getPieceType() == PieceType.EXPRESSION) {
				if (!expressionComparator.compare(javaPieces.get(j), jsPieces.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	private void initializeComparators() {
		if (expressionComparator == null) {
			expressionComparator = new ExpressionComparator();
			TernaryOperatorComparator ternaryOperatorComparator = new TernaryOperatorComparator();
			OpenGlMethodComparator openGlMethodComparator = new OpenGlMethodComparator();
			OpenGlConstantComparator openGlConstantComparator = new OpenGlConstantComparator();
			MethodComparator methodComparator = new MethodComparator();
			BooleanExpressionComparator booleanExpressionComparator = new BooleanExpressionComparator();
			NewStatementComparator newStatementComparator = new NewStatementComparator();
			NameComparator nameComparator = new NameComparator();

			nameComparator.setComparators(expressionComparator);
			ternaryOperatorComparator.setComparators(expressionComparator, booleanExpressionComparator);
			openGlMethodComparator.setComparators(expressionComparator);
			methodComparator.setComparators(nameComparator, expressionComparator, newStatementComparator);
			booleanExpressionComparator.setComparators(nameComparator, methodComparator);
			newStatementComparator.setComparators(nameComparator, expressionComparator);
			expressionComparator.setComparators(nameComparator, ternaryOperatorComparator, methodComparator,
					openGlMethodComparator, openGlConstantComparator);
		}
	}
}
