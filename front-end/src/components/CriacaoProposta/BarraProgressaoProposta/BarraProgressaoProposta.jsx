import React, { useState, useEffect, useContext } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { ClipLoader } from "react-spinners";

import { Box, Stepper, Step, StepLabel, Button } from "@mui/material";

import FormularioPropostaProposta from "../FormularioPropostaProposta/FormularioPropostaProposta";
import FormularioCustosProposta from "../FormularioCustosProposta/FormularioCustosProposta";
import FormularioGeralProposta from "../FormularioGeralProposta/FormularioGeralProposta";
import FormularioEscopoProposta from "../FormularioEscopoProposta/FormularioEscopoProposta";
import Feedback from "../../Feedback/Feedback";

import ForumService from "../../../service/forumService";
import BUService from "../../../service/buService";
import beneficioService from "../../../service/beneficioService";
import propostaService from "../../../service/propostaService";
import DemandaService from "../../../service/demandaService";
import ResponsavelNegocioService from "../../../service/responsavelNegocioService";
import CustosService from "../../../service/custosService";
import EscopoPropostaService from "../../../service/escopoPropostaService";
import ExportPdfService from "../../../service/exportPdfService";
import SecaoTIService from "../../../service/secaoTIService";
import TextLanguageContext from "../../../service/TextLanguageContext";
import CookieService from "../../../service/cookieService";
import MoedasService from "../../../service/moedasService";
import NotificacaoService from "../../../service/notificacaoService";
import { WebSocketContext } from "../../../service/WebSocketService";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";

