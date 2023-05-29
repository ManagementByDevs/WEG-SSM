import React, { useState, useEffect, useContext } from "react";
import { useNavigate, useLocation } from "react-router-dom";

import { Box, Stepper, Step, StepLabel, Button } from "@mui/material";

import FormularioPropostaProposta from "../FormularioPropostaProposta/FormularioPropostaProposta";
import FormularioCustosProposta from "../FormularioCustosProposta/FormularioCustosProposta";
import FormularioGeralProposta from "../FormularioGeralProposta/FormularioGeralProposta";
import FormularioEscopoProposta from "../FormularioEscopoProposta/FormularioEscopoProposta";
import Feedback from "../Feedback/Feedback";

import ForumService from "../../service/forumService";
import BUService from "../../service/buService";
import beneficioService from "../../service/beneficioService";
import propostaService from "../../service/propostaService";
import DemandaService from "../../service/demandaService";
import ResponsavelNegocioService from "../../service/responsavelNegocioService";
import CustosService from "../../service/custosService";
import EscopoPropostaService from "../../service/escopoPropostaService";
import ExportPdfService from "../../service/exportPdfService";
import SecaoTIService from "../../service/secaoTIService";
import TextLanguageContext from "../../service/TextLanguageContext";
import CookieService from "../../service/cookieService";
import MoedasService from "../../service/moedasService";

