package net.weg.wegssm.controller;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.service.DemandaService;
import net.weg.wegssm.model.service.PDFGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm")
public class PDFExportController {

    private final PDFGeneratorService pdfGeneratorService;

    private DemandaService demandaService;

    @GetMapping("/pdf/demanda/{id}")
    public void generatePDFDemanda(@PathVariable(value = "id") Long demandaId, HttpServletResponse response) throws IOException {
        Demanda demanda = demandaService.findById(demandaId).get();
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_demanda_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        Document document = this.pdfGeneratorService.exportDemanda(response, demanda);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        baos.flush();
        byte[] pdfData = baos.toByteArray();
        baos.close();
        response.getOutputStream().write(pdfData);
        response.getOutputStream().flush();
    }

    @GetMapping("/pdf/proposta")
    public void generatePDFProposta(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_proposta_" + currentDateTime + " .pdf";

        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.exportProposta(response);
    }

    @GetMapping("/pdf/pauta")
    public void generatePDFPauta(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_pauta_" + currentDateTime + " .pdf";

        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.exportPauta(response);
    }

    @GetMapping("/pdf/ata")
    public void generatePDFAta(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_ata_" + currentDateTime + " .pdf";

        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.exportAta(response);
    }

}
