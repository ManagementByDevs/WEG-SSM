import React, { useState, useContext, useEffect } from "react";
import {
  Box,
  Avatar,
  Typography,
  Divider,
  Tooltip,
  IconButton,
  Menu,
  MenuItem,
  FormControlLabel,
} from "@mui/material";

import { useLocation } from "react-router-dom";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import Contato from "../../components/Contato/Contato";
import Mensagem from "../../components/Mensagem/Mensagem";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import Ajuda from "../../components/Ajuda/Ajuda";

import logoWeg from "../../assets/logo-weg.png";
import CommentsDisabledOutlinedIcon from "@mui/icons-material/CommentsDisabledOutlined";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import MoreVertOutlinedIcon from "@mui/icons-material/MoreVertOutlined";
import AttachFileOutlinedIcon from "@mui/icons-material/AttachFileOutlined";
import SendOutlinedIcon from "@mui/icons-material/SendOutlined";
import OpenInNewOutlinedIcon from "@mui/icons-material/OpenInNewOutlined";

import UsuarioService from "../../service/usuarioService";

import FontContext from "../../service/FontContext";
import ChatContext from "../../service/ChatContext";

import { over } from "stompjs";
import SockJS from "sockjs-client";

import Tour from "reactour";

var stompClient = null;

const Chat = () => {
  const { setVisibilidade, visibilidade } = useContext(ChatContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Location utilizado para conectar o usuário no webSocket
  const location = useLocation();

  // UseState para pesquisar um contato da lista
  const [pesquisaContato, setpesquisaContato] = useState("");

  // UseState para armazenar os resultados da pesquisa
  const [resultadosContato, setresultadosContato] = useState([]);

  // UseState para armazenar o contato selecionado
  const onChange = (evt) => {
    setpesquisaContato(evt.target.value);
  };

  // UseState para armazenar o contato selecionado
  useEffect(() => {
    const resultados = [];
    usuarios.filter((usuario) => {
      if (usuario.nome.toLowerCase().includes(pesquisaContato.toLowerCase())) {
        resultados.push(usuario);
      }
    });
    setresultadosContato(resultados);
  }, [pesquisaContato]);

  // Lista de exemplo com usuários
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
    setTab(usuarios[index].nome);
  }

  const [texto, setTexto] = useState();

  const save = (e) => {
    setUserData({ ...userData, message: e.target.value });
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

  // Map para salvar os chats privados
  const [privateChats, setPrivateChats] = useState(new Map());

  // Lista para armazenar os chats públicos
  const [publicChats, setPublicChats] = useState([]);

  // tab para setar o nome do usuário selecionado
  const [tab, setTab] = useState("CHATROOM");

  const [solicitanteTab, setSolicitanteTab] = useState();

  const [listaNomes, setListaNomes] = useState([]);

  // Pegar o usuario
  const [usuarioId, setUsuarioId] = useState();

  let nomeUsuario;

  nomeUsuario = JSON.parse(localStorage.getItem("user"));

  const [userData, setUserData] = useState({
    username: nomeUsuario?.nome,
    receivername: "",
    connected: false,
    message: "",
  });

  const buscarUsuario = () => {
    UsuarioService.getUsuarioById(
      parseInt(localStorage.getItem("usuarioId"))
    ).then((e) => {
      setUsuarioId(e);
    });
  };

  const buscarSolicitante = () => {
    UsuarioService.getUsuarioById(parseInt(tab)).then((e) => {
      setSolicitanteTab(e);
    });
  };

  const buscarNomeLista = (id) => {
    for (let usuario of listaNomes) {
      if (usuario.id === id) {
        return usuario.nome;
      }
    }
  };

  useEffect(() => {
    buscarUsuario();
  }, []);

  useEffect(() => {
    connect();
  }, [usuarioId]);

  useEffect(() => {
    console.log(userData);
  }, [userData]);

  useEffect(() => {
    buscarSolicitante();
  }, [tab]);

  const connect = () => {
    let Sock = new SockJS("http://localhost:8080/ws");
    stompClient = over(Sock);
    stompClient.connect({}, onConnected, onError);
  };

  const onConnected = () => {
    setUserData({ ...userData, connected: true });
    stompClient.subscribe("/chat/public", onMessageReceived);
    stompClient.subscribe(
      "/user/" + userData.username + "/private",
      onPrivateMessage
    );
    userJoin();
  };

  const userJoin = () => {
    const usuario = usuarioId;

    var chatMessage = {
      usuario: usuario,
      status: "JOIN",
    };

    stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
  };

  const onMessageReceived = (payload) => {
    var payloadData = JSON.parse(payload.body);

    console.log("Aqui vem o payload: ", payloadData);

    switch (payloadData.status) {
      case "JOIN":
        if (!privateChats.get(payloadData.usuario.nome)) {
          console.log("seila: ", payloadData.usuario.nome);
          privateChats.set(payloadData.usuario.id, []);
          listaNomes.push({
            nome: payloadData.usuario.nome,
            id: payloadData.usuario.id,
          });
          setPrivateChats(new Map(privateChats));
        }
        break;
      case "MESSAGE":
        publicChats.push(payloadData);
        setPublicChats([...publicChats]);
        console.log("Public chatssssss: ", publicChats);
        break;
    }
  };

  const onPrivateMessage = (payload) => {
    var payloadData = JSON.parse(payload.body);

    console.log("Aqui vem o payload 2: ", payloadData);
    console.log("Private chatss: ", privateChats);

    if (privateChats.get(payloadData.usuario.nome)) {
      privateChats.get(payloadData.usuario.id).push(payloadData);
      setPrivateChats(new Map(privateChats));
      console.log("Private chats 1: ", privateChats);
    } else {
      let list = [];
      list.push(payloadData);
      privateChats.set(payloadData.usuario.id, list);
      listaNomes.push({
        nome: payloadData.usuario.nome,
        id: payloadData.usuario.id,
      });
      setPrivateChats(new Map(privateChats));
      console.log("Private chats 2: ", privateChats);
    }
  };

  const onError = (err) => {
    console.log(err);
  };

  const handleMessage = (event) => {
    const { value } = event.target;
    setUserData({ ...userData, message: value });
  };

  const sendPrivateValue = () => {
    var dataAtual = new Date();

    const usuario = usuarioId;

    if (stompClient) {
      var chatMessage = {
        id: 1,
        data: dataAtual,
        visto: true,
        texto: userData.message,
        status: "MESSAGE",
        usuario: usuario,
        solicitante: solicitanteTab,
      };

      if (userData.username != solicitanteTab.nome) {
        privateChats.get(tab).push(chatMessage);
        setPrivateChats(new Map(privateChats));
      }

      stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
      setUserData({ ...userData, message: "" });
    }
  };

  // UseState para poder visualizar e alterar a visibilidade do menu
  const [anchorEl, setAnchorEl] = useState(null);

  // Variável que é usada para saber se o menu está aberto ou não
  const open = Boolean(anchorEl);

  // Função para abrir o menu
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  // Função para fechar o menu
  const handleClose = () => {
    setAnchorEl(null);
  };

  // useState para abrir e fechar o tour
  const [isTourOpen, setIsTourOpen] = useState(false);

  // Passos do tour
  const stepsTour = [
    {
      selector: "#primeiro",
      content:
        "Neste input você pode pesquisar pelos chats por nome do usuário e pelo número sequêncial da demanda.",
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#segundo",
      content:
        "Aqui fica os chats abertos, tendo o nome do usuário, o número sequêncial da demanda e de qual demanda pertence.",
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#terceiro",
      content:
        "Neste botão pode escolher entre minimizar o chat ou encerrar a conversa (fechando o chat).",
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
    {
      selector: "#quarto",
      content:
        "Aqui pode escrever o que deseja enviar, podendo também anexar algum arquivo.",
      style: {
        backgroundColor: "#DCDCDC",
        color: "#000000",
      },
    },
  ];

  return (
    <>
      <Ajuda onClick={() => setIsTourOpen(true)} />
      <Tour
        steps={stepsTour}
        isOpen={isTourOpen}
        onRequestClose={() => setIsTourOpen(false)}
        accentColor="#00579D"
        rounded={10}
        showCloseButton={false}
      />
      {abrirModal && (
        <ModalConfirmacao
          open={abrirModal}
          setOpen={setOpenModal}
          textoModal={"fecharChat"}
          onConfirmClick={deletarChat}
          onCancelClick={fecharModalCancelarChat}
          textoBotao={"sim"}
        />
      )}
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
                  id="primeiro"
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
                {isTourOpen ? (
                  <Contato
                    key={0}
                    onClick={() => {
                      abrirChat(0);
                    }}
                    usuario={{
                      foto: "",
                      nome: "Tour",
                      cargo: "Gerente",
                      demanda: "Mostrar no tour",
                      codigoDemanda: "#1",
                      mensagens: [
                        {
                          texto: "Olá, tudo bem?",
                          data: "10/10/2021",
                          hora: "10:00",
                          remetente: "Tour",
                        },
                        {
                          texto:
                            "Tudo sim, e você? Estou aqui para te mostrar como funciona o chat.",
                          data: "10/10/2021",
                          hora: "10:01",
                          remetente: "Eu",
                        },
                        {
                          texto: "Bem também, obrigado.",
                          data: "10/10/2021",
                          hora: "10:02",
                          remetente: "Tour",
                        },
                      ],
                    }}
                    index={0}
                    usuarioAtual={0}
                  />
                ) : (
                  resultadosContato.map((resultado, index) => {
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
                  })
                )}
              </Box>
              {indexUsuario == null || (visibilidade && usuarioId) ? (
                <Box
                  className="flex flex-col items-center justify-center rounded border"
                  sx={{ width: "75%", height: "95%", cursor: "default" }}
                >
                  <img src={logoWeg} alt="chat" />
                  {indexUsuario == null ? (
                    <Typography
                      fontSize={FontConfig.title}
                      color={"text.secondary"}
                      sx={{ fontWeight: "600" }}
                    >
                      Selecione alguma conversa
                    </Typography>
                  ) : (
                    <Typography
                      fontSize={FontConfig.title}
                      color={"text.secondary"}
                      sx={{ fontWeight: "600" }}
                    >
                      Mini chat aberto
                    </Typography>
                  )}
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
                          Tour
                        </Typography>
                        <Typography fontSize={FontConfig.small}>
                          {usuarios[indexUsuario].cargo}
                        </Typography>
                      </Box>
                    </Box>
                    <Box className="mr-5">
                      {/* Botão para abrir o menu */}
                      <Tooltip title="Opções">
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
                            }}
                          >
                            <OpenInNewOutlinedIcon />
                            <Typography
                              color={"text.primary"}
                              fontSize={FontConfig.medium}
                              sx={{ fontWeight: 500 }}
                            >
                              Abrir Pop-Up
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
                            >
                              Encerrar Chat
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
                      {/* Botão para abrir o menu */}
                      <Tooltip title="Opções">
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
                            }}
                          >
                            <OpenInNewOutlinedIcon />
                            <Typography
                              color={"text.primary"}
                              fontSize={FontConfig.medium}
                              sx={{ fontWeight: 500 }}
                            >
                              Abrir Pop-Up
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
                                color: "tertiary.main",
                                cursor: "pointer",
                              }}
                            />
                            <Typography
                              color={"text.primary"}
                              fontSize={FontConfig.medium}
                              sx={{ fontWeight: 500 }}
                            >
                              Encerrar Chat
                            </Typography>
                          </MenuItem>
                        </Box>
                      </Menu>
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

