package com.human.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.human.vo.FreeBoardVO;

@Component("freeBoardFileUtils")
public class FreeBoardFileUtils {
	private static final String filePath="D:\\spring-tool\\file\\";

	public List<Map<String, Object>> parseInsertFileInfo(FreeBoardVO freeBoardVo, MultipartHttpServletRequest mpRequest) throws Exception{
		
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;
		
		int fb_id = freeBoardVo.getFb_id();
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				listMap.put("fb_id",fb_id);
				listMap.put("fb_org_name", originalFileName);
				listMap.put("fb_stored_name", storedFileName);
				listMap.put("fb_file_size", multipartFile.getSize());
				list.add(listMap);
			}
		}
		
		return list;
		
	}
	
	public List<Map<String,Object>> parseUpdateFileInfo(FreeBoardVO freeBoardVo, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception{
		
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;
		int fb_id=freeBoardVo.getFb_id();
		while(iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty()==false) {
				//새로운 첨부파일이 등록되었을 때
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				multipartFile.transferTo(new File(filePath + storedFileName));
				listMap = new HashMap<String, Object>();
				listMap.put("is_new", "y");
				listMap.put("fb_id", fb_id);
				listMap.put("fb_org_name", originalFileName);
				listMap.put("fb_stored_name", storedFileName);
				listMap.put("fb_file_size", multipartFile.getSize());
				list.add(listMap);
			}
		}
		if(files != null && fileNames != null) {
			for(int i=0; i<fileNames.length; i++) {
				listMap = new HashMap<String, Object>();
				listMap.put("is_new", "n");
				listMap.put("fb_file_no", files[i]);
				list.add(listMap);
			}
		}
		return list;
	}

	private String getRandomString() {
		String random = UUID.randomUUID().toString().replaceAll("-", "");
		return random;
	}
}
