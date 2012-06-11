package codeconverter.javaJsComparator.codePatterns;

import java.util.List;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePieces.ExpressionComparator;
import codeconverter.javaJsComparator.codePieces.MethodComparator;
import codeconverter.javaJsComparator.codePieces.NewStatementComparator;
import codeconverter.javaJsComparator.codePieces.OpenGlMethodComparator;
import codeconverter.javaJsComparator.codePieces.VariableComparator;
import codeconverter.javaJsComparator.special.ArrayContentComparator;

public class AttributeAndVariableDeclarationAndAssignmentComparator implements CodePatternComparator {

	@Override
	public int[][] compare(List<CodePattern> javaCodePatterns, int javaIndex,
			List<CodePattern> jsCodePatterns, int jsIndex) {
		
		if (javaCodePatterns.get(javaIndex).getPatternType().size() < 2) {
			return null;
		}
		if (javaCodePatterns.get(javaIndex).getPatternType().get(0) != PatternType.ATTRIBUTE_DECLARATION
				|| javaCodePatterns.get(javaIndex).getPatternType().get(1) != PatternType.ATTRIBUTE_ASSIGNMENT) {
			return null;
		}
		if (jsCodePatterns.get(jsIndex).getPatternType().get(0) != PatternType.VARIABLE_DECLARATION
				|| jsCodePatterns.get(jsIndex).getPatternType().get(1) != PatternType.VARIABLE_ASSIGNMENT) {
			return null;
		}
		CodePattern javaPattern = javaCodePatterns.get(javaIndex);
		CodePattern jsPattern = jsCodePatterns.get(jsIndex);

		if (!new VariableComparator().compare(javaPattern.getPieceByType(PieceType.VARIABLE),
				jsPattern.getPieceByType(PieceType.VARIABLE))) {
			return null;
		}
		
		if (javaPattern.getPieces().size() > 3) {

			if (javaPattern.getPieces().get(3).getPieceType() == PieceType.EXPRESSION) {
				if (!new ExpressionComparator().compare(javaPattern.getPieceByType(PieceType.EXPRESSION),
						jsPattern.getPieceByType(PieceType.EXPRESSION))) {
					return null;
				}
			}

			if (javaPattern.getPieces().get(3).getPieceType() == PieceType.CALL) {
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

					if (jsPattern.getPieces().get(3).getPieceType() == PieceType.NEW_STATEMENT) {
						ICodePiece call = javaPattern.getPieces().get(3).getPieces().get(2)
								.getPieceByType(PieceType.COMPOSITE);
						String vectorName = call.getPieceByType(PieceType.SEQUENCE).toString().trim();

						int[][] result = findArrayDecalation(javaCodePatterns, javaIndex, jsIndex, jsPattern,
								call, vectorName);
						if (result != null) {
							return result;
						}
					}

					return null;
				}
			}

			if (javaPattern.getPieces().get(3).getPieceType() == PieceType.OPENGL_CALL) {
				if (!new OpenGlMethodComparator().compare(javaPattern.getPieceByType(PieceType.OPENGL_CALL),
						jsPattern.getPieceByType(PieceType.OPENGL_CALL))) {
					return null;
				}
			}

			if (javaPattern.getPieces().get(3).getPieceType() == PieceType.NEW_STATEMENT) {
				if (!new NewStatementComparator().compare(
						javaPattern.getPieceByType(PieceType.NEW_STATEMENT),
						jsPattern.getPieceByType(PieceType.NEW_STATEMENT))) {

					if (javaPattern.getPieceByType(PieceType.NEW_STATEMENT).getPieceByType(PieceType.TYPE)
							.getPieceByType(PieceType.TYPE).toString().equals("float")
							&& jsPattern.getPieceByType(PieceType.NEW_STATEMENT)
									.getPieceByType(PieceType.TYPE).getPieceByType(PieceType.TYPE).toString()
									.equals("Float32Array")) {
						if (javaCodePatterns.size() > javaIndex + 1) {
							if (javaCodePatterns.get(javaIndex + 1).getPatternType().get(0) == PatternType.ARRAY_CONTENT_DECLARTION) {
								if (new ArrayContentComparator().compare(
										javaCodePatterns.get(javaIndex + 1),
										jsPattern.getPieceByType(PieceType.NEW_STATEMENT)
												.getPieceByType(PieceType.COMPOSITE)
												.getPieceByType(PieceType.SEQUENCE)
												.getPieceByType(PieceType.ARRAY_CONTENT))) {
									return new int[][] { new int[] { javaIndex, javaIndex + 1 }, new int[] { jsIndex } };
								}
							}
						}
					}

					return null;
				}
			}
		} else {
			if (javaCodePatterns.size() > javaIndex + 1) {
				if (javaCodePatterns.get(javaIndex + 1).getPatternType().get(0) == PatternType.ARRAY_CONTENT_DECLARTION) {
					if (!new ArrayContentComparator().compare(javaCodePatterns.get(javaIndex + 1),
							jsPattern.getPieceByType(PieceType.ARRAY_CONTENT))) {
						return null;
					}
				}
			}
			return new int[][] { new int[] { javaIndex, javaIndex + 1 }, new int[] { jsIndex } };
		}

