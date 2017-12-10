package students.service.impl;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import students.db.MyHibernateSessionFactoty;
import students.entity.User;
import students.service.UserDAO;

public class UserDAOImpl implements UserDAO {

	public boolean userLogin(User user) {
		//事务对象
		Transaction tx = null;
		//SQL查询语句
		String hql = "";
		try{
			//创建会话
			Session session = MyHibernateSessionFactoty.getSessionFactory().getCurrentSession();
			//开启事务
			tx = session.beginTransaction();
			hql = "from User where username=? and password=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, user.getUsername());
			query.setParameter(1, user.getPassword());
			List list = query.list();
			tx.commit();//提交事务
			if(list.size() > 0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			if(tx != null){
				tx = null;
			}
		}
	}

}
