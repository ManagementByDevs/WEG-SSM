import React, { useState, useContext, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import { Box, Tooltip, Typography, IconButton, Menu } from "@mui/material";

import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";

import Notificacao from "../Notificacao/Notificacao";
import Feedback from "../Feedback/Feedback";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

import CookieService from "../../service/cookieService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import NotificacaoService from "../../service/notificacaoService";
import UsuarioService from "../../service/usuarioService";
import DateService from "../../service/dateService";
import { WebSocketContext } from "../../service/WebSocketService";

// Modal de notificações do sistema
const NotificacaoModal = (props) => {
  // Variável para pegar informações da URL
  const location = useLocation();

  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  /**  Context do WebSocket */
  const { inscrever, stompClient } = useContext(WebSocketContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Navigate utilizado para navegar para outras páginas
  const navigate = useNavigate();

  /** Usuário logado */
  const [user, setUser] = useState(UsuarioService.getUserCookies().usuario);

  // UseState para poder visualizar e alterar a visibilidade das notificacoes
  const [anchorEl, setAnchorEl] = useState(null);

  // UseState para poder visualizar e alterar a visibilidade do feedback de notificação lida
  const [feedback, setFeedback] = useState(false);

  /** Lista de notificações não lidas do usuário */
  const [notificacoes, setNotificacoes] = useState([]);

  // UseState para poder visualizar e alterar rota (pathname)
  const [rota, setRota] = useState(location.pathname);

  // Variável que é usada para saber se o menu está aberto ou não
  const open = Boolean(anchorEl);

  // Função para abrir o menu
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  // Função para fechar o menu
  const handleClose = (src) => {
    setAnchorEl(null);
  };

  // Função para buscar as notificações não lidas do usuário
  const buscarNotificacoes = () => {
    if (!CookieService.getCookie("jwt")) return;
    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt").sub).then(
      (user) => {
        NotificacaoService.getByUserIdAndNotVisualizado(user.id)
          .then((response) => {
            setNotificacoes([...response.content]);
          })
          .catch((error) => {});
      }
    );
  };

  // Função para quando clicar em uma notificação
  const onNotificationItemClick = () => {
    setFeedback(true);
    buscarNotificacoes();
  };

  const saveNotificacao = (mensagem = EntitiesObjectService.mensagem()) => {
    let newNotificacao = EntitiesObjectService.notificacao();
    newNotificacao.numeroSequencial = mensagem.idChat.id;
    newNotificacao.data = DateService.getTodaysDateMySQL();
    newNotificacao.tipoNotificacao = "MENSAGENS";
    newNotificacao.usuario.id = user.id;

    NotificacaoService.post(newNotificacao).then((notificacaoResponse) => {
      setNotificacoes((oldNotificacoes) => {
        oldNotificacoes.unshift(notificacaoResponse);
        return [...oldNotificacoes];
      });
    });
  };

  // UseEffect para buscar as informações assim que entra na página
  useEffect(() => {
    buscarNotificacoes();
  }, []);

  // UseEffect para se inscrever no tópico de chats para receber notificações em tempo real
  useEffect(() => {
    const receivedAnyMessage = (mensagem) => {
      let mensagemRecebida = EntitiesObjectService.mensagem();
      mensagemRecebida = JSON.parse(mensagem.body);
      // Se a mensagem recebida for do usuário logado, ignore
      if (mensagemRecebida.usuario.id == user.id) {
        console.log("a");
        return;
      }

      saveNotificacao(mensagemRecebida);
    };

    if (!rota.includes("/chat")) {
      let inscricaoAllMensagens = inscrever(
        "/weg_ssm/mensagem/all",
        receivedAnyMessage
      );

      return () => {
        if (inscricaoAllMensagens) {
          inscricaoAllMensagens.unsubscribe();
        }
      };
    }
  }, [stompClient]);

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    if (props.lendo && props.texto != "") {
      if ("speechSynthesis" in window) {
        const synthesis = window.speechSynthesis;
        const utterance = new SpeechSynthesisUtterance(props.texto);
        synthesis.speak(utterance);
      }
      props.setTexto("");
    }
  }, [props.texto]);

  return (
    <>
      <Feedback
        open={feedback}
        handleClose={() => {
          setFeedback(false);
        }}
        status={"info"}
        mensagem={texts.notificacaoModal.notificacaoLidaComSucesso}
      />
      {/* Title  */}
      <Tooltip title={texts.notificacaoModal.notificacoes}>
        <IconButton size="small" aria-haspopup="true" onClick={handleClick}>
          {/* Manter a bolinha e o icon de notificacao juntos */}
          <Box className="relative">
            {/* Icone de notificações */}
            <NotificationsNoneIcon
              sx={{ fontSize: "30px", color: "#FFFF", cursor: "pointer" }}
            />
            {
              // Verifica se tem notificação não lida
              notificacoes.length > 0 && (
                <Box className="absolute top-0 right-0.5 w-3 h-3 bg-red-500 rounded-full" />
              )
            }
          </Box>
        </IconButton>
      </Tooltip>

      {/* Menu (modal) */}
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
        transformOrigin={{ vertical: "top", horizontal: "right" }}
        {...props}
      >
        <Box className="w-72 px-3 py-2 max-h-60 overflow-hidden">
          <Box
            className="flex flex-col items-center overflow-y-auto overflow-x-hidden"
            sx={{ maxHeight: "12.5rem" }}
          >
            {/* Componente Notificacao (Cada notificacao que aparece) */}
            {notificacoes?.map((notificacao, index) => {
              return (
                <Notificacao
                  key={index}
                  notificacao={notificacao}
                  onNotificacaoClick={onNotificationItemClick}
                  index={index}
                />
              );
            })}
            {/* Caso não haja notificação não lida, aparece a mensagem abaixo */}
            {notificacoes.length === 0 && (
              <Box className="flex items-center justify-center text-center w-full pt-1">
                <Typography
                  fontSize={FontConfig?.default}
                  color={"text.secondary"}
                  sx={{
                    fontWeight: 600,
                  }}
                  onClick={() => {
                    lerTexto(
                      texts.notificacaoModal.nenhumaNotificaçaoEncontrada
                    );
                  }}
                >
                  {texts.notificacaoModal.nenhumaNotificaçaoEncontrada}
                </Typography>
              </Box>
            )}
          </Box>
          {/* Ver Tudo */}
          <Box className="flex justify-center w-full py-1 mt-2">
            <Typography
              fontSize={FontConfig?.default}
              color={"link.main"}
              sx={{
                fontWeight: 600,
                cursor: "pointer",
                "&:hover": {
                  textDecoration: "underline",
                },
              }}
              // Se clicar ir para a pagina de notificacao
              onClick={() => {
                if (!props.lendo) {
                  navigate("/notificacao");
                } else {
                  lerTexto(texts.notificacaoModal.verTudo);
                }
              }}
            >
              {notificacoes.length > 0
                ? texts.notificacaoModal.verTudo +
                  "(" +
                  notificacoes.length +
                  ")"
                : texts.notificacaoModal.verNotificacoes}
            </Typography>
          </Box>
        </Box>
      </Menu>
    </>
  );
};

export default NotificacaoModal;
