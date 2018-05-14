package cn.dubidubi.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.dubidubi.dao.RootLogMapper;
import cn.dubidubi.model.RootLog;
import cn.dubidubi.service.RootLogService;

@Service
public class RootLogServiceImpl implements RootLogService {
	@Autowired
	RootLogMapper rootLogMapper;

	@Override
	@Async
	public void saveOneLog(String log) {
		RootLog rootLog = new RootLog();
		rootLog.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		rootLog.setLog(log);
		rootLogMapper.saveOneLog(rootLog);
	}

}
