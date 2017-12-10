package students.entity;

//用户类
public class User {

	private int uid;				//用户ID
	private String username;		//用户名
	private String password;		//用户密码
	
	public User() {
	}
	
	public User(int uid, String username, String password) {
		this.uid = uid;
		this.username = username;
		this.password = password;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
