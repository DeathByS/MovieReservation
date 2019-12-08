package movie_reserv.vo;

public class MovieCustomerVO {
	private String id;
	private String name;
	private String addr;
	private String phone;
	private String pwd;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void setAll( String id, String name, String addr, String phone, String pwd ) {
		setId(id);
		setName(name);
		setAddr(addr);
		setPhone(phone);
		setPwd(pwd);
	}
	
	

}
