import { Box, Typography, Button } from '@mui/material'
import React, {useState} from 'react'
import FontConfig from '../../service/FontConfig'

import ModalMotivoRecusa from '../ModalMotivoRecusa/ModalMotivoRecusa'

const Demanda = (props) => {
    let autor = props.demanda.dono;
    let status = props.demanda.status;
    let tela = props.demanda.tela;
    let userLogado = "Thiago";
    let corStatus = getStatusColor();
    let tamanhoHeight = getTamanhoHeight();

    function getStatusColor() {
        switch (status) {
            case 'Aguardando edição':
                return '#FFD600';
            case 'Aguardando revisão':
                return '#C4C4C4';
            case 'Aprovada':
                return '#11B703';
            case 'Reprovada':
                return '#DA0303';
            default:
                return '#DA0303';
        }
    }

    function getTamanhoHeight() {
        if (tela !== "minhasDemandas") {
            return '10rem';
        } else {
            return '12rem';
        }
    }

    // useState para abrir o modal de motivo recusa

    const [abrirModal, setOpenModal] = useState(false);

    const abrirModalMotivoRecusa = () =>{
        setOpenModal(true);
    }

    return (
        <Box onClick={props.onClick} sx={{ backgroundColor: 'background.default', padding: '1rem', minWidth: '550px', maxWidth: '100%', minHeight: tamanhoHeight, maxHeight: '12rem', border: '0.2px solid black', borderTop: '7px solid #00579D', borderRadius: '5px', cursor: 'pointer' }} className={`items-center h-30 text-justify drop-shadow-lg`}>
            <Box className={`flex justify-between`} sx={{ marginBottom: '1%' }}>
                <Typography fontSize={FontConfig.veryBig} sx={{ fontWeight: '600', cursor: 'default' }} color="text.primary">Título da proposta</Typography>
                {
                    autor === "Thiago" && tela === "minhasDemandas"?
                    <Box className={`items-center text-justify flex`}>
                        <Typography fontSize={FontConfig.default} sx={{ fontWeight: '600', cursor: 'default' }}>{status}</Typography>
                        <Box sx={{ backgroundColor: corStatus, width: '12px', height: '12px', borderRadius: '10px', marginLeft: '10px' }} className={`items-center h-30 text-justify`} />
                    </Box>
                    : null
                }
                
            </Box>
            
            <Typography gutterBottom fontSize={FontConfig.default} color="text.secondary">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries</Typography>
            <Box className={`flex justify-end`} sx={{ marginTop: '.5%' }}>
                {
                    status === 'Aguardando revisão' && autor !== userLogado ?
                        <Typography fontSize={FontConfig.default} sx={{ fontWeight: '600', cursor: 'default' }} color="text.primary">{autor}</Typography>
                        : status === 'Reprovada' && autor === userLogado?
                            <Button onClick={abrirModalMotivoRecusa} variant="contained">Motivo</Button>
                        :null
                }
                {/* Abrindo o modal de motivo recusa */}
                {abrirModal && <ModalMotivoRecusa open={abrirModal} setOpen={setOpenModal} motivoRecusa={"Aqui vai o texto de motivo da recusa"} />}
            </Box>
        </Box>
    )
}



export default Demanda