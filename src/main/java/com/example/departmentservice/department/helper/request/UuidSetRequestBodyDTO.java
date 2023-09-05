package com.example.departmentservice.department.helper.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class UuidSetRequestBodyDTO {

    @NotEmpty
    private Set<@NotBlank String> uuids;

}
