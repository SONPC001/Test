package com.sonpro.final_test.services.file;

import com.sonpro.final_test.entities.Staff;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ExcelService {
    String exportToExcel(List<Staff> data) throws IOException;
    void importFormExcel(InputStream input) throws IOException;
}
