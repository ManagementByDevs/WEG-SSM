import React from "react";

import { Box, Select, FormControl, InputLabel, MenuItem } from '@mui/material'
import FontConfig from '../../service/FontConfig'

const Beneficios = () => {

let age

    return (
        <Box className='flex rounded h-40' sx={{ backgroundColor: 'background.default', border: '1px solid', borderColor: 'tertiary.main' }}>
            <Box className="" sx={{width: '8rem'}}>
                <FormControl fullWidth>
                    <InputLabel id="demo-simple-select-label">Age</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={age}
                        label="Age"
                    >
                        <MenuItem value={10}>Ten</MenuItem>
                        <MenuItem value={20}>Twenty</MenuItem>
                        <MenuItem value={30}>Thirty</MenuItem>
                    </Select>
                </FormControl>
            </Box>
        </Box>
    )
}

export default Beneficios