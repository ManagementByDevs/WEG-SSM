import React, { useContext, useEffect, useState } from "react";
import {
  FormControl,
  MenuItem,
  Select,
  Pagination,
  Box,
  Typography,
} from "@mui/material";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";

// Componente de paginação dos resultados na página principal
const Paginacao = (props) => {
  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Função ativada quando o tamanho da página é mudada, atualizando a variável recebida no props */
  const mudarTamanho = (event) => {
    props.setTamanho(event.target.value);
  };

  /** Função ativada quando a página é mudada, atualizando a variável recebida no props */
  const mudarPagina = (event, page) => {
    props.setPaginaAtual(page - 1);
  };

  const [textoLeitura, setTextoLeitura] = useState("");

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (props.lendo) {
      setTextoLeitura(escrita);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(textoLeitura);

    const finalizarLeitura = () => {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    };

    if (props.lendo && textoLeitura !== "") {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else {
      finalizarLeitura();
    }

    return () => {
      finalizarLeitura();
    };
  }, [textoLeitura]);

  return (
    <Box className="flex items-end" sx={{ marginBottom: "30px" }}>
      <Typography
        fontSize={FontConfig.medium}
        sx={{ marginRight: "15px" }}
        onClick={() => {
          lerTexto(texts.paginacao.itensPorPagina);
        }}
      >
        {texts.paginacao.itensPorPagina}:
      </Typography>
      <FormControl
        variant="standard"
        sx={{ marginRight: "10px", minWidth: 50 }}
      >
        {/* Select para mudar o tamanho da página */}
        <Select
          labelId="demo-simple-select-standard-label"
          id="demo-simple-select-standard"
          value={props.tamanhoPagina}
          onChange={mudarTamanho}
        >
          <MenuItem value={20}>20</MenuItem>
          <MenuItem value={40}>40</MenuItem>
          <MenuItem value={60}>60</MenuItem>
          <MenuItem value={80}>80</MenuItem>
        </Select>
      </FormControl>
      {/* Paginação padrão do Material UI */}
      <Pagination
        onChange={(e, page) => mudarPagina(e, page)}
        count={props.totalPaginas}
        shape="rounded"
      />
    </Box>
  );
};

export default Paginacao;
