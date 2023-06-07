import React, { useContext, useState } from "react";

import {
  Box,
  Paper,
  Table,
  TableBody,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";

import DeleteIcon from "@mui/icons-material/Delete";

import Feedback from "../../components/Feedback/Feedback";
import Escopo from "../Escopo/Escopo";
import ModalConfirmacao from "../ModalConfirmacao/ModalConfirmacao";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import EscopoService from "../../service/escopoService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

// Componente para mudar o modo de visualização dos escopos (Grid, tabela ou nenhum escopo encontrado)
const EscopoModoVisualizacao = ({
  listaEscopos,
  onEscopoClick,
  nextModoVisualizacao,
  myEscopos,
  handleDelete,
  buscar,
}) => {
  // Se não encontrar nada, vai aparecer componente de nada encontrado
  if (listaEscopos && listaEscopos.length === 0) {
    return <NadaEncontrado />;
  }

  // Se o modo de visualização for GRID, vai aparecer o componente de grid
  if (nextModoVisualizacao == "TABLE")
    return (
      <EscopoGrid
        listaEscopos={listaEscopos}
        onEscopoClick={onEscopoClick}
        myEscopos={myEscopos}
        handleDelete={handleDelete}
      />
    );

  // Se for table, aparecerá o componente de Table
  return (
    <EscopoTable
      listaEscopos={listaEscopos}
      onEscopoClick={onEscopoClick}
      myEscopos={myEscopos}
      handleDelete={handleDelete}
      buscar={buscar}
    />
  );
};

// Componente de escopo em table
const EscopoTable = ({
  listaEscopos = [
    {
      id: 0,
      titulo: "",
      problema: "",
      proposta: null,
      frequencia: "",
      beneficios: null,
      anexos: null,
      ultimaModificacao: null,
    },
  ],
  onEscopoClick,
  buscar,
}) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  /** Context para ler o texto da tela */
  const { lerTexto, lendoTexto } = useContext(SpeechSynthesisContext);

  // Variável para controlar a abertura do modal de confirmação de remoção do escopo
  const [openModalConfirmacao, setOpenModalConfirmacao] = useState(false);

  // Guarda a demanda selecionada para abrir o modal de motivo recusa
  const [escopoSelecionado, setEscopoSelecionado] = useState();

  // useState para aparecer o feedback de escopo deletado
  const [feedbackDeletar, setFeedbackDeletar] = useState(false);

  // Função que captura o click no ícone e abre o modal de confirmação de remoção do escopo
  const onTrashCanClick = (index) => {
    setOpenModalConfirmacao(true);
    setEscopoSelecionado(listaEscopos[index]);
  };

  // Função para deletar o escopo que foi selecionado
  const onDeleteClickEscopo = () => {
    EscopoService.excluirEscopo(escopoSelecionado.id).then((response) => {
      {
        buscar();
      }
    });
    setFeedbackDeletar(true);
  };

  // Função para calcular a porcentagem de preenchimento do escopo
  const calculaPorcentagem = (escopo) => {
    let porcentagem = 0;

    if (escopo.titulo != "" && escopo.titulo != null) {
      porcentagem += 20;
    }
    if (escopo.problema != "" && escopo.problema != null) {
      porcentagem += 20;
    }
    if (escopo.proposta != "" && escopo.proposta != null) {
      porcentagem += 20;
    }
    if (escopo.frequencia != "" && escopo.frequencia != null) {
      porcentagem += 20;
    }

    return porcentagem + "%";
  };

  return (
    <Paper sx={{ width: "100%" }} square>
      <Table className="mb-8 table-fixed" sx={{ width: "100%" }}>
        <TableHead>
          <TableRow sx={{ backgroundColor: "primary.main" }}>
            {/* Porcentagem */}
            <th className="text-white p-3 w-1/10">
              <Typography
                fontSize={FontConfig.big}
                onClick={() => {
                  lerTexto(texts.escopoModoVisualizacao.porcentagem);
                }}
              >
                {texts.escopoModoVisualizacao.porcentagem}
              </Typography>
            </th>
            {/* Título */}
            <th className="text-left text-white p-3 w-3/6">
              <Typography
                fontSize={FontConfig.big}
                onClick={() => {
                  lerTexto(texts.escopoModoVisualizacao.titulo);
                }}
              >
                {texts.escopoModoVisualizacao.titulo}
              </Typography>
            </th>
            {/* Excluir */}
            <th className="text-white p-3 w-1/12">
              <Typography
                fontSize={FontConfig.big}
                onClick={() => {
                  lerTexto(texts.escopoModoVisualizacao.excluir);
                }}
              >
                {texts.escopoModoVisualizacao.excluir}
              </Typography>
            </th>
          </TableRow>
        </TableHead>
        <TableBody>
          {listaEscopos.map((row, index) => (
            <TableRow
              className="cursor-pointer tabela-linha-demanda"
              hover
              key={index}
              sx={{
                "&:last-child td, &:last-child th": { border: 0 },
              }}
              onClick={() => {
                onEscopoClick(row);
              }}
            >
              <td className="text-center p-3" title={row.id}>
                <Typography
                  id="quarto"
                  className="truncate"
                  fontSize={FontConfig.medium}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.stopPropagation();
                      lerTexto(calculaPorcentagem(row));
                    }
                  }}
                >
                  {calculaPorcentagem(row)}
                </Typography>
              </td>
              <td className="text-left p-3" title={row.titulo}>
                <Typography
                  className="truncate"
                  fontSize={FontConfig.medium}
                  onClick={(e) => {
                    if (lendoTexto) {
                      e.stopPropagation();
                      lerTexto(row.titulo);
                    }
                  }}
                >
                  {row.titulo}
                </Typography>
              </td>
              <td
                className="text-center p-3"
                title={texts.escopoModoVisualizacao.excluir}
              >
                <DeleteIcon
                  id="terceiro"
                  className="delay-120 hover:scale-110 duration-300"
                  color="primary"
                  onClick={(e) => {
                    e.stopPropagation();
                    onTrashCanClick(index);
                  }}
                />
              </td>
            </TableRow>
          ))}
        </TableBody>
      </Table>

      {/* Abrindo modal de confirmação de deletar escopo */}
      <ModalConfirmacao
        textoModal={"descartarRascunho"}
        onConfirmClick={onDeleteClickEscopo}
        onCancelClick={() => {
          setOpenModalConfirmacao(false);
        }}
        textoBotao={"sim"}
        open={openModalConfirmacao}
        setOpen={setOpenModalConfirmacao}
      />

      {/* Feedback de escopo deletado com sucesso */}
      <Feedback
        open={feedbackDeletar}
        handleClose={() => {
          setFeedbackDeletar(false);
        }}
        status={"sucesso"}
        mensagem={texts.escopos.escopoDeletadoComSucesso}
      />
    </Paper>
  );
};

