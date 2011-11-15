package codeconverter;

public class CodeMatch implements Comparable<CodeMatch> {

	private int lineStart;
	private int lineEnd;
	private ICodeElement matcher;

	public CodeMatch(int lineStart, int lineEnd, ICodeElement element) {
		super();
		this.lineStart=lineStart;
		this.lineEnd=lineEnd;
		matcher=element;
	}

	public int getLineStart() {
		return lineStart;
	}

	public int getLineEnd() {
		return lineEnd;
	}

	@Override
	public int compareTo(CodeMatch o) {
		if (lineStart == o.lineStart) {
			return o.lineEnd - lineStart;
		}
		return (lineStart - o.lineStart);
	}

	public ICodeElement getMatcher() {
		return matcher;
	}

}