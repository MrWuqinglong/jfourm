package site.controller;

import site.model.HeadLine;
import site.model.User;
import site.service.HeadLineService;
import site.system.web.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/headLine")
@SessionAttributes(names = {"modelType"})
public class HeadLineController extends BaseController {

    @Autowired
    private HeadLineService headLineService;

    /**
     * 分页列出最新的头条
     */
    @RequestMapping("/listLast.action")
    public String listLast(@RequestParam(name = "pageNo", required = false) Integer pageNo, ModelMap modelMap) {
        Page<HeadLine> page;
        if (pageNo == null) {
            page = new Page<>(1);
        } else {
            page = new Page<>(pageNo);
        }
        page = headLineService.listLast(page);

        modelMap.addAttribute("page", page);
        modelMap.addAttribute("modelType", "headLineModel");
        return "/list_head_line";
    }

    /**
     * 跳转到发布头条
     */
    @RequestMapping("/addHeadLine.action")
    public String addHeadLine() {
        return "/send_head_line";
    }

    /**
     * 保存头条
     */
    @RequestMapping("/saveHeadline.action")
    public String saveHeadLine(HeadLine headLine, ModelMap modelMap) {
        User user = (User) modelMap.get("user");
        headLine.setStatus(0);
        headLine.setUser(user);
        headLineService.save(headLine);
        modelMap.addAttribute("message", "保存头条成功.");
        return "/success";
    }

    /**
     * 列出待审核的头条
     */
    @RequestMapping("/listUnReview.action")
    public String listUnReview(ModelMap modelMap) {
        List<HeadLine> headLines = headLineService.listUnReview();
        modelMap.addAttribute("headLines", headLines);
        return "/back/list_head_line";
    }

    /**
     * 审核通过头条
     */
    @RequestMapping("/reviewPassed.action")
    @ResponseBody
    public String reviewPassed(Integer headLineId) {
        HeadLine headLine = headLineService.get(headLineId);
        headLine.setStatus(1);
        headLineService.update(headLine);
        return "success";
    }

}
