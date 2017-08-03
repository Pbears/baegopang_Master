package sist.com.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sist.spring.bean.MasterBean;
import sist.spring.bean.OrderBean;
import sist.spring.dao.AdminDao;
import sist.spring.dao.MemberDao;
import sist.spring.dao.OrderDao;

@Controller
@SessionAttributes("master")
public class BaegopangController {
	@Resource(name = "adminDao")
	private AdminDao admin;
	@Resource(name = "memberDao")
	private MemberDao member;
	@Resource(name = "orderDao")
	private OrderDao order;

	@RequestMapping(value = "index.do")
	public String signIn() {

		return "jsp/login/signIn";

	}

	@RequestMapping(value = "login.do")
	public String login(Model model, @RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "userPw", required = false) String userPw) {

		MasterBean bean = member.memberCheck(userId);
		if (userPw.equals(bean.getPw())) {
			model.addAttribute("master", bean);
			return "/jsp/main";
		} else {
			model.addAttribute("check", "fail");
			return "jsp/login/signIn";
		}

	}
	

	@RequestMapping(value = "order.do")
	public String order(Model model, @RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "data", required = false) String data,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "query2", required = false) String query2,
			@RequestParam(value = "data2", required = false) String data2,
			@RequestParam(value = "page2", required = false) Integer page2, HttpSession session) throws Exception {

		// master order1
		List<OrderBean> list = null;

		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//MasterBean storename = (MasterBean) session.getAttribute("master");

		//map.put("storename", storename.getStorename());
		 map.put("storename", "굽네치킨-역삼1동점");

		int pageScale = 10;
		map.put("Q", query);
		map.put("D", data);
		int currentPage = 0;
		int totalRow = order.getTotalRow(map);
		try {
			currentPage = page;
		} catch (Exception e) {
			currentPage = 1;
		}
		int totalPage = totalRow % pageScale == 0 ? totalRow / pageScale : totalRow / pageScale + 1;
		if (totalRow == 0)
			totalPage = 1;
		int start = 1 + (currentPage - 1) * pageScale;
		int end = pageScale + (currentPage - 1) * pageScale;
		// out.print(query+" "+data ); //출력확인
		int currentBlock = currentPage % pageScale == 0 ? (currentPage / pageScale) : (currentPage / pageScale + 1);
		int startPage = 1 + (currentBlock - 1) * pageScale;
		int endPage = pageScale + (currentBlock - 1) * pageScale;
		// out.println(startPage+" "+endPage+ " "+currentBlock+" "+totalPage);
		if (totalPage <= endPage)
			endPage = totalPage;
		map.put("start", start);
		map.put("end", end);

		if (query != null && data != null) {
			map.put("query", query);
			map.put("data", data);
			list = order.selectOrder(map);
		} else {
			list = order.selectOrder(map);
		}

		// masterorder2

				
		List<OrderBean> list2 = null;

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		

		//map2.put("storename", storename.getStorename());
		 map2.put("storename", "굽네치킨-역삼1동점");

		int pageScale2 = 10;
		map2.put("Q2", query2);
		map2.put("D2", data2);
		int currentPage2 = 0;
		int totalRow2 = order.getTotalRowCom(map);
		try {
			currentPage2 = page2;
		} catch (Exception e) {
			currentPage2 = 1;
		}
		int totalPage2 = totalRow2 % pageScale2 == 0 ? totalRow2 / pageScale2 : totalRow2 / pageScale2 + 1;
		if (totalRow2 == 0)
			totalPage2 = 1;
		int start2 = 1 + (currentPage2 - 1) * pageScale2;
		int end2 = pageScale2 + (currentPage2 - 1) * pageScale2;
		// out.print(query+" "+data ); //출력확인
		int currentBlock2 = currentPage2 % pageScale2 == 0 ? (currentPage2 / pageScale2) : (currentPage2 / pageScale2 + 1);
		int startPage2 = 1 + (currentBlock2 - 1) * pageScale2;
		int endPage2 = pageScale2 + (currentBlock2 - 1) * pageScale2;
		// out.println(startPage+" "+endPage+ " "+currentBlock+" "+totalPage);
		if (totalPage2 <= endPage2)
			endPage2 = totalPage2;
		map2.put("start2", start2);
		map2.put("end2", end2);

		if (query2 != null && data2 != null) {
			map2.put("query2", query2);
			map2.put("data2", data2);
			list2 = order.selectOrderCom(map2);
		} else {
			list2 = order.selectOrderCom(map2);
		}		
		
		
		
		
		
		

		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("currentBlock2", currentBlock2);
		model.addAttribute("currentBlock", currentBlock);
		model.addAttribute("currentPage2", currentPage2);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("startPage2", startPage2);
		model.addAttribute("query", query);
		model.addAttribute("query2", query2);
		model.addAttribute("data", data);
		model.addAttribute("data2", data2);
		model.addAttribute("endPage", endPage);
		model.addAttribute("endPage2", endPage2);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalPage2", totalPage2);

		return "jsp/masterOrder";
	}

}
