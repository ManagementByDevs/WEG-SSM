import React, { useContext, useState, useEffect } from "react";

import { Box, Typography, Avatar, Tooltip } from "@mui/material";

import DoneAllIcon from "@mui/icons-material/DoneAll";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";

import FontContext from "../../service/FontContext";

import dateService from "../../service/dateService";
import UsuarioService from "../../service/usuarioService";
import EntitiesObjectService from "../../service/entitiesObjectService";


// Componnete para utilizar durante uma mensagem no chat
const Mensagem = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  const [user, setUser] = useState(UsuarioService.getUserCookies());

  // Função para baixar um anexo
  const downloadAnexo = (anexo = EntitiesObjectService.anexo()) => {
    const file = anexo;
    let blob = new Blob([base64ToArrayBuffer(file.dados)]);
    let fileName = `${file.nome}`;

    if (navigator.msSaveBlob) {
      navigator.msSaveBlob(blob, fileName);
    } else {
      const link = document.createElement("a");
      if (link.download !== undefined) {
        const url = URL.createObjectURL(blob);
        link.setAttribute("href", url);
        link.setAttribute("download", fileName);
        link.style.visibility = "hidden";
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(url);
      }
    }
  };

   // Função para transformar uma string em base64 para um ArrayBuffer
   const base64ToArrayBuffer = (base64) => {
    const binaryString = window.atob(base64);
    const bytes = new Uint8Array(binaryString.length);
    return bytes.map((byte, i) => binaryString.charCodeAt(i));
  };

  return (
    <>
      {props.mensagem.usuario.id == user.usuario.id ? (
        <Box className="flex w-full justify-end">
          <Box
            className="flex items-center border py-2 px-4 my-2"
            sx={{
              backgroundColor: "chat.eu",
              borderRadius: "10px 10px 0px 10px",
              maxWidth: "70%",
              marginRight: "4%",
            }}
          >
            <Box className="flex flex-col w-full">
              <Box className="flex">
                {props.mensagem.texto != "" ? (
                  <Typography fontSize={FontConfig.default} fontWeight="400">
                    {props.mensagem.texto.split("\n").map((line, index) => (
                      <React.Fragment key={index}>
                        {line}
                        <br />
                      </React.Fragment>
                    ))}
                  </Typography>
                ) : (
                  <Tooltip title={props.mensagem.anexo.nome}>
                    <Box
                      className="px-5 pb-2 mb-2 border rounded cursor-pointer"
                      sx={{ backgroundColor: "chat.outro" }}
                      onClick={() => downloadAnexo(props.mensagem.anexo)}
                    >
                      <FolderOutlinedIcon
                        sx={{ fontSize: "100px", color: "chat.eu" }}
                      />
                      <Typography sx={{ fontWeight: 600 }}>
                        {props.mensagem.anexo.nome}
                      </Typography>
                    </Box>
                  </Tooltip>
                )}
              </Box>

              <Box className="flex justify-end items-end w-full">
                <Typography
                  fontSize={FontConfig.small}
                  fontWeight="600"
                  sx={{ color: "text.secondary", marginRight: "0.2rem" }}
                >
                  {`${dateService.getHorasFormatado(props.mensagem.data)}`}
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
              backgroundColor: "chat.outro",
              borderRadius: "10px 10px 10px 0px",
              maxWidth: "70%",
              marginLeft: "5%",
            }}
          >
            <Box className="flex flex-col w-full">
              <Box className="flex">
                {props.mensagem.texto != "" ? (
                  <Typography fontSize={FontConfig.default} fontWeight="400">
                    {props.mensagem.texto.split("\n").map((line, index) => (
                      <React.Fragment key={index}>
                        {line}
                        <br />
                      </React.Fragment>
                    ))}
                  </Typography>
                ) : (
                  <Tooltip title={props.mensagem.anexo.nome}>
                    <Box
                      className="px-5 pb-2 mb-2 border rounded cursor-pointer"
                      sx={{ backgroundColor: "background.paper" }}
                      onClick={() => downloadAnexo(props.mensagem.anexo)}
                    >
                      <FolderOutlinedIcon
                        sx={{ fontSize: "100px", color: "chat.eu" }}
                      />
                      <Typography sx={{ fontWeight: 600 }}>
                        {props.mensagem.anexo.nome}
                      </Typography>
                    </Box>
                  </Tooltip>
                )}
              </Box>

              <Box className="flex justify-end w-full">
                <Typography
                  fontSize={FontConfig.small}
                  fontWeight="600"
                  sx={{ color: "text.secondary" }}
                >
                  {`${dateService.getHorasFormatado(props.mensagem.data)}`}
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
