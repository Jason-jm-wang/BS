package com.example.springboot.Mapper;

import java.util.List;
import com.example.springboot.entity.User;
import com.example.springboot.controller.request.UserPageRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // @Select("select * from user")
    List<User> listUsers();

    List<User> listByCondition(UserPageRequest userPageRequest);
}
