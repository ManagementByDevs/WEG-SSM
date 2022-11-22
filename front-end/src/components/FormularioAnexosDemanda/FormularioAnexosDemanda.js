import React, { useRef, useState } from 'react'

import { Box, Button, Typography } from '@mui/material'

import FontConfig from '../../service/FontConfig'

const FormularioAnexosDemanda = () => {
    const dragArea = useRef(null);
    const inputFile = useRef(null);
    const [fileList, setFileList] = useState([]);
    const [mapAbleFileList, setMapAbleFileList] = useState([]);
    const [dragText, setDragText] = useState('Arraste & Solte para Adicionar um Arquivo');

    const fileClick = () => {
        inputFile.current.click();
    }
    const onFilesSelect = () => {
        setFileList(fileList.push.apply(fileList, inputFile.current.files));
        setMapAbleFileList(Array.from(fileList));
        console.log(fileList[0].name);
        for (let file of fileList) {
            console.log("1", file.name);
        }
    }

    const drag = (event) => {
        event.preventDefault();
        setDragText('Solte para Adicionar um Arquivo');
    }

    const onDragLeaveHandle = (event) => {
        event.preventDefault();
        setDragText('Arraste & Solte para Adicionar um Arquivo');
    }

    const onDropFile = (event) => {
        console.log("entreou on drop file")
        event.preventDefault();
        event.stopPropagation();
        let files = event.dataTransfer.files;
        console.log("files: ", files)
        let fileArrayAux = Array.from(files);
        console.log("fileArrayAux: ", fileArrayAux)
        setFileList(fileList.push.apply(fileList, fileArrayAux));
        console.log("filelist: ", fileList)
        setMapAbleFileList(Array.from(fileList));
    }

    return (
        <Box className='flex justify-center items-center' sx={{ height: '45rem' }}>
            <Box ref={dragArea} onDragOver={drag} onDragLeave={onDragLeaveHandle} onDrop={onDropFile} className='flex justify-center items-center flex-col rounded border-2' sx={{ width: '85%', height: '85%' }}>
                {
                    fileList.length === 0 ?
                        <>
                            <Typography fontSize={FontConfig.veryBig} color='text.secondary' sx={{ fontWeight: '600', cursor: 'default', marginBottom: '1rem' }}>{dragText}</Typography>
                            <Typography fontSize={FontConfig.veryBig} color='text.secondary' sx={{ fontWeight: '600', cursor: 'default', marginBottom: '1rem' }}>OU</Typography>
                            <Button onClick={fileClick} variant='contained' disableElevation>Pesquisar Arquivos</Button>
                            <input onChange={onFilesSelect} ref={inputFile} type='file' multiple hidden />
                        </>
                        :
                        mapAbleFileList.map((file, index) => {
                            return (
                                <Typography key={index} fontSize={FontConfig.veryBig} color='text.secondary' sx={{ fontWeight: '600', cursor: 'default', marginBottom: '1rem' }}>{file.name}</Typography>
                            )
                        })
                }

            </Box>
        </Box>
    )
}

export default FormularioAnexosDemanda