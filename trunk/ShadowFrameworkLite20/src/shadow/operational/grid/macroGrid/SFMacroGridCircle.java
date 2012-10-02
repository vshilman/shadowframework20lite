package shadow.operational.grid.macroGrid;

public interface SFMacroGridCircle<T>{
		
	public void getValue(int edgeIndex,int index, T value);
	
	public void setValue(int edgeIndex,int index,T value);

	public int getN() ;

	public int getEdges() ;
}
