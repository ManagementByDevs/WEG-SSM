import React, { useState, useEffect } from 'react'
import './FormularioBeneficiosDemanda.css';

import { Box, Button } from '@mui/material'

import AddOutlinedIcon from '@mui/icons-material/AddOutlined';

import Beneficios from '../Beneficios/Beneficios';

const FormularioBeneficiosDemanda = (props) => {
  // Lista de benefícios adicionadas
  const [beneficios, setBeneficios] = useState([]);

  // UseEffect para pegar os benefícios já adicionados na demanda na troca de página
  useEffect(() => {
    setBeneficios(props.dados);
  }, [])

  // Adiciona um benefício na lista de benefícios
  function adicionarBeneficio() {
    setBeneficios([...beneficios, { id: beneficios.length + 1, tipoBeneficio: '', valor_mensal: '', moeda: '', memoriaCalculo: '', visible: true }]);
  }

  // Função genérica para salvar os dados de um benefício
  function salvarDados(dados) {
    setBeneficios(beneficios.map(beneficio => {
      if (beneficio.id === dados.id) {
        beneficio = dados;
      }
      return beneficio;
    }));
  }

  // Função que irá setar a visibildade de um benefício para false, sendo o benefício excluído da lista savedBeneficios
  function removerBeneficio(desiredIndex) {
    setBeneficios(beneficios.map((beneficio, index) => {
      if (index === desiredIndex) {
        beneficio.visible = false;
      }
      return beneficio;
    }));
  }

  // UseEffect que irá atualizar a lista de benefícios a serem salvos (que não foram excluídos)
  useEffect(() => {
    props.setDados(beneficios?.filter(beneficio => beneficio.visible === true));
  }, [beneficios]);

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
              if (beneficio.visible) {
                return <Beneficios key={index} save={salvarDados} index={index} removerBeneficio={removerBeneficio} dados={beneficio} />;
              }
            })
          }
        </Box>
      </Box>
    </Box>
  )
}

export default FormularioBeneficiosDemanda