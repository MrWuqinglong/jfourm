package site.service;

import site.dao.BaseDAO;
import site.dao.UserDAO;
import site.dao.UserLoginDAO;
import site.exception.UserRegisterException;
import site.model.Question;
import site.model.QuestionCategory;
import site.model.User;
import site.model.UserLogin;
import site.system.utils.MD5Util;
import site.system.utils.PropertiesUtil;
import site.system.utils.WebConstants;
import site.system.web.Page;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Set;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserLoginDAO userLoginDAO;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionCategoryService questionCategoryService;

    @Resource(name = "userDAO")
    @Override
    public void setDao(BaseDAO<User> dao) {
        super.setDao(dao);
    }

    /* 检测昵称是否被占用 */
    public void validateNickName(User user) throws UserRegisterException {
        User dbUser = userDAO.getByNickName(user);
        if (dbUser != null) {
            throw new UserRegisterException("昵称已经被占用.");
        }
    }

    /* 通过邮箱获取一个实体 */
    public User getByEmail(UserLogin userLogin) {
        User user = new User();
        user.setEmail(userLogin.getEmail());
        return userDAO.getByEmail(user);
    }

    /* 获取用户收藏的问题分类 */
    public Set<QuestionCategory> getUserQuestionCategories(User user) {
        User dbUser = userDAO.get(user.getId());
        // 实例化代理对象
        Hibernate.initialize(dbUser.getAttentionQuestionCategories());
        return dbUser.getAttentionQuestionCategories();
    }

    /* 分页获取用户关注的问题 */
    public Page<Question> getUserAttentionQuestion(User user, Page<Question> page) {
        user = userDAO.get(user.getId());
        Set<Question> attentionQuestions = user.getAttentionQuestions();
        for (Question question : attentionQuestions)
            Hibernate.initialize(question);
        page.setList(new ArrayList<>(attentionQuestions));
        // TODO 将要分页
        return page;
    }

    @Transactional
    @Override
    public void update(User user) {
        User dbUser = userDAO.get(user.getId());
        dbUser.setGender(user.getGender());
        dbUser.setBirthDay(user.getBirthDay());
        dbUser.setPersonUrl(user.getPersonUrl());
        dbUser.setMobile(user.getMobile());
        dbUser.setCurrentTown(user.getCurrentTown());
        super.update(dbUser);
    }

    /* 实例化用户关联对象 */
    public void initialize(User user) {

        Hibernate.initialize(user.getComments());
        Hibernate.initialize(user.getAskedQuestions());
        Hibernate.initialize(user.getAttentionQuestions());
        Hibernate.initialize(user.getHeadLines());

    }

    /**
     * 保存用户
     */
    @Transactional
    public void save(User user, UserLogin userLogin) {
        // 保存用户信息
        user.setScore(0);
        userDAO.save(user);

        // 保存用户登录信息
        String salt = PropertiesUtil.getStringValue(WebConstants.ENCRYPTION_SALT);
        String passwordSalt = userLogin.getPassword() + salt;
        String passwordMD5 = MD5Util.generateMD5(passwordSalt);
        userLogin.setPassword(passwordMD5);
        userLogin.setEmailValidated(0);
        userLogin.setUserSource(1);
        userLogin.setEnabled(1);
        userLoginDAO.save(userLogin);
    }

    @Transactional
    public void addQuestionCategory(User user, QuestionCategory questionCategory) {
        user = userDAO.get(user.getId());
        questionCategory = questionCategoryService.get(questionCategory.getId());
        user.getAttentionQuestionCategories().add(questionCategory);
        userDAO.update(user);
    }

    @Transactional
    public void deleteQuestionCategory(User user, QuestionCategory questionCategory) {
        user = userDAO.get(user.getId());
        questionCategory = questionCategoryService.get(questionCategory.getId());
        user.getAttentionQuestionCategories().remove(questionCategory);
        questionCategory.getUsers().remove(user);
        userDAO.update(user);
    }

    @Transactional
    public void addQuestion(User user, Question question) {
        user = userDAO.get(user.getId());
        question = questionService.get(question.getId());
        user.getAttentionQuestions().add(question);
        userDAO.update(user);
    }

    @Transactional
    public void deleteQuestion(User user, Question question) {
        user = userDAO.get(user.getId());
        question = questionService.get(question.getId());
        user.getAttentionQuestions().remove(question);
        question.getUsers().remove(user);
        userDAO.update(user);
    }

}
