import React, { useContext, useEffect, useState } from "react";

import { useNavigate } from "react-router-dom";

import {
  Box,
  Divider,
  Typography,
  Input,
  Select,
  MenuItem,
  IconButton,
  Button,
} from "@mui/material";

import RemoveIcon from "@mui/icons-material/Remove";
import SwapHorizIcon from "@mui/icons-material/SwapHoriz";

import Feedback from "../Feedback/Feedback";

import AsteriscoObrigatorio from "./SubComponents/AsteriscoObrigatorio";
import InputCustom from "./SubComponents/InputCustom";
import PropostaItemList from "./SubComponents/PropostaItemList";

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

  /** Procura as propostas que podem ser colocadas na pauta do banco */
  const getPropostas = () => {
    PropostaService.getPage(
      params,
      ordenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual
    ).then((data) => {
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

  /** Remove quaisquer itens com o mesmo ID da lista passada por parâmetro */
  const removeItemFromList = (item, setList) => {
    setList((list) => list.filter((listItem) => listItem.id !== item.id));
  };

  /** Troca o status publicada da proposta passada por parâmetro */
  const togglePropostaPublished = (
    proposta = EntitiesObjectService.proposta()
  ) => {
    if (proposta.id == 0) return;

    const listAux = [...listaPropostas];
    const propostaIndex = listAux.findIndex((item) => item.id == proposta.id);
    listAux[propostaIndex].publicada = !listAux[propostaIndex].publicada;

    setListaPropostas(listAux);
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

  /** Pega a proposta que o usuário está arrastando para o elemento HTML */
  const onPropostaDrop = (e) => {
    e.preventDefault();
    const propostaAux = JSON.parse(e.dataTransfer.getData("Text"));
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
    console.log("Número seq:", numeroSequencial);
    console.log("Data:", dataReuniao == "");
    console.log("Forum:", comissao);
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

      <Box className="w-full gap-4 flex mt-4">
        {/* Formulário pauta */}
        <Box className="w-1/2">
          <Box className="w-full flex justify-between">
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
              <Box>
                <Typography
                  fontSize={FontConfig.default}
                  fontWeight={500}
                  color="primary"
                >
                  Data da reunião
                  <AsteriscoObrigatorio />
                </Typography>
                <Input
                  fullWidth
                  value={dataReuniao}
                  onChange={handleOnDataChange}
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
                listaPropostas.map((proposta, index) => {
                  return (
                    <PropostaItemList key={index} proposta={proposta}>
                      <Box className="flex items-center gap-4">
                        <Box className="flex items-center gap-1">
                          <Typography fontSize={FontConfig.medium}>
                            {proposta.publicada ? "Publicada" : "Não Publicada"}
                          </Typography>
                          <IconButton
                            size="small"
                            color="primary"
                            onClick={() => togglePropostaPublished(proposta)}
                          >
                            <SwapHorizIcon />
                          </IconButton>
                        </Box>
                        <IconButton
                          size="small"
                          color="primary"
                          onClick={() => {
                            removeItemFromList(proposta, setListaPropostas);
                          }}
                        >
                          <RemoveIcon />
                        </IconButton>
                      </Box>
                    </PropostaItemList>
                  );
                })
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
          {!isEmpty(listaPropostasData) ? (
            listaPropostasData.map((proposta, index) => {
              return (
                <PropostaItemList
                  key={index}
                  onDragStart={handleOnPropostaDragStart}
                  draggable
                  proposta={{ ...proposta, publicada: false }} // Seta como false pq vem como null
                />
              );
            })
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
