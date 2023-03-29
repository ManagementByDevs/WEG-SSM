import React, { useState, useEffect, useContext } from "react";
import { useNavigate, useLocation } from "react-router-dom";

import { Box, Stepper, Step, StepLabel, Button } from "@mui/material";

import FormularioPropostaProposta from "../FormularioPropostaProposta/FormularioPropostaProposta";
import FormularioCustosProposta from "../FormularioCustosProposta/FormularioCustosProposta";
import FormularioGeralProposta from "../FormularioGeralProposta/FormularioGeralProposta";
import FormularioEscopoProposta from "../FormularioEscopoProposta/FormularioEscopoProposta";

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

// Componente utilizado para criação da proposta, redirecionando para as etapas respectivas
const BarraProgressaoProposta = (props) => {

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Location utilizado para pegar informações passadas por parâmetro na URL
  const location = useLocation();

  // Variáveis utilizadas para controlar a barra de progessão na criação da demanda
  const [activeStep, setActiveStep] = useState(0);

  /** Lista de etapas usadas na criação de proposta */
  const etapasProposta = [texts.barraProgressaoProposta.proposta, texts.barraProgressaoProposta.escopo, texts.barraProgressaoProposta.custo, texts.barraProgressaoProposta.gerais];

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
    historicoDemanda: []
  });

  // Variável utilizada para armazenar a lista de benefícios
  const [listaBeneficios, setListaBeneficios] = useState([]);

  // Variável utilizada para armazenar a lista de benefícios excluídos
  const [listaBeneficiosExcluidos, setListaBeneficiosExcluidos] = useState([]);

  // Variável para guardar os custos 
  const [custos, setCustos] = useState([]);

  // UseState com o escopo da proposta (texto digitado no editor de texto, vem em formato HTML)
  const [escopo, setEscopo] = useState("");

  // Dados gerais definidos na página de dados gerais da criação de demanda
  const [gerais, setGerais] = useState({
    periodoExecucacaoInicio: "",
    periodoExecucacaoFim: "",
    qtdPaybackSimples: "",
    unidadePaybackSimples: "",
    ppm: "",
    linkJira: "",
    responsaveisNegocio: []
  });

  const [mudancasFeitas, setMudancasFeitas] = useState(false);

  // UseEffect utilizado para pegar os dados da demanda e pegar os fóruns e BUs
  useEffect(() => {
    setDadosDemanda(props.dados);
    pesquisarBUs();
    pesquisarForuns();
    pesquisarSecoesTI();
  }, []);

  useEffect(() => {
    if (!idEscopo) {
      if (!location.state.tabelaCustos) {
        if (mudancasFeitas) {
          idEscopo = 1;

          EscopoPropostaService.buscarPorDemanda(dadosDemanda.id).then((data) => {
            if (data.length == 0) {
              receberBeneficios();
              let escopo = retornaObjetoProposta();
              delete escopo.historicoProposta;
              delete escopo.status;
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
          });
        }
      }
    }
  }, [mudancasFeitas]);

  useEffect(() => {
    if (listaBeneficios && dadosDemanda && gerais && custos) {
      setTimeout(() => {
        setMudancasFeitas(true);
      }, 1000)
    }
  }, [dadosDemanda, gerais, custos, listaBeneficios]);

  // UseEffect utilizado para salvar o escopo a cada 5 segundos
  useEffect(() => {
    if (ultimoEscopo) {
      setTimeout(() => {
        salvarEscopo();
      }, 5000);
    }
  }, [ultimoEscopo]);

  const carregarEscopoSalvo = (escopo) => {
    setDadosDemanda({
      id: escopo.demanda.id,
      titulo: escopo.titulo,
      status: null,
      problema: escopo.problema,
      proposta: escopo.proposta,
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
      historicoDemanda: escopo.historicoDemanda
    });

    setGerais({
      periodoExecucacaoInicio: new Date(escopo.inicioExecucao).toISOString().slice(0, 10),
      periodoExecucacaoFim: new Date(escopo.fimExecucao).toISOString().slice(0, 10),
      qtdPaybackSimples: escopo.paybackValor,
      unidadePaybackSimples: escopo.paybackTipo,
      ppm: escopo.codigoPPM,
      linkJira: escopo.linkJira,
      responsaveisNegocio: escopo.responsavelNegocio
    });

    setListaBeneficios(escopo.beneficios);
    setCustos(escopo.tabelaCustos);
    
    setCarregamento(false);
  }

  /** Função de salvamento de escopo, usando a variável "ultimoEscopo" e atualizando ela com os dados da página */
  const salvarEscopo = () => {
    try {
      let escopo = retornaObjetoProposta();
      delete escopo.historicoProposta;
      delete escopo.status;
      escopo.id = ultimoEscopo.id;
      EscopoPropostaService.salvarDados(escopo, receberIdsAnexos()).then((response) => {
        setUltimoEscopo(response);
      });
    } catch (error) { }
  };

  /** Função para criar as chaves estrangeiras necessárias para o escopo no banco de dados */
  const criarDadosIniciais = () => {
    if (!variaveisIniciais) {
      variaveisIniciais = true;

      if (gerais.responsaveisNegocio.length == 0) {
        ResponsavelNegocioService.post({ nome: "", area: "" }).then((response) => {
          setGerais({ ...gerais, responsaveisNegocio: [...gerais.responsaveisNegocio, response] });
        });
      }

      if (custos.length == 0) {
        CustosService.postTabela({
          custos: [{
            tipoDespesa: "",
            perfilDespesa: "",
            periodoExecucao: "",
            horas: "",
            valorHora: ""
          }],
          ccs: [{
            codigo: "",
            porcentagem: ""
          }]
        }).then((response) => {
          setCustos([...custos, response]);
          setCarregamento(false);
        })
      }
    }
  }

  const receberBeneficios = () => {
    const aux = dadosDemanda?.beneficios?.map((beneficio) => {
      return {
        id: beneficio.id,
        tipoBeneficio:
          beneficio.tipoBeneficio?.charAt(0) +
          beneficio.tipoBeneficio
            ?.substring(1, beneficio.tipoBeneficio?.length)
            ?.toLowerCase() || "Real",
        valor_mensal: beneficio.valor_mensal,
        moeda: beneficio.moeda,
        memoriaCalculo: beneficio.memoriaCalculo,
        visible: true,
      };
    });
    setListaBeneficios(aux);
  }

  // Função para passar para próxima página
  const proximaEtapa = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };

  // Função para voltar para página anterior
  const voltarEtapa = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  // Função para pesquisar os fóruns do banco e salvar na lista para o select
  const pesquisarForuns = () => {
    ForumService.getAll().then((response) => {
      setListaForuns(response);
    })
  }

  // Função para pesquisar as BUs do banco e salvar na lista para o select
  const pesquisarBUs = () => {
    BUService.getAll().then((response) => {
      setListaBU(response);
    })
  }

  /** Função para pesquisar as seções de TI do banco para uso no select */
  const pesquisarSecoesTI = () => {
    SecaoTIService.getAll().then((response) => {
      setListaSecoesTI(response);
    })
  }

  // Função para formatar os benefícios recebidos da página de benefícios para serem adicionados ao banco na criação da demanda
  const formatarBeneficios = () => {
    try {
      let listaNova = [];
      for (let beneficio of listaBeneficios) {
        listaNova.push({
          id: beneficio.id,
          tipoBeneficio: beneficio.tipoBeneficio.toUpperCase(),
          valor_mensal: beneficio.valor_mensal,
          moeda: beneficio.moeda,
          memoriaCalculo: beneficio.memoriaCalculo
        });
      }
      return listaNova;
    } catch (error) {
      return [];
    }
  };

  // Função para excluir os benefícios retirados da lista que foram criados no banco
  const excluirBeneficios = () => {
    for (const beneficio of listaBeneficiosExcluidos) {
      beneficioService.delete(beneficio.id).then((response) => { })
    }
  }

  /** Função para formatar a lista de responsáveis do negócio, retirando o atributo "visible" */
  const formatarResponsaveisNegocio = () => {
    let listaNova = [];
    for (const responsavelNegocio of gerais.responsaveisNegocio) {
      listaNova.push({ nome: responsavelNegocio.nome, area: responsavelNegocio.area });
    }
    return listaNova;
  }

  /** Função para formatar os custos dentro de cadas tabela de custos (retirar o atributo "total") */
  const formatarCustos = () => {
    let listaNova = [];
    for (const tabelaCustos of custos) {
      let listaCustos = [];
      for (const custo of tabelaCustos.custos) {
        listaCustos.push({ tipoDespesa: custo.tipoDespesa, perfilDespesa: custo.perfilDespesa, periodoExecucao: custo.periodoExecucao, horas: custo.horas, valorHora: custo.valorHora })
      }

      let listaCCs = [...tabelaCustos.ccs];
      listaNova.push({ custos: listaCustos, ccs: listaCCs });
    }
    return listaNova;
  }

  /** Função que retorna os IDs de todos os anexos da proposta */
  const receberIdsAnexos = () => {
    let listaIds = [];
    for (const anexo of dadosDemanda.anexo) {
      listaIds.push(anexo.id);
    }
    return listaIds;
  }

  /** Função para montar um objeto de proposta para salvamento de escopos e propostas */
  const retornaObjetoProposta = () => {
    const objeto = {
      demanda: dadosDemanda,
      titulo: dadosDemanda.titulo,
      status: "ASSESSMENT_APROVACAO",
      problema: dadosDemanda.problema,
      proposta: dadosDemanda.proposta,
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
      beneficios: formatarBeneficios(listaBeneficios),
      tabelaCustos: formatarCustos(),
      responsavelNegocio: formatarResponsaveisNegocio(),
      inicioExecucao: gerais.periodoExecucacaoInicio,
      fimExecucao: gerais.periodoExecucacaoFim,
      paybackValor: gerais.qtdPaybackSimples,
      paybackTipo: gerais.unidadePaybackSimples,
      codigoPPM: gerais.ppm,
      linkJira: gerais.linkJira,
      historicoProposta: dadosDemanda.historicoDemanda,
    }
    return objeto;
  }

  /** Função para criar a proposta no banco de dados, também atualizando o status da demanda e excluindo o escopo da proposta */
  const criarProposta = () => {
    excluirBeneficios();

    propostaService.post(retornaObjetoProposta(), receberIdsAnexos()).then((response) => {
      // DemandaService.atualizarStatus(dadosDemanda.id, "ASSESSMENT_APROVACAO").then(() => {
        EscopoPropostaService.excluirEscopo(ultimoEscopo.id).then(() => {

          // Salvamento de histórico
          ExportPdfService.exportProposta(response.id).then((file) => {
            let arquivo = new Blob([file], { type: "application/pdf" });
            propostaService.addHistorico(response.id, "Proposta Criada", arquivo, parseInt(localStorage.getItem("usuarioId"))).then(() => {
              localStorage.setItem("tipoFeedback", "5");
              navigate("/");
            })
          });
        })
      // });
    });
  }

  return (
    <>
      <Stepper activeStep={activeStep} sx={{minWidth: "60rem"}}>
        {etapasProposta.map((label, index) => {
          const stepProps = {};
          const labelProps = {};
          return (
            <Step key={label} {...stepProps}>
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
          beneficiosExcluidos={listaBeneficiosExcluidos}
          setBeneficiosExcluidos={setListaBeneficiosExcluidos}

          listaForuns={listaForuns}
          listaBU={listaBU}
          listaSecoesTI={listaSecoesTI}
        />
      )}
      {activeStep == 1 && (
        <FormularioEscopoProposta escopo={escopo} setEscopo={setEscopo} />
      )}
      {activeStep == 2 && (
        <FormularioCustosProposta
          custos={custos}
          setCustos={setCustos}
        />
      )}
      {activeStep == 3 && (
        <FormularioGeralProposta
          gerais={gerais}
          setGerais={setGerais}
          dados={dadosDemanda}
          setDados={setDadosDemanda}
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
    </>
  );
};

export default BarraProgressaoProposta;
