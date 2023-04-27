import React, { useContext } from "react";

import { Box, Typography, Avatar } from "@mui/material";

import DoneAllIcon from "@mui/icons-material/DoneAll";

import FontContext from "../../service/FontContext";

// Componnete para utilizar durante uma mensagem no chat
const Mensagem = (props) => {

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  return (
    <>
      {props.usuario.mensagens[props.index].remetente === "Eu" ? (
        <Box className="flex w-full justify-end">
          <Box
            className="flex items-center border py-2 px-4 my-2"
            sx={{
              backgroundColor: "chat.eu", borderRadius: "10px 10px 0px 10px", maxWidth: "70%",
            }}
          >
            <Box className="flex flex-col w-full">
              <Box className="flex">
                <Typography fontSize={FontConfig.default} fontWeight="400">
                  {props.mensagem.texto}
                </Typography>
              </Box>

              <Box className="flex justify-end items-end w-full">
                <Typography
                  fontSize={FontConfig.small}
                  fontWeight="600"
                  sx={{ color: "text.secondary", marginRight: "0.2rem" }}
                >
                  {props.mensagem.data}
                </Typography>
                <DoneAllIcon fontSize="small" sx={{ color: "#FFFF" }} />
              </Box>
            </Box>
          </Box>
        </Box>
      ) : (
        <Box className="flex w-full justify-start">
          <Box
            className="flex items-center rounded-lg border py-2 px-4 my-2"
            sx={{
              backgroundColor: "chat.outro", borderRadius: "10px 10px 10px 0px", maxWidth: "70%",
            }}
          >
            <Box className="flex flex-col w-full">
              <Box className="flex">
                <Typography fontSize={FontConfig.default} fontWeight="400">
                  {props.usuario.mensagens[props.index].texto}
                </Typography>
              </Box>

              <Box className="flex justify-end w-full">
                <Typography
                  fontSize={FontConfig.small}
                  fontWeight="600"
                  sx={{ color: "text.secondary" }}
                >
                  {props.usuario.mensagens[props.index].hora}
                </Typography>
              </Box>
            </Box>
          </Box>
        </Box>
      )}
    </>
  );
};

export default Mensagem;