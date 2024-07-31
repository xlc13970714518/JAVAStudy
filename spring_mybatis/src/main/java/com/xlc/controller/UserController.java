package com.xlc.controller;

import com.xlc.pojo.Employee;
import com.xlc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/allEmployee")
    @ResponseBody
    List<Employee> selectAllEmployee() {
        List<Employee> employees = userService.selectAll();
        System.out.println(employees);
        System.out.println("xlc");
        return employees;
    }
    @RequestMapping("/employee")
    public String testModelView (Model model) {
        List<Employee> employees = userService.selectAll();
        model.addAttribute("employee", employees);
        return "employee";
    }
}
