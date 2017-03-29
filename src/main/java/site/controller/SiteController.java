package site.controller;

import com.alibaba.fastjson.JSON;
import site.exception.UserLoginException;
import site.model.HeadLine;
import site.model.Question;
import site.model.QuestionCategory;
import site.service.HeadLineService;
import site.service.QuestionCategoryService;
import site.service.QuestionService;
import site.service.UserLoginService;
import site.system.utils.EmailUtil;
import site.system.web.Page;
import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/site")
@SessionAttributes(names = {"questionCategories", "headLines", "modelType", "user"})
public class SiteController extends BaseController {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionCategoryService questionCategoryService;
    @Autowired
    private HeadLineService headLineService;

    /**
     * 访问网站主页
     */
    @RequestMapping("/index.action")
    public String index(ModelMap modelMap) {
        // 1. 获取最新的提问
        Page<Question> page = new Page<>(1);
        page = questionService.listLast(page);
        modelMap.addAttribute("page", page);

        // ------------------- 放入 Session 中, 用户登录之后移除
        // 2. 获取所有问题分类, 前 10 个
        List<QuestionCategory> questionCategoryList = questionCategoryService.getTopTen();
        modelMap.addAttribute("questionCategories", questionCategoryList);

        // ------------------- 放入 Session 中, 用户登录之后移除
        // 3. 获取最新头条, 前 10 个
        List<HeadLine> headLines = headLineService.listTopTen();
        modelMap.addAttribute("headLines", headLines);

        modelMap.addAttribute("modelType", "questionModel"); // 设置默认模块为 问答模块
        modelMap.addAttribute("href", "/question/listLast.action"); // 设置下一页的链接
        return "/index";
    }

    /**
     * 激活邮箱账号
     */
    @RequestMapping("/email/activateEmail.action")
    public String activateAccount(String emailSign, ModelMap modelMap) {
        try {
            String decodeEmailSign = EmailUtil.decodeEmailSign(emailSign);
            userLoginService.activeEmail(decodeEmailSign);
        } catch (UserLoginException e) {
            modelMap.addAttribute("errorMsg", e.getMessage());
        }
        return "/user/login/active_result";
    }

    /**
     * 验证用户是否登录
     */
    @RequestMapping("/user/isLogin.action")
    @ResponseBody
    public String userIsLogin(ModelMap modelMap) {
        Object user = modelMap.get("user");
        if (user == null)
            response.setStatus(false);
        else
            response.setStatus(true);
        return JSON.toJSONString(response);
    }

    /**
     * 查看网站状态
     */
    @RequestMapping("/siteStatus.action")
    public String siteStatus(ModelMap modelMap) {
        // 用户量
        Integer userNum = userLoginService.countAll();
        modelMap.addAttribute("userNum", userNum);
        // 帖子数
        Integer questionNum = questionService.countAll();
        modelMap.addAttribute("questionNum", questionNum);

        // TODO 当前在线人数
        modelMap.addAttribute("currentUserNum", 0);

        // TODO 昨天注册的用户数量
        modelMap.addAttribute("yesterdayUserNum", 0);

        // TODO 昨天发的帖子数量
        modelMap.addAttribute("yesterdayQuestionNum", 0);

        // TODO 昨天发的头条数量
        modelMap.addAttribute("yesterdayHeadlineNum", 0);

        // 获取操作系统信息
        modelMap.addAttribute("javaVersion", SystemUtils.JAVA_VERSION);
        modelMap.addAttribute("osName", SystemUtils.OS_NAME);
        modelMap.addAttribute("MySQLVersion", userLoginService.getMySQLVersio());
        return "/back/site_status";
    }



    /**
     * 跳转到操作结果页面
     */
    @RequestMapping("/operationResult.action")
    public String optionResult(ModelMap modelMap) {
        modelMap.addAttribute("message", "操作成功");
        return "/success";
    }

}
