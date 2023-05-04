import React, { useState, useContext, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";

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
  Input,
} from "@mui/material";

import Cookies from "js-cookie";
import { over } from "stompjs";
import SockJS from "sockjs-client";
import Tour from "reactour";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Caminho from "../../components/Caminho/Caminho";
import Contato from "../../components/Contato/Contato";
import Mensagem from "../../components/Mensagem/Mensagem";
import ModalConfirmacao from "../../components/ModalConfirmacao/ModalConfirmacao";
import Ajuda from "../../components/Ajuda/Ajuda";
import Feedback from "../../components/Feedback/Feedback";

import logoWeg from "../../assets/logo-weg.png";
import CommentsDisabledOutlinedIcon from "@mui/icons-material/CommentsDisabledOutlined";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import MoreVertOutlinedIcon from "@mui/icons-material/MoreVertOutlined";
import AttachFileOutlinedIcon from "@mui/icons-material/AttachFileOutlined";
import SendOutlinedIcon from "@mui/icons-material/SendOutlined";
import OpenInNewOutlinedIcon from "@mui/icons-material/OpenInNewOutlined";

import ChatService from "../../service/chatService";
import { MensagemService } from "../../service/MensagemService";
import UsuarioService from "../../service/usuarioService";
import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import ChatContext from "../../service/ChatContext";
import { useParams } from "react-router-dom";
import { WebSocketContext } from "../../service/WebSocketService";
import CookieService from "../../service/cookieService";
import EntitiesObjectService from "../../service/entitiesObjectService";
import dateService from "../../service/dateService";

// Chat para conversa entre usuários do sistema
const Chat = () => {
  /** Navigate utilizado para navegar para outras páginas */
  const navigate = useNavigate();

  // Context para alterar o idioma
  const { texts, setTexts } = useContext(TextLanguageContext);

  const { setVisibilidade, visibilidade } = useContext(ChatContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Location utilizado para conectar o usuário no webSocket
  const location = useLocation();

  // UseState para pesquisar um contato da lista
  const [pesquisaContato, setPesquisaContato] = useState("");

  // UseState para armazenar os resultados da pesquisa
  const [resultadosContato, setresultadosContato] = useState([]);

  const [feedbackChatEncerrado, setFeedbackChatEncerrado] = useState(false);

  // UseState para armazenar o contato selecionado
  const onChange = (evt) => {
    setPesquisaContato(evt.target.value);
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
    ChatService.getByIdChat(idChat).then((e) => {
      ChatService.put(
        {
          ...e,
          conversaEncerrada: true,
        },
        idChat
      ).then((e) => {
        navigate("/chat");
        setFeedbackChatEncerrado(true);
      });
    });
  };

  const [listaChats, setListaChats] = useState([]);

  const buscarChats = () => {
    ChatService.getByRemetente(user.usuario.id).then((e) => {
      setListaChats(e);
    });
  };

  const [user, setUser] = useState(UsuarioService.getUserCookies());

  const idChat = useParams().id;
  const [mensagens, setMensagens] = useState([
    EntitiesObjectService.mensagem(),
  ]);
  const [mensagem, setMensagem] = useState(EntitiesObjectService.mensagem());
  const { enviar, inscrever, stompClient } = useContext(WebSocketContext);

  useEffect(() => {
    if (idChat) carregar();
    buscarChats();
  }, []);

  useEffect(() => {
    if (idChat) carregar();
  }, [idChat]);

  async function carregar() {
    await MensagemService.getMensagensChat(idChat)
      .then((response) => {
        setMensagens(response);
      })
      .catch((error) => {});
    setDefaultMensagem();
  }

  // UseState para armazenar o contato selecionado
  useEffect(() => {
    const resultados = [];
    listaChats.filter((chat) => {
      chat.usuariosChat.map((userChat) => {
        if (userChat.id != user.usuario.id) {
          if (
            userChat.nome.toLowerCase().includes(pesquisaContato.toLowerCase())
          ) {
            resultados.push(chat);
          }
        }
      });
    });
    setresultadosContato(resultados);
  }, [pesquisaContato, listaChats, idChat]);

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

  const [contatoSelecionado, setContatoSelecionado] = useState({});

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

  const atualizaMensagem = (event) => {
    const { value } = event.target;
    setMensagem({ ...mensagem, texto: value });
  };

  const submit = async (event) => {
    event.preventDefault();
    enviar(`/app/weg_ssm/mensagem/${idChat}`, mensagem);
    setDefaultMensagem();
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
        {/* Feedback Chat encerrado com sucesso */}
        <Feedback
          open={feedbackChatEncerrado}
          handleClose={() => {
            setFeedbackChatEncerrado(false);
          }}
          status={"sucesso"}
          mensagem={texts.chat.chatEncerrado}
        />
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
                    placeholder={texts.chat.pesquisarPorNome}
                  />
                  <Box className="flex gap-2">
                    <SearchOutlinedIcon sx={{ color: "text.secondary" }} />
                  </Box>
                </Box>
                {isTourOpen ? (
                  <Contato
                    key={0}
                    contatoSelecionado={contatoSelecionado}
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
                ) : (
                  resultadosContato.map((resultado, index) => {
                    return (
                      <Contato
                        key={index}
                        onClick={() => {
                          navigate(`/chat/${resultado.id}`);
                          setContatoSelecionado(resultado.id);
                        }}
                        contatoSelecionado={contatoSelecionado}
                        chat={resultado}
                        index={index}
                      />
                    );
                  })
                )}
              </Box>
              {!idChat ? (
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
                        >
                          {texts.chat.usuarioTour.tour}
                        </Typography>
                        <Typography fontSize={FontConfig.small}>
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
                      onChange={(e) => {
                        // save(e);
                      }}
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
                        <IconButton
                          onClick={() => {
                            // salvarTexto();
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
                        >
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
                        <Typography fontSize={FontConfig.small}>
                          {listaChats.map((chat) => {
                            let cargoChat;
                            if (chat.id == idChat) {
                              if (
                                chat.idProposta.solicitante.id ==
                                user.usuario.id
                              ) {
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
                            }}
                          >
                            <OpenInNewOutlinedIcon />
                            <Typography
                              color={"text.primary"}
                              fontSize={FontConfig.medium}
                              sx={{ fontWeight: 500 }}
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
                                color: "tertiary.main",
                                cursor: "pointer",
                              }}
                            />
                            <Typography
                              color={"text.primary"}
                              fontSize={FontConfig.medium}
                              sx={{ fontWeight: 500 }}
                            >
                              {texts.chat.encerrarChat}
                            </Typography>
                          </MenuItem>
                        </Box>
                      </Menu>
                    </Box>
                  </Box>
                  <Box
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
                  <Box
                    className="flex border px-3 py-1 m-4 rounded items-center"
                    sx={{
                      backgroundColor: "input.main",
                      width: "90%",
                      height: "6.5%",
                    }}
                  >
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
                        paddingTop: "0.4rem",
                        resize: "none",
                      }}
                      placeholder={texts.chat.escrevaSuaMensagem}
                      value={mensagem.texto}
                    />
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
                        <IconButton onClick={submit}>
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
