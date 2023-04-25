package com.yedam.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.member.mapper.MemberMapper;

public class SampleExe2 {

	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = com.yedam.common.DataSource.getInstance();
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			
			List<Map<String, Object>> list= mapper.memberByDept();
			// [{Adminstration, 1}, {Accounting,2}....{}]
			
			for(Map<String, Object>map : list) {
				Set<String> set = map.keySet();	//keySet: key값만 담아주는거
//				for(String key:set) {
					System.out.println(map.get("DEPARTMENT_NAME")+", "+map.get("CNT"));					
//				}
			}
		}
	}
}
