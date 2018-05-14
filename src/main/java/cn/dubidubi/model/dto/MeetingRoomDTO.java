package cn.dubidubi.model.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
* @author linzj
* @Description: 会议室管理的dto类
* @ClassName: MeetingRoomDTO  
* @date 2018年4月19日 下午4:43:39
 */
public class MeetingRoomDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String[] openDays;
	private String room;
	private String max;
	private String start_time;
	private String close_time;
	private Integer department;
	private String department_name;
	private String open_date; // join后的字符串即opendate
	private String endDate;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOpen_date() {
		return open_date;
	}

	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String[] getOpenDays() {
		return openDays;
	}

	public void setOpenDays(String[] openDays) {
		this.openDays = openDays;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getClose_time() {
		return close_time;
	}

	public void setClose_time(String close_time) {
		this.close_time = close_time;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

}
