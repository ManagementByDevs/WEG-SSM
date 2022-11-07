import { Button, Paper, TextField, InputAdornment, FormControlLabel, Checkbox, Typography } from '@mui/material'
import React from 'react'
import Header from '../../components/Header/Header';

import LogoWeg from "../../assets/logo-weg.png";
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import VisibilityIcon from '@mui/icons-material/Visibility';
import FontConfig from '../../service/FontConfig';

import "./login.css";

const login = (props) => {
    return (
        <Paper square sx={{ height: '100vh', width: '100vw' }}>
            <Header />
            <Paper sx={{ height: '90%' }} className='flex justify-center items-center'>
                <Paper sx={{ backgroundColor: 'secondary.main' }} className='w-4/12 h-3/4'>
                    <div className='w-full h-full border-t-12 border-[#00579D] rounded shadow-2xl flex flex-col items-center justify-center space-y-10'>
                        <img className='w-3/12' src={LogoWeg}></img>
                        <TextField className='w-8/12' id="filled-basic" label="Email" variant="filled" color='primary' />
                        <TextField
                            className='w-8/12'
                            id="input-with-icon-textfield"
                            label="Senha"
                            color='primary'
                            type='password'
                            InputProps={{
                                endAdornment: (
                                    <InputAdornment position="end">
                                        <VisibilityOffIcon />
                                    </InputAdornment>
                                ),
                            }}
                            variant="filled"
                        />
                        <div className='w-8/12 flex justify-between items-center'>
                            <FormControlLabel control={<Checkbox />} label="Lembrar-me" />
                            <Typography fontSize={FontConfig.medium} variant="h2" color='text.primary' className='underline'>Esqueci a Senha</Typography>
                        </div>
                        <div className='w-8/12 flex justify-center'>
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

export default login