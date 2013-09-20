package codeconverter.javaCppComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.comparator.CodePieceComparator;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlConstantComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
import codeconverter.javaJsComparator.codePieces.TernaryOperatorComparator;

public class NewStatementComparatorJC extends CodePieceComparator {

	private NameComparatorJC nameComparator;
	private ExpressionComparatorJC expressionComparator;

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> cppPieces) {
		initializeComparators();
		if (!nameComparator.compare(javaPieces.get(1), cppPieces.get(1))) {
			return false;
		}
		if (javaPieces.get(2).getPieceType() != cppPieces.get(2).getPieceType()) {
			return false;
		}
		if (javaPieces.get(2).getPieceType() != PieceType.IGNORED) {
			List<ICodePiece> javaCompList = javaPieces.get(2).getPieces().get(1).getPieces();
			List<ICodePiece> cppCompList = cppPieces.get(2).getPieces().get(1).getPieces();
			if (javaCompList.size() != cppCompList.size()) {
				return false;
			}
			for (int i = 0; i < javaCompList.size(); i++) {
				if (javaCompList.get(i).getPieceType() != cppCompList.get(i).getPieceType()) {
					return false;
				}
				if (javaCompList.get(i).getPieceType() != PieceType.IGNORED) {
					if (javaCompList.get(i).getPieceType() == PieceType.EXPRESSION) {
						if (!expressionComparator.compare(javaCompList.get(i), cppCompList.get(i))) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void initializeComparators() {
		if (nameComparator == null) {
			nameComparator = new NameComparatorJC();
			expressionComparator = new ExpressionComparatorJC();
			TernaryOperatorComparatorJC ternaryOperatorComparator = new TernaryOperatorComparatorJC();
			MethodComparatorJC methodComparator = new MethodComparatorJC();
			BooleanExpressionComparatorJC booleanExpressionComparator = new BooleanExpressionComparatorJC();
			methodComparator.setComparators(nameComparator, expressionComparator, this);
			booleanExpressionComparator.setComparators(nameComparator, methodComparator);
			ternaryOperatorComparator.setComparators(expressionComparator, booleanExpressionComparator);
			nameComparator.setComparators(expressionComparator);
			expressionComparator.setComparators(nameComparator, ternaryOperatorComparator, methodComparator);
		}
	}

	public void setComparators(NameComparatorJC nameComparator, ExpressionComparatorJC expressionComparator) {
		this.nameComparator = nameComparator;
		this.expressionComparator = expressionComparator;
	}

}
