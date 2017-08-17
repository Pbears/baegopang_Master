package sist.com.web;


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sist.spring.bean.AgeDataBean;
import sist.spring.bean.BrandDataBean;
import sist.spring.bean.DaySalesDataBean;
import sist.spring.bean.GenderDataBean;
import sist.spring.bean.MasterBean;
import sist.spring.bean.MasterReplyBean;
import sist.spring.bean.MasteraskadminBean;
import sist.spring.bean.MenuBean;
import sist.spring.bean.MonthSalesDataBean;
import sist.spring.bean.NoticeBean;
import sist.spring.bean.OrderBean;
import sist.spring.bean.PointBean;
import sist.spring.bean.ReplyTotalBean;
import sist.spring.bean.StoreBean;
import sist.spring.bean.WeekDayDataBean;
import sist.spring.bean.WeekdaySalesDataBean;
import sist.spring.dao.AdminDao;
import sist.spring.dao.DataDao;
import sist.spring.dao.MasterAskDao;
import sist.spring.dao.MasterReplyDao;
import sist.spring.dao.MemberDao;
import sist.spring.dao.MenuManageDao;
import sist.spring.dao.NoticeDao;
import sist.spring.dao.OrderDao;
import sist.spring.dao.PointDao;

@Controller
@SessionAttributes("master")
public class BaegopangController {
	@Resource(name = "adminDao")
	private AdminDao admin;
	@Resource(name = "memberDao")
	private MemberDao member;
	@Resource(name = "orderDao")
	private OrderDao order;
	@Resource(name = "noticeDao")
	private NoticeDao notice;
	@Resource(name = "pointDao")
	private PointDao point;
	@Resource(name = "dataDao")
	private DataDao data;
	@Resource(name = "menuManageDao")
	private MenuManageDao menu;
	@Resource(name = "masterAskDao")
	private MasterAskDao ask;
	@Resource(name = "masterReplyDao")
	private MasterReplyDao reply;
 
	@RequestMapping(value = "index.do")
	public String signIn() {

		return "jsp/login/signIn";

	}

