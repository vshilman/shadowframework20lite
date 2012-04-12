package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.javaJsComparator.CodePieceComparator;

public class VariableComparator extends CodePieceComparator {

	private NameComparator nameComparator;

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		initializeComparators();
		if (!nameComparator.compare(javaPieces.get(1), jsPieces.get(1))) {
			return false;
		}
		return true;
	}

	private void initializeComparators() {
		if (nameComparator == null) {
			nameComparator = new NameComparator();
			MethodComparator methodComparator = new MethodComparator();
			ExpressionComparator expressionComparator = new ExpressionComparator();
			TernaryOperatorComparator ternaryOperatorComparator = new TernaryOperatorComparator();
			BooleanExpressionComparator booleanExpressionComparator = new BooleanExpressionComparator();
			OpenGlMethodComparator openGlMethodComparator = new OpenGlMethodComparator();
			NewStatementComparator newStatementComparator = new NewStatementComparator();
			newStatementComparator.setComparators(nameComparator, expressionComparator);
			booleanExpressionComparator.setComparators(nameComparator, methodComparator);
			ternaryOperatorComparator.setComparators(expressionComparator, booleanExpressionComparator);
			openGlMethodComparator.setComparators(nameComparator, expressionComparator);
			expressionComparator.setComparators(nameComparator, ternaryOperatorComparator, methodComparator,
					openGlMethodComparator, new OpenGlConstantComparator());
			nameComparator.setComparators(expressionComparator);
		}
	}

	public void setComparators(NameComparator nameComparator) {
		this.nameComparator = nameComparator;
	}

}
