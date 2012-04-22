package codeconverter.javaJsComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePieceComparator;

public class ExpressionComparator extends CodePieceComparator {

	private NameComparator nameComparator;
	private TernaryOperatorComparator ternaryOperatorComparator;
	private MethodComparator methodComparator;
	private OpenGlMethodComparator openGlMethodComparator;
	private OpenGlConstantComparator openGlConstantComparator;
	private NumberComparator numberComparator = new NumberComparator();

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> jsPieces) {
		initializeComparators();
		if (javaPieces.size() != jsPieces.size()) {
			return false;
		}
		for (int i = 0; i < javaPieces.size(); i++) {
			ICodePiece javaPiece = javaPieces.get(i);
			ICodePiece jsPiece = jsPieces.get(i);
			if (javaPiece.getPieceType() == PieceType.VALUE) {
				if (!numberComparator.compare(javaPiece, jsPiece)
						|| jsPiece.getPieceType() != PieceType.VALUE) {
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.OPERATOR) {
				if (!javaPiece.toString().equals(jsPiece.toString())
						|| jsPiece.getPieceType() != PieceType.OPERATOR) {
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.COMPOSITE) {
				if (jsPiece.getPieceType() == PieceType.COMPOSITE) {
					if (javaPiece.getPieces().get(1).getPieceType() == PieceType.COMPOSITE) {
						if (!compare(javaPiece.getPieces().get(1).getPieces().get(1), jsPiece.getPieces()
								.get(1))) {
							return false;
						}
					} else {
						return false;
					}
				} else if (jsPiece.getPieceType() == PieceType.VARIABLE) {
					if (!nameComparator.compare(javaPiece.getPieces().get(1), jsPiece)) {
						return false;
					}
				} else if (jsPiece.getPieceType() == PieceType.NAME) {
					if (!nameComparator.compare(javaPiece.getPieces().get(1), jsPiece)) {
						return false;
					}
				} else {
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.TERNARY_OPERATOR) {
				if (!ternaryOperatorComparator.compare(javaPiece, jsPiece)) {
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.OPENGL_CONSTANT) {
				if (!openGlConstantComparator.compare(javaPiece, jsPiece)) {
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.CALL) {
				if (!methodComparator.compare(javaPiece, jsPiece)) {
					boolean cont = false;

					ICodePiece call = javaPiece.getPieces().get(2).getPieceByType(PieceType.COMPOSITE);
					if (call.getPieceByType(PieceType.COMPOSITE).getPieceByType(PieceType.NAME).toString()
							.equals("BufferUtil.newFloatBuffer")
							&& jsPiece.getPieceType() == PieceType.NEW_STATEMENT) {
						if (jsPiece.getPieceByType(PieceType.VARIABLE).getPieceByType(PieceType.NAME)
								.toString().equals("Float32Array")) {
							if (call.getPieceByType(PieceType.SEQUENCE)
									.toString()
									.trim()
									.equals(jsPiece.getPieceByType(PieceType.COMPOSITE)
											.getPieceByType(PieceType.SEQUENCE).toString().trim())) {
								cont = true;
							}
						}
					}
					if (!cont) {
						return false;
					}
				}
			}
			if (javaPiece.getPieceType() == PieceType.OPENGL_CALL) {
				if (!openGlMethodComparator.compare(javaPiece, jsPiece)) {
					return false;
				}
			}
		}
		return true;
	}

	private void initializeComparators() {
		if (nameComparator == null) {
			nameComparator = new NameComparator();
			methodComparator = new MethodComparator();
			ternaryOperatorComparator = new TernaryOperatorComparator();
			openGlMethodComparator = new OpenGlMethodComparator();
			openGlConstantComparator = new OpenGlConstantComparator();
			BooleanExpressionComparator booleanExpressionComparator = new BooleanExpressionComparator();
			NewStatementComparator newStatementComparator = new NewStatementComparator();
			booleanExpressionComparator.setComparators(nameComparator, methodComparator);
			newStatementComparator.setComparators(nameComparator, this);
			ternaryOperatorComparator.setComparators(this, booleanExpressionComparator);
			openGlMethodComparator.setComparators(this);
			nameComparator.setComparators(this);
			methodComparator.setComparators(nameComparator, this, newStatementComparator);
		}
	}

	public void setComparators(NameComparator nameComparator,
			TernaryOperatorComparator ternaryOperatorComparator, MethodComparator methodComparator,
			OpenGlMethodComparator openGlMethodComparator, OpenGlConstantComparator openGlConstantComparator) {
		this.nameComparator = nameComparator;
		this.ternaryOperatorComparator = ternaryOperatorComparator;
		this.methodComparator = methodComparator;
		this.openGlMethodComparator = openGlMethodComparator;
		this.openGlConstantComparator = openGlConstantComparator;
	}

}
