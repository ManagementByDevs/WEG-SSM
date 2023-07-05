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

/**
 * Controller para exportar pdfs
 */
@AllArgsConstructor
@RestController
@RequestMapping("/weg_ssm")
public class PDFExportController {

    /**
     * Service do gerador de pdf
     */
    private final PDFGeneratorService pdfGeneratorService;

    /**
     * Service da demanda
     */
    private DemandaService demandaService;

    /**
     * Service da proposta
     */
    private PropostaService propostaService;

    /**
     * Service da pauta
     */
    private PautaService pautaService;

    /**
     * Service da ata
     */
    private AtaService ataService;

    /**
     * Método GET para gerar um pdf de uma demanda
     *
     * @param demandaId - Id da demanda
     * @param response  - Resposta da requisição web
     * @throws IOException       - Exceção de entrada e saída
     * @throws DocumentException - Exceção de documento
     */
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

    /**
     * Método GET para gerar o pdf de uma proposta
     *
     * @param propostaId - Id da proposta
     * @param response   - Resposta da requisição web
     * @throws IOException       - Exceção de entrada e saída
     * @throws DocumentException - Exceção de documento
     */
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

    /**
     * Método GET para gerar o pdf de uma pauta
     *
     * @param idPauta  - Id da pauta
     * @param response - Resposta da requisição web
     * @throws IOException       - Exceção de entrada e saída
     * @throws DocumentException - Exceção de documento
     */
    @GetMapping("/pdf/pauta/{id}")
    public void generatePDFPauta(@PathVariable(value = "id") Long idPauta, HttpServletResponse response) throws IOException, DocumentException {
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

    /**
     * Método GET para gerar o pdf de uma ata
     *
     * @param idAta    - Id da ata
     * @param response - Resposta da requisição web
     * @throws IOException       - Exceção de entrada e saída
     * @throws DocumentException - Exceção de documento
     */
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