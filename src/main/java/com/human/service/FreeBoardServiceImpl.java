package com.human.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.human.dao.FreeBoardDAO;
import com.human.util.FreeBoardFileUtils;
import com.human.vo.FreeBoardVO;
import com.human.vo.SearchCriteria;

@Service
public class FreeBoardServiceImpl implements FreeBoardService {

	@Resource(name="freeBoardFileUtils")
	private FreeBoardFileUtils freeBoardFileUtils;
	
	@Inject
	private FreeBoardDAO dao;
	
	//게시글 작성
	@Override
	public void write(FreeBoardVO freeBoardVo, MultipartHttpServletRequest mpRequest) throws Exception {
		dao.write(freeBoardVo);
		
		List<Map<String,Object>> list = freeBoardFileUtils.parseInsertFileInfo(freeBoardVo, mpRequest);
		int size = list.size();
		for(int i=0; i<size; i++) {
			dao.fb_insertFile(list.get(i));
		}

	}
	
	//게시글 목록 조회
	@Override
	public List<FreeBoardVO> fb_list(SearchCriteria searchCri) throws Exception {
		
		return dao.fb_list(searchCri);
	
	}
	
	//게시물 총 개수
	public int fb_listCount(SearchCriteria searchCri) throws Exception{
		
		return dao.fb_listCount(searchCri);
			
	}
	
	//게시글 상세보기
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public FreeBoardVO fb_read(int fb_id) throws Exception{
			
			dao.fb_upHit(fb_id);
			
		return dao.fb_read(fb_id);
	}

	//게시글 수정, 첨부파일 수정삭제
	@Override
	public void fb_update(FreeBoardVO freeBoardVo,
						String [] files,
						String [] fileNames,
						MultipartHttpServletRequest mpRequest) throws Exception {
		//게시물 업데이트
		dao.fb_update(freeBoardVo);
		
		//첨부파일 업데이트
		List<Map<String,Object>> list = freeBoardFileUtils.parseUpdateFileInfo(freeBoardVo, files,fileNames,mpRequest);
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("is_new").equals('y')) {
				dao.fb_insertFile(tempMap);	
			}else {
				dao.fb_updateFile(tempMap);
			}
			
		}
	}

	//게시글 삭제
	@Override
	public void fb_delete(int fb_id) throws Exception {
		
		dao.fb_delete(fb_id);
	}

	//게시글 답글
	@Override
	public void fb_reply(FreeBoardVO freeBoardVo) throws Exception {
		
		dao.fb_reply(freeBoardVo);
		
	}

	//게시글 답글 모양
	@Override
	public void fb_replyShape(FreeBoardVO freeBoardVo) throws Exception {
		
		dao.fb_replyShape(freeBoardVo);
		
	}
	
	//첨부파일 조회
	@Override
	public List<Map<String, Object>> fb_selectFileList(int fb_id) throws Exception{
		
		return dao.fb_selectFileList(fb_id);
	}
	
	//첨부파일 다운
	@Override
	public Map<String, Object> fb_selectFileDown(Map<String, Object>map) throws Exception{
		
		return dao.fb_selectFileDown(map);
	}

}
