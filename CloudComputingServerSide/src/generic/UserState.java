package generic;

import java.util.List;

public class UserState {

	private String ip;
	private int port;
	private String available;
	private String platform;

	public UserState(String ip, int port, String available, String platform) {
		super();
		this.ip = ip;
		this.port = port;
		this.available = available;
		this.platform=platform;
		}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}
	
	public void setPort(int port){
		this.port=port;
	}

	public String isAvailable() {
		return available;
	}

	public void setAvailability(String availability) {
		this.available=availability;
	}
	
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	

}
