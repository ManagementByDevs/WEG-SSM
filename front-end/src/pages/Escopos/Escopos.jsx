import React, { useState, useEffect } from "react";

import { Box, Button } from "@mui/material";

import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import SwapVertIcon from "@mui/icons-material/SwapVert";

import Caminho from "../../components/Caminho/Caminho";
import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Escopo from "../../components/Escopo/Escopo";
import ModalOrdenacao from "../../components/ModalOrdenacao/ModalOrdenacao";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";

import FontConfig from "../../service/FontConfig";
import EscopoService from "../../service/escopoService";

const Escopos = () => {
  const [escopos, setEscopos] = useState(null);

  useEffect(() => {
    if (!escopos) {
      buscarEscopos();
    }
  }, []);

  const buscarEscopos = () => {
    EscopoService.buscarPorUsuario(parseInt(localStorage.getItem("usuarioId"))).then((response) => {
      let listaEscopos = [];
      for (let escopo of response) {
        listaEscopos.push({ titulo: escopo.titulo, problema: escopo.problema, proposta: escopo.proposta, frequencia: escopo.frequencia, beneficios: escopo.beneficios, anexos: escopo.anexo, ultimaModificacao: escopo.ultimaModificacao, porcentagem: calculaPorcentagem(escopo) });
      }
      setEscopos([...listaEscopos]);
    })
  };

  const calculaPorcentagem = (escopo) => {
    let porcentagem = 0;
    if (escopo.titulo != "" && escopo.titulo != null) {
      porcentagem += 25;
    }
    if (escopo.problema != "" && escopo.problema != null) {
      porcentagem += 25;
    }
    if (escopo.proposta != "" && escopo.proposta != null) {
      porcentagem += 25;
    }
    if (escopo.frequencia != "" && escopo.frequencia != null) {
      porcentagem += 25;
    }
    return (porcentagem) + "%";
  }

  const [abrirOrdenacao, setOpenOrdenacao] = useState(false);
  const [ordenacao, setOrdenacao] = useState("sort=id,asc&");
  const [openModalConfirmacao, setOpenModalConfirmacao] = useState(false);

  const openEscopo = (id) => {
    console.log(id)
  }

  const abrirModalOrdenacao = () => {
    setOpenOrdenacao(true);
  };

  const onDeleteClickEscopo = () => {
    // delete escopo
  };

  const onTrashCanClick = (index) => {
    setOpenModalConfirmacao(true);
    console.log(index)
  }

  return (
    <FundoComHeader>
      {/* Modal de confirmação de exclusão de escopo */}
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
      <Box className="p-2">
        <Caminho />
        {/* Div pegando width inteira para fazer o espaçamento das bordas */}
        <Box className="flex justify-center w-full">
          {/* Container conteudo */}
          <Box className="w-11/12 mt-10">
            {/* Container para o input e botão de filtrar */}
            <Box className="flex gap-4 w-2/4">
              {/* Input de pesquisa */}
              <Box
                className="flex justify-between border px-3 py-1"
                sx={{ backgroundColor: "input.main", width: "50%" }}
              >
                {/* Input de pesquisa */}
                <Box
                  className="w-full"
                  component="input"
                  sx={{
                    backgroundColor: "input.main",
                    outline: "none",
                    color: "text.primary",
                    fontSize: FontConfig.medium,
                  }}
                  placeholder="Pesquisar por título..."
                />

                {/* Container para os ícones */}
                <Box className="flex gap-2">
                  {/* Ícone de pesquisa */}
                  <SearchOutlinedIcon sx={{ color: "text.secondary" }} />

                  {/* Ícone de ordenação */}
                  <SwapVertIcon
                    onClick={abrirModalOrdenacao}
                    className="cursor-pointer"
                    sx={{ color: "text.secondary" }}
                  />

                  {/* Modal de ordenação */}
                  {abrirOrdenacao && (
                    <ModalOrdenacao
                      ordenacao={ordenacao}
                      setOrdenacao={setOrdenacao}
                      open={abrirOrdenacao}
                      setOpen={setOpenOrdenacao}
                    />
                  )}
                </Box>
              </Box>
            </Box>
            <Box
              className="mt-6 grid gap-4"
              sx={{
                gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))",
              }}
            >
              {escopos?.map((escopo, index) => {
                return <Escopo key={index} escopo={escopo} index={index} onclick={openEscopo} handleDelete={onTrashCanClick} />;
              })}
            </Box>
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default Escopos;
