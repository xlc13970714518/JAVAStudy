package com.xlc.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentVo {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("部门名称")
    @NotNull
    private String departmentName;
}
