import React, { useState, useContext, useEffect, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";

import {
  Box,
  Avatar,
  Typography,
  Divider,
  Tooltip,
  IconButton,
  Menu,
  MenuItem,
} from "@mui/material";

import Tour from "reactour";
import ClipLoader from "react-spinners/ClipLoader";

import CommentOutlinedIcon from "@mui/icons-material/CommentOutlined";
import CommentsDisabledOutlinedIcon from "@mui/icons-material/CommentsDisabledOutlined";
import MoreVertOutlinedIcon from "@mui/icons-material/MoreVertOutlined";
import AttachFileOutlinedIcon from "@mui/icons-material/AttachFileOutlined";
import SendOutlinedIcon from "@mui/icons-material/SendOutlined";
import OpenInNewOutlinedIcon from "@mui/icons-material/OpenInNewOutlined";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import Contato from "../../components/Contato/Contato";
import Mensagem from "../../components/Mensagem/Mensagem";
import ModalConfirmacao from "../../components/Modais/Modal-confirmacao/ModalConfirmacao";
import Ajuda from "../../components/Ajuda/Ajuda";
import Feedback from "../../components/Feedback/Feedback";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import ChatContext from "../../service/ChatContext";
import ChatService from "../../service/chatService";
import UsuarioService from "../../service/usuarioService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import DateService from "../../service/dateService";
import AnexoService from "../../service/anexoService";
import { MensagemService } from "../../service/MensagemService";
import { WebSocketContext } from "../../service/WebSocketService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";
import { SpeechRecognitionContext } from "../../service/SpeechRecognitionService";

import logoWeg from "../../assets/logo-weg.png";

/** Chat para conversa entre usuários do sistema */
const Chat = () => {
  /** Context para alterar o idioma */
  const { texts } = useContext(TextLanguageContext);

  /** Variáveis utilizadas para a visibilidade e id do chat */
  const { visibilidade, setVisibilidade, setIdChat } = useContext(ChatContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /**  Context do WebSocket */
  const { enviar, inscrever, stompClient } = useContext(WebSocketContext);

  /** Context para ler o texto da tela */
  const { lerTexto } = useContext(SpeechSynthesisContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, localClique, palavrasJuntas } = useContext(
    SpeechRecognitionContext
  );

  /** Navigate utilizado para navegar para outras páginas */
  const navigate = useNavigate();

  /** ID do chat passado por params */
  const idChatParam = useParams().id;

  /** Container das mensagens */
  const boxRef = useRef(null);

  /** Input de arquivo */
  const inputRef = useRef(null);

  /** UseState para armazenar o ID do chat */
  const [idChat, setIdChatState] = useState(idChatParam);

  /** UseState para pesquisar um contato da lista */
  const [pesquisaContato, setPesquisaContato] = useState("");

  /** UseState para armazenar os resultados da pesquisa */
  const [resultadosContato, setResultadosContato] = useState([
    EntitiesObjectService.chat(),
  ]);

  /** UseState para controlar o loading de mensagens */
  const [buscandoMensagens, setBuscandoMensagens] = useState(true);

  /** UseState para feedback de chat encerrado */
  const [feedbackChatEncerrado, setFeedbackChatEncerrado] = useState(false);

  /** UseState para feedback de chat encerrado */
  const [feedbackNaoPodeAbrir, setFeedbackNaoPodeAbrir] = useState(false);

  /** UseState para feedback de chat aberto */
  const [feedbackChatAberto, setFeedbackChatAberto] = useState(false);

  /** UseState para feedback de que anexo é muito grande */
  const [feedbackAnexoGrande, setFeedbackAnexoGrande] = useState(false);

  /** useState para abrir e fechar o tour */
  const [isTourOpen, setIsTourOpen] = useState(false);

  /** Modal de confiramação para encerrar o chat */
  const [abrirModalEncerrarChat, setOpenModalEncerrarChat] = useState(false);

  /** Modal de confirmação para reabrir o chat */
  const [abrirModalReabrirChat, setOpenModalReabrirChat] = useState(false);

  /** Lista de chats do usuário */
  const [listaChats, setListaChats] = useState([EntitiesObjectService.chat()]);

  /** Mensagem que está sendo digitada */
  const [mensagem, setMensagem] = useState(EntitiesObjectService.mensagem());

  /** Guarda o chat atual */
  const [chatAtual, setChatAtual] = useState(EntitiesObjectService.chat());

  /** Todas as mensagens do chat selecionado */
  const [mensagens, setMensagens] = useState([
    EntitiesObjectService.mensagem(),
  ]);

  /** Usuário logado */
  const [user, setUser] = useState(UsuarioService.getUserCookies());

  /** UseState para poder visualizar e alterar a visibilidade do menu */
  const [anchorEl, setAnchorEl] = useState(null);

  /** Variável que é usada para saber se o menu está aberto ou não */
  const open = Boolean(anchorEl);

  /** Passos do tour */
  const stepsTour = [
    {
      selector: "#primeiro",
      content: texts.chat.tour.tour1,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#segundo",
      content: texts.chat.tour.tour2,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#terceiro",
      content: texts.chat.tour.tour3,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quarto",
      content: texts.chat.tour.tour4,
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  /** useEffect utilizado para buscar os chats do usuário */
  useEffect(() => {
    if (idChat) carregar();
    buscarChats();
  }, [idChat]);

  /** useEffect utilizado para receber uma mensagem  */
  useEffect(() => {
    const receivedAnyMessage = (mensagem) => {
      let mensagemRecebida = EntitiesObjectService.mensagem();
      mensagemRecebida = JSON.parse(mensagem.body);
      let idChatAux = idChat;
      setIdChatState((oldIdChat) => {
        idChatAux = oldIdChat;
        return oldIdChat;
      });

      // Se a mensagem recebida for do usuário logado, ignore
      if (mensagemRecebida.usuario.id == user.usuario.id) {
        return;
      }

      // Se a mensagem recebida for do chat atual, ignore
      if (mensagemRecebida.idChat.id == idChatAux) {
        return;
      }

      setListaChats((oldListaChats) => {
        let listaAux = JSON.parse(JSON.stringify(oldListaChats));

        let chatAux = listaAux.find(
          (chat) => chat.id == mensagemRecebida.idChat.id
        );

        chatAux.msgNaoLidas = chatAux.msgNaoLidas + 1;
        return [...listaAux];
      });
    };

    let inscricaoAllMensagens = inscrever(
      `/weg_ssm/mensagem/all/user/${user.usuario.id}`,
      receivedAnyMessage
    );

    return () => {
      if (inscricaoAllMensagens) {
        inscricaoAllMensagens.unsubscribe();
      }
    };
  }, [stompClient]);

  /** useEffect utilizado para enviar uma nova mensagem */
  useEffect(() => {
    const acaoNovaMensagem = (response) => {
      const mensagemRecebida = JSON.parse(response.body);

      // Se o chat estiver encerrado, aparece alert para o usuário que não for analista
      if (
        mensagemRecebida.statusCodeValue == 403 &&
        JSON.parse(mensagemRecebida.body).usuario.id != user.usuario.id
      ) {
        alert(texts.chat.vcNPodeEnviarMsg);
        return;
      }

      // Se o chat estiver fechado, ignora qualquer nova mensagem que for recebida
      if (mensagemRecebida.statusCodeValue == 403) {
        return;
      }

      let mensagemNova = {
        ...mensagemRecebida.body,
        texto: mensagemRecebida.body.texto.replace(/%BREAK%/g, "\n"),
      };

      // Se o remetente não for o usuário, tenho que notificar a visualização
      if (mensagemNova.usuario.id != user.usuario.id) {
        mensagemNova.visto = true;
        enviar(
          `/app/weg_ssm/mensagem/chat/${idChat}/visto`,
          JSON.stringify({
            ...mensagemNova,
            texto: mensagemNova.texto.replace(/\n/g, "%BREAK%"),
          })
        );
      } else {
        clearNewMessages();
      }

      setMensagens((oldMensagens) => [...oldMensagens, mensagemNova]);
    };

    const readMessage = (mensagem) => {
      if (mensagem.body == `visualizar-novas-mensagens/${user.usuario.id}`) {
        clearNewMessages();
        return;
      }

      setMensagens((oldMensagens) => {
        for (let oldMensagem of oldMensagens) {
          if (!oldMensagem.visto) {
            oldMensagem.visto = true;
          }
        }

        return [...oldMensagens];
      });
    };

    if (idChat) {
      let inscricaoId = inscrever(
        `/weg_ssm/mensagem/${idChat}/chat`,
        acaoNovaMensagem
      );

      let inscricaoVerMensagem = inscrever(
        `/weg_ssm/mensagem/chat/${idChat}/visto`,
        readMessage
      );

      return () => {
        if (inscricaoId) {
          inscricaoId.unsubscribe();
          inscricaoVerMensagem.unsubscribe();
        }
      };
    }
  }, [stompClient, idChat]);

  /** useEffect utilizado para filtragem dos chats */
  useEffect(() => {
    let listaChatsAux = listaChats.filter((chat) => {
      let demanda = chat.idProposta ? chat.idProposta : chat.idDemanda;

      // Pesquisa por Nome do usuario
      for (let userChat of chat.usuariosChat) {
        if (userChat.id != user.usuario.id) {
          if (
            userChat.nome.toLowerCase().includes(pesquisaContato.toLowerCase())
          ) {
            return true;
          }
        }
      }

      // Pesquisa por código PPM
      if (demanda.codigoPPM) {
        if (demanda.codigoPPM.toString().startsWith(pesquisaContato)) {
          return true;
        }
      } else {
        if (demanda.id.toString().startsWith(pesquisaContato)) {
          return true;
        }
      }

      // Pesquisa pelo título da proposta
      if (
        demanda.titulo.toLowerCase().includes(pesquisaContato.toLowerCase())
      ) {
        return true;
      }

      // Pesquisa pelo nome do contato
      if (containsUser(chat.usuariosChat, user.usuario.id, pesquisaContato)) {
        return true;
      }
    });
    setResultadosContato([...listaChatsAux]);
  }, [pesquisaContato, listaChats, idChat]);

  /** useEffect utilizado para colocar scroll na tela de mensagens */
  useEffect(() => {
    setBuscandoMensagens(false);

    const boxElement = boxRef.current;

    // Colocando o scroll para a última mensagem recebida
    if (boxElement) {
      boxElement.scrollTop = boxElement.scrollHeight;
    }
  }, [mensagens]);

  /** useEffect utilizado para descobrir qual foi o local clicado */
  useEffect(() => {
    switch (localClique) {
      case "titulo":
        setPesquisaContato(palavrasJuntas);
        break;
      case "mensagem":
        setMensagem({ ...mensagem, texto: palavrasJuntas });
        break;
      default:
        break;
    }
  }, [palavrasJuntas]);

  /** Função para abrir o menu */
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  /** Função para fechar o menu */
  const handleClose = () => {
    setAnchorEl(null);
  };

  /** Hanlder para armazenar o contato selecionado */
  const onChange = (evt) => {
    setPesquisaContato(evt.target.value);
  };

  /** Função para abrir o modal de confirmação para encerrar o chat */
  const abrirModalCancelarChat = () => {
    setOpenModalEncerrarChat(true);
  };

  /** Função para fechar o modal de confirmação para encerrar o chat */
  const fecharModalCancelarChat = () => {
    setOpenModalEncerrarChat(false);
  };

  /** Função para abrir o modal de confirmação para reabrir o chat */
  const abrirModalAbrirChat = () => {
    setOpenModalReabrirChat(true);
  };

  /** Função para fechar o modal de confirmação para reabrir o chat */
  const fecharModalAbrirChat = () => {
    setOpenModalReabrirChat(false);
  };

  /** Função para buscar o id do destinatário no chat */
  const getIdDestinatario = () => {
    if (!idChat) return 0;

    let chatAux = listaChats.find((chat) => chat.id == idChat);

    if (!chatAux) return 0;

    let userAux = chatAux.usuariosChat.find((e) => e.id != user.usuario.id);
    return userAux.id;
  };

  /** Seta a mensagem padrão */
  const setDefaultMensagem = () => {
    setMensagem({
      data: DateService.getTodaysDate(),
      visto: false,
      texto: "",
      status: "MESSAGE",
      usuario: { id: user.usuario.id },
      idChat: { id: idChat },
      idDestinatario: getIdDestinatario(),
    });
  };

  /** Atualiza a mensagem a cada tecla digitada */
  const atualizaMensagem = (event) => {
    const { value } = event.target;
    setMensagem({ ...mensagem, texto: value });
  };

  /** Envia uma mensagem para o tópico */
  const submit = (event) => {
    if (mensagem.texto !== "") {
      event.preventDefault();
      let message = {
        ...mensagem,
        texto: mensagem.texto.replace(/\n/g, "%BREAK%"),
        idDestinatario: getIdDestinatario(),
      };

      try {
        enviar(`/app/weg_ssm/mensagem/${idChat}`, JSON.stringify(message));
      } catch (error) {
        setChatAtual({ ...chatAtual, conversaEncerrada: true });
      }
      setDefaultMensagem();
    }
  };

  /** Atualiza o chat para encerrado */
  const deletarChat = () => {
    fecharModalCancelarChat();
    ChatService.getByIdChat(idChat).then((e) => {
      ChatService.put(
        {
          ...e,
          conversaEncerrada: true,
        },
        idChat
      ).then((chat) => {
        setFeedbackChatEncerrado(true);
        setChatAtual(chat);
        buscarChats();
      });
    });
  };

  /** Atualiza o chat para não encerrado */
  const abrirChat = () => {
    fecharModalAbrirChat();
    ChatService.getByIdChat(idChat).then((e) => {
      // Verifica se o chat é pela demanda ou proposta
      let demanda = e.idProposta ? e.idProposta : e.idDemanda;

      if (demanda.status != "Cancelled") {
        ChatService.put(
          {
            ...e,
            conversaEncerrada: false,
          },
          idChat
        ).then((chat) => {
          setFeedbackChatAberto(true);
          setChatAtual(chat);
          buscarChats();
          updateResultadosPesquisa(chat);
        });
      } else {
        setFeedbackNaoPodeAbrir(true);
      }
    });
  };

  const updateResultadosPesquisa = (chat) => {
    setResultadosContato((prevState) => {
      return prevState.map((chatAux) => {
        if (chatAux.id == chat.id) {
          return chat;
        }
        return chatAux;
      });
    });
  };

  /** Envia um anexo para o tópico caso ele seja menor que 64KB */
  const handleFileUpload = (event) => {
    const file = event.target.files[0];
    event.preventDefault();

    if (file.size <= 65535) {
      AnexoService.save(file)
        .then((response) => {
          enviar(
            `/app/weg_ssm/mensagem/${idChat}`,
            JSON.stringify({
              ...mensagem,
              anexo: response,
            })
          );
          inputRef.current.value = "";
        })
        .catch((error) => {});
    } else {
      setFeedbackAnexoGrande(true);
    }

    setDefaultMensagem();
  };

  /** Busca os chats do usuário */
  const buscarChats = () => {
    ChatService.getByRemetente(user.usuario.id).then((chatResponse) => {
      if (chatResponse) {
        // Se existir uma resposta, salva a lista de chats
        let listaChatsAux = [...chatResponse];

        // Para cada chat, verifica se a demanda está cancelada
        for (let chatEspecifico of listaChatsAux) {
          // Salva a demanda ou proposta do chat em variável
          let demanda = chatEspecifico.idProposta
            ? chatEspecifico.idProposta
            : chatEspecifico.idDemanda;

          // Se a demanda estiver cancelada e o chat estiver aberto, fecha o chat
          if (
            demanda.status == "Cancelled" &&
            !chatEspecifico.conversaEncerrada
          ) {
            ChatService.getByIdChat(chatEspecifico.id).then((e) => {
              ChatService.put(
                {
                  ...e,
                  conversaEncerrada: true,
                },
                chatEspecifico.id
              );
            });
            chatEspecifico.conversaEncerrada = true;
          }
        }

        // Por fim salva a lista de chats atualizada (caso em alguma instância tenha passado pelo if anterior)
        setListaChats(listaChatsAux);
      }
    });
  };

  /** Busca as mensagens do usuário */
  const carregar = () => {
    if (idChat) {
      /** Procura as mensagens do chat */
      MensagemService.getMensagensChat(idChat).then((response) => {
        setMensagens(response);

        enviar(`/app/weg_ssm/enter/chat/${idChat}`, user.usuario.id);
      });

      /** Procura o chat atual */
      ChatService.getByIdChat(idChat).then((chat) => {
        if (chat) {
          setChatAtual(chat);
        }
      });

      setDefaultMensagem();
    }
  };

  /** Função para visualizar as mensagens não lidas */
  const clearNewMessages = () => {
    setListaChats((oldListaChats) => {
      if (oldListaChats.length == 0) return;

      let listaChatsAux = JSON.parse(JSON.stringify(oldListaChats));
      let chatAux = listaChatsAux.find((chat) => chat.id == idChat);

      if (chatAux) {
        chatAux.msgNaoLidas = 0;
      }

      return [...listaChatsAux];
    });
  };

  /** Função para verificar se um usuário está na lista */
  const containsUser = (
    usuarios = [EntitiesObjectService.usuario()],
    idUserLogado = 0,
    nome = ""
  ) => {
    return usuarios.some(
      (usuario) =>
        usuario.id != idUserLogado && usuario.nome.toLowerCase().includes(nome)
    );
  };

  return (
    <>
      {/* Botão de ajuda/tour */}
      <Ajuda onClick={() => setIsTourOpen(true)} />
      <Tour
        steps={stepsTour}
        isOpen={isTourOpen}
        onRequestClose={() => setIsTourOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />
      {/* Modal de confirmação encerrar chat */}
      {abrirModalEncerrarChat && (
        <ModalConfirmacao
          open={abrirModalEncerrarChat}
          setOpen={setOpenModalEncerrarChat}
          textoModal={"fecharChat"}
          onConfirmClick={deletarChat}
          onCancelClick={fecharModalCancelarChat}
          textoBotao={"sim"}
        />
      )}
      {/* Modal de confirmação reabrir chat */}
      {abrirModalReabrirChat && (
        <ModalConfirmacao
          open={abrirModalReabrirChat}
          setOpen={setOpenModalReabrirChat}
          textoModal={"abrirChat"}
          onConfirmClick={abrirChat}
          onCancelClick={fecharModalAbrirChat}
          textoBotao={"sim"}
        />
      )}
      <FundoComHeader>
        {/* Feedback Chat encerrado com sucesso */}
        <Feedback
          open={feedbackChatEncerrado}
          handleClose={() => {
            setFeedbackChatEncerrado(false);
          }}
          status={"sucesso"}
          mensagem={texts.chat.chatEncerrado}
        />
        {/* Feedback que chat não pode ser aberto*/}
        <Feedback
          open={feedbackNaoPodeAbrir}
          handleClose={() => {
            setFeedbackNaoPodeAbrir(false);
          }}
          status={"erro"}
          mensagem={texts.chat.chatNaoPodeSerReaberto}
        />
        {/* Feedback Anexo pesado */}
        <Feedback
          open={feedbackAnexoGrande}
          handleClose={() => {
            setFeedbackAnexoGrande(false);
          }}
          status={"erro"}
          mensagem={texts.chat.anexoMuitoPesado}
        />
        {/* Feedback Chat reaberto com sucesso */}
        <Feedback
          open={feedbackChatAberto}
          handleClose={() => {
            setFeedbackChatAberto(false);
          }}
          status={"sucesso"}
          mensagem={texts.chat.chatReaberto}
        />
        <Box className="p-2" sx={{ height: "90%" }}>
          <Caminho />
          <Box
            className="w-full h-full flex justify-center items-center"
            sx={{ height: "100%" }}
          >
            <Box
              className="flex justify-evenly items-center rounded border mt-4"
              sx={{ width: "85%", height: "100%" }}
            >
              <Box
                className="flex items-center rounded border flex-col gap-3 overflow-y-auto overflow-x-hidden"
                sx={{ width: "20%", height: "95%" }}
              >
                <Box
                  id="primeiro"
                  className="flex items-center border px-3 py-1 m-4 rounded-lg"
                  sx={{
                    backgroundColor: "input.main",
                    width: "90%",
                    height: "5%",
                    minHeight: "1.5rem",
                  }}
                >
                  <Box
                    title={texts.chat.pesquisarChat}
                    className="w-full h-full"
                    component="input"
                    onChange={onChange}
                    sx={{
                      backgroundColor: "transparent",
                      outline: "none",
                      color: "text.primary",
                      fontSize: FontConfig.medium,
                    }}
                    placeholder={texts.chat.pesquisarChat}
                    value={pesquisaContato}
                  />
                  <Tooltip
                    className="hover:cursor-pointer"
                    title={texts.homeGerencia.gravarAudio}
                    onClick={() => {
                      startRecognition("titulo");
                    }}
                  >
                    {escutar && localClique == "titulo" ? (
                      <MicOutlinedIcon
                        sx={{
                          cursor: "pointer",
                          color: "primary.main",
                          fontSize: "1.45rem",
                        }}
                      />
                    ) : (
                      <MicNoneOutlinedIcon
                        sx={{
                          cursor: "pointer",
                          color: "text.secondary",
                          fontSize: "1.45rem",
                        }}
                      />
                    )}
                  </Tooltip>
                </Box>
                {isTourOpen ? (
                  <Contato
                    idChat={idChat}
                    chat={{
                      conversa_encerrada: false,
                      id: 0,
                      idProposta: {
                        analista: {},
                        anexo: [{}],
                        beneficios: [{}],
                        buSolicitante: {},
                        busBeneficiadas: [{}],
                        codigoPPM: "0",
                        data: "",
                        demanda: {},
                        departamento: {},
                        emAta: false,
                        emPauta: false,
                        escopo: "",
                        fimExecucao: "",
                        id: 0,
                        titulo: "Demanda Tour",
                        solicitante: { id: 0, nome: "Tour", email: "" },
                      },
                      usuariosChat: [{ id: 0, nome: "Tour", email: "" }],
                    }}
                    index={0}
                  />
                ) : resultadosContato[0]?.id != 0 ? (
                  resultadosContato.map((resultado, index) => {
                    return (
                      <Contato
                        key={index}
                        onClick={() => {
                          setIdChatState(resultado.id);
                          navigate(`/chat/${resultado.id}`);
                        }}
                        idChat={idChat}
                        chat={resultado}
                        index={index}
                      />
                    );
                  })
                ) : null}
              </Box>
              {!idChat && !visibilidade ? (
                <Box
                  className="flex flex-col items-center justify-center rounded border"
                  sx={{ width: "75%", height: "95%", cursor: "default" }}
                >
                  <img src={logoWeg} alt="chat" />
                  <Typography
                    fontSize={FontConfig.title}
                    color={"text.secondary"}
                    sx={{ fontWeight: "600" }}
                    onClick={() => {
                      lerTexto(texts.chat.selecioneAlgumaConversa);
                    }}
                  >
                    {texts.chat.selecioneAlgumaConversa}
                  </Typography>
                </Box>
              ) : isTourOpen ? (
                <Box
                  className="flex flex-col items-center justify-between rounded border"
                  sx={{ width: "75%", height: "95%" }}
                >
                  <Box
                    className="flex justify-between items-center w-full rounded-t"
                    sx={{ backgroundColor: "primary.main", height: "10%" }}
                  >
                    <Box className="flex items-center">
                      <Avatar
                        className="ml-7"
                        sx={{ width: "3.5rem", height: "3.5rem" }}
                        // src={usuarios[indexUsuario].foto}
                      />
                      <Box
                        className="flex flex-col ml-3"
                        sx={{ cursor: "default", color: "#FFFF" }}
                      >
                        <Typography
                          className="ml-2"
                          fontSize={FontConfig.veryBig}
                          fontWeight="600"
                          onClick={() => {
                            lerTexto(texts.chat.usuarioTour.tour);
                          }}
                        >
                          {texts.chat.usuarioTour.tour}
                        </Typography>
                        <Typography
                          fontSize={FontConfig.small}
                          onClick={() => {
                            lerTexto(texts.chat.cargo);
                          }}
                        >
                          {texts.chat.cargo}
                        </Typography>
                      </Box>
                    </Box>
                    <Box className="mr-5">
                      {/* Botão para abrir o menu */}
                      <Tooltip title={texts.chat.opcoes}>
                        <IconButton
                          id="terceiro"
                          onClick={handleClick}
                          size="small"
                          aria-controls={open ? "account-menu" : undefined}
                          aria-haspopup="true"
                          aria-expanded={open ? "true" : undefined}
                        >
                          <MoreVertOutlinedIcon sx={{ color: "white" }} />
                        </IconButton>
                      </Tooltip>

                      {/* Menu (modal) */}
                      <Menu
                        id="basic-menu"
                        anchorEl={anchorEl}
                        open={open}
                        onClose={handleClose}
                        MenuListProps={{ "aria-labelledby": "basic-button" }}
                      >
                        <Box className="w-52">
                          {/* Itens do menu */}
                          <MenuItem
                            className="gap-2"
                            onClick={() => {
                              handleClose();
                            }}
                          >
                            <OpenInNewOutlinedIcon />
                            <Typography
                              color={"text.primary"}
                              fontSize={FontConfig.medium}
                              sx={{ fontWeight: 500 }}
                              onClick={() => {
                                lerTexto(texts.chat.abrirPopUp);
                              }}
                            >
                              {texts.chat.abrirPopUp}
                            </Typography>
                          </MenuItem>

                          {/* Divisão de um item clicável e outro no modal */}
                          <div className="w-full flex justify-center">
                            <hr className="w-10/12 my-1.5" />
                          </div>

                          <MenuItem
                            className="gap-2"
                            onClick={() => {
                              handleClose();
                              abrirModalCancelarChat();
                            }}
                          >
                            <CommentsDisabledOutlinedIcon
                              sx={{
                                fontSize: "25px",
                                color: "#FFFF",
                                cursor: "pointer",
                              }}
                            />
                            <Typography
                              color={"text.primary"}
                              fontSize={FontConfig.medium}
                              sx={{ fontWeight: 500 }}
                              onClick={() => {
                                lerTexto(texts.chat.encerrarChat);
                              }}
                            >
                              {texts.chat.encerrarChat}
                            </Typography>
                          </MenuItem>
                        </Box>
                      </Menu>
                    </Box>
                  </Box>
                  <Box
                    id="quarto"
                    className="flex border px-3 py-1 m-4 rounded items-center"
                    sx={{
                      backgroundColor: "input.main",
                      width: "90%",
                      height: "6.5%",
                    }}
                  >
                    <Box
                      className="w-full"
                      component="input"
                      sx={{
                        backgroundColor: "input.main",
                        outline: "none",
                        color: "text.primary",
                        fontSize: FontConfig.medium,
                      }}
                      placeholder={texts.chat.escrevaSuaMensagem}
                    />
                    <Tooltip
                      className="hover:cursor-pointer"
                      title={texts.homeGerencia.gravarAudio}
                      onClick={() => {
                        startRecognition("titulo");
                      }}
                    >
                      {escutar && localClique == "titulo" ? (
                        <MicOutlinedIcon
                          sx={{
                            cursor: "pointer",
                            color: "primary.main",
                            fontSize: "1.7rem",
                          }}
                        />
                      ) : (
                        <MicNoneOutlinedIcon
                          sx={{
                            cursor: "pointer",
                            color: "text.secondary",
                            fontSize: "1.7rem",
                          }}
                        />
                      )}
                    </Tooltip>
                    <Box className="flex gap-2 delay-120 hover:scale-110 duration-300">
                      <Tooltip title={texts.chat.enviarAnexo}>
                        <IconButton>
                          <AttachFileOutlinedIcon
                            sx={{ color: "primary.main", cursor: "pointer" }}
                          />
                        </IconButton>
                      </Tooltip>
                    </Box>
                    <Divider
                      orientation="vertical"
                      sx={{
                        borderColor: "primary.main",
                        margin: "0 10px 0 10px",
                      }}
                    />
                    <Box className="flex gap-2 delay-120 hover:scale-110 duration-300">
                      <Tooltip title={texts.chat.enviarMensagem}>
                        <IconButton>
                          <SendOutlinedIcon sx={{ color: "primary.main" }} />
                        </IconButton>
                      </Tooltip>
                    </Box>
                  </Box>
                </Box>
              ) : visibilidade ? (
                <Box
                  className="flex flex-col items-center justify-center rounded border"
                  sx={{ width: "75%", height: "95%", cursor: "default" }}
                >
                  <img src={logoWeg} alt="chat" />
                  <Typography
                    fontSize={FontConfig.title}
                    color={"text.secondary"}
                    sx={{ fontWeight: "600" }}
                    onClick={() => {
                      lerTexto(texts.chat.miniChatAberto);
                    }}
                  >
                    {texts.chat.miniChatAberto}
                  </Typography>
                </Box>
              ) : (
                <Box
                  className="flex flex-col items-center justify-between rounded border"
                  sx={{ width: "75%", height: "95%" }}
                >
                  <Box
                    className="flex justify-between items-center w-full rounded-t py-8"
                    sx={{ backgroundColor: "primary.main", height: "4rem" }}
                  >
                    <Box className="flex items-center">
                      <Avatar
                        className="ml-7"
                        sx={{ width: "3.5rem", height: "3.5rem" }}
                      />
                      <Box
                        className="flex flex-col ml-3"
                        sx={{ cursor: "default", color: "#FFFF" }}
                      >
                        <Typography
                          className="ml-2"
                          fontSize={FontConfig.veryBig}
                          fontWeight="600"
                        >
                          {chatAtual.usuariosChat.map((usuarioChat) => {
                            let nomeChat;
                            if (usuarioChat.id != user.usuario.id) {
                              nomeChat = usuarioChat.nome;
                            }
                            return nomeChat;
                          })}
                        </Typography>
                        <Typography fontSize={FontConfig.small}>
                          {listaChats.map((chat) => {
                            let cargoChat;
                            let demanda = chat.idProposta
                              ? chat.idProposta
                              : chat.idDemanda;

                            if (chat.id == idChat) {
                              if (demanda.solicitante.id == user.usuario.id) {
                                cargoChat = texts.chat.analista;
                              } else {
                                cargoChat = texts.chat.solicitante;
                              }
                            }
                            return cargoChat;
                          })}
                        </Typography>
                      </Box>
                    </Box>
                    <Box className="mr-5">
                      {/* Botão para abrir o menu */}
                      <Tooltip title={texts.chat.opcoes}>
                        <IconButton
                          onClick={handleClick}
                          size="small"
                          aria-controls={open ? "account-menu" : undefined}
                          aria-haspopup="true"
                          aria-expanded={open ? "true" : undefined}
                        >
                          <MoreVertOutlinedIcon sx={{ color: "white" }} />
                        </IconButton>
                      </Tooltip>

                      {/* Menu (modal) */}
                      <Menu
                        id="basic-menu"
                        anchorEl={anchorEl}
                        open={open}
                        onClose={handleClose}
                        MenuListProps={{
                          "aria-labelledby": "basic-button",
                        }}
                      >
                        <Box className="w-52">
                          {/* Itens do menu */}
                          <MenuItem
                            className="gap-2"
                            onClick={() => {
                              handleClose();
                              setVisibilidade(true);
                              setIdChat(idChat);
                            }}
                          >
                            <OpenInNewOutlinedIcon />
                            <Typography
                              color={"text.primary"}
                              fontSize={FontConfig.medium}
                              sx={{ fontWeight: 500 }}
                              onClick={() => {
                                lerTexto(texts.chat.abrirPopUp);
                              }}
                            >
                              {texts.chat.abrirPopUp}
                            </Typography>
                          </MenuItem>

                          {listaChats.some((chat) => {
                            let demanda = chat.idProposta
                              ? chat.idProposta
                              : chat.idDemanda;
                            return (
                              chat.id == idChat &&
                              demanda.solicitante.id != user.usuario.id &&
                              demanda.status != "CANCELLED" &&
                              demanda.status != "Cancelled"
                            );
                          }) && (
                            <>
                              <div className="w-full flex justify-center">
                                <hr className="w-10/12 my-1.5" />
                              </div>

                              {chatAtual?.conversaEncerrada ? (
                                <MenuItem
                                  className="gap-2"
                                  onClick={() => {
                                    handleClose();
                                    abrirModalAbrirChat();
                                  }}
                                >
                                  <CommentOutlinedIcon
                                    sx={{
                                      fontSize: "25px",
                                      color: "tertiary.main",
                                      cursor: "pointer",
                                    }}
                                  />
                                  <Typography
                                    color={"text.primary"}
                                    fontSize={FontConfig.medium}
                                    sx={{ fontWeight: 500 }}
                                    onClick={() => {
                                      lerTexto(texts.chat.reabrirChat);
                                    }}
                                  >
                                    {texts.chat.reabrirChat}
                                  </Typography>
                                </MenuItem>
                              ) : (
                                <MenuItem
                                  className="gap-2"
                                  onClick={() => {
                                    handleClose();
                                    abrirModalCancelarChat();
                                  }}
                                >
                                  <CommentsDisabledOutlinedIcon
                                    sx={{
                                      fontSize: "25px",
                                      color: "tertiary.main",
                                      cursor: "pointer",
                                    }}
                                  />
                                  <Typography
                                    color={"text.primary"}
                                    fontSize={FontConfig.medium}
                                    sx={{ fontWeight: 500 }}
                                    onClick={() => {
                                      lerTexto(texts.chat.encerrarChat);
                                    }}
                                  >
                                    {texts.chat.encerrarChat}
                                  </Typography>
                                </MenuItem>
                              )}
                            </>
                          )}
                        </Box>
                      </Menu>
                    </Box>
                  </Box>
                  {buscandoMensagens ? (
                    <Box>
                      <Box className="mt-6 w-full h-full flex justify-center items-center">
                        <ClipLoader color="#00579D" size={110} />
                      </Box>
                    </Box>
                  ) : (
                    <Box
                      ref={boxRef}
                      className="flex flex-col"
                      sx={{
                        width: "100%",
                        height: "85%",
                        overflowY: "auto",
                        overflowX: "hidden",
                      }}
                    >
                      {/* Componente mensagens, é cada mensagem que aparece */}
                      {mensagens.length > 0 &&
                        mensagens.map((mensagemDoMap, index) => {
                          return (
                            <Mensagem
                              key={index}
                              mensagem={mensagemDoMap}
                              index={index}
                            />
                          );
                        })}
                    </Box>
                  )}
                  <Box
                    className="flex border px-3 py-1 m-4 rounded items-center"
                    sx={{
                      backgroundColor: "input.main",
                      width: "90%",
                      height: "6.5%",
                      minHeight: "2.5rem",
                    }}
                  >
                    {chatAtual?.conversaEncerrada ? (
                      <>
                        <Box
                          disabled
                          className="w-full h-full flex items-center"
                          component="textarea"
                          sx={{
                            backgroundColor: "input.main",
                            outline: "none",
                            color: "text.primary",
                            fontSize: FontConfig.medium,
                            paddingTop: "0.4rem",
                            resize: "none",
                          }}
                          placeholder={texts.chat.inputChatEncerrado}
                          value={mensagem.texto}
                        />
                      </>
                    ) : (
                      <>
                        <Box
                          onChange={atualizaMensagem}
                          onKeyDown={(event) => {
                            if (event.key === "Enter" && !event.shiftKey) {
                              event.preventDefault(); // Evita quebra de linha ao enviar mensagem
                              submit(event); // Função que envia a mensagem
                            }
                          }}
                          className="w-full h-full flex items-center"
                          component="textarea"
                          sx={{
                            backgroundColor: "input.main",
                            outline: "none",
                            color: "text.primary",
                            fontSize: FontConfig.medium,
                            resize: "none",
                            overflow: "hidden",
                            paddingTop: "0.3rem",
                            minHeight: "2rem",
                          }}
                          placeholder={texts.chat.escrevaSuaMensagem}
                          value={mensagem.texto}
                        />
                        <Tooltip
                          className="hover:cursor-pointer"
                          title={texts.homeGerencia.gravarAudio}
                          onClick={() => {
                            startRecognition("mensagem");
                          }}
                        >
                          {escutar && localClique == "mensagem" ? (
                            <MicOutlinedIcon
                              sx={{
                                cursor: "pointer",
                                color: "primary.main",
                                fontSize: "1.7rem",
                              }}
                            />
                          ) : (
                            <MicNoneOutlinedIcon
                              sx={{
                                color: "text.secondary",
                                fontSize: "1.7rem",
                              }}
                            />
                          )}
                        </Tooltip>
                        <Box className="flex gap-2 delay-120 hover:scale-110 duration-300">
                          <Tooltip title={texts.chat.enviarAnexo}>
                            <IconButton
                              onClick={() => {
                                inputRef.current.click();
                              }}
                            >
                              <AttachFileOutlinedIcon
                                sx={{
                                  color: "primary.main",
                                  cursor: "pointer",
                                }}
                              />
                            </IconButton>
                          </Tooltip>
                          <input
                            ref={inputRef}
                            type="file"
                            style={{ display: "none" }}
                            onChange={handleFileUpload}
                          />
                        </Box>
                        <Divider
                          orientation="vertical"
                          sx={{
                            borderColor: "primary.main",
                            margin: "0 10px 0 10px",
                          }}
                        />
                        <Box className="flex gap-2 delay-120 hover:scale-110 duration-300">
                          <Tooltip title={texts.chat.enviarMensagem}>
                            <IconButton onClick={submit}>
                              <SendOutlinedIcon
                                sx={{
                                  color: "primary.main",
                                  cursor: "pointer",
                                }}
                              />
                            </IconButton>
                          </Tooltip>
                        </Box>
                      </>
                    )}
                  </Box>
                </Box>
              )}
            </Box>
          </Box>
        </Box>
      </FundoComHeader>
    </>
  );
};

export default Chat;
