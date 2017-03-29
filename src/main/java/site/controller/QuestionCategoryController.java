package site.controller;

import site.model.Question;
import site.model.QuestionCategory;
import site.model.User;
import site.service.QuestionCategoryService;
import site.service.QuestionService;
import site.service.UserService;
import site.system.utils.PropertiesUtil;
import site.system.utils.WebConstants;
import site.system.web.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/questionCategory")
@SessionAttributes(names = {"user"})
public class QuestionCategoryController {

    @Autowired
    private UserService userService;
    @Autowired
    private QuestionCategoryService questionCategoryService;
    @Autowired
    private QuestionService questionService;

    /* 按时间倒叙查看该标签下的所有问题 */
    @RequestMapping("/listAllQuestion.action")
    public String listAllQuestion(QuestionCategory questionCategory, Page<Question> page, ModelMap modelMap) {
        if (page == null) {
            page = new Page<>(1);
        }
        page = questionService.listLastWithCategory(page, questionCategory);
        modelMap.addAttribute("page", page);

        questionCategory = questionCategoryService.get(questionCategory.getId());
        modelMap.addAttribute("questionCategory", questionCategory);

        modelMap.addAttribute("href", "/questionCategory/listAllQuestion.action");
        modelMap.addAttribute("pageSize", PropertiesUtil.getIntegerValue(WebConstants.PAGE_SIZE));

        return "/category_question";
    }

    /* 列出该用户的所有问题分类 */
    @RequestMapping("/listAll.action")
    public String listAll(ModelMap modelMap) {
        User user = (User) modelMap.get("user");
        List<QuestionCategory> questionCategories = questionCategoryService.listAll();
        if (user != null) {
            Set<QuestionCategory> userQuestionCategories = userService.getUserQuestionCategories(user);
            questionCategories.removeAll(userQuestionCategories);
            modelMap.addAttribute("userQuestionCategories", userQuestionCategories);
            modelMap.addAttribute("login", "true");
        }
        modelMap.addAttribute("questionCategories", questionCategories);
        modelMap.addAttribute("login", "false");
        return "/list_question_category";
    }

    /* 新增问题分类 */
    @RequestMapping("/doAddCategory.action")
    @ResponseBody
    public String doAddCategory(QuestionCategory questionCategory, ModelMap modelMap) {
        questionCategoryService.save(questionCategory);
        return "success";
    }

    /* 列出所有问题分类 */
    @RequestMapping("/listAllCategory.action")
    public String listAllCategory(ModelMap modelMap) {
        List<QuestionCategory> questionCategories = questionCategoryService.listAll();
        modelMap.addAttribute("questionCategories", questionCategories);
        return "/back/list_question_category";
    }

    /* 跳转到新增问题分类页面 */
    @RequestMapping("/addCategory.action")
    public String addCategory() {
        return "/back/add_question_category";
    }
}
