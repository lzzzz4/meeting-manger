package cn.dubidubi.service;

import java.util.List;

import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.dto.QueryDTO;

/**
 * 
* @author linzj
* @Description: 部门信息
* @ClassName: DepartmentService  
* @date 2018年4月18日 上午9:31:13
 */
public interface DepartmentService {
	/**
	* @Title: listAllDepartments  
	* @Description: 得到所有的department,用于初始化部门信息
	* @param @return
	* @return List<DepartmentInfo>
	* @author linzj
	* @date 2018年4月18日 上午9:31:20 
	* @throws
	 */
	List<DepartmentInfo> listAllDepartments();

	/**
	 * 
	* @Title: getOneDepartmentById  
	* @Description: 根据departmentId查询department
	* @param @param id
	* @param @return
	* @return DepartmentInfo
	* @author linzj
	* @date 2018年4月18日 上午9:33:04 
	* @throws
	 */
	DepartmentInfo getOneDepartmentById(Integer id);

	/**
	 * 
	* @Title: listDepartmentsByQuery  
	* @Description: 根据query条件查询指定的部门信息
	* @param @param queryDTO
	* @param @return
	* @return List<DepartmentInfo>
	* @author linzj
	* @date 2018年4月18日 上午9:32:44 
	* @throws
	 */
	List<DepartmentInfo> listDepartmentsByQuery(QueryDTO queryDTO);

	void saveOne(DepartmentInfo departmentInfo);

	/**
	 * 
	* @Title: updateOne  
	* @Description: 在更新dep的同时，也需要更新user表
	* @param @param departmentInfo
	* @return void
	* @author linzj
	* @date 2018年4月19日 上午8:41:59 
	* @throws
	 */
	void updateOne(DepartmentInfo departmentInfo);

	void delOne(Integer id);
}
