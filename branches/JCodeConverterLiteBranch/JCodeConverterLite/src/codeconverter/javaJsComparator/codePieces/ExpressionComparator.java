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

		List<ICodePiece> javaPieces2 = javaPieces;

		if (javaPieces.size() != jsPieces.size()) {

			if (javaPieces.get(0).getPieceType() == PieceType.COMPOSITE) {
				if (javaPieces.get(0).getPieces().get(1) == null) {
					return false;
				}
				if (javaPieces.get(0).getPieces().get(1).getPieceByType(PieceType.EXPRESSION) == null) {
					return false;
				}
				javaPieces2 = javaPieces.get(0).getPieces().get(1).getPieceByType(PieceType.EXPRESSION)
						.getPieces();
			}

			if (javaPieces2.size() != jsPieces.size()) {
				return false;
			}
		}
		for (int i = 0; i < javaPieces2.size(); i++) {
			ICodePiece javaPiece = javaPieces2.get(i);
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

					cont = compareBufferString(jsPiece, cont, "BufferUtil.newFloatBuffer", "Float32Array",
							call);

					if (!cont) {
						cont = compareBufferString(jsPiece, cont, "BufferUtil.newShortBuffer", "Uint16Array",
								call);
						cont = compareEvent(jsPiece, cont, call, "e.getX", "e.clientX");
						cont = compareEvent(jsPiece, cont, call, "e.getY", "e.clientY");
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

	private boolean compareEvent(ICodePiece jsPiece, boolean cont, ICodePiece call, String javaEvent,
			String jsEvent) {
		if (!cont) {
			if (call.getPieceByType(PieceType.COMPOSITE).getPieceByType(PieceType.NAME)
					.toString().equals(javaEvent)
					&& jsPiece.getPieceType() == PieceType.VARIABLE) {

				if (jsPiece.getPieceByType(PieceType.NAME).toString().equals(jsEvent)) {
					cont = true;
				}
			}
		}
		return cont;
	}

	private boolean compareBufferString(ICodePiece jsPiece, boolean cont, String javaNewBufferString,
			String jsNewBufferString, ICodePiece call) {
		if (call.getPieceByType(PieceType.COMPOSITE).getPieceByType(PieceType.NAME).toString()
				.equals(javaNewBufferString)
				&& jsPiece.getPieceType() == PieceType.NEW_STATEMENT) {

			if (jsPiece.getPieceByType(PieceType.VARIABLE).getPieceByType(PieceType.NAME).toString()
					.equals(jsNewBufferString)) {
				if (call.getPieceByType(PieceType.SEQUENCE)
						.toString()
						.trim()
						.equals(jsPiece.getPieceByType(PieceType.COMPOSITE)
								.getPieceByType(PieceType.SEQUENCE).toString().trim())) {
					cont = true;
				}
			}
		}
		return cont;
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
