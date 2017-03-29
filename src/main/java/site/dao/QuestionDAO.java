package site.dao;

import site.model.Question;
import site.model.QuestionCategory;
import site.model.User;
import site.system.web.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDAO extends BaseDAO<Question> {

    /**
     * 根据提问时间倒序分页获取数据
     */
    public List<Question> listPageSortByLast(Page<Question> page) {
        String hql = "FROM Question ORDER BY submitTime DESC";
        return listPage(hql, page.getPageNo(), page.getPageSize());
    }

    /**
     * 统计某个类别下的问题数量
     */
    public Integer countLastWithCategory(QuestionCategory questionCategory) {
        String hql = "SELECT COUNT(*) FROM Question WHERE category.id=?";
        return countWithCondition(hql, questionCategory.getId());
    }

    /**
     * 按时间倒序分页获取该类别下的问题
     */
    public List<Question> listPageWithCategorySortByLast(Page<Question> page, QuestionCategory questionCategory) {
        String hql = "FROM Question WHERE category.id=? ORDER BY submitTime DESC";
        return listPageWithCondition(hql, page.getPageNo(), page.getPageSize(), questionCategory.getId());
    }

    /**
     * 统计我的问题数量
     */
    public Integer countMyQuestion(User user) {
        String hql = "SELECT COUNT(*) FROM Question WHERE user.id=?";
        return countWithCondition(hql, user.getId());
    }

    /**
     * 我的问题
     */
    public List<Question> listPageMyQuestion(Page<Question> page, User user) {
        String hql = "FROM Question WHERE user.id=? ORDER BY submitTime DESC";
        return listPageWithCondition(hql, page.getPageNo(), page.getPageSize(), user.getId());
    }
}
