package utils;

import java.io.ObjectInputStream.GetField;
import java.util.LinkedList;
import java.util.List;

public class Table {

	private String name; 
	private int id;
	private String game;
	private int playersSupported;
	private List<String> playersList;
	private boolean spectable;
	private String manager;
	
	
	public Table(String name, int id, String game, int playersSupported, List<String> playersList, boolean spectable, String manager) {
		super();
		this.name = name;
		this.id = id;
		this.game=game;
		this.playersSupported = playersSupported;
		this.playersList = playersList;
		this.spectable = spectable;
		this.manager=manager;
	}


	public int getPlayersSupported() {
		return playersSupported;
	}


	public void setPlayersSupported(int playersSupported) {
		this.playersSupported = playersSupported;
	}


	public List<String> getPlayersList() {
		return playersList;
	}


	public void setAvailablePlayerPlaces(List<String> playersList) {
		this.playersList = playersList;
	}


	public boolean isSpectable() {
		return spectable;
	}


	public void setSpectation(boolean spectable) {
		this.spectable = spectable;
	}


	public String getName() {
		return name;
	}
	
	public LinkedList<String> getOrderedPlayers() {
		List<String> ordered= new LinkedList<String>();
		for (int i = 0; i < playersList.size(); i++) {
			ordered.add(playersList.get(i));
		}
		return (LinkedList<String>)ordered;
	}
	
	public int getId() {
		return id;
	}
	
	public String getManager(){
		return manager; 
	}
	 public String getGame(){
		 return game;
	 }
	public boolean isEmpty() {
		if (playersList.size()==playersSupported) {
			return false;
		}
		return true;
	}
	
}
