package mediator;

import java.util.LinkedList;
import java.util.List;

import game.Briscola;
import game.IGame;
import game.Memory;
import game.ProxyGame;

public class PlayingMediator {

	
	private static IGame briscola;
	private static IGame memory;
	private static ProxyGame proxyGame;
	private String gameName;
	
	
	
	public PlayingMediator() {
		
	}
	
	static{
		proxyGame= new ProxyGame();
	}
	
	public void setGame(String gameName) {
		this.gameName=gameName;
		 if (gameName.equals("briscola")) {
			proxyGame.changeGame(briscola);
		}else if (gameName.equals("memory")) {
			proxyGame.changeGame(memory);
		}
	}
	
	public void startGame(int ID, int numberOfPlayers, int myTurn, LinkedList<String> orderedPlayers, int numberOfCards){
		if (gameName.equals("briscola")) {
			briscola=new Briscola(ID, numberOfPlayers, myTurn, orderedPlayers);
		}else if (gameName.equals("briscola")) {
			memory=new Memory(ID, numberOfCards, myTurn, orderedPlayers);
		}
	}
	
	public int nextCard() {
		return proxyGame.nextCard();
	}
	public String checkWin() {
		return proxyGame.checkWin();
		
	}
	public void setPlayer(int position, String name) {
		proxyGame.setPlayer(position, name);
		
	}
	public LinkedList<String> getOrderedPlayers() {
		return proxyGame.getOrderedPlayers();
	}
	public List<Integer> getPossiblePlayers() {
		return proxyGame.getPossiblePlayers();
	}
	
	public int getMaxPlayers() {
		return proxyGame.getMaxPlayers();
	}
	
	public String storeCard(List<Integer> card) {
		return proxyGame.storeCard(card);
		
	}
}
