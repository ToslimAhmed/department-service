package com.example.departmentservice.department.database.repositories;

import com.example.departmentservice.base.repositories.ServiceRepository;
import com.example.departmentservice.department.database.entities.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentRepository extends ServiceRepository<Department> {
    List<Department> findAllByIsDeletedOrderByIdDesc(Boolean isDeleted);
    Page<Department> findAllByIsDeletedOrderByIdDesc(Boolean isDeleted, Pageable pageable);
    List<Department> findAllByActiveAndIsDeletedOrderByIdDesc(Boolean active, Boolean isDeleted);
    List<Department> findAllByIsDeletedOrderByCreatedOnDesc(String isDeleted);
}
