package cn.dubidubi.dao;

import cn.dubidubi.model.RootLog;

public interface RootLogMapper {
	RootLog selectByPrimaryKey(Integer id);

	void saveOneLog(RootLog rootLog);
}