package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.common.CodeLabelValue;
import com.kh.domain.Member;
import com.kh.service.CodeService;
import com.kh.service.MemberService;

@Controller
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private MemberService service;

	@Autowired
	private CodeService codeService;

	// 스프링 시큐리티의 비밀번호 암호처리기 
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 등록 페이지 
	@GetMapping("/register")
	public void registerForm(Member member, Model model) throws Exception {
		// 직업코드 목록을 조회하여 뷰에 전달 
		String groupCode = "A00";
		List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);
		model.addAttribute("jobList", jobList);

	}

	// 등록 처리 
	@PostMapping("/register")
	public String register(@Validated Member member, BindingResult result, Model model, RedirectAttributes rttr)
			throws Exception {
		if (result.hasErrors()) {
			// 직업코드 목록을 조회하여 뷰에 전달 
			String groupCode = "A00";
			List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);

			model.addAttribute("jobList", jobList);

			return "user/register";
		}
		// 비밀번호 암호화 
		String inputPassword = member.getUserPw();
		member.setUserPw(passwordEncoder.encode(inputPassword));

		service.register(member);
		rttr.addFlashAttribute("userName", member.getUserName());

		return "redirect:/user/registerSuccess";
	}

	// 등록 성공 페이지 
	@GetMapping("/registerSuccess")
	public void registerSuccess(Model model) throws Exception {

	}

	// 목록 페이지 
	@GetMapping("/list")
	public void list(Model model) throws Exception {
		model.addAttribute("list", service.list());
	}

	// 상세 페이지 
	@GetMapping("/read")
	public void read(Member member, Model model) throws Exception {
		// 직업코드 목록을 조회하여 뷰에 전달 
		String groupCode = "A00";
		List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);

		model.addAttribute("jobList", jobList);
		model.addAttribute(service.read(member));
	}

	//수정 페이지 
	@GetMapping("/modify")
	public void modifyForm(Member member, Model model) throws Exception {
		//직업코드 목록을 조회하여 뷰에 전달 
		String groupCode = "A00";
		List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);

		model.addAttribute("jobList", jobList);
		model.addAttribute(service.read(member));
	}

	//수정 처리 
	@PostMapping("/modify")
	public String modify(Member member, RedirectAttributes rttr) throws Exception {
		service.modify(member);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/user/list";
	}

	//삭제 처리 
	@PostMapping("/remove")
	public String remove(Member member, RedirectAttributes rttr) throws Exception {
		service.remove(member);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/user/list";
	}
}
