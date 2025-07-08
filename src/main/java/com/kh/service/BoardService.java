package com.kh.service;

import java.util.List;

import com.kh.domain.Board;

public interface BoardService {
	// 게시글 등록 처리
	public void register(Board board) throws Exception;

	// 게시글 목록 페이지
	public List<Board> list() throws Exception;

	// 게시글 상세 페이지
	public Board read(Board board) throws Exception;

	// 게시글 수정 처리
	public void modify(Board board) throws Exception;

	// 게시글 삭제 처리
	public void remove(Board board) throws Exception;
}
