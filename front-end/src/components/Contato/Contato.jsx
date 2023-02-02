import { Box, Typography, Avatar } from "@mui/material";
import React, { useContext, useState } from "react";
import FontConfig from "../../service/FontConfig";

import FontContext from "../../service/FontContext";

const Contato = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  const [corFundoContato, setCorFundoContato] = useState();

  const corSelecionado = () => {
    props.onClick();
    if (props.usuarioAtual != props.index) {
      setCorFundoContato("chat.eu");
    } else {
      setCorFundoContato("transparent");
    }
  };

  return (
    <Box
      onClick={corSelecionado}
      className="flex justify-evenly items-center rounded-lg border delay-120 hover:scale-105 duration-300"
      sx={{
        width: "90%",
        minWidth: "195px",
        minHeight: "8%",
        cursor: "pointer",
        backgroundColor:corFundoContato,
        "&:hover": {
          backgroundColor: "chat.eu",
        },
      }}
      title={props.usuario.demanda}
    >
      <Box className="flex justify-content items-center">
        <Avatar sx={{ width: "3rem", height: "3rem" }} src={props.usuario.foto}/>
      </Box>
      <Box className="flex justify-content flex-col" sx={{ width: "70%" }}>
        <Box className="flex justify-between">
          <Typography fontSize={FontConfig.medium} fontWeight="600">
            {props.usuario.nome}
          </Typography>
          <Typography
            fontSize={FontConfig.default}
            fontWeight="600"
            sx={{ color: "primary.main" }}
          >
            {props.usuario.codigoDemanda}
          </Typography>
        </Box>
        <Typography
          fontSize={FontConfig.small}
          fontWeight="400"
          className="overflow-hidden truncate"
          sx={{ width: "100%" }}
        >
          Demanda: {props.usuario.demanda}
        </Typography>
      </Box>
    </Box>
  );
};

export default Contato;
