package com.xlc.spring_dynamicsources.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "tb_user")
@Data
public class UserPo {
    @TableId(value = "id")
    private Long id;

    @TableField(value = "username")
    private String userName;

    @TableField(value = "address")
    private String address;

}
