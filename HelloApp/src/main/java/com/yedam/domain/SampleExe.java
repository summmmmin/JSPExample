package com.yedam.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SampleExe {
	public static void main(String[] args) {
		
		SqlSessionFactory sqlSessionFactory = com.yedam.common.DataSource.getInstance();
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
//			  Employee emp = session.selectOne("com.yedam.common.NoticeMapper.getEmp", 101);
			
//			  session.delete("com.yedam.common.NoticeMapper.delEmp",274);
			  Employee emp = new Employee();
			  emp.setEmployeeId(301);
			  emp.setLastName("Hong");
			  emp.setEmail("honemail");
			  emp.setJobId("IT_PROG");
			  session.insert("com.yedam.common.NoticeMapper.addEmp", emp);
			  List<Employee> empl = session.selectList("com.yedam.common.NoticeMapper.empList");
			  System.out.println(empl);
			}
	}
}
