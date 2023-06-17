import React, { useState, useContext, useRef, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import { Box, Typography, IconButton, Paper, Tooltip } from "@mui/material";

import ModalMotivoRecusa from "../ModalMotivoRecusa/ModalMotivoRecusa";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

import ChatService from "../../service/chatService";
import UsuarioService from "../../service/usuarioService";
import CookieService from "../../service/cookieService";

import InfoOutlinedIcon from "@mui/icons-material/InfoOutlined";
import ChatOutlinedIcon from "@mui/icons-material/ChatOutlined";
import propostaService from "../../service/propostaService";

/** Componente de demanda em formato de bloco, usado na listagem de demandas para os usuários.
 * Também possui a função de redirecionar a outra página com detalhes da demanda.
 */
const Demanda = (props) => {
  /** Navigate utilizado para navegar para outras páginas */
  const navigate = useNavigate();

  /** Contexto para trocar a linguagem */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto, lendoTexto } = useContext(SpeechSynthesisContext);

  // Variável para obter o usuário logado
  const [user] = useState(UsuarioService.getUserCookies());

  /** UseState determinando o estado do modal de motivo recusa */
  const [modalMotivoRecusa, setModalMotivoRecusa] = useState(false);

  /** Variável utilizada para armazenar o valor em html do campo */
  const descricaoDemanda = useRef(null);

  /** useEffect utilizado para declarar o campo html na variável */
  useEffect(() => {
    if (descricaoDemanda.current) {
      descricaoDemanda.current.innerHTML = props.demanda.proposta;
    }
  }, []);

  /** useEffect utilizado para declarar o campo html na variável */
  useEffect(() => {
    if (descricaoDemanda.current) {
      descricaoDemanda.current.innerHTML = props.demanda.proposta;
    }
  }, [props.demanda]);

  /** Função para receber a altura da div principal da demanda (é maior caso o solicitante seja o usuário logado) */
  const retornaAlturaDemanda = () => {
    if (
      props.demanda?.solicitante?.email != CookieService.getCookie("jwt").sub
    ) {
      return "8rem";
    } else {
      return "10rem";
    }
  };

  // Função para abrir o chat caso o solicitante da demanda/proposta não seja o usuario
  const entrarChat = (e) => {
    e.stopPropagation();
    let chat;
    propostaService.getByDemanda(props.demanda.id).then((proposta) => {
      console.log(proposta);
    ChatService.getByPropostaAndUser(proposta.id, user.usuario.id).then(
      (response) => {
        chat = response;
        if (chat.length > 0) {
          navigate(`/chat/${chat[0].id}`);
        } else {
          let newChat = {
            idProposta: { id: proposta.id },
            usuariosChat: [
              { id: user.usuario.id },
              { id: proposta.analista.id },
            ],
          };
          ChatService.post(newChat).then((response) => {
            chat = response;
            navigate(`/chat/${chat.id}`);
          });
        }
      }
    );
    });
  };

  /** Função para receber a cor do status da demanda de acordo com o status */
  const getStatusColor = () => {
    if (props.demanda.status == "CANCELLED") {
      return "#DA0303";
    } else if (props.demanda.status == "BACKLOG_REVISAO") {
      return "#C4C4C4";
    } else if (props.demanda.status == "BACKLOG_EDICAO") {
      return "#FFD600";
    } else if (props.demanda.status == "BACKLOG_APROVACAO") {
      return "#00579D";
    } else if (props.demanda.status == "ASSESSMENT") {
      return "#11B703";
    } else if (props.demanda.status == "ASSESSMENT_APROVACAO") {
      return "#F7DC6F";
    } else if (props.demanda.status == "DONE") {
      return "#7EB61C";
    }
  };

  /** Função para formatar o nome do status da demanda para o solicitante */
  const formatarNomeStatus = () => {
    if (props.demanda.status == "CANCELLED") {
      return texts.demanda.status.reprovada;
    } else if (props.demanda.status == "BACKLOG_REVISAO") {
      return texts.demanda.status.aguardandoRevisao;
    } else if (props.demanda.status == "BACKLOG_EDICAO") {
      return texts.demanda.status.aguardandoEdicao;
    } else if (props.demanda.status == "BACKLOG_APROVACAO") {
      return texts.demanda.status.emAprovacao;
    } else if (props.demanda.status == "ASSESSMENT") {
      return texts.demanda.status.aprovada;
    } else if (props.demanda.status == "ASSESSMENT_APROVACAO") {
      return texts.demanda.status.emAndamento;
    } else if (props.demanda.status == "DONE") {
      return texts.demanda.status.emDesenvolvimento;
    }
  };

  /** Função para formatar o html em texto */
  const getPropostaFomartted = (proposta) => {
    return proposta[0].toUpperCase() + proposta.substring(1).toLowerCase();
  };

  return (
    <>
      {/* Modal de motivo recusa */}
      {modalMotivoRecusa && (
        <ModalMotivoRecusa
          open={true}
          setOpen={setModalMotivoRecusa}
          motivoRecusa={props.demanda?.motivoRecusa}
        />
      )}
      <Paper
        onClick={props.onClick}
        sx={{
          "&:hover": { backgroundColor: "hover.main" },
          borderColor: "primary.main",
          minWidth: "540px",
          minHeight: retornaAlturaDemanda(),
          cursor: "pointer",
        }}
        className={`items-center h-30 text-justify border-t-4 pt-2 pb-1 px-6 drop-shadow-lg transition duration-200 hover:transition hover:duration-200`}
      >
        <Box
          className={`flex justify-between`}
          sx={{ marginBottom: "1%", height: "20%" }}
        >
          {/* Título da demanda */}
          <Typography
            className="overflow-hidden text-ellipsis whitespace-nowrap"
            fontSize={FontConfig.veryBig}
            sx={{ fontWeight: "600", maxWidth: "77%" }}
            color="text.primary"
            title={props.demanda.titulo}
            onClick={(e) => {
              if (lendoTexto) {
                e.stopPropagation();
                lerTexto(props.demanda.titulo);
              }
            }}
          >
            {props.demanda.titulo}
          </Typography>

          {/* Lógica para mostrar o status da demanda somente caso o usuário seja o dono dela */}
          {(props.demanda?.solicitante?.email ==
            CookieService.getCookie("jwt").sub ||
            props.demanda?.solicitante?.tour) && (
            <Box>
              <Typography
                fontSize={FontConfig.default}
                fontWeight={600}
                color="primary"
                className="text-end"
                onClick={(e) => {
                  if (lendoTexto) {
                    e.stopPropagation();
                    lerTexto(props.demanda.id);
                  }
                }}
              >
                #{props.demanda.id}
              </Typography>

              <Box id="oitavo" className={`items-center text-justify flex`}>
                <Typography
                  fontSize={FontConfig?.default}
                  sx={{ fontWeight: "600" }}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.stopPropagation();
                      lerTexto(formatarNomeStatus());
                    }
                  }}
                >
                  {formatarNomeStatus()}
                </Typography>
                <Box
                  sx={{
                    backgroundColor: getStatusColor(),
                    width: "12px",
                    height: "12px",
                    borderRadius: "10px",
                    marginLeft: "10px",
                  }}
                  className={`items-center h-30 text-justify`}
                />
              </Box>
            </Box>
          )}
        </Box>
        <Box sx={{ height: "40%" }}>
          {/* Proposta da demanda */}
          <Typography
            gutterBottom
            fontSize={FontConfig?.default}
            color="text.secondary"
            ref={descricaoDemanda}
            sx={{
              maxHeight: "5rem",
              maxWidth: "70%",
              overflow: "hidden",
              textOverflow: "ellipsis",
            }}
            onClick={(e) => {
              if (lendoTexto) {
                e.stopPropagation();
                lerTexto(getPropostaFomartted(props.demanda.proposta));
              }
            }}
          >
            {/* Chamando a função de formatação html, passando como parâmetro o texto em html */}
            {getPropostaFomartted(props.demanda.proposta)}
          </Typography>
        </Box>
        <Box
          className="flex justify-end items-center"
          sx={{ marginTop: ".5%", height: "20%" }}
        >
          {/* Lógica para mostrar o nome do solicitante que criou a demanda caso o usuário logado não seja ele */}
          {props.demanda?.solicitante?.email !=
          CookieService.getCookie("jwt").sub ? (
            <Typography
              fontSize={FontConfig?.default}
              sx={{ fontWeight: "600", cursor: "default" }}
              color="text.primary"
              onClick={(e) => {
                if (lendoTexto) {
                  e.stopPropagation();
                  lerTexto(props.demanda.solicitante?.nome);
                }
              }}
            >
              {props.demanda.solicitante?.nome}
            </Typography>
          ) : (props.demanda?.status == "CANCELLED" ||
              props.demanda?.status == "BACKLOG_EDICAO") &&
            props.demanda?.solicitante?.email ==
              CookieService.getCookie("jwt").sub ? (
            <Tooltip title={texts.demanda.motivo}>
              <IconButton
                onClick={(e) => {
                  e.stopPropagation();
                  if (!lendoTexto) {
                    setModalMotivoRecusa(true);
                  } else {
                    lerTexto(texts.demanda.motivo);
                  }
                }}
              >
                <InfoOutlinedIcon
                  id="setimo"
                  className="delay-120 hover:scale-110 duration-300"
                  sx={{
                    color: "icon.main",
                    cursor: "pointer",
                    fontSize: "30px",
                  }}
                />
              </IconButton>
            </Tooltip>
          ) : null}

          {props.demanda?.solicitante?.email ==
          CookieService.getCookie("jwt").sub ? (
            props.demanda.forum != null ? (
              <Tooltip title={texts.demandaGerencia.chat}>
                <IconButton onClick={entrarChat}>
                  <ChatOutlinedIcon
                    id="segundoPropostas"
                    className="delay-120 hover:scale-110 duration-300"
                    sx={{
                      color: "icon.main",
                      cursor: "pointer",
                      fontSize: "30px",
                    }}
                  />
                </IconButton>
              </Tooltip>
            ) : null
          ) : null}
        </Box>
      </Paper>
    </>
  );
};

export default Demanda;
