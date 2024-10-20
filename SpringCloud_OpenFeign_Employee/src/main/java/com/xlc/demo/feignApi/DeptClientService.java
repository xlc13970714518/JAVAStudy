package com.xlc.demo.feignApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "dept-service")
public interface DeptClientService {

    @GetMapping ("/dept/getDept")
    String getDept();
}
