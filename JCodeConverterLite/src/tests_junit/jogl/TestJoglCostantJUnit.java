package tests_junit.jogl;

import static org.junit.Assert.*;

import org.junit.Test;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.jogl.JoglConstant;

public class TestJoglCostantJUnit {

	@Test
	public void test() {
		
		JoglConstant constant=new JoglConstant();
		String []data=new String[]{"GL.GL_COLOR_BUFFER_BIT","GL2.GL_COLOR_BUFFER_BIT",
				"GL.COLOR_BUFFER_BIT","GL_COLOR_BUFFER_BIT",
				"gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT)"};
		String sx="";
		for (String s : data) {
			ICodePieceMatch match=constant.elementMatch(s, 0);
			sx+=writeCodePieceMatch(match);
		}
		
		assertEquals(sx, "Matching Piece 22 [GL .GL_ COLOR_BUFFER_BIT]\nMatching Piece 23 [GL2 .GL_ COLOR_BUFFER_BIT]\nMatching Piece -1 [null]\nMatching Piece -1 [null]\nMatching Piece -1 [null]\n");
		
	}
	
	public static String writeCodePieceMatch(ICodePieceMatch match){
		return "Matching Piece "+match.getMatchPosition()+" ["+match.getDataPiece()+"]\n";
	}

}
