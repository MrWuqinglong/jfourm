package site.controller;

import site.exception.AdminException;
import site.model.Admin;
import site.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/admin")
@SessionAttributes(names = {"admin"})
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    /* 跳转到登录页面 */
    @RequestMapping("/login.action")
    public String toLoginPage() {
        return "/back/login";
    }

    /* 管理员进行登录 */
    @RequestMapping("/doLogin.action")
    public String login(Admin admin, ModelMap modelMap) {
        try {
            adminService.validateInfo(admin);
            modelMap.addAttribute("admin", admin);
        } catch (AdminException e) {
            modelMap.addAttribute("errorMsg", e.getMessage());
            return "/back/login";
        }
        return "redirect:/admin/back_home.action";
    }

    /* 跳转到后台主页 */
    @RequestMapping("/back_home.action")
    public String backHome() {
        return "/back/main";
    }

}
