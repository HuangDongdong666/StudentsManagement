package students.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class MyHibernateSessionFactoty {

	private static SessionFactory sessionFactory;	//会话工厂
	
	//构造方法私有化，保证单例模式
	private MyHibernateSessionFactoty() {
		// TODO Auto-generated constructor stub
	}
	
	//公有的静态方法，获取会话工厂对象
	public static SessionFactory getSessionFactory(){
		if(sessionFactory == null){
			//创建服务注册对象
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
			//创建会话工厂对象
			sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
			//返回会话工厂对象
			return sessionFactory;
		}else{
			return sessionFactory;
		}
	}
	
}
