import React from 'react'
import { Button, Paper, Typography, Input } from '@mui/material';
import classes from './home.module.css';

import Header from '../../components/Header/Header';
import FontConfig from '../../service/FontConfig';

const home = (props) => {
  return (
    <Paper sx={{ backgroundColor: 'component.main' }}>
      <Header/>
      <Button variant="contained" onClick={props.togglePalette}>Contained</Button>
      <Button color='secondary' variant="contained" onClick={props.togglePalette}>Contained</Button>
      home
      <Paper sx={{ backgroundColor: 'input.main' }}>
        <Input sx={{ backgroundColor: 'input.main' }} />
        TEset
      </Paper>
      <Typography fontSize={FontConfig.title} variant="h2" color='text.primary'>Teste com texto</Typography>
    </Paper>
  )
}

export default home;