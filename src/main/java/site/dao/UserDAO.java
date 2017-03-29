package site.dao;

import site.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends BaseDAO<User> {

    public User getByEmail(User user) {
        String hql = "FROM User WHERE email=?";
        return get(hql, user.getEmail());
    }

    public User getByNickName(User user) {
        String hql = "FROM User WHERE nickName=?";
        return get(hql, user.getNickName());
    }

}
