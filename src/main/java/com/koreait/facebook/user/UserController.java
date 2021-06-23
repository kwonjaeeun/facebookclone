package com.koreait.facebook.user;

import com.koreait.facebook.user.model.UserEntity;
import groovyjarjarantlr4.v4.codegen.model.ModelElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public void login(@ModelAttribute UserEntity userEntity){
    };


    @GetMapping("/join")
    public void join(@ModelAttribute UserEntity userEntity){
    };
    @PostMapping("/join")
    public String joinProc(@ModelAttribute UserEntity userEntity){
        service.join(userEntity);
        return "redirect:login?needAuth=1";
    };

    @GetMapping("/auth")
    public String email(@ModelAttribute UserEntity param){
        int result= service.setAuth(param);
        return "redirect:login?auth="+result;
    }
    @GetMapping("/profile")
    public void profile() {}

    @PostMapping("/profileImg")
    public String profileImg(MultipartFile[] imgArr) {
        service.profileImg(imgArr);
        return "redirect:profile";
    }
}
