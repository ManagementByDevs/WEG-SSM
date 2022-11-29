import { Button, Paper, TextField, InputAdornment, FormControlLabel, Checkbox, Typography, IconButton } from '@mui/material'
import { React, useState } from 'react'
import { useNavigate } from "react-router-dom";

import FundoComHeader from '../../components/FundoComHeader/FundoComHeader';

import LogoWeg from "../../assets/logo-weg.png";
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import VisibilityIcon from '@mui/icons-material/Visibility';
import FontConfig from '../../service/FontConfig';

import UsuarioService from "../../service/usuarioService"

const Login = (props) => {
    // Variável para usar função de navegação do react-router-dom
    let navigate = useNavigate();

    // Variável usada para a visibilidade da senha (true - Senha Invisível / false - Senha Visível)
    const [senha, setSenha] = useState(true);

    // Variável usada como valor para os inputs de email e senha, para posterior login
    const [dados, setDados] = useState({ email: "", senha: "" });

    // Função para mudar a visualização da senha (ver ou não)
    const mudarVisualizacaoSenha = () => {
        setSenha(!senha);
    }

    // Função usada par atualizar o valor da variável dos inputs após alguma mudança
    const atualizarInput = (numero, e) => {
        if (numero == 1) {
            setDados({ ...dados, email: e.target.value })
        } else {
            setDados({ ...dados, senha: e.target.value })
        }
    }

    // Função para fazer login através do botão "Entrar", procurando o usuário no back-end e indo para a página principal caso encontre
    const login = () => {
        if (dados.email && dados.senha) {
            UsuarioService.login(dados.email, dados.senha).then((e) => {
                if (e != null && e != "") {
                    // Salvar token recebido no localstorage
                    localStorage.setItem('usuarioId', e.id);
                    navigate('/');
                } else {
                    // Abrir modal de feedback de usuário ou senha inválidos
                }
            });
        } else {
            // Abrir modal de feedback de dados não preenchidos
        }
    }

    return (
        // Div Principal com width preenchendo a tela
        <FundoComHeader>
            {/* Div principal abaixo do Header */}
            <Paper sx={{ height: '91%' }} className='flex justify-center items-center'>
                {/* Div Principal com as opções do login */}
                <Paper sx={{ backgroundColor: 'background.default', width: "28%", height: "63%" }} className=' '>
                    <div className='w-full h-full border-t-12 border-[#00579D] rounded shadow-2xl flex flex-col items-center justify-center space-y-10'>
                        {/* Logo WEG */}
                        <img className='w-3/12' src={LogoWeg}></img>
                        {/* Input de texto do email do usuário */}
                        <TextField value={dados.email}
                            onChange={(e) => { atualizarInput(1, e) }}
                            className='w-8/12' id="filled-basic"
                            label="Email" variant="filled"
                            color='primary'
                        />
                        {/* Input de senha (usa os ícones e o tipo do input conforme a variável "senha", também chamando a função
                        "mudarVisualizacaoSenha" no click) */}
                        <TextField
                            value={dados.senha}
                            onChange={(e) => { atualizarInput(2, e) }}
                            className='w-8/12'
                            id="input-with-icon-textfield"
                            label="Senha"
                            color='primary'
                            type={senha ? 'password' : 'text'}
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
                        {/* Div para checkbox "lembrar-me" e "Esqueci a senha" */}
                        <div className='w-8/12 flex justify-between items-center'>
                            {/* Checkbox com label para função de "lembrar-me" */}
                            <FormControlLabel control={<Checkbox />} label="Lembrar-me" />
                            {/* Texto "Esqueci a Senha" (Usa a fonte média) */}
                            <Typography fontSize={FontConfig.medium} variant="h2" color='text.primary' className='underline hover:cursor-pointer'>Esqueci a Senha</Typography>
                        </div>
                        {/* Div para centralizar o botão de login */}
                        <div className='w-8/12 flex justify-center'>

                            {/* Botão para entrar no sistema */}
                            <Button onClick={login} variant="contained" size="large" color='primary' className='self-end w-2/6'>
                                Entrar
                            </Button>
                        </div>
                    </div>
                </Paper>
            </Paper>
        </FundoComHeader>
    )
}

export default Login