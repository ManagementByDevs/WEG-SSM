package net.weg.wegssm.model.service;

import lombok.AllArgsConstructor;
import net.weg.wegssm.model.entities.*;
import net.weg.wegssm.util.DemandaUtil;
import net.weg.wegssm.util.PropostaUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe service para o gerador de excel
 */
@Service
@AllArgsConstructor
public class ExcelGeneratorService {

    private DemandaService demandaService;
    private PropostaService propostaService;
    private PautaService pautaService;
    private AtaService ataService;

    /**
     * Função para criar uma linha de cabeçalho com o estilo
     *
     * @param workbook
     * @param color
     * @return
     */
    private static XSSFCellStyle createRowStyle(XSSFWorkbook workbook, XSSFColor color) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(color);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    /**
     * Função para exportar demandas back_log para excel
     *
     * @param response
     * @param listaDemandas
     * @throws IOException
     */
    public void exportDemandasBackLogToExcel(HttpServletResponse response, List<Long> listaDemandas) throws IOException {
        ArrayList<Optional<Demanda>> listDemandas = new ArrayList<>();

        // Convertendo para demanda e adicionando na lista das demandas
        for (int i = 0; i < listaDemandas.size(); i++) {
            Long idDemanda = listaDemandas.get(i);
            Optional<Demanda> demanda = demandaService.findById(idDemanda);
            listDemandas.add(demanda);
        }

        // Cria um workbook do Excel e uma planilha
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Demandas BackLog");

        // Informações para o documento
        response.setHeader("Content-Disposition", "attachment; filename=demandas.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Criando estilos e fontes para o arquivo
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

        for (Optional<Demanda> demandaOp : listDemandas) {
            XSSFRow row = sheet.createRow(rowNum++);

            int rowIndex = rowNum;

            if (demandaOp.isPresent()) {
                Demanda demanda = demandaOp.get();

                // Adicionando informações nas respectivas colunas
                row.createCell(0).setCellValue(contadorDemanda);
                row.createCell(1).setCellValue(demanda.getTitulo());
                row.createCell(2).setCellValue(Jsoup.parse(new String(demanda.getProblema(), StandardCharsets.UTF_8)).text());
                row.createCell(3).setCellValue(Jsoup.parse(new String(demanda.getProposta(), StandardCharsets.UTF_8)).text());

                int contadorBeneficios = 0;

                for (Beneficio beneficio : demanda.getBeneficios()) {
                    if (contadorBeneficios == 0) {
                        row.createCell(4).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "    Valor Mensal: " + beneficio.getValor_mensal() + "    Moeda: " + beneficio.getMoeda() + "    Memória de Cálculo: " + beneficio.getMemoriaCalculo());
                    } else {
                        Row roww = sheet.createRow(rowIndex);
                        roww.createCell(4).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "    Valor Mensal: " + beneficio.getValor_mensal() + "    Moeda: " + beneficio.getMoeda() + "    Memória de Cálculo: " + beneficio.getMemoriaCalculo());
                        rowIndex++;
                    }
                    contadorBeneficios++;
                }

                row.createCell(5).setCellValue(demanda.getFrequencia());

                for (Anexo anexo : demanda.getAnexo()) {
                    row.createCell(6).setCellValue(" - " + anexo.getNome() + ". " + anexo.getTipo());
                    row.getCell(6).setCellStyle(alignLeft);
                }

                // Setando os estilos para as colunas

                int[] colunasStyle = {1, 2, 3, 4, 5, 6};

                for (int colunaStyle : colunasStyle) {
                    if (row.getCell(colunaStyle) != null) {
                        row.getCell(colunaStyle).setCellStyle(alignLeft);
                    }
                }

                row.getCell(0).setCellStyle(style);

                // Auto ajustando o tamanho das colunas de acordo com as informações

                int[] colunasAutoSize = {1, 2, 3, 4, 5, 6};

                for (int coluna : colunasAutoSize) {
                    sheet.autoSizeColumn(coluna);
                }

                rowNum = rowIndex;
                contadorDemanda++;
            }
        }

