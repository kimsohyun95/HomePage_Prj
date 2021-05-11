package com.human.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.human.dao.MemberDAO;
import com.human.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDAO dao;
	
	//회원가입
	@Override
	public void register(MemberVO vo) throws Exception {
		
		dao.register(vo);
		
	}
	
	//로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception{
		
		return dao.login(vo);
		
	}

	//회원정보 수정
	@Override
	public void memberUpdate(MemberVO vo) throws Exception{
		
		dao.memberUpdate(vo);
	}
	
	//회원탈퇴
	@Override
	public void memberDelete(MemberVO vo) throws Exception{
		
		dao.memberDelete(vo);
	}
}
