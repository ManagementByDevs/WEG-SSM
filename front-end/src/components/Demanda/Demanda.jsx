import React, { useState, useContext, useRef, useEffect } from "react";

import { Box, Typography, Button, Paper } from "@mui/material";

import ModalMotivoRecusa from "../ModalMotivoRecusa/ModalMotivoRecusa";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import CookieService from "../../service/cookieService";

/** Componente de demanda em formato de bloco, usado na listagem de demandas para os usuários.
 * Também possui a função de redirecionar a outra página com detalhes da demanda.
 */
const Demanda = (props) => {

  /** Contexto para trocar a linguagem */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

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

  /** useEffect utilizado para declarar o campo html na variável */
  useEffect(() => {
    if (descricaoDemanda.current) {
      descricaoDemanda.current.innerHTML = props.demanda.proposta;
    }

  }, [props.demanda]);

  /** Função para formatar o html em texto */
  const getPropostaFomartted = (proposta) => {
    return proposta[0].toUpperCase() + proposta.substring(1).toLowerCase();
  };

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (props.lendo) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (props.lendo && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

  return (
    <>
      {/* Modal de motivo recusa */}
      {modalMotivoRecusa && (
        <ModalMotivoRecusa
          open={true}
          setOpen={setModalMotivoRecusa}
          motivoRecusa={props.demanda?.motivoRecusa}
          lendo={props.lendo}
        />
      )}
      <Paper
        onClick={props.onClick}
        sx={{
          "&:hover": { backgroundColor: "hover.main" },
          borderColor: "primary.main",
          minWidth: "550px",
          maxWidth: "100%",
          minHeight: retornaAlturaDemanda(),
          cursor: "pointer",
        }}
        className={`items-center h-30 text-justify border-t-4 pt-2 pb-3 px-6 drop-shadow-lg transition duration-200 hover:transition hover:duration-200`}
      >
        <Box className={`flex justify-between`} sx={{ marginBottom: "1%" }}>
          {/* Título da demanda */}
          <Typography
            className="overflow-hidden text-ellipsis whitespace-nowrap"
            fontSize={FontConfig.veryBig}
            sx={{ fontWeight: "600", maxWidth: "77%" }}
            color="text.primary"
            title={props.demanda.titulo}
            onClick={(e) => {
              if (props.lendo) {
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
                    if (props.lendo) {
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
                      if (props.lendo) {
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
            if (props.lendo) {
              e.stopPropagation();
              lerTexto(getPropostaFomartted(props.demanda.proposta));
            }
          }}
        >
          {/* Chamando a função de formatação html, passando como parâmetro o texto em html */}
          {getPropostaFomartted(props.demanda.proposta)}
        </Typography>
        <Box className={`flex justify-end`} sx={{ marginTop: ".5%" }}>
          {/* Lógica para mostrar o nome do solicitante que criou a demanda caso o usuário logado não seja ele */}
          {props.demanda?.solicitante?.email !=
            CookieService.getCookie("jwt").sub ? (
            <Typography
              fontSize={FontConfig?.default}
              sx={{ fontWeight: "600", cursor: "default" }}
              color="text.primary"
              onClick={(e) => {
                if (props.lendo) {
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
            <Button
              id="setimo"
              onClick={(e) => {
                e.stopPropagation();
                if (!props.lendo) {
                  setModalMotivoRecusa(true);
                } else {
                  lerTexto(texts.demanda.motivo);
                }
              }}
              variant="contained"
            >
              {texts.demanda.motivo}
            </Button>
          ) : null}
        </Box>
      </Paper>
    </>
  );
};

export default Demanda;
