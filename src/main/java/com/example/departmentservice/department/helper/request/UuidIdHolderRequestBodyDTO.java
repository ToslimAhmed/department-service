package com.example.departmentservice.department.helper.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UuidIdHolderRequestBodyDTO implements IUuidIdHolderRequestBodyDTO {


    @NotNull
    private Long id;

    @NotBlank
    private String uuid;

}
