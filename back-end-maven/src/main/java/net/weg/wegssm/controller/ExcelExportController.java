package net.weg.wegssm.controller;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.model.service.DemandaService;
import net.weg.wegssm.model.service.ExcelGeneratorService;
import net.weg.wegssm.util.DemandaUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe controller para exportar os dados para excel
 */
@AllArgsConstructor
@RestController
@RequestMapping("/weg_ssm")
public class ExcelExportController {

    /**
     * Service do excel
     */
    private ExcelGeneratorService excelGeneratorService;

    /**
     * Método POST para exportar as demandas do backlog para excel
     *
     * @param response
     * @param listaDemandas
     * @throws IOException
     */
    @PostMapping("/excel/demandas_backlog")
    public void exportDemandasBackLogToExcel(HttpServletResponse response, @RequestParam("demandas_backlog") List<Long> listaDemandas) throws IOException {
        excelGeneratorService.exportDemandasBackLogToExcel(response, listaDemandas);
    }

    /**
     * Método POST para exportar as demandas do assessment para excel
     *
     * @param response
     * @param listaDemandas
     * @throws IOException
     */
    @PostMapping("/excel/demandas_assessment")
    public void exportDemandasAssessmentToExcel(HttpServletResponse response, @RequestParam("demandas_assessment") List<Long> listaDemandas) throws IOException {
        excelGeneratorService.exportDemandasAssessmentToExcel(response, listaDemandas);
    }

    /**
     * Método POST para exportar as propostas para excel
     *
     * @param response
     * @param listaPropostas
     * @throws IOException
     */
    @PostMapping("/excel/propostas")
    public void exportPropostasToExcel(HttpServletResponse response, @RequestParam("propostas") List<Long> listaPropostas) throws IOException {
        excelGeneratorService.exportPropostasToExcel(response, listaPropostas);
    }

    /**
     * Método POST para exportar as pautas para excel
     *
     * @param response
     * @param listaPautas
     * @throws IOException
     */
    @PostMapping("/excel/pautas")
    public void exportPautasToExcel(HttpServletResponse response, @RequestParam("pautas") List<Long> listaPautas) throws IOException {
        excelGeneratorService.exportPautasToExcel(response, listaPautas);
    }

    /**
     * Método POST para exportar as atas para excel
     *
     * @param response
     * @param listaAtas
     * @throws IOException
     */
    @PostMapping("/excel/atas")
    public void exportAtasToExcel(HttpServletResponse response, @RequestParam("atas") List<Long> listaAtas) throws IOException {
        excelGeneratorService.exportAtasToExcel(response, listaAtas);
    }

}
