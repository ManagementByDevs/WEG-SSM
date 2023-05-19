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
import { MensagemService } from "../../service/MensagemService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import dateService from "../../service/dateService";
import { WebSocketContext } from "../../service/WebSocketService";
import anexoService from "../../service/anexoService";

// Componente utilizado para representar a versão minimizada do chat
const ChatMinimizado = (props) => {

  // Contexto para trocar a linguagem
  const { texts } = useContext(TextLanguageContext);

  // Navigate utilizado para nevegar para uma outra página
  const navigate = useNavigate();

  // Contexto para controlar a visibilidade do chat
  const { visibilidade, setVisibilidade, idChat } = useContext(ChatContext);

  // Contexto para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // UseState para controlar o tamanho do chat ( minimizado ou não )
  const [minimizarChatMinimizado, setMinimizarChatMinimizado] = useState(false);

  // UseState para setar o tamanho do chat minimizado
  const [tamanhoChatMinimizado, setTamanhoChatMinimizado] = useState("24rem");

  // UseState para receber todos os chats do user
  const [listaChats, setListaChats] = useState([]);

  // UseState para receber o id do usuário
  const [user, setUser] = useState(UsuarioService.getUserCookies());

  // UseState para receber o chat selecionado
  const [chat, setChat] = useState({});

  // UseState para receber true se estiver buscando mensagens
  const [buscandoMensagens, setBuscandoMensagens] = useState(true);

  // UseContext para o chat
  const { enviar, inscrever, stompClient } = useContext(WebSocketContext);

  // UseState para receber todas as mensagens do chat
  const [mensagens, setMensagens] = useState([
    EntitiesObjectService.mensagem(),
  ]);

  // UseState para setar a mensagem
  const [mensagem, setMensagem] = useState(EntitiesObjectService.mensagem());

  // UseRef para setar o scroll do chat
  const boxRef = useRef(null);

  // UseRef para setar o input de mensagem
  const inputRef = useRef(null);

  // UseEffect para carregar as mensagens do chat
  useEffect(() => {
    const acaoNovaMensagem = (response) => {
      const mensagemRecebida = JSON.parse(response.body);
      let mensagemNova = {
        ...mensagemRecebida.body,
        texto: mensagemRecebida.body.texto.replace(/%BREAK%/g, "\n"),
      };
      setMensagens((oldMensagens) => [...oldMensagens, mensagemNova]);
    };

    if (idChat) {
      let inscricaoId = inscrever(
        `/weg_ssm/mensagem/${idChat}/chat`,
        acaoNovaMensagem
      );

      return () => {
        if (inscricaoId) {
          inscricaoId.unsubscribe();
        }
      };
    }
  }, [stompClient, idChat]);

  // UseEffect para carregar as mensagens do chat
  useEffect(() => {
    setBuscandoMensagens(false);
    const boxElement = boxRef.current;
    if (boxElement) {
      boxElement.scrollTop = boxElement.scrollHeight;
    }
  }, [mensagens]);

  // UseEffect para buscar todos os chats do usuário
  useEffect(() => {
    buscarChats();
  }, []);

  // UseEffect para retornar o chat selecionado
  useEffect(() => {
    retornaChat();
  }, [listaChats]);

  // Função para carregar as mensagens do chat
  async function carregar() {
    await MensagemService.getMensagensChat(idChat)
      .then((response) => {
        setMensagens(response);
      })
      .catch((error) => { });
    setDefaultMensagem();
  }

  // Função para setar uma mensagem default
  const setDefaultMensagem = () => {
    setMensagem({
      data: dateService.getTodaysDate(),
      visto: false,
      texto: "",
      status: "MESSAGE",
      usuario: { id: user.usuario.id },
      idChat: { id: idChat },
      anexo: [],
    });
  };

  // Função para enviar um anexo
  function handleFileUpload(event) {
    const file = event.target.files[0];
    event.preventDefault();
    anexoService
      .save(file)
      .then((response) => {
        enviar(`/app/weg_ssm/mensagem/${idChat}`, {
          ...mensagem,
          anexo: response,
        });
        inputRef.current.value = "";
      })
      .catch((error) => {
        console.log(error);
      });
    setDefaultMensagem();
  }

  // Função para atualizar a mensagem
  const atualizaMensagem = (event) => {
    const { value } = event.target;
    setMensagem({ ...mensagem, texto: value });
  };

  // Função para enviar a mensagem
  const submit = async (event) => {
    if (mensagem.texto !== "") {
      event.preventDefault();
      enviar(`/app/weg_ssm/mensagem/${idChat}`, mensagem);
      setDefaultMensagem();
    }
  };

  // Função para minimizar o chat
  const sumir = keyframes({
    from: { height: "24rem" },
    to: { height: "2.88rem" },
  });

  // Função para abrir o chat
  const aparecer = keyframes({
    from: { height: "2.88rem" },
    to: { height: "24rem" },
  });

  // Função para buscar todos os chats do usuário e setar na listaChats
  async function buscarChats() {
    await ChatService.getByRemetente(user.usuario.id).then((e) => {
      setListaChats(e);
    });
  }

  // Função para retornar o chat selecionado
  const retornaChat = () => {
    listaChats.map((chat) => {
      if (chat.id == idChat) {
        setChat(chat);
      }
    });
  };

  // Função para retornar true se a conversa estiver encerrada
  const retornaConversaEncerrada = () => {
    let valor = false;
    listaChats.map((chatInput) => {
      if (chatInput.id == idChat) {
        if (chatInput.conversaEncerrada == true) {
          valor = true;
        }
      }
    });
    return valor;
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
