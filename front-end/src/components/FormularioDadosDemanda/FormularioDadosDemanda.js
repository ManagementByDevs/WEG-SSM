import React from 'react'

import { Box, Typography } from '@mui/material'
import FontConfig from '../../service/FontConfig'

const FormularioDadosDemanda = () => {
  return (
    <Box className="flex justify-center items-center" sx={{ height: '45rem' }}>
      <Box className='w-3/4 flex flex-col justify-evenly' sx={{ height: '85%' }}>
        <Box>
          <Box className='flex'>
            <Typography fontSize={FontConfig.default} sx={{ fontWeight: '600', cursor: 'default' }} gutterBottom>Título:</Typography>
            <Typography fontSize={FontConfig.default} sx={{ fontWeight: '800', cursor: 'default', margin: '0 .2% .2% .2%' }} className='text-red-600' gutterBottom>*</Typography>
          </Box>
          <Box fontSize={FontConfig.default} className='outline-none border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded drop-shadow-md

' sx={{ borderLeftColor: 'primary.main', width: '100%;', backgroundColor: 'background.default', fontWeight: '300'}} component='input' placeholder='Digite o título...' />
        </Box>
        <Box>
          <Box className='flex'>
            <Typography fontSize={FontConfig.default} sx={{ fontWeight: '600', cursor: 'default' }} gutterBottom>Problema:</Typography>
            <Typography fontSize={FontConfig.default} sx={{ fontWeight: '800', cursor: 'default', margin: '0 .2% .2% .2%' }} className='text-red-600' gutterBottom>*</Typography>
          </Box>
          <Box fontSize={FontConfig.default} className='outline-none border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded drop-shadow-md

' sx={{ borderLeftColor: 'primary.main', width: '100%;', backgroundColor: 'background.default', fontWeight: '300', resize:'none' }} component='textarea' placeholder='Digite o problema...' rows="5"/>
        </Box>
        <Box>
          <Box className='flex'>
            <Typography fontSize={FontConfig.default} sx={{ fontWeight: '600', cursor: 'default' }} gutterBottom>Proposta:</Typography>
            <Typography fontSize={FontConfig.default} sx={{ fontWeight: '800', cursor: 'default', margin: '0 .2% .2% .2%' }} className='text-red-600' gutterBottom>*</Typography>
          </Box>
          <Box fontSize={FontConfig.default} className='outline-none border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded drop-shadow-md

' sx={{ borderLeftColor: 'primary.main', width: '100%;', backgroundColor: 'background.default', fontWeight: '300', resize:'none' }} component='textarea' placeholder='Digite a proposta...' rows="8"/>
        </Box>
        <Box>
          <Box className='flex'>
            <Typography fontSize={FontConfig.default} sx={{ fontWeight: '600', cursor: 'default' }} gutterBottom>Frequência de uso:</Typography>
            <Typography fontSize={FontConfig.default} sx={{ fontWeight: '800', cursor: 'default', margin: '0 .2% .2% .2%', fontWeight: '300' }} className='text-red-600' gutterBottom>*</Typography>
          </Box>
          <Box fontSize={FontConfig.default} className='outline-none border-solid border border-l-4 px-1 py-1.5 drop-shadow-sm rounded drop-shadow-md

' sx={{ borderLeftColor: 'primary.main', width: '40%;', backgroundColor: 'background.default', fontWeight: '300'}} component='input' placeholder='Frequência de uso...'/>
        </Box>
      </Box>
    </Box>
  )
}

export default FormularioDadosDemanda