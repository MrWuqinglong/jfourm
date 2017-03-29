package site.controller;

import com.alibaba.fastjson.JSON;
import site.exception.UserLoginException;
import site.exception.UserRegisterException;
import site.service.*;
import site.model.*;
import site.system.utils.EmailUtil;
import site.system.web.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
@SessionAttributes(names = {"modelType", "user", "questionCategories", "headLines"})
public class UserController extends BaseController {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private HeadLineService headLineService;
    @Autowired
    private QuestionCategoryService questionCategoryService;

    /**
     * 跳转到注册页面
     */
    @RequestMapping("/login/register.action")
    public String toLoginPage() {
        return "/user/login/register";
    }

    /**
     * 用户注册
     */
    @PostMapping("/login/doRegister.action")
    public String userRegister(UserLogin userLogin, ModelMap modelMap) {
        try {
            // 1. 验证邮箱是否可用
            userLoginService.validateEmail(userLogin);

            // 2. 验证昵称是否已经被占用
            User user = userLogin.getUser();
            userService.validateNickName(user);

            // 3. 保存 User 和 UserLogin
            user.setEmail(userLogin.getEmail());
            String emailSign = EmailUtil.generateEmailSign();
            userLogin.setEmailSign(emailSign);
            userService.save(user, userLogin);

            // 4. 发送邮箱验证邮件, 签名经过加密发送出去, 同密码一样, 使用 salt盐进行加密
            EmailUtil.sendAccountActivateEmail(userLogin.getEmail(), EmailUtil.encodeEmailSign(emailSign));
        } catch (UserRegisterException e) {
            modelMap.addAttribute("errorMsg", e.getMessage());
            return "/user/login/register";
        }
        return "redirect:/user/registerSuccess.action";
    }

    /**
     * 注册成功， 进行页面跳转
     */
    @RequestMapping("/registerSuccess.action")
    public String registerSuccess(ModelMap modelMap) {
        modelMap.addAttribute("message", "注册成功, 请登录邮箱进行激活.");
        return "/success";
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping("/login/login.action")
    public String toRegisterPage() {
        return "/user/login/login";
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login/doLogin.action", method = RequestMethod.POST)
    public String userLogin(UserLogin userLogin, ModelMap modelMap) {
        try {
            // 验证邮箱用户信息
            userLoginService.validateUserLoginInfo(userLogin);

            // 获取用户信息, 存入 Session
            User user = userService.getByEmail(userLogin);
            modelMap.addAttribute("user", user);

        } catch (UserLoginException e) {
            modelMap.addAttribute("errorMsg", e.getMessage());
            return "/user/login/login";
        }
        return "redirect:/user/home.action";
    }

    /**
     * 用户退出
     */
    @RequestMapping("/logout.action")
    public String logout(HttpSession session, SessionStatus sessionStatus) {
        session.invalidate();;
        sessionStatus.setComplete();
        return "redirect:/site/index.action";
    }

    /**
     * 网站主页
     */
    @RequestMapping("/home.action")
    public String home(ModelMap modelMap) {
        // 获取最新的提问
        Page<Question> page = new Page<>(1);
        page = questionService.listLast(page);

        // 获取用户收藏的问题分类
        User user = (User) modelMap.get("user");
        Set<QuestionCategory> questionCategories = userService.getUserQuestionCategories(user);
        modelMap.addAttribute("questionCategories", questionCategories);

        // 获取头条 top 10
        List<HeadLine> headLines = headLineService.listTopTen();
        modelMap.addAttribute("headLines", headLines);

        modelMap.addAttribute("modelType", "questionModel");
        return "/index";
    }

    /**
     * 用户关注一个问题分类
     */
    @RequestMapping("/attention/addQuestionCategory.action")
    @ResponseBody
    public String attentionAddQuestionCategory(QuestionCategory questionCategory, ModelMap modelMap) {
        // 获取这个问题
        questionCategory = questionCategoryService.get(questionCategory.getId());

        // 添加用户收藏的问题分类
        User user = (User) modelMap.get("user");
        userService.addQuestionCategory(user, questionCategory);

        // 回复客户端
        response.setStatus(true);
        return JSON.toJSONString(response);
    }

    /**
     * 用户取消关注一个问题分类
     */
    @RequestMapping("/attention/deleteQuestionCategory.action")
    @ResponseBody
    public String attentionDeleteQuestionCategory(QuestionCategory questionCategory, ModelMap modelMap) {
        // 获取这个问题
        questionCategory = questionCategoryService.get(questionCategory.getId());

        // 添加用户收藏的问题分类
        User user = (User) modelMap.get("user");
        userService.deleteQuestionCategory(user, questionCategory);

        // 回复客户端
        response.setStatus(true);
        return JSON.toJSONString(response);
    }

    /**
     * 用户收藏一个问题
     */
    @RequestMapping("/favoriteQuestion.action")
    @ResponseBody
    public String favoriteQuestion(Question question, ModelMap modelMap) {
        User user = (User) modelMap.get("user");
        userService.addQuestion(user, question);
        return "success";
    }

    /**
     * 用户取消一个问题收藏
     */
    @RequestMapping("/unFavoriteQuestion.action")
    @ResponseBody
    public String unFavoriteQuestion(Question question, ModelMap modelMap) {
        User user = (User) modelMap.get("user");
        userService.deleteQuestion(user, question);
        return "success";
    }

    /**
     * 跳转到用户后台
     */
    @RequestMapping("/user_back.action")
    public String userBack() {
        return "/user/profile/user_back";
    }

    /**
     * 查看用户档案
     */
    @RequestMapping("/profile.action")
    public String profile() {
        return "/user/profile/profile";
    }

    /**
     * 更新用户档案
     */
    @RequestMapping("/updateProfile.action")
    public String updateProfile(User user, ModelMap modelMap) {
        userService.update(user);
        user = userService.get(user.getId());
        modelMap.addAttribute("user", user);
        return "/user/profile/user_back";
    }

    /**
     * 查看用户收藏的问题
     */
    @RequestMapping("/listAttentionQuestions.action")
    public String listAttentionQuestions(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap modelMap) {
        // 分页获取问题
        Page<Question> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        User user = (User) modelMap.get("user");
        page = userService.getUserAttentionQuestion(user, page);
        modelMap.put("page", page);
        return "/user/profile/attention_question";
    }

    /**
     * 启用/禁用用户
     */
    @RequestMapping("/closeUser.action")
    @ResponseBody
    public String closeUser(UserLogin userLogin) {
        userLogin = userLoginService.getByUserId(userLogin.getId());
        userLogin.setEnabled(0);
        userLoginService.update(userLogin);
        return "success";
    }

    /**
     * 禁启用用户
     */
    @RequestMapping("/openUser.action")
    @ResponseBody
    public String openUser(UserLogin userLogin) {
        userLogin = userLoginService.getByUserId(userLogin.getId());
        userLogin.setEnabled(1);
        userLoginService.update(userLogin);
        return "success";
    }

    /**
     * 显示所有用户
     */
    @RequestMapping("/listUsers.action")
    public String listUsers(ModelMap modelMap) {
        List<UserLogin> userLogins = userLoginService.listUsers();
        modelMap.addAttribute("userLogins", userLogins);
        return "/back/list_user";
    }

}
