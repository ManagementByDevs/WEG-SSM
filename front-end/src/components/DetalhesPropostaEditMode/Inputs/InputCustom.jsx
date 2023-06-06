import React, { useContext, useState } from "react";

import { Input, InputAdornment, Tooltip } from "@mui/material";

import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import FontContext from "../../../service/FontContext";
import TextLanguageContext from "../../../service/TextLanguageContext";
import { SpeechRecognitionContext } from "../../../service/SpeechRecognitionService";
import { useEffect } from "react";

const InputCustom = ({
  defaultText = "",
  saveProposal = () => {},
  sx = {},
  label = "",
  multiline = false,
  regex = new RegExp(),
  handleOnMicChange = () => {},
}) => {
  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  /** Context para obter a função de leitura de texto */
  const { startRecognition, escutar, localClique, palavrasJuntas } = useContext(
    SpeechRecognitionContext
  );

  const [text, setText] = useState(JSON.parse(JSON.stringify(defaultText)));

  const validateText = (text = "") => {
    return regex.test(text);
  };

  const handleOnTextChange = (e) => {
    if (!validateText(e.target.value)) {
      return;
    }

    setText(e.target.value);
  };

  useEffect(() => {
    if (localClique == label) {
      console.log(palavrasJuntas);
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
      onBlur={() => saveProposal(text)}
      type="text"
      fullWidth
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
