package net.weg.wegssm.model.service;

import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.util.DemandaUtil;
import net.weg.wegssm.util.PropostaUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExcelGeneratorService {

    public void exportDemandasBackLogToExcel(HttpServletResponse response, List<String> listaDemandas) throws IOException {
        DemandaUtil demandaUtil = new DemandaUtil();
        ArrayList<Demanda> listDemandas = new ArrayList<>();

        // Convertendo para demanda e adicioando na lista das demandas
        for (int i = 0; i < listaDemandas.size(); i++) {
            String demandaJSON = listaDemandas.get(i);
            Demanda demanda = demandaUtil.convertJsonToModelDirect(demandaJSON);
            listDemandas.add(demanda);
        }

        // Cria um workbook do Excel e uma planilha
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Demandas BackLog");

        // Pegando a data atual para colocar no título do documento
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Informações para o documento
        response.setHeader("Content-Disposition", "attachment; filename=demandas - " + currentDateTime + ".xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Crinado estilos e fontes para o arquivo
        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setBold(true);

        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        CellStyle alignLeft = workbook.createCellStyle();
        alignLeft.setIndention((short) 2);

        // Cria uma linha de cabeçalho para a planilha
        XSSFRow headerRow = sheet.createRow(0);

        // Criando os títulos das colunas
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Título");
        headerRow.createCell(2).setCellValue("Problema");
        headerRow.createCell(3).setCellValue("Proposta");
        headerRow.createCell(4).setCellValue("Benefícios");
        headerRow.createCell(5).setCellValue("Frequência de Uso");
        headerRow.createCell(6).setCellValue("Anexos");

        // Setando o estilo para as colunas
        int startColIndex = 0;
        int endColIndex = 6;
        int rowColIndex = 0;

        for (int colIndex = startColIndex; colIndex <= endColIndex; colIndex++) {
            Cell cell = sheet.getRow(rowColIndex).getCell(colIndex);
            cell.setCellStyle(style);
        }

        // Preenchendo informações das demandas
        int rowNum = 1;
        int contadorDemanda = 1;

        for (Demanda demanda : listDemandas) {
            // Criando uma nova linha
            XSSFRow row = sheet.createRow(rowNum++);

            // Adicionando informações nas respectivas colunas
            row.createCell(0).setCellValue(contadorDemanda);
            row.createCell(1).setCellValue(demanda.getTitulo());
            row.createCell(2).setCellValue(demanda.getProblema());
            row.createCell(3).setCellValue(demanda.getProposta());

            int rowIndex = rowNum;

            for (Beneficio beneficio : demanda.getBeneficios()) {
                Row roww = sheet.createRow(rowIndex);
                roww.createCell(4).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + " Valor Mensal: " + beneficio.getValor_mensal() + " Moeda: " + beneficio.getMoeda() + " Memória de Cálculo: " + beneficio.getMemoriaCalculo());
                rowIndex++;
            }

            row.createCell(5).setCellValue(demanda.getFrequencia());

            for (Anexo anexo : demanda.getAnexo()) {
                row.createCell(6).setCellValue(" - " + anexo.getNome() + ". " + anexo.getTipo());
                row.getCell(6).setCellStyle(alignLeft);
            }

            // Setando os estilos para as colunas
            row.getCell(0).setCellStyle(style);
            row.getCell(1).setCellStyle(alignLeft);
            row.getCell(2).setCellStyle(alignLeft);
            row.getCell(3).setCellStyle(alignLeft);
            row.getCell(5).setCellStyle(alignLeft);

            // Auto ajustando o tamanho das colunas de acordo com as informações
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);

            rowNum = rowIndex;
            contadorDemanda++;
        }

        // Escreve o workbook no response
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportDemandasAssessmentToExcel(HttpServletResponse response, List<String> listaDemandas) throws IOException {
        DemandaUtil demandaUtil = new DemandaUtil();
        ArrayList<Demanda> listDemandas = new ArrayList<>();

        // Convertendo para demanda e adicioando na lista das demandas
        for (int i = 0; i < listaDemandas.size(); i++) {
            String demandaJSON = listaDemandas.get(i);
            Demanda demanda = demandaUtil.convertJsonToModelDirect(demandaJSON);
            listDemandas.add(demanda);
        }

        // Cria um workbook do Excel e uma planilha
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Demandas Assessment");

        // Pegando a data atual para colocar no título do documento
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Informações para o documento
        response.setHeader("Content-Disposition", "attachment; filename=demandas - " + currentDateTime + ".xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Crinado estilos e fontes para o arquivo
        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setBold(true);

        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        CellStyle alignLeft = workbook.createCellStyle();
        alignLeft.setIndention((short) 2);

        // Cria uma linha de cabeçalho para a planilha
        XSSFRow headerRow = sheet.createRow(0);

        // Criando os títulos das colunas
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Título");
        headerRow.createCell(2).setCellValue("Problema");
        headerRow.createCell(3).setCellValue("Proposta");
        headerRow.createCell(4).setCellValue("Benefícios");
        headerRow.createCell(5).setCellValue("Frequência de Uso");
        headerRow.createCell(6).setCellValue("Tamanho");
        headerRow.createCell(7).setCellValue("Seção TI");
        headerRow.createCell(8).setCellValue("BU Solicitante");
        headerRow.createCell(9).setCellValue("BUs Beneficiadas");
        headerRow.createCell(10).setCellValue("Fórum");
        headerRow.createCell(11).setCellValue("Anexos");

        // Setando o estilo para as colunas
        int startColIndex = 0;
        int endColIndex = 11;
        int rowColIndex = 0;

        for (int colIndex = startColIndex; colIndex <= endColIndex; colIndex++) {
            Cell cell = sheet.getRow(rowColIndex).getCell(colIndex);
            cell.setCellStyle(style);
        }

        // Preenchendo informações das demandas
        int rowNum = 1;
        int contadorDemanda = 1;

        for (Demanda demanda : listDemandas) {
            // Criando uma nova linha
            XSSFRow row = sheet.createRow(rowNum++);

            // Adicionando informações nas respectivas colunas
            row.createCell(0).setCellValue(contadorDemanda);
            row.createCell(1).setCellValue(demanda.getTitulo());
            row.createCell(2).setCellValue(demanda.getProblema());
            row.createCell(3).setCellValue(demanda.getProposta());

            int rowIndex = rowNum;

            for (Beneficio beneficio : demanda.getBeneficios()) {
                Row roww = sheet.createRow(rowIndex);
                roww.createCell(4).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "  " + " Valor Mensal: " + beneficio.getValor_mensal() + "  " + " Moeda: " + beneficio.getMoeda() + "  " + " Memória de Cálculo: " + beneficio.getMemoriaCalculo());
                rowIndex++;
            }

            row.createCell(5).setCellValue(demanda.getFrequencia());
            row.createCell(6).setCellValue(demanda.getTamanho());
            row.createCell(7).setCellValue(demanda.getSecaoTI().getSiglaSecao() + " - " + demanda.getSecaoTI().getNomeSecao());
            row.createCell(8).setCellValue(demanda.getBuSolicitante().getSiglaBu() + " - " + demanda.getBuSolicitante().getNomeBu());

            for (Bu bu : demanda.getBusBeneficiadas()) {
                row.createCell(9).setCellValue(" - " + bu.getSiglaBu() + " - " + bu.getNomeBu() + "  ");
                row.getCell(9).setCellStyle(alignLeft);
            }

            row.createCell(10).setCellValue(demanda.getForum().getSiglaForum() + " - " + demanda.getForum().getNomeForum());

            for (Anexo anexo : demanda.getAnexo()) {
                row.createCell(11).setCellValue(" - " + anexo.getNome() + ". " + anexo.getTipo());
                row.getCell(11).setCellStyle(alignLeft);
            }

            // Setando os estilos para as colunas
            row.getCell(0).setCellStyle(style);
            row.getCell(1).setCellStyle(alignLeft);
            row.getCell(2).setCellStyle(alignLeft);
            row.getCell(3).setCellStyle(alignLeft);
            row.getCell(5).setCellStyle(alignLeft);
            row.getCell(6).setCellStyle(alignLeft);
            row.getCell(7).setCellStyle(alignLeft);
            row.getCell(8).setCellStyle(alignLeft);
            row.getCell(10).setCellStyle(alignLeft);

            // Auto ajustando o tamanho das colunas de acordo com as informações
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);

            rowNum = rowIndex;
            contadorDemanda++;
        }

        // Escreve o workbook no response
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportPropostasToExcel(HttpServletResponse response, List<String> listaPropostas) throws IOException {
        PropostaUtil propostaUtil = new PropostaUtil();
        ArrayList<Proposta> listPropostas = new ArrayList<>();

        // Convertendo para proposta e adicioando na lista das propostas
        for (int i = 0; i < listaPropostas.size(); i++) {
            String propostaJSON = listaPropostas.get(i);
            Proposta proposta = propostaUtil.convertJsonToModelDirect(propostaJSON);
            listPropostas.add(proposta);
        }

        // Cria um workbook do Excel e uma planilha
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Propostas");

        // Pegando a data atual para colocar no título do documento
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // Informações para o documento
        response.setHeader("Content-Disposition", "attachment; filename=propostas - " + currentDateTime + ".xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Crinado estilos e fontes para o arquivo
        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setBold(true);

        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        CellStyle alignLeft = workbook.createCellStyle();
        alignLeft.setIndention((short) 2);

        // Cria uma linha de cabeçalho para a planilha
        XSSFRow headerRow = sheet.createRow(0);

        // Criando os títulos das colunas
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("PPM");
        headerRow.createCell(2).setCellValue("Data");
        headerRow.createCell(3).setCellValue("Título");
        headerRow.createCell(4).setCellValue("Solicitante");
        headerRow.createCell(5).setCellValue("Gerente");
        headerRow.createCell(6).setCellValue("Problema");
        headerRow.createCell(7).setCellValue("Proposta");
        headerRow.createCell(8).setCellValue("Escopo");
        headerRow.createCell(9).setCellValue("Custos");
        headerRow.createCell(10).setCellValue("Benefícios");
        headerRow.createCell(11).setCellValue("Frequência de Uso");
        headerRow.createCell(12).setCellValue("Link Jira");
        headerRow.createCell(13).setCellValue("Período Execução");
        headerRow.createCell(14).setCellValue("Payback");
        headerRow.createCell(15).setCellValue("Tamanho");
        headerRow.createCell(16).setCellValue("Seção TI");
        headerRow.createCell(17).setCellValue("BU Solicitante");
        headerRow.createCell(18).setCellValue("BUs Beneficiadas");
        headerRow.createCell(19).setCellValue("Fórum");
        headerRow.createCell(20).setCellValue("Responsáveis Negócio");
        headerRow.createCell(21).setCellValue("Anexos");

        // Setando o estilo para as colunas
        int startColIndex = 0;
        int endColIndex = 21;
        int rowColIndex = 0;

        for (int colIndex = startColIndex; colIndex <= endColIndex; colIndex++) {
            Cell cell = sheet.getRow(rowColIndex).getCell(colIndex);
            cell.setCellStyle(style);
        }

        // Preenchendo informações das demandas
        int rowNum = 1;
        int contadorProposta = 1;
        for (Proposta proposta : listPropostas) {
            // Criando uma nova linha
            XSSFRow row = sheet.createRow(rowNum++);

            // Adicionando informações nas respectivas colunas
            row.createCell(0).setCellValue(contadorProposta);
            row.createCell(1).setCellValue(proposta.getCodigoPPM());
            row.createCell(2).setCellValue(proposta.getData());
            row.createCell(3).setCellValue(proposta.getTitulo());
            row.createCell(4).setCellValue(proposta.getSolicitante().getNome() + " - " + proposta.getSolicitante().getDepartamento().getNome());
            row.createCell(5).setCellValue(proposta.getGerente().getNome() + " - " + proposta.getGerente().getDepartamento().getNome());
            row.createCell(6).setCellValue(proposta.getProblema());
            row.createCell(7).setCellValue(proposta.getProposta());

            int rowIndex = rowNum;

            for (TabelaCusto tbCusto : proposta.getTabelaCustos()) {
                for (Custo custo : tbCusto.getCustos()) {
                    Row rowCusto = sheet.createRow(rowIndex);
                    rowCusto.createCell(9).setCellValue("Tipo Despesa: " + custo.getTipoDespesa() + "  " + "Perfil Despesa: " + custo.getPerfilDespesa() + "  " + "Período de Execução (meses): " + custo.getPeriodoExecucao() + "  " + "Horas: " + custo.getHoras() + "  " + "Valor Hora: " + custo.getValorHora() + "  " + "Total: 100");
                }
            }

            for (Beneficio beneficio : proposta.getBeneficios()) {
                Row roww = sheet.createRow(rowIndex);
                roww.createCell(10).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "  " + " Valor Mensal: " + beneficio.getValor_mensal() + "  " + " Moeda: " + beneficio.getMoeda() + "  " + " Memória de Cálculo: " + beneficio.getMemoriaCalculo());
                rowIndex++;
            }

            row.createCell(11).setCellValue(proposta.getFrequencia());
            row.createCell(12).setCellValue(proposta.getLinkJira());
            row.createCell(13).setCellValue(proposta.getInicioExecucao() + " à " + proposta.getFimExecucao());
            row.createCell(14).setCellValue(proposta.getPaybackValor() + "  " + proposta.getPaybackTipo());
            row.createCell(15).setCellValue(proposta.getTamanho());
            row.createCell(16).setCellValue(proposta.getSecaoTI().getSiglaSecao() + " - " + proposta.getSecaoTI().getNomeSecao());
            row.createCell(17).setCellValue(proposta.getBuSolicitante().getSiglaBu() + "  " + proposta.getBuSolicitante().getNomeBu());

            for (Bu bu : proposta.getBusBeneficiadas()) {
                row.createCell(18).setCellValue(" - " + bu.getSiglaBu() + " - " + bu.getNomeBu() + "  ");
                row.getCell(18).setCellStyle(alignLeft);
            }

            row.createCell(19).setCellValue(proposta.getForum().getSiglaForum() + " - " + proposta.getForum().getNomeForum());

            for (ResponsavelNegocio rn : proposta.getResponsavelNegocio()) {
                row.createCell(20).setCellValue(" - " + rn.getNome() + " - " + rn.getArea() + "  ");
                row.getCell(20).setCellStyle(alignLeft);
            }

            for (Anexo anexo : proposta.getAnexo()) {
                row.createCell(21).setCellValue(" - " + anexo.getNome() + ". " + anexo.getTipo());
                row.getCell(21).setCellStyle(alignLeft);
            }

            // Aplicando o estilo nas linhas
            int startRowIndex = 1;
            int endRowIndex = 21;
            int colIndex = 0;

            for (int rowwIndex = startRowIndex; rowwIndex <= endRowIndex; rowwIndex++) {
                Cell cell = sheet.getRow(rowwIndex).getCell(colIndex);
                cell.setCellStyle(alignLeft);
            }

            // Setando os estilos para as colunas
            row.getCell(0).setCellStyle(style);

            // Auto ajustando o tamanho das colunas de acordo com as informações
            Row headerRowAutoSize = sheet.getRow(0);
            int lastCellNum = headerRowAutoSize.getLastCellNum();

            for (int i = 0; i < lastCellNum; i++) {
                sheet.autoSizeColumn(i);
            }

            rowNum = rowIndex;
            contadorProposta++;
        }

        // Escreve o workbook no response
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportPautasToExcel(HttpServletResponse response, List<String> listaPautas) throws IOException {

    }

    public void exportAtasToExcel(HttpServletResponse response, List<String> listaAtas) throws IOException {

    }
}