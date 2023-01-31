package net.weg.wegssm.model.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import net.weg.wegssm.model.entities.Beneficio;
import net.weg.wegssm.model.entities.Demanda;
import net.weg.wegssm.model.entities.TipoBeneficio;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class PDFGeneratorService {

    private static DemandaService demandaService;

    public void export(HttpServletResponse response) throws IOException {

        Demanda demanda = new Demanda();
        Beneficio beneficio1 = new Beneficio();
        Beneficio beneficio2 = new Beneficio();
        Beneficio beneficio3 = new Beneficio();
        List<Beneficio> listaBeneficios = new ArrayList<>();

        TipoBeneficio tipoBeneficio1 = TipoBeneficio.QUALITATIVO;
        TipoBeneficio tipoBeneficio2 = TipoBeneficio.POTENCIAL;
        TipoBeneficio tipoBeneficio3 = TipoBeneficio.REAL;

        beneficio1.setTipoBeneficio(tipoBeneficio1);
        beneficio1.setMoeda("BR");
        beneficio1.setMemoriaCalculo("Memória de cálculo");
        beneficio1.setValor_mensal(2.00);

        beneficio2.setTipoBeneficio(tipoBeneficio2);
        beneficio2.setMoeda("BR");
        beneficio2.setMemoriaCalculo("Memória de cálculo 2");
        beneficio2.setValor_mensal(4.00);

        beneficio3.setTipoBeneficio(tipoBeneficio3);
        beneficio3.setMoeda("BR");
        beneficio3.setMemoriaCalculo("Memória de cálculo 3");
        beneficio3.setValor_mensal(6.00);

        listaBeneficios.add(beneficio1);
        listaBeneficios.add(beneficio2);
        listaBeneficios.add(beneficio3);

        demanda.setFrequencia("Muito Alta");
        demanda.setProposta("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. ");
        demanda.setTitulo("A Falha na Procura de Demandas");
        demanda.setProblema("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
        demanda.setBeneficios(listaBeneficios);

        // Criando a página do pdf

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Criando a formatação da página pdf

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(24);
        fontTitle.setColor(Color.decode("#00579D"));
        fontTitle.setStyle(Font.BOLD);

        Paragraph paragraph = new Paragraph(demanda.getTitulo(), fontTitle);
        paragraph.setSpacingBefore(20);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(18);
        fontParagraph.setStyle(Font.BOLD);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(18);
        fontParagraph2.setStyle(Font.BOLD);

        Font fontParagraph3 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(15);

        Paragraph paragraph2 = new Paragraph("Problema: ", fontParagraph2);
        paragraph2.setSpacingBefore(20);

        Paragraph paragraph3 = new Paragraph(demanda.getProblema(), fontParagraph3);
        paragraph3.setIndentationLeft(40);
        paragraph3.setSpacingBefore(5);

        Paragraph paragraph4 = new Paragraph("Proposta: ", fontParagraph2);
        paragraph4.setSpacingBefore(15);

        Paragraph paragraph5 = new Paragraph(demanda.getProposta(), fontParagraph3);
        paragraph5.setIndentationLeft(40);
        paragraph5.setSpacingBefore(5);

        Paragraph paragraph6 = new Paragraph("Benefícios: ", fontParagraph2);
        paragraph6.setSpacingBefore(15);

        Image img = Image.getInstance("C:\\Users\\matheus_hohmann\\Documents\\GitHub\\WEG-SSM\\back-end-maven\\src\\main\\java\\net\\weg\\wegssm\\images\\logo-pequeno.png");

        int indentation = 0;
        float scale = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - indentation) / img.getWidth()) * 20;

        img.scalePercent(scale);
        img.setAbsolutePosition(460, 740);

        document.add(img);
        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(paragraph6);

        // Criando tabela para os centros de custo

        for (Beneficio beneficio : demanda.getBeneficios()) {
            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 3, 3, 3});
            table.setSpacingBefore(15);

            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(Color.decode("#00579D"));
            cell.setPadding(5);
            cell.setBorder(0);

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(CMYKColor.WHITE);
            font.setStyle(Font.BOLD);
            font.setSize(12);

            cell.setPhrase(new Phrase("Tipo", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Valor Mensal", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Moeda", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Memória de Cálculo", font));
            table.addCell(cell);

            table.addCell(String.valueOf(beneficio.getTipoBeneficio()));
            table.addCell(String.valueOf(beneficio.getValor_mensal()));
            table.addCell(String.valueOf(beneficio.getMoeda()));
            table.addCell(String.valueOf(beneficio.getMemoriaCalculo()));

            document.add(table);
        }

        Paragraph paragraph7 = new Paragraph("Frequência de Uso: ", fontParagraph2);
        paragraph7.setAlignment(Paragraph.ANCHOR);
        paragraph7.setSpacingBefore(15);

        Paragraph paragraph8 = new Paragraph(demanda.getFrequencia(), fontParagraph3);
        paragraph8.setAlignment(Paragraph.ANCHOR);
        paragraph8.setIndentationLeft(40);
        paragraph8.setSpacingBefore(5);

        Paragraph paragraph9 = new Paragraph("Anexos: ", fontParagraph2);
        paragraph9.setAlignment(Paragraph.ANCHOR);
        paragraph9.setSpacingBefore(15);

        Paragraph paragraph10 = new Paragraph(String.valueOf(demanda.getAnexo()), fontParagraph3);
        paragraph10.setIndentationLeft(40);
        paragraph10.setSpacingBefore(5);

        // Adicionando tudo na página pdf

        document.add(paragraph7);
        document.add(paragraph8);
        document.add(paragraph9);
        document.add(paragraph10);

        document.close();
    }

    public void exportProposta(HttpServletResponse response) throws IOException{
        Demanda demanda = new Demanda();
        Beneficio beneficio1 = new Beneficio();
        Beneficio beneficio2 = new Beneficio();
        Beneficio beneficio3 = new Beneficio();
        List<Beneficio> listaBeneficios = new ArrayList<>();

        TipoBeneficio tipoBeneficio1 = TipoBeneficio.QUALITATIVO;
        TipoBeneficio tipoBeneficio2 = TipoBeneficio.POTENCIAL;
        TipoBeneficio tipoBeneficio3 = TipoBeneficio.REAL;

        beneficio1.setTipoBeneficio(tipoBeneficio1);
        beneficio1.setMoeda("BR");
        beneficio1.setMemoriaCalculo("Memória de cálculo");
        beneficio1.setValor_mensal(2.00);

        beneficio2.setTipoBeneficio(tipoBeneficio2);
        beneficio2.setMoeda("BR");
        beneficio2.setMemoriaCalculo("Memória de cálculo 2");
        beneficio2.setValor_mensal(4.00);

        beneficio3.setTipoBeneficio(tipoBeneficio3);
        beneficio3.setMoeda("BR");
        beneficio3.setMemoriaCalculo("Memória de cálculo 3");
        beneficio3.setValor_mensal(6.00);

        listaBeneficios.add(beneficio1);
        listaBeneficios.add(beneficio2);
        listaBeneficios.add(beneficio3);

        demanda.setFrequencia("Muito Alta");
        demanda.setProposta("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. ");
        demanda.setTitulo("A Falha na Procura de Demandas");
        demanda.setProblema("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
        demanda.setBeneficios(listaBeneficios);

        // Criando a página do pdf

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Criando a formatação da página pdf

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(24);
        fontTitle.setColor(Color.decode("#00579D"));
        fontTitle.setStyle(Font.BOLD);

        Paragraph paragraph = new Paragraph(demanda.getTitulo(), fontTitle);
        paragraph.setSpacingBefore(20);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(18);
        fontParagraph.setStyle(Font.BOLD);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(18);
        fontParagraph2.setStyle(Font.BOLD);

        Font fontParagraph3 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(15);

        Paragraph paragraph2 = new Paragraph("Problema: ", fontParagraph2);
        paragraph2.setSpacingBefore(20);

        Paragraph paragraph3 = new Paragraph(demanda.getProblema(), fontParagraph3);
        paragraph3.setIndentationLeft(40);
        paragraph3.setSpacingBefore(5);

        Paragraph paragraph4 = new Paragraph("Proposta: ", fontParagraph2);
        paragraph4.setSpacingBefore(15);

        Paragraph paragraph5 = new Paragraph(demanda.getProposta(), fontParagraph3);
        paragraph5.setIndentationLeft(40);
        paragraph5.setSpacingBefore(5);

        Paragraph paragraph6 = new Paragraph("Benefícios: ", fontParagraph2);
        paragraph6.setSpacingBefore(15);

        Image img = Image.getInstance("C:\\Users\\matheus_hohmann\\Documents\\GitHub\\WEG-SSM\\back-end-maven\\src\\main\\java\\net\\weg\\wegssm\\images\\logo-pequeno.png");

        int indentation = 0;
        float scale = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - indentation) / img.getWidth()) * 20;

        img.scalePercent(scale);
        img.setAbsolutePosition(460, 740);

        document.add(img);
        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(paragraph6);

        // Criando tabela para os centros de custo

        for (Beneficio beneficio : demanda.getBeneficios()) {
            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 3, 3, 3});
            table.setSpacingBefore(15);

            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(Color.decode("#00579D"));
            cell.setPadding(5);
            cell.setBorder(0);

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(CMYKColor.WHITE);
            font.setStyle(Font.BOLD);
            font.setSize(12);

            cell.setPhrase(new Phrase("Tipo", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Valor Mensal", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Moeda", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Memória de Cálculo", font));
            table.addCell(cell);

            table.addCell(String.valueOf(beneficio.getTipoBeneficio()));
            table.addCell(String.valueOf(beneficio.getValor_mensal()));
            table.addCell(String.valueOf(beneficio.getMoeda()));
            table.addCell(String.valueOf(beneficio.getMemoriaCalculo()));

            document.add(table);
        }

        Paragraph paragraph7 = new Paragraph("Frequência de Uso: ", fontParagraph2);
        paragraph7.setAlignment(Paragraph.ANCHOR);
        paragraph7.setSpacingBefore(15);

        Paragraph paragraph8 = new Paragraph(demanda.getFrequencia(), fontParagraph3);
        paragraph8.setAlignment(Paragraph.ANCHOR);
        paragraph8.setIndentationLeft(40);
        paragraph8.setSpacingBefore(5);

        Paragraph paragraph9 = new Paragraph("Anexos: ", fontParagraph2);
        paragraph9.setAlignment(Paragraph.ANCHOR);
        paragraph9.setSpacingBefore(15);

        Paragraph paragraph10 = new Paragraph(String.valueOf(demanda.getAnexo()), fontParagraph3);
        paragraph10.setIndentationLeft(40);
        paragraph10.setSpacingBefore(5);

        // Adicionando tudo na página pdf

        document.add(paragraph7);
        document.add(paragraph8);
        document.add(paragraph9);
        document.add(paragraph10);

        document.close();
    }

    public void exportPauta(HttpServletResponse response) throws IOException {
        Demanda demanda = new Demanda();
        Beneficio beneficio1 = new Beneficio();
        Beneficio beneficio2 = new Beneficio();
        Beneficio beneficio3 = new Beneficio();
        List<Beneficio> listaBeneficios = new ArrayList<>();

        TipoBeneficio tipoBeneficio1 = TipoBeneficio.QUALITATIVO;
        TipoBeneficio tipoBeneficio2 = TipoBeneficio.POTENCIAL;
        TipoBeneficio tipoBeneficio3 = TipoBeneficio.REAL;

        beneficio1.setTipoBeneficio(tipoBeneficio1);
        beneficio1.setMoeda("BR");
        beneficio1.setMemoriaCalculo("Memória de cálculo");
        beneficio1.setValor_mensal(2.00);

        beneficio2.setTipoBeneficio(tipoBeneficio2);
        beneficio2.setMoeda("BR");
        beneficio2.setMemoriaCalculo("Memória de cálculo 2");
        beneficio2.setValor_mensal(4.00);

        beneficio3.setTipoBeneficio(tipoBeneficio3);
        beneficio3.setMoeda("BR");
        beneficio3.setMemoriaCalculo("Memória de cálculo 3");
        beneficio3.setValor_mensal(6.00);

        listaBeneficios.add(beneficio1);
        listaBeneficios.add(beneficio2);
        listaBeneficios.add(beneficio3);

        demanda.setFrequencia("Muito Alta");
        demanda.setProposta("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. ");
        demanda.setTitulo("A Falha na Procura de Demandas");
        demanda.setProblema("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
        demanda.setBeneficios(listaBeneficios);

        // Criando a página do pdf

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Criando a formatação da página pdf

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(24);
        fontTitle.setColor(Color.decode("#00579D"));
        fontTitle.setStyle(Font.BOLD);

        Paragraph paragraph = new Paragraph(demanda.getTitulo(), fontTitle);
        paragraph.setSpacingBefore(20);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(18);
        fontParagraph.setStyle(Font.BOLD);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(18);
        fontParagraph2.setStyle(Font.BOLD);

        Font fontParagraph3 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(15);

        Paragraph paragraph2 = new Paragraph("Problema: ", fontParagraph2);
        paragraph2.setSpacingBefore(20);

        Paragraph paragraph3 = new Paragraph(demanda.getProblema(), fontParagraph3);
        paragraph3.setIndentationLeft(40);
        paragraph3.setSpacingBefore(5);

        Paragraph paragraph4 = new Paragraph("Proposta: ", fontParagraph2);
        paragraph4.setSpacingBefore(15);

        Paragraph paragraph5 = new Paragraph(demanda.getProposta(), fontParagraph3);
        paragraph5.setIndentationLeft(40);
        paragraph5.setSpacingBefore(5);

        Paragraph paragraph6 = new Paragraph("Benefícios: ", fontParagraph2);
        paragraph6.setSpacingBefore(15);

        Image img = Image.getInstance("C:\\Users\\matheus_hohmann\\Documents\\GitHub\\WEG-SSM\\back-end-maven\\src\\main\\java\\net\\weg\\wegssm\\images\\logo-pequeno.png");

        int indentation = 0;
        float scale = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - indentation) / img.getWidth()) * 20;

        img.scalePercent(scale);
        img.setAbsolutePosition(460, 740);

        document.add(img);
        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(paragraph6);

        // Criando tabela para os centros de custo

        for (Beneficio beneficio : demanda.getBeneficios()) {
            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 3, 3, 3});
            table.setSpacingBefore(15);

            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(Color.decode("#00579D"));
            cell.setPadding(5);
            cell.setBorder(0);

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(CMYKColor.WHITE);
            font.setStyle(Font.BOLD);
            font.setSize(12);

            cell.setPhrase(new Phrase("Tipo", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Valor Mensal", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Moeda", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Memória de Cálculo", font));
            table.addCell(cell);

            table.addCell(String.valueOf(beneficio.getTipoBeneficio()));
            table.addCell(String.valueOf(beneficio.getValor_mensal()));
            table.addCell(String.valueOf(beneficio.getMoeda()));
            table.addCell(String.valueOf(beneficio.getMemoriaCalculo()));

            document.add(table);
        }

        Paragraph paragraph7 = new Paragraph("Frequência de Uso: ", fontParagraph2);
        paragraph7.setAlignment(Paragraph.ANCHOR);
        paragraph7.setSpacingBefore(15);

        Paragraph paragraph8 = new Paragraph(demanda.getFrequencia(), fontParagraph3);
        paragraph8.setAlignment(Paragraph.ANCHOR);
        paragraph8.setIndentationLeft(40);
        paragraph8.setSpacingBefore(5);

        Paragraph paragraph9 = new Paragraph("Anexos: ", fontParagraph2);
        paragraph9.setAlignment(Paragraph.ANCHOR);
        paragraph9.setSpacingBefore(15);

        Paragraph paragraph10 = new Paragraph(String.valueOf(demanda.getAnexo()), fontParagraph3);
        paragraph10.setIndentationLeft(40);
        paragraph10.setSpacingBefore(5);

        // Adicionando tudo na página pdf

        document.add(paragraph7);
        document.add(paragraph8);
        document.add(paragraph9);
        document.add(paragraph10);

        document.close();
    }

    public void exportAta(HttpServletResponse response) throws IOException {
        Demanda demanda = new Demanda();
        Beneficio beneficio1 = new Beneficio();
        Beneficio beneficio2 = new Beneficio();
        Beneficio beneficio3 = new Beneficio();
        List<Beneficio> listaBeneficios = new ArrayList<>();

        TipoBeneficio tipoBeneficio1 = TipoBeneficio.QUALITATIVO;
        TipoBeneficio tipoBeneficio2 = TipoBeneficio.POTENCIAL;
        TipoBeneficio tipoBeneficio3 = TipoBeneficio.REAL;

        beneficio1.setTipoBeneficio(tipoBeneficio1);
        beneficio1.setMoeda("BR");
        beneficio1.setMemoriaCalculo("Memória de cálculo");
        beneficio1.setValor_mensal(2.00);

        beneficio2.setTipoBeneficio(tipoBeneficio2);
        beneficio2.setMoeda("BR");
        beneficio2.setMemoriaCalculo("Memória de cálculo 2");
        beneficio2.setValor_mensal(4.00);

        beneficio3.setTipoBeneficio(tipoBeneficio3);
        beneficio3.setMoeda("BR");
        beneficio3.setMemoriaCalculo("Memória de cálculo 3");
        beneficio3.setValor_mensal(6.00);

        listaBeneficios.add(beneficio1);
        listaBeneficios.add(beneficio2);
        listaBeneficios.add(beneficio3);

        demanda.setFrequencia("Muito Alta");
        demanda.setProposta("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. ");
        demanda.setTitulo("A Falha na Procura de Demandas");
        demanda.setProblema("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
        demanda.setBeneficios(listaBeneficios);

        // Criando a página do pdf

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Criando a formatação da página pdf

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(24);
        fontTitle.setColor(Color.decode("#00579D"));
        fontTitle.setStyle(Font.BOLD);

        Paragraph paragraph = new Paragraph(demanda.getTitulo(), fontTitle);
        paragraph.setSpacingBefore(20);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(18);
        fontParagraph.setStyle(Font.BOLD);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(18);
        fontParagraph2.setStyle(Font.BOLD);

        Font fontParagraph3 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(15);

        Paragraph paragraph2 = new Paragraph("Problema: ", fontParagraph2);
        paragraph2.setSpacingBefore(20);

        Paragraph paragraph3 = new Paragraph(demanda.getProblema(), fontParagraph3);
        paragraph3.setIndentationLeft(40);
        paragraph3.setSpacingBefore(5);

        Paragraph paragraph4 = new Paragraph("Proposta: ", fontParagraph2);
        paragraph4.setSpacingBefore(15);

        Paragraph paragraph5 = new Paragraph(demanda.getProposta(), fontParagraph3);
        paragraph5.setIndentationLeft(40);
        paragraph5.setSpacingBefore(5);

        Paragraph paragraph6 = new Paragraph("Benefícios: ", fontParagraph2);
        paragraph6.setSpacingBefore(15);

        Image img = Image.getInstance("C:\\Users\\matheus_hohmann\\Documents\\GitHub\\WEG-SSM\\back-end-maven\\src\\main\\java\\net\\weg\\wegssm\\images\\logo-pequeno.png");

        int indentation = 0;
        float scale = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - indentation) / img.getWidth()) * 20;

        img.scalePercent(scale);
        img.setAbsolutePosition(460, 740);

        document.add(img);
        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(paragraph6);

        // Criando tabela para os centros de custo

        for (Beneficio beneficio : demanda.getBeneficios()) {
            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 3, 3, 3});
            table.setSpacingBefore(15);

            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(Color.decode("#00579D"));
            cell.setPadding(5);
            cell.setBorder(0);

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(CMYKColor.WHITE);
            font.setStyle(Font.BOLD);
            font.setSize(12);

            cell.setPhrase(new Phrase("Tipo", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Valor Mensal", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Moeda", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Memória de Cálculo", font));
            table.addCell(cell);

            table.addCell(String.valueOf(beneficio.getTipoBeneficio()));
            table.addCell(String.valueOf(beneficio.getValor_mensal()));
            table.addCell(String.valueOf(beneficio.getMoeda()));
            table.addCell(String.valueOf(beneficio.getMemoriaCalculo()));

            document.add(table);
        }

        Paragraph paragraph7 = new Paragraph("Frequência de Uso: ", fontParagraph2);
        paragraph7.setAlignment(Paragraph.ANCHOR);
        paragraph7.setSpacingBefore(15);

        Paragraph paragraph8 = new Paragraph(demanda.getFrequencia(), fontParagraph3);
        paragraph8.setAlignment(Paragraph.ANCHOR);
        paragraph8.setIndentationLeft(40);
        paragraph8.setSpacingBefore(5);

        Paragraph paragraph9 = new Paragraph("Anexos: ", fontParagraph2);
        paragraph9.setAlignment(Paragraph.ANCHOR);
        paragraph9.setSpacingBefore(15);

        Paragraph paragraph10 = new Paragraph(String.valueOf(demanda.getAnexo()), fontParagraph3);
        paragraph10.setIndentationLeft(40);
        paragraph10.setSpacingBefore(5);

        // Adicionando tudo na página pdf

        document.add(paragraph7);
        document.add(paragraph8);
        document.add(paragraph9);
        document.add(paragraph10);

        document.close();
    }

}
