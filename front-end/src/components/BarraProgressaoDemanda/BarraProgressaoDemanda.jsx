import React, { useState, useEffect, useContext } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { Box, Stepper, Step, StepLabel, Typography, Button } from "@mui/material";

import FormularioDadosDemanda from "../FormularioDadosDemanda/FormularioDadosDemanda";
import FormularioBeneficiosDemanda from "../FormularioBeneficiosDemanda/FormularioBeneficiosDemanda";
import FormularioAnexosDemanda from "../FormularioAnexosDemanda/FormularioAnexosDemanda";
import Feedback from "../Feedback/Feedback";

import DemandaService from "../../service/demandaService";
import EscopoService from "../../service/escopoService";
import ModalConfirmacao from "../ModalConfirmacao/ModalConfirmacao";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

/** Componente principal usado para criação de demanda, redirecionando para as etapas respectivas e
 * salvando a demanda e escopos no banco de dados
 */
const BarraProgressaoDemanda = () => {

  // Contexto para alterar o idioma
  const {texts} = useContext(TextLanguageContext);

  // Contexto para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Location utilizado para setar o state utilizado para verificação de lógica
  const location = useLocation();

  // Navigate utilizado para navegar para outras páginas
  const navigate = useNavigate();

  // Variáveis utilizadas para controlar a barra de progessão na criação da demanda
  const [etapaAtiva, setEtapaAtiva] = useState(0);
  const steps = [`${texts.barraProgressaoDemanda.steps.dados}`, `${texts.barraProgressaoDemanda.steps.beneficios}`, `${texts.barraProgressaoDemanda.steps.anexos}`];

  // Variável utilizada para abrir o modal de feedback de dados faltantes
  const [feedbackDadosFaltantes, setFeedbackDadosFaltantes] = useState(false);

  // Variáveis utilizadas para salvar um escopo de uma demanda
  var idEscopo = null;
  const [ultimoEscopo, setUltimoEscopo] = useState(null);

  const [escreveu, setEscreveu] = useState(false);

  // Dados da página inicial da criação de demanda
  const [paginaDados, setPaginaDados] = useState({ titulo: "", problema: "", proposta: "", frequencia: "", });

  // Lista de benefícios definidos na segunda página da criação de demanda
  const [paginaBeneficios, setPaginaBeneficios] = useState([]);

  // Lista de anexos definidos na terceira página da criação de demanda
  const [paginaArquivos, setPaginaArquivos] = useState([]);

  // Variável utilizada para abrir o modal de confirmação de criação de demanda
  const [modalConfirmacao, setOpenConfirmacao] = useState(false);

  // UseEffect utilizado para criar um escopo ou receber um escopo do banco ao entrar na página
  useEffect(() => {
    if (!idEscopo) {
      if (!location.state) {
        if (escreveu) {
          idEscopo = 1;
          EscopoService.postNew(
            parseInt(localStorage.getItem("usuarioId"))
          ).then((response) => {
            idEscopo = response.id;
            setUltimoEscopo({ id: idEscopo });
          });
        }
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
  }, [escreveu]);

  useEffect(() => {
    if (
      paginaDados.frequencia !== "" ||
      paginaDados.proposta !== "" ||
      paginaDados.problema !== "" ||
      paginaDados.titulo !== ""
    ) {
      setEscreveu(true);
    }
  }, [paginaDados]);

  // UseEffect utilizado para salvar o escopo a cada 5 segundos
  useEffect(() => {
    if (ultimoEscopo) {
      setTimeout(() => {
        if(ultimoEscopo.id) {
          salvarEscopo(ultimoEscopo.id);
        }
      }, 5000);
    }
  }, [ultimoEscopo]);

  /** Função para formatar os benefícios recebidos no banco para a lista da página de edição */
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

  /** Função para formatar os arquivos recebidos no banco para a lista da página de edição */
  const receberArquivos = (arquivos) => {
    let listaArquivos = [];
    for (let arquivo of arquivos) {
      listaArquivos.push(
        new File([arquivo.dados], arquivo.nome, { type: arquivo.tipo })
      );
    }
    setPaginaArquivos(listaArquivos);
  };

  /** Função de salvamento de escopo, usando a variável "ultimoEscopo" e atualizando ela com os dados da página */
  const salvarEscopo = (id) => {
    const escopo = {
      id: id,
      titulo: paginaDados.titulo,
      problema: paginaDados.problema,
      proposta: paginaDados.proposta,
      frequencia: paginaDados.frequencia,
      beneficios: formatarBeneficios(paginaBeneficios),
    }

    try {
      EscopoService.salvarDados(escopo).then((response) => {
        setUltimoEscopo(escopo);
        //Confirmação de salvamento (se sobrar tempo)
      });
    } catch (error) { }
  };

  /** Função para atualizar os anexos de um escopo quando um anexo for adicionado/removido */
  const salvarAnexosEscopo = () => {
    if (paginaArquivos.length > 0) {
      EscopoService.salvarAnexosEscopo(ultimoEscopo.id, paginaArquivos).then((response) => { });
    } else {
      EscopoService.removerAnexos(ultimoEscopo.id).then((response) => { });
    }
  };

  /** Função para voltar para a etapa anterior na criação da demanda */
  const voltarEtapa = () => {
    setEtapaAtiva((prevActiveStep) => prevActiveStep - 1);
  };

  /** Função para ir para a próxima etapa da demanda */
  const proximaEtapa = () => {
    if (paginaDados.titulo !== "" && paginaDados.problema !== "" && paginaDados.proposta !== "" && paginaDados.frequencia !== "") {
      setEtapaAtiva((prevActiveStep) => prevActiveStep + 1);
    } else {
      setFeedbackDadosFaltantes(true);
    }
  };

  /** Função para abrir o modal de confirmação de criação de demanda */
  const abrirModalConfirmacao = () => {
    setOpenConfirmacao(true);
  };

  /** Função para criar a demanda com os dados recebidos após a confirmação do modal */
  const criarDemanda = () => {
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
      direcionarHome();
      excluirEscopo();
    });
  }

  /** Função para formatar os benefícios recebidos da página de benefícios para serem adicionados ao banco na criação da demanda */
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

  /** Função para excluir o escopo determinado quando a demanda a partir dele for criada */
  const excluirEscopo = () => {
    EscopoService.excluirEscopo(ultimoEscopo.id).then((response) => { });
  };

  /** Função para direcionar o usuário para a tela de home após terminar a criação de demanda */
  const direcionarHome = () => {
    localStorage.setItem("tipoFeedback", "1");
    navigate("/");
  };

  return (
    <>
      {/* Stepper utilizado para os passos da criação e a barra de progressão */}
      <Stepper activeStep={etapaAtiva}>
        {steps.map((label, index) => {
          return (
            <Step key={label}>
              <StepLabel>
                <Typography fontSize={FontConfig.default}>{label}</Typography>
              </StepLabel>
            </Step>
          );
        })}
      </Stepper>
      {etapaAtiva == 0 && (
        <FormularioDadosDemanda dados={paginaDados} setDados={setPaginaDados} />
      )}
      {etapaAtiva == 1 && (
        <FormularioBeneficiosDemanda dados={paginaBeneficios} setDados={setPaginaBeneficios} />
      )}
      {etapaAtiva == 2 && (
        <FormularioAnexosDemanda salvarEscopo={salvarAnexosEscopo} dados={paginaArquivos} setDados={setPaginaArquivos} />
      )}
      <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>

        {/* Botão de voltar à etapa anterior da criação */}
        <Button
          variant="outlined"
          color="tertiary"
          disabled={etapaAtiva === 0}
          onClick={voltarEtapa}
          sx={{ mr: 1 }}
          disableElevation
        >
          <Typography fontSize={FontConfig.default}>{texts.barraProgressaoDemanda.botaoVoltar}</Typography>
        </Button>
        <Box sx={{ flex: "1 1 auto" }} />

        {/* Verificações para mudar texto do botão de Próximo/Criar de acordo com o passo atual */}
        {etapaAtiva === steps.length - 1 ? (
          <Button
            color="primary"
            variant="contained"
            onClick={abrirModalConfirmacao}
            disableElevation
          >
            <Typography fontSize={FontConfig.default}>{texts.barraProgressaoDemanda.botaoCriar}</Typography>
          </Button>
        ) : (
          <Button
            color="primary"
            variant="contained"
            onClick={proximaEtapa}
            disableElevation
          >
            <Typography fontSize={FontConfig.default}>{texts.barraProgressaoDemanda.botaoProximo}</Typography>
          </Button>
        )}

        {/* Modal de confirmação de criar demanda */}
        {modalConfirmacao && (
          <ModalConfirmacao
            open={true}
            setOpen={setOpenConfirmacao}
            textoModal={"enviarDemanda"}
            textoBotao={"enviar"}
            onConfirmClick={criarDemanda}
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
        mensagem={texts.barraProgressaoDemanda.mensagemFeedback}
      />
    </>
  );
};

export default BarraProgressaoDemanda;
