package com.human.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.human.vo.FreeBoardVO;
import com.human.vo.SearchCriteria;

@Repository
public class FreeBoardDAOImpl implements FreeBoardDAO {

	@Inject
	private SqlSession sqlSession;
	
	//게시글 작성
	@Override
	public void write(FreeBoardVO freeBoardVo) throws Exception {
		sqlSession.insert("freeBoardMapper.fb_insert", freeBoardVo);
	}

	//게시글 목록 조회
	@Override
	public List<FreeBoardVO> fb_list(SearchCriteria searchCri) throws Exception {
		
		return sqlSession.selectList("freeBoardMapper.fb_listPage", searchCri);
	}
	
	//게시물 총 개수
	public int fb_listCount(SearchCriteria searchCri) throws Exception{
		
		return sqlSession.selectOne("freeBoardMapper.fb_listCount", searchCri);
	}
	
	//게시글 상세보기
	@Override
	public FreeBoardVO fb_read(int fb_id) throws Exception {
		
		return sqlSession.selectOne("freeBoardMapper.fb_read", fb_id);
	}

	//게시글 수정
	@Override
	public void fb_update(FreeBoardVO freeBoardVo) throws Exception {
		
		sqlSession.update("freeBoardMapper.fb_update", freeBoardVo);	
	}

	//게시글 삭제
	@Override
	public void fb_delete(int fb_id) throws Exception {
		
		sqlSession.delete("freeBoardMapper.fb_delete", fb_id);		
	}

	//게시글 답글
	@Override
	public void fb_reply(FreeBoardVO freeBoardVo) throws Exception {
		
		sqlSession.insert("freeBoardMapper.fb_reply", freeBoardVo);
		
	}

	//게시글 답글 모양
	@Override
	public void fb_replyShape(FreeBoardVO freeBoardVo) throws Exception {

		sqlSession.update("freeBoardMapper.fb_replyShape", freeBoardVo);
		
	}

	//게시글 조회수
	@Override
	public void fb_upHit(int fb_id) throws Exception {
		
		sqlSession.update("freeBoardMapper.fb_upHit", fb_id);
		
	}

	//첨부파일 업로드
	@Override
	public void fb_insertFile(Map<String, Object> map) throws Exception {
		
		sqlSession.insert("freeBoardMapper.fb_insertFile", map);
		
	}
	
	//첨부파일 조회
	@Override
	public List<Map<String, Object>> fb_selectFileList(int fb_id) throws Exception{
		
		return sqlSession.selectList("freeBoardMapper.fb_selectFileList", fb_id);
	}

	//첨부파일 다운
	@Override
	public Map<String, Object> fb_selectFileDown(Map<String, Object> map) throws Exception{
		
		return sqlSession.selectOne("freeBoardMapper.fb_selectFileDown", map);
	}

	//첨부파일 수정,삭제
	@Override
	public void fb_updateFile(Map<String, Object> map) throws Exception{
		
		sqlSession.update("freeBoardMapper.fb_updateFile", map);
	}

}
