package generic;

public class User {

	private String name;
	private String surname;
	private String nickname;
	private String email;
	private String pass;
	
	public User(String name, String surname, String nickname, String email,
			String pass) {
		super();
		this.name = name;
		this.surname = surname;
		this.nickname = nickname;
		this.email = email;
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return pass;
	}
	
	
	
	
	
}
