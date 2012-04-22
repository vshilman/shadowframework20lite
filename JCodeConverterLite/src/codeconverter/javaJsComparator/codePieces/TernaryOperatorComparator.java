package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.javaJsComparator.CodePieceComparator;

public class TernaryOperatorComparator extends CodePieceComparator {

	private ExpressionComparator expressionComparator;
	private BooleanExpressionComparator booleanExpressionComparator;

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		initializeComparators();
		if (!booleanExpressionComparator.compare(javaPieces.get(0), jsPieces.get(0))) {
			return false;
		}
		if (!expressionComparator.compare(javaPieces.get(2), jsPieces.get(2))) {
			return false;
		}
		if (!expressionComparator.compare(javaPieces.get(4), jsPieces.get(4))) {
			return false;
		}
		return true;
	}

	private void initializeComparators() {
		expressionComparator = new ExpressionComparator();
		booleanExpressionComparator=new BooleanExpressionComparator();
		NameComparator nameComparator = new NameComparator();
		OpenGlMethodComparator openGlMethodComparator = new OpenGlMethodComparator();
		OpenGlConstantComparator openGlConstantComparator = new OpenGlConstantComparator();
		MethodComparator methodComparator = new MethodComparator();
		NewStatementComparator newStatementComparator = new NewStatementComparator();
		
		booleanExpressionComparator.setComparators(nameComparator, methodComparator);
		nameComparator.setComparators(expressionComparator);
		openGlMethodComparator.setComparators(expressionComparator);
		methodComparator.setComparators(nameComparator, expressionComparator, newStatementComparator);
		newStatementComparator.setComparators(nameComparator, expressionComparator);
		expressionComparator.setComparators(nameComparator, this, methodComparator, openGlMethodComparator, openGlConstantComparator);
	}

	public void setComparators(ExpressionComparator expressionComparator,
			BooleanExpressionComparator booleanExpressionComparator) {
		this.expressionComparator = expressionComparator;
		this.booleanExpressionComparator = booleanExpressionComparator;
	}

}
