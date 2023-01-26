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

  return (
    <>
      {/* Title  */}
      <Tooltip title="Notificações">
        <IconButton size="small" aria-haspopup="true">
          {/* Icone de notificações */}
          <NotificationsNoneIcon
            sx={{ fontSize: "30px", color: "#FFFF", cursor: "pointer" }}
            onClick={handleClick}
          />
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
          <Notificacao />
          <Notificacao />
          <Notificacao />
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
              Ver mais (3)
            </Typography>
          </Box>
        </Box>
      </Menu>
    </>
  );
};

export default NotificacaoModal;
