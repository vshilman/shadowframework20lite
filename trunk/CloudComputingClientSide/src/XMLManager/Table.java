package XMLManager;

public class Table {

	private String name; 
	private String availablePlayers;
	private String availableSpectators;
	
	public String getName() {
		return name;
	}

	public void setAttribute(String name, String value){
		if (name.equals("nomeTavolo")) {
			setName(value);
		}else if (name.equals("postiPerGiocatori")) {
			setAvailablePlayers(value);
		}else if (name.equals("postiPerSpettatori")) {
			setAvailableSpectators(value);
		}
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvailablePlayers() {
		return availablePlayers;
	}
	public void setAvailablePlayers(String availablePlayers) {
		this.availablePlayers = availablePlayers;
	}
	public String getAvailableSpectators() {
		return availableSpectators;
	}

	public void setAvailableSpectators(String availableSpectators) {
		this.availableSpectators = availableSpectators;
	}
	
	@Override
	public String toString() {
		return "Table [name=" + name + ", avaible Players=" + availablePlayers + ", avaible Spectators="+ availableSpectators + "]";
	}
}
