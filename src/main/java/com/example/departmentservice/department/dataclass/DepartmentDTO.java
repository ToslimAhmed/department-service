package com.example.departmentservice.department.dataclass;

import com.example.departmentservice.department.helper.request.UuidIdHolderRequestBodyDTO;
import lombok.Data;


@Data
public class DepartmentDTO extends UuidIdHolderRequestBodyDTO {
    private String name;
    private boolean active;
}
