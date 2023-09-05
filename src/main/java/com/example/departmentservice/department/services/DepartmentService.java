package com.example.departmentservice.department.services;

import com.example.departmentservice.base.services.BaseService;
import com.example.departmentservice.department.database.entities.Department;
import com.example.departmentservice.department.database.repositories.DepartmentRepository;
import com.example.departmentservice.department.dataclass.DepartmentDTO;
import com.example.departmentservice.department.helper.config.IdGeneratorComponent;
import com.example.departmentservice.department.helper.exception.ServiceExceptionHolder;
import com.example.departmentservice.department.helper.request.BooleanValueHolderDTO;
import com.example.departmentservice.department.helper.request.PageableRequestBodyDTO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class DepartmentService extends BaseService<Department, DepartmentDTO> {

    private final DepartmentRepository repository;
    private final IdGeneratorComponent idGeneratorComponent;

    protected DepartmentService(DepartmentRepository repository, IdGeneratorComponent idGeneratorComponent) {
        super(repository);
        this.repository = repository;
        this.idGeneratorComponent = idGeneratorComponent;
    }

    public DepartmentDTO create(DepartmentDTO d) {
        validateDepartment(d);
        return convertForRead(createEntity(d));
    }

    protected Department createEntity(DepartmentDTO body) {
        Department e = convertForCreate(body);
        e.setUuid(idGeneratorComponent.generateUUID());
        e.setCreatedBy("user");
        e.setCreatedOn(LocalDate.now());
        e.setIsDeleted(false);
        return repository.save(e);
    }

    public DepartmentDTO update(DepartmentDTO d) {
        validateDepartment(d);
        return convertForRead(updateEntity(d));
    }

    protected Department updateEntity(DepartmentDTO body) {
        String uuid = body.getUuid();
        if (uuid == null)
            throw new ServiceExceptionHolder.ResourceNotFoundDuringWriteRequestException("No Id Provided for Department");
        Department e = getByUuidForRead(uuid);
        body.setId(e.getId());
        convertForUpdate(body, e);
        e.setUpdatedBy("user");
        e.setUpdatedOn(LocalDate.now());
        e.setIsDeleted(false);
        return repository.save(e);
    }

    public List<DepartmentDTO> getList() {
        return convertForRead(repository.findAllByIsDeletedOrderByIdDesc(false));
    }

    public Page<DepartmentDTO> getList(PageableRequestBodyDTO requestBodyDTO) {
        Pageable pageable = this.getPageable(requestBodyDTO);
        Page<Department> ePage = repository.findAllByIsDeletedOrderByIdDesc(false, pageable);
        return new PageImpl<>(convertForRead(ePage.getContent()), pageable, ePage.getTotalElements());
    }

    public List<DepartmentDTO> getActiveList() {
        List<Department> list = repository.findAllByActiveAndIsDeletedOrderByIdDesc(true, false);
        return convertForRead(list);
    }

    public DepartmentDTO getByUuid(@NonNull String uuid) {
        return convertForRead(repository.findByUuidAndIsDeleted(uuid, false)
                .orElseThrow(() -> new ServiceExceptionHolder.ResourceNotFoundDuringWriteRequestException(
                        "No Department Found with UUID: " + uuid)));
    }

    public List<DepartmentDTO> getByUuids(Set<String> uuids) {
        List<Department> list = repository.findAllByUuidInAndIsDeleted(uuids,false);
        if (list.isEmpty()) return new ArrayList<>();
        return convertForRead(list);
    }

    public DepartmentDTO getById(@NonNull Long id) {
        return convertForRead(repository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new ServiceExceptionHolder.ResourceNotFoundDuringWriteRequestException(
                        "No Department Found with ID: " + id)));
    }

    public List<DepartmentDTO> getByIds(Set<Long> ids) {
        List<Department> list = repository.findAllByIdInAndIsDeleted(ids,false);
        if (list.isEmpty()) return new ArrayList<>();
        return convertForRead(list);
    }

    public BooleanValueHolderDTO delete(@NonNull String uuid) {
        deleteEntity(uuid);
        return new BooleanValueHolderDTO() {{ setValue(true); }};
    }

    protected void deleteEntity(@NonNull String uuid) {
        if (uuid == null)
            throw new ServiceExceptionHolder.ResourceNotFoundDuringWriteRequestException("No Id Provided for Department");
        Department e = getByUuidForRead(uuid);
        e.setIsDeleted(true);
        e.setUpdatedOn(LocalDate.now());
        e.setUpdatedBy("user");
        repository.save(e);
    }

    public Department getByUuidForRead(@NonNull String uuid) {
        return repository.findByUuidAndIsDeleted(uuid,false).orElse(null);
    }

    private void validateDepartment(DepartmentDTO requestDTO) {
        if (isEmpty(requestDTO.getName())){
            throw new ServiceExceptionHolder.InvalidRequestException("Department name cannot be null");
        }
    }

    /*private void validateDepartmentDelete(@NonNull String uuid) {
        if (empServiceClientHelper.isExistDepartmentByOid(uuid)){
            throw new ServiceExceptionHolder.InvalidRequestException("Cannot delete the Department, employee exist in this department");
        }
    }*/

    public boolean isEmpty(String value) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }
        return false;
    }
}

