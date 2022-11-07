import React, { useState } from 'react';
import { Paper } from '@mui/material/';
import { useLocation } from 'react-router-dom';

import IdiomaModal from '../Idioma-Modal/IdiomaModal';
import UserModal from '../User-Modal/UserModal';

import LogoBranca from '../../assets/LogoBranca.png';
import Grid from "../../assets/GridSemFundo.png";

import "./Header.css";
const Header = (props) => {
    const location = useLocation();
    const [rota, setRota] = useState(location.pathname);

    return (
        <Paper sx={{ backgroundColor: 'primary.main', padding: '1rem' }} className='flex justify-between items-center h-1/10' square>
            <div className='flex gap-3.5'>
                <img className='h-12' src={Grid} />
                <img className='h-12' src={LogoBranca} />
            </div>
            <div className='flex items-center gap-4'>
                <IdiomaModal />
                {rota == '/' ? <UserModal /> : null}
            </div>
        </Paper>
    )
}

export default Header