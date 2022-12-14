import React, { useState, useEffect } from "react";
import "./Beneficios.css";

import { Box, Select, FormControl, InputLabel, MenuItem } from '@mui/material'
import FontConfig from "../../service/FontConfig";
import InputComLabel from '../InputComLabel/InputComLabel';

import DeleteOutlineOutlinedIcon from '@mui/icons-material/DeleteOutlineOutlined';

const Beneficios = (props) => {

    const [dadosBeneficio, setDadosBeneficio] = useState({ id: props.dados.id, tipoBeneficio: props.dados.tipoBeneficio, valor_mensal: props.dados.valor_mensal, moeda: props.dados.moeda, memoriaCalculo: props.dados.memoriaCalculo, visible: true });

    const handleChange = (event) => {
        setDadosBeneficio({ ...dadosBeneficio, tipoBeneficio: event.target.value });
    };

    const handleChangeValor = (event) => {
        setDadosBeneficio({ ...dadosBeneficio, valor_mensal: event.target.value });
    };

    const handleChangeMoeda = (event) => {
        setDadosBeneficio({ ...dadosBeneficio, moeda: event.target.value });
    };

    const handleChangeMemoriaCalculo = (event) => {
        setDadosBeneficio({ ...dadosBeneficio, memoriaCalculo: event.target.value });
    };

    useEffect(() => {
        props.save(dadosBeneficio);
    }, [dadosBeneficio]);

    function salvarValorMensal(texto) {
        setDadosBeneficio({ ...dadosBeneficio, valor_mensal: texto });
    }

    function salvarMemoriaCalculo(texto) {
        setDadosBeneficio({ ...dadosBeneficio, memoriaCalculo: texto });
    }

    function removerComponente() {
        props.removerBeneficio(props.index);
    }

    const getSelectedTipo = (tipo) => {
        return tipo === dadosBeneficio.tipoBeneficio;
    }

    return (
        <Box className='flex rounded h-40 border-2 drop-shadow-md' sx={{ backgroundColor: 'background.default', padding: '1%', position: 'relative', minWidth: '725px' }}>
            <Box className="flex flex-col" sx={{ width: '30%', marginTop: '1%' }}>
                <Box className="" sx={{ width: '100%', margin: '1%' }}>
                    <FormControl sx={{ width: '68%', height: '35%' }}>
                        <InputLabel id="demo-simple-select-label" sx={{ margin: '-2px 0 0 0' }}>Benef??cios</InputLabel>
                        <Select
                            labelId="demo-simple-select-label"
                            id="demo-simple-select"
                            value={dadosBeneficio.tipoBeneficio}
                            onChange={handleChange}
                            label="Beneficio"
                            defaultValue={dadosBeneficio.tipoBeneficio}
                        >
                            <MenuItem value={"Real"}>Real</MenuItem>
                            <MenuItem value={"Potencial"}>Potencial</MenuItem>
                            <MenuItem value={"Qualitativo"}>Qualitativo</MenuItem>
                        </Select>
                    </FormControl>
                </Box>
                {
                    dadosBeneficio.tipoBeneficio === "Real" || dadosBeneficio.tipoBeneficio === "Potencial" ?
                        <Box className="flex items-end" sx={{ minWidth: '275px' }}>
                            <Box className="flex items-end" sx={{ width: '92%', margin: '3% 1% 1% 1%' }}>
                                <Box className="flex items-end" sx={{ width: '100%' }}>
                                    <Box sx={{ width: '40%' }}>
                                        <InputComLabel saveInputValue={salvarValorMensal} component='input' label="Valor Mensal:" placeholder='Ex: 1000,00' fontConfig={FontConfig.default} texto={dadosBeneficio.valor_mensal} onChange={handleChangeValor} />
                                    </Box>
                                    <FormControl variant="filled" sx={{ margin: '0 0 0 10px', minWidth: '95px' }}>
                                        <InputLabel id="demo-simple-select-label" sx={{ margin: '-10px 0 0 0' }}>Moeda</InputLabel>
                                        <Select
                                            labelId="demo-simple-select-filled-label"
                                            id="demo-simple-select-filled"
                                            value={dadosBeneficio.moeda}
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
                dadosBeneficio.tipoBeneficio === "Real" || dadosBeneficio.tipoBeneficio === "Potencial" || dadosBeneficio.tipoBeneficio === "Qualitativo" ?
                    <Box className="flex items-end" sx={{ width: '65%' }}>
                        <InputComLabel saveInputValue={salvarMemoriaCalculo} component='textarea' label="Mem??ria de c??lculo:" placeholder='Digite a mem??ria de c??lculo...' fontConfig={FontConfig.default} rows="4" sx={{ width: '100%' }} texto={dadosBeneficio.memoriaCalculo} onChange={handleChangeMemoriaCalculo} />
                    </Box>
                    : null
            }
            <Box className="flex flex-col justify-end items-end" sx={{ position: 'absolute', bottom: '5px', right: '5px' }}>
                <DeleteOutlineOutlinedIcon className="delay-120 hover:scale-110 duration-300" sx={{ color: 'icon.main', cursor: 'pointer', fontSize: '30px' }} onClick={removerComponente} />
            </Box>
        </Box>
    )
}

export default Beneficios