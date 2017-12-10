package students.service;

import java.util.List;

import students.entity.Students;

//学生业务接口
public interface StudentsDAO {

	//查询所有学生资料
	public List<Students> queryAllStudents();
	
	//查询单个学生资料,根据学生id
	public Students queryStudentsBySid(String sid);
	
	//添加学生资料
	public boolean addStudents(Students stu);
	
	//修改学生资料
	public boolean updateStudents(Students stu);
	
	//删除学生资料,根据学生id
	public boolean deleteStudents(String sid);
}
