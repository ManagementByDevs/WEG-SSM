import React, { useRef, useState, useEffect } from 'react'

import { Box, Button, Typography, IconButton } from '@mui/material'

import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';

import DeleteOutlineOutlinedIcon from '@mui/icons-material/DeleteOutlineOutlined';
import AddCircleIcon from '@mui/icons-material/AddCircle';

import FontConfig from '../../service/FontConfig'

const FormularioAnexosDemanda = (props) => {
    const dragArea = useRef(null);
    const inputFile = useRef(null);
    const [mapAbleFileList, setMapAbleFileList] = useState([]);
    const [dragText, setDragText] = useState('Arraste & Solte para Adicionar um Arquivo');

    const fileClick = () => {
        inputFile.current.click();
    }

    const onFilesSelect = () => {
        for (let file of inputFile.current.files) {
            props.setDados([...props.dados, file]);
        }
    }

    useEffect(() => {
        setMapAbleFileList(Array.from(props.dados));
    }, [props.dados]);

    const drag = (event) => {
        event.preventDefault();
        setDragText('Solte para Adicionar um Arquivo');
    }

    const onDragLeaveHandle = (event) => {
        event.preventDefault();
        setDragText('Arraste & Solte para Adicionar um Arquivo');
    }

    const onDropFile = (event) => {
        event.preventDefault();
        event.stopPropagation();
        let files = event.dataTransfer.files;
        let fileArrayAux = Array.from(files);
        for (let file of fileArrayAux) {
            props.setDados([...props.dados, file]);
        }
    }

    const deleteFile = (desiredIndex) => {
        setMapAbleFileList(mapAbleFileList.filter((_, index) => index !== desiredIndex));
        props.setDados(props.dados.filter((_, index) => index !== desiredIndex));
    }

    return (
        <Box className='flex justify-center items-center' sx={{ height: '45rem' }}>
            <Box ref={dragArea} onDragOver={drag} onDragLeave={onDragLeaveHandle} onDrop={onDropFile} className='flex justify-center items-center flex-col rounded border-2' sx={{ width: '85%', height: '85%' }}>
                <input onChange={onFilesSelect} ref={inputFile} type='file' multiple hidden />
                {
                    props.dados?.length === 0 ?
                        <>
                            <Typography fontSize={FontConfig.veryBig} color='text.secondary' sx={{ fontWeight: '600', cursor: 'default', marginBottom: '1rem' }}>{dragText}</Typography>
                            <Typography fontSize={FontConfig.veryBig} color='text.secondary' sx={{ fontWeight: '600', cursor: 'default', marginBottom: '1rem' }}>OU</Typography>
                            <Button onClick={fileClick} variant='contained' disableElevation>Pesquisar Arquivos</Button>
                        </>
                        :
                        <Box className='w-full h-full px-2 py-4 relative'>
                            <Box className='absolute bottom-2 right-2'>
                                <IconButton onClick={fileClick} aria-label="adicionar">
                                    <AddCircleIcon color='primary' fontSize='large' />
                                </IconButton>
                            </Box>
                            <TableContainer >
                                <Table sx={{ minWidth: 650 }} aria-label="simple table">
                                    <TableHead className='border-b'>
                                        <TableRow>
                                            <th>
                                                <Typography fontSize={FontConfig.big} color='text.primary' sx={{ fontWeight: '700', cursor: 'default' }}>
                                                    Nome
                                                </Typography>
                                            </th>
                                            <th>
                                                <Typography fontSize={FontConfig.big} color='text.primary' sx={{ fontWeight: '700', cursor: 'default' }}>
                                                    Tipo
                                                </Typography>
                                            </th>
                                            <th>
                                                <Typography fontSize={FontConfig.big} color='text.primary' sx={{ fontWeight: '700', cursor: 'default' }}>
                                                    Remover
                                                </Typography>
                                            </th>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {mapAbleFileList?.map((file, index) => {
                                            return (
                                                <TableRow key={index} className="border-b">
                                                    <td className='text-center'>
                                                        <Typography fontSize={FontConfig.medium} color='text.secondary' sx={{ fontWeight: '500', cursor: 'default' }}>{file.name}</Typography>
                                                    </td>
                                                    <td className='text-center'>
                                                        <Typography fontSize={FontConfig.medium} color='text.secondary' sx={{ fontWeight: '500', cursor: 'default' }}>{file.type}</Typography>
                                                    </td>
                                                    <td className='text-center'>
                                                        <IconButton onClick={() => deleteFile(index)}>
                                                            <DeleteOutlineOutlinedIcon color='primary' />
                                                        </IconButton>
                                                    </td>
                                                </TableRow>
                                            )
                                        })}
                                    </TableBody>
                                </Table>
                            </TableContainer>
                        </Box>
                }
            </Box>
        </Box >
    )
}

export default FormularioAnexosDemanda