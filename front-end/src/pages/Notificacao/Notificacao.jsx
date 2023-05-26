import React, { useState, useEffect, useContext } from "react";

import VLibras from "@djpfs/react-vlibras";

import {
  Box,
  Typography,
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

import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import MarkEmailReadOutlinedIcon from "@mui/icons-material/MarkEmailReadOutlined";
import MarkEmailUnreadOutlinedIcon from "@mui/icons-material/MarkEmailUnreadOutlined";
import RefreshIcon from "@mui/icons-material/Refresh";

import "./notificacaoStyle.css";

import ClipLoader from "react-spinners/ClipLoader";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import Paginacao from "../../components/Paginacao/Paginacao";
import NotificacaoDetermineIcon from "../../components/NotificacaoDetermineIcon/NotificacaoDetermineIcon";
import Feedback from "../../components/Feedback/Feedback";

import NotificacaoService from "../../service/notificacaoService";
import DateService from "../../service/dateService";
import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import UsuarioService from "../../service/usuarioService";
import CookieService from "../../service/cookieService";

// Tela para mostrar as notificações do usuário no sistema
const Notificacao = (props) => {
  // useContext para alterar o idioma do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  const [usuario, setUsuario] = useState(null);

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

  // Variável para esconder a lista de itens e mostrar um ícone de carregamento enquanto busca os itens no banco
  const [carregamento, setCarregamento] = useState(false);

  // Linhas da tabela
  const [rows, setRows] = useState([]);

  const [dadosNotificacao, setDadosNotificacao] = useState([]);

  // UseState para controlar o estado do feedback de lido/não lido
  const [feedback, setFeedback] = useState({
    visibilidade: false,
    tipo: "sucesso",
    mensagem: "sfd",
  });

  useEffect(() => {
    setCarregamento(true);
    buscarUsuario();
  }, []);

  // UseEffect utilizado para buscar as notificações do usuário
  useEffect(() => {
    buscarNotificacoes();
  }, [usuario]);

  useEffect(() => {
    setRows(createRows(dadosNotificacao));
  }, [texts]);

  // Cria uma linha da tabela retornando um objeto
  const createRows = (dataset) => {
    let rowsAux = [];

    for (let data of dataset) {
      // para ter o "titulo da notificação"
      const retornaTitulo = () => {
        if (data.numeroSequencial) {
          return formataStatus();
        }
      };

      const formataStatus = () => {
        switch (data.tipoNotificacao) {
          case "APROVADO":
            return `${texts.notificacaoComponente.demandaDeNumero} ${data.numeroSequencial} ${texts.notificacaoComponente.foi} ${texts.notificacaoComponente.aprovada}!`;
          case "REPROVADO":
            return `${texts.notificacaoComponente.demandaDeNumero} ${data.numeroSequencial} ${texts.notificacaoComponente.foi} ${texts.notificacaoComponente.reprovada}!`;
          case "MENSAGENS":
            return `${texts.notificacaoComponente.vcRecebeuMensagem} ${data.numeroSequencial}!`;
          case "MAIS_INFORMACOES":
            return `${texts.notificacaoComponente.demandaDeNumero} ${data.numeroSequencial} ${texts.notificacaoComponente.foi} ${texts.notificacaoComponente.reprovadaPorFaltaDeInformacoes}!`;
        }
      };

      rowsAux.push({
        checked: false,
        titulo: retornaTitulo(),
        numeroSequencial: data.numeroSequencial,
        date: formatDate(data.data),
        visualizado: data.visualizado,
        tipo_icone: data.tipoNotificacao,
        id: data.id,
        userId: data.usuario.id,
      });
    }

    return rowsAux;
  };

  const buscarUsuario = () => {
    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt").sub).then(
      (user) => {
        setUsuario(user);
      }
    );
  };

  // Formata a data do banco de dados de fulldate para date no padrão yyyy-mm-dd
  const formatDate = (fullDate) => {
    const data = DateService.getDateByPreviousDate(fullDate);
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

    openFeedback(
      bool
        ? texts.notificacao.notificacaoMArcadasComoNaoLidasComSucesso
        : texts.notificacao.notificacaoMArcadasComoLidasComSucesso
    );
    setRows(newList);
  };

  // Abre o feedback com a mensagem passada por parâmetro
  const openFeedback = (mensagem) => {
    setFeedback({ visibilidade: true, tipo: "info", mensagem });
  };

  // Deleta todas as linhas selecionadas
  const onMultiDeleteRowClick = () => {
    let aux = rows.filter((row) => {
      return row.checked;
    });

    for (let notificacao of aux) {
      deleteNotificacao(notificacao);
    }

    openFeedback(texts.notificacao.notificacaoExcluidasComSucesso);
  };

  // Atualiza o estado de visualizado da linha selecionada
  const onReadOrUnreadClick = (index) => {
    let aux = [...rows];
    aux[index].visualizado = !aux[index].visualizado;
    updateNotificacao(aux[index]);
    openFeedback(
      !aux[index].visualizado
        ? texts.notificacao.notificacaoMArcadasComoNaoLidasComSucesso
        : texts.notificacao.notificacaoMArcadasComoLidasComSucesso
    );
  };

  // Deleta linha selecionada
  const onDeleteClick = () => {
    deleteNotificacao(rows[indexDelete]);
    openFeedback(texts.notificacao.notificacaoExcluidasComSucesso);
  };

  // Busca as notificações do usuário no banco de dados
  const buscarNotificacoes = () => {
    if (usuario) {
      NotificacaoService.getByUserId(
        parseInt(usuario?.id),
        "size=" + tamanhoPagina + "&page=" + paginaAtual
      ).then((data) => {
        setRows(createRows(data.content));
        setTotalPaginas(data.totalPages);
        setCarregamento(false);
      });
    }
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
      numeroSequencial: notificacao.numeroSequencial,
      data: convertDateToSQLDate(notificacao.date),
      tipoNotificacao: convertTipoIconeToEnum(notificacao.tipo_icone),
      visualizado: notificacao.visualizado,
      usuario: { id: notificacao.userId },
    }).then(() => {
      buscarNotificacoes();
    });
  };

  const handleOnRefreshClick = () => {
    setCarregamento(true);
    buscarNotificacoes();
  };

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Busca as notificações do usuário ao carregar a página
  useEffect(() => {
    buscarNotificacoes();
  }, []);

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(props.texto);

    const finalizarLeitura = () => {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    };

    if (props.lendo && props.texto !== "") {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else if (!props.lendo) {
      finalizarLeitura();
    }

    return () => {
      finalizarLeitura();
    };
  }, [props.texto, props.lendo]);

  return (
    <FundoComHeader
      lendo={props.lendo}
      texto={props.texto}
      setTexto={props.setTexto}
    >
      <VLibras forceOnload />
      <Feedback
        open={feedback.visibilidade}
        handleClose={() => {
          setFeedback({ ...feedback, visibilidade: false });
        }}
        status={feedback.tipo}
        mensagem={feedback.mensagem}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />

      <ModalConfirmacao
        open={openModalConfirmDelete}
        setOpen={setOpenModalConfirmDelete}
        textoModal={"confirmarExclusao"}
        onConfirmClick={onDeleteClick}
        onCancelClick={() => {}}
        textoBotao={"sim"}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />

      <ModalConfirmacao
        open={openModalConfirmMultiDelete}
        setOpen={setOpenModalConfirmMultiDelete}
        textoModal={"confirmarExclusao"}
        onConfirmClick={onMultiDeleteRowClick}
        onCancelClick={() => {}}
        textoBotao={"sim"}
        lendo={props.lendo}
        texto={props.texto}
        setTexto={props.setTexto}
      />

      <Box className="p-2" sx={{ minWidth: "40rem" }}>
        <Caminho
          lendo={props.lendo}
          texto={props.texto}
          setTexto={props.setTexto}
        />
        <Box className="w-full flex flex-col items-center">
          <Box className="w-full flex justify-center m-2">
            <Typography
              fontSize={FontConfig.title}
              color={"icon.main"}
              sx={{ fontWeight: "600", cursor: "default" }}
              onClick={() => {
                lerTexto(texts.notificacao.notificacoes);
              }}
            >
              {texts.notificacao.notificacoes}
            </Typography>
          </Box>
          <Box className="w-10/12">
            <Divider sx={{ borderColor: "tertiary.main" }} />
          </Box>
          <Box
            className="w-10/12 h-10 flex justify-between "
            color={"icon.main"}
            sx={{ margin: "5px" }}
          >
            {rows.find((row) => row.checked) ? (
              <Box className="w-1/12 flex justify-center">
                <Tooltip title={texts.notificacao.deletar}>
                  <IconButton
                    color="primary"
                    size="small"
                    onClick={() => {
                      setOpenModalConfirmMultiDelete(true);
                    }}
                  >
                    <DeleteOutlineOutlinedIcon />
                  </IconButton>
                </Tooltip>
                <Tooltip title={texts.notificacao.marcarComoLido}>
                  <IconButton
                    color="primary"
                    size="small"
                    onClick={onMultiReadOrUnreadClick}
                  >
                    {rows.every((row) => row.visualizado) ? (
                      <MarkEmailUnreadOutlinedIcon />
                    ) : (
                      <MarkEmailReadOutlinedIcon />
                    )}
                  </IconButton>
                </Tooltip>
              </Box>
            ) : (
              <Box />
            )}
            <Tooltip title={texts.notificacao.atualizar}>
              <IconButton color="primary" onClick={handleOnRefreshClick}>
                <RefreshIcon />
              </IconButton>
            </Tooltip>
          </Box>
          {carregamento ? (
            <Box className="mt-6 w-full h-full flex justify-center items-center">
              <ClipLoader color="#00579D" size={110} />
            </Box>
          ) : totalPaginas >= 1 ? (
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
                            rows.every((row) => row.checked) && rows.length > 0
                          }
                          onChange={(e) => {
                            onSelectAllClick(e.target.checked);
                          }}
                        />
                      </th>
                      <th className="text-white">
                        <Typography
                          fontSize={FontConfig.big}
                          onClick={() => {
                            lerTexto(texts.notificacao.tipo);
                          }}
                        >
                          {texts.notificacao.tipo}
                        </Typography>
                      </th>
                      <th className="text-white">
                        <Typography
                          fontSize={FontConfig.big}
                          onClick={() => {
                            lerTexto(texts.notificacao.titulo);
                          }}
                        >
                          {texts.notificacao.titulo}
                        </Typography>
                      </th>
                      <th className="w-1/10 text-white">
                        <Typography
                          fontSize={FontConfig.big}
                          onClick={() => {
                            lerTexto(texts.notificacao.data);
                          }}
                        >
                          {texts.notificacao.data}
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
                          "&:last-child td, &:last-child th": {
                            border: 0,
                          },
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
                          <Typography
                            fontSize={FontConfig.medium}
                            onClick={() => {
                              lerTexto(row.titulo);
                            }}
                          >
                            {row.titulo}
                          </Typography>
                        </td>
                        <td className="text-center">
                          <Typography
                            className="notificacao-table-row-td"
                            fontSize={FontConfig.default}
                            onClick={() => {
                              lerTexto(row.date);
                            }}
                          >
                            {row.date}
                          </Typography>
                          <Typography className="notificacao-table-row-td-action">
                            {row.visualizado ? (
                              <Tooltip title={texts.login.marcarComoNaoLido}>
                                <MarkEmailUnreadOutlinedIcon
                                  onClick={() => onReadOrUnreadClick(index)}
                                  className="cursor-pointer"
                                  sx={{ color: "primary.main" }}
                                />
                              </Tooltip>
                            ) : (
                              <Tooltip title={texts.notificacao.marcarComoLido}>
                                <MarkEmailReadOutlinedIcon
                                  onClick={() => onReadOrUnreadClick(index)}
                                  className="cursor-pointer"
                                  sx={{ color: "primary.main" }}
                                />
                              </Tooltip>
                            )}
                            <Tooltip
                              title={texts.notificacao.deletar}
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
          ) : (
            <Box className="flex justify-center items-center h-32">
              <Typography
                fontSize={FontConfig.big}
                onClick={() => {
                  lerTexto(texts.notificacao.naoHaNotificacoes);
                }}
              >
                {texts.notificacao.naoHaNotificacoes}
              </Typography>
            </Box>
          )}
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default Notificacao;
