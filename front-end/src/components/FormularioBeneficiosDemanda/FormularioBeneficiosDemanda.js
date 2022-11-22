import React, { useState } from 'react'

import { Box, Button } from '@mui/material'
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import Beneficios from '../Beneficios/Beneficios';

const FormularioBeneficiosDemanda = () => {
  const [beneficios, setBeneficios] = useState([]);

  function adicionarBeneficio() {
    setBeneficios([...beneficios, {id:beneficios.length+1, tipo:'', valor:'', moeda:'', memoriaCalculo:''}]);
  }

  function salvarDados(dados) {
    for(let beneficio of beneficios) {
      if(beneficio.id === dados.id) {
        beneficio.tipo = dados.tipo;
        beneficio.valor = dados.valor;
        beneficio.moeda = dados.moeda;
        beneficio.memoriaCalculo = dados.memoriaCalculo;
      }
    }
    console.log(dados);
    console.log("cleitoom", beneficios);
  }

  return (
    <Box className="flex justify-center items-center" sx={{ height: '45rem' }}>
      <Box className='w-3/4 flex flex-col' sx={{ height: '85%' }}>
        <Button className='rounded flex justify-evenly' color='primary' variant='contained' disableElevation sx={{width: '10%'}} onClick={adicionarBeneficio}>
          Adicionar
          <AddOutlinedIcon />
        </Button>
        <Box className='flex flex-col justify-evenly' sx={{marginTop:'3%'}}>
          {
            beneficios.map((beneficio, index) => {
              return <Beneficios key={index} save={salvarDados} dados={beneficio}/>;
            })
          }
        </Box>
      </Box>
    </Box>
  )
}

export default FormularioBeneficiosDemanda