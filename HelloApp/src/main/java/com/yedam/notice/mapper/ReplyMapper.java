package com.yedam.notice.mapper;

import java.util.List;

import com.yedam.notice.domain.ReplyVO;

public interface ReplyMapper {
	public List<ReplyVO> replyList(int noticeId);
	//댓글등록
	public int insertReply(ReplyVO vo);
	//댓글삭제
	public int deleteReply(int replyId);
}
