package com.service;

import site.model.User;
import site.model.UserLogin;
import site.service.UserLoginService;
import site.system.config.spring.RootConfig;
import site.system.utils.EmailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestUserLoginService {

    @Autowired
    private UserLoginService userLoginService;

    @Test
    public void testSave() {
        User user = new User();
        user.setId(1);
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail("tom@gmail.com");
        userLogin.setPassword("admin");
        userLogin.setEmailValidated(0);
        userLogin.setUserSource(1);
        String emailSign = EmailUtil.generateEmailSign();
        userLogin.setEmailSign(emailSign);
        userLogin.setUser(user);
        userLoginService.save(userLogin);
    }

    @Test
    public void testActiveAccount() {
        UserLogin userLogin = userLoginService.get(1);
        userLogin.setEmailValidated(1);
        userLoginService.update(userLogin);
    }

    @Test
    public void testGet() {
        userLoginService.get(1);
    }

}
