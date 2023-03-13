import React from "react";

import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import ErrorOutlineOutlinedIcon from "@mui/icons-material/ErrorOutlineOutlined";
import HelpOutlineIcon from "@mui/icons-material/HelpOutline";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";

const NotificacaoDetermineIcon = ({ tipoIcone }) => {
  
  // Determina o tipo do ícone da notificação
  return tipoIcone == "APROVADO" ? (
    <CheckCircleOutlineIcon
      color="primary"
      sx={{ fontSize: "35px", marginX: "0.5rem" }}
    />
  ) : tipoIcone == "REPROVADO" ? (
    <ErrorOutlineOutlinedIcon
      color="primary"
      sx={{ fontSize: "35px", marginX: "0.5rem" }}
    />
  ) : tipoIcone == "MAIS_INFORMACOES" ? (
    <HelpOutlineIcon
      color="primary"
      sx={{ fontSize: "35px", marginX: "0.5rem" }}
    />
  ) : (
    <ChatBubbleOutlineIcon
      color="primary"
      sx={{ fontSize: "35px", marginX: "0.5rem" }}
    />
  );
};

export default NotificacaoDetermineIcon;