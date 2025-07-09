package com.kh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.common.CodeLabelValue;
import com.kh.common.domain.PageRequest;
import com.kh.common.domain.Pagination;
import com.kh.common.security.domain.CustomUser;
import com.kh.domain.Board;
import com.kh.domain.Member;
import com.kh.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;

	// 게시글 등록 페이지
	@GetMapping("/register")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String registerForm(Model model, Authentication authentication) throws Exception {
		// 로그인한 사용자 정보 획득
		CustomUser customUser = (CustomUser) authentication.getPrincipal();
		Member member = customUser.getMember();

		Board board = new Board();
		// 로그인한 사용자 아이디를 등록 페이지에 표시
		board.setWriter(member.getUserId());

		model.addAttribute(board);
		return "board/register";
	}

	// 게시글 등록 처리
	@PostMapping("/register")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String register(Board board, RedirectAttributes rttr) throws Exception {
		service.register(board);

		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/list";
	}

	// 게시글 목록 페이지
	@GetMapping("/list") 
	public void list(@ModelAttribute("pgrq") PageRequest pageRequest, Model 
	model) throws Exception { 
	// 뷰에 페이징 처리를 한 게시글 목록을 전달
	model.addAttribute("list", service.list(pageRequest)); 
	 
	// 페이징 네비게이션 정보를 뷰에 전달 
	Pagination pagination = new Pagination(); 
	pagination.setPageRequest(pageRequest); 
	// 페이지 네비게이션 정보에 검색 처리된 게시글 건수를 저장 
	pagination.setTotalCount(service.count(pageRequest)); 
	model.addAttribute("pagination", pagination); 
	// 검색 유형의 코드명과 코드값을 정의
	List<CodeLabelValue> searchTypeCodeValueList = new 
	ArrayList<CodeLabelValue>(); 
	searchTypeCodeValueList.add(new CodeLabelValue("n", "---")); 
	searchTypeCodeValueList.add(new CodeLabelValue("t", "Title")); 
	searchTypeCodeValueList.add(new CodeLabelValue("c", "Content")); 
	searchTypeCodeValueList.add(new CodeLabelValue("w", "Writer")); 
	searchTypeCodeValueList.add(new CodeLabelValue("tc", "Title OR Content")); 
	searchTypeCodeValueList.add(new CodeLabelValue("cw", "Content OR Writer")); 
	searchTypeCodeValueList.add(new CodeLabelValue("tcw", "Title OR Content OR Writer")); 
	 
	model.addAttribute("searchTypeCodeValueList", 
	searchTypeCodeValueList); 
	}

	// 게시글 상세 페이지
	@GetMapping("/read")
	public void read(Board board, @ModelAttribute("pgrq") PageRequest pageRequest, Model model) throws Exception {
		// 조회한 게시글 상세 정보를 뷰에 전달
		Board _board = service.read(board);

		model.addAttribute(_board);
	}

	// 게시글 수정 페이지
	@GetMapping("/modify")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public void modifyForm(Board board, @ModelAttribute("pgrq") PageRequest pageRequest, Model model) throws Exception {
		// 조회한 게시글 상세 정보를 뷰에 전달
		Board _board = service.read(board);

		model.addAttribute(_board);
	}

	// 게시글 수정 처리
	@PostMapping("/modify")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public String modify(Board board, PageRequest pageRequest, RedirectAttributes rttr) throws Exception {
		service.modify(board);
		// RedirectAttributes 객체에 일회성 데이터를 지정하여 전달한다.
		rttr.addAttribute("page", pageRequest.getPage());
		rttr.addAttribute("sizePerPage", pageRequest.getSizePerPage());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/board/list";
	}

	// 게시글 삭제 처리
	@PostMapping("/remove")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public String remove(Board board, PageRequest pageRequest, RedirectAttributes rttr) throws Exception {
		service.remove(board);

		// RedirectAttributes 객체에 일회성 데이터를 지정하여 전달한다.
		rttr.addAttribute("page", pageRequest.getPage());
		rttr.addAttribute("sizePerPage", pageRequest.getSizePerPage());
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}
}
