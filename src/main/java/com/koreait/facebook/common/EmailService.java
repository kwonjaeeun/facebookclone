package com.koreait.facebook.common;

public interface EmailService {
    void sendSimpleMessage(String to,String subject,String text);
    void sendMineMessage(String to,String subject,String text);
}
