package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.Anexo;
import net.weg.wegssm.model.entities.Beneficio;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.SecaoTI;
import net.weg.wegssm.util.DemandaUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelGeneratorService {

    public void exportDemandasBackLogToExcel(HttpServletResponse response, List<String> listaDemandas) throws IOException {
        DemandaUtil demandaUtil = new DemandaUtil();
        ArrayList<Demanda> listDemandas = new ArrayList<>();
        SecaoTI secaoTI = new SecaoTI();

        secaoTI.setNomeSecao("nome");
        secaoTI.setSiglaSecao("n");

        for (int i = 0; i < listaDemandas.size(); i++) {
            String demandaJSON = listaDemandas.get(i);
            Demanda demanda = demandaUtil.convertJsonToModelDirect(demandaJSON);
            demanda.setSecaoTI(secaoTI);
            listDemandas.add(demanda);
        }

        // Cria um workbook do Excel e uma planilha

        System.out.println("Pegou a lista: " + listDemandas.get(1).getSecaoTI().getNomeSecao());

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Demandas");

        response.setHeader("Content-Disposition", "attachment; filename=demandas.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        // Cria uma linha de cabeçalho para a planilha
        XSSFRow headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Título");
        headerRow.createCell(2).setCellValue("Problema");
        headerRow.createCell(3).setCellValue("Proposta");
        headerRow.createCell(4).setCellValue("Benefícios");
        headerRow.createCell(5).setCellValue("Frequência de Uso");
        headerRow.createCell(6).setCellValue("Anexos");

        headerRow.getCell(0).setCellStyle(style);
        headerRow.getCell(1).setCellStyle(style);
        headerRow.getCell(2).setCellStyle(style);
        headerRow.getCell(3).setCellStyle(style);
        headerRow.getCell(4).setCellStyle(style);
        headerRow.getCell(5).setCellStyle(style);
        headerRow.getCell(6).setCellStyle(style);

        int rowNum = 1;
        for (Demanda demanda : listDemandas) {
            XSSFRow row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(demanda.getId());
            row.createCell(1).setCellValue(demanda.getTitulo());
            row.createCell(2).setCellValue(demanda.getProblema());
            row.createCell(3).setCellValue(demanda.getProposta());

            int rowIndex = rowNum;

            for (Beneficio beneficio : demanda.getBeneficios()) {
                Row roww = sheet.createRow(rowIndex);
                roww.createCell(4).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + " Valor Mensal: " + beneficio.getValor_mensal() + " Moeda: " + beneficio.getMoeda() + " Memória de Cálculo: " + beneficio.getMemoriaCalculo());
                rowIndex++;
            }

            row.createCell(5).setCellValue(demanda.getFrequencia());

            for(Anexo anexo : demanda.getAnexo()){
                row.createCell(11).setCellValue(" - " + anexo.getNome() + ". " + anexo.getTipo());
            }

            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);

            rowNum = rowIndex;
        }

        // Escreve o workbook no response
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportDemandasAssessmentToExcel(HttpServletResponse response, List<String> listaDemandas) throws IOException {
        DemandaUtil demandaUtil = new DemandaUtil();
        ArrayList<Demanda> listDemandas = new ArrayList<>();
        SecaoTI secaoTI = new SecaoTI();

        secaoTI.setNomeSecao("nome");
        secaoTI.setSiglaSecao("n");

        for (int i = 0; i < listaDemandas.size(); i++) {
            String demandaJSON = listaDemandas.get(i);
            Demanda demanda = demandaUtil.convertJsonToModelDirect(demandaJSON);
            demanda.setSecaoTI(secaoTI);
            listDemandas.add(demanda);
        }

        // Cria um workbook do Excel e uma planilha

        System.out.println("Pegou a lista: " + listDemandas.get(1).getSecaoTI().getNomeSecao());

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Demandas");

        response.setHeader("Content-Disposition", "attachment; filename=demandas.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        // Cria uma linha de cabeçalho para a planilha
        XSSFRow headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Título");
        headerRow.createCell(2).setCellValue("Problema");
        headerRow.createCell(3).setCellValue("Proposta");
        headerRow.createCell(4).setCellValue("Benefícios");
        headerRow.createCell(5).setCellValue("Frequência de Uso");
        headerRow.createCell(6).setCellValue("Anexos");

        headerRow.getCell(0).setCellStyle(style);
        headerRow.getCell(1).setCellStyle(style);
        headerRow.getCell(2).setCellStyle(style);
        headerRow.getCell(3).setCellStyle(style);
        headerRow.getCell(4).setCellStyle(style);
        headerRow.getCell(5).setCellStyle(style);
        headerRow.getCell(6).setCellStyle(style);

        int rowNum = 1;
        for (Demanda demanda : listDemandas) {
            XSSFRow row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(demanda.getId());
            row.createCell(1).setCellValue(demanda.getTitulo());
            row.createCell(2).setCellValue(demanda.getProblema());
            row.createCell(3).setCellValue(demanda.getProposta());

            int rowIndex = rowNum;

            for (Beneficio beneficio : demanda.getBeneficios()) {
                Row roww = sheet.createRow(rowIndex);
                roww.createCell(4).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + " Valor Mensal: " + beneficio.getValor_mensal() + " Moeda: " + beneficio.getMoeda() + " Memória de Cálculo: " + beneficio.getMemoriaCalculo());
                rowIndex++;
            }

            row.createCell(5).setCellValue(demanda.getFrequencia());

            for(Anexo anexo : demanda.getAnexo()){
                row.createCell(11).setCellValue(" - " + anexo.getNome() + ". " + anexo.getTipo());
            }

            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);

            rowNum = rowIndex;
        }

        // Escreve o workbook no response
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
