import { React, useState, useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import {
  Button,
  Box,
  Paper,
  TextField,
  InputAdornment,
  FormControlLabel,
  Checkbox,
  Typography,
  IconButton,
  Input,
  Divider,
  Tooltip,
} from "@mui/material";

import FundoComHeader from "../../components/FundoComHeader/FundoComHeader";
import Feedback from "../../components/Feedback/Feedback";

import LogoWeg from "../../assets/logo-weg.png";
import LogoAzul from "../../assets/LogoAzul.png";
import Grid from "../../assets/GridSemFundo.png";

import VisibilityOffIcon from "@mui/icons-material/VisibilityOff";
import VisibilityIcon from "@mui/icons-material/Visibility";
import AlternateEmailIcon from "@mui/icons-material/AlternateEmail";

import FontContext from "../../service/FontContext";
import TextLanguageContext from "../../service/TextLanguageContext";
import AutenticacaoService from "../../service/autenticacaoService";
import SpeechSynthesisContext from "../../service/SpeechSynthesisContext";

/** Página de login, para que o usuário tenha acesso ao restante do sistema */
const Login = (props) => {
  /** useContext que contém os textos do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Context para alterar o tamanho da fonte */
  const { FontConfig } = useContext(FontContext);

  /** Context para ler o texto da tela */
  const { lendoTexto, lerTexto, librasAtivo } = useContext(
    SpeechSynthesisContext
  );

  /** Variável para usar função de navegação do react-router-dom */
  let navigate = useNavigate();

  /** Variável usada para a visibilidade da senha (true - Senha Invisível / false - Senha Visível) */
  const [senha, setSenha] = useState(true);

  /** Variável usada como valor para os inputs de email e senha, para posterior login */
  const [dados, setDados] = useState({ email: "", senha: "" });

  /** Variáveis usadas para determinar se os feedback de dados inválidos ou faltantes estão visívies, respectivamente */
  const [dadosInvalidos, setDadosInvalidos] = useState(false);

  /** Variável utilizada para feedback de dados faltantes */
  const [dadosFaltantes, setDadosFaltantes] = useState(false);

  /** Feedback para ver se o navegador é compatível com a API de síntese de fala */
  const [
    feedbackErroNavegadorIncompativel,
    setFeedbackErroNavegadorIncompativel,
  ] = useState(false);

  /** Função para mudar a visualização da senha (visível ou em formato padrão) */
  const mudarVisualizacaoSenha = () => {
    setSenha(!senha);
  };

  /** Função usada para atualizar o valor da variável dos inputs após alguma mudança, recebendo um número referenciando a variável
    (1 - Email | 2 - Senha) e o valor do input */
  const atualizarInput = (numero, e) => {
    if (numero == 1) {
      setDados({ ...dados, email: e.target.value });
    } else {
      setDados({ ...dados, senha: e.target.value });
    }
  };

  /** Função para fazer login através do botão "Entrar", procurando o usuário no back-end e indo para a página principal caso encontre
   * Caso não encontre ou os inputs não estejam preenchidos, os feedbacks respectivos serão ativados
   */
  const fazerLogin = async () => {
    if (!lendoTexto && !librasAtivo) {
      if (dados.email && dados.senha) {
        try {
          await AutenticacaoService.login(dados);
          navigate("/");
        } catch (error) {
          // Abrir o modal de feedback de dados inválidos
          setDadosInvalidos(true);
        }
      } else {
        // Abrir modal de feedback de dados não preenchidos
        setDadosFaltantes(true);
      }
    }
  };

  /** Função para "ouvir" um evento de teclado no input de pesquisa e fazer o login caso seja a tecla "Enter" */
  const eventoTeclado = (e) => {
    if (e.key == "Enter") {
      fazerLogin();
    }
  };

  /**
   * <Paper
        sx={{ height: "100%"}}
        className="flex justify-center items-center"
      >
         Div Principal com as opções do login 
        <Paper
          sx={{
            backgroundColor: "background.default",
            width: "28%",
            height: "60%",
            minHeight: "32rem",
            minWidth: "26rem",
          }}
        >
          <Box className="w-full h-full border-t-12 border-[#00579D] rounded shadow-2xl flex flex-col items-center justify-center space-y-10">
            <Box>
              Logo WEG 
              <img className="w-36" src={LogoWeg}></img>
            </Box>
             Input de texto do email do usuário 
            <TextField
              value={dados.email}
              onChange={(e) => {
                atualizarInput(1, e);
              }}
              onKeyDown={(e) => {
                eventoTeclado(e);
              }}
              className="w-8/12"
              id="filled-basic"
              label={texts.login.email}
              variant="filled"
              color="primary"
            />
             Input de senha (usa os ícones e o tipo do input conforme a variável "senha", também chamando a função
                        "mudarVisualizacaoSenha" no click) 
            <TextField
              value={dados.senha}
              onChange={(e) => {
                atualizarInput(2, e);
              }}
              onKeyDown={(e) => {
                eventoTeclado(e);
              }}
              className="w-8/12"
              id="input-with-icon-textfield"
              label={texts.login.senha}
              color="primary"
              type={senha ? "password" : "text"}
              InputProps={{
                endAdornment: (
                  <InputAdornment position="end">
                    <IconButton
                      aria-label="Mudar visibilidade senha"
                      onClick={mudarVisualizacaoSenha}
                      edge="end"
                    >
                      {senha ? <VisibilityIcon /> : <VisibilityOffIcon />}
                    </IconButton>
                  </InputAdornment>
                ),
              }}
              variant="filled"
            />
             Div para checkbox "lembrar-me" e "Esqueci a senha" 
            <div className="w-8/12 flex justify-between items-center">
               Checkbox com label para função de "lembrar-me" 
              <FormControlLabel
                control={<Checkbox />}
                label={texts.login.lembrarme}
                onClick={() => {
                  lerTexto(texts.login.lembrarme);
                }}
              />
               Texto "Esqueci a Senha" (Usa a fonte média) 
              <Typography
                fontSize={FontConfig.medium}
                variant="h2"
                color="text.primary"
                className="underline hover:cursor-pointer"
                onClick={() => {
                  lerTexto(texts.login.esqueciSenha);
                }}
              >
                {texts.login.esqueciSenha}
              </Typography>
            </div>
             Div para centralizar o botão de login 
            <div className="w-8/12 flex justify-center">
               Botão para entrar no sistema 
              <Button
                onClick={fazerLogin}
                variant="contained"
                size="large"
                color="primary"
                className="self-end w-2/6"
              >
                <Typography
                  onClick={() => {
                    lerTexto(texts.login.entrar);
                  }}
                >
                  {texts.login.entrar}
                </Typography>
              </Button>
            </div>

             Feedback de dados inválidos 
            {dadosInvalidos && (
              <Feedback
                open={dadosInvalidos}
                handleClose={() => {
                  setDadosInvalidos(false);
                }}
                status={"erro"}
                mensagem={texts.login.feedback.dadosInvalidos}
                lendo={lendoTexto}
                texto={props.texto}
                setTexto={props.setTexto}
              />
            )}

             Feedback de dados faltantes / não preenchidos 
            {dadosFaltantes && (
              <Feedback
                open={dadosFaltantes}
                handleClose={() => {
                  setDadosFaltantes(false);
                }}
                status={"erro"}
                mensagem={texts.login.feedback.preenchaTodosOsCampos}
                lendo={lendoTexto}
                texto={props.texto}
                setTexto={props.setTexto}
              />
            )}
             Feedback navegador incompativel 
            <Feedback
              open={feedbackErroNavegadorIncompativel}
              handleClose={() => {
                setFeedbackErroNavegadorIncompativel(false);
              }}
              status={"erro"}
              mensagem={texts.homeGerencia.feedback.feedback13}
              lendo={lendoTexto}
              texto={props.texto}
              setTexto={props.setTexto}
            />
          </Box>
        </Paper>
      </Paper>
   */

  return (
    <FundoComHeader>
      {/* Feedback de dados inválidos  */}
      {dadosInvalidos && (
        <Feedback
          open={dadosInvalidos}
          handleClose={() => {
            setDadosInvalidos(false);
          }}
          status={"erro"}
          mensagem={texts.login.feedback.dadosInvalidos}
          lendo={lendoTexto}
          texto={props.texto}
          setTexto={props.setTexto}
        />
      )}
      {/* Feedback de dados faltantes / não preenchidos  */}
      {dadosFaltantes && (
        <Feedback
          open={dadosFaltantes}
          handleClose={() => {
            setDadosFaltantes(false);
          }}
          status={"erro"}
          mensagem={texts.login.feedback.preenchaTodosOsCampos}
          lendo={lendoTexto}
          texto={props.texto}
          setTexto={props.setTexto}
        />
      )}
      {/* Feedback navegador incompativel  */}
      <Feedback
        open={feedbackErroNavegadorIncompativel}
        handleClose={() => {
          setFeedbackErroNavegadorIncompativel(false);
        }}
        status={"erro"}
        mensagem={texts.homeGerencia.feedback.feedback13}
        lendo={lendoTexto}
        texto={props.texto}
        setTexto={props.setTexto}
      />
      <Box
        className="w-full flex justify-center items-center"
        sx={{ height: "95%" }}
      >
        {/* Modal de login */}
        <Box>
          {/* Título Login */}
          <Box className="w-full flex justify-between items-center mb-1">
            <Typography
              fontSize={FontConfig.title}
              fontWeight={600}
              color={"primary"}
            >
              {texts.login.login}
            </Typography>

            <img src={Grid} className="h-8" alt="Grid" />
          </Box>

          <Divider />

          {/* Formulario */}
          <Box className="flex gap-4 mt-2">
            {/* Inputs */}
            <Box className="flex flex-col gap-4 mt-2 w-80">
              <Input
                className="border-t border-r text-center p-2"
                sx={{
                  borderLeft: "4px solid #00579d",
                }}
                value={dados.email}
                onChange={(e) => {
                  atualizarInput(1, e);
                }}
                onKeyDown={(e) => {
                  eventoTeclado(e);
                }}
                placeholder={texts.login.email}
                fullWidth
                endAdornment={
                  <InputAdornment position="end">
                    <Tooltip title={texts.login.email}>
                      <AlternateEmailIcon />
                    </Tooltip>
                  </InputAdornment>
                }
              />
              <Box className="flex flex-col gap-2">
                <Input
                  className="border-t border-r text-center p-2"
                  sx={{
                    borderLeft: "4px solid #00579d",
                  }}
                  value={dados.senha}
                  onChange={(e) => {
                    atualizarInput(2, e);
                  }}
                  onKeyDown={(e) => {
                    eventoTeclado(e);
                  }}
                  type={senha ? "password" : "text"}
                  placeholder={texts.login.senha}
                  fullWidth
                  endAdornment={
                    <InputAdornment position="end">
                      <Tooltip
                        title={texts.login.visibilidade}
                        className="cursor-pointer"
                        onClick={mudarVisualizacaoSenha}
                      >
                        {senha ? <VisibilityIcon /> : <VisibilityOffIcon />}
                      </Tooltip>
                    </InputAdornment>
                  }
                />
              </Box>
            </Box>

            {/* Entrar */}
            <Box className="flex flex-col justify-end gap-5 mt-2">
              <Box className="w-full flex justify-start">
                <FormControlLabel
                  control={<Checkbox />}
                  label={texts.login.lembrarme}
                  onClick={() => {
                    lerTexto(texts.login.lembrarme);
                  }}
                />
              </Box>
              {/* <Box className="h-full flex flex-col justify-end gap-2"> */}

              <Button
                className="w-44 h-12"
                variant="contained"
                disableElevation
                onClick={fazerLogin}
              >
                <Typography
                  onClick={() => {
                    lerTexto(texts.login.entrar);
                  }}
                >
                  {texts.login.entrar}
                </Typography>
              </Button>
              {/* </Box> */}
            </Box>
          </Box>

          {/* Logos */}
          <Box className="w-full flex justify-center items-center gap-2 mt-8">
            <img src={LogoAzul} className="h-12" alt="WEG" />
            {/* <Divider orientation="vertical" flexItem />
            <Box
              className="p-1.5 rounded"
              sx={{ backgroundColor: "primary.main" }}
            >
              <img src={SsmBranca} className="h-10" alt="WEG-SSM" />
            </Box> */}
          </Box>
        </Box>
      </Box>
    </FundoComHeader>
  );
};

export default Login;
