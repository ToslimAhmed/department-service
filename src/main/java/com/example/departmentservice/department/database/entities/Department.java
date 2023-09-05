package com.example.departmentservice.department.database.entities;

import com.example.departmentservice.base.entities.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "department")
public class Department extends BaseEntity {
    private String name;
    private Boolean active;
}
