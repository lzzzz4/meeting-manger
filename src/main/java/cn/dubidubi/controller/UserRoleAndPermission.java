package cn.dubidubi.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dubidubi.model.base.UserDO;
import cn.dubidubi.model.base.dto.AjaxResultDTO;
import cn.dubidubi.model.dto.AddRoleDTO;
import cn.dubidubi.model.json.SystemRoleJson;
import cn.dubidubi.service.RootLogService;
import cn.dubidubi.service.SystemRoleService;
import cn.dubidubi.service.UserLoginService;
import cn.dubidubi.service.base.LoginRealm;

/**
* @author linzj
* @Description:分配用户权限
* @ClassName: UserRoleAndPermission  
* @date 2018年4月21日 下午5:54:12
 */
@Controller
@RequestMapping("/shiro")
public class UserRoleAndPermission {
	@Autowired
	SystemRoleService systemRoleService;
	@Autowired
	RootLogService rootLogService;
	@Autowired
	UserLoginService userLoginService;
	@Autowired
	LoginRealm loginRealm;

	/**
	* @Title: listAll  
	* @Description: 展示所有的角色
	* @param 
	* @return void
	* @author linzj
	* @date 2018年4月21日 下午5:56:05 
	* @throws
	 */
	@RequestMapping("/show")
	@ResponseBody
	@RequiresPermissions("pc:role")
	public SystemRoleJson listAll(HttpServletRequest request) {
		SystemRoleJson json = new SystemRoleJson();
		json.setList(systemRoleService.listAll());
		json.setCode(200);
		UserDO do1 = (UserDO) request.getSession().getAttribute("user");
		rootLogService.saveOneLog(do1.getAccount() + "查看了权限管理信息!warn--->仅限超管操作");
		return json;
	}

	/**
	* @Title: add  
	* @Description: 为用户指定角色,当角色id已存在则拒绝
	* @param @return
	* @return SystemRoleJson
	* @author linzj
	* @date 2018年4月21日 下午7:56:48 
	* @throws
	 */
	@RequestMapping("/add")
	@ResponseBody
	@RequiresPermissions("pc:role")
	public AjaxResultDTO add(AddRoleDTO addRoleDTO, HttpServletRequest request) {

		AjaxResultDTO ajaxResultDTO = new AjaxResultDTO();
		if (systemRoleService.getIdByUserId(addRoleDTO.getUserId()) != null) {
			ajaxResultDTO.setCode(300);
			return ajaxResultDTO;
		}
		systemRoleService.addUserRole(addRoleDTO);
		ajaxResultDTO.setCode(200);
		loginRealm.clearCache();
		UserDO do1 = (UserDO) request.getSession().getAttribute("user");
		rootLogService.saveOneLog(do1.getAccount() + "新增了权限信息!warn--->仅限超管操作");
		return ajaxResultDTO;
	}

	/**
	* @Title: add  
	* @Description: 根据工号查找name与id
	* @param @return
	* @return SystemRoleJson
	* @author linzj
	* @date 2018年4月21日 下午7:56:48 
	* @throws
	 */
	@RequestMapping("/find")
	@ResponseBody
	@RequiresPermissions("pc:role")
	public UserDO findByAccount(String account) {
		return userLoginService.getUserDOToSessionByAccount(account);
	}
}
