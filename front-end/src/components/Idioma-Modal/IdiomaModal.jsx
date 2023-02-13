import React, { useState, useContext } from "react";
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

import FontContext from "../../service/FontContext";
import UsuarioService from "../../service/usuarioService";
import { useEffect } from "react";

const IdiomaModal = () => {
  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

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

  // ********************************************** Preferências **********************************************
  /**
   * Pega as preferências do usuário e as aplica no sistema
   */
  // const arrangePreferences = () => {
  //   let theme = UsuarioService.getPreferencias().themeMode;
  //   let fontSizeDefault = UsuarioService.getPreferencias().fontSizeDefault;

  //   if (theme != mode) {
  //     setTemaDark(!temaDark);
  //   }

  //   if (fontSizeDefault != FontConfig.default) {
  //     setFontConfig(getUserFontSizePreference());
  //   }
  // };

  /**
   * Salva as novas preferências do usuário no banco de dados
   * @param {String} newPreference
   */
  const saveNewPreference = (newPreference) => {
    let user = UsuarioService.getUser();
    let preferencias = UsuarioService.getPreferencias();

    switch (newPreference) {
      case Brasil:
        newPreference = "pt";
        break;
      case China:
        newPreference = "ch";
        break;
      case EstadosUnidos:
        newPreference = "en";
        break;
    }
    
    preferencias.lang = newPreference;

    user.preferencias = JSON.stringify(preferencias);

    UsuarioService.updateUser(user.id, user).then((e) => {
      UsuarioService.updateUserInLocalStorage();
    });
  };

  useEffect(() => {
    saveNewPreference();
  }, [idioma]);
  // ********************************************** Fim Preferências **********************************************

  return (
    // Div container do idioma
    <div className=" border border-solid rounded-sm border-[#ffffff75]">
      <Tooltip title="Idiomas">
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
      </Menu>
    </div>
  );
};

export default IdiomaModal;
