package site.dao;

import site.model.Comment;
import site.model.User;
import site.system.web.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAO extends BaseDAO<Comment> {

    /**
     * 统计我的回答数， 按问题分类
     */
    public Integer countMyCommentsNum(User user) {
        String hql = "SELECT COUNT(*) FROM Comment WHERE user.id=? ORDER BY submitTime DESC";
        return countWithCondition(hql, user.getId());
    }

    /**
     * 按时间倒序分页获取数据
     */
    public List<Comment> listMyAnswer(User user, Page<Comment> page) {
        String hql = "FROM Comment WHERE user.id=? ORDER BY submitTime DESC";
        return listWithCustom(hql, user.getId());
    }
}
