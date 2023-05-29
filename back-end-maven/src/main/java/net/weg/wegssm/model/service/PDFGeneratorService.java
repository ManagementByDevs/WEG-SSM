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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;

/**
 * Service para gerar pdf
 */
@Service
public class PDFGeneratorService {

    /**
     * Service da demanda
     */
    private static DemandaService demandaService;

    /**
     * Construtor do service
     *
     * @param demandaService
     */
    public PDFGeneratorService(DemandaService demandaService) {
        this.demandaService = demandaService;
    }

    /**
     * Função para exportar uma demanda para pdf
     *
     * @param response
     * @param demanda
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public Document exportDemanda(HttpServletResponse response, Demanda demanda) throws IOException, DocumentException {

        // Colors
        BaseColor azulWeg = new BaseColor(0, 87, 157);

        // Formatação da data para o documento pdf

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Criando a página do pdf

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
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

        Paragraph paragraphData = new Paragraph("Data de emissão: " + currentDateTime, fontInfoHeader);
        paragraphData.setSpacingBefore(20);

        Paragraph paragraphProblema = new Paragraph("Problema: ", fontSubtitulo);
        paragraphProblema.setSpacingBefore(20);

        Paragraph paragraphProposta = new Paragraph("Proposta: ", fontSubtitulo);
        paragraphProposta.setSpacingBefore(15);

        Paragraph paragraphBeneficios = new Paragraph("Benefícios: ", fontSubtitulo);
        paragraphBeneficios.setSpacingBefore(15);

        document.add(img);
        document.add(paragraphData);
        document.add(paragraphTitulo);
        document.add(paragraphProblema);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(demanda.getProblema()));
        document.add(paragraphProposta);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(demanda.getProposta()));
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
            tableBeneficios.addCell(Jsoup.parse(new String(beneficio.getMemoriaCalculo(), StandardCharsets.UTF_8)).text());

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
            Paragraph paragraphInfoAnexos = new Paragraph(anexo.getNome() + " - " + anexo.getTipo(), fontInformacoes);
            paragraphInfoAnexos.setIndentationLeft(40);
            paragraphInfoAnexos.setSpacingBefore(5);

            document.add(paragraphInfoAnexos);
        }

        // Encerrando o documento

        document.close();

        return document;
    }

    /**
     * Função para exportar uma proposta para pdf
     *
     * @param response
     * @param proposta
     * @return
     * @throws IOException
     * @throws DocumentException
     */
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

        String stringSolicitante = proposta.getSolicitante().getNome();
        try {
            stringSolicitante += " - " + proposta.getSolicitante().getDepartamento().getNome();
        } catch (Exception e) {
        }

        Chunk chunkSolicitante = new Chunk("Solicitante: ", fontSubtitulo);
        Chunk chunkValorSolicitante = new Chunk(stringSolicitante, fontInformacoes);
        Paragraph paragraphSolicitante = new Paragraph();
        paragraphSolicitante.add(chunkSolicitante);
        paragraphSolicitante.add(chunkValorSolicitante);
        paragraphSolicitante.setSpacingBefore(20);

        String stringGerente = proposta.getGerente().getNome();
        try {
            stringGerente += " - " + proposta.getGerente().getDepartamento().getNome();
        } catch (Exception e) {
        }

        Chunk chunkGerente = new Chunk("Gerente: ", fontSubtitulo);
        Chunk chunkValorGerente = new Chunk(stringGerente, fontInformacoes);
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

        Paragraph paragraphProblema = new Paragraph("Problema: ", fontSubtitulo);
        paragraphProblema.setSpacingBefore(15);

