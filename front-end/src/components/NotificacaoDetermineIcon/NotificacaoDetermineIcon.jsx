import React from "react";

import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import ErrorOutlineOutlinedIcon from "@mui/icons-material/ErrorOutlineOutlined";
import HelpOutlineIcon from "@mui/icons-material/HelpOutline";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";

const NotificacaoDetermineIcon = ({ tipoIcone }) => {
  // Propriedades do ícone da notificação
  const properties = { fontSize: "30px" };

  // Determina o tipo do ícone da notificação
  return tipoIcone.startsWith("APROVADO") ? (
    <CheckCircleOutlineIcon color="primary" sx={properties} />
  ) : tipoIcone.startsWith("REPROVADO") ? (
    <ErrorOutlineOutlinedIcon color="primary" sx={properties} />
  ) : tipoIcone == "MAIS_INFORMACOES" ? (
    <HelpOutlineIcon color="primary" sx={properties} />
  ) : tipoIcone == "MENSAGENS" ? (
    <ChatBubbleOutlineIcon color="primary" sx={properties} />
  ) : null;
};

export default NotificacaoDetermineIcon;
