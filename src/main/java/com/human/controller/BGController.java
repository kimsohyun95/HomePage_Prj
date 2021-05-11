 package com.human.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.human.service.FreeBoardService;
import com.human.service.FreeCommentService;
import com.human.vo.FreeBoardVO;
import com.human.vo.FreeCommentVO;
import com.human.vo.MemberVO;
import com.human.vo.PageMaker;
import com.human.vo.SearchCriteria;


@Controller
public class BGController {
	
	private static final Logger logger = LoggerFactory.getLogger(BGController.class);

	@Inject
	FreeBoardService service;
	
	@Inject
	FreeCommentService fCommentService;
	
	// 메인페이지
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main(Model model) {
		System.out.println("main()");
		logger.info("main");
		
		return "main/main";
	}
	// 자유게시판---------------------------------------------------------

	// 자유게시판 목록 조회
	@RequestMapping(value="/free_board/fb_list", method=RequestMethod.GET)
	public String list(Model model, @ModelAttribute("searchCri") SearchCriteria searchCri) throws Exception{
		System.out.println("list()");
		
		logger.info("fb_list");

		model.addAttribute("list",service.fb_list(searchCri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(searchCri);
		pageMaker.setTotalCount(service.fb_listCount(searchCri));
		
		model.addAttribute("pageMaker", pageMaker);
		System.out.println(pageMaker.getCurrentPageNum());
		
		return "free_board/fb_list";
	}

	//자유게시판 글 작성 화면
	@RequestMapping(value="/free_board/fb_writeView", method = RequestMethod.GET)
	public void fb_writeView() throws Exception{
		System.out.println("fb_writeView()");
		logger.info("fb_writeView");
	}
	//자유게시판 글 작성
	@RequestMapping(value="/free_board/fb_write", method = RequestMethod.POST)
	public String fb_wirte(FreeBoardVO freeBoardVo, MultipartHttpServletRequest mpRequest) throws Exception{
		System.out.println("fb_write()");
		logger.info("fb_write");
		
		service.write(freeBoardVo, mpRequest);
		
		return "redirect:/free_board/fb_list";
		//글쓰고 나서 메인페이지말고 list페이지로 변경
	}

	// 자유게시판 게시글 상세보기
	@RequestMapping(value = "/free_board/fb_read", method=RequestMethod.GET)
	public String fb_read(FreeBoardVO freeBoardVo, @ModelAttribute("searchCri") SearchCriteria searchCri, Model model)throws Exception {
		System.out.println("fb_read()");
		
		logger.info("fb_read");
		
		model.addAttribute("fb_read",service.fb_read(freeBoardVo.getFb_id()));
		model.addAttribute("searchCri", searchCri);
		
		List<FreeCommentVO> freeCommentRead = fCommentService.fb_readComment(freeBoardVo.getFb_id());
		model.addAttribute("fb_commentRead", freeCommentRead);
		
		System.out.println("fb_fileList()");
		List<Map<String, Object>> fb_fileList = service.fb_selectFileList(freeBoardVo.getFb_id());
		model.addAttribute("fb_file", fb_fileList);
		
		return "free_board/fb_read";
	}

	// 자유게시판 수정뷰
	@RequestMapping(value="/free_board/fb_modifyView", method=RequestMethod.GET)
	public String fb_modifyView(FreeBoardVO freeBoardVo, @ModelAttribute("searchCri") SearchCriteria searchCri, Model model) throws Exception{
		System.out.println("fb_modifyView()");
		
		logger.info("fb_modifyView");
		
		model.addAttribute("fb_update", service.fb_read(freeBoardVo.getFb_id()));
		model.addAttribute("searchCri", searchCri);
		
		List<Map<String, Object>> fb_fileList = service.fb_selectFileList(freeBoardVo.getFb_id());
		model.addAttribute("fb_file", fb_fileList);
		
		return "free_board/fb_modifyView";
	}

	// 자유게시판 수정, 첨부파일 수정삭제
	@RequestMapping(value="/free_board/fb_modify", method=RequestMethod.POST)
	public String fb_modify(FreeBoardVO freeBoardVo, @ModelAttribute("searchCri") SearchCriteria searchCri, RedirectAttributes rttr,
							@RequestParam(value="fileNoDel[]") String [] files,
							@RequestParam(value="fileNameDel[]") String [] fileNames,
							MultipartHttpServletRequest mpRequest) throws Exception {
		System.out.println("fb_modify()");
		
		logger.info("fb_modify");
		
		service.fb_update(freeBoardVo, files, fileNames, mpRequest);
		
		rttr.addAttribute("page", searchCri.getPage());
		rttr.addAttribute("perPageNum", searchCri.getPerPageNum());
		rttr.addAttribute("searchType", searchCri.getSearchType());
		rttr.addAttribute("keyword", searchCri.getKeyword());

		return "redirect:/free_board/fb_list";
	}

	// 자유게시판 삭제
	@RequestMapping(value="/free_board/fb_delete", method=RequestMethod.POST)
	public String fb_delete(FreeBoardVO freeBoardVo, @ModelAttribute("searchCri") SearchCriteria searchCri, RedirectAttributes rttr) throws Exception{
		System.out.println("fb_delete()");

		logger.info("fb_delete");

		service.fb_delete(freeBoardVo.getFb_id());
		
		rttr.addAttribute("page", searchCri.getPage());
		rttr.addAttribute("perPageNum", searchCri.getPerPageNum());
		rttr.addAttribute("searchType", searchCri.getSearchType());
		rttr.addAttribute("keyword", searchCri.getKeyword());
		
		return "redirect:/free_board/fb_list";
	}

	// 자유게시판 답글뷰
	@RequestMapping(value="/free_board/fb_replyView", method=RequestMethod.POST)
	public String fb_replyView(FreeBoardVO freeBoardVo, Model model) throws Exception {
		System.out.println("fb_replyView()");
		
		logger.info("fb_replyView");
		
		model.addAttribute("fb_reply", service.fb_read(freeBoardVo.getFb_id()));


		return "free_board/fb_replyView";
	}

	// 자유게시판 답글
	@RequestMapping(value="/free_board/fb_reply")
	public String fb_reply(FreeBoardVO freeBoardVo, Model model) throws Exception {
		System.out.println("fb_reply()");
		
		logger.info("fb_reply");
		
		service.fb_replyShape(freeBoardVo);
		service.fb_reply(freeBoardVo);

		return "redirect:/free_board/fb_list";
	}
	
	//댓글 작성
	@RequestMapping(value="/free_board/fb_writeComment", method=RequestMethod.POST)
	public String fb_writeComment(FreeCommentVO vo, SearchCriteria scri, RedirectAttributes rttr)throws Exception{
		//vo - 댓글 작성위한 데이터
		//scri - fb_read에 있는 page,perPageNum,searchType,keyword값 받아오기
		//rttr - redirect했을 때 값들을 가지고 이동
		System.out.println("fb_writeComment()");
		
		logger.info("fb_writeComment");
		
		fCommentService.fb_writeComment(vo);
		
		rttr.addAttribute("fb_id", vo.getFb_id());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/free_board/fb_read";
	}
	
	//댓글 수정 GET
	@RequestMapping(value="/free_board/fb_updateCommentView", method=RequestMethod.GET)
	public String fb_updateCommentView(FreeCommentVO vo, SearchCriteria scri, Model model) throws Exception {
		System.out.println("fb_updateCommentView()");
		
		logger.info("fb_updateCommentView");
		
		model.addAttribute("fb_updateComment", fCommentService.fb_selectComment(vo.getFc_id()));
		model.addAttribute("scri",scri);
		
		return "free_board/fb_updateCommentView";
	}
	
	//댓글 수정 POST
	@RequestMapping(value="/free_board/fb_updateComment", method=RequestMethod.POST)
	public String fb_updateComment(FreeCommentVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		System.out.println("fb_updateComment()");
		
		logger.info("fb_updateComment");
		
		fCommentService.fb_updateComment(vo);
		
		rttr.addAttribute("fb_id", vo.getFb_id());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/free_board/fb_read";
	}
	
	//댓글 삭제 GET
	@RequestMapping(value="/free_board/fb_deleteCommentView", method=RequestMethod.GET)
	public String fb_deleteCommentView(FreeCommentVO vo, SearchCriteria scri, Model model) throws Exception{
		System.out.println("fb_deleteCommentView()");
		
		logger.info("fb_deleteCommentView");
		
		model.addAttribute("fb_deleteComment", fCommentService.fb_selectComment(vo.getFc_id()));
		model.addAttribute("scri", scri);
		
		return "free_board/fb_deleteCommentView";
	}
	
	//댓글 삭제 POST
	@RequestMapping(value="/free_board/fb_deleteComment", method=RequestMethod.POST)
	public String fb_deleteComment(FreeCommentVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		System.out.println("fb_delectComment()");
		
		logger.info("fb_deleteComment");
		
		fCommentService.fb_deleteComment(vo);
		
		rttr.addAttribute("fb_id", vo.getFb_id());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/free_board/fb_read";
	}
	
	//첨부파일 다운
	@RequestMapping(value="/free_board/fb_fileDown")
	public void fb_fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception{
		System.out.println("fb_fileDown()");
		
		Map<String, Object> resultMap = service.fb_selectFileDown(map);
		System.out.println("-----------11111111------------");
		System.out.println(resultMap);
		String storedFileName = (String) resultMap.get("FB_STORED_NAME");
		String originalFileName = (String) resultMap.get("FB_ORG_NAME");
		
		System.out.println("----00000------");
		System.out.println(storedFileName);
		System.out.println(originalFileName);
		
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("D:\\spring-tool\\file\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
