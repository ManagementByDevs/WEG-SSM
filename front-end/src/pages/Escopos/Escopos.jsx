import React, { useState, useEffect, useContext, useRef } from "react";
import { useNavigate } from "react-router-dom";

import VLibras from "@djpfs/react-vlibras"

import { Box, Button, Tooltip } from "@mui/material";

import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import SwapVertIcon from "@mui/icons-material/SwapVert";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import Caminho from "../../components/Caminho/Caminho";
import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Escopo from "../../components/Escopo/Escopo";
import ModalOrdenacao from "../../components/ModalOrdenacao/ModalOrdenacao";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import Feedback from "../../components/Feedback/Feedback";
import Ajuda from "../../components/Ajuda/Ajuda";
import EscopoModoVisualizacao from "../../components/EscopoModoVisualizacao/EscopoModoVisualizacao";

import FontConfig from "../../service/FontConfig";
import EscopoService from "../../service/escopoService";
import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import UsuarioService from "../../service/usuarioService";
import CookieService from "../../service/cookieService";

import Tour from "reactour";

// Tela para mostrar os escopos de demandas/propostas não finalizadas
const Escopos = () => {
  
  const [usuario, setUsuario] = useState(null);

  // useContext para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

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
    if (!usuario) {
      buscarUsuario();
    }
  }, []);

  useEffect(() => {
    if (!escopos && usuario) {
      buscarEscopos();
    }
  }, [usuario]);

  const buscarUsuario = () => {
    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt").sub).then(
      (user) => {
        setUsuario(user);
      }
    );
  };

  // função integrada com a barra de pesquisa para buscar os escopos
  const buscarEscopos = () => {
    if (inputPesquisa == "") {
      EscopoService.buscarPorUsuario(usuario.id, "sort=id,asc&").then(
        (response) => {
          let listaEscopos = [];
          for (let escopo of response.content) {
            listaEscopos.push({
              id: escopo.id,
              titulo: escopo.titulo,
              problema: escopo.problema,
              proposta: atob(escopo.proposta),
              frequencia: escopo.frequencia,
              beneficios: escopo.beneficios,
              anexos: escopo.anexo,
              ultimaModificacao: escopo.ultimaModificacao,
              porcentagem: calculaPorcentagem(escopo),
            });
          }
          setEscopos([...listaEscopos]);
        }
      );
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
      content: texts.escopos.tour.tour1,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#primeiro",
      content: texts.escopos.tour.tour2,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quarto",
      content: texts.escopos.tour.tour3,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#terceiro",
      content: texts.escopos.tour.tour4,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  // // ********************************************** Gravar audio **********************************************

  const [
    feedbackErroNavegadorIncompativel,
    setFeedbackErroNavegadorIncompativel,
  ] = useState(false);
  const [feedbackErroReconhecimentoVoz, setFeedbackErroReconhecimentoVoz] =
    useState(false);

  const recognitionRef = useRef(null);

  const [escutar, setEscutar] = useState(false);

  const ouvirAudio = () => {
    // Verifica se a API é suportada pelo navegador
    if ("webkitSpeechRecognition" in window) {
      const recognition = new window.webkitSpeechRecognition();
      recognition.continuous = true;
      switch (texts.linguagem) {
        case "pt":
          recognition.lang = "pt-BR";
          break;
        case "en":
          recognition.lang = "en-US";
          break;
        case "es":
          recognition.lang = "es-ES";
          break;
        case "ch":
          recognition.lang = "cmn-Hans-CN";
          break;
        default:
          recognition.lang = "pt-BR";
          break;
      }

      recognition.onstart = () => {
        // console.log("Reconhecimento de fala iniciado. Fale algo...");
      };

      recognition.onresult = (event) => {
        const transcript =
          event.results[event.results.length - 1][0].transcript;
        setInputPesquisa(transcript);
      };

      recognition.onerror = (event) => {
        setFeedbackErroReconhecimentoVoz(true);
        setEscutar(false);
      };

      recognitionRef.current = recognition;
      recognition.start();
    } else {
      setFeedbackErroNavegadorIncompativel(true);
      setEscutar(false);
    }
  };

  const stopRecognition = () => {
    if (recognitionRef.current) {
      recognitionRef.current.stop();
      // console.log("Reconhecimento de fala interrompido.");
    }
  };

  const startRecognition = () => {
    setEscutar(!escutar);
  };

  useEffect(() => {
    if (escutar) {
      ouvirAudio();
    } else {
      stopRecognition();
    }
  }, [escutar]);

  // // ********************************************** Fim Gravar audio **********************************************

  const [nextModoVisualizacao, setNextModoVisualizacao] = useState("TABLE");

  const verEscopo = (escopo) => {

  };

  return (
    <FundoComHeader>
      <VLibras forceOnload />
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
                sx={{
                  backgroundColor: "input.main",
                  width: "50%",
                  minWidth: "15rem",
                }}
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
                  placeholder={texts.escopos.pesquisarPorTitulo}
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
                  <Tooltip title={texts.escopos.pesquisar}>
                    <SearchOutlinedIcon
                      onClick={buscarEscopos}
                      className="hover:cursor-pointer"
                      sx={{ color: "text.secondary" }}
                    />
                  </Tooltip>
                  <Tooltip
                    className="hover:cursor-pointer"
                    title={texts.homeGerencia.gravarAudio}
                    onClick={() => {
                      startRecognition();
                    }}
                  >
                    {escutar ? (
                      <MicOutlinedIcon
                        sx={{ color: "primary.main", fontSize: "1.3rem" }}
                      />
                    ) : (
                      <MicNoneOutlinedIcon
                        sx={{ color: "text.secondary", fontSize: "1.3rem" }}
                      />
                    )}
                  </Tooltip>
                </Box>
              </Box>
            </Box>

            {/* <EscopoModoVisualizacao
              listaEscopos={escopos}
              onEscopoClick={verEscopo}
              nextModoVisualizacao={nextModoVisualizacao}
              myEscopos={true}
            /> */}

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
            {/* Feedback Erro reconhecimento de voz */}
            <Feedback
              open={feedbackErroReconhecimentoVoz}
              handleClose={() => {
                setFeedbackErroReconhecimentoVoz(false);
              }}
              status={"erro"}
              mensagem={texts.homeGerencia.feedback.feedback12}
            />
            {/* Feedback Não navegador incompativel */}
            <Feedback
              open={feedbackErroNavegadorIncompativel}
              handleClose={() => {
                setFeedbackErroNavegadorIncompativel(false);
              }}
              status={"erro"}
              mensagem={texts.homeGerencia.feedback.feedback13}
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
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default Escopos;
