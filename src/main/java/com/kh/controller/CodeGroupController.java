package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.domain.CodeGroup;
import com.kh.service.CodeGroupService;

@Controller
@RequestMapping("/codegroup")
//관리자 권한을 가진 사용자만 접근 가능 
@PreAuthorize("hasRole('ROLE_ADMIN')") 
public class CodeGroupController {
	@Autowired
	private CodeGroupService service;

	// 코드그룹 등록 페이지
	@GetMapping("/register")
	public String registerForm(Model model) throws Exception {
		CodeGroup codeGroup = new CodeGroup();
		model.addAttribute(codeGroup);
		return "codegroup/register";
	}

	// 코드그룹 등록 처리
	@PostMapping("/register")
	public String register(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception {
		service.register(codeGroup);

		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codegroup/list";
	}

	// 코드그룹 목록 페이지
	@GetMapping("/list")
	public String list(Model model) throws Exception {
		model.addAttribute("list", service.list());
		return "codegroup/list";
	}

	// 코드그룹 상세 페이지
	@GetMapping("/read")
	public String read(CodeGroup codeGroup, Model model) throws Exception {
		model.addAttribute(service.read(codeGroup));
		return "codegroup/read";
	}

	// 코드그룹 수정 페이지
	@GetMapping("/modify")
	public String modifyForm(CodeGroup codeGroup, Model model) throws Exception {
		model.addAttribute(service.read(codeGroup));
		return "codegroup/modify";
	}

	// 코드그룹 수정 처리
	@PostMapping("/modify")
	public String modify(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception {
		service.modify(codeGroup);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codegroup/list"; 
	}

	// 코드그룹 삭제 처리
	@PostMapping("/remove")
	public String remove(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception {
		service.remove(codeGroup);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codegroup/list";
	}
}