import React from 'react'

import { Box } from '@mui/material'

import FontConfig from '../../service/FontConfig'
import InputComLabel from '../InputComLabel/InputComLabel'

const FormularioDadosDemanda = () => {
  return (
    <Box className="flex justify-center items-center" sx={{ height: '45rem' }}>
      <Box className='w-3/4 flex flex-col justify-evenly' sx={{ height: '85%' }}>
        <InputComLabel component='input' label="Titulo:" obrigatorio={true} placeholder='Digite o título...' fontConfig={FontConfig.default} />
        <InputComLabel component='textarea' label="Problema:" obrigatorio={true} placeholder='Digite o problema...' fontConfig={FontConfig.default} rows="5" />
        <InputComLabel component='textarea' label="Proposta:" obrigatorio={true} placeholder='Digite a proposta...' fontConfig={FontConfig.default} rows="8" />
        <Box sx={{ width: '40%' }}>
          <InputComLabel component='input' label="Frequência de uso:" obrigatorio={true} placeholder='Digite a frequência...' fontConfig={FontConfig.default} />
        </Box>
      </Box>
    </Box>
  )
}

export default FormularioDadosDemanda