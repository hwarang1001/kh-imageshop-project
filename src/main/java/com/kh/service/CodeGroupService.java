package com.kh.service;

import java.util.List;

import com.kh.domain.CodeGroup;

public interface CodeGroupService {
	// 코드그룹 등록 처리
	public void register(CodeGroup codeGroup) throws Exception;

	// 코드그룹 목록 페이지
	public List<CodeGroup> list() throws Exception;

	// 코드그룹 상세 페이지
	public CodeGroup read(CodeGroup codeGroup) throws Exception;

	// 코드그룹 수정 처리
	public void modify(CodeGroup codeGroup) throws Exception;

	// 코드그룹 삭제 처리
	public void remove(CodeGroup codeGroup) throws Exception;
}
