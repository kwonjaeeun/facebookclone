package com.koreait.facebook.security;
import com.koreait.facebook.user.UserMapper;
import com.koreait.facebook.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper mapper;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity param= new UserEntity();
        param.setEmail(email);
        UserEntity loginUser = mapper.selUser(param);
        if(loginUser==null){
            return null;
        }
        return new UserDetailsImpl(loginUser);
    }
}
