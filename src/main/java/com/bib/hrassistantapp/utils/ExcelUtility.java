package com.bib.hrassistantapp.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ExcelUtility {

    public static int convertStringToInt(String str) {
        int result = 0;
        if (str == null || str.isEmpty() || str.trim().isEmpty()) {
            return result;
        }
        result = Integer.parseInt(str);
        return result;
    }
    public static String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }


    public static Date getDateValue(XSSFWorkbook wb, Row row, int cellNo) {
        Cell cell = row.getCell(cellNo);
        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper creationHelper = wb.getCreationHelper();
        cellStyle.setDataFormat((creationHelper.createDataFormat().getFormat("dd/mm/yyyy")));
        cell.setCellStyle(cellStyle);;
        return cell.getDateCellValue();

    }

    public static boolean checkExcelHeaders(XSSFRow headerRow){
        List<String> headerList = new ArrayList<>(Arrays.asList("uuid","full_name","position","contact_number","email",
                "date_endorsed", "overall_status", "hiring_manager", "paper_screening_status", "tech_interview_schedule",
                "interview_result", "offer_status", "offer_date", "is_rejection_email_sent","onboarding_date", "updated_at", "created_at"));
        Cell cell;
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            cell = headerRow.getCell(i);
            String val = headerList.get(i);
            if((val.equals(cell.getStringCellValue())))
                continue;
            else{
                return false;
            }
        }
        return true;
    }
}
