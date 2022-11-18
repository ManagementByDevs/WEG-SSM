import React from 'react'

import { Box, Typography } from '@mui/material'
import FontConfig from '../../service/FontConfig'

const FormularioDadosDemanda = () => {
  return (
    <Box sx={{height: '45rem'}}>
      <Box className='flex'>
        <Typography fontSize={FontConfig.default} sx={{ fontWeight: '600', cursor: 'default' }} gutterBottom>Título:</Typography>
        <Typography fontSize={FontConfig.default} sx={{ fontWeight: '800', cursor: 'default', margin: '0 .2% .2% .2%' }} className='text-red-600' gutterBottom>*</Typography>
      </Box>
      <Box className='outline-none border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm' sx={{ borderLeftColor: 'primary.main', width: '80%;' }} component='input' />
    </Box>
  )
}

export default FormularioDadosDemanda