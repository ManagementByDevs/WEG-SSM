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
  label = "",
}) => {
  // Context para alterar o tamanho da fonte
  const { FontConfig } = useContext(FontContext);

  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para obter a função de leitura de texto
  const { startRecognition, escutar, localClique, palavrasJuntas } = useContext(
    SpeechRecognitionContext
  );

  const [text, setText] = useState(JSON.parse(JSON.stringify(defaultText)));

  useEffect(() => {
    if (localClique == label) {
      setText(palavrasJuntas);
    }
  }, [palavrasJuntas]);

  return (
    <Input
      size="small"
      value={text}
      onChange={(e) => setText(e.target.value)}
      onBlur={() => saveProposal(text)}
      type="text"
      fullWidth
      sx={{ color: "primary.main", fontSize: FontConfig.smallTitle }}
      multiline
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
