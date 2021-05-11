package com.human.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.human.dao.FreeCommentDAO;
import com.human.vo.FreeCommentVO;

@Service
public class FreeCommentServiceImpl implements FreeCommentService {

	@Inject
	private FreeCommentDAO dao;
	
	//댓글 조회
	@Override
	public List<FreeCommentVO> fb_readComment(int fb_id) throws Exception {
		
		return dao.fb_readComment(fb_id);
	}

	//댓글 작성
	@Override
	public void fb_writeComment(FreeCommentVO vo) throws Exception {
		dao.fb_writeComment(vo);
		
	}

	//댓글 수정
	@Override
	public void fb_updateComment(FreeCommentVO vo) throws Exception {
		dao.fb_updateComment(vo);
		
	}

	//댓글 삭제
	@Override
	public void fb_deleteComment(FreeCommentVO vo) throws Exception {
		dao.fb_deleteComment(vo);
		
	}

	//선택 댓글 조회
	@Override
	public FreeCommentVO fb_selectComment(int fc_id) throws Exception {
		return dao.fb_selectComment(fc_id);
	}

}
