package com.service;

import site.model.QuestionCategory;
import site.service.QuestionCategoryService;
import site.service.UserService;
import site.system.config.spring.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestQuestionCategoryService {

    @Autowired
    private QuestionCategoryService questionCategoryService;
    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        QuestionCategory questionCategory1 = new QuestionCategory();
        questionCategory1.setName("MySQL");
        questionCategoryService.save(questionCategory1);
        QuestionCategory questionCategory2 = new QuestionCategory();
        questionCategory2.setName("Java");
        questionCategoryService.save(questionCategory2);
        QuestionCategory questionCategory3 = new QuestionCategory();
        questionCategory3.setName(".Net");
        questionCategoryService.save(questionCategory3);
        QuestionCategory questionCategory4 = new QuestionCategory();
        questionCategory4.setName("Oracle");
        questionCategoryService.save(questionCategory4);
        QuestionCategory questionCategory5 = new QuestionCategory();
        questionCategory5.setName("Go");
        questionCategoryService.save(questionCategory5);
        QuestionCategory questionCategory6 = new QuestionCategory();
        questionCategory6.setName("C#");
        questionCategoryService.save(questionCategory6);
    }

}
