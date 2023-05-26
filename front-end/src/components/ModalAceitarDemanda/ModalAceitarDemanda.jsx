import React, { useState, useRef, useEffect, useContext } from "react";

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

import TextLanguageContext from "../../service/TextLanguageContext";
import FontContext from "../../service/FontContext";
import BuService from "../../service/buService";
import ForumService from "../../service/forumService";
import SecaoTIService from "../../service/secaoTIService";
import AnexoService from "../../service/anexoService";

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

  /** UseState para armazenar a lista de Seções de TI */
  const [listaSecoesTI, setListaSecoesTI] = useState([]);

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

  // UseEffect para carregar as listas de BUs, Seções de TI e Fóruns do banco de dados para serem usadas nos selects
  useEffect(() => {
    if (listaBus.length == 0) {
      BuService.getAll().then((response) => {
        setListaBus([...response]);
      });
    }

    if (listaSecoesTI.length == 0) {
      SecaoTIService.getAll().then((response) => {
        setListaSecoesTI([...response]);
      });
    }

    if (listaForum.length == 0) {
      ForumService.getAll().then((response) => {
        setListaForum([...response]);
      });
    }
  }, []);

  /** Função para acionar o input de arquivos ao clicar no botão de adicionar anexo */
  const adicionarAnexo = () => {
    inputFile.current.click();
  };

  /** Função para salvar um arquivo quando selecionado no input de arquivos */
  const salvarArquivo = () => {
    for (let arquivo of inputFile.current.files) {
      AnexoService.save(arquivo).then((response) => {
        setAnexos([...anexos, response]);
      });
    }
  };

  // Função que irá setar o texto que será "lido" pela a API
  const lerTexto = (texto) => {
    if (props.lendo) {
      props.setTexto(texto);
    }
  };

  // Função que irá "ouvir" o texto que será "lido" pela a API
  useEffect(() => {
    const synthesis = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(props.texto);
    if (props.lendo && props.texto != "" && countFala == 0) {
      if ("speechSynthesis" in window) {
        synthesis.speak(utterance);
      }
    } else if (!props.lendo) {
      if ("speechSynthesis" in window) {
        synthesis.cancel();
      }
    }
  }, [props.texto, props.lendo]);

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
        onClick={() => lerTexto(texts.modalAceitarDemanda.informacoes)}
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
          <MenuItem key={"Muito Pequeno"} value={"Muito Pequeno"}>
            {texts.modalAceitarDemanda.muitoPequeno}
          </MenuItem>
          <MenuItem key={"Pequeno"} value={"Pequeno"}>
            {texts.modalAceitarDemanda.pequeno}
          </MenuItem>
          <MenuItem key={"Médio"} value={"Médio"}>
            {texts.modalAceitarDemanda.medio}
          </MenuItem>
          <MenuItem key={"Grande"} value={"Grande"}>
            {texts.modalAceitarDemanda.grande}
          </MenuItem>
          <MenuItem key={"Muito Grande"} value={"Muito Grande"}>
            {texts.modalAceitarDemanda.muitoGrande}
          </MenuItem>
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
            <MenuItem key={option.idBu} value={option} title={option.nomeBu}>
              {option.siglaBu}
            </MenuItem>
          ))}
          {listaBus.length == 0 ? (
            <Typography
              sx={{
                color: "text.primary",
                fontSize: FontConfig.medium,
                marginLeft: "10px",
              }}
            >
              {texts.modalAceitarDemanda.nenhumaBuEncontrada}
            </Typography>
          ) : null}
        </TextField>

        {/* Select de BUs beneficiadas */}
        <Autocomplete
          multiple
          options={listaBus}
          disableCloseOnSelect
          onChange={(event, newValue) => {
            setBusBeneficiadas(newValue);
          }}
          getOptionLabel={(option) => option.siglaBu}
          renderOption={(props, option, { selected }) => (
            <li {...props} title={option.nomeBu}>
              <Checkbox
                icon={icon}
                checkedIcon={checkedIcon}
                style={{ marginRight: 8 }}
                checked={selected}
              />
              {option.siglaBu}
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
        <TextField
          select
          label={texts.modalAceitarDemanda.secaoTi}
          value={secaoTI}
          onChange={(event) => setSecaoTI(event.target.value)}
          fullWidth
          variant="standard"
        >
          {listaSecoesTI.map((option) => (
            <MenuItem
              key={option.idSecao}
              value={option}
              title={option.nomeSecao}
            >
              {option.siglaSecao}
            </MenuItem>
          ))}
          {listaSecoesTI.length == 0 ? (
            <Typography
              sx={{
                color: "text.primary",
                fontSize: FontConfig.medium,
                marginLeft: "10px",
              }}
            >
              {texts.modalAceitarDemanda.nenhumaSecaoEncontrada}
            </Typography>
          ) : null}
        </TextField>

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
            <MenuItem
              key={option.idForum}
              value={option}
              title={option.nomeForum}
            >
              {option.siglaForum}
            </MenuItem>
          ))}
          {listaForum.length == 0 ? (
            <Typography
              sx={{
                color: "text.primary",
                fontSize: FontConfig.medium,
                marginLeft: "10px",
              }}
            >
              {texts.modalAceitarDemanda.nenhumForumEncontrado}
            </Typography>
          ) : null}
        </TextField>

        {/* Input de anexos */}
        <Box className="flex w-full justify-between items-center">
          <Typography
            sx={{ color: "text.primary", fontSize: FontConfig.big }}
            onClick={() => {
              lerTexto(texts.modalAceitarDemanda.anexos);
            }}
          >
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
                  onClick={() => {
                    lerTexto(anexo.nome);
                  }}
                >
                  {anexo.nome}
                </Typography>
                <IconButton
                  onClick={() => {
                    setAnexos(anexos.filter((anexo, i) => i !== index));
                    AnexoService.deleteById(anexos[index].id).then(
                      (response) => {}
                    );
                  }}
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
            onClick={() => {
              lerTexto(texts.modalAceitarDemanda.nenhumAnexoAdicionado);
            }}
          >
            {texts.modalAceitarDemanda.nenhumAnexoAdicionado}
          </Typography>
        )}
      </DialogContent>

      {/* Botão para confirmar aceite */}
      <DialogActions>
        <Button
          onClick={() => {
            if (!props.lendo) {
              props.handleClose();
            } else {
              lerTexto(texts.modalAceitarDemanda.cancelar);
            }
          }}
        >
          {texts.modalAceitarDemanda.cancelar}
        </Button>
        <Button
          variant="contained"
          disableElevation
          onClick={() => {
            if (!props.lendo) {
              props.confirmAceitarDemanda({
                tamanho,
                buSolicitante,
                busBeneficiadas,
                secaoTI,
                anexos,
                forum,
              });
              props.handleClose();
            } else {
              lerTexto(texts.modalAceitarDemanda.aceitar);
            }
          }}
        >
          {texts.modalAceitarDemanda.aceitar}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default ModalAceitarDemanda;
