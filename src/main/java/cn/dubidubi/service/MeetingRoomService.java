package cn.dubidubi.service;

import java.util.List;

import javax.management.Query;

import com.github.pagehelper.PageInfo;

import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.MeetingRoomDO;
import cn.dubidubi.model.dto.MeetingRoomDTO;
import cn.dubidubi.model.dto.QueryDTO;

/**
 * 
* @author linzj
* @Description: 会议室信息管理业务
* @ClassName: MeetingRoomService  
* @date 2018年4月19日 下午4:44:30
 */
public interface MeetingRoomService {
	public PageInfo<MeetingRoomDO> listAllMeetRoomByQuery(QueryDTO dto);

	/**
	* @Title: saveOne  
	* @Description: 保存一个会议室信息
	* @param @param meetingRoomDTO
	* @return void
	* @author linzj
	* @date 2018年4月19日 下午5:49:49 
	* @throws
	 */
	public void saveOne(MeetingRoomDTO meetingRoomDTO);

	boolean isExistRoom(String roomName);

	public boolean updateOne(MeetingRoomDTO meetingRoomDTO);

	public void delOne(Integer id);

	public Integer getCount(String room);

	/**
	 * 
	* @Title: updateDepartmentByDepartmentId  
	* @Description: 部门表更新时,该表也要同步更新
	* @param 
	* @return void
	* @author linzj
	* @date 2018年4月20日 上午7:32:40 
	* @throws
	 */
	public void updateDepartmentByDepartmentId(DepartmentInfo departmentInfo);
}
