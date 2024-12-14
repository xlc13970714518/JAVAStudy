package com.xlc.spring_dynamicsources.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "tb_order")
@Data
public class OrderPo {
    @TableId(value = "id")
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "name")
    private String name;

    @TableField(value = "price")
    private Double price;

    @TableField(value = "num")
    private Double num;

}
