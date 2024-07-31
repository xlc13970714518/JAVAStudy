package com.xlc.gamallinterface.service;



import com.xlc.gamallinterface.bean.UserAddress;

import java.util.List;

/**订单服务*/
public interface OrderService {
    /**
     * 初始化订单
     * @param userId
     */
    public List<UserAddress> initOrder(Integer userId);
}
