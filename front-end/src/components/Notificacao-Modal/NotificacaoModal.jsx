import React, { useState, useContext, useEffect } from "react";
import { Box, Tooltip, Typography, IconButton, Menu } from "@mui/material";
import { useNavigate } from "react-router-dom";

import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";

import Notificacao from "../Notificacao/Notificacao";
import Feedback from "../Feedback/Feedback";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import NotificacaoService from "../../service/notificacaoService";

const NotificacaoModal = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Navigate utilizado para navegar para outras páginas
  const navigate = useNavigate();

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // UseState para poder visualizar e alterar a visibilidade das notificacoes
  const [anchorEl, setAnchorEl] = useState(null);

  // UseState para poder visualizar e alterar a visibilidade do feedback de notificação lida
  const [feedback, setFeedback] = useState(false);

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

  // Contador para ver se tem notificação não lida
  const [contNaoLidas, setContNaoLidas] = useState(0);

  const [notificacoes, setNotificacoes] = useState([]);

  // Função para buscar as notificações não lidas do usuário
  const buscarNotificacoes = () => {
    let user = JSON.parse(localStorage.getItem("user"));
    NotificacaoService.getByUserIdAndNotVisualizado(user.id)
      .then((response) => {
        setNotificacoes(response.content);
        setContNaoLidas(response.totalElements);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  // UseEffect para buscar as informações assim que entra na página
  useEffect(() => {
    buscarNotificacoes();
  }, []);

  // Função para quando clicar em uma notificação
  const onNotificationItemClick = () => {
    setFeedback(true);
    buscarNotificacoes();
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
              notificacoes?.map((notificacao, index) => {
                if (!notificacao.lida) {
                  return (
                    <Box
                      key={index}
                      className="absolute top-0 right-0.5 w-3 h-3 bg-red-500 rounded-full"
                    ></Box>
                  );
                }
              })
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
        anchorOrigin={{
          vertical: "bottom",
          horizontal: "right",
        }}
        transformOrigin={{
          vertical: "top",
          horizontal: "right",
        }}
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
            {contNaoLidas === 0 && (
              <Box className="flex items-center justify-center text-center w-full pt-1">
                <Typography
                  fontSize={FontConfig.default}
                  color={"text.secondary"}
                  sx={{
                    fontWeight: 600,
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
              fontSize={FontConfig.default}
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
                navigate("/notificacao");
              }}
            >
              {contNaoLidas > 0
                ? texts.notificacaoModal.verTudo + "(" + contNaoLidas + ")"
                : texts.notificacaoModal.verNotificacoes}
            </Typography>
          </Box>
        </Box>
      </Menu>
    </>
  );
};

export default NotificacaoModal;
