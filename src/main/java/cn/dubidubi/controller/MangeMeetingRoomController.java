package cn.dubidubi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.dubidubi.dao.DepartmentInfoMapper;
import cn.dubidubi.model.DepartmentInfo;
import cn.dubidubi.model.MeetingRoomDO;
import cn.dubidubi.model.base.UserDO;
import cn.dubidubi.model.base.dto.AjaxResultDTO;
import cn.dubidubi.model.dto.MeetingRoomDTO;
import cn.dubidubi.model.dto.QueryDTO;
import cn.dubidubi.model.json.MeetingRoomJSON;
import cn.dubidubi.service.MeetingRoomService;
import cn.dubidubi.service.RootLogService;

/**
 * @author linzj
 * @Description: 会议室资源管理
 * @ClassName: MangeMeetingRoomController
 * @date 2018年4月19日 上午9:00:06
 */
@Controller
@RequestMapping("/room")
public class MangeMeetingRoomController {
    @Autowired
    MeetingRoomService meetingRoomService;
    @Autowired
    DepartmentInfoMapper DepartmentInfoMapper;
    @Autowired
    RootLogService log;

    /**
     * @param @param  queryDTO
     * @param @return
     * @return MeetingRoomJSON
     * @throws
     * @Title: init
     * @Description: 页面依据查询结果初始化
     * @author linzj
     * @date 2018年4月19日 下午2:42:46
     */
    @RequestMapping("/init")
    @RequiresPermissions("pc:show")
    @ResponseBody
    public MeetingRoomJSON init(QueryDTO queryDTO) {
        if (queryDTO.getPageNum() == null) {
            queryDTO.setPageNum(1);
        }
        if (StringUtils.isBlank(queryDTO.getSearch()) || queryDTO.getSearch().equals("nosearch")) {
            queryDTO.setSearch(null);
        }
        if (queryDTO.getChangeId() == null || queryDTO.getChangeId() == 0) {
            queryDTO.setChangeId(null);
        }
        // 查出所有的部门信息
        List<DepartmentInfo> DepartmentInfo = DepartmentInfoMapper.listAllDepartmentInfo();
        MeetingRoomJSON meetingRoomJSON = new MeetingRoomJSON();
        // 依据查询结果初始化,查询sys_meeting_room
        PageInfo<MeetingRoomDO> list = meetingRoomService.listAllMeetRoomByQuery(queryDTO);
        // 设置返回初始化值
        meetingRoomJSON.setCurrentDepartmentId(queryDTO.getChangeId());
        meetingRoomJSON.setCurrentPage(queryDTO.getPageNum());
        meetingRoomJSON.setList(list.getList());
        meetingRoomJSON.setTotal((int) list.getTotal());
        meetingRoomJSON.setTotalPage(list.getPages());
        meetingRoomJSON.setDepartmentInfos(DepartmentInfo);
        return meetingRoomJSON;
    }

    /**
     * @param @param  dto
     * @param @return
     * @return AjaxResultDTO
     * @throws
     * @Title: add
     * @Description: 新增会议室, 需要校验会议室是否存在
     * @author linzj
     * @date 2018年4月19日 下午4:50:03
     */
    @RequestMapping("/add")
    @RequiresPermissions("pc:show")
    @ResponseBody
    public AjaxResultDTO add(MeetingRoomDTO dto, @RequestParam(value = "days[]") String[] days,
                             HttpServletRequest request) {
        // 查询会议室是否存在,依据会议室名查询
        AjaxResultDTO ajaxResultDTO = new AjaxResultDTO();
        if (meetingRoomService.isExistRoom(dto.getRoom())) {
            ajaxResultDTO.setCode(300);
            return ajaxResultDTO;
        }
        // 数组设置入dto中
        // dto.setOpenDays(days);
        // 将数组分割为1,2,3的形式,并放入opendate中
        dto.setOpen_date(StringUtils.join(days, ","));
        // 写入end_date
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 7; i++) {
            if (!StringUtils.contains(dto.getOpen_date(), Integer.toString(i))) {
                sb.append(",");
                sb.append(Integer.toString(i));
            }
        }
        //替换掉前面多余的，号
        dto.setEndDate(sb.toString().replaceFirst(",", ""));
        meetingRoomService.saveOne(dto);
        ajaxResultDTO.setCode(200);
        UserDO do1 = (UserDO) request.getSession().getAttribute("user");

        //判断是否自动审核

        log.saveOneLog(do1.getAccount() + "{}新增了会议室信息");
        return ajaxResultDTO;
    }

    /**
     * @param @param  meetingRoomDTO
     * @param @param  days
     * @param @param  request
     * @param @return
     * @return AjaxResultDTO
     * @throws
     * @Title: update
     * @Description: 更新信息
     * @author linzj
     * @date 2018年4月19日 下午10:27:35
     */
    @RequestMapping("/update")
    @RequiresPermissions("pc:show")
    @ResponseBody
    public AjaxResultDTO update(MeetingRoomDTO meetingRoomDTO, @RequestParam(value = "days[]") String[] days,
                                HttpServletRequest request) {
        AjaxResultDTO ajaxResultDTO = new AjaxResultDTO();
        // room总数不为1则已存在,禁止更新
        //写在service下了
        //Integer count = meetingRoomService.getCount(meetingRoomDTO.getRoom());
        meetingRoomDTO.setOpen_date(StringUtils.join(days, ","));
        // 写入end_date
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 7; i++) {
            if (!StringUtils.contains(meetingRoomDTO.getOpen_date(), Integer.toString(i))) {
                sb.append(",");
                sb.append(Integer.toString(i));
            }
        }
        meetingRoomDTO.setEndDate(sb.toString().replaceFirst(",", ""));
        boolean flag = meetingRoomService.updateOne(meetingRoomDTO);
        if (!flag) {
            ajaxResultDTO.setCode(400);
            return ajaxResultDTO;
        }
        UserDO do1 = (UserDO) request.getSession().getAttribute("user");
        log.saveOneLog(do1.getAccount() + "更新了会议室信息");
        ajaxResultDTO.setCode(200);
        return ajaxResultDTO;
    }

    @RequestMapping("/del")
    @RequiresPermissions("pc:del")
    @ResponseBody
    public AjaxResultDTO del(Integer id, HttpServletRequest request) {
        AjaxResultDTO ajaxResultDTO = new AjaxResultDTO();
        ajaxResultDTO.setCode(200);
        meetingRoomService.delOne(id);
        UserDO do1 = (UserDO) request.getSession().getAttribute("user");
        log.saveOneLog(do1.getAccount() + "删除了会议室信息");
        return ajaxResultDTO;
    }
}
