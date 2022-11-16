import React, { useContext } from 'react'
import { Button, Paper, Typography, Input, Tab, Box, TextField } from '@mui/material';
import { TabContext, TabList, TabPanel } from '@mui/lab';
import SearchOutlinedIcon from '@mui/icons-material/SearchOutlined';
import SwapVertIcon from '@mui/icons-material/SwapVert';
import FilterAltOutlinedIcon from '@mui/icons-material/FilterAltOutlined';
import AddIcon from '@mui/icons-material/Add';

import Header from '../../components/Header/Header';
import Demanda from '../../components/Demanda/Demanda';

import FontConfig from '../../service/FontConfig';
import ColorModeContext from '../../service/TemaContext';
import { grid } from '@mui/system';

const Home = () => {
  // Desestruturação de objeto em duas variáveis:
  // - Mode: modo do tema atual ("light" ou "dark")
  // - toggleColorMode: função para alternar o tema
  const { mode, toggleColorMode } = useContext(ColorModeContext);

  // UseState para poder visualizar e alterar a aba selecionada
  const [value, setValue] = React.useState('1');

  // Função para alterar a aba selecionada
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    // Container pai
    <Box sx={{ backgroundColor: 'background.default', height: '100vh', width: '100vw' }}>
      <Header />
      <Button variant="contained" onClick={toggleColorMode}>Contained</Button>
      {/* <Button color='secondary' variant="contained" onClick={toggleColorMode} sx={{fontSize: FontConfig.medium}}>Contained</Button> */}
      {/* home
      <Paper sx={{ backgroundColor: 'input.main' }}>
        <Input sx={{ backgroundColor: 'input.main' }} />
        TEset
      </Paper>
      <Typography fontSize={FontConfig.title} variant="h2" color='text.primary'>Teste com texto</Typography> */}

      {/* Div container */}
      <Box className='flex justify-center mt-12' sx={{ backgroundColor: 'background.default', width: '100%' }}>

        {/* Div container para o conteúdo da home */}
        <Box sx={{ width: '90%' }}>

          {/* Sistema de abas */}
          <TabContext value={value}>
            <Box className='mb-4' sx={{ borderBottom: 1, borderColor: 'divider.main' }}>
              <TabList onChange={handleChange} aria-label="lab API tabs example">
                <Tab sx={{ color: 'text.secondary', fontSize: FontConfig.medium }} label="Meu Departamento" value="1" />
                <Tab sx={{ color: 'text.secondary', fontSize: FontConfig.medium }} label="Minhas Demandas" value="2" />
              </TabList>
            </Box>

            {/* Container das ações abaixo das abas (input de pesquisa, filtrar e criar demanda) */}
            <Box className='flex justify-between w-full'>

              {/* Container para o input e botão de filtrar */}
              <Box className='flex gap-4 w-2/4'>

                {/* Input de pesquisa */}
                <Box className='flex justify-between border px-3 py-1' sx={{ backgroundColor: 'input.main', width: "50%" }}>

                  {/* Input de pesquisa */}
                  <Box className='w-full' component="input" sx={{ backgroundColor: 'input.main', outline: 'none', color: "text.primary", fontSize: FontConfig.medium }} placeholder="Pesquisar por título..." />

                  {/* Container para os ícones */}
                  <Box className='flex gap-2'>

                    {/* Ícone de pesquisa */}
                    <SearchOutlinedIcon sx={{ color: 'text.secondary' }} />

                    {/* Ícone de ordenação */}
                    <SwapVertIcon className='cursor-pointer' sx={{ color: 'text.secondary' }} />
                  </Box>
                </Box>

                {/* Botão de filtrar */}
                <Button sx={{ backgroundColor: 'primary.main', color: 'text.white', fontSize: FontConfig.default }} variant="contained" disableElevation>Filtrar <FilterAltOutlinedIcon /></Button>
              </Box>

              {/* Botão de criar demanda */}
              <Button className='gap-2' sx={{ backgroundColor: 'primary.main', color: 'text.white', fontSize: FontConfig.default }} variant="contained" disableElevation>Criar demanda <AddIcon /></Button>
            </Box>

            {/* Container para o conteúdo das abas */}
            <Box className='mt-6'>

              {/* Valores para as abas selecionadas */}
              <TabPanel sx={{ padding: 0 }} value="1">
                <Box sx={{ display: 'grid', gap: '1rem', gridTemplateColumns: 'repeat(auto-fit, minmax(650px, 1fr))' }}>
                  <Demanda demanda={{ status: "Aguardando revisão", dono: "Kenzo"}} />
                  <Demanda demanda={{ status: "Aguardando revisão", dono: "Felipe"}} />
                  <Demanda demanda={{ status: "Aguardando revisão", dono: "Matheus"}} />
                  <Demanda demanda={{ status: "Aguardando revisão", dono: "Thiago"}} />
                </Box>
              </TabPanel>
              <TabPanel sx={{ padding: 0 }} value="2">
                <Box sx={{ display: 'grid', gap: '1rem', gridTemplateColumns: 'repeat(auto-fit, minmax(650px, 1fr))' }}>
                  <Demanda demanda={{ status: "Aguardando edição", dono: "Thiago"}} />
                  <Demanda demanda={{ status: "Aguardando revisão", dono: "Thiago"}} />
                  <Demanda demanda={{ status: "Aprovada", dono: "Thiago"}} />
                  <Demanda demanda={{ status: "Reprovada", dono: "Thiago"}} />
                </Box>
              </TabPanel>
            </Box>
          </TabContext>
        </Box>
      </Box>
    </Box>
  )
}

export default Home;