import React, { useState, useEffect, useContext } from "react";
import {
  Box,
  Typography,
  Button,
  Divider,
  Table,
  TableBody,
  TableHead,
  TableRow,
  Paper,
  Checkbox,
  IconButton,
  Tooltip,
} from "@mui/material";

import "./notificacaoStyle.css";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import Paginacao from "../../components/Paginacao/Paginacao";
import NotificacaoDetermineIcon from "../../components/NotificacaoDetermineIcon/NotificacaoDetermineIcon";

import NotificacaoService from "../../service/notificacaoService";

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import MarkEmailReadOutlinedIcon from "@mui/icons-material/MarkEmailReadOutlined";
import MarkEmailUnreadOutlinedIcon from "@mui/icons-material/MarkEmailUnreadOutlined";

import FontContext from "../../service/FontContext";

const Notificacao = () => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Modal de confirmação de exclusão individual
  const [openModalConfirmDelete, setOpenModalConfirmDelete] = useState(false);
  // Modal de confirmação de exclusão múltipla
  const [openModalConfirmMultiDelete, setOpenModalConfirmMultiDelete] =
    useState(false);
  // UseState para saber qual notificação deletar ao usar o botão de delete individual
  const [indexDelete, setIndexDelete] = useState(null);
  // UseState que têm informações sobre a página atual
  const [page, setPage] = useState("size=20&page=0");
  // Pagina atual do componente de paginação
  const [paginaAtual, setPaginaAtual] = useState(0);
  // Tamanho da página do componente de paginação
  const [tamanhoPagina, setTamanhoPagina] = useState(20);
  // Total de páginas do componente de paginação
  const [totalPaginas, setTotalPaginas] = useState(1);

  useEffect(() => {
    buscarNotificacoes();
  }, [page, paginaAtual, tamanhoPagina]);

  // Linhas da tabela
  const [rows, setRows] = useState([]);

  // Cria uma linha da tabela retornando um objeto
  const createRows = (dataset) => {
    let rows = [];

    for (let data of dataset) {
      rows.push({
        checked: false,
        title: data.titulo,
        date: formatDate(data.data),
        visualizado: data.visualizado,
        tipo_icone: data.tipoNotificacao,
        id: data.id,
        userId: data.usuario.id,
      });
    }

    return rows;
  };

  // formata a data do banco de dados de fulldate para date no padrão yyyy-mm-dd
  const formatDate = (fullDate) => {
    const data = new Date(fullDate);
    const dd = String(data.getDate()).padStart(2, "0");
    const mm = String(data.getMonth() + 1).padStart(2, "0");
    const yyyy = data.getFullYear();
    return yyyy + "-" + mm + "-" + dd;
  };

  // Atualiza o estado da linha ao clicar no checkbox
  const onSelectRowClick = (event, index) => {
    let aux = [...rows];
    aux[index].checked = event.target.checked;

    setRows(aux);
  };

  // Atualiza o estado de todas as linhas ao clicar no checkbox de seleção de todas as linhas da tabela
  const onSelectAllClick = (isSelect) => {
    let aux = [...rows];
    aux.forEach((row) => {
      row.checked = isSelect;
    });
    setRows(aux);
  };

  // Atualiza o estado de visualizado de todas as linhas selecionadas
  const onMultiReadOrUnreadClick = () => {
    let listWithCheckeds = [...rows.filter((row) => row.checked)];
    let bool = listWithCheckeds.every((row) => row.visualizado);

    let newList = rows.map((row) => {
      if (row.checked) {
        row.visualizado = !bool;
        updateNotificacao(row);
      }
      return row;
    });
    setRows(newList);
  };

  // Deleta todas as linhas selecionadas
  const onMultiDeleteRowClick = () => {
    let aux = rows.filter((row) => {
      return row.checked;
    });

    for (let notificacao of aux) {
      deleteNotificacao(notificacao);
    }
  };

  // Atualiza o estado de visualizado da linha selecionada
  const onReadOrUnreadClick = (index) => {
    let aux = [...rows];
    aux[index].visualizado = !aux[index].visualizado;
    updateNotificacao(aux[index]);
  };

  // Deleta linha selecionada
  const onDeleteClick = () => {
    deleteNotificacao(rows[indexDelete]);
  };

  // Busca as notificações do usuário no banco de dados
  const buscarNotificacoes = () => {
    let user = JSON.parse(localStorage.getItem("user"));
    NotificacaoService.getByUserId(
      parseInt(user.id),
      "size=" + tamanhoPagina + "&page=" + paginaAtual
    ).then((data) => {
      setRows(createRows(data.content));
      setTotalPaginas(data.totalPages);
    });
  };

  // Delete uma notificação no banco de dados
  const deleteNotificacao = (notificacao) => {
    NotificacaoService.delete(notificacao.id).then(() => {
      buscarNotificacoes();
    });
  };

  // Converte a data para o formato yyyy-mm-dd hh:mm:ss (utilizado no banco de dados)
  const convertDateToSQLDate = (date) => {
    return new Date(date).toISOString().slice(0, 19);
  };

  // Converte o tipo do ícone resgatado do banco de dados para um valor que será interpretado pelo front-end
  const convertTipoIconeToEnum = (tipo) => {
    switch (tipo) {
      case "APROVADO":
        return 0;
      case "REPROVADO":
        return 1;
      case "MAIS_INFORMACOES":
        return 2;
      case "MENSAGENS":
        return 3;
    }
  };

  // Atualiza a notificação no banco de dados
  const updateNotificacao = (notificacao) => {
    NotificacaoService.put({
      id: notificacao.id,
      titulo: notificacao.title,
      data: convertDateToSQLDate(notificacao.date),
      tipoNotificacao: convertTipoIconeToEnum(notificacao.tipo_icone),
      visualizado: notificacao.visualizado,
      usuario: { id: notificacao.userId },
    }).then(() => {
      buscarNotificacoes();
    });
  };

  // Busca as notificações do usuário ao carregar a página
  useEffect(() => {
    buscarNotificacoes();
  }, []);

  return (
    <FundoComHeader>
      <ModalConfirmacao
        open={openModalConfirmDelete}
        setOpen={setOpenModalConfirmDelete}
        textoModal={"confirmarExclusao"}
        onConfirmClick={onDeleteClick}
        onCancelClick={() => {}}
        textoBotao={"sim"}
      />

      <ModalConfirmacao
        open={openModalConfirmMultiDelete}
        setOpen={setOpenModalConfirmMultiDelete}
        textoModal={"confirmarExclusao"}
        onConfirmClick={onMultiDeleteRowClick}
        onCancelClick={() => {}}
        textoBotao={"sim"}
      />

      <Box className="p-2">
        <Caminho />
        <Box className="w-full flex flex-col items-center">
          <Box className="w-full flex justify-center m-2">
            <Typography
              fontSize={FontConfig.title}
              color={"icon.main"}
              sx={{ fontWeight: "600", cursor: "default" }}
            >
              Notificações
            </Typography>
          </Box>
          <Box className="w-10/12">
            <Divider sx={{ borderColor: "tertiary.main" }} />
          </Box>
          {totalPaginas >= 1 ? (
            <>
              <Box className="w-full flex justify-center">
                <Box
                  className="w-10/12 h-10 flex justify-between "
                  color={"icon.main"}
                  sx={{ margin: "5px" }}
                >
                  {rows.find((row) => row.checked) ? (
                    <Box className="w-1/12 flex justify-center">
                      <Tooltip title="Deletar">
                        <IconButton
                          onClick={() => {
                            setOpenModalConfirmMultiDelete(true);
                          }}
                        >
                          <DeleteOutlineOutlinedIcon
                            sx={{ color: "primary.main" }}
                          />
                        </IconButton>
                      </Tooltip>
                      <Tooltip title="Marcar como lido">
                        <IconButton onClick={onMultiReadOrUnreadClick}>
                          {rows.every((row) => row.visualizado) ? (
                            <MarkEmailUnreadOutlinedIcon
                              sx={{ color: "primary.main" }}
                            />
                          ) : (
                            <MarkEmailReadOutlinedIcon
                              sx={{ color: "primary.main" }}
                            />
                          )}
                        </IconButton>
                      </Tooltip>
                    </Box>
                  ) : (
                    <Box />
                  )}
                </Box>
              </Box>
              <Box className="w-10/12 flex justify-center">
                <Paper sx={{ width: "100%" }} square>
                  <Table className="mb-8" sx={{ width: "100%" }}>
                    <TableHead>
                      <TableRow sx={{ backgroundColor: "primary.main" }}>
                        <th className="w-1/12">
                          <Checkbox
                            sx={{
                              color: "white",
                              "&.Mui-checked": {
                                color: "white",
                              },
                            }}
                            checked={
                              rows.every((row) => row.checked) &&
                              rows.length > 0
                            }
                            onChange={(e) => {
                              onSelectAllClick(e.target.checked);
                            }}
                          />
                        </th>
                        <th className="text-white">
                          <Typography fontSize={FontConfig.big}>
                            Tipo
                          </Typography>
                        </th>
                        <th className="text-white">
                          <Typography fontSize={FontConfig.big}>
                            Título
                          </Typography>
                        </th>
                        <th className="w-1/10 text-white">
                          <Typography fontSize={FontConfig.big}>
                            Data
                          </Typography>
                        </th>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {rows.map((row, index) => (
                        <TableRow
                          className="drop-shadow-lg noticacao-table-row"
                          selected={!row.visualizado}
                          hover
                          key={index}
                          sx={{
                            "&:last-child td, &:last-child th": { border: 0 },
                          }}
                        >
                          <td className="text-center">
                            <Checkbox
                              checked={row.checked}
                              onChange={(e) => {
                                onSelectRowClick(e, index);
                              }}
                            />
                          </td>
                          <td className="text-center">
                            <NotificacaoDetermineIcon
                              tipoIcone={row.tipo_icone}
                            />
                          </td>
                          <td className="text-left">
                            <Typography fontSize={FontConfig.medium}>
                              {row.title}
                            </Typography>
                          </td>
                          <td className="text-center">
                            <Typography
                              className="notificacao-table-row-td"
                              fontSize={FontConfig.default}
                            >
                              {row.date}
                            </Typography>
                            <Typography className="notificacao-table-row-td-action">
                              {row.visualizado ? (
                                <Tooltip title="Marcar como não lido">
                                  <MarkEmailUnreadOutlinedIcon
                                    onClick={() => onReadOrUnreadClick(index)}
                                    className="cursor-pointer"
                                    sx={{ color: "primary.main" }}
                                  />
                                </Tooltip>
                              ) : (
                                <Tooltip title="Marcar como lido">
                                  <MarkEmailReadOutlinedIcon
                                    onClick={() => onReadOrUnreadClick(index)}
                                    className="cursor-pointer"
                                    sx={{ color: "primary.main" }}
                                  />
                                </Tooltip>
                              )}
                              <Tooltip
                                title="Deletar"
                                className="cursor-pointer ml-4"
                              >
                                <DeleteOutlineOutlinedIcon
                                  onClick={() => {
                                    setIndexDelete(index);
                                    setOpenModalConfirmDelete(true);
                                  }}
                                  sx={{ color: "primary.main" }}
                                />
                              </Tooltip>
                            </Typography>
                          </td>
                        </TableRow>
                      ))}
                    </TableBody>
                  </Table>
                  {totalPaginas >= 1 && (
                    <Box className="flex justify-end">
                      <Paginacao
                        totalPaginas={totalPaginas}
                        setPage={setPage}
                        setTamanho={setTamanhoPagina}
                        tamanhoPagina={tamanhoPagina}
                        setPaginaAtual={setPaginaAtual}
                      />
                    </Box>
                  )}
                </Paper>
              </Box>
            </>
          ) : (
            <Box className="flex justify-center items-center h-32">
              <Typography fontSize={FontConfig.big}>
                Não há nenhuma notificação
              </Typography>
            </Box>
          )}
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default Notificacao;
