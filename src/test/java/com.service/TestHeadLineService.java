package com.service;

import site.service.HeadLineService;
import site.system.config.spring.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestHeadLineService {

    @Autowired
    private HeadLineService headLineService;

    @Test
    public void testSave() {

       /* for (int i = 1; i <= 14; i++) {
            HeadLineCategory headLineCategory = new HeadLineCategory();
            if (i < 7)
                headLineCategory.setId(1);
            else
                headLineCategory.setId(2);
            HeadLine headLine = new HeadLine();
            headLine.setTitle("头条标题 " + i);
            headLine.setUrl("https://www.baidu.com");
            headLine.setCategory(headLineCategory);
            headLine.setViewNum(0);
            headLineService.save(headLine);
        }
*/
    }

}
