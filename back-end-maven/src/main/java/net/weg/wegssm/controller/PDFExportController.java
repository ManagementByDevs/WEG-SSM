package net.weg.wegssm.controller;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.service.DemandaService;
import net.weg.wegssm.model.service.PDFGeneratorService;
import net.weg.wegssm.model.service.PropostaService;
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
    private PropostaService propostaService;

    @GetMapping("/pdf/escopo")
    public void generatePDFEscopo(@RequestBody Demanda demanda, HttpServletResponse response) throws IOException {
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

    @GetMapping("/pdf/proposta/{id}")
    public void generatePDFProposta(@PathVariable(value = "id") Long propostaId, HttpServletResponse response) throws IOException {
        Proposta proposta = propostaService.findById(propostaId).get();
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_proposta_" + currentDateTime + " .pdf";

        response.setHeader(headerKey, headerValue);

        System.out.println("Ainda nao deu erro");

        Document document = this.pdfGeneratorService.exportProposta(response, proposta);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        baos.flush();
        byte[] pdfData = baos.toByteArray();
        baos.close();
        response.getOutputStream().write(pdfData);
        response.getOutputStream().flush();
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
