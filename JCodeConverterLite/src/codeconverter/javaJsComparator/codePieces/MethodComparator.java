package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePieceComparator;

public class MethodComparator extends CodePieceComparator {

	private NameComparator nameComparator;
	private ExpressionComparator expressionComparator;
	private NewStatementComparator newStatementComparator;

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		initializeComparators();
		if (javaPieces.get(1).getPieceType() != jsPieces.get(0).getPieceType()) {
			return false;
		}
		if (javaPieces.get(1).getPieceType() == PieceType.COMPOSITE) {
			List<ICodePiece> javaCompList = javaPieces.get(1).getPieces();
			List<ICodePiece> jsCompList = jsPieces.get(0).getPieces();
			if (javaCompList.get(0).getPieceType() == PieceType.VALUE) {
				if (!nameComparator.compare(javaCompList.get(0), jsCompList.get(0))) {
					return false;
				}
			}
			if (javaCompList.get(0).getPieceType() == PieceType.NEW_STATEMENT) {
				if (!newStatementComparator.compare(javaCompList.get(0), jsCompList.get(0))) {
					return false;
				}
			}
		}
		List<ICodePiece> javaCompList = javaPieces.get(2).getPieces();
		List<ICodePiece> jsCompList = jsPieces.get(1).getPieces();
		if (javaCompList.size() != jsCompList.size()) {
			return false;
		}
		for (int i = 0; i < javaCompList.size(); i++) {
			if (javaCompList.get(i).getPieceType() == PieceType.COMPOSITE) {
				List<ICodePiece> javaCompList2 = javaCompList.get(i).getPieces();
				List<ICodePiece> jsCompList2 = jsCompList.get(i).getPieces();
				if (!nameComparator.compare(javaCompList2.get(0), jsCompList2.get(0))) {
					return false;
				}
				List<ICodePiece> javaCompList3 = javaCompList2.get(2).getPieces();
				List<ICodePiece> jsCompList3 = jsCompList2.get(2).getPieces();
				if (javaCompList3.size() != jsCompList3.size()) {
					return false;
				}
				for (int j = 0; j < javaCompList3.size(); j++) {
					if (javaCompList3.get(j).getPieceType() == PieceType.EXPRESSION) {
						if (!expressionComparator.compare(javaCompList3.get(j), jsCompList3.get(j))) {
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
			expressionComparator = new ExpressionComparator();
			nameComparator = new NameComparator();
			newStatementComparator = new NewStatementComparator();
			TernaryOperatorComparator ternaryOperatorComparator = new TernaryOperatorComparator();
			OpenGlMethodComparator openGlMethodComparator = new OpenGlMethodComparator();
			OpenGlConstantComparator openGlConstantComparator = new OpenGlConstantComparator();
			BooleanExpressionComparator booleanExpressionComparator = new BooleanExpressionComparator();
			booleanExpressionComparator.setComparators(nameComparator, this);
			ternaryOperatorComparator.setComparators(expressionComparator, booleanExpressionComparator);
			openGlMethodComparator.setComparators(nameComparator, expressionComparator);
			newStatementComparator.setComparators(nameComparator, expressionComparator);
			nameComparator.setComparators(expressionComparator);
			expressionComparator.setComparators(nameComparator, ternaryOperatorComparator, this,
					openGlMethodComparator, openGlConstantComparator);
		}
	}

	public void setComparators(NameComparator nameComparator, ExpressionComparator expressionComparator,
			NewStatementComparator newStatementComparator) {
		this.nameComparator = nameComparator;
		this.expressionComparator = expressionComparator;
		this.newStatementComparator = newStatementComparator;
	}

}
