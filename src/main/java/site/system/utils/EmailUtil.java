package site.system.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import java.util.UUID;

/**
 * 发送邮件工具类
 */
public class EmailUtil {

    private static Logger logger = Logger.getLogger(EmailUtil.class);

    /**
     * 发送账户激活邮件
     */
    public static void sendAccountActivateEmail(String toUser, String emailSign) {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(PropertiesUtil.getStringValue(WebConstants.EMAIL_HOSTNAME));
            String smtpPort = PropertiesUtil.getStringValue(WebConstants.EMAIL_SMTPPORT);
            email.setSmtpPort(Integer.parseInt(smtpPort));
            String userName = PropertiesUtil.getStringValue(WebConstants.EMAIL_USERNAME);
            String password = PropertiesUtil.getStringValue(WebConstants.EMAIL_PASSWORD);
            email.setAuthenticator(new DefaultAuthenticator(userName, password));
            email.setSSLOnConnect(true);
            email.setCharset("UTF-8");
            email.setFrom(PropertiesUtil.getStringValue(WebConstants.EMAIL_FROM));
            email.setSubject("JFourm账户激活邮件.");
            email.setHtmlMsg("<html><a href='http://localhost:8080/site/email/activateEmail.action?emailSign=" + emailSign + "'>点击此处链接</a>, 激活您的 JFourm 账户.<html>");
            email.addTo(toUser);
            email.send();
        } catch (EmailException e) {
            logger.error("发送邮件失败, 异常信息: " + e);
        }
    }

    public static String generateEmailSign() {
        return System.currentTimeMillis() + UUID.randomUUID().toString();
    }

    /**
     * 加密 emailSign
     */
    public static String encodeEmailSign(String emailSign) {
        String salt = PropertiesUtil.getStringValue(WebConstants.ENCRYPTION_SALT);
        String emailSignSalt = emailSign + salt;
        byte[] bytes = Base64.encodeBase64(emailSignSalt.getBytes());
        return new String(bytes);
    }

    /**
     * 解密 emailSign
     */
    public static String decodeEmailSign(String encodeEmailSign) {
        String salt = PropertiesUtil.getStringValue(WebConstants.ENCRYPTION_SALT);
        byte[] bytes = Base64.decodeBase64(encodeEmailSign.getBytes());
        String emailSignSalt = new String(bytes);
        String emailSign = emailSignSalt.replace(salt, "");
        return emailSign;
    }

}
