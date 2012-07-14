package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePieceComparator;

public class ArrayContentComparator extends CodePieceComparator {

	private ExpressionComparator expressionComparator;
	
	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		initializeComparators();
		
		List<ICodePiece> javaPiecesComp = javaPieces.get(1).getPieces();
		List<ICodePiece> jsPiecesComp = jsPieces.get(1).getPieces();
		
		for (int j = 0; j < javaPiecesComp.size(); j++) {
			if (javaPiecesComp.get(j).getPieceType() == PieceType.EXPRESSION) {
				if (!expressionComparator.compare(javaPiecesComp.get(j), jsPiecesComp.get(j))) {
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
