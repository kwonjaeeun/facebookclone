package com.koreait.facebook.user;

import com.koreait.facebook.common.EmailServiceImpl;
import com.koreait.facebook.common.MySecurityUtils;
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

    @Autowired
    private MySecurityUtils secUtils;

    public int join(UserEntity param){
        String  rVal= secUtils.getRandomValues(5);
        String hashedPw = BCrypt.hashpw(param.getPw(),BCrypt.gensalt());
        param.setPw(hashedPw);
        param.setAuthCd(rVal);
        int result=mapper.join(param);

        return 0;
    }
    public void sendEmail(){
        String to="dino218@naver.com";
        String sub="제목";
        String text="내용 <a href='http://localhost:8090/user/login'>sss</a>";
        email.sendMineMessage(to,sub,text);
    }

}
