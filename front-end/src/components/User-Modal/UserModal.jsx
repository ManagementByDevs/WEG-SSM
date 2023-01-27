import React, { useState, useEffect, useContext } from "react";
import { useNavigate, Link } from "react-router-dom";

import {
  Menu,
  MenuItem,
  Tooltip,
  IconButton,
  Avatar,
  Typography,
  Box,
  FormControlLabel,
  Switch,
  Slider,
} from "@mui/material/";
import { styled } from "@mui/material/styles";

import NotificationsOutlinedIcon from "@mui/icons-material/NotificationsOutlined";
import BorderColorOutlinedIcon from "@mui/icons-material/BorderColorOutlined";
import ChatBubbleOutlineOutlinedIcon from "@mui/icons-material/ChatBubbleOutlineOutlined";
import MarkChatUnreadOutlinedIcon from "@mui/icons-material/MarkChatUnreadOutlined";

import FontContext from "../../service/FontContext";
import ColorModeContext from "../../service/TemaContext";

import UsuarioService from "../../service/usuarioService";

const MaterialUISwitch = styled(Switch)(({ theme }) => ({
  width: 58,
  height: 32,
  padding: 7,
  "& .MuiSwitch-switchBase": {
    margin: 1,
    padding: 0,
    transform: "translateX(6px)",
    "&.Mui-checked": {
      color: "#fff",
      transform: "translateX(22px)",
      "& .MuiSwitch-thumb:before": {
        backgroundImage: `url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" height="20" width="20" viewBox="0 0 20 20"><path fill="${encodeURIComponent(
          "#fff"
        )}" d="M4.2 2.5l-.7 1.8-1.8.7 1.8.7.7 1.8.6-1.8L6.7 5l-1.9-.7-.6-1.8zm15 8.3a6.7 6.7 0 11-6.6-6.6 5.8 5.8 0 006.6 6.6z"/></svg>')`,
      },
      "& + .MuiSwitch-track": {
        opacity: 1,
        backgroundColor: theme.palette.mode === "dark" ? "#e5e7eb" : "#e5e7eb",
      },
    },
  },
  "& .MuiSwitch-thumb": {
    backgroundColor: theme.palette.mode === "dark" ? "#00579d" : "#00579d",
    width: 30,
    height: 30,
    "&:before": {
      content: "''",
      position: "absolute",
      width: "100%",
      height: "100%",
      left: 0,
      top: 0,
      backgroundRepeat: "no-repeat",
      backgroundPosition: "center",
      backgroundImage: `url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" height="20" width="20" viewBox="0 0 20 20"><path fill="${encodeURIComponent(
        "#fff"
      )}" d="M9.305 1.667V3.75h1.389V1.667h-1.39zm-4.707 1.95l-.982.982L5.09 6.072l.982-.982-1.473-1.473zm10.802 0L13.927 5.09l.982.982 1.473-1.473-.982-.982zM10 5.139a4.872 4.872 0 00-4.862 4.86A4.872 4.872 0 0010 14.862 4.872 4.872 0 0014.86 10 4.872 4.872 0 0010 5.139zm0 1.389A3.462 3.462 0 0113.471 10a3.462 3.462 0 01-3.473 3.472A3.462 3.462 0 016.527 10 3.462 3.462 0 0110 6.528zM1.665 9.305v1.39h2.083v-1.39H1.666zm14.583 0v1.39h2.084v-1.39h-2.084zM5.09 13.928L3.616 15.4l.982.982 1.473-1.473-.982-.982zm9.82 0l-.982.982 1.473 1.473.982-.982-1.473-1.473zM9.305 16.25v2.083h1.389V16.25h-1.39z"/></svg>')`,
    },
  },
  "& .MuiSwitch-track": {
    opacity: 1,
    backgroundColor: theme.palette.mode === "dark" ? "#e5e7eb" : "#e5e7eb",
    borderRadius: 20 / 2,
  },
}));

