package com.koreait.facebook.user;

import com.koreait.facebook.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public void login(){};
    @GetMapping("/join")
    public void join(@ModelAttribute UserEntity userEntity){

    };
    @PostMapping("/join")
    public String joinProc(@ModelAttribute UserEntity userEntity){
        service.join(userEntity);
        return "redirect:/feed/home";
    };

}
