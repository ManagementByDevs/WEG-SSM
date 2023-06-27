import React, { useContext } from "react";
import { List, Box, Typography } from "@mui/material";

import TextLanguageContext from "../../../service/TextLanguageContext";
import FontContext from "../../../service/FontContext";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";

import ItemOrdenacao from "./ItemOrdenacao/itemOrdenacao";

export default function TemporaryDrawer(props) {
  // Context para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  const opcoesFiltrar = [
    {
      id: 1,
      tipo: texts.modalOrdenacao.titulo,
    },
    {
      id: 2,
      tipo: texts.modalOrdenacao.numeroSequencial,
    },
    {
      id: 3,
      tipo: texts.modalOrdenacao.score,
    },
    {
      id: 4,
      tipo: texts.modalOrdenacao.dataReuniao,
    },
    {
      id: 5,
      tipo: texts.modalOrdenacao.dataDeCriacao,
    },
  ];

  return (
    <List
      sx={{
        width: "18rem",
        bgcolor: "background.paper",
        padding: "0",
      }}
      component="nav"
      aria-labelledby="nested-list-subheader"
    >
      <Box
        className="w-full flex items-center justify-center"
        sx={{ backgroundColor: "primary.main", height: "4.5rem" }}
      >
        <Typography
          fontSize={FontConfig.smallTitle}
          sx={{ color: "text.white", fontWeight: 600 }}
          onClick={() => {
            lerTexto(texts.modalOrdenacao.ordenar);
          }}
        >
          {texts.modalOrdenacao.ordenar}
        </Typography>
      </Box>
      <Box className="h-full">
        {opcoesFiltrar.map((opcao, index) => (
          <>
            {props.valorAba != 5 && props.valorAba != 6 && opcao.id != 4 ? (
              <ItemOrdenacao
                opcao={opcao}
                key={index}
                ordenacaoTitulo={props.ordenacaoTitulo}
                setOrdenacaoTitulo={props.setOrdenacaoTitulo}
                ordenacaoScore={props.ordenacaoScore}
                setOrdenacaoScore={props.setOrdenacaoScore}
                ordenacaoDate={props.ordenacaoDate}
                setOrdenacaoDate={props.setOrdenacaoDate}
                valorAba={props.valorAba}
              />
            ) : (
              (props.valorAba == 5 || props.valorAba == 6) &&
              opcao.id != 1 && (
                <ItemOrdenacao
                  opcao={opcao}
                  key={index}
                  ordenacaoTitulo={props.ordenacaoTitulo}
                  setOrdenacaoTitulo={props.setOrdenacaoTitulo}
                  ordenacaoScore={props.ordenacaoScore}
                  setOrdenacaoScore={props.setOrdenacaoScore}
                  ordenacaoDate={props.ordenacaoDate}
                  setOrdenacaoDate={props.setOrdenacaoDate}
                  valorAba={props.valorAba}
                />
              )
            )}
          </>
        ))}
      </Box>
    </List>
  );
}
