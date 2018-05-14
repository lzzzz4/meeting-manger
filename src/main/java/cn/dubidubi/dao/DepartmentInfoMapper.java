package cn.dubidubi.dao;

import java.util.List;

import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.dto.QueryDTO;

public interface DepartmentInfoMapper {
	DepartmentInfo selectByPrimaryKey(Integer id);

	List<DepartmentInfo> listAllDepartmentInfo();

	List<DepartmentInfo> listDepartmentInfoByQuery(QueryDTO dto);

	void saveOne(DepartmentInfo departmentInfo);

	void updateOne(DepartmentInfo departmentInfo);

	void delOne(Integer id);
}