import React from 'react'
import { Button, Paper, Typography, Input, Tab, Box, TextField } from '@mui/material';
import { TabContext, TabList, TabPanel } from '@mui/lab';
import SearchOutlinedIcon from '@mui/icons-material/SearchOutlined';
import SwapVertIcon from '@mui/icons-material/SwapVert';

import Header from '../../components/Header/Header';
import FontConfig from '../../service/FontConfig';

const Home = (props) => {
  // UseState para poder visualizar e alterar a aba selecionada
  const [value, setValue] = React.useState('1');

  // Função para alterar a aba selecionada
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    // Container pai
    <Paper sx={{ backgroundColor: 'component.main', height: '100vh', width: '100vw' }}>
      <Header />
      {/* <Button variant="contained" onClick={props.togglePalette}>Contained</Button>
      <Button color='secondary' variant="contained" onClick={props.togglePalette}>Contained</Button> */}
      {/* home
      <Paper sx={{ backgroundColor: 'input.main' }}>
        <Input sx={{ backgroundColor: 'input.main' }} />
        TEset
      </Paper>
      <Typography fontSize={FontConfig.title} variant="h2" color='text.primary'>Teste com texto</Typography> */}

      {/* Div container */}
      <Box className='flex justify-center mt-12' sx={{ backgroundColor: 'component.main', width: '100%' }}>

        {/* Div container para o conteúdo da home */}
        <Box sx={{ width: '90%' }}>

          {/* Sistema de abas */}
          <TabContext value={value}>
            <Box className='mb-4' sx={{ borderBottom: 1, borderColor: 'divider.main' }}>
              <TabList onChange={handleChange} aria-label="lab API tabs example">
                <Tab sx={{ color: 'text.secondary' }} label="Meu Departamento" value="1" />
                <Tab sx={{ color: 'text.secondary' }} label="Minhas Demandas" value="2" />
              </TabList>
            </Box>

            <Box className='flex justify-between border px-3 py-1' sx={{ backgroundColor: 'input.main', width: "25%" }}>
              <Box className='' component="input" sx={{ backgroundColor: 'input.main', outline: 'none' }} placeholder="Pesquisar por título..." />
              <Box className='flex gap-2'>
                <SearchOutlinedIcon sx={{ color: 'text.secondary' }} />
                <SwapVertIcon sx={{ color: 'text.secondary' }} />
              </Box>
            </Box>
            <TabPanel value="1">Meu Departamento</TabPanel>
            <TabPanel value="2">Minhas Demandas</TabPanel>
          </TabContext>
        </Box>
      </Box>
    </Paper>
  )
}

export default Home;