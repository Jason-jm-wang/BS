package com.example.springboot.Service.implementation;

import com.example.springboot.entity.User;
import com.example.springboot.Mapper.UserMapper;
import com.example.springboot.Service.IUserService;
import com.example.springboot.controller.request.UserPageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.listUsers();
    }

    @Override
    public Object page(UserPageRequest userPageRequest){
        PageHelper.startPage(userPageRequest.getPageNumber(),userPageRequest.getPageSize());
        List<User> users = userMapper.listByCondition(userPageRequest);
        return new PageInfo<>(users);
    }
}
