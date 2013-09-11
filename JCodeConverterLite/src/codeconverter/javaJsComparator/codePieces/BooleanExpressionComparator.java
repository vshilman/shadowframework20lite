package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.comparator.CodePieceComparator;

public class BooleanExpressionComparator extends CodePieceComparator {

	private NameComparator nameComparator;
	private MethodComparator methodComparator;
	private NumberComparator numberComparator = new NumberComparator();

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		initializeComparators();
		for (int i = 0; i < javaPieces.size(); i++) {
			ICodePiece javaPiece = javaPieces.get(i);
			ICodePiece jsPiece = jsPieces.get(i);
			if (javaPiece.getPieceType() == PieceType.OPERATOR) {
				if (!javaPiece.toString().equals(jsPiece.toString())
						|| jsPiece.getPieceType() != PieceType.OPERATOR) {
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.VARIABLE) {
				if (!nameComparator.compare(javaPiece, jsPiece)) {
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.VALUE) {
				if (!numberComparator.compare(javaPiece, jsPiece)
						|| jsPiece.getPieceType() != PieceType.VALUE) {
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.CALL) {
				if (!methodComparator.compare(javaPiece, jsPiece)) {
					boolean cont = false;

					ICodePiece call = javaPiece.getPieces().get(2).getPieceByType(PieceType.COMPOSITE);

					if (call.getPieceByType(PieceType.COMPOSITE).getPieceByType(PieceType.NAME).toString()
							.equals("e.getKeyCode")
							&& jsPiece.getPieceType() == PieceType.VARIABLE) {

						if (jsPiece.getPieceByType(PieceType.NAME).toString().equals("e.keyCode")) {
							cont = true;
						}
					}

					if (!cont) {
						return false;
					}
				}
			}
			if (javaPiece.getPieceType() == PieceType.COMPOSITE) {
				if (jsPiece.getPieceType() != PieceType.COMPOSITE) {
					return false;
				}
				if (javaPiece.getPieces().get(1).getPieceType() == PieceType.EXPRESSION) {
					if (!compare(javaPiece.getPieces().get(1), jsPiece.getPieces().get(1))) {
						return false;
					}
				} else if (javaPiece.getPieces().get(2).getPieceType() == PieceType.EXPRESSION) {
					if (!compare(javaPiece.getPieces().get(2), jsPiece.getPieces().get(2))) {
						return false;
					}
				} else if (javaPiece.getPieces().get(1).getPieceType() == PieceType.VARIABLE) {
					if (!nameComparator.compare(javaPiece.getPieces().get(1), jsPiece.getPieces().get(1))) {
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
			nameComparator = new NameComparator();
			methodComparator = new MethodComparator();
			ExpressionComparator expressionComparator = new ExpressionComparator();
			TernaryOperatorComparator ternaryOperatorComparator = new TernaryOperatorComparator();
			OpenGlMethodComparator openGlMethodComparator = new OpenGlMethodComparator();
			NewStatementComparator newStatementComparator = new NewStatementComparator();
			newStatementComparator.setComparators(nameComparator, expressionComparator);
			ternaryOperatorComparator.setComparators(expressionComparator, this);
			openGlMethodComparator.setComparators(expressionComparator);
			expressionComparator.setComparators(nameComparator, ternaryOperatorComparator, methodComparator,
					openGlMethodComparator, new OpenGlConstantComparator());
			nameComparator.setComparators(expressionComparator);
			methodComparator.setComparators(nameComparator, expressionComparator, newStatementComparator);
		}
	}

	public void setComparators(NameComparator nameComparator, MethodComparator methodComparator) {
		this.nameComparator = nameComparator;
		this.methodComparator = methodComparator;
	}

}
