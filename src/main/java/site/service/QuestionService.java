package site.service;

import site.dao.BaseDAO;
import site.dao.QuestionDAO;
import site.model.Question;
import site.model.QuestionCategory;
import site.model.User;
import site.system.web.Page;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionService extends BaseService<Question> {

    @Autowired
    private QuestionDAO questionDAO;

    @Resource(name = "questionDAO")
    @Override
    public void setDao(BaseDAO<Question> dao) {
        super.setDao(dao);
    }

    /**
     * 根据提问时间排序分页获取数据
     * @param page
     * @return
     */
    public Page<Question> listLast(Page<Question> page) {
        // 1. 计算总条数
        Integer totalItem = questionDAO.countAll();
        page.setTotalItem(totalItem);

        // 2. 分页获取数据
        List<Question> list = questionDAO.listPageSortByLast(page);
        for (Question question : list) {
            Hibernate.initialize(question.getUser());
            Hibernate.initialize(question.getCategory());
        }
        page.setList(list);
        return page;
    }

    /**
     * 我的提问
     */
    public Page<Question> listMyQuestion(Page<Question> page, User user) {
        Integer totalItem = questionDAO.countMyQuestion(user);
        page.setTotalItem(totalItem);
        List<Question> list = questionDAO.listPageMyQuestion(page, user);
        page.setList(list);
        return page;
    }

    /**
     * 按时间排序列出该分类下的所有问题
     */
    public Page<Question> listLastWithCategory(Page<Question> page, QuestionCategory questionCategory) {
        Integer totalItem = questionDAO.countLastWithCategory(questionCategory);
        page.setTotalItem(totalItem);
        List<Question> list = questionDAO.listPageWithCategorySortByLast(page, questionCategory);
        page.setList(list);
        for (Question question : list) {
            Hibernate.initialize(question.getUser());
            Hibernate.initialize(question.getCategory());
        }
        return page;
    }

}
