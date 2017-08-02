package sist.spring.bean;

public class MasterReplyBean {
	private int no;
	private String storename;
	private String ordernumber;
	private String id;
	private String contents;
	private String regdate;
	private int ref;
	private int pnum;
	private int reply;
	private int RM;
	private String state;

	
	public int getRM() {
		return RM;
	}

	public void setRM(int rM) {
		RM = rM;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getStoreName() {
		return storename;
	}

	public void setStoreName(String storeName) {
		this.storename = storeName;
	}

	public String getOrderNumber() {
		return ordernumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.ordernumber = orderNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDate() {
		return regdate;
	}

	public void setRegDate(String regDate) {
		this.regdate = regDate;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getReply() {
		return reply;
	}

	public void setReply(int reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "MasterReplyBean [no=" + no + ", storeName=" + storename + ", orderNumber=" + ordernumber + ", id=" + id
				+ ", contents=" + contents + ", regDate=" + regdate + ", ref=" + ref + ", pnum=" + pnum + ", reply="
				+ reply + ", RM=" + RM + ", state=" + state + "]";
	}
}