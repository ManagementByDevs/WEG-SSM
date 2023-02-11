import React, { useState, useContext } from "react";
import { Paper, Tooltip } from "@mui/material/";
import { Link, useLocation } from "react-router-dom";

import IdiomaModal from "../Idioma-Modal/IdiomaModal";
import UserModal from "../User-Modal/UserModal";
import NotificacaoModal from "../Notificacao-Modal/NotificacaoModal";

import LogoBranca from "../../assets/LogoBranca.png";
import Grid from "../../assets/GridSemFundo.png";

import FontContext from "../../service/FontContext";

const Header = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  
  // Variável para pegar informações da URL
  const location = useLocation();

  // UseState para poder visualizar e alterar rota (pathname)
  const [rota, setRota] = useState(location.pathname);

  return (
    // Div Principal com width preenchendo a tela
    <Paper
      sx={{ backgroundColor: "primary.main", padding: "1rem", width: "100%" }}
      className={`flex justify-between items-center h-header-weg min-h-header-weg`}
      square
    >
      {/* Link para página inicial */}
      <Link to={"/"}>
        {/* Title */}
        <Tooltip title="Página Inicial">
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
