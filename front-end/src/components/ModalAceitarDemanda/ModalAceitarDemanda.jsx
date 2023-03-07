import React, { useState, useRef, useEffect, useContext } from "react";
import { Button, TextField, Dialog, DialogActions, DialogContent, DialogTitle, Typography, MenuItem, Checkbox, Autocomplete, Box, IconButton } from "@mui/material";

import CheckBoxOutlineBlankIcon from "@mui/icons-material/CheckBoxOutlineBlank";
import CheckBoxIcon from "@mui/icons-material/CheckBox";
import CloseIcon from "@mui/icons-material/Close";
import AddIcon from "@mui/icons-material/Add";

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import BuService from "../../service/buService";
import ForumService from "../../service/forumService";

// Variável para armazenar os ícones do checkbox
const icon = <CheckBoxOutlineBlankIcon fontSize="small" />;
const checkedIcon = <CheckBoxIcon fontSize="small" />;

/** Modal de aceitar demanda na revisão inicial (analista), preenchendo informações adicionais */
const ModalAceitarDemanda = (props) => {

  // Context para alterar a linguagem do sistema
  const { texts, setTexts } = useContext(TextLanguageContext);

  // Context para alterar o tamanho da fonte
  const { FontConfig, setFontConfig } = useContext(FontContext);

  /** UseState para armazenar a lista de BUs */
  const [listaBus, setListaBus] = useState([]);

  /** Lista de seções de ti */
  const secoesTI = ["Seção 1", "Seção 2", "Seção 3"];

  /** UseState para armazenar a lista de fóruns */
  const [listaForum, setListaForum] = useState([]);

  /** Variável de referência do input de arquivos */
  const inputFile = useRef(null);

  /** Tamanho selecionado */
  const [tamanho, setTamanho] = useState("");

  /** BU solicitante selecionada */
  const [buSolicitante, setBuSolicitante] = useState("");

  /** Listas de BUs beneficiadas selecionadas */
  const [busBeneficiadas, setBusBeneficiadas] = useState([]);

  /** Seção TI selecionada */
  const [secaoTI, setSecaoTI] = useState("");

  /** Fórum selecionado */
  const [forum, setForum] = useState("");

  /** Lista de anexos adicionados */
  const [anexos, setAnexos] = useState([]);

  // UseEffect para carregar as listas de BUs e fóruns do banco de dados para serem usadas nos selects
  useEffect(() => {
    if (listaBus.length == 0) {
      BuService.getAll().then((response) => {
        setListaBus([...response]);
      })
    }

    if (listaForum.length == 0) {
      ForumService.getAll().then((response) => {
        setListaForum([...response]);
      })
    }
  }, []);

  /** Função para acionar o input de arquivos ao clicar no botão de adicionar anexo */
  const adicionarAnexo = () => {
    inputFile.current.click();
  };

  /** Função para salvar um arquivo quando selecionado no input de arquivos */
  const salvarArquivo = () => {
    setAnexos([...anexos, ...inputFile.current.files]);
  };

  return (
    <Dialog
      PaperProps={{ sx: { backgroundColor: "background.default", backgroundImage: "none" } }}
      open={props.open}
      onClose={props.handleClose}
    >
      <DialogTitle
        sx={{ color: "primary.main", fontSize: FontConfig.veryBig, borderColor: "primary.main", }}
        fontWeight="bold"
        className="border-t-8 border-solid text-center"
      >
        {texts.modalAceitarDemanda.informacoes}
      </DialogTitle>
      <DialogContent className="flex flex-col gap-4" sx={{ width: 500 }}>

        {/* Select de tamanho */}
        <TextField
          select
          label={texts.modalAceitarDemanda.tamanho}
          value={tamanho}
          onChange={(event) => setTamanho(event.target.value)}
          variant="standard"
          fullWidth
        >
          <MenuItem key={"Muito Pequeno"} value={"Muito Pequeno"}>{texts.modalAceitarDemanda.muitoPequeno}</MenuItem>
          <MenuItem key={"Pequeno"} value={"Pequeno"}>{texts.modalAceitarDemanda.pequeno}</MenuItem>
          <MenuItem key={"Médio"} value={"Médio"}>{texts.modalAceitarDemanda.medio}</MenuItem>
          <MenuItem key={"Grande"} value={"Grande"}>{texts.modalAceitarDemanda.grande}</MenuItem>
          <MenuItem key={"Muito Grande"} value={"Muito Grande"}>{texts.modalAceitarDemanda.muitoGrande}</MenuItem>
        </TextField>

        {/* Select de BU solicitante */}
        <TextField
          select
          label={texts.modalAceitarDemanda.buSolicitante}
          value={buSolicitante}
          onChange={(event) => setBuSolicitante(event.target.value)}
          variant="standard"
          fullWidth
        >
          {listaBus.map((option) => (
            <MenuItem key={option.id} value={option}>
              {option.nome}
            </MenuItem>
          ))}
          {listaBus.length == 0 ? (
            <Typography sx={{ color: "text.primary", fontSize: FontConfig.medium, marginLeft: '10px' }}>{texts.modalAceitarDemanda.nenhumaBuEncontrada}</Typography>
          ) : null}
        </TextField>

        {/* Select de BUs beneficiadas */}
        <Autocomplete
          multiple
          options={listaBus}
          disableCloseOnSelect
          onChange={(event, newValue) => { setBusBeneficiadas(newValue); }}
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
          noOptionsText={texts.modalAceitarDemanda.nenhumaBuEncontrada}
          fullWidth
          renderInput={(params) => (
            <TextField
              {...params}
              label={texts.modalAceitarDemanda.busBeneficiadas}
              variant="standard"
              placeholder={texts.modalAceitarDemanda.selecioneUmaOuMaisBus}
            />
          )}
        />

        {/* Select de Seções TI */}
        <Autocomplete
          disablePortal
          options={secoesTI}
          onChange={(event, newValue) => {
            setSecaoTI(newValue);
          }}
          fullWidth
          noOptionsText={texts.modalAceitarDemanda.nenhumaSecaoEncontrada}
          renderInput={(params) => (
            <TextField variant="standard" {...params} label={texts.modalAceitarDemanda.secaoTi} />
          )}
        />

        {/* Select de fóruns */}
        <TextField
          select
          label={texts.modalAceitarDemanda.forum}
          value={forum}
          onChange={(event) => setForum(event.target.value)}
          variant="standard"
          fullWidth
        >
          {listaForum.map((option) => (
            <MenuItem key={option.id} value={option}>
              {option.nome}
            </MenuItem>
          ))}
          {listaForum.length == 0 ? (
            <Typography sx={{ color: "text.primary", fontSize: FontConfig.medium, marginLeft: '10px' }}>{texts.modalAceitarDemanda.nenhumForumEncontrado}</Typography>
          ) : null}
        </TextField>

        {/* Input de anexos */}
        <Box className="flex w-full justify-between items-center">
          <Typography sx={{ color: "text.primary", fontSize: FontConfig.big }}>
            {texts.modalAceitarDemanda.anexos}
          </Typography>
          <IconButton onClick={adicionarAnexo}>
            <AddIcon />
          </IconButton>
          <input
            onChange={salvarArquivo}
            ref={inputFile}
            type="file"
            multiple
            hidden
          />
        </Box>

        {/* Lista de anexos */}
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
            {texts.modalAceitarDemanda.nenhumAnexoAdicionado}
          </Typography>
        )}
      </DialogContent>

      {/* Botão para confirmar aceite */}
      <DialogActions>
        <Button onClick={props.handleClose}>{texts.modalAceitarDemanda.cancelar}</Button>
        <Button
          variant="contained"
          disableElevation
          onClick={() => {
            props.confirmAceitarDemanda({ tamanho, buSolicitante, busBeneficiadas, secaoTI, anexos, forum });
            props.handleClose();
          }}
        >
          {texts.modalAceitarDemanda.aceitar}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default ModalAceitarDemanda;