/** Componente utilizado para criação da proposta, redirecionando para as etapas respectivas  */
const BarraProgressaoProposta = (props) => {
  /** Navigate utilizado para navegar para outras páginas */
  const navigate = useNavigate();

  /** Location utilizado para pegar informações passadas por parâmetro na URL */
  const location = useLocation();

  /** Context do WebSocket */
  const { enviar } = useContext(WebSocketContext);

  /** Contexto para trocar a linguagem */
  const { texts } = useContext(TextLanguageContext);

  /** Context para ler o texto da tela */
  const { lendoTexto, lerTexto, librasAtivo } = useContext(
    SpeechSynthesisContext
  );

  /** Variáveis utilizadas para controlar a barra de progessão na criação da demanda */
  const [activeStep, setActiveStep] = useState(0);

  /** Lista de etapas usadas na criação de proposta */
  const etapasProposta = [
    texts.barraProgressaoProposta.proposta,
    texts.barraProgressaoProposta.escopo,
    texts.barraProgressaoProposta.custo,
    texts.barraProgressaoProposta.gerais,
  ];

  /** Variável para esconder a lista de itens e mostrar um ícone de carregamento enquanto busca os itens no banco */
  const [carregamento, setCarregamento] = useState(true);

  /** Variável para mostrar o carregamento ao criar proposta */
  const [carregamentoProposta, setCarregamentoProposta] = useState(false);

  /** Variáveis utilizadas para salvar um escopo de uma demanda */
  var idEscopo = null;
  const [ultimoEscopo, setUltimoEscopo] = useState(null);

  /** Utilizado para a criação dos dados iniciais da proposta */
  let variaveisIniciais = false;

  /** Variável utilizada para armazenar a lista de seções de TI */
  const [listaSecoesTI, setListaSecoesTI] = useState([]);

  /** Variável utilizada para armazenar a lista de fóruns */
  const [listaForuns, setListaForuns] = useState([]);

  /** Variável utilizada para armazenar a lista de BUs */
  const [listaBU, setListaBU] = useState([]);

  /** Variável utilizada para armazenar a lista de benefícios */
  const [listaBeneficios, setListaBeneficios] = useState([]);

  /** Variável para guardar os custos */
  const [custos, setCustos] = useState([]);

  /** Variável para armazenar os dados da demanda */
  const [dadosDemanda, setDadosDemanda] = useState({
    id: 0,
    titulo: "",
    status: null,
    problema: "",
    proposta: "",
    frequencia: "",
    anexo: [],
    solicitante: null,
    analista: null,
    gerente: null,
    buSolicitante: null,
    busBeneficiadas: [],
    data: "",
    departamento: null,
    forum: null,
    secaoTI: null,
    tamanho: "",
    historicoDemanda: [],
  });

  /** UseState com o escopo da proposta (texto digitado no editor de texto, vem em formato HTML) */
  const [escopo, setEscopo] = useState("");
  const [escopoTemp, setEscopoTemp] = useState("");

  /** Dados gerais definidos na página de dados gerais da criação de demanda */
  const [gerais, setGerais] = useState({
    periodoExecucacaoInicio: "",
    periodoExecucacaoFim: "",
    qtdPaybackSimples: "",
    unidadePaybackSimples: "",
    ppm: "",
    linkJira: "",
    responsaveisNegocio: [],
  });

  /** Variável utilizada para lógica de criação de escopo */
  const [mudancasFeitas, setMudancasFeitas] = useState(false);

  /** Variável para armazenar o valor mais atualizado da cotação do dólar */
  const [valorDolar, setValorDolar] = useState(null);

  /** Variável para armazenar o valor mais atualizado da cotação do euro */
  const [valorEuro, setValorEuro] = useState(null);

  /** Variável utilizada para abrir o feedback de dados faltantes */
  const [feedbackFaltante, setFeedbackFaltante] = useState(false);

  /** Variável utilizada para abrir o feedback que precisa de uma porcentagem de 100% nos CCS */
  const [feedback100porcentoCcs, setFeedback100porcentoCcs] = useState(false);

  /** Variável utilizada para abrir o feedback de data inicio maior que data fim */
  const [feedbackErroDataInicioMaior, setFeedbackErroDataInicioMaior] =
    useState(false);

  /** Variável utilizada para abrir o feedback de ppm inválido */
  const [feedbackPPM, setFeedbackPPM] = useState(false);

  /** Variável utilizada para abrir o feedback de payback inválido */
  const [feedbackPayback, setFeedbackPayback] = useState(false);

  /** Variável usada para interromper o salvamento de escopos enquanto a proposta estiver sendo criada */
  let criandoProposta = false;

  /** UseEffect utilizado para pegar os dados da demanda e pegar os fóruns e BUs */
  useEffect(() => {
    setDadosDemanda(props.dados);
    pesquisarBUs();
    pesquisarForuns();
    pesquisarSecoesTI();
  }, []);

  /** UseEffect utilizado para buscar os valores de euro e dólar ao criar um novo benefício */
  useEffect(() => {
    valoresMoedas();
  }, [dadosDemanda.beneficios]);

  /** UseEffect utilizado para criação de um escopo caso ocorra alguma alteração */
  useEffect(() => {
    if (!idEscopo) {
      if (!location.state.tabelaCustos) {
        if (mudancasFeitas) {
          idEscopo = 1;

          EscopoPropostaService.buscarPorDemanda(dadosDemanda.id).then(
            (data) => {
              if (data.length == 0) {
                receberBeneficios(dadosDemanda.beneficios);
                let escopo = retornaObjetoProposta();
                delete escopo.historicoProposta;
                delete escopo.status;
                delete escopo.emPauta;
                delete escopo.emAta;

                EscopoPropostaService.post(escopo).then((response) => {
                  idEscopo = response.id;
                  setUltimoEscopo(response);
                  criarDadosIniciais();
                });
              } else {
                idEscopo = data[0].id;
                setUltimoEscopo(data[0]);
                carregarEscopoSalvo(data[0]);
              }
            }
          );
        }
      }
    }
  }, [mudancasFeitas]);

  /** UseEffect utilizado para caso haja alteração nos dados, ativar a variável utilizada para a criação do escopo  */
  useEffect(() => {
    if (listaBeneficios && dadosDemanda && gerais && custos) {
      setTimeout(() => {
        setMudancasFeitas(true);
      }, 1000);
    }
  }, [dadosDemanda, gerais, custos, listaBeneficios]);

  /** UseEffect utilizado para salvar o escopo a cada 5 segundos */
  useEffect(() => {
    if (ultimoEscopo) {
      setTimeout(() => {
        if (!criandoProposta) {
          salvarEscopo();
        }
      }, 5000);
    }
  }, [ultimoEscopo]);

  /** Função para buscar os valores mais atualizados para a cotação do dólar e do euro */
  const valoresMoedas = () => {
    MoedasService.getDolar().then((response) => {
      setValorDolar(response);
    });

    MoedasService.getEuro().then((response) => {
      setValorEuro(response);
    });
  };

  /** Função para somar os valores dos benefícios */
  const retornarTotalBeneficios = () => {
    let valorBeneficio = 0;

    for (const beneficio of listaBeneficios) {
      if (beneficio.visible) {
        if (beneficio.tipoBeneficio == "REAL") {
          if (beneficio.moeda == "Dolar") {
            valorBeneficio +=
              parseFloat(beneficio.valor_mensal) * valorDolar.USDBRL.bid;
          } else if (beneficio.moeda == "Euro") {
            valorBeneficio +=
              parseFloat(beneficio.valor_mensal) * valorEuro.EURBRL.bid;
          } else {
            valorBeneficio += parseFloat(beneficio.valor_mensal);
          }
        } else if (beneficio.tipoBeneficio == "POTENCIAL") {
          if (beneficio.moeda == "Dolar") {
            valorBeneficio +=
              (parseFloat(beneficio.valor_mensal) * valorDolar.USDBRL.bid) / 2;
          } else if (beneficio.moeda == "Euro") {
            valorBeneficio +=
              (parseFloat(beneficio.valor_mensal) * valorEuro.EURBRL.bid) / 2;
          } else {
            valorBeneficio += parseFloat(beneficio.valor_mensal) / 2;
          }
        }
      }
    }

    return valorBeneficio;
  };

  /** Função para calcular e retornar a soma de todos os custos da proposta */
  const retornarCustosTotais = () => {
    let valorTotal = 0;

    for (const tabelaCustos of custos) {
      for (const custo of tabelaCustos.custos) {
        valorTotal += parseInt(custo.total);
      }
    }

    return valorTotal;
  };

  /** Função para calcular a quantidade de dias do payback de forma automática */
  const calculoDiasPayback = () => {
    let valorDia = retornarTotalBeneficios() / 30;
    let valorTotalCustos = retornarCustosTotais();

    let contadorDia = 1;

    if (valorDia > 0) {
      while (valorTotalCustos > valorDia) {
        valorTotalCustos = valorTotalCustos - valorDia;
        contadorDia++;
      }
    } else {
      setGerais({
        ...gerais,
        qtdPaybackSimples: null,
        unidadePaybackSimples: "DIAS",
      });
      return;
    }

    let meses = contadorDia / 30;
    let semanas = contadorDia / 7;

    if (meses < 1) {
      if (semanas < 1) {
        setGerais({
          ...gerais,
          qtdPaybackSimples: contadorDia,
          unidadePaybackSimples: "DIAS",
        });
      } else {
        setGerais({
          ...gerais,
          qtdPaybackSimples: Math.ceil(semanas),
          unidadePaybackSimples: "SEMANAS",
        });
      }
    } else {
      setGerais({
        ...gerais,
        qtdPaybackSimples: Math.ceil(meses),
        unidadePaybackSimples: "MESES",
      });
    }
  };

  /** Função para formatar os benefícios recebidos no banco para a lista da página de edição na edição de um escopo existente */
  const receberBeneficios = (beneficios) => {
    let listaNova = [];
    for (let beneficio of beneficios) {
      listaNova.push({ ...beneficio, visible: true, valor_mensal: formatarValorMensal(beneficio.valor_mensal) });
    }

    setListaBeneficios(listaNova);
  };

  /** Função para carregar os custos quando forem recebidos através de um escopo salvo */
  const receberCustos = (tabelaCustos) => {
    let listaTabelasCustos = [];
    for (const tabela of tabelaCustos) {

      let listaCustos = [];
      for (const custo of tabela.custos) {
        let novoCusto = { ...custo };
        if (custo.valorHora) {
          novoCusto.valorHora = custo.valorHora.toString().replace(".", ",")
        }
        listaCustos.push(novoCusto);
      }

      let listaCCs = [];
      for (const cc of tabela.ccs) {
        listaCCs.push({ ...cc });
      }

      listaTabelasCustos.push({ ...tabela, custos: listaCustos, ccs: listaCCs });
    }

    setCustos(listaTabelasCustos);
  }

  /** Função para formatar o valor mensal de um benefício ao recebê-lo */
  const formatarValorMensal = (valor_mensal) => {
    if (!valor_mensal) return;
    let valorMensalString = valor_mensal.toString();
    let arrayValor = valorMensalString.split(".");

    if (arrayValor.length == 1) {
      return valor_mensal + ",00"
    } else if (arrayValor[1].length == 1) {
      if (arrayValor[1].charAt(0)) {
        return valor_mensal + "0"
      } else {
        return arrayValor[0] + ",0" + arrayValor[1]
      }
    } else {
      return arrayValor[0] + "," + arrayValor[1]
    }
  }

  /** Função para buscar um escopo que já foi salvo */
  const carregarEscopoSalvo = (escopo) => {
    setDadosDemanda({
      ...escopo,
      id: escopo.demanda.id,
      status: null,
    });

    setGerais({
      periodoExecucacaoInicio: escopo.inicioExecucao
        ? new Date(escopo.inicioExecucao).toISOString().slice(0, 10)
        : "",
      periodoExecucacaoFim: escopo.fimExecucao
        ? new Date(escopo.fimExecucao).toISOString().slice(0, 10)
        : "",
      qtdPaybackSimples: escopo.paybackValor,
      unidadePaybackSimples: escopo.paybackTipo,
      ppm: escopo.codigoPPM,
      linkJira: escopo.linkJira,
      responsaveisNegocio: escopo.responsavelNegocio,
    });

    receberBeneficios(escopo.beneficios);
    receberCustos(escopo.tabelaCustos);
    setEscopo(escopo.escopo);

    setCarregamento(false);
  };

  /** Função de salvamento de escopo, usando a variável "ultimoEscopo" e atualizando ela com os dados da página */
  const salvarEscopo = () => {
    try {
      let escopoFinal = retornaObjetoProposta();
      delete escopoFinal.historicoProposta;
      delete escopoFinal.status;
      delete escopoFinal.emAta;
      delete escopoFinal.emPauta;
      escopoFinal.id = ultimoEscopo.id;
      EscopoPropostaService.salvarDados(escopoFinal).then((response) => {
        setUltimoEscopo(response);
      });
    } catch (error) { }
  };

  /** Função para criar as chaves estrangeiras necessárias para o escopo no banco de dados */
  const criarDadosIniciais = () => {
    if (!variaveisIniciais) {
      variaveisIniciais = true;

      if (gerais.responsaveisNegocio.length == 0) {
        ResponsavelNegocioService.post({ nome: "", area: "" }).then(
          (response) => {
            setGerais({
              ...gerais,
              responsaveisNegocio: [...gerais.responsaveisNegocio, response],
            });
          }
        );
      }

      if (custos.length == 0) {
        CustosService.postTabela({
          tipoDespesa: "",
          custos: [
            {
              perfilDespesa: "",
              periodoExecucao: "",
              horas: "",
              valorHora: "",
            },
          ],
          ccs: [
            {
              codigo: "",
              porcentagem: "",
            },
          ],
        }).then((response) => {
          setCustos([...custos, { ...response, tipoDespesa: "" }]);
          setCarregamento(false);
        });
      }
    }
  };

  /** Função para salvar os benefícios quando a etapa inicial da criação for concluída */
  const salvarBeneficios = () => {
    for (let beneficio of listaBeneficios) {
      if (beneficio.visible) {
        let beneficioFinal = { ...beneficio };

        if (typeof (beneficioFinal.valor_mensal) == "string") {
          beneficioFinal.valor_mensal = parseFloat(beneficioFinal.valor_mensal.replace(",", "."))
        }

        delete beneficioFinal.visible;
        beneficioService.put(beneficioFinal).then((response) => { });
      }
    }
  };

  /** Função para passar para próxima página */
  const proximaEtapa = () => {
    if (lendoTexto) {
      lerTexto(texts.barraProgressaoProposta.botaoProximo);
    } else if (librasAtivo) {
      return;
    } else {
      let dadosFaltantes = false;
      let fechar100porcentoCcs = false;
      switch (activeStep) {
        case 0:
          if (
            dadosDemanda.titulo == "" ||
            dadosDemanda.problema == "" ||
            dadosDemanda.proposta == "" ||
            dadosDemanda.frequencia == "" ||
            dadosDemanda.buSolicitante == "" ||
            dadosDemanda.busBeneficiadas == "" ||
            dadosDemanda.forum == "" ||
            dadosDemanda.secaoTI == "" ||
            dadosDemanda.tamanho == ""
          ) {
            dadosFaltantes = true;
            setFeedbackFaltante(true);
          } else {
            listaBeneficios.map((beneficio) => {
              if (beneficio.visible) {
                if (
                  beneficio.tipoBeneficio == "" ||
                  beneficio.memoriaCalculo == ""
                ) {
                  if (
                    beneficio.tipoBeneficio != "Qualitativo" &&
                    (beneficio.valor_mensal == "" || beneficio.moeda == "")
                  ) {
                    dadosFaltantes = true;
                    setFeedbackFaltante(true);
                  }
                }
              }
            });
          }
          salvarBeneficios();
          break;
        case 2:
          let porcentagemCcs = 0;
          custos.map((custo) => {
            if (custo.tipoDespesa == "") {
              dadosFaltantes = true;
              setFeedbackFaltante(true);
            } else {
              custo.custos.map((custolinha) => {
                if (
                  custolinha.perfilDespesa == "" ||
                  custolinha.periodoExecucao == "" ||
                  custolinha.periodoExecucao == null ||
                  custolinha.horas == "" ||
                  custolinha.horas == null ||
                  custolinha.valorHora == "" ||
                  custolinha.valorHora == null
                ) {
                  dadosFaltantes = true;
                  setFeedbackFaltante(true);
                }
              });
              custo.ccs.map((cc) => {
                if (cc.codigo == "" || cc.porcentagem == "") {
                  dadosFaltantes = true;
                  setFeedbackFaltante(true);
                }
                porcentagemCcs += cc.porcentagem * 1;
              });
            }
            if (dadosFaltantes == false && porcentagemCcs != 100) {
              fechar100porcentoCcs = true;
              setFeedback100porcentoCcs(true);
            } else {
              porcentagemCcs = 0;
            }
          });
          calculoDiasPayback();
          break;
      }
      if (dadosFaltantes == false && fechar100porcentoCcs == false) {
        setActiveStep((prevActiveStep) => prevActiveStep + 1);
      }
    }
  };

  /** Função para voltar para página anterior */
  const voltarEtapa = () => {
    if (!lendoTexto && !librasAtivo) {
      setActiveStep((prevActiveStep) => prevActiveStep - 1);
    } else {
      lerTexto(texts.barraProgressaoProposta.botaoVoltar);
    }
  };

  /** Função para pesquisar os fóruns do banco e salvar na lista para o select */
  const pesquisarForuns = () => {
    ForumService.getAll().then((response) => {
      setListaForuns(response);
    });
  };

  /** Função para pesquisar as BUs do banco e salvar na lista para o select */
  const pesquisarBUs = () => {
    BUService.getAll().then((response) => {
      setListaBU(response);
    });
  };

  /** Função para pesquisar as seções de TI do banco para uso no select */
  const pesquisarSecoesTI = () => {
    SecaoTIService.getAll().then((response) => {
      setListaSecoesTI(response);
    });
  };

  /** Função para formatar a lista de responsáveis do negócio, retirando o atributo "visible" */
  const formatarResponsaveisNegocio = () => {
    let listaNova = [];

    for (const responsavelNegocio of gerais.responsaveisNegocio) {
      listaNova.push({
        id: responsavelNegocio.id,
        nome: responsavelNegocio.nome,
        area: responsavelNegocio.area,
      });
    }

    return listaNova;
  };

  /** Função para formatar os custos dentro de cadas tabela de custos (retirar o atributo "total") */
  const formatarCustos = () => {
    let listaNova = [];

    for (const tabelaCustos of custos) {
      let listaCustos = [];
      for (const custo of tabelaCustos.custos) {
        listaCustos.push({
          id: custo.id,
          perfilDespesa: custo.perfilDespesa,
          periodoExecucao: custo.periodoExecucao,
          horas: custo.horas,
          valorHora: parseFloat(custo.valorHora.replace(",", ".")),
        });
      }

      let listaCCs = [...tabelaCustos.ccs];
      listaNova.push({
        id: tabelaCustos.id,
        custos: listaCustos,
        ccs: listaCCs,
        tipoDespesa: tabelaCustos.tipoDespesa,
      });
    }
    return listaNova;
  };

  /** Função para formatar uma lista de objetos, retornando somente o id de cada objeto presente, com a lista sendo recebida como parâmetro */
  const retornarIdsObjetos = (listaObjetos) => {
    let listaNova = [];

    for (let objeto of listaObjetos) {
      if (objeto.tipoBeneficio) {
        if (objeto.visible) {
          listaNova.push({ id: objeto.id });
        }
      } else {
        listaNova.push({ id: objeto.id });
      }
    }

    return listaNova;
  };

  /** Função para montar um objeto de proposta para salvamento de escopos e propostas */
  const retornaObjetoProposta = () => {
    const objeto = {
      demanda: { id: dadosDemanda.id },
      titulo: dadosDemanda.titulo,
      status: "ASSESSMENT_APROVACAO",
      problema: formatarHtml(dadosDemanda.problema),
      proposta: formatarHtml(dadosDemanda.proposta),
      frequencia: dadosDemanda.frequencia,
      solicitante: dadosDemanda.solicitante,
      analista: dadosDemanda.analista,
      gerente: dadosDemanda.gerente,
      buSolicitante: dadosDemanda.buSolicitante,
      busBeneficiadas: dadosDemanda.busBeneficiadas,
      departamento: dadosDemanda.departamento,
      forum: dadosDemanda.forum,
      secaoTI: dadosDemanda.secaoTI,
      tamanho: dadosDemanda.tamanho,
      beneficios: retornarIdsObjetos(listaBeneficios),
      tabelaCustos: formatarCustos(),
      responsavelNegocio: formatarResponsaveisNegocio(),
      inicioExecucao: gerais.periodoExecucacaoInicio
        ? gerais.periodoExecucacaoInicio
        : null,
      fimExecucao: gerais.periodoExecucacaoFim
        ? gerais.periodoExecucacaoFim
        : null,
      paybackValor: gerais.qtdPaybackSimples,
      paybackTipo: gerais.unidadePaybackSimples,
      codigoPPM: gerais.ppm,
      linkJira: gerais.linkJira,
      historicoProposta: dadosDemanda.historicoDemanda,
      anexo: retornarIdsObjetos(dadosDemanda.anexo),
      presenteEm: "Solta",
      escopo: formatarHtml(escopo),
    };

    return objeto;
  };

  /** Função para formatar o HTML em casos como a falta de fechamentos em tags "<br>" */
  const formatarHtml = (texto) => {
    if (texto) {
      texto = texto.replace(/<br>/g, "<br/>");
      return texto;
    } else {
      return "";
    }
  };

  /** Verificação para o ppm */
  const verificacaoPPM = async () => {
    let ppmVerificacao = parseInt(gerais.ppm);

    if (
      typeof ppmVerificacao === "number" &&
      !isNaN(ppmVerificacao) &&
      Number.isInteger(ppmVerificacao)
    ) {
      try {
        const response = await propostaService.getByPPM(ppmVerificacao);
        if (response.content.length > 0) {
          return false;
        } else {
          return true;
        }
      } catch (e) {
        return false;
      }
    } else {
      return false;
    }
  };

  /** Verificação para o payback */
  const verificacaoPaybak = () => {
    let paybackVerificacao = parseInt(gerais.qtdPaybackSimples);

    if (
      typeof paybackVerificacao === "number" &&
      !isNaN(paybackVerificacao) &&
      Number.isInteger(paybackVerificacao)
    ) {
      return true;
    } else {
      return false;
    }
  };

  /** Função para criar e retornar um objeto de histórico para salvamento */
  const retornaObjetoHistorico = (acaoRealizada) => {
    const historico = {
      data: new Date(),
      acaoRealizada: acaoRealizada,
      autor: { id: CookieService.getUser().id },
    };
    return historico;
  };

  /** Função para criar a proposta no banco de dados, também atualizando o status da demanda e excluindo o escopo da proposta */
  const criarProposta = async () => {
    if (lendoTexto) {
      lerTexto(texts.barraProgressaoProposta.botaoCriar);
    } else if (librasAtivo) {
      return;
    } else {
      let feedbackFaltante = false;
      criandoProposta = true;

      if (
        gerais.periodoExecucacaoInicio == "" ||
        gerais.periodoExecucacaoFim == "" ||
        gerais.qtdPaybackSimples == "" ||
        gerais.unidadePaybackSimples == "" ||
        gerais.ppm == "" ||
        gerais.linkJira == ""
      ) {
        setFeedbackFaltante(true);
      } else {
        if (gerais.periodoExecucacaoInicio < gerais.periodoExecucacaoFim) {
          if (gerais.responsaveisNegocio.length != 0) {
            gerais.responsaveisNegocio.map((responsavel) => {
              if (responsavel.nome == "" || responsavel.area == "") {
                feedbackFaltante = true;
                setFeedbackFaltante(true);
              }
            });

            const ppmVerificacao = await verificacaoPPM();
            const paybackVerificacao = verificacaoPaybak();

            if (ppmVerificacao) {
              if (feedbackFaltante != true && paybackVerificacao) {
                propostaService
                  .post(retornaObjetoProposta())
                  .then((response) => {
                    setCarregamentoProposta(true);

                    DemandaService.atualizarStatus(
                      dadosDemanda.id,
                      "ASSESSMENT_APROVACAO"
                    ).then(() => {
                      EscopoPropostaService.excluirEscopo(ultimoEscopo.id).then(
                        () => {
                          // Salvamento de histórico
                          ExportPdfService.exportProposta(response.id).then(
                            (file) => {
                              let arquivo = new Blob([file], {
                                type: "application/pdf",
                              });
                              propostaService
                                .addHistorico(
                                  response.id,
                                  retornaObjetoHistorico("Proposta Criada"),
                                  arquivo
                                )
                                .then((propostaResponse) => {
                                  setCarregamentoProposta(false);

                                  // Envio de Notificação ao Solicitante
                                  const notificacao =
                                    NotificacaoService.createNotificationObject(
                                      NotificacaoService.criadoProposta,
                                      dadosDemanda,
                                      CookieService.getUser().id
                                    );
                                  enviar(
                                    `/app/weg_ssm/notificacao/${dadosDemanda.solicitante.id}`,
                                    JSON.stringify(notificacao)
                                  );

                                  localStorage.setItem("tipoFeedback", "5");
                                  navigate("/");
                                });
                            }
                          );
                        }
                      );
                    });
                  });
              } else {
                setFeedbackPayback(true);
              }
            } else {
              setFeedbackPPM(true);
            }
          } else {
            setFeedbackFaltante(true);
          }
        } else {
          setFeedbackErroDataInicioMaior(true);
        }
      }
    }
  };

  return (
    <>
      {/* Carregamento dos dados para a criação da proposta */}
      {carregamentoProposta ? (
        <Box className="mt-6 w-full h-full flex justify-center items-center">
          <ClipLoader color="#00579D" size={110} />
        </Box>
      ) : (
        <>
          {/* Passos de criação da proposta */}
          <Stepper activeStep={activeStep} sx={{ minWidth: "60rem" }}>
            {etapasProposta.map((label, index) => {
              const stepProps = {};
              const labelProps = {};

              return (
                <Step
                  key={label}
                  {...stepProps}
                  onClick={() => {
                    lerTexto(label);
                  }}
                >
                  <StepLabel {...labelProps}>{label}</StepLabel>
                </Step>
              );
            })}
          </Stepper>
          {activeStep == 0 && (
            <FormularioPropostaProposta
              carregamento={carregamento}
              dados={dadosDemanda}
              setDadosDemanda={setDadosDemanda}
              beneficios={listaBeneficios}
              setBeneficios={setListaBeneficios}
              listaForuns={listaForuns}
              listaBU={listaBU}
              listaSecoesTI={listaSecoesTI}
            />
          )}

          {activeStep == 1 && (
            <FormularioEscopoProposta
              escopoTemp={escopoTemp}
              escopo={escopo}
              setEscopo={setEscopo}
            />
          )}

          {activeStep == 2 && (
            <FormularioCustosProposta custos={custos} setCustos={setCustos} />
          )}

          {activeStep == 3 && (
            <FormularioGeralProposta
              gerais={gerais}
              setGerais={setGerais}
              dados={dadosDemanda}
              setDados={setDadosDemanda}
              setFeedbackErroDataInicioMaior={setFeedbackErroDataInicioMaior}
            />
          )}
          {/* Botão para voltar um passo na criação */}
          <Button
            variant="outlined"
            color="tertiary"
            disabled={activeStep === 0}
            onClick={voltarEtapa}
            sx={{ mr: 1, position: "fixed", bottom: 30, left: 160 }}
            disableElevation
          >
            {texts.barraProgressaoProposta.botaoVoltar}
          </Button>

          {/* Botão para criar a proposta ou seguir para o próximo passo */}
          <Box sx={{ flex: "1 1 auto" }} />
          {activeStep === etapasProposta.length - 1 ? (
            <Button
              color="primary"
              variant="contained"
              onClick={criarProposta}
              sx={{ position: "fixed", bottom: 30, right: 160 }}
              disableElevation
            >
              {texts.barraProgressaoProposta.botaoCriar}
            </Button>
          ) : (
            <Button
              color="primary"
              variant="contained"
              onClick={proximaEtapa}
              sx={{ position: "fixed", bottom: 30, right: 160 }}
              disableElevation
            >
              {texts.barraProgressaoProposta.botaoProximo}
            </Button>
          )}
        </>
      )}

      {/* Feedback Data inicio Maior que data fim */}
      <Feedback
        open={feedbackErroDataInicioMaior}
        handleClose={() => {
          setFeedbackErroDataInicioMaior(false);
        }}
        status={"erro"}
        mensagem={texts.homeGerencia.feedback.feedback15}
      />
      {/* Feedback de dados faltantes */}
      <Feedback
        open={feedbackFaltante}
        handleClose={() => {
          setFeedbackFaltante(false);
        }}
        status={"erro"}
        mensagem={
          texts.barraProgressaoProposta.mensagemFeedbackCamposObrigatorios
        }
      />
      {/* Feedback de que não fechou 100% de CCs */}
      <Feedback
        open={feedback100porcentoCcs}
        handleClose={() => {
          setFeedback100porcentoCcs(false);
        }}
        status={"erro"}
        mensagem={texts.barraProgressaoProposta.mensagemFeedbackCcsFaltando}
      />
      {/* Feedback de ppm inválido */}
      <Feedback
        open={feedbackPPM}
        handleClose={() => {
          setFeedbackPPM(false);
        }}
        status={"erro"}
        mensagem={texts.barraProgressaoProposta.mensagemFeedbackPPM}
      />
      {/* Feedback de payback inválido */}
      <Feedback
        open={feedbackPayback}
        handleClose={() => {
          setFeedbackPayback(false);
        }}
        status={"erro"}
        mensagem={texts.barraProgressaoProposta.mensagemFeedbackPayback}
      />
    </>
  );
};

export default BarraProgressaoProposta;
