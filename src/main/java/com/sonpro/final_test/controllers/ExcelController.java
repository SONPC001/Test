
package com.sonpro.final_test.controllers;

import com.sonpro.final_test.entities.Staff;
import com.sonpro.final_test.services.file.ExcelService;
import com.sonpro.final_test.services.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    private final ExcelService excelService;
    private final StaffService staffService;

    public ExcelController(ExcelService excelService, StaffService staffService) {
        this.excelService = excelService;
        this.staffService = staffService;
    }

    @GetMapping("export/form/staff")
    public ResponseEntity<?> exportStaffToExcel(@RequestParam("ajax") boolean ajax) {
        try {
            List<Staff> staffs = staffService.getAllStaff();
            String filename = excelService.exportToExcel(staffs); // Lưu file và trả về tên file

            if (ajax) {
                return ResponseEntity.ok("Export successfully: " + filename);
            } else {
                // Redirect nếu không phải AJAX request
                return ResponseEntity.ok("redirect:/staffs");
            }
        } catch (IOException e) {
            if (ajax) {
                return ResponseEntity.status(500).body("Export failed");
            } else {
                // Redirect nếu không phải AJAX request
                return ResponseEntity.status(500).body("redirect:/staffs");
            }
        }
    }

    @PostMapping("import/to/staff")
    public ResponseEntity<?> importExcelToStaff(
            @RequestParam("file") MultipartFile file,
            @RequestParam("ajax") boolean ajax
    ) {
        // In thông tin file ra console để debug
        System.out.println("Received file: " + file.getOriginalFilename());
        System.out.println("Content Type: " + file.getContentType());
        System.out.println("File Size: " + file.getSize());

        if (file.isEmpty()) {
            return ResponseEntity.status(400).body("File is empty");
        }

        String contentType = file.getContentType();
        if (!contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return ResponseEntity.status(400).body("Invalid file type. \nPlease upload an Excel file.");
        }

        long maxSize = 5 * 1024 * 1024; // 5MB
        if (file.getSize() > maxSize) {
            return ResponseEntity.status(400).body("File is too large. \nPlease upload a smaller file.");
        }

        try {
            excelService.importFormExcel(file.getInputStream());
            if (ajax) {
                return ResponseEntity.ok("Import successfully " + file.getOriginalFilename());
            } else {
                return ResponseEntity.ok("redirect:/staffs");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Import failed");
        }
    }

}
