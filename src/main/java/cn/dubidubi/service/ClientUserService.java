package cn.dubidubi.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.Client;

import cn.dubidubi.model.DependUserInfo;
import cn.dubidubi.model.dto.ClientUserDTO;
import cn.dubidubi.model.dto.QueryDTO;

/**
 * 
* @author linzj
* @ClassName: ClientUserService  
* @Description: depend_user_info
* @date 2018年4月15日 下午9:23:09
 */
public interface ClientUserService {
	public void saveOneClientUser(ClientUserDTO clientUserDTO);

	/**
	 * 
	* @Title: listAllDependUserInfoByPage  
	* @Description: 获得当前指定的页数的用户信息
	* @param @param page
	* @param @return
	* @return PageInfo<DependUserInfo>
	* @author linzj
	* @date 2018年4月16日 下午2:34:45 
	* @throws
	 */
	public PageInfo<DependUserInfo> listAllDependUserInfoByQueryDTO(QueryDTO dto);

	/**
	 * 
	* @Title: getIdByAccount  
	* @Description: 依据account获取id
	* @param @param account
	* @param @return
	* @return Integer
	* @author linzj
	* @date 2018年4月16日 下午5:32:44 
	* @throws
	 */
	public Integer getIdByAccount(String account);

	public void delOneById(Integer id);

	public boolean updateOne(ClientUserDTO clientUserDTO);

	public DependUserInfo getOneById(Integer id);

	/**
	 * 
	* @Title: countByAccount  
	* @Description: 数据库中共有多少个目标更新account,若超过1则不能更新
	* @param @param account
	* @param @return
	* @return Integer
	* @author linzj
	* @date 2018年4月20日 上午7:38:26 
	* @throws
	 */
	public Integer countByAccount(String account);
}
