package cn.dubidubi.dao.base;

import java.util.List;

import cn.dubidubi.model.ShowRole;
import cn.dubidubi.model.base.SystemRole;
import cn.dubidubi.model.dto.QueryDTO;

public interface SystemRoleMapper {
	SystemRole selectByPrimaryKey(Integer id);

	List<SystemRole> listAll();

	List<ShowRole> listUserAndRole(QueryDTO dto);

	Integer getIdByUserId(Integer userId);

	void delOneById(Integer id);
}