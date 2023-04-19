package com.yedam.domain;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.mapper.NoticeMapper;

public class SampleExe2 {

	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = com.yedam.common.DataSource.getInstance();
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
//			Employee emp = new Employee();
//			emp.setEmployeeId(310);
//			emp.setLastName("Hong");
//			emp.setEmail("honmail");
//			emp.setJobId("IT_PROG");
//			mapper.addEmp(emp);
//			session.commit();   //또는 sqlSessionFactory.openSession(true) db커밋
//			Employee emp1 = mapper.getEmp(310);
//			System.out.println(emp1);
			NoticeVO vo = new NoticeVO();
			vo.setNoticeId(5);
//			vo.setNoticeWriter("user04");
			vo.setNoticeTitle("5 글");
			vo.setNoticeSubject("5번쨰 내용");
//			mapper.insertNotice(vo);
			mapper.updateNotice(vo);
//			mapper.deleteNotice(4);
//			System.out.println(mapper.searchNotice(5));

			for(NoticeVO vol : mapper.noticeList()) {
				System.out.println(vol);			
			}
		}
	}
}
