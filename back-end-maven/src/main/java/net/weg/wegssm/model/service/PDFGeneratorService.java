package net.weg.wegssm.model.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import net.weg.wegssm.model.entities.Beneficio;
import net.weg.wegssm.model.entities.Demanda;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Service
public class PDFGeneratorService {

    private static DemandaService demandaService;

    public void export(HttpServletResponse response) throws IOException {

        Demanda demanda = new Demanda();

        demanda.setFrequencia("Aqui vai a frequencia");
        demanda.setProposta("Aqui vai a proposta");
        demanda.setTitulo("Título");

        // Criando a página do pdf
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        document.setMarginMirroring(true);

        // Criando a formatação da página pdf

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(21);
        fontTitle.setColor(Color.decode("#00579D"));
        fontTitle.setStyle(Font.BOLD);

        Paragraph paragraph = new Paragraph(demanda.getTitulo(), fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(18);
        fontParagraph.setStyle(Font.BOLD);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(18);
        fontParagraph2.setStyle(Font.BOLD);

        Font fontParagraph3 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(16);

        Paragraph paragraph2 = new Paragraph("Problema: ", fontParagraph2);
        paragraph2.setAlignment(Paragraph.ALIGN_BASELINE);

        Paragraph paragraph3 = new Paragraph(demanda.getProblema(), fontParagraph3);
        paragraph3.setAlignment(Paragraph.ALIGN_BASELINE);

        Paragraph paragraph4 = new Paragraph("Proposta: ", fontParagraph2);
        paragraph4.setAlignment(Paragraph.ALIGN_JUSTIFIED);

        Paragraph paragraph5 = new Paragraph(demanda.getProposta(), fontParagraph3);
        paragraph5.setAlignment(Paragraph.ALIGN_JUSTIFIED);

        Paragraph paragraph6 = new Paragraph("Benefícios: ", fontParagraph2);
        paragraph6.setAlignment(Paragraph.ANCHOR);


        // Criando tabela para os centros de custo
        PdfPTable table = new PdfPTable(4);

        table.setWidthPercentage(100);
        table.setWidths(new int[]{3, 3, 3, 3});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.WHITE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.BLACK);

        cell.setPhrase(new Phrase("Tipo", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Valor Mensal", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Moeda", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Memória de Cálculo", font));
        table.addCell(cell);

        for (Beneficio beneficio : demanda.getBeneficios()) {
            table.addCell(String.valueOf(beneficio.getTipoBeneficio()));
            table.addCell(String.valueOf(beneficio.getValor_mensal()));
            table.addCell(String.valueOf(beneficio.getMoeda()));
            table.addCell(String.valueOf(beneficio.getMemoriaCalculo()));
        }

        Paragraph paragraph7 = new Paragraph("Frequência de Uso: ", fontParagraph2);
        paragraph7.setAlignment(Paragraph.ANCHOR);

        Paragraph paragraph8 = new Paragraph(demanda.getFrequencia(), fontParagraph3);
        paragraph8.setAlignment(Paragraph.ANCHOR);

        Paragraph paragraph9 = new Paragraph("Anexos: ", fontParagraph2);
        paragraph9.setAlignment(Paragraph.ANCHOR);

        Paragraph paragraph10 = new Paragraph(String.valueOf(demanda.getAnexo()), fontParagraph3);
        paragraph10.setAlignment(Paragraph.ANCHOR);

        // Adicionando tudo na página pdf


        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(paragraph6);
        document.add(table);
        document.add(paragraph7);
        document.add(paragraph8);
        document.add(paragraph9);
        document.add(paragraph10);

        document.close();
    }

}
