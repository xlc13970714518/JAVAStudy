package com.xlc.demo.controller;

import com.xlc.demo.feignApi.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private DeptClientService deptClientService;

    @RequestMapping("/getDept")
    public String getDept()
    {
        return deptClientService.getDept();
    }

}
