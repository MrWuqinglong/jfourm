package site.dao;

import site.model.Admin;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO extends BaseDAO<Admin> {

    public Admin getByUserName(String userName) {
        String hql = "FROM Admin WHERE userName=?";
        return get(hql, userName);
    }
}
