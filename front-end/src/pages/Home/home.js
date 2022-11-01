import React from 'react'
import Button from '@mui/material/Button';

import { ColorModeContext } from '../../App';

// const ColorModeContext = React.createContext({ toggleColorMode: () => { } });

function home() {
  const colorMode = React.useContext(ColorModeContext);
  return (
    <div>
      <Button variant="contained" onClick={colorMode.toggleColorMode}>Contained</Button>
      {/* <Button variant="contained" >Contained</Button> */}
      home
    </div>
  )
}

export default home