import React, { useState, useContext } from "react";
import { Box, Avatar, Typography, Divider } from "@mui/material";

import logoWeg from "../../assets/logo-weg.png";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import Contato from "../../components/Contato/Contato";

import FontConfig from "../../service/FontConfig";

import MoreVertOutlinedIcon from "@mui/icons-material/MoreVertOutlined";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import AttachFileOutlinedIcon from "@mui/icons-material/AttachFileOutlined";
import SendOutlinedIcon from "@mui/icons-material/SendOutlined";

const Chat = () => {
  const [chatAberto, setChatAberto] = useState(false);

  function abrirChat() {
    setChatAberto(true);
  }

  return (
    <FundoComHeader>
      <Box className="p-2">
        <Caminho />
        <Box className="w-full flex justify-center items-center">
          <Box
            className="flex justify-evenly items-center rounded border mt-4"
            sx={{ width: "100rem", height: "50rem" }}
          >
            <Box
              className="flex items-center rounded border flex-col gap-3 overflow-y-auto overflow-x-hidden"
              sx={{ width: "20%", height: "95%" }}
            >
              <Box
                className="flex border px-3 py-1 m-4 rounded-lg"
                sx={{
                  backgroundColor: "input.main",
                  width: "90%",
                  height: "5%",
                }}
              >
                <Box
                  className="w-full"
                  component="input"
                  sx={{
                    backgroundColor: "input.main",
                    outline: "none",
                    color: "text.primary",
                    fontSize: FontConfig.medium,
                  }}
                  placeholder="Pesquisar por nome..."
                />
                <Box className="flex gap-2">
                  <SearchOutlinedIcon sx={{ color: "text.secondary" }} />
                </Box>
              </Box>
              <Contato onClick={abrirChat} />
              <Contato />
              <Contato />
              <Contato />
            </Box>
            {!chatAberto ? (
              <Box
                className="flex flex-col items-center justify-center rounded border"
                sx={{ width: "75%", height: "95%", cursor: "default" }}
              >
                <img src={logoWeg} alt="chat" />
                <Typography
                  fontSize={FontConfig.title}
                  color={"text.secondary"}
                  sx={{ fontWeight: "600" }}
                >
                  Selecione alguma conversa
                </Typography>
              </Box>
            ) : (
              <Box
                className="flex flex-col items-center justify-between rounded border"
                sx={{ width: "75%", height: "95%" }}
              >
                <Box
                  className="flex justify-between items-center w-full"
                  sx={{ backgroundColor: "primary.main", height: "10%" }}
                >
                  <Box className="flex items-center">
                    <Avatar
                      className="ml-7"
                      sx={{ width: "3.5rem", height: "3.5rem" }}
                    />
                    <Box
                      className="flex flex-col ml-3"
                      sx={{ cursor: "default", color: "#FFFF" }}
                    >
                      <Typography
                        className="ml-2"
                        fontSize={FontConfig.veryBig}
                        fontWeight="600"
                      >
                        Nome
                      </Typography>
                      <Typography fontSize={FontConfig.small}>Cargo</Typography>
                    </Box>
                  </Box>
                  <Box
                    className="rounded-3xl p-0.5 mr-7 delay-120 hover:scale-110 duration-300"
                    sx={{ cursor: "pointer", color: "#FFFF" }}
                  >
                    <MoreVertOutlinedIcon fontSize="large" />
                  </Box>
                </Box>
                <Box
                  className="flex flex-col mt-4"
                  sx={{ width: "95%", height: "85%" }}
                ></Box>
                <Box
                  className="flex border px-3 py-1 m-4 rounded items-center"
                  sx={{
                    backgroundColor: "input.main",
                    width: "90%",
                    height: "6.5%",
                  }}
                >
                  <Box
                    className="w-full"
                    component="input"
                    sx={{
                      backgroundColor: "input.main",
                      outline: "none",
                      color: "text.primary",
                      fontSize: FontConfig.medium,
                    }}
                    placeholder="Escreva sua mensagem..."
                  />
                  <Box className="flex gap-2 delay-120 hover:scale-110 duration-300">
                    <AttachFileOutlinedIcon
                      sx={{ color: "primary.main", cursor: "pointer" }}
                    />
                  </Box>
                  <Divider
                    orientation="vertical"
                    sx={{
                      borderColor: "primary.main",
                      margin: "0 10px 0 10px",
                    }}
                  />
                  <Box className="flex gap-2 delay-120 hover:scale-110 duration-300">
                    <SendOutlinedIcon
                      sx={{ color: "primary.main", cursor: "pointer" }}
                    />
                  </Box>
                </Box>
              </Box>
            )}
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default Chat;
