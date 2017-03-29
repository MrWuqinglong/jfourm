package site.service;

import site.dao.BaseDAO;
import site.dao.QuestionCategoryDAO;
import site.model.QuestionCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionCategoryService extends BaseService<QuestionCategory> {

    @Autowired
    private QuestionCategoryDAO questionCategoryDAO;

    @Resource(name = "questionCategoryDAO")
    @Override
    public void setDao(BaseDAO<QuestionCategory> dao) {
        super.setDao(dao);
    }

    public List<QuestionCategory> getTopTen() {
        return questionCategoryDAO.listTopTen();
    }

    public QuestionCategory getWithEager(Integer categoryId) {
        QuestionCategory questionCategory = questionCategoryDAO.get(categoryId);
        return questionCategory;
    }
}
