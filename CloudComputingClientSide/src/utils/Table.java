package utils;

import java.util.List;

public class Table {

	private String name; 
	private int id;
	private int playersSupported;
	private List<String> playersList;
	private boolean spectable;
	
	
	public Table(String name, int id, int playersSupported,
			List<String> playersList, boolean spectable) {
		super();
		this.name = name;
		this.id = id;
		this.playersSupported = playersSupported;
		this.playersList = playersList;
		this.spectable = spectable;
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


	public int getId() {
		return id;
	}
	

	
	
	
}
