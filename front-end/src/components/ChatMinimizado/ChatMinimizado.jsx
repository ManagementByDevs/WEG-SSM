import React, { useContext, useEffect, useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import ClipLoader from "react-spinners/ClipLoader";

import { keyframes } from "@emotion/react";

import { Box, Tooltip, Divider, Typography, IconButton } from "@mui/material";

import Mensagem from "../../components/Mensagem/Mensagem";

import AttachFileOutlinedIcon from "@mui/icons-material/AttachFileOutlined";
import SendOutlinedIcon from "@mui/icons-material/SendOutlined";
import CloseOutlinedIcon from "@mui/icons-material/CloseOutlined";
import OpenInNewOutlinedIcon from "@mui/icons-material/OpenInNewOutlined";
import RemoveOutlinedIcon from "@mui/icons-material/RemoveOutlined";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";

import ChatContext from "../../service/ChatContext";
import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import ChatService from "../../service/chatService";
import UsuarioService from "../../service/usuarioService";
import DateService from "../../service/dateService";
import AnexoService from "../../service/anexoService";
import { MensagemService } from "../../service/MensagemService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import { WebSocketContext } from "../../service/WebSocketService";

/** Componente utilizado para representar a versão minimizada do chat */
const ChatMinimizado = () => {

  /** Contexto para trocar a linguagem */
  const { texts } = useContext(TextLanguageContext);

  /** Contexto para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Navigate utilizado para nevegar para uma outra página */
  const navigate = useNavigate();

  /** Contexto para controlar a visibilidade do chat */
  const { visibilidade, setVisibilidade, idChat } = useContext(ChatContext);

  // UseState para controlar o tamanho do chat ( minimizado ou não )
  const [minimizarChatMinimizado, setMinimizarChatMinimizado] = useState(false);

  // UseState para setar o tamanho do chat minimizado
  const [tamanhoChatMinimizado, setTamanhoChatMinimizado] = useState("24rem");

  // UseContext para o chat
  const { enviar, inscrever, stompClient } = useContext(WebSocketContext);

  // UseRef para setar o scroll do chat
  const boxRef = useRef(null);

  // UseRef para setar o input de mensagem
  const inputRef = useRef(null);

  /** UseState para pesquisar um contato da lista */
  const [pesquisaContato, setPesquisaContato] = useState("");

  /** UseState para armazenar os resultados da pesquisa */
  const [resultadosContato, setResultadosContato] = useState([
    EntitiesObjectService.chat(),
  ]);

  /** UseState para controlar o loading de mensagens */
  const [buscandoMensagens, setBuscandoMensagens] = useState(true);

  /** UseState para feedback de que anexo é muito grande */
  const [feedbackAnexoGrande, setFeedbackAnexoGrande] = useState(false);

  /** Lista de chats do usuário */
  const [listaChats, setListaChats] = useState([EntitiesObjectService.chat()]);

  /** Mensagem que está sendo digitada */
  const [mensagem, setMensagem] = useState(EntitiesObjectService.mensagem());

  /** Todas as mensagens do chat selecionado */
  const [mensagens, setMensagens] = useState([EntitiesObjectService.mensagem(),]);

  /** Usuário logado */
  const [user, setUser] = useState(UsuarioService.getUserCookies());

  /** useEffect utilizado para carregar os chats */
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

  /** useEffect utilizado para mandar uma nova mensagem */
  useEffect(() => {
    const acaoNovaMensagem = (response) => {
      const mensagemRecebida = JSON.parse(response.body);
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

  /** useEffect utilizado para colocar scroll no chat */
  useEffect(() => {
    setBuscandoMensagens(false);

    const boxElement = boxRef.current;

    if (boxElement) {
      boxElement.scrollTop = boxElement.scrollHeight;
    }
  }, [mensagens]);

  /** Função para retornar uma conversa que ja foi encerrada */
  const retornaConversaEncerrada = () => {
    for (let chatInput of listaChats) {
      if (chatInput.id == idChat) {
        if (chatInput.conversaEncerrada) {
          return true;
        }
      }
    }

    return false;
  };

  /** Funlão para pegar o id do destinatário do chat */
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
      data: DateService.getTodaysDate(texts.linguagem),
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
      enviar(
        `/app/weg_ssm/mensagem/${idChat}`,
        JSON.stringify({
          ...mensagem,
          texto: mensagem.texto.replace(/\n/g, "%BREAK%"),
        })
      );
      setDefaultMensagem();
    }
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
        .catch((error) => { });
    } else {
      setFeedbackAnexoGrande(true);
    }
    setDefaultMensagem();
  };

  /** Busca os chats do usuário */
  const buscarChats = () => {
    ChatService.getByRemetente(user.usuario.id).then((chatResponse) => {
      setListaChats([...chatResponse]);
    });
  };

  /** Busca as mensagens do usuário */
  const carregar = () => {
    MensagemService.getMensagensChat(idChat).then((response) => {
      setMensagens(response);

      enviar(`/app/weg_ssm/enter/chat/${idChat}`, user.usuario.id);
    });
    setDefaultMensagem();
  };

  /** Marca as notificações como vistas */
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

  /** Função para minimizar o chat */
  const sumir = keyframes({
    from: { height: "24rem" },
    to: { height: "2.88rem" },
  });

  /** Função para abrir o chat */
  const aparecer = keyframes({
    from: { height: "2.88rem" },
    to: { height: "24rem" },
  });

  // // ********************************************** Gravar audio **********************************************

  /** Variável para abrir o feedbcak de navegador incompatível */
  const [feedbackErroNavegadorIncompativel, setFeedbackErroNavegadorIncompativel,] = useState(false);

  /** Variável para abrir o feedback de erro ao reconhecer voz */
  const [feedbackErroReconhecimentoVoz, setFeedbackErroReconhecimentoVoz] = useState(false);

  /** Varíavel utilizada para lógica de gravação de audio */
  const recognitionRef = useRef(null);

  /** Variável utilizada para ativar o microfone para gravação de audio */
  const [escutar, setEscutar] = useState(false);

  /** Varíavel utilizada para armazenar o local onde foi clicado */
  const [localClicado, setLocalClicado] = useState("");

  /** Varíavel utilizada para concatenar palavras ao receber resultados da transcrição de voz */
  const [palavrasJuntas, setPalavrasJuntas] = useState("");

  /** Função para gravar audio nos inputs */
  const ouvirAudio = () => {
    /** Verifica se a API é suportada pelo navegador */
    if ("webkitSpeechRecognition" in window) {
      const recognition = new window.webkitSpeechRecognition();
      recognition.continuous = true;
      switch (texts.linguagem) {
        case "pt":
          recognition.lang = "pt-BR";
          break;
        case "en":
          recognition.lang = "en-US";
          break;
        case "es":
          recognition.lang = "es-ES";
          break;
        case "ch":
          recognition.lang = "cmn-Hans-CN";
          break;
        default:
          recognition.lang = "pt-BR";
          break;
      }

      recognition.onstart = () => { };

      recognition.onresult = (event) => {
        const transcript =
          event.results[event.results.length - 1][0].transcript;
        setPalavrasJuntas((palavrasJuntas) => palavrasJuntas + transcript);
        // setValorPesquisa(transcript);
      };

      recognition.onerror = (event) => {
        setFeedbackErroReconhecimentoVoz(true);
        setEscutar(false);
      };

      recognitionRef.current = recognition;
      recognition.start();
    } else {
      setFeedbackErroNavegadorIncompativel(true);
      setEscutar(false);
    }
  };

  /** useEffect utilizado para armazenar as palavras juntas dependendo do local onde foi clicado */
  useEffect(() => {
    switch (localClicado) {
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

  /** useEffect utilizado para verificar se a gravação ainda está funcionando */
  useEffect(() => {
    if (escutar) {
      ouvirAudio();
    } else {
      stopRecognition();
    }
  }, [escutar]);

  /** Função para encerrar a gravação de voz */
  const stopRecognition = () => {
    if (recognitionRef.current) {
      recognitionRef.current.stop();
    }
  };

  /** Função para iniciar a gravação de voz */
  const startRecognition = (ondeClicou) => {
    setEscutar(!escutar);
    setLocalClicado(ondeClicou);
  };

  return (
    <Box
      className="absolute bottom-0 right-24 w-96 h-96 z-100"
      sx={{
        backgroundColor: "component.main",
        borderRadius: "10px 10px 0 0",
        animation: `${tamanhoChatMinimizado} 1s forwards`,
      }}
    >
      {/* Container para o cabeçalho do chat */}
      <Box
        className="w-full h-11 pl-4 pr-2 py-1 items-center flex justify-between"
        sx={{
          backgroundColor: "primary.main",
          borderRadius: "10px 10px 0 0",
          borderBottom: "4px #FFFF solid",
        }}
      >
        {/* Nome do chat */}
        <Typography color="#FFF" fontSize={FontConfig.default} fontWeight="600">
          {listaChats.map((chat) => {
            let nomeChat;
            if (chat.id == idChat) {
              chat.usuariosChat.map((usuarioChat) => {
                if (usuarioChat.id != user.usuario.id) {
                  nomeChat = usuarioChat.nome;
                }
              });
            }
            return nomeChat;
          })}
        </Typography>
        {/* Container para os ícones do cabeçalho */}
        <Box>
          {/* Icone para minimizar ou maximizar o chat */}
          {!minimizarChatMinimizado ? (
            // Icone para minimizar o chat
            <Tooltip title={texts.chatMinimizado.minimizar}>
              <RemoveOutlinedIcon
                className="mr-2 cursor-pointer delay-120 hover:scale-110 duration-300"
                sx={{ color: "#FFF" }}
                onClick={() => {
                  setTamanhoChatMinimizado(sumir);
                  setMinimizarChatMinimizado(true);
                }}
              />
            </Tooltip>
          ) : (
            // Icone para maximizar o chat
            <Tooltip title={texts.chatMinimizado.maximazar}>
              <AddOutlinedIcon
                className="mr-2 cursor-pointer delay-120 hover:scale-110 duration-300"
                sx={{ color: "#FFF" }}
                onClick={() => {
                  setTamanhoChatMinimizado(aparecer);
                  setTimeout(() => {
                    setMinimizarChatMinimizado(false);
                  }, 830);
                }}
              />
            </Tooltip>
          )}
          {/* Icone para fazer dar tela cheia */}
          <Tooltip title={texts.chatMinimizado.telaCheia}>
            <OpenInNewOutlinedIcon
              className="mr-2 cursor-pointer delay-120 hover:scale-110 duration-300"
              sx={{ fontSize: "20px", color: "#FFF" }}
              onClick={() => {
                setVisibilidade(false);
                navigate(`/chat/${idChat}`);
              }}
            />
          </Tooltip>
          {/* Icone para fechar o chat */}
          <Tooltip title={texts.chatMinimizado.fecharChat}>
            <CloseOutlinedIcon
              className="cursor-pointer delay-120 hover:scale-110 duration-300"
              sx={{ fontSize: "25px", color: "#FFF" }}
              onClick={() => {
                setVisibilidade(false);
              }}
            />
          </Tooltip>
        </Box>
      </Box>
      {!minimizarChatMinimizado ? (
        <>
          {/*Container para o chat  */}
          <Box className="flex flex-col items-center justify-center h-72">
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
                        usuario={mensagemDoMap.usuario}
                      />
                    );
                  })}
              </Box>
            )}
          </Box>
          {/* Container para o input de mensagem */}
          <Box className="flex justify-center">
            <Box
              className="flex justify-between items-center border pl-3 pr-2 py-1 rounded"
              sx={{ backgroundColor: "input.main", width: "85%" }}
            >
              {retornaConversaEncerrada() == true ? (
                <>
                  <Box
                    disabled={true}
                    className="flex items-center overflow-hidden truncate pb-5"
                    component="textarea"
                    sx={{
                      width: "60%",
                      height: "1rem",
                      backgroundColor: "input.main",
                      outline: "none",
                      color: "text.primary",
                      fontSize: FontConfig.default,
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
                    className="flex items-center overflow-hidden truncate"
                    component="textarea"
                    sx={{
                      width: "75%",
                      height: "2rem",
                      paddingTop: "0.3rem",
                      backgroundColor: "input.main",
                      outline: "none",
                      color: "text.primary",
                      fontSize: FontConfig.default,
                      resize: "none",
                      overflowY: "auto",
                    }}
                    placeholder={texts.chat.escrevaSuaMensagem}
                    value={mensagem.texto}
                  />
                  <Box className="flex delay-120 hover:scale-110 duration-300">
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
                            fontSize: FontConfig.veryBig,
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
                    }}
                  />
                  <Box className="flex gap-2 delay-120 hover:scale-110 duration-300">
                    <Tooltip title={texts.chat.enviarMensagem}>
                      <IconButton onClick={submit}>
                        <SendOutlinedIcon
                          sx={{
                            color: "primary.main",
                            cursor: "pointer",
                            fontSize: FontConfig.veryBig,
                          }}
                        />
                      </IconButton>
                    </Tooltip>
                  </Box>
                </>
              )}
            </Box>
          </Box>
        </>
      ) : null}
    </Box>
  );
};

export default ChatMinimizado;