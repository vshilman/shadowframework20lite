package codeconverter.javaCppComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.comparator.CodePieceComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.NumberComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlConstantComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
import codeconverter.javaJsComparator.codePieces.TernaryOperatorComparator;

public class BooleanExpressionComparatorJC extends CodePieceComparator{

	private NameComparatorJC nameComparator;
	private MethodComparatorJC methodComparator;
	private NumberComparatorJC numberComparator = new NumberComparatorJC();

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces,
			List<ICodePiece> cppPieces) {

		initializeComparators();

		for (int i = 0; i < javaPieces.size(); i++) {
			ICodePiece javaPiece = javaPieces.get(i);
			ICodePiece cppPiece = cppPieces.get(i);
			if (javaPiece.getPieceType() == PieceType.OPERATOR) {
				if (!javaPiece.toString().equals(cppPiece.toString())
						|| cppPiece.getPieceType() != PieceType.OPERATOR) {
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.VARIABLE) {
				if (!nameComparator.compare(javaPiece, cppPiece)) {
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.VALUE) {
				if (!numberComparator.compare(javaPiece, cppPiece)
						|| cppPiece.getPieceType() != PieceType.VALUE) {
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.CALL) {
				if (!methodComparator.compare(javaPiece, cppPiece)) {
					boolean cont = false;

					ICodePiece call = javaPiece.getPieces().get(2).getPieceByType(PieceType.COMPOSITE);

					if (call.getPieceByType(PieceType.COMPOSITE).getPieceByType(PieceType.NAME).toString()
							.equals("e.getKeyCode")
							&& cppPiece.getPieceType() == PieceType.VARIABLE) {

						if (cppPiece.getPieceByType(PieceType.NAME).toString().equals("e->KeyCode")) {
							cont = true;
						}
					}

					if (!cont) {
						return false;
					}
				}
			}
			if (javaPiece.getPieceType() == PieceType.COMPOSITE) {
				if (cppPiece.getPieceType() != PieceType.COMPOSITE) {
					return false;
				}
				if (javaPiece.getPieces().get(1).getPieceType() == PieceType.EXPRESSION) {
					if (!compare(javaPiece.getPieces().get(1), cppPiece.getPieces().get(1))) {
						return false;
					}
				} else if (javaPiece.getPieces().get(2).getPieceType() == PieceType.EXPRESSION) {
					if (!compare(javaPiece.getPieces().get(2), cppPiece.getPieces().get(2))) {
						return false;
					}
				} else if (javaPiece.getPieces().get(1).getPieceType() == PieceType.VARIABLE) {
					if (!nameComparator.compare(javaPiece.getPieces().get(1), cppPiece.getPieces().get(1))) {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		return true;

	}

	private void initializeComparators() {
		if (nameComparator == null) {
			nameComparator = new NameComparatorJC();
			methodComparator = new MethodComparatorJC();
			ExpressionComparatorJC expressionComparator = new ExpressionComparatorJC();
			TernaryOperatorComparatorJC ternaryOperatorComparator = new TernaryOperatorComparatorJC();
			NewStatementComparatorJC newStatementComparator = new NewStatementComparatorJC();
			newStatementComparator.setComparators(nameComparator, expressionComparator);
			ternaryOperatorComparator.setComparators(expressionComparator, this);
			expressionComparator.setComparators(nameComparator, ternaryOperatorComparator, methodComparator);
			nameComparator.setComparators(expressionComparator);
			methodComparator.setComparators(nameComparator, expressionComparator, newStatementComparator);
		}
	}

	public void setComparators(NameComparatorJC nameComparator, MethodComparatorJC methodComparator) {
		this.nameComparator = nameComparator;
		this.methodComparator = methodComparator;
	}

}
