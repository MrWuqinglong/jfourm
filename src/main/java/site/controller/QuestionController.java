package site.controller;

import site.model.Question;
import site.model.QuestionCategory;
import site.model.User;
import site.service.QuestionCategoryService;
import site.service.QuestionService;
import site.system.web.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/question")
@SessionAttributes(names = {"user"})
public class QuestionController extends BaseController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionCategoryService questionCategoryService;

    /**
     * 通过最新提问, 进行翻页
     */
    @RequestMapping("/listLast.action")
    public String listLastQuestion(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap modelMap) {
        Page<Question> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        page = questionService.listLast(page);
        modelMap.addAttribute("page", page);
        return "/index";
    }

    /**
     * 通过最新提问, 进行翻页
     */
    @RequestMapping("/listLastBack.action")
    public String listLastQuestionBack(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap modelMap) {
        Page<Question> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        page = questionService.listLast(page);
        modelMap.addAttribute("page", page);
        return "/back/list_question";
    }

    /**
     * 获取单个问题的详细内容
     */
    @RequestMapping("/questionDetail.action")
    public String questionDetail(Question question, ModelMap modelMap) {
        question = questionService.get(question.getId());
        modelMap.addAttribute("question", question);
        return "/question_detail";
    }

    /**
     * 获取某个分类下的所有问题
     */
    @RequestMapping("/listLastCategoryQuestion.action")
    public String listLastCategoryQuestion(@RequestParam(name = "pageNo", required = false) Integer pageNo, Integer categoryId, ModelMap modelMap) {

        // 获取分类信息
        QuestionCategory questionCategory = questionCategoryService.get(categoryId);
        modelMap.addAttribute("questionCategory", questionCategory);

        // 分页获取问题
        Page<Question> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        page = questionService.listLastWithCategory(page, questionCategory);
        modelMap.addAttribute("page", page);
        return "/list_question";
    }

    /**
     * 跳转到提问题页面
     */
    @RequestMapping("/askQuestion.action")
    public String askQuestion(ModelMap modelMap) {
        // 查出所有的分类
        List<QuestionCategory> questionCategories = questionCategoryService.listAll();
        modelMap.addAttribute("questionCategories", questionCategories);
        return "/ask_question";
    }

    /**
     * 增加一个问题
     */
    @RequestMapping("/saveQuestion.action")
    @ResponseBody
    public String saveQuestion(Question question, ModelMap modelMap) {
        User user = (User) modelMap.get("user");
        question.setAnswerNum(0);
        question.setAttentionNum(0);
        question.setQuestionState(1);
        question.setUser(user);
        questionService.save(question);
        return "/success";
    }

    /**
     * 我的提问
     */
    @RequestMapping("/listMyQuestion.action")
    public String listMyQuestion(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap modelMap) {
        Page<Question> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        User user = (User) modelMap.get("user");
        page = questionService.listMyQuestion(page, user);
        modelMap.addAttribute("page", page);
        return "/user/profile/my_ask";
    }

    /**
     * 关闭问题
     */
    @RequestMapping("/closeQuestion.action")
    @ResponseBody
    public String closeQuestion(Question question) {
        question = questionService.get(question.getId());
        question.setQuestionState(0);
        questionService.update(question);
        return "success";
    }

    /**
     * 删除问题
     */
    @RequestMapping("/deleteQuestion.action")
    @ResponseBody
    public String deleteQuestion(Question question) {
        question = questionService.get(question.getId());
        questionService.delete(question);
        return "success";
    }

}
