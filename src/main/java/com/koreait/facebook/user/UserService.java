package com.koreait.facebook.user;

import com.koreait.facebook.common.EmailServiceImpl;
import com.koreait.facebook.common.MyFileUtils;
import com.koreait.facebook.common.MySecurityUtils;
import com.koreait.facebook.security.IAuthenticationFacade;
import com.koreait.facebook.user.model.UserEntity;
import com.koreait.facebook.user.model.UserProfileEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private  UserMapper mapper;

    @Autowired
    private EmailServiceImpl email;

    @Autowired
    private MySecurityUtils secUtils;

    @Autowired
    private UserProfileMapper profileMapper;

    @Autowired
    private IAuthenticationFacade auth;

    @Autowired
    private MyFileUtils myFileUtils;

    public int join(UserEntity param){
        String  rVal= secUtils.getRandomValues(5);
        String hashedPw = BCrypt.hashpw(param.getPw(),BCrypt.gensalt());
        param.setPw(hashedPw);
        param.setAuthCd(rVal);
        int result=mapper.join(param);
        if(result ==1){
            String subject ="[얼굴책]인증 메일입니다.";
            String text=String.format("<a href=\"http://localhost:8090/user/auth?email=%s&authCd=%s\">인증하기</a>",param.getEmail(),param.getAuthCd());
            email.sendMineMessage(param.getEmail(),subject,text);
        }
        return 0;
    }

    public int setAuth(UserEntity param) {
        return mapper.setAuth(param);
    }

    public void profileImg(MultipartFile[] imgArr) {
        UserEntity loginUser = auth.getLoginUser();
        int iuser = loginUser.getIuser();
        System.out.println("iuser : " + iuser);
        String target = "profile/" + iuser;
        UserProfileEntity param = new UserProfileEntity();
        param.setIuser(iuser);

        for(MultipartFile img : imgArr) {
            String saveFileNm = myFileUtils.transferTo(img, target);
            if(saveFileNm!=null){
                param.setImg(saveFileNm);
                if(profileMapper.insUserProfile(param) == 1 && loginUser.getMainProfile() == null) {
                    UserEntity param2 = new UserEntity();
                    param2.setIuser(iuser); //11
                    param2.setMainProfile(saveFileNm);

                    if(mapper.updUser(param2) == 1) {
                        loginUser.setMainProfile(saveFileNm);
                    }
                }
            }
        }
    }

    public List<UserProfileEntity> selUserProfileList(UserEntity param) {
        return profileMapper.selUserProfileList(param);
    }
    //메인 이미지 변경
    public int updUserMainProfile(UserProfileEntity param) {
        param.setIuser(auth.getLoginUserPk());
        return mapper.updUserMainProfile(param);
    }
}
