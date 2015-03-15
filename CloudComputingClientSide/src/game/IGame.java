package game;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public interface IGame {

	
	public int getMaxPlayers();
	
	public int nextCard();
	
	public String checkWin();
	
	public LinkedList<String> getOrderedPlayers();
	
	public List<Integer> getPossiblePlayers();
	
	public String storeCard(List<Integer> card);
	
	public void setPlayer(int position, String name);
	
}
