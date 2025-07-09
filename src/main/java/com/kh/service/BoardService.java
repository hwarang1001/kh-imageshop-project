package com.kh.service;

import java.util.List;

import com.kh.common.domain.PageRequest;
import com.kh.domain.Board;

public interface BoardService {
	// 게시글 등록 처리
	public void register(Board board) throws Exception;

	// 게시글 목록 페이지
	public List<Board> list(PageRequest pageRequest) throws Exception;

	// 게시글 상세 페이지
	public Board read(Board board) throws Exception;

	// 게시글 수정 처리
	public void modify(Board board) throws Exception;

	// 게시글 삭제 처리
	public void remove(Board board) throws Exception;
	
	// 게시글 전체 건수 반환
	public int count(PageRequest pageRequest) throws Exception;
}
