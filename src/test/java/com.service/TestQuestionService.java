package com.service;

import site.model.Question;
import site.model.QuestionCategory;
import site.model.User;
import site.service.QuestionService;
import site.system.config.spring.RootConfig;
import site.system.web.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestQuestionService {

    @Autowired
    private QuestionService questionService;

    @Test
    public void testSave() throws SQLException {
        User user = new User();
        user.setId(1);
        QuestionCategory questionCategory = new QuestionCategory();
        questionCategory.setId(1);

       for (int i = 1; i <= 23; i++) {
            Question question = new Question();
            question.setTitle("问题标题" + i);
            question.setContent("问题内容");
            question.setQuestionState(1);
            question.setAnswerNum(0);
            question.setAttentionNum(0);
            question.setCategory(questionCategory);
            question.setUser(user);
            questionService.save(question);
        }

    }

    @Test
    public void testPage() {
        Page<Question> page = new Page<>(1);
        page = questionService.listLast(page);
        System.out.println("总页数: " + page.getTotalPage());
        page.getList().forEach( o -> System.out.println(o.getTitle()));
    }

}
