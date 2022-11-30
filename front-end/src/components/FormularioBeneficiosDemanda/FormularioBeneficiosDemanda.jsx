import React, { useState } from 'react'
import './FormularioBeneficiosDemanda.css';

import { Box, Button } from '@mui/material'
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import Beneficios from '../Beneficios/Beneficios';
import { useEffect } from 'react';

const FormularioBeneficiosDemanda = () => {
  const [beneficios, setBeneficios] = useState([]);
  // const [visible, setVisible] = useState(true);

  function adicionarBeneficio() {
    setBeneficios([...beneficios, { id: beneficios.length + 1, tipo: '', valor: '', moeda: '', memoriaCalculo: '' }]);
  }

  function salvarDados(dados) {
    setBeneficios(beneficios.map(beneficio => {
      if (beneficio.id === dados.id) {
        beneficio = dados;
      }
      return beneficio;
    }));
    // for (let beneficio of beneficios) {
    //   if (beneficio.id === dados.id) {
    //     beneficio.tipo = dados.tipo;
    //     beneficio.valor = dados.valor;
    //     beneficio.moeda = dados.moeda;
    //     beneficio.memoriaCalculo = dados.memoriaCalculo;
    //   }
    // }
  }

  // useEffect(() => {
  //   setVisible(true);
  // }, [visible]);

  function verDados() {
    console.log(beneficios);
  }

  function removerBeneficio(desiredIndex) {
    setBeneficios(beneficios.filter((_, index) => index !== desiredIndex));
    // setVisible(false);
  }

  return (
    <Box className="flex justify-center items-center" sx={{ height: '45rem' }}>
      <Box className='w-3/4 flex flex-col' sx={{ height: '85%' }}>
        <Button className='rounded flex justify-evenly' color='primary' variant='contained' disableElevation sx={{ width: '10%' }} onClick={adicionarBeneficio}>
          Adicionar
          <AddOutlinedIcon />
        </Button>
        <Box className='flex flex-col overflow-auto' sx={{ marginTop: '3%', gap: '5%', paddingRight: '20px' }}>
          {
            beneficios?.map((beneficio, index) => {
              return <Beneficios key={index} save={salvarDados} index={index} removerBeneficio={removerBeneficio} dados={beneficio} />;
            })
          }
        </Box>
        <Button onClick={verDados} >tsete</Button>
      </Box>
    </Box>
  )
}

export default FormularioBeneficiosDemanda