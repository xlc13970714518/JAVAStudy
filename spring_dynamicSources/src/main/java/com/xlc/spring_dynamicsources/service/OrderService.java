package com.xlc.spring_dynamicsources.service;

import com.xlc.spring_dynamicsources.model.po.OrderPo;

import java.util.List;

public interface OrderService {
    List<OrderPo> selectByPage(Integer pageNum, Integer pageSize);
}
