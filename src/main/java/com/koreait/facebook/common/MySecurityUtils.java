package com.koreait.facebook.common;

import org.springframework.stereotype.Component;

@Component
public class MySecurityUtils {
    public String getRandomValues(int len){
        int result=0;
        for(int i=0;i<len;i++){
            result=result*10;
            result+=(int)(Math.random()*10);
        }
        System.out.println(result);
        return result+"";
    }
}
