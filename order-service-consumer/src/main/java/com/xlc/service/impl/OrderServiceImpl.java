package com.xlc.service.impl;

import com.xlc.gamallinterface.bean.UserAddress;
import com.xlc.gamallinterface.service.OrderService;
import com.xlc.gamallinterface.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserService userService;
    @Override
    public List<UserAddress> initOrder(Integer userId) {
        List<UserAddress> userAddressList = userService.getUserAddressList(userId);
        for (UserAddress userAddress: userAddressList) {
            System.out.println(userAddress);
        }
        System.out.println(userAddressList);
        return userAddressList;
    }
}
