package students.service;

import students.entity.User;

//用户业务逻辑接口
public interface UserDAO {

	//用户登陆
	public boolean userLogin(User u);
}
