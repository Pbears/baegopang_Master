package sist.spring.bean;

public class OrderBean {
	private String ordernumber;
	private String memberid;
	private String membername;
	private String membertel;
	private String memberaddress;
	private int price;
	private String orderinfo;
	private String menuname;
	private String amount;
	private String storename;
	private String storeaddress;
	private String storetel;
	private String ordertime;
	private String state;	
	
	
	
	
	public String getOrdernumber() {
		return ordernumber;
	}






	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}






	public String getMemberid() {
		return memberid;
	}






	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}






	public String getMembername() {
		return membername;
	}






	public void setMembername(String membername) {
		this.membername = membername;
	}






	public String getMembertel() {
		return membertel;
	}






	public void setMembertel(String membertel) {
		this.membertel = membertel;
	}






	public String getMemberaddress() {
		return memberaddress;
	}






	public void setMemberaddress(String memberaddress) {
		this.memberaddress = memberaddress;
	}






	public int getPrice() {
		return price;
	}






	public void setPrice(int price) {
		this.price = price;
	}






	public String getOrderinfo() {
		return orderinfo;
	}






	public void setOrderinfo(String orderinfo) {
		this.orderinfo = orderinfo;
	}






	public String getMenuname() {
		return menuname;
	}






	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}






	public String getAmount() {
		return amount;
	}






	public void setAmount(String amount) {
		this.amount = amount;
	}






	public String getStorename() {
		return storename;
	}






	public void setStorename(String storename) {
		this.storename = storename;
	}






	public String getStoreaddress() {
		return storeaddress;
	}






	public void setStoreaddress(String storeaddress) {
		this.storeaddress = storeaddress;
	}






	public String getStoretel() {
		return storetel;
	}






	public void setStoretel(String storetel) {
		this.storetel = storetel;
	}






	public String getOrdertime() {
		return ordertime;
	}






	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}






	public String getState() {
		return state;
	}






	public void setState(String state) {
		this.state = state;
	}






	@Override
	public String toString() {
		return "OrderBean [ordernumber=" + ordernumber + ", memberid=" + memberid + ", membername=" + membername
				+ ", membertel=" + membertel + ", memberaddress=" + memberaddress + ", price=" + price + ", orderinfo="
				+ orderinfo + ", menuname=" + menuname + ", amount=" + amount + ", storename=" + storename
				+ ", storeaddress=" + storeaddress + ", storetel=" + storetel + ", ordertime=" + ordertime + ", state="
				+ state + "]";
	}	
	

}
