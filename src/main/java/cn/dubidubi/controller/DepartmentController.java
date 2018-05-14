package cn.dubidubi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.base.UserDO;
import cn.dubidubi.model.base.dto.AjaxResultDTO;
import cn.dubidubi.model.dto.QueryDTO;
import cn.dubidubi.service.DepartmentService;
import cn.dubidubi.service.RootLogService;

@Controller
@RequestMapping("/department")
/**
* @author linzj
* @Description: 部门管理controller
* @ClassName: DepartmentController  
* @date 2018年4月17日 下午12:46:26
 */
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	@Autowired
	RootLogService logService;

	/**
	* @Title: init  
	* @Description: 初始化信息
	* @param 
	* @return void
	* @author linzj
	* @date 2018年4月17日 下午12:47:29 
	* @throws
	 */
	@RequestMapping("/init")
	@RequiresPermissions("pc:show")
	@ResponseBody
	public List<DepartmentInfo> init(QueryDTO queryDTO) {
		if (StringUtils.isBlank(queryDTO.getSearch())) {
			queryDTO.setSearch(null);
		}
		List<DepartmentInfo> list = departmentService.listDepartmentsByQuery(queryDTO);
		return list;
	}

	/**
	* @Title: add  
	* @Description: 增加数据接口
	* @param @param departmentInfo
	* @param @return
	* @return AjaxResultDTO
	* @author linzj
	* @date 2018年4月18日 上午10:23:59 
	* @throws
	 */
	@RequestMapping("/add")
	@RequiresPermissions("pc:show")
	@ResponseBody
	// 待更新,若存在则拒绝增加
	public AjaxResultDTO add(DepartmentInfo departmentInfo, HttpServletRequest request) {
		AjaxResultDTO ajaxResultDTO = new AjaxResultDTO();
		departmentService.saveOne(departmentInfo);
		ajaxResultDTO.setCode(200);
		UserDO do1 = (UserDO) request.getSession().getAttribute("user");
		logService.saveOneLog(do1.getAccount() + ":新增了部门" + departmentInfo.getDepartmentName());
		return ajaxResultDTO;
	}

	/**
	 * 
	* @Title: update  
	* @Description: 更改部门信息 需要同时更改user,meeting等的信息
	* @param @param departmentInfo
	* @param @return
	* @return AjaxResultDTO
	* @author linzj
	* @date 2018年4月19日 下午4:50:57 
	* @throws
	 */
	@RequestMapping("/update")
	@RequiresPermissions("pc:show")
	@ResponseBody
	public AjaxResultDTO update(DepartmentInfo departmentInfo, HttpServletRequest request) {
		AjaxResultDTO ajaxResultDTO = new AjaxResultDTO();
		departmentService.updateOne(departmentInfo);
		ajaxResultDTO.setCode(200);
		UserDO do1 = (UserDO) request.getSession().getAttribute("user");
		logService.saveOneLog(do1.getAccount() + ":更新了部门信息");
		return ajaxResultDTO;
	}

	/**	
	* @Title: update  
	* @Description: 删除del
	* @param @param id
	* @param @return
	* @return AjaxResultDTO
	* @author linzj
	* @date 2018年4月18日 上午11:13:29 
	* @throws
	 */
	@RequestMapping("/del")
	@RequiresPermissions("pc:del")
	@ResponseBody
	public AjaxResultDTO update(Integer id, HttpServletRequest request) {
		AjaxResultDTO ajaxResultDTO = new AjaxResultDTO();
		departmentService.delOne(id);
		ajaxResultDTO.setCode(200);
		UserDO do1 = (UserDO) request.getSession().getAttribute("user");
		logService.saveOneLog(do1.getAccount() + ":删除了部门信息");
		return ajaxResultDTO;
	}
}
