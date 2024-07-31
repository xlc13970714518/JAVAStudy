package com.xlc.service.impl;

import com.xlc.mapper.UserMapper;
import com.xlc.pojo.Employee;
import com.xlc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Employee> selectAll() {
        return userMapper.selectAll();
    }
}
