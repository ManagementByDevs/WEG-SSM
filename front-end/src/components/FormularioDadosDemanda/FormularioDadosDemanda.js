import React from 'react'

import { Box } from '@mui/material'
import { borderColor } from '@mui/system'

const FormularioDadosDemanda = () => {
  return (
    <Box>
      <Box className='outline-none border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm' sx={{ borderLeftColor: 'primary.main' }} component='input' />
    </Box>
  )
}

export default FormularioDadosDemanda