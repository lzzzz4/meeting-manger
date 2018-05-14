package cn.dubidubi.model;

import java.io.Serializable;

/**
 * 
* @author linzj
* @Description: 授权信息界面三表连接返回的对象
* @ClassName: ShowRole  
* @date 2018年4月21日 下午9:33:58
 */
public class ShowRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String name;
	private String account;
	private Integer id; // sys_user_role的id

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
