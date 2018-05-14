package cn.dubidubi.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @author linzj
* @Description: 会议室的实体类
* @ClassName: MeetingRoomDO  
* @date 2018年4月19日 下午4:44:07
 */
public class MeetingRoomDO implements Serializable {
	private Integer id;

	private String room;

	private Integer maxPeople;

	private String openDate;

	private String endDate;

	private String openTime;

	private String endTime;

	private String department;

	private String picUrl;

	private Integer departmentId;
	private static final long serialVersionUID = 1L;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room == null ? null : room.trim();
	}

	public Integer getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(Integer maxPeople) {
		this.maxPeople = maxPeople;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate == null ? null : openDate.trim();
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate == null ? null : endDate.trim();
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department == null ? null : department.trim();
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl == null ? null : picUrl.trim();
	}
}