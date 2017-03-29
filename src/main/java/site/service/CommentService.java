package site.service;

import site.dao.BaseDAO;
import site.dao.CommentDAO;
import site.model.Comment;
import site.model.User;
import site.system.web.Page;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService extends BaseService<Comment> {

    @Autowired
    private CommentDAO commentDAO;

    @Resource(name = "commentDAO")
    @Override
    public void setDao(BaseDAO<Comment> dao) {
        super.setDao(dao);
    }

    /**
     * 列出我的回复
     */
    public Page<Comment> listMyComments(User user, Page<Comment> page) {

        Integer totalItem = commentDAO.countMyCommentsNum(user);
        page.setTotalItem(totalItem);

        List<Comment> list = commentDAO.listMyAnswer(user, page);
        for (Comment comment : list)
            Hibernate.initialize(comment.getQuestion());
        page.setList(list);
        return page;
    }
}
