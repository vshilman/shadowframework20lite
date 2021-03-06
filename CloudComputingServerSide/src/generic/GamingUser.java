package generic;

public class GamingUser {

	private String nick;
	private String ip;
	private String platform;
	private String game;
	public GamingUser(String nick, String ip, String platform, String game) {
		super();
		this.nick = nick;
		this.ip = ip;
		this.platform = platform;
		this.game=game;
	}
	public String getNick() {
		return nick;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip){
		this.ip=ip;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform){
		this.platform=platform;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
}
