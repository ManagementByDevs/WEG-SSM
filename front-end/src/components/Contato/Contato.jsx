import React, { useContext, useState, useEffect } from "react";

import { Box, Typography, Avatar, Tooltip } from "@mui/material";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

import CommentsDisabledOutlinedIcon from "@mui/icons-material/CommentsDisabledOutlined";

import UsuarioService from "../../service/usuarioService";

// Componente contato utilizado para representar os contatos do chat
const Contato = (props) => {
  // UseState para saber se o contato foi selecionado ou não
  const [corSelecionado, setCorSelecionado] = useState("transparent");

  // UseEffect para alterar a cor do contato quando ele for selecionado
  useEffect(() => {
    if (props.contatoSelecionado == props.chat.id) {
      setCorSelecionado("chat.eu");
    } else {
      if (props.chat.conversaEncerrada) {
        setCorSelecionado("divider.claro");
      } else {
        setCorSelecionado("transparent");
      }
    }
  }, [props.contatoSelecionado, props.idChat]);

  return (
    <>
      {
        // Verificando se o chat está ativo ou não
        props.chat.conversaEncerrada ? (
          <Tooltip title={props.chat.idProposta.titulo} placement="right">
            <Box
              id="segundo"
              onClick={props.onClick}
              className="flex justify-evenly items-center rounded-lg border "
              sx={{
                width: "90%",
                minWidth: "195px",
                minHeight: "8%",
                cursor: "pointer",
                backgroundColor: corSelecionado,
              }}
            >
              <Conteudo chat={props.chat} />
            </Box>
          </Tooltip>
        ) : (
          <Tooltip title={props.chat.idProposta.titulo} placement="right">
            <Box
              id="segundo"
              onClick={props.onClick}
              className="flex justify-evenly items-center rounded-lg border delay-120 hover:scale-105 duration-300"
              sx={{
                width: "90%",
                minWidth: "195px",
                minHeight: "8%",
                cursor: "pointer",
                backgroundColor: corSelecionado,
                "&:hover": { backgroundColor: "chat.eu" },
              }}
            >
              <Conteudo chat={props.chat} />
            </Box>
          </Tooltip>
        )
      }
    </>
  );
};

const Conteudo = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  const [usuarioLogado, setUsuario] = useState(UsuarioService.getUserCookies());
  const [nomeContato, setNomeContato] = useState("");

  useEffect(() => {
    retornaNomeContato();
  }, []);

  const retornaNomeContato = () => {
    if (usuarioLogado.usuario.id !== props.chat.idProposta.solicitante.id) {
      setNomeContato(props.chat.idProposta.solicitante.nome);
    } else {
      props.chat.usuariosChat.map((usuarioChat) => {
        if (usuarioLogado.usuario.id !== usuarioChat.id) {
          setNomeContato(usuarioChat.nome);
        }
      });
    }
  };

  return (
    <>
      {/* Pegando a foto de perfil do usuário */}
      <Box className="flex justify-content items-center">
        <Avatar
          sx={{ width: "3rem", height: "3rem" }}
          // src={props.usuario.foto}
        />
      </Box>
      {/* Informações adicioanais do usuário e da demanda respectiva */}
      <Box className="flex justify-content flex-col" sx={{ width: "70%" }}>
        <Box className="flex justify-between">
          <Typography fontSize={FontConfig.medium} fontWeight="600">
            {nomeContato}
          </Typography>
          {
            // Verificando se o chat está ativo ou não
            props.chat.conversaEncerrada && (
              <Tooltip>
                <CommentsDisabledOutlinedIcon
                  sx={{
                    fontSize: "23px",
                    color: "text.secondary",
                    cursor: "pointer",
                    marginTop: "0.2rem",
                  }}
                />
              </Tooltip>
            )
          }
        </Box>
        <Typography
          fontSize={FontConfig.small}
          fontWeight="600"
          sx={{ color: "primary.main" }}
        >
          {texts.contato.ppm}: #{props.chat.idProposta.codigoPPM}
        </Typography>
        <Typography
          fontSize={FontConfig.small}
          fontWeight="400"
          className="overflow-hidden truncate"
          sx={{ width: "100%" }}
        >
          {texts.contato.demanda}: {props.chat.idProposta.titulo}
        </Typography>
      </Box>
    </>
  );
};

export default Contato;