        Paragraph paragraphEscopo = new Paragraph("Escopo da Proposta: ", fontSubtitulo);
        paragraphEscopo.setSpacingBefore(15);

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
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(proposta.getDemanda().getProposta()));
        document.add(paragraphProblema);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(proposta.getDemanda().getProblema()));

        document.add(paragraphEscopo);

        byte[] escopoByte = proposta.getEscopo();

        if (escopoByte != null) {
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(proposta.getEscopo()));
        }

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
//                tableCustos.addCell(custos.getTipoDespesa());
                tableCustos.addCell(custos.getPerfilDespesa());
                tableCustos.addCell(String.valueOf(custos.getPeriodoExecucao()));
                tableCustos.addCell(String.valueOf(custos.getHoras()));
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

            // FAZER O CALCULO

            cell2.setPhrase(new Phrase("Valor Hora", font));
            tableValorTotal.addCell(cell2);
            cell2.setPhrase(new Phrase("Total", font));
            tableValorTotal.addCell(cell2);

            for (Custo custos : tableCusto.getCustos()) {
                if (custos.getValorHora() != null) {
                    tableValorTotal.addCell((String.valueOf(custos.getValorHora())));
                    tableValorTotal.addCell(String.valueOf(custos.getValorHora() * custos.getHoras()));
                }
            }

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
            tableBeneficios.addCell(Jsoup.parse(new String(beneficio.getMemoriaCalculo(), StandardCharsets.UTF_8)).text());

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
            Paragraph paragraphInfoAnexos = new Paragraph(anexo.getNome() + " - " + anexo.getTipo(), fontInformacoes);
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

    /**
     * Função para exportar uma pauta para pdf
     *
     * @param response
     * @param pauta
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public Document exportPauta(HttpServletResponse response, Pauta pauta) throws IOException, DocumentException {

        // Colors
        BaseColor azulWeg = new BaseColor(0, 87, 157);

        // Formatação da data para quando baixar o documento

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Criação do documento pdf

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
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

        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA);
        fontHeader.setSize(13);

        Font fontInfoHeaderProposta = FontFactory.getFont(FontFactory.HELVETICA);
        fontInfoHeaderProposta.setSize(13);
        fontInfoHeaderProposta.setStyle(Font.BOLD);

        Font fontInfoHeaderPauta = FontFactory.getFont(FontFactory.HELVETICA);
        fontInfoHeaderPauta.setSize(15);
        fontInfoHeaderPauta.setColor(azulWeg);
        fontInfoHeaderPauta.setStyle(Font.BOLD);

        // Formatação da página pdf

        Paragraph paragraphData = new Paragraph("Data de emissão: " + currentDateTime, fontHeader);
        paragraphData.setSpacingBefore(20);

        Paragraph paragraphPauta = new Paragraph("Pauta", fontTitulo);
        paragraphPauta.setSpacingBefore(22);
        paragraphPauta.setAlignment(Element.ALIGN_CENTER);

        Chunk numeroSequencial = new Chunk("Número Sequencial: ", fontInfoHeaderProposta);
        Chunk numeroSequencialValor = new Chunk(pauta.getNumeroSequencial(), fontHeader);
        Paragraph paragraphNumeroSequencial = new Paragraph();
        paragraphNumeroSequencial.add(numeroSequencial);
        paragraphNumeroSequencial.add(numeroSequencialValor);
        paragraphNumeroSequencial.setSpacingBefore(20);

        Chunk comissao = new Chunk("Comissão: ", fontInfoHeaderProposta);
        Chunk comissaoValor = new Chunk(pauta.getComissao().getSiglaForum() + " - " + pauta.getComissao().getNomeForum(), fontHeader);
        Paragraph paragraphComissaoHeader = new Paragraph();
        paragraphComissaoHeader.add(comissao);
        paragraphComissaoHeader.add(comissaoValor);
        paragraphComissaoHeader.setSpacingBefore(5);

        Chunk reuniaoForum = new Chunk("Reunião Fórum: ", fontInfoHeaderProposta);
        Chunk reuniaoForumValor = new Chunk(pauta.getDataReuniao().toString(), fontHeader);
        Paragraph paragraphReuniaoForum = new Paragraph();
        paragraphReuniaoForum.add(reuniaoForum);
        paragraphReuniaoForum.add(reuniaoForumValor);
        paragraphReuniaoForum.setSpacingBefore(5);

        Chunk analista = new Chunk("Analista Responsável: ", fontInfoHeaderProposta);
        Chunk analistaValor = new Chunk(pauta.getAnalistaResponsavel().getNome(), fontHeader);
        Paragraph paragraphAnalista = new Paragraph();
        paragraphAnalista.add(analista);
        paragraphAnalista.add(analistaValor);
        paragraphAnalista.setSpacingBefore(5);

        document.add(img);
        document.add(paragraphData);
        document.add(paragraphPauta);
        document.add(paragraphNumeroSequencial);
        document.add(paragraphComissaoHeader);
        document.add(paragraphReuniaoForum);
        document.add(paragraphAnalista);

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

            Paragraph paragraphProblema = new Paragraph("Problema: ", fontSubtitulo);
            paragraphProblema.setSpacingBefore(15);

            Paragraph paragraphEscopo = new Paragraph("Escopo da Proposta: ", fontSubtitulo);
            paragraphEscopo.setSpacingBefore(15);

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
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(proposta.getDemanda().getProposta()));
            document.add(paragraphProblema);
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(proposta.getDemanda().getProblema()));

            document.add(paragraphEscopo);

            byte[] escopoByte = proposta.getEscopo();

            if (escopoByte != null) {
                XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(proposta.getEscopo()));
            }
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
//                    tableCustos.addCell(custos.getTipoDespesa());
                    tableCustos.addCell(custos.getPerfilDespesa());
                    tableCustos.addCell(String.valueOf(custos.getPeriodoExecucao()));
                    tableCustos.addCell(String.valueOf(custos.getHoras()));
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

                for (Custo custos : tableCusto.getCustos()) {
                    tableValorTotal.addCell((String.valueOf(custos.getValorHora())));
                    tableValorTotal.addCell(String.valueOf(custos.getValorHora() * custos.getHoras()));
                }

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
                tableBeneficios.addCell(Jsoup.parse(new String(beneficio.getMemoriaCalculo(), StandardCharsets.UTF_8)).text());

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
                Paragraph paragraphInfoAnexos = new Paragraph(anexo.getNome() + " - " + anexo.getTipo(), fontInformacoes);
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

            if (proposta.getParecerComissao() != null) {
                Paragraph paragraphParecer = new Paragraph("Pareceres: ", fontSubtitulo);
                paragraphParecer.setAlignment(Paragraph.ANCHOR);
                paragraphParecer.setSpacingBefore(15);

                Chunk chunkParecerComissao = new Chunk("Comisão: ", fontInformacoes);
                Chunk chunkValorParecerComissao = new Chunk(String.valueOf(proposta.getParecerComissao()), fontHeader);
                Paragraph paragraphParecerComissao = new Paragraph();
                paragraphParecerComissao.add(chunkParecerComissao);
                paragraphParecerComissao.add(chunkValorParecerComissao);
                paragraphParecerComissao.setSpacingBefore(10);

                document.add(paragraphParecer);
                document.add(paragraphParecerComissao);
                XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(proposta.getParecerInformacao().getBytes()));
            }

            document.newPage();

        }

        document.close();

        return document;
    }

    /**
     * Função para exportar uma ata para pdf
     *
     * @param response
     * @param ata
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public Document exportAta(HttpServletResponse response, Ata ata) throws IOException, DocumentException {

        // Colors
        BaseColor azulWeg = new BaseColor(0, 87, 157);

        // Formatando a data para colocar no documento

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Criando o documento pdf

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
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
        fontHeader.setSize(13);

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

        Chunk numeroSequencial = new Chunk("Número Sequencial: ", fontHeaderProposta);
        Chunk numeroSequencialValor = new Chunk(ata.getNumeroSequencial(), fontHeader);
        Paragraph paragraphNumeroSequencial = new Paragraph();
        paragraphNumeroSequencial.add(numeroSequencial);
        paragraphNumeroSequencial.add(numeroSequencialValor);
        paragraphNumeroSequencial.setSpacingBefore(20);

        Chunk analistaResponsavel = new Chunk("Analista Responsável: ", fontHeaderProposta);
        Chunk analistaResponsavelValor = new Chunk(ata.getAnalistaResponsavel().getNome(), fontHeader);
        Paragraph paragraphAnalistaResponsavelHeader = new Paragraph();
        paragraphAnalistaResponsavelHeader.add(analistaResponsavel);
        paragraphAnalistaResponsavelHeader.add(analistaResponsavelValor);
        paragraphAnalistaResponsavelHeader.setSpacingBefore(5);

        Chunk comissao = new Chunk("Comissão: ", fontHeaderProposta);
        Chunk comissaoValor = new Chunk(ata.getComissao().getSiglaForum() + " - " + ata.getComissao().getNomeForum(), fontHeader);
        Paragraph paragraphComissaoHeader = new Paragraph();
        paragraphComissaoHeader.add(comissao);
        paragraphComissaoHeader.add(comissaoValor);
        paragraphComissaoHeader.setSpacingBefore(5);

        document.add(img);
        document.add(paragraphData);
        document.add(paragraphAta);
        document.add(paragraphNumeroSequencial);
        document.add(paragraphAnalistaResponsavelHeader);
        document.add(paragraphComissaoHeader);

        int contadorProposta = 1;

        // Adicionando as propostas dentro da ata

        for (Proposta proposta : ata.getPropostas()) {

            Demanda demanda = demandaService.findById(proposta.getDemanda().getId()).get();

            Paragraph paragraphContadorProposta = new Paragraph("Proposta " + contadorProposta, fontTituloProposta);
            paragraphContadorProposta.setSpacingBefore(20);
            paragraphContadorProposta.setAlignment(Element.ALIGN_CENTER);

            contadorProposta++;

            Chunk chunkPPMData = new Chunk("PPM   " + String.valueOf(proposta.getCodigoPPM()) + "    ", fontHeaderPauta);
            Chunk chunkValorPPMData = new Chunk("DATA   " + String.valueOf(proposta.getData()) + "    ", fontHeaderPauta);
            Chunk chunkAtaPublicada = new Chunk();

            if (proposta.getPublicada()) {
                chunkAtaPublicada = new Chunk("PUBLICADA", fontHeaderPauta);
            } else {
                chunkAtaPublicada = new Chunk("NÃO PUBLICADA", fontHeaderPauta);
            }

            Paragraph paragraph = new Paragraph();
            paragraph.add(chunkPPMData);
            paragraph.add(chunkValorPPMData);
            paragraph.add(chunkAtaPublicada);
            paragraph.setSpacingBefore(40);

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

            Paragraph paragraphProblema = new Paragraph("Problema: ", fontSubtitulo);
            paragraphProblema.setSpacingBefore(15);

            Paragraph paragraphEscopo = new Paragraph("Escopo da Proposta: ", fontSubtitulo);
            paragraphEscopo.setSpacingBefore(15);

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
            document.add(paragraph);
            document.add(paragraphTitulo);
            document.add(paragraphSolicitante);
            document.add(paragraphBuSolicitante);
            document.add(paragraphGerente);
            document.add(tableTamanhoForum);
            document.add(paragraphProposta);
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(proposta.getDemanda().getProposta()));
            document.add(paragraphProblema);
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(proposta.getDemanda().getProblema()));

            document.add(paragraphEscopo);

            byte[] escopoByte = proposta.getEscopo();

            if (escopoByte != null) {
                XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(proposta.getEscopo()));
            }

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
//                    tableCustos.addCell(custos.getTipoDespesa());
                    tableCustos.addCell(custos.getPerfilDespesa());
                    tableCustos.addCell(String.valueOf(custos.getPeriodoExecucao()));
                    tableCustos.addCell(String.valueOf(custos.getHoras()));
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

                for (Custo custos : tableCusto.getCustos()) {
                    tableValorTotal.addCell((String.valueOf(custos.getValorHora())));
                    tableValorTotal.addCell(String.valueOf(custos.getValorHora() * custos.getHoras()));
                }

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
                tableBeneficios.addCell(Jsoup.parse(new String(beneficio.getMemoriaCalculo(), StandardCharsets.UTF_8)).text());

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
                Paragraph paragraphInfoAnexos = new Paragraph(anexo.getNome() + " - " + anexo.getTipo(), fontInformacoes);
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

            Paragraph paragraphParecer = new Paragraph("Pareceres: ", fontSubtitulo);
            paragraphParecer.setAlignment(Paragraph.ANCHOR);
            paragraphParecer.setSpacingBefore(15);

            Chunk chunkParecerComissao = new Chunk("Comisão: ", fontInformacoes);
            Chunk chunkValorParecerComissao = new Chunk(String.valueOf(proposta.getParecerComissao()), fontHeader);
            Paragraph paragraphParecerComissao = new Paragraph();
            paragraphParecerComissao.add(chunkParecerComissao);
            paragraphParecerComissao.add(chunkValorParecerComissao);
            paragraphParecerComissao.setSpacingBefore(10);

            document.add(paragraphParecer);
            document.add(paragraphParecerComissao);
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(proposta.getParecerInformacao().getBytes()));

            if (proposta.getParecerDG() != null) {
                Chunk chunkParecerDG = new Chunk("Direção Geral: ", fontInformacoes);
                Chunk chunkValorParecerDG = new Chunk(String.valueOf(proposta.getParecerDG()), fontHeader);
                Paragraph paragraphParecerDG = new Paragraph();
                paragraphParecerDG.add(chunkParecerDG);
                paragraphParecerDG.add(chunkValorParecerDG);
                paragraphParecerDG.setSpacingBefore(10);

                document.add(paragraphParecerDG);
                XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(proposta.getParecerInformacaoDG().getBytes()));
            }

            document.newPage();

        }

        document.close();

        return document;
    }

}