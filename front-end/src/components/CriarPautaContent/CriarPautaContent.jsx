import React, { useContext, useEffect, useState } from "react";

import { useNavigate } from "react-router-dom";

import {
  Box,
  Divider,
  Typography,
  Input,
  Select,
  MenuItem,
  Button,
  Dialog,
  DialogContent,
  TextField,
} from "@mui/material";

import { ClipLoader } from "react-spinners";

import Feedback from "../Feedback/Feedback";

import AsteriscoObrigatorio from "./SubComponents/AsteriscoObrigatorio";
import InputCustom from "./SubComponents/InputCustom";
import PropostaList from "./SubComponents/PropostaList";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";
import ColorModeContext from "../../service/TemaContext";
import ForumService from "../../service/forumService";
import PropostaService from "../../service/propostaService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";
import CookieService from "../../service/cookieService";
import PautaService from "../../service/pautaService";
import ExportPdfService from "../../service/exportPdfService";
import DetalhesProposta from "../DetalhesProposta/DetalhesProposta";
import { useDeferredValue } from "react";

const CriarPautaContent = () => {
  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para obter os textos do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Context para saber o tema atual */
  const { mode } = useContext(ColorModeContext);

  /** Context para obter a função de leitura de texto */
  const { localClique, palavrasJuntas } = useContext(SpeechRecognitionContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  /** Navigate do react-router-dom */
  const navigate = useNavigate();

  /** Parâmetros para pesquisa das demandas e propostas (filtros e pesquisa por título) */
  const [params, setParams] = useState({
    id: null,
    titulo: null,
    solicitante: null,
    gerente: null,
    analista: null,
    forum: null,
    departamento: null,
    tamanho: null,
    status: "ASSESSMENT_APROVACAO",
    presenteEm: null,
    codigoPPM: null,
  });

  /** Armazena o usuário logado no sistema */
  const [usuarioLogado, setUsuarioLogado] = useState(CookieService.getUser());

  /** String para ordenação dos itens atualizada com o valor dos checkboxes a cada busca de itens */
  const [ordenacao, setOrdenacao] = useState("sort=id,asc&");

  /** Variável editável na paginação com o número de itens em uma página */
  const [tamanhoPagina, setTamanhoPagina] = useState(20);

  /** Variável armazenando a página atual usada para a paginação e busca de itens */
  const [paginaAtual, setPaginaAtual] = useState(0);

  /** Número sequencial digitado */
  const [numeroSequencial, setNumeroSequencial] = useState("");

  /** Data de reunião escolhida */
  const [dataReuniao, setDataReunaio] = useState("");

  /** Comissão escolhida */
  const [comissao, setComissao] = useState("");

  /** Propostas a serem apreciadas */
  const [listaPropostas, setListaPropostas] = useState([
    EntitiesObjectService.proposta(),
  ]);

  /** Variável para controlar o input de pesquisa */
  const [search, setSearch] = useState("");

  const deferredSearch = useDeferredValue(search);

  /** Lista de comissões disponíveis */
  const [listaComissoes, setListaComissoes] = useState([
    EntitiesObjectService.forum,
  ]);

  /** Lista de propostas disponíveis para serem escolhidas na pauta */
  const [listaPropostasData, setListaPropostasData] = useState([
    EntitiesObjectService.proposta(),
  ]);

  /** Feedback de erro na criação da pauta */
  const [feedbackErroPauta, setFeedbackErroPauta] = useState(false);

  /** Texto que vai aparecer no feedback de erro na criação da pauta */
  const [feedbackTexto, setFeedbackTexto] = useState("");

  /** Controla o estado do loading component */
  const [isLoading, setIsLoading] = useState(true);

  /** Procura as propostas que podem ser colocadas na pauta do banco */
  const getPropostas = () => {
    PropostaService.getPage(
      params,
      ordenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual
    ).then((data) => {
      setIsLoading(false);
      setListaPropostasData(data.content);
    });
  };

  /** Procura as comissões disponíveis do banco */
  const getForum = () => {
    ForumService.getAll().then((data) => {
      setListaComissoes(data);
    });
  };

  /** Verifica se a lista passada está vazia */
  const isEmpty = (list = []) => {
    if (list.length == 0) return true;

    if (list[0].id == 0) return true;

    return false;
  };

  /** Verifica se a pauta é válida para criação */
  const isPautaValid = () => {
    if (numeroSequencial == "") {
      setFeedbackTexto(texts.criarPauta.addNumeroSequencial);
      setFeedbackErroPauta(true);
      return false;
    }

    if (dataReuniao == "") {
      setFeedbackTexto(texts.criarPauta.addDataReuniao);
      setFeedbackErroPauta(true);
      return false;
    }

    if (comissao == "") {
      setFeedbackTexto(texts.criarPauta.addComissao);
      setFeedbackErroPauta(true);
      return false;
    }

    if (isEmpty(listaPropostas)) {
      setFeedbackTexto(texts.criarPauta.addProposta);
      setFeedbackErroPauta(true);
      return false;
    }

    return true;
  };

  /** Acionada quando o usuário começa a arrastar uma proposta */
  const handleOnPropostaDragStart = (e, proposta) => {
    e.dataTransfer.setData("Text", JSON.stringify(proposta));
  };

  /** Permite que um elemento HTML possa receber um onDrop */
  const allowDrop = (e) => {
    e.preventDefault();
  };

  /** Adiciona a proposta na lista de propostas a serem apreciadas */
  const addProposta = (propostaAux = EntitiesObjectService.proposta()) => {
    let listAux = [];

    if (isEmpty(listaPropostas)) {
      listAux = [propostaAux];
      setListaPropostas([propostaAux]);
    } else {
      listAux = [...listaPropostas, propostaAux];
      setListaPropostas([...listaPropostas, propostaAux]);
    }

    handleListPropostasData(listAux, listaPropostasData);
    setListaPropostas(listAux);
  };

  /** Pega a proposta que o usuário está arrastando para o elemento HTML */
  const onPropostaDrop = (e) => {
    e.preventDefault();
    const propostaAux = JSON.parse(e.dataTransfer.getData("Text"));

    addProposta(propostaAux);
  };

  /** Handler par quando for captado algum som do microfone */
  const handleOnMicChange = () => {
    switch (localClique) {
      case "numero-sequencial":
        setNumeroSequencial(palavrasJuntas);
        break;
      default:
        break;
    }
  };

  /** Handler para quando uma nova comissão for escolhida */
  const handleOnComissaoChange = (event) => {
    setComissao(event.target.value);
  };

  /** Handler para quando for escolhida uma nova data de reunião */
  const handleOnDataChange = (event) => {
    setDataReunaio(event.target.value);
  };

  /** Remove as propostas que estão presentes na lista passada por parâmetro (ou da listaPropostas por padrão) da lista de propostas do banco */
  const handleListPropostasData = (
    list = listaPropostas,
    listData = listaPropostasData
  ) => {
    const idsListaPropostas = new Set(list.map((proposta) => proposta.id));

    const novaListaPropostasData = listData.filter(
      (proposta) => !idsListaPropostas.has(proposta.id)
    );

    setListaPropostasData(novaListaPropostasData);
  };

  /** Handler para atualizar state de pesquisa */
  const handleOnSearchChange = (e) => {
    setSearch(e.target.value);
  };

  /** Handler para quando for clicado para criar uma pauta */
  const handleOnCreatePauta = () => {
    if (!isPautaValid()) return;

    const newPauta = PautaService.createPautaObjectWithPropostas(
      numeroSequencial,
      dataReuniao,
      comissao,
      usuarioLogado.id,
      listaPropostas
    );

    console.log("Nova pauta:", newPauta);

    PautaService.post(newPauta).then((res) => {
      for (let proposta of listaPropostas) {
        PropostaService.atualizacaoPauta(proposta.id, proposta.publicada).then(
          (response) => {
            // Salvamento de histórico
            ExportPdfService.exportProposta(response.id).then((file) => {
              let arquivo = new Blob([file], { type: "application/pdf" });
              PropostaService.addHistorico(
                response.id,
                "Adicionada na Pauta #" + res.numeroSequencial,
                arquivo,
                CookieService.getUser().id
              );
            });
          }
        );
      }

      console.log("Pauta:", res);
      localStorage.setItem("tipoFeedback", 7);
      navigate("/");
    });
  };

  useEffect(() => {
    getForum();
    getPropostas();
  }, []);

  useEffect(() => {
    // console.log("Número seq:", numeroSequencial);
    // console.log("Data:", dataReuniao == "");
    // console.log("Forum:", comissao);
  }, [numeroSequencial, dataReuniao, comissao]);

  useEffect(() => {
    // console.log("Lista propostas data:", listaPropostasData);
  }, [listaPropostasData]);

  useEffect(() => {
    // console.log("Lista de propostas:", listaPropostas);

    PropostaService.getPage(
      params,
      ordenacao + "size=" + tamanhoPagina + "&page=" + 0
    ).then((data) => {
      handleListPropostasData(listaPropostas, data.content);
    });
  }, [listaPropostas]);

  return (
    <>
      {/* Feedback de erro na criação da pauta */}
      <Feedback
        open={feedbackErroPauta}
        handleClose={() => setFeedbackErroPauta(false)}
        status={"erro"}
        mensagem={feedbackTexto}
      />

      <Box className="w-full flex justify-between">
        <Typography
          fontSize={FontConfig.smallTitle}
          fontWeight={600}
          color="primary"
        >
          Nova Pauta
        </Typography>
        <Button variant="text" onClick={handleOnCreatePauta}>
          <Typography fontSize={FontConfig.medium} fontWeight={600}>
            Criar pauta
          </Typography>
        </Button>
      </Box>
      <Divider />

      <Box className="w-full gap-4 flex mt-4 mb-4">
        {/* Formulário pauta */}
        <Box className="w-1/2">
          <Box className="w-full gap-2 flex justify-between">
            {/* Numero sequencial */}
            <Box className="w-2/3">
              <Typography
                fontSize={FontConfig.default}
                fontWeight={500}
                color="primary"
              >
                Número sequencial
                <AsteriscoObrigatorio />
              </Typography>
              <InputCustom
                label="numero-sequencial"
                defaultText={numeroSequencial}
                handleOnMicChange={handleOnMicChange}
                saveText={setNumeroSequencial}
                fullWidth
              />
            </Box>
            {/* Data da reunião  */}
            <Box className="w-1/3 flex justify-end">
              <Box className="w-full">
                <Typography
                  fontSize={FontConfig.default}
                  fontWeight={500}
                  color="primary"
                >
                  Data da reunião
                  <AsteriscoObrigatorio />
                </Typography>
                <Input
                  value={dataReuniao}
                  onChange={handleOnDataChange}
                  fullWidth
                  type="datetime-local"
                  sx={{ colorScheme: mode }}
                />
              </Box>
            </Box>
          </Box>
          {/* Comissão */}
          <Box className="w-full mt-4">
            <Box>
              <Typography
                fontSize={FontConfig.default}
                fontWeight={500}
                color="primary"
              >
                Fórum
                <AsteriscoObrigatorio />
              </Typography>
              <Select
                variant="standard"
                value={comissao}
                onChange={handleOnComissaoChange}
                displayEmpty
                fullWidth
                inputProps={{ "aria-label": "Without label" }}
              >
                <MenuItem value={comissao} disabled>
                  {texts.modalAddPropostaPauta.comissao}
                </MenuItem>
                {listaComissoes?.map((e, index) => (
                  <MenuItem key={index} value={e} title={e.nomeForum}>
                    {e.siglaForum} - {e.nomeForum}
                  </MenuItem>
                ))}
              </Select>
            </Box>
          </Box>

          {/* Propostas a serem discutidas */}
          <Box
            className="mt-10 w-full"
            onDrop={onPropostaDrop}
            onDragOver={allowDrop}
          >
            <Typography
              fontSize={FontConfig.default}
              fontWeight={500}
              color="primary"
            >
              Propostas <AsteriscoObrigatorio />
            </Typography>
            <Box className="border h-96 rounded">
              {!isEmpty(listaPropostas) ? (
                <PropostaList
                  listaPropostas={listaPropostas}
                  setListaPropostas={setListaPropostas}
                  searchText={deferredSearch}
                  inDiscussion
                />
              ) : (
                <Box className="w-full h-full flex justify-center items-center">
                  <Typography
                    fontSize={FontConfig.big}
                    sx={{ color: "text.secondary", mb: 1 }}
                    onClick={() => {
                      lerTexto("");
                    }}
                  >
                    Arraste e Solte uma Proposta Aqui
                  </Typography>
                </Box>
              )}
            </Box>
          </Box>
        </Box>
        {/* Lista de propostas */}
        <Box className="w-1/2 border rounded p-2">
          {isLoading ? (
            <Box className="w-full mt-4 flex justify-center">
              <ClipLoader color="#00579D" size={80} />
            </Box>
          ) : !isEmpty(listaPropostasData) ? (
            <Box className="flex flex-col gap-2">
              <TextField
                value={search}
                onChange={handleOnSearchChange}
                label="Pesquisar por nome ou PPM"
                fullWidth
                variant="standard"
              />
              <Box>
                <PropostaList
                  draggable
                  onDragStart={handleOnPropostaDragStart}
                  listaPropostas={listaPropostasData}
                  setListaPropostas={setListaPropostasData}
                  addProposta={addProposta}
                  searchText={deferredSearch}
                />
              </Box>
            </Box>
          ) : (
            <Box className="w-full h-full flex justify-center items-center">
              <Typography
                fontSize={FontConfig.big}
                sx={{ color: "text.secondary", mb: 1 }}
                onClick={() => {
                  lerTexto("");
                }}
              >
                Não há propostas para serem adicionadas
              </Typography>
            </Box>
          )}
        </Box>
      </Box>
    </>
  );
};

export default CriarPautaContent;
