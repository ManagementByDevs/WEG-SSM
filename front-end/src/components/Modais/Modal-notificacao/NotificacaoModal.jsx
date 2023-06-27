import React, { useState, useContext, useEffect, useRef } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import { Box, Tooltip, Typography, IconButton, Menu } from "@mui/material";

import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";

import Notificacao from "../../Notificacao/Notificacao";
import Feedback from "../../Feedback/Feedback";

import TextLanguageContext from "../../../service/TextLanguageContext";
import FontContext from "../../../service/FontContext";

import CookieService from "../../../service/cookieService";
import EntitiesObjectService from "../../../service/entitiesObjectService";
import NotificacaoService from "../../../service/notificacaoService";
import UsuarioService from "../../../service/usuarioService";
import DateService from "../../../service/dateService";
import { WebSocketContext } from "../../../service/WebSocketService";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";

/** Ícone e modal de notificações presente no Header */
const NotificacaoModal = () => {
  /** Variável para pegar informações da URL */
  const location = useLocation();

  /** Navigate utilizado para navegar para outras páginas */
  const navigate = useNavigate();

  /** Context para alterar a linguagem do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /**  Context do WebSocket */
  const { inscrever, stompClient } = useContext(WebSocketContext);

  // Context para ler o texto da tela
  const { lendoTexto, lerTexto } = useContext(SpeechSynthesisContext);

  /** Referência usada para determinar a posição do modal na tela */
  const elementoAncora = useRef(null);

  /** Usuário logado */
  const [user, setUser] = useState(UsuarioService.getUserCookies().usuario);

  /** UseState para poder visualizar e alterar a visibilidade das notificacoes */
  const [anchorEl, setAnchorEl] = useState(null);

  /** UseState para poder visualizar e alterar a visibilidade do feedback de notificação lida */
  const [feedback, setFeedback] = useState(false);

  /** Lista de notificações não lidas do usuário */
  const [notificacoes, setNotificacoes] = useState([]);

  /** UseState para poder visualizar e alterar rota (pathname) */
  const [rota, setRota] = useState(location.pathname);

  /** Variável que é usada para saber se o menu está aberto ou não */
  const [open, setOpen] = useState(false);

  /** UseEffect para buscar as informações assim que entra na página */
  useEffect(() => {
    buscarNotificacoes();

    if (elementoAncora.current) {
      setAnchorEl(elementoAncora.current);
    }
  }, []);

  /** UseEffect para se inscrever no tópico de chats para receber notificações em tempo real */
  useEffect(() => {
    /** Função para chamar a função "saveNotificacao" quando receber uma mensagem nova */
    const receivedAnyMessage = (mensagem) => {
      let mensagemRecebida = EntitiesObjectService.mensagem();
      mensagemRecebida = JSON.parse(mensagem.body);
      saveNotificacao(mensagemRecebida);
    };

    /** Inscrição para o servidor WebSocket, redirecionando para a função "receivedAnyMessage" quando receber uma nova mensagem */
    if (!rota.includes("/chat")) {
      let inscricaoAllMensagens = inscrever(
        `/weg_ssm/mensagem/all/user/${user.id}`,
        receivedAnyMessage
      );

      return () => {
        if (inscricaoAllMensagens) {
          inscricaoAllMensagens.unsubscribe();
        }
      };
    }
  }, [stompClient]);

  /** UseEffect para se inscrever no servidor WebSocket para receber novas notificações */
  useEffect(() => {
    /** Função para adicionar uma nova notificação recebida na lista de notificações */
    const acaoNovaNotificacao = (response) => {
      const notificacao = JSON.parse(response.body);
      setNotificacoes([...notificacoes, notificacao]);
    };

    /** Inscrição ao WebSocket, que redirecionará para a função "acaoNovaNotificacao" quando receber uma nova notificação */
    if (stompClient) {
      let userId = CookieService.getUser().id;
      inscrever(`/weg_ssm/${userId}/notificacao`, acaoNovaNotificacao);
    }
  }, [stompClient, notificacoes]);

  /** Função para abrir/fechar o modal */
  const handleClick = () => {
    setOpen(!open);
  };

  /** Função para buscar as notificações não lidas do usuário */
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

  /** Função para ativar o feedback e recarregar as notificações quando uma for lida */
  const onNotificationItemClick = () => {
    setFeedback(true);
    buscarNotificacoes();
  };

  /** Função para salvar uma notificação de "Nova Mensagem" recebida pelo servidor WebSocket */
  const saveNotificacao = (mensagem = EntitiesObjectService.mensagem()) => {
    let newNotificacao = EntitiesObjectService.notificacao();
    newNotificacao.numeroSequencial = mensagem.idChat.id;
    newNotificacao.data = DateService.getTodaysDateMySQL();
    newNotificacao.tipoNotificacao = "MENSAGENS";
    newNotificacao.usuario.id = user.id;
    newNotificacao.remetente.id = user.id;

    NotificacaoService.post(newNotificacao).then((notificacaoResponse) => {
      setNotificacoes((oldNotificacoes) => {
        oldNotificacoes.unshift(notificacaoResponse);
        return [...oldNotificacoes];
      });
    });
  };

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
      <Tooltip title={texts.notificacaoModal.notificacoes} ref={elementoAncora}>
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
        onClose={handleClick}
        anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
        transformOrigin={{ vertical: "top", horizontal: "right" }}
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
                if (!lendoTexto) {
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
