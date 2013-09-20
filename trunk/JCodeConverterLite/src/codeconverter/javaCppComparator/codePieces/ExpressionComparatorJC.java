package codeconverter.javaCppComparator.codePieces;

import java.util.List;

import codeconverter.ICodePiece;
import codeconverter.PieceType;
import codeconverter.comparator.CodePieceComparator;

public class ExpressionComparatorJC extends CodePieceComparator{

	private NameComparatorJC nameComparator;
	private TernaryOperatorComparatorJC ternaryOperatorComparator;
	private MethodComparatorJC methodComparator;
	private NumberComparatorJC numberComparator = new NumberComparatorJC();

	@Override
	protected boolean internalCompare(List<ICodePiece> javaPieces, List<ICodePiece> cppPieces) {
		initializeComparators();
		List<ICodePiece> javaPieces2 = javaPieces;

		if (javaPieces.size() != (cppPieces.size())) {

			if (javaPieces.get(0).getPieceType() == PieceType.COMPOSITE) {
				if (javaPieces.get(0).getPieces().get(1) == null) {
					System.out.println("1");
					return false;
				}
				if (javaPieces.get(0).getPieces().get(1).getPieceByType(PieceType.EXPRESSION) == null) {
					System.out.println("2");
					return false;
				}
				javaPieces2 = javaPieces.get(0).getPieces().get(1).getPieceByType(PieceType.EXPRESSION)
						.getPieces();
			}

			if (javaPieces2.size() != (cppPieces.size())) {
				System.out.println("3");
				return false;
			}
		}
		for (int i = 0; i < javaPieces2.size(); i++) {
			ICodePiece javaPiece = javaPieces2.get(i);
			ICodePiece cppPiece = cppPieces.get(i);
			if (javaPiece.getPieceType() == PieceType.VALUE) {
				if (!numberComparator.compare(javaPiece, cppPiece)
						|| cppPiece.getPieceType() != PieceType.VALUE) {
					System.out.println("4");
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.OPERATOR) {
				if (!javaPiece.toString().equals(cppPiece.toString())
						|| cppPiece.getPieceType() != PieceType.OPERATOR) {
					System.out.println("5");
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.COMPOSITE) {
				if (cppPiece.getPieceType() == PieceType.COMPOSITE) {
					if (javaPiece.getPieces().get(1).getPieceType() == PieceType.COMPOSITE && cppPiece.getPieces().get(1).getPieceType() == PieceType.COMPOSITE) {
						if (!compare(javaPiece.getPieces().get(1).getPieces().get(1), cppPiece.getPieces()
								.get(1).getPieces().get(1))) {
							System.out.println("6");
							return false;
						}
					} else {
						if(javaPiece.getPieces().get(1).getPieceType()==PieceType.VARIABLE && cppPiece.getPieces().get(1).getPieceType()==PieceType.VARIABLE){
							if(!nameComparator.compare(javaPiece.getPieces().get(1), cppPiece.getPieces().get(1))){
								System.out.println("6a");
								return false;
							}
						} else {
						System.out.println(javaPiece.getPieces().get(1).getPieceType()+" e che ci devo fare");
						System.out.println("6b");
						return false;
					}
				}
				} else if (cppPiece.getPieceType() == PieceType.VARIABLE) {
					if (!nameComparator.compare(javaPiece.getPieces().get(1), cppPiece)) {
						System.out.println("7");
						return false;
					}
				} else if (cppPiece.getPieceType() == PieceType.NAME) {
					if (!nameComparator.compare(javaPiece.getPieces().get(1), cppPiece)) {
						System.out.println("8");
						return false;
					}
				} else {
					System.out.println("9");
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.TERNARY_OPERATOR) {
				if (!ternaryOperatorComparator.compare(javaPiece, cppPiece)) {
					System.out.println("10");
					return false;
				}
			}
			if (javaPiece.getPieceType() == PieceType.CALL) {
				if (!methodComparator.compare(javaPiece, cppPiece)) {
					System.out.println("11");
					return false;
				}
			}
		}
		return true;

	}



	private void initializeComparators() {
		if (nameComparator == null) {
			nameComparator = new NameComparatorJC();
			methodComparator = new MethodComparatorJC();
			ternaryOperatorComparator = new TernaryOperatorComparatorJC();
			BooleanExpressionComparatorJC booleanExpressionComparator = new BooleanExpressionComparatorJC();
			NewStatementComparatorJC newStatementComparator = new NewStatementComparatorJC();
			booleanExpressionComparator.setComparators(nameComparator, methodComparator);
			newStatementComparator.setComparators(nameComparator, this);
			ternaryOperatorComparator.setComparators(this, booleanExpressionComparator);
			nameComparator.setComparators(this);
			methodComparator.setComparators(nameComparator, this, newStatementComparator);
		}
	}

	public void setComparators(NameComparatorJC nameComparator,
			TernaryOperatorComparatorJC ternaryOperatorComparator, MethodComparatorJC methodComparator) {
		this.nameComparator = nameComparator;
		this.ternaryOperatorComparator = ternaryOperatorComparator;
		this.methodComparator = methodComparator;
	}



}
