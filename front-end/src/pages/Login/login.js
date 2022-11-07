import { Button, Paper, TextField, InputAdornment } from '@mui/material'
import React from 'react'
import Header from '../../components/Header/Header';

import LogoWeg from "../../assets/logo-weg.png";
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import VisibilityIcon from '@mui/icons-material/Visibility';

import "./login.css";

const login = (props) => {
    return (
        <Paper square sx={{ height: '100vh', width: '100vw' }}>
            <Header />
            <Paper sx={{ height: '90%' }} className='flex justify-center items-center'>
                <div className='w-2/5 h-2/3 border-t-12 border-[#00579D] rounded shadow-2xl flex flex-col items-center justify-center space-y-10'>
                    <img className='w-3/12' src={LogoWeg}></img>
                    <TextField id="filled-basic" label="Filled" variant="filled" />
                    <TextField
                        id="input-with-icon-textfield"
                        label="TextField"
                        InputProps={{
                            endAdornment: (
                                <InputAdornment position="end">
                                    <VisibilityOffIcon/>
                                </InputAdornment>
                            ),
                        }}
                        variant="filled"
                    />
                </div>
            </Paper>
        </Paper>
    )
}

export default login