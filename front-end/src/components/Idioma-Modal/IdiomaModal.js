import React, { useState } from 'react'
import { Menu, MenuItem, Tooltip, IconButton } from '@mui/material/';

import "./idiomaModal.css"

import Brasil from "../../assets/brasil.jpg";
import China from "../../assets/china.png";
import EstadosUnidos from "../../assets/estados-unidos.png";

const IdiomaModal = () => {
  const [idioma, setIdioma] = useState(Brasil);
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = (src) => {
    setIdioma(src);
    setAnchorEl(null);
  };

  return (
    <div className=' border border-solid rounded-sm border-[#ffffff75]'>
      <Tooltip title="Idiomas">
        <IconButton
          onClick={handleClick}
          size="small"
          aria-haspopup="true"
          aria-expanded={open ? 'true' : undefined}

        >
          <img className='h-6 w-8' src={idioma} />
        </IconButton>
      </Tooltip>
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={() => handleClose(idioma)}
        MenuListProps={{
          'aria-labelledby': 'basic-button',
        }}
      >
        <MenuItem className='gap-2' onClick={() => handleClose(EstadosUnidos)}>
          <img className='bandeira-icone' src={EstadosUnidos} />
          English
        </MenuItem>
        <div className='w-full flex justify-center'>
          <hr className='w-10/12' />
        </div>
        <MenuItem className='gap-2' onClick={() => handleClose(Brasil)}>
          <img className='bandeira-icone' src={Brasil} />
          Português (Brasil)
        </MenuItem>
        <div className='w-full flex justify-center'>
          <hr className='w-10/12' />
        </div>
        <MenuItem className='gap-2' onClick={() => handleClose(China)}>
          <img className='bandeira-icone' src={China} />
          中国人
        </MenuItem>
      </Menu>
    </div>
  )
}

export default IdiomaModal