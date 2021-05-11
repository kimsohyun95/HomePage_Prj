package com.human.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.human.vo.FreeCommentVO;

@Repository
public class FreeCommentDAOImpl implements FreeCommentDAO {
	
	@Inject 
	SqlSession sqlSession;

	//댓글 조회
	@Override
	public List<FreeCommentVO> fb_readComment(int fb_id) throws Exception {
		
		return sqlSession.selectList("freeCommentMapper.fb_readComment", fb_id);
	}

	//댓글 작성
	@Override
	public void fb_writeComment(FreeCommentVO vo) throws Exception {
		sqlSession.insert("freeCommentMapper.fb_writeComment", vo);
		
	}

	//댓글 수정
	@Override
	public void fb_updateComment(FreeCommentVO vo) throws Exception {
		sqlSession.update("freeCommentMapper.fb_updateComment", vo);
		
	}

	//댓글 삭제
	@Override
	public void fb_deleteComment(FreeCommentVO vo) throws Exception {
		sqlSession.delete("freeCommentMapper.fb_deleteComment", vo);
		
	}

	//선택 댓글 조회
	@Override
	public FreeCommentVO fb_selectComment(int fc_id) throws Exception {
		return sqlSession.selectOne("freeCommentMapper.fb_selectComment", fc_id);
	}

}