// Componente utilizado para criação da proposta, redirecionando para as etapas respectivas
const BarraProgressaoProposta = (props) => {
  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Location utilizado para pegar informações passadas por parâmetro na URL
  const location = useLocation();

  // Variáveis utilizadas para controlar a barra de progessão na criação da demanda
  const [activeStep, setActiveStep] = useState(0);

  /** Lista de etapas usadas na criação de proposta */
  const etapasProposta = [
    texts.barraProgressaoProposta.proposta,
    texts.barraProgressaoProposta.escopo,
    texts.barraProgressaoProposta.custo,
    texts.barraProgressaoProposta.gerais,
  ];

  // Navigate utilizado para navegar para outras páginas
  const navigate = useNavigate();

  // Variável para esconder a lista de itens e mostrar um ícone de carregamento enquanto busca os itens no banco
  const [carregamento, setCarregamento] = useState(true);

  // Variáveis utilizadas para salvar um escopo de uma demanda
  var idEscopo = null;
  const [ultimoEscopo, setUltimoEscopo] = useState(null);

  let variaveisIniciais = false;

  /** Variável utilizada para armazenar a lista de seções de TI */
  const [listaSecoesTI, setListaSecoesTI] = useState([]);

  // Variável utilizada para armazenar a lista de fóruns
  const [listaForuns, setListaForuns] = useState([]);

  // Variável utilizada para armazenar a lista de BUs
  const [listaBU, setListaBU] = useState([]);

  // Variável para armazenar os dados da demanda
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

  // Variável utilizada para armazenar a lista de benefícios
  const [listaBeneficios, setListaBeneficios] = useState([]);

  // Variável para guardar os custos
  const [custos, setCustos] = useState([]);

  // UseState com o escopo da proposta (texto digitado no editor de texto, vem em formato HTML)
  const [escopo, setEscopo] = useState("");
  const [escopoTemp, setEscopoTemp] = useState("");

  // Dados gerais definidos na página de dados gerais da criação de demanda
  const [gerais, setGerais] = useState({
    periodoExecucacaoInicio: "",
    periodoExecucacaoFim: "",
    qtdPaybackSimples: "",
    unidadePaybackSimples: "",
    ppm: "",
    linkJira: "",
    responsaveisNegocio: [],
  });

  const [mudancasFeitas, setMudancasFeitas] = useState(false);

  /** Variável usada para interromper o salvamento de escopos enquanto a proposta estiver sendo criada */
  let criandoProposta = false;

  // UseEffect utilizado para pegar os dados da demanda e pegar os fóruns e BUs
  useEffect(() => {
    setDadosDemanda(props.dados);
    pesquisarBUs();
    pesquisarForuns();
    pesquisarSecoesTI();
  }, []);

  /** Função para somar os valores dos benefícios */
  const retornarTotalBeneficios = () => {
    let valorBeneficio = 0;
    let valorDolar, valorEuro;
    MoedasService.getDolar().then((response) => {
      valorDolar = response;
    });

    MoedasService.getEuro().then((response) => {
      valorEuro = response;
    })

    for (const object in listaBeneficios) {
      if (listaBeneficios[object].tipoBeneficio == "Real") {
        if (listaBeneficios[object].moeda == "Dolar") {
          valorBeneficio += (parseFloat(listaBeneficios[object].valor_mensal) * valorDolar.USDBRL.bid);
        } else if (listaBeneficios[object].moeda == "Euro") {
          valorBeneficio += (parseFloat(listaBeneficios[object].valor_mensal) * valorEuro.EURBRL.bid);
        } else {
          valorBeneficio += parseFloat(listaBeneficios[object].valor_mensal);
        }
      } else if (listaBeneficios[object].tipoBeneficio == "Potencial") {
        if (listaBeneficios[object].moeda == "Dolar") {
          valorBeneficio += (parseFloat(listaBeneficios[object].valor_mensal) * valorDolar.USDBRL.bid) / 2;
        } else if (listaBeneficios[object].moeda == "Euro") {
          valorBeneficio += (parseFloat(listaBeneficios[object].valor_mensal) * valorEuro.EURBRL.bid) / 2;
        } else {
          valorBeneficio += parseFloat(listaBeneficios[object].valor_mensal) / 2;
        }
      }
    }

    return valorBeneficio;
  }

  /** Função para calcular e retornar a soma de todos os custos da proposta */
  const retornarCustosTotais = () => {
    let valorTotal = 0;

    for (const tabelaCustos of custos) {
      for (const custo of tabelaCustos.custos) {
        valorTotal += custo.total;
      }
    }

    return valorTotal;
  }

  /** Cálculo para calcular a quantidade automático */
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
      setGerais({ ...gerais, qtdPaybackSimples: null, unidadePaybackSimples: "DIAS" });
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

  useEffect(() => {
    if (listaBeneficios && dadosDemanda && gerais && custos) {
      setTimeout(() => {
        setMudancasFeitas(true);
      }, 1000);
    }
  }, [dadosDemanda, gerais, custos, listaBeneficios]);

  // UseEffect utilizado para salvar o escopo a cada 5 segundos
  useEffect(() => {
    if (ultimoEscopo) {
      setTimeout(() => {
        if (!criandoProposta) {
          salvarEscopo();
        }
      }, 5000);
    }
  }, [ultimoEscopo]);

  /** Função para formatar os benefícios recebidos no banco para a lista da página de edição na edição de um escopo existente */
  const receberBeneficios = (beneficios) => {
    let listaNova = [];
    for (let beneficio of beneficios) {
      const tipoBeneficioNovo =
        beneficio.tipoBeneficio?.charAt(0) +
        beneficio.tipoBeneficio
          ?.substring(1, beneficio.tipoBeneficio.length)
          .toLowerCase();

      let memoriaCalculo = beneficio.memoriaCalculo;
      try {
        memoriaCalculo = atob(beneficio.memoriaCalculo);
      } catch (error) { }

      listaNova.push({
        id: beneficio.id,
        tipoBeneficio: tipoBeneficioNovo,
        valor_mensal: beneficio.valor_mensal,
        moeda: beneficio.moeda,
        memoriaCalculo: memoriaCalculo,
        visible: true
      });
    }
    setListaBeneficios(listaNova);
  };

  const carregarEscopoSalvo = (escopo) => {
    setDadosDemanda({
      id: escopo.demanda.id,
      titulo: escopo.titulo,
      status: null,
      problema: atob(escopo.problema),
      proposta: atob(escopo.proposta),
      frequencia: escopo.frequencia,
      anexo: escopo.anexo,
      solicitante: escopo.solicitante,
      analista: escopo.analista,
      gerente: escopo.gerente,
      buSolicitante: escopo.buSolicitante,
      busBeneficiadas: escopo.busBeneficiadas,
      data: escopo.data,
      departamento: escopo.departamento,
      forum: escopo.forum,
      secaoTI: escopo.secaoTI,
      tamanho: escopo.tamanho,
      historicoDemanda: escopo.historicoDemanda,
    });

    setGerais({
      periodoExecucacaoInicio: new Date(escopo.inicioExecucao)
        .toISOString()
        .slice(0, 10),
      periodoExecucacaoFim: new Date(escopo.fimExecucao)
        .toISOString()
        .slice(0, 10),
      qtdPaybackSimples: escopo.paybackValor,
      unidadePaybackSimples: escopo.paybackTipo,
      ppm: escopo.codigoPPM,
      linkJira: escopo.linkJira,
      responsaveisNegocio: escopo.responsavelNegocio,
    });

    receberBeneficios(escopo.beneficios);
    setCustos(escopo.tabelaCustos);
    setEscopo(carregarTextoEscopo(escopo));

    setCarregamento(false);
  };

  /** Função para transformar uma string em base64 para um ArrayBuffer, usada para baixar anexos */
  function converterBase64(base64) {
    const textoBinario = window.atob(base64);
    const bytes = new Uint8Array(textoBinario.length);
    return bytes.map((byte, i) => textoBinario.charCodeAt(i));
  }

  /** Função para carregar o escopo da proposta (campo de texto) quando recebido de um escopo (Objeto salvo) do banco */
  const carregarTextoEscopo = (escopo) => {
    let reader = new FileReader();
    reader.onload = function () {
      setEscopo(reader.result);
    };

    if (escopo.escopo) {
      let blob = new Blob([converterBase64(escopo.escopo)]);
      reader.readAsText(blob);
    }
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
          custos: [
            {
              tipoDespesa: "",
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
          setCustos([...custos, response]);
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
        delete beneficioFinal.visible;
        beneficioService.put(beneficioFinal, beneficioFinal.memoriaCalculo).then((response) => { });
      }
    }
  };

  // Função para passar para próxima página
  const proximaEtapa = () => {
    if (props.lendo) {
      lerTexto(texts.barraProgressaoProposta.botaoProximo);
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
            custo.custos.map((custolinha) => {
              if (
                custolinha.tipoDespesa == "" ||
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
              porcentagemCcs += cc.porcentagem;
            });
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

  // Função para voltar para página anterior
  const voltarEtapa = () => {
    if (!props.lendo) {
      setActiveStep((prevActiveStep) => prevActiveStep - 1);
    } else {
      lerTexto(texts.barraProgressaoProposta.botaoVoltar);
    }
  };

  // Função para pesquisar os fóruns do banco e salvar na lista para o select
  const pesquisarForuns = () => {
    ForumService.getAll().then((response) => {
      setListaForuns(response);
    });
  };

  // Função para pesquisar as BUs do banco e salvar na lista para o select
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
          tipoDespesa: custo.tipoDespesa,
          perfilDespesa: custo.perfilDespesa,
          periodoExecucao: custo.periodoExecucao,
          horas: custo.horas,
          valorHora: custo.valorHora,
        });
      }

      let listaCCs = [...tabelaCustos.ccs];
      listaNova.push({
        id: tabelaCustos.id,
        custos: listaCustos,
        ccs: listaCCs,
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
      problema: btoa(formatarHtml(dadosDemanda.problema)),
      proposta: btoa(formatarHtml(dadosDemanda.proposta)),
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
      inicioExecucao: gerais.periodoExecucacaoInicio,
      fimExecucao: gerais.periodoExecucacaoFim,
      paybackValor: gerais.qtdPaybackSimples,
      paybackTipo: gerais.unidadePaybackSimples,
      codigoPPM: gerais.ppm,
      linkJira: gerais.linkJira,
      historicoProposta: dadosDemanda.historicoDemanda,
      anexo: retornarIdsObjetos(dadosDemanda.anexo),
      presenteEm: "Nada",
      escopo: btoa(formatarHtml(escopo)),
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

  /** Função para criar a proposta no banco de dados, também atualizando o status da demanda e excluindo o escopo da proposta */
  const criarProposta = () => {
    if (props.lendo) {
      lerTexto(texts.barraProgressaoProposta.botaoCriar);
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
        if (gerais.responsaveisNegocio.length != 0) {
          gerais.responsaveisNegocio.map((responsavel) => {
            if (responsavel.nome == "" || responsavel.area == "") {
              feedbackFaltante = true;
              setFeedbackFaltante(true);
            }
          });
          if (feedbackFaltante != true) {

            propostaService.post(retornaObjetoProposta()).then((response) => {
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
                            "Proposta Criada",
                            arquivo,
                            CookieService.getUser().id
                          )
                          .then(() => {
                            localStorage.setItem("tipoFeedback", "5");
                            navigate("/");
                          });
                      }
                    );
                  }
                );
              });
            });
          }
        } else {
          setFeedbackFaltante(true);
        }
      }
    }
  };

  /** Variável utilizada para abrir o modal de feedback de dados faltantes */
  const [feedbackFaltante, setFeedbackFaltante] = useState(false);
  const [feedback100porcentoCcs, setFeedback100porcentoCcs] = useState(false);

  const [
    feedbackErroNavegadorIncompativel,
    setFeedbackErroNavegadorIncompativel,
  ] = useState(false);
  const [feedbackErroReconhecimentoVoz, setFeedbackErroReconhecimentoVoz] =
    useState(false);

  const [textoLeitura, setTextoLeitura] = useState("");

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (escrita) => {
    if (props.lendo) {
      setTextoLeitura(escrita);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(textoLeitura);

    const finalizarLeitura = () => {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    };

    if (props.lendo && textoLeitura !== "") {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else {
      finalizarLeitura();
    }

    return () => {
      finalizarLeitura();
    };
  }, [textoLeitura]);

  return (
    <>
      <Stepper activeStep={activeStep} sx={{ minWidth: "60rem" }}>
        {etapasProposta.map((label, index) => {
          const stepProps = {};
          const labelProps = {};
          return (
            <Step key={label} {...stepProps} onClick={() => {
              lerTexto(label)
            }}>
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
          setFeedbackErroNavegadorIncompativel={
            setFeedbackErroNavegadorIncompativel
          }
          setFeedbackErroReconhecimentoVoz={setFeedbackErroReconhecimentoVoz}
          lendo={props.lendo}
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
        <FormularioCustosProposta
          custos={custos}
          setCustos={setCustos}
          setFeedbackErroNavegadorIncompativel={
            setFeedbackErroNavegadorIncompativel
          }
          setFeedbackErroReconhecimentoVoz={setFeedbackErroReconhecimentoVoz}
          lendo={props.lendo}
           
        />
      )}
      {activeStep == 3 && (
        <FormularioGeralProposta
          gerais={gerais}
          setGerais={setGerais}
          dados={dadosDemanda}
          setDados={setDadosDemanda}
          setFeedbackErroNavegadorIncompativel={
            setFeedbackErroNavegadorIncompativel
          }
          setFeedbackErroReconhecimentoVoz={setFeedbackErroReconhecimentoVoz}
          lendo={props.lendo}
           
        />
      )}
      <Button
        variant="outlined"
        color="tertiary"
        disabled={activeStep === 0}
        onClick={voltarEtapa}
        sx={{ mr: 1, position: "fixed", bottom: 50, left: 160 }}
        disableElevation
      >
        {texts.barraProgressaoProposta.botaoVoltar}
      </Button>
      <Box sx={{ flex: "1 1 auto" }} />
      {activeStep === etapasProposta.length - 1 ? (
        <Button
          color="primary"
          variant="contained"
          onClick={criarProposta}
          sx={{ position: "fixed", bottom: 50, right: 160 }}
          disableElevation
        >
          {texts.barraProgressaoProposta.botaoCriar}
        </Button>
      ) : (
        <Button
          color="primary"
          variant="contained"
          onClick={proximaEtapa}
          sx={{ position: "fixed", bottom: 50, right: 160 }}
          disableElevation
        >
          {texts.barraProgressaoProposta.botaoProximo}
        </Button>
      )}
      {/* Feedback Erro reconhecimento de voz */}
      <Feedback
        open={feedbackErroReconhecimentoVoz}
        handleClose={() => {
          setFeedbackErroReconhecimentoVoz(false);
        }}
        status={"erro"}
        mensagem={texts.homeGerencia.feedback.feedback12}
        lendo={props.lendo}
         
      />
      {/* Feedback Não navegador incompativel */}
      <Feedback
        open={feedbackErroNavegadorIncompativel}
        handleClose={() => {
          setFeedbackErroNavegadorIncompativel(false);
        }}
        status={"erro"}
        mensagem={texts.homeGerencia.feedback.feedback13}
        lendo={props.lendo}
         
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
        lendo={props.lendo}
         
      />
      {/* Feedback de que não fechou 100% de CCs */}
      <Feedback
        open={feedback100porcentoCcs}
        handleClose={() => {
          setFeedback100porcentoCcs(false);
        }}
        status={"erro"}
        mensagem={texts.barraProgressaoProposta.mensagemFeedbackCcsFaltando}
        lendo={props.lendo}
         
      />
    </>
  );
};

export default BarraProgressaoProposta;
