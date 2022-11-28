import React, { useState } from 'react'

import { Box } from '@mui/material'

import FontConfig from '../../service/FontConfig'
import InputComLabel from '../InputComLabel/InputComLabel'
import ModalConfirmacao from '../ModalConfirmacao/ModalConfirmacao'

const FormularioDadosDemanda = () => {
  const [dados, setDados] = useState({ titulo: "", problema: "", proposta: "" });

  const salvarTitulo = (texto) => {
    setDados({ ...dados, titulo: texto });
  }

  const salvarProblema = (texto) => {
    setDados({ ...dados, problema: texto });
  }

  const salvarProposta = (texto) => {
    setDados({ ...dados, proposta: texto });
  }

  const salvarFrequencia = (texto) => {
    setDados({ ...dados, frequencia: texto });
  }

  return (
    <Box className="flex justify-center items-center" sx={{ height: '45rem' }}>
      <Box className='w-3/4 flex flex-col justify-evenly' sx={{ height: '85%' }}>
        <InputComLabel saveInputValue={salvarTitulo} component='input' label="Titulo:" placeholder='Digite o título...' fontConfig={FontConfig.default} />
        <InputComLabel saveInputValue={salvarProblema} component='textarea' label="Problema:" placeholder='Digite o problema...' fontConfig={FontConfig.default} rows="5" />
        <InputComLabel saveInputValue={salvarProposta} component='textarea' label="Proposta:" placeholder='Digite a proposta...' fontConfig={FontConfig.default} rows="8" />
        {/* <ModalConfirmacao titulo="sair"/> */}
        <Box sx={{ width: '40%' }}>
          <InputComLabel saveInputValue={salvarFrequencia} component='input' label="Frequência de uso:" placeholder='Digite a frequência...' fontConfig={FontConfig.default} />
        </Box>
      </Box>
    </Box>
  )
}

export default FormularioDadosDemanda