		return new int[][] { new int[] { javaIndex }, new int[] { jsIndex } };
	}
	
	private int[][] findArrayDecalation(List<CodePattern> javaCodePatterns, int javaIndex, int jsIndex,
			CodePattern jsPattern, ICodePiece call, String vectorName) {
		for (int i = 0; i < javaCodePatterns.size(); i++) {
			if (javaCodePatterns.get(i).getPatternType().get(0) == PatternType.VARIABLE_DECLARATION
					&& javaCodePatterns.get(i).getPatternType().get(1) == PatternType.VARIABLE_ASSIGNMENT) {
				if (javaCodePatterns.get(i).getPieceByType(PieceType.VARIABLE).getPieceByType(PieceType.NAME)
						.toString().trim().equals(vectorName)) {
					if (javaCodePatterns.get(i).getPieceByType(PieceType.VARIABLE)
							.getPieceByType(PieceType.TYPE).toString().equals("float[]")
							&& call.getPieceByType(PieceType.COMPOSITE).toString().trim()
									.equals("BufferUtil.newFloatBuffer")) {
						if (new ArrayContentComparator().compare(
								javaCodePatterns.get(i + 1),
								jsPattern.getPieces().get(2).getPieceByType(PieceType.COMPOSITE)
										.getPieceByType(PieceType.SEQUENCE)
										.getPieceByType(PieceType.ARRAY_CONTENT))) {
							return new int[][] { new int[] { javaIndex, i, i + 1 }, new int[] { jsIndex } };
						}
					}
				}
			}
			if (javaCodePatterns.get(i).getPatternType().get(0) == PatternType.VARIABLE_ASSIGNMENT) {
				if (javaCodePatterns.get(i).getPieceByType(PieceType.NAME).toString().trim()
						.equals(vectorName)) {
					if (javaCodePatterns.get(i).getPieceByType(PieceType.NEW_STATEMENT)
							.getPieceByType(PieceType.TYPE).getPieceByType(PieceType.TYPE).toString()
							.equals("float")
							&& call.getPieceByType(PieceType.COMPOSITE).toString().trim()
									.equals("BufferUtil.newFloatBuffer")) {
						if (new ArrayContentComparator().compare(
								javaCodePatterns.get(i + 1),
								jsPattern.getPieces().get(3).getPieceByType(PieceType.COMPOSITE)
										.getPieceByType(PieceType.SEQUENCE)
										.getPieceByType(PieceType.ARRAY_CONTENT))) {
							return new int[][] { new int[] { javaIndex, i, i + 1 }, new int[] { jsIndex } };
						}
					}
				}
			}
		}
		return null;
	}

}
