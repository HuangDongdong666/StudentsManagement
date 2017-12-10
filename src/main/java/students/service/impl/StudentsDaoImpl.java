package students.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import students.db.MyHibernateSessionFactoty;
import students.entity.Students;
import students.service.StudentsDAO;

//学生业务逻辑接口的实现
public class StudentsDaoImpl implements StudentsDAO {

	Transaction tx = null;
	List<Students> list = null;
	String hql = "";
	
	//查询所有学生的信息
	public List<Students> queryAllStudents() {
		try{
			Session session = MyHibernateSessionFactoty.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql="from Students";
			Query query = session.createQuery(hql);
			list = query.list();
			tx.commit();
			return list;
			
		}catch(Exception e){
			e.printStackTrace();
			tx.commit();
			return list;
		}finally{
			if(tx != null){
				tx = null;
			}
		}
	}

	//根据sid查找学生
	public Students queryStudentsBySid(String sid) {
		Students s = null;
		try{
			Session session = MyHibernateSessionFactoty.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			s = session.get(Students.class, sid);
			tx.commit();
			return s;
			
		}catch(Exception e){
			e.printStackTrace();
			tx.commit();
			return s;
		}finally{
			if(tx != null){
				tx = null;
			}
		}
	}

	//增加学生资料
	public boolean addStudents(Students stu) {
		stu.setSid(getNewSid()); 		//设置学生ID
		try{
			Session session = MyHibernateSessionFactoty.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(stu);
			tx.commit();
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			tx.commit();
			return false;
		}finally{
			if(tx != null){
				tx = null;
			}
		}
	}

	//更新学生资料
	public boolean updateStudents(Students stu) {
		try{
			Session session = MyHibernateSessionFactoty.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(stu);
			tx.commit();
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			tx.commit();
			return false;
		}finally{
			if(tx != null){
				tx = null;
			}
		}
	}

	//删除学生信息,根据学生ID
	public boolean deleteStudents(String sid) {
		try{
			Session session = MyHibernateSessionFactoty.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Students stu = session.get(Students.class, sid);
			session.delete(stu);
			tx.commit();
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			tx.commit();
			return false;
		}finally{
			if(tx != null){
				tx = null;
			}
		}
	}
	
	//生成学生的学号
	private String getNewSid(){
		try{
			Session session = MyHibernateSessionFactoty.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			//获取当前学生的最大编号
			hql = "select max(sid) from Students";
			Query query = session.createQuery(hql);
			String sid = (String)query.uniqueResult();
			if(sid == null || "".equals(sid)){			//如果为null，表示数据库中没有数据
				sid = "s0000001";						//给定一个默认的sid
			}else{
				String temp = sid.substring(1);			//去掉第一位，取后7位
				int i = Integer.parseInt(temp);			//String转为int
				i++;
				temp = String.valueOf(i);				//int转为String
				int len = temp.length();				//String的长度
				for(int j =0; j < 7-len; j++){			//补足7位
					temp = "0" + temp;
				}
				sid = "s" + temp;						//补上s,补足8位
				
			}
			tx.commit();
			return sid;
			
		}catch(Exception e){
			e.printStackTrace();
			tx.commit();
			return null;
		}finally{
			if(tx != null){
				tx = null;
			}
		}
	}

}
