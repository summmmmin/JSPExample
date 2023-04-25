package com.yedam.notice.mapper;

import java.util.List;

import com.yedam.notice.domain.ReplyVO;

public interface ReplyMapper {
	public List<ReplyVO> replyList(int noticeId);
	//댓글등록
	public int insertReply(ReplyVO vo);
	//댓글삭제
	public int deleteReply(int replyId);
	//댓글수정
	public int updateReply(ReplyVO vo);
	//public int updateReply(@Param("replyId") int replyId, @Param("reply") String reply) //parameter값 2개 받고싶을때 / parameter type은 지정안함
	public ReplyVO searchReply(int replyId);
}
