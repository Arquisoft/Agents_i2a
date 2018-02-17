package domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class UserLoginData {

	private String login;
	private String password;
	private String kind;

	public UserLoginData(){

	}

	public UserLoginData(String login, String password, String kind) {
		this.login = login;
		this.password = password;
		this.setKind(kind);
	}

	public String getLogin() {
		return login;
	}

    public String getPassword() {
		return password;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
}
