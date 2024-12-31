package com.example.springboot.Service;

import com.example.springboot.entity.User;
import com.example.springboot.controller.request.UserPageRequest;

import java.util.List;

public interface IUserService {

    List<User> listUsers();

    Object page(UserPageRequest userPageRequest);

}
