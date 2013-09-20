package codeconverter.javaCppComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.comparator.CodePieceComparator;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlConstantComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
import codeconverter.javaJsComparator.codePieces.TernaryOperatorComparator;

public class NameComparatorJC extends CodePieceComparator {

	private ExpressionComparatorJC expressionComparator;

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> cppPieces) {
		initializeComparators();
		if (javaPieces.get(0).toString().equals(cppPieces.get(0).toString())) {
			//It needs template classes comparison
			if (javaPieces.get(2).getPieceType() == cppPieces.get(2).getPieceType()) {
				if (javaPieces.get(2).getPieceType() == PieceType.IGNORED) {
					return true;
				} else {
					return expressionComparator.compare(javaPieces.get(2).getPieces().get(1), cppPieces.get(2)
							.getPieces().get(1));
				}
			}
		}
		if (javaPieces.get(0).toString().equals("String")) {
			if (cppPieces.get(0).toString().equals("string")) {
				return true;
			}
		}
		if (javaPieces.get(0).toString().equals("boolean")) {
			if (cppPieces.get(0).toString().equals("bool")) {
				return true;
			}
		}
		if (javaPieces.get(0).toString().startsWith("Util.")
				&& cppPieces.get(0).toString().equals(javaPieces.get(0).toString().substring(5))) {
			return true;
		}
		if (javaPieces.get(0).toString().startsWith("this.")) {
			if (javaPieces.get(0).toString().substring(5).equals(cppPieces.get(0).toString())) {
				return true;
			}
		}
		if (cppPieces.get(0).toString().startsWith("this.")) {
			if (javaPieces.get(0).toString().equals(cppPieces.get(0).toString().substring(5))) {
				return true;
			}
		}
		if (cppPieces.get(0).toString().startsWith("this->")) {
			if (javaPieces.get(0).toString().equals(cppPieces.get(0).toString().substring(6))) {
				return true;
			}
		}
		return false;
	}

	private void initializeComparators() {
		if (expressionComparator == null) {
			expressionComparator = new ExpressionComparatorJC();
			TernaryOperatorComparatorJC ternaryOperatorComparator = new TernaryOperatorComparatorJC();
			MethodComparatorJC methodComparator = new MethodComparatorJC();
			BooleanExpressionComparatorJC booleanExpressionComparator = new BooleanExpressionComparatorJC();
			NewStatementComparatorJC newStatementComparator = new NewStatementComparatorJC();

			ternaryOperatorComparator.setComparators(expressionComparator, booleanExpressionComparator);
			methodComparator.setComparators(this, expressionComparator, newStatementComparator);
			booleanExpressionComparator.setComparators(this, methodComparator);
			newStatementComparator.setComparators(this, expressionComparator);
			expressionComparator.setComparators(this, ternaryOperatorComparator, methodComparator);
		}
	}

	public void setComparators(ExpressionComparatorJC expressionComparator) {
		this.expressionComparator = expressionComparator;
	}


}
