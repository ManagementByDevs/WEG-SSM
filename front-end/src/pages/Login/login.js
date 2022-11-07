import { Button, Paper, TextField, InputAdornment, FormControlLabel, Checkbox, Typography, IconButton } from '@mui/material'
import { React, useState } from 'react'
import Header from '../../components/Header/Header';

import LogoWeg from "../../assets/logo-weg.png";
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import VisibilityIcon from '@mui/icons-material/Visibility';
import FontConfig from '../../service/FontConfig';

const Login = (props) => {

    // Variável usada para a visibilidade da senha (true - Senha Invisível / false - Senha Visível)
    const [senha, setSenha] = useState(true);

    // Função para mudar a visualização da senha (ver ou não)
    const mudarVisualizacaoSenha = () => {
        setSenha(!senha);
    }

    return (
        // Div Principal com width preenchendo a tela
        <Paper square sx={{ height: '100vh', width: '100vw' }}>
            {/* Componente Header */}
            <Header />

            {/* Div principal abaixo do Header */}
            <Paper sx={{ height: '91%' }} className='flex justify-center items-center'>
                {/* Div Principal com as opções do login */}
                <Paper sx={{ backgroundColor: 'secondary.main' }} className='w-4/12 h-3/4'>
                    <div className='w-full h-full border-t-12 border-[#00579D] rounded shadow-2xl flex flex-col items-center justify-center space-y-10'>
                        {/* Logo WEG */}
                        <img className='w-3/12' src={LogoWeg}></img>
                        {/* Input de texto do email do usuário */}
                        <TextField className='w-8/12' id="filled-basic" label="Email" variant="filled" color='primary' />
                        {/* Input de senha (usa os ícones e o tipo do input conforme a variável "senha", também chamando a função
                        "mudarVisualizacaoSenha" no click) */}
                        <TextField
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
                            <Button variant="contained" size="large" color='primary' className='self-end w-2/6'>
                                Entrar
                            </Button>
                        </div>
                    </div>
                </Paper>
            </Paper>
        </Paper>
    )
}

export default Login