import React, { useContext, useEffect, useState } from "react";

import { Input, InputAdornment, Tooltip } from "@mui/material";

import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import TextLanguageContext from "../../../service/TextLanguageContext";
import { SpeechRecognitionContext } from "../../../service/SpeechRecognitionService";

const InputCustom = ({
  defaultText = "",
  saveText = () => {},
  sx = {},
  label = "",
  multiline = false,
  regex = new RegExp(),
  handleOnMicChange = () => {},
  fullWidth = false,
}) => {
  /** Context para obter os textos do sistema */
  const { texts } = useContext(TextLanguageContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, localClique, palavrasJuntas } = useContext(
    SpeechRecognitionContext
  );

  /** Texto do input */
  const [text, setText] = useState(JSON.parse(JSON.stringify(defaultText)));

  /** Valida o texto de acordo com a regex */
  const validateText = (text = "") => {
    return regex.test(text);
  };

  /** Atualiza o texto do input validando o texto */
  const handleOnTextChange = (e) => {
    if (!validateText(e.target.value)) {
      return;
    }

    setText(e.target.value);
  };

  /** UseEffect para quando É juntado palavras no mic */
  useEffect(() => {
    if (localClique == label) {
      if (validateText(palavrasJuntas)) {
        setText(palavrasJuntas);
        handleOnMicChange();
      }
    }
  }, [palavrasJuntas]);

  return (
    <Input
      size="small"
      value={text}
      onChange={handleOnTextChange}
      onBlur={() => saveText(text)}
      type="text"
      fullWidth={fullWidth}
      sx={sx}
      multiline={multiline}
      endAdornment={
        <InputAdornment position="end">
          <Tooltip
            className="flex items-center hover:cursor-pointer"
            title={texts.homeGerencia.gravarAudio}
            onClick={() => {
              startRecognition(label);
            }}
          >
            {escutar && localClique == label ? (
              <MicOutlinedIcon color="primary" />
            ) : (
              <MicNoneOutlinedIcon />
            )}
          </Tooltip>
        </InputAdornment>
      }
    />
  );
};

export default InputCustom;
