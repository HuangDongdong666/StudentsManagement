package students.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import students.entity.Students;
import students.service.StudentsDAO;
import students.service.impl.StudentsDaoImpl;

//学生Action
public class StudentsAction extends SuperAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//查询所有学生
	public String query(){
		StudentsDAO stuDao = new StudentsDaoImpl();
		List<Students> list = stuDao.queryAllStudents();
		//放进session
		if(list != null && list.size() > 0){
			session.setAttribute("students_list", list);
		}
		return "query_success";
	}
	
	//删除学生资料
	public String delete(){
		StudentsDAO stu = new StudentsDaoImpl();
		String sid = request.getParameter("sid");
		stu.deleteStudents(sid);
		return "delete_success";
	}
	
	//增加学生资料
	public String add() throws ParseException{
		Students s = new Students();
		StudentsDAO stu = new StudentsDaoImpl();
		
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		s.setAddress(request.getParameter("address"));
		
		//格式化日期
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyy-MM-dd");
		Date birthday =dateFormat.parse(request.getParameter("birthday"));
		s.setBirthday(birthday);
		
		stu.addStudents(s);
		return "add_success";
	}
	
	//显示学生资料
	public String modify(){
		//获取学生ID 
		String sid = request.getParameter("sid");
		StudentsDAO stu = new StudentsDaoImpl();
		Students s = stu.queryStudentsBySid(sid);
		//保存在会话中
		session.setAttribute("modify_students", s);
		return "modify_success";
	}
	
	//更新学生资料
	public String save() throws ParseException{
		Students s = new Students();
		StudentsDAO stu = new StudentsDaoImpl();
		
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		s.setAddress(request.getParameter("address"));
		
		//格式化日期
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyy-MM-dd");
		Date birthday =dateFormat.parse(request.getParameter("birthday"));
		s.setBirthday(birthday);	
		
		stu.updateStudents(s);
		return "save_success";
		
	}

}
