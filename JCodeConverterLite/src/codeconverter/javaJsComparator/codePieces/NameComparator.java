package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePieceComparator;

public class NameComparator extends CodePieceComparator {

	private ExpressionComparator expressionComparator;

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		initializeComparators();
		if (javaPieces.get(0).toString().equals(jsPieces.get(0).toString())) {
			if (javaPieces.get(2).getPieceType() == jsPieces.get(1).getPieceType()) {
				if (javaPieces.get(2).getPieceType() == PieceType.IGNORED) {
					return true;
				} else {
					return expressionComparator.compare(javaPieces.get(2).getPieces().get(1), jsPieces.get(1)
							.getPieces().get(1));
				}
			}
		}
		return false;
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
			
			ternaryOperatorComparator.setComparators(expressionComparator, booleanExpressionComparator);
			openGlMethodComparator.setComparators(this, expressionComparator);
			methodComparator.setComparators(this, expressionComparator, newStatementComparator);
			booleanExpressionComparator.setComparators(this, methodComparator);
			newStatementComparator.setComparators(this, expressionComparator);
			expressionComparator.setComparators(this, ternaryOperatorComparator, methodComparator,
					openGlMethodComparator, openGlConstantComparator);
		}
	}

	public void setComparators(ExpressionComparator expressionComparator) {
		this.expressionComparator = expressionComparator;
	}

}