const UserModal = (props) => {
  // Desestruturação de objeto em duas variáveis:
  // - Mode: modo do tema atual ("light" ou "dark")
  // - toggleColorMode: função para alternar o tema
  const { mode, toggleColorMode } = useContext(ColorModeContext);
  // Variável de estado para controlar o tema
  const [temaDark, setTemaDark] = useState(mode === "dark" ? true : false);

  useEffect(() => {
    UsuarioService.getUsuarioById(
      parseInt(localStorage.getItem("usuarioId"))
    ).then((e) => {
      setUsuario(e);
    });
  }, []);

  const navigate = useNavigate();

  // UseState para poder visualizar e alterar o chat icon
  const [chatIcon, setChatIcon] = useState(ChatBubbleOutlineOutlinedIcon);

  // UseState com as informações do usuário, recebidas no useEffect ao criar o componente
  const [usuario, setUsuario] = useState({
    id: 0,
    email: "",
    nome: "",
    senha: "",
    tipo_usuario: 0,
    visibilidade: 1,
    departamento: null,
  });

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

  // Função para sair da conta do usuário
  const sair = () => {
    localStorage.removeItem("usuarioId");
    localStorage.removeItem("user");
  };

  useEffect(() => {
    toggleColorMode();
  }, [temaDark]);

  // Personalizar o slider da fonte
  const SliderMark = styled(Slider)(({ theme }) => ({
    color: theme.palette.mode === "dark" ? "#3880ff" : "#3880ff",
    height: 4,
    padding: "15px 0",
    "& .MuiSlider-thumb": {
      height: 18,
      width: 18,
      backgroundColor: "#fff",
      "&:focus, &:hover, &.Mui-active": {
        boxShadow:
          "0 3px 1px rgba(0,0,0,0.1),0 4px 8px rgba(0,0,0,0.3),0 0 0 1px rgba(0,0,0,0.02)",
      },
    },
    "& .MuiSlider-valueLabel": {
      fontSize: 13,
      fontWeight: "normal",
      top: 0,
      backgroundColor: "unset",
      color: theme.palette.text.primary,
      "&:before": {
        display: "none",
      },
      "& *": {
        background: "transparent",
        color: theme.palette.mode === "dark" ? "#fff" : "#000",
      },
    },
    "& .MuiSlider-track": {
      border: "none",
    },
    "& .MuiSlider-rail": {
      opacity: 0.5,
      backgroundColor: "#bfbfbf",
    },
    "& .MuiSlider-mark": {
      backgroundColor: "#bfbfbf",
      height: 9,
      width: 2,
      "&.MuiSlider-markActive": {
        opacity: 1,
        backgroundColor: "currentColor",
      },
    },
  }));

  // Mudar o value para texto
  function valuetext(value) {
    if (value === 0) {
      return "Normal";
    } else if (value === -1) {
      return "Pequeno";
    } else if (value === -2) {
      return "Muito Pequeno";
    } else if (value === 1) {
      return "Grande";
    } else if (value === 2) {
      return "Muito Grande";
    }
  }

  // Funções para aumentar o value do slider
  const aumentarValue = (event) => {
    if (valueSlider === 2) {
      setValueSlider(2);
    } else {
      setValueSlider(valueSlider + 1);
    }
  };

  // Funções para diminuir o value do slider
  const diminuirValue = (event) => {
    if (valueSlider === -2) {
      setValueSlider(-2);
    } else {
      setValueSlider(valueSlider - 1);
    }
  };

  // UseState para poder visualizar e alterar o value do slider
  const [valueSlider, setValueSlider] = useState(0);

  // Função para mudar o value do slider
  const handleChange = (event, newValue) => {
    if (typeof newValue === "number") {
      setValueSlider(newValue);
    }
  };

  //useContext para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // UseEffect para alterar o tamanho da fonte
  useEffect(() => {
    switch (valueSlider) {
      case 0:
        setFontConfig({
          verySmall: "10px",
          small: "12px",
          default: "14px",
          medium: "16px",
          big: "18px",
          veryBig: "20px",
          smallTitle: "30px",
          title: "36px",
        });
        break;
      case -1:
        setFontConfig({
          verySmall: "8px",
          small: "10px",
          default: "12px",
          medium: "14px",
          big: "16px",
          veryBig: "18px",
          smallTitle: "28px",
          title: "34px",
        });
        break;
      case -2:
        setFontConfig({
          verySmall: "6px",
          small: "8px",
          default: "10px",
          medium: "12px",
          big: "14px",
          veryBig: "16px",
          smallTitle: "26px",
          title: "32px",
        });
        break;
      case 1:
        setFontConfig({
          verySmall: "12px",
          small: "14px",
          default: "16px",
          medium: "18px",
          big: "20px",
          veryBig: "22px",
          smallTitle: "32px",
          title: "38px",
        });
        break;
      case 2:
        setFontConfig({
          verySmall: "14px",
          small: "16px",
          default: "18px",
          medium: "20px",
          big: "22px",
          veryBig: "24px",
          smallTitle: "34px", 
          title: "40px",
        });
        break;
      default:
        setFontConfig({
          verySmall: "10px",
          small: "12px",
          default: "14px",
          medium: "16px",
          big: "18px",
          veryBig: "20px",
          smallTitle: "30px",
          title: "36px",
        });
    }
  }, [valueSlider]);

  return (
    <>
      {/* Botão para abrir o menu */}
      <Tooltip title="Configurações">
        <IconButton
          onClick={handleClick}
          size="small"
          aria-controls={open ? "account-menu" : undefined}
          aria-haspopup="true"
          aria-expanded={open ? "true" : undefined}
        >
          <Avatar
            sx={{
              width: 40,
              height: 40,
              backgroundColor: "primary.main",
              border: "1px solid rgba(255, 255, 255, 0.5)",
              color: "rgba(255, 255, 255, 0.9)",
            }}
          ></Avatar>
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
          <Typography
            className="px-4 pt-1.5"
            color={"text.primary"}
            fontSize={FontConfig.medium}
            sx={{ fontWeight: 600 }}
          >
            {usuario.nome}
          </Typography>
          {usuario.departamento != null ? (
            <Typography
              className="px-4"
              color={"text.secondary"}
              fontSize={FontConfig.medium}
            >
              {usuario.departamento.nome}
            </Typography>
          ) : null}
          <MenuItem
            className="gap-2"
            onClick={() => {
              handleClose();
              navigate("/notificacao");
            }}
          >
            <NotificationsOutlinedIcon />
            Notificações
          </MenuItem>

          {/* Divisão de um item clicável e outro no modal */}
          <div className="w-full flex justify-center pt-1.5">
            <hr className="w-10/12 my-1.5" />
          </div>

          <MenuItem
            className="gap-2"
            onClick={() => {
              handleClose();
              navigate("/escopos");
            }}
          >
            <BorderColorOutlinedIcon />
            <Typography
              color={"text.primary"}
              fontSize={FontConfig.medium}
              sx={{ fontWeight: 500 }}
            >
              Escopos
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
              navigate("/chat");
            }}
          >
            {chatIcon == ChatBubbleOutlineOutlinedIcon ? (
              <ChatBubbleOutlineOutlinedIcon />
            ) : (
              <MarkChatUnreadOutlinedIcon />
            )}
            Chats
          </MenuItem>

          {/* Slider para mudar o tamanho da fonte */}
          <Box className="flex justify-center w-full mt-4">
            <Box
              className="flex items-center justify-around"
              sx={{ width: "85%" }}
            >
              {/* Letra A pequena, para diminuir a fonto */}
              <Tooltip title="Diminui fonte">
                <IconButton onClick={diminuirValue} size="small">
                  <Typography
                    fontSize={FontConfig.default}
                    sx={{ cursor: "pointer" }}
                  >
                    A
                  </Typography>
                </IconButton>
              </Tooltip>
              {/* Slider */}
              <Box className="w-24 h-8">
                <SliderMark
                  aria-label="Small steps"
                  defaultValue={0}
                  step={1}
                  value={valueSlider}
                  onChange={handleChange}
                  marks
                  min={-2}
                  max={2}
                  valueLabelFormat={valuetext}
                  valueLabelDisplay="auto"
                />
              </Box>
              {/* Letra A grande, para aumentar a fonte */}
              <Tooltip title="Diminui fonte">
                <IconButton onClick={aumentarValue} size="small">
                  <Typography
                    fontSize={FontConfig.veryBig}
                    sx={{ cursor: "pointer" }}
                  >
                    A
                  </Typography>
                </IconButton>
              </Tooltip>
            </Box>
          </Box>

          <Box className="w-full flex gap-2 px-4 items-center justify-center ml-4">
            {/* <Typography color={'text.primary'} fontSize={FontConfig.medium} sx={{ fontWeight: 500 }}>Tema</Typography> */}
            <Tooltip title="Modo Escuro/Claro">
              <FormControlLabel
                control={
                  <MaterialUISwitch
                    checked={temaDark}
                    onChange={() => {
                      setTemaDark(!temaDark);
                    }}
                    sx={{ m: 1 }}
                  />
                }
              />
            </Tooltip>
          </Box>

          {/* Link para deslogar do sistema */}
          <Typography
            className="px-4 pt-1.5"
            color={"icon.main"}
            variant="body2"
            fontSize={FontConfig.medium}
            align="right"
            sx={{ fontWeight: 600, mt: "-16px" }}
          >
            <Link to={"/login"} onClick={sair}>
              Sair
            </Link>
          </Typography>
        </Box>
      </Menu>
    </>
  );
};

export default UserModal;