        // Escreve o workbook no response
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    /**
     * Função para exportar as demandas assessment para excel
     *
     * @param response
     * @param listaDemandas
     * @throws IOException
     */
    public void exportDemandasAssessmentToExcel(HttpServletResponse response, List<Long> listaDemandas) throws IOException {
        ArrayList<Optional<Demanda>> listDemandas = new ArrayList<>();

        // Convertendo para demanda e adicionando na lista das demandas
        for (int i = 0; i < listaDemandas.size(); i++) {
            Long idDemanda = listaDemandas.get(i);
            Optional<Demanda> demanda = demandaService.findById(idDemanda);
            listDemandas.add(demanda);
        }

        // Cria um workbook do Excel e uma planilha
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Demandas Assessment");

        // Informações para o documento
        response.setHeader("Content-Disposition", "attachment; filename=demandas.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Criando estilos e fontes para o arquivo
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

        for (Optional<Demanda> demandaOp : listDemandas) {
            XSSFRow row = sheet.createRow(rowNum++);

            int rowIndex = rowNum;

            if (demandaOp.isPresent()) {
                Demanda demanda = demandaOp.get();

                // Adicionando informações nas respectivas colunas
                row.createCell(0).setCellValue(contadorDemanda);
                row.createCell(1).setCellValue(demanda.getTitulo());
                row.createCell(2).setCellValue(Jsoup.parse(new String(demanda.getProblema(), StandardCharsets.UTF_8)).text());
                row.createCell(3).setCellValue(Jsoup.parse(new String(demanda.getProposta(), StandardCharsets.UTF_8)).text());

                int contadorBeneficios = 0;

                for (Beneficio beneficio : demanda.getBeneficios()) {
                    if (contadorBeneficios == 0) {
                        row.createCell(4).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "    Valor Mensal: " + beneficio.getValor_mensal() + "    Moeda: " + beneficio.getMoeda() + "    Memória de Cálculo: " + beneficio.getMemoriaCalculo());
                    } else {
                        Row roww = sheet.createRow(rowIndex);
                        roww.createCell(4).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "    Valor Mensal: " + beneficio.getValor_mensal() + "    Moeda: " + beneficio.getMoeda() + "    Memória de Cálculo: " + beneficio.getMemoriaCalculo());
                        rowIndex++;
                    }
                    contadorBeneficios++;
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

                int[] colunasStyle = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

                for (int colunaStyle : colunasStyle) {
                    if (row.getCell(colunaStyle) != null) {
                        row.getCell(colunaStyle).setCellStyle(alignLeft);
                    }
                }

                // Auto ajustando o tamanho das colunas de acordo com as informações
                int[] colunasAutoSize = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

                for (int colunaAuto : colunasAutoSize) {
                    sheet.autoSizeColumn(colunaAuto);
                }

                rowNum = rowIndex;
                contadorDemanda++;

            }

        }

        // Escreve o workbook no response
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    /**
     * Função para exportar as propostas para excel
     *
     * @param response
     * @param listaPropostas
     * @throws IOException
     */
    public void exportPropostasToExcel(HttpServletResponse response, List<Long> listaPropostas) throws IOException {
        ArrayList<Optional<Proposta>> listPropostas = new ArrayList<>();

        // Convertendo para proposta e adicionando na lista das propostas
        for (int i = 0; i < listaPropostas.size(); i++) {
            Long idProposta = listaPropostas.get(i);
            Optional<Proposta> proposta = propostaService.findById(idProposta);
            listPropostas.add(proposta);
        }

        // Cria um workbook do Excel e uma planilha
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Propostas");

        // Informações para o documento
        response.setHeader("Content-Disposition", "attachment; filename=propostas.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Criando estilos e fontes para o arquivo
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

        for (Optional<Proposta> propostaOp : listPropostas) {
            XSSFRow row = sheet.createRow(rowNum++);

            int rowIndex = rowNum;

            if (propostaOp.isPresent()) {
                Proposta proposta = propostaOp.get();

                // Adicionando informações nas respectivas colunas
                row.createCell(0).setCellValue(contadorProposta);
                row.createCell(1).setCellValue(proposta.getCodigoPPM());
                row.createCell(2).setCellValue(proposta.getData());
                row.createCell(3).setCellValue(proposta.getTitulo());
                row.createCell(4).setCellValue(proposta.getSolicitante().getNome() + " - " + proposta.getSolicitante().getDepartamento().getNome());
                row.createCell(5).setCellValue(proposta.getGerente().getNome() + " - " + proposta.getGerente().getDepartamento().getNome());
                row.createCell(6).setCellValue(Jsoup.parse(new String(proposta.getProblema(), StandardCharsets.UTF_8)).text());
                row.createCell(7).setCellValue(Jsoup.parse(new String(proposta.getProposta(), StandardCharsets.UTF_8)).text());

                int contadorCusto = 0;

                for (TabelaCusto tbCusto : proposta.getTabelaCustos()) {
                    for (Custo custo : tbCusto.getCustos()) {
                        if (contadorCusto == 0) {
                            row.createCell(9).setCellValue("Tipo Despesa: " + /** custo.getTipoDespesa() */  "  " + "Perfil Despesa: " + custo.getPerfilDespesa() + "  " + "Período de Execução (meses): " + custo.getPeriodoExecucao() + "  " + "Horas: " + custo.getHoras() + "  " + "Valor Hora: " + custo.getValorHora() + "  " + "Total: 100");
                        } else {
                            Row rowCusto = sheet.createRow(rowIndex);
                            rowCusto.createCell(9).setCellValue("Tipo Despesa: " + /** custo.getTipoDespesa() */ "  " + "Perfil Despesa: " + custo.getPerfilDespesa() + "  " + "Período de Execução (meses): " + custo.getPeriodoExecucao() + "  " + "Horas: " + custo.getHoras() + "  " + "Valor Hora: " + custo.getValorHora() + "  " + "Total: 100");
                            rowIndex++;
                        }

                        contadorCusto++;
                    }
                }

                int contadorBeneficios = 0;

                for (Beneficio beneficio : proposta.getBeneficios()) {
                    if (contadorBeneficios == 0) {
                        row.createCell(10).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "  " + " Valor Mensal: " + beneficio.getValor_mensal() + "  " + " Moeda: " + beneficio.getMoeda() + "  " + " Memória de Cálculo: " + beneficio.getMemoriaCalculo());
                    } else {
                        Row roww = sheet.createRow(rowIndex);
                        roww.createCell(10).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "  " + " Valor Mensal: " + beneficio.getValor_mensal() + "  " + " Moeda: " + beneficio.getMoeda() + "  " + " Memória de Cálculo: " + beneficio.getMemoriaCalculo());
                        rowIndex++;
                    }
                    contadorBeneficios++;
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

                // Setando os estilos para as colunas
                int[] colunasStyle = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};

                for (int colunaStyle : colunasStyle) {
                    if (row.getCell(colunaStyle) != null) {
                        row.getCell(colunaStyle).setCellStyle(alignLeft);
                    }
                }

                row.getCell(0).setCellStyle(style);

                // Auto ajustando o tamanho das colunas de acordo com as informações
                int[] colunasAutoSize = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};

                for (int colunaAuto : colunasAutoSize) {
                    sheet.autoSizeColumn(colunaAuto);
                }

                rowNum = rowIndex;
                contadorProposta++;

            }
        }

