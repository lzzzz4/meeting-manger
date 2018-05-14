package cn.dubidubi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dubidubi.dao.base.SystemRoleMapper;
import cn.dubidubi.dao.base.UserRoleMapper;
import cn.dubidubi.model.ShowRole;
import cn.dubidubi.model.base.SystemRole;
import cn.dubidubi.model.dto.AddRoleDTO;
import cn.dubidubi.model.dto.QueryDTO;
import cn.dubidubi.service.SystemRoleService;

@Service
public class SystemRoleServiceImpl implements SystemRoleService {
	@Autowired
	SystemRoleMapper systemRoleMapper;
	@Autowired
	UserRoleMapper userRoleMapper;

	@Override
	public List<SystemRole> listAll() {
		return systemRoleMapper.listAll();
	}

	@Override
	public void addUserRole(AddRoleDTO addRoleDTO) {
		userRoleMapper.addUserRole(addRoleDTO);
	}

	@Override
	public List<ShowRole> listShowRoleByQuery(QueryDTO dto) {
		return systemRoleMapper.listUserAndRole(dto);
	}

	@Override
	public Integer getIdByUserId(Integer id) {
		return systemRoleMapper.getIdByUserId(id);
	}

	@Override
	public void delOneById(Integer id) {
		systemRoleMapper.delOneById(id);
	}

}
