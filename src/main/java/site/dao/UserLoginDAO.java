package site.dao;

import site.model.UserLogin;
import org.springframework.stereotype.Repository;

@Repository
public class UserLoginDAO extends BaseDAO<UserLogin> {

    /**
     * 根据邮箱查找用户登录信息
     */
    public UserLogin getByEmail(UserLogin userLogin) {
        String hql = "FROM UserLogin WHERE email=?";
        return get(hql, userLogin.getEmail());
    }

    /**
     * 根据邮箱签名查找用户
     */
    public UserLogin getByEmailSign(String emailSign) {
        String hql = "FROM UserLogin WHERE emailSign=?";
        return get(hql, emailSign);
    }

    public UserLogin getByUserId(Integer userId) {
        String hql = "FROM UserLogin WHERE user.id=?";
        return get(hql, userId);
    }

}
