package mediator;

import java.util.HashMap;
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
	private static HashMap<Integer, String> numberToCardPath;
	
	
	
	public PlayingMediator() {
		
	}
	
	static{
		proxyGame= new ProxyGame();
		numberToCardPath=new HashMap<Integer, String>();
		setUpPaths();
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
	public String getImgPathFromNumber(int n){
		return numberToCardPath.get(n);
	}
	public int getCardBack(){
		return 41;
	}
	private static void setUpPaths(){
		numberToCardPath.put(1, "./img/carte/briscola/1coppe.png");
		numberToCardPath.put(2, "./img/carte/briscola/2coppe.png");
		numberToCardPath.put(3, "./img/carte/briscola/3coppe.png");
		numberToCardPath.put(4, "./img/carte/briscola/4coppe.png");
		numberToCardPath.put(5, "./img/carte/briscola/5coppe.png");
		numberToCardPath.put(6, "./img/carte/briscola/6coppe.png");
		numberToCardPath.put(7, "./img/carte/briscola/7coppe.png");
		numberToCardPath.put(8, "./img/carte/briscola/8coppe.png");
		numberToCardPath.put(9, "./img/carte/briscola/9coppe.png");
		numberToCardPath.put(10, "./img/carte/briscola/10coppe.png");
		numberToCardPath.put(11, "./img/carte/briscola/1bastoni.png");
		numberToCardPath.put(12, "./img/carte/briscola/2bastoni.png");
		numberToCardPath.put(13, "./img/carte/briscola/3bastoni.png");
		numberToCardPath.put(14, "./img/carte/briscola/4bastoni.png");
		numberToCardPath.put(15, "./img/carte/briscola/5bastoni.png");
		numberToCardPath.put(16, "./img/carte/briscola/6bastoni.png");
		numberToCardPath.put(17, "./img/carte/briscola/7bastoni.png");
		numberToCardPath.put(18, "./img/carte/briscola/8bastoni.png");
		numberToCardPath.put(19, "./img/carte/briscola/9bastoni.png");
		numberToCardPath.put(20, "./img/carte/briscola/10bastoni.png");
		numberToCardPath.put(21, "./img/carte/briscola/1spade.png");
		numberToCardPath.put(22, "./img/carte/briscola/2spade.png");
		numberToCardPath.put(23, "./img/carte/briscola/3spade.png");
		numberToCardPath.put(24, "./img/carte/briscola/4spade.png");
		numberToCardPath.put(25, "./img/carte/briscola/5spade.png");
		numberToCardPath.put(26, "./img/carte/briscola/6spade.png");
		numberToCardPath.put(27, "./img/carte/briscola/7spade.png");
		numberToCardPath.put(28, "./img/carte/briscola/8spade.png");
		numberToCardPath.put(29, "./img/carte/briscola/9spade.png");
		numberToCardPath.put(30, "./img/carte/briscola/10spade.png");
		numberToCardPath.put(31, "./img/carte/briscola/1denari.png");
		numberToCardPath.put(32, "./img/carte/briscola/2denari.png");
		numberToCardPath.put(33, "./img/carte/briscola/3denari.png");
		numberToCardPath.put(34, "./img/carte/briscola/4denari.png");
		numberToCardPath.put(35, "./img/carte/briscola/5denari.png");
		numberToCardPath.put(36, "./img/carte/briscola/6denari.png");
		numberToCardPath.put(37, "./img/carte/briscola/7denari.png");
		numberToCardPath.put(38, "./img/carte/briscola/8denari.png");
		numberToCardPath.put(39, "./img/carte/briscola/9denari.png");
		numberToCardPath.put(40, "./img/carte/briscola/10denari.png");
		numberToCardPath.put(41, "./img/carte/briscola/retro.png");

	}
}
