package com.human.dao;

import java.util.List;

import com.human.vo.FreeCommentVO;

public interface FreeCommentDAO {

	//댓글 조회
	public List<FreeCommentVO> fb_readComment(int fb_id) throws Exception;
	
	//댓글 작성
	public void fb_writeComment(FreeCommentVO vo) throws Exception;
	
	//댓글 수정
	public void fb_updateComment(FreeCommentVO vo) throws Exception;
	
	//댓글 삭제
	public void fb_deleteComment(FreeCommentVO vo) throws Exception;
	
	//선택 댓글 조회
	public FreeCommentVO fb_selectComment(int fc_id) throws Exception;
	
}
