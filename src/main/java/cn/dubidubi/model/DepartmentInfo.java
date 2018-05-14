package cn.dubidubi.model;

import java.io.Serializable;

/**
 * 
* @author linzj
* @Description: 部门信息实体类
* @ClassName: DepartmentInfo  
* @date 2018年4月16日 下午4:17:51
 */
public class DepartmentInfo implements Serializable {
	private Integer id;

	private String departmentName;

	private String departmentSuperior;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName == null ? null : departmentName.trim();
	}

	public String getDepartmentSuperior() {
		return departmentSuperior;
	}

	public void setDepartmentSuperior(String departmentSuperior) {
		this.departmentSuperior = departmentSuperior == null ? null : departmentSuperior.trim();
	}
}