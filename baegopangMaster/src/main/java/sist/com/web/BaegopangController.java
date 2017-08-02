package sist.com.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sist.spring.bean.MasterBean;
import sist.spring.dao.AdminDao;
import sist.spring.dao.MemberDao;

@Controller
@SessionAttributes("master")
public class BaegopangController {
	@Resource(name = "adminDao")
	private AdminDao admin;
	@Resource(name="memberDao")
	private MemberDao member;

	@RequestMapping(value = "index.do")
	public String signIn() {

		return "jsp/login/signIn";

	}

	@RequestMapping(value = "login.do")
	public String login(Model model, @RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "userPw", required = false) String userPw) {

		System.out.println(userId);
		MasterBean bean = member.memberCheck(userId);
		if (userPw.equals(bean.getPw())) {
			model.addAttribute("master", bean);
			return "/jsp/main";
		} else {
			model.addAttribute("check","fail");
			return "jsp/login/signIn";
		}
		
		

	}

}
