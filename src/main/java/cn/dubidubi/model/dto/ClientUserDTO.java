package cn.dubidubi.model.dto;

import java.io.Serializable;

/**
* @author linzj
* @ClassName: ClientUserDTO  
* @Description: 上传的用户信息类
* @date 2018年4月15日 下午8:36:16
 */
public class ClientUserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String account;
	private String password;
	private String name;
	private String department;
	private Integer departmentId;
	private String mail;
	private String oldAccount;

	@Override
	public String toString() {
		return "ClientUserDTO [account=" + account + ", password=" + password + ", name=" + name + ", department="
				+ department + ", departmentId=" + departmentId + "]";
	}

	public String getOldAccount() {
		return oldAccount;
	}

	public void setOldAccount(String oldAccount) {
		this.oldAccount = oldAccount;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
