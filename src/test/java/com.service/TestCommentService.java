package com.service;

import site.model.Comment;
import site.model.Question;
import site.model.User;
import site.service.CommentService;
import site.system.config.spring.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestCommentService {

    @Autowired
    private CommentService commentService;

    @Test
    public void testSave() {
        User user = new User();
        user.setId(1);
        Question question = new Question();
        question.setId(2);
        Comment comment = new Comment();
        comment.setContent("");
        comment.setQuestion(question);
        comment.setUser(user);
        commentService.save(comment);

    }

}
