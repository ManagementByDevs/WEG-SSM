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

@AllArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm")
public class ExcelExportController {

    private ExcelGeneratorService excelGeneratorService;

    @PostMapping("/excel/demandas_backlog")
    public void exportDemandasBackLogToExcel(HttpServletResponse response, @RequestParam("demandas_backlog") List<String> listaDemandas) throws IOException {
        excelGeneratorService.exportDemandasBackLogToExcel(response, listaDemandas);
    }

    @PostMapping("/excel/demandas_assessment")
    public void exportDemandasAssessmentToExcel(HttpServletResponse response, @RequestParam("demandas_assessment") List<String> listaDemandas) throws IOException {
        excelGeneratorService.exportDemandasAssessmentToExcel(response, listaDemandas);
    }

    @PostMapping("/excel/propostas")
    public void exportPropostasToExcel(HttpServletResponse response, @RequestParam("propostas") List<String> listaPropostas) throws IOException {
        excelGeneratorService.exportPropostasToExcel(response, listaPropostas);
    }

    @PostMapping("/excel/pautas")
    public void exportPautasToExcel(HttpServletResponse response, @RequestParam("pautas") List<String> listaPautas) throws IOException {
        excelGeneratorService.exportPautasToExcel(response, listaPautas);
    }

    @PostMapping("/excel/atas")
    public void exportAtasToExcel(HttpServletResponse response, @RequestParam("atas") List<String> listaAtas) throws IOException {
        excelGeneratorService.exportAtasToExcel(response, listaAtas);
    }

}
