import React, { useState, useContext } from "react";
import { Link, useLocation } from "react-router-dom";

import { Paper, Tooltip } from "@mui/material/";

import LogoBranca from "../../assets/LogoBranca.png";
import Grid from "../../assets/GridSemFundo.png";

import IdiomaModal from "../Idioma-Modal/IdiomaModal";
import UserModal from "../User-Modal/UserModal";
import NotificacaoModal from "../Notificacao-Modal/NotificacaoModal";
import ChatMinimizado from "../ChatMinimizado/ChatMinimizado";

import ChatContext from "../../service/ChatContext";
import TextLanguageContext from "../../service/TextLanguageContext";

/** Header padrão usado no topo de todas as páginas do sistema */
const Header = () => {

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Contexto para ver o chat minimizado
  const { visibilidade, usuarioId } = useContext(ChatContext);

  // Variável para pegar informações da URL
  const location = useLocation();

  // UseState para poder visualizar e alterar rota (pathname)
  const [rota, setRota] = useState(location.pathname);

  return (
    // Div Principal com width preenchendo a tela
    <Paper
      sx={{ backgroundColor: "primary.main", padding: "1rem", width: "100%", minWidth: "27rem" }}
      className={`flex justify-between items-center h-header-weg min-h-header-weg`}
      square
    >
      {/* Aparecer o chat em qualquer lugar */}
      {visibilidade && (
        <ChatMinimizado usuarioId={usuarioId} />
      )}

      {/* Link para página inicial */}
      <Link to={"/"}>
        {/* Title */}
        <Tooltip title={texts.Header.paginaInicial}>
          {/* Parte esquerda do header */}
          <div className="flex gap-2">
            {/* Grid da WEG */}
            <img className="h-10" src={Grid} />

            {/* Logo da WEG SSM */}
            <img className="h-10" src={LogoBranca} />
          </div>
        </Tooltip>
      </Link>

      {/* Parte direita do header */}
      <div className="flex items-center gap-4">
        {/* Caso não esteja no /login, aparecerá o Icon de notificações */}
        {rota != "/login" ? <NotificacaoModal /> : null}

        {/* Componente da parte do idioma da página */}
        <IdiomaModal />

        {/* Caso a rota não seja a do login, irá aparecer o componente do usuário */}
        {rota != "/login" ? <UserModal /> : null}
      </div>
    </Paper>
  );
};

export default Header;