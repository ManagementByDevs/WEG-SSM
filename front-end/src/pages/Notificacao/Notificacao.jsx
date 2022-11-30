import React, {useState} from 'react';
import { Box, Typography, Button, Divider } from '@mui/material';

import FundoComHeader from '../../components/FundoComHeader/FundoComHeader';
import Caminho from '../../components/Caminho/Caminho';
import FontConfig from '../../service/FontConfig';
import NotificacaoComponente from '../../components/NotificacaoComponente/NotificacaoComponente';

import ModalFiltro from '../../components/ModalFiltro/ModalFiltro';
import FilterAltOutlinedIcon from '@mui/icons-material/FilterAltOutlined';

const Notificacao = () => {
    const [abrirFiltro, setOpenFiltro] = useState(false);

    const abrirModalFiltro = () =>{
        setOpenFiltro(true);
    }

    return (
        <FundoComHeader>
            <Box className='p-2'>
                <Caminho />
                <Box className='w-full flex flex-col items-center'>
                    <Box className='w-full flex justify-center m-2'>
                        <Typography fontSize={FontConfig.title} color={'icon.main'} sx={{ fontWeight: '600', cursor: 'default' }}>Notificações</Typography>
                    </Box>
                    <Box className='w-10/12'>
                    <Divider sx={{borderColor: 'tertiary.main'}}/>
                    </Box>
                    <Box className='w-full flex justify-center'>
                        <Box className='w-10/12 flex justify-end ' color={'icon.main'} sx={{ margin: '5px' }}>
                            <Button onClick={abrirModalFiltro} sx={{ backgroundColor: 'primary.main', color: 'text.white', fontSize: FontConfig.default }} variant="contained" disableElevation>Filtrar <FilterAltOutlinedIcon /></Button>
                        </Box>
                        {abrirFiltro && <ModalFiltro open={abrirFiltro} setOpen={setOpenFiltro} filtroDemanda={false} />}
                    </Box>
                    <Box className='w-full flex justify-center'>
                        <Box className='flex flex-col gap-3' sx={{width: '70%', marginTop: '2%' }}>
                            <NotificacaoComponente lido={false}/>
                            <NotificacaoComponente lido={false}/>
                            <NotificacaoComponente lido={false}/>
                            <NotificacaoComponente lido={true}/>
                            <NotificacaoComponente lido={true}/>
                            <NotificacaoComponente lido={true}/>
                            <NotificacaoComponente lido={true}/>
                            <NotificacaoComponente lido={true}/>
                        </Box>
                    </Box>
                </Box>
            </Box>
        </FundoComHeader>
    );

}

export default Notificacao