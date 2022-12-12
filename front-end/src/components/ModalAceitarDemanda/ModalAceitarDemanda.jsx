import React, { useState, useRef } from "react";

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

const icon = <CheckBoxOutlineBlankIcon fontSize="small" />;
const checkedIcon = <CheckBoxIcon fontSize="small" />;

const ModalAceitarDemanda = (props) => {
  const tamanhos = ["1", "2", "3"];
  const buSolicitantes = ["1", "2", "3"];
  const bus = [
    "BU1",
    "BU2",
    "BU3",
    "BU4",
    "BU5",
    "BU6",
    "BU7",
    "BU8",
    "BU9",
    "BU10",
    "BU11",
    "BU12",
    "BU13",
    "BU14",
    "BU15",
    "BU16",
    "BU17",
    "BU18",
    "BU19",
    "BU20",
    "BU21",
    "BU22",
    "BU23",
    "BU24",
    "BU25",
    "BU26",
    "BU27",
    "BU28",
    "BU29",
    "BU30",
    "BU31",
    "BU32",
    "BU33",
    "BU34",
    "BU35",
    "BU36",
    "BU37",
    "BU38",
    "BU39",
    "BU40",
    "BU41",
    "BU42",
    "BU43",
    "BU44",
    "BU45",
    "BU46",
    "BU47",
    "BU48",
    "BU49",
    "BU50",
    "BU51",
    "BU52",
    "BU53",
    "BU54",
    "BU55",
    "BU56",
    "BU57",
    "BU58",
    "BU59",
    "BU60",
    "BU61",
    "BU62",
    "BU63",
    "BU64",
    "BU65",
    "BU66",
    "BU67",
    "BU68",
    "BU69",
    "BU70",
    "BU71",
    "BU72",
    "BU73",
    "BU74",
    "BU75",
    "BU76",
    "BU77",
    "BU78",
    "BU79",
    "BU80",
    "BU81",
    "BU82",
    "BU83",
    "BU84",
    "BU85",
    "BU86",
    "BU87",
    "BU88",
    "BU89",
    "BU90",
    "BU91",
    "BU92",
    "BU93",
  ];
  const secoesTI = ["Seção 1", "Seção 2", "Seção 3"];

  const inputFile = useRef(null);

  const [tamanho, setTamanho] = useState("");
  const [buSolicitante, setBuSolicitante] = useState("");
  const [busBeneficiadas, setBusBeneficiadas] = useState([]);
  const [secaoTI, setSecaoTI] = useState("");
  const [anexos, setAnexos] = useState([]);

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
          {buSolicitantes.map((option) => (
            <MenuItem key={option} value={option}>
              {option}
            </MenuItem>
          ))}
        </TextField>

        <Autocomplete
          multiple
          options={bus}
          disableCloseOnSelect
          onChange={(event, newValue) => {
            setBusBeneficiadas(newValue);
          }}
          getOptionLabel={(option) => option}
          renderOption={(props, option, { selected }) => (
            <li {...props}>
              <Checkbox
                icon={icon}
                checkedIcon={checkedIcon}
                style={{ marginRight: 8 }}
                checked={selected}
              />
              {option}
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
