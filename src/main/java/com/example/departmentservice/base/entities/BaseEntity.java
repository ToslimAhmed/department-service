package com.example.departmentservice.base.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String uuid;

    @NotNull
    private Boolean isDeleted;

    @NotNull
    private String createdBy;

    @NotNull
    private LocalDate createdOn;

    private String updatedBy;

    private LocalDate updatedOn;

}
