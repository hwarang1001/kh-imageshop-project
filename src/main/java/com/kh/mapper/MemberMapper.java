package com.kh.mapper;

import java.util.List;

import com.kh.domain.Member;
import com.kh.domain.MemberAuth;

public interface MemberMapper {
	// 유저 등록 처리
	public void create(Member member) throws Exception;

	// 유저 권한 생성
	public void createAuth(MemberAuth memberAuth) throws Exception;

	// 유저 목록 페이지
	public List<Member> list() throws Exception;

	// 유저 상세 페이지
	public Member read(Member member) throws Exception;

	// 유저 권한 수정
	public void modifyAuth(MemberAuth memberAuth) throws Exception;

	// 유저 수정 처리
	public void update(Member member) throws Exception;

	// 유저 삭제 처리
	public void delete(Member member) throws Exception;

	// 유저 권한 삭제
	public void deleteAuth(Member member) throws Exception;
	
	// 회원테이블 데이터 건수 조회 
	public int countAll() throws Exception; 
	
	// 사용자 아이디 회원 정보 조회 
	public Member readByUserId(String userId);
}