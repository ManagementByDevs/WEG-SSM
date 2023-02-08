import React, { useState, useEffect, useContext } from "react";
import { Box, Stepper, Step, StepLabel, Typography, Button } from "@mui/material";

import { useNavigate } from "react-router-dom";

import FormularioPropostaProposta from "../FormularioPropostaProposta/FormularioPropostaProposta";
import FormularioCustosProposta from "../FormularioCustosProposta/FormularioCustosProposta";
import FormularioGeralProposta from "../FormularioGeralProposta/FormularioGeralProposta";
import FormularioEscopoProposta from "../FormularioEscopoProposta/FormularioEscopoProposta";

import ResponsavelNegocioService from "../../service/responsavelNegocioService";
import ForumService from "../../service/forumService";
import BUService from "../../service/buService";

import FontContext from "../../service/FontContext";
import beneficioService from "../../service/beneficioService";

const BarraProgressaoProposta = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Variáveis utilizadas para controlar a barra de progessão na criação da demanda
  const [activeStep, setActiveStep] = useState(0);
  const [skipped, setSkipped] = useState(new Set());
  const steps = props.steps;

  // Variáveis utilizadas para salvar um escopo de uma demanda
  var idEscopo = null;
  const [ultimoEscopo, setUltimoEscopo] = useState(null);

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
  }, []);

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
    responsaveisNegocio: [
      {
        nome: "",
        area: "",
        visible: true,
      },
    ]
  });

  // Função para pular passos opcionais
  const isStepOptional = (step) => {
    return false;
  };

  // Função para pular passos já realizados
  const isStepSkipped = (step) => {
    return skipped.has(step);
  };

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
  const [custos, setCustos] = useState([
    {
      despesas: [
        {
          tipoDespesa: "",
          perfilDespesa: "",
          periodoExecucao: "",
          horas: "",
          valorHora: "",
          total: "",
          visible: true,
        },
      ],
      ccs: [
        {
          codigo: "",
          porcentagem: "",
          visible: true,
        },
      ],
      visible: true,
    },
  ]);

  const setDespesas = (index) => {
    let custosNovos = [...custos];

    custosNovos[index].despesas.push({
      tipoDespesa: "",
      perfilDespesa: "",
      periodoExecucao: "",
      horas: "",
      valorHora: "",
      total: "",
      visible: true,
    });

    setCustos(custosNovos);
  };

  const setCcs = (index) => {
    let custosNovos = [...custos];
    custosNovos[index].ccs.push({
      codigo: "",
      porcentagem: "",
      visible: true,
    });
    setCustos(custosNovos);
  }

  const deletarLinhaCustos = (index, indexCusto) => {
    let custosNovos = [...custos];
    custosNovos[indexCusto].despesas.splice(index, 1);
    setCustos(custosNovos);
  };

  const deletarLinhaCCs = (index, indexCusto) => {
    let custosNovos = [...custos];
    custosNovos[indexCusto].ccs.splice(index, 1);
    setCustos(custosNovos);
  };

  // Função para excluir os benefícios retirados da lista que foram criados no banco
  const excluirBeneficios = () => {
    for (const beneficio of listaBeneficiosExcluidos) {
      beneficioService.delete(beneficio.id).then((response) => { })
    }
  }

  // UseEffect para criação da proposta
  useEffect(() => {
    if (open) {

      let listaNova = [];
      for (const responsavel of gerais.responsaveisNegocio) {
        ResponsavelNegocioService.post(responsavel).then((response) => {
          listaNova.push(response);
        })
      }

      excluirBeneficios();

      const propostaFinal = {
        demanda: dadosDemanda,
        titulo: dadosDemanda.titulo,
        status: "ASSESSMENT_APROVACAO",
        problema: dadosDemanda.problema,
        proposta: dadosDemanda.proposta,
        frequencia: dadosDemanda.frequencia,
        anexo: dadosDemanda.anexo,
        solicitante: dadosDemanda.solicitante,
        analista: dadosDemanda.analista,
        gerente: dadosDemanda.gerente,
        buSolicitante: dadosDemanda.buSolicitante,
        busBeneficiadas: dadosDemanda.busBeneficiadas,
        data: dadosDemanda.data,
        departamento: dadosDemanda.departamento,
        forum: dadosDemanda.forum,
        secaoTI: dadosDemanda.secaoTI,
        tamanho: dadosDemanda.tamanho,
        responsaveisNegocio: listaNova,
        beneficios: formatarBeneficios(listaBeneficios)
      }
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
          setDespesas={setDespesas}
          setCcs={setCcs}
          deletarLinhaCustos={deletarLinhaCustos}
          deletarLinhaCCs={deletarLinhaCCs}
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
          onClick={handleClick}
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
