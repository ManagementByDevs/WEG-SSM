import React, { useContext, useState, useEffect } from "react";

import { Box, Typography, Avatar, Tooltip } from "@mui/material";

import CommentsDisabledOutlinedIcon from "@mui/icons-material/CommentsDisabledOutlined";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

import UsuarioService from "../../service/usuarioService";
import EntitiesObjectService from "../../service/entitiesObjectService";

// Componente contato utilizado para representar os contatos do chat
const Contato = ({
  onClick = () => {},
  idChat = 0,
  chat = EntitiesObjectService.chat(),
  lendo = false,
}) => {
  // UseState para saber se o contato foi selecionado ou não
  const [corSelecionado, setCorSelecionado] = useState("transparent");

  // UseEffect para alterar a cor do contato quando ele for selecionado
  useEffect(() => {
    console.log("chat: ", chat);
    if (idChat == 0) {
      setCorSelecionado("transparent");
    } else {
      if (idChat == chat.id) {
        setCorSelecionado("chat.eu");
      } else {
        if (chat.conversaEncerrada) {
          setCorSelecionado("divider.claro");
        } else {
          setCorSelecionado("transparent");
        }
      }
    }
  }, [idChat]);

  return (
    <>
      <Tooltip title={chat.idProposta.titulo} placement="right">
        <Box
          id="segundo"
          onClick={onClick}
          className="flex justify-evenly items-center rounded-lg border "
          sx={{
            width: "90%",
            minWidth: "195px",
            minHeight: "8%",
            cursor: "pointer",
            backgroundColor: corSelecionado,
            "&:hover": {
              backgroundColor: !chat.conversaEncerrada
                ? "chat.eu"
                : corSelecionado,
            },
          }}
        >
          <Conteudo
            chat={chat}
            lendo={lendo}
            texto={texto}
            setTexto={setTexto}
          />
        </Box>
      </Tooltip>
    </>
  );
};

const Conteudo = ({
  chat = EntitiesObjectService.chat(),
  lendo = false,
}) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  const [usuarioLogado, setUsuario] = useState(UsuarioService.getUserCookies());
  const [nomeContato, setNomeContato] = useState("");

  useEffect(() => {
    retornaNomeContato();
  }, []);

  const retornaNomeContato = () => {
    for (let user of chat.usuariosChat) {
      if (usuarioLogado.usuario.id != user.id) {
        setNomeContato(user.nome);
      }
    }
  };

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (lendo) {
      setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(texto);

    const finalizarLeitura = () => {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    };

    if (lendo && texto !== "") {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else if (!lendo) {
      finalizarLeitura();
    }

    return () => {
      finalizarLeitura();
    };
  }, [texto, lendo]);

  return (
    <>
      {/* Pegando a foto de perfil do usuário */}
      <Box className="flex w-1/5 justify-center items-center">
        <Avatar />
      </Box>
      {/* Informações adicioanais do usuário e da demanda respectiva */}
      <Box className="flex w-4/5 justify-content flex-col">
        <Box className="flex justify-between">
          <Box className="flex w-full justify-between relative">
            <Typography
              className="w-11/12 overflow-hidden text-ellipsis whitespace-nowrap"
              fontSize={FontConfig.medium}
              fontWeight="600"
              onClick={() => {
                lerTexto(nomeContato);
              }}
            >
              {nomeContato}
            </Typography>
            <Typography
              className="rounded-full absolute top-1 right-1 px-2"
              sx={{
                backgroundColor: chat.msgNaoLidas > 0 ? "primary.main" : "",
              }}
              fontSize={FontConfig.verySmall}
              color="white"
            >
              {chat.msgNaoLidas > 0 ? chat.msgNaoLidas : ""}
            </Typography>
          </Box>
          {
            // Verificando se o chat está ativo ou não
            chat.conversaEncerrada && (
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
          onClick={() => {
            lerTexto(texts.contato.ppm);
          }}
        >
          {texts.contato.ppm}: #{chat.idProposta.codigoPPM}
        </Typography>
        <Typography
          fontSize={FontConfig.small}
          fontWeight="400"
          className="overflow-hidden truncate"
          sx={{ width: "100%" }}
          onClick={() => {
            lerTexto(texts.contato.demanda);
          }}
        >
          {texts.contato.demanda}: {chat.idProposta.titulo}
        </Typography>
      </Box>
    </>
  );
};

export default Contato;
