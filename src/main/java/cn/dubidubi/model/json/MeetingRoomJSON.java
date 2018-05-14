package cn.dubidubi.model.json;

import java.io.Serializable;
import java.util.List;

import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.MeetingRoomDO;

public class MeetingRoomJSON implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<MeetingRoomDO> list;
	private Integer total;
	private Integer currentPage;
	private Integer totalPage;
	private Integer currentDepartmentId;
	private List<DepartmentInfo> departmentInfos;

	public List<DepartmentInfo> getDepartmentInfos() {
		return departmentInfos;
	}

	public void setDepartmentInfos(List<DepartmentInfo> departmentInfos) {
		this.departmentInfos = departmentInfos;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<MeetingRoomDO> getList() {
		return list;
	}

	public void setList(List<MeetingRoomDO> list) {
		this.list = list;
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

	public Integer getCurrentDepartmentId() {
		return currentDepartmentId;
	}

	public void setCurrentDepartmentId(Integer currentDepartmentId) {
		this.currentDepartmentId = currentDepartmentId;
	};

}
