package cn.dubidubi.dao;

import java.util.List;

import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.MeetingRoomDO;
import cn.dubidubi.model.dto.MeetingRoomDTO;
import cn.dubidubi.model.dto.QueryDTO;

/**
 * 
* @author linzj
* @Description: 会议室数据库mapper
* @ClassName: MeetingRoomDOMapper  
* @date 2018年4月19日 下午5:42:50
 */
public interface MeetingRoomDOMapper {
	MeetingRoomDO selectByPrimaryKey(Integer id);

	List<MeetingRoomDO> listAllMeetingRoomByQuery(QueryDTO dto);

	void saveOne(MeetingRoomDTO roomDTO);

	Integer getIdByName(String name);

	void updateOne(MeetingRoomDTO dto);

	void delOneById(Integer id);

	Integer countByRoom(String room);

	void updateDepartmentByDepartmentId(DepartmentInfo departmentInfo);
}