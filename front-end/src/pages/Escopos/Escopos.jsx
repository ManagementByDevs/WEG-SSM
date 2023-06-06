import React, { useState, useEffect, useContext, useRef } from "react";
import { useNavigate } from "react-router-dom";
import Tour from "reactour";

import VLibras from "@djpfs/react-vlibras";

import { Box, IconButton, Tooltip } from "@mui/material";
import ClipLoader from "react-spinners/ClipLoader";

import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";
import ViewListIcon from "@mui/icons-material/ViewList";
import ViewModuleIcon from "@mui/icons-material/ViewModule";

import Caminho from "../../components/Caminho/Caminho";
import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import Feedback from "../../components/Feedback/Feedback";
import Ajuda from "../../components/Ajuda/Ajuda";
import EscopoModoVisualizacao from "../../components/EscopoModoVisualizacao/EscopoModoVisualizacao";

import EscopoService from "../../service/escopoService";
import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import UsuarioService from "../../service/usuarioService";
import CookieService from "../../service/cookieService";

import Tour from "reactour";
import Paginacao from "../../components/Paginacao/Paginacao";

/** Tela para mostrar os escopos de demandas/propostas não finalizadas */
const Escopos = ({ lendo = false }) => {

  // useContext para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Navigate utilizado para navegar entre as páginas */
  const navigate = useNavigate();

  /** useState utilizado para armazenar o usuário logado no sistema */
  const [usuario, setUsuario] = useState(null);

  /** useState utilizado para armazenar os escopos */
  const [escopos, setEscopos] = useState(null);

  /** useState utilizado para abrir e fechar o modal de confirmação */
  const [openModalConfirmacao, setOpenModalConfirmacao] = useState(false);

  /** useState para armazenar o escopo selecionado */
  const [escopoSelecionado, setEscopoSelecionado] = useState(null);

  /** useState para aparecer o feedback de escopo deletado */
  const [feedbackDeletar, setFeedbackDeletar] = useState(false);

  /** useState utilizado para ativar o feedbakc de navegador incompatível */
  const [feedbackErroNavegadorIncompativel, setFeedbackErroNavegadorIncompativel] = useState(false);

  /** useState utilizado para ativar o feedback de erro ao reconhecimento de voz */
  const [feedbackErroReconhecimentoVoz, setFeedbackErroReconhecimentoVoz] = useState(false);

  /** useState utilizado para setar o modo de visualização dos escopos ( Tabela ou grid ) */
  const [nextModoVisualizacao, setNextModoVisualizacao] = useState("TABLE");

  /** useState para armazenar o valor do input na barra de pesquisa */
  let inputPesquisa = "";

  /** useState para abrir e fechar o tour */
  const [isTourOpen, setIsTourOpen] = useState(false);

  /** Passos do tour */
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
  /** Variável usada para esconder a lista de escopos durante sua busca */
  const [carregamentoItens, setCarregamentoItens] = useState(true);

  /** Variável usada na paginação para determinar o número total de páginas */
  const [totalPaginas, setTotalPaginas] = useState(1);

  /** Variável usada na paginação para determinar o número da página de escopos que o usuário está */
  const [paginaAtual, setPaginaAtual] = useState(0);

  /** Variável usada na paginação para determinar o número máximo de escopos numa página */
  const [tamanhoPagina, setTamanhoPagina] = useState(20);

  // useEffect utilizado para buscar o usuário ao entrar na página
  useEffect(() => {
    if (!usuario) {
      buscarUsuario();
    }
  }, []);

  /** useEffect utilizado para buscar os escopos ao entrar na página se tiver um usuário logado */
  useEffect(() => {
    if (!escopos && usuario) {
      buscarEscopos();
    }
  }, [usuario]);

  /** Função para buscar o usuário logado no sistema pelo cookie salvo no navegador */
  const buscarUsuario = () => {
    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt").sub).then(
      (user) => {
        setUsuario(user);
      }
    );
  };

  /** Função para formatar e retornar uma lista de escopos para o modelo de visualização */
  const formatarEscopos = (listaEscopos) => {
    let listaNova = [];
    for (let escopo of listaEscopos) {
      const escopoFormatado = {
        ...escopo,
        proposta: atob(escopo.proposta),
        porcentagem: calculaPorcentagem(escopo)
      }
      listaNova.push(escopoFormatado);
    }

    return listaNova;
  }

  // Função integrada com a barra de pesquisa para buscar os escopos
  const buscarEscopos = () => {
    setCarregamentoItens(true);

    let params = { usuario: usuario, titulo: inputPesquisa };
    EscopoService.buscarPagina(params, "sort=id,asc&").then((response) => {
      setEscopos(formatarEscopos(response.content));
      setCarregamentoItens(false);
    })
  };

  /** Função para calcular a porcentagem de preenchimento do escopo */
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

  /** Função para salvar o valor do input de pesquisa no estado */
  const salvarPesquisa = (e) => {
    inputPesquisa = e.target.value;
  };

  /** Função para abrir o escopo para continuar a edição */
  const openEscopo = (escopo) => {
    navigate("/editar-escopo", { state: escopo.id });
  };

  /** Função para deletar um escopo */
  const onDeleteClickEscopo = () => {
    EscopoService.excluirEscopo(escopoSelecionado.id).then((response) => {
      buscarEscopos();
    });
    setFeedbackDeletar(true);
  };

  /** Função que captura o click no ícone e abre o modal de confirmação de remoção do escopo */
  const onTrashCanClick = (index) => {
    setOpenModalConfirmacao(true);
    setEscopoSelecionado(escopos[index]);
  };

  /** Função para "ouvir" um evento de teclado no input de pesquisa e fazer a pesquisa caso seja a tecla "Enter" */
  const eventoTeclado = (e) => {
    if (e.key == "Enter") {
      buscarEscopos();
    }
  };

  // ********************************************** Gravar audio ********************************************** //

  /** Varíavel utilizada para lógica de gravação de audio */
  const recognitionRef = useRef(null);

  /** Variável utilizada para ativar o microfone para gravação de audio */
  const [escutar, setEscutar] = useState(false);

  /** Varíavel utilizada para concatenar palavras ao receber resultados da transcrição de voz */
  const [palavrasJuntas, setPalavrasJuntas] = useState("");

  /** Função para gravar audio nos inputs */
  const ouvirAudio = () => {
    /** Verifica se a API é suportada pelo navegador */
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
      };

      recognition.onresult = (event) => {
        const transcript =
          event.results[event.results.length - 1][0].transcript;
        setPalavrasJuntas((palavrasJuntas) => palavrasJuntas + transcript);


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

  /** useEffect utilizado para atualizar o input de pesquisa com o texto reconhecido */
  useEffect(() => {
    inputPesquisa = palavrasJuntas;
  }, [palavrasJuntas]);

  /** Função para encerrar a gravação de voz */
  const stopRecognition = () => {
    if (recognitionRef.current) {
      recognitionRef.current.stop();
    }
  };

  /** Função para iniciar a gravação de voz */
  const startRecognition = () => {
    setEscutar(!escutar);
  };

  /** useEffect utilizado para verificar se a gravação ainda está funcionando */
  useEffect(() => {
    if (escutar) {
      ouvirAudio();
    } else {
      stopRecognition();
    }
  }, [escutar]);

  /** Função para trocar o modo de visualização dos itens (bloco / lista) */
  const trocarModoVisualizacao = () => {
    let novoModo = nextModoVisualizacao === "GRID" ? "TABLE" : "GRID";
    saveNewPreference("itemsVisualizationMode", novoModo);
    setNextModoVisualizacao(novoModo);
  };

  /** Função que salva a nova preferência do usuário */
  const saveNewPreference = (preferenciaTipo = "", value) => {
    if (!CookieService.getCookie("jwt")) return;

    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt").sub).then(
      (user) => {
        let preferencias = JSON.parse(user.preferencias);

        switch (preferenciaTipo) {
          case "itemsVisualizationMode":
            // Nova preferência do modo de visualização
            preferencias.itemsVisualizationMode =
              value == "TABLE" ? "grid" : "table";
            break;
          case "abaPadrao":
            // Nova preferência da aba padrão
            preferencias.abaPadrao = value;
            // setValorAba(preferencias.abaPadrao);
            break;
        }

        user.preferencias = JSON.stringify(preferencias);

        UsuarioService.updateUser(user.id, user);
      }
    );
  };

  return (
    <FundoComHeader lendo={lendo}>
      <VLibras forceOnload />
      {/* Modal de confirmação de exclusão de escopo */}
      <ModalConfirmacao
        textoModal={"descartarRascunho"}
        onConfirmClick={onDeleteClickEscopo}
        onCancelClick={() => { setOpenModalConfirmacao(false) }}
        textoBotao={"sim"}
        open={openModalConfirmacao}
        setOpen={setOpenModalConfirmacao}
        lendo={lendo}
      />
      {/* Tour de ajuda */}
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
        <Caminho lendo={lendo} />
        {/* Div pegando width inteira para fazer o espaçamento das bordas */}
        <Box className="flex justify-center w-full">
          {/* Container conteudo */}
          <Box className="w-11/12 mt-10">
            {/* Container para o input e botão de filtrar */}
            <Box className="relative mb-4">
              {/* Input de pesquisa */}
              <Box
                id="segundo"
                className="flex justify-between border px-3 py-1"
                sx={{
                  backgroundColor: "input.main",
                  width: "25%",
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
                        sx={{
                          cursor: "pointer",
                          color: "primary.main",
                          fontSize: "1.3rem",
                        }}
                      />
                    ) : (
                      <MicNoneOutlinedIcon
                        sx={{
                          cursor: "pointer",
                          color: "text.secondary",
                          fontSize: "1.3rem",
                        }}
                      />
                    )}
                  </Tooltip>
                </Box>
              </Box>
              <Box id="nonoDemandas" className="absolute right-0 top-2">
                {nextModoVisualizacao == "TABLE" ? (
                  <Tooltip title={texts.homeGerencia.visualizacaoEmTabela}>
                    <IconButton
                      onClick={() => {
                        trocarModoVisualizacao();
                      }}
                    >
                      <ViewListIcon color="primary" />
                    </IconButton>
                  </Tooltip>
                ) : (
                  <Tooltip title={texts.homeGerencia.visualizacaoEmBloco}>
                    <IconButton
                      onClick={() => {
                        trocarModoVisualizacao();
                      }}
                    >
                      <ViewModuleIcon color="primary" />
                    </IconButton>
                  </Tooltip>
                )}
              </Box>
            </Box>

            {/* Mostrando os escopos de acordo com a forma de visualização */}
            <Box sx={{ marginTop: "2%" }}>
              {carregamentoItens ? (
                <Box className="mt-6 w-full h-full flex justify-center items-center">
                  <ClipLoader color="#00579D" size={110} />
                </Box>
              ) : (
                <EscopoModoVisualizacao
                  listaEscopos={escopos}
                  onEscopoClick={openEscopo}
                  nextModoVisualizacao={nextModoVisualizacao}
                  myEscopos={true}
                  handleDelete={onTrashCanClick}
                  buscar={buscarEscopos}
                  isTourOpen={isTourOpen}
                  lendo={lendo}
                />
              )}
            </Box>

            {/* Feedback Erro reconhecimento de voz */}
            <Feedback
              open={feedbackErroReconhecimentoVoz}
              handleClose={() => {
                setFeedbackErroReconhecimentoVoz(false);
              }}
              status={"erro"}
              mensagem={texts.homeGerencia.feedback.feedback12}
              lendo={lendo}
            />
            {/* Feedback Não navegador incompativel */}
            <Feedback
              open={feedbackErroNavegadorIncompativel}
              handleClose={() => {
                setFeedbackErroNavegadorIncompativel(false);
              }}
              status={"erro"}
              mensagem={texts.homeGerencia.feedback.feedback13}
              lendo={lendo}
            />
            {/* Feedback de escopo deletado com sucesso */}
            <Feedback
              open={feedbackDeletar}
              handleClose={() => {
                setFeedbackDeletar(false);
              }}
              status={"sucesso"}
              mensagem={texts.escopos.escopoDeletadoComSucesso}
              lendo={lendo}
              s
            />
          </Box>
        </Box>
      </Box>
      <Box className="flex justify-end mt-10" sx={{ width: "95%" }}>
        {totalPaginas > 1 || escopos?.length > 20 ? (
          <Paginacao
            totalPaginas={totalPaginas}
            setTamanho={setTamanhoPagina}
            tamanhoPagina={tamanhoPagina}
            setPaginaAtual={setPaginaAtual}
            lendo={lendo}
          />
        ) : null}
      </Box>
    </FundoComHeader>
  );
};

export default Escopos;