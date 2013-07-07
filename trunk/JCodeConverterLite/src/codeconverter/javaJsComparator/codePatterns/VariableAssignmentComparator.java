package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.ComparatorUtilities;
import codeconverter.javaJsComparator.codePieces.ArrayContentComparator;
import codeconverter.javaJsComparator.codePieces.ArrayDeclarationComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NameComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;

public class VariableAssignmentComparator implements CodePatternComparator {

	@Override
	public boolean compare(CodePattern javaPattern, CodePattern jsPattern) {
//	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex,
//			List<CodePattern> jsCodePatterns, int jsIndex) {
		if (javaPattern.getPatternType().get(0) != PatternType.VARIABLE_ASSIGNMENT) {
			return false;
		}
		if (javaPattern.getPatternType().get(0) != jsPattern
				.getPatternType().get(0)) {
			return false;
		}

		if (!new NameComparator().compare(javaPattern.getPieceByType(PieceType.NAME),
				jsPattern.getPieceByType(PieceType.NAME))) {
			return false;
		}

		if (javaPattern.getPieces().get(1).getPieceType() != jsPattern.getPieces().get(1).getPieceType()) {
			return false;
		}

		if (javaPattern.getPieces().get(1).getPieceType() == PieceType.KEYWORD) {
			if (!javaPattern.getPieces().get(1).toString().equals(jsPattern.getPieces().get(1).toString())) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(3).getPieces().get(0).getPieceType() == PieceType.CALL
				&& jsPattern.getPieces().get(3).getPieceType() == PieceType.NEW_STATEMENT) {

			if (javaPattern.getPieces().get(3).getPieceByType(PieceType.CALL)
					.getPieceByType(PieceType.SEQUENCE).getPieceByType(PieceType.COMPOSITE)
					.getPieceByType(PieceType.COMPOSITE).toString().trim().equals("Arrays.copyOf")
					&& jsPattern.getPieceByType(PieceType.NEW_STATEMENT).getPieceByType(PieceType.TYPE)
							.toString().trim().equals("Float32Array")) {

				if (new ExpressionComparator().compare(
						javaPattern.getPieces().get(3).getPieceByType(PieceType.CALL)
								.getPieceByType(PieceType.SEQUENCE).getPieceByType(PieceType.COMPOSITE)
								.getPieceByType(PieceType.SEQUENCE).getPieceByType(PieceType.EXPRESSION),
						jsPattern.getPieceByType(PieceType.NEW_STATEMENT).getPieceByType(PieceType.COMPOSITE)
								.getPieceByType(PieceType.SEQUENCE).getPieceByType(PieceType.EXPRESSION))) {
					return true;
				}
			}
		}
		
		/*
		 * compare this: float[] pMatrixv = new float [ ]
		 * {1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,4 } pMatrix =
		 * BufferUtil.newFloatBuffer (pMatrixv )
		 * 
		 * with this: pMatrix = new Float32Array ( [
		 * 1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,4 ] )
		 */

		if (javaPattern.getPieces().get(3).getPieces().get(0).getPieceType() == PieceType.CALL
				&& jsPattern.getPieces().get(3).getPieceType() == PieceType.ARRAY_DECLARATION) {
			//This is no more necessary, the problem was solved outside
//			ICodePiece call = javaPattern.getPieceByType(PieceType.CALL);
//			String vectorName = call.getPieceByType(PieceType.VARIABLE).getPieceByType(PieceType.NAME).toString();
//			int[][] result = ComparatorUtilities.findArrayDecalation(javaCodePatterns, javaIndex, jsIndex,
//					jsPattern, call, vectorName);
//			if (result != null) {
//				return false;
//			}
		}

		if (javaPattern.getPieces().get(3).getPieces().get(0).getPieceType() == PieceType.CALL
				&& jsPattern.getPieces().get(3).getPieceType() == PieceType.EXPRESSION) {
			ICodePiece call = javaPattern.getPieces().get(3).getPieces().get(0)
					.getPieceByType(PieceType.SEQUENCE).getPieceByType(PieceType.COMPOSITE);
			String vectorName = call.getPieceByType(PieceType.SEQUENCE).toString().trim();
			if (vectorName.equals(jsPattern.getPieces().get(3).toString().trim())) {
				return true;
			}
		}

		if (javaPattern.getPieces().get(3).getPieceType() != jsPattern.getPieces().get(3).getPieceType()) {
			return false;
		}

		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.EXPRESSION) {
			if (!new ExpressionComparator().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
					jsPattern.getPieceByType(PieceType.EXPRESSION))) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.CALL) {
			if (!new MethodComparator().compare(javaPattern.getPieceByType(PieceType.CALL),
					jsPattern.getPieceByType(PieceType.CALL))) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.OPENGL_CALL) {
			if (!new OpenGlMethodComparator().compare(javaPattern.getPieceByType(PieceType.OPENGL_CALL),
					jsPattern.getPieceByType(PieceType.OPENGL_CALL))) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.ARRAY_CONTENT) {
			if (!new ArrayContentComparator().compare(javaPattern.getPieceByType(PieceType.ARRAY_CONTENT),
					jsPattern.getPieceByType(PieceType.ARRAY_CONTENT))) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(2).getPieceType() == PieceType.ARRAY_DECLARATION) {
			if (!new ArrayDeclarationComparator().compare(
					javaPattern.getPieceByType(PieceType.ARRAY_DECLARATION),
					jsPattern.getPieceByType(PieceType.ARRAY_DECLARATION))) {
				return false;
			}
		}

		if (javaPattern.getPieces().get(3).getPieceType() == PieceType.NEW_STATEMENT) {
			if (!new NewStatementComparator().compare(javaPattern.getPieceByType(PieceType.NEW_STATEMENT),
					jsPattern.getPieceByType(PieceType.NEW_STATEMENT))) {
				return false;
			}
		}

		return true;
	}

}
