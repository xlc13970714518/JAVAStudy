package com.xlc.gamallinterface.service;



import com.xlc.gamallinterface.bean.UserAddress;

import java.util.List;

/**用户服务*/
public interface UserService {
    /**按用户id查找所有的用户地址
     *
     * @param userId
     */
    public List<UserAddress> getUserAddressList(Integer userId);
}
