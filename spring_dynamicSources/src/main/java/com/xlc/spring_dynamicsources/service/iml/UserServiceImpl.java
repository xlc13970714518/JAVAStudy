package com.xlc.spring_dynamicsources.service.iml;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xlc.spring_dynamicsources.Dao.UserMapper;
import com.xlc.spring_dynamicsources.model.po.UserPo;
import com.xlc.spring_dynamicsources.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@DS("secondary")
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<UserPo> selectPage(Integer pageNum, Integer pageSize) {
        return userMapper.selectPage(new Page<>(pageNum, pageSize), null).getRecords();
    }
}
