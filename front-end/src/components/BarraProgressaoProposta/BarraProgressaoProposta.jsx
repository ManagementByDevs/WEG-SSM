import React, { useState, useEffect, useContext } from "react";
import { Box, Stepper, Step, StepLabel, Typography, Button } from "@mui/material";

import { useNavigate } from "react-router-dom";

import FormularioPropostaProposta from "../FormularioPropostaProposta/FormularioPropostaProposta";
import FormularioCustosProposta from "../FormularioCustosProposta/FormularioCustosProposta";
import FormularioGeralProposta from "../FormularioGeralProposta/FormularioGeralProposta";
import FormularioEscopoProposta from "../FormularioEscopoProposta/FormularioEscopoProposta";

import ForumService from "../../service/forumService";
import BUService from "../../service/buService";

import FontContext from "../../service/FontContext";
import beneficioService from "../../service/beneficioService";
import propostaService from "../../service/propostaService";
import DemandaService from "../../service/demandaService";
import ResponsavelNegocioService from "../../service/responsavelNegocioService";
import CustosService from "../../service/custosService";

const BarraProgressaoProposta = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Variáveis utilizadas para controlar a barra de progessão na criação da demanda
  const [activeStep, setActiveStep] = useState(0);
  const [skipped, setSkipped] = useState(new Set());
  const steps = props.steps;

  // Navigate utilizado para navegar para outras páginas
  const navigate = useNavigate();

  // Variáveis utilizadas para salvar um escopo de uma demanda
  var idEscopo = null;
  const [ultimoEscopo, setUltimoEscopo] = useState(null);
  const [mudancasFeitas, setMudancasFeitas] = useState(false);

  let variaveisIniciais = false;

  // Variável utilizada para armazenar a lista de fóruns
  const [listaForuns, setListaForuns] = useState([]);

  // Variável utilizada para armazenar a lista de BUs
  const [listaBU, setListaBU] = useState([]);

  // Variável para armazenar os dados da demanda
  const [dadosDemanda, setDadosDemanda] = useState({
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
    tamanho: ""
  });

  // Variável utilizada para armazenar a lista de benefícios
  const [listaBeneficios, setListaBeneficios] = useState([]);

  // Variável utilizada para armazenar a lista de benefícios excluídos
  const [listaBeneficiosExcluidos, setListaBeneficiosExcluidos] = useState([]);

  // UseEffect utilizado para pegar os dados da demanda e pegar os fóruns e BUs
  useEffect(() => {
    setDadosDemanda(props.dados);
    pesquisarBUs();
    pesquisarForuns();
    criarDadosIniciais();
  }, []);

  useEffect(() => {
    if (!idEscopo) {
      if (!location.state) {
        if (mudancasFeitas) {
          
        }
      }
    }
  }, [mudancasFeitas]);

  // UseEffect para formatar os benefícios recebidos do banco para os necessários na edição
  useEffect(() => {
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
  }, [dadosDemanda]);

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

  // Função para pular passos opcionais
  const isStepOptional = (step) => {
    return false;
  };

  // Função para pular passos já realizados
  const isStepSkipped = (step) => {
    return skipped.has(step);
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
        })
      }
    }
  }

  // Função para passar para próxima página
  const handleNext = () => {
    if (activeStep === steps.length - 1) {
      criarDemanda();
    }
    let newSkipped = skipped;
    if (isStepSkipped(activeStep)) {
      newSkipped = new Set(newSkipped.values());
      newSkipped.delete(activeStep);
    }
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setSkipped(newSkipped);
  };

  // Função para voltar para página anterior
  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  // Função para criar a demanda
  const criarDemanda = () => {
    handleClick(true);
  };

  const [state, setState] = React.useState({
    open: false,
  });

  const { open } = state;

  // Função para fechar modal
  const handleClick = (newState) => () => {
    setState({ open: true, ...newState });
  };

  // Variável utilizada para salvar alterações caso o click seja acionado
  const [salvarClick, setSalvarClick] = useState(false);

  // Função para salvar alterações na criação da proposta
  const salvarAlteracoes = () => {
    setSalvarClick(true);
    setEditar(false);
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

  // Função para formatar os benefícios recebidos da página de benefícios para serem adicionados ao banco na criação da demanda
  const formatarBeneficios = () => {
    let listaNova = [];
    for (let beneficio of listaBeneficios) {
      delete beneficio.visible;
      listaNova.push({
        ...beneficio,
        tipoBeneficio: beneficio.tipoBeneficio.toUpperCase(),
      });
    }
    return listaNova;
  };

  // Variável utilizada para realizar edições
  const [editar, setEditar] = useState(false);

  // Variável para guardar os custos 
  const [custos, setCustos] = useState([]);

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

  /** Função para filtrar a lista de anexos totais e retornar somente os anexos já salvos no banco */
  const pegarAnexosSalvos = () => {
    let listaNova = [];
    for (const anexo of dadosDemanda.anexo) {
      if (anexo.id) {
        listaNova.push(anexo);
      }
    }
    return listaNova;
  }

  /** Função para filtrar a lista de anexos totais e retornar somente os anexos não salvos no banco */
  const pegarAnexosNovos = () => {
    let listaNova = [];
    for (const anexo of dadosDemanda.anexo) {
      if (!anexo.id) {
        listaNova.push(anexo);
      }
    }
    return listaNova;
  }

  /** Função para tirar os anexos não salvos dos dados da demanda para criação de proposta */
  const retirarAnexosDemanda = () => {
    let demandaNova = { ...dadosDemanda };
    demandaNova.anexo = pegarAnexosSalvos();
    return demandaNova;
  }

  const criarProposta = () => {
    excluirBeneficios();

    const propostaFinal = {
      demanda: retirarAnexosDemanda(),
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
      anexo: pegarAnexosSalvos()
    }

    propostaService.post(propostaFinal, pegarAnexosNovos()).then((response) => {
      DemandaService.atualizarStatus(dadosDemanda.id, "ASSESSMENT_APROVACAO").then((data) => {
        localStorage.setItem("tipoFeedback", "5");
        navigate("/");
      });
    });
  }

  // UseEffect para criação da proposta
  useEffect(() => {
    if (open) {
      criarProposta();
    }
  }, [open]);

  return (
    <>
      <Stepper activeStep={activeStep}>
        {steps.map((label, index) => {
          const stepProps = {};
          const labelProps = {};
          if (isStepOptional(index)) {
            labelProps.optional = (
              <Typography variant="caption">Optional</Typography>
            );
          }
          if (isStepSkipped(index)) {
            stepProps.completed = false;
          }
          return (
            <Step key={label} {...stepProps}>
              <StepLabel {...labelProps}>{label}</StepLabel>
            </Step>
          );
        })}
      </Stepper>
      {activeStep == 0 && (
        <FormularioPropostaProposta
          dados={dadosDemanda}
          setDadosDemanda={setDadosDemanda}
          editar={editar}
          setEditar={setEditar}
          salvarClick={salvarClick}
          setSalvarClick={setSalvarClick}

          beneficios={listaBeneficios}
          setBeneficios={setListaBeneficios}
          beneficiosExcluidos={listaBeneficiosExcluidos}
          setBeneficiosExcluidos={setListaBeneficiosExcluidos}

          listaForuns={listaForuns}
          listaBU={listaBU}
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
        onClick={handleBack}
        sx={{ mr: 1, position: "fixed", bottom: 50, left: 160 }}
        disableElevation
      >
        Voltar
      </Button>
      <Box sx={{ flex: "1 1 auto" }} />
      {activeStep === steps.length - 1 ? (
        <Button
          color="primary"
          variant="contained"
          onClick={criarProposta}
          sx={{ position: "fixed", bottom: 50, right: 160 }}
          disableElevation
        >
          Criar
        </Button>
      ) : (
        <Button
          color="primary"
          variant="contained"
          onClick={handleNext}
          sx={{ position: "fixed", bottom: 50, right: 160 }}
          disableElevation
        >
          Próximo
        </Button>
      )}
    </>
  );
};

export default BarraProgressaoProposta;
