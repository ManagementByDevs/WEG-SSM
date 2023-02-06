import React, { useState, useContext, useEffect } from "react";
import {
  Box,
  Avatar,
  Typography,
  Divider,
  Tooltip,
  IconButton,
} from "@mui/material";

import { useNavigate, useLocation } from "react-router-dom";

import logoWeg from "../../assets/logo-weg.png";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import Contato from "../../components/Contato/Contato";
import Mensagem from "../../components/Mensagem/Mensagem";

import FontConfig from "../../service/FontConfig";

import CommentsDisabledIcon from "@mui/icons-material/CommentsDisabled";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import AttachFileOutlinedIcon from "@mui/icons-material/AttachFileOutlined";
import SendOutlinedIcon from "@mui/icons-material/SendOutlined";

import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";

import FontContext from "../../service/FontContext";

import { over } from "stompjs";
import SockJS from "sockjs-client";

var stompClient = null;

const Chat = () => {

  const navigate = useNavigate();
  const location = useLocation();

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  const [pesquisaContato, setpesquisaContato] = useState("");
  const [resultadosContato, setresultadosContato] = useState([]);

  const onChange = (evt) => {
    setpesquisaContato(evt.target.value);
  };

  useEffect(() => {
    const resultados = [];
    usuarios.filter((usuario) => {
      if (usuario.nome.toLowerCase().includes(pesquisaContato.toLowerCase())) {
        resultados.push(usuario);
      }
    });
    setresultadosContato(resultados);
  }, [pesquisaContato]);

  const [usuarios, setUsuarios] = useState([
    {
      foto: "",
      nome: "Thiago",
      cargo: "Gerente",
      demanda: "Colocar pote de bala nas mesas de TI",
      codigoDemanda: "#123456",
      mensagens: [
        {
          texto: "Olá, tudo bem?",
          data: "10/10/2021",
          hora: "10:00",
          remetente: "Thiago",
        },
        {
          texto:
            "Tudo sim, e você? Vou testar um texto grande aqui, então vou escrever muito aqui para ter um exemplo de como é um texto grande.",
          data: "10/10/2021",
          hora: "10:01",
          remetente: "Eu",
        },
        {
          texto: "Tudo bem",
          data: "10/10/2021",
          hora: "10:02",
          remetente: "Thiago",
        },
      ],
    },
    {
      foto: "https://avatars.githubusercontent.com/u/54643525?v=4",
      nome: "Kenzo",
      cargo: "Analista",
      demanda: "Ver se o Shrek está bem",
      codigoDemanda: "#654321",
      mensagens: [
        {
          texto: "こんにちは元気ですか？",
          data: "10/10/2021",
          hora: "10:00",
          remetente: "Kenzo",
        },
        {
          texto: "はい、元気です",
          data: "10/10/2021",
          hora: "10:01",
          remetente: "Eu",
        },
        {
          texto: "それは良いですね",
          data: "10/10/2021",
          hora: "10:02",
          remetente: "Kenzo",
        },
      ],
    },
    {
      foto: "https://avatars.githubusercontent.com/u/54643525?v=4",
      nome: "Matheus",
      cargo: "Solicitante",
      demanda: "Visitar o futuro",
      codigoDemanda: "#24783",
      mensagens: [
        {
          texto: "Fala man, bó jogar?",
          data: "10/10/2021",
          hora: "10:00",
          remetente: "Matheus",
        },
        {
          texto: "Vamo sim",
          data: "10/10/2021",
          hora: "10:01",
          remetente: "Eu",
        },
        {
          texto: "Vamo",
          data: "10/10/2021",
          hora: "10:02",
          remetente: "Matheus",
        },
      ],
    },
    {
      foto: "https://avatars.githubusercontent.com/u/54643525?v=4",
      nome: "Vieira",
      cargo: "Gestor",
      demanda: "Conhecer o Relampago Marquinhos",
      codigoDemanda: "#32467",
      mensagens: [
        {
          texto: "Katchau!",
          data: "10/10/2021",
          hora: "10:00",
          remetente: "Vieira",
        },
        {
          texto: "Não sabia que o Relampago Marquinhos era Brasileiro",
          data: "10/10/2021",
          hora: "10:01",
          remetente: "Eu",
        },
        {
          texto: "No soy, I'm from Argentina",
          data: "10/10/2021",
          hora: "10:02",
          remetente: "Vieira",
        },
      ],
    },
  ]);

  const [indexUsuario, setIndexUsuario] = useState();

  function abrirChat(index) {
    setIndexUsuario(index); 
    setTab(usuarios[index].nome)
  }

  const [texto, setTexto] = useState();

  const save = (e) => {
    setUserData({ ...userData, "message": e.target.value });
  };

  const salvarTexto = () => {
    // aqui é o salvar (utiliza a variavel texto para pegar o valor do texto)
  };

  const [abrirModal, setOpenModal] = useState(false);

  const abrirModalCancelarChat = () => {
    setOpenModal(true);
  };

  const fecharModalCancelarChat = () => {
    setOpenModal(false);
  };

  const deletarChat = () => {
    fecharModalCancelarChat();
    // aqui é o deletar
  };

  // Começo da integração do chat

  const [privateChats, setPrivateChats] = useState(new Map());
  const [publicChats, setPublicChats] = useState([]);
  const [tab, setTab] = useState("CHATROOM");

  let nomeUsuario;  

  nomeUsuario = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    if (location.state?.userChat) {
        connect();
    }
}, [location.state?.userChat]);

  const [userData, setUserData] = useState({
    username: nomeUsuario?.nome,
    receivername: '',
    connected: false,
    message: ''
  });

  useEffect(() => {
    console.log(userData);
  }, [userData]);

  const connect = () => {
    let Sock = new SockJS('http://localhost:8080/ws');
    stompClient = over(Sock);
    stompClient.connect({}, onConnected, onError);
  };

  const onConnected = () => {
    setUserData({ ...userData, "connected": true });
    stompClient.subscribe('/chat/public', onMessageReceived);
    stompClient.subscribe('/user/' + userData.username + '/private', onPrivateMessage);
    userJoin();
  };

  const userJoin = () => {
    var chatMessage = {
      senderName: userData.username,
      status: "JOIN"
    };

    stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
  };

  const onMessageReceived = (payload) => {
    var payloadData = JSON.parse(payload.body);

    switch (payloadData.status) {
      case "JOIN":
        if (!privateChats.get(payloadData.username)) {
          privateChats.set(payloadData.senderName, []);
          setPrivateChats(new Map(privateChats));
        }
        break;
      case "MESSAGE":
        publicChats.push(payloadData);
        setPublicChats([...publicChats]);
        break;
    }
  };

  const onPrivateMessage = (payload) => {
    console.log(payload);

    var payloadData = JSON.parse(payload.body);

    if (privateChats.get(payload.senderName)) {
      privateChats.get(payloadData.senderName).push(payloadData);
      setPrivateChats(new Map(privateChats));
    } else {
      let list = [];
      list.push(payloadData);
      privateChats.set(payloadData.senderName, list);
      setPrivateChats(new Map(privateChats));
    }
  };

  const onError = (err) => {
    console.log(err);
  };

  const handleMessage = (event) => {
    const { value } = event.target;
    setUserData({ ...userData, "message": value });
  };

  const sendValue = () => {
    if (stompClient) {
      var chatMessage = {
        senderName: userData.username,
        message: userData.message,
        status: "MESSAGE"
      };

      console.log(chatMessage);
      stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
      setUserData({ ...userData, "message": "" });
    }
  };

  const sendPrivateValue = () => {
    if (stompClient) {
      var chatMessage = {
        senderName: userData.username,
        receiverName: tab,
        message: userData.message,
        status: "MESSAGE"
      }

      if (userData.username != tab) {
        privateChats.get(tab).push(chatMessage);
        setPrivateChats(new Map(privateChats));
      }

      stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
      setUserData({ ...userData, "message": "" });
    }
  };

  return (
    <>
      {
        abrirModal && (
          <ModalConfirmacao open={abrirModal} setOpen={setOpenModal} textoModal={"fecharChat"} onConfirmClick={deletarChat} onCancelClick={fecharModalCancelarChat} textoBotao={"sim"} />
        )
      }
      <FundoComHeader>
        <Box className="p-2">
          <Caminho />
          <Box className="w-full flex justify-center items-center">
            <Box
              className="flex justify-evenly items-center rounded border mt-4"
              sx={{ width: "100rem", height: "50rem" }}
            >
              <Box
                className="flex items-center rounded border flex-col gap-3 overflow-y-auto overflow-x-hidden"
                sx={{ width: "20%", height: "95%" }}
              >
                <Box
                  className="flex border px-3 py-1 m-4 rounded-lg"
                  sx={{
                    backgroundColor: "input.main",
                    width: "90%",
                    height: "5%",
                  }}
                >
                  <Box
                    className="w-full"
                    component="input"
                    onChange={onChange}
                    sx={{
                      backgroundColor: "input.main",
                      outline: "none",
                      color: "text.primary",
                      fontSize: FontConfig.medium,
                    }}
                    placeholder="Pesquisar por nome..."
                  />
                  <Box className="flex gap-2">
                    <SearchOutlinedIcon sx={{ color: "text.secondary" }} />
                  </Box>
                </Box>
                {resultadosContato.map((resultado, index) => {
                  return (
                    <Contato
                      key={index}
                      onClick={() => {
                        abrirChat(index);
                      }}
                      usuario={resultado}
                      index={index}
                      usuarioAtual={indexUsuario}
                    />
                  );
                })}
              </Box>
              {indexUsuario == null ? (
                <Box
                  className="flex flex-col items-center justify-center rounded border"
                  sx={{ width: "75%", height: "95%", cursor: "default" }}
                >
                  <img src={logoWeg} alt="chat" />
                  <Typography
                    fontSize={FontConfig.title}
                    color={"text.secondary"}
                    sx={{ fontWeight: "600" }}
                  >
                    Selecione alguma conversa
                  </Typography>
                </Box>
              ) : (
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
                        src={usuarios[indexUsuario].foto}
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
                          {usuarios[indexUsuario].nome}
                        </Typography>
                        <Typography fontSize={FontConfig.small}>
                          {usuarios[indexUsuario].cargo}
                        </Typography>
                      </Box>
                    </Box>
                    <Box className="mr-5">
                      <Tooltip title="Encerrar chat">
                        <IconButton onClick={abrirModalCancelarChat}>
                          <CommentsDisabledIcon
                            sx={{
                              fontSize: "30px",
                              color: "#FFFF",
                              cursor: "pointer",
                            }}
                          />
                        </IconButton>
                      </Tooltip>
                    </Box>
                  </Box>
                  <Box
                    className="flex flex-col mt-4"
                    sx={{ width: "95%", height: "85%" }}
                  >
                    {/* Por enquanto está usando um componente mensagens para pegar as mensagens */}
                    {usuarios[indexUsuario].mensagens.map((mensagem, index) => {
                      return (
                        <Mensagem
                          key={index}
                          mensagem={mensagem}
                          index={index}
                          usuario={usuarios[indexUsuario]}
                        />
                      );
                    })}
                  </Box>
                  <Box
                    className="flex border px-3 py-1 m-4 rounded items-center"
                    sx={{
                      backgroundColor: "input.main",
                      width: "90%",
                      height: "6.5%",
                    }}
                  >
                    <Box
                      onChange={(e) => {
                        save(e);
                      }}
                      className="w-full"
                      component="input"
                      sx={{
                        backgroundColor: "input.main",
                        outline: "none",
                        color: "text.primary",
                        fontSize: FontConfig.medium,
                      }}
                      placeholder="Escreva sua mensagem..."
                    />
                    <Box className="flex gap-2 delay-120 hover:scale-110 duration-300">
                      <Tooltip title="Enviar Anexo">
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
                      <Tooltip title="Enviar mensagem">
                        <IconButton
                          onClick={() => {
                            salvarTexto();
                          }}
                        >
                          <SendOutlinedIcon
                            sx={{ color: "primary.main", cursor: "pointer" }}
                          />
                        </IconButton>
                      </Tooltip>
                    </Box>
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
