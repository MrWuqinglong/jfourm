package com.service;

import site.model.Admin;
import site.service.AdminService;
import site.system.config.spring.RootConfig;
import site.system.utils.MD5Util;
import site.system.utils.PropertiesUtil;
import site.system.utils.WebConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestAdminService {

    @Autowired
    private AdminService adminService;

    @Test
    public void testSave() {
        Admin admin = new Admin();
        admin.setUserName("admin");
        String salt = PropertiesUtil.getStringValue(WebConstants.ENCRYPTION_SALT);
        admin.setPassword(MD5Util.generateMD5("admin" + salt));
        adminService.save(admin);
    }

}
