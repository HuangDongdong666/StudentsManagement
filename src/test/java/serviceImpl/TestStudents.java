package serviceImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;
import students.entity.Students;
import students.service.StudentsDAO;
import students.service.impl.StudentsDaoImpl;

public class TestStudents {

	@Test
	public void testAllStudents(){
		StudentsDAO stu = new StudentsDaoImpl();
		List<Students> list = stu.queryAllStudents();
		
		/*for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).toString());
		}*/
		
		Iterator<Students> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
	}
	
	@Test
	public void testAddStudents(){
		Students s = new Students();
		s.setSname("黄贝贝");
		s.setGender("女");
		s.setBirthday(new Date());
		s.setAddress("三水区");
		StudentsDAO stu = new StudentsDaoImpl();
		Assert.assertEquals(true, stu.addStudents(s));
	}
		
}
