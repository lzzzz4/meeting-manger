package cn.dubidubi.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dubidubi.model.base.UserDO;
import cn.dubidubi.model.json.IndexJSON;
import sun.tools.tree.IdentifierExpression;

@Controller
@RequestMapping("/index")
/**
 *
 * @author linzj
 * @ClassName: IndexController
 * @Description: 首页index
 * @date 2018年4月16日 下午2:49:47
 */
public class IndexController {
    static String URL = "http://dubidubi.cn/meetingroom-web/index/open.do?random=";

    /**
     * @param @param  request
     * @param @return
     * @return IndexJSON
     * @throws
     * @Title: init
     * @Description: 初始化首页的状态栏
     * @author linzj
     * @date 2018年4月16日 下午2:49:57
     */
    @RequestMapping("/init")
    @ResponseBody
    public IndexJSON init(HttpServletRequest request) {
        IndexJSON indexJSON = new IndexJSON();
        UserDO userDO = (UserDO) request.getSession().getAttribute("user");
        // 用户信息的设置
        indexJSON.setAccount(userDO.getAccount());
        indexJSON.setUserId(userDO.getId());
        indexJSON.setUsername(userDO.getUsername());
        // 设置审核的url参数
        String str = RandomStringUtils.randomAlphabetic(6);
        String finalStr = str + "123456";
        String base64 = Base64.encodeBase64URLSafeString(finalStr.getBytes());
        // 设置url
        String url = URL + base64 + "&account=" + userDO.getAccount();
        indexJSON.setApiUrl("http://39.108.77.70/dist/index.html#/loginAPI?account=" + userDO.getAccount() + "&password=" + "admin");
        return indexJSON;
    }
}
