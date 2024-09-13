package com.xlc.domain.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private String userName;
    private Integer userAge;
}
