package site.controller;

import site.model.Comment;
import site.model.Question;
import site.model.User;
import site.service.CommentService;
import site.service.QuestionService;
import site.system.web.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.Timestamp;

@Controller
@RequestMapping("/comment")
@SessionAttributes(names = {"user"})
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private QuestionService questionService;

    /* 添加评论 */
    @RequestMapping("/addComment.action")
    @ResponseBody
    public String addComment(Integer questionId, String content, ModelMap modelMap) {
        User user = (User) modelMap.get("user");
        Question question = questionService.get(questionId);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setSubmitTime(new Timestamp(System.currentTimeMillis()));
        comment.setUser(user);
        comment.setQuestion(question);
        commentService.save(comment);

        return "success";
    }

    /* 我的评论 */
    @RequestMapping("/listMyComment.action")
    public String listMyComments(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap modelMap) {
        Page<Comment> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        User user = (User) modelMap.get("user");
        page = commentService.listMyComments(user, page);
        modelMap.addAttribute("page", page);
        return "/user/profile/my_comment";
    }

    /* 删除评论 */
    @RequestMapping("/deleteComment.action")
    @ResponseBody
    public String deleteComment (Comment comment){
        comment = commentService.get(comment.getId());
        commentService.delete(comment);
        return "success";
    }

}