        // Escreve o workbook no response
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    /**
     * Função para exportar as pautas para excel
     *
     * @param response
     * @param listaPautas
     * @throws IOException
     */
    public void exportPautasToExcel(HttpServletResponse response, List<Long> listaPautas) throws IOException {
        ArrayList<Optional<Pauta>> listPautas = new ArrayList<>();

        // Convertendo para pauta e adicionando na lista das pautas
        for (int i = 0; i < listaPautas.size(); i++) {
            Long idPauta = listaPautas.get(i);
            Optional<Pauta> pauta = pautaService.findById(idPauta);
            listPautas.add(pauta);
        }

        // Cria um workbook do Excel e uma planilha
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Pautas");

        // Informações para o documento
        response.setHeader("Content-Disposition", "attachment; filename=pautas.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Criando estilos e fontes para o arquivo
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
        headerRow.createCell(1).setCellValue("Pauta");
        headerRow.createCell(2).setCellValue("Propostas");
        headerRow.createCell(3).setCellValue("PPM");
        headerRow.createCell(4).setCellValue("Data");
        headerRow.createCell(5).setCellValue("Solicitante");
        headerRow.createCell(6).setCellValue("Gerente");
        headerRow.createCell(7).setCellValue("Problema");
        headerRow.createCell(8).setCellValue("Proposta");
        headerRow.createCell(9).setCellValue("Escopo");
        headerRow.createCell(10).setCellValue("Custos");
        headerRow.createCell(11).setCellValue("Benefícios");
        headerRow.createCell(12).setCellValue("Frequência de Uso");
        headerRow.createCell(13).setCellValue("Link Jira");
        headerRow.createCell(14).setCellValue("Período Execução");
        headerRow.createCell(15).setCellValue("Payback");
        headerRow.createCell(16).setCellValue("Tamanho");
        headerRow.createCell(17).setCellValue("Seção TI");
        headerRow.createCell(18).setCellValue("BU Solicitante");
        headerRow.createCell(19).setCellValue("BUs Beneficiadas");
        headerRow.createCell(20).setCellValue("Fórum");
        headerRow.createCell(21).setCellValue("Responsáveis Negócio");
        headerRow.createCell(22).setCellValue("Anexos");

        // Setando o estilo para as colunas
        int startColIndex = 0;
        int endColIndex = 22;
        int rowColIndex = 0;

        for (int colIndex = startColIndex; colIndex <= endColIndex; colIndex++) {
            Cell cell = sheet.getRow(rowColIndex).getCell(colIndex);
            cell.setCellStyle(style);
        }

        int rowNum = 1;
        int contadorPauta = 1;
        for (Optional<Pauta> pautaOp : listPautas) {

            XSSFRow row = sheet.createRow(rowNum++);

            if (pautaOp.isPresent()) {
                Pauta pauta = pautaOp.get();

                row.createCell(0).setCellValue(contadorPauta);
                row.createCell(1).setCellValue("Número Sequencial: " + pauta.getNumeroSequencial() + "    Comissão: " + pauta.getComissao().getSiglaForum() + " - " + pauta.getComissao().getNomeForum() + "    Reunião do Fórum: " + pauta.getDataReuniao() + "    Analista Responsável: " + pauta.getAnalistaResponsavel().getNome());

                int rowIndexProposta = rowNum;

                int contadorProposta = 0;
                for (Proposta proposta : pauta.getPropostas()) {

                    if (contadorProposta == 0) {
                        row.createCell(2).setCellValue(proposta.getTitulo());
                        row.createCell(3).setCellValue(proposta.getCodigoPPM());

                        row.createCell(4).setCellValue(proposta.getData());
                        row.createCell(5).setCellValue(proposta.getSolicitante().getNome() + " - " + proposta.getSolicitante().getDepartamento().getNome());
                        row.createCell(6).setCellValue(proposta.getGerente().getNome() + " - " + proposta.getGerente().getDepartamento().getNome());
                        row.createCell(7).setCellValue(Jsoup.parse(new String(proposta.getProblema(), StandardCharsets.UTF_8)).text());
                        row.createCell(8).setCellValue(Jsoup.parse(new String(proposta.getProposta(), StandardCharsets.UTF_8)).text());

                        row.createCell(12).setCellValue(proposta.getFrequencia());
                        row.createCell(13).setCellValue(proposta.getLinkJira());
                        row.createCell(14).setCellValue(proposta.getInicioExecucao() + " à " + proposta.getFimExecucao());
                        row.createCell(15).setCellValue(proposta.getPaybackValor() + "  " + proposta.getPaybackTipo());
                        row.createCell(16).setCellValue(proposta.getTamanho());
                        row.createCell(17).setCellValue(proposta.getSecaoTI().getSiglaSecao() + " - " + proposta.getSecaoTI().getNomeSecao());
                        row.createCell(18).setCellValue(proposta.getBuSolicitante().getSiglaBu() + "  " + proposta.getBuSolicitante().getNomeBu());

                        for (Bu bu : proposta.getBusBeneficiadas()) {
                            row.createCell(19).setCellValue(" - " + bu.getSiglaBu() + " - " + bu.getNomeBu() + "  ");
                            row.getCell(19).setCellStyle(alignLeft);
                        }

                        row.createCell(20).setCellValue(proposta.getForum().getSiglaForum() + " - " + proposta.getForum().getNomeForum());

                        for (ResponsavelNegocio rn : proposta.getResponsavelNegocio()) {
                            row.createCell(21).setCellValue(" - " + rn.getNome() + " - " + rn.getArea() + "  ");
                            row.getCell(21).setCellStyle(alignLeft);
                        }

                        for (Anexo anexo : proposta.getAnexo()) {
                            row.createCell(22).setCellValue(" - " + anexo.getNome() + ". " + anexo.getTipo());
                            row.getCell(22).setCellStyle(alignLeft);
                        }

                        int contadorCusto = 0;

                        for (TabelaCusto tbCusto : proposta.getTabelaCustos()) {
                            for (Custo custo : tbCusto.getCustos()) {
                                if (contadorCusto == 0) {
                                    row.createCell(10).setCellValue("Tipo Despesa: " + /** custo.getTipoDespesa() */ "  " + "Perfil Despesa: " + custo.getPerfilDespesa() + "  " + "Período de Execução (meses): " + custo.getPeriodoExecucao() + "  " + "Horas: " + custo.getHoras() + "  " + "Valor Hora: " + custo.getValorHora() + "  " + "Total: 100");
                                } else {
                                    Row rowCusto = sheet.createRow(rowIndexProposta++);
                                    rowCusto.createCell(10).setCellValue("Tipo Despesa: " + /** custo.getTipoDespesa() */  "  " + "Perfil Despesa: " + custo.getPerfilDespesa() + "  " + "Período de Execução (meses): " + custo.getPeriodoExecucao() + "  " + "Horas: " + custo.getHoras() + "  " + "Valor Hora: " + custo.getValorHora() + "  " + "Total: 100");
                                }
                                contadorCusto++;
                            }
                        }

                        int contadorBeneficio = 0;

                        for (Beneficio beneficio : proposta.getBeneficios()) {
                            if (contadorBeneficio == 0) {
                                row.createCell(11).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "  " + " Valor Mensal: " + beneficio.getValor_mensal() + "  " + " Moeda: " + beneficio.getMoeda() + "  " + " Memória de Cálculo: " + Jsoup.parse(new String(beneficio.getMemoriaCalculo(), StandardCharsets.UTF_8)).text());
                            } else {
                                Row roww = sheet.createRow(rowIndexProposta++);
                                roww.createCell(11).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "  " + " Valor Mensal: " + beneficio.getValor_mensal() + "  " + " Moeda: " + beneficio.getMoeda() + "  " + " Memória de Cálculo: " + Jsoup.parse(new String(beneficio.getMemoriaCalculo(), StandardCharsets.UTF_8)));
                            }

                            contadorBeneficio++;
                        }

                    } else {
                        XSSFRow rowProposta = sheet.createRow(rowIndexProposta++);

                        rowProposta.createCell(2).setCellValue(proposta.getTitulo());
                        rowProposta.createCell(3).setCellValue(proposta.getCodigoPPM());

                        rowProposta.createCell(4).setCellValue(proposta.getData());
                        rowProposta.createCell(5).setCellValue(proposta.getSolicitante().getNome() + " - " + proposta.getSolicitante().getDepartamento().getNome());
                        rowProposta.createCell(6).setCellValue(proposta.getGerente().getNome() + " - " + proposta.getGerente().getDepartamento().getNome());
                        rowProposta.createCell(7).setCellValue(Jsoup.parse(new String(proposta.getProblema(), StandardCharsets.UTF_8)).text());
                        rowProposta.createCell(8).setCellValue(Jsoup.parse(new String(proposta.getProposta(), StandardCharsets.UTF_8)).text());

                        rowProposta.createCell(12).setCellValue(proposta.getFrequencia());
                        rowProposta.createCell(13).setCellValue(proposta.getLinkJira());
                        rowProposta.createCell(14).setCellValue(proposta.getInicioExecucao() + " à " + proposta.getFimExecucao());
                        rowProposta.createCell(15).setCellValue(proposta.getPaybackValor() + "  " + proposta.getPaybackTipo());
                        rowProposta.createCell(16).setCellValue(proposta.getTamanho());
                        rowProposta.createCell(17).setCellValue(proposta.getSecaoTI().getSiglaSecao() + " - " + proposta.getSecaoTI().getNomeSecao());
                        rowProposta.createCell(18).setCellValue(proposta.getBuSolicitante().getSiglaBu() + "  " + proposta.getBuSolicitante().getNomeBu());

                        for (Bu bu : proposta.getBusBeneficiadas()) {
                            rowProposta.createCell(19).setCellValue(" - " + bu.getSiglaBu() + " - " + bu.getNomeBu() + "  ");
                            rowProposta.getCell(19).setCellStyle(alignLeft);
                        }

                        rowProposta.createCell(20).setCellValue(proposta.getForum().getSiglaForum() + " - " + proposta.getForum().getNomeForum());

                        for (ResponsavelNegocio rn : proposta.getResponsavelNegocio()) {
                            rowProposta.createCell(21).setCellValue(" - " + rn.getNome() + " - " + rn.getArea() + "  ");
                            rowProposta.getCell(21).setCellStyle(alignLeft);
                        }

                        for (Anexo anexo : proposta.getAnexo()) {
                            rowProposta.createCell(22).setCellValue(" - " + anexo.getNome() + ". " + anexo.getTipo());
                            rowProposta.getCell(22).setCellStyle(alignLeft);
                        }

                        int contadorCusto = 0;

                        for (TabelaCusto tbCusto : proposta.getTabelaCustos()) {
                            for (Custo custo : tbCusto.getCustos()) {
                                if (contadorCusto == 0) {
                                    rowProposta.createCell(10).setCellValue("Tipo Despesa: " + /** custo.getTipoDespesa() */ "  " + "Perfil Despesa: " + custo.getPerfilDespesa() + "  " + "Período de Execução (meses): " + custo.getPeriodoExecucao() + "  " + "Horas: " + custo.getHoras() + "  " + "Valor Hora: " + custo.getValorHora() + "  " + "Total: 100");
                                } else {
                                    Row rowCusto = sheet.createRow(rowIndexProposta++);
                                    rowCusto.createCell(10).setCellValue("Tipo Despesa: " + /** custo.getTipoDespesa() */ "  " + "Perfil Despesa: " + custo.getPerfilDespesa() + "  " + "Período de Execução (meses): " + custo.getPeriodoExecucao() + "  " + "Horas: " + custo.getHoras() + "  " + "Valor Hora: " + custo.getValorHora() + "  " + "Total: 100");
                                }
                                contadorCusto++;
                            }
                        }

                        int contadorBeneficio = 0;

                        for (Beneficio beneficio : proposta.getBeneficios()) {
                            if (contadorBeneficio == 0) {
                                rowProposta.createCell(11).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "  " + " Valor Mensal: " + beneficio.getValor_mensal() + "  " + " Moeda: " + beneficio.getMoeda() + "  " + " Memória de Cálculo: " + Jsoup.parse(new String(beneficio.getMemoriaCalculo(), StandardCharsets.UTF_8)).text());
                            } else {
                                Row roww = sheet.createRow(rowIndexProposta++);
                                roww.createCell(11).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "  " + " Valor Mensal: " + beneficio.getValor_mensal() + "  " + " Moeda: " + beneficio.getMoeda() + "  " + " Memória de Cálculo: " + Jsoup.parse(new String(beneficio.getMemoriaCalculo(), StandardCharsets.UTF_8)));
                            }
                            contadorBeneficio++;
                        }

                        int[] colunasStyle = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};

                        for (int colunaStyle : colunasStyle) {
                            if (rowProposta.getCell(colunaStyle) != null) {
                                rowProposta.getCell(colunaStyle).setCellStyle(alignLeft);
                            }
                        }
                    }
                    contadorProposta++;
                }
                rowNum = rowIndexProposta;
            }

            // Setando os estilos para as colunas
            int[] colunasStyle = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};

            for (int colunaStyle : colunasStyle) {
                if (row.getCell(colunaStyle) != null) {
                    row.getCell(colunaStyle).setCellStyle(alignLeft);
                }
            }

            // Auto ajustando o tamanho das colunas de acordo com as informações
            int[] colunasAutoSize = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};

            for (int colunaAuto : colunasAutoSize) {
                sheet.autoSizeColumn(colunaAuto);
            }

            contadorPauta++;
        }

        // Escreve o workbook no response
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    /**
     * Função para exportar as atas para excel
     *
     * @param response
     * @param listaAtas
     * @throws IOException
     */
    public void exportAtasToExcel(HttpServletResponse response, List<Long> listaAtas) throws IOException {
        ArrayList<Optional<Ata>> listAtas = new ArrayList<>();

        // Convertendo para pauta e adicionando na lista das pautas
        for (int i = 0; i < listaAtas.size(); i++) {
            Long idAta = listaAtas.get(i);
            Optional<Ata> ata = ataService.findById(idAta);
            listAtas.add(ata);
        }

        // Cria um workbook do Excel e uma planilha
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Atas");

        // Informações para o documento
        response.setHeader("Content-Disposition", "attachment; filename=atas.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Criando estilos e fontes para o arquivo
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
        headerRow.createCell(1).setCellValue("Pauta");
        headerRow.createCell(2).setCellValue("Propostas");
        headerRow.createCell(3).setCellValue("PPM");
        headerRow.createCell(4).setCellValue("Data");
        headerRow.createCell(5).setCellValue("Solicitante");
        headerRow.createCell(6).setCellValue("Gerente");
        headerRow.createCell(7).setCellValue("Problema");
        headerRow.createCell(8).setCellValue("Proposta");
        headerRow.createCell(9).setCellValue("Escopo");
        headerRow.createCell(10).setCellValue("Custos");
        headerRow.createCell(11).setCellValue("Benefícios");
        headerRow.createCell(12).setCellValue("Frequência de Uso");
        headerRow.createCell(13).setCellValue("Link Jira");
        headerRow.createCell(14).setCellValue("Período Execução");
        headerRow.createCell(15).setCellValue("Payback");
        headerRow.createCell(16).setCellValue("Tamanho");
        headerRow.createCell(17).setCellValue("Seção TI");
        headerRow.createCell(18).setCellValue("BU Solicitante");
        headerRow.createCell(19).setCellValue("BUs Beneficiadas");
        headerRow.createCell(20).setCellValue("Fórum");
        headerRow.createCell(21).setCellValue("Responsáveis Negócio");
        headerRow.createCell(22).setCellValue("Anexos");

        // Setando o estilo para as colunas
        int startColIndex = 0;
        int endColIndex = 22;
        int rowColIndex = 0;

        for (int colIndex = startColIndex; colIndex <= endColIndex; colIndex++) {
            Cell cell = sheet.getRow(rowColIndex).getCell(colIndex);
            cell.setCellStyle(style);
        }

        int rowNum = 1;
        int contadorAta = 1;
        for (Optional<Ata> ataOp : listAtas) {

            XSSFRow row = sheet.createRow(rowNum++);

            if (ataOp.isPresent()) {
                Ata ata = ataOp.get();

                row.createCell(0).setCellValue(contadorAta);
                row.createCell(1).setCellValue("Número Sequencial: " + ata.getNumeroSequencial() + "    Analista Responsável: " + ata.getAnalistaResponsavel().getNome() + "    Comissão: " + ata.getComissao().getSiglaForum() + " - " + ata.getComissao().getNomeForum());

                int rowIndexProposta = rowNum;

                int contadorProposta = 0;
                for (Proposta proposta : ata.getPropostas()) {

                    if (contadorProposta == 0) {
                        row.createCell(2).setCellValue(proposta.getTitulo());
                        row.createCell(3).setCellValue(proposta.getCodigoPPM());

                        row.createCell(4).setCellValue(proposta.getData());
                        row.createCell(5).setCellValue(proposta.getSolicitante().getNome() + " - " + proposta.getSolicitante().getDepartamento().getNome());
                        row.createCell(6).setCellValue(proposta.getGerente().getNome() + " - " + proposta.getGerente().getDepartamento().getNome());
                        row.createCell(7).setCellValue(Jsoup.parse(new String(proposta.getProblema(), StandardCharsets.UTF_8)).text());
                        row.createCell(8).setCellValue(Jsoup.parse(new String(proposta.getProposta(), StandardCharsets.UTF_8)).text());
                        int contadorCusto = 0;

                        for (TabelaCusto tbCusto : proposta.getTabelaCustos()) {
                            for (Custo custo : tbCusto.getCustos()) {
                                if (contadorCusto == 0) {
                                    row.createCell(10).setCellValue("Tipo Despesa: " + /** custo.getTipoDespesa() */ "  " + "Perfil Despesa: " + custo.getPerfilDespesa() + "  " + "Período de Execução (meses): " + custo.getPeriodoExecucao() + "  " + "Horas: " + custo.getHoras() + "  " + "Valor Hora: " + custo.getValorHora() + "  " + "Total: 100");
                                } else {
                                    Row rowCusto = sheet.createRow(rowIndexProposta);
                                    rowCusto.createCell(10).setCellValue("Tipo Despesa: " + /** custo.getTipoDespesa() */ "  " + "Perfil Despesa: " + custo.getPerfilDespesa() + "  " + "Período de Execução (meses): " + custo.getPeriodoExecucao() + "  " + "Horas: " + custo.getHoras() + "  " + "Valor Hora: " + custo.getValorHora() + "  " + "Total: 100");
                                    rowIndexProposta++;
                                }

                                contadorCusto++;
                            }
                        }

                        int contadorBeneficio = 0;

                        for (Beneficio beneficio : proposta.getBeneficios()) {
                            if (contadorBeneficio == 0) {
                                row.createCell(11).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "  " + " Valor Mensal: " + beneficio.getValor_mensal() + "  " + " Moeda: " + beneficio.getMoeda() + "  " + " Memória de Cálculo: " + Jsoup.parse(new String(beneficio.getMemoriaCalculo(), StandardCharsets.UTF_8)).text());
                            } else {
                                Row roww = sheet.createRow(rowIndexProposta);
                                roww.createCell(11).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "  " + " Valor Mensal: " + beneficio.getValor_mensal() + "  " + " Moeda: " + beneficio.getMoeda() + "  " + " Memória de Cálculo: " + Jsoup.parse(new String(beneficio.getMemoriaCalculo(), StandardCharsets.UTF_8)).text());
                                rowIndexProposta++;
                            }

                            contadorBeneficio++;
                        }

                        row.createCell(12).setCellValue(proposta.getFrequencia());
                        row.createCell(13).setCellValue(proposta.getLinkJira());
                        row.createCell(14).setCellValue(proposta.getInicioExecucao() + " à " + proposta.getFimExecucao());
                        row.createCell(15).setCellValue(proposta.getPaybackValor() + "  " + proposta.getPaybackTipo());
                        row.createCell(16).setCellValue(proposta.getTamanho());
                        row.createCell(17).setCellValue(proposta.getSecaoTI().getSiglaSecao() + " - " + proposta.getSecaoTI().getNomeSecao());
                        row.createCell(18).setCellValue(proposta.getBuSolicitante().getSiglaBu() + "  " + proposta.getBuSolicitante().getNomeBu());

                        for (Bu bu : proposta.getBusBeneficiadas()) {
                            row.createCell(19).setCellValue(" - " + bu.getSiglaBu() + " - " + bu.getNomeBu() + "  ");
                            row.getCell(19).setCellStyle(alignLeft);
                        }

                        row.createCell(20).setCellValue(proposta.getForum().getSiglaForum() + " - " + proposta.getForum().getNomeForum());

                        for (ResponsavelNegocio rn : proposta.getResponsavelNegocio()) {
                            row.createCell(21).setCellValue(" - " + rn.getNome() + " - " + rn.getArea() + "  ");
                            row.getCell(21).setCellStyle(alignLeft);
                        }

                        for (Anexo anexo : proposta.getAnexo()) {
                            row.createCell(22).setCellValue(" - " + anexo.getNome() + ". " + anexo.getTipo());
                            row.getCell(22).setCellStyle(alignLeft);
                        }
                    } else {
                        XSSFRow rowProposta = sheet.createRow(rowIndexProposta++);

                        rowProposta.createCell(2).setCellValue(proposta.getTitulo());
                        rowProposta.createCell(3).setCellValue(proposta.getCodigoPPM());

                        rowProposta.createCell(4).setCellValue(proposta.getData());
                        rowProposta.createCell(5).setCellValue(proposta.getSolicitante().getNome() + " - " + proposta.getSolicitante().getDepartamento().getNome());
                        rowProposta.createCell(6).setCellValue(proposta.getGerente().getNome() + " - " + proposta.getGerente().getDepartamento().getNome());
                        rowProposta.createCell(7).setCellValue(Jsoup.parse(new String(proposta.getProblema(), StandardCharsets.UTF_8)).text());
                        rowProposta.createCell(8).setCellValue(Jsoup.parse(new String(proposta.getProposta(), StandardCharsets.UTF_8)).text());

                        int contadorCusto = 0;

                        for (TabelaCusto tbCusto : proposta.getTabelaCustos()) {
                            for (Custo custo : tbCusto.getCustos()) {
                                if (contadorCusto == 0) {
                                    rowProposta.createCell(10).setCellValue("Tipo Despesa: " + /** custo.getTipoDespesa() */ "  " + "Perfil Despesa: " + custo.getPerfilDespesa() + "  " + "Período de Execução (meses): " + custo.getPeriodoExecucao() + "  " + "Horas: " + custo.getHoras() + "  " + "Valor Hora: " + custo.getValorHora() + "  " + "Total: 100");
                                } else {
                                    Row rowCusto = sheet.createRow(rowIndexProposta++);
                                    rowCusto.createCell(10).setCellValue("Tipo Despesa: " + /** custo.getTipoDespesa() */ "  " + "Perfil Despesa: " + custo.getPerfilDespesa() + "  " + "Período de Execução (meses): " + custo.getPeriodoExecucao() + "  " + "Horas: " + custo.getHoras() + "  " + "Valor Hora: " + custo.getValorHora() + "  " + "Total: 100");
                                }

                                contadorCusto++;
                            }
                        }

                        int contadorBeneficio = 0;

                        for (Beneficio beneficio : proposta.getBeneficios()) {
                            if (contadorBeneficio == 0) {
                                rowProposta.createCell(11).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "  " + " Valor Mensal: " + beneficio.getValor_mensal() + "  " + " Moeda: " + beneficio.getMoeda() + "  " + " Memória de Cálculo: " + Jsoup.parse(new String(beneficio.getMemoriaCalculo(), StandardCharsets.UTF_8)).text());
                            } else {
                                Row roww = sheet.createRow(rowIndexProposta++);
                                roww.createCell(11).setCellValue("Tipo: " + String.valueOf(beneficio.getTipoBeneficio()) + "  " + " Valor Mensal: " + beneficio.getValor_mensal() + "  " + " Moeda: " + beneficio.getMoeda() + "  " + " Memória de Cálculo: " + Jsoup.parse(new String(beneficio.getMemoriaCalculo(), StandardCharsets.UTF_8)).text());
                            }

                            contadorBeneficio++;
                        }

                        rowProposta.createCell(12).setCellValue(proposta.getFrequencia());
                        rowProposta.createCell(13).setCellValue(proposta.getLinkJira());
                        rowProposta.createCell(14).setCellValue(proposta.getInicioExecucao() + " à " + proposta.getFimExecucao());
                        rowProposta.createCell(15).setCellValue(proposta.getPaybackValor() + "  " + proposta.getPaybackTipo());
                        rowProposta.createCell(16).setCellValue(proposta.getTamanho());
                        rowProposta.createCell(17).setCellValue(proposta.getSecaoTI().getSiglaSecao() + " - " + proposta.getSecaoTI().getNomeSecao());
                        rowProposta.createCell(18).setCellValue(proposta.getBuSolicitante().getSiglaBu() + "  " + proposta.getBuSolicitante().getNomeBu());

                        for (Bu bu : proposta.getBusBeneficiadas()) {
                            rowProposta.createCell(19).setCellValue(" - " + bu.getSiglaBu() + " - " + bu.getNomeBu() + "  ");
                            rowProposta.getCell(19).setCellStyle(alignLeft);
                        }

                        rowProposta.createCell(20).setCellValue(proposta.getForum().getSiglaForum() + " - " + proposta.getForum().getNomeForum());

                        for (ResponsavelNegocio rn : proposta.getResponsavelNegocio()) {
                            rowProposta.createCell(21).setCellValue(" - " + rn.getNome() + " - " + rn.getArea() + "  ");
                            rowProposta.getCell(21).setCellStyle(alignLeft);
                        }

                        for (Anexo anexo : proposta.getAnexo()) {
                            rowProposta.createCell(22).setCellValue(" - " + anexo.getNome() + ". " + anexo.getTipo());
                            rowProposta.getCell(22).setCellStyle(alignLeft);
                        }

                        // Setando os estilos para as colunas
                        int[] colunasStyle = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};

                        for (int colunaStyle : colunasStyle) {
                            if (rowProposta.getCell(colunaStyle) != null) {
                                rowProposta.getCell(colunaStyle).setCellStyle(alignLeft);
                            }
                        }
                    }
                    contadorProposta++;
                }
                rowNum = rowIndexProposta;
            }

            // Setando os estilos para as colunas
            int[] colunasStyle = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};

            for (int colunaStyle : colunasStyle) {
                if (row.getCell(colunaStyle) != null) {
                    row.getCell(colunaStyle).setCellStyle(alignLeft);
                }
            }

            // Auto ajustando o tamanho das colunas de acordo com as informações
            int[] colunasAutoSize = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};

            for (int colunaAuto : colunasAutoSize) {
                sheet.autoSizeColumn(colunaAuto);
            }

            contadorAta++;
        }

        // Escreve o workbook no response
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}