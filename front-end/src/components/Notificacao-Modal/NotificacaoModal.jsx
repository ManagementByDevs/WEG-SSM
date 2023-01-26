import React, { useState } from "react";
import {
  Box,
  Tooltip,
  Typography,
  IconButton,
  Menu,
  MenuItem,
  FormControlLabel,
} from "@mui/material";

import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";

import Notificacao from "../Notificacao/Notificacao";
import FontConfig from "../../service/FontConfig";

const NotificacaoModal = (props) => {
  // UseState para poder visualizar e alterar a visibilidade das notificacoes
  const [anchorEl, setAnchorEl] = useState(null);

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

  //   Contador para ver se tem notificação não lida
  let contNaoLidas = 0;

  const [notificacoes, setNotificacoes] = useState([
    {
      tipo: "Aviso",
      notificacao: "Essa é o titulo da notificação",
      data: "01/25/2023",
      lida: false,
    },
    {
      tipo: "Aviso",
      notificacao: "notificação Essa titulo é o  da ",
      data: "01/16/2023",
      lida: false,
    },
    {
      tipo: "Aviso",
      notificacao: "titulo Essa é o notificação da ",
      data: "01/25/2023",
      lida: false,
    },
  ]);

  return (
    <>
      {/* Title  */}
      <Tooltip title="Notificações">
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
        <Box className="w-52 px-3 py-2 max-h-72">
          {/* Componente Notificacao (Cada notificacao que aparece) */}
          {notificacoes?.map((notificacao, index) => {
            if (!notificacao.lida) {
              contNaoLidas++;
              return (
                <Notificacao
                  key={index}
                  notificacao={notificacao}
                  index={index}
                />
              );
            }
          })}
          {/* Caso não haja notificação não lida, aparece a mensagem abaixo */}
          {contNaoLidas === 0 && (
            <Box className="flex items-center text-center w-full pt-1">
              <Typography
              fontSize={FontConfig.default}
                color={"text.secondary"}
                sx={{
                  fontWeight: 600,
                }}
              >
                Nenhuma notificação encontrada!
              </Typography>
            </Box>
          )}
          {/* Ver mais */}
          <Box className="flex justify-center w-full pt-1">
            <Typography
              color={"link.main"}
              sx={{
                fontWeight: 600,
                cursor: "pointer",
                "&:hover": {
                  textDecoration: "underline",
                },
              }}
            >
              Ver mais ({contNaoLidas})
            </Typography>
          </Box>
        </Box>
      </Menu>
    </>
  );
};

export default NotificacaoModal;
