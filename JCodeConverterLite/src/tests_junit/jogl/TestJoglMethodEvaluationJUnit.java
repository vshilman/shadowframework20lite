package tests_junit.jogl;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.jogl.JoglMethodEvaluation;

public class TestJoglMethodEvaluationJUnit {

	@Test
	public void test() {
		JoglMethodEvaluation methodEvaluation = new JoglMethodEvaluation(".");
		String[] data = new String[] { "gl.glAttachShader(shaderProgram, vertexShader)",
				"gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)",
				"glClearColor(0.0f, 0.0f, 0.0f, 1.0f)",
				"class.function(0.0f, 0.0f, 0.0f, 1.0f)",
				"gl.glClear(GL2.GL_COLOR_BUFFER_BIT)",
				"gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT)",
				"gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertices.length * BufferUtil.SIZEOF_FLOAT, BufferUtil.newFloatBuffer(vertices), GL2.GL_STATIC_DRAW)"};
		String sx="";
		for (String s : data) {
			ICodePieceMatch match = methodEvaluation.elementMatch(s, 0);
			sx+=writeCodePieceMatch(match);
		}
		
		assertEquals(sx, "Matching Piece 46 [ gl . gl AttachShader (  shaderProgram  ,  vertexShader   )]\nMatching Piece 39 [ gl . gl ClearColor ( 0.0f, 0.0f, 0.0f, 1.0f )]\nMatching Piece -1 [null]\nMatching Piece -1 [null]\nMatching Piece 35 [ gl . gl Clear ( GL2 .GL_ COLOR_BUFFER_BIT )]\nMatching Piece 61 [ gl . gl Clear ( GL2 .GL_ COLOR_BUFFER_BIT|GL2 .GL_ DEPTH_BUFFER_BIT )]\nMatching Piece 136 [ gl . gl BufferData ( GL2 .GL_ ARRAY_BUFFER,  vertices.length  * BufferUtil.SIZEOF_FLOAT  ,   BufferUtil.newFloatBuffer   (  vertices   ), GL2 .GL_ STATIC_DRAW )]\n");
	
	}

	public static String writeCodePieceMatch(ICodePieceMatch match) {
		return "Matching Piece " + match.getMatchPosition() + " [" + match.getDataPiece() + "]\n";
	}
}
