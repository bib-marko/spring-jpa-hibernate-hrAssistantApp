package com.bib.hrassistantapp.utils;


import com.bib.hrassistantapp.model.EmailReport;
import com.bib.hrassistantapp.model.EmailSentHistory;
import com.bib.hrassistantapp.repository.EmailReportRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;


import java.util.*;

@Slf4j
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
        if (cell.getNumericCellValue() == 0){
            return null;
        }
        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper creationHelper = wb.getCreationHelper();
        cellStyle.setDataFormat((creationHelper.createDataFormat().getFormat("dd/mm/yyyy")));
        cell.setCellStyle(cellStyle);
        return cell.getDateCellValue();
    }

    public static boolean checkExcelHeaders(XSSFRow headerRow){
        List<String> headerList = new ArrayList<>(Arrays.asList("No.","Name","","Position","Contact Number",
                "Email", "Date Endorsed", "Overall Status", "Hiring Manager", "Paper Screening\n (Passed / Failed)",
                "Technical Interview Schedule", "Interview Result\n (Passed / Failed)", "Offer\n Accepted / Declined", "Offer Date","On-boarding Date", "Rejection Email"));
        Cell cell;
        for (int index = 0; index < headerRow.getPhysicalNumberOfCells(); index++) {
            cell = headerRow.getCell(index);
            String val = headerList.get(index);
            if((val.equals(cell.getStringCellValue())))
                continue;
            else{
                return false;
            }
        }
        return true;
    }

    public static Base64Util export(Long id, String filename, EmailReportRepository emailReportRepository) throws IOException {

        String fName = filename + DateUtil.currentDateTime() + ".xlsx";

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + fName;

        try (var workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Email History Report");
            for(int index = 0 ; index < 9; index++){
                sheet.autoSizeColumn(index);
                sheet.setColumnWidth(index, sheet.getColumnWidth(index)*17/10);
            }
            excelHeaders(sheet, workbook);

            CellStyle style = workbook.createCellStyle();
            style.setWrapText(true);

            Row row = sheet.createRow(1);

            Optional<EmailReport> emailReport = emailReportRepository.getEmailReportById(id);

            List<EmailSentHistory> sentHistories = emailReport.get().getEmailSentHistoryList();

            for (EmailSentHistory record : sentHistories) {
                int columnCount = 0;
                createCell(sheet , row, columnCount++, String.valueOf(record.getId()), style, workbook);
                createCell(sheet , row, columnCount++, record.getFullName(), style, workbook);
                createCell(sheet , row, columnCount++, record.getEmail(), style, workbook);
                createCell(sheet , row, columnCount++, record.getStatus(), style, workbook);
                createCell(sheet , row, columnCount++, record.getPosition(), style, workbook);
                createCell(sheet , row, columnCount++, emailReport.get().getSubject(), style, workbook);
                createCell(sheet , row, columnCount++, record.getLastFollowUpdate(), style, workbook);
                createCell(sheet , row, columnCount++, DateUtil.formatDate(record.getCreatedAt()), style, workbook);
                createCell(sheet , row, columnCount++, emailReport.get().getHr(), style, workbook);
            }

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            return convertFiletoBase64(fName);
        }

    }

    private static void createCell(Sheet sheet, Row row, int columnCount, Object valueOfCell, CellStyle style, XSSFWorkbook workbook) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof Date) {
            CreationHelper creationHelper = workbook.getCreationHelper();
            style.setDataFormat(creationHelper.createDataFormat().getFormat("dd/mm/yyyy"));
            cell.setCellValue((Date) valueOfCell);
        }
        cell.setCellStyle(style);
    }


    private static Base64Util convertFiletoBase64(String fName) throws IOException {
        String base64encoded;
        byte [] base64decoded;

        File file = new File(fName);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] buf = new byte[1024];

        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
            log.info("read " + readNum + " bytes,");
        }

        byte[] bArray = bos.toByteArray();

        base64encoded = Base64Util.encodeBase64(bArray);
        log.info(base64encoded);
        bos.close();

        base64decoded = Base64Util.decode64(base64encoded);
        log.info(String.valueOf(base64decoded));

        String converted = new String(base64decoded, StandardCharsets.UTF_8);
        log.info(converted);

        Base64Util base64 = new Base64Util();
        base64.setEncoded(base64encoded);
        base64.setDecoded(String.valueOf(base64decoded));
        return base64;
    }


    private static void excelHeaders(Sheet sheet, XSSFWorkbook workbook) {
        ArrayList<String> headers = new ArrayList<>(Arrays.asList("ID", "Full Name", "Email", "Status", "Position", "Subject", "Last Follow Up Date", "Date Emailed", "HR In-charge"));
        Row header = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = workbook.createFont();
        font.setFontName("Calibri");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        headerStyle.setFont(font);

        for (int index = 0; index < headers.size(); index++) {
            Cell headerCell = header.createCell(index);
            headerCell.setCellValue(headers.get(index));
            headerCell.setCellStyle(headerStyle);
        }
    }

}
