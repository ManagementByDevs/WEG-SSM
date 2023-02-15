package net.weg.wegssm.model.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import net.weg.wegssm.model.entities.*;
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

    public Document exportDemanda(HttpServletResponse response, Demanda demanda) throws IOException {

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

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
        paragraph.setSpacingBefore(15);

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

        // Adicionando a logo da weg na página ( arrumar o diretório da imagem )

        Image img = Image.getInstance("C:\\Users\\felipe_mielke-vieira\\Documents\\GitHub\\WEG-SSM\\back-end-maven\\src\\main\\java\\net\\weg\\wegssm\\images\\logo-pequeno.png");

        int indentation = 0;
        float scale = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - indentation) / img.getWidth()) * 20;

        img.scalePercent(scale);
        img.setAbsolutePosition(460, 740);

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

    public void exportProposta(HttpServletResponse response) throws IOException {

        Proposta proposta = new Proposta();

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Criando a página do pdf

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Criando a formatação da página pdf

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA);
        fontTitle.setSize(24);
        fontTitle.setColor(Color.decode("#00579D"));
        fontTitle.setStyle(Font.BOLD);

        Paragraph paragraph = new Paragraph(proposta.getDemanda().getTitulo(), fontTitle);
        paragraph.setSpacingBefore(20);

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

        Paragraph paragraph23 = new Paragraph("Data de emissão: " + currentDateTime, fontParagraph4);
        paragraph23.setSpacingBefore(20);

        Paragraph paragraph11 = new Paragraph("Código PPM: ", fontParagraph2);
        paragraph11.setSpacingBefore(15);

        Paragraph paragraph12 = new Paragraph(String.valueOf(proposta.getCodigoPPM()), fontParagraph3);
        paragraph12.setIndentationLeft(40);
        paragraph12.setSpacingBefore(5);

        Paragraph paragraph13 = new Paragraph("Responsável Negócio: ", fontParagraph2);
        paragraph13.setSpacingBefore(15);

        Paragraph paragraph2 = new Paragraph("Problema: ", fontParagraph2);
        paragraph2.setSpacingBefore(15);

        Paragraph paragraph3 = new Paragraph(proposta.getDemanda().getProblema(), fontParagraph3);
        paragraph3.setIndentationLeft(40);
        paragraph3.setSpacingBefore(5);

        Paragraph paragraph4 = new Paragraph("Proposta: ", fontParagraph2);
        paragraph4.setSpacingBefore(15);

        Paragraph paragraph5 = new Paragraph(proposta.getDemanda().getProposta(), fontParagraph3);
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
        document.add(paragraph23);
        document.add(paragraph);
        document.add(paragraph11);
        document.add(paragraph12);
        document.add(paragraph13);

        for (ResponsavelNegocio responsavel : proposta.getResponsavelNegocio()) {
            Paragraph p = new Paragraph(responsavel.getNome() + " - " + responsavel.getArea(), fontParagraph3);
            p.setIndentationLeft(40);
            p.setSpacingBefore(5);

            document.add(p);
        }

        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(paragraph6);

        // Criando tabela para os centros de custo

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

        Paragraph paragraph7 = new Paragraph("Frequência de Uso: ", fontParagraph2);
        paragraph7.setAlignment(Paragraph.ANCHOR);
        paragraph7.setSpacingBefore(15);

        Paragraph paragraph8 = new Paragraph(proposta.getDemanda().getFrequencia(), fontParagraph3);
        paragraph8.setAlignment(Paragraph.ANCHOR);
        paragraph8.setIndentationLeft(40);
        paragraph8.setSpacingBefore(5);

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

        Paragraph paragraph9 = new Paragraph("Anexos: ", fontParagraph2);
        paragraph9.setAlignment(Paragraph.ANCHOR);
        paragraph9.setSpacingBefore(15);

        // Adicionando tudo na página pdf

        document.add(paragraph7);
        document.add(paragraph8);
        document.add(paragraph15);
        document.add(paragraph16);
        document.add(paragraph17);
        document.add(paragraph18);
        document.add(paragraph19);
        document.add(paragraph20);
        document.add(paragraph21);
        document.add(paragraph22);
        document.add(paragraph9);

//        for(Anexo anexo : proposta.getDemanda().getAnexo()){
//            Paragraph paragraph10 = new Paragraph(anexo.getNome() + "." + anexo.getTipo(), fontParagraph3);
//            paragraph10.setIndentationLeft(40);
//            paragraph10.setSpacingBefore(5);
//
//            document.add(paragraph10);
//        }

        document.close();
    }

    public void exportPauta(HttpServletResponse response) throws IOException {

        Pauta pauta = new Pauta();

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

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

        Paragraph paragraph2 = new Paragraph("Pauta", fontTitle);
        paragraph2.setSpacingBefore(22);
        paragraph2.setAlignment(Element.ALIGN_CENTER);

        Paragraph paragraph3 = new Paragraph("Número Sequencial: " + pauta.getNumeroSequencial(), fontParagraph5);
        paragraph3.setSpacingBefore(20);

        Paragraph paragraph4 = new Paragraph("Ano: 2023", fontParagraph5);
        paragraph3.setSpacingBefore(5);

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

        List<Proposta> listaPropostas = new ArrayList<>();

        Demanda demanda = new Demanda();
        Beneficio beneficio1 = new Beneficio();
        Beneficio beneficio2 = new Beneficio();
        Beneficio beneficio3 = new Beneficio();
        List<Beneficio> listaBeneficios = new ArrayList<>();

        TipoBeneficio tipoBeneficio1 = TipoBeneficio.QUALITATIVO;
        TipoBeneficio tipoBeneficio2 = TipoBeneficio.POTENCIAL;
        TipoBeneficio tipoBeneficio3 = TipoBeneficio.REAL;

        ResponsavelNegocio responsavelNegocio = new ResponsavelNegocio();
        responsavelNegocio.setArea("TI");
        responsavelNegocio.setNome("Matheus");

        List<ResponsavelNegocio> listaResponsavelNgc = new ArrayList<>();

        listaResponsavelNgc.add(responsavelNegocio);

        Proposta proposta1 = new Proposta();
        Proposta proposta2 = new Proposta();

        proposta2.setDemanda(demanda);
        proposta1.setDemanda(demanda);

        proposta1.setResponsavelNegocio(listaResponsavelNgc);
        proposta1.setLinkJira("https://link.com.br");

        proposta2.setResponsavelNegocio(listaResponsavelNgc);
        proposta2.setLinkJira("https://link.com.br");

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

        listaPropostas.add(proposta1);
        listaPropostas.add(proposta2);

        ata.setPropostas(listaPropostas);

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

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