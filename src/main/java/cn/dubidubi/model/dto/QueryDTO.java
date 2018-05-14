package cn.dubidubi.model.dto;

import java.io.Serializable;

/**
 * 
* @author linzj
* @Description: 前端上传的查询条件
* @ClassName: QueryDTO  
* @date 2018年4月17日 上午10:12:59
 */
public class QueryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer changeId;
	private String search;
	private Integer pageNum;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getChangeId() {
		return changeId;
	}

	public void setChangeId(Integer changeId) {
		this.changeId = changeId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
