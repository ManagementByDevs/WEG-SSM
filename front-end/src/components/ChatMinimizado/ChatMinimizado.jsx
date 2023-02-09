import React, { useContext, useState, useEffect } from "react";
import {
  Box,
  Button,
  IconButton,
  Tooltip,
  Divider,
  Typography,
} from "@mui/material";

import AttachFileOutlinedIcon from "@mui/icons-material/AttachFileOutlined";
import SendOutlinedIcon from "@mui/icons-material/SendOutlined";
import CloseOutlinedIcon from "@mui/icons-material/CloseOutlined";
import OpenInNewOutlinedIcon from "@mui/icons-material/OpenInNewOutlined";
import RemoveOutlinedIcon from "@mui/icons-material/RemoveOutlined";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";

import Mensagem from "../../components/Mensagem/Mensagem";

import { keyframes } from "@emotion/react";

import FontConfig from "../../service/FontConfig";
import FontContext from "../../service/FontContext";

const ChatMinimizado = (props) => {
  // Contexto para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  const [minimizarChatMinimizado, setMinimizarChatMinimizado] = useState(false);
  const [tamanhoChatMinimizado, setTamanhoChatMinimizado] = useState("24rem");

  const [usuarios, setUsuarios] = useState([
    {
      foto: "",
      nome: "Thiago",
      cargo: "Gerente",
      demanda: "Colocar pote de bala nas mesas de TI",
      codigoDemanda: "#123456",
      mensagens: [
        {
          texto: "Olá, tudo bem?",
          data: "10/10/2021",
          hora: "10:00",
          remetente: "Thiago",
        },
        {
          texto:
            "Tudo sim, e você? Vou testar um texto grande aqui, então vou escrever muito aqui para ter um exemplo de como é um texto grande.",
          data: "10/10/2021",
          hora: "10:01",
          remetente: "Eu",
        },
        {
          texto: "Tudo bem",
          data: "10/10/2021",
          hora: "10:02",
          remetente: "Thiago",
        },
      ],
    },
  ]);

  const [indexUsuario, setIndexUsuario] = useState(0);

  const sumir = keyframes({
    from: { height: "24rem" },
    to: { height: "2.88rem" },
  });
  const aparecer = keyframes({
    from: { height: "2.88rem" },
    to: { height: "24rem" },
  });

  return (
    <Box
      className="absolute bottom-0 right-24 w-72 h-96"
      sx={{
        backgroundColor: "component.main",
        borderRadius: "10px 10px 0 0",
        animation: `${tamanhoChatMinimizado} 1s forwards`,
      }}
    >
      {/* Container para o cabeçalho do chat */}
      <Box
        className="w-full h-11 pl-4 pr-2 py-1 items-center flex justify-between"
        sx={{
          backgroundColor: "primary.main",
          borderRadius: "10px 10px 0 0",
          borderBottom: "4px #FFFF solid",
        }}
      >
        {/* Nome do chat */}
        <Typography color="#FFF" fontSize={FontConfig.default} fontWeight="600">
          {usuarios[indexUsuario].nome}
        </Typography>
        {/* Container para os ícones do cabeçalho */}
        <Box>
          {/* Icone para minimizar ou maximizar o chat */}
          {!minimizarChatMinimizado ? (
            // Icone para minimizar o chat
            <Tooltip title="Minimizar">
              <RemoveOutlinedIcon
                className="mr-2 cursor-pointer delay-120 hover:scale-110 duration-300"
                sx={{ color: "#FFF" }}
                onClick={() => {
                  setTamanhoChatMinimizado(sumir);
                  setMinimizarChatMinimizado(true);
                }}
              />
            </Tooltip>
          ) : (
            // Icone para maximizar o chat
            <Tooltip title="Maximizar">
              <AddOutlinedIcon
                className="mr-2 cursor-pointer delay-120 hover:scale-110 duration-300"
                sx={{ color: "#FFF" }}
                onClick={() => {
                  setTamanhoChatMinimizado(aparecer);
                  setTimeout(() => {
                    setMinimizarChatMinimizado(false);
                  }, 780);
                }}
              />
            </Tooltip>
          )}
          {/* Icone para fazer dar tela cheia */}
          <Tooltip title="Tela cheia">
            <OpenInNewOutlinedIcon
              className="mr-2 cursor-pointer delay-120 hover:scale-110 duration-300"
              sx={{ fontSize: "20px", color: "#FFF" }}
            />
          </Tooltip>
          {/* Icone para fechar o chat */}
          <Tooltip title="Fechar chat">
            <CloseOutlinedIcon
              className="cursor-pointer delay-120 hover:scale-110 duration-300"
              sx={{ fontSize: "25px", color: "#FFF" }}
              onClick={() => {
                props.setFecharChatMinimizado(true);
              }}
            />
          </Tooltip>
        </Box>
      </Box>
      {!minimizarChatMinimizado ? (
        <>
          {/*Container para o chat  */}
          <Box className="p-2 px-4" sx={{ height: "18rem", overflow: "auto" }}>
            {/* Mensagens do chat */}
            {usuarios[indexUsuario].mensagens.map((mensagem, index) => {
              return (
                <Mensagem
                  key={index}
                  mensagem={mensagem}
                  index={index}
                  usuario={usuarios[indexUsuario]}
                />
              );
            })}
          </Box>
          {/* Container para o input de mensagem */}
          <Box className="flex justify-center mt-2">
            <Box
              className="flex justify-between items-center border pl-3 pr-2 py-1 rounded"
              sx={{ backgroundColor: "input.main", width: "85%" }}
            >
              {/* Input da mensagem */}
              <Box
                className="w-full"
                component="input"
                sx={{
                  backgroundColor: "input.main",
                  outline: "none",
                  color: "text.primary",
                  fontSize: FontConfig.default,
                }}
                placeholder="Escreva sua mensagem..."
              />

              {/* Container para os ícones */}
              <Box className="flex">
                {/* Ícone de Anexo */}
                <Box className="flex gap-2 delay-120 hover:scale-110 duration-300">
                  <Tooltip title="Enviar Anexo">
                    <AttachFileOutlinedIcon
                      sx={{
                        color: "primary.main",
                        cursor: "pointer",
                        fontSize: "22px",
                      }}
                    />
                  </Tooltip>
                </Box>
                {/* divisor */}
                <Divider
                  orientation="vertical"
                  sx={{
                    borderColor: "primary.main",
                  }}
                />
                {/* Ícone de Enviar */}
                <Box className="flex gap-2 delay-120 hover:scale-110 duration-300">
                  <Tooltip title="Enviar mensagem">
                    <SendOutlinedIcon
                      sx={{
                        color: "primary.main",
                        cursor: "pointer",
                        fontSize: "22px",
                      }}
                    />
                  </Tooltip>
                </Box>
              </Box>
            </Box>
          </Box>
        </>
      ) : null}
    </Box>
  );
};

export default ChatMinimizado;
