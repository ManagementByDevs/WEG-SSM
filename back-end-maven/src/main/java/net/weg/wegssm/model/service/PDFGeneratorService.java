package net.weg.wegssm.model.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.VerticalPositionMark;
import lombok.AllArgsConstructor;
import net.weg.wegssm.dto.CustoDTO;
import net.weg.wegssm.dto.TabelaCustoDTO;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.repository.DemandaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PDFGeneratorService {

    private static DemandaService demandaService;

    public PDFGeneratorService(DemandaService demandaService) {
        this.demandaService = demandaService;
    }

    public Document exportDemanda(HttpServletResponse response, Demanda demanda) throws IOException {

        // Formatação da data para o documento pdf

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Criando a página do pdf

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Criando a logo da weg para modelo pdf

        Image img = Image.getInstance("https://logospng.org/download/weg/logo-weg-2048.png");

        int indentation = 0;
        float scale = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - indentation) / img.getWidth()) * 20;

        img.scalePercent(scale);
        img.setAbsolutePosition(460, 740);

        // Criando as fontes da página

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(24);
        fontTitle.setColor(Color.decode("#00579D"));
        fontTitle.setStyle(Font.BOLD);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(18);
        fontParagraph.setStyle(Font.BOLD);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(18);
        fontParagraph2.setStyle(Font.BOLD);

        Font fontParagraph3 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(15);

        Font fontParagraph4 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph4.setSize(12);

        // Criando a formatação da página pdf

        Paragraph paragraph = new Paragraph(demanda.getTitulo(), fontTitle);
        paragraph.setSpacingBefore(15);

        Paragraph paragraph2 = new Paragraph("Problema: ", fontParagraph2);
        paragraph2.setSpacingBefore(20);

        Paragraph paragraph11 = new Paragraph("Data de emissão: " + currentDateTime, fontParagraph4);
        paragraph11.setSpacingBefore(20);

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

        document.add(img);
        document.add(paragraph11);
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

            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
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

        // Adicionando tudo na página pdf

        document.add(paragraph7);
        document.add(paragraph8);

        if (demanda.getTamanho() != null) {
            Chunk chunkTamanho = new Chunk("Tamanho: ", fontParagraph2);
            Chunk chunkValorTamanho = new Chunk(demanda.getTamanho(), fontParagraph3);
            Paragraph paragraph12 = new Paragraph();
            paragraph12.add(chunkTamanho);
            paragraph12.add(chunkValorTamanho);

            Chunk chunkSecao = new Chunk("Seção de TI: ", fontParagraph2);
            Chunk chunkValorSecao = new Chunk(demanda.getSecaoTI(), fontParagraph3);
            Paragraph paragraph13 = new Paragraph();
            paragraph13.add(chunkSecao);
            paragraph13.add(chunkValorSecao);

            Chunk chunkBuSolicitante = new Chunk("BU Solicitante: ", fontParagraph2);
            Chunk chunkValorBuSolicitante = new Chunk(demanda.getBuSolicitante().getNome(), fontParagraph3);
            Paragraph paragraph14 = new Paragraph();
            paragraph14.add(chunkBuSolicitante);
            paragraph14.add(chunkValorBuSolicitante);

            Chunk chunkBuBeneficiadas = new Chunk("BUs Beneficiadas: ", fontParagraph2);
            Paragraph paragraph15 = new Paragraph();
            paragraph15.add(chunkBuBeneficiadas);

            for (Bu bu : demanda.getBusBeneficiadas()) {
                Chunk chunkValorBuBeneficiadas = new Chunk(bu.getNome() + " ", fontParagraph3);
                paragraph15.add(chunkValorBuBeneficiadas);
            }

            Chunk chunkForum = new Chunk("Fórum: ", fontParagraph2);
            Chunk chunkValorForum = new Chunk(demanda.getForum().getNome(), fontParagraph3);
            Paragraph paragraph16 = new Paragraph();
            paragraph16.setSpacingBefore(15);
            paragraph16.add(chunkForum);
            paragraph16.add(chunkValorForum);

            PdfPTable table2 = new PdfPTable(2);
            table2.setWidthPercentage(100);
            table2.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell cell1 = new PdfPCell(paragraph12);
            cell1.setBorder(Rectangle.NO_BORDER);

            PdfPCell cell2 = new PdfPCell(paragraph13);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

            table2.addCell(cell1);
            table2.addCell(cell2);
            table2.setSpacingBefore(15);

            PdfPTable table3 = new PdfPTable(2);
            table3.setWidthPercentage(100);
            table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell cell3 = new PdfPCell(paragraph14);
            cell3.setBorder(Rectangle.NO_BORDER);

            PdfPCell cell4 = new PdfPCell(paragraph15);
            cell4.setBorder(Rectangle.NO_BORDER);
            cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);

            table3.addCell(cell3);
            table3.addCell(cell4);
            table3.setSpacingBefore(15);

            document.add(table2);
            document.add(table3);
            document.add(paragraph16);
        }

        document.add(paragraph9);

        // Adicionando o nome dos arquivos

        for (Anexo anexo : demanda.getAnexo()) {
            Paragraph paragraph10 = new Paragraph(anexo.getNome() + "." + anexo.getTipo(), fontParagraph3);
            paragraph10.setIndentationLeft(40);
            paragraph10.setSpacingBefore(5);

            document.add(paragraph10);
        }

        // Encerrando o documento

        document.close();

        return document;
    }

    public Document exportProposta(HttpServletResponse response, Proposta proposta) throws IOException {

        Demanda demanda = demandaService.findById(proposta.getDemanda().getId()).get();

        // Formatação da data para quando baixar o documento pdf

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Criando a página do pdf

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Criando a logo da weg para o modelo pdf

        Image img = Image.getInstance("https://logospng.org/download/weg/logo-weg-2048.png");

        int indentation = 0;
        float scale = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - indentation) / img.getWidth()) * 20;

        img.scalePercent(scale);
        img.setAbsolutePosition(460, 740);

        // Criando as fontes da página

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(24);
        fontTitle.setColor(Color.decode("#00579D"));
        fontTitle.setStyle(Font.BOLD);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(18);
        fontParagraph.setStyle(Font.BOLD);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(18);
        fontParagraph2.setStyle(Font.BOLD);

        Font fontParagraph3 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(15);

        Font fontParagraph4 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph4.setSize(12);

        Font fontParagraph5 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph5.setSize(15);
        fontParagraph5.setColor(Color.decode("#00579D"));
        fontParagraph5.setStyle(Font.BOLD);

        // Criando a formatação da página pdf

        Paragraph paragraph23 = new Paragraph("Data de emissão: " + currentDateTime, fontParagraph4);
        paragraph23.setSpacingBefore(20);

        Chunk chunkPPMData = new Chunk("PPM   " + String.valueOf(proposta.getCodigoPPM()) + "               ", fontParagraph5);
        Chunk chunkValorPPMData = new Chunk("DATA   " + String.valueOf(proposta.getData()), fontParagraph5);
        Paragraph paragraph25 = new Paragraph();
        paragraph25.add(chunkPPMData);
        paragraph25.add(chunkValorPPMData);
        paragraph25.setSpacingBefore(20);

        Paragraph paragraph = new Paragraph(proposta.getDemanda().getTitulo(), fontTitle);
        paragraph.setSpacingBefore(10);

        Chunk chunkSolicitante = new Chunk("Solicitante: ", fontParagraph2);
        Chunk chunkValorSolicitante = new Chunk(String.valueOf(proposta.getSolicitante().getNome() + " - " + proposta.getSolicitante().getDepartamento().getNome()), fontParagraph3);
        Paragraph paragraph11 = new Paragraph();
        paragraph11.add(chunkSolicitante);
        paragraph11.add(chunkValorSolicitante);
        paragraph11.setSpacingBefore(20);

        Chunk chunkGerente = new Chunk("Gerente: ", fontParagraph2);
        Chunk chunkValorGerente = new Chunk(String.valueOf(proposta.getGerente().getNome() + " - " + proposta.getGerente().getDepartamento().getNome()), fontParagraph3);
        Paragraph paragraph31 = new Paragraph();
        paragraph31.add(chunkGerente);
        paragraph31.add(chunkValorGerente);
        paragraph31.setSpacingBefore(15);

        Chunk chunkBuSolicitante = new Chunk("BU Solicitante: ", fontParagraph2);
        Chunk chunkValorBuSolicitante = new Chunk(proposta.getBuSolicitante().getNome(), fontParagraph3);
        Paragraph paragraph38 = new Paragraph();
        paragraph38.add(chunkBuSolicitante);
        paragraph38.add(chunkValorBuSolicitante);
        paragraph38.setSpacingBefore(15);

        Chunk chunkForum = new Chunk("Fórum: ", fontParagraph2);
        Chunk chunkValorForum = new Chunk(proposta.getForum().getNome(), fontParagraph3);
        Paragraph paragraph32 = new Paragraph();
        paragraph32.add(chunkForum);
        paragraph32.add(chunkValorForum);

        Chunk chunkTamanho = new Chunk("Tamanho ", fontParagraph2);
        Chunk chunkValorTamanho = new Chunk(proposta.getTamanho(), fontParagraph3);
        Paragraph paragraph33 = new Paragraph();
        paragraph33.add(chunkTamanho);
        paragraph33.add(chunkValorTamanho);

        PdfPTable table4 = new PdfPTable(2);
        table4.setWidthPercentage(100);
        table4.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        PdfPCell cell4 = new PdfPCell(paragraph32);
        cell4.setBorder(Rectangle.NO_BORDER);

        PdfPCell cell5 = new PdfPCell(paragraph33);
        cell5.setBorder(Rectangle.NO_BORDER);
        cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);

        table4.addCell(cell4);
        table4.addCell(cell5);
        table4.setSpacingBefore(15);

        Paragraph paragraph4 = new Paragraph("Proposta: ", fontParagraph2);
        paragraph4.setSpacingBefore(10);

        Paragraph paragraph5 = new Paragraph(proposta.getDemanda().getProposta(), fontParagraph3);
        paragraph5.setSpacingBefore(3);
        paragraph5.setIndentationLeft(10);

        Paragraph paragraph2 = new Paragraph("Problema: ", fontParagraph2);
        paragraph2.setSpacingBefore(15);

        Paragraph paragraph3 = new Paragraph(proposta.getDemanda().getProblema(), fontParagraph3);
        paragraph3.setSpacingBefore(3);
        paragraph3.setIndentationLeft(10);

        Paragraph paragraph26 = new Paragraph("Escopo da Proposta: ", fontParagraph2);
        paragraph26.setSpacingBefore(15);

        Paragraph paragraph27 = new Paragraph(String.valueOf(proposta.getEscopo()), fontParagraph3);
        paragraph27.setSpacingBefore(3);
        paragraph27.setIndentationLeft(10);

        Paragraph paragraph28 = new Paragraph("Tabela de Custos: ", fontParagraph2);
        paragraph28.setSpacingBefore(15);

        Chunk chunkFrequencia = new Chunk("Frequência: ", fontParagraph2);
        Chunk chunkValorFrequencia = new Chunk(proposta.getFrequencia(), fontParagraph3);
        Paragraph paragraph34 = new Paragraph();
        paragraph34.add(chunkFrequencia);
        paragraph34.add(chunkValorFrequencia);
        paragraph34.setSpacingBefore(15);

        document.add(img);
        document.add(paragraph23);
        document.add(paragraph25);
        document.add(paragraph);
        document.add(paragraph11);
        document.add(paragraph38);
        document.add(paragraph31);
        document.add(table4);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph26);
        document.add(paragraph27);
        document.add(paragraph34);
        document.add(paragraph28);

        for (TabelaCusto tableCusto : proposta.getTabelaCustos()) {
            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 3, 3, 3});
            table.setSpacingBefore(15);

            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
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

            cell.setPhrase(new Phrase("Tipo da Despesa", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Perfil da Despesa", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Período de Execução (meses)", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Horas", font));
            table.addCell(cell);

            for (Custo custos : tableCusto.getCustos()) {
                table.addCell(custos.getTipoDespesa());
                table.addCell(custos.getPerfilDespesa());
                table.addCell(String.valueOf(custos.getPeriodoExecucao()));
                table.addCell(String.valueOf(custos.getHoras()));
                table.addCell(String.valueOf(custos.getValorHora()));
            }

            PdfPTable table2 = new PdfPTable(2);

            table2.setWidthPercentage(100);
            table2.setWidths(new int[]{3, 3});
            table2.setSpacingBefore(15);

            table2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell2 = new PdfPCell();
            cell2.setBackgroundColor(Color.decode("#00579D"));
            cell2.setPadding(5);
            cell2.setBorder(0);

            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_CENTER);

            cell2.setPhrase(new Phrase("Valor Hora", font));
            table2.addCell(cell2);
            cell2.setPhrase(new Phrase("Total", font));
            table2.addCell(cell2);

            table2.addCell("10");
            table2.addCell("100");

            PdfPTable table3 = new PdfPTable(2);

            table3.setWidthPercentage(100);
            table3.setWidths(new int[]{3, 3});
            table3.setSpacingBefore(20);

            table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell3 = new PdfPCell();
            cell3.setBackgroundColor(Color.decode("#00579D"));
            cell3.setPadding(5);
            cell3.setBorder(0);

            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_CENTER);

            cell3.setPhrase(new Phrase("CCs", font));
            table3.addCell(cell3);
            cell3.setPhrase(new Phrase("Porcentagem", font));
            table3.addCell(cell3);

            table3.addCell("Cc");
            table3.addCell("50%");

            document.add(table);
            document.add(table2);
            document.add(table3);
        }

        Paragraph paragraph35 = new Paragraph("Benefícios: ", fontParagraph2);
        paragraph35.setAlignment(Paragraph.ANCHOR);
        paragraph35.setSpacingBefore(15);

        document.add(paragraph35);

        for (Beneficio beneficio : proposta.getDemanda().getBeneficios()) {
            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 3, 3, 3});
            table.setSpacingBefore(15);

            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
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

        Chunk chunkBuBeneficiadas = new Chunk("BUs Beneficiadas: ", fontParagraph2);
        Paragraph paragraph36 = new Paragraph();
        paragraph36.add(chunkBuBeneficiadas);
        paragraph36.setSpacingBefore(15);

        for (Bu bu : demanda.getBusBeneficiadas()) {
            Chunk chunkValorBuBeneficiadas = new Chunk(bu.getNome() + " ", fontParagraph3);
            paragraph36.add(chunkValorBuBeneficiadas);
        }

        Chunk chunkLink = new Chunk("Link Jira: ", fontParagraph2);
        Chunk chunkValorLink = new Chunk(proposta.getLinkJira(), fontParagraph3);
        Paragraph paragraph21 = new Paragraph();
        paragraph21.add(chunkLink);
        paragraph21.add(chunkValorLink);
        paragraph21.setSpacingBefore(15);

        Chunk chunkExecucao = new Chunk("Período de Execução: ", fontParagraph2);
        Chunk chunkValorExecucao = new Chunk(String.valueOf(proposta.getInicioExecucao()) + " à " + String.valueOf(proposta.getFimExecucao()), fontParagraph3);
        Paragraph paragraph17 = new Paragraph();
        paragraph17.add(chunkExecucao);
        paragraph17.add(chunkValorExecucao);
        paragraph17.setSpacingBefore(15);

        Chunk chunkPayback = new Chunk("Payback: ", fontParagraph2);
        Chunk chunkValorPayback = new Chunk(String.valueOf(proposta.getPaybackValor()) + " " + String.valueOf(proposta.getPaybackTipo()), fontParagraph3);
        Paragraph paragraph19 = new Paragraph();
        paragraph19.add(chunkPayback);
        paragraph19.add(chunkValorPayback);
        paragraph19.setSpacingBefore(15);

        Paragraph paragraph9 = new Paragraph("Anexos: ", fontParagraph2);
        paragraph9.setAlignment(Paragraph.ANCHOR);
        paragraph9.setSpacingBefore(15);

        document.add(paragraph36);
        document.add(paragraph21);
        document.add(paragraph17);
        document.add(paragraph19);
        document.add(paragraph9);

        for (Anexo anexo : proposta.getDemanda().getAnexo()) {
            Paragraph paragraph10 = new Paragraph(anexo.getNome() + "." + anexo.getTipo(), fontParagraph3);
            paragraph10.setIndentationLeft(40);
            paragraph10.setSpacingBefore(5);

            document.add(paragraph10);
        }

        for (ResponsavelNegocio responsavel : proposta.getResponsavelNegocio()) {
            Paragraph p = new Paragraph(responsavel.getNome() + " - " + responsavel.getArea(), fontParagraph3);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingBefore(5);

            document.add(p);
        }

        Paragraph paragraph30 = new Paragraph("Responsáveis pelo Negócio", fontParagraph2);
        paragraph30.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph30);

        // Encerrando o documento

        document.close();

        return document;
    }

    public void exportPauta(HttpServletResponse response) throws IOException {

        Pauta pauta = new Pauta();

        // Formatação da data para quando baixar o documento

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Criação do documento pdf

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Criando a logo da weg para o modelo pdf

        Image img = Image.getInstance("https://logospng.org/download/weg/logo-weg-2048.png");

        int indentation = 0;
        float scale = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - indentation) / img.getWidth()) * 20;

        img.scalePercent(scale);
        img.setAbsolutePosition(460, 740);

        // Criação das fontes da página

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(24);
        fontTitle.setColor(Color.decode("#00579D"));
        fontTitle.setStyle(Font.BOLD);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(18);
        fontParagraph.setStyle(Font.BOLD);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(18);
        fontParagraph2.setStyle(Font.BOLD);

        Font fontParagraph3 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(15);

        Font fontParagraph4 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph4.setSize(12);

        Font fontParagraph5 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph5.setSize(13);
        fontParagraph5.setStyle(Font.BOLD);

        // Formatação da página pdf

        Paragraph paragraph = new Paragraph("Data de emissão: " + currentDateTime, fontParagraph4);
        paragraph.setSpacingBefore(20);

        Paragraph paragraph2 = new Paragraph("Pauta", fontTitle);
        paragraph2.setSpacingBefore(22);
        paragraph2.setAlignment(Element.ALIGN_CENTER);

        Paragraph paragraph3 = new Paragraph("Número Sequencial: " + pauta.getNumeroSequencial(), fontParagraph5);
        paragraph3.setSpacingBefore(20);

        Paragraph paragraph4 = new Paragraph("Ano: 2023", fontParagraph5);
        paragraph3.setSpacingBefore(5);

        document.add(img);
        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);

        int contadorProposta = 1;

        for (Proposta proposta : pauta.getPropostas()) {

            Paragraph paragraph30 = new Paragraph("Proposta " + contadorProposta, fontTitle);
            paragraph30.setSpacingBefore(20);
            paragraph30.setAlignment(Element.ALIGN_CENTER);

            contadorProposta++;

            Paragraph paragraph5 = new Paragraph("Código PPM: ", fontParagraph2);
            paragraph5.setSpacingBefore(15);

            Paragraph paragraph6 = new Paragraph(String.valueOf(proposta.getCodigoPPM()), fontParagraph3);
            paragraph6.setIndentationLeft(40);
            paragraph6.setSpacingBefore(5);

            Paragraph paragraph7 = new Paragraph("Responsável Negócio: ", fontParagraph2);
            paragraph7.setSpacingBefore(15);

            Paragraph paragraph8 = new Paragraph("Problema: ", fontParagraph2);
            paragraph8.setSpacingBefore(15);

            Paragraph paragraph9 = new Paragraph(proposta.getDemanda().getProblema(), fontParagraph3);
            paragraph9.setIndentationLeft(40);
            paragraph9.setSpacingBefore(5);

            Paragraph paragraph10 = new Paragraph("Proposta: ", fontParagraph2);
            paragraph10.setSpacingBefore(15);

            Paragraph paragraph11 = new Paragraph(proposta.getDemanda().getProposta(), fontParagraph3);
            paragraph11.setIndentationLeft(40);
            paragraph11.setSpacingBefore(5);

            Paragraph paragraph12 = new Paragraph("Benefícios: ", fontParagraph2);
            paragraph12.setSpacingBefore(15);

            document.add(paragraph30);
            document.add(paragraph5);
            document.add(paragraph6);

            for (ResponsavelNegocio responsavel : proposta.getResponsavelNegocio()) {
                Paragraph p = new Paragraph(responsavel.getNome() + " - " + responsavel.getArea(), fontParagraph3);
                p.setIndentationLeft(40);
                p.setSpacingBefore(5);

                document.add(p);
            }

            document.add(paragraph7);
            document.add(paragraph8);
            document.add(paragraph9);
            document.add(paragraph10);
            document.add(paragraph11);
            document.add(paragraph12);

            for (Beneficio beneficio : proposta.getDemanda().getBeneficios()) {
                PdfPTable table = new PdfPTable(4);

                table.setWidthPercentage(100);
                table.setWidths(new int[]{3, 3, 3, 3});
                table.setSpacingBefore(15);

                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
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

            Paragraph paragraph13 = new Paragraph("Frequência de Uso: ", fontParagraph2);
            paragraph13.setAlignment(Paragraph.ANCHOR);
            paragraph13.setSpacingBefore(15);

            Paragraph paragraph14 = new Paragraph(proposta.getDemanda().getFrequencia(), fontParagraph3);
            paragraph14.setAlignment(Paragraph.ANCHOR);
            paragraph14.setIndentationLeft(40);
            paragraph14.setSpacingBefore(5);

            Paragraph paragraph15 = new Paragraph("Custos: ", fontParagraph2);
            paragraph15.setSpacingBefore(15);

            Paragraph paragraph16 = new Paragraph(String.valueOf(proposta.getTabelaCustos()), fontParagraph3);
            paragraph16.setIndentationLeft(40);
            paragraph16.setSpacingBefore(5);

            Paragraph paragraph17 = new Paragraph("Período de Execução: ", fontParagraph2);
            paragraph17.setSpacingBefore(15);

            Paragraph paragraph18 = new Paragraph(String.valueOf(proposta.getInicioExecucao()) + " à " + String.valueOf(proposta.getFimExecucao()), fontParagraph3);
            paragraph18.setIndentationLeft(40);
            paragraph18.setSpacingBefore(5);

            Paragraph paragraph19 = new Paragraph("Payback Simples: ", fontParagraph2);
            paragraph19.setSpacingBefore(15);

            Paragraph paragraph20 = new Paragraph(String.valueOf(proposta.getPaybackValor()) + " " + String.valueOf(proposta.getPaybackTipo()), fontParagraph3);
            paragraph20.setIndentationLeft(40);
            paragraph20.setSpacingBefore(5);

            Paragraph paragraph21 = new Paragraph("Link Jira: ", fontParagraph2);
            paragraph21.setSpacingBefore(15);

            Paragraph paragraph22 = new Paragraph(proposta.getLinkJira(), fontParagraph3);
            paragraph22.setIndentationLeft(40);
            paragraph22.setSpacingBefore(5);

            Paragraph paragraph23 = new Paragraph("Anexos: ", fontParagraph2);
            paragraph23.setAlignment(Paragraph.ANCHOR);
            paragraph23.setSpacingBefore(15);

            Paragraph paragraph24 = new Paragraph("Parecer Comissão: ", fontParagraph2);
            paragraph24.setAlignment(Paragraph.ANCHOR);
            paragraph24.setSpacingBefore(15);

            Paragraph paragraph25 = new Paragraph(String.valueOf(proposta.getParecerComissao()), fontParagraph3);
            paragraph25.setIndentationLeft(40);
            paragraph25.setSpacingBefore(5);

            document.add(paragraph13);
            document.add(paragraph14);
            document.add(paragraph15);
            document.add(paragraph16);
            document.add(paragraph17);
            document.add(paragraph18);
            document.add(paragraph19);
            document.add(paragraph20);
            document.add(paragraph21);
            document.add(paragraph22);
            document.add(paragraph23);

//            for (Anexo anexo : proposta.getDemanda().getAnexo()) {
//                Paragraph paragraph24 = new Paragraph(anexo.getNome() + "." + anexo.getTipo(), fontParagraph3);
//                paragraph24.setIndentationLeft(40);
//                paragraph24.setSpacingBefore(5);
//
//                document.add(paragraph24);
//            }

            document.add(paragraph24);
            document.add(paragraph25);

        }

        document.close();
    }

    public void exportAta(HttpServletResponse response) throws IOException {

        Ata ata = new Ata();

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Criando a logo da weg para o modelo pdf

        Image img = Image.getInstance("https://logospng.org/download/weg/logo-weg-2048.png");

        int indentation = 0;
        float scale = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - indentation) / img.getWidth()) * 20;

        img.scalePercent(scale);
        img.setAbsolutePosition(460, 740);

        // Formatação para o documento

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(24);
        fontTitle.setColor(Color.decode("#00579D"));
        fontTitle.setStyle(Font.BOLD);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(18);
        fontParagraph.setStyle(Font.BOLD);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(18);
        fontParagraph2.setStyle(Font.BOLD);

        Font fontParagraph3 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(15);

        Font fontParagraph4 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph4.setSize(12);

        Font fontParagraph5 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph5.setSize(13);
        fontParagraph5.setStyle(Font.BOLD);

        Paragraph paragraph = new Paragraph("Data de emissão: " + currentDateTime, fontParagraph4);
        paragraph.setSpacingBefore(20);

        Paragraph paragraph2 = new Paragraph("Ata", fontTitle);
        paragraph2.setSpacingBefore(22);
        paragraph2.setAlignment(Element.ALIGN_CENTER);

        Paragraph paragraph3 = new Paragraph("Número Sequencial: " + ata.getNumeroSequencial(), fontParagraph5);
        paragraph3.setSpacingBefore(20);

        Paragraph paragraph4 = new Paragraph("Início: " + ata.getInicioDataReuniao(), fontParagraph5);
        paragraph4.setSpacingBefore(5);

        Paragraph paragraph26 = new Paragraph("Fim: " + ata.getFimDataReuniao(), fontParagraph5);
        paragraph26.setSpacingBefore(5);

        document.add(img);
        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph26);

        int contadorPropostas = 1;

        for (Proposta proposta : ata.getPropostas()) {

            Paragraph paragraph30 = new Paragraph("Proposta: " + contadorPropostas, fontTitle);
            paragraph30.setSpacingBefore(20);
            paragraph30.setAlignment(Element.ALIGN_CENTER);

            contadorPropostas++;

            Paragraph paragraph5 = new Paragraph("Código PPM: ", fontParagraph2);
            paragraph5.setSpacingBefore(15);

            Paragraph paragraph6 = new Paragraph(String.valueOf(proposta.getCodigoPPM()), fontParagraph3);
            paragraph6.setIndentationLeft(40);
            paragraph6.setSpacingBefore(5);

            Paragraph paragraph7 = new Paragraph("Responsável Negócio: ", fontParagraph2);
            paragraph7.setSpacingBefore(15);

            Paragraph paragraph8 = new Paragraph("Problema: ", fontParagraph2);
            paragraph8.setSpacingBefore(15);

            Paragraph paragraph9 = new Paragraph(proposta.getDemanda().getProblema(), fontParagraph3);
            paragraph9.setIndentationLeft(40);
            paragraph9.setSpacingBefore(5);

            Paragraph paragraph10 = new Paragraph("Proposta: ", fontParagraph2);
            paragraph10.setSpacingBefore(15);

            Paragraph paragraph11 = new Paragraph(proposta.getDemanda().getProposta(), fontParagraph3);
            paragraph11.setIndentationLeft(40);
            paragraph11.setSpacingBefore(5);

            Paragraph paragraph12 = new Paragraph("Benefícios: ", fontParagraph2);
            paragraph12.setSpacingBefore(15);

            document.add(paragraph30);
            document.add(paragraph5);
            document.add(paragraph6);

            for (ResponsavelNegocio responsavel : proposta.getResponsavelNegocio()) {
                Paragraph p = new Paragraph(responsavel.getNome() + " - " + responsavel.getArea(), fontParagraph3);
                p.setIndentationLeft(40);
                p.setSpacingBefore(5);

                document.add(p);
            }

            document.add(paragraph7);
            document.add(paragraph8);
            document.add(paragraph9);
            document.add(paragraph10);
            document.add(paragraph11);
            document.add(paragraph12);

            for (Beneficio beneficio : proposta.getDemanda().getBeneficios()) {
                PdfPTable table = new PdfPTable(4);

                table.setWidthPercentage(100);
                table.setWidths(new int[]{3, 3, 3, 3});
                table.setSpacingBefore(15);

                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
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

            Paragraph paragraph13 = new Paragraph("Frequência de Uso: ", fontParagraph2);
            paragraph13.setAlignment(Paragraph.ANCHOR);
            paragraph13.setSpacingBefore(15);

            Paragraph paragraph14 = new Paragraph(proposta.getDemanda().getFrequencia(), fontParagraph3);
            paragraph14.setAlignment(Paragraph.ANCHOR);
            paragraph14.setIndentationLeft(40);
            paragraph14.setSpacingBefore(5);

            Paragraph paragraph15 = new Paragraph("Custos: ", fontParagraph2);
            paragraph15.setSpacingBefore(15);

            Paragraph paragraph16 = new Paragraph(String.valueOf(proposta.getTabelaCustos()), fontParagraph3);
            paragraph16.setIndentationLeft(40);
            paragraph16.setSpacingBefore(5);

            Paragraph paragraph17 = new Paragraph("Período de Execução: ", fontParagraph2);
            paragraph17.setSpacingBefore(15);

            Paragraph paragraph18 = new Paragraph(String.valueOf(proposta.getInicioExecucao()) + " à " + String.valueOf(proposta.getFimExecucao()), fontParagraph3);
            paragraph18.setIndentationLeft(40);
            paragraph18.setSpacingBefore(5);

            Paragraph paragraph19 = new Paragraph("Payback Simples: ", fontParagraph2);
            paragraph19.setSpacingBefore(15);

            Paragraph paragraph20 = new Paragraph(String.valueOf(proposta.getPaybackValor()) + " " + String.valueOf(proposta.getPaybackTipo()), fontParagraph3);
            paragraph20.setIndentationLeft(40);
            paragraph20.setSpacingBefore(5);

            Paragraph paragraph21 = new Paragraph("Link Jira: ", fontParagraph2);
            paragraph21.setSpacingBefore(15);

            Paragraph paragraph22 = new Paragraph(proposta.getLinkJira(), fontParagraph3);
            paragraph22.setIndentationLeft(40);
            paragraph22.setSpacingBefore(5);

            Paragraph paragraph23 = new Paragraph("Anexos: ", fontParagraph2);
            paragraph23.setAlignment(Paragraph.ANCHOR);
            paragraph23.setSpacingBefore(15);

            Paragraph paragraph24 = new Paragraph("Parecer Comissão: ", fontParagraph2);
            paragraph24.setAlignment(Paragraph.ANCHOR);
            paragraph24.setSpacingBefore(15);

            Paragraph paragraph25 = new Paragraph(String.valueOf(proposta.getParecerComissao()), fontParagraph3);
            paragraph25.setIndentationLeft(40);
            paragraph25.setSpacingBefore(5);

            Paragraph paragraph27 = new Paragraph("Parecer DG: ", fontParagraph2);
            paragraph27.setAlignment(Paragraph.ANCHOR);
            paragraph27.setSpacingBefore(15);

            Paragraph paragraph28 = new Paragraph(String.valueOf(proposta.getParecerDG()), fontParagraph3);
            paragraph28.setIndentationLeft(40);
            paragraph28.setSpacingBefore(5);

            document.add(paragraph13);
            document.add(paragraph14);
            document.add(paragraph15);
            document.add(paragraph16);
            document.add(paragraph17);
            document.add(paragraph18);
            document.add(paragraph19);
            document.add(paragraph20);
            document.add(paragraph21);
            document.add(paragraph22);
            document.add(paragraph23);

//            for (Anexo anexo : proposta.getDemanda().getAnexo()) {
//                Paragraph paragraph29 = new Paragraph(anexo.getNome() + "." + anexo.getTipo(), fontParagraph3);
//                paragraph29.setIndentationLeft(40);
//                paragraph29.setSpacingBefore(5);
//
//                document.add(paragraph29);
//            }

            document.add(paragraph24);
            document.add(paragraph25);
            document.add(paragraph27);
            document.add(paragraph28);

        }

        document.close();

    }
}