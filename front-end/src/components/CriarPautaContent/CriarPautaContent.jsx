import React, { useContext, useEffect, useState } from "react";

import {
  Box,
  Divider,
  Typography,
  Input,
  Select,
  MenuItem,
  IconButton,
  Paper,
} from "@mui/material";

import AddCircleIcon from "@mui/icons-material/AddCircle";
import AddIcon from "@mui/icons-material/Add";

import AsteriscoObrigatorio from "./SubComponents/AsteriscoObrigatorio";
import InputCustom from "./SubComponents/InputCustom";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";
import ColorModeContext from "../../service/TemaContext";
import ForumService from "../../service/forumService";
import PropostaService from "../../service/propostaService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

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

  /** String para ordenação dos itens atualizada com o valor dos checkboxes a cada busca de itens */
  const [ordenacao, setOrdenacao] = useState("sort=id,asc&");

  /** Variável editável na paginação com o número de itens em uma página */
  const [tamanhoPagina, setTamanhoPagina] = useState(20);

  /** Variável armazenando a página atual usada para a paginação e busca de itens */
  const [paginaAtual, setPaginaAtual] = useState(0);

  const [numeroSequencial, setNumeroSequencial] = useState("");

  const [dataReuniao, setDataReunaio] = useState("");

  const [comissao, setComissao] = useState("");

  const [listaPropostas, setListaPropostas] = useState([
    EntitiesObjectService.proposta(),
  ]);

  const [listaComissoes, setListaComissoes] = useState([
    EntitiesObjectService.forum,
  ]);

  const [listaPropostasData, setListaPropostasData] = useState([
    EntitiesObjectService.proposta(),
  ]);

  const regexOnlyNumber = new RegExp(/^[0-9]*$/);

  /** Verifica se a lista passada está vazia */
  const isEmpty = (list = []) => {
    console.log(list);
    if (list.length == 0) return true;

    if (list[0].id == 0) return true;

    return false;
  };

  /** Retorna o ano a partir de uma data SQL */
  const getAno = (dateSql) => {
    return dateSql.split("-")[0];
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

  /** Handler para quando for clicado para criar uma pauta */
  const handleOnCreatePauta = () => {
    console.log("Criar pauta");
  };

  useEffect(() => {
    ForumService.getAll().then((data) => {
      setListaComissoes(data);
    });

    PropostaService.getPage(
      params,
      ordenacao + "size=" + tamanhoPagina + "&page=" + paginaAtual
    ).then((data) => {
      setListaPropostasData(data.content);
    });
  }, []);

  useEffect(() => {
    console.log("Número seq:", numeroSequencial);
    console.log("Data:", dataReuniao);
    console.log("Forum:", comissao);
  }, [numeroSequencial, dataReuniao, comissao]);

  useEffect(() => {
    console.log("Lista propostas data:", listaPropostasData);
  }, [listaPropostasData]);

  return (
    <>
      <Box className="w-full flex justify-between">
        <Typography
          fontSize={FontConfig.smallTitle}
          fontWeight={600}
          color="primary"
        >
          Nova Pauta
        </Typography>
        <IconButton onClick={handleOnCreatePauta}>
          <AddCircleIcon color="primary" />
        </IconButton>
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
          <Box className="mt-10 w-full">
            <Typography
              fontSize={FontConfig.default}
              fontWeight={500}
              color="primary"
            >
              Propostas <AsteriscoObrigatorio />
            </Typography>
            <Box className="border h-96 rounded">
              {!isEmpty(listaPropostas) ? (
                listaPropostas.map((proposta, index) => {})
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
                <Paper
                  key={index}
                  className="w-full mt-1 flex justify-between p-1 border"
                  square
                  variant="outlined"
                  draggable={true}
                >
                  <Box className="flex items-center gap-2 w-2/3">
                    <Box className="w-1/3">
                      <Typography
                        fontSize={FontConfig.default}
                        fontWeight={500}
                        color="primary"
                      >
                        {proposta.codigoPPM}/{getAno(proposta.data)}
                      </Typography>
                    </Box>
                    <Box className="w-2/3">
                      <Typography
                        fontSize={FontConfig.default}
                        fontWeight={500}
                        color="text.primary"
                      >
                        {proposta.titulo}
                      </Typography>
                    </Box>
                  </Box>
                  <Box className="w-1/3 flex justify-end">
                    <IconButton size="small">
                      <AddIcon color="primary" />
                    </IconButton>
                  </Box>
                </Paper>
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
