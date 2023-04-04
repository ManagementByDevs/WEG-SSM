package net.weg.wegssm.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.DemandaDTO;
import net.weg.wegssm.model.entities.Ata;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.Pauta;
import net.weg.wegssm.model.entities.Proposta;
import net.weg.wegssm.model.service.*;
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
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/weg_ssm")
public class PDFExportController {

    private final PDFGeneratorService pdfGeneratorService;

    private DemandaService demandaService;
    private PropostaService propostaService;
    private PautaService pautaService;
    private AtaService ataService;

    @GetMapping("/pdf/demanda/{id}")
    public void generatePDFDemanda(@PathVariable(value = "id") Long demandaId, HttpServletResponse response) throws IOException, DocumentException {
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
    public void generatePDFProposta(@PathVariable(value = "id") Long propostaId, HttpServletResponse response) throws IOException, DocumentException {
        Proposta proposta = propostaService.findById(propostaId).get();
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_proposta_" + currentDateTime + " .pdf";

        response.setHeader(headerKey, headerValue);

        Document document = this.pdfGeneratorService.exportProposta(response, proposta);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        baos.flush();
        byte[] pdfData = baos.toByteArray();
        baos.close();
        response.getOutputStream().write(pdfData);
        response.getOutputStream().flush();
    }

    @GetMapping("/pdf/pauta/{id}")
    public void generatePDFPauta(@PathVariable(value = "id") Long idPauta,  HttpServletResponse response) throws IOException, DocumentException {
        Pauta pauta = pautaService.findById(idPauta).get();
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_pauta_" + currentDateTime + " .pdf";

        response.setHeader(headerKey, headerValue);

        Document document = this.pdfGeneratorService.exportPauta(response, pauta);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        baos.flush();
        byte[] pdfData = baos.toByteArray();
        baos.close();
        response.getOutputStream().write(pdfData);
        response.getOutputStream().flush();
    }

    @GetMapping("/pdf/ata/{id}")
    public void generatePDFAta(@PathVariable(value = "id") Long idAta, HttpServletResponse response) throws IOException, DocumentException {
        Ata ata = ataService.findById(idAta).get();
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_ata_" + currentDateTime + " .pdf";

        response.setHeader(headerKey, headerValue);

        Document document = this.pdfGeneratorService.exportAta(response, ata);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        baos.flush();
        byte[] pdfData = baos.toByteArray();
        baos.close();
        response.getOutputStream().write(pdfData);
        response.getOutputStream().flush();
    }

}
