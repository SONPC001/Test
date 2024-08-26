package com.sonpro.final_test.services.file;

import com.sonpro.final_test.entities.Staff;
import com.sonpro.final_test.repositories.StaffRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExcelServiceImpl implements ExcelService {
    private final StaffRepository repository;

    public ExcelServiceImpl(StaffRepository repository) {
        this.repository = repository;
    }

    @Override
    public String exportToExcel(List<Staff> data) throws IOException {
        String fileName;
        try (
                Workbook workbook = new XSSFWorkbook();
        ) {
            Sheet sheet = workbook.createSheet("staff"); // Tạo sheet mới
            // Tạo header row
            Row headerRow = sheet.createRow(0); // Row đầu tiên
            String[] columns = {"ID", "Staff Code", "Name", "Account FE", "Account FPT", "Status", "Created Date", "Last Modified Date"};

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i); // Tạo cell mới
                cell.setCellValue(columns[i]); // Điền dữ liệu
            }

            // Điền dữ liệu
            int rowIdx = 1;
            for (Staff staff : data) {
                Row row = sheet.createRow(rowIdx++); // Tạo row mới
                mapStaffToRow(staff, row); // Điền dữ liệu vào row
            }
            // Tạo tên file theo thời gian tạo
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd - HH-mm-ss");
            String timeString = sdf.format(new Date());
            fileName = "staffs - " + timeString + ".xlsx";

            // Xác định đường dẫn file
            File directory = new File("E:/exports");
            if (!directory.exists()) {
                directory.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }

            String filePath = "E:/exports/" + fileName; // Đường dẫn file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }
        return fileName;
    }

    @Override
    public void importFormExcel(InputStream input) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(input)) {
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            List<Staff> staffs = StreamSupport.stream(sheet.spliterator(), false)
                    .skip(1) // Bỏ qua header row
                    .map(row -> mapRowToStaff(row)) // Chuyển row thành Staff
                    .collect(Collectors.toList()); // Add vào list
            // Lưu vào database
            repository.saveAll(staffs);
        }
    }

    // Máp Staff cho row
    private void mapStaffToRow(Staff staff, Row row) {
        row.createCell(0).setCellValue(staff.getId().toString());
        row.createCell(1).setCellValue(staff.getStaffCode());
        row.createCell(2).setCellValue(staff.getName());
        row.createCell(3).setCellValue(staff.getAccountFe());
        row.createCell(4).setCellValue(staff.getAccountFpt());
        row.createCell(5).setCellValue(staff.getStatus());
        row.createCell(6).setCellValue(staff.getCreatedDate().toString());
        row.createCell(7).setCellValue(staff.getLastModifiedDate().toString());
    }

    // Máp row cho Staff
    private Staff mapRowToStaff(Row row) {
        Staff staff = new Staff();

        staff.setId(getUUIDValue(row.getCell(0)));
        staff.setStaffCode(getCellValue(row.getCell(1)));
        staff.setName(getCellValue(row.getCell(2)));
        staff.setAccountFe(getCellValue(row.getCell(3)));
        staff.setAccountFpt(getCellValue(row.getCell(4)));
        staff.setStatus(getShortValue(row.getCell(5)));
        staff.setCreatedDate(getLongValue(row.getCell(6)));
        staff.setLastModifiedDate(getLongValue(row.getCell(7)));

        return staff;
    }

    private UUID getUUIDValue(Cell cell) {
        return cell != null ? UUID.fromString(getCellValue(cell)) : null;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }

    private short getShortValue(Cell cell) {
        if (cell == null) return 0;
        switch (cell.getCellType()) {
            case STRING:
                try {
                    return Short.parseShort(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0;
                }
            case NUMERIC:
                return (short) cell.getNumericCellValue();
            default:
                return 0;
        }
    }

    private long getLongValue(Cell cell) {
        if (cell == null) return 0L;
        switch (cell.getCellType()) {
            case STRING:
                try {
                    return Long.parseLong(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0L;
                }
            case NUMERIC:
                return (long) cell.getNumericCellValue();
            default:
                return 0L;
        }
    }

}
