import React from "react";
import "./Beneficios.css";

import { Box, Select, FormControl, InputLabel, MenuItem } from '@mui/material'
import FontConfig from "../../service/FontConfig";
import InputComLabel from '../InputComLabel/InputComLabel';

import DeleteOutlineOutlinedIcon from '@mui/icons-material/DeleteOutlineOutlined';
import { margin, positions } from "@mui/system";

const Beneficios = () => {
    const [beneficio, setBeneficio] = React.useState('');
    const [moeda, setMoeda] = React.useState('');

    const handleChange = (event) => {
        setBeneficio(event.target.value);
    };

    const handleChangeMoeda = (event) => {
        setMoeda(event.target.value);
    };

    return (
        <Box className='flex rounded h-40 border-2 drop-shadow-md' sx={{ backgroundColor: 'background.default', padding: '1%', position:'relative', minWidth: '725px'}}>
            <Box className="flex flex-col" sx={{ width: '30%', marginTop:'1%' }}>
                <Box className="" sx={{ width: '100%', margin: '1%' }}>
                    <FormControl sx={{ width: '68%', height: '35%' }}>
                        <InputLabel id="demo-simple-select-label" sx={{ margin: '-2px 0 0 0' }}>Benefícios</InputLabel>
                        <Select
                            labelId="demo-simple-select-label"
                            id="demo-simple-select"
                            value={beneficio}
                            onChange={handleChange}
                            label="Beneficio"
                        >
                            <MenuItem value={"Real"}>Real</MenuItem>
                            <MenuItem value={"Potencial"}>Potencial</MenuItem>
                            <MenuItem value={"Qualitativo"}>Qualitativo</MenuItem>
                        </Select>
                    </FormControl>
                </Box>
                {
                    beneficio === "Real" || beneficio === "Potencial" ?
                        <Box className="flex items-end" sx={{minWidth: '275px'}}>
                            <Box className="flex items-end" sx={{ width: '92%', margin: '3% 1% 1% 1%' }}>
                                <Box className="flex items-end" sx={{ width: '100%' }}>
                                    <Box sx={{ width: '40%'}}>
                                        <InputComLabel component='input' label="Valor Mensal:" placeholder='Ex: 1000,00' fontConfig={FontConfig.default} />
                                    </Box>
                                    <FormControl variant="filled" sx={{ margin: '0 0 0 10px', minWidth: '95px' }}>
                                        <InputLabel id="demo-simple-select-label" sx={{ margin: '-10px 0 0 0' }}>Moeda</InputLabel>
                                        <Select
                                            labelId="demo-simple-select-filled-label"
                                            id="demo-simple-select-filled"
                                            value={moeda}
                                            onChange={handleChangeMoeda}
                                        >
                                            <MenuItem value={"Real"}>BR</MenuItem>
                                            <MenuItem value={"Dolar"}>UR</MenuItem>
                                            <MenuItem value={"Euro"}>EUR</MenuItem>
                                        </Select>
                                    </FormControl>
                                </Box>
                            </Box>
                        </Box>
                        : null
                }
            </Box>
            {
                beneficio === "Real" || beneficio === "Potencial" || beneficio === "Qualitativo"?
                    <Box className="flex items-end" sx={{ width: '65%'}}>
                        <InputComLabel component='textarea' label="Memória de cálculo:" placeholder='Digite a memória de cálculo...' fontConfig={FontConfig.default} rows="4" sx={{width:'100%'}}/>
                    </Box>
                    : null
            }
            <Box className="flex flex-col justify-end items-end" sx={{position: 'absolute', bottom: '5px', right: '5px'}}>
                <DeleteOutlineOutlinedIcon className="delay-120 hover:scale-110 duration-300" sx={{ color: 'primary.main', cursor: 'pointer', fontSize: '30px' }} />
            </Box>
        </Box>
    )
}

export default Beneficios