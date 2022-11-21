import React from 'react'

import { Box, Typography } from '@mui/material'

const InputComLabel = (props) => {
    return (
        <Box>
            <Box className='flex'>
                <Typography fontSize={props.fontConfig} sx={{ fontWeight: '600', cursor: 'default' }} gutterBottom>{props.label}</Typography>
                <Typography fontSize={props.fontConfig} sx={{ fontWeight: '800', cursor: 'default', margin: '0 .2% .2% .2%' }} className='text-red-600' gutterBottom>*</Typography>
            </Box>
            {props.component === 'input' 
            ? <Box fontSize={props.fontConfig} className='outline-none border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded drop-shadow-md

' sx={{ borderLeftColor: 'primary.main', width: '100%;', backgroundColor: 'background.default', fontWeight: '300' }} component='input' placeholder={props.placeholder} /> 
            : <Box fontSize={props.fontConfig} className='outline-none border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded drop-shadow-md

            ' sx={{ borderLeftColor: 'primary.main', width: '100%;', backgroundColor: 'background.default', fontWeight: '300', resize: 'none' }} component='textarea' placeholder={props.placeholder} rows={props.rows} />}

        </Box>
    )
}

export default InputComLabel