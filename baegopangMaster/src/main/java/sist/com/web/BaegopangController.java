package sist.com.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sist.spring.bean.AdminBean;
import sist.spring.dao.AdminDao;

@Controller
public class BaegopangController {
	@Resource(name = "adminDao")
	private AdminDao admin;
	
	@RequestMapping(value = "signIn.do")
	public String signInAction(Model model) {
		List<AdminBean> list = admin.selectAllAdmin();
		model.addAttribute("list", list);
		return "jsp/view/main";
	}
}
