package students.action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import students.entity.User;
import students.service.UserDAO;
import students.service.impl.UserDAOImpl;

public class UsersAction extends SuperAction implements ModelDriven<User> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();
	
	//用户登陆方法
	public String login(){
		UserDAO userDao = new UserDAOImpl();
		if(userDao.userLogin(user)){
			session.setAttribute("loginUserName", user.getUsername());
			return "login_success";
		}else{
			return "login_failure";
		}
	}
	
	//注销用户登陆,跳过表单验证
	@SkipValidation
	public String logout(){
		if(session.getAttribute("loginUserName") != null){
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	
	//服务器表单验证
	@Override
	public void validate() {
		//用户名不能为空
		if("".equals(user.getUsername().trim())){
			this.addFieldError("usernameErroe", "用户名不能为空");
		}
		if(user.getPassword().length() < 6){
			this.addFieldError("passwordError", "密码长度不少于6位");
		}
	}

	public User getModel() {
		return this.user;
	}

	

}