// Componente para exibir as demanda em forma de grid
const EscopoGrid = ({ listaEscopos, onEscopoClick, handleDelete }) => {
  return (
    <Box
      sx={{
        display: "grid",
        gap: "1rem",
        gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))",
      }}
    >
      {listaEscopos?.map((escopo, index) => (
        <Escopo
          key={index}
          escopo={escopo}
          index={index}
          onclick={() => {
            onEscopoClick(escopo);
          }}
          handleDelete={handleDelete}
        />
      ))}
    </Box>
  );
};

// Componente para exibir nada encontrado
const NadaEncontrado = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (props.lendoTexto) {
      const synthesis = window.speechSynthesis;
      const utterance = new SpeechSynthesisUtterance(escrita);

      const finalizarLeitura = () => {
        if ("speechSynthesis" in window) {
          synthesis.cancel();
        }
      };

      if (props.lendoTexto && escrita !== "") {
        if ("speechSynthesis" in window) {
          synthesis.speak(utterance);
        }
      } else {
        finalizarLeitura();
      }

      return () => {
        finalizarLeitura();
      };
    }
  };

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
        height: "100%",
        marginTop: "2rem",
      }}
    >
      <Typography
        fontSize={FontConfig.big}
        sx={{ color: "text.secondary", mb: 1 }}
        onClick={() => {
          lerTexto(texts.demandaModoVisualizacao.nadaEncontrado);
        }}
      >
        {texts.demandaModoVisualizacao.nadaEncontrado}
      </Typography>
      <Typography
        fontSize={FontConfig.medium}
        sx={{ color: "text.secondary", mb: 1 }}
        onClick={() => {
          lerTexto(texts.demandaModoVisualizacao.tenteNovamenteMaisTarde);
        }}
      >
        {texts.demandaModoVisualizacao.tenteNovamenteMaisTarde}
      </Typography>
    </Box>
  );
};

export default EscopoModoVisualizacao;
