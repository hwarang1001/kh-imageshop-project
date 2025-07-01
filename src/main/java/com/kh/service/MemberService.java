package com.kh.service;

import java.util.List;

import com.kh.domain.Member;

public interface MemberService {
	// 등록 처리
	public void register(Member member) throws Exception;

	// 목록 페이지
	public List<Member> list() throws Exception;

	// 상세 페이지
	public Member read(Member member) throws Exception;

	// 수정 처리
	public void modify(Member member) throws Exception;

	// 삭제 처리
	public void remove(Member member) throws Exception;
}
