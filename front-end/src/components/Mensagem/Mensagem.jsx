import React, { useContext, useState } from "react";

import { Box, Typography, Tooltip } from "@mui/material";

import DoneIcon from "@mui/icons-material/Done";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";

import FontContext from "../../service/FontContext";
import dateService from "../../service/dateService";
import UsuarioService from "../../service/usuarioService";
import EntitiesObjectService from "../../service/entitiesObjectService";

/** Componnete para utilizar durante uma mensagem no chat */
const Mensagem = ({ mensagem = EntitiesObjectService.mensagem() }) => {
  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Variável para armazenar as informações do usuário */
  const [user, setUser] = useState(UsuarioService.getUserCookies());

  /** Função para baixar um anexo */
  const downloadAnexo = (anexo = EntitiesObjectService.anexo()) => {
    const file = anexo;
    let blob;
    let fileName;

    if (anexo instanceof File) {
      blob = file;
      fileName = file.name;
    } else {
      blob = new Blob([base64ToArrayBuffer(file.dados)]);
      fileName = `${file.nome}`;
    }

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

  /** Função para transformar uma string em base64 para um ArrayBuffer */
  const base64ToArrayBuffer = (base64) => {
    const binaryString = window.atob(base64);
    const bytes = new Uint8Array(binaryString.length);
    return bytes.map((byte, i) => binaryString.charCodeAt(i));
  };

  if (mensagem.usuario.id == user.usuario.id) {
    return (
      <>
        <Box className="flex w-full justify-end">
          <Box
            className="flex items-center border py-2 my-2"
            sx={{
              backgroundColor: "chat.eu",
              borderRadius: "10px 10px 0px 10px",
              maxWidth: "70%",
              marginRight: "4%",
            }}
          >
            <Box className="flex flex-col w-full">
              <Box className="flex pr-2 pl-3">
                {mensagem.texto != "" ? (
                  <Typography fontSize={FontConfig.default} fontWeight="400">
                    {mensagem.texto.split("\n").map((line, index) => (
                      <React.Fragment key={index}>
                        {line}
                        <br />
                      </React.Fragment>
                    ))}
                  </Typography>
                ) : (
                  <Tooltip title={mensagem.anexo.nome}>
                    <Box
                      className="px-5 pb-2 mb-2 border rounded cursor-pointer flex flex-col justify-center items-center "
                      sx={{ backgroundColor: "chat.outro" }}
                      onClick={() => downloadAnexo(mensagem.anexo)}
                    >
                      <FolderOutlinedIcon
                        sx={{ fontSize: "100px", color: "chat.eu" }}
                      />
                      <Typography sx={{ fontWeight: 600 }}>
                        {mensagem.anexo.nome}
                      </Typography>
                    </Box>
                  </Tooltip>
                )}
              </Box>

              <Box className="flex justify-end items-end w-full px-2">
                <Typography
                  fontSize={FontConfig.small}
                  fontWeight="600"
                  sx={{ color: "text.secondary", marginRight: "0.2rem" }}
                >
                  {dateService.getHorasFormatado(mensagem.data)}
                </Typography>
                <DoneIcon
                  fontSize="small"
                  sx={{ color: mensagem.visto ? "#00579D" : "#FFFF" }}
                />
              </Box>
            </Box>
          </Box>
        </Box>
      </>
    );
  }

  return (
    <>
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
              {mensagem.texto != "" ? (
                <Typography fontSize={FontConfig.default} fontWeight="400">
                  {mensagem.texto.split("\n").map((line, index) => (
                    <React.Fragment key={index}>
                      {line}
                      <br />
                    </React.Fragment>
                  ))}
                </Typography>
              ) : (
                <Tooltip title={mensagem.anexo.nome}>
                  <Box
                    className="px-4 py-2.5 mb-1 flex items-center gap-2 border rounded cursor-pointer"
                    sx={{
                      backgroundColor: "background.paper",
                    }}
                    onClick={() => downloadAnexo(mensagem.anexo)}
                  >
                    <FolderOutlinedIcon
                      sx={{ fontSize: "32px", color: "chat.eu" }}
                    />
                    <Typography sx={{ fontWeight: 600 }}>
                      {mensagem.anexo.nome}
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
                {dateService.getHorasFormatado(mensagem.data)}
              </Typography>
            </Box>
          </Box>
        </Box>
      </Box>
    </>
  );
};

export default Mensagem;
