package com.xlc.spring_dynamicsources.service.iml;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xlc.spring_dynamicsources.Dao.OrderMapper;
import com.xlc.spring_dynamicsources.model.po.OrderPo;
import com.xlc.spring_dynamicsources.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@DS("primary")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<OrderPo> selectByPage(Integer pageNum, Integer pageSize) {
        return orderMapper.selectPage(new Page<>(pageNum, pageSize), null).getRecords();
    }
}