package tests.jogl;

import codeconverter.ICodePiece.ICodePieceMatch;
import codeconverter.java.jogl.JoglConstant;

public class TestJoglConstant {

	public static void main(String[] args) {
		
		JoglConstant constant=new JoglConstant();
		String []data=new String[]{"GL.GL_COLOR_BUFFER_BIT","GL2.GL_COLOR_BUFFER_BIT",
				"GL.COLOR_BUFFER_BIT","GL_COLOR_BUFFER_BIT"};
		
		for (String s : data) {
			ICodePieceMatch match=constant.elementMatch(s, 0);
			writeCodePieceMatch(match);
		}
		
	}
	
	public static void writeCodePieceMatch(ICodePieceMatch match){
		System.out.println("Matching Piece "+match.getMatchPosition()+" ["+match.getDataPiece()+"]");
	}
}
