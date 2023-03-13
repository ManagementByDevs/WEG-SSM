import React, { useState, useContext, useEffect } from "react";
import {
  Menu,
  MenuItem,
  Tooltip,
  IconButton,
  Typography,
} from "@mui/material/";

import Brasil from "../../assets/brasil.jpg";
import China from "../../assets/china.png";
import EstadosUnidos from "../../assets/estados-unidos.png";
import Espanha from "../../assets/espanha.png";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import UsuarioService from "../../service/usuarioService";
import TextLanguage from "../../service/TextLanguage";

const IdiomaModal = () => {
  useEffect(() => {
    arrangePreferences();
  }, []);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  // Context que contém os textos do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // UseState para poder visualizar e alterar a imagem da linguagem selecionada (Valor padrão é Brasil)
  const [idioma, setIdioma] = useState(Brasil);

  // UseState para poder visualizar e alterar o menu do idioma
  const [anchorEl, setAnchorEl] = useState(null);

  // Variável que é usada para saber se o menu está aberto ou não
  const open = Boolean(anchorEl);

  // Função para abrir o menu
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  // Função para fechar o menu
  const handleClose = (src) => {
    setIdioma(src);
    setAnchorEl(null);
  };

  /**
   * Altera o idioma do sistema de acordo com o idioma selecionado
   */
  const toggleLanguage = () => {
    if (idioma == Brasil) setTexts(TextLanguage("pt"));
    else if (idioma == EstadosUnidos) setTexts(TextLanguage("en"));
    else if (idioma == China) setTexts(TextLanguage("ch"));
    else if (idioma == Espanha) setTexts(TextLanguage("es"));
  };

  // ********************************************** Preferências **********************************************
  /**
   * Pega as preferências do usuário e as aplica no sistema
   */
  const arrangePreferences = () => {
    let lang = UsuarioService.getPreferencias().lang;

    if (lang == "pt" && idioma != Brasil) setIdioma(Brasil);
    else if (lang == "ch" && idioma != China) setIdioma(China);
    else if (lang == "en" && idioma != EstadosUnidos) setIdioma(EstadosUnidos);
    else if (lang == "es" && idioma != Espanha) setIdioma(Espanha);
  };

  /**
   * Salva as novas preferências do usuário no banco de dados
   */
  const saveNewPreference = () => {
    let user = UsuarioService.getUser();

    toggleLanguage();

    if (!user) return;

    let preferencias = UsuarioService.getPreferencias();

    switch (idioma) {
      case Brasil:
        preferencias.lang = "pt";
        break;
      case China:
        preferencias.lang = "ch";
        break;
      case EstadosUnidos:
        preferencias.lang = "en";
        break;
      case Espanha:
        preferencias.lang = "es";
        break;
      default:
        preferencias.lang = "en";
    }

    user.preferencias = JSON.stringify(preferencias);

    UsuarioService.updateUser(user.id, user).then((e) => {
      UsuarioService.updateUserInLocalStorage();
    });
  };

  // Toda vez que o idioma for mudado, será salvado a nova preferência do usuário
  useEffect(() => {
    saveNewPreference();
  }, [idioma]);
  // ********************************************** Fim Preferências **********************************************

  return (
    // Div container do idioma
    <div className=" border border-solid rounded-sm border-[#ffffff75]">
      <Tooltip title={texts.idiomaModal.idiomas}>
        <IconButton
          onClick={handleClick}
          size="small"
          aria-haspopup="true"
          aria-expanded={open ? "true" : undefined}
        >
          {/* Imagem do idioma selecionado no momento */}
          <img className="h-6 w-8" src={idioma} />
        </IconButton>
      </Tooltip>
      {/* Menu (modal) com as opções dos idiomas */}
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={() => handleClose(idioma)}
        MenuListProps={{
          "aria-labelledby": "basic-button",
        }}
      >
        {/* Item de idioma */}
        <MenuItem className="gap-2" onClick={() => handleClose(EstadosUnidos)}>
          <img className="h-5 w-7" src={EstadosUnidos} />
          <Typography fontSize={FontConfig.default}>English</Typography>
        </MenuItem>

        {/* Divisor entre um item de idioma e outro */}
        <div className="w-full flex justify-center">
          <hr className="w-10/12" />
        </div>

        {/* Item de idioma */}
        <MenuItem className="gap-2" onClick={() => handleClose(Brasil)}>
          <img className="h-5 w-7" src={Brasil} />
          <Typography fontSize={FontConfig.default}>
            Português (Brasil)
          </Typography>
        </MenuItem>

        {/* Divisor entre um item de idioma e outro */}
        <div className="w-full flex justify-center">
          <hr className="w-10/12" />
        </div>

        {/* Item de idioma */}
        <MenuItem className="gap-2" onClick={() => handleClose(China)}>
          <img className="h-5 w-7" src={China} />
          <Typography fontSize={FontConfig.default}>中国人</Typography>
        </MenuItem>

        {/* Divisor entre um item de idioma e outro */}
        <div className="w-full flex justify-center">
          <hr className="w-10/12" />
        </div>

        {/* Item de idioma */}
        <MenuItem className="gap-2" onClick={() => handleClose(Espanha)}>
          <img className="h-8 w-7" src={Espanha} />
          <Typography fontSize={FontConfig.default}>
            Espanhol
          </Typography>
        </MenuItem>
      </Menu>
    </div>
  );
};

export default IdiomaModal;
