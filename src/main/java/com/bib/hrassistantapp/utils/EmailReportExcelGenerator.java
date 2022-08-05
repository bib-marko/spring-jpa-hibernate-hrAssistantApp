package com.bib.hrassistantapp.utils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.bib.hrassistantapp.model.EmailReport;
import com.bib.hrassistantapp.model.EmailSentHistory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmailReportExcelGenerator {

    private final Optional<EmailReport> emailReport;
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public EmailReportExcelGenerator(Optional<EmailReport> emailReport) {
        this.emailReport = emailReport;
        workbook = new XSSFWorkbook();

    }
    private void writeHeader() {
        sheet = workbook.createSheet("Email Sent History");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Full Name", style);
        createCell(row, 2, "Email", style);
        createCell(row, 3, "Status", style);
        createCell(row, 4, "Position", style);
        createCell(row, 5, "Subject", style);
        createCell(row, 6, "Last Follow Up Date", style);
        createCell(row, 7, "Date Emailed", style);
        createCell(row, 8, "HR In-charge", style);

    }
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
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
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);
        List<EmailSentHistory> sentHistories = emailReport.get().getEmailSentHistoryList();
        for (EmailSentHistory record: sentHistories) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, String.valueOf(record.getId()), style);
            createCell(row, columnCount++, record.getFullName(), style);
            createCell(row, columnCount++, record.getEmail(), style);
            createCell(row, columnCount++, record.getStatus(), style);
            createCell(row, columnCount++, record.getPosition(), style);
            createCell(row, columnCount++, emailReport.get().getSubject(), style);
            createCell(row, columnCount++, record.getLastFollowUpdate(), style);
            createCell(row, columnCount++, record.getCreatedAt(), style);
            createCell(row, columnCount++, emailReport.get().getHr(), style);
        }

    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }


}
