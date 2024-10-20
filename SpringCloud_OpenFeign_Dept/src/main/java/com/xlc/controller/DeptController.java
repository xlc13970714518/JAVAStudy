package com.xlc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dept")
public class DeptController {
    @RequestMapping("/getDept")
    @ResponseBody
    String getDept() {
        return "dept";
    }
}
