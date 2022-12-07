import React, { useState } from "react";

import {
  Button,
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Typography,
  MenuItem
} from "@mui/material";

import FontConfig from "../../service/FontConfig";

const ModalAceitarDemanda = (props) => {
  const tamanhos = ["1", "2", "3"];
  const [tamanho, setTamanho] = useState("");

  const handleChange = (event) => {
    setTamanho(event.target.value);
  };

  return (
    <Dialog open={props.open} onClose={props.handleClose}>
      <DialogTitle
        sx={{
          color: "text.primary",
          fontSize: FontConfig.veryBig,
          borderColor: "primary.main",
        }}
        className="border-t-8 border-solid"
      >
        Informações
      </DialogTitle>
      <DialogContent>
        <TextField
          select
          label="Tamanho"
          value={tamanho}
          onChange={handleChange}
          helperText="Por favor selecione o tamanho"
          variant="standard"
        >
          {tamanhos.map((option) => (
            <MenuItem key={option} value={option}>
              {option}
            </MenuItem>
          ))}
        </TextField>
      </DialogContent>
      <DialogActions>
        <Button onClick={props.handleClose}>Cancel</Button>
        <Button onClick={props.handleClose}>Subscribe</Button>
      </DialogActions>
    </Dialog>
  );
};

export default ModalAceitarDemanda;
