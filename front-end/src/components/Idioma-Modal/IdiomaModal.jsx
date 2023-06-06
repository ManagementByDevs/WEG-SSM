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
import TextLanguage from "../../service/TextLanguage";

import UsuarioService from "../../service/usuarioService";
import CookieService from "../../service/cookieService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

// Modal para selecionar o idioma do sistema
const IdiomaModal = () => {
  useEffect(() => {
    arrangePreferences();
  }, []);

  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context que contém os textos do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para ler o texto da tela
  const { lerTexto } = useContext(SpeechSynthesisContext);

  // UseState para poder visualizar e alterar a imagem da linguagem selecionada (Valor padrão é Brasil)
  const [idioma, setIdioma] = useState();

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
    if (!CookieService.getCookie("jwt")) return;
    UsuarioService.getPreferencias(CookieService.getCookie("jwt")?.sub).then(
      (preferencias) => {
        if (preferencias.lang == "pt" && idioma != Brasil) setIdioma(Brasil);
        else if (preferencias.lang == "ch" && idioma != China) setIdioma(China);
        else if (preferencias.lang == "en" && idioma != EstadosUnidos)
          setIdioma(EstadosUnidos);
        else if (preferencias.lang == "es" && idioma != Espanha)
          setIdioma(Espanha);
      }
    );
  };

  /**
   * Salva as novas preferências do usuário no banco de dados
   */
  const saveNewPreference = () => {
    if (!CookieService.getCookie("jwt")) return;

    UsuarioService.getUsuarioByEmail(CookieService.getCookie("jwt")?.sub).then(
      (user) => {
        toggleLanguage();
        let preferencias = JSON.parse(user.preferencias);

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
        }

        user.preferencias = JSON.stringify(preferencias);

        UsuarioService.updateUser(user.id, user).then((e) => {});
      }
    );
  };

  // Toda vez que o idioma for mudado, será salvado a nova preferência do usuário
  useEffect(() => {
    saveNewPreference();
  }, [idioma]);

  useEffect(() => {
    if (!CookieService.getCookie("jwt")) return;
    UsuarioService.getPreferencias(CookieService.getCookie("jwt")?.sub).then(
      (preferencias) => {
        if (preferencias.lang == "pt") setIdioma(Brasil);
        else if (preferencias.lang == "ch") setIdioma(China);
        else if (preferencias.lang == "en") setIdioma(EstadosUnidos);
        else if (preferencias.lang == "es") setIdioma(Espanha);
      }
    );
  }, []);
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
          <Typography
            fontSize={FontConfig?.default}
            onClick={() => {
              lerTexto("English");
            }}
          >
            English
          </Typography>
        </MenuItem>

        {/* Divisor entre um item de idioma e outro */}
        <div className="w-full flex justify-center">
          <hr className="w-10/12" />
        </div>

        {/* Item de idioma */}
        <MenuItem className="gap-2" onClick={() => handleClose(Brasil)}>
          <img className="h-5 w-7" src={Brasil} />
          <Typography
            fontSize={FontConfig?.default}
            onClick={() => {
              lerTexto("Português (Brasil)");
            }}
          >
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
          <Typography
            fontSize={FontConfig?.default}
            onClick={() => {
              lerTexto("中国人");
            }}
          >
            中国人
          </Typography>
        </MenuItem>

        {/* Divisor entre um item de idioma e outro */}
        <div className="w-full flex justify-center">
          <hr className="w-10/12" />
        </div>

        {/* Item de idioma */}
        <MenuItem className="gap-2" onClick={() => handleClose(Espanha)}>
          <img className="h-6 w-7" src={Espanha} />
          <Typography
            fontSize={FontConfig?.default}
            onClick={() => {
              lerTexto("Español");
            }}
          >
            Español
          </Typography>
        </MenuItem>
      </Menu>
    </div>
  );
};

export default IdiomaModal;
