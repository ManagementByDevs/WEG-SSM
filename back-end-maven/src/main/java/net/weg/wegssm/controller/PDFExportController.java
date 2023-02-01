package net.weg.wegssm.controller;

import net.weg.wegssm.model.service.PDFGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PDFExportController {

    private final PDFGeneratorService pdfGeneratorService;

    public PDFExportController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/demanda")
    public void generatePDFDemanda(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_demanda_" + currentDateTime + " .pdf";

        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.exportDemanda(response);
    }

    @GetMapping("/pdf/proposta")
    public void generatePDFProposta(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_proposta_" + currentDateTime + " .pdf";

        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.exportProposta(response);

    }

//    @GetMapping("/pdf/pauta")

}
