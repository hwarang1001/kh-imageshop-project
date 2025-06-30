package com.kh.mapper;

import java.util.List;

import com.kh.domain.CodeDetail;

public interface CodeDetailMapper {
	// 코드 디테일 등록 처리
	public void create(CodeDetail codeDetail) throws Exception;

	// 그룹코드 정렬 순서의 최대값
	public Integer getMaxSortSeq(CodeDetail codeDetail) throws Exception;

	// 코드 디테일 목록 페이지
	public List<CodeDetail> list() throws Exception;

	// 코드 디테일 상세 페이지(수정 페이지)
	public CodeDetail read(CodeDetail codeDetail) throws Exception;

	// 코드 디테일 수정 처리
	public void update(CodeDetail codeDetail) throws Exception;
	
	// 코드 디테일 삭제 처리 
	public void delete(CodeDetail codeDetail) throws Exception;
}