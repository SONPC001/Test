package com.sonpro.final_test.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class StaffRequest implements Serializable {
    UUID id;
    Short status;
    Long createdDate;
    Long lastModifiedDate;
    @Size(max = 255)
    @Email(message = "Email phải có đuôi @fe.edu.vn", regexp = "^[A-Za-z0-9]+@fe\\.edu\\.vn$")
    private String accountFe;

    @Size(max = 255)
    @Email(message = "Email phải có đuôi @fpt.edu.vn", regexp = "^[A-Za-z0-9]+@fpt\\.edu\\.vn$")
    private String accountFpt;

    @Size(max = 255)
    @NotBlank(message = "Không để trống name!")
    String name;
    @Size(max = 255)
    String staffCode;
}