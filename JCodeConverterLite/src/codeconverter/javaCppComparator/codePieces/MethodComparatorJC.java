package codeconverter.javaCppComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.comparator.CodePieceComparator;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlConstantComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
import codeconverter.javaJsComparator.codePieces.TernaryOperatorComparator;

public class MethodComparatorJC extends CodePieceComparator {

	private NameComparatorJC nameComparator;
	private ExpressionComparatorJC expressionComparator;
	private NewStatementComparatorJC newStatementComparator;

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> cppPieces) {
		initializeComparators();
		if (javaPieces.get(1).getPieceType() != cppPieces.get(1).getPieceType()) {
			return false;
		}
		if (javaPieces.get(1).getPieceType() == PieceType.COMPOSITE) {
			List<ICodePiece> javaCompList = javaPieces.get(1).getPieces();
			List<ICodePiece> cppCompList = cppPieces.get(1).getPieces();
			if (javaCompList.get(0).getPieceType() == PieceType.VALUE) {
				if (!nameComparator.compare(javaCompList.get(0), cppCompList.get(0))) {
					return false;
				}
			}
			if (javaCompList.get(0).getPieceType() == PieceType.NEW_STATEMENT) {
				if (!newStatementComparator.compare(javaCompList.get(0), cppCompList.get(0))) {
					return false;
				}
			}
		}
		List<ICodePiece> javaCompList = javaPieces.get(2).getPieces();
		List<ICodePiece> cppCompList = cppPieces.get(2).getPieces();
		if (javaCompList.size() != cppCompList.size()) {
			return false;
		}
		for (int i = 0; i < javaCompList.size(); i++) {
			if (javaCompList.get(i).getPieceType() == PieceType.COMPOSITE) {
				List<ICodePiece> javaCompList2 = javaCompList.get(i).getPieces();
				List<ICodePiece> cppCompList2 = cppCompList.get(i).getPieces();
				if (!nameComparator.compare(javaCompList2.get(0), cppCompList2.get(0))) {
					return false;
				}
				List<ICodePiece> javaCompList3 = javaCompList2.get(2).getPieces();
				List<ICodePiece> cppCompList3 = cppCompList2.get(2).getPieces();
				if (javaCompList3.size() != cppCompList3.size()) {
					return false;
				}
				for (int j = 0; j < javaCompList3.size(); j++) {
					if (javaCompList3.get(j).getPieceType() == PieceType.EXPRESSION) {
						if (!expressionComparator.compare(javaCompList3.get(j), cppCompList3.get(j))) {
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
			expressionComparator = new ExpressionComparatorJC();
			nameComparator = new NameComparatorJC();
			newStatementComparator = new NewStatementComparatorJC();
			TernaryOperatorComparatorJC ternaryOperatorComparator = new TernaryOperatorComparatorJC();
			BooleanExpressionComparatorJC booleanExpressionComparator = new BooleanExpressionComparatorJC();
			booleanExpressionComparator.setComparators(nameComparator, this);
			ternaryOperatorComparator.setComparators(expressionComparator, booleanExpressionComparator);
			newStatementComparator.setComparators(nameComparator, expressionComparator);
			nameComparator.setComparators(expressionComparator);
			expressionComparator.setComparators(nameComparator, ternaryOperatorComparator, this );
		}
	}

	public void setComparators(NameComparatorJC nameComparator, ExpressionComparatorJC expressionComparator,
			NewStatementComparatorJC newStatementComparator) {
		this.nameComparator = nameComparator;
		this.expressionComparator = expressionComparator;
		this.newStatementComparator = newStatementComparator;
	}


}
