package com.xlc.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployeeVo {
    @ApiModelProperty("id")
    private Long id;

    @NotNull
    @ApiModelProperty("姓名")
    private String lastName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty(name = "性别")
    private String gender;

    @ApiModelProperty("部门")
    private DepartmentVo department;

    @ApiModelProperty("生日")
    private String birth;
}
