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
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
import codeconverter.javaJsComparator.codePieces.VariableComparator;

public class VariableDeclarationAndAssignmentComparator implements CodePatternComparator {

	@Override
	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex,
			List<CodePattern> jsCodePatterns, int jsIndex) {
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != PatternType.VARIABLE_DECLARATION
				|| javaCodePatterns.get(javaIndex).getPatternType().get(1) != PatternType.VARIABLE_ASSIGNMENT) {
			return null;
		}
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != jsCodePatterns.get(jsIndex)
				.getPatternType().get(0)
				|| javaCodePatterns.get(javaIndex).getPatternType().get(1) != jsCodePatterns.get(jsIndex)
						.getPatternType().get(1)) {
			return null;
		}
		CodePattern javaPattern = javaCodePatterns.get(javaIndex);
		CodePattern jsPattern = jsCodePatterns.get(jsIndex);

		if (!new VariableComparator().compare(javaPattern.getPieceByType(PieceType.VARIABLE),
				jsPattern.getPieceByType(PieceType.VARIABLE))) {
			return null;
		}

		if (javaPattern.getPieces().size() > 2) {

			if (javaPattern.getPieces().get(2).getPieceType() == PieceType.EXPRESSION) {
				if (!new ExpressionComparator().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
						jsPattern.getPieceByType(PieceType.EXPRESSION))) {
					return null;
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

			if (javaPattern.getPieces().get(2).getPieceType() == PieceType.CALL
					&& jsPattern.getPieces().get(2).getPieceType() == PieceType.ARRAY_DECLARATION) {
				ICodePiece call = javaPattern.getPieceByType(PieceType.CALL);
				String vectorName = call.getPieceByType(PieceType.VARIABLE).getPieceByType(PieceType.NAME).toString();

				int[][] result = ComparatorUtilities.findArrayDecalation(javaCodePatterns, javaIndex, jsIndex,
						jsPattern, call, vectorName);
				if (result != null) {
					return result;
				}
			}

			if (javaPattern.getPieces().get(2).getPieceType() == PieceType.CALL) {
				if (!new MethodComparator().compare(javaPattern.getPieceByType(PieceType.CALL),
						jsPattern.getPieceByType(PieceType.CALL))) {

					if (javaPattern.getPieceByType(PieceType.CALL).toString().replaceAll(" ", "")
							.equals("System.currentTimeMillis()")
							&& jsPattern.getPieceByType(PieceType.CALL).toString().replaceAll(" ", "")
									.equals("newDate().getTime()")) {
						return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
					}

					if (javaPattern.getPieceByType(PieceType.CALL).getPieceByType(PieceType.SEQUENCE)
							.getPieceByType(PieceType.COMPOSITE).getPieceByType(PieceType.COMPOSITE)
							.toString().trim().equals("Arrays.copyOf")
							&& jsPattern.getPieceByType(PieceType.NEW_STATEMENT)
									.getPieceByType(PieceType.TYPE).toString().trim().equals("Float32Array")) {

						if (new ExpressionComparator().compare(javaPattern.getPieceByType(PieceType.CALL)
								.getPieceByType(PieceType.SEQUENCE).getPieceByType(PieceType.COMPOSITE)
								.getPieceByType(PieceType.SEQUENCE).getPieceByType(PieceType.EXPRESSION),
								jsPattern.getPieceByType(PieceType.NEW_STATEMENT).getPieceByType(PieceType.COMPOSITE)
										.getPieceByType(PieceType.SEQUENCE)
										.getPieceByType(PieceType.EXPRESSION))) {
							return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
						}
					}

					if (javaPattern.getPieceByType(PieceType.CALL).getPieceByType(PieceType.SEQUENCE)
							.getPieceByType(PieceType.COMPOSITE).getPieceByType(PieceType.COMPOSITE)
							.toString().trim().equals("TextureIO.newTextureData")
							&& jsPattern.getPieceByType(PieceType.NEW_STATEMENT)
									.getPieceByType(PieceType.TYPE).toString().trim().equals("Image")) {
						String texName = javaPattern.getPieceByType(PieceType.VARIABLE).getPieces().get(1)
								.toString().trim();
						String filePath = javaPattern.getPieceByType(PieceType.CALL)
								.getPieceByType(PieceType.SEQUENCE).getPieceByType(PieceType.COMPOSITE)
								.getPieceByType(PieceType.SEQUENCE).getPieceByType(PieceType.EXPRESSION)
								.getPieceByType(PieceType.NEW_STATEMENT).getPieceByType(PieceType.COMPOSITE)
								.getPieceByType(PieceType.SEQUENCE).toString().replaceAll(" ", "");
						int found = 0;
						int foundIndex[] = new int[2];
						for (int i = 0; i < jsCodePatterns.size(); i++) {
							if (jsCodePatterns.get(i).getPatternType().get(0) == PatternType.VARIABLE_ASSIGNMENT) {
								String patternString = jsCodePatterns.get(i).toString().replaceAll(" ", "");
								if (patternString.startsWith(texName)
										&& (patternString.endsWith(".onload=function()") || (patternString
												.indexOf(".src") != -1 && patternString.endsWith(filePath)))) {
									foundIndex[found] = i;
									found++;
									if (found == 2) {
										break;
									}
								}
							}
						}
						if (found == 2) {
							return new int[][] { new int[] { javaIndex }, new int[] { jsIndex, foundIndex[0], foundIndex[1] } };
						}
					}
					return null;
				}
			}

			if (javaPattern.getPieces().get(2).getPieceType() == PieceType.OPENGL_CALL) {
				if (!new OpenGlMethodComparator().compare(javaPattern.getPieceByType(PieceType.OPENGL_CALL),
						jsPattern.getPieceByType(PieceType.OPENGL_CALL))) {
					return null;
				}
			}
			
			if (javaPattern.getPieces().get(2).getPieceType() == PieceType.ARRAY_CONTENT) {
				if (!new ArrayContentComparator().compare(javaPattern.getPieceByType(PieceType.ARRAY_CONTENT),
						jsPattern.getPieceByType(PieceType.ARRAY_CONTENT))) {
					return null;
				}
			}
			
			
			if (javaPattern.getPieces().get(2).getPieceType() == PieceType.ARRAY_DECLARATION) {
				if (!new ArrayDeclarationComparator().compare(javaPattern.getPieceByType(PieceType.ARRAY_DECLARATION),
						jsPattern.getPieceByType(PieceType.ARRAY_DECLARATION))) {
					return null;
				}
			}

			if (javaPattern.getPieces().get(2).getPieceType() == PieceType.NEW_STATEMENT) {
				if (!new NewStatementComparator().compare(
						javaPattern.getPieceByType(PieceType.NEW_STATEMENT),
						jsPattern.getPieceByType(PieceType.NEW_STATEMENT))) {
					return null;
				}
			}
			
		} else {
			return null;
		}

		return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
	}

}
