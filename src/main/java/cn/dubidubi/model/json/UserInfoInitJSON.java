package cn.dubidubi.model.json;

import java.io.Serializable;
import java.util.List;

import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.DependUserInfo;

/**
 * 
* @author linzj
* @ClassName: UserInfoInitJSON  
* @Description: 用户管理界面的初始化json
* @date 2018年4月16日 下午2:10:17
 */
public class UserInfoInitJSON implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<DependUserInfo> dependUserInfos;
	private Integer total;
	private List<DepartmentInfo> departmentInfos;
	private Integer currentPage;
	private Integer totalPage;
	private Integer currentDepartmentId;
	
	public Integer getCurrentDepartmentId() {
		return currentDepartmentId;
	}

	public void setCurrentDepartmentId(Integer currentDepartmentId) {
		this.currentDepartmentId = currentDepartmentId;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<DependUserInfo> getDependUserInfos() {
		return dependUserInfos;
	}

	public void setDependUserInfos(List<DependUserInfo> dependUserInfos) {
		this.dependUserInfos = dependUserInfos;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<DepartmentInfo> getDepartmentInfos() {
		return departmentInfos;
	}

	public void setDepartmentInfos(List<DepartmentInfo> departmentInfos) {
		this.departmentInfos = departmentInfos;
	}

}
