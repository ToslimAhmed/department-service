package com.example.departmentservice.department.helper.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class IdSetRequestBodyDTO {

    @NotEmpty
    private Set<@NotNull Long> ids;

}
