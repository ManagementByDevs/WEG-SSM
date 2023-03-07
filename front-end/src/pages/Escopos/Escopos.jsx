import React, { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { Box, Button, Tooltip } from "@mui/material";

import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import SwapVertIcon from "@mui/icons-material/SwapVert";

import Caminho from "../../components/Caminho/Caminho";
import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Escopo from "../../components/Escopo/Escopo";
import ModalOrdenacao from "../../components/ModalOrdenacao/ModalOrdenacao";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import Feedback from "../../components/Feedback/Feedback";
import Ajuda from "../../components/Ajuda/Ajuda";

import FontConfig from "../../service/FontConfig";
import EscopoService from "../../service/escopoService";
import FontContext from "../../service/FontContext";

import Tour from "reactour";

const Escopos = () => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // navigate utilizado para navegar entre as páginas
  const navigate = useNavigate();

  // useState utilizado para armazenar os escopos
  const [escopos, setEscopos] = useState(null);
  // useState utilizado para abrir e fechar o modal de confirmação
  const [openModalConfirmacao, setOpenModalConfirmacao] = useState(false);
  // useState para armazenar o escopo selecionado
  const [escopoSelecionado, setEscopoSelecionado] = useState(null);
  // useState para armazenar o valor do input na barra de pesquisa
  const [inputPesquisa, setInputPesquisa] = useState("");
  // useState para aparecer o feedback de escopo deletado
  const [feedbackDeletar, setFeedbackDeletar] = useState(false);

  // useEffect utilizado para buscar os escopos assim que a página é carregada
  useEffect(() => {
    if (!escopos) {
      buscarEscopos();
    }
  }, []);

  // função integrada com a barra de pesquisa para buscar os escopos
  const buscarEscopos = () => {
    if (inputPesquisa == "") {
      EscopoService.buscarPorUsuario(
        parseInt(localStorage.getItem("usuarioId")),
        "sort=id,asc&"
      ).then((response) => {
        let listaEscopos = [];
        for (let escopo of response.content) {
          listaEscopos.push({
            id: escopo.id,
            titulo: escopo.titulo,
            problema: escopo.problema,
            proposta: escopo.proposta,
            frequencia: escopo.frequencia,
            beneficios: escopo.beneficios,
            anexos: escopo.anexo,
            ultimaModificacao: escopo.ultimaModificacao,
            porcentagem: calculaPorcentagem(escopo),
          });
        }
        setEscopos([...listaEscopos]);
      });
    } else {
      EscopoService.buscarPorTitulo(
        parseInt(localStorage.getItem("usuarioId")),
        inputPesquisa,
        "sort=id,asc&"
      ).then((response) => {
        let listaEscopos = [];
        for (let escopo of response.content) {
          listaEscopos.push({
            id: escopo.id,
            titulo: escopo.titulo,
            problema: escopo.problema,
            proposta: escopo.proposta,
            frequencia: escopo.frequencia,
            beneficios: escopo.beneficios,
            anexos: escopo.anexo,
            ultimaModificacao: escopo.ultimaModificacao,
            porcentagem: calculaPorcentagem(escopo),
          });
        }
        setEscopos([...listaEscopos]);
      });
    }
  };

  // Função para salvar o valor do input de pesquisa no estado
  const salvarPesquisa = (e) => {
    setInputPesquisa(e.target.value);
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

  // Função para abrir o escopo para continuar a edição
  const openEscopo = (escopo) => {
    navigate("/editar-escopo", { state: escopo.id });
  };

  // Função para deletar um escopo
  const onDeleteClickEscopo = () => {
    EscopoService.excluirEscopo(escopoSelecionado.id).then((response) => {
      buscarEscopos();
    });
    setFeedbackDeletar(true);
  };

  // Função que captura o click no ícone e abre o modal de confirmação de remoção do escopo
  const onTrashCanClick = (index) => {
    setOpenModalConfirmacao(true);
    setEscopoSelecionado(escopos[index]);
  };

  // Função para "ouvir" um evento de teclado no input de pesquisa e fazer a pesquisa caso seja a tecla "Enter"
  const eventoTeclado = (e) => {
    if (e.key == "Enter") {
      buscarEscopos();
    }
  };

  // useState para abrir e fechar o tour
  const [isTourOpen, setIsTourOpen] = useState(false);

  // Passos do tour
  const stepsTour = [
    {
      selector: "#segundo",
      content:
        "Aqui fica a barra de pesquisa, onde você pode pesquisar por um título.",
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#primeiro",
      content:
        "Aqui fica os escopos criados automaticamente, em cada escopo é possível abrir novamente para edição. É criado um escopo para cada projeto que você abre e não finaliza.",
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quarto",
      content:
        "Nesta área você consegue visualizar qual a porcentagem preenchida do formulário.",
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#terceiro",
      content: "Clicando na lixeira você exclui o escopo.",
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

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
      <Tour
        steps={stepsTour}
        isOpen={isTourOpen}
        onRequestClose={() => setIsTourOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />
      <Ajuda onClick={() => setIsTourOpen(true)} />
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
                id="segundo"
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
                  value={inputPesquisa}
                  onChange={(e) => salvarPesquisa(e)}
                  onKeyDown={(e) => {
                    eventoTeclado(e);
                  }}
                  onBlur={() => {
                    buscarEscopos();
                  }}
                />

                {/* Container para os ícones */}
                <Box className="flex gap-2">
                  {/* Ícone de pesquisa */}
                  <Tooltip title="Pesquisar">
                    <SearchOutlinedIcon
                      onClick={buscarEscopos}
                      className="hover:cursor-pointer"
                      sx={{ color: "text.secondary" }}
                    />
                  </Tooltip>
                </Box>
              </Box>
            </Box>
            <Box
              id="primeiro"
              className="mt-6 grid gap-4"
              sx={{
                gridTemplateColumns: "repeat(auto-fit, minmax(650px, 1fr))",
              }}
            >
              {escopos?.map((escopo, index) => {
                return (
                  <Escopo
                    key={index}
                    isTourOpen={isTourOpen}
                    escopo={escopo}
                    index={index}
                    onclick={() => {
                      openEscopo(escopo);
                    }}
                    handleDelete={onTrashCanClick}
                  />
                );
              })}
            </Box>

            {/* Feedback de escopo deletado com sucesso */}
            <Feedback
              open={feedbackDeletar}
              handleClose={() => {
                setFeedbackDeletar(false);
              }}
              status={"sucesso"}
              mensagem={"Escopo deletado com sucesso!"}
            />
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default Escopos;
