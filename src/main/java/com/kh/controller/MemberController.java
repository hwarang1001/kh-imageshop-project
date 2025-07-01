package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	// 회원 목록 페이지, 관리자 권한을 가진 사용자만 접근
	@GetMapping("/list")
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
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

	// 수정 페이지
	@GetMapping("/modify")
	public void modifyForm(Member member, Model model) throws Exception {
		// 직업코드 목록을 조회하여 뷰에 전달
		String groupCode = "A00";
		List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);

		model.addAttribute("jobList", jobList);
		model.addAttribute(service.read(member));
	}

	// 수정 처리
	@PostMapping("/modify")
	public String modify(Member member, RedirectAttributes rttr) throws Exception {
		service.modify(member);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/user/list";
	}

	// 삭제 처리
	// 회원 삭제 처리, 관리자 권한을 가진 사용자만 접근
	@PostMapping("/remove")
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	public String remove(Member member, RedirectAttributes rttr) throws Exception {
		service.remove(member);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/user/list";
	}

	// 최초 관리자 등록 화면요청
	@GetMapping("/setup")
	public String setupAdminForm(Member member, Model model) throws Exception {
		// 회원 테이블 데이터 건수 체크
		if (service.countAll() == 0) {
			return "user/setup";
		}
		// 최초 관리자를 생성할 수 없으면 실패 페이지 이동
		return "user/setupFailure";
	}

	// 최초 관리자 등록
	@PostMapping("/setup")
	public String setupAdmin(Member member, RedirectAttributes rttr) throws Exception {
		// 회원 테이블 데이터 건수 체크
		if (service.countAll() == 0) {
			String inputPassword = member.getUserPw();
			member.setUserPw(passwordEncoder.encode(inputPassword));
			member.setJob("00");
			service.setupAdmin(member);
			rttr.addFlashAttribute("userName", member.getUserName());
			return "redirect:/user/registerSuccess";
		}
		// 최초 관리자를 생성할 수 없으면 실패 페이지 이동
		return "redirect:/user/setupFailure";
	}
}
