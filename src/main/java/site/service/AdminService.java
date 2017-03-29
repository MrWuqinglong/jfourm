package site.service;

import site.dao.AdminDAO;
import site.dao.BaseDAO;
import site.exception.AdminException;
import site.model.Admin;
import site.system.utils.MD5Util;
import site.system.utils.PropertiesUtil;
import site.system.utils.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService extends BaseService<Admin> {

    @Autowired
    private AdminDAO adminDAO;

    @Resource(name = "adminDAO")
    @Override
    public void setDao(BaseDAO<Admin> dao) {
        super.setDao(dao);
    }

    /* 验证管理员登录信息 */
    public void validateInfo(Admin admin) throws AdminException {
        Admin dbAdmin = adminDAO.getByUserName(admin.getUserName());
        if (dbAdmin == null) {
            throw new AdminException("无此管理员.");
        }
        String salt = PropertiesUtil.getStringValue(WebConstants.ENCRYPTION_SALT);
        String generateMD5 = MD5Util.generateMD5(admin.getPassword() + salt);
        if (!dbAdmin.getPassword().equals(generateMD5)) {
            throw new AdminException("密码不正确");
        }
    }
}
