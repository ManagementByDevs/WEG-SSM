import React, { useState } from 'react'
import { Menu, MenuItem, Tooltip, IconButton } from '@mui/material/';

import Brasil from "../../assets/brasil.jpg";
import China from "../../assets/china.png";
import EstadosUnidos from "../../assets/estados-unidos.png";

const IdiomaModal = () => {
  // UseState para poder visualizar e alterar a imagem da linguagem selecionada (Valor padrão é Brasil)
  const [idioma, setIdioma] = useState(Brasil);

  // UseState para poder visualizar e alterar o menu do idioma
  const [anchorEl, setAnchorEl] = useState(null);

  // Variável que é usada para saber se o menu está aberto ou não
  const open = Boolean(anchorEl);

  // Função para abrir o menu
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  // Função para fechar o menu
  const handleClose = (src) => {
    setIdioma(src);
    setAnchorEl(null);
  };

  return (
    // Div container do idioma
    <div className=' border border-solid rounded-sm border-[#ffffff75]'>
      <Tooltip title="Idiomas">
        <IconButton
          onClick={handleClick}
          size="small"
          aria-haspopup="true"
          aria-expanded={open ? 'true' : undefined}

        >
          {/* Imagem do idioma selecionado no momento */}
          <img className='h-6 w-8' src={idioma} />
        </IconButton>
      </Tooltip>
      {/* Menu (modal) com as opções dos idiomas */}
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={() => handleClose(idioma)}
        MenuListProps={{
          'aria-labelledby': 'basic-button',
        }}
      >

        {/* Item de idioma */}
        <MenuItem className='gap-2' onClick={() => handleClose(EstadosUnidos)}>
          <img className='h-5 w-7' src={EstadosUnidos} />
          English
        </MenuItem>

        {/* Divisor entre um item de idioma e outro */}
        <div className='w-full flex justify-center'>
          <hr className='w-10/12' />
        </div>

        {/* Item de idioma */}
        <MenuItem className='gap-2' onClick={() => handleClose(Brasil)}>
          <img className='h-5 w-7' src={Brasil} />
          Português (Brasil)
        </MenuItem>

        {/* Divisor entre um item de idioma e outro */}
        <div className='w-full flex justify-center'>
          <hr className='w-10/12' />
        </div>

        {/* Item de idioma */}
        <MenuItem className='gap-2' onClick={() => handleClose(China)}>
          <img className='h-5 w-7' src={China} />
          中国人
        </MenuItem>
      </Menu>
    </div>
  )
}

export default IdiomaModal