	@RequestMapping(value = "login.do")
	public String login(Model model, @RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "userPw", required = false) String userPw) {
		System.out.println(userId);
		MasterBean bean = member.memberCheck(userId);
		if (userPw.equals(bean.getPw())) {
			model.addAttribute("master", bean);
			return "redirect:main.do";
		} else {
			model.addAttribute("check", "fail");
			return "jsp/login/signIn";
		}

	}

	@RequestMapping(value = "main.do")
	public String main(Model model, HttpSession session) {
		/*
		 * String id = (String) session.getAttribute("id"); MemberDao dao = new
		 * MemberDao(); MasterBean bean = (MasterBean) session.getAttribute("master");
		 * String storename = bean.getStorename(); NoticeDao ndao = new NoticeDao();
		 * session�� �������� (MasterBean)session.getAttribute("master") �ȿ� ���� �� �־�����ϱ� ���⼭
		 * �̾Ƽ� ������ ~ Ȯ���Ͻø� �� �ּ� ������Ź�帳�ϴ�.
		 * 
		 * session.setAttribute("storename", storename);
		 */
		MasterBean master = (MasterBean) session.getAttribute("master");
		List<NoticeBean> list = notice.noticeSel();
		PointBean brandp = point.mybrandPoint(master.getStorename());
		PointBean gup = point.myguPoint(master.getStorename());
		model.addAttribute("notice", list);
		model.addAttribute("brandp", brandp);
		model.addAttribute("gup", gup);
		return "jsp/main";
	}

	@RequestMapping(value = "notice.do")
	public String notice(Model model, @RequestParam(value = "title") String title) {
		NoticeBean bean = notice.selNoticeOne(title);
		model.addAttribute("noticesel", bean);
		return "jsp/Notice";
	}

	@RequestMapping(value = "noticeList")
	public String noticeAll(Model model, @RequestParam(value = "page", required = false) Integer page)
			throws Exception {

		List<NoticeBean> list = notice.noticeSel();

		model.addAttribute("notice", list);

		HashMap<String, Object> map = new HashMap<String, Object>();
		int pageScale = 10;
		int currentPage = 0;
		int totalRow = notice.getNTotalRows();
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
		// out.print(query+" "+data ); //���Ȯ��
		int currentBlock = currentPage % pageScale == 0 ? (currentPage / pageScale) : (currentPage / pageScale + 1);
		int startPage = 1 + (currentBlock - 1) * pageScale;
		int endPage = pageScale + (currentBlock - 1) * pageScale;
		// out.println(startPage+" "+endPage+ " "+currentBlock+" "+totalPage);
		if (totalPage <= endPage)
			endPage = totalPage;
		map.put("start", start);
		map.put("end", end);

		model.addAttribute("currentBlock", currentBlock);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPage", totalPage);

		return "jsp/NoticeList";
	}

	@RequestMapping(value="menuManager.do")
	public String menuManage(Model model,HttpSession session) {
		MasterBean master=(MasterBean)session.getAttribute("master");
	//	MenuManageDao dao = new MenuManageDao();
		String picture = "img/noimage.jpg";
		List<MenuBean> list = menu.selectMenu(master.getId());
		List<MenuBean> mbslist = menu.myStoreMenu(master.getStorename());
		//List<MenuBean> ismenu = dao.selectMenuOne();
		
		model.addAttribute("selmenu", list);
		model.addAttribute("mybmenu", mbslist);
		return "jsp/menuManager";
	}
	@RequestMapping(value="insertMenuProcess.do")
	public String insertMenuProcess(Model model,HttpSession session,@RequestParam(name="menuname")String menuname) {
		MasterBean master=(MasterBean)session.getAttribute("master");
		HashMap<String,String>map=new HashMap<String,String>();
		map.put("menuname", menuname);
		map.put("storename", master.getStorename());
		menu.insertMenu(map); 
		return "redirect:menuManager.do";
	}
	@RequestMapping(value="deleteProcess.do")
	public String deleteProcess(@RequestParam(name="menuname")String menuname) {
		menu.deleteMenu(menuname);
		return "redirect:menuManager.do";
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

		MasterBean storename = (MasterBean) session.getAttribute("master");

		map.put("storename", storename.getStorename());
		// map.put("storename", "����ġŲ-����1����");

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
		// out.print(query+" "+data ); //���Ȯ��
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

		map2.put("storename", storename.getStorename());
		// map2.put("storename", "����ġŲ-����1����");

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
		// out.print(query+" "+data ); //���Ȯ��
		int currentBlock2 = currentPage2 % pageScale2 == 0 ? (currentPage2 / pageScale2)
				: (currentPage2 / pageScale2 + 1);
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
	@RequestMapping(value="ReplyManage.do")
	public String ReplyManage(Model model,HttpSession session,@RequestParam(value = "page", required = false) String page)throws Exception{
		MasterBean master=(MasterBean)session.getAttribute("master");
 
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> ckmap = new HashMap<String, Object>();
		map.put("storename", master.getStorename());
		map.put("id", master.getId());
		
		int pageScale = 6;

		int currentPage = 0;
		int totalRow = reply.getRTotalRows(master.getStorename());
		try {
			currentPage = Integer.parseInt(page);
		} catch (Exception e) {
			currentPage = 1;
		}
		int totalPage = totalRow % pageScale == 0 ? totalRow / pageScale : totalRow / pageScale + 1;
		if (totalRow == 0)
			totalPage = 1;
		int start = 1 + (currentPage - 1) * pageScale;
		int end = pageScale + (currentPage - 1) * pageScale;
		//out.print(query+"   "+data ); //���Ȯ��
		int currentBlock = currentPage % pageScale == 0 ? (currentPage / pageScale) : (currentPage / pageScale + 1);
		int startPage = 1 + (currentBlock - 1) * pageScale;
		int endPage = pageScale + (currentBlock - 1) * pageScale;
		//   out.println(startPage+" "+endPage+ " "+currentBlock+" "+totalPage);
		if (totalPage <= endPage)
			endPage = totalPage;
		map.put("start", start);
		map.put("end", end);
		
		List<MasterReplyBean>list = reply.selectReply(map);
		System.out.println(list);
		ReplyTotalBean replytotal = null;
		MasterReplyBean bean = null;
		for (int i = 0; i < list.size(); i++) {
			 bean = list.get(i);
		}
	/*	ckmap.put("storename", master.getStorename());
		ckmap.put("id", master.getId());
		ckmap.put("pnum", bean.getPnum());
		int ck=reply.checkReply(ckmap);
		*/
		HashMap<String, Object> omap = new HashMap<String, Object>();
		omap.put("pnum", bean.getPnum());
		omap.put("id", master.getId());
		String sbean= reply.selectOneRep(omap);
		
		session.setAttribute("sbean", bean);
		model.addAttribute("replylist", list);
	//	model.addAttribute("ck", ck);
		model.addAttribute("sbean", sbean);
		return "jsp/ReplyManage";
	}

	@RequestMapping(value = "insertReply")
	public String insertReply(HttpSession session,Model model, @RequestParam(value="contents",required=false)String contents,@RequestParam(value="pnum",required=false)Integer pnum)throws Exception {
		MasterBean master=(MasterBean)session.getAttribute("master");
		String storename = master.getStorename();
		String id = master.getId();
		HashMap<String,Object>map = new HashMap<String,Object>();
		map.put("storename",storename);
		map.put("pnum",pnum);
		map.put("contents",contents);
		map.put("id",id);
		
		reply.insertReply(map);
		reply.updateReply(map);
		reply.getPoint(id);
		return "web/ReplyManage.do";
	}
	@RequestMapping(value="Point.do")
	public String Point() {
		return "jsp/Point";
	}
	@RequestMapping(value="PointAjax.do")
	public String pointAjax(Model model,HttpSession session,@RequestParam(value="flag")int flag)throws Exception {
		MasterBean master=(MasterBean)session.getAttribute("master");
		String storename = master.getStorename();
		List<PointBean>list = null; 
		if(flag==1){
			list = point.brandPoint(storename);
		}else{
			list = point.guPoint(storename);
		}
		PointBean bbean = point.mybrandPoint(storename);
		PointBean gbean = point.myguPoint(storename);
		
		model.addAttribute("list", list);
		model.addAttribute("flag", flag);
		model.addAttribute("gbean", gbean);
		model.addAttribute("bbean", bbean);
		return "jsp/Ajax/PointAjax";
	}
	
	@RequestMapping(value="question.do")
	public String question(Model model,HttpSession session,@RequestParam(value = "page", required = false) Integer page)throws Exception {
		MasterBean master=(MasterBean)session.getAttribute("master");
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("masterid", master.getId());
		int pageScale = 6;
		int currentPage = 0;
		int totalRow = ask.getTotalRows(master.getId());
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
		// out.print(query+" "+data ); //���Ȯ��
		int currentBlock = currentPage % pageScale == 0 ? (currentPage / pageScale) : (currentPage / pageScale + 1);
		int startPage = 1 + (currentBlock - 1) * pageScale;
		int endPage = pageScale + (currentBlock - 1) * pageScale;
		// out.println(startPage+" "+endPage+ " "+currentBlock+" "+totalPage);
		if (totalPage <= endPage)
			endPage = totalPage;
		
		
		map.put("start", start);
		map.put("end", end);
		model.addAttribute("currentBlock", currentBlock);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPage", totalPage);
		
		
		List<MasteraskadminBean> list = ask.selectAsk(map);
		
		model.addAttribute("list", list);
		return "jsp/question";
	}
	@RequestMapping(value = "insertQuestion")
	public String insertQuestion() {
		return "jsp/insertQuestion";
	}
	@RequestMapping(value = "insertAskProcess.do")
	public String insertAsk(Model model,HttpSession session,@RequestParam(value="title" , required = false)String title,@RequestParam(value="contents" ,required = false)String contents) {
		MasterBean master=(MasterBean)session.getAttribute("master");
		MasteraskadminBean bean = new MasteraskadminBean();
		System.out.println(title);
		bean.setTitle(title);
		bean.setMasterinfo(contents);
		bean.setMasterid(master.getId());
		ask.insertAsk(bean);
		return "jsp/insertAskProcess";
	}
	@RequestMapping(value = "selectQuestion")
	public String selectQuestion(Model model,HttpSession session,@RequestParam(name="title")String title) {
		MasteraskadminBean mbean =null;
		mbean = ask.selQuestionOne(title);
		
		model.addAttribute("bean", mbean);
		return "jsp/selectQuestion";
	}
	@RequestMapping(value = "orderupdate.do")
	public String orderUpdate(@RequestParam(value = "flag", required = false) String flag,
			@RequestParam(value = "ordernumber", required = false) String ordernumber,
			@RequestParam(value = "menuname", required = false) String menuname,
			@RequestParam(value = "storename", required = false) String storename,
			@RequestParam(value = "ordertime", required = false) String ordertime,
			@RequestParam(value = "memberid", required = false) String memberid,
			@RequestParam(value = "amount", required = false) String amount,
			@RequestParam(value = "price", required = false) String price) {

		OrderDao dao = order;
		DataDao dao2 = data;

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ordernumber", ordernumber);
		if (flag.equals("check")) {
			map.put("state", "���οϷ�");
			dao.updateOrder(map);
		} else if (flag.equals("del")) {
			map.put("state", "��޿Ϸ�");
			String orderday = ordertime.substring(0, 10);
			String ordermonth = ordertime.substring(0, 7);
			map.put("amount", amount);
			map.put("price", price);
			map.put("menuname", menuname);
			map.put("storename", storename);
			map.put("orderday", orderday);
			map.put("ordermonth", ordermonth);
			map.put("memberid", memberid);

			// ����
			Calendar cal = Calendar.getInstance();

			cal.set(Calendar.YEAR, Integer.parseInt(orderday.substring(0, 4)));
			cal.set(Calendar.MONTH, Integer.parseInt(orderday.substring(5, 7)) - 1);
			cal.set(Calendar.DATE, Integer.parseInt(orderday.substring(8, 10)));

			String[] dayOfWeek = { "��", "��", "ȭ", "��", "��", "��", "��" };
			String weekday = dayOfWeek[cal.get(Calendar.DAY_OF_WEEK) - 1];

			map.put("weekday", weekday);

			dao.updateOrder(map);
			dao2.insertOrderData(map);
			dao.updateMemberPang(map);

		} else if (flag.equals("refuse")) {
			map.put("state", "����");
			dao.updateOrder(map);
		}

		return "redirect:order.do";
	}

	@RequestMapping(value = "genderData.do")
	public String genderData(Model model, HttpSession session) {

		MasterBean master = (MasterBean) session.getAttribute("master");
		String storename = master.getStorename();
		// String storename = "����ġŲ-����1����";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("storename", storename);
		DataDao dao = data;
		List<GenderDataBean> list = dao.selectGenderData(map);
		String man = null;
		String woman = null;

		String json = "[";

		for (int i = 0; i < list.size(); i++) {

			if (man == null) {
				if (((GenderDataBean) list.get(i)).getGender().equals("��")) {
					man = String.valueOf(((GenderDataBean) list.get(i)).getRatiogender());
					json += "{" + "\"type\" : \"����\"," + "\"percent\" : \"" + man + "\"," + "\"color\" : \"#ff9e01\","
							+ "\"subs\" : " + "[";
				}

			}
		}

		int metc = 0;

		for (int i = 0; i < list.size(); i++) {
			GenderDataBean bean = ((GenderDataBean) list.get(i));

			if (bean.getGender().equals("��") && (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3)) {
				json += "{" + "\"type\" : \"" + bean.getMenuname() + "\"," + "\"percent\" : \"" + bean.getRatiomenu()
						+ "\"" + "},";
			} else if (bean.getGender().equals("��") && bean.getRank() != 1 && bean.getRank() != 2
					&& bean.getRank() != 3) {
				metc += bean.getRatiomenu();
			}

		}
		if (metc != 0) {

			json += "{" + "\"type\" : \"��Ÿ\"," + "\"percent\" : \"" + metc + "\"" + "},";
		}

		if (man != null) {
			json = (new String(json)).substring(0, json.lastIndexOf(","));
			json += "]" + "},";
		}

		for (int i = 0; i < list.size(); i++) {

			if (woman == null) {
				if (((GenderDataBean) list.get(i)).getGender().equals("��")) {
					woman = String.valueOf(((GenderDataBean) list.get(i)).getRatiogender());
					json += "{" + "\"type\" : \"����\"," + "\"percent\" : \"" + woman + "\"," + "\"color\" : \"#b0de09\","
							+ "\"subs\" : " + "[";
				}

			}

		}

		int wetc = 0;

		for (int i = 0; i < list.size(); i++) {
			GenderDataBean bean = ((GenderDataBean) list.get(i));

			if (bean.getGender().equals("��") && (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3)) {
				json += "{" + "\"type\" : \"" + bean.getMenuname() + "\"," + "\"percent\" : \"" + bean.getRatiomenu()
						+ "\"" + "},";
			} else if (bean.getGender().equals("��") && bean.getRank() != 1 && bean.getRank() != 2
					&& bean.getRank() != 3) {
				wetc += bean.getRatiomenu();
			}

		}
		if (metc != 0) {

			json += "{" + "\"type\" : \"��Ÿ\"," + "\"percent\" : \"" + wetc + "\"" + "},";
		}

		json = (new String(json)).substring(0, json.lastIndexOf(","));

		if (man != null && woman != null) {

			json += "]" + "}";
		}

		json += "]";

		model.addAttribute("json", json);
		System.out.println(json);

		/*
		 * PrintWriter pw = response.getWriter(); pw = response.getWriter();
		 * pw.print(json); pw.flush(); pw.close();
		 */
		return "jsp/chartdata/genderData";
	}

	@RequestMapping(value = "bestInfo.do")
	public String bestInfo() {

		return "jsp/bestInfo";
	}

	@RequestMapping(value = "ageData.do")
	public String ageBestChart(Model model, HttpSession session) {

		MasterBean master = (MasterBean) session.getAttribute("master");
		String storename = master.getStorename();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("storename", storename);
		// map.put("storename", "����ġŲ-����1����");
		DataDao dao = data;
		List<AgeDataBean> list = dao.selectAgeData(map);
		// out.print(list);

		JSONObject[] ageObj2 = { new JSONObject(), new JSONObject(), new JSONObject(), new JSONObject(),
				new JSONObject() };

		JSONArray[] subArray2 = { new JSONArray(), new JSONArray(), new JSONArray(), new JSONArray(), new JSONArray() };
		JSONArray totalArray2 = new JSONArray();
		String[] ageRatio2 = new String[5];
		int[] metc2 = new int[5];

		for (int i = 0; i < list.size(); i++) {
			AgeDataBean bean = list.get(i);

			if (bean.getAge() == 10) {

				if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
					metc2[0] += bean.getRatiomenu();
				}

			} else if (bean.getAge() == 20) {

				if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
					metc2[1] += bean.getRatiomenu();
				}

			} else if (bean.getAge() == 30) {

				if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
					metc2[2] += bean.getRatiomenu();
				}

			} else if (bean.getAge() == 40) {

				if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
					metc2[3] += bean.getRatiomenu();
				}

			} else if (bean.getAge() == 50) {

				if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
					metc2[4] += bean.getRatiomenu();
				}

			}
		}

		for (int i = 0; metc2.length < i; i++) {
			if (metc2[i] != 0) {

				JSONObject subObj = new JSONObject();
				subObj.put("type", "��Ÿ");
				subObj.put("percent", metc2[i]);
				subArray2[i].add(subObj);

			}
		}

		for (int i = 0; i < list.size(); i++) {
			AgeDataBean bean = list.get(i);

			if (bean.getAge() == 10) {
				if (ageRatio2[0] == null) {
					ageRatio2[0] = String.valueOf(bean.getRatioage());
					ageObj2[0].put("type", "10��");
					ageObj2[0].put("percent", ageRatio2[0]);
					ageObj2[0].put("color", "#FF0F00");
					ageObj2[0].put("subs", subArray2[0]);
					totalArray2.add(ageObj2[0]);

				}
				if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
					JSONObject subObj = new JSONObject();
					subObj.put("type", bean.getMenuname());
					subObj.put("percent", bean.getRatiomenu());
					subArray2[0].add(subObj);
				}

			} else if (bean.getAge() == 20) {
				if (ageRatio2[1] == null) {
					ageRatio2[1] = String.valueOf(bean.getRatioage());
					ageObj2[1].put("type", "20��");
					ageObj2[1].put("percent", ageRatio2[1]);
					ageObj2[1].put("color", "#FF9E01");
					ageObj2[1].put("subs", subArray2[1]);
					totalArray2.add(ageObj2[1]);

				}
				if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
					JSONObject subObj = new JSONObject();
					subObj.put("type", bean.getMenuname());
					subObj.put("percent", bean.getRatiomenu());
					subArray2[1].add(subObj);
				}
			} else if (bean.getAge() == 30) {
				if (ageRatio2[2] == null) {
					ageRatio2[2] = String.valueOf(bean.getRatioage());
					ageObj2[2].put("type", "30��");
					ageObj2[2].put("percent", ageRatio2[2]);
					ageObj2[2].put("color", "#F8FF01");
					ageObj2[2].put("subs", subArray2[2]);
					totalArray2.add(ageObj2[2]);

				}
				if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
					JSONObject subObj = new JSONObject();
					subObj.put("type", bean.getMenuname());
					subObj.put("percent", bean.getRatiomenu());
					subArray2[2].add(subObj);
				}

			} else if (bean.getAge() == 40) {
				if (ageRatio2[3] == null) {
					ageRatio2[3] = String.valueOf(bean.getRatioage());
					ageObj2[3].put("type", "40��");
					ageObj2[3].put("percent", ageRatio2[3]);
					ageObj2[3].put("color", "#04D215");
					ageObj2[3].put("subs", subArray2[3]);
					totalArray2.add(ageObj2[3]);

				}
				if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
					JSONObject subObj = new JSONObject();
					subObj.put("type", bean.getMenuname());
					subObj.put("percent", bean.getRatiomenu());
					subArray2[3].add(subObj);
				}

			} else if (bean.getAge() == 50) {
				if (ageRatio2[4] == null) {
					ageRatio2[4] = String.valueOf(bean.getRatioage());
					ageObj2[4].put("type", "50��");
					ageObj2[4].put("percent", ageRatio2[4]);
					ageObj2[4].put("color", "#0D8ECF");
					ageObj2[4].put("subs", subArray2[4]);
					totalArray2.add(ageObj2[4]);

				}
				if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
					JSONObject subObj = new JSONObject();
					subObj.put("type", bean.getMenuname());
					subObj.put("percent", bean.getRatiomenu());
					subArray2[4].add(subObj);
				}

			}

		}

		// ������������ ��ϵ�����

		model.addAttribute("json", totalArray2);

		/*
		 * PrintWriter pw = response.getWriter(); pw.print(totalArray2); pw.flush();
		 * pw.close();
		 */

		return "jsp/chartdata/ageData";
	}

	@RequestMapping(value = "weekDayData.do")
	public String dayBestChart(Model model, HttpSession session) {

		MasterBean master = (MasterBean) session.getAttribute("master");
		String storename = master.getStorename();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("storename", storename);
		// map.put("storename", "����ġŲ-����1����");
		DataDao dao = data;
		List<WeekDayDataBean> list = dao.selectWeekDayData(map);
		// out.print(list);

		JSONObject[] dayObj1 = { new JSONObject(), new JSONObject(), new JSONObject(), new JSONObject(),
				new JSONObject(), new JSONObject(), new JSONObject() };

		JSONArray[] subArray1 = { new JSONArray(), new JSONArray(), new JSONArray(), new JSONArray(), new JSONArray(),
				new JSONArray(), new JSONArray() };
		JSONArray totalArray1 = new JSONArray();
		String[] dayRatio1 = new String[7];
		int[] metc1 = new int[7];

		for (int i = 0; i < list.size(); i++) {
			WeekDayDataBean bean = list.get(i);

			if (bean.getWeekday().equals("��")) {

				if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
					metc1[0] += bean.getRatiomenu();
				}

			} else if (bean.getWeekday().equals("ȭ")) {

				if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
					metc1[1] += bean.getRatiomenu();
				}

			} else if (bean.getWeekday().equals("��")) {

				if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
					metc1[2] += bean.getRatiomenu();
				}

			} else if (bean.getWeekday().equals("��")) {

				if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
					metc1[3] += bean.getRatiomenu();
				}

			} else if (bean.getWeekday().equals("��")) {

				if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
					metc1[4] += bean.getRatiomenu();
				}

			} else if (bean.getWeekday().equals("��")) {

				if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
					metc1[5] += bean.getRatiomenu();
				}

			} else if (bean.getWeekday().equals("��")) {

				if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
					metc1[6] += bean.getRatiomenu();
				}

			}
		}
		for (int i = 0; metc1.length < i; i++) {
			if (metc1[i] != 0) {

				JSONObject subObj = new JSONObject();
				subObj.put("type", "��Ÿ");
				subObj.put("percent", metc1[i]);
				subArray1[i].add(subObj);

			}
		}

		for (int i = 0; i < list.size(); i++) {
			WeekDayDataBean bean = list.get(i);

			if (bean.getWeekday().equals("��")) {
				if (dayRatio1[0] == null) {
					dayRatio1[0] = String.valueOf(bean.getRatioday());
					dayObj1[0].put("type", "������");
					dayObj1[0].put("percent", dayRatio1[0]);
					dayObj1[0].put("color", "#FF0F00");
					dayObj1[0].put("subs", subArray1[0]);
					totalArray1.add(dayObj1[0]);

				}
				if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
					JSONObject subObj = new JSONObject();
					subObj.put("type", bean.getMenuname());
					subObj.put("percent", bean.getRatiomenu());
					subArray1[0].add(subObj);
				}

			} else if (bean.getWeekday().equals("ȭ")) {
				if (dayRatio1[1] == null) {
					dayRatio1[1] = String.valueOf(bean.getRatioday());
					dayObj1[1].put("type", "ȭ����");
					dayObj1[1].put("percent", dayRatio1[1]);
					dayObj1[1].put("color", "#FF9E01");
					dayObj1[1].put("subs", subArray1[1]);
					totalArray1.add(dayObj1[1]);

				}
				if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
					JSONObject subObj = new JSONObject();
					subObj.put("type", bean.getMenuname());
					subObj.put("percent", bean.getRatiomenu());
					subArray1[1].add(subObj);
				}

			} else if (bean.getWeekday().equals("��")) {
				if (dayRatio1[2] == null) {
					dayRatio1[2] = String.valueOf(bean.getRatioday());
					dayObj1[2].put("type", "������");
					dayObj1[2].put("percent", dayRatio1[2]);
					dayObj1[2].put("color", "#F8FF01");
					dayObj1[2].put("subs", subArray1[2]);
					totalArray1.add(dayObj1[2]);

				}
				if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
					JSONObject subObj = new JSONObject();
					subObj.put("type", bean.getMenuname());
					subObj.put("percent", bean.getRatiomenu());
					subArray1[2].add(subObj);
				}

			} else if (bean.getWeekday().equals("��")) {
				if (dayRatio1[3] == null) {
					dayRatio1[3] = String.valueOf(bean.getRatioday());
					dayObj1[3].put("type", "�����");
					dayObj1[3].put("percent", dayRatio1[3]);
					dayObj1[3].put("color", "#04D215");
					dayObj1[3].put("subs", subArray1[3]);
					totalArray1.add(dayObj1[3]);

				}
				if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
					JSONObject subObj = new JSONObject();
					subObj.put("type", bean.getMenuname());
					subObj.put("percent", bean.getRatiomenu());
					subArray1[3].add(subObj);
				}

			} else if (bean.getWeekday().equals("��")) {
				if (dayRatio1[4] == null) {
					dayRatio1[4] = String.valueOf(bean.getRatioday());
					dayObj1[4].put("type", "�ݿ���");
					dayObj1[4].put("percent", dayRatio1[4]);
					dayObj1[4].put("color", "#0D8ECF");
					dayObj1[4].put("subs", subArray1[4]);
					totalArray1.add(dayObj1[4]);

				}
				if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
					JSONObject subObj = new JSONObject();
					subObj.put("type", bean.getMenuname());
					subObj.put("percent", bean.getRatiomenu());
					subArray1[4].add(subObj);
				}

			} else if (bean.getWeekday().equals("��")) {
				if (dayRatio1[5] == null) {
					dayRatio1[5] = String.valueOf(bean.getRatioday());
					dayObj1[5].put("type", "�����");
					dayObj1[5].put("percent", dayRatio1[5]);
					dayObj1[5].put("color", "#2A0CD0");
					dayObj1[5].put("subs", subArray1[5]);
					totalArray1.add(dayObj1[5]);

				}
				if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
					JSONObject subObj = new JSONObject();
					subObj.put("type", bean.getMenuname());
					subObj.put("percent", bean.getRatiomenu());
					subArray1[5].add(subObj);
				}

			} else if (bean.getWeekday().equals("��")) {
				if (dayRatio1[6] == null) {
					dayRatio1[6] = String.valueOf(bean.getRatioday());
					dayObj1[6].put("type", "�Ͽ���");
					dayObj1[6].put("percent", dayRatio1[6]);
					dayObj1[6].put("color", "#8A0CCF");
					dayObj1[6].put("subs", subArray1[6]);
					totalArray1.add(dayObj1[6]);

				}
				if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
					JSONObject subObj = new JSONObject();
					subObj.put("type", bean.getMenuname());
					subObj.put("percent", bean.getRatiomenu());
					subArray1[6].add(subObj);
				}

			}

		}

		model.addAttribute("json", totalArray1);

		// ������������ ��ϵ�����
		/*
		 * out.print(totalArray1);
		 * 
		 * PrintWriter pw = response.getWriter(); pw.print(totalArray1); pw.flush();
		 * pw.close();
		 */

		return "jsp/chartdata/weekDayData";
	}

	@RequestMapping(value = "brandBestData.do")
	public String brandBestChart(Model model, HttpSession session) {

		MasterBean master = (MasterBean) session.getAttribute("master");
		String storename = master.getStorename();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("storename", storename);
		// map.put("storename", "����ġŲ-����1����");

		DataDao dao = data;
		List<BrandDataBean> list = dao.selectBrandData(map);
		// out.print(list);

		JSONArray totalArray = new JSONArray();

		int metc = 0;

		for (int i = 0; i < list.size(); i++) {
			BrandDataBean bean = list.get(i);

			if (bean.getRank() != 1 && bean.getRank() != 2 && bean.getRank() != 3) {
				metc += bean.getRatiomenu();
			}

		}

		if (metc != 0) {
			JSONObject obj = new JSONObject();
			obj.put("country", "��Ÿ");
			obj.put("litres", metc);
			totalArray.add(obj);

		}

		for (int i = 0; i < list.size(); i++) {
			BrandDataBean bean = list.get(i);

			if (bean.getRank() == 1 || bean.getRank() == 2 || bean.getRank() == 3) {
				JSONObject obj = new JSONObject();
				obj.put("country", bean.getMenuname());
				obj.put("litres", bean.getRatiomenu());
				totalArray.add(obj);
			}

		}

		// ������������ ��ϵ�����
		model.addAttribute("json", totalArray);
		/*
		 * out.print(totalArray.toString());
		 * 
		 * PrintWriter pw = response.getWriter(); pw.print(totalArray.toString());
		 * pw.flush(); pw.close();
		 */

		System.out.println();

		return "jsp/chartdata/brandBestData";
	}

	@RequestMapping(value = "salesInfo.do")
	public String salesInfo() {

		return "jsp/salesInfo";
	}

	@RequestMapping(value = "daySalesData.do")
	public String daySalesChart(Model model, HttpSession session) {

		MasterBean master = (MasterBean) session.getAttribute("master");
		String storename = master.getStorename();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("storename", storename);
		// map.put("storename", "����ġŲ-����1����");

		DataDao dao = data;
		List<DaySalesDataBean> list = dao.selectDaySalesData(map);
		String[] color = { "#FF0F00", "#FF9E01", "#F8FF01", "#04D215", "#0D8ECF", "#2A0CD0", "#8A0CCF" };
		// out.print(list);

		JSONArray totalArray = new JSONArray();

		for (int i = 0; i < list.size(); i++) {
			DaySalesDataBean bean = list.get(i);

			JSONObject jsonObj = new JSONObject();
			jsonObj.put("date", bean.getOrderday());
			jsonObj.put("value", bean.getDaysale());
			// System.out.println(jsonObj.toString());
			totalArray.add(jsonObj);
		}

		model.addAttribute("json", totalArray);

		// ������������ ��ϵ�����
		// out.print(totalArray.toString());

		/*
		 * PrintWriter pw = response.getWriter(); pw.print(totalArray.toString());
		 * pw.flush(); pw.close();
		 */

		return "jsp/chartdata/daySalesData";
	}

	@RequestMapping(value = "weekdaySalesData.do")
	public String weekdaySalesChart(Model model, HttpSession session) {

		MasterBean master = (MasterBean) session.getAttribute("master");
		String storename = master.getStorename();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("storename", storename);
		// map.put("storename", "����«��-������");

		DataDao dao = data;
		List<WeekdaySalesDataBean> list = dao.selectWeekDaySalesData(map);
		// out.print(list);

		JSONArray totalArray = new JSONArray();
		String[] color = { "#FF0F00", "#FF9E01", "#F8FF01", "#04D215", "#0D8ECF", "#2A0CD0", "#8A0CCF" };

		for (int i = 0; i < list.size(); i++) {
			WeekdaySalesDataBean bean = list.get(i);

			JSONObject jsonObj = new JSONObject();
			jsonObj.put("country", bean.getWeekday() + "����");
			jsonObj.put("visits", bean.getAvgsales());
			jsonObj.put("color", color[i]);
			totalArray.add(jsonObj);
		}

		model.addAttribute("json", totalArray);

		// ������������ ��ϵ�����
		/*
		 * out.print(totalArray.toString());
		 * 
		 * PrintWriter pw = response.getWriter(); pw.print(totalArray.toString());
		 * pw.flush(); pw.close();
		 */

		return "jsp/chartdata/weekdaySalesData";
	}

	@RequestMapping(value = "monthSalesData.do")
	public String monthSalesChart(Model model, HttpSession session) {

		MasterBean master = (MasterBean) session.getAttribute("master");
		String storename = master.getStorename();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("storename", storename);
		// map.put("storename", "����«��-������");

		DataDao dao = data;
		List<MonthSalesDataBean> list = dao.selectMonthSalesData(map);
		// out.print(list);

		JSONArray totalArray = new JSONArray();
		String[] color = { "#FF0F00", "#FF6600", "#FF9E01", "#FCD202", "#F8FF01", "#B0DE09", "#04D215", "#0D8ECF",
				"#0D52D1", "#2A0CD0", "#8A0CCF", "#CD0D74" };

		for (int i = 0; i < list.size(); i++) {
			MonthSalesDataBean bean = list.get(i);

			JSONObject jsonObj = new JSONObject();
			jsonObj.put("country", bean.getMonth() + "��");
			jsonObj.put("visits", bean.getAvgsales());
			jsonObj.put("color", color[i]);
			totalArray.add(jsonObj);
		}

		// ������������ ��ϵ�����
		/*
		 * out.print(totalArray.toString());
		 * 
		 * PrintWriter pw = response.getWriter(); pw.print(totalArray.toString());
		 * pw.close(); pw.flush();
		 */

		model.addAttribute("json", totalArray);

		return "jsp/chartdata/monthSalesData";
	}

	@RequestMapping(value = "masterInfo.do")
	public String masterInfo(Model model, HttpSession session) {

		MasterBean storename = (MasterBean) session.getAttribute("master");

		OrderDao dao = order;

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("storename", storename.getStorename());

		MasterBean mbean = dao.selectMaster(map);
		StoreBean sbean = dao.selectStore(map);

		model.addAttribute("mbean", mbean);
		model.addAttribute("sbean", sbean);

		return "jsp/masterInfo";
	}

	@RequestMapping(value = "masterUpdate.do")
	public String masterUpdate(HttpSession session, @RequestParam(value = "pw", required = false) String pw,
			@RequestParam(value = "maddress1", required = false) String maddress1,
			@RequestParam(value = "maddress2", required = false) String maddress2,
			@RequestParam(value = "mtel1", required = false) String mtel1,
			@RequestParam(value = "mtel2", required = false) String mtel2,
			@RequestParam(value = "mtel3", required = false) String mtel3,
			@RequestParam(value = "stel1", required = false) String stel1,
			@RequestParam(value = "stel2", required = false) String stel2,
			@RequestParam(value = "stel3", required = false) String stel3,
			@RequestParam(value = "hours", required = false) String hours,
			@RequestParam(value = "info", required = false) String info) {

		MasterBean mbean = (MasterBean) session.getAttribute("master");
		String storename = mbean.getStorename();

		String maddress = maddress1.concat(" " + maddress2);
		String mtel = mtel1.concat("-" + mtel2).concat("-" + mtel3);

		String stel = stel1.concat("-" + stel2).concat("-" + stel3);

		// master
		HashMap<String, Object> store = new HashMap<String, Object>();

		store.put("hours", hours);
		store.put("info", info);
		store.put("storename", storename);
		store.put("tel", stel);

		// store

		HashMap<String, Object> master = new HashMap<String, Object>();

		master.put("pw", pw);
		master.put("address", maddress);
		master.put("tel", mtel);
		master.put("storename", storename);

		OrderDao dao = order;

		dao.updateMaster(master);
		dao.updateStore(store);

		return "redirect:masterInfo.do";
	}

	@RequestMapping(value = "masterDelete.do")
	public String masterDelete(@RequestParam(value = "pw", required = false) String pw,
			@RequestParam(value = "maddress1", required = false) String maddress1,
			@RequestParam(value = "maddress2", required = false) String maddress2,
			@RequestParam(value = "mtel1", required = false) String mtel1,
			@RequestParam(value = "mtel2", required = false) String mtel2,
			@RequestParam(value = "mtel3", required = false) String mtel3,
			@RequestParam(value = "stel1", required = false) String stel1,
			@RequestParam(value = "stel2", required = false) String stel2,
			@RequestParam(value = "stel3", required = false) String stel3,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "storename", required = false) String storename,
			@RequestParam(value = "birth", required = false) String birth) {
		
		String maddress=maddress1.concat(" "+maddress2);
		String mtel=mtel1.concat("-"+mtel2).concat("-"+mtel3);
		String stel=stel1.concat("-"+stel2).concat("-"+stel3);

		MasterBean mbean=new MasterBean();

		mbean.setPw(pw);
		mbean.setAddress(maddress);
		mbean.setTel(mtel);
		mbean.setName(name);
		mbean.setId(id);
		mbean.setStorename(storename);
		mbean.setBirth(birth);


		OrderDao dao=order;
		dao.dropMaster(mbean);

		return "redirect:index.do";
	}

}
