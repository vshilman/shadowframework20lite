package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePieceComparator;

public class OpenGlMethodComparator extends CodePieceComparator {

	private NameComparator nameComparator;
	private ExpressionComparator expressionComparator;

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		initializeComparators();
		String name = jsPieces.get(1).toString();
		name = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
		if (!javaPieces.get(3).toString().equals(name)) {
			return false;
		}
		List<ICodePiece> javaCompList = javaPieces.get(5).getPieces();
		List<ICodePiece> jsCompList = jsPieces.get(3).getPieces();
		if (javaCompList.size() != jsCompList.size()) {
			return false;
		}
		for (int j = 0; j < javaCompList.size(); j++) {
			if (javaCompList.get(j).getPieceType() == PieceType.EXPRESSION) {
				if (!expressionComparator.compare(javaCompList.get(j), jsCompList.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	private void initializeComparators() {
		if (nameComparator == null) {
			expressionComparator = new ExpressionComparator();
			nameComparator = new NameComparator();
			NewStatementComparator newStatementComparator = new NewStatementComparator();
			TernaryOperatorComparator ternaryOperatorComparator = new TernaryOperatorComparator();
			MethodComparator methodComparator = new MethodComparator();
			OpenGlConstantComparator openGlConstantComparator = new OpenGlConstantComparator();
			BooleanExpressionComparator booleanExpressionComparator = new BooleanExpressionComparator();
			booleanExpressionComparator.setComparators(nameComparator, methodComparator);
			ternaryOperatorComparator.setComparators(expressionComparator, booleanExpressionComparator);
			newStatementComparator.setComparators(nameComparator, expressionComparator);
			methodComparator.setComparators(nameComparator, expressionComparator, newStatementComparator);
			nameComparator.setComparators(expressionComparator);
			expressionComparator.setComparators(nameComparator, ternaryOperatorComparator, methodComparator,
					this, openGlConstantComparator);
		}
	}

	public void setComparators(NameComparator nameComparator, ExpressionComparator expressionComparator) {
		this.nameComparator = nameComparator;
		this.expressionComparator = expressionComparator;
	}

}
