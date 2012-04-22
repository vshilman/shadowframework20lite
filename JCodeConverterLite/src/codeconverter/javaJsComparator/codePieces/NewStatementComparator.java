package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePieceComparator;

public class NewStatementComparator extends CodePieceComparator {

	private NameComparator nameComparator;
	private ExpressionComparator expressionComparator;

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		initializeComparators();
		if (!nameComparator.compare(javaPieces.get(1), jsPieces.get(1))) {
			return false;
		}
		if (javaPieces.get(2).getPieceType() != jsPieces.get(2).getPieceType()) {
			if(jsPieces.get(1).toString().trim().equals("Array")){
				return true;
			}
			return false;
		}
		if (javaPieces.get(2).getPieceType() != PieceType.IGNORED) {
			List<ICodePiece> javaCompList = javaPieces.get(2).getPieces().get(1).getPieces();
			List<ICodePiece> jsCompList = jsPieces.get(2).getPieces().get(1).getPieces();
			if (javaCompList.size() != jsCompList.size()) {
				return false;
			}
			for (int i = 0; i < javaCompList.size(); i++) {
				if (javaCompList.get(i).getPieceType() != jsCompList.get(i).getPieceType()) {
					return false;
				}
				if (javaCompList.get(i).getPieceType() != PieceType.IGNORED) {
					if (javaCompList.get(i).getPieceType() == PieceType.EXPRESSION) {
						if (!expressionComparator.compare(javaCompList.get(i), jsCompList.get(i))) {
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
			nameComparator = new NameComparator();
			expressionComparator = new ExpressionComparator();
			TernaryOperatorComparator ternaryOperatorComparator = new TernaryOperatorComparator();
			OpenGlMethodComparator openGlMethodComparator = new OpenGlMethodComparator();
			OpenGlConstantComparator openGlConstantComparator = new OpenGlConstantComparator();
			MethodComparator methodComparator = new MethodComparator();
			BooleanExpressionComparator booleanExpressionComparator = new BooleanExpressionComparator();
			openGlMethodComparator.setComparators(expressionComparator);
			methodComparator.setComparators(nameComparator, expressionComparator, this);
			booleanExpressionComparator.setComparators(nameComparator, methodComparator);
			ternaryOperatorComparator.setComparators(expressionComparator, booleanExpressionComparator);
			nameComparator.setComparators(expressionComparator);
			expressionComparator.setComparators(nameComparator, ternaryOperatorComparator, methodComparator,
					openGlMethodComparator, openGlConstantComparator);
		}
	}

	public void setComparators(NameComparator nameComparator, ExpressionComparator expressionComparator) {
		this.nameComparator = nameComparator;
		this.expressionComparator = expressionComparator;
	}

}
