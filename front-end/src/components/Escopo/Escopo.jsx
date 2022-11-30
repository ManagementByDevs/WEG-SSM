import React from 'react'

import { Box, Paper, Typography } from '@mui/material'

import DeleteIcon from '@mui/icons-material/Delete';

import FontConfig from '../../service/FontConfig';

const Escopo = (props) => {
    return (
        <Paper className='flex flex-col gap-1 border-t-4 py-2 px-6' sx={{ borderColor: "primary.main" }}>
            {/* Container titulo e progressao */}
            <Box className='flex w-full gap-4 items-center'>
                <Typography className='w-3/4 overflow-hidden text-ellipsis whitespace-nowrap' fontSize={FontConfig.veryBig} fontWeight="600">Título do escopo que irá se tornar uma demanda posteriormente</Typography>

                {/* Progressão do escopo */}
                <Box className='flex w-1/4'>
                    <Box className='w-11/12 flex' sx={{ backgroundColor: "divider.main" }}>
                        <Box>f</Box>
                    </Box>
                    <Box className='w-full'>{props.porcentagem}</Box>
                </Box>
            </Box>

            {/* Proposta (dados do escopo) */}
            <Box className='relative'>
                <Box className='h-16'>
                    <Typography className='w-11/12 h-full overflow-hidden text-ellipsis whitespace-pre-wrap' fontSize={FontConfig.default}>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Repellat expedita nobis praesentium enim odit officiis voluptatem. Possimus odio quo consequatur et totam! Obcaecati neque, tempora sit est rerum molestias ex. Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex voluptas nostrum hic dolores? Nostrum est totam quidem quod. Eaque consequatur iusto iure illo laboriosam quibusdam corrupti, ex necessitatibus sit perferendis?</Typography>
                </Box>
                <DeleteIcon className='absolute bottom-0' sx={{ right: "-10px" }} color='primary' />
            </Box>
        </Paper>
    )
}

export default Escopo