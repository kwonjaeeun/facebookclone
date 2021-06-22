package com.koreait.facebook.user;

import com.koreait.facebook.common.EmailServiceImpl;
import com.koreait.facebook.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private  UserMapper mapper;

    @Autowired
    private EmailServiceImpl email;

    public int join(UserEntity param){
    String hashedPw = BCrypt.hashpw(param.getPw(),BCrypt.gensalt());
    param.setPw(hashedPw);
    return mapper.join(param);
    }
    public void sendEmail(){
        String to="dino218@naver.com";
        String sub="제목";
        String text="내용 <a href='http://localhost:8090/user/login'>sss</a>";
        email.sendMineMessage(to,sub,text);
    }

}
