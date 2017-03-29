package site.system.config.web;

import site.system.config.spring.RootConfig;
import site.system.config.spring.WebConfig;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Web 应用的初始化
 */
public class WebInitialization extends AbstractAnnotationConfigDispatcherServletInitializer {

    private Logger logger = Logger.getLogger(WebInitialization.class);

    protected Class<?>[] getRootConfigClasses() {
        logger.info("加载 RootConfig 成功");
        return new Class<?>[]{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        logger.info("加载 WebConfig 成功");
        return new Class<?>[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        logger.info("映射 DispatcherServlet 路径为 *.action");
        return new String[]{"*.action"};
    }
}
