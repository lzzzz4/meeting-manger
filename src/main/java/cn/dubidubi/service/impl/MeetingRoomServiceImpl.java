package cn.dubidubi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.dubidubi.dao.MeetingRoomDOMapper;
import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.MeetingRoomDO;
import cn.dubidubi.model.dto.MeetingRoomDTO;
import cn.dubidubi.model.dto.QueryDTO;
import cn.dubidubi.service.MeetingRoomService;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {

	@Autowired
	MeetingRoomDOMapper meetingRoomDOMapper;

	@Override
	public PageInfo<MeetingRoomDO> listAllMeetRoomByQuery(QueryDTO dto) {
		PageHelper.startPage(dto.getPageNum(), 12);
		List<MeetingRoomDO> list = meetingRoomDOMapper.listAllMeetingRoomByQuery(dto);
		PageInfo<MeetingRoomDO> info = new PageInfo<>(list);
		return info;
	}

	@Override
	public void saveOne(MeetingRoomDTO meetingRoomDTO) {
		meetingRoomDOMapper.saveOne(meetingRoomDTO);
	}

	@Override
	public boolean isExistRoom(String roomName) {
		Integer id = meetingRoomDOMapper.getIdByName(roomName);
		// 为空则不存在
		if (id == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateOne(MeetingRoomDTO meetingRoomDTO) {
		meetingRoomDOMapper.updateOne(meetingRoomDTO);
		Integer count = getCount(meetingRoomDTO.getRoom());
		if (count != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public void delOne(Integer id) {
		meetingRoomDOMapper.delOneById(id);
	}

	@Override
	public Integer getCount(String room) {
		return meetingRoomDOMapper.countByRoom(room);
	}

	@Override
	public void updateDepartmentByDepartmentId(DepartmentInfo departmentInfo) {
		meetingRoomDOMapper.updateDepartmentByDepartmentId(departmentInfo);
	}
}
