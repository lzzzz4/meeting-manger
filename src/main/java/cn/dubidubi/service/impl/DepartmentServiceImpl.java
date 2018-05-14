package cn.dubidubi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dubidubi.dao.DepartmentInfoMapper;
import cn.dubidubi.dao.DependUserInfoMapper;
import cn.dubidubi.dao.base.UserMapper;
import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.dto.QueryDTO;
import cn.dubidubi.service.DepartmentService;
import cn.dubidubi.service.MeetingRoomService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	DepartmentInfoMapper departmentInfoMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	DependUserInfoMapper DependUserInfoMapper;
	@Autowired
	MeetingRoomService meetingRoomService;

	@Override
	public List<DepartmentInfo> listAllDepartments() {
		return departmentInfoMapper.listAllDepartmentInfo();
	}

	@Override
	public DepartmentInfo getOneDepartmentById(Integer id) {
		return departmentInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<DepartmentInfo> listDepartmentsByQuery(QueryDTO queryDTO) {
		return departmentInfoMapper.listDepartmentInfoByQuery(queryDTO);
	}

	@Override
	public void saveOne(DepartmentInfo departmentInfo) {
		departmentInfoMapper.saveOne(departmentInfo);
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = false, propagation = Propagation.REQUIRED)
	public void updateOne(DepartmentInfo departmentInfo) {
		// 更新用户表
		userMapper.updateDepartmentByDepId(departmentInfo);
		// 更新用户依赖表信息
		DependUserInfoMapper.updateDependnameByDependId(departmentInfo);
		// 更新会议室表
		meetingRoomService.updateDepartmentByDepartmentId(departmentInfo);
		// 更新部门表信息
		departmentInfoMapper.updateOne(departmentInfo);

	}

	@Override
	public void delOne(Integer id) {
		departmentInfoMapper.delOne(id);
	}

}
