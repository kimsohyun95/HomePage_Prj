package com.human.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.human.vo.FreeBoardVO;
import com.human.vo.SearchCriteria;

public interface FreeBoardService {

	//게시글 작성
	public void write(FreeBoardVO freeBoardVo, MultipartHttpServletRequest mpRequest) throws Exception;
	
	//게시글 목록 조회
	public List<FreeBoardVO> fb_list(SearchCriteria searchCri) throws Exception;
		
	//게시물 총 개수
	public int fb_listCount(SearchCriteria searchCri) throws Exception;
	
	//게시글 상세보기
	public FreeBoardVO fb_read(int fb_id) throws Exception;
	
	//게시글 수정
	public void fb_update(FreeBoardVO freeBoardVo, 
						String[] files, 
						String[] fileNames,
						MultipartHttpServletRequest mpRequest) throws Exception;
	
	//게시글 삭제
	public void fb_delete(int fb_id) throws Exception;
	
	//게시글 답글
	public void fb_reply(FreeBoardVO freeBoardVo) throws Exception;
	
	//게시글 답글 모양
	public void fb_replyShape(FreeBoardVO freeBoardVo) throws Exception;
	
	//첨부파일 조회
	public List<Map<String, Object>> fb_selectFileList(int fb_id) throws Exception;
	
	//첨부파일 다운
	public Map<String, Object> fb_selectFileDown(Map<String, Object>map) throws Exception;
	
}
