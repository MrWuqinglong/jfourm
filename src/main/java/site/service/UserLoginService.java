package site.service;

import site.dao.BaseDAO;
import site.dao.UserLoginDAO;
import site.exception.UserLoginException;
import site.exception.UserRegisterException;
import site.model.User;
import site.model.UserLogin;
import site.system.utils.MD5Util;
import site.system.utils.PropertiesUtil;
import site.system.utils.WebConstants;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserLoginService extends BaseService<UserLogin> {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Resource(name = "userLoginDAO")
    @Override
    public void setDao(BaseDAO<UserLogin> dao) {
        super.setDao(dao);
    }

    /**
     * 验证邮箱是否被注册
     */
    public void validateEmail(UserLogin userLogin) throws UserRegisterException {
        UserLogin dbUserLogin = userLoginDAO.getByEmail(userLogin);
        if (dbUserLogin != null) {
            throw new UserRegisterException("邮箱已经被占用.");
        }
    }

    /**
     * 验证用户登录信息是否正确
     */
    public void validateUserLoginInfo(UserLogin userLogin) throws UserLoginException {
        if (userLogin == null) {
            throw new UserLoginException("参数信息异常.");
        }

        UserLogin dbUserLogin = userLoginDAO.getByEmail(userLogin);
        if (dbUserLogin == null) {
            throw new UserLoginException("用户不存在.");
        } else if (dbUserLogin.getEmailValidated() == 0) {
            throw new UserLoginException("邮箱未通通过验证, 请登录邮箱验证.");
        } else if (dbUserLogin.getEnabled() == 0) {
            throw new UserLoginException("该账户已经被管理员禁用, 请联系管理员.");
        } else {
            // 验证密码
            String salt = PropertiesUtil.getStringValue(WebConstants.ENCRYPTION_SALT);
            String generateMD5 = MD5Util.generateMD5(userLogin.getPassword() + salt);
            if (!dbUserLogin.getPassword().equals(generateMD5)) {
                throw new UserLoginException("密码不正确.");
            }
        }
    }

    /**
     * 根据邮箱获取一个实体
     */
    public UserLogin getByEmail(UserLogin userLogin) {
        return userLoginDAO.getByEmail(userLogin);
    }

    /**
     * 激活邮箱用户
     */
    @Transactional
    public void activeEmail(String emailSign) throws UserLoginException {
        UserLogin userLogin = userLoginDAO.getByEmailSign(emailSign);
        if (userLogin == null) {
            throw new UserLoginException("邮箱签名不正确, 激活失败!");
        } else if (userLogin.getEmailValidated() == 1) {
            throw new UserLoginException("邮箱账户已经激活, 不需要重复激活!");
        }
        userLogin.setEmailValidated(1);
        super.update(userLogin);
    }

    public UserLogin getByUserId(Integer userId) {
        return userLoginDAO.getByUserId(userId);
    }

    /**
     * 列出启用/禁用的用户
     */
    public List<UserLogin> listUsers() {
        List<UserLogin> userLogins = userLoginDAO.listAll();
        for (UserLogin userLogin : userLogins) {
            User user = userLogin.getUser();
            Hibernate.initialize(user.getComments());
            Hibernate.initialize(user.getAskedQuestions());
            Hibernate.initialize(user.getHeadLines());
        }
        return userLogins;
    }

}
