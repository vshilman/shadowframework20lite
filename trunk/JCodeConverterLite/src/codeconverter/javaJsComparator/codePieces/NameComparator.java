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
		if (javaPieces.get(0).toString().equals("int") || javaPieces.get(0).toString().equals("float")
				|| javaPieces.get(0).toString().equals("short")) {
			if (jsPieces.get(0).toString().equals("Array")) {
				return true;
			}
		}
		if (javaPieces.get(0).toString().startsWith("Util.")
				&& jsPieces.get(0).toString().equals(javaPieces.get(0).toString().substring(5))) {
			return true;
		}
		if (javaPieces.get(0).toString().startsWith("this.")) {
			if (javaPieces.get(0).toString().substring(5).equals(jsPieces.get(0).toString())) {
				return true;
			}
		}
		if (jsPieces.get(0).toString().startsWith("this.")) {
			if (javaPieces.get(0).toString().equals(jsPieces.get(0).toString().substring(5))) {
				return true;
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
			openGlMethodComparator.setComparators(expressionComparator);
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
