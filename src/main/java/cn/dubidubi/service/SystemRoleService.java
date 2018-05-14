package cn.dubidubi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.dubidubi.model.ShowRole;
import cn.dubidubi.model.base.SystemRole;
import cn.dubidubi.model.dto.AddRoleDTO;
import cn.dubidubi.model.dto.QueryDTO;

@Service
public interface SystemRoleService {
	public List<SystemRole> listAll();

	public void addUserRole(AddRoleDTO addRoleDTO);

	public List<ShowRole> listShowRoleByQuery(QueryDTO dto);

	Integer getIdByUserId(Integer id);

	void delOneById(Integer id);
}