// Parte do websocket

{
  /* <>
{
  abrirModal && (
    <ModalConfirmacao open={abrirModal} setOpen={setOpenModal} textoModal={"fecharChat"} onConfirmClick={deletarChat} onCancelClick={fecharModalCancelarChat} textoBotao={"sim"} />
  )
}
<FundoComHeader>
  <Box>
    Enviar Mensagem:
    <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} />
    <button type="button" className="send-button" onClick={sendPrivateValue}>send</button>
  </Box>

  <Box>
    {[...privateChats.get(tab)].map((chat, index) => (
      <li key={index}>
        <div >{chat.usuario.nome}</div>
        <div >{chat.texto}</div>
      </li>
    ))}
  </Box>
</FundoComHeader>
</> */
}

{
  /* <Box>
  <ul>
    <li onClick={() => { setTab("CHATROOM") }}>Chatroom</li>
    {[...privateChats.keys()].map((id, index) => (
      <li onClick={() => { setTab(id) }} key={index}>{buscarNomeLista(id)}</li>
    ))}
  </ul>

  {tab !== "CHATROOM" && <Box>
    <ul>
      {[...privateChats.get(tab)].map((chat, index) => (
        <li key={index}>
          {chat.usuario.nome !== userData.username && <div className="avatar">Usuario: {chat.usuario.nome}</div>}
          <div className="message-data">Mensagem: {chat.texto}</div>
          {chat.usuario.nome === userData.username && <div className="avatar self">Usuario: {chat.usuario.nome}</div>}
        </li>
      ))}
    </ul>

    <div className="send-message">
      <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} />
      <button type="button" className="send-button" onClick={sendPrivateValue}>send</button>
    </div>
  </Box>}
</Box> */
}
