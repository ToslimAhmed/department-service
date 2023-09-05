package com.example.departmentservice.department.controllers;

import com.example.departmentservice.base.constants.BaseConstant;
import com.example.departmentservice.base.controllers.BaseController;
import com.example.departmentservice.department.database.entities.Department;
import com.example.departmentservice.department.dataclass.DepartmentDTO;
import com.example.departmentservice.department.helper.constants.DepartmentConstant;
import com.example.departmentservice.department.helper.request.BooleanValueHolderDTO;
import com.example.departmentservice.department.helper.request.IdSetRequestBodyDTO;
import com.example.departmentservice.department.helper.request.PageableRequestBodyDTO;
import com.example.departmentservice.department.helper.request.UuidSetRequestBodyDTO;
import com.example.departmentservice.department.services.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = DepartmentConstant.ENDPOINT_DEPARTMENT)
public class DepartmentController extends BaseController<Department, DepartmentDTO> {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        super(departmentService);
        this.departmentService = departmentService;
    }

    @Transactional
    @PostMapping(path= DepartmentConstant.CREATE, produces = "application/json")
    public @ResponseBody DepartmentDTO create(@RequestBody DepartmentDTO d) {
        return departmentService.create(d);
    }

    @Transactional
    @PutMapping(path=DepartmentConstant.UPDATE, produces = "application/json")
    public @ResponseBody DepartmentDTO update(@RequestBody DepartmentDTO d) {
        return departmentService.update(d);
    }

    @GetMapping(path= DepartmentConstant.GET_LIST, produces = "application/json")
    public @ResponseBody List<DepartmentDTO> getList() {
        return departmentService.getList();
    }

    @GetMapping(path=DepartmentConstant.GET_LIST + "/{page}" + "/{size}" , produces = "application/json")
    public @ResponseBody
    Page<DepartmentDTO> getList(@PathVariable("page") int page, @PathVariable("size") int size) {
        return departmentService.getList(new PageableRequestBodyDTO() {{setPage(page); setSize(size);}});
    }

    @GetMapping(path=DepartmentConstant.GET_ACTIVE_LIST, produces = "application/json")
    public @ResponseBody
    List<DepartmentDTO> getActiveList() {
        return departmentService.getActiveList();
    }

    @GetMapping(path=DepartmentConstant.GET_BY_UUID + "/{uuid}" , produces = "application/json")
    public @ResponseBody DepartmentDTO getByUuid(@PathVariable String uuid) {
        return departmentService.getByUuid(uuid);
    }

    @PostMapping(path=DepartmentConstant.GET_BY_UUID_SET, produces = "application/json")
    public @ResponseBody List<DepartmentDTO> getByUuidSet(@RequestBody UuidSetRequestBodyDTO requestBodyDTO) {
        return departmentService.getByUuids(requestBodyDTO.getUuids());
    }

    @GetMapping(path=DepartmentConstant.GET_BY_ID + "/{id}" , produces = "application/json")
    public @ResponseBody DepartmentDTO getById(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    @PostMapping(path=DepartmentConstant.GET_BY_ID_SET, produces = "application/json")
    public @ResponseBody List<DepartmentDTO> getByIdSet(@RequestBody IdSetRequestBodyDTO requestBodyDTO) {
        return departmentService.getByIds(requestBodyDTO.getIds());
    }

    @DeleteMapping(path=DepartmentConstant.DELETE + "/{uuid}" , produces = "application/json")
    public @ResponseBody
    BooleanValueHolderDTO delete(@PathVariable String uuid) {
        return departmentService.delete(uuid);
    }
}
