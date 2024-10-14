package com.xlc.domain.controller;

import com.xlc.demo.util.ApiResult;
import com.xlc.domain.service.EmployeeService;
import com.xlc.domain.vo.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequestMapping( "/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/detail")
    public EmployeeVo getEmployeeById(@Param("id") Long id){
        return employeeService.findEmployeeById(id);
    }
    
    @PostMapping("/save")
    ApiResult<Long> saveEmployee(@Valid @RequestBody EmployeeVo employeeVo){
        return ApiResult.success(employeeService.addOrUpdateEmployee(employeeVo));
    }

    @GetMapping("/list")
    public ApiResult<Map<String, Object>> list(@NotNull Integer pageNum, @NotNull Integer pageSize){
        return ApiResult.success(employeeService.findEmployeeList(pageNum, pageSize));
    }
}
