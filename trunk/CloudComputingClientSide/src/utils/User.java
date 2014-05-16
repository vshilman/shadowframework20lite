package utils;

public class User {

	private String nick;
	private String ip;
	private String platform;
	public User(String nick, String ip, String platform) {
		super();
		this.nick = nick;
		this.ip = ip;
		this.platform = platform;
	}
	public String getNick() {
		return nick;
	}
	public String getIp() {
		return ip;
	}
	public String getPlatform() {
		return platform;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
}
