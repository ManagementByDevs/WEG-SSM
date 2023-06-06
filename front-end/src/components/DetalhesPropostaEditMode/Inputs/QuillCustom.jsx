import React, { useContext, useEffect, useRef, useState } from "react";

import ReactQuill from "react-quill";

import { Box, Tooltip } from "@mui/material";

import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";

import TextLanguageContext from "../../../service/TextLanguageContext";
import { SpeechRecognitionContext } from "../../../service/SpeechRecognitionService";

const QuillCustom = ({
  defaultText = "",
  saveProposal = () => {},
  label = "",
  modules = {},
  handleOnMicChange = () => {},
}) => {
  // Context para obter os textos do sistema
  const { texts } = useContext(TextLanguageContext);

  // Context para obter a função de leitura de texto
  const { startRecognition, escutar, localClique, palavrasJuntas } = useContext(
    SpeechRecognitionContext
  );

  const quillRef = useRef(null);

  const [text, setText] = useState(JSON.parse(JSON.stringify(defaultText)));

  const handleOnTextChange = (e) => {
    setText(e);
  };

  useEffect(() => {
    if (localClique == label) {
      handleOnTextChange(palavrasJuntas);
      handleOnMicChange();
    }
  }, [palavrasJuntas]);

  return (
    <Box className="relative">
      <ReactQuill
        value={text}
        onChange={handleOnTextChange}
        onBlur={() => saveProposal(text)}
        modules={modules}
        ref={quillRef}
      />
      <Box className="absolute top-1.5 right-1">
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
      </Box>
    </Box>
  );
};

export default QuillCustom;
