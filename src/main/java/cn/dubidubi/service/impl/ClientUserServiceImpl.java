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

import cn.dubidubi.dao.DependUserInfoMapper;
import cn.dubidubi.dao.base.UserMapper;
import cn.dubidubi.model.DependUserInfo;
import cn.dubidubi.model.dto.ClientUserDTO;
import cn.dubidubi.model.dto.QueryDTO;
import cn.dubidubi.service.ClientUserService;

@Service
public class ClientUserServiceImpl implements ClientUserService {
	@Autowired
	DependUserInfoMapper dependUserInfoMapper;
	@Autowired
	UserMapper userMapper;

	@Override
	public PageInfo<DependUserInfo> listAllDependUserInfoByQueryDTO(QueryDTO dto) {
		PageHelper.startPage(dto.getPageNum(), 12);
		List<DependUserInfo> list = dependUserInfoMapper.listAllDependUserInfo(dto);
		PageInfo<DependUserInfo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public void saveOneClientUser(ClientUserDTO clientUserDTO) {
		dependUserInfoMapper.saveOneUserInfo(clientUserDTO);
	}

	@Override
	public Integer getIdByAccount(String account) {
		return dependUserInfoMapper.getIdByAccount(account);
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = false, propagation = Propagation.REQUIRED)
	public void delOneById(Integer id) {
		DependUserInfo dependUserInfo = dependUserInfoMapper.selectByPrimaryKey(id);
		dependUserInfoMapper.delOneById(id);
		if (dependUserInfo != null) {
			userMapper.delOneByAccount(dependUserInfo.getAccount());
		}
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateOne(ClientUserDTO clientUserDTO) {
		// 根据老的工号更新
		dependUserInfoMapper.updateOne(clientUserDTO);
		// 更新user信息
		userMapper.updateUserByAccount(clientUserDTO);
		// 想要更新的工号的个数
		Integer count = countByAccount(clientUserDTO.getAccount());
		if (count != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public DependUserInfo getOneById(Integer id) {
		return dependUserInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer countByAccount(String account) {
		return dependUserInfoMapper.countAccount(account);
	}

}
