import React from 'react'

import { Box } from '@mui/material';

import Header from '../../components/Header/Header';


const FundoComHeader = (props) => {
    return (
        <Box sx={{ backgroundColor: 'background.default', height: '100vh', minHeight: '100vh', width: '100vw' }}>
            <Header />
            {props.children}
        </Box>
    )
}

export default FundoComHeader