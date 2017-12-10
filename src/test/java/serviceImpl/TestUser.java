package serviceImpl;

import org.junit.Test;

import junit.framework.Assert;
import students.entity.User;
import students.service.UserDAO;
import students.service.impl.UserDAOImpl;

public class TestUser {

	@Test
	public void testUserLogin(){
		User user = new User(1,"huang","123456");
		UserDAO userDao = new UserDAOImpl();
		//断言
		Assert.assertEquals(true, userDao.userLogin(user));
	}

	
}
