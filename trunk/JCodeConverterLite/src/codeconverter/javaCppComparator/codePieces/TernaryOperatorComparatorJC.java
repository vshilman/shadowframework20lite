package codeconverter.javaCppComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.comparator.CodePieceComparator;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlConstantComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;

public class TernaryOperatorComparatorJC extends CodePieceComparator {

	private ExpressionComparatorJC expressionComparator;
	private BooleanExpressionComparatorJC booleanExpressionComparator;

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> cppPieces) {
		initializeComparators();
		if (!booleanExpressionComparator.compare(javaPieces.get(0), cppPieces.get(0))) {
			return false;
		}
		if (!expressionComparator.compare(javaPieces.get(2), cppPieces.get(2))) {
			return false;
		}
		if (!expressionComparator.compare(javaPieces.get(4), cppPieces.get(4))) {
			return false;
		}
		return true;
	}

	private void initializeComparators() {
		expressionComparator = new ExpressionComparatorJC();
		booleanExpressionComparator=new BooleanExpressionComparatorJC();
		NameComparatorJC nameComparator = new NameComparatorJC();
		MethodComparatorJC methodComparator = new MethodComparatorJC();
		NewStatementComparatorJC newStatementComparator = new NewStatementComparatorJC();

		booleanExpressionComparator.setComparators(nameComparator, methodComparator);
		nameComparator.setComparators(expressionComparator);
		methodComparator.setComparators(nameComparator, expressionComparator, newStatementComparator);
		newStatementComparator.setComparators(nameComparator, expressionComparator);
		expressionComparator.setComparators(nameComparator, this, methodComparator);
	}

	public void setComparators(ExpressionComparatorJC expressionComparator,
			BooleanExpressionComparatorJC booleanExpressionComparator) {
		this.expressionComparator = expressionComparator;
		this.booleanExpressionComparator = booleanExpressionComparator;
	}


}
