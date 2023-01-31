import React, { useState, useEffect, useContext } from "react";

import {
  Box,
  Stepper,
  Step,
  StepLabel,
  Typography,
  Button,
} from "@mui/material";

import { useNavigate } from "react-router-dom";

import FormularioPropostaProposta from "../FormularioPropostaProposta/FormularioPropostaProposta";
import FormularioCustosProposta from "../FormularioCustosProposta/FormularioCustosProposta";
import FormularioGeralProposta from "../FormularioGeralProposta/FormularioGeralProposta";
import FormularioEscopoProposta from "../FormularioEscopoProposta/FormularioEscopoProposta";

import DemandaService from "../../service/demandaService";
import EscopoService from "../../service/escopoService";

import FontContext from "../../service/FontContext";

const BarraProgressaoProposta = (props) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);
  const [activeStep, setActiveStep] = useState(0);
  const [skipped, setSkipped] = useState(new Set());
  const steps = props.steps;
  const [ultimoEscopo, setUltimoEscopo] = useState(null);
  var idEscopo = null;

  const [dadosDemanda, setDadosDemanda] = useState({
    titulo: "",
    status: null,
    problema: "",
    proposta: "",
    beneficios: [],
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
    secaoTI: null
  });

  useEffect(() => {
    setDadosDemanda(props.dados);
    console.log(props.dados);
  }, []);

  // Dados da página inicial da criação de demanda
  const [paginaDados, setPaginaDados] = useState({
    titulo: "",
    problema: "",
    proposta: "",
    frequencia: "",
  });

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
    ],
    anexos: [],
  });

  // Lista de benefícios definidos na segunda página da criação de demanda
  const [paginaBeneficios, setPaginaBeneficios] = useState([]);

  // Lista de anexos definidos na terceira página da criação de demanda
  const [paginaArquivos, setPaginaArquivos] = useState([]);

  const navigate = useNavigate();

  // useEffect(() => {
  //   if (!idEscopo) {
  //     idEscopo = 1;
  //     EscopoService.postNew(parseInt(localStorage.getItem("usuarioId"))).then(
  //       (response) => {
  //         idEscopo = response.id;
  //         setUltimoEscopo({ id: idEscopo });
  //       }
  //     );
  //   }
  // }, []);

  useEffect(() => {
    if (ultimoEscopo) {
      setTimeout(() => {
        salvarEscopo(ultimoEscopo.id);
      }, 10000);
    }
  }, [ultimoEscopo]);

  const salvarEscopo = (id) => {
    setUltimoEscopo({
      id: id,
      titulo: paginaDados.titulo,
      problema: paginaDados.problema,
      proposta: paginaDados.proposta,
      frequencia: paginaDados.frequencia,
      beneficios: paginaBeneficios,
    });

    EscopoService.salvarDados(ultimoEscopo).then((res) => {
      console.log("escopo ", res);
    });
  };

  const isStepOptional = (step) => {
    return false;
  };

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

  const handleClick = (newState) => () => {
    setState({ open: true, ...newState });
  };

  const [salvarClick, setSalvarClick] = useState(false);

  const salvarAlteracoes = () => {
    setSalvarClick(true);
    setEditar(false);
  };

  // Função para formatar os benefícios recebidos da página de benefícios para serem adicionados ao banco na criação da demanda
  const formatarBeneficios = () => {
    let listaNova = [];
    for (let beneficio of paginaBeneficios) {
      delete beneficio.id;
      delete beneficio.visible;
      listaNova.push({
        ...beneficio,
        tipoBeneficio: beneficio.tipoBeneficio.toUpperCase(),
      });
    }
    return listaNova;
  };

  // UseEffect para criar a demanda usando os dados recebidos das páginas
  useEffect(() => {
    if (open) {
      if (
        paginaDados.titulo != "" &&
        paginaDados.problema &&
        paginaDados.proposta &&
        paginaDados.frequencia
      ) {
        const demandaFinal = {
          titulo: paginaDados.titulo,
          problema: paginaDados.problema,
          proposta: paginaDados.proposta,
          frequencia: paginaDados.frequencia,
          beneficios: formatarBeneficios(),
          status: "BACKLOG",
        };

        DemandaService.post(
          demandaFinal,
          paginaArquivos,
          parseInt(localStorage.getItem("usuarioId"))
        ).then((e) => {
          navigate("/");
        });
      } else {
        // Fazer feedback de campos obrigatórios faltantes
      }
    }
  }, [open]);

  const [editar, setEditar] = useState(false);

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
          setDados={setDadosDemanda}
          editar={editar}
          setEditar={setEditar}
          salvarClick={salvarClick}
          setSalvarClick={setSalvarClick}
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
        <FormularioGeralProposta gerais={gerais} setGerais={setGerais} />
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
      ) : editar ? (
        <Button
          color="primary"
          variant="contained"
          onClick={salvarAlteracoes}
          sx={{ position: "fixed", bottom: 50, right: 160 }}
          disableElevation
        >
          Salvar
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
