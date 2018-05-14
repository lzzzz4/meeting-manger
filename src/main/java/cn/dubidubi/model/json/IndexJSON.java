package cn.dubidubi.model.json;

import java.io.Serializable;

//初始化首页状态栏
public class IndexJSON implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private Integer userId;
	private String account;
	private String URL;

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
