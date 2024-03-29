import React, { useState, useEffect, useContext } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { ClipLoader } from "react-spinners";

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
import ModalConfirmacao from "../../Modais/Modal-confirmacao/ModalConfirmacao";
import ModalSimilaridade from "../../Modais/Modal-similaridade/ModalSimilaridade"
import Feedback from "../../Feedback/Feedback";

import DOMPurify from "dompurify";

import DemandaService from "../../../service/demandaService";
import EscopoService from "../../../service/escopoService";
import ExportPdfService from "../../../service/exportPdfService";
import FontContext from "../../../service/FontContext";
import TextLanguageContext from "../../../service/TextLanguageContext";
import UsuarioService from "../../../service/usuarioService";
import CookieService from "../../../service/cookieService";
import beneficioService from "../../../service/beneficioService";
import SpeechSynthesisContext from "../../../service/SpeechSynthesisContext";
import DemandaSimilaridade from "../../../service/demandaSimilaridade";

/** Componente principal usado para criação de demanda, redirecionando para as etapas respectivas e
 * salvando a demanda e escopos no banco de dados
 */
const BarraProgressaoDemanda = () => {
  /** Location utilizado para setar o state utilizado para verificação de lógica */
  const location = useLocation();

  /** Navigate utilizado para navegar para outras páginas */
  const navigate = useNavigate();

  /** Contexto para alterar o idioma */
  const { texts } = useContext(TextLanguageContext);

  /** Contexto para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lendoTexto, lerTexto, librasAtivo } = useContext(
    SpeechSynthesisContext
  );

  /** UseState utilizado para armazenar o usuário */
  const [usuario, setUsuario] = useState(null);

  /** Variável utilizada para controlar a barra de progessão na criação da demanda */
  const [etapaAtiva, setEtapaAtiva] = useState(0);

  /** Variável utilizada para abrir o modal de feedback de dados faltantes */
  const [feedbackDadosFaltantes, setFeedbackDadosFaltantes] = useState(false);

  /** Variáveis utilizadas para salvar um escopo de uma demanda */
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

  /** Variável utilizada para mostrar o carregamento ao criar demanda */
  const [carregamentoDemanda, setCarregamentoDemanda] = useState(true);

  const [erro, setErro] = useState(false);

  /** Variável utilizada para abrir o modal de aviso de similaridade */
  const [modalSimilaridade, setModalSimilaridade] = useState(false);

  /** State que guarda a demanda similar */
  const [demandaSimilar, setDemandaSimilar] = useState(null);

  /** Variável para interromper o salvamento de escopos enquanto a demanda estiver sendo criada */
  let criandoDemanda = false;

  /** Variável com os nomes dos respectivos passos da criação da demanda, usando o contexto de tradução */
  const steps = [
    `${texts.barraProgressaoDemanda.steps.dados}`,
    `${texts.barraProgressaoDemanda.steps.beneficios}`,
    `${texts.barraProgressaoDemanda.steps.anexos}`,
  ];

  /** UseEffect utilizado para buscar o usuário assim que entrar na página */
  useEffect(() => {
    buscarUsuario();
  }, []);

  /** UseEffect utilizado para criar um escopo ou receber um escopo do banco ao entrar na página */
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

  /** UseEffect utilizado para atualizar a variável mudancasFeitas caso algum input seja preenchido */
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

  /** UseEffect utilizado para salvar o escopo a cada 5 segundos */
  useEffect(() => {
    if (ultimoEscopo) {
      setTimeout(() => {
        salvarEscopo();
      }, 5000);
    }
  }, [ultimoEscopo]);

  /** UseEffect para carregar a página quando o usuário for buscado */
  useEffect(() => {
    if (usuario) {
      setTimeout(() => {
        setCarregamentoDemanda(false);
      }, 500);
    }
  }, [usuario]);

  /** Função para buscar o usuário salvo no cookie de autenticação */
  const buscarUsuario = () => {
    if (!CookieService.getCookie("jwt")) navigate("/login");
    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt").sub).then(
      (user) => {
        setUsuario(user);
      }
    );
  };

  /** Função para criar um novo escopo ativada quando alguma alteração for feita (caso não seja um escopo já existente) */
  const criarNovoEscopo = () => {
    EscopoService.postNew(usuario?.id).then((response) => {
      idEscopo = response.id;
      setUltimoEscopo({ id: idEscopo });
    });
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
      setPaginaArquivos(response.anexo);
      setUltimoEscopo({ ...response });
    });
  };

  /** Função para formatar os benefícios recebidos no banco para a lista da página de edição na edição de um escopo existente */
  const receberBeneficios = (beneficios) => {
    let listaNova = [];
    for (let beneficio of beneficios) {
      let tipoBeneficioNovo = "";
      if (beneficio.tipoBeneficio) {
        tipoBeneficioNovo =
          beneficio.tipoBeneficio.charAt(0) +
          beneficio.tipoBeneficio
            .substring(1, beneficio.tipoBeneficio.length)
            .toLowerCase();
      }

      listaNova.push({
        ...beneficio,
        tipoBeneficio: tipoBeneficioNovo,
        visible: true,
      });
    }
    setPaginaBeneficios(listaNova);
  };

  /** Função para formatar uma lista de objetos, retornando somente o id de cada objeto presente, com a lista sendo recebida como parâmetro */
  const retornarIdsObjetos = (listaObjetos) => {
    let listaNova = [];
    for (let objeto of listaObjetos) {
      listaNova.push({ id: objeto.id });
    }
    return listaNova;
  };

  /** Função para retornar o id do beneficio caso seu visible seja true */
  const retornarBeneficiosVisible = (listaBeneficios) => {
    let listaBeneficiosVisible = [];
    for (let objeto of listaBeneficios) {
      if (objeto.visible) {
        listaBeneficiosVisible.push(objeto);
      }
    }
    return listaBeneficiosVisible;
  };

  /** Função para retornar um objeto de demanda para salvamento dela ou de um escopo */
  const retornaObjetoDemanda = () => {
    const objetoDemanda = {
      titulo: paginaDados.titulo,
      problema: formatarHtml(paginaDados.problema),
      proposta: formatarHtml(paginaDados.proposta),
      frequencia: paginaDados.frequencia,
      beneficios: retornarIdsObjetos(
        retornarBeneficiosVisible(paginaBeneficios)
      ),
      anexo: retornarIdsObjetos(paginaArquivos),
      solicitante: { id: usuario.id },
    };

    return objetoDemanda;
  };

  const retornarObjetoDemandaConsulta = () => {

    let problemaSemHtml = DOMPurify.sanitize(paginaDados.problema, { ALLOWED_TAGS: [] });
    let propostaSemHtml = DOMPurify.sanitize(paginaDados.proposta, { ALLOWED_TAGS: [] });

    const objeto = {
      titulo: paginaDados.titulo,
      problema: problemaSemHtml,
      proposta: propostaSemHtml,
    }

    return objeto;
  }

  /** Função para criar e retornar um objeto de histórico para salvamento */
  const retornaObjetoHistorico = () => {
    const historico = {
      data: new Date(),
      acaoRealizada: "Demanda Criada",
      autor: { id: usuario.id },
    };
    return historico;
  };

  /** Função para formatar o HTML em casos como a falta de fechamentos em tags "<br>" */
  const formatarHtml = (texto) => {
    texto = texto.replace(/<br>/g, "<br/>");
    return texto;
  };

  /** Função de salvamento de escopo, usando a variável "ultimoEscopo" e atualizando ela com os dados da página */
  const salvarEscopo = () => {
    let escopoFinal = retornaObjetoDemanda();
    escopoFinal.id = ultimoEscopo.id;

    try {
      if (!criandoDemanda) {
        EscopoService.salvarDados(escopoFinal).then((response) => {
          setUltimoEscopo(response);
          //Confirmação de salvamento (se sobrar tempo)
        });
      }
    } catch (error) { }
  };

  /** Função para excluir o escopo determinado quando a demanda a partir dele for criada */
  const excluirEscopo = () => {
    EscopoService.excluirEscopo(ultimoEscopo.id).then((response) => { });
  };

  /** Função para criar a demanda com os dados recebidos após a confirmação do modal */
  const criarDemanda = () => {
    let demandaFinal = retornaObjetoDemanda();
    demandaFinal.status = "BACKLOG_REVISAO";

    let variavel = retornarObjetoDemandaConsulta();

    DemandaSimilaridade.postSimilaridade(variavel).then((response) => {
      if (response.message === "Nenhuma demanda similar encontrada.") {
        setCarregamentoDemanda(true);
        criandoDemanda = true;

        DemandaService.post(demandaFinal).then((demanda) => {
          ExportPdfService.exportDemanda(demanda.id).then((file) => {
            // Salvamento do histórico número 1 da demanda
            let arquivo = new Blob([file], { type: "application/pdf" });
            DemandaService.addHistorico(
              demanda.id,
              retornaObjetoHistorico(),
              arquivo
            ).then((response) => {
              direcionarHome();
              excluirEscopo();
            });
          });
        });
      } else {
        DemandaService.getById(response.id_demanda_similar).then((demandaSimilarRes) => {
          setDemandaSimilar(demandaSimilarRes.content[0]);
          setModalSimilaridade(true);
        })
      }
    })
  };

  const criarDemandaSemVerificacao = () => {
    let demandaFinal = retornaObjetoDemanda();
    demandaFinal.status = "BACKLOG_REVISAO";

    DemandaService.post(demandaFinal).then((demanda) => {
      ExportPdfService.exportDemanda(demanda.id).then((file) => {
        // Salvamento do histórico número 1 da demanda
        let arquivo = new Blob([file], { type: "application/pdf" });
        DemandaService.addHistorico(
          demanda.id,
          retornaObjetoHistorico(),
          arquivo
        ).then((response) => {
          direcionarHome();
          excluirEscopo();
        });
      });
    });
  }

  /** Função para voltar para a etapa anterior na criação da demanda */
  const voltarEtapa = () => {
    if (!lendoTexto && !librasAtivo) {
      setEtapaAtiva((prevActiveStep) => prevActiveStep - 1);
    } else if (librasAtivo) {
      return;
    } else {
      lerTexto(texts.barraProgressaoDemanda.botaoVoltar);
    }
  };

  /** Função para ir para a próxima etapa da demanda */
  const proximaEtapa = () => {
    if (lendoTexto) {
      lerTexto(texts.barraProgressaoDemanda.botaoProximo);
    } else if (librasAtivo) {
      return;
    } else {
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
            setErro(true);
            setFeedbackDadosFaltantes(true);
          }
          break;
        case 1:
          let precisaFeedback = false;
          if (paginaBeneficios.length > 0) {
            paginaBeneficios.map((beneficio) => {
              if (beneficio.visible) {
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
                if (beneficio.tipoBeneficio == "") {
                  precisaFeedback = true;
                }
              }
            });
            if (precisaFeedback) {
              setFeedbackDadosFaltantes(true);
            } else {
              setEtapaAtiva((prevActiveStep) => prevActiveStep + 1);
              salvarBeneficios();
            }
          } else {
            setEtapaAtiva((prevActiveStep) => prevActiveStep + 1);
          }
          break;
      }
    }
  };

  /** Função para salvar a lista de benefícios */
  const salvarBeneficios = () => {
    for (let beneficio of paginaBeneficios) {
      if (beneficio.visible) {
        let beneficioFinal = { ...beneficio, valor_mensal: parseFloat(beneficio.valor_mensal.replace(",", ".")) };
        delete beneficioFinal.visible;
        beneficioService.put(beneficioFinal).then((response) => { });
      }
    }
  };

  /** Função para direcionar o usuário para a tela de home após terminar a criação de demanda, ativando o feedback de criação de demanda */
  const direcionarHome = () => {
    localStorage.setItem("tipoFeedback", "1");
    navigate("/");
  };

  return (
    <>
      {/* Feedback de dados faltantes */}
      <Feedback
        open={feedbackDadosFaltantes}
        handleClose={() => {
          setFeedbackDadosFaltantes(false);
        }}
        status={"erro"}
        mensagem={texts.barraProgressaoDemanda.mensagemFeedback}
      />

      <ModalSimilaridade
        open={modalSimilaridade}
        setOpen={setModalSimilaridade}
        criarSemVerificacao={criarDemandaSemVerificacao}
        dados={demandaSimilar}
      />

      {carregamentoDemanda ? (
        <Box className="mt-6 w-full h-full flex justify-center items-center">
          <ClipLoader color="#00579D" size={110} />
        </Box>
      ) : (
        <>
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

          {/* Stepper utilizado para os passos da criação e a barra de progressão */}
          <Stepper activeStep={etapaAtiva} sx={{ minWidth: "50rem" }}>
            {steps.map((label, index) => {
              return (
                <Step key={label}>
                  <StepLabel>
                    <Typography
                      fontSize={FontConfig.default}
                      onClick={() => {
                        if (!librasAtivo) {
                          lerTexto(label);
                        }
                      }}
                    >
                      {label}
                    </Typography>
                  </StepLabel>
                </Step>
              );
            })}
          </Stepper>

          {/* Componentes / páginas respectivas da criação da demanda */}
          {etapaAtiva == 0 && (
            <FormularioDadosDemanda
              erro={erro}
              dados={paginaDados}
              setDados={setPaginaDados}
            />
          )}
          {etapaAtiva == 1 && (
            <Box className="w-full" sx={{ minWidth: "50rem" }}>
              <FormularioBeneficiosDemanda
                dados={paginaBeneficios}
                setDados={setPaginaBeneficios}
                salvarBeneficios={salvarBeneficios}
              />
            </Box>
          )}
          {etapaAtiva == 2 && (
            <FormularioAnexosDemanda
              dados={paginaArquivos}
              setDados={setPaginaArquivos}
            />
          )}

          {/* Botão de voltar à etapa anterior da criação */}
          <Button
            variant="outlined"
            color="tertiary"
            disabled={etapaAtiva === 0}
            onClick={voltarEtapa}
            sx={{ mr: 1, position: "fixed", bottom: 50, left: 160 }}
            disableElevation
          >
            <Typography fontSize={FontConfig.default}>
              {texts.barraProgressaoDemanda.botaoVoltar}
            </Typography>
          </Button>

          {/* Verificações para mudar texto do botão de Próximo/Criar de acordo com o passo atual */}
          {etapaAtiva === steps.length - 1 ? (
            <Button
              color="primary"
              variant="contained"
              onClick={() => {
                if (!lendoTexto && !librasAtivo) {
                  setOpenConfirmacao(true);
                } else if (librasAtivo) {
                } else {
                  lerTexto(texts.barraProgressaoDemanda.botaoCriar);
                }
              }}
              sx={{ mr: 1, position: "fixed", bottom: 50, right: 160 }}
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
              sx={{ mr: 1, position: "fixed", bottom: 50, right: 160 }}
            >
              <Typography fontSize={FontConfig.default}>
                {texts.barraProgressaoDemanda.botaoProximo}
              </Typography>
            </Button>
          )}
        </>
      )}
    </>
  );
};

export default BarraProgressaoDemanda;
