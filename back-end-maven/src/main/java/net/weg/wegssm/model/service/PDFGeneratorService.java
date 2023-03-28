package net.weg.wegssm.model.service;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import net.weg.wegssm.model.entities.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PDFGeneratorService {

    private static DemandaService demandaService;

    public PDFGeneratorService(DemandaService demandaService) {
        this.demandaService = demandaService;
    }

    // métodos para criar o modelo de exportação do pdf

    public Document exportDemanda(HttpServletResponse response, Demanda demanda) throws IOException, DocumentException {

        // Colors
        BaseColor azulWeg = new BaseColor(0, 87, 157);

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

        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitulo.setSize(24);
        fontTitulo.setColor(azulWeg);
        fontTitulo.setStyle(Font.BOLD);

        Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA);
        fontSubtitulo.setSize(15);
        fontSubtitulo.setStyle(Font.BOLD);

        Font fontInformacoes = FontFactory.getFont(FontFactory.HELVETICA);

        Font fontInfoHeader = FontFactory.getFont(FontFactory.HELVETICA);
        fontInfoHeader.setSize(12);

        // Criando a formatação da página pdf

        Paragraph paragraphTitulo = new Paragraph(demanda.getTitulo(), fontTitulo);
        paragraphTitulo.setSpacingBefore(15);

        Paragraph paragraphProblema = new Paragraph("Problema: ", fontSubtitulo);
        paragraphProblema.setSpacingBefore(20);

        Paragraph paragraphData = new Paragraph("Data de emissão: " + currentDateTime, fontInfoHeader);
        paragraphData.setSpacingBefore(20);

        Paragraph paragraphInfoProblema = new Paragraph(demanda.getProblema(), fontInformacoes);
        paragraphInfoProblema.setIndentationLeft(40);
        paragraphInfoProblema.setSpacingBefore(5);

        Paragraph paragraphProposta = new Paragraph("Proposta: ", fontSubtitulo);
        paragraphProposta.setSpacingBefore(15);

        Paragraph paragraphInfoProposta = new Paragraph(demanda.getProposta(), fontInformacoes);
        paragraphInfoProposta.setIndentationLeft(40);
        paragraphInfoProposta.setSpacingBefore(5);

        Paragraph paragraphBeneficios = new Paragraph("Benefícios: ", fontSubtitulo);
        paragraphBeneficios.setSpacingBefore(15);

        document.add(img);
        document.add(paragraphData);
        document.add(paragraphTitulo);
        document.add(paragraphProblema);
        document.add(paragraphInfoProblema);
        document.add(paragraphProposta);
        document.add(paragraphInfoProposta);
        document.add(paragraphBeneficios);

        // Criando tabela para os benefícios

        for (Beneficio beneficio : demanda.getBeneficios()) {
            PdfPTable tableBeneficios = new PdfPTable(4);

            tableBeneficios.setWidthPercentage(100);
            tableBeneficios.setWidths(new int[]{3, 3, 3, 3});
            tableBeneficios.setSpacingBefore(15);

            tableBeneficios.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableBeneficios.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tableBeneficios.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(azulWeg);
            cell.setPadding(5);
            cell.setBorder(0);

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(CMYKColor.WHITE);
            font.setStyle(Font.BOLD);
            font.setSize(12);

            cell.setPhrase(new Phrase("Tipo", font));
            tableBeneficios.addCell(cell);
            cell.setPhrase(new Phrase("Valor Mensal", font));
            tableBeneficios.addCell(cell);
            cell.setPhrase(new Phrase("Moeda", font));
            tableBeneficios.addCell(cell);
            cell.setPhrase(new Phrase("Memória de Cálculo", font));
            tableBeneficios.addCell(cell);

            tableBeneficios.addCell(String.valueOf(beneficio.getTipoBeneficio()));
            tableBeneficios.addCell(String.valueOf(beneficio.getValor_mensal()));
            tableBeneficios.addCell(String.valueOf(beneficio.getMoeda()));
            tableBeneficios.addCell(String.valueOf(beneficio.getMemoriaCalculo()));

            document.add(tableBeneficios);
        }

        Paragraph paragraphFrequencia = new Paragraph("Frequência de Uso: ", fontSubtitulo);
        paragraphFrequencia.setAlignment(Paragraph.ANCHOR);
        paragraphFrequencia.setSpacingBefore(15);

        Paragraph paragraphInfoFrequencia = new Paragraph(demanda.getFrequencia(), fontInformacoes);
        paragraphInfoFrequencia.setAlignment(Paragraph.ANCHOR);
        paragraphInfoFrequencia.setIndentationLeft(40);
        paragraphInfoFrequencia.setSpacingBefore(5);

        Paragraph paragraphAnexos = new Paragraph("Anexos: ", fontSubtitulo);
        paragraphAnexos.setAlignment(Paragraph.ANCHOR);
        paragraphAnexos.setSpacingBefore(15);

        document.add(paragraphFrequencia);
        document.add(paragraphInfoFrequencia);

        // Adicionando informações após o retorno do analista

        if (demanda.getTamanho() != null) {
            Chunk chunkTamanho = new Chunk("Tamanho: ", fontSubtitulo);
            Chunk chunkValorTamanho = new Chunk(demanda.getTamanho(), fontInformacoes);
            Paragraph paragraphTamanho = new Paragraph();
            paragraphTamanho.add(chunkTamanho);
            paragraphTamanho.add(chunkValorTamanho);

            Chunk chunkSecao = new Chunk("Seção de TI: ", fontSubtitulo);
            Chunk chunkValorSecao = new Chunk(demanda.getSecaoTI().getNomeSecao(), fontInformacoes);
            Paragraph paragraphSecao = new Paragraph();
            paragraphSecao.add(chunkSecao);
            paragraphSecao.add(chunkValorSecao);

            Chunk chunkBuSolicitante = new Chunk("BU Solicitante: ", fontSubtitulo);
            Chunk chunkValorBuSolicitante = new Chunk(demanda.getBuSolicitante().getNomeBu(), fontInformacoes);
            Paragraph paragraphBuSolicitante = new Paragraph();
            paragraphBuSolicitante.add(chunkBuSolicitante);
            paragraphBuSolicitante.add(chunkValorBuSolicitante);

            Chunk chunkBuBeneficiadas = new Chunk("BUs Beneficiadas: ", fontSubtitulo);
            Paragraph paragraphBuBeneficiadas = new Paragraph();
            paragraphBuBeneficiadas.add(chunkBuBeneficiadas);

            for (Bu bu : demanda.getBusBeneficiadas()) {
                Chunk chunkValorBuBeneficiadas = new Chunk(bu.getNomeBu() + " ", fontInformacoes);
                paragraphBuBeneficiadas.add(chunkValorBuBeneficiadas);
            }

            Chunk chunkForum = new Chunk("Fórum: ", fontSubtitulo);
            Chunk chunkValorForum = new Chunk(demanda.getForum().getNomeForum(), fontInformacoes);
            Paragraph paragraphForum = new Paragraph();
            paragraphForum.setSpacingBefore(15);
            paragraphForum.add(chunkForum);
            paragraphForum.add(chunkValorForum);

            PdfPTable tableBu = new PdfPTable(2);
            tableBu.setWidthPercentage(100);
            tableBu.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell cell1 = new PdfPCell(paragraphTamanho);
            cell1.setBorder(Rectangle.NO_BORDER);

            PdfPCell cell2 = new PdfPCell(paragraphSecao);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

            tableBu.addCell(cell1);
            tableBu.addCell(cell2);
            tableBu.setSpacingBefore(15);

            PdfPTable table3 = new PdfPTable(2);
            table3.setWidthPercentage(100);
            table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell cell3 = new PdfPCell(paragraphBuSolicitante);
            cell3.setBorder(Rectangle.NO_BORDER);

            PdfPCell cell4 = new PdfPCell(paragraphBuBeneficiadas);
            cell4.setBorder(Rectangle.NO_BORDER);
            cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);

            table3.addCell(cell3);
            table3.addCell(cell4);
            table3.setSpacingBefore(15);

            document.add(tableBu);
            document.add(table3);
            document.add(paragraphForum);
        }

        document.add(paragraphAnexos);

        // Adicionando o nome dos arquivos

        for (Anexo anexo : demanda.getAnexo()) {
            Paragraph paragraphInfoAnexos = new Paragraph(anexo.getNome() + "." + anexo.getTipo(), fontInformacoes);
            paragraphInfoAnexos.setIndentationLeft(40);
            paragraphInfoAnexos.setSpacingBefore(5);

            document.add(paragraphInfoAnexos);
        }

        // Encerrando o documento

        document.close();

        return document;
    }

    public Document exportProposta(HttpServletResponse response, Proposta proposta) throws IOException, DocumentException {

        Demanda demanda = demandaService.findById(proposta.getDemanda().getId()).get();

        //Colors
        BaseColor azulWeg = new BaseColor(0, 87, 157);

        // Formatação da data para quando baixar o documento pdf

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Criando a página do pdf

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();

//        String html = "<h1>123ksdjfçlasjfçskldajfçkldsajfçskldajç</h1>";

//        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(html.getBytes()));

        // Criando a logo da weg para o modelo pdf

        Image img = Image.getInstance("https://logospng.org/download/weg/logo-weg-2048.png");

        int indentation = 0;
        float scale = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - indentation) / img.getWidth()) * 20;

        img.scalePercent(scale);
        img.setAbsolutePosition(460, 740);

        // Criando as fontes da página

        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitulo.setSize(24);
        fontTitulo.setColor(azulWeg);
        fontTitulo.setStyle(Font.BOLD);

        Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA);
        fontSubtitulo.setSize(15);
        fontSubtitulo.setStyle(Font.BOLD);

        Font fontInformacoes = FontFactory.getFont(FontFactory.HELVETICA);

        Font fontInfoHeader = FontFactory.getFont(FontFactory.HELVETICA);
        fontInfoHeader.setSize(12);

        Font fontInfoHeaderProposta = FontFactory.getFont(FontFactory.HELVETICA);
        fontInfoHeaderProposta.setSize(15);
        fontInfoHeaderProposta.setColor(azulWeg);
        fontInfoHeaderProposta.setStyle(Font.BOLD);

        // Criando a formatação da página pdf

        Paragraph paragraphData = new Paragraph("Data de emissão: " + currentDateTime, fontInfoHeader);
        paragraphData.setSpacingBefore(20);

        Chunk chunkPPMData = new Chunk("PPM   " + String.valueOf(proposta.getCodigoPPM()) + "               ", fontInfoHeaderProposta);
        Chunk chunkValorPPMData = new Chunk("DATA   " + String.valueOf(proposta.getData()), fontInfoHeaderProposta);
        Paragraph paragraphPPMData = new Paragraph();
        paragraphPPMData.add(chunkPPMData);
        paragraphPPMData.add(chunkValorPPMData);
        paragraphPPMData.setSpacingBefore(20);

        Paragraph paragraphTitulo = new Paragraph(proposta.getDemanda().getTitulo(), fontTitulo);
        paragraphTitulo.setSpacingBefore(10);

        Chunk chunkSolicitante = new Chunk("Solicitante: ", fontSubtitulo);
        Chunk chunkValorSolicitante = new Chunk(String.valueOf(proposta.getSolicitante().getNome() + " - " + proposta.getSolicitante().getDepartamento().getNome()), fontInformacoes);
        Paragraph paragraphSolicitante = new Paragraph();
        paragraphSolicitante.add(chunkSolicitante);
        paragraphSolicitante.add(chunkValorSolicitante);
        paragraphSolicitante.setSpacingBefore(20);

        Chunk chunkGerente = new Chunk("Gerente: ", fontSubtitulo);
        Chunk chunkValorGerente = new Chunk(String.valueOf(proposta.getGerente().getNome() + " - " + proposta.getGerente().getDepartamento().getNome()), fontInformacoes);
        Paragraph paragraphGerente = new Paragraph();
        paragraphGerente.add(chunkGerente);
        paragraphGerente.add(chunkValorGerente);
        paragraphGerente.setSpacingBefore(15);

        Chunk chunkBuSolicitante = new Chunk("BU Solicitante: ", fontSubtitulo);
        Chunk chunkValorBuSolicitante = new Chunk(proposta.getBuSolicitante().getNomeBu(), fontInformacoes);
        Paragraph paragraphBuSolicitante = new Paragraph();
        paragraphBuSolicitante.add(chunkBuSolicitante);
        paragraphBuSolicitante.add(chunkValorBuSolicitante);
        paragraphBuSolicitante.setSpacingBefore(15);

        Chunk chunkForum = new Chunk("Fórum: ", fontSubtitulo);
        Chunk chunkValorForum = new Chunk(proposta.getForum().getNomeForum(), fontInformacoes);
        Paragraph paragraphForum = new Paragraph();
        paragraphForum.add(chunkForum);
        paragraphForum.add(chunkValorForum);

        Chunk chunkTamanho = new Chunk("Tamanho ", fontSubtitulo);
        Chunk chunkValorTamanho = new Chunk(proposta.getTamanho(), fontInformacoes);
        Paragraph paragraphTamanho = new Paragraph();
        paragraphTamanho.add(chunkTamanho);
        paragraphTamanho.add(chunkValorTamanho);

        PdfPTable tableForumTamanho = new PdfPTable(2);
        tableForumTamanho.setWidthPercentage(100);
        tableForumTamanho.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        PdfPCell cell4 = new PdfPCell(paragraphForum);
        cell4.setBorder(Rectangle.NO_BORDER);

        PdfPCell cell5 = new PdfPCell(paragraphTamanho);
        cell5.setBorder(Rectangle.NO_BORDER);
        cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);

        tableForumTamanho.addCell(cell4);
        tableForumTamanho.addCell(cell5);
        tableForumTamanho.setSpacingBefore(15);

        Paragraph paragraphProposta = new Paragraph("Proposta: ", fontSubtitulo);
        paragraphProposta.setSpacingBefore(10);

        Paragraph paragraphInfoProposta = new Paragraph(proposta.getDemanda().getProposta(), fontInformacoes);
        paragraphInfoProposta.setSpacingBefore(3);
        paragraphInfoProposta.setIndentationLeft(10);

        Paragraph paragraphProblema = new Paragraph("Problema: ", fontSubtitulo);
        paragraphProblema.setSpacingBefore(15);

        Paragraph paragraphInfoProblema = new Paragraph(proposta.getDemanda().getProblema(), fontInformacoes);
        paragraphInfoProblema.setSpacingBefore(3);
        paragraphInfoProblema.setIndentationLeft(10);

        Paragraph paragraphEscopo = new Paragraph("Escopo da Proposta: ", fontSubtitulo);
        paragraphEscopo.setSpacingBefore(15);

        Paragraph paragraphInfoEscopo = new Paragraph(String.valueOf(proposta.getEscopo()), fontInformacoes);
        paragraphInfoEscopo.setSpacingBefore(3);
        paragraphInfoEscopo.setIndentationLeft(10);

        Paragraph paragraphTabelaCustos = new Paragraph("Tabela de Custos: ", fontSubtitulo);
        paragraphTabelaCustos.setSpacingBefore(15);

        Chunk chunkFrequencia = new Chunk("Frequência: ", fontSubtitulo);
        Chunk chunkValorFrequencia = new Chunk(proposta.getFrequencia(), fontInformacoes);
        Paragraph paragraphFrequencia = new Paragraph();
        paragraphFrequencia.add(chunkFrequencia);
        paragraphFrequencia.add(chunkValorFrequencia);
        paragraphFrequencia.setSpacingBefore(15);

        // Adicionando os paragrafos no documento

        document.add(img);
        document.add(paragraphData);
        document.add(paragraphPPMData);
        document.add(paragraphTitulo);
        document.add(paragraphSolicitante);
        document.add(paragraphBuSolicitante);
        document.add(paragraphGerente);
        document.add(tableForumTamanho);
        document.add(paragraphProposta);
        document.add(paragraphInfoProposta);
        document.add(paragraphProblema);
        document.add(paragraphInfoProblema);
        document.add(paragraphEscopo);
        document.add(paragraphInfoEscopo);
        document.add(paragraphFrequencia);
        document.add(paragraphTabelaCustos);

        // Adicionando a tabela de CC

        for (TabelaCusto tableCusto : proposta.getTabelaCustos()) {
            PdfPTable tableCustos = new PdfPTable(4);

            tableCustos.setWidthPercentage(100);
            tableCustos.setWidths(new int[]{3, 3, 3, 3});
            tableCustos.setSpacingBefore(15);

            tableCustos.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableCustos.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCustos.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(azulWeg);
            cell.setPadding(5);
            cell.setBorder(0);

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(CMYKColor.WHITE);
            font.setStyle(Font.BOLD);
            font.setSize(12);

            cell.setPhrase(new Phrase("Tipo da Despesa", font));
            tableCustos.addCell(cell);
            cell.setPhrase(new Phrase("Perfil da Despesa", font));
            tableCustos.addCell(cell);
            cell.setPhrase(new Phrase("Período de Execução (meses)", font));
            tableCustos.addCell(cell);
            cell.setPhrase(new Phrase("Horas", font));
            tableCustos.addCell(cell);

            for (Custo custos : tableCusto.getCustos()) {
                tableCustos.addCell(custos.getTipoDespesa());
                tableCustos.addCell(custos.getPerfilDespesa());
                tableCustos.addCell(String.valueOf(custos.getPeriodoExecucao()));
                tableCustos.addCell(String.valueOf(custos.getHoras()));
                tableCustos.addCell(String.valueOf(custos.getValorHora()));
            }

            PdfPTable tableValorHora = new PdfPTable(2);

            tableValorHora.setWidthPercentage(100);
            tableValorHora.setWidths(new int[]{3, 3});
            tableValorHora.setSpacingBefore(15);

            tableValorHora.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableValorHora.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tableValorHora.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell2 = new PdfPCell();
            cell2.setBackgroundColor(azulWeg);
            cell2.setPadding(5);
            cell2.setBorder(0);

            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_CENTER);

            // FAZER O CALCULO

            cell2.setPhrase(new Phrase("Valor Hora", font));
            tableValorHora.addCell(cell2);
            cell2.setPhrase(new Phrase("Total", font));
            tableValorHora.addCell(cell2);

            tableValorHora.addCell("10");
            tableValorHora.addCell("100");

            PdfPTable tableCC = new PdfPTable(2);

            tableCC.setWidthPercentage(100);
            tableCC.setWidths(new int[]{3, 3});
            tableCC.setSpacingBefore(20);

            tableCC.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableCC.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCC.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell3 = new PdfPCell();
            cell3.setBackgroundColor(azulWeg);
            cell3.setPadding(5);
            cell3.setBorder(0);

            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_CENTER);

            cell3.setPhrase(new Phrase("CCs", font));
            tableCC.addCell(cell3);
            cell3.setPhrase(new Phrase("Porcentagem", font));
            tableCC.addCell(cell3);

            for (CC cc : tableCusto.getCcs()) {
                tableCC.addCell(String.valueOf(cc.getCodigo()));
                tableCC.addCell(String.valueOf(cc.getPorcentagem()));
            }

            document.add(tableCustos);
            document.add(tableValorHora);
            document.add(tableCC);
        }

        Paragraph paragraphBeneficios = new Paragraph("Benefícios: ", fontSubtitulo);
        paragraphBeneficios.setAlignment(Paragraph.ANCHOR);
        paragraphBeneficios.setSpacingBefore(15);

        document.add(paragraphBeneficios);

        // Adicionando a tabela de benefícios

        for (Beneficio beneficio : proposta.getDemanda().getBeneficios()) {
            PdfPTable tableBeneficios = new PdfPTable(4);

            tableBeneficios.setWidthPercentage(100);
            tableBeneficios.setWidths(new int[]{3, 3, 3, 3});
            tableBeneficios.setSpacingBefore(15);

            tableBeneficios.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableBeneficios.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tableBeneficios.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(azulWeg);
            cell.setPadding(5);
            cell.setBorder(0);

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);

            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(CMYKColor.WHITE);
            font.setStyle(Font.BOLD);
            font.setSize(12);

            cell.setPhrase(new Phrase("Tipo", font));
            tableBeneficios.addCell(cell);
            cell.setPhrase(new Phrase("Valor Mensal", font));
            tableBeneficios.addCell(cell);
            cell.setPhrase(new Phrase("Moeda", font));
            tableBeneficios.addCell(cell);
            cell.setPhrase(new Phrase("Memória de Cálculo", font));
            tableBeneficios.addCell(cell);

            tableBeneficios.addCell(String.valueOf(beneficio.getTipoBeneficio()));
            tableBeneficios.addCell(String.valueOf(beneficio.getValor_mensal()));
            tableBeneficios.addCell(String.valueOf(beneficio.getMoeda()));
            tableBeneficios.addCell(String.valueOf(beneficio.getMemoriaCalculo()));

            document.add(tableBeneficios);
        }

        Chunk chunkBuBeneficiadas = new Chunk("BUs Beneficiadas: ", fontSubtitulo);
        Paragraph paragraphBuBeneficiadas = new Paragraph();
        paragraphBuBeneficiadas.add(chunkBuBeneficiadas);
        paragraphBuBeneficiadas.setSpacingBefore(15);

        for (Bu bu : demanda.getBusBeneficiadas()) {
            Chunk chunkValorBuBeneficiadas = new Chunk(bu.getNomeBu() + " ", fontInformacoes);
            paragraphBuBeneficiadas.add(chunkValorBuBeneficiadas);
        }

        Chunk chunkLink = new Chunk("Link Jira: ", fontSubtitulo);
        Chunk chunkValorLink = new Chunk(proposta.getLinkJira(), fontInformacoes);
        Paragraph paragraphLinkJira = new Paragraph();
        paragraphLinkJira.add(chunkLink);
        paragraphLinkJira.add(chunkValorLink);
        paragraphLinkJira.setSpacingBefore(15);

        Chunk chunkExecucao = new Chunk("Período de Execução: ", fontSubtitulo);
        Chunk chunkValorExecucao = new Chunk(String.valueOf(proposta.getInicioExecucao()) + " à " + String.valueOf(proposta.getFimExecucao()), fontInformacoes);
        Paragraph paragraphPeriodoExecucao = new Paragraph();
        paragraphPeriodoExecucao.add(chunkExecucao);
        paragraphPeriodoExecucao.add(chunkValorExecucao);
        paragraphPeriodoExecucao.setSpacingBefore(15);

        Chunk chunkPayback = new Chunk("Payback: ", fontSubtitulo);
        Chunk chunkValorPayback = new Chunk(String.valueOf(proposta.getPaybackValor()) + " " + String.valueOf(proposta.getPaybackTipo()), fontInformacoes);
        Paragraph paragraphPayback = new Paragraph();
        paragraphPayback.add(chunkPayback);
        paragraphPayback.add(chunkValorPayback);
        paragraphPayback.setSpacingBefore(15);

        Paragraph paragraphAnexos = new Paragraph("Anexos: ", fontSubtitulo);
        paragraphAnexos.setAlignment(Paragraph.ANCHOR);
        paragraphAnexos.setSpacingBefore(15);

        document.add(paragraphBuBeneficiadas);
        document.add(paragraphLinkJira);
        document.add(paragraphPeriodoExecucao);
        document.add(paragraphPayback);
        document.add(paragraphAnexos);

        // Adicionando o nome dos anexos

        for (Anexo anexo : proposta.getDemanda().getAnexo()) {
            Paragraph paragraphInfoAnexos = new Paragraph(anexo.getNome() + "." + anexo.getTipo(), fontInformacoes);
            paragraphInfoAnexos.setIndentationLeft(40);
            paragraphInfoAnexos.setSpacingBefore(5);

            document.add(paragraphInfoAnexos);
        }

        // Adicionando os responsáveis pelo negócio

        for (ResponsavelNegocio responsavel : proposta.getResponsavelNegocio()) {
            Paragraph paragraphResponsavelNegocio = new Paragraph(responsavel.getNome() + " - " + responsavel.getArea(), fontInformacoes);
            paragraphResponsavelNegocio.setAlignment(Element.ALIGN_CENTER);
            paragraphResponsavelNegocio.setSpacingBefore(10);

            document.add(paragraphResponsavelNegocio);
        }

        Paragraph paragraphRN = new Paragraph("Responsáveis pelo Negócio", fontSubtitulo);
        paragraphRN.setAlignment(Element.ALIGN_CENTER);
        paragraphRN.setSpacingBefore(10);

        document.add(paragraphRN);

        // Encerrando o documento

        document.close();

        return document;
    }

    public Document exportPauta(HttpServletResponse response, Pauta pauta) throws IOException, DocumentException {

        // Colors
        BaseColor azulWeg = new BaseColor(0, 87, 157);

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

        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitulo.setSize(24);
        fontTitulo.setStyle(Font.BOLD);

        Font fontTituloProposta = FontFactory.getFont(FontFactory.HELVETICA);
        fontTituloProposta.setSize(24);
        fontTituloProposta.setStyle(Font.BOLD);

        Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA);
        fontSubtitulo.setSize(15);
        fontSubtitulo.setStyle(Font.BOLD);

        Font fontInformacoes = FontFactory.getFont(FontFactory.HELVETICA);

        Font fontInfoHeader = FontFactory.getFont(FontFactory.HELVETICA);
        fontInfoHeader.setSize(12);

        Font fontInfoHeaderProposta = FontFactory.getFont(FontFactory.HELVETICA);
        fontInfoHeaderProposta.setSize(13);
        fontInfoHeaderProposta.setStyle(Font.BOLD);

        Font fontInfoHeaderPauta = FontFactory.getFont(FontFactory.HELVETICA);
        fontInfoHeaderPauta.setSize(15);
        fontInfoHeaderPauta.setColor(azulWeg);
        fontInfoHeaderPauta.setStyle(Font.BOLD);

        // Formatação da página pdf

        Paragraph paragraphData = new Paragraph("Data de emissão: " + currentDateTime, fontInfoHeader);
        paragraphData.setSpacingBefore(20);

        Paragraph paragraphPauta = new Paragraph("Pauta", fontTitulo);
        paragraphPauta.setSpacingBefore(22);
        paragraphPauta.setAlignment(Element.ALIGN_CENTER);

        Paragraph paragraphNumeroSequencial = new Paragraph("Número Sequencial: " + pauta.getNumeroSequencial(), fontInfoHeaderProposta);
        paragraphNumeroSequencial.setSpacingBefore(20);

        Paragraph paragraphAno = new Paragraph("Ano: 2023", fontInfoHeaderProposta);
        paragraphAno.setSpacingBefore(5);

        document.add(img);
        document.add(paragraphData);
        document.add(paragraphPauta);
        document.add(paragraphNumeroSequencial);
        document.add(paragraphAno);

        int contadorProposta = 1;

        // Adicionando as propostas dentro da pauta

        for (Proposta proposta : pauta.getPropostas()) {

            Demanda demanda = demandaService.findById(proposta.getDemanda().getId()).get();

            Paragraph paragraphContadorProposta = new Paragraph("Proposta " + contadorProposta, fontTituloProposta);
            paragraphContadorProposta.setSpacingBefore(20);
            paragraphContadorProposta.setAlignment(Element.ALIGN_CENTER);

            contadorProposta++;

            Chunk chunkPPMData = new Chunk("PPM   " + String.valueOf(proposta.getCodigoPPM()) + "    ", fontInfoHeaderPauta);
            Chunk chunkValorPPMData = new Chunk("DATA   " + String.valueOf(proposta.getData()) + "    ", fontInfoHeaderPauta);
            Chunk chunkAtaPublicada = new Chunk();

            if (proposta.getPublicada()) {
                chunkAtaPublicada = new Chunk("PUBLICADA", fontInfoHeaderPauta);
            } else {
                chunkAtaPublicada = new Chunk("NÃO PUBLICADA", fontInfoHeaderPauta);
            }

            Paragraph paragraphHeaderProposta = new Paragraph();
            paragraphHeaderProposta.add(chunkPPMData);
            paragraphHeaderProposta.add(chunkValorPPMData);
            paragraphHeaderProposta.add(chunkAtaPublicada);
            paragraphHeaderProposta.setSpacingBefore(40);

            Paragraph paragraphTitulo = new Paragraph(proposta.getDemanda().getTitulo(), fontTitulo);
            paragraphTitulo.setSpacingBefore(10);

            Chunk chunkSolicitante = new Chunk("Solicitante: ", fontSubtitulo);
            Chunk chunkValorSolicitante = new Chunk(String.valueOf(proposta.getSolicitante().getNome() + " - " + proposta.getSolicitante().getDepartamento().getNome()), fontInformacoes);
            Paragraph paragraphSolicitante = new Paragraph();
            paragraphSolicitante.add(chunkSolicitante);
            paragraphSolicitante.add(chunkValorSolicitante);
            paragraphSolicitante.setSpacingBefore(20);

            Chunk chunkGerente = new Chunk("Gerente: ", fontSubtitulo);
            Chunk chunkValorGerente = new Chunk(String.valueOf(proposta.getGerente().getNome() + " - " + proposta.getGerente().getDepartamento().getNome()), fontInformacoes);
            Paragraph paragraphGerente = new Paragraph();
            paragraphGerente.add(chunkGerente);
            paragraphGerente.add(chunkValorGerente);
            paragraphGerente.setSpacingBefore(15);

            Chunk chunkBuSolicitante = new Chunk("BU Solicitante: ", fontSubtitulo);
            Chunk chunkValorBuSolicitante = new Chunk(proposta.getBuSolicitante().getNomeBu(), fontInformacoes);
            Paragraph paragraphBuSolicitante = new Paragraph();
            paragraphBuSolicitante.add(chunkBuSolicitante);
            paragraphBuSolicitante.add(chunkValorBuSolicitante);
            paragraphBuSolicitante.setSpacingBefore(15);

            Chunk chunkForum = new Chunk("Fórum: ", fontSubtitulo);
            Chunk chunkValorForum = new Chunk(proposta.getForum().getNomeForum(), fontInformacoes);
            Paragraph paragraphForum = new Paragraph();
            paragraphForum.add(chunkForum);
            paragraphForum.add(chunkValorForum);

            Chunk chunkTamanho = new Chunk("Tamanho ", fontSubtitulo);
            Chunk chunkValorTamanho = new Chunk(proposta.getTamanho(), fontInformacoes);
            Paragraph paragraphTamanho = new Paragraph();
            paragraphTamanho.add(chunkTamanho);
            paragraphTamanho.add(chunkValorTamanho);

            PdfPTable tableTamanhoForum = new PdfPTable(2);
            tableTamanhoForum.setWidthPercentage(100);
            tableTamanhoForum.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell cell4 = new PdfPCell(paragraphForum);
            cell4.setBorder(Rectangle.NO_BORDER);

            PdfPCell cell5 = new PdfPCell(paragraphTamanho);
            cell5.setBorder(Rectangle.NO_BORDER);
            cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);

            tableTamanhoForum.addCell(cell4);
            tableTamanhoForum.addCell(cell5);
            tableTamanhoForum.setSpacingBefore(15);

            Paragraph paragraphProposta = new Paragraph("Proposta: ", fontSubtitulo);
            paragraphProposta.setSpacingBefore(10);

            Paragraph paragraphInfoProposta = new Paragraph(proposta.getDemanda().getProposta(), fontInformacoes);
            paragraphInfoProposta.setSpacingBefore(3);
            paragraphInfoProposta.setIndentationLeft(10);

            Paragraph paragraphProblema = new Paragraph("Problema: ", fontSubtitulo);
            paragraphProblema.setSpacingBefore(15);

            Paragraph paragraphInfoProblema = new Paragraph(proposta.getDemanda().getProblema(), fontInformacoes);
            paragraphInfoProblema.setSpacingBefore(3);
            paragraphInfoProblema.setIndentationLeft(10);

            Paragraph paragraphEscopo = new Paragraph("Escopo da Proposta: ", fontSubtitulo);
            paragraphEscopo.setSpacingBefore(15);

            Paragraph paragraphInfoEscopo = new Paragraph(String.valueOf(proposta.getEscopo()), fontInformacoes);
            paragraphInfoEscopo.setSpacingBefore(3);
            paragraphInfoEscopo.setIndentationLeft(10);

            Paragraph paragraphTabelaCustos = new Paragraph("Tabela de Custos: ", fontSubtitulo);
            paragraphTabelaCustos.setSpacingBefore(15);

            Chunk chunkFrequencia = new Chunk("Frequência: ", fontSubtitulo);
            Chunk chunkValorFrequencia = new Chunk(proposta.getFrequencia(), fontInformacoes);
            Paragraph paragraphFrequencia = new Paragraph();
            paragraphFrequencia.add(chunkFrequencia);
            paragraphFrequencia.add(chunkValorFrequencia);
            paragraphFrequencia.setSpacingBefore(15);

            // Adicionando os paragrafos no documento

            document.add(paragraphContadorProposta);
            document.add(paragraphHeaderProposta);
            document.add(paragraphTitulo);
            document.add(paragraphSolicitante);
            document.add(paragraphBuSolicitante);
            document.add(paragraphGerente);
            document.add(tableTamanhoForum);
            document.add(paragraphProposta);
            document.add(paragraphInfoProposta);
            document.add(paragraphProblema);
            document.add(paragraphInfoProblema);
            document.add(paragraphEscopo);
            document.add(paragraphInfoEscopo);
            document.add(paragraphFrequencia);
            document.add(paragraphTabelaCustos);

            // Adicionando a tabela de CC

            for (TabelaCusto tableCusto : proposta.getTabelaCustos()) {
                PdfPTable tableCustos = new PdfPTable(4);

                tableCustos.setWidthPercentage(100);
                tableCustos.setWidths(new int[]{3, 3, 3, 3});
                tableCustos.setSpacingBefore(15);

                tableCustos.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                tableCustos.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCustos.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(azulWeg);
                cell.setPadding(5);
                cell.setBorder(0);

                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_CENTER);

                Font font = FontFactory.getFont(FontFactory.HELVETICA);
                font.setColor(CMYKColor.WHITE);
                font.setStyle(Font.BOLD);
                font.setSize(12);

                cell.setPhrase(new Phrase("Tipo da Despesa", font));
                tableCustos.addCell(cell);
                cell.setPhrase(new Phrase("Perfil da Despesa", font));
                tableCustos.addCell(cell);
                cell.setPhrase(new Phrase("Período de Execução (meses)", font));
                tableCustos.addCell(cell);
                cell.setPhrase(new Phrase("Horas", font));
                tableCustos.addCell(cell);

                for (Custo custos : tableCusto.getCustos()) {
                    tableCustos.addCell(custos.getTipoDespesa());
                    tableCustos.addCell(custos.getPerfilDespesa());
                    tableCustos.addCell(String.valueOf(custos.getPeriodoExecucao()));
                    tableCustos.addCell(String.valueOf(custos.getHoras()));
                    tableCustos.addCell(String.valueOf(custos.getValorHora()));
                }

                PdfPTable tableValorTotal = new PdfPTable(2);

                tableValorTotal.setWidthPercentage(100);
                tableValorTotal.setWidths(new int[]{3, 3});
                tableValorTotal.setSpacingBefore(15);

                tableValorTotal.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                tableValorTotal.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                tableValorTotal.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell2 = new PdfPCell();
                cell2.setBackgroundColor(azulWeg);
                cell2.setPadding(5);
                cell2.setBorder(0);

                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setVerticalAlignment(Element.ALIGN_CENTER);

                cell2.setPhrase(new Phrase("Valor Hora", font));
                tableValorTotal.addCell(cell2);
                cell2.setPhrase(new Phrase("Total", font));
                tableValorTotal.addCell(cell2);

                tableValorTotal.addCell("10");
                tableValorTotal.addCell("100");

                PdfPTable tableCC = new PdfPTable(2);

                tableCC.setWidthPercentage(100);
                tableCC.setWidths(new int[]{3, 3});
                tableCC.setSpacingBefore(20);

                tableCC.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                tableCC.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                tableCC.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell3 = new PdfPCell();
                cell3.setBackgroundColor(azulWeg);
                cell3.setPadding(5);
                cell3.setBorder(0);

                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setVerticalAlignment(Element.ALIGN_CENTER);

                cell3.setPhrase(new Phrase("CCs", font));
                tableCC.addCell(cell3);
                cell3.setPhrase(new Phrase("Porcentagem", font));
                tableCC.addCell(cell3);

                for (CC cc : tableCusto.getCcs()) {
                    tableCC.addCell(String.valueOf(cc.getCodigo()));
                    tableCC.addCell(String.valueOf(cc.getPorcentagem()));
                }

                document.add(tableCustos);
                document.add(tableValorTotal);
                document.add(tableCC);
            }

            Paragraph paragraphBeneficios = new Paragraph("Benefícios: ", fontSubtitulo);
            paragraphBeneficios.setAlignment(Paragraph.ANCHOR);
            paragraphBeneficios.setSpacingBefore(15);

            document.add(paragraphBeneficios);

            // Adicionando a tabela de benefícios

            for (Beneficio beneficio : proposta.getDemanda().getBeneficios()) {
                PdfPTable tableBeneficios = new PdfPTable(4);

                tableBeneficios.setWidthPercentage(100);
                tableBeneficios.setWidths(new int[]{3, 3, 3, 3});
                tableBeneficios.setSpacingBefore(15);

                tableBeneficios.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                tableBeneficios.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                tableBeneficios.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(azulWeg);
                cell.setPadding(5);
                cell.setBorder(0);

                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_CENTER);

                Font font = FontFactory.getFont(FontFactory.HELVETICA);
                font.setColor(CMYKColor.WHITE);
                font.setStyle(Font.BOLD);
                font.setSize(12);

                cell.setPhrase(new Phrase("Tipo", font));
                tableBeneficios.addCell(cell);
                cell.setPhrase(new Phrase("Valor Mensal", font));
                tableBeneficios.addCell(cell);
                cell.setPhrase(new Phrase("Moeda", font));
                tableBeneficios.addCell(cell);
                cell.setPhrase(new Phrase("Memória de Cálculo", font));
                tableBeneficios.addCell(cell);

                tableBeneficios.addCell(String.valueOf(beneficio.getTipoBeneficio()));
                tableBeneficios.addCell(String.valueOf(beneficio.getValor_mensal()));
                tableBeneficios.addCell(String.valueOf(beneficio.getMoeda()));
                tableBeneficios.addCell(String.valueOf(beneficio.getMemoriaCalculo()));

                document.add(tableBeneficios);
            }

            Chunk chunkBuBeneficiadas = new Chunk("BUs Beneficiadas: ", fontSubtitulo);
            Paragraph paragraphBuBeneficiadas = new Paragraph();
            paragraphBuBeneficiadas.add(chunkBuBeneficiadas);
            paragraphBuBeneficiadas.setSpacingBefore(15);

            for (Bu bu : demanda.getBusBeneficiadas()) {
                Chunk chunkValorBuBeneficiadas = new Chunk(bu.getNomeBu() + " ", fontInformacoes);
                paragraphProblema.add(chunkValorBuBeneficiadas);
            }

            Chunk chunkLink = new Chunk("Link Jira: ", fontSubtitulo);
            Chunk chunkValorLink = new Chunk(proposta.getLinkJira(), fontInformacoes);
            Paragraph paragraphLinkJira = new Paragraph();
            paragraphLinkJira.add(chunkLink);
            paragraphLinkJira.add(chunkValorLink);
            paragraphLinkJira.setSpacingBefore(15);

            Chunk chunkExecucao = new Chunk("Período de Execução: ", fontSubtitulo);
            Chunk chunkValorExecucao = new Chunk(String.valueOf(proposta.getInicioExecucao()) + " à " + String.valueOf(proposta.getFimExecucao()), fontInformacoes);
            Paragraph paragraphPeriodoExecucao = new Paragraph();
            paragraphPeriodoExecucao.add(chunkExecucao);
            paragraphPeriodoExecucao.add(chunkValorExecucao);
            paragraphPeriodoExecucao.setSpacingBefore(15);

            Chunk chunkPayback = new Chunk("Payback: ", fontSubtitulo);
            Chunk chunkValorPayback = new Chunk(String.valueOf(proposta.getPaybackValor()) + " " + String.valueOf(proposta.getPaybackTipo()), fontInformacoes);
            Paragraph paragraphPayback = new Paragraph();
            paragraphPayback.add(chunkPayback);
            paragraphPayback.add(chunkValorPayback);
            paragraphPayback.setSpacingBefore(15);

            Paragraph paragraphAnexos = new Paragraph("Anexos: ", fontSubtitulo);
            paragraphAnexos.setAlignment(Paragraph.ANCHOR);
            paragraphAnexos.setSpacingBefore(15);

            document.add(paragraphBuBeneficiadas);
            document.add(paragraphLinkJira);
            document.add(paragraphPeriodoExecucao);
            document.add(paragraphPayback);
            document.add(paragraphAnexos);

            // Adicionando o nome dos anexos

            for (Anexo anexo : proposta.getDemanda().getAnexo()) {
                Paragraph paragraphInfoAnexos = new Paragraph(anexo.getNome() + "." + anexo.getTipo(), fontInformacoes);
                paragraphInfoAnexos.setIndentationLeft(40);
                paragraphInfoAnexos.setSpacingBefore(5);

                document.add(paragraphInfoAnexos);
            }

            // Adicionando os responsáveis pelo negócio

            for (ResponsavelNegocio responsavel : proposta.getResponsavelNegocio()) {
                Paragraph paragraphInfoResponsavelNegocio = new Paragraph(responsavel.getNome() + " - " + responsavel.getArea(), fontInformacoes);
                paragraphInfoResponsavelNegocio.setAlignment(Element.ALIGN_CENTER);
                paragraphInfoResponsavelNegocio.setSpacingBefore(10);

                document.add(paragraphInfoResponsavelNegocio);
            }

            Paragraph paragraphResponsavelNegocio = new Paragraph("Responsáveis pelo Negócio", fontSubtitulo);
            paragraphResponsavelNegocio.setAlignment(Element.ALIGN_CENTER);
            paragraphResponsavelNegocio.setSpacingBefore(10);

            document.add(paragraphResponsavelNegocio);

        }

        document.close();

        return document;
    }

    public Document exportAta(HttpServletResponse response, Ata ata) throws IOException, DocumentException {

        // Colors
        BaseColor azulWeg = new BaseColor(0, 87, 157);

        // Formatando a data para colocar no documento

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Criando o documento pdf

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

        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitulo.setSize(24);
        fontTitulo.setColor(azulWeg);
        fontTitulo.setStyle(Font.BOLD);

        Font fontTituloProposta = FontFactory.getFont(FontFactory.HELVETICA);
        fontTituloProposta.setSize(24);
        fontTituloProposta.setStyle(Font.BOLD);

        Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA);
        fontSubtitulo.setSize(15);
        fontSubtitulo.setStyle(Font.BOLD);

        Font fontInformacoes = FontFactory.getFont(FontFactory.HELVETICA);

        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA);
        fontHeader.setSize(12);

        Font fontHeaderProposta = FontFactory.getFont(FontFactory.HELVETICA);
        fontHeaderProposta.setSize(13);
        fontHeaderProposta.setStyle(Font.BOLD);

        Font fontHeaderPauta = FontFactory.getFont(FontFactory.HELVETICA);
        fontHeaderPauta.setSize(15);
        fontHeaderPauta.setColor(azulWeg);
        fontHeaderPauta.setStyle(Font.BOLD);

        Paragraph paragraphData = new Paragraph("Data de emissão: " + currentDateTime, fontHeader);
        paragraphData.setSpacingBefore(20);

        Paragraph paragraphAta = new Paragraph("Ata", fontTitulo);
        paragraphAta.setSpacingBefore(22);
        paragraphAta.setAlignment(Element.ALIGN_CENTER);

        Paragraph paragraphNumeroSequencial = new Paragraph("Número Sequencial: " + ata.getNumeroSequencial(), fontHeaderProposta);
        paragraphNumeroSequencial.setSpacingBefore(20);

        Paragraph paragraphReuniao = new Paragraph("Reunião: " + ata.getDataReuniao(), fontHeaderProposta);
        paragraphReuniao.setSpacingBefore(5);

        document.add(img);
        document.add(paragraphData);
        document.add(paragraphAta);
        document.add(paragraphNumeroSequencial);
        document.add(paragraphReuniao);

        int contadorProposta = 1;

        // Adicionando as propostas dentro da ata

        for (Proposta proposta : ata.getPropostas()) {

            Demanda demanda = demandaService.findById(proposta.getDemanda().getId()).get();

            Paragraph paragraph30 = new Paragraph("Proposta " + contadorProposta, fontTituloProposta);
            paragraph30.setSpacingBefore(20);
            paragraph30.setAlignment(Element.ALIGN_CENTER);

            contadorProposta++;

            Chunk chunkPPMData = new Chunk("PPM   " + String.valueOf(proposta.getCodigoPPM()) + "    ", fontHeaderPauta);
            Chunk chunkValorPPMData = new Chunk("DATA   " + String.valueOf(proposta.getData()) + "    ", fontHeaderPauta);
            Chunk chunkAtaPublicada = new Chunk();

            if (proposta.getPublicada()) {
                chunkAtaPublicada = new Chunk("PUBLICADA", fontHeaderPauta);
            } else {
                chunkAtaPublicada = new Chunk("NÃO PUBLICADA", fontHeaderPauta);
            }

            Paragraph paragraph25 = new Paragraph();
            paragraph25.add(chunkPPMData);
            paragraph25.add(chunkValorPPMData);
            paragraph25.add(chunkAtaPublicada);
            paragraph25.setSpacingBefore(40);

            Paragraph paragraphTitle = new Paragraph(proposta.getDemanda().getTitulo(), fontTitulo);
            paragraphTitle.setSpacingBefore(10);

            Chunk chunkSolicitante = new Chunk("Solicitante: ", fontSubtitulo);
            Chunk chunkValorSolicitante = new Chunk(String.valueOf(proposta.getSolicitante().getNome() + " - " + proposta.getSolicitante().getDepartamento().getNome()), fontInformacoes);
            Paragraph paragraph11 = new Paragraph();
            paragraph11.add(chunkSolicitante);
            paragraph11.add(chunkValorSolicitante);
            paragraph11.setSpacingBefore(20);

            Chunk chunkGerente = new Chunk("Gerente: ", fontSubtitulo);
            Chunk chunkValorGerente = new Chunk(String.valueOf(proposta.getGerente().getNome() + " - " + proposta.getGerente().getDepartamento().getNome()), fontInformacoes);
            Paragraph paragraph31 = new Paragraph();
            paragraph31.add(chunkGerente);
            paragraph31.add(chunkValorGerente);
            paragraph31.setSpacingBefore(15);

            Chunk chunkBuSolicitante = new Chunk("BU Solicitante: ", fontSubtitulo);
            Chunk chunkValorBuSolicitante = new Chunk(proposta.getBuSolicitante().getNomeBu(), fontInformacoes);
            Paragraph paragraph38 = new Paragraph();
            paragraph38.add(chunkBuSolicitante);
            paragraph38.add(chunkValorBuSolicitante);
            paragraph38.setSpacingBefore(15);

            Chunk chunkForum = new Chunk("Fórum: ", fontSubtitulo);
            Chunk chunkValorForum = new Chunk(proposta.getForum().getNomeForum(), fontInformacoes);
            Paragraph paragraph32 = new Paragraph();
            paragraph32.add(chunkForum);
            paragraph32.add(chunkValorForum);

            Chunk chunkTamanho = new Chunk("Tamanho ", fontSubtitulo);
            Chunk chunkValorTamanho = new Chunk(proposta.getTamanho(), fontInformacoes);
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

            Paragraph paragraph35 = new Paragraph("Proposta: ", fontSubtitulo);
            paragraph35.setSpacingBefore(10);

            Paragraph paragraph5 = new Paragraph(proposta.getDemanda().getProposta(), fontInformacoes);
            paragraph5.setSpacingBefore(3);
            paragraph5.setIndentationLeft(10);

            Paragraph paragraph36 = new Paragraph("Problema: ", fontSubtitulo);
            paragraph36.setSpacingBefore(15);

            Paragraph paragraph37 = new Paragraph(proposta.getDemanda().getProblema(), fontInformacoes);
            paragraph37.setSpacingBefore(3);
            paragraph37.setIndentationLeft(10);

            Paragraph paragraph26 = new Paragraph("Escopo da Proposta: ", fontSubtitulo);
            paragraph26.setSpacingBefore(15);

            Paragraph paragraph27 = new Paragraph(String.valueOf(proposta.getEscopo()), fontInformacoes);
            paragraph27.setSpacingBefore(3);
            paragraph27.setIndentationLeft(10);

            Paragraph paragraph28 = new Paragraph("Tabela de Custos: ", fontSubtitulo);
            paragraph28.setSpacingBefore(15);

            Chunk chunkFrequencia = new Chunk("Frequência: ", fontSubtitulo);
            Chunk chunkValorFrequencia = new Chunk(proposta.getFrequencia(), fontInformacoes);
            Paragraph paragraph34 = new Paragraph();
            paragraph34.add(chunkFrequencia);
            paragraph34.add(chunkValorFrequencia);
            paragraph34.setSpacingBefore(15);

            // Adicionando os paragrafos no documento

            document.add(paragraph30);
            document.add(paragraph25);
            document.add(paragraphTitle);
            document.add(paragraph11);
            document.add(paragraph38);
            document.add(paragraph31);
            document.add(table4);
            document.add(paragraph35);
            document.add(paragraph5);
            document.add(paragraph36);
            document.add(paragraph37);
            document.add(paragraph26);
            document.add(paragraph27);
            document.add(paragraph34);
            document.add(paragraph28);

            // Adicionando a tabela de CC

            for (TabelaCusto tableCusto : proposta.getTabelaCustos()) {
                PdfPTable table = new PdfPTable(4);

                table.setWidthPercentage(100);
                table.setWidths(new int[]{3, 3, 3, 3});
                table.setSpacingBefore(15);

                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(azulWeg);
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
                cell2.setBackgroundColor(azulWeg);
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
                cell3.setBackgroundColor(azulWeg);
                cell3.setPadding(5);
                cell3.setBorder(0);

                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setVerticalAlignment(Element.ALIGN_CENTER);

                cell3.setPhrase(new Phrase("CCs", font));
                table3.addCell(cell3);
                cell3.setPhrase(new Phrase("Porcentagem", font));
                table3.addCell(cell3);

                for (CC cc : tableCusto.getCcs()) {
                    table3.addCell(String.valueOf(cc.getCodigo()));
                    table3.addCell(String.valueOf(cc.getPorcentagem()));
                }

                document.add(table);
                document.add(table2);
                document.add(table3);
            }

            Paragraph paragraph39 = new Paragraph("Benefícios: ", fontSubtitulo);
            paragraph39.setAlignment(Paragraph.ANCHOR);
            paragraph39.setSpacingBefore(15);

            document.add(paragraph39);

            // Adicionando a tabela de benefícios

            for (Beneficio beneficio : proposta.getDemanda().getBeneficios()) {
                PdfPTable table = new PdfPTable(4);

                table.setWidthPercentage(100);
                table.setWidths(new int[]{3, 3, 3, 3});
                table.setSpacingBefore(15);

                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(azulWeg);
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

            Chunk chunkBuBeneficiadas = new Chunk("BUs Beneficiadas: ", fontSubtitulo);
            Paragraph paragraph40 = new Paragraph();
            paragraph40.add(chunkBuBeneficiadas);
            paragraph40.setSpacingBefore(15);

            for (Bu bu : demanda.getBusBeneficiadas()) {
                Chunk chunkValorBuBeneficiadas = new Chunk(bu.getNomeBu() + " ", fontInformacoes);
                paragraph36.add(chunkValorBuBeneficiadas);
            }

            Chunk chunkLink = new Chunk("Link Jira: ", fontSubtitulo);
            Chunk chunkValorLink = new Chunk(proposta.getLinkJira(), fontInformacoes);
            Paragraph paragraph21 = new Paragraph();
            paragraph21.add(chunkLink);
            paragraph21.add(chunkValorLink);
            paragraph21.setSpacingBefore(15);

            Chunk chunkExecucao = new Chunk("Período de Execução: ", fontSubtitulo);
            Chunk chunkValorExecucao = new Chunk(String.valueOf(proposta.getInicioExecucao()) + " à " + String.valueOf(proposta.getFimExecucao()), fontInformacoes);
            Paragraph paragraph17 = new Paragraph();
            paragraph17.add(chunkExecucao);
            paragraph17.add(chunkValorExecucao);
            paragraph17.setSpacingBefore(15);

            Chunk chunkPayback = new Chunk("Payback: ", fontSubtitulo);
            Chunk chunkValorPayback = new Chunk(String.valueOf(proposta.getPaybackValor()) + " " + String.valueOf(proposta.getPaybackTipo()), fontInformacoes);
            Paragraph paragraph19 = new Paragraph();
            paragraph19.add(chunkPayback);
            paragraph19.add(chunkValorPayback);
            paragraph19.setSpacingBefore(15);

            Paragraph paragraph9 = new Paragraph("Anexos: ", fontSubtitulo);
            paragraph9.setAlignment(Paragraph.ANCHOR);
            paragraph9.setSpacingBefore(15);

            document.add(paragraph40);
            document.add(paragraph21);
            document.add(paragraph17);
            document.add(paragraph19);
            document.add(paragraph9);

            // Adicionando o nome dos anexos

            for (Anexo anexo : proposta.getDemanda().getAnexo()) {
                Paragraph paragraph10 = new Paragraph(anexo.getNome() + "." + anexo.getTipo(), fontInformacoes);
                paragraph10.setIndentationLeft(40);
                paragraph10.setSpacingBefore(5);

                document.add(paragraph10);
            }

            // Adicionando os responsáveis pelo negócio

            for (ResponsavelNegocio responsavel : proposta.getResponsavelNegocio()) {
                Paragraph p = new Paragraph(responsavel.getNome() + " - " + responsavel.getArea(), fontInformacoes);
                p.setAlignment(Element.ALIGN_CENTER);
                p.setSpacingBefore(10);

                document.add(p);
            }

            Paragraph paragraph41 = new Paragraph("Responsáveis pelo Negócio", fontSubtitulo);
            paragraph41.setAlignment(Element.ALIGN_CENTER);
            paragraph41.setSpacingBefore(10);

            document.add(paragraph41);

        }

        document.close();

        return document;
    }
}