package tests.jogl;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.jogl.JoglMethodEvaluation;

public class TestJoglMethodEvaluation {

	public static void main(String[] args) {

		JoglMethodEvaluation methodEvaluation = new JoglMethodEvaluation(".");
		String[] data = new String[] { "gl.glAttachShader(shaderProgram, vertexShader)",
				"gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)",
				"glClearColor(0.0f, 0.0f, 0.0f, 1.0f)",
				"class.function(0.0f, 0.0f, 0.0f, 1.0f)",
				"gl.glClear(GL2.GL_COLOR_BUFFER_BIT)",
				"gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT)",
				"gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices), GL2.GL_STATIC_DRAW)"};

		for (String s : data) {
			ICodePieceMatch match = methodEvaluation.elementMatch(s, 0);
			writeCodePieceMatch(match);
		}

	}

	public static void writeCodePieceMatch(ICodePieceMatch match) {
		System.out.println("Matching Piece " + match.getMatchPosition() + " [" + match.getDataPiece() + "]");
	}
}
