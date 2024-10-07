package com.liu.luntan;

import com.liu.luntan.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.processing.AbstractProcessor;

@SpringBootTest
@ContextConfiguration(classes = LuntanApplication.class)
public class MailTest {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;
    // 直接发送文字信息
    @Test
    public void testTextMail() {
        mailClient.sendMail("2727428294@qq.com", "TEST", "Welcome！");
    }

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username", "liu11111");

        String content = templateEngine.process("/mail/demo", context);
        System.out.println(context);

        mailClient.sendMail("2727428294@qq.com", "HTML", content);
    }

}
