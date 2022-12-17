import React, { useState, useRef, useEffect } from "react";

import {
  Button,
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Typography,
  MenuItem,
  Checkbox,
  Autocomplete,
  Box,
  IconButton,
} from "@mui/material";

import CheckBoxOutlineBlankIcon from "@mui/icons-material/CheckBoxOutlineBlank";
import CheckBoxIcon from "@mui/icons-material/CheckBox";
import CloseIcon from "@mui/icons-material/Close";
import AddIcon from "@mui/icons-material/Add";

import FontConfig from "../../service/FontConfig";
import BuService from "../../service/buService";

const icon = <CheckBoxOutlineBlankIcon fontSize="small" />;
const checkedIcon = <CheckBoxIcon fontSize="small" />;

const ModalAceitarDemanda = (props) => {
  const tamanhos = ["Muito Pequeno", "Pequeno", "Médio", "Grande", "Muito Grande"];
  const [listaBus, setListaBus] = useState([]);
  const secoesTI = ["Seção 1", "Seção 2", "Seção 3"];

  const inputFile = useRef(null);

  const [tamanho, setTamanho] = useState("");
  const [buSolicitante, setBuSolicitante] = useState("");
  const [busBeneficiadas, setBusBeneficiadas] = useState([]);
  const [secaoTI, setSecaoTI] = useState("");
  const [anexos, setAnexos] = useState([]);

  useEffect(() => {
    if (listaBus.length == 0) {
      BuService.getAll().then((response) => {
        setListaBus([...response]);
      })
    }
  }, []);

  const onFilesSelect = () => {
    for (let file of inputFile.current.files) {
      setAnexos([...anexos, file]);
    }
  };

  const onAddFileButtonClick = () => {
    inputFile.current.click();
  };

  return (
    <Dialog
      PaperProps={{
        sx: { backgroundColor: "background.default", backgroundImage: "none" },
      }}
      open={props.open}
      onClose={props.handleClose}
    >
      <DialogTitle
        sx={{
          color: "primary.main",
          fontSize: FontConfig.veryBig,
          borderColor: "primary.main",
        }}
        fontWeight="bold"
        className="border-t-8 border-solid text-center"
      >
        Informações
      </DialogTitle>
      <DialogContent className="flex flex-col gap-4" sx={{ width: 500 }}>
        <TextField
          select
          label="Tamanho"
          value={tamanho}
          onChange={(event) => setTamanho(event.target.value)}
          variant="standard"
          fullWidth
        >
          {tamanhos.map((option) => (
            <MenuItem key={option} value={option}>
              {option}
            </MenuItem>
          ))}
        </TextField>

        <TextField
          select
          label="BU Solicitante"
          value={buSolicitante}
          onChange={(event) => setBuSolicitante(event.target.value)}
          variant="standard"
          fullWidth
        >
          {listaBus.map((option) => (
            <MenuItem key={option.id} value={option.id}>
              {option.nome}
            </MenuItem>
          ))}
          {listaBus.length == 0 ? (
            <Typography sx={{ color: "text.primary", fontSize: FontConfig.medium, marginLeft: '10px' }}>Nenhuma BU encontrada</Typography>
          ) : null}
        </TextField>

        <Autocomplete
          multiple
          options={listaBus}
          disableCloseOnSelect
          onChange={(event, newValue) => {
            setBusBeneficiadas(newValue);
          }}
          getOptionLabel={(option) => option.nome}
          renderOption={(props, option, { selected }) => (
            <li {...props}>
              <Checkbox
                icon={icon}
                checkedIcon={checkedIcon}
                style={{ marginRight: 8 }}
                checked={selected}
              />
              {option.nome}
            </li>
          )}
          noOptionsText="Nenhuma BU encontrada"
          fullWidth
          renderInput={(params) => (
            <TextField
              {...params}
              label="BUs Beneficiadas"
              variant="standard"
              placeholder="Selecione uma ou mais BUs"
            />
          )}
        />

        <Autocomplete
          disablePortal
          options={secoesTI}
          onChange={(event, newValue) => {
            setSecaoTI(newValue);
          }}
          fullWidth
          renderInput={(params) => (
            <TextField variant="standard" {...params} label="Seção TI" />
          )}
        />

        <Box className="flex w-full justify-between items-center">
          <Typography sx={{ color: "text.primary", fontSize: FontConfig.big }}>
            Anexos
          </Typography>
          <IconButton onClick={onAddFileButtonClick}>
            <AddIcon />
          </IconButton>
          <input
            onChange={onFilesSelect}
            ref={inputFile}
            type="file"
            multiple
            hidden
          />
        </Box>

        {anexos.length > 0 ? (
          <Box className="flex flex-col gap-2">
            {anexos.map((anexo, index) => (
              <Box key={index} className="flex justify-between items-center">
                <Typography
                  sx={{ color: "text.primary", fontSize: FontConfig.default }}
                >
                  {anexo.name}
                </Typography>
                <IconButton
                  onClick={() =>
                    setAnexos(anexos.filter((anexo, i) => i !== index))
                  }
                >
                  <CloseIcon sx={{ color: "text.primary" }} />
                </IconButton>
              </Box>
            ))}
          </Box>
        ) : (
          <Typography
            textAlign="center"
            sx={{ color: "text.primary", fontSize: FontConfig.default }}
          >
            Nenhum anexo adicionado
          </Typography>
        )}
      </DialogContent>
      <DialogActions>
        <Button onClick={props.handleClose}>Cancelar</Button>
        <Button
          variant="contained"
          disableElevation
          onClick={() => {
            props.confirmAceitarDemanda({
              tamanho,
              buSolicitante,
              busBeneficiadas,
              secaoTI,
              anexos,
            });
            props.handleClose();
          }}
        >
          Aceitar
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default ModalAceitarDemanda;
