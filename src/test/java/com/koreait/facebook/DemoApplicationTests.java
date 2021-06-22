package com.koreait.facebook;

import com.koreait.facebook.common.EmailService;
import com.koreait.facebook.common.MySecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private EmailService email;

    @Autowired
    private MySecurityUtils utils;

    @Test
    void sendEmail() {
        String s1="asd@sad.com";
        String s2="sss";
        String s3="tttt";
        email.sendSimpleMessage(s1,s2,s3);
    }

    @Test
    void test1(){
        int len=5;
        String val=utils.getRandomValues(len);
        assertEquals(val.length(),len);
        String val2=utils.getRandomValues(len);
        assertEquals(val2.length(),len);
        assertNotEquals(val,val2);
        System.out.println("1:"+val);
        System.out.println("2:"+val2);
    }
}
