package com.ykaoqin.kit.util;

import com.ykaoqin.kit.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class SpringContextUtilsTest {

    @Test
    public void testSpringContextIsNotNull(){
        assertNotNull(SpringContextUtils.getApplicationContext());
    }

    @Test
    public void testMessageSourceInSpringContext(){
        MessageSource messageSource = (MessageSource) SpringContextUtils.getBean("messageSource");
        assertNotNull(messageSource);
    }
}
