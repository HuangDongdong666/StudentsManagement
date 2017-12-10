package entity;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import students.entity.Students;
import students.entity.User;

public class TestEntity {
	private SessionFactory sessionFactory = null;
	private Session session;
	private Transaction transaction;

	@Before
	public void init() {
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
		//创建会话工厂对象
		sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		//创建会话对象
		session = sessionFactory.openSession();
		//开启事务
		transaction = session.beginTransaction();
	}
	@After
	public void destory(){
		transaction.commit();//提交事务
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testStudents(){
		Students stu1 = new Students("s0000001","黄东东","男",new Date(),"广州市");
		Students stu2 = new Students("s0000002","黄西西","男",new Date(),"佛山市");
		Students stu3 = new Students("s0000003","黄北北","男",new Date(),"深圳市");
		Students stu4 = new Students("s0000004","黄南南","男",new Date(),"东莞市");
		session.save(stu1);
		session.save(stu2);
		session.save(stu3);
		session.save(stu4);
	}
	
	@Test
	public void testUser(){
		User user = new User();
		user.setUsername("黄东东");
		user.setPassword("123456");
		session.save(user);
	}
}
