import React, { useState } from 'react';
import { Paper } from '@mui/material/';

import IdiomaModal from '../Idioma-Modal/IdiomaModal';
import UserModal from '../User-Modal/UserModal';

import LogoBranca from '../../assets/LogoBranca.png';
import Grid from "../../assets/GridSemFundo.png";

import "./Header.css";
const Header = (props) => {
    const [userModal, setUserModal] = useState(true);

    return (
        <Paper sx={{ backgroundColor: 'primary.main', padding: '1rem' }} id="header-container" square>
            <div className='parte-esquerda'>
                <img className='grid tamanhoImagem' src={Grid} />
                <img className='logo tamanhoImagem' src={LogoBranca} />
            </div>
            <div className='parte-direita'>
                <IdiomaModal />
                {userModal && <UserModal />}
            </div>
        </Paper>
    )
}

export default Header