package com.koreait.facebook.user;


import com.koreait.facebook.user.model.UserEntity;
import com.koreait.facebook.user.model.UserProfileEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int join(UserEntity param);
    int setAuth(UserEntity param);
    UserEntity selUser(UserEntity param);
    int updUser(UserEntity param);
    int updUserMainProfile(UserProfileEntity param);
}
