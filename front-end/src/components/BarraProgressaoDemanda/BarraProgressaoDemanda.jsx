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
import ModalConfirmacao from "../ModalConfirmacao/ModalConfirmacao";
import Feedback from "../Feedback/Feedback";

import DemandaService from "../../service/demandaService";
import EscopoService from "../../service/escopoService";
import ExportPdfService from "../../service/exportPdfService";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";

/** Componente principal usado para criação de demanda, redirecionando para as etapas respectivas e
 * salvando a demanda e escopos no banco de dados
 */
const BarraProgressaoDemanda = () => {
  // Contexto para alterar o idioma
  const { texts } = useContext(TextLanguageContext);

  // Contexto para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** Location utilizado para setar o state utilizado para verificação de lógica */
  const location = useLocation();

  /** Navigate utilizado para navegar para outras páginas */
  const navigate = useNavigate();

  /** Variável utilizada para controlar a barra de progessão na criação da demanda */
  const [etapaAtiva, setEtapaAtiva] = useState(0);

  /** Variável utilizada para abrir o modal de feedback de dados faltantes */
  const [feedbackDadosFaltantes, setFeedbackDadosFaltantes] = useState(false);

  // Variáveis utilizadas para salvar um escopo de uma demanda
  var idEscopo = null;
  const [ultimoEscopo, setUltimoEscopo] = useState(null);

  /** Variável para detectar se algum input for alterado e caso verdadeiro permitir a criação e salvamento dos escopos */
  const [mudancasFeitas, setMudancasFeitas] = useState(false);

  /** Dados da página inicial da criação de demanda */
  const [paginaDados, setPaginaDados] = useState({
    titulo: "",
    problema: "",
    proposta: "",
    frequencia: "",
  });

  /** Lista de benefícios definidos na segunda página da criação de demanda */
  const [paginaBeneficios, setPaginaBeneficios] = useState([]);

  /** Lista de anexos definidos na terceira página da criação de demanda */
  const [paginaArquivos, setPaginaArquivos] = useState([]);

  /** Variável utilizada para abrir o modal de confirmação de criação de demanda */
  const [modalConfirmacao, setOpenConfirmacao] = useState(false);

  /** Variável com os nomes dos respectivos passos da criação da demanda, usando o contexto de tradução */
  const steps = [
    `${texts.barraProgressaoDemanda.steps.dados}`,
    `${texts.barraProgressaoDemanda.steps.beneficios}`,
    `${texts.barraProgressaoDemanda.steps.anexos}`,
  ];

  // UseEffect utilizado para criar um escopo ou receber um escopo do banco ao entrar na página
  useEffect(() => {
    if (!idEscopo) {
      if (!location.state) {
        if (mudancasFeitas) {
          idEscopo = 1;
          criarNovoEscopo();
        }
      } else {
        idEscopo = location.state;
        carregarEscopoExistente(idEscopo);
      }
    }
  }, [mudancasFeitas]);

  // UseEffect utilizado para atualizar a variável mudancasFeitas caso algum input seja preenchido
  useEffect(() => {
    if (
      paginaDados.frequencia !== "" ||
      paginaDados.proposta !== "" ||
      paginaDados.problema !== "" ||
      paginaDados.titulo !== ""
    ) {
      setMudancasFeitas(true);
    }
  }, [paginaDados]);

  // UseEffect utilizado para salvar o escopo a cada 5 segundos
  useEffect(() => {
    if (ultimoEscopo) {
      setTimeout(() => {
        salvarEscopo();
      }, 5000);
    }
  }, [ultimoEscopo]);

  /** Função para criar um novo escopo ativada quando alguma alteração for feita (caso não seja um escopo já existente) */
  const criarNovoEscopo = () => {
    EscopoService.postNew(parseInt(localStorage.getItem("usuarioId"))).then(
      (response) => {
        idEscopo = response.id;
        setUltimoEscopo({ id: idEscopo });
      }
    );
  };

  /** Função para carregar escopo recebido (quando selecionado para edição através da página de escopos)
   * e preencher os inputs com as suas devidas informações salvas
   */
  const carregarEscopoExistente = (id) => {
    EscopoService.buscarPorId(id).then((response) => {
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
  };

  /** Função para formatar os benefícios recebidos no banco para a lista da página de edição na edição de um escopo existente */
  const receberBeneficios = (beneficios) => {
    let listaNova = [];
    for (let beneficio of beneficios) {
      const tipoBeneficioNovo =
        beneficio.tipoBeneficio.charAt(0) +
        beneficio.tipoBeneficio
          .substring(1, beneficio.tipoBeneficio.length)
          .toLowerCase();

      listaNova.push({
        id: beneficio.id,
        tipoBeneficio: tipoBeneficioNovo,
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

  /** Função para retornar um objeto de demanda para salvamento dela ou de um escopo */
  const retornaObjetoDemanda = () => {
    const objetoDemanda = {
      titulo: paginaDados.titulo,
      problema: paginaDados.problema,
      proposta: paginaDados.proposta,
      frequencia: paginaDados.frequencia,
      beneficios: formatarBeneficios(paginaBeneficios),
    };
    return objetoDemanda;
  };

  /** Função de salvamento de escopo, usando a variável "ultimoEscopo" e atualizando ela com os dados da página */
  const salvarEscopo = () => {
    let escopoFinal = retornaObjetoDemanda();
    escopoFinal.id = ultimoEscopo.id;

    try {
      EscopoService.salvarDados(escopoFinal).then((response) => {
        setUltimoEscopo(response);
        //Confirmação de salvamento (se sobrar tempo)
      });
    } catch (error) {}
  };

  /** Função para atualizar os anexos de um escopo quando um anexo for adicionado/removido */
  const salvarAnexosEscopo = () => {
    if (paginaArquivos.length > 0) {
      EscopoService.salvarAnexosEscopo(ultimoEscopo.id, paginaArquivos).then(
        (response) => {}
      );
    } else {
      EscopoService.removerAnexos(ultimoEscopo.id).then((response) => {});
    }
  };

  /** Função para excluir o escopo determinado quando a demanda a partir dele for criada */
  const excluirEscopo = () => {
    EscopoService.excluirEscopo(ultimoEscopo.id).then((response) => {});
  };

  /** Função para criar a demanda com os dados recebidos após a confirmação do modal */
  const criarDemanda = () => {
    let demandaFinal = retornaObjetoDemanda();
    demandaFinal.status = "BACKLOG_REVISAO";

    DemandaService.post(
      demandaFinal,
      paginaArquivos,
      parseInt(localStorage.getItem("usuarioId"))
    ).then((e) => {
      ExportPdfService.exportDemanda(e.id).then((file) => {
        // Salvamento do histórico número 1 da demanda
        let arquivo = new Blob([file], { type: "application/pdf" });
        DemandaService.addHistorico(
          e.id,
          "Demanda Criada",
          arquivo,
          parseInt(localStorage.getItem("usuarioId"))
        ).then((response) => {
          direcionarHome();
          excluirEscopo();
        });
      });
    });
  };

  /** Função para voltar para a etapa anterior na criação da demanda */
  const voltarEtapa = () => {
    setEtapaAtiva((prevActiveStep) => prevActiveStep - 1);
  };

  /** Função para ir para a próxima etapa da demanda */
  const proximaEtapa = () => {
    switch (etapaAtiva) {
      case 0:
        if (
          paginaDados.titulo !== "" &&
          paginaDados.problema !== "" &&
          paginaDados.proposta !== "" &&
          paginaDados.frequencia !== ""
        ) {
          setEtapaAtiva((prevActiveStep) => prevActiveStep + 1);
        } else {
          setFeedbackDadosFaltantes(true);
        }
        break;
      case 1:
        let precisaFeedback = false;
        if (paginaBeneficios.length > 0) {
          paginaBeneficios.map((beneficio) => {
            if (
              beneficio.tipoBeneficio == "Qualitativo" &&
              beneficio.memoriaCalculo == ""
            ) {
              precisaFeedback = true;
            } else if (
              (beneficio.tipoBeneficio == "Real" ||
                beneficio.tipoBeneficio == "Potencial") &&
              (beneficio.valor_mensal == "" ||
                beneficio.moeda == "" ||
                beneficio.memoriaCalculo == "")
            ) {
              precisaFeedback = true;
            }
            if(beneficio.tipoBeneficio == "") {
              precisaFeedback = true;
            }
          });
          if (precisaFeedback) {
            setFeedbackDadosFaltantes(true);
          } else {
            setEtapaAtiva((prevActiveStep) => prevActiveStep + 1);
          }
        } else {
          setEtapaAtiva((prevActiveStep) => prevActiveStep + 1);
        }
        break;
    }
  };

  /** Função para direcionar o usuário para a tela de home após terminar a criação de demanda */
  const direcionarHome = () => {
    localStorage.setItem("tipoFeedback", "1");
    navigate("/");
  };

  return (
    <>
      {/* Stepper utilizado para os passos da criação e a barra de progressão */}
      <Stepper activeStep={etapaAtiva} sx={{ minWidth: "50rem" }}>
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

      {/* Componentes / páginas respectivas da criação da demanda */}
      {etapaAtiva == 0 && (
        <FormularioDadosDemanda dados={paginaDados} setDados={setPaginaDados} />
      )}
      {etapaAtiva == 1 && (
        <Box className="w-full" sx={{minWidth: "50rem"}}> 
          <FormularioBeneficiosDemanda
            dados={paginaBeneficios}
            setDados={setPaginaBeneficios}
          />
        </Box>
      )}
      {etapaAtiva == 2 && (
        <FormularioAnexosDemanda
          salvarEscopo={salvarAnexosEscopo}
          dados={paginaArquivos}
          setDados={setPaginaArquivos}
        />
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
          <Typography fontSize={FontConfig.default}>
            {texts.barraProgressaoDemanda.botaoVoltar}
          </Typography>
        </Button>
        <Box sx={{ flex: "1 1 auto" }} />

        {/* Verificações para mudar texto do botão de Próximo/Criar de acordo com o passo atual */}
        {etapaAtiva === steps.length - 1 ? (
          <Button
            color="primary"
            variant="contained"
            onClick={() => {
              setOpenConfirmacao(true);
            }}
            disableElevation
          >
            <Typography fontSize={FontConfig.default}>
              {texts.barraProgressaoDemanda.botaoCriar}
            </Typography>
          </Button>
        ) : (
          <Button
            color="primary"
            variant="contained"
            onClick={proximaEtapa}
            disableElevation
          >
            <Typography fontSize={FontConfig.default}>
              {texts.barraProgressaoDemanda.botaoProximo}
            </Typography>
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
