package cn.dubidubi.model.json;

import java.io.Serializable;
import java.util.List;

import cn.dubidubi.model.base.SystemRole;

/** 
* @author linzj
* @Description: 角色授权json对象
* @ClassName: SystemRoleJson  
* @date 2018年4月21日 下午7:18:34    
*/
public class SystemRoleJson implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<SystemRole> list;
	private Integer code;

	public List<SystemRole> getList() {
		return list;
	}

	public void setList(List<SystemRole> list) {
		this.list = list;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
