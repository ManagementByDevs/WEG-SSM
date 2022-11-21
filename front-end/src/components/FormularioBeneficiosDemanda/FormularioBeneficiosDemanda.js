import React from 'react'

import { Box, Button } from '@mui/material'
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import Beneficios from '../Beneficios/Beneficios';

const FormularioBeneficiosDemanda = () => {

  function adicionarBeneficio() {
    alert('Adicionar beneficio')
  }

  return (
    <Box className="flex justify-center items-center" sx={{ height: '45rem' }}>
      <Box className='w-3/4 flex flex-col' sx={{ height: '85%' }}>
        <Button className='rounded flex justify-evenly' color='primary' variant='contained' disableElevation sx={{width: '10%'}} onClick={adicionarBeneficio}>
          Adicionar
          <AddOutlinedIcon />
        </Button>
        <Box className='flex flex-col justify-evenly' sx={{marginTop:'3%'}}>
          <Beneficios />
        </Box>
      </Box>
    </Box>
  )
}

export default FormularioBeneficiosDemanda