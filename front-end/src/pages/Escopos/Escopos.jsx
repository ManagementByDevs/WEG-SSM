import React, { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";

import { Box, IconButton, Tooltip } from "@mui/material";

import VLibras from "@djpfs/react-vlibras";
import ClipLoader from "react-spinners/ClipLoader";
import Tour from "reactour";

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
import Paginacao from "../../components/Paginacao/Paginacao";

import EscopoService from "../../service/escopoService";
import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import UsuarioService from "../../service/usuarioService";
import CookieService from "../../service/cookieService";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";

/** Tela para mostrar os escopos de demandas/propostas não finalizadas */
const Escopos = () => {
  // useContext para alterar a linguagem do sistema
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, palavrasJuntas } = useContext(
    SpeechRecognitionContext
  );

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

  /** useState utilizado para setar o modo de visualização dos escopos ( Tabela ou grid ) */
  const [nextModoVisualizacao, setNextModoVisualizacao] = useState("TABLE");

  /** Variável usada para esconder a lista de escopos durante sua busca */
  const [carregamentoItens, setCarregamentoItens] = useState(true);

  /** Variável usada na paginação para determinar o número total de páginas */
  const [totalPaginas, setTotalPaginas] = useState(1);

  /** Variável usada na paginação para determinar o número da página de escopos que o usuário está */
  const [paginaAtual, setPaginaAtual] = useState(0);

  /** Variável usada na paginação para determinar o número máximo de escopos numa página */
  const [tamanhoPagina, setTamanhoPagina] = useState(20);

  /** useState para armazenar o valor do input na barra de pesquisa */
  const [inputPesquisa, setInputPesquisa] = useState("");

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

  /** useEffect utilizado para buscar o usuário ao entrar na página */
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
        porcentagem: calculaPorcentagem(escopo),
      };
      listaNova.push(escopoFormatado);
    }

    return listaNova;
  };

  /** Função integrada com a barra de pesquisa para buscar os escopos */
  const buscarEscopos = () => {
    setCarregamentoItens(true);

    let params = { usuario: usuario, titulo: inputPesquisa };
    EscopoService.buscarPagina(params, "sort=id,asc&").then((response) => {
      setEscopos(formatarEscopos(response.content));
      setCarregamentoItens(false);
    });
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
    setInputPesquisa(e.target.value);
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

  /** useEffect utilizado para atualizar o input de pesquisa com o texto reconhecido */
  useEffect(() => {
    setInputPesquisa(palavrasJuntas);
  }, [palavrasJuntas]);

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
        <Caminho />
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
                  value={inputPesquisa}
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
                />
              )}
            </Box>

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
      <Box className="flex justify-end mt-10" sx={{ width: "95%" }}>
        {totalPaginas > 1 || escopos?.length > 20 ? (
          <Paginacao
            totalPaginas={totalPaginas}
            setTamanho={setTamanhoPagina}
            tamanhoPagina={tamanhoPagina}
            setPaginaAtual={setPaginaAtual}
          />
        ) : null}
      </Box>
    </FundoComHeader>
  );
};

export default Escopos;
