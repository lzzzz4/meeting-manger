package cn.dubidubi.dao;

import java.util.List;

import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.DependUserInfo;
import cn.dubidubi.model.dto.ClientUserDTO;
import cn.dubidubi.model.dto.QueryDTO;

public interface DependUserInfoMapper {
	DependUserInfo selectByPrimaryKey(Integer id);

	void saveOneUserInfo(ClientUserDTO clientUserDTO);

	List<DependUserInfo> listAllDependUserInfo(QueryDTO queryDTO);

	Integer getIdByAccount(String account);

	void delOneById(Integer id);

	void updateOne(ClientUserDTO clientUserDTO);

	void updateDependnameByDependId(DepartmentInfo id);

	Integer countAccount(String account);
}