import React, { useState, useEffect, useContext } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import {
  Box,
  Stepper,
  Step,
  StepLabel,
  Typography,
  Button,
} from "@mui/material";

import FormularioDadosDemanda from "../FormularioDadosDemanda/FormularioDadosDemanda";
import FormularioBeneficiosDemanda from "../FormularioBeneficiosDemanda/FormularioBeneficiosDemanda";
import FormularioAnexosDemanda from "../FormularioAnexosDemanda/FormularioAnexosDemanda";
import Feedback from "../Feedback/Feedback";

import DemandaService from "../../service/demandaService";
import EscopoService from "../../service/escopoService";
import ModalConfirmacao from "../ModalConfirmacao/ModalConfirmacao";

import FontContext from "../../service/FontContext";

const BarraProgressaoDemanda = (props) => {
  // Contexto para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Location utilizado para setar o state utilizado para verificação de lógica
  const location = useLocation();

  // Navigate utilizado para navegar para outras páginas
  const navigate = useNavigate();

  // Variáveis utilizadas para controlar a barra de progessão na criação da demanda
  const [activeStep, setActiveStep] = useState(0);
  const [skipped, setSkipped] = useState(new Set());
  const steps = props.steps;

  // Variável utilizada para abrir o modal de feedback de dados faltantes
  const [feedbackDadosFaltantes, setFeedbackDadosFaltantes] = useState(false);

  // Variáveis utilizadas para salvar um escopo de uma demanda
  var idEscopo = null;
  const [ultimoEscopo, setUltimoEscopo] = useState(null);

  // Dados da página inicial da criação de demanda
  const [paginaDados, setPaginaDados] = useState({
    titulo: "",
    problema: "",
    proposta: "",
    frequencia: "",
  });

  // Lista de benefícios definidos na segunda página da criação de demanda
  const [paginaBeneficios, setPaginaBeneficios] = useState([]);

  // Lista de anexos definidos na terceira página da criação de demanda
  const [paginaArquivos, setPaginaArquivos] = useState([]);

  // Variável utilizada para abrir o modal de confirmação de criação de demanda
  const [modalConfirmacao, setOpenConfirmacao] = useState(false);

  // Variável utilizada para abrir o modal de saída de demanda
  const [modalSairDemanda, setModalSairDemanda] = useState(false);

  // UseEffect utilizado para criar um escopo ou receber um escopo do banco ao entrar na página
  useEffect(() => {
    if (!idEscopo) {
      if (!location.state) {
        idEscopo = 1;
        EscopoService.postNew(parseInt(localStorage.getItem("usuarioId"))).then(
          (response) => {
            idEscopo = response.id;
            setUltimoEscopo({ id: idEscopo });
          }
        );
      } else {
        idEscopo = location.state;
        EscopoService.buscarPorId(location.state).then((response) => {
          setPaginaDados({
            titulo: response.titulo,
            problema: response.problema,
            proposta: response.proposta,
            frequencia: response.frequencia,
          });
          receberBeneficios(response.beneficios);
          receberArquivos(response.anexo);
          setUltimoEscopo({
            id: response.id,
            titulo: response.titulo,
            problema: response.problema,
            proposta: response.proposta,
            frequencia: response.frequencia,
            beneficios: formatarBeneficios(response.beneficios),
          });
        });
      }
    }
  }, []);

  // UseEffect utilizado para salvar o escopo a cada 5 segundos
  useEffect(() => {
    if (ultimoEscopo) {
      setTimeout(() => {
        salvarEscopo(ultimoEscopo.id);
      }, 5000);
    }
  }, [ultimoEscopo]);

  // Função para formatar os benefícios recebidos no banco para a lista da página de edição
  const receberBeneficios = (beneficios) => {
    let listaNova = [];
    for (let beneficio of beneficios) {
      let tipoNovo =
        beneficio.tipoBeneficio.charAt(0) +
        beneficio.tipoBeneficio
          .substring(1, beneficio.tipoBeneficio.length)
          .toLowerCase();
      listaNova.push({
        id: beneficio.id,
        tipoBeneficio: tipoNovo,
        valor_mensal: beneficio.valor_mensal,
        moeda: beneficio.moeda,
        memoriaCalculo: beneficio.memoriaCalculo,
        visible: true,
      });
    }
    setPaginaBeneficios(listaNova);
  };

  // Função para formatar os arquivos recebidos no banco para a lista da página de edição
  const receberArquivos = (arquivos) => {
    let listaArquivos = [];
    for (let arquivo of arquivos) {
      listaArquivos.push(
        new File([arquivo.dados], arquivo.nome, { type: arquivo.tipo })
      );
    }
    setPaginaArquivos(listaArquivos);
  };

  // Função de salvamento de escopo, usando a variável "ultimoEscopo" e atualizando ela com os dados da página
  const salvarEscopo = (id) => {
    if (
      paginaDados.titulo !== "" ||
      (paginaDados.titulo !== null && paginaDados.problema !== "") ||
      (paginaDados.problema !== null && paginaDados.proposta !== "") ||
      (paginaDados.proposta !== null && paginaDados.frequencia !== "") ||
      paginaDados.frequencia !== null
    ) {
      setUltimoEscopo({
        id: id,
        titulo: paginaDados.titulo,
        problema: paginaDados.problema,
        proposta: paginaDados.proposta,
        frequencia: paginaDados.frequencia,
        beneficios: formatarBeneficios(paginaBeneficios),
      });

      try {
        EscopoService.salvarDados(ultimoEscopo).then((response) => {
          //Confirmação de salvamento (se sobrar tempo)
        });
      } catch (error) {}
    }
  };

  // Função para atualizar os anexos de um escopo quando um anexo for adicionado / removido
  const salvarAnexosEscopo = () => {
    if (paginaArquivos.length > 0) {
      EscopoService.salvarAnexosEscopo(ultimoEscopo.id, paginaArquivos).then(
        (response) => {}
      );
    } else {
      EscopoService.removerAnexos(ultimoEscopo.id).then((response) => {});
    }
  };

  // Função para excluir o escopo determinado quando a demanda a partir dele for criada
  const excluirEscopo = () => {
    EscopoService.excluirEscopo(ultimoEscopo.id).then((response) => {});
  };

  // Função para pular passos opcionais
  const isStepOptional = (step) => {
    return false;
  };

  // Função para pular passos já realizados
  const isStepSkipped = (step) => {
    return skipped.has(step);
  };

  // Função para ir para o próximo passo, que dependendo poderá criar a demanda
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

  // Função para voltar para o passo anterior
  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  // Função para verificação de passos obrigatórios para a criação da demanda
  const handleSkip = () => {
    if (!isStepOptional(activeStep)) {
      throw new Error("Você não pode pular um passo que não é opcional!");
    }

    setActiveStep((prevActiveStep) => prevActiveStep + 1);

    setSkipped((prevSkipped) => {
      const newSkipped = new Set(prevSkipped.values());
      newSkipped.add(activeStep);
      return newSkipped;
    });
  };

  // Função para resetar o stepper
  const handleReset = () => {
    setActiveStep(0);
  };

  // Função para criar uma demanda
  const criarDemanda = () => {
    handleClick(true);
  };

  const [state, setState] = useState({
    open: false,
  });

  const { open } = state;

  // Função para fechar modal
  const handleClick = (newState) => () => {
    setState({ open: true, ...newState });
  };

  // Função para abrir o modal de confirmação de criação de demanda
  const abrirModalConfirmacao = () => {
    setOpenConfirmacao(true);
  };

  // Função para abrir o modal de saída de demanda
  const abrirModalSairDemanda = () => {
    setModalSairDemanda(true);
  };

  // Função para formatar os benefícios recebidos da página de benefícios para serem adicionados ao banco na criação da demanda
  const formatarBeneficios = (listaBeneficios) => {
    let listaNova = [];

    for (let beneficio of listaBeneficios) {
      if (beneficio.visible) {
        listaNova.push({
          id: beneficio.id,
          memoriaCalculo: beneficio.memoriaCalculo,
          moeda: beneficio.moeda,
          valor_mensal: beneficio.valor_mensal,
          tipoBeneficio: beneficio.tipoBeneficio.toUpperCase(),
        });
      }
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
        let demandaFinal = {
          titulo: paginaDados.titulo,
          problema: paginaDados.problema,
          proposta: paginaDados.proposta,
          frequencia: paginaDados.frequencia,
          beneficios: formatarBeneficios(paginaBeneficios),
          status: "BACKLOG_REVISAO",
        };

        DemandaService.post(
          demandaFinal,
          paginaArquivos,
          parseInt(localStorage.getItem("usuarioId"))
        ).then((e) => {
          direcionarHome(true);
          excluirEscopo();
        });
      } else {
        setFeedbackDadosFaltantes(true);
      }
    }
  }, [open]);

  // Função para direcionar o usuário para a tela de home
  const direcionarHome = (feedbackDemanda) => {
    localStorage.removeItem("tipoFeedback");

    localStorage.setItem("tipoFeedback", "1");

    navigate("/");
  };

  // HandleClose utilizado para modais
  const handleClose = () => {
    setState({ ...state, open: false });
  };

  return (
    <>
      {/* Stepper utilizado para os passos da criação e a barra de progressão */}
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
              <StepLabel {...labelProps}>
                <Typography fontSize={FontConfig.default}>{label}</Typography>
              </StepLabel>
            </Step>
          );
        })}
      </Stepper>
      {activeStep == 0 && (
        <FormularioDadosDemanda dados={paginaDados} setDados={setPaginaDados} />
      )}
      {activeStep == 1 && (
        <FormularioBeneficiosDemanda
          dados={paginaBeneficios}
          setDados={setPaginaBeneficios}
        />
      )}
      {activeStep == 2 && (
        <FormularioAnexosDemanda
          salvarEscopo={salvarAnexosEscopo}
          dados={paginaArquivos}
          setDados={setPaginaArquivos}
        />
      )}
      <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
        <Button
          variant="outlined"
          color="tertiary"
          disabled={activeStep === 0}
          onClick={handleBack}
          sx={{ mr: 1 }}
          disableElevation
        >
          <Typography fontSize={FontConfig.default}>Voltar</Typography>
        </Button>
        <Box sx={{ flex: "1 1 auto" }} />
        {isStepOptional(activeStep) && (
          <Button color="inherit" onClick={handleSkip} sx={{ mr: 1 }}>
            <Typography fontSize={FontConfig.default}>Pular</Typography>
          </Button>
        )}

        {/* Verificações para mudar texto de botões de acordo com os passos */}
        {activeStep === steps.length - 1 ? (
          <Button
            color="primary"
            variant="contained"
            onClick={abrirModalConfirmacao}
            disableElevation
          >
            <Typography fontSize={FontConfig.default}>Criar</Typography>
          </Button>
        ) : (
          <Button
            color="primary"
            variant="contained"
            onClick={handleNext}
            disableElevation
          >
            <Typography fontSize={FontConfig.default}>Próximo</Typography>
          </Button>
        )}
        {modalConfirmacao && (
          <ModalConfirmacao
            open={modalConfirmacao}
            setOpen={setOpenConfirmacao}
            textoModal={"enviarDemanda"}
            textoBotao={"enviar"}
            onConfirmClick={handleClick()}
          />
        )}
        {modalSairDemanda && (
          <ModalConfirmacao
            open={modalSairDemanda}
            setOpen={setModalSairDemanda}
            textoModal={"sairCriacao"}
            textoBotao={"sim"}
            onConfirmClick={handleClick()}
          />
        )}
      </Box>

      {/* Feedback de dados faltantes */}
      <Feedback
        open={feedbackDadosFaltantes}
        handleClose={() => {
          setFeedbackDadosFaltantes(false);
        }}
        status={"erro"}
        mensagem={"Preencha todos os campos obrigatórios!"}
      />
    </>
  );
};

export default BarraProgressaoDemanda;
