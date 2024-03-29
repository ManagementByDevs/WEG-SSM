import React, { useContext, useState, useEffect } from "react";

import { Box, Typography, Avatar, Tooltip } from "@mui/material";

import CommentsDisabledOutlinedIcon from "@mui/icons-material/CommentsDisabledOutlined";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

import UsuarioService from "../../service/usuarioService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

/** Componente contato utilizado para representar os contatos do chat */
const Contato = ({
  onClick = () => {},
  idChat = 0,
  chat = EntitiesObjectService.chat(),
}) => {
  /** UseState para saber se o contato foi selecionado ou não */
  const [corSelecionado, setCorSelecionado] = useState("transparent");

  /** Demanda do chat */
  const demanda = chat.idProposta ? chat.idProposta : chat.idDemanda;

  /** UseEffect para alterar a cor do contato quando ele for selecionado */
  useEffect(() => {
    if (idChat == 0) {
      if (chat.conversaEncerrada) {
        setCorSelecionado("divider.claro");
      } else {
        setCorSelecionado("transparent");
      }
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
      <Tooltip title={demanda.titulo} placement="right">
        <Box
          id="segundo"
          onClick={onClick}
          className="flex justify-evenly items-center rounded-lg border p-3"
          sx={{
            width: "90%",
            minWidth: "100px",
            minHeight: "4rem",
            cursor: "pointer",
            backgroundColor: corSelecionado,
            "&:hover": {
              backgroundColor: !chat.conversaEncerrada
                ? "chat.eu"
                : corSelecionado,
            },
          }}
        >
          <Conteudo chat={chat} />
        </Box>
      </Tooltip>
    </>
  );
};

/** Informações do contato */
const Conteudo = ({ chat = EntitiesObjectService.chat() }) => {
  /** Contexto para trocar a linguagem */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  /** Variável para pegar o usuário logado */
  const [usuarioLogado, setUsuario] = useState(UsuarioService.getUserCookies());

  /** Variável para buscar o nome do contato */
  const [nomeContato, setNomeContato] = useState("");

  /** Demanda do chat */
  const demanda = chat.idProposta ? chat.idProposta : chat.idDemanda;

  /** useEffect para chamar a função de buscar o nome do contato */
  useEffect(() => {
    retornaNomeContato();
  }, []);

  /** Função para retornar o nome do contato */
  const retornaNomeContato = () => {
    for (let user of chat.usuariosChat) {
      if (usuarioLogado.usuario.id != user.id) {
        setNomeContato(user.nome);
      }
    }
  };

  return (
    <>
      {/* Pegando a foto de perfil do usuário */}
      <Box className="flex w-1/5 justify-center items-center">
        <Avatar />
      </Box>
      {/* Informações adicioanais do usuário e da demanda respectiva */}
      <Box className="ml-1 flex w-4/5 justify-content flex-col">
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
          {demanda.codigoPPM
            ? texts.contato.ppm + ": #" + demanda.codigoPPM
            : "ID: #" + demanda.id}
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
          {texts.contato.demanda}: {demanda.titulo}
        </Typography>
      </Box>
    </>
  );
};

export default Contato;
