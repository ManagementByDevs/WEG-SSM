import { Box, Typography, Button } from '@mui/material'
import React, { useState, useEffect } from 'react'
import FontConfig from '../../service/FontConfig'

import ModalMotivoRecusa from "../ModalMotivoRecusa/ModalMotivoRecusa";

const Demanda = (props) => {

  // Cor do status da demansa
  let corStatus = getStatusColor();
  let tamanhoHeight = getTamanhoHeight();

  // Função para receber a cor do status da demanda
  function getStatusColor() {

    if (props.demanda.status == "CANCELLED") {
      return '#DA0303';
    }

    if (props.demanda.status == "BACKLOG") {
      if (props.demanda.analista == null) {
        return "#C4C4C4";
      }
      if (props.demanda.motivoRecusa != null) {
        return "#FFD600";
      }
      if (props.demanda.analista != null) {
        return "#11B703";
      }
    }
  }

  // Função para formatar o nome do status da demanda
  const formatarNomeStatus = () => {
    if (props.demanda.status == "CANCELLED") {
      return 'Reprovada';
    }

    if (props.demanda.status == "BACKLOG") {
      if (props.demanda.analista == null) {
        return "Aguardando Revisão";
      }
      if (props.demanda.motivoRecusa != null) {
        return "Aguardando Edição";
      }
      if (props.demanda.analista != null) {
        return "Aprovada";
      }
    }
  }

  function getTamanhoHeight() {
    if (parseInt(localStorage.getItem("userId")) != props.demanda?.solicitante?.id) {
      return '10rem';
    } else {
      return '12rem';
    }
  }

  // useState para abrir o modal de motivo recusa
  const [abrirModal, setOpenModal] = useState(false);

  const abrirModalMotivoRecusa = () => {
    setOpenModal(true);
  }

  return (
    <Box onClick={props.onClick} sx={{ backgroundColor: 'background.default', padding: '1rem', minWidth: '550px', maxWidth: '100%', minHeight: tamanhoHeight, maxHeight: '12rem', border: '0.2px solid black', borderTop: '7px solid #00579D', borderRadius: '5px', cursor: 'pointer' }} className={`items-center h-30 text-justify drop-shadow-lg`}>
      <Box className={`flex justify-between`} sx={{ marginBottom: '1%' }}>
        <Typography fontSize={FontConfig.veryBig} sx={{ fontWeight: '600', cursor: 'default' }} color="text.primary">{props.demanda.titulo}</Typography>
        {
          parseInt(localStorage.getItem("usuarioId")) == props.demanda?.solicitante?.id &&
          <Box className={`items-center text-justify flex`}>
            <Typography fontSize={FontConfig.default} sx={{ fontWeight: '600', cursor: 'default' }}>{formatarNomeStatus()}</Typography>
            <Box sx={{ backgroundColor: corStatus, width: '12px', height: '12px', borderRadius: '10px', marginLeft: '10px' }} className={`items-center h-30 text-justify`} />
          </Box>
        }

      </Box>

      <Typography gutterBottom fontSize={FontConfig.default} color="text.secondary">{props.demanda.proposta}</Typography>
      <Box className={`flex justify-end`} sx={{ marginTop: '.5%' }}>
        {
          parseInt(localStorage.getItem("usuarioId")) != props.demanda?.solicitante?.id ?
            <Typography fontSize={FontConfig.default} sx={{ fontWeight: '600', cursor: 'default' }} color="text.primary">{props.demanda.solicitante?.nome}</Typography>
            : props.demanda.status === 'CANCELLED' && props.demanda.solicitante.id === parseInt(localStorage.getItem("userId")) ?
              <Button onClick={abrirModalMotivoRecusa} variant="contained">Motivo</Button>
              : null
        }
        {/* Abrindo o modal de motivo recusa */}
        {abrirModal && <ModalMotivoRecusa open={abrirModal} setOpen={setOpenModal} motivoRecusa={"Aqui vai o texto de motivo da recusa"} />}
      </Box>
    </Box>
  );
};

export default Demanda;
