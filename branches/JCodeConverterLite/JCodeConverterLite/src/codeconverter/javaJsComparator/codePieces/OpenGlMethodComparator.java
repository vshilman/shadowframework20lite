package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePieceComparator;

public class OpenGlMethodComparator extends CodePieceComparator {

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
			List<ICodePiece> minList = javaCompList.size() < jsCompList.size() ? javaCompList : jsCompList;
			List<ICodePiece> maxList = javaCompList.size() > jsCompList.size() ? javaCompList : jsCompList;
			int dist = javaCompList.size() - jsCompList.size();

			for (int i = 0; i < minList.size(); i++) {
				boolean found = false;
				for (int j = 0; j < maxList.size(); j++) {
					if (dist > 0) {
						if (expressionComparator.compare(javaCompList.get(j), jsCompList.get(i))) {
							found = true;
						}
					} else {
						if (expressionComparator.compare(javaCompList.get(i), jsCompList.get(j))) {
							found = true;
						}
					}
				}
				if (!found) {
					if (name.equals("TexImage2D") && i == 10) {
						if (!javaCompList.get(16).toString().replaceAll(" ", "")
								.startsWith(jsCompList.get(10).toString().trim())) {
							return false;
						}
					} else {
						return false;
					}
				}
			}

			return true;
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
		if (expressionComparator == null) {
			expressionComparator = new ExpressionComparator();
			NameComparator nameComparator = new NameComparator();
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

	public void setComparators(ExpressionComparator expressionComparator) {
		this.expressionComparator = expressionComparator;
	}

}
