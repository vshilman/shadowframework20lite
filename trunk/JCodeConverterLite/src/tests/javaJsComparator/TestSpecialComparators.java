package tests.javaJsComparator;

import java.util.ArrayList;

import codeconverter.CodePattern;
import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.JavaArrayContentDeclaration;
import codeconverter.java.codelines.JavaVariableDeclarationAndAssignment;
import codeconverter.javaJsComparator.CodePatternComparator;
import codeconverter.javaJsComparator.codePatterns.VariableDeclarationAndAssignmentComparator;
import codeconverter.javaJsComparator.special.ArrayContentComparator;
import codeconverter.js.JsArrayContent;
import codeconverter.js.codelines.JsVariableDeclarationAndAssignment;

public class TestSpecialComparators {

	public static void main(String[] args) {

		String javaLine = "0.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f";
		String jsLine = "[0.0, 1.0, 0.0, -1.0, -1.0, 0.0, 1.0, -1.0, 0.0]";

		ArrayContentComparator arrayContentComparator = new ArrayContentComparator();

		CodePattern javaMatch = new JavaArrayContentDeclaration().match(javaLine);
		ICodePieceMatch jsMatch = new JsArrayContent().elementMatch(jsLine, 0);

		if (javaMatch != null && jsMatch.getMatchPosition() != -1) {
			System.out.print(arrayContentComparator.compare(javaMatch, jsMatch.getDataPiece()));
			System.out.println(" --> {" + javaLine + "} , {" + jsLine + "}");
		} else {
			System.out.println("didn't match");
		}

		javaLine = "float[] vertices =";
		String javaLine2 = "0.0f, 1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f";
		jsLine = "var vertices = [ 0.0, 1.0, 0.0, -1.0, -1.0, 0.0, 1.0, -1.0, 0.0 ]";

		CodePatternComparator comparator = new VariableDeclarationAndAssignmentComparator();

		ArrayList<CodePattern> javaMatch2 = new ArrayList<CodePattern>();
		javaMatch2.add(new JavaVariableDeclarationAndAssignment().match(javaLine));
		javaMatch2.add(new JavaArrayContentDeclaration().match(javaLine2));
		ArrayList<CodePattern> jsMatch2 = new ArrayList<CodePattern>();
		jsMatch2.add(new JsVariableDeclarationAndAssignment().match(jsLine));

		if (javaMatch2.get(0) != null && javaMatch2.get(1) != null && jsMatch2.get(0) != null) {
			int[][] result = comparator.compare(javaMatch2, 0, jsMatch2, 0);
			if (result != null) {
				System.out.print("true");
			} else {
				System.out.print("false");
			}
			System.out.println(" --> {" + javaLine + "{" + javaLine2 + "} } , {" + jsLine + "}");
		} else {
			System.out.println("didn't match");
		}

	}

}
