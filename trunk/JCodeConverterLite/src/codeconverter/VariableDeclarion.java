package codeconverter;

public abstract class VariableDeclarion implements ICodePiece {

	protected ICodePiece type=getTypeCode();
	protected Name name=new Name();

	public abstract ICodePiece getTypeCode();

	@Override
	public int elementMatch(String data, int matchPosition) {
		char[] datac=data.toCharArray();
		int position=matchPosition;

		int nextIndex=0;
		if(type!=null)
			nextIndex=type.elementMatch(data,position);
		else
			nextIndex=position;
		
		if (nextIndex != -1 && datac.length > nextIndex) {
			if (datac[nextIndex] == ' ') {
				nextIndex++;
				nextIndex=name.elementMatch(data,nextIndex);
				if (nextIndex != -1) {
					return nextIndex;
				}
			}
		}

		if (position == matchPosition)
			return -1;

		return position;
	}

}
