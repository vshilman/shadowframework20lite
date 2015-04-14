package game;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import mediator.Mediator;

public class ProxyGame implements IGame{

	
	public IGame game;
	
	
	public void changeGame(IGame game){
		this.game=game;
	}
	
	@Override
	public String checkWin() {
		return game.checkWin();
		
	}
	@Override
	public void setPlayer(int position, String name) {
		game.setPlayer(position, name);
		
	}
	@Override
	public LinkedList<String> getOrderedPlayers() {
		return game.getOrderedPlayers();
	}
	@Override
	public List<Integer> getPossiblePlayers() {
		return game.getPossiblePlayers();
	}
	
	@Override
	public int getMaxPlayers() {
		return game.getMaxPlayers();
	}
	
	@Override
	public int nextCard() {
		return game.nextCard();
	}
	@Override
	public String storeCard(List<Integer> card) {
		return game.storeCard(card);
		
	}
	@Override
	public int firstCard() {
		return game.firstCard();
	}
	
}
