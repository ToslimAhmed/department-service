package com.example.departmentservice.department.helper.request;

import lombok.Data;

@Data
public class PageableRequestBodyDTO {

    private Integer page;
    private Integer size;
}
