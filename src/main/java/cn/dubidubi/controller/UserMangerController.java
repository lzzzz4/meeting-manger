package cn.dubidubi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.DependUserInfo;
import cn.dubidubi.model.base.UserDO;
import cn.dubidubi.model.base.dto.AjaxResultDTO;
import cn.dubidubi.model.dto.ClientUserDTO;
import cn.dubidubi.model.dto.QueryDTO;
import cn.dubidubi.model.json.UserInfoInitJSON;
import cn.dubidubi.service.ClientUserService;
import cn.dubidubi.service.DepartmentService;
import cn.dubidubi.service.RootLogService;

/**
* @author linzj
* @ClassName: IndexController  
* @Description: 用户管理加载
* @date 2018年4月12日 下午5:44:13
 */
@Controller
@RequestMapping("/user")
public class UserMangerController {
	@Autowired
	ClientUserService clientUserService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	RootLogService log;

	/**
	* @Title: init  
	* @Description: 初始化获得页面所有的信息
	* @param 
	* @return void
	* @author linzj
	* @date 2018年4月16日 下午1:56:06 
	* @throws
	 */
	@RequestMapping("/init")
	@RequiresPermissions("pc:show")
	@ResponseBody
	public UserInfoInitJSON init(QueryDTO queryDTO) {
		Integer pageNum = queryDTO.getPageNum();
		// 判断页数
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		// 判断是否有查询与changeId
		if (queryDTO.getChangeId() == null || queryDTO.getChangeId() == 0) {
			queryDTO.setChangeId(null);
		}
		if (StringUtils.isBlank(queryDTO.getSearch()) || queryDTO.getSearch().equals("nosearch")) {
			queryDTO.setSearch(null);
		}
		// 依据queryDTO查询表格信息
		// 初始化表格信息
		final UserInfoInitJSON infoInitJSON = new UserInfoInitJSON();
		PageInfo<DependUserInfo> listAllDependUserInfoByPage = clientUserService
				.listAllDependUserInfoByQueryDTO(queryDTO);
		// 得到数据总个数
		Long total = listAllDependUserInfoByPage.getTotal();
		infoInitJSON.setTotal(total.intValue());
		infoInitJSON.setDependUserInfos(listAllDependUserInfoByPage.getList());
		// 初始化部门信息
		infoInitJSON.setDepartmentInfos(departmentService.listAllDepartments());
		// 初始化页数相关信息
		infoInitJSON.setCurrentPage(pageNum);
		infoInitJSON.setTotalPage(listAllDependUserInfoByPage.getPages());
		// 设置默认的select
		infoInitJSON.setCurrentDepartmentId(queryDTO.getChangeId());
		return infoInitJSON;
	}

	/**
	* @Title: showBrief  
	* @Description: 增加用户信息
	* @param 
	* @return void
	* @author linzj
	* @date 2018年4月15日 下午3:31:38 
	* @throws
	*/
	@RequestMapping("/userAdd")
	@RequiresPermissions("pc:show")
	@ResponseBody
	public AjaxResultDTO userAdd(ClientUserDTO clientUserDTO, HttpServletRequest request) {
		AjaxResultDTO ajaxResultDTO = new AjaxResultDTO();
		// 查询当前account是否存在,若存在则拒绝插入
		Integer id = clientUserService.getIdByAccount(clientUserDTO.getAccount());
		if (id != null) {
			ajaxResultDTO.setCode(500);
			return ajaxResultDTO;
		}
		DepartmentInfo department = departmentService.getOneDepartmentById(clientUserDTO.getDepartmentId());
		clientUserDTO.setDepartment(department.getDepartmentName());
		clientUserService.saveOneClientUser(clientUserDTO);
		ajaxResultDTO.setCode(200);
		UserDO do1 = (UserDO) request.getSession().getAttribute("user");
		log.saveOneLog(do1.getAccount() + ":新增了用户depend信息" + clientUserDTO.getAccount());
		return ajaxResultDTO;
	}

	/**
	* @Title: update  
	* @Description: 更新用户信息,同时更新user
	* @param @param clientUserDTO
	* @param @return
	* @return AjaxResultDTO
	* @author linzj
	* @date 2018年4月16日 下午8:34:21 
	* @throws
	 */
	@RequestMapping("/userUpdate")
	@RequiresPermissions("pc:show")
	@ResponseBody
	public AjaxResultDTO update(ClientUserDTO clientUserDTO, HttpServletRequest request) {
		AjaxResultDTO ajaxResultDTO = new AjaxResultDTO();
		// 判断当前account是否存在
		DepartmentInfo department = departmentService.getOneDepartmentById(clientUserDTO.getDepartmentId());
		clientUserDTO.setDepartment(department.getDepartmentName());
		boolean flag = clientUserService.updateOne(clientUserDTO);
		if (flag)
			ajaxResultDTO.setCode(200);
		else {
			ajaxResultDTO.setCode(500);
			return ajaxResultDTO;
		}
		UserDO do1 = (UserDO) request.getSession().getAttribute("user");
		log.saveOneLog(do1.getAccount() + ":更新了用户depend信息" + clientUserDTO.getAccount());
		return ajaxResultDTO;
	}

	/**
	* @Title: del  
	* @Description: 删除用户信息,同时删除user中
	* @param @param id
	* @param @return
	* @return AjaxResultDTO
	* @author linzj
	* @date 2018年4月16日 下午8:33:02 
	* @throws
	 */
	@RequestMapping("/del")
	@RequiresPermissions("pc:del")
	@ResponseBody
	public AjaxResultDTO del(Integer id, HttpServletRequest request) {
		AjaxResultDTO ajaxResultDTO = new AjaxResultDTO();
		clientUserService.delOneById(id);
		ajaxResultDTO.setCode(200);
		UserDO do1 = (UserDO) request.getSession().getAttribute("user");
		log.saveOneLog(do1.getAccount() + ":删除了用户depend信息");
		return ajaxResultDTO;
	}

}
