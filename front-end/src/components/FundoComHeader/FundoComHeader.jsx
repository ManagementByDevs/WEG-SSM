import React from 'react'

import { Box } from '@mui/material';

import Header from '../Header/Header';


const FundoComHeader = (props) => {
    return (
        <Box sx={{ backgroundColor: 'background.default', height: '100vh', minHeight: '100vh', width: '100%' }}>
            <Header />
                {props.children}
        </Box>
    )
}

export default FundoComHeader;