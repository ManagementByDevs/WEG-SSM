import React, { useState } from 'react'
import { Menu, MenuItem, Tooltip, IconButton, Divider } from '@mui/material/';

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
    <div className='idioma-container'>
      <Tooltip title="Idiomas">
        <IconButton
          onClick={handleClick}
          size="small"
          aria-haspopup="true"
          aria-expanded={open ? 'true' : undefined}

        >
          <img className='bandeira' src={idioma} />
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
        <MenuItem className='item-idioma-modal' onClick={() => handleClose(EstadosUnidos)}>
          <img className='bandeira-icone' src={EstadosUnidos} />
          English
        </MenuItem>
        <Divider />
        <MenuItem className='item-idioma-modal' onClick={() => handleClose(Brasil)}>
          <img className='bandeira-icone' src={Brasil} />
          Português (Brasil)
        </MenuItem>
        <Divider />
        <MenuItem className='item-idioma-modal' onClick={() => handleClose(China)}>
          <img className='bandeira-icone' src={China} />
          中国人
        </MenuItem>
      </Menu>
    </div>
  )
}

export default IdiomaModal