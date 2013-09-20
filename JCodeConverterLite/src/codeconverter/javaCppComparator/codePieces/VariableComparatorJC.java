package codeconverter.javaCppComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.comparator.CodePieceComparator;
import codeconverter.javaJsComparator.codePieces.BooleanExpressionComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlConstantComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
import codeconverter.javaJsComparator.codePieces.TernaryOperatorComparator;

public class VariableComparatorJC  extends CodePieceComparator {

	private NameComparatorJC nameComparator;

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> cppPieces) {
		initializeComparators();

		if(!new TypeComparatorJC().compare(javaPieces.get(0), cppPieces.get(0).getPieceByType(PieceType.TYPE))){
			return false;
		}

		if (!nameComparator.compare(javaPieces.get(1), cppPieces.get(1))) {
			return false;
		}
		return true;
	}

	private void initializeComparators() {
		if (nameComparator == null) {
			nameComparator = new NameComparatorJC();
			MethodComparatorJC methodComparator = new MethodComparatorJC();
			ExpressionComparatorJC expressionComparator = new ExpressionComparatorJC();
			TernaryOperatorComparatorJC ternaryOperatorComparator = new TernaryOperatorComparatorJC();
			BooleanExpressionComparatorJC booleanExpressionComparator = new BooleanExpressionComparatorJC();
			NewStatementComparatorJC newStatementComparator = new NewStatementComparatorJC();
			newStatementComparator.setComparators(nameComparator, expressionComparator);
			booleanExpressionComparator.setComparators(nameComparator, methodComparator);
			ternaryOperatorComparator.setComparators(expressionComparator, booleanExpressionComparator);
			expressionComparator.setComparators(nameComparator, ternaryOperatorComparator, methodComparator);
			nameComparator.setComparators(expressionComparator);
		}
	}

	public void setComparators(NameComparatorJC nameComparator) {
		this.nameComparator = nameComparator;
	}


}
