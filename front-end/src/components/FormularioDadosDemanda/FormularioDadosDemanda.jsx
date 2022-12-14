import React from 'react'

import { Box } from '@mui/material'

import InputComLabel from '../InputComLabel/InputComLabel'

import FontConfig from '../../service/FontConfig'

const FormularioDadosDemanda = (props) => {

  // Todas as funções de salvamento modificam diretamente as variáveis do componente "BarraProgressãoDemanda" (paginaDados)
  // através do "props"

  // Função para salvar o título da demanda
  const salvarTitulo = (texto) => {
    props.setDados({ ...props.dados, titulo: texto });
  }

  // Função para salvar o problema da demanda
  const salvarProblema = (texto) => {
    props.setDados({ ...props.dados, problema: texto });
  }

  // Função para salvar a proposta da demanda
  const salvarProposta = (texto) => {
    props.setDados({ ...props.dados, proposta: texto });
  }

  // Função para salvar a frequência da demanda
  const salvarFrequencia = (texto) => {
    props.setDados({ ...props.dados, frequencia: texto });
  }

  return (
    <Box className="flex justify-center items-center" sx={{ height: '45rem' }}>
      <Box className='w-3/4 flex flex-col justify-evenly' sx={{ height: '85%' }}>
        <InputComLabel texto={props.dados.titulo} saveInputValue={salvarTitulo} component='input' label="Titulo:" placeholder='Digite o título...' fontConfig={FontConfig.default} />
        <InputComLabel texto={props.dados.problema} saveInputValue={salvarProblema} component='textarea' label="Problema:" placeholder='Digite o problema...' fontConfig={FontConfig.default} rows="5" />
        <InputComLabel texto={props.dados.proposta} saveInputValue={salvarProposta} component='textarea' label="Proposta:" placeholder='Digite a proposta...' fontConfig={FontConfig.default} rows="8" />
        {/* <ModalConfirmacao titulo="sair"/> */}
        <Box sx={{ width: '40%' }}>
          <InputComLabel texto={props.dados.frequencia} saveInputValue={salvarFrequencia} component='input' label="Frequência de uso:" placeholder='Digite a frequência...' fontConfig={FontConfig.default} />
        </Box>
      </Box>
    </Box>
  )
}

export default FormularioDadosDemanda;