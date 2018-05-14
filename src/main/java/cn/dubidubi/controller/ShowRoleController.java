package cn.dubidubi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dubidubi.model.ShowRole;
import cn.dubidubi.model.base.UserDO;
import cn.dubidubi.model.base.dto.AjaxResultDTO;
import cn.dubidubi.model.dto.QueryDTO;
import cn.dubidubi.service.RootLogService;
import cn.dubidubi.service.SystemRoleService;

/**
* @author linzj
* @Description: 查看权限分配情况
* @ClassName: ShowRoleController  
* @date 2018年4月21日 下午9:08:47
 */
@Controller
@RequestMapping("/showRole")
public class ShowRoleController {
	@Autowired
	SystemRoleService systemRoleService;
	@Autowired
	RootLogService rootLogService;

	/**
	 * 
	* @Title: show  
	* @Description: 查看权限信息,无需授权
	* @param @param dto
	* @param @return
	* @return List<ShowRole>
	* @author linzj
	* @date 2018年4月21日 下午9:58:52 
	* @throws
	 */
	@RequestMapping("/show")
	@ResponseBody
	public List<ShowRole> show(QueryDTO dto, HttpServletRequest request) {
		if (StringUtils.isBlank(dto.getSearch()) || dto.getSearch().equals("nosearch")) {
			dto.setSearch(null);
		}
		UserDO do1 = (UserDO) request.getSession().getAttribute("user");
		rootLogService.saveOneLog(do1.getAccount() + "查看了授权信息");
		return systemRoleService.listShowRoleByQuery(dto);

	}

	/**
	 * 
	* @Title: del  
	* @Description: 根据id删除
	* @param @param id
	* @param @return
	* @return AjaxResultDTO
	* @author linzj
	* @date 2018年4月21日 下午9:58:45 
	* @throws
	 */
	@RequestMapping("/del")
	@ResponseBody
	@RequiresPermissions("pc:role")
	public AjaxResultDTO del(Integer id, HttpServletRequest request) {
		systemRoleService.delOneById(id);
		AjaxResultDTO ajaxResultDTO = new AjaxResultDTO();
		ajaxResultDTO.setCode(200);
		UserDO do1 = (UserDO) request.getSession().getAttribute("user");
		rootLogService.saveOneLog(do1.getAccount() + "删除了授权信息");
		return ajaxResultDTO;
	}
}
