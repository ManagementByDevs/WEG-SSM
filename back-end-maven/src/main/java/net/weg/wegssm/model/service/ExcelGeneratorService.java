package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Demanda;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelGeneratorService {

    private static DemandaService demandaService;

    public File exportToExcel(List<Demanda> listaDemanda) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Demandas");

        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeight(16);
        headerStyle.setFont(headerFont);

        createCell(headerRow, 0, "Título", headerStyle);
        createCell(headerRow, 1, "Proposta", headerStyle);
        createCell(headerRow, 2, "Problema", headerStyle);
        createCell(headerRow, 3, "Solicitante", headerStyle);

        int rowNum = 1;
        CellStyle rowStyle = workbook.createCellStyle();
        XSSFFont rowFont = workbook.createFont();
        rowFont.setFontHeight(14);
        rowStyle.setFont(rowFont);

        for (Demanda demanda : listaDemanda) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            createCell(row, colNum++, demanda.getTitulo(), rowStyle);
            createCell(row, colNum++, demanda.getProposta(), rowStyle);
            createCell(row, colNum++, demanda.getProblema(), rowStyle);
            createCell(row, colNum++, demanda.getSolicitante(), rowStyle);
        }

        File file = File.createTempFile("demandas", ".xlsx");
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

        return file;
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
//        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

}
