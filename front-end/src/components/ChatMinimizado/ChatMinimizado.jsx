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

import FontConfig from "../../service/FontConfig";
import FontContext from "../../service/FontContext";

const ChatMinimizado = (props) => {
  // Contexto para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  return (
    <Box
      className="absolute bottom-0 right-24 w-72 h-96"
      sx={{ backgroundColor: "secondary.main", borderRadius: "10px 10px 0 0" }}
    >
      {/* Container para o cabeçalho do chat */}
      <Box
        className="w-full h-11 px-4 py-1 items-center flex justify-between"
        sx={{
          backgroundColor: "primary.main",
          borderRadius: "10px 10px 0 0",
          borderBottom: "4px #FFFF solid",
        }}
      >
        <Typography color="#FFF" fontSize={FontConfig.default} fontWeight="600">
          NOME DO CHAT
        </Typography>
        <Box>
          <RemoveOutlinedIcon />
          <OpenInNewOutlinedIcon />
          <CloseOutlinedIcon />
        </Box>
      </Box>
      {/* Container para o chat */}
      <Box sx={{ height: "18.5rem" }}></Box>
      {/* Container para o input de mensagem */}
      <Box className="flex justify-center">
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
    </Box>
  );
};

export default ChatMinimizado;
