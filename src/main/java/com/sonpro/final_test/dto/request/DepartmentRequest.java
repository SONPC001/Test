package com.sonpro.final_test.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class DepartmentRequest implements Serializable {
    UUID id;
    @Size(max = 255)
    String code;
    @Size(max = 255)
    String name;
    Short status;
    Long createdDate;
    Long lastModifiedDate;
}