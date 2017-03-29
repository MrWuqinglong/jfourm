package com.system;

import site.service.UserService;
import site.system.config.spring.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 吴庆龙
 * 2017/3/27 9:28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestMySQLVersion {

    @Autowired
    private UserService userService;

    @Test
    public void testGetMySQLVersion() {
        System.out.println(userService.getMySQLVersio());
    }

}
