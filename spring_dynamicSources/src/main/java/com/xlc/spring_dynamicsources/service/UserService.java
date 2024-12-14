package com.xlc.spring_dynamicsources.service;

import com.xlc.spring_dynamicsources.model.po.UserPo;

import java.util.List;

public interface UserService {
    List<UserPo> selectPage(Integer pageNum, Integer pageSize);
}
